/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Config.config;

import static Controlador.BackupDirectorio.*;
import static Encriptacion.Main.generateString;

import Modelo.CredencialesDAO;
import Modelo.CredencialesVO;
import Modelo.Escalamiento;
import Modelo.GrupoDAO;
import Modelo.Grupos;
import Modelo.LoginDAO;
import Modelo.Post;
import Modelo.ProyectoDAO;
import Modelo.ProyectoVO;
import Modelo.Query;
import Modelo.RegistroLlamadasDAO;
import Modelo.RegistroLlamadasVO;
import Modelo.RegistroRutaDAO;
import Modelo.RegistroRutaVO;
import Modelo.ReporteDAO;
import Modelo.ReporteElemento;
import Modelo.UsuarioMovilDAO;
import Modelo.UsuarioMovilVO;
import Modelo.UsuarioSysDAO;
import Modelo.ValidarIP;
import Modelo.paises;
import Request.request;
import com.google.gson.Gson;
import com.opentok.exception.OpenTokException;

import java.io.IOException;

import static java.lang.Integer.parseInt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Moises Juarez
 */
@Controller
@Async

public class ControladorPOST {

    private static String Dependencia = config.getDEPENDENCIA();

    public static void setDependencia(String Dependencia) {
        ControladorPOST.Dependencia = Dependencia;
    }

    private JSONObject respuesta(boolean success, String message) {
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", success);
        respuesta.put("failure", !success);
        respuesta.put("mensaje", message);
        return respuesta;
    }

    @RequestMapping(value = "/NuevoGrupo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject NuevoGrupo(Model model, @RequestBody JSONObject json) {
        System.out.println("NuevoGrupo: " + Dependencia);
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return new JSONObject();
        }

        String idSys = json.get("idSys").toString();
        String grupo = json.get("grupo").toString();
        boolean existe = GrupoDAO.ExisteGrupo(idSys, grupo);
        //System.out.println("Grupo ya existe");
        JSONObject res = new JSONObject();
        if (!existe) {
            boolean agregado = GrupoDAO.NuevoGrupo(idSys, grupo);
            if (!agregado) {
                res.put("grupo", grupo);
                res.put("estado", "Error al añadir grupo.");
            } else {
                res.put("grupo", grupo);
                res.put("estado", "Agregado correctamente.");
            }
        } else {
            res.put("grupo", grupo);
            res.put("estado", "El grupo ya existe.");

        }

        return res;
    }

    @RequestMapping(value = "/NuevoIntegrante", method = RequestMethod.POST)
    public String NuevoIntegrante(Model model, @RequestParam("idUsuarios_Movil") String idUsuarios_Movil, @RequestParam("id_Grupo") String id_Grupo, @RequestParam("NombreGrupo") String NombreGrupo) {
        System.out.println("NuevoIntegrante: " + Dependencia);
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }

        boolean existe = UsuarioMovilDAO.ExisteUsuario(idUsuarios_Movil);
        if (existe) {
            boolean ExisteEnGrupo = GrupoDAO.ExisteUsuarioEnGrupo(id_Grupo, idUsuarios_Movil);
            if (!ExisteEnGrupo) {
                boolean agregado = GrupoDAO.NuevoIntegrante(id_Grupo, idUsuarios_Movil);
            }
        } else {
            System.out.println("El usuario no esta registrado en la aplicacion");
            model.addAttribute("Alerta", "El usuario no esta registrado en la aplicacion");
        }
        return "monitoreo/MonitoreoUnidades";
    }

    @RequestMapping(value = "/EliminarIntegrante", method = RequestMethod.POST)
    public String EliminarIntegrante(Model model, @RequestParam("idUsuarios_Movil") String idUsuarios_Movil, @RequestParam("id_Grupo") String id_Grupo) {

        System.out.println("EliminarIntegrante: " + Dependencia);
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        GrupoDAO.EliminarIntegrante(id_Grupo, idUsuarios_Movil);
        return "monitoreo/MonitoreoUnidades";
    }

    @RequestMapping(value = "/EliminarGrupo", method = RequestMethod.POST)
    public String EliminarGrupo(Model model, @RequestParam("id_Grupo") String id_Grupo, @RequestParam("NombreGrupo") String NombreGrupo) {
        System.out.println("EliminarGrupo: " + Dependencia);
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        boolean eliminado = GrupoDAO.EliminarGrupo(id_Grupo);
        if (eliminado) {
            String query = "Update grupos_usuario_movil set estado =0 where idgruposUsuarioSys =" + id_Grupo + ";";
            Query.update(query);
        }
        return "monitoreo/MonitoreoUnidades";
    }

    @RequestMapping(value = "/RutaHistorico", method = RequestMethod.POST)
    public String RutaHistorico(Model model, @RequestParam("idUsuarios_Movil") String idUsuario_Movil) throws ParseException, IOException {
        System.out.println("RutaHistorico: " + Dependencia);
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        model.addAttribute("idUsuario_Movil", idUsuario_Movil);
//        String query = "SELECT urlServicio FROM directorio where idUsuario ='" + idUsuario_Movil + "' AND activo=1";
//        JSONArray array = Query.execute(query);
//        if (!array.isEmpty()) {
//            String url = ((JSONObject) array.get(0)).get("urlServicio").toString();
//            String respuesta = request.POST(url + "/envioPerfil", idUsuario_Movil);
            JSONObject Usuario_Movil = new JSONObject();
//            JSONParser parser = new JSONParser();
//            Usuario_Movil.put("Usuarios_Movil", (JSONObject) parser.parse(respuesta));
            JSONObject json = new JSONObject();
            json.put("id360", idUsuario_Movil);
            json = request.POST(config.getURL_CONTROLADOR()+"API/cuenta360/perfil", json);
            String query = "SELECT Fecha FROM registro_rutas WHERE idUsuarios_Movil=\"" + idUsuario_Movil + "\";";
            JSONArray array = Query.execute(query);
            json.put("FechasRutas", array);
            Usuario_Movil.put("Usuarios_Movil", json);
           
            
            
            model.addAttribute("data", Usuario_Movil.toString().replace("\"", "&quot;"));
//        } else {
//            System.out.println("ERROR. Usuario no existe en el directorio: " + idUsuario_Movil);
//        }
        return "monitoreo/HistoricoRuta";
    }

    @RequestMapping(value = "/ActualizarProyecto", method = RequestMethod.POST)
    public String ActualizarProyecto(Model model, @RequestParam("apikey") String apikey, @RequestParam("heroku") String heroku, @RequestParam("FireBaseAuthorization") String FireBaseAuthorization) {
        System.out.println("ActualizarProyecto: " + Dependencia);
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        ProyectoVO p = new ProyectoVO("1", apikey, heroku, FireBaseAuthorization);
        boolean agregado = ProyectoDAO.ActualizarDatos(p);
        if (agregado) {
            // System.out.println("Se actualizaron los datos del proyecto.");
            model.addAttribute("Alerta", " Se actualizo correctamente.  <br> API KEY: " + apikey + "<br> URL Heroku: " + heroku + "<br> Firebase Authorization: " + FireBaseAuthorization + "<br>" + "<br>");

        } else {
            //System.out.println("Error al actualizar los datos del proyecto.");
            model.addAttribute("Alerta", " Error al actualizar. <br> ");
        }
        return "proyecto";
    }

    @RequestMapping(value = "/DatosPerfil", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String DatosPerfil(Model model, @RequestBody JSONObject json) throws IOException {
        System.out.println("DatosPerfil: " + Dependencia);
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        String respuesta = request.POST(json.get("urlServicio") + "/envioPerfil", json.get("id").toString());
        return respuesta;

    }

    @RequestMapping(value = "/envioPerfil", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public String envioPerfil(@RequestBody String id) {
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return "{}";
        }
        System.out.println("envioPerfil: " + Dependencia);
        JSONObject respuesta = new JSONObject();
        String query = "select idUsuarios_Movil,U.nombre,apellido_paterno,apellido_materno,correo,telefono,contacto_nombre,contacto_telefono,alergias,rh,fecha_nacimiento,genero,direccion,cp,condicion_medica,FireBaseKey,D.aliasServicio  from usuarios_movil U,directorio D where D.activo=1 and U.idUsuarios_Movil=D.idUsuario AND idUsuarios_Movil ='" + id + "';";
        respuesta = Query.select(query);
        if (respuesta != null) {
            respuesta.put("img", config.getPATH() + Dependencia + "/API/ConsultarImg/perfil/" + id);
            respuesta.put("icon", config.getPATH() + Dependencia + "/API/ConsultarImg/icono/" + id);
            query = "SELECT Fecha FROM registro_rutas WHERE idUsuarios_Movil=\"" + id + "\";";
            JSONArray array = Query.execute(query);
            respuesta.put("FechasRutas", array);
            System.out.println(respuesta);
        } else {
            respuesta = new JSONObject();
        }
        return respuesta.toString();
    }

    @RequestMapping(value = "/SetFireBaseKey", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String SetFireBaseKey(@RequestBody String json) throws ParseException, IOException, java.text.ParseException {
        System.out.println("SetFireBaseKey: " + Dependencia);
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return "{}";
        }
        Gson gson = new Gson();
        UsuarioMovilVO usr = gson.fromJson(json, UsuarioMovilVO.class);
        boolean Actualizado;
        Actualizado = UsuarioMovilDAO.SetFireBaseKey(usr.getIdUsuarios_Movil(), usr.getFireBaseKey());

        JSONObject firebase_json = new JSONObject();
        firebase_json.put("idUsuarios_Movil", usr.getIdUsuarios_Movil());
        firebase_json.put("FireBaseKey", usr.getFireBaseKey());
        firebase_json.put("SetFireBaseKey", true);
        UpdateBackupDirectorio(firebase_json);
        Modelo.Escalamiento.AgregarData(firebase_json);
//            JSONArray arrayServicios = config.getSuperiores();
//            for (int i = 0; i < arrayServicios.size(); i++) {
//                  request.POST(arrayServicios.get(i).toString() + "/API/UpdateBackupDirectorio", firebase_json.toString());
//            }
        if (Actualizado) {
            //System.out.println("Se actualizo FireBaseKey del usuario: " + usr.getIdUsuarios_Movil());
            return "{\"respuesta\":\"1\"}";
        } else {
            //System.out.println("Error al registrar FireBaseKey del Usuario: " + usr.getIdUsuarios_Movil());
            return "{\"respuesta\":\"0\"}";
        }
    }

    @RequestMapping(value = "/ActualizarDatos", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String ActualizarDatos(@RequestBody String json) throws ParseException, IOException, java.text.ParseException {
        System.out.println("ActualizarDatos: " + Dependencia);
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return "{}";
        }
        Gson gson = new Gson();
        UsuarioMovilVO usr = gson.fromJson(json, UsuarioMovilVO.class);
        boolean existe = UsuarioMovilDAO.ExisteUsuario(usr.getIdUsuarios_Movil());

        if (existe) {
            boolean actualizado = UsuarioMovilDAO.ActualizarDatos(usr);

            JSONObject datos_json = new JSONObject();
            datos_json.put("idUsuarios_Movil", usr.getIdUsuarios_Movil());
            datos_json.put("nombre", usr.getNombre());
            datos_json.put("apellido_paterno", usr.getApellido_paterno());
            datos_json.put("apellido_materno", usr.getApellido_materno());
            datos_json.put("ActualizarDatos", true);
            UpdateBackupDirectorio(datos_json);
            Modelo.Escalamiento.AgregarData(datos_json);
//                  JSONArray arrayServicios = config.getSuperiores();
//                  for (int i = 0; i < arrayServicios.size(); i++) {
//                        request.POST(arrayServicios.get(i).toString() + "/API/UpdateBackupDirectorio", datos_json.toString());
//                  }

            // System.out.println("Se actualizaron los datos del usuario: " + usr.getIdUsuarios_Movil() + " : " + usr.getNombre());
            return "{\"respuesta\":\"1\"}";
        } else {
            //System.out.println("Error al actualizar los datos del usuario.");
            return "{\"respuesta\":\"0\"}";
        }

    }

    @RequestMapping(value = "/ActualizarFoto", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String ActualizarImagen(@RequestBody String json) throws ParseException, IOException {
        System.out.println("ActualizarImagen: " + Dependencia);
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return "{}";
        }
        Gson gson = new Gson();
        UsuarioMovilVO usr = gson.fromJson(json, UsuarioMovilVO.class);
        boolean agregado = false;
        agregado = UsuarioMovilDAO.ActualizarFoto(usr);

        if (usr.getImg() != null && !"".equals(usr.getImg()) && !"null".equals(usr.getImg()) && usr.getIcon() != null && !"".equals(usr.getIcon()) && !"null".equals(usr.getIcon())) {
//            JSONObject img_json = new JSONObject();
//            img_json.put("idUsuarios_Movil", usr.getIdUsuarios_Movil());
//            img_json.put("img", usr.getImg());
//            img_json.put("icon", usr.getIcon());
//            img_json.put("ActualizarImg", true);
//            UpdateBackupDirectorio(img_json);
//            
//            JSONArray arrayServicios = config.getSuperiores();
//            for (int i = 0; i < arrayServicios.size(); i++) {
//                System.out.println(arrayServicios.get(i));
//                request.POST(arrayServicios.get(i).toString() + "/API/UpdateBackupDirectorio", img_json.toString());
//            }
        }

        if (agregado) {
            //System.out.println("Se actualizo la foto del usuario: " + usr.getIdUsuarios_Movil() + " : " + usr.getNombre());
            return "{\"respuesta\":\"1\"}";
        } else {
            //System.out.println("Error al actualizar la foto del usuario.");
            return "{\"respuesta\":\"0\"}";
        }
    }

    @RequestMapping(value = "/RegistrarllamadaAtendida", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String Registro_llamadas(@RequestBody String json) throws ParseException {
        System.out.println("Registro_llamadas: " + Dependencia);
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return "Failure";
        }
        boolean agregado = RegistroLlamadasDAO.ActualizarRegistroLlamada(json);
        if (agregado) {
            //  System.out.println("Se actualizo la informacion de la llamada en la base de datos.");
            return json;
        } else {
            //  System.out.println("Error al registrar sesion.");
            return "Failure";
        }
    }

    @RequestMapping(value = "/RegistrarllamadaFinalizada", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String RegistrarllamadaFinalizada(@RequestBody String json) throws ParseException {
        System.out.println("RegistrarllamadaFinalizada: " + Dependencia);
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return "Failure";
        }
        boolean agregado = RegistroLlamadasDAO.LlamadaFinalizada(json);
        if (agregado) {
            //  System.out.println("Se actualizo la informacion de la llamada en la base de datos.");
            return json;
        } else {
            //  System.out.println("Error al registrar sesion.");
            return "Failure";
        }

    }

    @RequestMapping(value = "/Registro_ruta", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String Registro_ruta(@RequestBody String json) {
        System.out.println("Registro_ruta: " + Dependencia);
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return "{}";
        }
        Gson gson = new Gson();
        RegistroLlamadasVO reg = gson.fromJson(json, RegistroLlamadasVO.class);
        boolean agregado = RegistroLlamadasDAO.agregarRuta(reg);
        if (agregado) {
            ;
            // System.out.println("Se registro la ruta en la base de datos.");
            return "{\"Success\":0}";
        } else {
            // System.out.println("Error al registrar la ruta en la base de datos.");
            return "{\"Failure\":0}";
        }

    }

    @RequestMapping(value = "/SOS/{tipo_usuario}/{tipo_servicio}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String SOS(@RequestBody String json, @PathVariable("tipo_usuario") String tipo_usuario, @PathVariable("tipo_servicio") String tipo_servicio) throws IOException, ParseException, JSONException, OpenTokException {
        System.out.println("Llamada SOS de la dependencia ---" + Dependencia + "---");
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return "Failure";
        }
        if (json.contains("id360")) {

            /**
             * ***PARCHE***
             */
            System.out.println("*********SOS-ID360*********");
            System.out.println(tipo_servicio);
            System.out.println(tipo_usuario);
            System.out.println(json);

            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(json);
            JSONObject estadoUsr = new JSONObject();
            //Solicitud del perfil
            JSONObject perfil = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfil", jsonObj);
            if ((Boolean) perfil.get("success")) {
                //Adecuacion de llaves 
                perfil.put("idUsuarios_Movil", perfil.get("id360"));
                perfil.put("FireBaseKey", perfil.get("firebasekey"));

                //Revision de coordenadas 
                if (jsonObj.containsKey("latitud")) {
                    if (jsonObj.get("latitud") == null) {
                        jsonObj.put("latitud", "0");
                    }
                } else {
                    jsonObj.put("latitud", "0");
                }
                if (jsonObj.containsKey("longitud")) {
                    if (jsonObj.get("longitud") == null) {
                        jsonObj.put("longitud", "0");
                    }
                } else {
                    jsonObj.put("longitud", "0");
                }

                //Solicitud de ticket de llamada
                JSONObject ticket_settings = new JSONObject();
                ticket_settings.put("origen", config.getPATH() + Dependencia);
                ticket_settings.put("fecha", jsonObj.get("fecha"));
                ticket_settings.put("hora", jsonObj.get("hora"));
                JSONObject JsonTicket = request.POST(config.getURL_CONTROLADOR() + "API/ticket", ticket_settings);

                //Revision del modo de llamada 
                JSONObject modo = Query.select("SELECT idModo_Llamada AS id,nombre,clave FROM modo_llamada WHERE idModo_Llamada ='" + jsonObj.get("modo") + "';");

                //Modulacion de credemciales de opentok
                JSONObject OpenTok = new JSONObject();
                OpenTok.put("apikey", jsonObj.get("apikey"));
                OpenTok.put("token", jsonObj.get("token"));
                OpenTok.put("sesion", jsonObj.get("idsession"));

                //Modulacion del registro de la llamada
                JSONObject registro = new JSONObject();
                registro.put("idLlamada", JsonTicket.get("ticket"));
                registro.put("fecha", jsonObj.get("fecha"));
                registro.put("hora", jsonObj.get("hora"));
                registro.put("latitud", jsonObj.get("latitud"));
                registro.put("longitud", jsonObj.get("longitud"));
                registro.put("modo", jsonObj.get("modo"));
                registro.put("idUsuarios_Movil", jsonObj.get("idUsuarios_Movil"));

                registro.put("tipo_usuario", tipo_usuario);
                registro.put("tipo_servicio", tipo_servicio);

                //Solicitud de gravacion de video
                String url = Request.request.POST(config.getURL_CONTROLADOR() + "restOpentok/ArchiveSession", OpenTok.toString());
                registro.put("url", url);

                //Construccion de JSON de notificacion
                JSONObject Notificacion = new JSONObject();

                jsonObj.put("idLlamada", JsonTicket.get("ticket"));
                jsonObj.put("ruta_video", url);

                Notificacion.put("RegistroLlamada", jsonObj);
                Notificacion.put("Usuarios_Movil", perfil);
                Notificacion.put("Credenciales", OpenTok);
                Notificacion.put("origen", Dependencia);
                Notificacion.put("idconnection", jsonObj.get("connectionid"));
                Notificacion.put("horaServidor", Modelo.Escalamiento.getHora());
                Notificacion.put("fechaServidor", Modelo.Escalamiento.getFecha());
                Notificacion.put("Modo", modo);
                Notificacion.put("llamadaSOS", true);
                Notificacion.put("tipo_servicio", tipo_servicio);
                Notificacion.put("tipo_usuario", tipo_usuario);

                //Notificacion por socket
                SocketEndPoint.NotificarLlamada(Notificacion);

                //Solicitud de guardado de registro
                if (!RegistroLlamadasDAO.nuevaLlamada(registro, OpenTok)) {
                    System.out.println("Error al registrar la llamada");
                    estadoUsr.put("error", 1);
                    estadoUsr.put("succes", 0);
                    estadoUsr.put("text", "Error al registrar la llamada");
                    return estadoUsr.toString();
                }

                estadoUsr.put("error", 0);
                estadoUsr.put("succes", 1);
                return estadoUsr.toString();
            } else {
                //usuario no registrado
                System.out.println("Error el usuario se encuentra deshabilitado.");
                estadoUsr.put("error", 1);
                estadoUsr.put("succes", 0);
                estadoUsr.put("text", "Error el usuario se encuentra deshabilitado");
                return estadoUsr.toString();
            }

        } else {

            System.out.println("sos******");
            System.out.println(tipo_servicio);
            System.out.println(tipo_usuario);
            System.out.println(json);
            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(json);
            JSONObject jsonUsr = new JSONObject();
            jsonUsr.put("idUsuario", jsonObj.get("idUsuarios_Movil"));
            System.out.println(jsonUsr);
            JSONObject estadoUsr = EstatusUsuario(jsonUsr);
            if (config.isDEPENDENCIA_BASE()) {
                estadoUsr.put("activo", true);
            }
            if (tipo_usuario.equals("10")
                    || tipo_usuario.equals("11")) {
                estadoUsr.put("activo", true);
            }
            estadoUsr.put("activo", true);
            System.out.println(estadoUsr);
            if ((boolean) estadoUsr.get("activo")) {
                String ticket = Post.GenerarTicket(config.getPATH() + Dependencia, jsonObj.get("fecha").toString(), jsonObj.get("hora").toString());
                JSONObject JsonTicket = (JSONObject) parser.parse(ticket);

                if (JsonTicket.get("ticket") != null) {
                    String idLlamada = JsonTicket.get("ticket").toString();
                    System.out.println("Id de la llamada: " + idLlamada);
                    JSONObject modo = UsuarioMovilDAO.ModoLlamada(jsonObj.get("modo").toString());

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
                    DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
                    Date date = new Date();

                    if (jsonObj.containsKey("latitud")) {
                        if (jsonObj.get("latitud") == null) {
                            jsonObj.put("latitud", "0");
                        }
                    } else {
                        jsonObj.put("latitud", "0");
                    }
                    if (jsonObj.containsKey("longitud")) {
                        if (jsonObj.get("longitud") == null) {
                            jsonObj.put("longitud", "0");
                        }
                    } else {
                        jsonObj.put("longitud", "0");
                    }
//                RegistroLlamadasVO reg = new RegistroLlamadasVO(idLlamada, 
//                        jsonObj.get("fecha").toString(), jsonObj.get("hora").toString(), 
//                        jsonObj.get("latitud").toString(), jsonObj.get("longitud").toString(), 
//                        jsonObj.get("modo").toString(), jsonObj.get("idUsuarios_Movil").toString());
                    String query_url = config.getURL_CONTROLADOR() + "restOpentok/ArchiveSession";
                    JSONObject OpenTok = new JSONObject();
                    OpenTok.put("apikey", jsonObj.get("apikey"));
                    OpenTok.put("token", jsonObj.get("token"));
                    OpenTok.put("sesion", jsonObj.get("idsession"));

                    String url = Request.request.POST(query_url, OpenTok.toString());
                    System.out.println(url);
                    JSONObject registro = new JSONObject();
                    registro.put("idLlamada", idLlamada);
                    registro.put("fecha", jsonObj.get("fecha"));
                    registro.put("hora", jsonObj.get("hora"));
                    registro.put("latitud", jsonObj.get("latitud"));
                    registro.put("longitud", jsonObj.get("longitud"));
                    registro.put("modo", jsonObj.get("modo"));
                    registro.put("idUsuarios_Movil", jsonObj.get("idUsuarios_Movil"));
                    registro.put("url", url);
                    registro.put("tipo_usuario", tipo_usuario);
                    registro.put("tipo_servicio", tipo_servicio);
//                if (RegistroLlamadasDAO.nuevaLlamada(reg, url, OpenTok)) {
                    if (RegistroLlamadasDAO.nuevaLlamada(registro, OpenTok)) {

                        JSONObject Usuario = new JSONObject();

                        if (config.isDEPENDENCIA_BASE()
                                || tipo_usuario.equals("10")
                                || tipo_usuario.equals("11")) {
                            Usuario = (JSONObject) parser.parse(Post.ConsultarPerfil(jsonObj.get("idUsuarios_Movil").toString(), config.getURL_CONTROLADOR() + "API/ConsultaPerfil"));
                            System.out.println(Usuario.get("apellido_paterno"));
                        } else {
                            Usuario = (JSONObject) parser.parse(envioPerfil(jsonObj.get("idUsuarios_Movil").toString()));
                        }
                        if (!Usuario.containsKey("idUsuarios_Movil")) {
                            Usuario = (JSONObject) parser.parse(Post.ConsultarPerfil(jsonObj.get("idUsuarios_Movil").toString(), config.getURL_CONTROLADOR() + "API/ConsultaPerfil"));

                        }
                        JSONObject Notificacion = new JSONObject();

                        jsonObj.put("idLlamada", idLlamada);
                        jsonObj.put("ruta_video", url);

                        Notificacion.put("RegistroLlamada", jsonObj);
                        Notificacion.put("Usuarios_Movil", Usuario);
                        Notificacion.put("Credenciales", OpenTok);
                        Notificacion.put("origen", Dependencia);
                        Notificacion.put("idconnection", jsonObj.get("connectionid"));
                        Notificacion.put("horaServidor", hourFormat.format(date));
                        Notificacion.put("fechaServidor", dateFormat.format(date));
                        Notificacion.put("Modo", modo);
                        Notificacion.put("llamadaSOS", true);
                        Notificacion.put("tipo_servicio", tipo_servicio);
                        Notificacion.put("tipo_usuario", tipo_usuario);
                        //tipo_usuario
//                              SocketEndPoint.NotificarLlamada(Notificacion);
                        SocketEndPoint.NotificarLlamada(Notificacion);
                        //SocketEndPoint.EnviarNotificacioMunicipio(Notificacion, jsonObj.get("idMunicipio").toString());
                        estadoUsr.put("error", 0);
                        estadoUsr.put("succes", 1);
                        return estadoUsr.toString();
                    } else {
                        System.out.println("Error al registrar la llamada");
                        estadoUsr.put("error", 1);
                        estadoUsr.put("succes", 0);
                        estadoUsr.put("text", "Error al registrar la llamada");
                        return estadoUsr.toString();
                    }

                } else {
                    System.out.println("Error al generar un ticket para la llamada.");
                    estadoUsr.put("error", 1);
                    estadoUsr.put("succes", 0);
                    estadoUsr.put("text", "Error al generar un ticket para la llamada");
                    return estadoUsr.toString();
                }
            } else {
                System.out.println("Error el usuario se encuentra deshabilitado.");
                estadoUsr.put("error", 1);
                estadoUsr.put("succes", 0);
                estadoUsr.put("text", "Error el usuario se encuentra deshabilitado");
                return estadoUsr.toString();
            }

        }

    }

    @RequestMapping(value = "/insertarllamada", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String insertarllamada(@RequestBody String json) throws IOException, ParseException {
        System.out.println("Llamada insertada en: --" + Dependencia + "--");

        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return "Failure";
        }
        JSONParser parser = new JSONParser();
        JSONObject data = (JSONObject) parser.parse(json);
        JSONObject reg = (JSONObject) data.get("RegistroLlamada");
        JSONObject opentok = (JSONObject) data.get("Credenciales");
        if (reg.containsKey("latitud")) {
            if (reg.get("latitud") == null) {
                reg.put("latitud", "0");
            }
        } else {
            reg.put("latitud", "0");
        }
        if (reg.containsKey("longitud")) {
            if (reg.get("longitud") == null) {
                reg.put("longitud", "0");
            }
        } else {
            reg.put("longitud", "0");
        }
        RegistroLlamadasVO registroLlamada = new RegistroLlamadasVO(reg.get("idLlamada").toString(), reg.get("fecha").toString(), reg.get("hora").toString(), reg.get("latitud").toString(), reg.get("longitud").toString(), reg.get("modo").toString(), reg.get("idUsuarios_Movil").toString());
        String url = reg.get("ruta_video").toString();

        boolean agregado = RegistroLlamadasDAO.nuevaLlamada(registroLlamada, url, opentok);

        if (agregado) {
            SocketEndPoint.NotificarLlamada(data);
            System.out.println("Se registro llamada: " + registroLlamada.getIdUsuarios_Movil() + " en " + Dependencia + "");

        } else {
            if (RegistroLlamadasDAO.ConsultarRegistro_Lamadas(registroLlamada)) {
                System.out.println("La llamada ya estaba registrada");
                return "{\"succes\":1}";
            } else {
                System.out.println("Error al registrar la llamada.");
                return "Failure";
            }

        }

        return "{\"succes\":1}";
    }

    @RequestMapping(value = "/insertarllamada/{tipo_usuario}/{tipo_servicio}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String insertarllamada(@RequestBody String json, @PathVariable("tipo_usuario") String tipo_usuario, @PathVariable("tipo_servicio") String tipo_servicio) throws IOException, ParseException {
        System.out.println("Llamada insertada en: --" + Dependencia + "-- con tipo_usuario = " + tipo_usuario + " y tipo_servicio = " + tipo_servicio);

        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return "Failure";
        }
        JSONParser parser = new JSONParser();
        JSONObject data = (JSONObject) parser.parse(json);
        JSONObject reg = (JSONObject) data.get("RegistroLlamada");
        JSONObject opentok = (JSONObject) data.get("Credenciales");
        if (reg.containsKey("latitud")) {
            if (reg.get("latitud") == null) {
                reg.put("latitud", "0");
            }
        } else {
            reg.put("latitud", "0");
        }
        if (reg.containsKey("longitud")) {
            if (reg.get("longitud") == null) {
                reg.put("longitud", "0");
            }
        } else {
            reg.put("longitud", "0");
        }
//        RegistroLlamadasVO registroLlamada = new RegistroLlamadasVO(reg.get("idLlamada").toString(), reg.get("fecha").toString(), reg.get("hora").toString(), reg.get("latitud").toString(), reg.get("longitud").toString(), reg.get("modo").toString(), reg.get("idUsuarios_Movil").toString());
        String url = reg.get("ruta_video").toString();
        JSONObject registro = new JSONObject();
        registro.put("idLlamada", reg.get("idLlamada"));
        registro.put("fecha", reg.get("fecha"));
        registro.put("hora", reg.get("hora"));
        registro.put("latitud", reg.get("latitud"));
        registro.put("longitud", reg.get("longitud"));
        registro.put("modo", reg.get("modo"));
        registro.put("idUsuarios_Movil", reg.get("idUsuarios_Movil"));
        registro.put("url", url);
        registro.put("tipo_usuario", tipo_usuario);
        registro.put("tipo_servicio", tipo_servicio);

//        boolean agregado = RegistroLlamadasDAO.nuevaLlamada(registroLlamada, url, opentok);
        boolean agregado = RegistroLlamadasDAO.nuevaLlamada(registro, opentok);

        if (agregado) {
            data.put("tipo_usuario", tipo_usuario);
            data.put("tipo_servicio", tipo_servicio);
            SocketEndPoint.NotificarLlamada(data);
            System.out.println("Se registro llamada: " + registro.get("idUsuarios_Movil") + " en " + Dependencia + " con tipo_usuario = " + tipo_usuario + " y tipo_servicio = " + tipo_servicio);

        } else {
            if (RegistroLlamadasDAO.ConsultarRegistro_Lamadas(registro)) {
                System.out.println("La llamada ya estaba registrada");
                return "{\"succes\":1}";
            } else {
                System.out.println("Error al registrar la llamada.");
                return "Failure";
            }

        }

        return "{\"succes\":1}";
    }

    //    @RequestMapping(value = "/insertarllamadaEspecifica", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
//    @ResponseBody
//    public String insertarllamadaEspecifica(@RequestBody String tipo_usuario, @RequestBody String tipo_servicio, @RequestBody String json) throws IOException, ParseException {
//        System.out.println("Llamada insertada en: --" + Dependencia + "-- con tipo_usuario = " + tipo_usuario + " y tipo_servicio = " + tipo_servicio);
//
//        if (config.getInit() == null) {
//            System.out.println("Proyecto no inicializado");
//            return "Failure";
//        }
//        JSONParser parser = new JSONParser();
//        JSONObject data = (JSONObject) parser.parse(json);
//        JSONObject reg = (JSONObject) data.get("RegistroLlamada");
//        JSONObject opentok = (JSONObject) data.get("Credenciales");
//        if (reg.containsKey("latitud")) {
//            if (reg.get("latitud") == null) {
//                reg.put("latitud", "0");
//            }
//        } else {
//            reg.put("latitud", "0");
//        }
//        if (reg.containsKey("longitud")) {
//            if (reg.get("longitud") == null) {
//                reg.put("longitud", "0");
//            }
//        } else {
//            reg.put("longitud", "0");
//        }
////        RegistroLlamadasVO registroLlamada = new RegistroLlamadasVO(reg.get("idLlamada").toString(), reg.get("fecha").toString(), reg.get("hora").toString(), reg.get("latitud").toString(), reg.get("longitud").toString(), reg.get("modo").toString(), reg.get("idUsuarios_Movil").toString());
//        String url = reg.get("ruta_video").toString();
//        JSONObject registro = new JSONObject();
//        registro.put("idLlamada", reg.get("idLlamada"));
//        registro.put("fecha", reg.get("fecha"));
//        registro.put("hora", reg.get("hora"));
//        registro.put("latitud", reg.get("latitud"));
//        registro.put("longitud", reg.get("longitud"));
//        registro.put("modo", reg.get("modo"));
//        registro.put("idUsuarios_Movil", reg.get("idUsuarios_Movil"));
//        registro.put("url", url);
//        registro.put("tipo_usuario", tipo_usuario);
//        registro.put("tipo_servicio", tipo_servicio);
//
//       
//
////        boolean agregado = RegistroLlamadasDAO.nuevaLlamada(registroLlamada, url, opentok);
//        boolean agregado = RegistroLlamadasDAO.nuevaLlamada(registro, opentok);
//
//        if (agregado) {
//            data.put("tipo_usuario", tipo_usuario);
//            data.put("tipo_servicio", tipo_servicio);
//            SocketEndPoint.NotificarLlamada(data);
//            System.out.println("Se registro llamada: " + registro.get("idUsuarios_Movil") + " en " + Dependencia + " con tipo_usuario = " + tipo_usuario + " y tipo_servicio = " + tipo_servicio);
//
//        } else {
//            if (RegistroLlamadasDAO.ConsultarRegistro_Lamadas(registro)) {
//                System.out.println("La llamada ya estaba registrada");
//                return "{\"succes\":1}";
//            } else {
//                System.out.println("Error al registrar la llamada.");
//                return "Failure";
//            }
//
//        }
//
//        return "{\"succes\":1}";
//    }
    @RequestMapping(value = "/NotDependencias", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String NotDependencias(@RequestBody String json) throws ParseException, IOException {
        System.out.println("NotDependencias: " + Dependencia);
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return "{}";
        }
        System.out.println("Notificando dependencia");
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        JSONArray url = (JSONArray) jsonObj.get("url");
        for (int i = 0; i < url.size(); i++) {
            String string = url.get(i).toString();
            String[] split = string.split("/");
            String tipo_usuario = split[4];
            String tipo_servicio = split[5];
            split = string.split(Dependencia);
            String path = split[0] + Dependencia + "/insertarllamada/" + tipo_usuario + "/" + tipo_servicio;
            System.out.println(path);
            request.POST(path, jsonObj.get("data").toString());
        }
        return "{\"succes\":1}";
    }

    //    @RequestMapping(value = "/NotDependenciasEspecificas", method = RequestMethod.POST, consumes = "application/json")
//    @ResponseBody
//    public String NotDependenciasEspecificas(@RequestBody JSONObject json) throws ParseException, IOException {
//        System.out.println("NotDependenciasEspecificas: " + Dependencia);
//        if (config.getInit() == null) {
//            System.out.println("Proyecto no inicializado");
//            return "{}";
//        }
//        JSONArray usuarios = (JSONArray) json.get("usuarios");
//        for (int i = 0; i < usuarios.size(); i++) {
//            JSONObject jsonObj = (JSONObject) usuarios.get(i);
//            insertarllamadaEspecifica(jsonObj.get("tipo_usuario").toString(), jsonObj.get("tipo_servicio").toString(), json.get("data").toString());
//        }
//
//        return "{\"succes\":1}";
//    }
    @RequestMapping(value = "/DependenciasNotificadas", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String DependenciasNotificadas(@RequestBody String json) throws ParseException, IOException /*throws ParseException*/ {
        System.out.println("DependenciasNotificadas: --" + Dependencia + "--");
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return "{}";
        }
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);

        if (!"".equals(jsonObj.get("info").toString())) {
            String query;
            //Valido que el id de llamada pertenesca a un registro de llamada entrante 
            query = "SELECT idRegistro_Llamadas FROM registro_llamadas WHERE idRegistro_Llamadas=\"" + jsonObj.get("id") + "\";";
            if (Query.select(query) != null) {
                System.out.println("Enviando POST desde: " + config.getDEPENDENCIA());
                String response = request.POST(config.getURL_CONTROLADOR() + "API/DependenciasNotificadas", json);
                System.out.println(response);
            } else {
                //El id de llamada pertenece posiblemente a un reporte de elemento
                query = "SELECT dependencias FROM ReporteElemento where id = '" + jsonObj.get("id") + "';";
                JSONObject dependencias = Query.select(query);

                String[] Infodep = jsonObj.get("info").toString().split(",");
                if (dependencias.get("dependencias") != null) {
                    String[] dep = dependencias.get("dependencias").toString().split(",");
                    String DepFinales = "";
                    for (int i = 0; i < Infodep.length; i++) {
                        boolean result = Arrays.stream(dep).anyMatch(Infodep[i]::equals);
                        if (!result) {
                            DepFinales += Infodep[i];
                        }
                    }
                    jsonObj.put("info", DepFinales);
                    if (!"".equals(DepFinales)) {
                        query = "UPDATE `ReporteElemento` SET `dependencias` =CONCAT(if(dependencias is null ,'',CONCAT(dependencias,',')),'" + jsonObj.get("info") + "') WHERE `id`='" + jsonObj.get("id") + "';";
                        Query.update(query);
                        for (int i = 0; i < Infodep.length; i++) {
                            String[] url = Infodep[i].split("\\|");
                            request.POST(url[1] + "/ActualizaDependencias", jsonObj.toString());
                        }
                        String response = request.POST(config.getURL_CONTROLADOR() + "API/DependenciasNotificadas", jsonObj.toString());
                    }
                } else {
                    query = "UPDATE `ReporteElemento` SET `dependencias` =CONCAT(if(dependencias is null ,'',CONCAT(dependencias,',')),'" + jsonObj.get("info") + "') WHERE `id`='" + jsonObj.get("id") + "';";
                    Query.update(query);
                    for (int i = 0; i < Infodep.length; i++) {
                        String[] url = Infodep[i].split("\\|");
                        request.POST(url[1] + "/ActualizaDependencias", jsonObj.toString());
                    }
                    System.out.println("Enviando POST desde: " + config.getDEPENDENCIA());
                    String response = request.POST(config.getURL_CONTROLADOR() + "API/DependenciasNotificadas", jsonObj.toString());
                }
            }

        }

        return "{\"succes\":1}";
    }

    @RequestMapping(value = "/envioID", method = RequestMethod.POST)
    public String envioID(Model model,
            @RequestParam("id") String id,
            @RequestParam("data") String data,
            @RequestParam("tipo_usuario") String tipo_usuario, @RequestParam("tipo_servicio") String tipo_servicio) throws ParseException {
        System.out.println("envioID: " + Dependencia);
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(data);
        JSONObject Credenciales = (JSONObject) parser.parse(json.get("Credenciales").toString());
        JSONObject RegistroLlamada = (JSONObject) parser.parse(json.get("RegistroLlamada").toString());

        String Data = data.replace("\"", "&quot;");
        model.addAttribute("data", Data);
        model.addAttribute("id", id);
        model.addAttribute("idLlamada", RegistroLlamada.get("idLlamada"));
        model.addAttribute("idStream", json.get("idconnection"));
        model.addAttribute("apikey", Credenciales.get("apikey"));
        model.addAttribute("session", Credenciales.get("sesion"));
        model.addAttribute("token", Credenciales.get("token"));
        model.addAttribute("origen", json.get("origen"));
        model.addAttribute("modo", RegistroLlamada.get("modo"));
        model.addAttribute("modoLlamada", json.get("Modo"));

        String dependencias = ProyectoDAO.ConsultarDependenciasAsociadas(tipo_usuario, tipo_servicio);
        model.addAttribute("Dependencias", dependencias);

        return "sos/Operador";
    }

    @RequestMapping(value = "/OperadorEmpresa", method = RequestMethod.POST)
    public String OperadorEmpresa(
            Model model,
            /*@RequestParam("id") String id,*/
            @RequestParam("origen") String origen,
            @RequestParam("idLlamada") String idLlamada,
            @RequestParam("apikey") String apikey,
            @RequestParam("session") String session,
            @RequestParam("token") String token,
            @RequestParam("fecha") String fecha,
            @RequestParam("hora") String hora,
            @RequestParam("idSys") String idSys,
            @RequestParam("integrantes") String integrantes,
            @RequestParam("tipo_usuario") String tipo_usuario,
            @RequestParam("tipo_servicio") String tipo_servicio,
            @RequestParam("modo") String modo) {

        System.out.println("LLAMADA SALIENTE:" + Dependencia + " con tipo_usuario: " + tipo_usuario + " y tipo_servicio: " + tipo_servicio);
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        String query = "select * from `registro_llamadas_salientes` where `idLlamada`= '" + idLlamada + "' AND `idOperador`= '" + idSys + "';";
        JSONArray array = Query.execute(query);

        System.out.println(integrantes);
        String[] integrantesArray = integrantes.split(",");

        JSONObject jsonIntegrantes = new JSONObject();
        for (String integrantesArray1 : integrantesArray) {
            jsonIntegrantes.put(integrantesArray1, new JSONObject());
        }

        if (array.isEmpty()) {
            System.out.println(integrantes);

            query = "INSERT INTO `registro_llamadas_salientes` (`idLlamada`,`idOperador`, `fecha_notificacion`, `hora_notificacion`, `apikey`, `sesion`, `modo_llamada`,`tipo_usuario`,`tipo_servicio`) "
                    + "VALUES ('" + idLlamada + "', '" + idSys + "', '" + fecha + "', '" + hora + "', '" + apikey + "', '" + session + "', '" + modo + "', '" + tipo_usuario + "', '" + tipo_servicio + "');";
            Query.insert(query);
        }

        JSONObject credenciales = new JSONObject();
        credenciales.put("apikey", apikey);
        credenciales.put("sesion", session);
        credenciales.put("token", token);

        JSONObject registroLlamada = new JSONObject();
        registroLlamada.put("fecha", fecha);
        registroLlamada.put("hora", hora);
        registroLlamada.put("idLlamada", idLlamada);
        registroLlamada.put("idOperador", idSys);
        registroLlamada.put("modo", modo);
        registroLlamada.put("participantes", jsonIntegrantes);

        query = "SELECT * FROM modo_llamada WHERE idModo_Llamada=\"" + modo + "\";";
        JSONObject Modo = (JSONObject) Query.execute(query).get(0);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();

        JSONObject Notificacion = new JSONObject();
        Notificacion.put("RegistroLlamada", registroLlamada);
        Notificacion.put("Credenciales", credenciales);
        Notificacion.put("origen", Dependencia);
        Notificacion.put("horaServidor", hourFormat.format(date));
        Notificacion.put("fechaServidor", dateFormat.format(date));
        Notificacion.put("Modo", Modo);

        model.addAttribute("data", Notificacion.toString().replace("\"", "&quot;"));

        return "monitoreo/LlamadaGrupal";
    }

    @RequestMapping(value = "/BuscarGrupos", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String BuscarGrupos(@RequestBody String json) throws ParseException {
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return "[]";
        }
        System.out.println("BuscarGrupos: " + Dependencia);
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        return Query.execute("SELECT idgruposUsuarioSys as id, nombre FROM grupos_usuario_sys WHERE idUsuario_Sys=" + jsonObj.get("idUsuario_Sys") + " AND estado='1';").toString();
    }

    @RequestMapping(value = "/IntegrantesGrupos", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JSONObject IntegrantesGrupos(@RequestBody String json) throws ParseException {

        System.out.println("IntegrantesGrupos: " + Dependencia);
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return new JSONObject();
        }
        JSONArray array_integrantes = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        JSONArray integrantes = Query.execute("SELECT idUsuarios_Movil FROM grupos_usuario_movil where idgruposUsuarioSys=" + jsonObj.get("id_Grupo") + " AND estado='1';"); // GrupoDAO.BuscarIntegrantes(grupo.getId_Grupo());

        for (int i = 0; i < integrantes.size(); i++) {

            JSONObject elemento = new JSONObject();
            elemento.put("id", ((JSONObject) integrantes.get(i)).get("idUsuarios_Movil"));
            String query = "SELECT * FROM directorio WHERE  idUsuario=" + ((JSONObject) integrantes.get(i)).get("idUsuarios_Movil") + " and activo=1 ;";
            System.out.println(query);
            //JSONArray user = Query.execute("SELECT nombre, apellido_paterno, apellido_materno FROM usuarios_movil WHERE  idUsuarios_Movil=" + ((JSONObject) integrantes.get(i)).get("idUsuarios_Movil") + ";");
            JSONArray user = Query.execute(query);
            System.out.println(user);
            if (user.size() > 0) {
                elemento.put("nombre", ((JSONObject) user.get(0)).get("Nombre"));
                elemento.put("apellido_paterno", ((JSONObject) user.get(0)).get("apellido_paterno"));
                elemento.put("apellido_materno", ((JSONObject) user.get(0)).get("apellido_materno"));
                elemento.put("urlServicio", ((JSONObject) user.get(0)).get("urlServicio"));
            }

            array_integrantes.add(elemento);

        }

        jsonObj.put("integrantes", array_integrantes);
        return jsonObj;

    }

    @RequestMapping(value = "/ConsultaCredenciales", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String ConsultaCredenciales(@RequestBody String json) {
        System.out.println("ConsultaCredenciales: " + Dependencia);
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return "{}";
        }
        Gson gson = new Gson();
        CredencialesVO obj = gson.fromJson(json, CredencialesVO.class
        );
        obj = CredencialesDAO.ConsultaCredenciales(obj);
        return gson.toJson(obj);

    }

    @RequestMapping(value = "/login_local", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String login_local(HttpSession sesion, Model model, @RequestBody String json) throws ParseException {
        System.out.println("login: " + Dependencia);
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            model.addAttribute("Alerta", " Proyecto no inicializado. <br><br> ");
            return "";
        }
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        String usr = jsonObj.get("Usuario").toString();
        String pwd = jsonObj.get("Password").toString();
        JSONObject usuario = LoginDAO.login(usr, pwd);
        String query = "SELECT * FROM servicios_usuario WHERE id='" + usuario.get("tipo_servicio") + "';";
        JSONObject institucion = Query.select(query);
        if (institucion != null) {
            usuario.put("nombre_institucion", institucion.get("nombre"));
            usuario.put("telefono_institucion", institucion.get("telefono"));
            usuario.put("traslado_ccb", institucion.get("traslado_ccb"));
            usuario.put("crum", institucion.get("crum"));
            boolean h = false;
            if (institucion.get("hospital").toString().equals("1")) {
                h = true;
            }
            usuario.put("hospital", h);
        }
        //covid
        if (usuario.get("tipo_usuario").toString().equals("11")) {
            usuario.put("tipo_servicio", "0");
        }
        //911
        if (usuario.get("tipo_usuario").toString().equals("10")) {
            usuario.put("tipo_servicio", "0");
        }
        if (parseInt(usuario.get("idUsuario_Sys").toString()) != 0) {

            return usuario.toString();
        }
        model.addAttribute("Alerta", " Usuario y/o Contraseña incorrecta. <br><br> ");
        return "";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String login(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("login: " + Dependencia);
        json.put("usuario", json.get("Usuario"));
        json.put("correo", json.get("Usuario"));
        json.put("contrasenia", json.get("Password"));
        JSONObject usuario = Request.request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/login", json);
        if ((boolean) usuario.get("success")) {
            usuario = data_login(usuario);
        }
        model.addAttribute("Alerta", " Usuario y/o Contraseña incorrecta. <br><br> ");
        return usuario.toString();
    }

    public JSONObject data_login(JSONObject usuario) {
        JSONObject cuenta360 = (JSONObject) usuario.get("claro360");
        usuario.put("idUsuario_Sys", cuenta360.get("id"));
        usuario.put("id_usuario", cuenta360.get("id"));
        usuario.put("nombre", cuenta360.get("nombre"));
        usuario.put("apellido_p", cuenta360.get("apellido_paterno"));
        usuario.put("apellido_m", cuenta360.get("apellido_materno"));
        usuario.put("apellidos", cuenta360.get("apellido_paterno") + " " + cuenta360.get("apellido_materno"));
        usuario.put("usuario", cuenta360.get("usuario"));
        usuario.put("correo", cuenta360.get("correo"));
        usuario.put("token", cuenta360.get("token"));

        ///////---Estaticos
        usuario.put("tipo", "Administrador");

        //  Duda si se ocupan // si si se ocupan 
        usuario.put("traslado_ccb", "0");
        usuario.put("ccb", "0");
        usuario.put("crum", "0");
        usuario.put("hospital", true);

        ///------
        //preguntar si el idUsuarioSys es el id360 retrocompatibilidad
        String query = "SELECT ccb FROM usuario_sys where idUsuario_Sys = " + cuenta360.get("id") + ";";
        JSONObject usuario_sys = Query.select(query);
        if (usuario_sys != null) {
            usuario.put("ccb", usuario_sys.get("ccb"));
        }

        //return usuario.toString();
        JSONArray plataforma360 = (JSONArray) usuario.get("plataforma360");
        for (int i = 0; i < plataforma360.size(); i++) {
            //si no es usuario maestro 

            JSONObject institucion = (JSONObject) plataforma360.get(i);
            System.out.println(institucion);
            if (institucion.get("url") != null) {
                System.out.println(institucion.get("url"));
                System.out.println(config.getPATH() + "" + Dependencia);
                if (institucion.get("url").toString().equals(config.getPATH() + "" + Dependencia + "/")) {
                    System.out.println("Tipo de servicio--->");
                    System.out.println(institucion.get("tipo_servicio"));
                    //tipo de servicio nulo 
                    if (institucion.get("tipo_servicio") != null) {
                        //sea usuario maestro 
                        if (institucion.get("tipo_usuario").toString().equals("0") && institucion.get("tipo_servicio").toString().equals("0")) {
                            institucion.put("nombre", "Administrador");
                            institucion.put("nombre_institucion", "Administrador");
                            institucion.put("direccion", "Cuenta maestra.");

                        } else if (institucion.get("tipo_servicio").toString().equals("0")) {
                            System.out.println("tipo_servicio es 0");
                            System.out.println(config.getPATH());
                            System.out.println(config.getPATH().contains("empresas"));
                            if (config.getPATH().contains("empresas")) {
                                //Buscar la informacion de la empresa a la que pertenece 
                                institucion.put("nombre_institucion", "Cuenta Administrativa");
                                institucion.put("logotipo", null);
                                institucion.put("direccion", null);
                                query = "SELECT * FROM tipos_usuarios WHERE id ='" + institucion.get("tipo_usuario") + "';";
                                JSONObject empresa = Query.select(query);
                                if (empresa != null) {
                                    institucion.put("logotipo", empresa.get("logotipo"));
                                    institucion.put("direccion", empresa.get("tipo_usuario"));
                                }
                            }

                        } else {
                            //los demas  

                            if (config.getPATH().contains("empresas")) {
                                //Buscar la informacion de la empresa a la que pertenece 
                                institucion.put("nombre_institucion", null);
                                institucion.put("logotipo", null);
                                institucion.put("direccion", null);
                                query = "SELECT * FROM tipos_usuarios WHERE id ='" + institucion.get("tipo_usuario") + "';";
                                JSONObject empresa = Query.select(query);
                                if (empresa != null) {
                                    institucion.put("logotipo", empresa.get("logotipo"));
                                    institucion.put("nombre_institucion", empresa.get("tipo_usuario"));
                                }

                                query = "SELECT * FROM servicios_usuario WHERE id='" + institucion.get("tipo_servicio") + "';";

                                JSONObject servicio = Query.select(query);
                                if (servicio != null) {
                                    institucion.put("nombre_institucion", institucion.get("nombre_institucion") + " - " + servicio.get("nombre"));
                                    servicio.put("telefono_institucion", servicio.get("telefono"));

                                    institucion.putAll(servicio);
                                }

                            } else {
                                query = "SELECT * FROM servicios_usuario WHERE id='" + institucion.get("tipo_servicio") + "';";

                                JSONObject servicio = Query.select(query);
                                if (servicio != null) {
                                    servicio.put("nombre_institucion", servicio.get("nombre"));
                                    servicio.put("telefono_institucion", servicio.get("telefono"));
                                    if (servicio.get("hospital").toString().equals("1")) {
                                        servicio.put("hospital", true);
                                    } else {
                                        servicio.put("hospital", false);
                                    }
                                    institucion.putAll(servicio);
                                }
                            }

                        }
                    } else {
                        institucion.put("nombre", "Crea tu red de trabajo");
                        institucion.put("nombre_institucion", "Activa tu Empresa o Registra tu Sucursal");
                        institucion.put("direccion", "");
                    }

                    //query = ""
                } else {
                    //System.out.println("Removiendo -> " + institucion.get("url"));
                    //plataforma360.remove(i);
                    //i--;
                }

            }

        }
        //return usuario.toString();
        System.out.println("usuarios----->");
        System.out.println(usuario);
        return usuario;
    }

    @RequestMapping(value = "/RegistroSys", method = RequestMethod.POST)
    public String RegistroSys(Model model, @RequestParam("Nombre") String Nombre, @RequestParam("Apellidos") String Apellidos, @RequestParam("Correo") String Correo, @RequestParam("Rol") String Rol, @RequestParam("Empresa") String Empresa, @RequestParam("Contrasenia") String Contrasenia) {
        System.out.println("RegistroSys: " + Dependencia);
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        boolean agregado;
        agregado = UsuarioSysDAO.agregarUsuario(Nombre, Apellidos, Correo, Rol, Contrasenia);
        if (agregado) {
            // model.addAttribute("Alerta", " El registro se realizo correctamente.  ");
        } else {
            model.addAttribute("Alerta", " Hubo un error al intentar registrarse, intentelo mas tarde. ");
        }
        return "Registro";
    }

    @RequestMapping(value = "/GuardarReporte", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String GuardarReporte(@RequestBody String json) throws ParseException, IOException {
        System.out.println(json);
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return "{}";
        }
        Gson gson = new Gson();
//        ReporteVO reporte = gson.fromJson(json, ReporteVO.class
//        );
        JSONParser parser = new JSONParser();
        JSONObject reporte = (JSONObject) parser.parse(json);
        boolean agregado = ReporteDAO.agregarReporte(reporte);
        /* parsear el json y crear un mini json con  el id de llamada y el folio externo
         * y usar request.request.POST para hacer el request al servidor
         * pasandole una url y el mini json
         */
        //JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);

        if (!"".equals(jsonObj.get("folioexterno").toString())) {
            JSONObject json2 = new JSONObject();
            json2.put("idLlamada", jsonObj.get("idLlamada"));
            json2.put("folioexterno", jsonObj.get("folioexterno"));

            String URL = "API/ActualizaTicket";

            /* vaer si la funcion va a regrear algo*/
            String actualizado = request.POST(config.getURL_CONTROLADOR() + URL, json2.toString());
            System.out.println("///////////////////////////////**************************/////////////////////");
            System.out.println("Se actualizo el ticket? " + actualizado);
            System.out.println("///////////////////////////////**************************/////////////////////");
            /**/
        }

        if (agregado) {
            return "{\"success\":1}";
        } else {
            return "{\"success\":0}";
        }
    }

    @RequestMapping(value = "/gpsvehicularlote", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String gpsvehicularlote(@RequestBody String json) throws ParseException {
        System.out.println("gpsvehicularlote: " + Dependencia);

        Gson gson = new Gson();
        RegistroRutaVO reg = gson.fromJson(json, RegistroRutaVO.class
        );
        System.out.println(reg.getIdUsuarios_Movil());
        System.out.println(reg.getIdUsuarios_Movil());
        //json.replace('~','"');
        //RegistroRutaDAO.saveRuta(json,reg.getIdUsuarios_Movil());
//        JSONParser parser = new JSONParser();
//        JSONObject reg = (JSONObject) parser.parse(json);
        if (RegistroRutaDAO.gpsLote(reg)) {
            //System.out.println("Registrando un lote de coordenadas del usuario "+reg.getIdUsuarios_Movil()+" perteneciente a "+Dependencia+"");
            //return "1";
            return "{\"respuesta\":\"1\"}";
        } else {
            System.out.println("FALLO: registrando un lote de coordenadas del usuario " + reg.getIdUsuarios_Movil() + " perteneciente a " + Dependencia + "");
            //return "0";
            return "{\"respuesta\":\"0\"}";
        }

    }

    @RequestMapping(value = "/gpsvehicular", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String gpsvehicular(@RequestBody JSONObject json) throws ParseException, IOException, java.text.ParseException {
        System.out.println("**************gpsvehicular**************");
        System.out.println(json);
        JSONObject respuesta = respuesta(false, "Error en realizar el registro");
        respuesta.put("respuesta", "0");
        String query = "SELECT idregistro_rutas FROM registro_rutas  WHERE idUsuarios_Movil='" + json.get("idUsuarios_Movil") + "' and Fecha = '" + json.get("fecha") + "';";
        JSONObject registro_gps = Query.select(query);
        if (registro_gps != null) {
            //update
            query = "UPDATE registro_rutas SET Hora='" + json.get("hora") + "', Latitud='" + json.get("latitud") + "', Longitud='" + json.get("longitud") + "' WHERE idregistro_rutas= '" + registro_gps.get("idregistro_rutas") + "';";
            if (Query.update(query)) {
                respuesta = respuesta(true, "Registro actualizado correctamente");
                respuesta.put("respuesta", "1");

                if (json.containsKey("gpsOTS")) {
                    query = "UPDATE registro_rutas SET  gpsOTS='1' WHERE idregistro_rutas= '" + registro_gps.get("idregistro_rutas") + "';";
                    Query.update(query);
                }
            } else {
                System.out.println("FALLO: actualizando coordenadas del usuario " + json.get("idUsuarios_Movil") + " perteneciente a " + Dependencia + "");
            }
        } else {
            //insert
            query = "INSERT INTO registro_rutas(idUsuarios_Movil,Fecha,Hora,Latitud,Longitud,Ruta) VALUES ('" + json.get("idUsuarios_Movil") + "','" + json.get("fecha") + "','" + json.get("hora") + "','" + json.get("latitud") + "','" + json.get("longitud") + "',\"\");";
            int n_reg = Query.insert(query);
            if (n_reg > 0) {
                respuesta = respuesta(true, "Registro creado correctamente");
                respuesta.put("respuesta", "1");
                if (json.containsKey("so") && json.containsKey("version") && json.containsKey("soversion")) {
                    query = "UPDATE `usuarios_movil` SET `so` = '" + json.get("so") + "', `soversion` = '" + json.get("soversion") + "', `version` = '" + json.get("version") + "' WHERE (`idUsuarios_Movil` = '" + json.get("idUsuarios_Movil") + "');";
                    Query.update(query);
                    respuesta = respuesta(true, "Registro creado correctamente, y se actualizo version del dispositivo.");
                    respuesta.put("respuesta", "1");
                }
            } else {
                System.out.println("FALLO: registrando coordenadas del usuario " + json.get("idUsuarios_Movil") + " perteneciente a " + Dependencia + "");
            }

        }

        //System.out.println("Registrando ultima ubicacion del usuario "+reg.getIdUsuarios_Movil()+" perteneciente a "+Dependencia+"... --- hora: "+reg.getHora());
        //return "1";
        JSONObject gps_json = new JSONObject();

        query = "SELECT tipo_usuario,tipo_servicio,tipo_area FROM directorio WHERE activo = 1 AND idUsuario = '" + json.get("idUsuarios_Movil") + "';";
        JSONObject info_directorio = Query.select(query);
        if (info_directorio != null) {
            gps_json.putAll(info_directorio);
        }
        gps_json.put("idUsuarios_Movil", json.get("idUsuarios_Movil"));
        gps_json.put("lat", Double.parseDouble(json.get("latitud").toString()));
        gps_json.put("lng", Double.parseDouble(json.get("longitud").toString()));
        gps_json.put("fecha", json.get("fecha"));
        gps_json.put("hora", json.get("hora"));
        gps_json.put("ActualizaGPS", true);
        if (json.containsKey("gpsOTS")) {
            gps_json.put("gpsOTS", json.get("gpsOTS"));
        }
        if (json.containsKey("so")) {
            gps_json.put("so", json.get("so"));
        } else {
            gps_json.put("so", "");
        }
        if (json.containsKey("soversion")) {
            gps_json.put("soversion", json.get("soversion"));
        } else {
            gps_json.put("soversion", "");
        }
        if (json.containsKey("version")) {
            gps_json.put("version", json.get("version"));
        } else {
            gps_json.put("version", "");
        }
        UpdateBackupDirectorio(gps_json);

        JSONObject r_gps = new JSONObject();
        r_gps.put("resultado", request.POST_GPS(json));
        Query.insert(Query.createQueryInsert("serviciogps", r_gps));

        //Modelo.Escalamiento.AgregarData(gps_json);
//                  JSONObject servicio = new JSONObject();
//                  servicio.put("url", config.getPATH() + Dependencia);
//                  String url = request.POST(config.getURL_CONTROLADOR() + "API/ServicioSuperior", servicio.toString());
//                  JSONArray arrayServicios = (JSONArray) parser.parse(url);
//                  for (int i = 0; i < arrayServicios.size(); i++) {
//                        //System.out.println(arrayServicios.get(i));
//                        request.POST(arrayServicios.get(i).toString() + "/API/UpdateBackupDirectorio", gps_json.toString());
//                  }
//                  JSONArray arrayServicios = config.getSuperiores();
//                  for (int i = 0; i < arrayServicios.size(); i++) {
//                        request.POST(arrayServicios.get(i).toString() + "/API/UpdateBackupDirectorio", gps_json.toString());
//                  }
        /**
         * ****************************GPS
         * PUBLICO***************************************
         */
        //JSONObject Elemento = (JSONObject) parser.parse(json);
//            if (!json.containsKey("gps_publico")) {
//                json.put("gps_publico", "false");
//            }
//
//            gps_json.put("idUsuario", gps_json.get("idUsuarios_Movil"));
//            gps_json.remove("idUsuarios_Movil");
//            gps_json.remove("ActualizaGPS");
        //solicitar perfil del lado del controlador cuando se ocupe
        //String query = "SELECT nombre , apellido_paterno, apellido_materno , FireBaseKey FROM usuarios_movil WHERE idUsuarios_Movil='" + gps_json.get("idUsuario") + "';";
//            JSONArray array = new JSONArray();
//            array = Query.execute(query);
//            if (!array.isEmpty()) {
//                gps_json.put("gps_publico", Elemento.get("gps_publico"));
//                gps_json.put("nombre", ((JSONObject) array.get(0)).get("nombre"));
//                gps_json.put("apellido_paterno", ((JSONObject) array.get(0)).get("apellido_paterno"));
//                gps_json.put("apellido_materno", ((JSONObject) array.get(0)).get("apellido_materno"));
//                gps_json.put("firebase", ((JSONObject) array.get(0)).get("FireBaseKey"));
//                gps_json.put("icon", config.getPATH() + Dependencia + "/API/ConsultarImg/icono/" + gps_json.get("idUsuario"));
//                gps_json.put("img", config.getPATH() + Dependencia + "/API/ConsultarImg/perfil/" + gps_json.get("idUsuario"));
//                gps_json.put("url_plataforma", config.getPATH() + Dependencia);
//            }
//            String url_request = config.getURL_CONTROLADOR() + "API/GPS_Publico";
//            JSONObject r = request.POST(url_request, gps_json);
        //estadoUsr.put("respuesta", "1");
        //return estadoUsr.toString();
        //return "{\"respuesta\":\"1\"}";
        System.out.println("respuesta");
        System.out.println(respuesta);
        return respuesta.toString();

    }

    //    @RequestMapping(value = "/gpsOTS", method = RequestMethod.POST, consumes = "application/json")
//    @ResponseBody
//    public JSONObject gpsOTS(@RequestBody JSONObject json) throws ParseException, IOException {
//        System.out.println(json);
//       
//            UpdateBackupDirectorio(json);
//
//            JSONObject servicio = new JSONObject();
//            servicio.put("url", config.getPATH() + Dependencia);
//            String url = request.POST(config.getURL_CONTROLADOR() + "API/ServicioSuperior", servicio.toString());
//            JSONParser parser = new JSONParser();
//            JSONArray arrayServicios = (JSONArray) parser.parse(url);
//            for (int i = 0; i < arrayServicios.size(); i++) {
//                request.POST(arrayServicios.get(i).toString() + "/API/UpdateBackupDirectorio", json.toString());
//            }
//
//            return json;
//        
//    }
    @RequestMapping(value = "/BuscarElementosCercanos", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JSONObject BuscarElementosCercanos(@RequestBody String json) throws ParseException {
        // System.out.println(json);
        Gson gson = new Gson();
        RegistroRutaVO p = gson.fromJson(json, RegistroRutaVO.class
        );
        JSONObject elementos = RegistroRutaDAO.BuscarElementosCercanos(p);
        //String JSON = gson.toJson(reg);
        //System.out.println("Usuario NONull: "+JSON);
        JSONParser parser = new JSONParser();
        JSONObject dependencia = new JSONObject();

        elementos.put("nombre", config.getDEPENDENCIA());
        dependencia.put(config.getDEPENDENCIA(), elementos);

        return dependencia;
        //return  "{\"descripcionLugar\":"+LugarAntecedente+"}";
        //   return "{\"success\":"+LugarAntecedente+"}";    

    }

    @RequestMapping(value = "/respuestaNotificacion", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void respuestaNotificacion(@RequestBody String json) throws ParseException, IOException {
        System.out.println("-------------------------------------");

        System.out.println(json);

        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);

        //if (!jsonObj.containsKey("razonamiento")) {
        String query_url = config.getURL_CONTROLADOR() + "restOpentok/ArchiveSession";
        JSONObject OpenTok = new JSONObject();
        OpenTok.put("apikey", jsonObj.get("apikey"));
        OpenTok.put("token", jsonObj.get("token"));
        OpenTok.put("sesion", jsonObj.get("idsession"));
        String url = Request.request.POST(query_url, OpenTok.toString());
        System.out.println(url);
        System.out.println(jsonObj.get("hora"));

        String query = "UPDATE `notificaciones` SET `ruta_video`='" + url + "', `hora_conexion`='" + jsonObj.get("hora") + "' WHERE `id`='" + jsonObj.get("id") + "';";
        System.out.println(query);
        if (Query.update(query)) {
            System.out.println("Registro exitoso de ruta_video o la hora de conexion del elemento");
        } else {
            System.out.println("Error al registrar ruta_video del elemeneto");
        }

        //}
        jsonObj.put("sesionelemento", true);
        System.out.println("-------------------------------------");

//        
//        Operador op = new Operador();
//        op.NotificarOperador(jsonObj.toString());
        SocketEndPoint.EnviarNotificacionIndividual(jsonObj, jsonObj.get("idSocket").toString());

        //return "";
        //
    }

    //            @RequestMapping(value = "/AgregarDependencia", method = RequestMethod.POST, consumes = "application/json")
//    @ResponseBody
//    public void AgregarDependencia(Model model,
//            @RequestParam("DependenciaNombre") String DependenciaNombre, 
//            @RequestParam("DependenciaURL") String DependenciaURL, 
//            @RequestParam("DependenciaAlias") String DependenciaAlias ) {
//        System.out.println("-------------------------------------");
//
//        System.out.println(DependenciaNombre);
//        System.out.println(DependenciaURL);
//        System.out.println(DependenciaAlias);
//
//        System.out.println("-------------------------------------");
//    }
    @RequestMapping(value = "/AgregarDependencia", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String AgregarDependencia(@RequestBody String json) throws ParseException {

        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);

        boolean agregado = ProyectoDAO.AgregarDependencia(jsonObj.get("nombre").toString(), jsonObj.get("alias").toString(), jsonObj.get("url").toString(), jsonObj.get("icon").toString());
        if (agregado) {
            return "{\"failure\":0,\"success\":1}";
        }
        return "{\"failure\":1,\"success\":0}";
    }

    @RequestMapping(value = "/DependenciaCambiarEstado", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String DependenciaCambiarEstado(@RequestBody String json) throws ParseException {

        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);

        boolean agregado = ProyectoDAO.DependenciaCambiarEstado(jsonObj.get("id").toString(), jsonObj.get("activo").toString());
        if (agregado) {
            return "{\"failure\":0,\"success\":1}";
        }
        return "{\"failure\":1,\"success\":0}";
    }

    @RequestMapping(value = "/ios", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String ios(@RequestBody String json) {
        return json;
    }

    @RequestMapping(value = "/ReporteCiudadano", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String ReporteCiudadano(@RequestBody String json) throws IOException, ParseException {
        System.out.println(json);

        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        String ok = ReporteDAO.reporteCiudadano(json);
        System.out.println(ok);
        JSONObject res = (JSONObject) parser.parse(ok);
        if (res.toString() != "") {
            jsonObj.put("estado", res.get("estado").toString());
            jsonObj.put("idReporte", res.get("folio").toString());

            //ServerReporte not = new ServerReporte();
            //not.EnviaNot(jsonObj.toString());

            return res.toString();
        } else {
            return ("fallo");
        }

    }

    @RequestMapping(value = "/IDReporteCiudadano", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String IDReporteCiudadano(@RequestBody String json) throws IOException, ParseException {
        System.out.println(json);
        return ReporteDAO.IDreporteCiudadano(json);
    }

    @RequestMapping(value = "/FolioReporteCiudadano", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String FolioReporteCiudadano(@RequestBody String json) throws IOException, ParseException {
        return ReporteDAO.FolioreporteCiudadano(json);
    }

    @RequestMapping(value = "/imgFolio", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String imgFolio(Model model, @RequestBody String json) throws IOException, ParseException {

        model.addAttribute("imagen", "data:image/png;base64," + ReporteDAO.imgFolio(json));

        //return "img";
        return "data:image/png;base64," + ReporteDAO.imgFolio(json);
    }

    @RequestMapping(value = "/Reportes ", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String Reportes(/*@RequestBody String json*/) throws IOException, ParseException {

        //System.out.println(ReporteDAO.traeReportes());
        return ReporteDAO.traeReportes();
        //return "{\"success\":\"ok\"}";
    }

    @RequestMapping(value = "/ActualizaEstadoReporte ", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String ActualizaEstadoReporte(@RequestBody String json) throws IOException, ParseException {
        //System.out.println(ReporteDAO.actualizaReporte(json));
        return ReporteDAO.actualizaReporte(json);
        //return "{\"success\":\"ok\"}";
    }

    @RequestMapping(value = "/Img", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String Img(@RequestBody String json) throws IOException, ParseException {
        //System.out.println(ReporteDAO.actualizaReporte(json));
        return ReporteDAO.guardaImg(json);
        //return "{\"success\":\"ok\"}";
    }

    @RequestMapping(value = "/CosultaImg", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String CosultaImg(@RequestBody String json) throws IOException, ParseException {
        //System.out.println(ReporteDAO.actualizaReporte(json));
        return request.POST(config.getURL_CONTROLADOR() + "API/BuscaImg", json);
        //return "{\"success\":\"ok\"}";
    }

    @RequestMapping(value = "/BuscaImg", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String BuscaImg(@RequestBody String json) throws IOException, ParseException {
        //System.out.println(ReporteDAO.actualizaReporte(json));
        return ReporteDAO.buscaImg(json);
        //return "{\"success\":\"ok\"}";
    }

    @RequestMapping(value = "/ReportesSimilares ", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String ReportesSimilares(@RequestBody String json) throws IOException, ParseException {
        String reportesSimilares = ReporteDAO.buscaSimilares(json);
        System.out.println(reportesSimilares);
        return reportesSimilares;
        //return "{\"success\":\"ok\"}";
    }

    @RequestMapping(value = "/SerieIncidentes", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String SerieIncidentes(@RequestBody String json) throws IOException, ParseException {
        System.out.println(json);
        String serie = Post.serieIncidentes(json);
        System.out.println("Esta es la serie :::: " + serie);
        return serie;
//        return "{\"success\":\"ok\"}";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String update(@RequestBody String json) throws IOException, ParseException {
        String update = Post.update(json);
        return update;
//        return "{\"success\":\"ok\"}";
    }

    @RequestMapping(value = "/reportedependencias", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String reportedependencias(@RequestBody String json) throws IOException, ParseException {
        String reportedependencias = Post.reportedependencias(json);
        //System.out.println("Esta es la serie :::: "+serie);
        return reportedependencias;
        //return "{\"success\":\"ok\"}";
    }

    @RequestMapping(value = "/Ticket", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String Ticket(@RequestBody String json) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        jsonObj.put("origen", config.getPATH() + Dependencia);

        //String ticket = Post.GenerarTicket(config.getPATH() + Dependencia, jsonObj.get("fecha").toString(), jsonObj.get("hora").toString());
        String ticket = Post.GenerarTicket(jsonObj);
        return ticket;
    }

    @RequestMapping(value = "/HistoricoIncidentes", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String HistoricoIncidentes(@RequestBody JSONObject json) {

        String query = "SELECT idRegistro_Llamadas as id,hora,Modo_Llamada_idModo_Llamada as modo_E,modo_llamada.nombre,Modo_Llamada_idModo_Llamada_Finalizada as modo_S FROM registro_llamadas inner join modo_llamada on Modo_Llamada_idModo_Llamada=idModo_Llamada  where fecha=\"" + json.get("fecha") + "\";";
        System.out.println(query);
        JSONArray jsonArray = Query.execute(query);
        System.out.println(jsonArray);
        //String ticket = Post.GenerarTicket(config.getPATH() + Dependencia, jsonObj.get("fecha").toString(), jsonObj.get("hora").toString());
        return "";
    }

    @RequestMapping(value = "/BusquedaFolioExterno", method = RequestMethod.POST/*, produces = "application/json;charset=UTF-8", consumes = "application/json"*/)
    @ResponseBody
    public String BusquedaFolioExterno(@RequestBody String json) throws IOException {
        System.out.println(json);
        String url = config.getURL_CONTROLADOR() + "API/FiltraFolioExterno";
        return request.POST(url, json);
    }

    @RequestMapping(value = "/BusquedaPorFecha", method = RequestMethod.POST/*, produces = "application/json;charset=UTF-8", consumes = "application/json"*/)
    @ResponseBody
    public String BusquedaPorFecha(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println(json);
        System.out.println(json.get("fecha"));
        JSONObject ids = new JSONObject();
        String query = "";
        if (json.containsKey("tipo_usuario") && json.containsKey("tipo_servicio")) {
            query = "SELECT idRegistro_Llamadas AS idLlamada FROM registro_llamadas where (fecha ='" + json.get("fecha").toString() + "' ";
            if ((boolean) json.get("usuario")) {
                query += " AND Usuario_Sys_idUsuario_Sys=\"" + json.get("idUser") + "\"";
            }
            String subquery1 = "AND( ";
            if ((boolean) json.get("atendidas")) {
                subquery1 += " Modo_Llamada_idModo_Llamada_Finalizada = 5 OR";
            }
            if ((boolean) json.get("perdidas")) {
                subquery1 += " Modo_Llamada_idModo_Llamada_Finalizada=0";
            }
            if (subquery1.substring(subquery1.length() - 2).equals("OR")) {
                subquery1 = subquery1.substring(0, subquery1.length() - 2);
            }
            subquery1 += ") ";
            if (subquery1.length() > 8) {
                query += subquery1;
            }
            subquery1 = "AND( ";
            if ((boolean) json.get("sigiloso")) {
                subquery1 += " Modo_Llamada_idModo_Llamada = 104 OR Modo_Llamada_idModo_Llamada = 2 OR";
            }
            if ((boolean) json.get("video")) {
                subquery1 += " Modo_Llamada_idModo_Llamada = 103 OR Modo_Llamada_idModo_Llamada = 1 OR";
            }
            if ((boolean) json.get("voz")) {
                subquery1 += " Modo_Llamada_idModo_Llamada = 102 OR";
            }
            if ((boolean) json.get("chat")) {
                subquery1 += " Modo_Llamada_idModo_Llamada = 101 OR Modo_Llamada_idModo_Llamada = 10 OR Modo_Llamada_idModo_Llamada = 11";
            }
            if (subquery1.substring(subquery1.length() - 2).equals("OR")) {
                subquery1 = subquery1.substring(0, subquery1.length() - 2);
            }
            subquery1 += ") ";
            if (subquery1.length() > 8) {
                query += subquery1;
            }

            query += ") AND tipo_usuario = '" + json.get("tipo_usuario") + "' AND tipo_servicio = '" + json.get("tipo_servicio") + "';";
            //System.out.println(query);
        } else {
            query = "SELECT idRegistro_Llamadas AS idLlamada FROM registro_llamadas where fecha ='" + json.get("fecha").toString() + "' ";
            if ((boolean) json.get("usuario")) {
                query += " AND Usuario_Sys_idUsuario_Sys=\"" + json.get("idUser") + "\"";
            }
            String subquery1 = "AND( ";
            if ((boolean) json.get("atendidas")) {
                subquery1 += " Modo_Llamada_idModo_Llamada_Finalizada = 5 OR";
            }
            if ((boolean) json.get("perdidas")) {
                subquery1 += " Modo_Llamada_idModo_Llamada_Finalizada=0";
            }
            if (subquery1.substring(subquery1.length() - 2).equals("OR")) {
                subquery1 = subquery1.substring(0, subquery1.length() - 2);
            }
            subquery1 += ") ";
            if (subquery1.length() > 8) {
                query += subquery1;
            }
            subquery1 = "AND( ";
            if ((boolean) json.get("sigiloso")) {
                subquery1 += " Modo_Llamada_idModo_Llamada = 104 OR Modo_Llamada_idModo_Llamada = 2 OR";
            }
            if ((boolean) json.get("video")) {
                subquery1 += " Modo_Llamada_idModo_Llamada = 103 OR Modo_Llamada_idModo_Llamada = 1 OR";
            }
            if ((boolean) json.get("voz")) {
                subquery1 += " Modo_Llamada_idModo_Llamada = 102 OR";
            }
            if ((boolean) json.get("chat")) {
                subquery1 += " Modo_Llamada_idModo_Llamada = 101 OR Modo_Llamada_idModo_Llamada = 10 OR Modo_Llamada_idModo_Llamada = 11";
            }
            if (subquery1.substring(subquery1.length() - 2).equals("OR")) {
                subquery1 = subquery1.substring(0, subquery1.length() - 2);
            }
            subquery1 += ") ";
            if (subquery1.length() > 8) {
                query += subquery1;
            }

            query += ";";

        }

        JSONArray jsonArray = Query.execute(query);
        if (!jsonArray.isEmpty()) {

            ids.put("llamada", jsonArray);
            if (json.containsKey("tipo_usuario") && json.containsKey("tipo_servicio")) {
                query = "SELECT idLlamada FROM registro_llamadas_salientes WHERE fecha_notificacion='" + json.get("fecha").toString() + "' "
                        + "AND tipo_usuario = '" + json.get("tipo_usuario") + "' AND tipo_servicio = '" + json.get("tipo_servicio") + "';";
                ids.put("tipo_usuario", json.get("tipo_usuario"));
                ids.put("tipo_servicio", json.get("tipo_servicio"));
            } else {
                query = "SELECT idLlamada FROM registro_llamadas_salientes WHERE fecha_notificacion='" + json.get("fecha").toString() + "';";
            }
            System.out.println(query);
            JSONArray array = Query.execute(query);
            ids.put("llamadaS", array);
            query = "SELECT id AS idLlamada FROM ReporteElemento WHERE cuando LIKE '" + json.get("fecha").toString() + "%' AND estado = 'revisado';";
            System.out.println(query);
            array = Query.execute(query);
            ids.put("reporteIO", array);

            String url = config.getURL_CONTROLADOR() + "API/FiltraIdTickets";
            String respuesta = request.POST(url, ids.toString());
            return respuesta.toString();
        } else {
            return "null";
        }

    }

    @RequestMapping(value = "/URLReportes", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String URLReportes(@RequestBody JSONObject json) {
        System.out.println("URLReportes de --> " + Dependencia);
        String query = "";
        if (json.containsKey("tipo_usuario") && json.containsKey("tipo_servicio")) {
            query = "SELECT * FROM registro_llamadas where idRegistro_Llamadas='" + json.get("idLlamada") + "' "
                    + "AND tipo_usuario = '" + json.get("tipo_usuario") + "' AND tipo_servicio = '" + json.get("tipo_servicio") + "';";
        } else {
            query = "SELECT * FROM registro_llamadas where idRegistro_Llamadas = '" + json.get("idLlamada") + "';";
        }

        JSONObject registro_llamada = Query.select(query);
        if (registro_llamada != null) {
            if (json.containsKey("tipo_usuario") && json.containsKey("tipo_servicio")) {
                query = "SELECT * FROM reporte_llamadas where idLlamada='" + json.get("idLlamada") + "' "
                        + "AND tipo_usuario = '" + json.get("tipo_usuario") + "' AND tipo_servicio = '" + json.get("tipo_servicio") + "';";
            } else {
                query = "SELECT * FROM reporte_llamadas where idLlamada='" + json.get("idLlamada") + "';";
            }

            JSONArray jsonArray = Query.execute(query);
            JSONObject respuesta = new JSONObject();
            JSONObject reporte_llamada = new JSONObject();
            JSONArray videos_relacionados = new JSONArray();

            if (jsonArray.size() > 0) {
                reporte_llamada = (JSONObject) jsonArray.get(jsonArray.size() - 1);
            }
            JSONArray jsonArray_respuesta = new JSONArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                String url = "";
                JSONObject json_url = new JSONObject();
                if (object.get("hora") != "null" && object.get("hora") != null) {
                    url = config.getPATH() + config.getDEPENDENCIA() + "/Reporte/" + json.get("idLlamada") + "/" + json.get("origen") + "/" + object.get("hora");
                    json_url.put("url", url);
                    jsonArray_respuesta.add(json_url);
                }

            }

//        query = "SELECT * FROM registro_llamadas where idRegistro_Llamadas=\"" + json.get("idLlamada") + "\";";
//        System.out.println(query);
//        jsonArray = Query.execute(query);
//        
//        if (jsonArray.size() > 0) {
//            registro_llamada = (JSONObject) jsonArray.get(0);
//        }
            query = "SELECT nombre from modo_llamada where idModo_Llamada =" + registro_llamada.get("Modo_Llamada_idModo_Llamada") + ";";
            System.out.println(query);
            jsonArray = Query.execute(query);
            if (jsonArray.size() > 0) {
                registro_llamada.put("Modo_Llamada", ((JSONObject) jsonArray.get(0)).get("nombre"));
            }

            query = "SELECT nombre from modo_llamada where idModo_Llamada =" + registro_llamada.get("Modo_Llamada_idModo_Llamada_Finalizada") + ";";
            System.out.println(query);
            jsonArray = Query.execute(query);
            System.out.println(jsonArray);
            if (jsonArray.size() > 0) {
                registro_llamada.put("Modo_Llamada_Finalizada", ((JSONObject) jsonArray.get(0)).get("nombre"));
            }

            /**
             * ************************* Para los videos relacionado
             * **************************
             */
            if (json.containsKey("tipo_usuario") && json.containsKey("tipo_servicio")) {
                query = "SELECT N.*,U.nombre,U.apellido_paterno,U.apellido_materno FROM notificaciones N, usuarios_movil U "
                        + "WHERE N.idLlamada='" + registro_llamada.get("idRegistro_Llamadas") + "' "
                        + "AND N.idUsuarios_Movil = U.idUsuarios_Movil AND (N.hora_conexion IS NOT NULL OR N.razonamiento IS NOT NULL) "
                        + "AND N.tipo_usuario = '" + json.get("tipo_usuario") + "' AND N.tipo_servicio = '" + json.get("tipo_servicio") + "';";
            } else {
                query = "SELECT N.*,U.nombre,U.apellido_paterno,U.apellido_materno FROM notificaciones N, usuarios_movil U "
                        + "WHERE N.idLlamada='" + registro_llamada.get("idRegistro_Llamadas") + "' "
                        + "AND N.idUsuarios_Movil = U.idUsuarios_Movil AND (N.hora_conexion IS NOT NULL OR N.razonamiento IS NOT NULL);";
            }

            System.out.println(query);
            jsonArray = Query.execute(query);
            if (!jsonArray.isEmpty()) {
//            System.out.println(jsonArray);
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonO = new JSONObject();
                    JSONObject bitacora = new JSONObject();
                    JSONObject js = (JSONObject) jsonArray.get(i);

                    jsonO.put("nombre", js.get("nombre"));
                    jsonO.put("apellido_paterno", js.get("apellido_paterno"));
                    jsonO.put("apellido_materno", js.get("apellido_materno"));
                    jsonO.put("telefono", js.get("idUsuarios_Movil"));
                    jsonO.put("ruta_video", js.get("ruta_video"));

                    bitacora.put("fecha", js.get("fecha"));
                    bitacora.put("hora_envio", js.get("hora_envio"));
                    bitacora.put("hora_recepcion", js.get("hora_recepcion"));
                    bitacora.put("hora_conexion", js.get("hora_conexion"));
                    bitacora.put("hora_desconexion", js.get("hora_desconexion"));

                    jsonO.put("bitacora", bitacora);

                    jsonO.put("razonamiento", js.get("razonamiento"));
                    videos_relacionados.add(jsonO);
                }
                //System.out.println(videos_relacionados);
                respuesta.put("videos_relacionados", videos_relacionados);
            }

            /**
             * ********************************************************************************
             */
            respuesta.put("reportes_generados", jsonArray_respuesta);
            respuesta.put("reporte_llamada", reporte_llamada);
            respuesta.put("registro_llamada", registro_llamada);
            query = "select nombre, apellido_paterno,apellido_materno from usuarios_movil WHERE idUsuarios_Movil='" + registro_llamada.get("Usuarios_Movil_idUsuarios_Movil") + "';";
            JSONObject datos = Query.select(query);
            if (datos != null) {
                respuesta.put("nombre", datos.get("nombre"));
                respuesta.put("apellido_paterno", datos.get("apellido_paterno"));
                respuesta.put("apellido_materno", datos.get("apellido_materno"));
            }

            System.out.println(respuesta.toString());
            return respuesta.toString();
        } else {
            return "null";
        }

    }

    @RequestMapping(value = "/{tipo_usuario}/{tipo_servicio}/URLReportes", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String URLReportes(@RequestBody JSONObject json, @PathVariable("tipo_usuario") String tipo_usuario, @PathVariable("tipo_servicio") String tipo_servicio) {
        System.out.println("URLReportes de --> " + Dependencia);
        String query = "";
        if (json.containsKey("tipo_usuario") && json.containsKey("tipo_servicio")) {
            query = "SELECT * FROM registro_llamadas where idRegistro_Llamadas='" + json.get("idLlamada") + "' "
                    + "AND tipo_usuario = '" + json.get("tipo_usuario") + "' AND tipo_servicio = '" + json.get("tipo_servicio") + "';";
        } else {
            query = "SELECT * FROM registro_llamadas where idRegistro_Llamadas = '" + json.get("idLlamada") + "';";
        }

        JSONObject registro_llamada = Query.select(query);
        if (registro_llamada != null) {
            if (json.containsKey("tipo_usuario") && json.containsKey("tipo_servicio")) {
                query = "SELECT * FROM reporte_llamadas where idLlamada='" + json.get("idLlamada") + "' "
                        + "AND tipo_usuario = '" + json.get("tipo_usuario") + "' AND tipo_servicio = '" + json.get("tipo_servicio") + "';";
            } else {
                query = "SELECT * FROM reporte_llamadas where idLlamada='" + json.get("idLlamada") + "';";
            }
            JSONArray jsonArray = Query.execute(query);
            JSONObject respuesta = new JSONObject();
            JSONObject reporte_llamada = new JSONObject();
            JSONArray videos_relacionados = new JSONArray();

            if (jsonArray.size() > 0) {
                reporte_llamada = (JSONObject) jsonArray.get(jsonArray.size() - 1);
            }
            JSONArray jsonArray_respuesta = new JSONArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                String url = "";
                JSONObject json_url = new JSONObject();
                if (object.get("hora") != "null" && object.get("hora") != null) {
                    url = config.getPATH() + config.getDEPENDENCIA() + "/Reporte/" + json.get("idLlamada") + "/" + json.get("origen") + "/" + object.get("hora");
                    json_url.put("url", url);
                    jsonArray_respuesta.add(json_url);
                }

            }

//        query = "SELECT * FROM registro_llamadas where idRegistro_Llamadas=\"" + json.get("idLlamada") + "\";";
//        System.out.println(query);
//        jsonArray = Query.execute(query);
//        
//        if (jsonArray.size() > 0) {
//            registro_llamada = (JSONObject) jsonArray.get(0);
//        }
            query = "SELECT nombre from modo_llamada where idModo_Llamada =" + registro_llamada.get("Modo_Llamada_idModo_Llamada") + ";";
            System.out.println(query);
            jsonArray = Query.execute(query);
            if (jsonArray.size() > 0) {
                registro_llamada.put("Modo_Llamada", ((JSONObject) jsonArray.get(0)).get("nombre"));
            }

            query = "SELECT nombre from modo_llamada where idModo_Llamada =" + registro_llamada.get("Modo_Llamada_idModo_Llamada_Finalizada") + ";";
            System.out.println(query);
            jsonArray = Query.execute(query);
            System.out.println(jsonArray);
            if (jsonArray.size() > 0) {
                registro_llamada.put("Modo_Llamada_Finalizada", ((JSONObject) jsonArray.get(0)).get("nombre"));
            }

            /**
             * ************************* Para los videos relacionado
             * **************************
             */
            if (json.containsKey("tipo_usuario") && json.containsKey("tipo_servicio")) {
                query = "SELECT N.*,U.nombre,U.apellido_paterno,U.apellido_materno FROM notificaciones N, usuarios_movil U "
                        + "WHERE N.idLlamada='" + registro_llamada.get("idRegistro_Llamadas") + "' "
                        + "AND N.idUsuarios_Movil = U.idUsuarios_Movil AND (N.hora_conexion IS NOT NULL OR N.razonamiento IS NOT NULL) "
                        + "AND N.tipo_usuario = '" + json.get("tipo_usuario") + "' AND N.tipo_servicio = '" + json.get("tipo_servicio") + "';";
            } else {
                query = "SELECT N.*,U.nombre,U.apellido_paterno,U.apellido_materno FROM notificaciones N, usuarios_movil U "
                        + "WHERE N.idLlamada='" + registro_llamada.get("idRegistro_Llamadas") + "' "
                        + "AND N.idUsuarios_Movil = U.idUsuarios_Movil AND (N.hora_conexion IS NOT NULL OR N.razonamiento IS NOT NULL);";
            }
            System.out.println(query);
            jsonArray = Query.execute(query);
            if (!jsonArray.isEmpty()) {
//            System.out.println(jsonArray);
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonO = new JSONObject();
                    JSONObject bitacora = new JSONObject();
                    JSONObject js = (JSONObject) jsonArray.get(i);

                    jsonO.put("nombre", js.get("nombre"));
                    jsonO.put("apellido_paterno", js.get("apellido_paterno"));
                    jsonO.put("apellido_materno", js.get("apellido_materno"));
                    jsonO.put("telefono", js.get("idUsuarios_Movil"));
                    jsonO.put("ruta_video", js.get("ruta_video"));

                    bitacora.put("fecha", js.get("fecha"));
                    bitacora.put("hora_envio", js.get("hora_envio"));
                    bitacora.put("hora_recepcion", js.get("hora_recepcion"));
                    bitacora.put("hora_conexion", js.get("hora_conexion"));
                    bitacora.put("hora_desconexion", js.get("hora_desconexion"));

                    jsonO.put("bitacora", bitacora);

                    jsonO.put("razonamiento", js.get("razonamiento"));
                    videos_relacionados.add(jsonO);
                }
                //System.out.println(videos_relacionados);
                respuesta.put("videos_relacionados", videos_relacionados);
            }

            /**
             * ********************************************************************************
             */
            respuesta.put("reportes_generados", jsonArray_respuesta);
            respuesta.put("reporte_llamada", reporte_llamada);
            respuesta.put("registro_llamada", registro_llamada);
            query = "select nombre, apellido_paterno,apellido_materno from usuarios_movil WHERE idUsuarios_Movil='" + registro_llamada.get("Usuarios_Movil_idUsuarios_Movil") + "';";
            JSONObject datos = Query.select(query);
            if (datos != null) {
                respuesta.put("nombre", datos.get("nombre"));
                respuesta.put("apellido_paterno", datos.get("apellido_paterno"));
                respuesta.put("apellido_materno", datos.get("apellido_materno"));
            }

            System.out.println(respuesta.toString());
            return respuesta.toString();
        } else {
            return "null";
        }

    }

    @RequestMapping(value = "/URL_RutaVideo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String URL_RutaVideo(@RequestBody JSONObject json) {
        String query = null;
        if (json.containsKey("tipo_usuario") && json.containsKey("tipo_servicio")) {
            query = "SELECT ruta_video FROM registro_llamadas where idRegistro_Llamadas='" + json.get("idLlamada") + "' "
                    + "AND tipo_usuario = '" + json.get("tipo_usuario") + "' AND tipo_servicio = '" + json.get("tipo_servicio") + "';";
        } else {
            query = "SELECT ruta_video FROM registro_llamadas where idRegistro_Llamadas=\"" + json.get("idLlamada") + "\";";
        }

        System.out.println(query);
        JSONArray jsonArray = Query.execute(query);
//        System.out.println(jsonArray.size());
        JSONObject j = new JSONObject();
        if (jsonArray.isEmpty()) {

            j.put("url", null);
            //jsonArray.add(j);
            return j.toString();
        } else {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
//                System.out.println(object.get("url"));
//            System.out.println();
                if (object.get("ruta_video") != null && !object.get("ruta_video").equals("null") && !object.get("ruta_video").equals("/Falta..")) {

                    object.put("url", object.get("ruta_video"));
                    object.remove("ruta_video");
                    return object.toString();
                } else {
                    object.replace("ruta_video", null);
                    object.put("url", object.get("ruta_video"));
                    object.remove("ruta_video");
//                System.out.println(object);
                    //jsonArray.set(i, object);
//                System.out.println(jsonArray);
                    return object.toString();
                }
            }
        }

        return j.toString();
    }

    @RequestMapping(value = "/{tipo_usuario}/{tipo_servicio}/URL_RutaVideo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String URL_RutaVideo(@RequestBody JSONObject json, @PathVariable("tipo_usuario") String tipo_usuario, @PathVariable("tipo_servicio") String tipo_servicio) {

        String query = null;
        if (json.containsKey("tipo_usuario") && json.containsKey("tipo_servicio")) {
            query = "SELECT ruta_video FROM registro_llamadas where idRegistro_Llamadas='" + json.get("idLlamada") + "' "
                    + "AND tipo_usuario = '" + json.get("tipo_usuario") + "' AND tipo_servicio = '" + json.get("tipo_servicio") + "';";
        } else {
            query = "SELECT ruta_video FROM registro_llamadas where idRegistro_Llamadas=\"" + json.get("idLlamada") + "\";";
        }

        System.out.println(query);
        JSONArray jsonArray = Query.execute(query);
//        System.out.println(jsonArray.size());
        JSONObject j = new JSONObject();
        if (jsonArray.isEmpty()) {

            j.put("url", null);
            //jsonArray.add(j);
            return j.toString();
        } else {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
//                System.out.println(object.get("url"));
//            System.out.println();
                if (object.get("ruta_video") != null && !object.get("ruta_video").equals("null") && !object.get("ruta_video").equals("/Falta..")) {

                    object.put("url", object.get("ruta_video"));
                    object.remove("ruta_video");
                    return object.toString();
                } else {
                    object.replace("ruta_video", null);
                    object.put("url", object.get("ruta_video"));
                    object.remove("ruta_video");
//                System.out.println(object);
                    //jsonArray.set(i, object);
//                System.out.println(jsonArray);
                    return object.toString();
                }
            }
        }

        return j.toString();
    }

    @RequestMapping(value = "/RegistroUsuarioSistema", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public JSONObject Verifica(@RequestBody JSONObject json) throws ParseException, IOException {

        json.put("servicio", config.getPATH());
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", false);
        respuesta.put("failure", true);

        String Contrasenia = Encriptacion.Encriptar.Next(json.get("Pass").toString());

        String query = "SELECT idUsuario_Sys FROM usuario_sys WHERE correo=\"" + json.get("mail") + "\";";

        if (Query.select(query) == null) {
            query = "SELECT idUsuario_Sys FROM usuario_sys WHERE usuario=\"" + json.get("user") + "\";";
            if (Query.select(query) == null) {
                query = "INSERT INTO `usuario_sys` (`usuario`, `nombre`, `apellidos`, `correo`, `contrasenia`, `tipo`, `estatus`, `sesion`, `disponibilidad`, `registrado_por_usuario`,`puede_registrar`,`tipo_usuario`,`tipo_servicio`) "
                        + "VALUES ('" + json.get("user") + "', '" + json.get("firstname") + "', '" + json.get("lastname") + "', "
                        + "'" + json.get("mail") + "', '" + Contrasenia + "', 'Administrador', '1', '0', '1', '1','false','" + json.get("tipo_usuario") + "','" + json.get("tipo_servicio") + "');";

                System.out.println(query);
                if (Query.insert(query) > 0) {
                    respuesta.put("success", true);
                    respuesta.put("failure", false);
                } else {
                    respuesta.put("mensaje", "Error en realizar el registro");
                }
            } else {
                respuesta.put("mensaje", "El nombre de usuario: " + json.get("Usuario") + " ya esta ocupado\nUtilice otro nombre de usuario.");
            }
        } else {
            respuesta.put("mensaje", "El correo: " + json.get("mail") + " ya esta registrado\\nUtilice un correo valido.");
        }

        return respuesta;
    }

    @RequestMapping(value = "/Verifica", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String Verifica(@RequestBody String json) throws ParseException, IOException {

        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        jsonObj.put("servicio", config.getPATH());
        JSONObject response = (JSONObject) parser.parse(request.POST(config.getURL_CONTROLADOR() + "Verifica", jsonObj.toString()));

        /////////////////////////////////////////
        if ((boolean) response.get("procede")) {
            String Contrasenia = Encriptacion.Encriptar.Next(jsonObj.get("Pass").toString());

            String query = "INSERT INTO `usuario_sys` (`usuario`, `nombre`, `apellidos`, `correo`, `contrasenia`, `tipo`, `estatus`, `sesion`, `disponibilidad`, `registrado_por_usuario`,`puede_registrar`,`tipo_usuario`,`tipo_servicio`) "
                    + "VALUES ('" + jsonObj.get("Usuario") + "', '" + jsonObj.get("Nombre") + "', '" + jsonObj.get("Apellidos") + "', "
                    + "'" + jsonObj.get("Correo") + "', '" + Contrasenia + "', 'Administrador', '1', '0', '1', " + jsonObj.get("RegistradoPor") + ",'" + jsonObj.get("Confirma") + "','" + jsonObj.get("tipo_usuario") + "','" + jsonObj.get("tipo_servicio") + "');";

            System.out.println(query);

            Query.update(query);
            //String url = config.getPATH() + config.getDEPENDENCIA();
            //jsonObj.put("url", url);
            //request.POST(config.getURL_CONTROLADOR() + "ConsultaURLSuperior", jsonObj.toString());
        }
        /////////////////////////////////////////
        return response.toString();

        /*
        String query = "select puede_registrar from usuario_sys WHERE idUsuario_Sys=" + json.get("RegistradoPor") + ";";
        System.out.println(query);
        JSONArray jsonArray = Query.execute(query);
        System.out.println(jsonArray);
        if (!jsonArray.isEmpty()) {
            if (((JSONObject) jsonArray.get(0)).get("puede_registrar") == null) {
                JSONObject aux = (JSONObject) jsonArray.get(0);
                aux.put("puede_registrar", "false");
                jsonArray.set(0, aux);
            }
            if ((((JSONObject) jsonArray.get(0)).get("puede_registrar")).toString().equals("true")) {
                query = "SELECT idUsuario_Sys FROM usuario_sys WHERE correo=\"" + json.get("Correo") + "\";";
                System.out.println(query);
                jsonArray = Query.execute(query);
                System.out.println(jsonArray);

                if (jsonArray.isEmpty()) {
                    query = "SELECT idUsuario_Sys FROM usuario_sys WHERE usuario=\"" + json.get("Usuario") + "\";";
                    System.out.println(query);
                    jsonArray = Query.execute(query);
                    System.out.println(jsonArray);

                    if (jsonArray.isEmpty()) {
                        JSONObject response = new JSONObject();
                        //response.put("mensaje", "El nombre de usuario: " + json.get("Usuario") + " ya esta ocupado\\nUtilice otro nombre de usuario.");
                        response.put("procede", true);
                        return response;
                    } else {
                        JSONObject response = new JSONObject();
                        response.put("mensaje", "El nombre de usuario: " + json.get("Usuario") + " ya esta ocupado\nUtilice otro nombre de usuario.");
                        response.put("procede", false);
                        return response;
                    }
                } else {
                    JSONObject response = new JSONObject();
                    response.put("mensaje", "El correo: " + json.get("Correo") + " ya esta registrado\\nUtilice un correo valido.");
                    response.put("procede", false);
                    return response;
                }
            } else {
                JSONObject response = new JSONObject();
                response.put("mensaje", "La cuenta no esta habilitada para realizar registros.");
                response.put("procede", false);
                return response;

            }
        } else {
            JSONObject response = new JSONObject();
            response.put("mensaje", "La cuenta no esta habilitada para realizar registros.");
            response.put("procede", false);
            return response;
        }
         */
    }

    @RequestMapping(value = "/CreaRegistro", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String CreaRegistro(@RequestBody JSONObject json) {

        System.out.println("Este es el json");
        System.out.println(json);

        String Contrasenia = Encriptacion.Encriptar.Next(json.get("Pass").toString());

        String query = "INSERT INTO `usuario_sys` (`usuario`, `nombre`, `apellidos`, `correo`, `contrasenia`, `tipo`, `estatus`, `sesion`, `disponibilidad`, `registrado_por_usuario`,`puede_registrar`) "
                + "VALUES ('" + json.get("Usuario") + "', '" + json.get("Nombre") + "', '" + json.get("Apellidos") + "', "
                + "'" + json.get("Correo") + "', '" + Contrasenia + "', 'Administrador', '1', '0', '1', '" + json.get("RegistradoPor") + "','" + json.get("Confirma") + "');";

        System.out.println(query);

        boolean response = Query.update(query);
        System.out.println(response);
        if (response) {
            return "{\"status\":200}";
        }
        return "";
    }

    @RequestMapping(value = "/FolioImprocedente", method = RequestMethod.POST/*, produces = "application/json;charset=UTF-8", consumes = "application/json"*/)
    @ResponseBody
    public String FolioImprocedente(@RequestBody String json) throws IOException {

        String url = config.getURL_CONTROLADOR() + "/API/FolioImprocedente";
        //System.out.println(json);
        System.out.println("FolioImprocedente");
        return request.POST(url, json);
    }

    @RequestMapping(value = "/Folio", method = RequestMethod.POST/*, produces = "application/json;charset=UTF-8", consumes = "application/json"*/)
    @ResponseBody
    public String Folio(@RequestBody String json) throws IOException {

        String url = config.getURL_CONTROLADOR() + "/API/Folio";
        //System.out.println(json);
        System.out.println("FolioProcedente");
        return request.POST(url, json);
    }

    @RequestMapping(value = "/GeneraCredenciales", method = RequestMethod.POST/*, produces = "application/json;charset=UTF-8", consumes = "application/json"*/)
    @ResponseBody
    public static String GeneraCredenciales(@RequestBody String json) throws IOException {

        String url = config.getURL_CONTROLADOR() + "API/Credenciales";
        //System.out.println(json);
        System.out.println("Generando credenciales");
        return request.POST(url, json);
    }

    /**
     * *********************************** El siguiente codigo es para la parte
     * infromativa de los elementos
     *
     * @param json
     * @return
     * @throws java.io.IOException
     * **********************************************
     */
    @RequestMapping(value = "/ReporteElemento", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String ReporteIncidenteOperativo(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("En ReporteIncidenteOperativo de " + Dependencia);
        //System.out.println(json);
        String query = "";
        if (json.containsKey("id")) {
            query = "SELECT id FROM ReporteElemento where id = '" + json.get("id") + "';";
            if (Query.select(query) == null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
                DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
                Date date = new Date();
                json.put("fecha_recepcion", dateFormat.format(date));
                json.put("hora_recepcion", hourFormat.format(date));
                JSONObject respuesta = ReporteElemento.agregarReporte(json);
                if (!respuesta.get("estado").toString().equals("fallo")) {
                    json.put("id", respuesta.get("folio"));
                    json.put("reporteelemento", true);
                    json.put("Nuevo", false);
                    SocketEndPoint.AddBackupReporteElemento(json);
                    json.put("Nuevo", true);
                    SocketEndPoint.EnviarNotificacion(json);
                    return respuesta.toString();
                } else {
                    return respuesta.toString();
                }
            }
        } else {
            System.out.println("Pidiendo Ticket en ReporteElemento de -------> " + Dependencia);
            JSONParser parser = new JSONParser();
            String[] date = json.get("cuando").toString().split(" ");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("origen", config.getPATH() + Dependencia);
            jsonObject.put("fecha", date[0]);
            jsonObject.put("hora", date[1]);
            jsonObject.put("tipo", "reporteIO");
            String ticket = Post.GenerarTicket(jsonObject);
            System.out.println(ticket);
            JSONObject JsonTicket = (JSONObject) parser.parse(ticket);

            if (JsonTicket.get("ticket") != null) {
                json.put("id", JsonTicket.get("ticket"));
                JSONObject respuesta = ReporteElemento.agregarReporte(json);
                if (!respuesta.get("estado").toString().equals("fallo")) {
                    json.put("id", respuesta.get("folio"));
                    query = "SELECT nombre,apellido_paterno,apellido_materno FROM usuarios_movil WHERE idUsuarios_Movil = '" + json.get("idUsuario") + "';";
                    JSONArray jsonA = Query.execute(query);
                    json.put("nombre", ((JSONObject) jsonA.get(0)).get("nombre"));
                    json.put("apellido_paterno", ((JSONObject) jsonA.get(0)).get("apellido_paterno"));
                    json.put("apellido_materno", ((JSONObject) jsonA.get(0)).get("apellido_materno"));
                    json.put("reporteelemento", true);
                    json.put("Nuevo", false);
                    SocketEndPoint.AddBackupReporteElemento(json);
                    json.put("Nuevo", true);
                    SocketEndPoint.EnviarNotificacion(json);
                    return respuesta.toString();
                } else {
                    return respuesta.toString();
                }
            } else {
                return "{}";
            }
        }
        return "{}";
    }

    @RequestMapping(value = "/TraeReportes", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONArray TraeReportes() {
        return ReporteElemento.traeReportes();
    }

    @RequestMapping(value = "/TraeReporteID", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONArray TraeReporteID(@RequestBody JSONObject json) {
        return ReporteElemento.traeReporteID(json);
    }

    @RequestMapping(value = "/IncidentesSimilares", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public JSONArray IncidentesSimilares(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println(json);
        JSONArray incidentesSimilares = ReporteElemento.buscaSimilares(json);
        //System.out.println(incidentesSimilares);
        return incidentesSimilares;
        //return "{\"success\":\"ok\"}";
    }

    @RequestMapping(value = "/actualizaReporteElemento", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject actualizaReporteElemento(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("En /actualizaReporteElemento de ---->" + Dependencia);
        JSONParser parser = new JSONParser();
        JSONObject response = ReporteElemento.actualizaReporteElemento(json);
        if (response != null) {
            json.put("idLlamada", json.get("id"));
            json.remove("reporte");
            JSONObject status = (JSONObject) parser.parse(request.POST(config.getURL_CONTROLADOR() + "API/ActualizaTicket", json.toString()));
            if (status.containsKey("success")) {
                return response;
            } else {
                response = new JSONObject();
                return response;
            }
        }
        response = new JSONObject();
        return response;
    }

    @RequestMapping(value = "/NotDependneciasRE", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String NotDependneciasRE(@RequestBody String json) throws ParseException, IOException {
        System.out.println("json de NotDependneciasRE en ---> " + Dependencia);

        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        System.out.println(jsonObj);
        JSONObject reporte = (JSONObject) parser.parse(jsonObj.get("data").toString());
        String query = "UPDATE ReporteElemento SET `fecha_envio` = '" + reporte.get("fecha_envio") + "',"
                + "`hora_envio` = '" + reporte.get("hora_envio") + "' where id = '" + reporte.get("id") + "';";
        Query.update(query);

        JSONArray url = (JSONArray) jsonObj.get("url");
        for (int i = 0; i < url.size(); i++) {
            String string = url.get(i).toString();
            String path = string + "/ReporteElemento";
            System.out.println(path);
            request.POST(path, jsonObj.get("data").toString());
        }
        return "{\"succes\":1}";
    }

    /**
     * ****************************** Fin parte codigo para la
     * Informativa*******************************************************************
     */
    @RequestMapping(value = "/API/Validar_ip_local", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JSONObject ValidarIPlocal(@RequestBody JSONObject json) throws IOException {
        System.out.println("Validar_ip_publica " + Dependencia);
        System.out.println(json);
        if (ValidarIP.Validacion_ip_local(json)) {
            return Acceso.generateToken();
        }
        return null;
    }

    @RequestMapping(value = "/API/BanderaListaBlanca", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public boolean BanderaListaBlanca(@RequestBody JSONObject json) {
        config.setLISTA_BLANCA((boolean) json.get("bandera"));
        return config.isLISTA_BLANCA();
    }

    @RequestMapping(value = "/API/Agregar_Directorio", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void Agregar_Directorio(@RequestBody JSONObject json) throws ParseException {

        //json.put("idSuperior", config.getIdSuperior());
        //json.put("idServicio", config.getIdServicio());
        System.out.println("Agregar_Directorio " + Dependencia);
        System.out.println(json);
        if (json.get("idSuperior").toString().equals(null)) {
            System.out.println("#####333333333333 es nullll");
        }
        if (UsuarioMovilDAO.ExisteUsuarioDirectorio(json.get("idUsuario").toString())) {
            String query = "update directorio set activo=0 where idUsuario=" + json.get("idUsuario") + ";";
            Query.update(query);
            ////
            // poner activo =0 de los usuarios con activo =3
            //actualizar query de insertar usuario
            /////
            query = "update grupos_usuario_movil set estado =0 where idUsuarios_Movil='" + json.get("idUsuario") + "' AND estado =3;";
            Query.update(query);
        }
        String query = "INSERT INTO `directorio` (`idUsuario`, `Nombre`, `urlServicio`, `aliasServicio`, `tipo_usuario`, `tipo_servicio`) "
                + "VALUES ('" + json.get("idUsuario") + "', '" + json.get("nombre") + "', '" + json.get("urlServicio") + "', '" + json.get("aliasServicio") + "', '" + json.get("tipo_usuario") + "', '" + json.get("tipo_servicio") + "');";
        //String query = "INSERT INTO `directorio` (`idUsuario`, `Nombre`, `urlServicio`) VALUES ('" + json.get("idUsuario") + "', '" + json.get("nombre") + "', '" + json.get("urlServicio") + "');";
        if (Query.insert(query) > 0) {
            System.out.println(json);
            System.out.println("Agregado a directorio: " + Dependencia);

            //Verificar que exista el grupo designado para el usuario 
            query = "SELECT idgruposUsuarioSys as id FROM grupos_usuario_sys where nombre = '" + json.get("aliasServicio") + "' AND estado = 3;";
            System.out.println(query);
            JSONArray arrayGrupo = Query.execute(query);
            System.out.println(arrayGrupo.size());
            if (arrayGrupo.isEmpty()) {
                //insert 
                //query = "INSERT INTO `grupos_usuario_sys` (`nombre`, `estado`, `idServicio`, `idSuperior`) VALUES ('" + json.get("aliasServicio") + "', '3', '" + json.get("idServicio") + "', " + json.get("idSuperior") + ");";
                query = "INSERT INTO `grupos_usuario_sys` (`nombre`, `estado`, `idServicio`, `idSuperior`, `tipo_usuario`, `tipo_servicio`) "
                        + "VALUES ('" + json.get("aliasServicio") + "', '3', '" + json.get("idServicio") + "', '" + json.get("idSuperior") + "', '" + json.get("tipo_usuario") + "', '" + json.get("tipo_servicio") + "');";
                System.out.println(query);
                int id = Query.insert(query);
                if (id < 0) {
                    System.out.println("Hubo un error al crear el grupo: " + json.get("aliasServicio"));
                } else {
                    System.out.println("El arreglo no esta vacio");
                    query = "INSERT INTO `grupos_usuario_movil` (`idgruposUsuarioSys`, `idUsuarios_Movil`, `estado`) VALUES ('" + id + "', '" + json.get("idUsuario") + "','3');";
                    System.out.println(query);
                    if (Query.insert(query) < 0) {
                        System.out.println("Hubo un error al insertar el usuario: " + json.get("idUsuario") + " al grupo: " + json.get("aliasServicio"));
                    }
                }

            } else {
                JSONObject id = (JSONObject) arrayGrupo.get(0);

                query = "INSERT INTO `grupos_usuario_movil` (`idgruposUsuarioSys`, `idUsuarios_Movil`, `estado`) VALUES ('" + id.get("id") + "', '" + json.get("idUsuario") + "','3');";
                if (Query.insert(query) < 0) {
                    System.out.println("Hubo un error al insertar el usuario: " + json.get("idUsuario") + " al grupo: " + json.get("aliasServicio"));
                }
            }

            //Deshabilitar de grupos personalizados 
//            query = "UPDATE `grupos_usuario_movil` SET `estado`='1' WHERE `idUsuarios_Movil`='" + json.get("idUsuario") + "' AND estado=4;";
//            if (Query.update(query)) {
//                System.out.println("Usuario: " + json.get("idUsuario") + " habilitado del los grupos personalizados en: " + Dependencia);
//            }
        }
    }

    @RequestMapping(value = "/API/Agregar_Directorio2", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JSONObject Agregar_Directorio2(@RequestBody JSONObject json) throws ParseException, IOException {
        JSONObject respuesta = new JSONObject();
        System.out.println("Agregar_Directorio " + Dependencia);

        json.put("aliasServicio", config.getAliasServicio() + " " + json.get("servicio"));
        json.put("idSuperior", config.getIdSuperior());
        json.put("idServicio", config.getIdServicio());

        System.out.println(json);

        if (((String) json.get("idSuperior")) == null) {
            json.put("idSuperior", "NULL");
        }

        if (UsuarioMovilDAO.ExisteUsuarioDirectorio(json.get("idUsuario").toString())) {
            String query = "update directorio set activo=0 where idUsuario=" + json.get("idUsuario") + ";";
            Query.update(query);
            query = "update grupos_usuario_movil set estado = 0 where idUsuarios_Movil='" + json.get("idUsuario") + "' AND estado = 3;";
            Query.update(query);
        }
        //String query = "INSERT INTO `directorio` (`idUsuario`, `Nombre`, `urlServicio`, `aliasServicio`) VALUES ('" + json.get("idUsuario") + "', '" + json.get("nombre") + "', '" + json.get("urlServicio") + "', '" + json.get("aliasServicio") + "');";
        String query = "INSERT INTO `directorio` (`idUsuario`, `Nombre`, `urlServicio`, `aliasServicio`, `tipo_usuario`, `tipo_servicio`) "
                + "VALUES ('" + json.get("idUsuario") + "', '" + json.get("nombre") + "', '" + json.get("urlServicio") + "', '" + json.get("aliasServicio") + "', '" + json.get("tipo_usuario") + "', '" + json.get("tipo_servicio") + "');";
        int id_directorio = Query.insert(query);
        respuesta.put("id_directorio", id_directorio);
        respuesta.put("id_grupo", -1);
        if (id_directorio > 0) {
            System.out.println(json);
            System.out.println("Agregado a directorio: " + Dependencia);
            json.put("url", config.getPATH() + Dependencia);

//            JSONObject servicio = new JSONObject();
//            servicio.put("url", config.getPATH() + Dependencia);
//            String url = request.POST(config.getURL_CONTROLADOR() + "API/ServicioSuperior", servicio.toString());
//            JSONParser parser = new JSONParser();
            JSONArray arrayServicios = config.getSuperiores();
            for (int i = 0; i < arrayServicios.size(); i++) {
                System.out.println(arrayServicios.get(i));
                request.POST(arrayServicios.get(i).toString() + "/API/Agregar_Directorio", json.toString());
            }

            //Verificar que exista el grupo designado para el usuario 
            query = "SELECT idgruposUsuarioSys as id FROM grupos_usuario_sys where nombre = '" + json.get("aliasServicio") + "' AND estado = 3;";
            System.out.println(query);
            JSONArray arrayGrupo = Query.execute(query);
            System.out.println(arrayGrupo.size());
            if (arrayGrupo.isEmpty()) {
                //insert 
//                query = "INSERT INTO `grupos_usuario_sys` (`nombre`, `estado`, `idServicio`, `idSuperior`, `tipo_usuario`, `tipo_servicio`) "
//                        + "VALUES ('" + json.get("aliasServicio") + "', '3', '" + json.get("idServicio") + "', '" + json.get("idSuperior") + "', '" + json.get("tipo_usuario") +"', '" + json.get("tipo_servicio") +"');";

                query = "INSERT INTO `grupos_usuario_sys` (`nombre`, `estado`, `idServicio`, `idSuperior`, `tipo_usuario`, `tipo_servicio`) "
                        + "VALUES ('" + json.get("aliasServicio") + "', '3', '" + json.get("idServicio") + "', " + json.get("idSuperior") + ", '" + json.get("tipo_usuario") + "', '" + json.get("tipo_servicio") + "');";
                int id = Query.insert(query);
                if (id < 0) {
                    System.out.println("Hubo un error al crear el grupo: " + json.get("aliasServicio"));
                } else {
                    query = "INSERT INTO `grupos_usuario_movil` (`idgruposUsuarioSys`, `idUsuarios_Movil`, `estado`,`tipo_usuario`,`tipo_servicio`) VALUES ('" + id + "', '" + json.get("idUsuario") + "','3','" + json.get("tipo_usuario") + "','" + json.get("tipo_servicio") + "');";
                    int id_grupo = Query.insert(query);
                    respuesta.put("id_grupo", id_grupo);
                    if (id_grupo < 0) {
                        System.out.println("Hubo un error al insertar el usuario: " + json.get("idUsuario") + " al grupo: " + json.get("aliasServicio"));
                    }
                }

            } else {
                JSONObject id = (JSONObject) arrayGrupo.get(0);

                query = "INSERT INTO `grupos_usuario_movil` (`idgruposUsuarioSys`, `idUsuarios_Movil`, `estado`,`tipo_usuario`,`tipo_servicio`) VALUES ('" + id.get("id") + "', '" + json.get("idUsuario") + "','3','" + json.get("tipo_usuario") + "','" + json.get("tipo_servicio") + "');";
                System.out.println(query);
                int id_grupo = Query.insert(query);
                respuesta.put("id_grupo", id_grupo);
                if (id_grupo < 0) {
                    System.out.println("Hubo un error al insertar el usuario: " + json.get("idUsuario") + " al grupo: " + json.get("aliasServicio"));
                }
            }

            System.out.println("json");

            //Deshabilitar de grupos personalizados 
//            query = "UPDATE `grupos_usuario_movil` SET `estado`='1' WHERE `idUsuarios_Movil`='" + json.get("idUsuario") + "' AND estado=4;";
//            if (Query.update(query)) {
//                System.out.println("Usuario: " + json.get("idUsuario") + " habilitado del los grupos personalizados en: " + Dependencia);
//            }
        }
        return respuesta;

    }

    @RequestMapping(value = "/API/GenerarDirectorio", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void GenerarDirectorio() throws ParseException, IOException {
        System.out.println("GenerarDirectorio: " + Dependencia);
        String query = "SELECT idUsuarios_Movil AS idUsuario,nombre,apellido_paterno FROM usuarios_movil;";
        //`idUsuario`, `Nombre`, `urlServicio`
        JSONArray array = Query.execute(query);
        for (int i = 0; i < array.size(); i++) {
            JSONObject json = (JSONObject) array.get(i);
            json.put("nombre", json.get("nombre") + " " + json.get("apellido_paterno"));
            //json.remove("nombre");
            json.remove("apellido_paterno");
            json.put("urlServicio", config.getPATH() + Dependencia);
            System.out.println(array.get(i));
            Agregar_Directorio2(json);
        }
    }

    @RequestMapping(value = "/API/ConsultarDirectorio", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JSONObject ConsultarDirectorio(@RequestBody JSONObject jsonObj) throws ParseException, IOException {
        System.out.println("ConsultarDirectorio: " + Dependencia);
        JSONObject json = new JSONObject();
        JSONArray arr = new JSONArray();
        String query = "SELECT * from directorio where activo =1 AND tipo_usuario='" + jsonObj.get("tipo_usuario") + "' AND tipo_servicio='" + jsonObj.get("tipo_servicio") + "' AND tipo_area = '" + jsonObj.get("tipo_area") + "' ; ";
        //`idUsuario`, `Nombre`, `urlServicio`
        if (config.getPATH().contains("cmx")) {
            if (jsonObj.get("tipo_servicio").toString().equals("30")) {
                query = "SELECT * from directorio "
                        + "where activo =1 "
                        + "AND  (tipo_servicio='" + jsonObj.get("tipo_servicio") + "' "
                        + "OR  tipo_servicio='29' "
                        + "OR  tipo_servicio='48'); ";
            }
        }

        //Encargado de area
        if (jsonObj.get("tipo_area").toString().equals("0")) {
            query = "SELECT * from directorio "
                    + "where activo =1 "
                    + "AND  (tipo_usuario='" + jsonObj.get("tipo_usuario") + "' AND tipo_servicio='" + jsonObj.get("tipo_servicio") + "'); ";
        }
        //Usuario Maestro
        if (jsonObj.get("tipo_servicio").toString().equals("0")) {
            query = "SELECT * from directorio "
                    + "where activo =1 "
                    + "AND  (tipo_usuario='" + jsonObj.get("tipo_usuario") + "'); ";
        }
        //Usuario Super Maestro
        if (jsonObj.get("tipo_servicio").toString().equals("0") && jsonObj.get("tipo_usuario").toString().equals("0")) {
            query = "SELECT * from directorio "
                    + "where activo =1; ";
        }

        JSONArray array = Query.execute(query);

        for (int i = 0; i < array.size(); i++) {
            JSONObject user = (JSONObject) array.get(i);
            user.put("id360", user.get("idUsuario"));
        }
        array = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfiles/array", array);
        
        for (int i = 0; i < array.size(); i++) {
            JSONObject servicio = ((JSONObject) array.get(i));
            
            if ((Boolean) servicio.get("failure")) {
               array.remove(i);
               i--;
            }
        }
        json.put("directorio", array);
        //JSONParser parser = new JSONParser();
        //arr = (JSONArray) parser.parse(request.POST(config.getURL_CONTROLADOR() + "API/ConsultarServicios", arr.toString()));
        //json.put("dependencias", arr);
        return json;
    }

    @RequestMapping(value = "/NuevosIntegrantes", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray NuevosIntegrantes(@RequestBody JSONObject json) throws ParseException {
        System.out.println("NuevosIntegrantes: " + Dependencia);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(json.get("usuarios").toString());
        String id_Grupo = json.get("idGrupo").toString();
        JSONArray res = new JSONArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonUsr = (JSONObject) jsonArray.get(i);
            String idUsuarios_Movil = jsonUsr.get("idUsuario").toString();
            JSONObject estado = new JSONObject();
            boolean existe = UsuarioMovilDAO.ExisteUsuarioDirectorio(idUsuarios_Movil);
            if (existe) {
                boolean ExisteEnGrupo = GrupoDAO.ExisteUsuarioEnGrupo(id_Grupo, idUsuarios_Movil);
                if (!ExisteEnGrupo) {
                    boolean agregado = GrupoDAO.NuevoIntegrante(id_Grupo, idUsuarios_Movil);
                    if (!agregado) {
                        //model.addAttribute("Alerta", "Hubo un problema al intentar añadir el usuario: " + idUsuarios_Movil);
                        estado.put("usuario", idUsuarios_Movil);
                        estado.put("estado", "Usuario no agregado a grupo");
                        res.add(estado);
                    } else {
                        estado.put("usuario", idUsuarios_Movil);
                        estado.put("estado", "Agregado exitosamente");
                        res.add(estado);
                    }
                } else {
                    //model.addAttribute("Alerta", "El usuario: " + idUsuarios_Movil + " ya esta incluido en: " + NombreGrupo);
                    estado.put("usuario", idUsuarios_Movil);
                    estado.put("estado", "Usuario ya incluido en el grupo");
                    res.add(estado);
                }
            } else {

                estado.put("usuario", idUsuarios_Movil);
                estado.put("estado", "Usuario no registrado en la plataforma");
                res.add(estado);
            }
        }

        return res;
    }

    @RequestMapping(value = "/API/ConsultarFirebase", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String ConsultarFirebase(@RequestBody String id) {
        System.out.println("ConsultarFirebase " + Dependencia);
        String query = "SELECT FireBaseKey FROM usuarios_movil WHERE idUsuarios_Movil='" + id + "';";

        System.out.println(query);
        JSONArray array = Query.execute(query);
        if (!array.isEmpty()) {
            System.out.println(array.get(0));
            JSONObject json = (JSONObject) array.get(0);
            System.out.println(json.containsKey("FireBaseKey"));
            if (json.get("FireBaseKey") != null) {
                System.out.println(json.get("FireBaseKey"));
                return json.get("FireBaseKey").toString();
            }
        }
        return null;

    }

    @RequestMapping(value = "/InsertaNotificacion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject InsertaNotificacion(@RequestBody JSONObject json) {
        System.out.println("InsertaNotificacion: " + Dependencia);
        String query = null;
        if (json.containsKey("tipo_usuario") && json.containsKey("tipo_servicio")) {
            query = "INSERT INTO `notificaciones` (`idLlamada`, `idUsuarios_Movil`, `fecha`,`hora_envio`,`tipo_usuario`,`tipo_servicio`) "
                    + "VALUES ('" + json.get("idLlamada") + "', '" + json.get("idUsuario") + "', '" + json.get("fecha") + "','" + json.get("hora") + "', "
                    + "'" + json.get("tippo_usuario") + "','" + json.get("tipo_servicio") + "');";
        } else {
            query = "INSERT INTO `notificaciones` (`idLlamada`, `idUsuarios_Movil`, `fecha`,`hora_envio`) "
                    + "VALUES ('" + json.get("idLlamada") + "', '" + json.get("idUsuario") + "', '" + json.get("fecha") + "','" + json.get("hora") + "');";
        }

        int idNot = Query.insert(query);
        JSONObject jsonObj = new JSONObject();
        if (idNot > 0) {
            jsonObj.put("idNotificacion", idNot);
            return jsonObj;
        }
        jsonObj.put("failure", true);
        return jsonObj;
    }

    @RequestMapping(value = "/RecepcionNotificacion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public boolean RecepcionNotificacion(@RequestBody JSONObject json) {
        System.out.println("RecepcionNotificacion: " + Dependencia);
        String query = "UPDATE `notificaciones` SET `hora_recepcion`='" + json.get("hora") + "', `estado_recepcion`='1' WHERE `id`='" + json.get("id") + "';";
        boolean actualizado = Query.update(query);
        if (actualizado) {
            return actualizado;
        }
        return actualizado;
    }

    @RequestMapping(value = "/ActualizaHoraElemento", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public boolean ActualizaHoraElemento(@RequestBody JSONObject json) {
        String query = "UPDATE `notificaciones` SET `hora_desconexion`='" + Escalamiento.getHora() + "' WHERE `id`='" + json.get("idNotificacion") + "';";
        boolean actualizado = Query.update(query);

        if (actualizado) {
            return actualizado;
        }
        return actualizado;
    }

    @RequestMapping(value = "/RazonamientoElemento", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public boolean RazonamientoElemento(@RequestBody JSONObject json) {

        String query = "UPDATE `notificaciones` SET `razonamiento`='" + json.get("razonamiento") + "' WHERE `id`='" + json.get("id") + "';";
        boolean actualizado = Query.update(query);

        if (actualizado) {
            return actualizado;
        }
        return actualizado;
    }

    @RequestMapping(value = "/ActualizaEstadoNot", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public boolean ActualizaEstadoNot(@RequestBody JSONObject json) {

        String query = "UPDATE `notificaciones` SET `estado_envio`='0' WHERE `id`='" + json.get("idNotificacion") + "';";
        boolean actualizado = Query.update(query);
        if (actualizado) {
            return actualizado;
        }
        return actualizado;
    }

    @RequestMapping(value = "/API/BuscarComplementoPerfil", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String BuscarComplementoPerfil(@RequestBody JSONObject json) throws IOException {
        System.out.println("BuscarComplementoPerfil " + Dependencia);
        String query = "SELECT urlServicio FROM directorio where idUsuario ='" + json.get("idUsuario") + "' AND activo=1";
        JSONArray array = Query.execute(query);
        if (!array.isEmpty()) {
            String url = ((JSONObject) array.get(0)).get("urlServicio").toString();
            String respuesta = request.POST(url + "/API/CompletarPerfil", json.toString());
            System.out.println(respuesta);
            return respuesta;
        } else {
            System.out.println("ERROR. Usuario no existe en el directorio: " + json.get("idUsuario"));
        }
        return "ERROR. Usuario no existe en el directorio: " + json.get("idUsuario");
    }

    @RequestMapping(value = "/API/CompletarPerfil", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String CompletarPerfil(@RequestBody JSONObject json) {
        System.out.println("CompletarPerfil " + Dependencia);
        String query = "SELECT correo, telefono,contacto_nombre,contacto_telefono,alergias,rh,fecha_nacimiento,genero,direccion,cp,condicion_medica FROM  usuarios_movil where idUsuarios_Movil ='" + json.get("idUsuario") + "'";
        JSONArray array = Query.execute(query);
        if (!array.isEmpty()) {
            return array.get(0).toString();
        }
        return "ERROR. Usuario: " + json.get("idUsuario") + " no existe esta registrado en la plataforma: " + Dependencia;
    }

    @RequestMapping(value = "/API/BuscarRuta", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String BuscarRuta(@RequestBody JSONObject json) throws IOException {
        System.out.println("BuscarRuta " + Dependencia);
        String query = "SELECT urlServicio FROM directorio where idUsuario ='" + json.get("idUsuario") + "' AND activo=1";
        JSONArray array = Query.execute(query);
        if (!array.isEmpty()) {
            String url = ((JSONObject) array.get(0)).get("urlServicio").toString();
            String respuesta = request.POST(url + "/API/EnviarRuta", json.toString());
            System.out.println(respuesta);
            return respuesta;
        } else {
            System.out.println("ERROR. Usuario no existe en el directorio: " + json.get("idUsuario"));
        }
        return "ERROR. Usuario no existe en el directorio: " + json.get("idUsuario");
    }

    @RequestMapping(value = "/API/EnviarRuta", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String EnviarRuta(@RequestBody JSONObject json) {
        System.out.println("EnviarRuta " + Dependencia);
        String query = "SELECT ruta FROM registro_rutas WHERE idUsuarios_Movil='" + json.get("idUsuario") + "' AND Fecha='" + json.get("fecha") + "';";
        JSONArray array = Query.execute(query);
        if (!array.isEmpty()) {
            return array.get(0).toString();
        }
        return null;
    }

    @RequestMapping(value = "/API/DeshabilitarUsr_Directorio", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject DeshabilitarUsr_Directorio(@RequestBody JSONObject json) throws IOException {
        System.out.println("DeshabilitarUsr_Directorio " + Dependencia);
        JSONObject respuesta = new JSONObject();
        respuesta.put("idUsuario", json.get("idUsuario"));
        respuesta.put("existe", false);
        respuesta.put("procedio", false);

        String query = "SELECT urlServicio FROM directorio where idUsuario ='" + json.get("idUsuario") + "' AND activo=1;";
        JSONArray array = Query.execute(query);
        if (!array.isEmpty()) {
            respuesta.put("existe", true);
            String url = ((JSONObject) array.get(0)).get("urlServicio").toString();
            json.put("urlServicio", url);
            String res = request.POST(url + "/API/Baja_Directorio", json.toString());
            respuesta.put("procedio", Boolean.parseBoolean(res));
            System.out.println(res);

            return respuesta;
        }
        return respuesta;
    }

   
    @RequestMapping(value = "/API/Baja_Directorio", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public boolean Baja_Directorio(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("Baja_Directorio: " + Dependencia);
        boolean procede = deshabilitar_usr_directorio(json);
        if (procede) {
            JSONArray arrayServicios = config.getSuperiores();
            for (int i = 0; i < arrayServicios.size(); i++) {
                request.POST(arrayServicios.get(i).toString() + "/API/deshabilitar_usr_directorio", json.toString());
            }
        }
        return procede;

    }

    @RequestMapping(value = "/API/deshabilitar_usr_directorio", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public boolean deshabilitar_usr_directorio(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("deshabilitar_usr_directorio: " + Dependencia);
        boolean procede = false;
        //Desabilitar del directorio
        String query = "UPDATE `directorio` SET `activo`='0' WHERE `idUsuario`='" + json.get("idUsuario") + "' AND `activo`='1';";
        if (Query.update(query)) {
            System.out.println("Usuario: " + json.get("idUsuario") + " deshabilitado del directorio en: " + Dependencia);
            JSONArray GruposA = (JSONArray) getBackupDirectorio().get("GruposAutomaticos");
            JSONArray Integrantes = (JSONArray) getBackupDirectorio().get("integrantes");

            for (int i = 0; i < GruposA.size(); i++) {
                JSONObject grupo = (JSONObject) GruposA.get(i);
                if (grupo.get("urlServicio").equals(json.get("urlServicio"))) {
                    JSONArray integrantes = (JSONArray) grupo.get("integrantes");
                    for (int j = 0; j < (integrantes).size(); j++) {
                        if (integrantes.get(j).toString().equals(json.get("idUsuario").toString())) {
                            integrantes.remove(j);
                            break;
                        }

                    }
                    break;
                }
            }

            for (int i = 0; i < Integrantes.size(); i++) {
                JSONObject integrante = (JSONObject) Integrantes.get(i);
                if (integrante.get("idUsuarios_Movil").equals(json.get("idUsuario"))) {
                    Integrantes.remove(i);
                    break;
                }
            }

            procede = true;
        }

        /* Antes de deshabilitar el usuario de los grupos usuarios moviles revisar a que tipo de usuario y tipo de servicio pertenece */
        //Esto es para los grupos automaticos
        query = "SELECT * FROM grupos_usuario_movil WHERE idUsuarios_Movil = '" + json.get("idUsuario") + "' AND estado = 3;";
        System.out.println(query);
        JSONObject gum = Query.select(query);
        if (gum != null) {
            query = "select TU.tipo_usuario, SU.nombre  from tipos_usuarios TU, servicios_usuario SU where TU.id = " + gum.get("tipo_usuario") + " AND SU.id=" + gum.get("tipo_servicio") + " AND TU.id=SU.idTipoUsuario;";
            System.out.println(query);
            JSONObject servicio = Query.select(query);

            gum.put("aliasServicio", config.getAliasServicio() + " " + servicio.get("tipo_usuario") + " " + servicio.get("nombre"));
            BackupDirectorio.EliminaDeGrupoAutomatico(gum);
        }
        /**
         * ******************************************************************************************************************************
         */

        //Deshabilitar de grupos personalizados 
        query = "UPDATE `grupos_usuario_movil` SET `estado`='4' WHERE `idUsuarios_Movil`='" + json.get("idUsuario") + "' AND estado=1;";
        if (Query.update(query)) {
            System.out.println("Usuario: " + json.get("idUsuario") + " deshabilitado del directorio en: " + Dependencia);
        }
        //Deshabilitar de grupos automaticos 
        query = "UPDATE `grupos_usuario_movil` SET `estado`='0' WHERE `idUsuarios_Movil`='" + json.get("idUsuario") + "' AND estado=3;";
        if (Query.update(query)) {
            System.out.println("Usuario: " + json.get("idUsuario") + " deshabilitado del directorio en: " + Dependencia);

        }

        return procede;
    }

    /**
     * ************************************************************************************************************************************************
     */
    /* Para los grupos automaticos */
    @RequestMapping(value = "/BackupDirectorio", method = RequestMethod.POST/*, consumes = "application/json"*/, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject BackupDirectorio() throws ParseException, IOException {
        SetBackUpDirectorioNUll();
        JSONObject json = new JSONObject();
        json.put("GruposAutomaticos", Grupos.GruposAutomaticos());
        json.put("integrantes", ControladorGET.MapeoIntegrantes());
        json.put("Dependencia", config.getAliasServicio());
        //

        return json;
    }

    @RequestMapping(value = "/CrearBackupDirectorioGlobal", method = RequestMethod.POST/*, consumes = "application/json"*/, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONArray CrearBackupDirectorioGlobal() throws ParseException, IOException {
        System.out.println("Creando Backup Directorio Global");
        SetBackUpDirectorioNUll();
        JSONObject directorio = getBackupDirectorio();
        JSONArray grupos = (JSONArray) directorio.get("GruposAutomaticos");
        JSONArray array = new JSONArray();
        array.add(directorio);
        for (int i = 0; i < grupos.size(); i++) {
            System.out.println(grupos.get(i));
            JSONObject grupo = (JSONObject) grupos.get(i);
            if (!(config.getPATH() + Dependencia).equals(grupo.get("urlServicio").toString())) {
                JSONParser parser = new JSONParser();
                String response = request.GET(grupo.get("urlServicio") + "/ConsultarBackupDirectorio");

                if (response != null && !"null".equals(response) && !"".equals(response)) {
                    System.out.println("La respuesta es buena en CrearBackupDirectorioGlobal");
                    array.add((JSONObject) parser.parse(response));
                }

            }

        }
        return array;
    }

    @RequestMapping(value = "/GruposPersonalizados", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject GruposPersonalizados(@RequestBody JSONObject id) throws ParseException, IOException {
        System.out.println("GruposPersonalizados: " + Dependencia);
        JSONObject json = new JSONObject();
        Grupos grupos = new Grupos();
        json.put("GruposPersonalizados", grupos.GruposPersonalizados(id.get("idUsuarioSys").toString()));
        System.out.println(json);
        json.put("GruposAutomaticos", grupos.GruposAutomaticos(id.get("tipo_usuario").toString(), id.get("tipo_servicio").toString()));
        System.out.println(json);
        json.put("integrantes", grupos.integrantes((JSONArray) json.get("GruposAutomaticos")));
        System.out.println(json);
        json.put("Dependencia", config.getAliasServicio());

        System.out.println("GruposPersonalizados: ");
        return json;
//        JSONObject jsonObj = (JSONObject) getBackupDirectorio();
//        JSONParser parser = new JSONParser();
//        JSONObject BackUp = (JSONObject) parser.parse(jsonObj.toString());
//
//        String query = "";
//        JSONArray gruposAutomaticos = (JSONArray) BackUp.get("GruposAutomaticos");
//        JSONArray integrantesEliminar = new JSONArray();
//        if (!"0".equals(id.get("tipo_usuario").toString()) && "0".equals(id.get("tipo_servicio").toString())) {
//            query = "select TU.tipo_usuario, SU.nombre  from tipos_usuarios TU, servicios_usuario SU where TU.id = " + id.get("tipo_usuario") + " AND TU.id=SU.idTipoUsuario;";
//            JSONObject servicio = Query.select(query);
//            String aliasServicio = config.getAliasServicio() + " " + servicio.get("tipo_usuario");
//            for (int i = 0; i < gruposAutomaticos.size(); i++) {
//                JSONObject grupoAutomatico = (JSONObject) gruposAutomaticos.get(i);
//                if (!grupoAutomatico.get("nombre").toString().contains(aliasServicio)) {
//                    JSONArray array = (JSONArray) grupoAutomatico.get("integrantes");
//                    for (int j = 0; j < array.size(); j++) {
//                        integrantesEliminar.add(array.get(j));
//                    }
//                    gruposAutomaticos.remove(i);
//                    i--;
//                }
//            }
//            json.put("GruposAutomaticos", gruposAutomaticos);
//
//            JSONArray integrantes = (JSONArray) BackUp.get("integrantes");
//            for (int i = 0; i < integrantes.size(); i++) {
//                JSONObject jsonI = (JSONObject) integrantes.get(i);
//                for (int j = 0; j < integrantesEliminar.size(); j++) {
//                    if (jsonI.get("idUsuarios_Movil").toString().equals(integrantesEliminar.get(j).toString())) {
//                        integrantes.remove(i);
//                        i--;
//                        integrantesEliminar.remove(j);
//                        break;
//                    }
//                }
//            }
//            json.put("integrantes", integrantes);
////            return BackUp;
//        } else if ("0".equals(id.get("tipo_usuario").toString())) {
//            return json;
//        } else {
//            query = "select TU.tipo_usuario, SU.nombre  from tipos_usuarios TU, servicios_usuario SU where TU.id = " + id.get("tipo_usuario") + " AND SU.id=" + id.get("tipo_servicio") + " AND TU.id=SU.idTipoUsuario;";
//            System.out.println(query);
//            JSONObject servicio = Query.select(query);
//            String aliasServicio = config.getAliasServicio() + " " + servicio.get("tipo_usuario") + " " + servicio.get("nombre");
//
//            //traer solo si el usuario es la unidad temporal 
//            //SUCRE
//            String aliasServicio2 = null;
//            //CRUM
//            String aliasServicio3 = null;
//            boolean agregar = false;
//            if (id.get("tipo_servicio").toString().equals("30")) {
//                agregar = true;
//                query = "select TU.tipo_usuario, SU.nombre  from tipos_usuarios TU, servicios_usuario SU where TU.id ='21'  AND SU.id='48' AND TU.id=SU.idTipoUsuario";
//                //System.out.println(query);
//                servicio = Query.select(query);
//                aliasServicio2 = config.getAliasServicio() + " " + servicio.get("tipo_usuario") + " " + servicio.get("nombre");
//
//                query = "select TU.tipo_usuario, SU.nombre  from tipos_usuarios TU, servicios_usuario SU where TU.id ='19'  AND SU.id='29' AND TU.id=SU.idTipoUsuario;";
//                //System.out.println(query);
//                servicio = Query.select(query);
//                aliasServicio3 = config.getAliasServicio() + " " + servicio.get("tipo_usuario") + " " + servicio.get("nombre");
//
//            }
//
//            for (int i = 0; i < gruposAutomaticos.size(); i++) {
//                JSONObject grupoAutomatico = (JSONObject) gruposAutomaticos.get(i);
//                if (agregar) {
//                    if (!grupoAutomatico.get("nombre").toString().equals(aliasServicio) && !grupoAutomatico.get("nombre").toString().equals(aliasServicio2) && !grupoAutomatico.get("nombre").toString().equals(aliasServicio3)) {
//                        JSONArray array = (JSONArray) grupoAutomatico.get("integrantes");
//                        for (int j = 0; j < array.size(); j++) {
//                            integrantesEliminar.add(array.get(j));
//                        }
//                        gruposAutomaticos.remove(i);
//                        i--;
//                    }
//                } else {
//                    if (!grupoAutomatico.get("nombre").toString().equals(aliasServicio)) {
//                        JSONArray array = (JSONArray) grupoAutomatico.get("integrantes");
//                        for (int j = 0; j < array.size(); j++) {
//                            integrantesEliminar.add(array.get(j));
//                        }
//                        gruposAutomaticos.remove(i);
//                        i--;
//                    }
//                }
//            }
//            json.put("GruposAutomaticos", gruposAutomaticos);
//
//            JSONArray integrantes = (JSONArray) BackUp.get("integrantes");
//            for (int i = 0; i < integrantes.size(); i++) {
//                JSONObject jsonI = (JSONObject) integrantes.get(i);
//                for (int j = 0; j < integrantesEliminar.size(); j++) {
//                    if (jsonI.get("idUsuarios_Movil").toString().equals(integrantesEliminar.get(j).toString())) {
//                        integrantes.remove(i);
//                        i--;
//                        integrantesEliminar.remove(j);
//                        break;
//                    }
//                }
//            }
//            json.put("integrantes", integrantes);
////            return BackUp;
//        }
//
////        json.put("GruposAutomaticos", getBackupDirectorio().get("GruposAutomaticos"));
////        json.put("integrantes", getBackupDirectorio().get("integrantes"));
//        return json;
    }

    
    @RequestMapping(value = "/API/solicitud/dataGrupos", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject solicitud_dataGrupos(@RequestBody JSONObject jsonObj) throws ParseException, IOException {
        System.out.println("solicitud_dataGrupos: " + Dependencia);
        /*
        {
            "idUsuarioSys":"0", // validar que tenga la llave 
            "id360":"1",
            "idmodulo":"40",
            "modulo":"mapagis"                                                                                
        }
         */
        JSONObject respuesta = respuesta(false, "Ocurrio un problema intentelo de nuevo.");
        Grupos grupos = new Grupos();
        //Consultamos la informacion necesaria en el controlador correspondiente al id360 y el modulo
        JSONObject info_modulo = request.POST(config.getURL_CONTROLADOR() + "API/info/modulo360", jsonObj);
        if ((Boolean) info_modulo.get("success")) {
            if (info_modulo.get("tipo_usuario") != null && info_modulo.get("tipo_servicio") != null) {
                respuesta = respuesta(true, "Información encontrada.");
                if (jsonObj.containsKey("idUsuarioSys")) {
                    respuesta.put("GruposPersonalizados", grupos.GruposPersonalizados(jsonObj.get("idUsuarioSys").toString()));
                }
                String tipo_usuario = (String) info_modulo.get("tipo_usuario");
                String tipo_servicio = (String) info_modulo.get("tipo_servicio");
                String persona = (String) info_modulo.get("persona");
                String id360 = (String) jsonObj.get("id360");
                respuesta.put("GruposAutomaticos", grupos.GruposAutomaticosEmpresas360(tipo_usuario, tipo_servicio));
                respuesta.put("integrantes", grupos.integrantesEmpresas360((JSONArray) respuesta.get("GruposAutomaticos")));
                respuesta.put("Dependencia", config.getAliasServicio());
            } else {
                respuesta = respuesta(false, "Nivel de usuario no registrado");
            }

        }

        return respuesta;
    }

    @RequestMapping(value = "/API/UpdateBackupDirectorio", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void UpdateBackupDirectorioPOST(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("UpdateBackupDirectorioPOST: " + Dependencia);
        UpdateBackupDirectorio(json);
    }

    /**
     * ********** Fin de los Grupos Automaticos ******************
     */
    @RequestMapping(value = "/rutaNow", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject rutaNow(@RequestBody JSONObject idUsuario) throws ParseException, IOException {
        System.out.println("rutaNow: " + Dependencia);
        JSONParser parser = new JSONParser();
        JSONObject Ruta = new JSONObject();
        String query = "SELECT urlServicio FROM directorio WHERE idUsuario = \"" + idUsuario.get("idUsuario") + "\" AND activo = \"1\";";
        JSONArray url = Query.execute(query);
        if (url.isEmpty()) {
            return null;
        } else {
            JSONArray ConsultaRuta = (JSONArray) parser.parse(request.POST(((JSONObject) url.get(0)).get("urlServicio").toString() + "/ConsultaRuta", idUsuario.toString()));
            if (!ConsultaRuta.isEmpty()) {
                JSONObject ruta = new JSONObject();
                String Fecha = ((JSONObject) ConsultaRuta.get(0)).get("Fecha").toString();

                ruta.put("Ruta", ((JSONObject) ConsultaRuta.get(0)).get("Ruta"));
                ruta.put("Latitud", ((JSONObject) ConsultaRuta.get(0)).get("Latitud"));
                ruta.put("Longitud", ((JSONObject) ConsultaRuta.get(0)).get("Longitud"));
                ruta.put("Hora", ((JSONObject) ConsultaRuta.get(0)).get("Hora"));
                Ruta.put(Fecha, ruta);
            } else {
                JSONObject ruta = new JSONObject();
                String Fecha = Modelo.Escalamiento.getFecha();

                ruta.put("Ruta", "");
                ruta.put("Latitud", "");
                ruta.put("Longitud", "");
                ruta.put("Hora", "");
                Ruta.put(Fecha, ruta);
            }
        }

        return Ruta;
    }

    @RequestMapping(value = "/ConsultaRuta", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONArray ConsultaRuta(@RequestBody JSONObject idUsuario) {
        System.out.println("ConsultaRuta: " + Dependencia);
        String query = "SELECT Fecha,Ruta,Latitud,Longitud,Hora FROM registro_rutas WHERE idUsuarios_Movil = \"" + idUsuario.get("idUsuario") + "\" AND Fecha = DATE(NOW());";
        JSONArray json = (JSONArray) Query.execute(query);
        return json;
    }

    @RequestMapping(value = "/rutaDate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject rutaDate(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("rutaDate: " + Dependencia);
        JSONParser parser = new JSONParser();
        JSONObject Ruta = new JSONObject();
//        String query = "SELECT urlServicio FROM directorio WHERE idUsuario = \"" + json.get("idUsuario") + "\" AND activo = \"1\";";
//        JSONArray url = (JSONArray) Query.execute(query);
//        if (url.isEmpty()) {
//            return null;
//        } else {
            //JSONArray ConsultaRuta = (JSONArray) parser.parse(request.POST(((JSONObject) url.get(0)).get("urlServicio").toString() + "/ConsultaRutaFecha", json.toString()));
            JSONArray ConsultaRuta = ConsultaRutaFecha(json);
            if (!ConsultaRuta.isEmpty()) {
                JSONObject ruta = new JSONObject();
                String Fecha = ((JSONObject) ConsultaRuta.get(0)).get("Fecha").toString();
                ruta.put("Ruta", ((JSONObject) ConsultaRuta.get(0)).get("Ruta"));
                ruta.put("Latitud", ((JSONObject) ConsultaRuta.get(0)).get("Latitud"));
                ruta.put("Longitud", ((JSONObject) ConsultaRuta.get(0)).get("Longitud"));
                ruta.put("Hora", ((JSONObject) ConsultaRuta.get(0)).get("Hora"));
                Ruta.put(Fecha, ruta);
            } else {
                JSONObject ruta = new JSONObject();
                String Fecha = json.get("fecha").toString();
                ruta.put("Ruta", "");
                ruta.put("Latitud", "");
                ruta.put("Longitud", "");
                ruta.put("Hora", "");
                Ruta.put(Fecha, ruta);
            }

//        }

        return Ruta;
    }

    @RequestMapping(value = "/ConsultaRutaFecha", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONArray ConsultaRutaFecha(@RequestBody JSONObject jsonObj) {
        System.out.println("ConsultaRutaFecha: " + Dependencia);
        String query = "SELECT Fecha,Ruta,Latitud,Longitud,Hora  FROM registro_rutas WHERE idUsuarios_Movil = \"" + jsonObj.get("idUsuario") + "\" AND Fecha = \"" + jsonObj.get("fecha") + "\";";
        JSONArray json = (JSONArray) Query.execute(query);
        return json;
    }

    @RequestMapping(value = "/API/backup/ConsultaIcono", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject ConsultaIcono(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("ConsultaIcono: " + Dependencia);
        JSONArray integrantes = (JSONArray) getBackupDirectorio().get("integrantes");
        JSONObject respuesta = new JSONObject();
        respuesta.put("existe", false);
        for (int i = 0; i < integrantes.size(); i++) {
            JSONObject integrante = (JSONObject) integrantes.get(i);
            if (integrante.get("idUsuarios_Movil").toString().equals(json.get("idUsuarios_Movil").toString())) {
                respuesta.put("existe", true);
                respuesta.put("icon", integrante.get("icon"));
                break;
            }
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/backup/ConsultaImg", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject ConsultaImg(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("ConsultaImg: " + Dependencia);
        JSONArray integrantes = (JSONArray) getBackupDirectorio().get("integrantes");
        JSONObject respuesta = new JSONObject();
        respuesta.put("existe", false);
        for (int i = 0; i < integrantes.size(); i++) {
            JSONObject integrante = (JSONObject) integrantes.get(i);
            if (integrante.get("idUsuarios_Movil").toString().equals(json.get("idUsuarios_Movil").toString())) {
                respuesta.put("existe", true);
                respuesta.put("img", integrante.get("img"));

                break;
            }
        }
        return respuesta;
    }

    @RequestMapping(value = "/restOpentok/ArchiveSession", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject ArchiveSession(@RequestBody JSONObject OpenTok) throws ParseException, IOException {
        System.out.println("ArchiveSession: " + Dependencia);
        String query_url = config.getURL_CONTROLADOR() + "restOpentok/ArchiveSession";
        String url = Request.request.POST(query_url, OpenTok.toString());
        JSONObject respuesta = new JSONObject();
        respuesta.put("ruta_video", url);
        return respuesta;
    }

    @RequestMapping(value = "/API/LlamadaSaliente/Ruta_video", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject LlamadaSalienteRuta_video(@RequestBody JSONObject json) {
        System.out.println("LlamadaSalienteRuta_video: " + Dependencia);
        if (json.get("ruta_video") != null && json.get("ruta_video") != "null") {
            String query = "select ruta_video from `registro_llamadas_salientes` where `idLlamada`= '" + json.get("idLlamada") + "' AND `idOperador`= '" + json.get("idSys") + "';";
            JSONArray array = Query.execute(query);
            if (!array.isEmpty()) {
                JSONObject ruta = (JSONObject) array.get(0);
                if (ruta.get("ruta_video") == null) {
                    query = "UPDATE `registro_llamadas_salientes` SET `ruta_video` = '" + json.get("ruta_video") + "', `hora_recepcion` = '" + json.get("hora_inicio") + "', `hora_conexion` = '" + json.get("hora_conexion") + "' WHERE (`idLlamada` = '" + json.get("idLlamada") + "' AND `idOperador` = '" + json.get("idSys") + "')  ;";
                    System.out.println(json);
                    Query.update(query);
                }

            }
        }

        return json;
    }

    @RequestMapping(value = "/API/LlamadaSaliente/RegistrarDesconexionUsr", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject RegistrarDesconexionUsr(@RequestBody JSONObject json) {
        System.out.println("RegistrarDesconexionUsr: " + Dependencia);
        String query = "UPDATE registro_llamadas_salientes SET hora_desconexion='" + json.get("hora_desconexion") + "' WHERE idLlamada='" + json.get("idLlamada") + "' AND idUsuario='" + json.get("idUsuario") + "'";
        json.put("RegistroDesconexion", Query.update(query));
        return json;
    }

    @RequestMapping(value = "/API/LlamadaSaliente/RegistrarDesconexionOp", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject RegistrarDesconexionOp(@RequestBody JSONObject json) {
        System.out.println("RegistrarDesconexionOp: " + Dependencia);
        String query = "UPDATE registro_llamadas_salientes SET hora_desconexion='" + json.get("hora_desconexion") + "', chat='" + json.get("chat") + "' WHERE idLlamada='" + json.get("idLlamada") + "' AND idOperador='" + json.get("idSys") + "'";
        json.put("RegistroDesconexion", Query.update(query));
        return json;
    }

    @RequestMapping(value = "/API/LlamadaSaliente/RegistrarConexionUsr", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject RegistrarConexionUsr(@RequestBody JSONObject json) {
        System.out.println("RegistrarConexionUsr: " + Dependencia);
        String query = "UPDATE registro_llamadas_salientes SET hora_conexion='" + json.get("hora_conexion") + "' WHERE idLlamada='" + json.get("idLlamada") + "' AND idOperador='" + json.get("idUsuario") + "'";
        json.put("RegistroDesconexion", Query.update(query));
        return json;
    }

    @RequestMapping(value = "/API/LlamadaSaliente/RegistrarNotificaciones", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject RegistrarNotificaciones(@RequestBody JSONObject json) {
        System.out.println("RegistrarNotificaciones: " + Dependencia);
        JSONObject respuesta = new JSONObject();
        ArrayList<String> array = (ArrayList<String>) json.get("idUsuarios_Movil");
        if (json.containsKey("tipo_usuario") && json.containsKey("tipo_servicio")) {
            for (int i = 0; i < array.size(); i++) {
                String query = "INSERT INTO `notificaciones` (`idLlamada`,`idUsuarios_Movil`, `fecha`, `hora_envio`,`tipo_usuario`,`tipo_servicio`) "
                        + "VALUES ('" + json.get("idLlamada") + "', '" + array.get(i) + "', '" + json.get("fecha") + "', '" + json.get("hora") + "', "
                        + "'" + json.get("tipo_usuario") + "', '" + json.get("tipo_servicio") + "');";

                System.out.println(query);

                respuesta.put(array.get(i), Query.insert(query));
            }
        } else {
            for (int i = 0; i < array.size(); i++) {
                String query = "INSERT INTO `notificaciones` (`idLlamada`,`idUsuarios_Movil`, `fecha`, `hora_envio`) "
                        + "VALUES ('" + json.get("idLlamada") + "', '" + array.get(i) + "', '" + json.get("fecha") + "', '" + json.get("hora") + "');";

                System.out.println(query);

                respuesta.put(array.get(i), Query.insert(query));
            }
        }

        return respuesta;
    }

    @RequestMapping(value = "/API/solicitudVideo/RegistroConexion", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JSONObject RegistroConexion(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("RegistroConexion: " + Dependencia);
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", false);
        respuesta.put("failure", true);
        System.out.println("RegistroConexion en: " + Dependencia);
        String query = "UPDATE `notificaciones` SET `hora_conexion`='" + json.get("hora") + "' WHERE `id`='" + json.get("idNotificacion") + "';";
        System.out.println(query);
        if (!Query.update(query)) {
            System.out.println("Error al registrar ruta_video del elemeneto");
        } else {
            respuesta.put("success", true);
            respuesta.put("failure", false);
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/solicitudVideo/RegistroRecepcion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject RegistroRecepcion(@RequestBody JSONObject json) {
        System.out.println("RegistroRecepcion en: " + Dependencia);
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", false);
        respuesta.put("failure", true);

        String query = "UPDATE `notificaciones` SET `hora_recepcion`='" + json.get("hora") + "', `estado_recepcion`='1' WHERE `id`='" + json.get("idNotificacion") + "';";
        boolean actualizado = Query.update(query);

        if (actualizado) {
            respuesta.put("success", true);
            respuesta.put("failure", false);
        } else {
            System.out.println("Error RegistroRecepcion de: " + json.get("idUsuario"));
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/solicitudVideo/RegistroDesconexion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject RegistroDesconexion(@RequestBody JSONObject json) {
        System.out.println("RegistroDesconexion en: " + Dependencia);
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", false);
        respuesta.put("failure", true);

        String query = "UPDATE `notificaciones` SET `hora_desconexion`='" + json.get("hora") + "' WHERE `id`='" + json.get("idNotificacion") + "';";
        boolean actualizado = Query.update(query);

        if (actualizado) {
            respuesta.put("success", true);
            respuesta.put("failure", false);
        } else {
            System.out.println("Error RegistroDesconexion de: " + json.get("idUsuario"));
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/solicitudVideo/guardarReporteLlamadaSaliente", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject guardarReporteLlamadaSaliente(@RequestBody JSONObject json) throws IOException {
        String query = null;
        if (json.containsKey("tipo_usuario") && json.containsKey("tipo_servicio")) {
            query = "INSERT INTO `reporte_llamadas` (`idLlamada`, `temergencia`, `folioexterno`, `hora`, `fecha`,`reporte`,`tipo_usuario`,`tipo_servicio`) "
                    + "VALUES ('" + json.get("idLlamada") + "', '" + json.get("motivo") + "', '" + json.get("folio") + "', '" + json.get("hora") + "', "
                    + "'" + json.get("fecha") + "','" + json.get("reporte") + "', '" + json.get("tipo_usuario") + "', '" + json.get("tipo_servicio") + "');";

        } else {
            query = "INSERT INTO `reporte_llamadas` (`idLlamada`, `temergencia`, `folioexterno`, `hora`, `fecha`,`reporte`) "
                    + "VALUES ('" + json.get("idLlamada") + "', '" + json.get("motivo") + "', '" + json.get("folio") + "', '" + json.get("hora") + "', '" + json.get("fecha") + "','" + json.get("reporte") + "');";

        }
        json.put("id", Query.insert(query));
        json.put("folioexterno", json.get("folio"));
        String URL = "API/ActualizaTicket";
        /* vaer si la funcion va a regrear algo*/
        String actualizado = request.POST(config.getURL_CONTROLADOR() + URL, json.toString());
        json.put("actualizado", actualizado);
        return json;
    }

    @RequestMapping(value = "/API/solicitudVideo/RegistrarEnvioFallido", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject RegistrarEnvioFallido(@RequestBody JSONObject json) {
        System.out.println("RegistrarEnvioFallido: " + Dependencia);
        String query = "UPDATE `notificaciones` SET `estado_envio` = '0' WHERE (`id` = '" + json.get("idNotificacion") + "');";
        json.put("registrado", Query.update(query));
        return json;

    }

    @RequestMapping(value = "/API/solicitudVideo/RegistrarEnvioCancelado", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject RegistrarEnvioCancelado(@RequestBody JSONObject json) {
        System.out.println("RegistrarEnvioCancelado: " + Dependencia);
        String query = "UPDATE `notificaciones` SET `estado_envio` = '-1' WHERE (`id` = '" + json.get("idNotificacion") + "');";
        json.put("registrado", Query.update(query));
        return json;

    }

    @RequestMapping(value = "/API/BackupDirectorio/ComplementoInfoIntegrantes", method = RequestMethod.POST)
    @ResponseBody
    public static JSONArray ComplementoInfoIntegrantes(@RequestBody String integrantes) throws ParseException {
        System.out.println("ComplementoInfoIntegrantes: " + Dependencia);

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(integrantes);
        for (int i = 0; i < array.size(); i++) {
            JSONObject integrante = (JSONObject) array.get(i);
            String query = "SELECT idUsuarios_Movil,nombre, apellido_paterno,apellido_materno,telefono,FireBaseKey, so, soversion, version FROM usuarios_movil WHERE idUsuarios_Movil='" + integrante.get("idUsuarios_Movil") + "';";
            JSONObject usuario = Query.select(query);
            if (usuario != null) {
//                String[] labels = (String[]) usuario.keySet().toArray(new String[0]);
//                for (String label : labels) {
//                    ((JSONObject) array.get(i)).put(label, usuario.get(label));
//                }
                integrante.putAll(usuario);
                query = "SELECT Hora AS hora,Fecha AS fecha,Latitud AS lat,Longitud AS lng FROM registro_rutas WHERE Fecha = DATE(NOW()) AND idUsuarios_Movil='" + integrante.get("idUsuarios_Movil") + "';";
                JSONObject gps = Query.select(query);
                if (gps == null) {
                    gps = new JSONObject();
                    gps.put("lat", "");
                    gps.put("lng", "");
                    gps.put("fecha", "");
                    gps.put("hora", "");
                }
                JSONObject ult = new JSONObject();
                ult.put("lat", "");
                ult.put("lng", "");
                gps.put("ult", ult);
                integrante.put("gps", gps);
                integrante.put("img", integrante.get("urlServicio") + "/API/ConsultarImg/perfil/" + integrante.get("idUsuarios_Movil"));
                integrante.put("icon", integrante.get("urlServicio") + "/API/ConsultarImg/icono/" + integrante.get("idUsuarios_Movil"));

            } else {
                System.out.println("Usuario no encontrado");
                System.out.println(integrante);
            }
        }
        return array;
    }

    @RequestMapping(value = "/SOSAltaPrioridad", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String SOSAltaPrioridad(@RequestBody String json) throws IOException, ParseException, JSONException, OpenTokException {
        System.out.println("Llamada SOSAltaPrioridad de la dependencia ---" + Dependencia + "---");
        System.out.println(json);
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);

        String ticket = Post.GenerarTicket(config.getPATH() + Dependencia, jsonObj.get("fecha").toString(), jsonObj.get("hora").toString());
        System.out.println(ticket);
        JSONObject JsonTicket = (JSONObject) parser.parse(ticket);

        if (JsonTicket.get("ticket") != null) {

            //Server s = new Server();
            String idLlamada = JsonTicket.get("ticket").toString();
            System.out.println("Id de la llamada:");
            System.out.println(idLlamada);

            JSONObject modo = UsuarioMovilDAO.ModoLlamada(jsonObj.get("modo").toString());

            DateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
            DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();

            if (jsonObj.containsKey("latitud")) {
                if (jsonObj.get("latitud") == null) {
                    jsonObj.put("latitud", "0");
                }
            } else {
                jsonObj.put("latitud", "0");
            }
            if (jsonObj.containsKey("longitud")) {
                if (jsonObj.get("longitud") == null) {
                    jsonObj.put("longitud", "0");
                }
            } else {
                jsonObj.put("longitud", "0");
            }
            RegistroLlamadasVO reg = new RegistroLlamadasVO(idLlamada, jsonObj.get("fecha").toString(), jsonObj.get("hora").toString(), jsonObj.get("latitud").toString(), jsonObj.get("longitud").toString(), jsonObj.get("modo").toString(), jsonObj.get("idUsuarios_Movil").toString());
            String query_url = config.getURL_CONTROLADOR() + "restOpentok/ArchiveSession";
            JSONObject OpenTok = new JSONObject();
            OpenTok.put("apikey", jsonObj.get("apikey"));
            OpenTok.put("token", jsonObj.get("token"));
            OpenTok.put("sesion", jsonObj.get("idsession"));

            String url = Request.request.POST(query_url, OpenTok.toString());

            System.out.println(url);

            if (RegistroLlamadasDAO.nuevaLlamada(reg, url, OpenTok)) {

                JSONObject Usuario = new JSONObject();
                if (config.isDEPENDENCIA_BASE()) {
                    Usuario = (JSONObject) parser.parse(Post.ConsultarPerfil(jsonObj.get("idUsuarios_Movil").toString(), config.getURL_CONTROLADOR() + "API/ConsultaPerfil"));
                    System.out.println(Usuario.get("apellido_paterno"));
                } else {
                    Usuario = (JSONObject) parser.parse(envioPerfil(jsonObj.get("idUsuarios_Movil").toString()));

                }

                JSONObject Notificacion = new JSONObject();
                jsonObj.put("idLlamada", idLlamada);
                jsonObj.put("ruta_video", url);

                Notificacion.put("RegistroLlamada", jsonObj);
                Notificacion.put("Usuarios_Movil", Usuario);
                Notificacion.put("Credenciales", OpenTok);
                Notificacion.put("origen", Dependencia);
                Notificacion.put("idconnection", jsonObj.get("connectionid"));
                Notificacion.put("horaServidor", hourFormat.format(date));
                Notificacion.put("fechaServidor", dateFormat.format(date));
                Notificacion.put("Modo", modo);
                Notificacion.put("llamadaSOSAltaPrioridad", true);

                SocketEndPoint.NotificarLlamada(Notificacion);

                JSONArray servicios = Modelo.AdministracionServicios.ServiciosCompletos();
                String notificados = "";
                for (int i = 0; i < servicios.size(); i++) {
                    request.POST(servicios.get(i) + "/API/NotificaLlamada", Notificacion.toString());
                    notificados += config.getPATH() + Dependencia + "|" + servicios.get(i);
                    if (i < servicios.size() - 1) {
                        notificados += ",";
                    }
                }
                JSONObject DependenciasNotificadasJson = new JSONObject();
                DependenciasNotificadasJson.put("info", notificados);
                DependenciasNotificadasJson.put("id", idLlamada);
                DependenciasNotificadas(DependenciasNotificadasJson.toString());

                //Server.EnviaNot(Notificacion.toString());
                //TestOpentok.startsession(jsonObj.get("idsession").toString(), "Bingo");
                return "{\"succes\":1}";
            } else {
                System.out.println("Error al registrar la llamada");
                return "{\"error\":0}";
            }

        } else {
            System.out.println("Error al registrar una nueva llamada.");
            return "{\"error\":0}";
        }

    }

    @RequestMapping(value = "/API/NotificaLlamada", method = RequestMethod.POST)
    @ResponseBody
    public static void NotificaLlamada(@RequestBody String json) throws ParseException, IOException {
        System.out.println("NotificarLlamadaAltaPrioridad en : " + Dependencia);
        JSONParser parser = new JSONParser();
        JSONObject Notificacion = (JSONObject) parser.parse(json);
        JSONObject registroLlamada = (JSONObject) Notificacion.get("RegistroLlamada");

        String query = "INSERT INTO registro_llamadas (`idRegistro_Llamadas`,`fecha`,`hora`,`Modo_Llamada_idModo_Llamada`,`Usuarios_Movil_idUsuarios_Movil`,`ruta_video`,`latitud`,`longitud`,`apikey`,`sesion`)  VALUES "
                + "('" + registroLlamada.get("idLlamada") + "','" + registroLlamada.get("fecha") + "','" + registroLlamada.get("hora") + "','" + registroLlamada.get("modo") + "','" + registroLlamada.get("idUsuarios_Movil") + "','" + registroLlamada.get("ruta_video") + "','" + registroLlamada.get("latitud") + "','" + registroLlamada.get("longitud") + "', '" + registroLlamada.get("apikey") + "','" + registroLlamada.get("idsession") + "');";
        System.out.println(query);
        Query.insert(query);
        SocketEndPoint.NotificarLlamada(Notificacion);

    }

    @RequestMapping(value = "/API/incidente/notificar", method = RequestMethod.POST)
    @ResponseBody
    public static void NotificaIncidente(@RequestBody JSONObject json) throws IOException {
        SocketEndPoint.EnviarNotificacion(json);
    }

    @RequestMapping(value = "/API/DirectorioMoviles", method = RequestMethod.POST)
    @ResponseBody
    public static JSONArray DirectorioMoviles(@RequestBody JSONObject json) throws IOException, ParseException {
        JSONArray respuesta = new JSONArray();

        String query = "SELECT id FROM directorio where idUsuario ='" + json.get("idUsuario") + "' AND activo = 1 AND tipo_usuario='" + json.get("tipo_usuario") + "' AND tipo_servicio='" + json.get("tipo_servicio") + "';";
        if (Query.select(query) != null) {
            query = "SELECT D.idUsuario, D.aliasServicio, D.tipo_usuario, D.tipo_servicio,U.nombre,U.apellido_paterno,U.apellido_materno,U.FireBaseKey \n"
                    + "FROM directorio D,usuarios_movil U \n"
                    + "where D.idUsuario=U.idUsuarios_Movil AND D.activo=1 AND D.tipo_usuario=\"" + json.get("tipo_usuario") + "\" AND D.tipo_servicio=\"" + json.get("tipo_servicio") + "\";";

            //JSONObject backup = BackupDirectorio.getBackupDirectorio();
            //JSONArray integrantes = (JSONArray) backup.get("integrantes");
            JSONArray integrantes = Query.execute(query);
            for (int i = 0; i < integrantes.size(); i++) {
                JSONObject integrante = new JSONObject();
                integrante.put("nombre", ((JSONObject) integrantes.get(i)).get("nombre"));
                integrante.put("apellido_paterno", ((JSONObject) integrantes.get(i)).get("apellido_paterno"));
                integrante.put("apellido_materno", ((JSONObject) integrantes.get(i)).get("apellido_materno"));
                integrante.put("img", "https://monitoreo360.ml/plataforma360/API/ConsultarImg/perfil/" + ((JSONObject) integrantes.get(i)).get("idUsuario"));
                integrante.put("icon", "https://monitoreo360.ml/plataforma360/API/ConsultarImg/icono/" + ((JSONObject) integrantes.get(i)).get("idUsuario"));
                //integrante.put("icon", ((JSONObject) integrantes.get(i)).get("icon"));
                integrante.put("FireBaseKey", ((JSONObject) integrantes.get(i)).get("FireBaseKey"));
                integrante.put("aliasServicio", ((JSONObject) integrantes.get(i)).get("aliasServicio"));
                integrante.put("idUsuario", ((JSONObject) integrantes.get(i)).get("idUsuario"));
                respuesta.add(integrante);
            }
        } else {
            JSONObject resp = new JSONObject();
            resp.put("success", false);
            resp.put("failure", true);
            resp.put("razon", "No cuentas con los permisos necesarios para visualizar este directorio.");
            respuesta.add(resp);
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/Registro/LlamadaDispositivosMoviles", method = RequestMethod.POST)
    @ResponseBody
    public static JSONObject LlamadaDispositivosMoviles(@RequestBody JSONObject json) throws IOException {
        String query = "INSERT INTO `registro_llamadas_salientes` (`idLlamada`,`idOperador`, `fecha_notificacion`, `hora_notificacion`, `apikey`, `sesion`) "
                + "VALUES ('" + json.get("idLlamada") + "', '" + json.get("idUsuario") + "', '" + json.get("fecha") + "', '" + json.get("hora") + "', '" + json.get("apikey") + "', '" + json.get("sesion") + "');";
        Query.insert(query);
        JSONObject respuesta = new JSONObject();
        query = "SELECT idLlamada FROM registro_llamadas_salientes WHERE idLlamada = '" + json.get("idLlamada") + "';";
        if (Query.select(query) != null) {
            respuesta.put("success", true);
            respuesta.put("failure", false);
        } else {
            respuesta.put("success", false);
            respuesta.put("failure", true);
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/inicializar", method = RequestMethod.POST)
    @ResponseBody
    public static String inicializar(@RequestBody JSONObject json) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        //config.setInit(json);
        config.setInit((JSONObject) parser.parse(json.toString()));
        if (config.initialize()) {
            Dependencia = config.getDEPENDENCIA();
            //BackupDirectorio();
            
            return "<span style=\"color:limegreen; font-weight: bold;\" >Proyecto inicializado Correctamente</span>";
        } else {
            return "<span style=\"color:red; font-weight: bold;\" >Proyecto no se ha inicializado Correctamente... </span>";
        }

    }

    @RequestMapping(value = "/API/ReporteRegistrados/mapeo", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public static JSONObject ReporteRegistradosMapeo(@RequestBody JSONObject json) throws IOException, ParseException {
        JSONArray orden = new JSONArray();
        JSONObject reporte = new JSONObject();
        JSONParser parser = new JSONParser();
        reporte.put(config.getAliasServicio(), ReporteRegistrados());
        orden.add(config.getAliasServicio());
        JSONArray inferiores = config.getJsonInferiores();
        for (int i = 0; i < inferiores.size(); i++) {
            JSONObject servicio = (JSONObject) inferiores.get(i);
            String arrayReporte = request.POST(servicio.get("url") + "/API/ReporteRegistrados", "");
            reporte.put(servicio.get("alias_proyecto"), arrayReporte == null ? new JSONArray() : (JSONArray) parser.parse(arrayReporte));
            orden.add(servicio.get("alias_proyecto"));
        }
        reporte.put("orden", orden);
        return reporte;
    }

    @RequestMapping(value = "/API/ReporteRegistrados", method = RequestMethod.POST)
    @ResponseBody
    public static JSONArray ReporteRegistrados() {
        String query = "SELECT A.idUsuarios_Movil, U.nombre,U.apellido_paterno,U.apellido_materno,A.Fecha AS Fecha_Registro, A.Hora AS Hora_Registro, X.Fecha AS Fecha_Ultimo_Registro,X.Hora AS Hora_Ultimo_Registro , X.Latitud, X.Longitud from usuarios_movil U INNER JOIN registro_rutas A  on A.idUsuarios_Movil = U.idUsuarios_Movil INNER JOIN registro_rutas X on X.idUsuarios_Movil = U.idUsuarios_Movil where A.Fecha=( SELECT  MIN(B.Fecha) FROM registro_rutas B WHERE A.idUsuarios_Movil=B.idUsuarios_Movil) AND  X.Fecha=( SELECT  MAX(C.Fecha) FROM registro_rutas C WHERE X.idUsuarios_Movil=C.idUsuarios_Movil) ;";
        return Query.execute(query);
    }

    @RequestMapping(value = "/API/reporte/usuariosregistrados", method = RequestMethod.POST)
    public String usuariosregistrados(Model model, @RequestParam("id") String id) throws ParseException, IOException {
        System.out.println("usuariosregistrados: " + Dependencia);
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }

        String query = "SELECT usuario FROM usuario_sys where idUsuario_Sys='" + id + "';";
        JSONObject usr = Query.select(query);
        if (usr != null) {
//            if (usr.get("usuario").toString().equals("supervision-sedena") || usr.get("usuario").toString().equals("supervision-gn") || usr.get("usuario").toString().equals("Global")) {
            model.addAttribute("registros", ReporteRegistradosMapeo(usr).toString().replace("\"", "&quot;"));
            return "pdf/PadronRegistro";
//            }
        }
        //validar correctamente 
        //return "login/Login";
        return "pdf/PadronRegistro";
    }

    @RequestMapping(value = "/API/reporte/usuariosactivos", method = RequestMethod.POST)
    public String usuariosactivos(Model model, @RequestParam("id") String id) throws ParseException, IOException {
        System.out.println("usuariosactivos: " + Dependencia);
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }

        String query = "SELECT usuario FROM usuario_sys where idUsuario_Sys='" + id + "';";
        JSONObject usr = Query.select(query);
        if (usr != null) {
            if (usr.get("usuario").toString().equals("supervision-sedena") || usr.get("usuario").toString().equals("supervision-gn") || usr.get("usuario").toString().equals("Global")) {
                // model.addAttribute("registros", ReporteRegistradosMapeo(usr).toString().replace("\"", "&quot;"));
                return "pdf/usuariosactivos";
            }
        }
        //validar de una mejor manera
        //return "login/Login";
        return "pdf/usuariosactivos";
    }

    @RequestMapping(value = "/API/bingo", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JSONObject bingo(@RequestBody JSONObject json) throws java.text.ParseException, IOException, ParseException {
        return request.POST(config.getURL_CONTROLADOR() + "API/bingo", json);
    }

    @RequestMapping(value = "/API/MapeoReporteIO", method = RequestMethod.POST)
    @ResponseBody
    public static JSONObject MapeoReporteIO(@RequestBody JSONObject json) throws IOException {
        String query = "SELECT * FROM ReporteElemento WHERE id='" + json.get("id") + "' "
                + "AND tipo_usuario = '" + json.get("tipo_usuario") + "' AND tipo_servicio = '" + json.get("tipo_servicio") + "';";
        JSONObject respuesta = Query.select(query);
        if (respuesta != null) {
            JSONObject resp = new JSONObject();
            resp.put("Reporte", respuesta);
            query = "SELECT nombre, apellido_paterno, apellido_materno FROM usuarios_movil WHERE idUsuarios_Movil = '" + respuesta.get("idUsuario_Movil") + "'";
            JSONObject respuesta2 = Query.select(query);
            resp.put("Usuario_Movil", respuesta2);
            query = "SELECT nombre, apellidos FROM usuario_sys WHERE idUsuario_Sys = '" + respuesta.get("idUsuario_Sys") + "';";
            respuesta2 = Query.select(query);
            resp.put("Usuario_Sys", respuesta2);
            respuesta = new JSONObject();
            respuesta.put(Dependencia, resp);
            return respuesta;
        } else {
            JSONObject r = new JSONObject();
            r.put("procede", false);
            return r;
        }

    }

    @RequestMapping(value = "/API/MapeoReportesIOEscalados", method = RequestMethod.POST)
    @ResponseBody
    public static JSONObject MapeoReportesIOEscalados(@RequestBody JSONObject json) throws IOException {
        String query = "SELECT * FROM ReporteElemento WHERE id='" + json.get("id") + "';";
        JSONObject respuesta = Query.select(query);
        JSONObject resp = new JSONObject();
        resp.put("Reporte", respuesta);
        query = "SELECT nombre, apellidos FROM usuario_sys WHERE idUsuario_Sys = '" + respuesta.get("idUsuario_Sys") + "';";
        respuesta = Query.select(query);
        resp.put("Usuario_Sys", respuesta);
        respuesta = new JSONObject();
        respuesta.put(Dependencia, resp);
        return respuesta;
    }

    @RequestMapping(value = "/API/MapeoLlamadaS", method = RequestMethod.POST)
    @ResponseBody
    public static JSONObject MapeoLlamadaS(@RequestBody JSONObject json) throws IOException {

        JSONObject resp = new JSONObject();
        String query = "";
        if (json.containsKey("tipo_usuario") && json.containsKey("tipo_servicio")) {
            query = "SELECT * FROM registro_llamadas_salientes WHERE idLlamada='" + json.get("id") + "'"
                    + "AND tipo_usuario = '" + json.get("tipo_usuario") + "' AND tipo_servicio = '" + json.get("tipo_servicio") + "';";
        } else {
            query = "SELECT * FROM registro_llamadas_salientes WHERE idLlamada='" + json.get("id") + "';";
        }

        JSONObject respuesta = Query.select(query);
        if (respuesta != null) {
            resp.put("LlamadaSaliente", respuesta);
            query = "SELECT * FROM modo_llamada WHERE idModo_Llamada = '" + respuesta.get("modo_llamada") + "';";
            JSONObject modo = Query.select(query);
            resp.put("Modo_Llamada", modo);
            if (json.containsKey("tipo_usuario") && json.containsKey("tipo_servicio")) {
                query = "SELECT temergencia,folioexterno,reporte,fecha,hora FROM reporte_llamadas "
                        + "WHERE idLlamada = '" + json.get("id") + "' "
                        + "AND tipo_usuario = '" + json.get("tipo_usuario") + "' AND tipo_servicio = '" + json.get("tipo_servicio") + "' ORDER BY idReporte desc limit 1;";
            } else {
                query = "SELECT temergencia,folioexterno,reporte,fecha,hora FROM reporte_llamadas WHERE idLlamada = '" + json.get("id") + "' ORDER BY idReporte desc limit 1;";
            }
            JSONArray reporte = Query.execute(query);
            resp.put("Reportes", reporte);
            query = "SELECT nombre, apellidos FROM usuario_sys WHERE idUsuario_Sys = '" + respuesta.get("idOperador") + "';";
            respuesta = Query.select(query);
            resp.put("Usuario_Sys", respuesta);
            query = "SELECT * FROM notificaciones WHERE idLlamada = '" + json.get("id") + "';";
            JSONArray Notificaciones = Query.execute(query);
            resp.put("Notificados", Notificaciones);
            respuesta = new JSONObject();
            respuesta.put(Dependencia, resp);
            return respuesta;
        } else {
            JSONObject res = new JSONObject();
            res.put("procede", false);
            return res;
        }

    }

    @RequestMapping(value = "/ActualizaDependencias", method = RequestMethod.POST)
    @ResponseBody
    public static void ActualizaDependencias(@RequestBody JSONObject jsonObj) {
        String query = "SELECT dependencias FROM ReporteElemento;";
        JSONObject dependencias = Query.select(query);
        String[] Infodep = jsonObj.get("info").toString().split(",");
        if (dependencias.get("dependencias") != null) {
            String[] dep = dependencias.get("dependencias").toString().split(",");
            String DepFinales = "";
            for (int i = 0; i < Infodep.length; i++) {
                boolean result = Arrays.stream(dep).anyMatch(Infodep[i]::equals);
                if (!result) {
                    DepFinales += Infodep[i];
                }
            }
            jsonObj.put("info", DepFinales);
            if (!"".equals(DepFinales)) {
                query = "UPDATE `ReporteElemento` SET `dependencias` =CONCAT(if(dependencias is null ,'',CONCAT(dependencias,',')),'" + jsonObj.get("info") + "') WHERE `id`='" + jsonObj.get("id") + "';";
                Query.update(query);
            }

        } else {
            query = "UPDATE `ReporteElemento` SET `dependencias` =CONCAT(if(dependencias is null ,'',CONCAT(dependencias,',')),'" + jsonObj.get("info") + "') WHERE `id`='" + jsonObj.get("id") + "';";
            Query.update(query);
        }
    }

    @RequestMapping(value = "/Consulta", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String Consulta(@RequestBody String json) throws ParseException {
        System.out.println("Consulta: " + Dependencia);
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        String usuario = envioPerfil(jsonObj.get("idUsuarios_Movil").toString());
        return usuario;
    }

    @RequestMapping(value = "/{tipo_usuario}/{tipo_servicio}/Consulta", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String Consulta(@RequestBody String json, @PathVariable("tipo_usuario") String tipo_usuario, @PathVariable("tipo_servicio") String tipo_servicio) throws ParseException {
        System.out.println("Consulta: " + Dependencia);
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        String usuario = envioPerfil(jsonObj.get("idUsuarios_Movil").toString());
        return usuario;
    }

    @RequestMapping(value = "/ConsultaIntegrante", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JSONObject ConsultaIntegrante(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("ConsultaIntegrante: " + Dependencia);
        String usuario = json.get("idUsuario").toString();
        JSONParser parser = new JSONParser();
        JSONObject respuesta;
        String integrante = request.POST(config.getURL_CONTROLADOR() + "API/ConsultaIntegrante", usuario);
        if (integrante != null) {
            respuesta = (JSONObject) parser.parse(integrante);
            respuesta.put("aliasServicio", config.getAliasServicio());
            respuesta.put("urlServicio", config.getPATH() + config.getDEPENDENCIA());
        } else {
            respuesta = new JSONObject();
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/Registro/EstatusUsuario", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public static JSONObject EstatusUsuario(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("EstatusUsuario en : " + Dependencia);
        System.out.println(json);
        String query = "SELECT id FROM directorio where idUsuario ='" + json.get("idUsuario") + "' AND activo = 1;";
        JSONObject respuesta = new JSONObject();
        if (Query.execute(query).isEmpty()) {
            //No esta en el directorio por lo tanto no esta activo 
            respuesta.put("activo", false);
            respuesta.put("estatus", 0);

        } else {
            //Usuario activo 
            respuesta.put("activo", true);
            respuesta.put("estatus", 1);
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/TraeInfoIntegrante", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JSONObject TraeInfoIntegrante(@RequestBody JSONObject json) throws ParseException, IOException {
        String query = "SELECT urlServicio FROM directorio WHERE idUsuario = '" + json.get("idUsuario") + "' AND activo = 1;";
        JSONObject url = Query.select(query);
        JSONObject respuesta = new JSONObject();
        if (url != null) {
            if (url.get("urlServicio").toString().equals(config.getPATH() + config.getDEPENDENCIA())) {
                respuesta.put("procede", true);
                respuesta.put("mensaje", "Usuario encontrado");
                respuesta.put("integrante", Integrante(json));
                return respuesta;
            } else {
                respuesta.put("procede", true);
                respuesta.put("mensaje", "Usuario encontrado");
                respuesta.put("integrante", request.POST(url.get("urlServicio") + "/API/Integrante".toString(), json));
                return respuesta;
            }
        }

        respuesta.put("procede", false);
        respuesta.put("mensaje", "No cuentas con los permisos necesarios para eliminar a este usuario.");
        return null;
    }

    @RequestMapping(value = "/API/Integrante", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JSONObject Integrante(@RequestBody JSONObject json) throws ParseException, IOException {
        JSONObject integrante;
        String query = "select idUsuarios_Movil,nombre, apellido_paterno,\n"
                + "apellido_materno, correo, telefono, contacto_nombre, \n"
                + "contacto_telefono, alergias, rh, fecha_nacimiento, genero, \n"
                + "direccion,cp,condicion_medica FROM usuarios_movil WHERE idUsuarios_Movil = '" + json.get("idUsuario") + "';";
        integrante = Query.select(query);
        integrante.put("img", config.getPATH() + Dependencia + "/API/ConsultarImg/perfil/" + json.get("idUsuario"));
        integrante.put("icon", config.getPATH() + Dependencia + "/API/ConsultarImg/icono/" + json.get("idUsuario"));
        query = "SELECT Fecha,Hora FROM registro_rutas WHERE idUsuarios_Movil = '" + json.get("idUsuario") + "' ORDER BY idregistro_rutas ASC LIMIT 1;";
        integrante.put("registro", Query.select(query));
        query = "SELECT Fecha,Hora FROM registro_rutas WHERE idUsuarios_Movil = '" + json.get("idUsuario") + "' ORDER BY idregistro_rutas DESC LIMIT 1;";
        integrante.put("ultConexion", Query.select(query));
        return integrante;
    }

    @RequestMapping(value = "/API/PaquetesBackup", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public static JSONObject PaquetesBackup(@RequestBody String json) throws java.text.ParseException, ParseException, IOException {
        System.out.println("PaquetesBackup en: " + Dependencia);
        Modelo.Escalamiento.RecibirData(json);
        JSONObject res = new JSONObject();
        res.put("ok", 1);
        res.put("dependencia", Dependencia);
        return res;
    }

    @RequestMapping(value = "/API/covid/test/{tipo_usuario}/{tipo_servicio}", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject covidTest(@RequestBody JSONObject json, @PathVariable("tipo_usuario") String tipo_usuario, @PathVariable("tipo_servicio") String tipo_servicio) throws ParseException, IOException {
        System.out.println("COVID Test");
        System.out.println(json);
        int rango = 0;
        String tittle = "¡Todo está bien! ";
        String message = "Sigue y mantente al tanto de las recomendaciones de las autoridades de salud, si presentas algún cambio o síntoma realiza este test nuevamente, la prevención es importante para evitar contagios. ";
        /////////////////EDAD
        String query = "SELECT "
                + "nombre,"
                + "apellido_paterno,"
                + "apellido_materno,"
                + "correo,telefono,"
                + "contacto_nombre,"
                + "contacto_telefono,"
                + "alergias,"
                + " rh,"
                + "fecha_nacimiento,"
                + "genero,"
                + "direccion,"
                + "cp,"
                + "condicion_medica, "
                + "FireBaseKey"
                + "  FROM usuarios_movil where idUsuarios_Movil=\"" + json.get("idUsuario") + "\"; ";
        JSONObject perfil = new JSONObject();

        if (config.isDEPENDENCIA_BASE()
                || tipo_usuario.equals("10")
                || tipo_usuario.equals("11")) {
            JSONParser parser = new JSONParser();
            perfil = (JSONObject) parser.parse(Post.ConsultarPerfil(json.get("idUsuario").toString(), config.getURL_CONTROLADOR() + "API/ConsultaPerfil"));
        } else {
            perfil = Query.select(query);
        }
        System.out.println(perfil);

        if (perfil != null) {

            perfil.put("img", config.getPATH() + Dependencia + "/API/ConsultarImg/perfil/" + json.get("idUsuario"));
            perfil.put("icon", config.getPATH() + Dependencia + "/API/ConsultarImg/icono/" + json.get("idUsuario"));
            if (config.isDEPENDENCIA_BASE()
                    || tipo_usuario.equals("10")
                    || tipo_usuario.equals("11")) {
                perfil.put("img", "https://plataforma911.ml/CONTROLADOR/API/ConsultarImg/perfil/" + json.get("idUsuario"));
                perfil.put("icon", "https://plataforma911.ml/CONTROLADOR/API/ConsultarImg/icono/" + json.get("idUsuario"));
            }
            //calcular edad 
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("M/dd/yyyy");
            LocalDate fechaNac = LocalDate.parse(perfil.get("fecha_nacimiento").toString(), fmt);
            LocalDate ahora = LocalDate.now();

            Period periodo = Period.between(fechaNac, ahora);
            System.out.printf("La edad es de: %s años, %s meses y %s días",
                    periodo.getYears(), periodo.getMonths(), periodo.getDays());
            int edad = periodo.getYears();
            perfil.put("edad", edad);
            if (edad < 13) {
                //No se suma nada al rango
            } else if (edad < 60) {
                rango += 1;//Riesgo medio
            } else if (edad > 59) {
                rango += 2;//riesgo alto
            }
        } else {
            System.out.println("Usuario no registrado");
        }

        /////////////////TEMPERATURA
        float temperatura = Float.parseFloat(json.get("p1").toString());
        if (temperatura < 37) {
            //Normal
        } else if (temperatura < 38) {
            rango += 1;//Riesgo medio
        } else if (temperatura > 37.9) {
            rango += 2;//riesgo alto
        }
        /////////////////TOS
        if (json.get("p2").toString().equals("1")) {
            rango += 1;//Riesgo medio
        }
        /////////////////FLEMAS
        if (json.get("p3").toString().equals("1")) {
            rango += 1;//Riesgo medio
        }
        /////////////////GARGANTA
        if (json.get("p4").toString().equals("1")) {
            rango += 1;//Riesgo medio
        }
        /////////////////RESPIRACION
        if (json.get("p5").toString().equals("1")) {
            rango += 3;//Riesgo medio
        }
        /////////////////VIAJE_LOCAL
        if (json.get("p6").toString().equals("1")) {
            rango += 0;//Riesgo medio

        }
        /////////////////VIAJE_EXTANGERO
        if (json.get("p7").toString().equals("1")) {
            rango += 1;//Riesgo medio
            JSONObject p = paises.getJson();
            JSONArray alto = (JSONArray) p.get("alto");
            JSONArray medio = (JSONArray) p.get("medio");

            for (int i = 0; i < alto.size(); i++) {
                if (alto.get(i).toString().equalsIgnoreCase(json.get("ep7").toString())) {
                    rango += 2;//Riesgo alto
                }
            }
            for (int i = 0; i < medio.size(); i++) {
                if (medio.get(i).toString().equalsIgnoreCase(json.get("ep7").toString())) {
                    rango += 1;//Riesgo medio
                }
            }

        }
        String nivel = null;
        boolean notificar = false;
        if (rango > 3) {
            // Notificar incidentes 
            nivel = "bajo";
            notificar = true;
            tittle = "¡Tu salud es muy importante!";
            message = "Considera visitar a un especialista de la salud para que realice una revisión detallada de tus síntomas, es muy importante no pasar por alto detalles e indicadores, tu bienestar y el de las personas cercanas a ti son muy importantes. ";

        }
        if (rango > 5) {
            // Notificar incidentes 
            nivel = "medio";
            tittle = "¡Acércate a un especialista de la salud!";
            message = "Tus respuestas indican algunos factores de riesgo, es recomendable acudas cuanto antes a un especialista o a tu unidad de salud más cercana, recibir atención cuanto antes hace la diferencia. ";

        }
        if (rango > 9) {
            // Notificar incidentes 
            nivel = "alto";
            tittle = "¡Acude inmediatamente a tu especialista de la salud!";
            message = "O a la unidad médica más cercana, tus síntomas indican la necesidad de una revisión personalizada, el tiempo es un factor importante para recibir el diagnóstico más acertado a tus necesidades, todos los casos son distintos y solo un médico puede darte un diagnóstico. ";

        }

        if (notificar) {
            json.put("perfil", perfil);
            json.put("nivel", nivel);
            json.put("rango", rango);
            json.put("origen", "App");
            json.put("proyecto", "sedena");
            json.put("tipo_servicio", tipo_servicio);
            json.put("tipo_usuario", tipo_usuario);
            System.out.println("Enviando a incidentes Covid");
            System.out.println(json);
            //                  JSONObject serie = request.POST("http://info-covid19.ml/api/registers", json);
            JSONObject serie = request.POST("http://incidente.ml/api/registers", json);
            json.put("serie", serie.get("serie"));
            //json.put("serie", 12345);
            json.put("covid", true);

            //*****
            if (tipo_usuario.equals("10") || tipo_usuario.equals("11")) {
                json.put("p360", true);
            }
            //*****

            //Notificar por socket incidente 
            SocketEndPoint.NotificarCOVID(json);

//                  if (request.pushNot(firebase, tittle, message) == null) {
//                        //segundo intento
//                        request.pushNot(firebase, tittle, message);
//                  }
            //request.pushNot(perfil.get("FireBaseKey").toString(), "Se genero un folio para su reporte: "+serie.get("serie"), "Amigo ponte en cuarentena tu nivel indicador de contagio es: "+nivel);
        }

        //Notificar mediante push Notifications se le dara seguimiento
        String firebase = perfil.get("FireBaseKey").toString();

        JSONObject jsonNotificacion = new JSONObject();
        jsonNotificacion.put("firebase", firebase);
        jsonNotificacion.put("tittle", tittle);
        jsonNotificacion.put("message", message);
        if (tipo_usuario.equals("10") || tipo_usuario.equals("11")) {
            request.POST(config.getURL_CONTROLADOR() + "API/NotificacionCovid360", jsonNotificacion);
        } else {
            request.POST(config.getURL_CONTROLADOR() + "API/NotificacionCovid", jsonNotificacion);
        }

        //realizar el registro...
        query = "INSERT INTO `testCovid` "
                + "(`serie`, `idUsuario`, `temperatura`, `tos`, `flemas`, `garganta`, `respiracion`, `viaje_local`, `donde_local`, `viaje_extranjero`, `donde_extranjero`, `fecha`, `hora`) "
                + "VALUES"
                + " ('" + json.get("serie") + "', '" + json.get("idUsuario") + "', '" + json.get("p1") + "', '" + json.get("p2") + "', '" + json.get("p3") + "', '" + json.get("p4") + "', '" + json.get("p5") + "', '" + json.get("p6") + "', '" + json.get("ep6") + "', '" + json.get("p7") + "', '" + json.get("ep7") + "', '" + json.get("fecha") + "', '" + json.get("hora") + "');";
        Query.insert(query);

        JSONObject res = new JSONObject();
        res.put("success", 1);
        return res;
    }

    @RequestMapping(value = "/API/sendNotificacion", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public static JSONObject sendNotificacion(@RequestBody JSONObject json) throws java.text.ParseException, ParseException, IOException {
        System.out.println("sendNotificacion en: " + Dependencia);
        JSONObject res = new JSONObject();

        if (Boolean.parseBoolean(json.get("p360").toString())) {
            return request.POST(config.getURL_CONTROLADOR() + "API/NotificacionCovid360", json);
        } else {
            request.POST(config.getURL_CONTROLADOR() + "API/NotificacionCovid", json);
            res.put("success", 1);
        }
        return res;
    }

    @RequestMapping(value = "/API/covid/testWeb", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject covidTestWeb(@RequestBody JSONObject json) throws ParseException, IOException {

        System.out.println("COVID Test");
        System.out.println(json);
        int rango = 0;
        String tittle = "¡Todo está bien! ";
        String message = "Sigue y mantente al tanto de las recomendaciones de las autoridades de salud, si presentas algún cambio o síntoma realiza este test nuevamente, la prevención es importante para evitar contagios. ";
        /////////////////EDAD
        String query = "SELECT "
                + "nombre,"
                + "apellido_paterno,"
                + "apellido_materno,"
                + "correo,telefono,"
                + "contacto_nombre,"
                + "contacto_telefono,"
                + "alergias,"
                + " rh,"
                + "fecha_nacimiento,"
                + "genero,"
                + "direccion,"
                + "cp,"
                + "condicion_medica, "
                + "FireBaseKey"
                + "  FROM usuarios_movil where idUsuarios_Movil=\"" + json.get("idUsuario") + "\"; ";
        JSONObject perfil = Query.select(query);
        if (perfil != null) {
            perfil.put("img", config.getPATH() + Dependencia + "/API/ConsultarImg/perfil/" + json.get("idUsuario"));
            perfil.put("icon", config.getPATH() + Dependencia + "/API/ConsultarImg/icono/" + json.get("idUsuario"));
            //calcular edad 
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaNac = LocalDate.parse(perfil.get("fecha_nacimiento").toString(), fmt);
            LocalDate ahora = LocalDate.now();

            Period periodo = Period.between(fechaNac, ahora);
            System.out.printf("La edad es de: %s años, %s meses y %s días",
                    periodo.getYears(), periodo.getMonths(), periodo.getDays());
            int edad = periodo.getYears();
            perfil.put("edad", edad);
            if (edad < 13) {
                //No se suma nada al rango
            } else if (edad < 60) {
                rango += 1;//Riesgo medio
            } else if (edad > 59) {
                rango += 2;//riesgo alto
            }
        } else {
            System.out.println("Usuario no registrado");
        }

        /////////////////TEMPERATURA
        float temperatura = Float.parseFloat(json.get("p1").toString());
        if (temperatura < 37) {
            //Normal
        } else if (temperatura < 38) {
            rango += 1;//Riesgo medio
        } else if (temperatura > 37.9) {
            rango += 2;//riesgo alto
        }
        /////////////////TOS
        if (json.get("p2").toString().equals("1")) {
            rango += 1;//Riesgo medio
        }
        /////////////////FLEMAS
        if (json.get("p3").toString().equals("1")) {
            rango += 1;//Riesgo medio
        }
        /////////////////GARGANTA
        if (json.get("p4").toString().equals("1")) {
            rango += 1;//Riesgo medio
        }
        /////////////////RESPIRACION
        if (json.get("p5").toString().equals("1")) {
            rango += 3;//Riesgo medio
        }
        /////////////////VIAJE_LOCAL
        if (json.get("p6").toString().equals("1")) {
            rango += 0;//Riesgo medio

        }
        /////////////////VIAJE_EXTANGERO
        if (json.get("p7").toString().equals("1")) {
            rango += 1;//Riesgo medio
            JSONObject p = paises.getJson();
            JSONArray alto = (JSONArray) p.get("alto");
            JSONArray medio = (JSONArray) p.get("medio");

            for (int i = 0; i < alto.size(); i++) {
                if (alto.get(i).toString().equalsIgnoreCase(json.get("ep7").toString())) {
                    rango += 2;//Riesgo alto
                }
            }
            for (int i = 0; i < medio.size(); i++) {
                if (medio.get(i).toString().equalsIgnoreCase(json.get("ep7").toString())) {
                    rango += 1;//Riesgo medio
                }
            }

        }
        String nivel = null;
        boolean notificar = false;
        if (rango > 3) {
            // Notificar incidentes 
            nivel = "bajo";
            notificar = true;
            tittle = "¡Tu salud es muy importante!";
            message = "Considera visitar a un especialista de la salud para que realice una revisión detallada de tus síntomas, es muy importante no pasar por alto detalles e indicadores, tu bienestar y el de las personas cercanas a ti son muy importantes. ";

        }
        if (rango > 5) {
            // Notificar incidentes 
            nivel = "medio";
            tittle = "¡Acércate a un especialista de la salud!";
            message = "Tus respuestas indican algunos factores de riesgo, es recomendable acudas cuanto antes a un especialista o a tu unidad de salud más cercana, recibir atención cuanto antes hace la diferencia. ";

        }
        if (rango > 9) {
            // Notificar incidentes 
            nivel = "alto";
            tittle = "¡Acude inmediatamente a tu especialista de la salud!";
            message = "O a la unidad médica más cercana, tus síntomas indican la necesidad de una revisión personalizada, el tiempo es un factor importante para recibir el diagnóstico más acertado a tus necesidades, todos los casos son distintos y solo un médico puede darte un diagnóstico. ";

        }

        if (notificar) {
            json.put("perfil", perfil);
            json.put("nivel", nivel);
            json.put("rango", rango);
            json.put("origen", "Plataforma Web");
            json.put("proyecto", "sedena");
            System.out.println("Enviando a incidentes Covid");
            System.out.println(json);
//                  JSONObject serie = request.POST("http://info-covid19.ml/api/registers", json);
            JSONObject serie = request.POST("http://incidente.ml/api/registers", json);
            json.put("serie", serie.get("serie"));
            //json.put("serie", 12345);
            json.put("covid", true);
            //Notificar por socket incidente 
            //SocketEndPoint.NotificarCOVID(json);

//                  if (request.pushNot(firebase, tittle, message) == null) {
//                        //segundo intento
//                        request.pushNot(firebase, tittle, message);
//                  }
            //request.pushNot(perfil.get("FireBaseKey").toString(), "Se genero un folio para su reporte: "+serie.get("serie"), "Amigo ponte en cuarentena tu nivel indicador de contagio es: "+nivel);
        }

        //Notificar mediante push Notifications se le dara seguimiento
        String firebase = perfil.get("FireBaseKey").toString();

        JSONObject jsonNotificacion = new JSONObject();
        jsonNotificacion.put("firebase", firebase);
        jsonNotificacion.put("tittle", tittle);
        jsonNotificacion.put("message", message);
        request.POST(config.getURL_CONTROLADOR() + "API/NotificacionCovid", jsonNotificacion);

        //realizar el registro...
        query = "INSERT INTO `testCovid` "
                + "(`serie`, `idUsuario`, `temperatura`, `tos`, `flemas`, `garganta`, `respiracion`, `viaje_local`, `donde_local`, `viaje_extranjero`, `donde_extranjero`, `fecha`, `hora`, `reporte`, `fecha_reporte`, `hora_reporte`) "
                + "VALUES"
                + " ('" + json.get("serie") + "', '" + json.get("idUsuario") + "', '" + json.get("p1") + "', '" + json.get("p2") + "', '" + json.get("p3") + "', '" + json.get("p4") + "', '" + json.get("p5") + "', '" + json.get("p6") + "', '" + json.get("ep6") + "', '" + json.get("p7") + "', '" + json.get("ep7") + "', '" + json.get("fecha") + "', '" + json.get("hora") + "', '" + json.get("conclusion") + "', '" + json.get("fecha") + "', '" + json.get("hora") + "');";
        Query.insert(query);

        JSONObject res = new JSONObject();
        res.put("success", 1);
        return res;
    }

    @RequestMapping(value = "/API/traeServicios", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public static JSONObject traeServicios(@RequestBody JSONObject json) throws java.text.ParseException, ParseException, IOException {
        System.out.println("traeServicios en: " + Dependencia);//??xD
        JSONObject resp = new JSONObject();
        String query = "SELECT * FROM tipos_usuarios WHERE activo = 1;";
        resp.put("tipos_usuarios", Query.execute(query));
        query = "SELECT * FROM servicios_usuario WHERE activo = 1;";
        resp.put("servicios_usuarios", Query.execute(query));
        return resp;
    }

    @RequestMapping(value = "/API/ServiciosPorUsuario", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public static JSONObject ServiciosPorUsuario() throws java.text.ParseException, ParseException, IOException {
        System.out.println("traeServicios en: " + Dependencia);
        JSONObject resp = new JSONObject();
        String query = "SELECT * FROM tipos_usuarios WHERE activo = 1 AND RegistroApp = 1;";
        JSONArray tiposUsuarios = Query.execute(query);
        query = "SELECT * FROM servicios_usuario WHERE activo = 1 AND visibilidad_app=1;";
        JSONArray serviciosUsuario = Query.execute(query);

        for (int i = 0; i < tiposUsuarios.size(); i++) {
            JSONArray array1 = new JSONArray();
            JSONObject jsonObj = new JSONObject();
            for (int j = 0; j < serviciosUsuario.size(); j++) {
                if (((JSONObject) serviciosUsuario.get(j)).get("idTipoUsuario").toString().equals(((JSONObject) tiposUsuarios.get(i)).get("id").toString())) {
                    array1.add(serviciosUsuario.get(j));
                }
            }
            jsonObj.put("idTipoUsuario", ((JSONObject) tiposUsuarios.get(i)).get("id"));
            jsonObj.put("instituciones", array1);

            resp.put(((JSONObject) tiposUsuarios.get(i)).get("tipo_usuario"), jsonObj);
        }
        return resp;
    }

    @RequestMapping(value = "/Registro/{tipo_usuario}/{tipo_servicio}", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String RegistroTipoUsuario(@RequestBody JSONObject json, @PathVariable("tipo_usuario") String tipo_usuario, @PathVariable("tipo_servicio") String tipo_servicio) throws ParseException, IOException, java.text.ParseException {
        System.out.println("Registro: " + Dependencia);

        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return "{}";
        }
        String id = json.get("idUsuarios_Movil").toString();

        //////Modulo para deshabilitar usuario en la plataforma si es que ya estaba registrado en otra rama de la jerarquia
        if (config.getUrlTop() != null) {

            JSONObject urlTopResponse = new JSONObject();
            urlTopResponse.put("idUsuario", id);
            urlTopResponse.put("response", config.getUrlTop());
            System.out.println(config.getUrlTop() + "/API/DeshabilitarUsr_Directorio");
            request.POST(config.getUrlTop() + "/API/DeshabilitarUsr_Directorio", urlTopResponse.toString());
        }

        boolean puede_registrarse = true;

        if (config.isLISTA_BLANCA()) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("id", id);
            jsonObj.put("correo", json.get("correo").toString());
            puede_registrarse = Boolean.parseBoolean(request.POST(config.getURL_CONTROLADOR() + "API/Validar_lista_blanca", jsonObj.toString()));
        }

        json.put("tipo_usuario", tipo_usuario);
        json.put("tipo_servicio", tipo_servicio);
        String query = "select TU.tipo_usuario, SU.nombre  from tipos_usuarios TU, servicios_usuario SU where TU.id = " + tipo_usuario + " AND SU.id=" + tipo_servicio + " AND TU.id=SU.idTipoUsuario;";
        JSONObject servicio = Query.select(query);
        if (puede_registrarse) {
            query = "SELECT idUsuarios_Movil FROM usuarios_movil WHERE idUsuarios_Movil='" + id + "';";
            if (Query.select(query) != null) {
                //El usuario ya estaba registrado por lo que solo actualizamos sus datos
                query = "UPDATE usuarios_movil SET "
                        + "nombre = '" + json.get("nombre") + "', "
                        + "apellido_paterno = '" + json.get("apellido_paterno") + "',"
                        + "apellido_materno = '" + json.get("apellido_materno") + "', "
                        + "fecha_nacimiento='" + json.get("fecha_nacimiento") + "',"
                        + "correo='" + json.get("correo") + "', "
                        + "telefono='" + json.get("telefono") + "', "
                        + "genero='" + json.get("genero") + "',"
                        + "rh='" + json.get("rh") + "', "
                        + "alergias = '" + json.get("alergias") + "', "
                        + "condicion_medica= '" + json.get("condicion_medica") + "',"
                        + "direccion='" + json.get("direccion") + "', "
                        + "cp='" + json.get("cp") + "', "
                        + "contacto_nombre='" + json.get("contacto_nombre") + "',"
                        + "contacto_telefono = '" + json.get("contacto_telefono") + "', "
                        + "tipo_usuario = '" + json.get("tipo_usuario") + "',"
                        + "tipo_servicio = '" + json.get("tipo_servicio") + "' "
                        + "WHERE idUsuarios_Movil = '" + json.get("idUsuarios_Movil") + "';";
//                boolean actualizado = UsuarioMovilDAO.ActualizarDatos(usr);

                if (Query.update(query)) {
                    System.out.println("Se actualizaron los datos del usuario: " + json.get("idUsuarios_Movil") + " : " + json.get("nombre"));
                    if (!json.containsKey("Img") && json.containsKey("img")) {
                        json.put("Img", json.get("img"));
                    }
                    query = "UPDATE usuarios_movil SET "
                            + "img='" + json.get("Img") + "', "
                            + "icon='" + json.get("icon") + "' "
                            + "WHERE idUsuarios_Movil = '" + json.get("idUsuarios_Movil") + "';";
                    if (Query.update(query)) {
                        System.out.println("Se actualizaron la imagen e icono de: " + json.get("idUsuarios_Movil") + " - " + json.get("nombre"));

                        JSONObject jsonDirectorio = new JSONObject();
                        jsonDirectorio.put("idUsuario", json.get("idUsuarios_Movil"));
                        jsonDirectorio.put("nombre", json.get("nombre") + " " + json.get("apellido_paterno"));
                        jsonDirectorio.put("urlServicio", config.getPATH() + Dependencia);
                        jsonDirectorio.put("servicio", servicio.get("tipo_usuario") + " " + servicio.get("nombre"));
                        jsonDirectorio.put("tipo_usuario", tipo_usuario);
                        jsonDirectorio.put("tipo_servicio", tipo_servicio);

                        JSONObject activacion_directorio = Agregar_Directorio2(jsonDirectorio);

                        JSONObject registro_json = new JSONObject();
                        JSONObject gps = new JSONObject();
                        JSONObject ult = new JSONObject();
                        registro_json.put("idUsuarios_Movil", json.get("idUsuarios_Movil"));
                        registro_json.put("telefono", json.get("telefono"));
                        registro_json.put("nombre", json.get("nombre"));
                        registro_json.put("apellido_paterno", json.get("apellido_paterno"));
                        registro_json.put("apellido_materno", json.get("apellido_materno"));
                        registro_json.put("img", config.getPATH() + Dependencia + "/API/ConsultarImg/perfil/" + json.get("idUsuarios_Movil"));
                        registro_json.put("icon", config.getPATH() + Dependencia + "/API/ConsultarImg/icono/" + json.get("idUsuarios_Movil"));
                        registro_json.put("aliasServicio", config.getAliasServicio() + " " + jsonDirectorio.get("servicio"));
                        registro_json.put("urlServicio", config.getPATH() + Dependencia);
                        registro_json.put("FireBaseKey", "");
                        registro_json.put("tipo_usuario", tipo_usuario);
                        registro_json.put("tipo_servicio", tipo_servicio);
                        ult.put("lat", "");
                        ult.put("lng", "");
                        gps.put("ult", ult);
                        gps.put("lat", "");
                        gps.put("lng", "");
                        gps.put("hora", "");
                        gps.put("fecha", "");
                        registro_json.put("gps", gps);

                        registro_json.put("Registro", true);
                        UpdateBackupDirectorio(registro_json);
                        System.out.println("Registro exitoso encolando solicitud de escalamiento...");
                        Modelo.Escalamiento.AgregarData(registro_json);
//                                    JSONArray arrayServicios = config.getSuperiores();
//                                    System.out.println(arrayServicios);
//                                    for (int i = 0; i < arrayServicios.size(); i++) {
//                                          request.POST(arrayServicios.get(i).toString() + "/API/UpdateBackupDirectorio", registro_json.toString());
//                                    }

                        return "{\"respuesta\":\"2\"}";
                    } else {
                        System.out.println("Error al actualizar la foto del usuario.");
                    }
                } else {
                    System.out.println("Error al actualizar los datos del usuario.");
                }

            } else {

                boolean agregado = UsuarioMovilDAO.agregarUsuario(json);
                if (agregado) {
                    JSONObject jsonDirectorio = new JSONObject();
                    jsonDirectorio.put("idUsuario", json.get("idUsuarios_Movil"));
                    jsonDirectorio.put("nombre", json.get("nombre") + " " + json.get("apellido_paterno"));
                    jsonDirectorio.put("urlServicio", config.getPATH() + Dependencia);
                    jsonDirectorio.put("servicio", servicio.get("tipo_usuario") + " " + servicio.get("nombre"));
                    jsonDirectorio.put("tipo_usuario", tipo_usuario);
                    jsonDirectorio.put("tipo_servicio", tipo_servicio);

                    Agregar_Directorio2(jsonDirectorio);

                    JSONObject registro_json = new JSONObject();
                    JSONObject gps = new JSONObject();
                    JSONObject ult = new JSONObject();
                    registro_json.put("idUsuarios_Movil", json.get("idUsuarios_Movil"));
                    registro_json.put("telefono", json.get("telefono"));
                    registro_json.put("nombre", json.get("nombre"));
                    registro_json.put("apellido_paterno", json.get("apellido_paterno"));
                    registro_json.put("apellido_materno", json.get("apellido_materno"));
                    registro_json.put("img", config.getPATH() + Dependencia + "/API/ConsultarImg/perfil/" + json.get("idUsuarios_Movil"));
                    registro_json.put("icon", config.getPATH() + Dependencia + "/API/ConsultarImg/icono/" + json.get("idUsuarios_Movil"));
                    registro_json.put("aliasServicio", config.getAliasServicio() + " " + jsonDirectorio.get("servicio"));
                    registro_json.put("urlServicio", config.getPATH() + Dependencia);
                    registro_json.put("FireBaseKey", "");
                    registro_json.put("tipo_usuario", tipo_usuario);
                    registro_json.put("tipo_servicio", tipo_servicio);

                    ult.put("lat", "");
                    ult.put("lng", "");
                    gps.put("ult", ult);
                    gps.put("lat", "");
                    gps.put("lng", "");
                    gps.put("hora", "");
                    gps.put("fecha", "");
                    registro_json.put("gps", gps);

                    registro_json.put("Registro", true);
                    UpdateBackupDirectorio(registro_json);
                    Modelo.Escalamiento.AgregarData(registro_json);
//                              JSONArray arrayServicios = config.getSuperiores();
//                              for (int i = 0; i < arrayServicios.size(); i++) {
//                                    request.POST(arrayServicios.get(i).toString() + "/API/UpdateBackupDirectorio", registro_json.toString());
//                              }
                    System.out.println("Se añadio a la base de datos.");
                    return "{\"respuesta\":\"1\"}";
                } else {
                    System.out.println("Error al registrar el usuario.");
                }
            }
            return "{\"respuesta\":\"0\"}";
        }
        return "{\"respuesta\":\"3\"}";
    }

    
    ///-----------------------------------------------------------------

    @RequestMapping(value = "/API/reportemedico", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject reportemedico(@RequestBody JSONObject reporte) {
        System.out.println("reportemedico en: " + Dependencia);
        String query = "INSERT INTO `reporte_medico` "
                + "(`id_UsuarioMovil`,"
                + " `fecha`, "
                + "`hora`, "
                + "`puesto`, "
                + "`especialidad`, "
                + "`estatus`, "
                + "`prueba_covid`, "
                + "`resultado_prueba`, "
                + "`cubrebocas`, "
                + "`careta`, "
                + "`guantes`, "
                + "`traje_desechable`, "
                + "`traje_aislamiento`, "
                + "`reporte`) "
                + "VALUES ("
                + "'" + reporte.get("id_UsuarioMovil") + "', "
                + "'" + reporte.get("fecha") + "', "
                + "'" + reporte.get("hora") + "', "
                + "'" + reporte.get("puesto") + "', "
                + "'" + reporte.get("especialidad") + "', "
                + "'" + reporte.get("estatus") + "', "
                + "'" + reporte.get("prueba_covid") + "', "
                + "'" + reporte.get("resultado_prueba") + "', "
                + "'" + reporte.get("cubrebocas") + "', "
                + "'" + reporte.get("careta") + "', "
                + "'" + reporte.get("guantes") + "', "
                + "'" + reporte.get("traje_desechable") + "', "
                + "'" + reporte.get("traje_aislamiento") + "', "
                + "'" + reporte.get("reporte") + "');";

        JSONObject respuesta = new JSONObject();
        respuesta.put("success", false);
        respuesta.put("error", true);
        if (Query.insert(query) > 0) {
            respuesta.put("success", true);
            respuesta.put("error", false);
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/reportehospital", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject reportehospital(@RequestBody JSONObject reporte) {
        System.out.println("reportehospital en: " + Dependencia);
        String query = "INSERT INTO `reporte_hospital` ("
                + "`id_Operador`, "
                + "`id_servicio`, "
                + "`fecha`, "
                + "`hora`, "
                + "`pacientes_totales`, "
                + "`pacientes_graves`, "
                + "`pacientes_nograves`, "
                + "`recuperados`, "
                + "`fallecidos`, "
                + "`camas_totales`, "
                + "`camas_ocupadas`, "
                + "`camas_covid_totales`, "
                + "`camas_covid_ocupadas`, "
                + "`camas_ti_totales`,"
                + " `camas_ti_ocupadas`, "
                + "`camas_urgencias_totales`, "
                + "`camas_urgencias_ocupadas`, "
                + "`uti`, "
                + "`cuartos_aislamiento`, "
                + "`positivos_nograves`, "
                + "`positivos_graves`, "
                + "`campamentos`, "
                + "`capacidad_campamentos`, "
                + "`casas_campana`, "
                + "`capacidad_casas_campana`, "
                + "`toldo`, "
                + "`capacidad_toldo`, "
                + "`catre`, "
                + "`cocina_moviles`, "
                + "`ambulancia_ti`, "
                + "`capacidad_ambulancia_ti`, "
                + "`ambulancia_traslado`, "
                + "`capacidad_ambulancia_traslado`, "
                + "`vehiculo_carga`, "
                + "`capacidad_vehiculo_carga`, "
                + "`tractocamion`, "
                + "`capacidad_tractocamion`, "
                + "`plataforma`, "
                + "`capacidad_plataforma`, "
                + "`cama_baja`, "
                + "`capacidad_cama_baja`, "
                + "`caja_seca`, "
                + "`capacidad_caja_seca`, "
                + "`carro_mudanza`, "
                + "`capacidad_carro_mudanza`, "
                + "`pullman`, "
                + "`capacidad_pullman`, "
                + "`aeronave_alafija`, "
                + "`capacidad_personas_aeronave_alafija`, "
                + "`capacidad_aeronave_alafija`, "
                + "`aeronave_alarotativa`, "
                + "`capacidad_personas_aeronave_alarotativa`, "
                + "`capacidad_aeronave_alarotativa`, "
                + "`ventiladores_mecanicos`, "
                + "`monitores`, "
                + "`rx_portatiles`, "
                + "`pulsioximetros`, "
                + "`carro_rojo`, "
                + "`ultrasonidos_moviles`, "
                + "`pruebas_covid_disponibles`, "
                + "`pruebas_covid_realizadas`, "
                + "`pruebas_covid_positivas`, "
                + "`pruebas_covid_negativas`, "
                + "`cubrebocas`, "
                + "`caretas`, "
                + "`guantes`, "
                + "`trajes_desechables`, "
                + "`trajes_aislamiento`, "
                + "`personal_total`, "
                + "`medicos_ti`, "
                + "`medicos_urg`, "
                + "`medicina_interna`, "
                + "`neumologia`, "
                + "`infectologia`, "
                + "`anesteciologia`, "
                + "`medico_cirujano`, "
                + "`cirujano_dentista`, "
                + "`enfermeros`, "
                + "`enfermeros_ti`, "
                + "`enfermeros_inhaloterapia`, "
                + "`personal_operativo_apoyo`, "
                + "`oficiales_sanidad`, "
                + "`tropas_sanidad`) "
                + "VALUES ("
                + "'" + reporte.get("id_Operador") + "', "
                + "'" + reporte.get("tipo_servicio") + "', "
                + "'" + reporte.get("fecha") + "', "
                + "'" + reporte.get("hora") + "', "
                + "'" + reporte.get("pacientes_totales") + "', "
                + "'" + reporte.get("pacientes_graves") + "', "
                + "'" + reporte.get("pacientes_nograves") + "', "
                + "'" + reporte.get("recuperados") + "', "
                + "'" + reporte.get("fallecidos") + "', "
                + "'" + reporte.get("camas_totales") + "', "
                + "'" + reporte.get("camas_ocupadas") + "', "
                + "'" + reporte.get("camas_covid_totales") + "', "
                + "'" + reporte.get("camas_covid_ocupadas") + "', "
                + "'" + reporte.get("camas_ti_totales") + "', "
                + "'" + reporte.get("camas_ti_ocupadas") + "', "
                + "'" + reporte.get("camas_urgencias_totales") + "', "
                + "'" + reporte.get("camas_urgencias_ocupadas") + "', "
                + "'" + reporte.get("uti") + "', "
                + "'" + reporte.get("cuartos_aislamiento") + "', "
                + "'" + reporte.get("positivos_nograves") + "', "
                + "'" + reporte.get("positivos_graves") + "', "
                + "'" + reporte.get("campamentos") + "', "
                + "'" + reporte.get("capacidad_campamentos") + "', "
                + "'" + reporte.get("casas_campana") + "', "
                + "'" + reporte.get("capacidad_casas_campana") + "', "
                + "'" + reporte.get("toldo") + "', "
                + "'" + reporte.get("capacidad_toldo") + "', "
                + "'" + reporte.get("catre") + "', "
                + "'" + reporte.get("cocina_moviles") + "', "
                + "'" + reporte.get("ambulancia_ti") + "', "
                + "'" + reporte.get("capacidad_ambulancia_ti") + "', "
                + "'" + reporte.get("ambulancia_traslado") + "', "
                + "'" + reporte.get("capacidad_ambulancia_traslado") + "', "
                + "'" + reporte.get("vehiculo_carga") + "', "
                + "'" + reporte.get("capacidad_vehiculo_carga") + "', "
                + "'" + reporte.get("tractocamion") + "', "
                + "'" + reporte.get("capacidad_tractocamion") + "', "
                + "'" + reporte.get("plataforma") + "', "
                + "'" + reporte.get("capacidad_plataforma") + "', "
                + "'" + reporte.get("cama_baja") + "', "
                + "'" + reporte.get("capacidad_cama_baja") + "', "
                + "'" + reporte.get("caja_seca") + "', "
                + "'" + reporte.get("capacidad_caja_seca") + "', "
                + "'" + reporte.get("carro_mudanza") + "', "
                + "'" + reporte.get("capacidad_carro_mudanza") + "', "
                + "'" + reporte.get("pullman") + "', "
                + "'" + reporte.get("capacidad_pullman") + "', "
                + "'" + reporte.get("aeronave_alafija") + "', "
                + "'" + reporte.get("capacidad_personas_aeronave_alafija") + "', "
                + "'" + reporte.get("capacidad_aeronave_alafija") + "', "
                + "'" + reporte.get("aeronave_alarotativa") + "', "
                + "'" + reporte.get("capacidad_personas_aeronave_alarotativa") + "', "
                + "'" + reporte.get("capacidad_aeronave_alarotativa") + "', "
                + "'" + reporte.get("ventiladores_mecanicos") + "', "
                + "'" + reporte.get("monitores") + "', "
                + "'" + reporte.get("rx_portatiles") + "', "
                + "'" + reporte.get("pulsioximetros") + "', "
                + "'" + reporte.get("carro_rojo") + "', "
                + "'" + reporte.get("ultrasonidos_moviles") + "', "
                + "'" + reporte.get("pruebas_covid_disponibles") + "', "
                + "'" + reporte.get("pruebas_covid_realizadas") + "', "
                + "'" + reporte.get("pruebas_covid_positivas") + "', "
                + "'" + reporte.get("pruebas_covid_negativas") + "', "
                + "'" + reporte.get("cubrebocas") + "', "
                + "'" + reporte.get("caretas") + "', "
                + "'" + reporte.get("guantes") + "', "
                + "'" + reporte.get("trajes_desechables") + "', "
                + "'" + reporte.get("trajes_aislamiento") + "', "
                + "'" + reporte.get("personal_total") + "', "
                + "'" + reporte.get("medicos_ti") + "', "
                + "'" + reporte.get("medicos_urg") + "', "
                + "'" + reporte.get("medicina_interna") + "', "
                + "'" + reporte.get("neumologia") + "', "
                + "'" + reporte.get("infectologia") + "', "
                + "'" + reporte.get("anesteciologia") + "', "
                + "'" + reporte.get("medico_cirujano") + "', "
                + "'" + reporte.get("cirujano_dentista") + "', "
                + "'" + reporte.get("enfermeros") + "', "
                + "'" + reporte.get("enfermeros_ti") + "', "
                + "'" + reporte.get("enfermeros_inhaloterapia") + "', "
                + "'" + reporte.get("personal_operativo_apoyo") + "', "
                + "'" + reporte.get("oficiales_sanidad") + "', "
                + "'" + reporte.get("tropas_sanidad") + "');";

        JSONObject respuesta = new JSONObject();
        respuesta.put("success", false);
        respuesta.put("error", true);
        if (Query.insert(query) > 0) {
            respuesta.put("success", true);
            respuesta.put("error", false);
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/ultimoreportehospital", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject ultimoreportehospital(@RequestBody JSONObject json) {
        System.out.println("reportehospital en: " + Dependencia);
        String query = "SELECT * from reporte_hospital where id_servicio=" + json.get("tipo_servicio") + "  ORDER BY id DESC limit 1;";
        return Query.select(query);
    }

    ///-----------------------------------------------------------------
    @RequestMapping(value = "/API/agregaServicio", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject agregaServicio(@RequestBody JSONObject json) throws java.text.ParseException, ParseException, IOException {
        System.out.println("agregaServicio en: " + Dependencia);
        JSONObject resp = new JSONObject();
        resp.put("procede", false);
        int hospital = 0;
        if (!json.get("nivel").toString().equals("NULL")) {
            hospital = 1;
        }

        String query = "INSERT INTO servicios_usuario (`idTipoUsuario`,`nombre`,`activo`,`direccion`,`telefono`,`lat`,`lng`,`nivel`,`hospital`) "
                + "VALUES ('" + json.get("idTipoUsuario") + "','" + json.get("nombre") + "',1,'" + json.get("direccion") + "','" + json.get("telefono") + "',"
                + "'" + json.get("lat") + "','" + json.get("lng") + "'," + json.get("nivel") + "," + hospital + ");";
        if (Query.insert(query) > 0) {
            resp.put("procede", true);
            resp.put("mensaje", "Institución agregada con éxito");
        } else {
            resp.put("mensaje", "Algo salio mal.<br>Inténtalo nuevamente.");
        }

        return resp;
    }

    @RequestMapping(value = "/API/registraTestCOVID/{tipo_usuario}/{tipo_servicio}", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject registraTestCOVID(@RequestBody JSONObject json, @PathVariable String tipo_usuario, @PathVariable String tipo_servicio) throws IOException, ParseException {
        System.out.println("registraTestCOVID en: " + Dependencia);
        JSONObject res = new JSONObject();
        int score = Integer.parseInt(json.get("score").toString());
        String tittle = "¡Todo está bien! ";
        String message = "Por el momento, te pedimos que permanezcas en aislamiento y tomes estas medidas preventivas:\n"
                + "Lávate las manos con frecuencia.\n"
                + "Evita lugares cocurridos.\n"
                + "Usa cubre bocas.";
        /////////////////EDAD
        String query = "SELECT "
                + "nombre,"
                + "apellido_paterno,"
                + "apellido_materno,"
                + "correo,telefono,"
                + "contacto_nombre,"
                + "contacto_telefono,"
                + "alergias,"
                + " rh,"
                + "fecha_nacimiento,"
                + "genero,"
                + "direccion,"
                + "cp,"
                + "condicion_medica, "
                + "FireBaseKey"
                + "  FROM usuarios_movil where idUsuarios_Movil=\"" + json.get("idUsuario") + "\"; ";
        JSONObject perfil = new JSONObject();

        if (config.isDEPENDENCIA_BASE()
                || tipo_usuario.equals("10")
                || tipo_usuario.equals("11")) {
            JSONParser parser = new JSONParser();
            perfil = (JSONObject) parser.parse(Post.ConsultarPerfil(json.get("idUsuario").toString(), config.getURL_CONTROLADOR() + "API/ConsultaPerfil"));
        } else {
            perfil = Query.select(query);
        }
        System.out.println(perfil);
        if (score > 2) { ///falta ver cual sera el score para notificar
            if (perfil != null) {
                if (config.isDEPENDENCIA_BASE()
                        || tipo_usuario.equals("10")
                        || tipo_usuario.equals("11")) {
                    perfil.put("img", "https://plataforma911.ml/CONTROLADOR/API/ConsultarImg/perfil/" + json.get("idUsuario"));
                    perfil.put("icon", "https://plataforma911.ml/CONTROLADOR/API/ConsultarImg/icono/" + json.get("idUsuario"));
                } else {
                    perfil.put("img", config.getPATH() + Dependencia + "/API/ConsultarImg/perfil/" + json.get("idUsuario"));
                    perfil.put("icon", config.getPATH() + Dependencia + "/API/ConsultarImg/icono/" + json.get("idUsuario"));
                }

            } else {
                System.out.println("Usuario no registrado");
            }
            String nivel = "";
            //Pidiendo serie a incidentes 
            if (score > 2) {
                nivel = "Bajo";
            }
            if (score > 4) {
                nivel = "Medio";
            }
            if (score > 5) {
                nivel = "Alto";
            }

            json.put("nivel", nivel);
            json.put("nombre", perfil.get("nombre"));
            json.put("apellido_paterno", perfil.get("apellido_paterno"));
            json.put("apellido_materno", perfil.get("apellido_materno"));

            System.out.println("Enviando a incidentes");
            System.out.println(json);
            JSONObject serie = request.POST("http://incidente.ml/api/registersCovid", json);
//            JSONObject serie = request.POST("http://172.19.0.75:80/api/registersCovid", json);
//            System.out.println("/////////////////");
//            System.out.println(serie.get("serie"));
//            json.put("serie", serie.get("serie"));
//            JSONObject serie = new JSONObject();
//            serie.put("serie", "12345");
            json.put("serie", serie.get("serie"));
            json.put("covid", true);

            //*****
            if (tipo_usuario.equals("10") || tipo_usuario.equals("11")) {
                json.put("p360", true);
            }
            //*****

            json.remove("nombre");
            json.remove("apellido_paterno");
            json.remove("apellido_materno");

            json.put("perfil", perfil);
            json.put("rango", score);
            json.put("origen", "App");
            json.put("proyecto", "sedena");
            json.put("tipo_servicio", tipo_servicio);
            json.put("tipo_usuario", tipo_usuario);

            //Notificar por socket incidente 
            SocketEndPoint.NotificarCOVID(json);

            if (score > 3) { //falta score bajo
                tittle = "¡Tu salud es muy importante!";
                message = "De acuerdo a tus respuestas, en esta fase de contagio (contagio comunitario) tienes riesgo bajo de tener COVID-19. \n"
                        + "Por el momento, te pedimos que permanezcas en aislamiento y tomes estas medidas preventivas:\n"
                        + "Lávate las manos con frecuencia.\n"
                        + "Evita lugares cocurridos.\n"
                        + "Usa cubre bocas.\n"
                        + "Si cambian tus síntomas, manda \"Covid19\" al 51515 de inmediato para llenar el cuestionario de nuevo o marca a Locatel al 56581111\n"
                        + "Le daremos seguimiento a tu caso con número " + serie.get("serie") + " en 4 días por este medio. Gracias.";
            }
            if (score > 4) { //falta score medio
                tittle = "¡Acércate a un especialista de la salud!";
                message = "De acuerdo a tus respuestas, en esta fase de contagio (contagio comunitario) tienes riesgo medio de tener COVID-19. \n"
                        + "Por el momento, te pedimos que permanezcas en aislamiento y tomes estas medidas preventivas:\n"
                        + "Lávate las manos con frecuencia.\n"
                        + "Evita lugares cocurridos.\n"
                        + "Usa cubre bocas.\n"
                        + "Si cambian tus síntomas, manda \"Covid19\" al 51515 de inmediato para llenar el cuestionario de nuevo o marca a Locatel al 56581111\n"
                        + "Le daremos seguimiento a tu caso con número " + serie.get("serie") + " en 4 días por este medio. Gracias.";
            }
            if (score > 5) { //falta score alto
                tittle = "¡Acude inmediatamente a tu especialista de la salud!";
                message = "De acuerdo a tus respuestas, en esta fase de contagio (contagio comunitario) tienes riesgo alto de tener COVID-19. \n"
                        + "Por el momento, te pedimos que permanezcas en aislamiento y tomes estas medidas preventivas:\n"
                        + "Lávate las manos con frecuencia.\n"
                        + "Evita lugares cocurridos.\n"
                        + "Usa cubre bocas.\n"
                        + "Si cambian tus síntomas, manda \"Covid19\" al 51515 de inmediato para llenar el cuestionario de nuevo o marca a Locatel al 56581111\n"
                        + "Le daremos seguimiento a tu caso con número " + serie.get("serie") + " en 4 días por este medio. Gracias.";
            }

//            String firebase = perfil.get("FireBaseKey").toString();
//            JSONObject jsonNotificacion = new JSONObject();
//            jsonNotificacion.put("firebase", firebase);
//            jsonNotificacion.put("tittle", tittle);
//            jsonNotificacion.put("message", message);
//            if (tipo_usuario.equals("10") || tipo_usuario.equals("11")) {
//                request.POST(config.getURL_CONTROLADOR() + "API/NotificacionCovid360", jsonNotificacion);
//            } else {
//                request.POST(config.getURL_CONTROLADOR() + "API/NotificacionCovid", jsonNotificacion);
//            }
            //realizamos registro en la BD
            query = "INSERT INTO reportes_covid (`lat`,`lng`,`idUsuario`,`edad`,`genero`,"
                    + "`cp`,`fecha`,`hora`,`convivir_enfermo`,`fiebre`,`dolor_cabeza`,"
                    + "`tos`,`dolor_pecho`,`dolor_garganta`,`dificultad_respirar`,`escurrimiento_nasal`,"
                    + "`dolor_cuerpo`,`conjuntivitis`,`dias_sintomas`,`condiciones_medicas`,`embarazada`,"
                    + "`meses_embarazo`,`dolor_respirar`,`falta_aire`,`coloracion_azul`,`score`,`serie`) VALUES"
                    + "('" + json.get("lat") + "','" + json.get("lng") + "','" + json.get("idUsuario") + "','" + json.get("edad") + "','" + json.get("genero") + "',"
                    + "'" + json.get("cp") + "','" + json.get("fecha") + "','" + json.get("hora") + "','" + json.get("convivir_enfermo") + "','" + json.get("fiebre") + "','" + json.get("dolor_cabeza") + "',"
                    + "'" + json.get("tos") + "','" + json.get("dolor_pecho") + "','" + json.get("dolor_garganta") + "','" + json.get("dificultad_respirar") + "','" + json.get("escurrimiento_nasal") + "',"
                    + "'" + json.get("dolor_cuerpo") + "','" + json.get("conjuntivitis") + "','" + json.get("dias_sintomas") + "','" + json.get("condiciones_medicas") + "','" + json.get("embarazada") + "',"
                    + "'" + json.get("meses_embarazo") + "','" + json.get("dolor_respirar") + "','" + json.get("falta_aire") + "','" + json.get("coloracion_azul") + "','" + json.get("score") + "',"
                    + "'" + serie.get("serie") + "');";
            int id = Query.insert(query);
            res.put("id", id);
        } else {
            query = "INSERT INTO reportes_covid (`lat`,`lng`,`idUsuario`,`edad`,`genero`,"
                    + "`cp`,`fecha`,`hora`,`convivir_enfermo`,`fiebre`,`dolor_cabeza`,"
                    + "`tos`,`dolor_pecho`,`dolor_garganta`,`dificultad_respirar`,`escurrimiento_nasal`,"
                    + "`dolor_cuerpo`,`conjuntivitis`,`dias_sintomas`,`condiciones_medicas`,`embarazada`,"
                    + "`meses_embarazo`,`dolor_respirar`,`falta_aire`,`coloracion_azul`,`score`,`serie`) VALUES"
                    + "('" + json.get("lat") + "','" + json.get("lng") + "','" + json.get("idUsuario") + "','" + json.get("edad") + "','" + json.get("genero") + "',"
                    + "'" + json.get("cp") + "','" + json.get("fecha") + "','" + json.get("hora") + "','" + json.get("convivir_enfermo") + "','" + json.get("fiebre") + "','" + json.get("dolor_cabeza") + "',"
                    + "'" + json.get("tos") + "','" + json.get("dolor_pecho") + "','" + json.get("dolor_garganta") + "','" + json.get("dificultad_respirar") + "','" + json.get("escurrimiento_nasal") + "',"
                    + "'" + json.get("dolor_cuerpo") + "','" + json.get("conjuntivitis") + "','" + json.get("dias_sintomas") + "','" + json.get("condiciones_medicas") + "','" + json.get("embarazada") + "',"
                    + "'" + json.get("meses_embarazo") + "','" + json.get("dolor_respirar") + "','" + json.get("falta_aire") + "','" + json.get("coloracion_azul") + "','" + json.get("score") + "',"
                    + "'NULL');";
            int id = Query.insert(query);
            res.put("id", id);
        }

        res.put("success", 1);
        return res;
    }

    //Esta funcion ira en le controlador
    @RequestMapping(value = "/API/registrarPaciente", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject registrarPaciente(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("registrarPaciente en: " + Dependencia);
        JSONObject resp = request.POST(config.getURL_CONTROLADOR() + "API/registrarPaciente", json);
        return resp;
    }

    //funcion para actualizar el estado del paciente
    @RequestMapping(value = "/API/actualizaEstadoPaciente", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject actualizaEstadoPaciente(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("actualizaEstadoPaciente en: " + Dependencia);
        JSONObject resp = request.POST(config.getURL_CONTROLADOR() + "API/actualizaEstadoPaciente", json);
        return resp;
    }

    /**
     * **************************************************************************************************************************
     */
    // Servicio web para enviar el formulario 2
    @RequestMapping(value = "/API/solicitud_traslado", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject solicitud_traslado(@RequestBody String jsonString) throws IOException, ParseException {
        System.out.println("solicitud_traslado en: " + Dependencia);
        //Registrar el json en la base de datos de solicitudes ***estado enviado***
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonString);
        JSONObject biometria_hematica = (JSONObject) (json.get("biometria_hematica"));
        JSONObject quimica_sanguinea = (JSONObject) (json.get("quimica_sanguinea"));
        JSONObject electrolitos_sericos = (JSONObject) (json.get("electrolitos_sericos"));
        JSONObject funcion_hepatica = (JSONObject) (json.get("funcion_hepatica"));
        JSONObject tiempos_coagulacion = (JSONObject) (json.get("tiempos_coagulacion"));
        JSONObject gasometria_arterial = (JSONObject) (json.get("gasometria_arterial"));
        json.put("codigo", generateString().substring(0, 8));
        String query = "INSERT INTO solicitudes ("
                + "`fecha`,"
                + "`hora`,"
                + "`institucion_refiere`,"
                + "`telefono_contacto_institucion`,"
                + "`nombre`,"
                + "`genero`,"
                + "`edad`,"
                + "`fecha_nacimiento`,"
                + "`telefono_contacto`,"
                + "`medico_refiere`,"
                + "`comorbilidades`,"
                + "`medicamentos`,"
                + "`padecimiento`,"
                + "`fecha_sintomas`,"
                + "`fecha_ingreso`,"
                + "`prueba_covid`,"
                + "`tratamiento`,"
                + "`evaluacion`,"
                + "`estado_actual`,"
                + "`signos_vitales`,"
                + "`motivo_envio`,"
                + "`eritrocitos`,"
                + "`hemoglobina`,"
                + "`hematocrito`,"
                + "`hemoglobina_corp`,"
                + "`concentracion_hemoglobina`,"
                + "`distribucion_erotrocitos`,"
                + "`plaquetas`,"
                + "`leucocitos`,"
                + "`neutrofilos`,"
                + "`linfocitos`,"
                + "`monocitos`,"
                + "`eosinofilos`,"
                + "`basofilos`,"
                + "`glucosa`,"
                + "`urea`,"
                + "`creatinina`,"
                + "`acido_urico`,"
                + "`colesterol`,"
                + "`trigliceridos`,"
                + "`ferritina`,"
                + "`dimero_d`,"
                + "`troponina_i`,"
                + "`ck_mb`,"
                + "`ck`,"
                + "`dhl`,"
                + "`bnp`,"
                + "`sodio`,"
                + "`potasio`,"
                + "`cloro`,"
                + "`calcio`,"
                + "`magnesio`,"
                + "`fosforo`,"
                + "`bilirrubina_total`,"
                + "`bilirrubina_directa`,"
                + "`bilirrubina_indirecta`,"
                + "`tgo`,"
                + "`tgp`,"
                + "`fosfatasa_alcalina`,"
                + "`albumina`,"
                + "`globulinas`,"
                + "`relacion_ag`,"
                + "`proteinas_totales`,"
                + "`tp`,"
                + "`tpt`,"
                + "`tt`,"
                + "`inr`,"
                + "`tiempo_sangrado`,"
                + "`ph`,"
                + "`pao2`,"
                + "`pco2`,"
                + "`sato2`,"
                + "`eb`,"
                + "`hco3`,"
                + "`lactato`,"
                + "`fio2`,"
                + "`estado`,`tipo_usuario`,`tipo_servicio`,`nombre_institucion`,`proyecto`,"
                + "`apellidop_paciente`,`apellidom_paciente`,`nombre_responsable`,"
                + "`apellidop_responsable`,`apellidom_responsable`,`vinculo`,"
                + "`correo_contacto`,`estudios_imagen`,`observaciones`,`correo_contacto_institucion`,"
                + "`neutrofilos_ul`,`linfocitos_ul`,`monocitos_ul`,`eosinofilos_ul`,`basofilos_ul`,"
                + "`curp`,`area_cama`,`codigo`,`turno`,`prioridad`,`nombre_cargo_solicita`,`hora_ingreso`) VALUES ("
                + " '" + json.get("fecha") + "',"
                + "'" + json.get("hora") + "',"
                + "'" + json.get("institucion_refiere") + "',"
                + "'" + json.get("telefono_contacto_institucion") + "',"
                + "'" + json.get("nombre") + "',"
                + "'" + json.get("genero") + "',"
                + "'" + json.get("edad") + "',"
                + "'" + json.get("fecha_nacimiento") + "',"
                + "'" + json.get("telefono_contacto") + "',"
                + "'" + json.get("medico_refiere") + "',"
                + "'" + json.get("comorbilidades") + "',"
                + "'" + json.get("medicamentos") + "',"
                + "'" + json.get("padecimiento") + "',"
                + "'" + json.get("fecha_sintomas") + "',"
                + "'" + json.get("fecha_ingreso") + "',"
                + "'" + json.get("prueba_covid") + "',"
                + "'" + json.get("tratamiento") + "',"
                + "'" + json.get("evaluacion") + "',"
                + "'" + json.get("estado_actual") + "',"
                + "'" + json.get("signos_vitales") + "',"
                + "'" + json.get("motivo_envio") + "',"
                + "'" + biometria_hematica.get("eritrocitos") + "',"
                + "'" + biometria_hematica.get("hemoglobina") + "',"
                + "'" + biometria_hematica.get("hematocrito") + "',"
                + "'" + biometria_hematica.get("hemoglobina_corp") + "',"
                + "'" + biometria_hematica.get("concentracion_hemoglobina") + "',"
                + "'" + biometria_hematica.get("distribucion_erotrocitos") + "',"
                + "'" + biometria_hematica.get("plaquetas") + "',"
                + "'" + biometria_hematica.get("leucocitos") + "',"
                + "'" + biometria_hematica.get("neutrofilos") + "',"
                + "'" + biometria_hematica.get("linfocitos") + "',"
                + "'" + biometria_hematica.get("monocitos") + "',"
                + "'" + biometria_hematica.get("eosinofilos") + "',"
                + "'" + biometria_hematica.get("basofilos") + "',"
                + "'" + quimica_sanguinea.get("glucosa") + "',"
                + "'" + quimica_sanguinea.get("urea") + "',"
                + "'" + quimica_sanguinea.get("creatinina") + "',"
                + "'" + quimica_sanguinea.get("acido_urico") + "',"
                + "'" + quimica_sanguinea.get("colesterol") + "',"
                + "'" + quimica_sanguinea.get("trigliceridos") + "',"
                + "'" + quimica_sanguinea.get("ferritina") + "',"
                + "'" + quimica_sanguinea.get("dimero_d") + "',"
                + "'" + quimica_sanguinea.get("troponina_i") + "',"
                + "'" + quimica_sanguinea.get("ck_mb") + "',"
                + "'" + quimica_sanguinea.get("ck") + "',"
                + "'" + quimica_sanguinea.get("dhl") + "',"
                + "'" + quimica_sanguinea.get("bnp") + "',"
                + "'" + electrolitos_sericos.get("sodio") + "',"
                + "'" + electrolitos_sericos.get("potasio") + "',"
                + "'" + electrolitos_sericos.get("cloro") + "',"
                + "'" + electrolitos_sericos.get("calcio") + "',"
                + "'" + electrolitos_sericos.get("magnesio") + "',"
                + "'" + electrolitos_sericos.get("fosforo") + "',"
                + "'" + funcion_hepatica.get("bilirrubina_total") + "',"
                + "'" + funcion_hepatica.get("bilirrubina_directa") + "',"
                + "'" + funcion_hepatica.get("bilirrubina_indirecta") + "',"
                + "'" + funcion_hepatica.get("tgo") + "',"
                + "'" + funcion_hepatica.get("tgp") + "',"
                + "'" + funcion_hepatica.get("fosfatasa_alcalina") + "',"
                + "'" + funcion_hepatica.get("albumina") + "',"
                + "'" + funcion_hepatica.get("globulinas") + "',"
                + "'" + funcion_hepatica.get("relacion_ag") + "',"
                + "'" + funcion_hepatica.get("proteinas_totales") + "',"
                + "'" + tiempos_coagulacion.get("tp") + "',"
                + "'" + tiempos_coagulacion.get("tpt") + "',"
                + "'" + tiempos_coagulacion.get("tt") + "',"
                + "'" + tiempos_coagulacion.get("inr") + "',"
                + "'" + tiempos_coagulacion.get("tiempo_sangrado") + "',"
                + "'" + gasometria_arterial.get("ph") + "',"
                + "'" + gasometria_arterial.get("pao2") + "',"
                + "'" + gasometria_arterial.get("pco2") + "',"
                + "'" + gasometria_arterial.get("sato2") + "',"
                + "'" + gasometria_arterial.get("eb") + "',"
                + "'" + gasometria_arterial.get("hco3") + "',"
                + "'" + gasometria_arterial.get("lactato") + "',"
                + "'" + gasometria_arterial.get("fio2") + "','Enviada','" + json.get("tipo_usuario") + "','" + json.get("tipo_servicio") + "',"
                + "'" + json.get("nombre_institucion") + "','" + json.get("proyecto") + "',"
                + "'" + json.get("apellidop_paciente") + "','" + json.get("apellidom_paciente") + "','" + json.get("nombre_responsable") + "',"
                + "'" + json.get("apellidop_responsable") + "','" + json.get("apellidom_responsable") + "','" + json.get("vinculo") + "',"
                + "'" + json.get("correo_contacto") + "','" + json.get("estudios_imagen") + "','" + json.get("observaciones") + "',"
                + "'" + json.get("correo_contacto_institucion") + "','" + biometria_hematica.get("neutrofilos_ul") + "','" + biometria_hematica.get("linfocitos_ul") + "',"
                + "'" + biometria_hematica.get("monocitos_ul") + "','" + biometria_hematica.get("eosinofilos_ul") + "','" + biometria_hematica.get("basofilos_ul") + "',"
                + "'" + json.get("curp") + "','" + json.get("area_cama") + "','" + json.get("codigo") + "','" + json.get("turno") + "',"
                + "'" + json.get("prioridad") + "','" + json.get("nombre_cargo_solicita") + "','" + json.get("hora_ingreso") + "');";

        int id = Query.insert(query);
        System.out.println(id);

        if (id > 0) {
            //BUSCAMOS EL ESTADO ANTERIOR
            query = "SELECT estado FROM solicitudes where id ='" + id + "';";
            JSONObject estado_anterior = Query.select(query);
            if (estado_anterior == null) {
                estado_anterior = new JSONObject();
                estado_anterior.put("estado", "NULL");
            }
            //Agregamos el cambio de estado a la tabla intermedia
            query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado_anterior`,`estado`,`nombre_institucion`) "
                    + "VALUES('" + id + "','" + json.get("fecha") + "','" + json.get("hora") + "','" + estado_anterior.get("estado") + "','Enviada','" + json.get("institucion_refiere") + "');";
            Query.insert(query);

            json.put("procede", true);
            //Notificar institucion
            json.put("estado", "Enviada");
            json.put("bandeja_entrada", true);
            json.put("id", id);
            SocketEndPoint.EnviarNotificacion(json);

            //Notificar a ccb mediante socket tipo_usuario = 20 tipo_servicio = 30
            json.put("bandeja_entrada", true);
            json.put("tipo_usuario", "20");
            json.put("tipo_servicio", "30");
            SocketEndPoint.EnviarNotificacion(json);

            /**
             * ****************Servicio WOkko***************
             */
            JSONObject wokko = new JSONObject();
            wokko.put("comorbilidadDM2", json.get("comorbilidades").toString().contains("Diabetes Melitus"));
            wokko.put("comorbilidadEPC", json.get("comorbilidades").toString().contains("Enfermedad pulmonar"));
            wokko.put("comorbilidadERC", json.get("comorbilidades").toString().contains("Enfermedad renal crónica"));
            wokko.put("comorbilidadHTA", json.get("comorbilidades").toString().contains("Hipertensión arterial sistémica"));
            wokko.put("comorbilidadObesidad", json.get("comorbilidades").toString().contains("IMC >30 y menor de 40"));
            wokko.put("contactoEmail", json.get("correo_contacto_institucion"));
            wokko.put("contactoTelefono", json.get("telefono_contacto_institucion"));
            wokko.put("excAmeritaCx", false);
            wokko.put("excCirrosis", false);
            wokko.put("excEmbarazo", false);
            wokko.put("excIncapazAlimentarse", false);
            wokko.put("excInfeccionBacteriana", false);
            wokko.put("excInfeccionBrote", false);
            wokko.put("excInfeccionResistente", false);
            wokko.put("excObesidadMorbida", false);
            wokko.put("excOtrasInfecciones", false);
            wokko.put("excPostOperado", false);
            wokko.put("excPostPCR", false);
            wokko.put("excSangradoActivo", false);
            wokko.put("excTerapiaRenal", false);
            wokko.put("excTraqueostomia", false);
            wokko.put("excVasopresores", false);
            wokko.put("excVentilacion", false);
            wokko.put("fechaHoraReferencia", Escalamiento.getFecha() + "T" + json.get("hora"));
            wokko.put("fechaIngreso", json.get("fecha_ingreso"));
            wokko.put("fechaSintomas", json.get("fecha_sintomas"));
            wokko.put("hospitalClues", "");
            wokko.put("hospitalNombre", json.get("institucion_refiere"));
            wokko.put("medicoTratanteApellido1", "");
            wokko.put("medicoTratanteApellido2", "");
            wokko.put("medicoTratanteNombre", json.get("medico_refiere"));
            wokko.put("pacienteApellido1", json.get("apellidop_paciente"));
            wokko.put("pacienteApellido2", json.get("apellidom_paciente"));
            wokko.put("pacienteFechaNacimiento", json.get("fecha_nacimiento"));
            wokko.put("pacienteGenero", json.get("genero").toString().equals("hombre") ? "M" : "F");
            wokko.put("pacienteNombre", json.get("nombre"));
            wokko.put("pruebaCovid", json.get("prueba_covid").toString().equals("No se realizo") ? "Sin prueba" : (json.get("prueba_covid").toString().equals("positiva") ? "Positiva" : "Negativa"));
            String response_wokko = null;
            try {
                System.out.println("Enviando informacion WOKKO");

                //response_wokko = request.POST("https://claro360.wokko.io:8443/referencia/sendClaroToWokko/", wokko.toString());
            } catch (Exception e) {
                System.out.println(e);
            }
            query = "INSERT INTO `wokko` (`folio`, `request`, `response`) VALUES ('" + id + "', '" + wokko.toString() + "', '" + response_wokko + "');";
            query = "INSERT INTO `wokko` (`folio`, `request`, `response`) VALUES ('" + id + "', '" + wokko.toString() + "', NULL);";
            Query.insert(query);

        } else {
            json.put("procede", false);
            json.put("mensaje", "Algo ocurrio al intentar procesar tu solicitud."
                    + "Intentalo nuevamente.");
        }

        return json;
    }

//    @RequestMapping(value = "/API/referencias_hospitalarias/solicitud_traslado", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
//    @ResponseBody
//    public static JSONObject referencias_hospitalarias_solicitud_traslado(@RequestBody String jsonString) throws IOException, ParseException {
//        System.out.println("solicitud_traslado en: " + Dependencia);
//        //Registrar el json en la base de datos de solicitudes ***estado enviado***
//        JSONParser parser = new JSONParser();
//        JSONObject json = (JSONObject) parser.parse(jsonString);
//        JSONObject biometria_hematica = (JSONObject) (json.get("biometria_hematica"));
//        JSONObject quimica_sanguinea = (JSONObject) (json.get("quimica_sanguinea"));
//        JSONObject electrolitos_sericos = (JSONObject) (json.get("electrolitos_sericos"));
//        JSONObject funcion_hepatica = (JSONObject) (json.get("funcion_hepatica"));
//        JSONObject tiempos_coagulacion = (JSONObject) (json.get("tiempos_coagulacion"));
//        JSONObject gasometria_arterial = (JSONObject) (json.get("gasometria_arterial"));
//        json.put("codigo", generateString().substring(0, 8));
//        String query=null;
//        
//        JSONObject  registro = new JSONObject();
//        registro = (JSONObject) json.clone();
//        registro.remove("biometria_hematica");
//        registro.remove("quimica_sanguinea");
//        registro.remove("electrolitos_sericos");
//        registro.remove("funcion_hepatica");
//        registro.remove("tiempos_coagulacion");
//        registro.remove("gasometria_arterial");
//        
//        
//        registro.putAll(biometria_hematica);
//        registro.putAll(quimica_sanguinea);
//        registro.putAll(electrolitos_sericos);
//        registro.putAll(gasometria_arterial);
//        registro.putAll(funcion_hepatica);
//        registro.putAll(tiempos_coagulacion);
//        
//        
//        int id = Query.insert(Query.createQueryInsert("referencias_hospitalarias", registro));
//        System.out.println(id);
//        
//       //crear el registro eventos 
//
//        if (id > 0) {
//            
//             //agregamos el registro e los cambios del evento 
//             JSONObject e = new JSONObject();
//             e.put("id_referencia_hospitalaria", id);
//             e.put("id_estado_referencia_hospitalaria", "1"); //como se acava de realizar la solicitud le corresponde el estado 1
//             e.put("tipo_usuario", json.get("tipo_usuario"));
//             e.put("tipo_servicio", json.get("tipo_servicio"));
//             e.put("to_tipo_usuario_institucion", json.get("to_tipo_usuario_institucion"));
//             e.put("to_tipo_servicio_institucion", json.get("to_tipo_servicio_institucion"));
//             Query.insert(Query.createQueryInsert("eventos_referencias_hospitalarias", e));
//             
//           
//
//            json.put("procede", true);
//            //Notificar institucion
//            json.put("estado", "1");//como se acava de realizar la solicitud le corresponde el estado 1
//            
//            json.put("estado_label", "Solicitud enviada desde: " + Query.select("SELECT nombre FROM servicios_usuario WHERE id = '"+json.get("tipo_servicio")+"';").get("nombre"));//como se acava de realizar la solicitud le corresponde el estado 1
//            json.put("nombre_estado", Query.select("SELECT nombre FROM estados_referencias_hospitalarias WHERE id = 1;").get("nombre"));
//            json.put("solicitud_de_trasferencia", true);
//            json.put("id", id);
//            SocketEndPoint.EnviarNotificacion(json);
//
//            
//            
//            //Notificar a ccb mediante socket a la institucion receptora
//            json.put("solicitud_de_trasferencia", true);
//            json.put("estado_label", "Solicitud recibida por: " + Query.select("SELECT nombre FROM servicios_usuario WHERE id = '"+json.get("tipo_servicio")+"';").get("nombre"));
//            json.put("tipo_usuario", json.get("to_tipo_usuario_institucion"));
//            json.put("tipo_servicio", json.get("to_tipo_servicio_institucion"));
//            SocketEndPoint.EnviarNotificacion(json);
//
//            
//
//        } else {
//            json.put("procede", false);
//            json.put("mensaje", "Algo ocurrio al intentar procesar tu solicitud."
//                    + "Intentalo nuevamente.");
//        }
//
//        return json;
//    }
    @RequestMapping(value = "/API/Wokko/Send", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject WokkoTemp() throws IOException, ParseException {
        System.out.println("WokkoTemp en: " + Dependencia);
        JSONObject respuesta = new JSONObject();
        String query = "select id,\n"
                + "comorbilidades,correo_contacto_institucion,telefono_contacto_institucion,fecha,hora,fecha_ingreso, \n"
                + "fecha_sintomas,nombre_institucion,medico_refiere,apellidop_paciente,apellidom_paciente,fecha_nacimiento, \n"
                + "genero,nombre, prueba_covid from solicitudes where activo=1;";
        JSONArray a = Query.execute(query);
        for (int i = 0; i < a.size(); i++) {
            JSONObject json = (JSONObject) a.get(i);
            JSONObject wokko = new JSONObject();
            wokko.put("comorbilidadDM2", json.get("comorbilidades").toString().contains("Diabetes Melitus"));
            wokko.put("comorbilidadEPC", json.get("comorbilidades").toString().contains("Enfermedad pulmonar"));
            wokko.put("comorbilidadERC", json.get("comorbilidades").toString().contains("Enfermedad renal crónica"));
            wokko.put("comorbilidadHTA", json.get("comorbilidades").toString().contains("Hipertensión arterial sistémica"));
            wokko.put("comorbilidadObesidad", json.get("comorbilidades").toString().contains("IMC >30 y menor de 40"));
            wokko.put("contactoEmail", json.get("correo_contacto_institucion"));
            wokko.put("contactoTelefono", json.get("telefono_contacto_institucion"));
            wokko.put("excAmeritaCx", false);
            wokko.put("excCirrosis", false);
            wokko.put("excEmbarazo", false);
            wokko.put("excIncapazAlimentarse", false);
            wokko.put("excInfeccionBacteriana", false);
            wokko.put("excInfeccionBrote", false);
            wokko.put("excInfeccionResistente", false);
            wokko.put("excObesidadMorbida", false);
            wokko.put("excOtrasInfecciones", false);
            wokko.put("excPostOperado", false);
            wokko.put("excPostPCR", false);
            wokko.put("excSangradoActivo", false);
            wokko.put("excTerapiaRenal", false);
            wokko.put("excTraqueostomia", false);
            wokko.put("excVasopresores", false);
            wokko.put("excVentilacion", false);
            wokko.put("fechaHoraReferencia", json.get("fecha") + "T" + json.get("hora"));
            wokko.put("fechaIngreso", json.get("fecha_ingreso"));
            wokko.put("fechaSintomas", json.get("fecha_sintomas"));
            wokko.put("hospitalClues", "");
            wokko.put("hospitalNombre", json.get("nombre_institucion"));
            wokko.put("medicoTratanteApellido1", "");
            wokko.put("medicoTratanteApellido2", "");
            wokko.put("medicoTratanteNombre", json.get("medico_refiere"));
            wokko.put("pacienteApellido1", json.get("apellidop_paciente"));
            wokko.put("pacienteApellido2", json.get("apellidom_paciente"));
            wokko.put("pacienteFechaNacimiento", json.get("fecha_nacimiento"));
            wokko.put("pacienteGenero", json.get("genero").toString().equals("hombre") ? "M" : "F");
            wokko.put("pacienteNombre", json.get("nombre"));
            wokko.put("pruebaCovid", json.get("prueba_covid").toString().equals("No se realizo") ? "Sin prueba" : (json.get("prueba_covid").toString().equals("positiva") ? "Positiva" : "Negativa"));
            String response_wokko = null;
            try {
                System.out.println("Enviando informacion WOKKO");
                respuesta.put(json.get("id"), false);
                response_wokko = request.POST("https://claro360.wokko.io:8443/referencia/sendClaroToWokko/", wokko.toString());
                respuesta.put(json.get("id"), true);
            } catch (Exception e) {
                System.out.println(e);
            }
            query = "INSERT INTO `wokko` (`folio`, `request`, `response`) VALUES ('" + json.get("id") + "', '" + wokko.toString() + "', '" + response_wokko + "');";
            Query.insert(query);
        }
        return respuesta;
    }

    //servicio rechazos automaticos
    @RequestMapping(value = "/API/rechazo_automatico", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject rechazo_automatico(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("rechazo_automatico en: " + Dependencia);
        //registro en bs
        String query = "INSERT INTO rechazos_automaticos ("
                + "`fecha`,"
                + "`hora`,"
                + "`institucion_refiere`,"
                + "`medico_refiere`,"
                + "`telefono_contacto`,"
                + "`correo_contacto`,"
                + "`nombre_paciente`,"
                + "`fecha_nacimiento`,"
                + "`genero`,"
                + "`fecha_ingreso`,"
                + "`fecha_sintomas`,"
                + "`prueba_covid`,"
                + "`comorbilidades`,"
                + "`imc`,"
                + "`traqueostomia`,"
                + "`terapia_renal`,"
                + "`incapacidad_alimentacion`,"
                + "`cirrosis_hepatica`,"
                + "`embarazo`,"
                + "`postoperado`,"
                + "`exantematicas_nosocomial`,"
                + "`requerimiento_transfusion`,"
                + "`infecciones`,"
                + "`paro_cardiovascular`,"
                + "`enfermedad_psiquiatrica`,"
                + "`dimero`,"
                + "`ferritina`,"
                + "`troponinas`,"
                + "`paciente_menor_edad`,`tipo_usuario`,`tipo_servicio`,`proyecto`,"
                + "`curp`,`peso`,`ventilacion_mecanica`,`procedimiento_quirurgico_urgencias`,`infecciones_brotes`,"
                + "`infecciones_microorganismos`,`otras_infecciones`,`paciente_vasopresores`) VALUES ("
                + "'" + json.get("fecha") + "',"
                + "'" + json.get("hora") + "',"
                + "'" + json.get("institucion_refiere") + "',"
                + "'" + json.get("medico_refiere") + "',"
                + "'" + json.get("telefono_contacto") + "',"
                + "'" + json.get("correo_contacto") + "',"
                + "'" + json.get("nombre_paciente") + "',"
                + "'" + json.get("fecha_nacimiento") + "',"
                + "'" + json.get("genero") + "',"
                + "'" + json.get("fecha_ingreso") + "',"
                + "'" + json.get("fecha_sintomas") + "',"
                + "'" + json.get("prueba_covid") + "',"
                + "'" + json.get("comorbilidades") + "',"
                + "'" + json.get("imc") + "',"
                + "'" + json.get("traqueostomia") + "',"
                + "'" + json.get("terapia_renal") + "',"
                + "'" + json.get("incapacidad_alimentacion") + "',"
                + "'" + json.get("cirrosis_hepatica") + "',"
                + "'" + json.get("embarazo") + "',"
                + "'" + json.get("postoperado") + "',"
                + "'" + json.get("exantematicas_nosocomial") + "',"
                + "'" + json.get("requerimiento_transfusion") + "',"
                + "'" + json.get("infecciones") + "',"
                + "'" + json.get("paro_cardiovascular") + "',"
                + "'" + json.get("enfermedad_psiquiatrica") + "',"
                + "'" + json.get("dimero") + "',"
                + "'" + json.get("ferritina") + "',"
                + "'" + json.get("troponinas") + "',"
                + "'" + json.get("paciente_menor_edad") + "',"
                + "'" + json.get("tipo_usuario") + "','" + json.get("tipo_servicio") + "','" + json.get("proyecto") + "',"
                + "'" + json.get("curp") + "','" + json.get("peso") + "','" + json.get("ventilacion_mecanica") + "',"
                + "'" + json.get("procedimiento_quirurgico_urgencias") + "','" + json.get("infecciones_brotes") + "',"
                + "'" + json.get("infecciones_microorganismos") + "','" + json.get("otras_infecciones") + "',"
                + "'" + json.get("paciente_vasopresores") + "');";

        int id = Query.insert(query);

        //Notificacion a institucion
        json.put("solicitud_traslado_rechazo", true);
        json.put("estado", "Rechazo Automatico");
        json.put("id", id);
        json.put("tipo_usuario", json.get("tipo_usuario"));
        json.put("tipo_servicio", json.get("tipo_servicio"));
        SocketEndPoint.EnviarNotificacion(json);

        //Notificacion en ccb 
        json.put("solicitud_traslado_rechazo", true);
        json.put("estado", "Rechazo Automatico");
        json.put("tipo_usuario", "20");
        json.put("tipo_servicio", "30");
        SocketEndPoint.EnviarNotificacion(json);

        //notificacion a institucion
        return json;
    }

    //servicio cambio de estado desde CCB 
    @RequestMapping(value = "/API/cambio_estado_ccb", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject cambio_estado_ccb(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("cambio_estado_ccb en: " + Dependencia);
        /*
        json de entrada
        - id registro de solicitud
        - estado (asignacion en server)
        - bandera_estado (booleano)
        - personal_ccb (array)
        - cama ()
        - estado_cama (si se acepta_ "apartada")
        - doctor_responsable (id)
         */
        JSONParser parser = new JSONParser();
        //Validacion 

        boolean procede = true;
        if (procede) {
            //cambiar estado elegido por ccb en solicitudes fecha y hora de cambio
            String query = null;
            if (Boolean.parseBoolean(json.get("bandera_estado").toString())) {
                //Aceptado por ccb
                json.put("estado", "Aceptada por UTC-19");

                //BUSCAMOS EL ESTADO ANTERIOR
                query = "SELECT estado FROM solicitudes where id ='" + json.get("id") + "';";
                JSONObject estado_anterior = Query.select(query);
                if (estado_anterior == null) {
                    estado_anterior = new JSONObject();
                    estado_anterior.put("estado", "NULL");
                }

                //Insertamos cambio en tabla intermedia
                query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado_anterior`,`estado`,`nombre_institucion`) "
                        + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
                        + "'" + estado_anterior.get("estado") + "','Aceptada por UTC-19','" + json.get("nombre_institucion") + "');";
                Query.insert(query);
                if (json.get("fecha_recepcion").toString().equals("NULL")) {
                    if (json.get("hora_recepcion").toString().equals("NULL")) {
                        query = "UPDATE `solicitudes` SET `estado` = 'Aceptada por UTC-19', `fecha_cambio` = '" + json.get("fecha_cambio") + "' , `hora_cambio` = '" + json.get("hora_cambio") + "', "
                                + "`cama` = '" + json.get("cama") + "', `estatus_cama` = 'apartada', `doctor_responsable` = '" + json.get("doctor_responsable") + "', "
                                + "nombre_doctor_responsable = '" + json.get("nombre_doctor_responsable") + "',"
                                + "`fecha_recepcion`= " + json.get("fecha_recepcion") + ", `hora_recepcion` = " + json.get("hora_recepcion") + " WHERE id = '" + json.get("id") + "';";
                    } else {
                        query = "UPDATE `solicitudes` SET `estado` = 'Aceptada por UTC-19', `fecha_cambio` = '" + json.get("fecha_cambio") + "' , `hora_cambio` = '" + json.get("hora_cambio") + "', "
                                + "`cama` = '" + json.get("cama") + "', `estatus_cama` = 'apartada', `doctor_responsable` = '" + json.get("doctor_responsable") + "', "
                                + "nombre_doctor_responsable = '" + json.get("nombre_doctor_responsable") + "',"
                                + "`fecha_recepcion`= " + json.get("fecha_recepcion") + ", `hora_recepcion` = '" + json.get("hora_recepcion") + "' WHERE id = '" + json.get("id") + "';";
                    }
                } else {
                    if (json.get("hora_recepcion").toString().equals("NULL")) {
                        query = "UPDATE `solicitudes` SET `estado` = 'Aceptada por UTC-19', `fecha_cambio` = '" + json.get("fecha_cambio") + "' , `hora_cambio` = '" + json.get("hora_cambio") + "', "
                                + "`cama` = '" + json.get("cama") + "', `estatus_cama` = 'apartada', `doctor_responsable` = '" + json.get("doctor_responsable") + "', "
                                + "nombre_doctor_responsable = '" + json.get("nombre_doctor_responsable") + "',"
                                + "`fecha_recepcion`= '" + json.get("fecha_recepcion") + "', `hora_recepcion` = " + json.get("hora_recepcion") + " WHERE id = '" + json.get("id") + "';";
                    } else {
                        query = "UPDATE `solicitudes` SET `estado` = 'Aceptada por UTC-19', `fecha_cambio` = '" + json.get("fecha_cambio") + "' , `hora_cambio` = '" + json.get("hora_cambio") + "', "
                                + "`cama` = '" + json.get("cama") + "', `estatus_cama` = 'apartada', `doctor_responsable` = '" + json.get("doctor_responsable") + "', "
                                + "nombre_doctor_responsable = '" + json.get("nombre_doctor_responsable") + "',"
                                + "`fecha_recepcion`= '" + json.get("fecha_recepcion") + "', `hora_recepcion` = '" + json.get("hora_recepcion") + "' WHERE id = '" + json.get("id") + "';";
                    }
                }

                Query.update(query);
            } else {

                //BUSCAMOS EL ESTADO ANTERIOR
                query = "SELECT estado FROM solicitudes where id ='" + json.get("id") + "';";
                JSONObject estado_anterior = Query.select(query);
                if (estado_anterior == null) {
                    estado_anterior = new JSONObject();
                    estado_anterior.put("estado", "NULL");
                }
                query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado_anterior`,`estado`,`nombre_institucion`) "
                        + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
                        + "'" + estado_anterior.get("estado") + "','Rechazada UTC-19','" + json.get("nombre_institucion") + "');";
                Query.insert(query);

                //Rechazado por ccb
                json.put("estado", "Rechazada UTC-19");
                query = "UPDATE `solicitudes` SET estado = 'Rechazada UTC-19', `fecha_cambio` = '" + json.get("fecha_cambio") + "' , `hora_cambio` = '" + json.get("hora_cambio") + "' WHERE id = '" + json.get("id") + "';";
                Query.update(query);

            }
            //quitar bandera de agregar en bandeja
            if (json.containsKey("bandeja_entrada")) {
                json.remove("bandeja_entrada");
            }

            //notificar institucion emisora
            query = "SELECT tipo_usuario, tipo_servicio FROM solicitudes WHERE id = '" + json.get("id") + "';";
            JSONObject resp = Query.select(query);
            if (resp != null) {
                json.put("cambio_estado_solicitud", true);
                json.put("tipo_usuario", resp.get("tipo_usuario"));
                json.put("tipo_servicio", resp.get("tipo_servicio"));
                SocketEndPoint.EnviarNotificacion(json);
            }

            //Notificar cambio de estado a la plataforma
            json.put("cambio_estado_solicitud", true);
            json.put("tipo_usuario", "20");
            json.put("tipo_servicio", "30");
            SocketEndPoint.EnviarNotificacion(json);

            // Buscar en base de datos origen
            //notificar 
            /*
            json.put("cambio_estado_solicitud", true);
        json.put("tipo_usuario", query.get("tipo_tipousuario));
        json.put("tipo_servicio", query.get("tipo_servicio));
        SocketEndPoint.EnviarNotificacion(json);
             */
            //notificar cambio de estado a ccb 
            //si es aceptado -------
            if (Boolean.parseBoolean(json.get("bandera_estado").toString())) {
                //Notificar a CRUM id_usuario = 19 tipo_servicio = 29
                json.remove("cambio_estado_solicitud");
                //duda por que bandeja_entrada
                json.put("bandeja_entrada", true);
                json.put("tipo_usuario", "19");
                json.put("tipo_servicio", "29");
                SocketEndPoint.EnviarNotificacion(json);

                //Notificar a SUCRE tipo_usuario = 21 tipo_servicio = 48
                json.put("bandeja_entrada", true);
                json.put("tipo_usuario", "21");
                json.put("tipo_servicio", "48");
                SocketEndPoint.EnviarNotificacion(json);

                // Enviar Push a personal involucrado a controlador
                /*
         json a enviar
          - firebase:[] //array corresponden a personal ccb
          - data = {}*
          - title *
          - message *
                 */
                //-----------------------
                //registrar personal involucrado
                JSONArray personal = (JSONArray) parser.parse(json.get("personal_ccb").toString());
                personal.add(json.get("doctor_responsable"));

                for (int i = 0; i < personal.size(); i++) {
                    query = "INSERT INTO `involucrados` (`idUsuario`, `idSolicitud`, `fecha`, `hora`)"
                            + " VALUES ('" + personal.get(i) + "', '" + json.get("id") + "', '" + Escalamiento.getFecha() + "', '" + Escalamiento.getHora() + "'); ";
                    Query.insert(query);
                }

                query = "SELECT FireBaseKey FROM usuarios_movil WHERE ";
                for (int i = 0; i < personal.size(); i++) {
                    query += "idUsuarios_Movil = '" + personal.get(i) + "' || ";
                }
                query = query.substring(0, (query.length() - 4));
                query += ";";

                JSONObject pushNot = new JSONObject();
                JSONArray firebase = Query.execute(query);

                JSONArray firebasenot = new JSONArray();
                for (int i = 0; i < firebase.size(); i++) {
                    JSONObject fb = (JSONObject) firebase.get(i);
                    firebasenot.add(fb.get("FireBaseKey"));
                }

                pushNot.put("firebase", firebasenot);

                JSONObject data = new JSONObject();
                data.put("idSolicitud", json.get("id"));
                pushNot.put("data", data);
                pushNot.put("type", "500");

                //Falta ver a que url del controlador se va a mandar
                request.POST(config.getURL_CONTROLADOR() + "API/NotificacionCCB", pushNot);
            }
        }
        return json;
    }

    //Servicio cambio de estado desde CRUM 
    @RequestMapping(value = "/API/cambio_estado_crum", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject cambio_estado_crum(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("cambio_estado_crum en: " + Dependencia);
        JSONParser parser = new JSONParser();
        /*
            -bandera_estado
            - personal_ccb (array)
         */
        boolean procede = true;
        if (procede) {
            String query = null;
            query = "SELECT estado FROM solicitudes WHERE id = '" + json.get("id") + "';";
            JSONObject estado_actual = Query.select(query);
            if (estado_actual.get("estado").toString().equals("Aceptada por UTC-19")) {
                JSONObject pushNot = new JSONObject();
                if (Boolean.parseBoolean(json.get("bandera_estado").toString())) {

                    //BUSCAMOS EL ESTADO ANTERIOR
                    query = "SELECT estado FROM solicitudes where id ='" + json.get("id") + "';";
                    JSONObject estado_anterior = Query.select(query);
                    if (estado_anterior == null) {
                        estado_anterior = new JSONObject();
                        estado_anterior.put("estado", "NULL");
                    }

                    query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado_anterior`,`estado`,`nombre_institucion`) "
                            + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
                            + "'" + estado_anterior.get("estado") + "','Aceptada por CRUM','" + json.get("nombre_institucion") + "');";
                    Query.insert(query);

                    json.put("estado", "Aceptada por CRUM");
                    pushNot.put("estado", "Aceptada por CRUM");
                    query = "UPDATE solicitudes SET estado = 'Aceptada por CRUM', `ambulancia`= '" + json.get("ambulancia")
                            + "', `personal_ambulancia`='" + json.get("personal_ambulancia")
                            + "', `responsable_ambulancia_asignado` = '" + json.get("responsable_ambulancia_asignado") + "' WHERE id = '" + json.get("id") + "';";
                    Query.update(query);

//                    query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado`,`nombre_institucion`) "
//                            + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
//                            + "'Aceptada por CRUM','" + json.get("nombre_institucion") + "');";
//                    Query.insert(query);
                } else {

                    //BUSCAMOS EL ESTADO ANTERIOR
                    query = "SELECT estado FROM solicitudes where id ='" + json.get("id") + "';";
                    JSONObject estado_anterior = Query.select(query);
                    if (estado_anterior == null) {
                        estado_anterior = new JSONObject();
                        estado_anterior.put("estado", "NULL");
                    }

                    query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado_anterior`,`estado`,`nombre_institucion`) "
                            + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
                            + "'" + estado_anterior.get("estado") + "','Rechazada por CRUM','" + json.get("nombre_institucion") + "');";
                    Query.insert(query);

                    json.put("estado", "Rechazada por CRUM");
                    pushNot.put("estado", "Rechazada por CRUM");
                    query = "UPDATE solicitudes SET estado = 'Rechazada por CRUM' WHERE id = '" + json.get("id") + "';";
                    Query.update(query);

//                    query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado`,`nombre_institucion`) "
//                            + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
//                            + "'Rechazada por CRUM','" + json.get("nombre_institucion") + "');";
//                    Query.insert(query);
                }

                //quitar bandera de agregar en bandeja
                if (json.containsKey("bandeja_entrada")) {
                    json.remove("bandeja_entrada");
                }

                //notificar institucion emisora
                query = "SELECT tipo_usuario, tipo_servicio FROM solicitudes WHERE id = '" + json.get("id") + "';";
                JSONObject resp = Query.select(query);
                if (resp != null) {
                    json.put("cambio_estado_solicitud", true);
                    json.put("tipo_usuario", resp.get("tipo_usuario"));
                    json.put("tipo_servicio", resp.get("tipo_servicio"));
                    SocketEndPoint.EnviarNotificacion(json);
                }

                //Notificar cambio de estado a la plataforma ccb
                json.put("cambio_estado_solicitud", true);
                json.put("tipo_usuario", "20");
                json.put("tipo_servicio", "30");
                SocketEndPoint.EnviarNotificacion(json);

                json.put("cambio_estado_solicitud", true);
                json.put("tipo_usuario", "19");
                json.put("tipo_servicio", "29");
                SocketEndPoint.EnviarNotificacion(json);

                //Notificar cambio de estado a la plataforma sucre
                json.put("cambio_estado_solicitud", true);
                json.put("tipo_usuario", "21");
                json.put("tipo_servicio", "48");
                SocketEndPoint.EnviarNotificacion(json);
//            
                //Si es aceptado por el CRUM se notificara al personal_ambulancia y al personal_cbb
                if (Boolean.parseBoolean(json.get("bandera_estado").toString())) {
                    JSONArray personal = (JSONArray) parser.parse(json.get("personal_ccb").toString());
                    JSONArray personal_ambulancia = (JSONArray) parser.parse(json.get("personal_ambulancia").toString());

                    query = "SELECT FireBaseKey FROM usuarios_movil WHERE ";
                    for (int i = 0; i < personal.size(); i++) {
                        query += "idUsuarios_Movil = '" + personal.get(i) + "' || ";
                    }
                    for (int i = 0; i < personal_ambulancia.size(); i++) {
                        query += "idUsuarios_Movil = '" + personal_ambulancia.get(i) + "' || ";
                    }
                    query = query.substring(0, (query.length() - 4));
                    query += ";";

                    JSONArray firebase = Query.execute(query);

                    JSONArray firebasenot = new JSONArray();
                    for (int i = 0; i < firebase.size(); i++) {
                        JSONObject fb = (JSONObject) firebase.get(i);
                        firebasenot.add(fb.get("FireBaseKey"));
                    }

                    pushNot.put("firebase", firebasenot);

                    JSONObject data = new JSONObject();
                    //Que info tendra el data aparte de el id
                    data.put("idSolicitud", json.get("id"));
                    pushNot.put("type", "501");
                    pushNot.put("personal_ambulancia", json.get("personal_ambulancia"));
                    pushNot.put("ambulancia", json.get("ambulancia"));
                    pushNot.put("data", data);
                } else {
                    //Caso contrario solo se notificara la desicion al personal_cbb
                    JSONArray personal = (JSONArray) parser.parse(json.get("personal_ccb").toString());
                    query = "SELECT FireBaseKey FROM usuarios_movil WHERE ";
                    for (int i = 0; i < personal.size(); i++) {
                        query += "idUsuarios_Movil = '" + personal.get(i) + "' || ";
                    }
                    query = query.substring(0, (query.length() - 4));
                    query += ";";

                    JSONArray firebase = Query.execute(query);

                    JSONArray firebasenot = new JSONArray();
                    for (int i = 0; i < firebase.size(); i++) {
                        JSONObject fb = (JSONObject) firebase.get(i);
                        firebasenot.add(fb.get("FireBaseKey"));
                    }

                    pushNot.put("firebase", firebasenot);

                    JSONObject data = new JSONObject();
                    //Que info tendra el data aparte de el id
                    data.put("idSolicitud", json.get("id"));
                    pushNot.put("type", "501");
//                pushNot.put("personal_ambulancia", json.get("personal_ambulancia"));
//                pushNot.put("ambulancia", json.get("ambulancia"));
                    pushNot.put("data", data);
                }

                //Falta ver a que url del controlador se va a mandar
                request.POST(config.getURL_CONTROLADOR() + "API/NotificacionCCB", pushNot);
            }
        }

        return json;
    }

    //Servicio cambio de estado desde SUCRE 
    @RequestMapping(value = "/API/cambio_estado_sucre", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject cambio_estado_sucre(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("cambio_estado_sucre en: " + Dependencia);
        JSONParser parser = new JSONParser();
        boolean procede = true;
        if (procede) {
            String query = null;
            query = "SELECT estado FROM solicitudes WHERE id = '" + json.get("id") + "';";
            JSONObject estado_actual = Query.select(query);
            if (estado_actual.get("estado").toString().equals("Aceptada por UTC-19")) {
                JSONObject pushNot = new JSONObject();
                if (Boolean.parseBoolean(json.get("bandera_estado").toString())) {

                    //BUSCAMOS EL ESTADO ANTERIOR
                    query = "SELECT estado FROM solicitudes where id ='" + json.get("id") + "';";
                    JSONObject estado_anterior = Query.select(query);
                    if (estado_anterior == null) {
                        estado_anterior = new JSONObject();
                        estado_anterior.put("estado", "NULL");
                    }

                    query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado_anterior`,`estado`,`nombre_institucion`) "
                            + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
                            + "'" + estado_anterior.get("estado") + "','Aceptada por SUCRE','" + json.get("nombre_institucion") + "');";
                    Query.insert(query);

                    json.put("estado", "Aceptada por SUCRE");
                    pushNot.put("estado", "Aceptada por SUCRE");
                    query = "UPDATE solicitudes SET estado = 'Aceptada por SUCRE', `ambulanciaSUCRE`= '" + json.get("ambulanciaSUCRE")
                            + "', `personal_ambulancia`='" + json.get("personal_ambulanciaSUCRE")
                            + "', `responsable_ambulancia_asignadoSUCRE` = '" + json.get("responsable_ambulancia_asignadoSUCRE") + "' WHERE id = '" + json.get("id") + "';";
                    Query.update(query);

//                    query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado`,`nombre_institucion`) "
//                            + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
//                            + "'Aceptada por SUCRE','" + json.get("nombre_institucion") + "');";
//                    Query.insert(query);
                } else {

                    //BUSCAMOS EL ESTADO ANTERIOR
                    query = "SELECT estado FROM solicitudes where id ='" + json.get("id") + "';";
                    JSONObject estado_anterior = Query.select(query);
                    if (estado_anterior == null) {
                        estado_anterior = new JSONObject();
                        estado_anterior.put("estado", "NULL");
                    }

                    query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado_anterior`,`estado`,`nombre_institucion`) "
                            + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
                            + "'" + estado_anterior.get("estado") + "','Rechazada por SUCRE','" + json.get("nombre_institucion") + "');";
                    Query.insert(query);

                    json.put("estado", "Rechazada por SUCRE");
                    pushNot.put("estado", "Rechazada por SUCRE");
                    query = "UPDATE solicitudes SET estado = 'Rechazada por SUCRE' WHERE id = '" + json.get("id") + "';";
                    Query.update(query);

//                    query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado`,`nombre_institucion`) "
//                            + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
//                            + "'Rechazada por SUCRE','" + json.get("nombre_institucion") + "');";
//                    Query.insert(query);
                }

                //quitar bandera de agregar en bandeja
                if (json.containsKey("bandeja_entrada")) {
                    json.remove("bandeja_entrada");
                }

                //notificar institucion emisora
                query = "SELECT tipo_usuario, tipo_servicio FROM solicitudes WHERE id = '" + json.get("id") + "';";
                JSONObject resp = Query.select(query);
                if (resp != null) {
                    json.put("cambio_estado_solicitud", true);
                    json.put("tipo_usuario", resp.get("tipo_usuario"));
                    json.put("tipo_servicio", resp.get("tipo_servicio"));
                    SocketEndPoint.EnviarNotificacion(json);
                }

                //Notificar cambio de estado a la plataforma ccb
                json.put("cambio_estado_solicitud", true);
                json.put("tipo_usuario", "20");
                json.put("tipo_servicio", "30");
                SocketEndPoint.EnviarNotificacion(json);
                //Notificar cambio de estado a la plataforma sucre
                json.put("cambio_estado_solicitud", true);
                json.put("tipo_usuario", "21");
                json.put("tipo_servicio", "48");
                SocketEndPoint.EnviarNotificacion(json);
                //Notificamos a crum
                json.put("cambio_estado_solicitud", true);
                json.put("tipo_usuario", "19");
                json.put("tipo_servicio", "29");
                SocketEndPoint.EnviarNotificacion(json);

//            
                //Si es aceptado por el CRUM se notificara al personal_ambulancia y al personal_cbb
                if (Boolean.parseBoolean(json.get("bandera_estado").toString())) {
                    JSONArray personal = (JSONArray) parser.parse(json.get("personal_ccb").toString());
                    JSONArray personal_ambulancia = (JSONArray) parser.parse(json.get("personal_ambulanciaSUCRE").toString());

                    query = "SELECT FireBaseKey FROM usuarios_movil WHERE ";
                    for (int i = 0; i < personal.size(); i++) {
                        query += "idUsuarios_Movil = '" + personal.get(i) + "' || ";
                    }
                    for (int i = 0; i < personal_ambulancia.size(); i++) {
                        query += "idUsuarios_Movil = '" + personal_ambulancia.get(i) + "' || ";
                    }
                    query = query.substring(0, (query.length() - 4));
                    query += ";";

                    JSONArray firebase = Query.execute(query);

                    JSONArray firebasenot = new JSONArray();
                    for (int i = 0; i < firebase.size(); i++) {
                        JSONObject fb = (JSONObject) firebase.get(i);
                        firebasenot.add(fb.get("FireBaseKey"));
                    }

                    pushNot.put("firebase", firebasenot);

                    JSONObject data = new JSONObject();
                    //Que info tendra el data aparte de el id
                    data.put("idSolicitud", json.get("id"));
                    pushNot.put("type", "501");
                    pushNot.put("personal_ambulancia", json.get("personal_ambulanciaSUCRE"));
                    pushNot.put("ambulancia", json.get("ambulancia"));
                    pushNot.put("data", data);
                } else {
                    //Caso contrario solo se notificara la desicion al personal_cbb
                    JSONArray personal = (JSONArray) parser.parse(json.get("personal_ccb").toString());
                    query = "SELECT FireBaseKey FROM usuarios_movil WHERE ";
                    for (int i = 0; i < personal.size(); i++) {
                        query += "idUsuarios_Movil = '" + personal.get(i) + "' || ";
                    }
                    query = query.substring(0, (query.length() - 4));
                    query += ";";

                    JSONArray firebase = Query.execute(query);

                    JSONArray firebasenot = new JSONArray();
                    for (int i = 0; i < firebase.size(); i++) {
                        JSONObject fb = (JSONObject) firebase.get(i);
                        firebasenot.add(fb.get("FireBaseKey"));
                    }

                    pushNot.put("firebase", firebasenot);

                    JSONObject data = new JSONObject();
                    //Que info tendra el data aparte de el id
                    data.put("idSolicitud", json.get("id"));
                    pushNot.put("type", "501");
//                pushNot.put("personal_ambulancia", json.get("personal_ambulancia"));
//                pushNot.put("ambulancia", json.get("ambulancia"));
                    pushNot.put("data", data);
                }

                //Falta ver a que url del controlador se va a mandar
                request.POST(config.getURL_CONTROLADOR() + "API/NotificacionCCB", pushNot);
            }
        }

        return json;
    }

    //servicio cambio de estado desde institucion que inicia traslado 
    @RequestMapping(value = "/API/inicio_traslado", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject inicio_traslado(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("inicio_traslado en: " + Dependencia);
        JSONParser parser = new JSONParser();
        String query = "";
        //Validacion 

//        String query = "SELECT * FROM solicitudes WHERE id = '"+json.get("id")+"';";
//        JSONObject jsonObj = Query.select(query);
        boolean procede = true;
        if (procede) {

            //BUSCAMOS EL ESTADO ANTERIOR
            query = "SELECT estado FROM solicitudes where id ='" + json.get("id") + "';";
            JSONObject estado_anterior = Query.select(query);
            if (estado_anterior == null) {
                estado_anterior = new JSONObject();
                estado_anterior.put("estado", "NULL");
            }

            query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado_anterior`,`estado`,`nombre_institucion`) "
                    + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
                    + "'" + estado_anterior.get("estado") + "','En traslado','" + json.get("nombre_institucion") + "');";
            Query.insert(query);

            //cambiar estado a trasldo iniciado
            //actualizar estad en traslado
            json.put("estado", "En traslado");
            query = "UPDATE `solicitudes` SET `estado` = 'En traslado', `fecha_cambio` = '" + json.get("fecha_cambio") + "' , `hora_cambio` = '" + json.get("hora_cambio") + "' WHERE id = '" + json.get("id") + "';";
            Query.update(query);

//            query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado`,`nombre_institucion`) "
//                    + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
//                    + "'En traslado','" + json.get("nombre_institucion") + "');";
//            Query.insert(query);
//            jsonObj.put("fecha_cambio", json.get("fecha_cambio"));
//            jsonObj.put("hora_cambio", json.get("hora_cambio"));
            //quitar bandera de agregar en bandeja
            if (json.containsKey("bandeja_entrada")) {
                json.remove("bandeja_entrada");
            }

            //notificar institucion emisora
            query = "SELECT tipo_usuario, tipo_servicio FROM solicitudes WHERE id = '" + json.get("id") + "';";
            JSONObject resp = Query.select(query);
            if (resp != null) {
                json.put("cambio_estado_solicitud", true);
                json.put("tipo_usuario", resp.get("tipo_usuario"));
                json.put("tipo_servicio", resp.get("tipo_servicio"));
                SocketEndPoint.EnviarNotificacion(json);
            }

            //Notificar cambio de estado a la plataforma ccb
            json.put("cambio_estado_solicitud", true);
            json.put("tipo_usuario", "20");
            json.put("tipo_servicio", "30");
            SocketEndPoint.EnviarNotificacion(json);

            //Notificamos al CRUM
            json.put("tipo_usuario", "19");
            json.put("tipo_servicio", "29");
            SocketEndPoint.EnviarNotificacion(json);

            //Notificar cambio de estado a la plataforma sucre
            json.put("cambio_estado_solicitud", true);
            json.put("tipo_usuario", "21");
            json.put("tipo_servicio", "48");
            SocketEndPoint.EnviarNotificacion(json);
            // Enviar Push a personal involucrado a controlador
            //registrar personal involucrado
//            query = "SELECT idUsuario FROM involucrados WHERE idSolicitud = '"+jsonObj.get("id")+"';";
            JSONArray personal = (JSONArray) parser.parse(json.get("personal_ccb").toString());
            personal.add(json.get("doctor_responsable"));

//            for (int i = 0; i < personal.size(); i++) {
//                query = "INSERT INTO `involucrados` (`idUsuario`, `idSolicitud`, `fecha`, `hora`)"
//                        + " VALUES ('" + personal.get(i) + "', '" + json.get("id") + "', '" + json.get("fecha") + "', '" + json.get("hora") + "'); ";
//                Query.insert(query);
//            }
            query = "SELECT FireBaseKey FROM usuarios_movil WHERE ";
            for (int i = 0; i < personal.size(); i++) {
                query += "idUsuarios_Movil = '" + personal.get(i) + "' || ";
            }
            query = query.substring(0, (query.length() - 4));
            query += ";";

            JSONObject pushNot = new JSONObject();
            JSONArray firebase = Query.execute(query);

            JSONArray firebasenot = new JSONArray();
            for (int i = 0; i < firebase.size(); i++) {
                JSONObject fb = (JSONObject) firebase.get(i);
                firebasenot.add(fb.get("FireBaseKey"));
            }

            pushNot.put("firebase", firebasenot);

            JSONObject data = new JSONObject();
            data.put("idSolicitud", json.get("id"));
            pushNot.put("data", data);
            pushNot.put("type", "500");

            //Falta ver a que url del controlador se va a mandar
            request.POST(config.getURL_CONTROLADOR() + "API/NotificacionCCB", pushNot);

        }
        return json;
    }

    //servicio cambio de estado desde institucion que inicia traslado 
    @RequestMapping(value = "/API/inicio_traslado_sucre", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject inicio_traslado_sucre(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("inicio_traslado_sucre en: " + Dependencia);
        JSONParser parser = new JSONParser();
        String query = "";
        //Validacion 

//        String query = "SELECT * FROM solicitudes WHERE id = '"+json.get("id")+"';";
//        JSONObject jsonObj = Query.select(query);
        boolean procede = true;
        if (procede) {

            //BUSCAMOS EL ESTADO ANTERIOR
            query = "SELECT estado FROM solicitudes where id ='" + json.get("id") + "';";
            JSONObject estado_anterior = Query.select(query);
            if (estado_anterior == null) {
                estado_anterior = new JSONObject();
                estado_anterior.put("estado", "NULL");
            }
            query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado_anterior`,`estado`,`nombre_institucion`) "
                    + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
                    + "'" + estado_anterior.get("estado") + "','En traslado SUCRE','" + json.get("nombre_institucion") + "');";
            Query.insert(query);

            //cambiar estado a trasldo iniciado
            //actualizar estad en traslado
            json.put("estado", "En traslado SUCRE");
            query = "UPDATE `solicitudes` SET `estado` = 'En traslado SUCRE', `fecha_cambio` = '" + json.get("fecha_cambio") + "' , `hora_cambio` = '" + json.get("hora_cambio") + "' WHERE id = '" + json.get("id") + "';";
            Query.update(query);

//            query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado`,`nombre_institucion`) "
//                    + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
//                    + "'En traslado SUCRE','" + json.get("nombre_institucion") + "');";
//            Query.insert(query);
//            jsonObj.put("fecha_cambio", json.get("fecha_cambio"));
//            jsonObj.put("hora_cambio", json.get("hora_cambio"));
            //quitar bandera de agregar en bandeja
            if (json.containsKey("bandeja_entrada")) {
                json.remove("bandeja_entrada");
            }

            //notificar institucion emisora
            query = "SELECT tipo_usuario, tipo_servicio FROM solicitudes WHERE id = '" + json.get("id") + "';";
            JSONObject resp = Query.select(query);
            if (resp != null) {
                json.put("cambio_estado_solicitud", true);
                json.put("tipo_usuario", resp.get("tipo_usuario"));
                json.put("tipo_servicio", resp.get("tipo_servicio"));
                SocketEndPoint.EnviarNotificacion(json);
            }

            //Notificar cambio de estado a la plataforma ccb
            json.put("cambio_estado_solicitud", true);
            json.put("tipo_usuario", "20");
            json.put("tipo_servicio", "30");
            SocketEndPoint.EnviarNotificacion(json);

            //Notificamos al CRUM
            json.put("tipo_usuario", "19");
            json.put("tipo_servicio", "29");
            SocketEndPoint.EnviarNotificacion(json);

            //Notificar cambio de estado a la plataforma sucre
            json.put("cambio_estado_solicitud", true);
            json.put("tipo_usuario", "21");
            json.put("tipo_servicio", "48");
            SocketEndPoint.EnviarNotificacion(json);
            // Enviar Push a personal involucrado a controlador
            //registrar personal involucrado
//            query = "SELECT idUsuario FROM involucrados WHERE idSolicitud = '"+jsonObj.get("id")+"';";
            JSONArray personal = (JSONArray) parser.parse(json.get("personal_ccb").toString());
            personal.add(json.get("doctor_responsable"));

//            for (int i = 0; i < personal.size(); i++) {
//                query = "INSERT INTO `involucrados` (`idUsuario`, `idSolicitud`, `fecha`, `hora`)"
//                        + " VALUES ('" + personal.get(i) + "', '" + json.get("id") + "', '" + json.get("fecha") + "', '" + json.get("hora") + "'); ";
//                Query.insert(query);
//            }
            query = "SELECT FireBaseKey FROM usuarios_movil WHERE ";
            for (int i = 0; i < personal.size(); i++) {
                query += "idUsuarios_Movil = '" + personal.get(i) + "' || ";
            }
            query = query.substring(0, (query.length() - 4));
            query += ";";

            JSONObject pushNot = new JSONObject();
            JSONArray firebase = Query.execute(query);

            JSONArray firebasenot = new JSONArray();
            for (int i = 0; i < firebase.size(); i++) {
                JSONObject fb = (JSONObject) firebase.get(i);
                firebasenot.add(fb.get("FireBaseKey"));
            }

            pushNot.put("firebase", firebasenot);

            JSONObject data = new JSONObject();
            data.put("idSolicitud", json.get("id"));
            pushNot.put("data", data);
            pushNot.put("type", "500");

            //Falta ver a que url del controlador se va a mandar
            request.POST(config.getURL_CONTROLADOR() + "API/NotificacionCCB", pushNot);

        }
        return json;
    }

    //servicio cambio de estado cuando cancelan la solicitud
    @RequestMapping(value = "/API/cancelar_solicitud", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject cancelar_solicitud(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("cancelar_solicitud en: " + Dependencia);
        JSONParser parser = new JSONParser();

        boolean procede = true;
        if (procede) {
            //cambiar estado a trasldo iniciado
            String query = null;

            //BUSCAMOS EL ESTADO ANTERIOR
            query = "SELECT estado FROM solicitudes where id ='" + json.get("id") + "';";
            JSONObject estado_anterior = Query.select(query);
            if (estado_anterior == null) {
                estado_anterior = new JSONObject();
                estado_anterior.put("estado", "NULL");
            }

            query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado_anterior`,`estado`,`nombre_institucion`) "
                    + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
                    + "'" + estado_anterior.get("estado") + "','Cancelada','" + json.get("nombre_institucion") + "');";
            Query.insert(query);

            //actualizar estado 
            json.put("estado", "Cancelada");
            query = "UPDATE `solicitudes` SET `estado` = 'Cancelada', `fecha_cambio` = '" + json.get("fecha_cambio") + "' , `hora_cambio` = '" + json.get("hora_cambio") + "', "
                    + " `cama` = '" + json.get("cama") + "',`estatus_cama`='Ocupada'  WHERE id = '" + json.get("id") + "';";
            Query.update(query);

            //quitar bandera de agregar en bandeja
            if (json.containsKey("bandeja_entrada")) {
                json.remove("bandeja_entrada");
            }

            //notificar institucion emisora
            query = "SELECT tipo_usuario, tipo_servicio FROM solicitudes WHERE id = '" + json.get("id") + "';";
            JSONObject resp = Query.select(query);
            if (resp != null) {
                json.put("cambio_estado_solicitud", true);
                json.put("tipo_usuario", resp.get("tipo_usuario"));
                json.put("tipo_servicio", resp.get("tipo_servicio"));
                SocketEndPoint.EnviarNotificacion(json);
            }

            //Notificar cambio de estado a la plataforma ccb
            json.put("cambio_estado_solicitud", true);
            json.put("tipo_usuario", "20");
            json.put("tipo_servicio", "30");
            SocketEndPoint.EnviarNotificacion(json);
            //notificar cambio de estado a crum
            json.put("tipo_usuario", "19");
            json.put("tipo_servicio", "29");
            SocketEndPoint.EnviarNotificacion(json);
            //Notificar cambio de estado a la plataforma sucre
            json.put("cambio_estado_solicitud", true);
            json.put("tipo_usuario", "21");
            json.put("tipo_servicio", "48");
            SocketEndPoint.EnviarNotificacion(json);
        }
        return json;
    }

    //servicio cambio de estado cuando cancelan la solicitud
    @RequestMapping(value = "/API/quitar_rechazo", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject quitar_rechazo(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("quitar_rechazo en: " + Dependencia);
        JSONParser parser = new JSONParser();

        boolean procede = true;
        if (procede) {
            //cambiar estado a trasldo iniciado
            String query = null;

            //BUSCAMOS EL ESTADO ANTERIOR
            query = "SELECT estado FROM solicitudes where id ='" + json.get("id") + "';";
            JSONObject estado_anterior = Query.select(query);
            if (estado_anterior == null) {
                estado_anterior = new JSONObject();
                estado_anterior.put("estado", "NULL");
            }

            query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado_anterior`,`estado`,`nombre_institucion`) "
                    + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
                    + "'" + estado_anterior.get("estado") + "','Enviada','" + json.get("nombre_institucion") + "');";
            Query.insert(query);

            //actualizar estado 
            json.put("estado", "Enviada");
            query = "UPDATE `solicitudes` SET `estado` = 'Enviada', `fecha_cambio` = '" + json.get("fecha_cambio") + "' , `hora_cambio` = '" + json.get("hora_cambio") + "', "
                    + " `cama` = '" + json.get("cama") + "',`estatus_cama`='Ocupada'  WHERE id = '" + json.get("id") + "';";
            Query.update(query);

            //quitar bandera de agregar en bandeja
            if (json.containsKey("bandeja_entrada")) {
                json.remove("bandeja_entrada");
            }

            //notificar institucion emisora
            query = "SELECT tipo_usuario, tipo_servicio FROM solicitudes WHERE id = '" + json.get("id") + "';";
            JSONObject resp = Query.select(query);
            if (resp != null) {
                json.put("cambio_estado_solicitud", true);
                json.put("tipo_usuario", resp.get("tipo_usuario"));
                json.put("tipo_servicio", resp.get("tipo_servicio"));
                SocketEndPoint.EnviarNotificacion(json);
            }

            //Notificar cambio de estado a la plataforma ccb
            json.put("cambio_estado_solicitud", true);
            json.put("tipo_usuario", "20");
            json.put("tipo_servicio", "30");
            SocketEndPoint.EnviarNotificacion(json);
            //notificar cambio de estado a crum
//            json.put("tipo_usuario", "19");
//            json.put("tipo_servicio", "29");
//            SocketEndPoint.EnviarNotificacion(json);
            //Notificar cambio de estado a la plataforma sucre
//            json.put("cambio_estado_solicitud", true);
//            json.put("tipo_usuario", "21");
//            json.put("tipo_servicio", "48");
//            SocketEndPoint.EnviarNotificacion(json);
        }
        return json;
    }

    //servicio cambio de estado desde ccb que recibe paciente
    @RequestMapping(value = "/API/recepcion_paciente", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject recepcion_paciente(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("recepcion_paciente en: " + Dependencia);
        JSONParser parser = new JSONParser();

        boolean procede = true;
        if (procede) {
            //cambiar estado a trasldo iniciado
            String query = null;

            //BUSCAMOS EL ESTADO ANTERIOR
            query = "SELECT estado FROM solicitudes where id ='" + json.get("id") + "';";
            JSONObject estado_anterior = Query.select(query);
            if (estado_anterior == null) {
                estado_anterior = new JSONObject();
                estado_anterior.put("estado", "NULL");
            }

            query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado_anterior`,`estado`,`nombre_institucion`) "
                    + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
                    + "'" + estado_anterior.get("estado") + "','Ingresado en UTC-19','" + json.get("nombre_institucion") + "');";
            Query.insert(query);

            /**/
            //actualizar estado 
            json.put("estado", "Ingresado en UTC-19");
            query = "UPDATE `solicitudes` SET `estado` = 'Ingresado en UTC-19', `fecha_cambio` = '" + json.get("fecha_cambio") + "' , `hora_cambio` = '" + json.get("hora_cambio") + "', "
                    + " `cama` = '" + json.get("cama") + "',`estatus_cama`='Ocupada'  WHERE id = '" + json.get("id") + "';";
            Query.update(query);

//            query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado`,`nombre_institucion`) "
//                    + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
//                    + "'Ingresado en UTC-19','" + json.get("nombre_institucion") + "');";
//            Query.insert(query);
            //quitar bandera de agregar en bandeja
            if (json.containsKey("bandeja_entrada")) {
                json.remove("bandeja_entrada");
            }

            //notificar institucion emisora
            query = "SELECT tipo_usuario, tipo_servicio FROM solicitudes WHERE id = '" + json.get("id") + "';";
            JSONObject resp = Query.select(query);
            if (resp != null) {
                json.put("cambio_estado_solicitud", true);
                json.put("tipo_usuario", resp.get("tipo_usuario"));
                json.put("tipo_servicio", resp.get("tipo_servicio"));
                SocketEndPoint.EnviarNotificacion(json);
            }

            //Notificar cambio de estado a la plataforma ccb
            json.put("cambio_estado_solicitud", true);
            json.put("tipo_usuario", "20");
            json.put("tipo_servicio", "30");
            SocketEndPoint.EnviarNotificacion(json);

            // Buscar en base de datos origen
            //notificar 
            /*
            json.put("cambio_estado_solicitud", true);
        json.put("tipo_usuario", query.get("tipo_tipousuario));
        json.put("tipo_servicio", query.get("tipo_servicio));
        SocketEndPoint.EnviarNotificacion(json);
             */
            //notificar cambio de estado a crum
            json.put("tipo_usuario", "19");
            json.put("tipo_servicio", "29");
            SocketEndPoint.EnviarNotificacion(json);
            //Notificar cambio de estado a la plataforma sucre
            json.put("cambio_estado_solicitud", true);
            json.put("tipo_usuario", "21");
            json.put("tipo_servicio", "48");
            SocketEndPoint.EnviarNotificacion(json);
            // Enviar Push a personal involucrado a controlador
            /*
         json a enviar
          - firebase:[] //array corresponden a personal ccb
          - data = {}*
          - title *
          - message *
             */
            //-----------------------
            //registrar personal involucrado
            JSONArray personal = (JSONArray) parser.parse(json.get("personal_ccb").toString());
            personal.add(json.get("doctor_responsable"));

            for (int i = 0; i < personal.size(); i++) {
                query = "INSERT INTO `involucrados` (`idUsuario`, `idSolicitud`, `fecha`, `hora`)"
                        + " VALUES ('" + personal.get(i) + "', '" + json.get("id") + "', '" + Escalamiento.getFecha() + "', '" + Escalamiento.getHora() + "'); ";
                Query.insert(query);
            }

            query = "SELECT FireBaseKey FROM usuarios_movil WHERE ";
            for (int i = 0; i < personal.size(); i++) {
                query += "idUsuarios_Movil = '" + personal.get(i) + "' || ";
            }
            query = query.substring(0, (query.length() - 4));
            query += ";";

            JSONObject pushNot = new JSONObject();
            JSONArray firebase = Query.execute(query);

            JSONArray firebasenot = new JSONArray();
            for (int i = 0; i < firebase.size(); i++) {
                JSONObject fb = (JSONObject) firebase.get(i);
                firebasenot.add(fb.get("FireBaseKey"));
            }

            pushNot.put("firebase", firebasenot);

            JSONObject data = new JSONObject();
            data.put("idSolicitud", json.get("id"));
            pushNot.put("data", data);
            pushNot.put("type", "500");

            //Falta ver a que url del controlador se va a mandar
            request.POST(config.getURL_CONTROLADOR() + "API/NotificacionCCB", pushNot);

        }
        return json;
    }

    //servicio para guardar los involucrados del ccb
    @RequestMapping(value = "/API/notifica_involucrados", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject notifica_involucrados(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("notifica_involucrados en: " + Dependencia);
        JSONParser parser = new JSONParser();
        String query = "";
        boolean procede = true;
        if (procede) {
            //registrar personal involucrado
            JSONArray personal = (JSONArray) parser.parse(json.get("personal_ccb").toString());

            for (int i = 0; i < personal.size(); i++) {
                query = "INSERT INTO `involucrados` (`idUsuario`, `idSolicitud`, `fecha`, `hora`)"
                        + " VALUES ('" + personal.get(i) + "', '" + json.get("id") + "', '" + Escalamiento.getFecha() + "', '" + Escalamiento.getHora() + "'); ";
                Query.insert(query);
            }
            query = "SELECT FireBaseKey FROM usuarios_movil WHERE ";
            for (int i = 0; i < personal.size(); i++) {
                query += "idUsuarios_Movil = '" + personal.get(i) + "' || ";
            }
            query = query.substring(0, (query.length() - 4));
            query += ";";

            JSONObject pushNot = new JSONObject();
            JSONArray firebase = Query.execute(query);
            JSONArray firebasenot = new JSONArray();
            for (int i = 0; i < firebase.size(); i++) {
                JSONObject fb = (JSONObject) firebase.get(i);
                firebasenot.add(fb.get("FireBaseKey"));
            }

            pushNot.put("firebase", firebasenot);

            JSONObject data = new JSONObject();
            data.put("idSolicitud", json.get("id"));
            pushNot.put("data", data);
            pushNot.put("type", "500");

            //Falta ver a que url del controlador se va a mandar
            request.POST(config.getURL_CONTROLADOR() + "API/NotificacionCCB", pushNot);

        }
        return json;
    }

    //servicio para notificar el cambio de estado de alta de paciente
    @RequestMapping(value = "/API/notificar_alta", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject notificar_alta(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("notificar_alta en: " + Dependencia);
        JSONParser parser = new JSONParser();
        String query = "";
        boolean procede = true;
        if (procede) {

            //BUSCAMOS EL ESTADO ANTERIOR
            query = "SELECT estado FROM solicitudes where id ='" + json.get("id") + "';";
            JSONObject estado_anterior = Query.select(query);
            if (estado_anterior == null) {
                estado_anterior = new JSONObject();
                estado_anterior.put("estado", "NULL");
            }

            query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado_anterior`,`estado`,`nombre_institucion`) "
                    + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
                    + "'" + estado_anterior.get("estado") + "','Dado de Alta por UTC-19','" + json.get("nombre_institucion") + "');";
            Query.insert(query);
            /**/
            json.put("codigo_alta", generateString().substring(0, 8));
            json.put("estado", "Dado de Alta por UTC-19");
            query = "UPDATE `solicitudes` SET `estado` = 'Dado de Alta por UTC-19', `fecha_cambio` = '" + json.get("fecha_cambio") + "' , "
                    + "`hora_cambio` = '" + json.get("hora_cambio") + "', `telefono_paciente`='" + json.get("telefono_paciente") + "',"
                    + "`codigo_alta` = '" + json.get("codigo_alta") + "' WHERE id = '" + json.get("id") + "';";
            Query.update(query);

//            query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado`,`nombre_institucion`) "
//                    + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
//                    + "'Dado de Alta por UTC-19','" + json.get("nombre_institucion") + "');";
//            Query.insert(query);
            //quitar bandera de agregar en bandeja
            if (json.containsKey("bandeja_entrada")) {
                json.remove("bandeja_entrada");
            }

            //notificar institucion emisora
            query = "SELECT tipo_usuario, tipo_servicio FROM solicitudes WHERE id = '" + json.get("id") + "';";
            JSONObject resp = Query.select(query);
            if (resp != null) {
                json.put("cambio_estado_solicitud", true);
                json.put("tipo_usuario", resp.get("tipo_usuario"));
                json.put("tipo_servicio", resp.get("tipo_servicio"));
                SocketEndPoint.EnviarNotificacion(json);
            }

            //Notificar cambio de estado a la plataforma ccb
            json.put("cambio_estado_solicitud", true);
            json.put("tipo_usuario", "20");
            json.put("tipo_servicio", "30");
            SocketEndPoint.EnviarNotificacion(json);

            //notificamos a crum 
            json.put("tipo_usuario", "19");
            json.put("tipo_servicio", "29");
            SocketEndPoint.EnviarNotificacion(json);
            //Notificar cambio de estado a la plataforma sucre
            json.put("cambio_estado_solicitud", true);
            json.put("tipo_usuario", "21");
            json.put("tipo_servicio", "48");
            SocketEndPoint.EnviarNotificacion(json);

            ///Agendar las 10 llamadas de monitoreo remoto 
            //llamada de 24 hrs
            int dias = 1;
            query = "INSERT INTO ccb_monitoreo_remoto (`folio`,`fecha`,`hora`,`estatus_llamada`,`idOperador`,`fecha_llamada`,`numero_llamada`) "
                    + "VALUES ('" + json.get("id") + "','" + Modelo.Escalamiento.getFecha() + "', '" + Modelo.Escalamiento.getHora() + "',"
                    + "'Programada','" + json.get("idOperador") + "','" + Escalamiento.getFecha(dias) + "','1');";
            Query.insert(query);
            //3 llamadas cada 48 hrs
            for (int i = 0; i < 3; i++) {
                dias += 2;
                query = "INSERT INTO ccb_monitoreo_remoto (`folio`,`fecha`,`hora`,`estatus_llamada`,`idOperador`,`fecha_llamada`,`numero_llamada`) "
                        + "VALUES ('" + json.get("id") + "','" + Modelo.Escalamiento.getFecha() + "', '" + Modelo.Escalamiento.getHora() + "',"
                        + "'Programada','" + json.get("idOperador") + "','" + Escalamiento.getFecha(dias) + "','" + (i + 2) + "');";
                Query.insert(query);
            }
            //6 llamadas cada 7 dias 
            for (int i = 0; i < 6; i++) {
                dias += 7;
                query = "INSERT INTO ccb_monitoreo_remoto (`folio`,`fecha`,`hora`,`estatus_llamada`,`idOperador`,`fecha_llamada`,`numero_llamada`) "
                        + "VALUES ('" + json.get("id") + "','" + Modelo.Escalamiento.getFecha() + "', '" + Modelo.Escalamiento.getHora() + "',"
                        + "'Programada','" + json.get("idOperador") + "','" + Escalamiento.getFecha(dias) + "','" + (i + 5) + "');";
                Query.insert(query);
            }
        }
        return json;
    }

    //servicio para notificar el cambio de estado de regreso de paciente
    @RequestMapping(value = "/API/notificar_regreso", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject notificar_regreso(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("notificar_regreso en: " + Dependencia);
        JSONParser parser = new JSONParser();
        String query = "";
        boolean procede = true;
        if (procede) {

            //BUSCAMOS EL ESTADO ANTERIOR
            query = "SELECT estado FROM solicitudes where id ='" + json.get("id") + "';";
            JSONObject estado_anterior = Query.select(query);
            if (estado_anterior == null) {
                estado_anterior = new JSONObject();
                estado_anterior.put("estado", "NULL");
            }

            query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado_anterior`,`estado`,`nombre_institucion`) "
                    + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
                    + "'" + estado_anterior.get("estado") + "','Contrarreferencia','" + json.get("nombre_institucion") + "');";
            Query.insert(query);

            json.put("estado", "Contrarreferencia");
            query = "UPDATE `solicitudes` SET `estado` = 'Contrarreferencia', `fecha_cambio` = '" + json.get("fecha_cambio") + "' , `hora_cambio` = '" + json.get("hora_cambio") + "' WHERE id = '" + json.get("id") + "';";
            Query.update(query);

//            query = "INSERT INTO cambios_estado (`idSolicitud`,`fecha_cambio`,`hora_cambio`,`estado`,`nombre_institucion`) "
//                    + "VALUES('" + json.get("id") + "','" + json.get("fecha_cambio") + "','" + json.get("hora_cambio") + "',"
//                    + "'Contrarreferencia','" + json.get("nombre_institucion") + "');";
//            Query.insert(query);
            //quitar bandera de agregar en bandeja
            if (json.containsKey("bandeja_entrada")) {
                json.remove("bandeja_entrada");
            }

            //notificar institucion emisora
            query = "SELECT tipo_usuario, tipo_servicio FROM solicitudes WHERE id = '" + json.get("id") + "';";
            JSONObject resp = Query.select(query);
            if (resp != null) {
                json.put("cambio_estado_solicitud", true);
                json.put("tipo_usuario", resp.get("tipo_usuario"));
                json.put("tipo_servicio", resp.get("tipo_servicio"));
                SocketEndPoint.EnviarNotificacion(json);
            }

            //Notificar cambio de estado a la plataforma ccb
            json.put("cambio_estado_solicitud", true);
            json.put("tipo_usuario", "20");
            json.put("tipo_servicio", "30");
            SocketEndPoint.EnviarNotificacion(json);

            //notificamos a crum 
            json.put("tipo_usuario", "19");
            json.put("tipo_servicio", "29");
            SocketEndPoint.EnviarNotificacion(json);
            //Notificar cambio de estado a la plataforma sucre
            json.put("cambio_estado_solicitud", true);
            json.put("tipo_usuario", "21");
            json.put("tipo_servicio", "48");
            SocketEndPoint.EnviarNotificacion(json);
        }
        return json;
    }

    //servicio recepcion de test personal de salud
    @RequestMapping(value = "/API/UTC19/COVID", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject utc19covid(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("utc19covid en: " + Dependencia);
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json.toString());
        JSONObject gravedad = (JSONObject) jsonObj.get("gravedad");
        JSONObject exposicion = (JSONObject) jsonObj.get("exposicion");

        jsonObj.put("gravedad", gravedad.get("activo"));
        jsonObj.put("condiciones_medicas", gravedad.get("condiciones_medicas"));
        jsonObj.put("embarazo", gravedad.get("embarazo"));
        jsonObj.put("dolor_respirar", gravedad.get("dolor_respirar"));
        jsonObj.put("falta_aire", gravedad.get("falta_aire"));
        jsonObj.put("coloracion_azul", gravedad.get("coloracion_azul"));

        jsonObj.put("exposicion", exposicion.get("activo"));
        jsonObj.put("convivido_sin_equipo", exposicion.get("convivido_sin_equipo"));
        jsonObj.put("contaminado_retiro", exposicion.get("contaminado_retiro"));

        String query = " INSERT INTO `personal_ccb` "
                + "(`idUsuario`, `lat`, `lng`, `fecha`, `hora`, `edad`, `genero`, `codigo_postal`, `fiebre`, `tos`, `dolor_cabeza`, `dolor_pecho`, `dolor_garganta`, `dificultad_respirar`, `dolor_general`, `escalofrios`, `gravedad`, `condiciones_medicas`, `embarazo`, `dolor_respirar`, `falta_aire`, `coloracion_azul`, `exposicion`, `convivido_sin_equipo`, `contaminado_retiro`, `score`) "
                + "VALUES ( '" + jsonObj.get("idUsuario") + "',  '" + jsonObj.get("lat") + "',  '" + jsonObj.get("lng") + "',  '" + jsonObj.get("fecha") + "',  '" + jsonObj.get("hora") + "',  '" + jsonObj.get("edad") + "',  '" + jsonObj.get("genero") + "',  '" + jsonObj.get("codigo_postal") + "',  '" + jsonObj.get("fiebre") + "',  '" + jsonObj.get("tos") + "',  '" + jsonObj.get("dolor_cabeza") + "',  '" + jsonObj.get("dolor_pecho") + "',  '" + jsonObj.get("dolor_garganta") + "',  '" + jsonObj.get("dificultad_respirar") + "',  '" + jsonObj.get("dolor_general") + "',  '" + jsonObj.get("escalofrios") + "',  '" + jsonObj.get("gravedad") + "',  '" + jsonObj.get("condiciones_medicas") + "',  '" + jsonObj.get("embarazo") + "',  '" + jsonObj.get("dolor_respirar") + "',  '" + jsonObj.get("falta_aire") + "',  '" + jsonObj.get("coloracion_azul") + "',  '" + jsonObj.get("exposicion") + "',  '" + jsonObj.get("convivido_sin_equipo") + "',  '" + jsonObj.get("contaminado_retiro") + "',  '" + jsonObj.get("score") + "');";
//        String query = " INSERT INTO `personal_ccb` "
//                + "(`idUsuario`, `lat`, `lng`, `fecha`, `hora`, `edad`, `genero`, `codigo_postal`, `fiebre`, "
//                + "`tos`, `dolor_cabeza`, `dolor_pecho`, `dolor_garganta`, `dificultad_respirar`, `dolor_general`, "
//                + "`escalofrios`, `gravedad`, `condiciones_medicas`, `embarazo`, `dolor_respirar`, `falta_aire`, "
//                + "`coloracion_azul`, `exposicion`, `convivido_sin_equipo`, `contaminado_retiro`, `score`,"
//                + "`llamada`,`mensaje`,`nivel`) "
//                + "VALUES ( '" + jsonObj.get("idUsuario") + "',  '" + jsonObj.get("lat") + "',  '" + jsonObj.get("lng") + "',  "
//                + "'" + jsonObj.get("fecha") + "',  '" + jsonObj.get("hora") + "',  '" + jsonObj.get("edad") + "',  '" + jsonObj.get("genero") + "',  "
//                + "'" + jsonObj.get("codigo_postal") + "',  '" + jsonObj.get("fiebre") + "',  '" + jsonObj.get("tos") + "',  '" + jsonObj.get("dolor_cabeza") + "',  "
//                + "'" + jsonObj.get("dolor_pecho") + "',  '" + jsonObj.get("dolor_garganta") + "',  '" + jsonObj.get("dificultad_respirar") + "',  "
//                + "'" + jsonObj.get("dolor_general") + "',  '" + jsonObj.get("escalofrios") + "',  '" + jsonObj.get("gravedad") + "',  "
//                + "'" + jsonObj.get("condiciones_medicas") + "',  '" + jsonObj.get("embarazo") + "',  '" + jsonObj.get("dolor_respirar") + "',  "
//                + "'" + jsonObj.get("falta_aire") + "',  '" + jsonObj.get("coloracion_azul") + "',  '" + jsonObj.get("exposicion") + "',  "
//                + "'" + jsonObj.get("convivido_sin_equipo") + "',  '" + jsonObj.get("contaminado_retiro") + "',  '" + jsonObj.get("score") + "',"
//                + "'"+jsonObj.get("llamada")+"','"+jsonObj.get("mensaje")+"','"+jsonObj.get("nivel")+"');";

        int id = Query.insert(query);

        query = "SELECT nombre, apellido_paterno , apellido_materno,fecha_nacimiento FROM usuarios_movil "
                + "WHERE idUsuarios_Movil = '" + jsonObj.get("idUsuario") + "';";
        JSONObject perfil = Query.select(query);
        perfil.put("img", config.getPATH() + Dependencia + "/API/ConsultarImg/perfil/" + jsonObj.get("idUsuario"));
        perfil.put("icon", config.getPATH() + Dependencia + "/API/ConsultarImg/icono/" + jsonObj.get("idUsuario"));

        jsonObj.put("Perfil", perfil);

        jsonObj.put("utc19covid", true);
        jsonObj.put("tipo_usuario", "20");
        //jsonObj.put("tipo_servicio", "46");
        jsonObj.put("tipo_servicio", "30");
        SocketEndPoint.EnviarNotificacion(jsonObj);

        JSONObject respuesta = new JSONObject();
        respuesta.put("id", id);

        return respuesta;

    }

    //servicio recepcion de test pacientes dados de alta
    @RequestMapping(value = "/API/UTC19/pacientes/COVID", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject utc19covidPacientes(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("utc19covidPacientes: " + Dependencia);
        //System.out.println(json);

        JSONObject respuesta = new JSONObject();
        respuesta.put("success", false);
        respuesta.put("error", true);
        if (!json.containsKey("lat")) {
            json.put("lat", 0);
        }
        if (!json.containsKey("lng")) {
            json.put("lng", 0);
        }
        String query = "INSERT INTO `ccb_test_pacientes` ("
                + "`idUsuario`, "
                + "`edad`, "
                + "`genero`, "
                + "`folio`, "
                + "`fecha`, "
                + "`hora`, "
                + "`fiebre`, "
                + "`check_temperatura`, "
                + "`resultado_temperatura`, "
                + "`check_oxigeno`, "
                + "`resultado_oxigeno`, "
                + "`dificultad_respirar`, "
                + "`chifla_respirar`, "
                + "`frecuencia_respirar`, "
                + "`desorientacion`, "
                + "`sangrado`, "
                + "`llamar`, "
                + "`lat`, "
                + "`lng`) "
                + "VALUES ('" + json.get("idUsuario") + "', '" + json.get("edad") + "', '" + json.get("genero") + "', '" + json.get("folio") + "', '" + json.get("fecha") + "', '" + json.get("hora") + "', '" + json.get("fiebre") + "', "
                + "'" + json.get("check_temperatura") + "', '" + json.get("resultado_temperatura") + "', '" + json.get("check_oxigeno") + "', '" + json.get("resultado_oxigeno") + "', '" + json.get("dificultad_respirar") + "', "
                + "'" + json.get("chifla_respirar") + "', '" + json.get("frecuencia_respirar") + "', '" + json.get("desorientacion") + "', '" + json.get("sangrado") + "', '" + json.get("llamar") + "', '" + json.get("lat") + "', '" + json.get("lng") + "');";

        int id = Query.insert(query);
        if (id > 0) {
            JSONParser parser = new JSONParser();
            respuesta.put("success", true);
            respuesta.put("error", false);
            respuesta.put("id", id);

            JSONArray array = new JSONArray();
            array.add(json.get("idUsuario"));
            JSONArray usuario = (JSONArray) parser.parse(request.POST(config.getURL_CONTROLADOR() + "API/ConsultaVariosPerfiles", array.toString()));

            json.put("Perfil", usuario.get(0));

            json.put("pacientes_testcovid", true);
            json.put("tipo_usuario", "20");
            //jsonObj.put("tipo_servicio", "46");
            json.put("tipo_servicio", "30");

            SocketEndPoint.EnviarNotificacion(json);
        }

        return respuesta;

    }

    //************** Vinculacion Familiar ***************************//
    @RequestMapping(value = "/API/vinculacionfamiliar/directorio", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONArray vinculacionfamiliarDirectorio(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("vinculacionfamiliar directorio en: " + Dependencia);
        JSONArray directorio = Query.execute("SELECT * FROM vinculacion_familiar where folio = '" + json.get("folio") + "' AND activo='1';");
        return directorio;
    }

    @RequestMapping(value = "/API/vinculacionfamiliar", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject vinculacionfamiliar(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("vinculacionfamiliar en: " + Dependencia);
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", false);
        respuesta.put("error", true);
        String query = "SELECT nombre,apellidop_paciente,apellidom_paciente from solicitudes WHERE codigo='" + json.get("codigo") + "' AND id='" + json.get("folio") + "';";
        JSONObject paciente = Query.select(query);
        if (paciente != null) {
            query = "UPDATE vinculacion_familiar SET activo = 0 WHERE idUsuario='" + json.get("idUsuario") + "' AND folio='" + json.get("folio") + "'";
            Query.update(query);
            query = "INSERT INTO `vinculacion_familiar` (`idUsuario`, `folio`, `nombre`, `apellido_paterno`, `apellido_materno`, `fecha`, `hora`) "
                    + "VALUES ('" + json.get("idUsuario") + "', '" + json.get("folio") + "', '" + json.get("nombre") + "', '" + json.get("apellido_paterno") + "', '" + json.get("apellido_materno") + "', '" + Modelo.Escalamiento.getFecha() + "', '" + Modelo.Escalamiento.getHora() + "');";
            if (Query.insert(query) > 0) {
                respuesta.put("success", true);
                respuesta.put("error", false);
                respuesta.put("nombre", paciente.get("nombre"));
                respuesta.put("apellido_materno", paciente.get("apellidop_paciente"));
                respuesta.put("apellido_paterno", paciente.get("apellidom_paciente"));
                //Notificar la vinculacion ...
                json.put("nombre", paciente.get("nombre"));
                json.put("apellido_paterno", paciente.get("apellidop_paciente"));
                json.put("apellido_materno", paciente.get("apellidom_paciente"));
                json.put("tittle", "CLARO360 Vinculación realizada.");
                json.put("message", "Ahora recibirás información acerca de: " + json.get("nombre") + " " + json.get("apellido_paterno") + " " + json.get("apellido_materno"));
                System.out.println(request.POST(config.getURL_CONTROLADOR() + "API/NotificarVinculacion", json.toString()));
            }
        }

        return respuesta;
    }

    @RequestMapping(value = "/API/vinculacionfamiliar/actualizacion_estado", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject vinculacionfamiliarActualizacion_Estado(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("vinculacionfamiliar actualizacion_estado en: " + Dependencia);
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", false);
        respuesta.put("error", true);
        String query = "INSERT INTO `ccb_evolucion` (`folio`, `estado`, `diagnostico`, `personal`, `fecha`, `hora`) "
                + "VALUES ('" + json.get("folio") + "', '" + json.get("estado") + "', '" + json.get("diagnostico") + "', '" + json.get("personal") + "', '" + Modelo.Escalamiento.getFecha() + "', '" + Modelo.Escalamiento.getHora() + "');";
        if (Query.insert(query) > 0) {
            respuesta.put("success", true);
            respuesta.put("error", false);
            //Notificar la actualizacion ...
            query = "SELECT nombre,apellidop_paciente,apellidom_paciente from solicitudes WHERE id='" + json.get("folio") + "'";
            JSONObject paciente = Query.select(query);
            json.put("nombre", paciente.get("nombre"));
            json.put("apellido_paterno", paciente.get("apellidop_paciente"));
            json.put("apellido_materno", paciente.get("apellidom_paciente"));
            json.put("tittle", "CLARO360 Actualización de estado.");
            json.put("message", "Hay nueva información acerca de: " + json.get("nombre") + " " + json.get("apellido_paterno") + " " + json.get("apellido_materno"));
            JSONArray directorio = Query.execute("SELECT idUsuario FROM vinculacion_familiar where folio = '" + json.get("folio") + "' AND activo='1';");
            json.put("idUsuarios", directorio);
            System.out.println(request.POST(config.getURL_CONTROLADOR() + "API/NotificarVinculaciones", json.toString()));
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/vinculacionfamiliar/registro_llamada", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject registro_llamada(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("vinculacionfamiliar registro_llamada en: " + Dependencia);
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", false);
        respuesta.put("error", true);
        String query = "INSERT INTO `ccb_llamadas` (`idUsuario`, `folio`, `fecha`, `hora`, `estado`, `url`) "
                + "VALUES ('" + json.get("idUsuario") + "', '" + json.get("folio") + "', '" + Modelo.Escalamiento.getFecha() + "', '" + Modelo.Escalamiento.getHora() + "', '" + json.get("estado") + "', '" + json.get("url") + "');";
        if (Query.insert(query) > 0) {
            respuesta.put("success", true);
            respuesta.put("error", false);
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/vinculacionfamiliar/listado", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONArray vinculacionfamiliar_listado(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("vinculacionfamiliar listado en: " + Dependencia);

        String query = "SELECT folio from vinculacion_familiar where activo =1 AND idUsuario='" + json.get("idUsuario") + "';";
        JSONArray folios = Query.execute(query);
        JSONArray listado = new JSONArray();
        for (int i = 0; i < folios.size(); i++) {
            query = "SELECT id AS folio_vinculacion,nombre AS nombre_paciente, apellidop_paciente, apellidom_paciente  "
                    + "FROM solicitudes WHERE id = '" + ((JSONObject) folios.get(i)).get("folio") + "';";
            JSONObject folio = Query.select(query);
            if (folio != null) {
                folio.put("reportes", Query.execute("select id AS folio_reporte,personal AS docto_emisor,estado,fecha,hora from ccb_evolucion where folio='" + ((JSONObject) folios.get(i)).get("folio") + "';"));
                folio.put("llamadas", Query.execute("select id AS folio_llamada,personal AS docto_emisor,estado,fecha,hora from ccb_llamadas where folio=" + ((JSONObject) folios.get(i)).get("folio") + " AND idUsuario='" + json.get("idUsuario") + "';"));
                listado.add(folio);
            }
        }
        return listado;
    }

    @RequestMapping(value = "/API/vinculacionfamiliar/inforeporte", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject vinculacionfamiliar_inforeporte(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("vinculacionfamiliar inforeporte en: " + Dependencia);
        String query = "select * from ccb_evolucion where id = " + json.get("folio_reporte") + ";";
        return Query.select(query);
    }

    @RequestMapping(value = "/API/vinculacionfamiliar/infoSolicitud", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject vinculacionfamiliar_infoSolicitud(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("vinculacionfamiliar infoSolicitud en: " + Dependencia);
        String query = "select * from solicitudes where id = " + json.get("folio") + ";";
        return Query.select(query);
    }

    @RequestMapping(value = "/API/vinculacionfamiliar/notificar_llamada", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject vinculacionfamiliar_notificar_llamada(@RequestBody String string) throws IOException, ParseException {
        System.out.println("vinculacionfamiliar notificar_llamada en: " + Dependencia);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(string);
        json.put("fecha", Modelo.Escalamiento.getFecha());
        json.put("hora", Modelo.Escalamiento.getHora());
        //System.out.println(json);
        json.put("estado", "Solicitud de llamada");
        String query_url = config.getURL_CONTROLADOR() + "restOpentok/ArchiveSession";
        JSONObject OpenTok = new JSONObject();
        OpenTok.put("apikey", json.get("apikey"));
        OpenTok.put("token", json.get("token"));
        OpenTok.put("sesion", json.get("idsesion"));
        String url = Request.request.POST(query_url, OpenTok.toString());
        json.put("url", url);
        JSONArray idUsuarios = (JSONArray) json.get("idUsuarios");
        JSONArray idNotificaciones = new JSONArray();
        for (int i = 0; i < idUsuarios.size(); i++) {
            //registrar llamada
            String query = "INSERT INTO `ccb_llamadas` (`idUsuario`, `folio`, `fecha`, `hora`, `estado`, `url`, `personal`) "
                    + "VALUES ('" + idUsuarios.get(i) + "', '" + json.get("folio") + "', '" + json.get("fecha") + "', '" + json.get("hora") + "', '" + json.get("estado") + "', '" + json.get("url") + "', '" + json.get("personal") + "');";

            json.put("idUsuario", idUsuarios.get(i));
            idNotificaciones.add(Query.insert(query));

        }
        json.put("idNotificaciones", idNotificaciones);

        JSONObject notificar_llamada = request.POST(config.getURL_CONTROLADOR() + "API/NotificarLlamada", json);
        return notificar_llamada;
    }

    @RequestMapping(value = "/API/actualizacion_monitoreoRemoto", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject actualizacion_monitoreoRemoto(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("actualizacion_monitoreoRemoto: " + Dependencia);
        String query = "SELECT * FROM ccb_monitoreo_remoto "
                + "WHERE id = " + json.get("folio") + " AND `numero_llamada` = '" + json.get("numero_llamada") + "' AND activo=1;";
        JSONObject info = Query.select(query);

        query = "UPDATE `ccb_monitoreo_remoto` SET `activo` = '0' where id = " + json.get("folio") + " AND `numero_llamada` = '" + json.get("numero_llamada") + "';";
        Query.update(query);

        query = "INSERT INTO `ccb_monitoreo_remoto` "
                + "(`folio`, `fecha`, `hora`, `estatus_llamada`, `idOperador`, `fecha_llamada`, `hora_llamada`, "
                + "`idOperador_llamada`, `numero_llamada`,`fecha_llamada_realizada`) "
                + "VALUES ('" + info.get("folio") + "', '" + info.get("fecha") + "', '" + info.get("hora") + "', '" + json.get("estatus_llamada") + "', "
                + "'" + info.get("idOperador") + "', '" + info.get("fecha_llamada") + "', '" + Escalamiento.getHora() + "', "
                + "'" + json.get("idOperador_llamada") + "', '" + info.get("numero_llamada") + "','" + Escalamiento.getFecha() + "');";
        int id = Query.insert(query);

        JSONObject response = new JSONObject();
        response.put("id", id);
        return response;
    }

    @RequestMapping(value = "/API/registrarDesconexionpacientesalta", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject registrarDesconexionpacientesalta(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("registrarDesconexionpacientesalta: " + Dependencia);
        String query = "UPDATE ccb_monitoreo_remoto SET `hora_finalizo_llamada`='" + Escalamiento.getHora() + "', WHERE id = '" + json.get("id") + "';";
        json.put("actualizado", Query.update(query));
        return json;

    }

    //************** Vinculacion Paciente dado de alta ***************************//
//    @RequestMapping(value = "/API/vinculacionpaciente/directorio", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
//    @ResponseBody
//    public static JSONArray vinculacionpacienteDirectorio(@RequestBody JSONObject json) throws IOException, ParseException {
//        System.out.println("vinculacionpaciente directorio en: " + Dependencia);
//        JSONArray directorio = Query.execute("SELECT * FROM vinculacion_familiar where folio = '" + json.get("folio") + "' AND activo='1';");
//        return directorio;
//    }
    @RequestMapping(value = "/API/vinculacionpaciente", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject vinculacionpaciente(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("vinculacionpaciente en: " + Dependencia);
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", false);
        respuesta.put("error", true);
        String query = "SELECT nombre,apellidop_paciente,apellidom_paciente,fecha_cambio from solicitudes WHERE codigo_alta='" + json.get("codigo_alta") + "' AND id='" + json.get("folio") + "';";
        //System.out.println(query);
        JSONObject paciente = Query.select(query);
        if (paciente != null) {
            query = "UPDATE vinculacion_paciente SET activo = 0 WHERE folio='" + json.get("folio") + "';";
            Query.update(query);
            query = "INSERT INTO `vinculacion_paciente` (`idUsuario`, `folio`, `fecha`, `hora`) "
                    + "VALUES ('" + json.get("idUsuario") + "', '" + json.get("folio") + "', '" + Modelo.Escalamiento.getFecha() + "', '" + Modelo.Escalamiento.getHora() + "');";
            if (Query.insert(query) > 0) {
                respuesta.put("success", true);
                respuesta.put("error", false);
                respuesta.put("nombre", paciente.get("nombre"));
                respuesta.put("apellido_materno", paciente.get("apellidop_paciente"));
                respuesta.put("apellido_paterno", paciente.get("apellidom_paciente"));
                respuesta.put("fecha_alta", paciente.get("fecha_cambio"));
                //Notificar la vinculacion ...
                json.put("nombre", paciente.get("nombre"));
                json.put("apellido_paterno", paciente.get("apellidop_paciente"));
                json.put("apellido_materno", paciente.get("apellidom_paciente"));
                json.put("tittle", "CLARO360 Vinculación realizada.");
                json.put("message", "" + json.get("nombre") + " " + json.get("apellido_paterno") + " " + json.get("apellido_materno") + ", ahora comenzará el monitoreo remoto sobre tu estado de salud");
                System.out.println(request.POST(config.getURL_CONTROLADOR() + "API/NotificarVinculacion", json.toString()));
            }
        }

        return respuesta;
    }

    @RequestMapping(value = "/API/vinculacionpaciente/actualizacion_estado", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject vinculacionpacienteActualizacion_Estado(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("vinculacionpaciente actualizacion_estado en: " + Dependencia);
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", false);
        respuesta.put("error", true);
        String query = "INSERT INTO `ccb_evolucion` (`folio`, `estado`, `diagnostico`, `personal`, `fecha`, `hora`) "
                + "VALUES ('" + json.get("folio") + "', '" + json.get("estado") + "', '" + json.get("diagnostico") + "', '" + json.get("personal") + "', '" + Modelo.Escalamiento.getFecha() + "', '" + Modelo.Escalamiento.getHora() + "');";
        if (Query.insert(query) > 0) {
            respuesta.put("success", true);
            respuesta.put("error", false);
            //Notificar la actualizacion ...
            query = "SELECT nombre,apellidop_paciente,apellidom_paciente from solicitudes WHERE id='" + json.get("folio") + "'";
            JSONObject paciente = Query.select(query);
            json.put("nombre", paciente.get("nombre"));
            json.put("apellido_paterno", paciente.get("apellidop_paciente"));
            json.put("apellido_materno", paciente.get("apellidom_paciente"));
            json.put("tittle", "CLARO360 Actualización de estado.");
            json.put("message", "Hay nueva información acerca de: " + json.get("nombre") + " " + json.get("apellido_paterno") + " " + json.get("apellido_materno"));
            JSONArray directorio = Query.execute("SELECT idUsuario FROM vinculacion_familiar where folio = '" + json.get("folio") + "' AND activo='1';");
            json.put("idUsuarios", directorio);
            System.out.println(request.POST(config.getURL_CONTROLADOR() + "API/NotificarVinculaciones", json.toString()));
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/vinculacionpaciente/registro_llamada", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject vinculacionpaciente_registro_llamada(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("vinculacionpaciente registro_llamada en: " + Dependencia);
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", false);
        respuesta.put("error", true);
        String query = "INSERT INTO `ccb_llamadas` (`idUsuario`, `folio`, `fecha`, `hora`, `estado`, `url`) "
                + "VALUES ('" + json.get("idUsuario") + "', '" + json.get("folio") + "', '" + Modelo.Escalamiento.getFecha() + "', '" + Modelo.Escalamiento.getHora() + "', '" + json.get("estado") + "', '" + json.get("url") + "');";
        if (Query.insert(query) > 0) {
            respuesta.put("success", true);
            respuesta.put("error", false);
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/vinculacionpaciente/listado", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONArray vinculacionpaciente_listado(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("vinculacionpaciente listado en: " + Dependencia);

        String query = "SELECT folio from vinculacion_familiar where activo =1 AND idUsuario='" + json.get("idUsuario") + "';";
        JSONArray folios = Query.execute(query);
        JSONArray listado = new JSONArray();
        for (int i = 0; i < folios.size(); i++) {
            query = "SELECT id AS folio_vinculacion,nombre AS nombre_paciente, apellidop_paciente, apellidom_paciente  "
                    + "FROM solicitudes WHERE id = '" + ((JSONObject) folios.get(i)).get("folio") + "';";
            JSONObject folio = Query.select(query);
            if (folio != null) {
                folio.put("reportes", Query.execute("select id AS folio_reporte,personal AS docto_emisor,estado,fecha,hora from ccb_evolucion where folio='" + ((JSONObject) folios.get(i)).get("folio") + "';"));
                folio.put("llamadas", Query.execute("select id AS folio_llamada,personal AS docto_emisor,estado,fecha,hora from ccb_llamadas where folio=" + ((JSONObject) folios.get(i)).get("folio") + " AND idUsuario='" + json.get("idUsuario") + "';"));
                listado.add(folio);
            }
        }
        return listado;
    }

    @RequestMapping(value = "/API/vinculacionpaciente/inforeporte", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject vinculacionpaciente_inforeporte(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("vinculacionpaciente inforeporte en: " + Dependencia);
        String query = "select * from ccb_evolucion where id = " + json.get("folio_reporte") + ";";
        return Query.select(query);
    }

    @RequestMapping(value = "/API/vinculacionpaciente/infoSolicitud", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject vinculacionpaciente_infoSolicitud(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("vinculacionpaciente infoSolicitud en: " + Dependencia);
        String query = "select * from solicitudes where id = " + json.get("folio") + ";";
        return Query.select(query);
    }

    @RequestMapping(value = "/API/vinculacionpaciente/notificar_llamada", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject vinculacionpaciente_notificar_llamada(@RequestBody String string) throws IOException, ParseException {
        System.out.println("vinculacionpaciente notificar_llamada en: " + Dependencia);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(string);
        json.put("fecha", Modelo.Escalamiento.getFecha());
        json.put("hora", Modelo.Escalamiento.getHora());
        System.out.println(json);
        json.put("estado", "Solicitud de llamada");
        String query_url = config.getURL_CONTROLADOR() + "restOpentok/ArchiveSession";
        JSONObject OpenTok = new JSONObject();
        OpenTok.put("apikey", json.get("apikey"));
        OpenTok.put("token", json.get("token"));
        OpenTok.put("sesion", json.get("idsesion"));
        String url = Request.request.POST(query_url, OpenTok.toString());
        json.put("url", url);
        JSONArray idUsuarios = (JSONArray) json.get("idUsuarios");
        JSONArray idNotificaciones = new JSONArray();
        for (int i = 0; i < idUsuarios.size(); i++) {
            //registrar llamada
            String query = null;
            if (json.containsKey("folio")) {
                query = "INSERT INTO `ccb_llamadas` (`idUsuario`, `folio`, `fecha`, `hora`, `estado`, `url`, `personal`,`paciente_alta`) "
                        + "VALUES ('" + idUsuarios.get(i) + "', '" + json.get("folio") + "', '" + json.get("fecha") + "', '" + json.get("hora") + "', '" + json.get("estado") + "', '" + json.get("url") + "', '" + json.get("personal") + "','1');";
            } else {
                query = "INSERT INTO `ccb_llamadas` (`idUsuario`, `folio`, `fecha`, `hora`, `estado`, `url`, `personal`,`paciente_alta`) "
                        + "VALUES ('" + idUsuarios.get(i) + "', NULL, '" + json.get("fecha") + "', '" + json.get("hora") + "', '" + json.get("estado") + "', '" + json.get("url") + "', '" + json.get("personal") + "','1');";
            }

            json.put("idUsuario", idUsuarios.get(i));
            idNotificaciones.add(Query.insert(query));

        }
        json.put("idNotificaciones", idNotificaciones);

        JSONObject notificar_llamada = request.POST(config.getURL_CONTROLADOR() + "API/NotificarLlamada", json);
        return notificar_llamada;
    }

    /**
     * ******************************************************************************************
     */
    @RequestMapping(value = "/API/actualizacion_pacientesTestCOVID", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject actualizacion_pacientesTestCOVID(@RequestBody JSONObject json) throws ParseException {
        System.out.println("actualizacion_pacientesTestCOVID: " + Dependencia);
        JSONObject respuesta = new JSONObject();
        String estado_llamada = "Llamada Realizada";
        String query = "UPDATE `ccb_test_pacientes` SET `fecha_llamada` = '" + Escalamiento.getFecha() + "', "
                + "`hora_llamada` = '" + Escalamiento.getHora() + "', `estado_llamada` = '" + estado_llamada + "'"
                + " WHERE id = '" + json.get("idCard") + "';";

        respuesta.put("procede", Query.update(query));
        return respuesta;
    }

    @RequestMapping(value = "/API/diagnostico_ccb", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject diagnostico_ccb(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("diagnostico_personal_ccb: " + Dependencia);
        String query = "UPDATE `ccb_test_pacientes` SET diagnostico = '" + json.get("diagnostico") + "' WHERE id = '" + json.get("id") + "';";
        JSONObject res = new JSONObject();
        res.put("procede", Query.update(query));
        if (Boolean.parseBoolean(res.get("procede").toString())) {
            if (!json.get("fecha_nuevaLlamada").toString().equals("NULL")) {
                query = "INSERT INTO `agenda_nuevas_llamada` (`idUsuario`,`nombre`,`fecha`,`hora`,`fecha_nuevaLlamada`,`rol_usuario`) "
                        + "VALUES('" + json.get("idUsuario") + "','" + json.get("nombre") + "','" + Escalamiento.getFecha() + "',"
                        + "'" + Escalamiento.getHora() + "','" + json.get("fecha_nuevaLlamada") + "','" + json.get("rol_usuario") + "');";
                Query.insert(query);
            }
//            query = "UPDATE `usuarios_movil` SET acceso_ccb = '" + json.get("acceso_ccb") + "' WHERE idUsuarios_Movil = '" + json.get("idUsuario") + "';";
//            Query.update(query);
        }

        return res;
    }

    @RequestMapping(value = "/API/actualizacion_estadoLlamada_personalCCB", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject actualizacion_estadoLlamada_personalCCB(@RequestBody JSONObject json) throws ParseException {
        System.out.println("actualizacion_estadoLlamada_personalCCB: " + Dependencia);
        JSONObject respuesta = new JSONObject();
        String estado_llamada = "Llamada Realizada";
        String query = "UPDATE `personal_ccb` SET `fecha_llamada` = '" + Escalamiento.getFecha() + "', "
                + "`hora_llamada` = '" + Escalamiento.getHora() + "', `estado_llamada` = '" + estado_llamada + "'"
                + " WHERE id = '" + json.get("idCard") + "';";

        respuesta.put("procede", Query.update(query));
        return respuesta;
    }

    @RequestMapping(value = "/API/diagnostico_personal_ccb", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject diagnostico_personal_ccb(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("diagnostico_personal_ccb: " + Dependencia);
        String query = "UPDATE `personal_ccb` SET diagnostico = '" + json.get("diagnostico") + "' WHERE id = '" + json.get("id") + "';";
        JSONObject res = new JSONObject();
        res.put("procede", Query.update(query));
        if (Boolean.parseBoolean(res.get("procede").toString())) {
            if (!json.get("fecha_nuevaLlamada").toString().equals("NULL")) {
                query = "INSERT INTO `agenda_nuevas_llamada` (`idUsuario`,`nombre`,`fecha`,`hora`,`fecha_nuevaLlamada`,`rol_usuario`) "
                        + "VALUES('" + json.get("idUsuario") + "','" + json.get("nombre") + "','" + Escalamiento.getFecha() + "',"
                        + "'" + Escalamiento.getHora() + "','" + json.get("fecha_nuevaLlamada") + "','" + json.get("rol_usuario") + "');";
                Query.insert(query);
            }
            query = "UPDATE `usuarios_movil` SET acceso_ccb = '" + json.get("acceso_ccb") + "' WHERE idUsuarios_Movil = '" + json.get("idUsuario") + "';";
            Query.update(query);
        }

        return res;
    }

    @RequestMapping(value = "/API/notificar_llamada_ccb", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public static JSONObject notificar_llamada_ccb(@RequestBody String string) throws IOException, ParseException {
        System.out.println("notificar_llamada_ccb en: " + Dependencia);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(string);
        json.put("fecha", Modelo.Escalamiento.getFecha());
        json.put("hora", Modelo.Escalamiento.getHora());
        System.out.println(json);
        json.put("estado", "Solicitud de llamada");
        String query_url = config.getURL_CONTROLADOR() + "restOpentok/ArchiveSession";
        JSONObject OpenTok = new JSONObject();
        OpenTok.put("apikey", json.get("apikey"));
        OpenTok.put("token", json.get("token"));
        OpenTok.put("sesion", json.get("idsesion"));
        String url = Request.request.POST(query_url, OpenTok.toString());
        json.put("url", url);
        JSONArray idUsuarios = (JSONArray) json.get("idUsuarios");
        JSONArray idNotificaciones = new JSONArray();
        for (int i = 0; i < idUsuarios.size(); i++) {
            //registrar llamada
            String query = "INSERT INTO `ccb_llamadas` (`idUsuario`, `folio`, `fecha`, `hora`, `estado`, `url`, `personal`,`paciente_alta`) "
                    + "VALUES ('" + idUsuarios.get(i) + "', NULL, '" + json.get("fecha") + "', '" + json.get("hora") + "', '" + json.get("estado") + "', '" + json.get("url") + "', '" + json.get("personal") + "','1');";

            json.put("idUsuario", idUsuarios.get(i));
            idNotificaciones.add(Query.insert(query));
            query = "SELECT FireBaseKey FROM usuarios_movil WHERE idUsuarios_Movil = '" + idUsuarios.get(i) + "';";
            JSONObject fb = Query.select(query);
            if (fb != null) {
                json.put("FireBaseKey", fb.get("FireBaseKey"));
            } else {
                json.put("FireBaseKey", null);
            }

        }
        json.put("idNotificaciones", idNotificaciones);
        JSONObject notificar_llamada = request.POST(config.getURL_CONTROLADOR() + "API/NotificarLlamada_CCB", json);
        System.out.println("Respuesta ------->");
        System.out.println(notificar_llamada);
        return notificar_llamada;
    }

    
    /**
     * **********************************************************************************
     */
    @RequestMapping(value = "/API/cuenta360/access_token", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JSONObject access_token(@RequestBody JSONObject json) throws ParseException, IOException {
        return Request.request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/access_token", json);
    }

    /**
     * *
     * <p>
     * API para modificar los modulo de forma local (Su plataforma )
     */
    @RequestMapping(value = "/API/BusquedaPorUsuarios", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JSONArray BusquedaPorUsuarios(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println(json);
        String query = "SELECT * FROM usuario_sys WHERE idUsuario_Sys = " + json.get("id") + ";";
        JSONArray url = Query.execute(query);

        return url;
    }

    @RequestMapping(value = "/API/GuardarModulosAUsuarios", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JSONObject GuardarModulosAUsuarios(@RequestBody String string) throws ParseException, IOException {
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", false);
        respuesta.put("failure", true);
        respuesta.put("message", "No database connection.");

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(string);
        JSONArray modulos = (JSONArray) json.get("value");
        String m = "";
        for (int i = 0; i < modulos.size(); i++) {
            m += modulos.get(i);
            if (i < modulos.size() - 1) {
                m += ",";
            }
        }
        String query = "UPDATE usuario_sys SET modulos ='" + m + "' WHERE idUsuario_Sys = " + json.get("idUsuario_Sys") + ";";
        if (Query.update(query)) {
            /// exito
            respuesta.put("success", true);
            respuesta.put("failure", false);
            respuesta.put("message", "Cambios registrados correctamente.");
        }

        return respuesta;
    }

    @RequestMapping(value = "/API/UsuariosControlador", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String UsuariosControlador(@RequestBody String string) throws ParseException, IOException {
        System.out.println("USUARIOS CONTROLADO");
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(string);
        String url = config.getPATH() + config.getDEPENDENCIA() + "/";
        jsonObj.put("url", url);
        System.out.println(string);
        System.out.println(jsonObj);
        return request.POST(config.getURL_CONTROLADOR() + "/API/cuenta360/usuarios/plataforma360", jsonObj.toString());
    }

    @RequestMapping(value = "/API/GuardarModulos360", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String GuardarModulos360(@RequestBody String string) throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(string);
        JSONArray modulos = (JSONArray) json.get("modulos");
        String m = "";
        for (int i = 0; i < modulos.size(); i++) {
            m += modulos.get(i);
            if (i < modulos.size()) {
                m += ",";
                json.put("modulos", m);
            }
        }
        return request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/registro_modulo", json.toString());
    }

    ///***********************Escuela***************************/
    @RequestMapping(value = "/API/escuela360/registro_institucion", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject escuela360_registro_institucion(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("escuela360_registro_institucion");
        JSONObject respuesta = respuesta(false, "Ocurrio un problema, intentarlo mas tarde.");
        json.put("idTipoUsuario", "1001");

        //id-token 104-ADF785SS
        json.put("token", generateString().substring(0, 8).toUpperCase());
        int tipo_servicio = Query.insert(Query.createQueryInsert("servicios_usuario", json));
        if (tipo_servicio > 0) {
            //
            //registrar modulo 
            /*
            {
                "id360":"3",
                "idModulo":"2",
                "modulo":"plataforma360",
                "url":"url",
                "tipo_usuario":"4",
                "tipo_servicio":"10",
                "modulos":"1,2,3,4,5",
                "modulo_principal":"2",
                "activo":"1"
            }
             */

            json.put("id360", json.get("id_usuario"));
            json.put("modulo", "plataforma360");
            json.put("url", "https://escuela360.ml/plataforma360/");
            json.put("tipo_usuario", "1001");
            json.put("tipo_servicio", tipo_servicio);
            json.put("modulos", "403,400");
            json.put("modulo_principal", "administracion_directiva");
            json.put("alias", "Escuela360");
            json.put("rol_escolar", "1");
            System.out.println(json);
            JSONObject r = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/registro_modulo", json);
            System.out.println(r);
//            respuesta=respuesta(true,"Registro realizado correctamente");
            if ((Boolean) r.get("success")) {
                respuesta = respuesta(true, "Registro realizado correctamente.");
                respuesta.put("nombre_institucion", json.get("nombre"));
                respuesta.put("tipo_usuario", json.get("tipo_usuario"));
                respuesta.put("tipo_servicio", json.get("tipo_servicio"));
            }
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/escuela360/vinculacion_institucion", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject escuela360_vinculacion_institucion(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("escuela360_vinculacion_institucion");
        JSONObject respuesta = respuesta(false, "Ocurrio un problema, intentarlo mas tarde.");

        JSONObject modulo = new JSONObject();
        modulo.put("id360", json.get("id_usuario"));
        modulo.put("modulo", "plataforma360");
        modulo.put("url", "https://escuela360.ml/plataforma360/");
        modulo.put("tipo_usuario", json.get("tipo_usuario"));
        modulo.put("tipo_servicio", json.get("tipo_servicio"));
        modulo.put("modulos", "403,401");
        modulo.put("modulo_principal", "planeacion_docente");
        modulo.put("alias", "Escuela360");
        modulo.put("rol_escolar", "2");
        System.out.println(modulo);
        JSONObject r = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/registro_modulo", modulo);
        System.out.println(r);
//            respuesta=respuesta(true,"Registro realizado correctamente");
        if ((Boolean) r.get("success")) {
            respuesta = respuesta(true, "Vinculacion realizada correctamente.");
            respuesta.put("nombre_institucion", json.get("nombre"));
            respuesta.put("tipo_usuario", json.get("tipo_usuario"));
            respuesta.put("tipo_servicio", json.get("tipo_servicio"));
        }

        return respuesta;
    }

    @RequestMapping(value = "/API/escuela/info_escuela", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject escuela360_info_escuela(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("escuela360_info_escuela");
        JSONObject respuesta = respuesta(false, "El código de vinculación no corresponde a alguna de las instituciones regristradas.");

        String query = "SELECT * from servicios_usuario WHERE token = '" + json.get("token") + "' AND id='" + json.get("id") + "';";
        JSONObject res = Query.select(query);
        if (res != null) {
            respuesta = respuesta(true, "Institución encontrada");
            respuesta.putAll(res);
            //recuperar nombre de director

            JSONObject id360 = new JSONObject();
            id360.put("id360", res.get("id_usuario"));
            JSONObject director = request.POST("https://plataforma911.ml/CONTROLADOR/API/cuenta360/perfil", id360);
            if ((Boolean) director.get("success")) {
                respuesta.put("nombre_director", director.get("nombre"));
                respuesta.put("apellido_paterno_director", director.get("apellido_paterno"));
                respuesta.put("apellido_materno_director", director.get("apellido_materno"));
            }
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/escuela/servicio/info_escuela", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject escuela360_servicio_info_escuela(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("escuela360_info_escuela");
        JSONObject respuesta = respuesta(false, "Institución no encontrada.");

        String query = "SELECT * from servicios_usuario WHERE id='" + json.get("id") + "';";
        JSONObject res = Query.select(query);
        if (res != null) {
            respuesta = respuesta(true, "Institución encontrada");
            respuesta.putAll(res);
            //recuperar nombre de director

            JSONObject id360 = new JSONObject();
            id360.put("id360", res.get("id_usuario"));
            JSONObject director = request.POST("https://plataforma911.ml/CONTROLADOR/API/cuenta360/perfil", id360);
            if ((Boolean) director.get("success")) {
                respuesta.put("nombre_director", director.get("nombre"));
                respuesta.put("apellido_paterno_director", director.get("apellido_paterno"));
                respuesta.put("apellido_materno_director", director.get("apellido_materno"));
            }
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/escuela360/registro_personal", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject escuela360_registro_personal(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("escuela360_registro_personal");
        JSONObject respuesta = respuesta(false, "Ocurrio un error. Intentarlo mas tarde.");

        json.put("modulo", "plataforma360");
        json.put("url", "https://escuela360.ml/plataforma360/");
        json.put("modulos", "403,401");
        json.put("modulo_principal", "planeacion_docente");
        json.put("alias", "Escuela360");
        json.put("rol_escolar", "2");

        //Madar request a controlador para el registro de la cuenta y del modulo o simplemente del modulo 
        JSONObject res = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/escuela360/registro_externo", json);
        System.out.println(res);
        if ((Boolean) res.get("success")) {
            respuesta = respuesta(true, "Registro realizado correctamente. Se envio una notificacion al correo electronico: " + json.get("correo"));
            //registrar personal de la institucuion 
            JSONObject personal = new JSONObject();
            personal.put("id_usuario", res.get("id360"));
            personal.put("cedula_profesional", json.get("cedula") != null ? json.get("cedula") : "");
            personal.put("curp", json.get("curp") != null ? json.get("curp") : "");
            personal.put("rfc", json.get("rfc") != null ? json.get("rfc") : "");
            personal.put("tipo_usuario", json.get("tipo_usuario") != null ? json.get("tipo_usuario") : "");
            personal.put("tipo_servicio", json.get("tipo_servicio") != null ? json.get("tipo_servicio") : "");
            System.out.println(personal);

            Query.insert(Query.createQueryInsert("personal_escuela", personal));
        }

        return respuesta;
    }

    @RequestMapping(value = "/API/escuela360/registro_personal_array", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject registro_personal_array(@RequestBody String jsonO) throws ParseException, IOException {
        System.out.println("escuela360_registro_personal");
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonO);
        JSONObject respuesta = respuesta(false, "Ocurrio un error. Intentarlo mas tarde.");

        JSONObject r = new JSONObject();
        for (int i = 0; i < json.size(); i++) {
            JSONArray hoja = (JSONArray) json.get(i);
//            System.out.println(hoja);
            for (int j = 0; j < hoja.size(); j++) {
                JSONObject info_hoja = (JSONObject) hoja.get(j);
                info_hoja.put("modulo", "plataforma360");
                info_hoja.put("url", "https://escuela360.ml/plataforma360/");
                info_hoja.put("modulos", "403,401");
                info_hoja.put("modulo_principal", "planeacion_docente");
                info_hoja.put("alias", "Escuela360");
                info_hoja.put("rol_escolar", "2");

                //Madar request a controlador para el registro de la cuenta y del modulo o simplemente del modulo 
                JSONObject res = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/escuela360/registro_externo", info_hoja);
//                System.out.println(res);
                if ((Boolean) res.get("success")) {
                    respuesta = respuesta(true, "Registro realizado correctamente. Se envio una notificacion al correo electronico: " + json.get("correo"));
                    //registrar personal de la institucuion 
                    JSONObject personal = new JSONObject();
                    personal.put("id_usuario", res.get("id360"));
                    personal.put("cedula_profesional", json.get("cedula") != null ? json.get("cedula") : "");
                    personal.put("curp", json.get("curp") != null ? json.get("curp") : "");
                    personal.put("rfc", json.get("rfc") != null ? json.get("rfc") : "");
                    personal.put("tipo_usuario", json.get("tipo_usuario") != null ? json.get("tipo_usuario") : "");
                    personal.put("tipo_servicio", json.get("tipo_servicio") != null ? json.get("tipo_servicio") : "");
                    System.out.println(personal);

                    int id = Query.insert(Query.createQueryInsert("personal_escuela", personal));

                    if (id > 0) {
                        respuesta.put("Agregado " + j, info_hoja.get("nombre") + " " + info_hoja.get("apellidopaterno") + " " + info_hoja.get("apellidomaterno"));
                    } else {
                        respuesta.put("No Agregado " + j, info_hoja.get("nombre") + " " + info_hoja.get("apellidopaterno") + " " + info_hoja.get("apellidomaterno"));
                    }

                }
            }
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/escuela360/registro_grupo", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject escuela360_registro_grupo(@RequestBody String jsonObj) throws ParseException, IOException {
        System.out.println("escuela360_registro_grupo");
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonObj);
        JSONObject respuesta = respuesta(false, "Ocurrio un error. Intentarlo mas tarde.");
        String query = "SELECT id FROM grupos_academicos WHERE nombre = '" + json.get("nombre") + "' AND id_institucion_academica = '" + json.get("id_institucion_academica") + "';";
        if (Query.select(query) == null) {
            json.put("token", generateString().substring(0, 8).toUpperCase());
            int id_grupo = Query.insert(Query.createQueryInsert("grupos_academicos", json));
            if (id_grupo > 0) {
                System.out.println("########");
                System.out.println(json.get("profesores_asignados"));
                JSONArray profesores = (JSONArray) json.get("profesores_asignados");

                for (int i = 0; i < profesores.size(); i++) {
                    JSONObject profesor = (JSONObject) profesores.get(i);
                    profesor.put("id_grupo", id_grupo);
                    profesor.put("id_usuario", profesor.get("id360"));
                    Query.insert(Query.createQueryInsert("profesores", profesor));
                }
                respuesta = respuesta(true, "Grupo creado correctamente.");
                respuesta.put("id_grupo", id_grupo);
                respuesta.put("token", json.get("token"));
                respuesta.put("nombre", json.get("nombre"));
                respuesta.put("profesores", json.get("profesores_asignados"));
            }
        } else {
            respuesta = respuesta(false, "Ya existe un grupo con el nombre \"" + json.get("nombre") + "\" registrado en esta institución.");
        }
        return respuesta;
    }
    ///***********************Escuela***************************/

    //como vamos a recibir una url no lo podemos hacer desde el front
    @RequestMapping(value = "/API/trae_servicios/esterno", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject trae_servicios(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("trae_servicios");
        // se recive la url y se hace el request url + "/traeservicios"
        if (json.get("url").toString().contains("https")) {
            return request.POST(json.get("url") + "/API/traeServicios", new JSONObject());
        }
        return null;
    }

    @RequestMapping(value = "/API/registro/nuevo_modulo", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject registro_nuevo_modulo(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("registro_nuevo_modulo");
        //Se realiza el registro del nuevo modulo
        json.put("modulo", "plataforma360");
        return request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/registro_modulo", json);
    }
    @RequestMapping(value = "/API/get/perfil360", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject get_perfil360(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("get_perfil360");
        return request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfil", json);
    }
 
}
