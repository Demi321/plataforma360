/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Config.config;
import Modelo.Grupos;
import java.io.IOException;
import static java.lang.System.out;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author moises
 */
public class BackupDirectorio {

    private static JSONObject BackupDirectorio = new JSONObject();

    public static void SetBackUpDirectorioNUll() {
        BackupDirectorio = new JSONObject();
    }

    public static JSONObject getBackupDirectorio() throws ParseException, IOException {

        if (!BackupDirectorio.containsKey("GruposAutomaticos")) {

            BackupDirectorio = Controlador.ControladorPOST.BackupDirectorio();
        }
        return BackupDirectorio;
    }

    public static void UpdateBackupDirectorio(JSONObject obj) throws ParseException, IOException {
        if (!BackupDirectorio.containsKey("GruposAutomaticos")) {
            BackupDirectorio = Controlador.ControladorPOST.BackupDirectorio();
        }
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(obj.toString());
        if (json.containsKey("ActualizaGPS")) {
            //Actualzo el estado se las sesiones que le pertenezcan al usuario en los Sockets web 
            if (json.containsKey("gpsOTS")) {
                 SocketEndPoint.actualizar_disponibilidad_sesion(json.get("idUsuarios_Movil").toString(), (Boolean)json.get("gpsOTS"));
            }
             
        }

        if (json.containsKey("ActualizarDatos")) {
            if ((boolean) json.get("ActualizarDatos")) {
                JSONArray json1 = (JSONArray) BackupDirectorio.get("integrantes");
                for (int i = 0; i < json1.size(); i++) {
                    JSONObject integrante = (JSONObject) json1.get(i);
                    if (integrante.get("idUsuarios_Movil").toString().equals(json.get("idUsuarios_Movil").toString())) {
                        integrante.put("nombre", json.get("nombre"));
                        integrante.put("apellido_paterno", json.get("apellido_paterno"));
                        integrante.put("apellido_materno", json.get("apellido_materno"));
                        break;
                    }
                }
            }
        }

        if (json.containsKey("ActualizarImg")) {
            if ((boolean) json.get("ActualizarImg")) {
                JSONArray json1 = (JSONArray) BackupDirectorio.get("integrantes");
                for (int i = 0; i < json1.size(); i++) {
                    JSONObject integrante = (JSONObject) json1.get(i);
                    if (integrante.get("idUsuarios_Movil").toString().equals(json.get("idUsuarios_Movil").toString())) {
                        integrante.put("img", json.get("img"));
                        integrante.put("icon", json.get("icon"));
                        break;
                    }
                }
            }
        }

        if (json.containsKey("SetFireBaseKey")) {
            if ((boolean) json.get("SetFireBaseKey")) {
                JSONArray json1 = (JSONArray) BackupDirectorio.get("integrantes");
                for (int i = 0; i < json1.size(); i++) {
                    JSONObject integrante = (JSONObject) json1.get(i);
                    if (integrante.get("idUsuarios_Movil").toString().equals(json.get("idUsuarios_Movil").toString())) {
                        integrante.replace("FireBaseKey", json.get("FireBaseKey"));
                        break;
                    }
                }
            }
        }

        if (json.containsKey("Registro")) {
            if ((boolean) json.get("Registro")) {
                System.out.println("En registro de updateBackupDirectorio --------->");
                boolean existe_gpo = false;
                boolean agregado = false;
                JSONArray GruposAutomaticos = (JSONArray) BackupDirectorio.get("GruposAutomaticos");
                for (int i = 0; i < GruposAutomaticos.size(); i++) {
                    JSONObject grupo = (JSONObject) GruposAutomaticos.get(i);
                    System.out.println("*********");
                    System.out.println(grupo.get("nombre"));
                    System.out.println(json.get("aliasServicio"));
                    System.out.println("*********");
                    if (grupo.get("nombre").toString().equals(json.get("aliasServicio").toString())) {
                        JSONArray integrantesGA = (JSONArray) grupo.get("integrantes");
                        for (int j = 0; j < integrantesGA.size(); j++) {
                            System.out.println(json.get("idUsuarios_Movil").toString());
                            System.out.println(integrantesGA.get(j));
                            System.out.println(integrantesGA.get(j).toString().equals(json.get("idUsuarios_Movil").toString()));
                            if (integrantesGA.get(j).toString().equals(json.get("idUsuarios_Movil").toString())) {
                                integrantesGA.remove(j);
                                j--;
                                break;
                            }

                        }
                        integrantesGA.add(json.get("idUsuarios_Movil"));
                        JSONArray integrantes = (JSONArray) BackupDirectorio.get("integrantes");
                        for (int j = 0; j < integrantes.size(); j++) {
                            JSONObject integrante = (JSONObject) integrantes.get(j);
                            System.out.println(json.get("idUsuarios_Movil").toString());
                            System.out.println(integrante.get("idUsuarios_Movil"));
                            System.out.println(integrante.get("idUsuarios_Movil").toString().equals(json.get("idUsuarios_Movil").toString()));
                            if (integrante.get("idUsuarios_Movil").toString().equals(json.get("idUsuarios_Movil").toString())) {

                                System.out.println("Actualizando integrante: " + json.get("idUsuarios_Movil"));
                                integrantes.remove(j);
                                j--;
                                integrantes.add(json);
                                agregado = true;
                                break;
                            }

                        }
                        if (!agregado) {
                            integrantes.add(json);
                        }

                        existe_gpo = true;
                        break;
                    }
                }
                System.out.println("Existe Grupo ?????");
                System.out.println(existe_gpo);
                if (!existe_gpo) {
                    JSONArray nuevoGrupo = Grupos.GrupoAutomatico(json.get("aliasServicio").toString());
                    GruposAutomaticos.add(nuevoGrupo.get(0));
                    JSONArray integrantes = (JSONArray) BackupDirectorio.get("integrantes");
                    integrantes.add(json);
                    existe_gpo = true;
                }

            }
        }
        if (json.containsKey("ActualizaRegistro")) {
            System.out.println("Se va a actualizar el registro en el backup de ---> " + json.get("idUsuarios_Movil").toString());
            if ((boolean) json.get("ActualizaRegistro")) {
                boolean agregado = false;
                JSONArray integrantes = (JSONArray) BackupDirectorio.get("integrantes");
                for (int i = 0; i < integrantes.size(); i++) {
                    JSONObject integrante = (JSONObject) integrantes.get(i);
                    if (integrante.get("idUsuarios_Movil").toString().equals(json.get("idUsuarios_Movil").toString())) {
                        String aServicio = integrante.get("aliasServicio").toString();
                        integrantes.remove(i);
                        i--;
                        System.out.println("Integrante sin valor de ActualizaRegistro");
                        integrantes.add(json);
                        JSONArray GruposAutomaticos = (JSONArray) BackupDirectorio.get("GruposAutomaticos");
                        for (int j = 0; j < GruposAutomaticos.size(); j++) {
                            JSONObject grupo = (JSONObject) GruposAutomaticos.get(j);
                            if (grupo.get("nombre").toString().equals(aServicio)) {
                                JSONArray integrantesGA = (JSONArray) grupo.get("integrantes");
                                for (int k = 0; k < integrantesGA.size(); k++) {
                                    if (integrantesGA.get(k).toString().equals(json.get("idUsuarios_Movil").toString())) {
                                        integrantesGA.remove(k);
                                        k--;
                                        break;
                                    }
                                }
                            }
                            if (grupo.get("nombre").toString().equals(json.get("aliasServicio").toString())) {
                                JSONArray integrantesGA = (JSONArray) grupo.get("integrantes");
                                integrantesGA.add(json.get("idUsuarios_Movil"));
                                agregado = true;
                            }
                            if (agregado) {
                                break;
                            }
                        }
                        break;
                    }
                }

            }
        }
        SocketEndPoint.EnviarNotificacion(json);
    }

    public static boolean EliminaDeGrupoAutomatico(JSONObject json) {
        boolean eliminado = false;
        System.out.println("En funcion EliminaDeGrupoAutomatico de " + config.getDEPENDENCIA());
        JSONArray GruposAutomaticos = (JSONArray) BackupDirectorio.get("GruposAutomaticos");
        for (int i = 0; i < GruposAutomaticos.size(); i++) {
            JSONObject grupo = (JSONObject) GruposAutomaticos.get(i);

            System.out.println(grupo.get("nombre"));
            System.out.println(json.get("aliasServicio"));
            if (grupo.get("nombre").toString().equals(json.get("aliasServicio").toString())) {
                JSONArray integrantesGA = (JSONArray) grupo.get("integrantes");
                for (int j = 0; j < integrantesGA.size(); j++) {
                    System.out.println(json.get("idUsuarios_Movil").toString());
                    if (integrantesGA.get(j).toString().equals(json.get("idUsuarios_Movil").toString())) {
                        integrantesGA.remove(j);
                        j--;
                        eliminado = true;
                        break;
                    }
                }
            }
        }
        return eliminado;
    }
}
