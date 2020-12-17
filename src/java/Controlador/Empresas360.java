/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Config.config;

import static Controlador.BackupDirectorio.*;
import static Encriptacion.Main.generateString;

import Modelo.*;
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
import java.util.Iterator;
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

public class Empresas360 {

    private static String Dependencia = config.getDEPENDENCIA();

   
    public static void setDependencia(String Dependencia) {
        Empresas360.Dependencia = Dependencia;
    }

    private JSONObject respuesta(boolean success, String message) {
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", success);
        respuesta.put("failure", !success);
        respuesta.put("mensaje", message);
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/DeshabilitarUsr", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject DeshabilitarUsr_Directorio_empresas360(@RequestBody JSONObject json) throws IOException {
        System.out.println("DeshabilitarUsr_Directorio " + Dependencia);
        JSONObject respuesta = respuesta(false, "No se encontro usuario.");

        //Desabilitar del directorio
        String query = "UPDATE `directorio` SET `activo`='0' WHERE `idUsuario`='" + json.get("id360") + "' AND `activo`='1';";
        if (Query.update(query)) {
            respuesta = respuesta(true, "Usuario deshabilitado del directorio");

            //Deshabilitar de grupos personalizados
            query = "UPDATE `grupos_usuario_movil` SET `estado`='4' WHERE `idUsuarios_Movil`='" + json.get("id360") + "' AND estado=1;";
            if (Query.update(query)) {
                respuesta.put("mensaje", respuesta.get("mensaje") + ", grupos personalizados");
            }
            //Deshabilitar de grupos automaticos
            query = "UPDATE `grupos_usuario_movil` SET `estado`='0' WHERE `idUsuarios_Movil`='" + json.get("id360") + "' AND estado=3;";
            if (Query.update(query)) {
                respuesta.put("mensaje", respuesta.get("mensaje") + ", grupos automaticos");
            }

        }
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/GruposPersonalizados", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject GruposPersonalizadosEmpresas360(@RequestBody JSONObject id) throws ParseException, IOException {
        System.out.println("GruposPersonalizados: " + Dependencia);
        JSONObject json = new JSONObject();
        Grupos grupos = new Grupos();
        json.put("GruposPersonalizados", grupos.GruposPersonalizadosEmpresas360(id.get("idUsuarioSys").toString()));
        System.out.println(json);
        json.put("GruposAutomaticos", grupos.GruposAutomaticosEmpresas360(id.get("tipo_usuario").toString(), id.get("tipo_servicio").toString()));
        System.out.println(json);
        json.put("integrantes", grupos.integrantesEmpresas360((JSONArray) json.get("GruposAutomaticos")));
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

    @RequestMapping(value = "/API/empresas360/vinculacion_empleado", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JSONObject vinculacion_empleado(@RequestBody JSONObject json) throws ParseException, IOException, java.text.ParseException {
        System.out.println("Registro: " + Dependencia);
        JSONObject respuesta = respuesta(true, "Proceso realizado.");
        JSONObject segimiento = new JSONObject();
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return respuesta(true, "Proyecto no inicializado.");
        }

        //Agregando al directorio ¿Aún en necesario????
        JSONObject jsonDirectorio = new JSONObject();
        jsonDirectorio.put("idUsuario", json.get("id360"));
        jsonDirectorio.put("urlServicio", config.getPATH() + Dependencia);
        jsonDirectorio.put("tipo_usuario", json.get("tipo_usuario"));
        jsonDirectorio.put("tipo_servicio", json.get("tipo_servicio"));
        jsonDirectorio.put("tipo_area", json.get("tipo_area"));
        jsonDirectorio.put("activo", "2");

        //Agregar_Directorio2(jsonDirectorio);****************************************************************
        //Deshabilitar del directorio si ya habia una vinculacion previa--
        segimiento.put("Actualizacion_directorio", false);
        String query = "update directorio set activo=0 where idUsuario=" + jsonDirectorio.get("idUsuario") + ";";
        if (Query.update(query)) {
            segimiento.put("Actualizacion_directorio", true);
        }

        //Deshabilitar de los grupos automaticos si ya habia una vinculacion previa--
        segimiento.put("Actualizacion_grupos", false);
        query = "update grupos_usuario_movil set estado = 0 where idUsuarios_Movil='" + jsonDirectorio.get("idUsuario") + "' AND estado = 3;";
        if (Query.update(query)) {
            segimiento.put("Actualizacion_grupos", true);
        }
        //Registro en el directorio con los nuevos datos
        segimiento.put("Registro_directorio", false);
        long id_directorio = Query.insert(Query.createQueryInsert("directorio", jsonDirectorio));
        if (id_directorio > 0) {
            segimiento.put("Registro_directorio", true);
            segimiento.put("id_directorio", id_directorio);

            //Agregando al grupo automatico correspondiente
            //Verificar que exista el grupo designado para el usuario
            query = "SELECT *  FROM grupos_usuario_sys WHERE nombre = '" + json.get("area") + "' AND tipo_usuario='" + json.get("tipo_usuario") + "' AND tipo_servicio='" + json.get("tipo_servicio") + "' AND estado = 3;";
            System.out.println(query);
            JSONObject Grupo = Query.select(query);
            System.out.println(Grupo);
            segimiento.put("Creacion_grupo", false);
            segimiento.put("Agregado_grupo", false);
            if (Grupo == null) {
                //Crear grupo si no existe...... //No deberia pasar por que los grupos automaticos se estan creando previamente....
                query = "INSERT INTO `grupos_usuario_sys` (`nombre`, `estado`, `tipo_usuario`, `tipo_servicio`, `tipo_area`) "
                        + "VALUES ('" + json.get("area") + "', '3', '" + json.get("tipo_usuario") + "', '" + json.get("tipo_servicio") + "', '" + json.get("tipo_area") + "');";
                int id = Query.insert(query);
                segimiento.put("Creacion_grupo", true);
                if (id < 0) {
                    System.out.println("Hubo un error al crear el grupo: " + json.get("area"));
                } else {
                    query = "INSERT INTO `grupos_usuario_movil` (`idgruposUsuarioSys`, `idUsuarios_Movil`, `estado`,`tipo_usuario`,`tipo_servicio`,`tipo_area`) VALUES ('" + id + "', '" + json.get("id360") + "','6','" + json.get("tipo_usuario") + "','" + json.get("tipo_servicio") + "','" + json.get("tipo_area") + "');";
                    long id_grupo = Query.insert(query);
                    if (id_grupo < 0) {
                        respuesta = respuesta(false, "Error al registrar en el grupo.");
                        System.out.println("Hubo un error al insertar el usuario: " + json.get("idUsuario") + " al grupo: " + json.get("aliasServicio"));
                    } else {
                        segimiento.put("Agregado_grupo", true);
                        segimiento.put("id_grupo", id_grupo);
                    }
                }

            } else {

                query = "INSERT INTO `grupos_usuario_movil` (`idgruposUsuarioSys`, `idUsuarios_Movil`, `estado`,`tipo_usuario`,`tipo_servicio`,`tipo_area`) "
                        + "VALUES ('" + Grupo.get("idgruposUsuarioSys") + "', '" + json.get("id360") + "','6','" + json.get("tipo_usuario") + "','" + json.get("tipo_servicio") + "','" + json.get("tipo_area") + "');";
                System.out.println(query);
                long id_grupo = Query.insert(query);
                if (id_grupo < 0) {
                    respuesta = respuesta(false, "Error al registrar en el grupo.");
                    System.out.println("Hubo un error al insertar el usuario: " + json.get("id360") + " al grupo: " + Grupo.get("nombre"));
                } else {
                    segimiento.put("Agregado_grupo", true);
                    segimiento.put("id_grupo", id_grupo);
                }
            }

            System.out.println("json");

            //Deshabilitar de grupos personalizados
//            query = "UPDATE `grupos_usuario_movil` SET `estado`='1' WHERE `idUsuarios_Movil`='" + json.get("idUsuario") + "' AND estado=4;";
//            if (Query.update(query)) {
//                System.out.println("Usuario: " + json.get("idUsuario") + " habilitado del los grupos personalizados en: " + Dependencia);
//            }
        } else {
            respuesta = respuesta(false, "Error al registrar en el directorio.");
        }

        respuesta.putAll(segimiento);
        return respuesta;

    }

    @RequestMapping(value = "/API/empresas360/activacion/empleado", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject activacion_empleado(@RequestBody JSONObject json) {
        JSONObject respuesta = respuesta(false, "Empleado no activado.");
        //id_directorio  id_grupo

        String query = "UPDATE directorio SET activo = '1' WHERE id = '" + json.get("id_directorio") + "';";
        if (Query.update(query)) {
            respuesta = respuesta(true, "Activado en directorio.");
            query = "UPDATE grupos_usuario_movil SET estado = '3' WHERE idgrupos_usuario_movil = '" + json.get("id_grupo") + "';";
            if (Query.update(query)) {
                respuesta = respuesta(true, "Activado en directorio y grupo automatico.");
            }
        }

        return respuesta;

    }

    /**
     * ******API'S PARA EMPRESAS360************
     */
    @RequestMapping(value = "/API/empresas360/ServiciosPorUsuario", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public static JSONObject ServiciosPorUsuario_empresas360() throws java.text.ParseException, ParseException, IOException {
        System.out.println("traeServicios en: " + Dependencia);
        JSONObject resp = new JSONObject();
        //obtengo las empresas
        String query = "SELECT * FROM tipos_usuarios WHERE activo = 1 AND RegistroApp = 1;";
        JSONArray tiposUsuarios = Query.execute(query);

        //obtengo las sucursales 
        query = "SELECT * FROM servicios_usuario WHERE activo = 1 AND visibilidad_app=1;";
        JSONArray serviciosUsuario = Query.execute(query);

        for (int i = 0; i < tiposUsuarios.size(); i++) {
            JSONArray array1 = new JSONArray();
            JSONObject jsonObj = new JSONObject();

            for (int j = 0; j < serviciosUsuario.size(); j++) {
                if (((JSONObject) serviciosUsuario.get(j)).get("idTipoUsuario").toString().equals(((JSONObject) tiposUsuarios.get(i)).get("id").toString())) {

                    //al agregar una sucursal a una empresa buscar las areas registradas de la sucursal
                    JSONObject suc = (JSONObject) serviciosUsuario.get(j);
//                    query = "SELECT A.id AS area_servicio, C.nombre, C.descripcion FROM areas_servicio A, catalogo_areas C \n"
//                            + "WHERE A.id_area=C.id AND A.activa=1 AND id_servicio_usuario='" + suc.get("id") + "';";
                    query = "SELECT id AS tipo_area, area  FROM tipo_area where tipo_servicio=" + suc.get("id") + " AND tipo_usuario=" + suc.get("idTipoUsuario") + " AND activa=1 ;";
                    suc.put("areas", Query.execute(query));

                    array1.add(serviciosUsuario.get(j));
                }
            }
            jsonObj.put("idTipoUsuario", ((JSONObject) tiposUsuarios.get(i)).get("id"));
            jsonObj.put("instituciones", array1);

            resp.put(((JSONObject) tiposUsuarios.get(i)).get("tipo_usuario"), jsonObj);
        }
        return resp;
    }

    @RequestMapping(value = "/API/Reporte_Sintomas", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject Reporte_Sintomas(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("Reporte_Sintomas en: " + Dependencia);
        /*
        {
            "id360":"",
            "tipo_usuario":"",
            "tipo_servicio":"",
            "temperatura":"",
            "oximetria":"",
            "dificultad_respirar":"",
            "sintoma_":"", //despues del guion bajo va el nombre del sintoma,
                             si tiene mas de un sintoma todos tienen que venir con la llave "sintoma_" seguido del nombre del sintoma
                             los nombres de los sintomas son:
                             debilidad, diarrea, dolor_cabeza, dolor_huesos, escalofrio, fiebre, secrecion, tos, ninguno
            "inicio_sintomas":"",
            "estado_usuario":"",
            "razon":"",
            "inicio_aislamiento":""
        }
         */
        JSONObject respuesta = new JSONObject();
        if (!json.containsKey("id360")) {
            respuesta = respuesta(false, "No se encuentra la llave id360 en JSON de entrada");
            return respuesta;
        }
        respuesta.put("mensaje", "Error al guardar el reporte");
        respuesta.put("success", false);
        respuesta.put("failure", true);
        int id_reporte = Query.insert(Query.createQueryInsert("reporte_sintomas", json));
        if (id_reporte > 0) {
            respuesta.put("mensaje", "Reporte guardado correctamente");
            respuesta.put("success", true);
            respuesta.put("failure", false);
            respuesta.put("id_reporte", id_reporte);
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/Reporte_EquipoProteccion", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject Reporte_EquipoProteccion(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("Reporte_EquipoProteccion en: " + Dependencia);
        /*
        {
            "id360":"",
            "tipo_usuario":"",
            "tipo_servicio":"",
            "prueba_covid":"",
            "prueba_reciente":"",
            "resultado_prueba":"",
            "proteccion_":"", //despues del guion bajo va el nombre del tipo de proteccion que se tiene,
                             si tiene mas de un tipo de proteccion todos tienen que venir con la llave "proteccion_" seguido del nombre del tipo de proteccion
                             los nombres de los tipos de proteccion son:
                             cubrebocas, careta, guantes, gogles, gorra
            "observaciones":""
        }
         */
        JSONObject respuesta = new JSONObject();
        if (!json.containsKey("id360")) {
            respuesta = respuesta(false, "No se encuentra la llave id360 en JSON de entrada");
            return respuesta;
        }
        respuesta.put("mensaje", "Error al guardar el reporte");
        respuesta.put("success", false);
        respuesta.put("failure", true);
        int id_reporte = Query.insert(Query.createQueryInsert("reporte_equipo_proteccion", json));
        if (id_reporte > 0) {
            respuesta.put("mensaje", "Reporte guardado correctamente");
            respuesta.put("success", true);
            respuesta.put("failure", false);
            respuesta.put("id_reporte", id_reporte);
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/movilidad_espacio_laboral", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject movilidad_espacio_laboral(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("movilidad_espacio_laboral en: " + Dependencia);

        JSONObject respuesta = respuesta(false, "Error al guardar el reporte");

        /*Validacion de que contenga la llave id360*/
        if (!json.containsKey("id360")) {
            respuesta = respuesta(false, "No se encuentra la llave id360 en JSON de entrada");
            return respuesta;
        }
        String query = "SELECT id FROM movilidad_espacio_laboral where id360=" + json.get("id360") + "";
        if (Query.select(query) == null) {
            int id_reporte = Query.insert(Query.createQueryInsert("movilidad_espacio_laboral", json));
            if (id_reporte > 0) {
                respuesta = respuesta(true, "Registro guardado correctamente");
            }

        } else {
            JSONObject where = new JSONObject();
            where.put("id360", json.get("id360"));
            if (Query.update(Query.createQueryUpdateAND("movilidad_espacio_laboral", json, where))) {
                respuesta = respuesta(true, "Registro actualizado correctamente");
            }
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/registro_familiar", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject registro_familiar(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("movilidad_espacio_laboral en: " + Dependencia);

        JSONObject respuesta = respuesta(false, "Error al guardar el reporte");

        /*Validacion de que contenga la llave id360*/
        if (!json.containsKey("id360")) {
            respuesta = respuesta(false, "No se encuentra la llave id360 en JSON de entrada");
            return respuesta;
        }

        int id_reporte = Query.insert(Query.createQueryInsert("registro_familiar", json));
        if (id_reporte > 0) {
            respuesta = respuesta(true, "Registro guardado correctamente");
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/registro_familiar_grupo_carso", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject registro_familiar_grupo_carso(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("movilidad_espacio_laboral en: " + Dependencia);

        JSONObject respuesta = respuesta(false, "Error al guardar el reporte");

        /*Validacion de que contenga la llave id360*/
        if (!json.containsKey("id360")) {
            respuesta = respuesta(false, "No se encuentra la llave id360 en JSON de entrada");
            return respuesta;
        }
        String query = "SELECT id FROM registro_familiar_grupo_carso where id360=" + json.get("id360") + "";
        if (Query.select(query) == null) {
            int id_reporte = Query.insert(Query.createQueryInsert("registro_familiar_grupo_carso", json));
            if (id_reporte > 0) {
                respuesta = respuesta(true, "Registro guardado correctamente");
            }

        } else {
            JSONObject where = new JSONObject();
            where.put("id360", json.get("id360"));
            if (Query.update(Query.createQueryUpdateAND("registro_familiar_grupo_carso", json, where))) {
                respuesta = respuesta(true, "Registro actualizado correctamente");
            }
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/registro_familiar/registro_hijos", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject registro_hijos(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("movilidad_espacio_laboral en: " + Dependencia);

        JSONObject respuesta = respuesta(false, "Error al guardar el reporte");

        /*Validacion de que contenga la llave id360*/
        if (!json.containsKey("id360")) {
            respuesta = respuesta(false, "No se encuentra la llave id360 en JSON de entrada");
            return respuesta;
        }
        String query = "SELECT id FROM registro_hijos where id360=" + json.get("id360") + "";
        if (Query.select(query) == null) {
            int id_reporte = Query.insert(Query.createQueryInsert("registro_hijos", json));
            if (id_reporte > 0) {
                respuesta = respuesta(true, "Registro guardado correctamente");
            }

        } else {
            JSONObject where = new JSONObject();
            where.put("id360", json.get("id360"));
            if (Query.update(Query.createQueryUpdateAND("registro_hijos", json, where))) {
                respuesta = respuesta(true, "Registro actualizado correctamente");
            }
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/registro_tipo_usuario", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject registro_tipo_usuario(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("registro_tipo_usuario en: " + Dependencia);

        JSONObject respuesta = respuesta(false, "Error al guardar el reporte");

        int tipo_usuario = Query.insert(Query.createQueryInsert("tipos_usuarios", json));
        if (tipo_usuario > 0) {
            respuesta = respuesta(true, "Registro guardado correctamente");
            respuesta.put("tipo_usuario", tipo_usuario);
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/registro/servicios_usuario", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject registro_servicios_usuario(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("registro_servicios_usuario en: " + Dependencia);

        JSONObject respuesta = respuesta(false, "Error al guardar el reporte");

        int servicio_usuario = Query.insert(Query.createQueryInsert("servicios_usuario", json));
        if (servicio_usuario >= 0) {
            respuesta = respuesta(true, "Registro guardado correctamente");
            respuesta.put("servicio_usuario", json.get("id"));
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/cuenta360/empresas360/perfil/empleado", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject cuenta360_perfil_empleado(@RequestBody JSONObject json) throws Exception {
        return request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfil/empleado", json);
    }

    @RequestMapping(value = "/API/empresas360/perfil", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject empresas360_perfil(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("empresas360_perfil en: " + Dependencia);
        /*
        {
            "id360":"",
            "correo":"",
            "usuario":"", *opcional
            "tipo_usuario":"", **
            "tipo_servicio":"" **
        }
         */

        JSONObject perfil_user = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfil360", json);
        JSONObject respuesta = respuesta(false, "Ocurrio un error intentelo mas tarde.");
        if ((Boolean) perfil_user.get("success")) {
            JSONArray modulo_empleados = (JSONArray) perfil_user.get("empleados");
            if (modulo_empleados.size() > 0) {
                for (int i = 0; i < modulo_empleados.size(); i++) {
                    JSONObject empleado = (JSONObject) modulo_empleados.get(i);
                    JSONObject reporte_sintomas = Query.select("SELECT * FROM reporte_sintomas "
                            + "WHERE id360 = '" + json.get("id360") + "' "
                            + "AND tipo_usuario = '" + empleado.get("tipo_usuario") + "' "
                            + "AND tipo_servicio = '" + empleado.get("tipo_servicio") + "' ORDER BY id DESC LIMIT 1;");

                    //Solicitamos el ultimo reporte de proteccion
                    JSONObject reporte_equipo_proteccion = Query.select("SELECT * FROM reporte_equipo_proteccion "
                            + "WHERE id360 = '" + json.get("id360") + "' "
                            + "AND tipo_usuario = '" + empleado.get("tipo_usuario") + "' "
                            + "AND tipo_servicio = '" + empleado.get("tipo_servicio") + "' ORDER BY id DESC LIMIT 1;");

                    //Solicitamos la informacion de movilidad al laboral
                    JSONObject movilidad_espacio_laboral = Query.select("SELECT * FROM movilidad_espacio_laboral "
                            + "WHERE id360 = '" + json.get("id360") + "';");

                    //Solicitamos la informacion correspondiente al registro familiar
                    JSONArray registro_familiar = Query.execute("SELECT * FROM registro_familiar "
                            + "WHERE id360 = '" + json.get("id360") + "';");

                    //Solicitamos la informacion del registro familiar en grupo carso
                    JSONArray registro_familiar_grupo_carso = Query.execute("SELECT * FROM registro_familiar_grupo_carso "
                            + "WHERE id360 = '" + json.get("id360") + "';");

                    //Solicitamos la informacion correspondiente al registro de hijos
                    JSONArray registro_hijos = Query.execute("SELECT * FROM registro_hijos "
                            + "WHERE id360 = '" + json.get("id360") + "';");

                    //Solicitamos la informacion correspondiente a la empresa  ******
                    /*JSONObject info_empresa = Query.select("SELECT T.tipo_usuario AS empresa , T.logotipo, T.logotipo_empresa, "
                            + "U.nombre AS centro_trabajo, U.direccion "
                            + "FROM tipos_usuarios T, servicios_usuario U "
                            + "WHERE U.idTipoUsuario = T.id AND U.id = '" + empleado.get("tipo_servicio") + "';");*/
                    respuesta = respuesta(true, "Información encontrada");
                    respuesta.put("modulo_empleado", empleado);
                    respuesta.put("reporte_sintomas", reporte_sintomas);
                    respuesta.put("reporte_equipo_proteccion", reporte_equipo_proteccion);
                    respuesta.put("movilidad_espacio_laboral", movilidad_espacio_laboral);
                    respuesta.put("registro_familiar", registro_familiar);
                    respuesta.put("registro_familiar_grupo_carso", registro_familiar_grupo_carso);
                    respuesta.put("registro_hijos", registro_hijos);
                }
            } else {
                respuesta = respuesta(false, "No se cuenta con el modulo de empleados.");
            }

        } else {
            respuesta = respuesta(false, "Usuario no encontrado.");
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/reporte_evento", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject reporte_evento(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("reporte_evento en: " + Dependencia);

        JSONObject respuesta = respuesta(false, "Error al guardar el reporte");
        //Guardar informacion el la BD
        if (!json.containsKey("id_reporte")) {
            //Insertamos el reporte en la BD
            int id = Query.insert(Query.createQueryInsertWithColumns("reporte_evento", json));
            if (id > 0) {
                respuesta = respuesta(true, "Reporte guardado correctamente");
                respuesta.put("id_reporte", id);
            }
        } else {
            //Actualizamos un registro ya existente
            if (Query.update("UPDATE `reporte_evento` SET `evidencia` = '" + json.get("evidencia") + "' "
                    + "WHERE `id` = '" + json.get("id_reporte") + "';")) {
                respuesta = respuesta(true, "Evidencia actualizada correctamente");
            }
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/reporte_seguridad", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject reporte_seguridad(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("reporte_seguridad en: " + Dependencia);

        return request.POST("https://seguridadsanitaria.claro360.com/lineamientos/API/centro_trabajo/reporte_evidencia", json);
    }

    @RequestMapping(value = "/API/empresas360/registro/horario_laboral", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject registro_horario_laboral(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("registro_horario_laboral: " + Dependencia);

        JSONObject respuesta = respuesta(false, "Error al guardar el reporte");

        //revisar si ya existe un registro del dia 
        String query = "SELECT * FROM registro_jornada_laboral WHERE id_usuario='" + json.get("id_usuario") + "' AND date_created='" + Modelo.Escalamiento.getFecha() + "'";
        JSONObject registro = Query.select(query);
        int id;
        if (registro == null) {
            //Guardar informacion el la BD
            id = Query.insert(Query.createQueryInsert("registro_jornada_laboral", json));
            if (id > 0) {
                respuesta = respuesta(true, "Reporte guardado correctamente");

            }
        } else {
            //Actualizar información
            id = Integer.parseInt(registro.get("id").toString());
            JSONObject where = new JSONObject();
            where.put("id", id);
            where.put("id_usuario", json.get("id_usuario"));
            if (Query.update(Query.createQueryUpdateAND("registro_jornada_laboral", json, where))) {
                respuesta = respuesta(true, "Reporte actualizado correctamente");
            }

        }
        JSONObject reg = Query.select("SELECT * FROM registro_jornada_laboral WHERE id='" + id + "';");
        respuesta.putAll(reg);
        //Notificacion por socket del inicio de envio de video por tipo de usuario 
        if (json.get("activo").toString().equals("1")) {
            reg.put("video_empleado", true);
            SocketEndPoint.EnviarNotificacio_jerarqia(reg, json.get("tipo_usuario").toString(), json.get("tipo_servicio").toString(), json.get("tipo_area").toString());
        }

        return respuesta;
    }

    //Para el registro de las empresas
    @RequestMapping(value = "/API/registro/empresa", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    @ResponseBody
    public JSONObject registro_empresa(@RequestBody JSONObject json) throws ParseException, IOException {
//        
//        
//        //Solicitamos el perfil correspondiante a un id360
//        JSONObject respuesta = respuesta(false, "Error al realizar el registro.");
//        json.put("token", generateString().substring(0, 8));
//        long id = Query.insert(Query.createQueryInsert("empresas", json));
//
//        if (id > 0) {
//            respuesta = respuesta(true, "Registro realizado correctamente");
//            //activar en empresas (monitoreo)
//            JSONObject empresa = new JSONObject();
//            empresa.put("id", id);
//            empresa.put("tipo_usuario", json.get("empresa"));
//            empresa.put("logotipo", json.get("logotipo"));
//            empresa.put("activo", "1");
//            empresa.put("RegistroApp", "1");
//            empresa.put("token", json.get("token"));
//
////            Request.request.POST("https://empresas.claro360.com/plataforma360/API/empresas360/registro_tipo_usuario", empresa);
//            registro_tipo_usuario(empresa);
//            //levantamiento de modulos
//            JSONObject lineamientos = new JSONObject();
//
//            lineamientos.put("id360", json.get("id360"));
//            lineamientos.put("modulo", "lineamientos");
//            lineamientos.put("url", "https://seguridadsanitaria.claro360.com/lineamientos/");
//            lineamientos.put("telefono", json.get("telefono"));
//            lineamientos.put("correo", json.get("correo"));
//            lineamientos.put("modulos", "204,205,206,207");
//            lineamientos.put("modulo_principal", "Reporte Empresas");
//            lineamientos.put("tipo_servicio", "0");
//            lineamientos.put("tipo_usuario", id);
//            lineamientos.put("icono", "https://lineamientos.s3.amazonaws.com/Suite/SeguridadSanitaria.png");
//            lineamientos.put("plataforma", "1");
//            lineamientos.put("precargada", "0");
//            lineamientos.put("persona", "0");
//
//            JSONObject mi_empresa = new JSONObject();
//
//            mi_empresa.put("id360", json.get("id360"));
//            mi_empresa.put("modulo", "plataforma360");
//            mi_empresa.put("url", "https://empresas.claro360.com/plataforma360/");
//            mi_empresa.put("alias", "Mi Empresa");
//            mi_empresa.put("modulos", "3,4,15,24");
//            mi_empresa.put("modulo_principal", "Empresa");
//            mi_empresa.put("tipo_servicio", "0");
//            mi_empresa.put("tipo_usuario", id);
//            mi_empresa.put("icono", "https://lineamientos.s3.amazonaws.com/Suite/Empresa360.png");
//            mi_empresa.put("plataforma", "1");
//            mi_empresa.put("precargada", "0");
//            mi_empresa.put("persona", "0");
//
//            JSONObject mapa_gis = new JSONObject();
//
//            mapa_gis.put("id360", json.get("id360"));
//            mapa_gis.put("url", "https://mapagis.ml/");
//            mapa_gis.put("modulo", "mapagis");
////            mapa_gis.put("cliente", "");
////            mapa_gis.put("estado", "");
//            mapa_gis.put("tipo_usario", json.get("empresa"));
//            mapa_gis.put("tipo_servicio", id);
//            mapa_gis.put("preregistro", "1");
////            mapa_gis.put("tipo_area", "");
//            //crea lineamientos empresas y gis 
//            JSONObject respuesta_lineamientos = Request.request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/registro_modulo", lineamientos);
//            if ((Boolean) respuesta_lineamientos.get("success")) {
//                respuesta = respuesta(true, "Registro modulo lineamientos realizado corrrectamente");
//                JSONObject respuesta_mi_empresa = Request.request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/registro_modulo", mi_empresa);
//                if ((Boolean) respuesta_mi_empresa.get("success")) {
//                    respuesta = respuesta(true, "Registro modulo lineamientos y empresas360 realizado corrrectamente");
//                    JSONObject respuesta_mapa_gis = Request.request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/registro_modulo", mapa_gis);
//                    if ((Boolean) respuesta_mapa_gis.get("success")) {
//                        respuesta = respuesta(true, "Registro modulo lineamientos, empresas360 y mapa gis realizado corrrectamente");
//                    }
//                }
//            }
//        }
        return request.POST("https://seguridadsanitaria.claro360.com/lineamientos/API/registro/empresa", json);
    }

    @RequestMapping(value = "/API/lineamientos/Registro/sucursales/nuevo_modulo", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    @ResponseBody
    public String registro_sucursales(@RequestBody String json) throws ParseException, IOException {
        return request.POST("https://seguridadsanitaria.claro360.com/lineamientos/API/lineamientos/Registro/sucursales/nuevo_modulo", json);
    }

    @RequestMapping(value = "/API/Registro/Institucion/nuevo_modulo", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject registro_institucion_n_modulo(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("rregistro_institucion_n_modulo en: " + Dependencia);

        return request.POST("https://seguridadsanitaria.claro360.com/lineamientos/API/Registro/Institucion/nuevo_modulo", json);
    }

    @RequestMapping(value = "/API/empresas360/actualizacion_sucursal", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject actualizacion_sucursal(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("actualizacion_sucursal en: " + Dependencia);
        JSONObject respuesta = respuesta(false, "No se actualizo la sucursal");
        //Actualizamos los servicios por usuario

        JSONObject json2 = (JSONObject) json.clone(); //para actualizar lineamientos 

        json.put("nombre", json.get("nombre_edificio"));
        //json.put("idTipoUsuario",json.get("tipo_usuario"));

        JSONObject where = new JSONObject();
        where.put("id", json.get("id_institucion"));

        if (Query.update(Query.createQueryUpdateAND("servicios_usuario", json, where))) {

            respuesta = respuesta(true, "Sucursal actualizada. 1");
            JSONObject res = request.POST("https://seguridadsanitaria.claro360.com/lineamientos/API/lineamientos/actualizacion_sucursal", json2);
            if ((Boolean) res.get("success")) {
                respuesta = respuesta(true, "Sucursal actualizada. 2");
            }
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/baja_sucursal", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject baja_sucursal(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("baja_sucursal en: " + Dependencia);
        JSONObject respuesta = respuesta(false, "No se pudo dar de baja la sucursal. Intentelo de nuevo.");
        //Damos de baja la sucursar en empresas
        if (Query.update("UPDATE `servicios_usuario` SET `activo` = 0 WHERE `id` = '" + json.get("id_institucion") + "';")) {
            respuesta = respuesta(true, "sucursal dada de baja. 1");
            //Damos de baja de lineamientos
            JSONObject res = request.POST("https://seguridadsanitaria.claro360.com/lineamientos/API/lineamientos/baja_sucursal", json);
            if ((Boolean) res.get("success")) {
                respuesta = respuesta(true, "sucursal dada de baja. 2");
                //Damos de baja los modulos plataforma360, lineamientos y mapais
                JSONObject modulos = new JSONObject();
                JSONArray array = new JSONArray();
                array.add("plataforma360");
                array.add("lineamientos");
                array.add("mapagis");
                modulos.put("modulos", array);
                modulos.put("tipo_servicio", json.get("id_institucion"));
                JSONObject r = request.POST(config.getURL_CONTROLADOR() + "API/baja_modulos/tipo_servicio", modulos);
                if ((Boolean) r.get("success")) {
                    System.out.println("Respuesta de baja de modulos");
                    System.out.println(r);
                    respuesta = respuesta(true, "sucursal dada de baja. 3");
                }
            }
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/registro/tipo_area", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject registro_tipo_area(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("registro_tipo_area en: " + Dependencia);
        JSONObject respuesta = respuesta(false, "Error en realizar el registro del Área");
        //vALIDAR QUE EL AREA CON EL NOMBRE PROPORCIONADO NO EXISTA
        String query = "SELECT * FROM tipo_area WHERE area ='" + json.get("area") + "' AND tipo_usuario='" + json.get("tipo_usuario") + "' AND tipo_servicio='" + json.get("tipo_servicio") + "';";
        JSONObject tipo_area = Query.select(query);
        if (tipo_area == null) {
            int id = Query.insert(Query.createQueryInsert("tipo_area", json));
            if (id > 0) {
                respuesta = respuesta(true, "Área registrada correctamente");
                //Registrar el Grupo automatico...
                JSONObject grupo = new JSONObject();

                grupo.put("nombre", json.get("area"));
                grupo.put("estado", 3);
                grupo.put("tipo_usuario", json.get("tipo_usuario"));
                grupo.put("tipo_servicio", json.get("tipo_servicio"));
                grupo.put("tipo_area", id);
                Query.insert(Query.createQueryInsert("grupos_usuario_sys", grupo));

                query = "select nombre from servicios_usuario where id = '" + json.get("tipo_servicio") + "'";
                JSONObject nombre = Query.select(query);
                if (nombre != null) {
                    respuesta.put("nombre", nombre.get("nombre"));
                }
                respuesta.put("id", id);
                respuesta.putAll(json);
            }
        } else {
            respuesta = respuesta(false, "Ya esta un Área registrada con el mismo nombre.");
            respuesta.put("id", tipo_area.get("id"));
        }

        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/consulta_empleados", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject consulta_empleados(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("consulta_empleados en: " + Dependencia);

        return request.POST(config.getURL_CONTROLADOR() + "API/empresas360/consulta_empleados", json);
    }

    @RequestMapping(value = "/API/empresas360/estadisticos_empleados", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject estadisticos_empleados(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("estadisticos_empleados en: " + Dependencia);

        return request.POST(config.getURL_CONTROLADOR() + "API/empresas360/estadisticos_empleados", json);
    }

//    //servicio para activar la visibilidad de un centro de trabajo 
//    @RequestMapping(value = "/API/empresas360/servicios_usuario/visibilidad_app", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
//    @ResponseBody
//    public JSONObject servicios_usuario_visibilidad_app(@RequestBody JSONObject json) throws IOException, ParseException {
//        System.out.println("servicios_usuario_visibilidad_app en: " + Dependencia);
//        /*
//        {
//        "tipo_usuario":"",
//        "visible":0/1
//        "tipo_servicio":""
//        }
//        */
//        JSONObject respuesta = respuesta(false, "sucursal no encontrada");
//        //revisar que exista una sucursal dentro del tipo_servicio{
//        String query="SELECT id from servicios_usuario where id='"+json.get("tipo_servicio")+"' AND idTipoUsuario = '"+json.get("tipo_usuario")+"';";
//        if(Query.select(query)!=null){
//            //Revisar que la sucursal cuente con areas 
//            query = 
//        }
//        int servicio_usuario = Query.insert(Query.createQueryInsert("servicios_usuario", json));
//        if (servicio_usuario > 0) {
//            respuesta = respuesta(true, "Registro guardado correctamente");
//            respuesta.put("servicio_usuario", servicio_usuario);
//        }
//        return respuesta;
//    }
    @RequestMapping(value = "/API/empresas360/registro_area_servicio", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject registro_area_servicio(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("registro_area_servicio");
        JSONObject respuesta = respuesta(false, "Ocurrio un error intentelo de nuevo");
        /*
        {
            "id_servicio_usuario":"",
            "id_area":""
        }
         */
        int id = Query.insert(Query.createQueryInsert("areas_servicio", json));
        if (id > 0) {
            respuesta = respuesta(true, "Registro realizado correctamente");
            respuesta.put("id", id);
        }
        return respuesta;
    }

    /**
     * *****************************************************
     *******************************************************
     *
     * SERVICIO WEB PARA MODIFICAR UN PARÁMETRO
     *
     *******************************************************
     *******************************************************
     */
    /**
     * **
     * Servicio para modificar el parámetro de visibilidad en la app ***
     */
    @RequestMapping(value = "/API/empresas360/modifica/visibilidad_app", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject visibilidad_app(@RequestBody JSONObject json) {

        JSONObject respuesta = respuesta(false, "Error al actualizar el registro");

        String query = "UPDATE servicios_usuario SET  visibilidad_app = '" + json.get("visibilidad_app") + "' WHERE id = " + json.get("id");
        if (Query.update(query)) {
            respuesta = respuesta(true, "Registro actualizado correctamente");
        } else {
            respuesta = respuesta(true, "Sin edicion");
        }

        return respuesta;
    }

    /*
        Servicio para modificar el parámetro de vinculación
     */
    @RequestMapping(value = "/API/empresas360/modifica/vinculacion", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject vinculacion(@RequestBody JSONObject json) {

        JSONObject respuesta = respuesta(false, "Error al actualizar el registro");

        String query = "UPDATE servicios_usuario SET  vinculacion = '" + json.get("vinculacion") + "' WHERE id = " + json.get("id");
        if (Query.update(query)) {
            respuesta = respuesta(true, "Registro actualizado correctamente");
        } else {
            respuesta = respuesta(true, "Sin edicion");
        }

        return respuesta;
    }

    /*
        Servicio para modificar el parámetro de token_vinculacion
     */
    @RequestMapping(value = "/API/empresas360/modifica/token_vinculacion", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject token_vinculacion(@RequestBody JSONObject json) {

        JSONObject respuesta = respuesta(false, "Error al actualizar el registro");

        String query;

        if (json.get("token_vinculacion") == null || json.get("token_vinculacion") == "") {
            query = "UPDATE servicios_usuario SET  token_vinculacion = null WHERE idTipoUsuario = " + json.get("id");
        } else {
            query = "UPDATE servicios_usuario SET  token_vinculacion = '" + json.get("token_vinculacion") + "' WHERE id = " + json.get("id");
        }

        if (Query.update(query)) {
            respuesta = respuesta(true, "Registro actualizado correctamente");
        } else {
            respuesta = respuesta(true, "Sin edicion");
        }

        return respuesta;
    }

    /*
        Servicio para modificar el parámetro de lista_blanca
     */
    @RequestMapping(value = "/API/empresas360/modifica/lista_blanca", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject lista_blanca(@RequestBody JSONObject json) {

        JSONObject respuesta = respuesta(false, "Error al actualizar el registro");

        String query = "UPDATE servicios_usuario SET  lista_blanca = '" + json.get("lista_blanca") + "' WHERE id = " + json.get("id");
        if (Query.update(query)) {
            respuesta = respuesta(true, "Registro actualizado correctamente");
        } else {
            respuesta = respuesta(true, "Sin edicion");
        }

        return respuesta;
    }

    /*
        Servicio para modificar el parámetro de lista_blanca
     */
    @RequestMapping(value = "/API/empresas360/modifica/edicion_individual", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject edicion_individual(@RequestBody JSONObject json) {

        JSONObject respuesta = respuesta(false, "Error al actualizar el registro");

        String data = String.valueOf(json.get("data"));
        String[] trabajadores = data.split(",");

        String queryActiva = "UPDATE servicios_usuario SET  edicion_individual = 1 WHERE ";
        String queryDesactiva = "UPDATE servicios_usuario SET  edicion_individual = 0 WHERE ";

        int cantidadTrabajadores = trabajadores.length;
        for (int x = 0; x < cantidadTrabajadores; x++) {

            trabajadores[x] = trabajadores[x].trim().replaceAll("\\{", "").replaceAll("\\}", "");
            String[] datosTrabajador = trabajadores[x].split("=");
            String id = datosTrabajador[0];
            String edicion = datosTrabajador[1];

            if (edicion.equals("1")) {
                queryActiva += "id = " + id + " or ";
            } else if (edicion.equals("0")) {
                queryDesactiva += "id = " + id + " or ";
            }

        }

        queryActiva = queryActiva.substring(0, queryActiva.length() - 3);
        queryDesactiva = queryDesactiva.substring(0, queryDesactiva.length() - 3);
        if (Query.update(queryActiva) && Query.update(queryDesactiva)) {
            respuesta = respuesta(true, "Registro actualizado correctamente");
        } else {
            respuesta = respuesta(true, "Sin edicion");
        }

        respuesta.put("queryActiva", queryActiva);
        respuesta.put("queryDesactiva", queryDesactiva);

        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/update/empresa", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject update_empresa(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("update_empresa");
        JSONObject respuesta = respuesta(false, "Error al actualizar la información.");
        //realizar el update en tipo usuario 
        json.put("id", json.get("tipo_usuario"));
        json.put("tipo_usuario", json.get("empresa"));
        JSONObject where = new JSONObject();
        where.put("id", json.get("id"));
        System.out.println(json);
        //verificar que no el nombre de la empresa no exista 
        if (Query.select("SELECT id FROM tipos_usuarios where tipo_usuario='" + json.get("empresa") + "' and id!='" + json.get("id") + "';") == null) {
            System.out.println("Se procede a realizar la actualizacion");
            if (Query.update(Query.createQueryUpdateAND("tipos_usuarios", json, where))) {
                respuesta = respuesta(true, "Información de la empresa actualizada correctamente");
            }
            //realizar el update en empresa lineamientos 
            JSONObject update = request.POST("https://seguridadsanitaria.claro360.com/lineamientos/API/empresas360/update/empresa", json);
            System.out.println(update);
            if (Boolean.parseBoolean(update.get("success").toString())) {
                respuesta = respuesta(true, update.get("mensaje").toString());
            }

        } else {
            respuesta = respuesta(false, "No es posible actualizar el nombre de la empresa, ya existe otra empresa usando este nombre.");
        }

        return respuesta;
    }

    @RequestMapping(value = "/API/notificacion/llamada360/agregar_participante", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject notificacion_llamada360_agregar_participante(@RequestBody String json_string) throws IOException, ParseException {
        System.out.println("notificacion_llamada360_agregar_participante");
        /*
        {
            "id360":"",
            "to_id360":[{"id360":""},{"id360":""},...n]
        }
         */

        //Revisando usuarios en linea * notificar por id360-
        //solicitud de credenciales 
        //solicitud del ticket 
        //iteracion por cada participante 
        //estructurar data
        /*
            perfil del quien llama
            fecha y hora
            credenciales opentok
            datos de registro  ---- por si uno otra llamada nececito el id de la llamada 
                -- listado de participantes* 
         */
        //notificar por firebase  * No se si exista ---
        //notificar por socket    * notificar por id360
        //registramos llamadas de quien a quien 
        //regresar a quien realizo el request el estatus de a quien si notifico y a quien no , credenciales opentok
        JSONObject respuesta = respuesta(false, "Usuarios no notificados");

        //Solicitamos las credenciales
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(json_string);

//        JSONObject credenciales = (JSONObject) parser.parse(ControladorPOST.GeneraCredenciales("{}"));
        JSONObject credenciales = (JSONObject) json.get("credenciales");
        //Solicitamos el ticket al controlador
        json.put("fecha", Query.getFecha());
        json.put("hora", Query.getHora());
//        JSONObject ticket_settings = new JSONObject();
//        ticket_settings.put("origen", config.getPATH() + Dependencia);
//        ticket_settings.put("fecha", json.get("fecha"));
//        ticket_settings.put("hora", json.get("hora"));
//        JSONObject JsonTicket = request.POST(config.getURL_CONTROLADOR() + "API/ticket", ticket_settings);
        //Obtenemos el perfil de quien llama
        JSONObject perfil360 = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfil", json);
        //

        JSONArray ids = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfiles/array", (JSONArray) json.get("to_id360"));

        JSONObject data = new JSONObject();
        JSONObject RegistroLlamada = new JSONObject();
        data.put("credenciales", credenciales);
        data.put("emisor", json.get("id360"));
//        data.put("receptores", ids);
        RegistroLlamada.put("fecha", json.get("fecha"));
        RegistroLlamada.put("hora", json.get("hora"));
        RegistroLlamada.put("idLlamada", json.get("idLlamada"));
        data.put("registro_llamada", RegistroLlamada);

        data.put("llamada_multiplataforma", true);

        JSONObject data_moviles = (JSONObject) data.clone();
        data.put("emisor", perfil360);
        data.put("receptores", ids);

        boolean notificados = false;

        for (int i = 0; i < ids.size(); i++) {
            JSONObject id360 = (JSONObject) ids.get(i);
            System.out.println(id360);
            boolean id360_notificado = SocketEndPoint.EnviarNotificacio_id360(data, id360.get("id360").toString());
            if (id360_notificado) {
                notificados = true;
            }
            //Notificar por socket al id360
            id360.put("web", id360_notificado);
            //Notificar por firebase a id360
            data_moviles.put("firebasekey", id360.get("firebasekey"));
            System.out.println("Data para moviles ------->");
            System.out.println(data_moviles);
            id360.put("movil", request.POST(config.getURL_CONTROLADOR() + "API/moviles/notification/llamada_multiplataforma", data_moviles));
        }

        if (notificados) {
            respuesta = respuesta(true, "Notificación enviada");
        }
        respuesta.putAll(data);
        return respuesta;
    }

    @RequestMapping(value = "/API/notificacion/llamada360", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject notificacion_llamada360(@RequestBody String json_string) throws IOException, ParseException {
        System.out.println("notificacion_llamada360");
        /*
        {
            "id360":"",
            "to_id360":[{"id360":""},{"id360":""},...n]
        }
         */

        //Revisando usuarios en linea * notificar por id360-
        //solicitud de credenciales 
        //solicitud del ticket 
        //iteracion por cada participante 
        //estructurar data
        /*
            perfil del quien llama
            fecha y hora
            credenciales opentok
            datos de registro  ---- por si uno otra llamada nececito el id de la llamada 
                -- listado de participantes* 
         */
        //notificar por firebase  * No se si exista ---
        //notificar por socket    * notificar por id360
        //registramos llamadas de quien a quien 
        //regresar a quien realizo el request el estatus de a quien si notifico y a quien no , credenciales opentok
        JSONObject respuesta = respuesta(false, "Usuarios no notificados");

        //Solicitamos las credenciales
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(json_string);

        JSONObject credenciales = (JSONObject) parser.parse(ControladorPOST.GeneraCredenciales("{}"));
        //Solicitamos el ticket al controlador
        json.put("fecha", Query.getFecha());
        json.put("hora", Query.getHora());
        JSONObject ticket_settings = new JSONObject();
        ticket_settings.put("origen", config.getPATH() + Dependencia);
        ticket_settings.put("fecha", json.get("fecha"));
        ticket_settings.put("hora", json.get("hora"));
        JSONObject JsonTicket = request.POST(config.getURL_CONTROLADOR() + "API/ticket", ticket_settings);
        //Obtenemos el perfil de quien llama
        JSONObject perfil360 = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfil", json);
        //

        JSONArray ids = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfiles/array", (JSONArray) json.get("to_id360"));

        JSONObject data = new JSONObject();
        JSONObject RegistroLlamada = new JSONObject();
        data.put("credenciales", credenciales);
        data.put("emisor", json.get("id360"));
//        data.put("receptores", ids);
        RegistroLlamada.put("fecha", json.get("fecha"));
        RegistroLlamada.put("hora", json.get("hora"));
        RegistroLlamada.put("idLlamada", JsonTicket.get("ticket"));
        data.put("registro_llamada", RegistroLlamada);

        data.put("llamada_multiplataforma", true);

        JSONObject data_moviles = (JSONObject) data.clone();
        data.put("emisor", perfil360);
        data.put("receptores", ids);

        boolean notificados = false;

        for (int i = 0; i < ids.size(); i++) {
            JSONObject id360 = (JSONObject) ids.get(i);
            System.out.println(id360);
            boolean id360_notificado = SocketEndPoint.EnviarNotificacio_id360(data, id360.get("id360").toString());
            if (id360_notificado) {
                notificados = true;
            }
            //Notificar por socket al id360
            id360.put("web", id360_notificado);
            //Notificar por firebase a id360
            data_moviles.put("firebasekey", id360.get("firebasekey"));
            System.out.println("Data para moviles ------->");
            System.out.println(data_moviles);
            id360.put("movil", request.POST(config.getURL_CONTROLADOR() + "API/moviles/notification/llamada_multiplataforma", data_moviles));
        }

        if (notificados) {
            respuesta = respuesta(true, "Notificación enviada");
        }
        respuesta.putAll(data);
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/video_empleados", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray video_empleados(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("video_empleados");

        String query = "SELECT * FROM registro_jornada_laboral WHERE  date_created='"+Query.getFecha()+"' AND tipo_usuario='" + json.get("tipo_usuario") + "' AND tipo_servicio='" + json.get("tipo_servicio") + "' AND tipo_area='" + json.get("tipo_area") + "' AND activo =1";
        if (json.get("tipo_usuario").toString().equals("0") && json.get("tipo_servicio").toString().equals("0") && json.get("tipo_area").toString().equals("0")) {
            query = "SELECT * FROM registro_jornada_laboral WHERE  date_created='"+Query.getFecha()+"' AND activo =1; ";
        } else if (json.get("tipo_servicio").toString().equals("0") && json.get("tipo_area").toString().equals("0")) {
            query = "SELECT * FROM registro_jornada_laboral WHERE  date_created='"+Query.getFecha()+"' AND tipo_usuario='" + json.get("tipo_usuario") + "' AND activo =1;";
        } else if (json.get("tipo_area").toString().equals("0")) {
            query = "SELECT * FROM registro_jornada_laboral WHERE  date_created='"+Query.getFecha()+"' AND tipo_usuario='" + json.get("tipo_usuario") + "' AND tipo_servicio='" + json.get("tipo_servicio") + "' AND activo =1;";
        }

        JSONArray empleados = Query.execute(query);

        //Revisar los videos activo por el id de socket 
        for (int i = 0; i < empleados.size(); i++) {
            JSONObject empleado = (JSONObject) empleados.get(i);
            if (!SocketEndPoint.revisar_socket(empleado.get("id_socket").toString())&&false) {//Deshabilitar temporalmente por desarrollo 
                Query.update("UPDATE registro_jornada_laboral set activo=0 WHERE id='" + empleado.get("id") + "';");
                empleados.remove(empleado);
                i--;
            }
        }

        return empleados;
    }

    @RequestMapping(value = "/API/registro_invitacion", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject registro_invitacion(@RequestBody String jsonO) throws ParseException, IOException {
        System.out.println("registro_invitacion");
        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(jsonO);
        JSONObject respuesta = respuesta(true, "Proceso realizado correctamente.");
        //Se realiza el registro y envia de la invitacion
        System.out.println(json.size());
        boolean revisado = false;
        String empresa = "";
        String sucursal = "";
        JSONObject r = new JSONObject();
        for (int i = 0; i < json.size(); i++) {
            JSONArray hoja = (JSONArray) json.get(i);
            System.out.println(hoja);
            for (int j = 0; j < hoja.size(); j++) {
                JSONObject info_hoja = (JSONObject) hoja.get(j);
                System.out.println(info_hoja);
                if (!revisado) {
                    //Obtenemos el nombre de la empresa
                    empresa = (String) Query.select("SELECT tipo_usuario FROM tipos_usuarios WHERE id = '" + info_hoja.get("tipo_usuario") + "';").get("tipo_usuario");
                    sucursal = (String) Query.select("SELECT nombre FROM servicios_usuario WHERE id = '" + info_hoja.get("tipo_servicio") + "';").get("nombre");
                    //revisado = true;
                }
                info_hoja.put("empresa", empresa);
                info_hoja.put("sucursal", sucursal);

                JSONObject respuesta_individual = respuesta(false, "registro no realizado");
                /*Revisar que el tipo_servicio sea correspondiente al tipo_usuario*/
                if (Query.select("SELECT id FROM servicios_usuario WHERE id = '" + info_hoja.get("tipo_servicio") + "' AND idTipoUsuario = '" + info_hoja.get("tipo_usuario") + "';") != null) {

                    JSONObject registro_area = new JSONObject();
                    registro_area.put("area", info_hoja.get("area"));
                    registro_area.put("tipo_servicio", info_hoja.get("tipo_servicio"));
                    registro_area.put("tipo_usuario", info_hoja.get("tipo_usuario"));
                    registro_area = registro_tipo_area(registro_area);
                    int tipo_area = Integer.parseInt(registro_area.get("id").toString());
                    respuesta_individual = respuesta(true, "Registro realizado.");
                    respuesta_individual.put("area", registro_area);
                    //Revisamos si existe un area con el nombre descrito en area
                    //JSONObject resp = Query.select("SELECT id FROM tipo_area WHERE tipo_usuario = '" + info_hoja.get("tipo_usuario") + "' "
                    //+ "AND tipo_servicio='" + info_hoja.get("tipo_servicio") + "' AND area = '" + info_hoja.get("area") + "';");
                    //if (resp != null) {
                    //registramos la invitacion
                    //int tipo_area = Integer.parseInt(resp.get("id").toString());
                    info_hoja.put("tipo_area", tipo_area);

                    /*int id_invitacion = Query.insert(Query.createQueryInsertWithColumns("invitaciones", info_hoja));
                        if (id_invitacion > 0) {
                            r.put("Invitacion-" + id_invitacion, "Registrada con correo: " + info_hoja.get("correo"));
                        } else {
                            r.put("Invitacion-" + id_invitacion, "No se registro");
                        }*/
                    // solicitar codigo de canje
                    JSONObject id360 = new JSONObject();
                    id360.put("id360", info_hoja.get("id360"));
                    JSONObject codigo_canje = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/solicitud_codigo_canje", id360);
                    respuesta_individual.put("codigo", codigo_canje);
                    if ((Boolean) codigo_canje.get("success")) {
                        //Agremagamos el codigo y la base64 del QR
                        info_hoja.put("codigo", codigo_canje.get("codigo"));
                        info_hoja.put("qr_code", codigo_canje.get("qr_code"));
//                            info_hoja.put("qr_json", codigo_canje.get("qr_json"));

                        id360.put("codigo", codigo_canje.get("codigo"));
                        id360.put("descripcion", "Acceso a plataforma de empleado:" + empresa + " - " + sucursal);
                        id360.put("modulo", "plataforma360");
                        id360.put("correo", info_hoja.get("correo"));
                        JSONObject sistema = new JSONObject();
                        sistema.put("url", "https://empresas.claro360.com/plataforma360/");
                        sistema.put("alias", "Mi Empresa");
                        sistema.put("tipo_usuario", info_hoja.get("tipo_usuario"));
                        sistema.put("tipo_servicio", info_hoja.get("tipo_servicio"));
                        sistema.put("tipo_area", tipo_area);
                        sistema.put("modulos", "25");
                        sistema.put("modulo_principal", "dashboard");
                        sistema.put("canje", "1");

                        JSONObject usuario = new JSONObject();
                        usuario.put("nombre", "Nombre:text");
                        usuario.put("apellido_paterno", "Apellido Paterno:text");
                        usuario.put("apellido_materno", "Apellido Materno:text");
                        usuario.put("correo", "Correo:email");

                        JSONArray orden = new JSONArray();
                        orden.add("nombre");
                        orden.add("apellido_paterno");
                        orden.add("apellido_materno");
                        orden.add("correo");

                        JSONObject info_usuario = new JSONObject();
                        info_usuario.put("nombre", info_hoja.get("nombre"));
                        info_usuario.put("apellido_paterno", info_hoja.get("apellidopaterno"));
                        info_usuario.put("apellido_materno", info_hoja.get("apellidomaterno"));
                        info_usuario.put("correo", info_hoja.get("correo"));

                        JSONObject body = new JSONObject();

                        body.put("sistema", sistema);
                        body.put("usuario", usuario);
                        body.put("orden", orden);
                        body.put("info_usuario", info_usuario);
                        id360.put("body", body);

                        // agregar modulo de plataforma 360
                        respuesta_individual.put("plataforma360", request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/codigo_canje/agregar_modulo", id360));

                        id360.put("codigo", codigo_canje.get("codigo"));
                        id360.put("descripcion", "Vinculación a :" + empresa + " - " + sucursal);
                        id360.put("modulo", "empleados");
                        id360.put("correo", info_hoja.get("correo"));
                        sistema = new JSONObject();
//                            sistema.put("url", "https://empresas.claro360.com/plataforma360/");
//                            sistema.put("alias", "Mi Empresa");

                        sistema.put("tipo_usuario", info_hoja.get("tipo_usuario"));
                        sistema.put("tipo_servicio", info_hoja.get("tipo_servicio"));
                        sistema.put("tipo_area", tipo_area);

                        sistema.put("num_empleado", info_hoja.get("numerodeempleado"));
                        sistema.put("puesto", info_hoja.get("puesto"));
                        sistema.put("area", info_hoja.get("area"));

//                            sistema.put("modulos", "25");
//                            sistema.put("modulo_principal", "dashboard");
                        sistema.put("canje", "1");

                        usuario = new JSONObject();
                        usuario.put("nombre", "Nombre:text");
                        usuario.put("apellido_paterno", "Apellido Paterno:text");
                        usuario.put("apellido_materno", "Apellido Materno:text");
                        usuario.put("correo", "Correo:text");
                        usuario.put("horario_entrada", "Horario Entrada:time");
                        usuario.put("horario_salida", "Horario Salida:time");
                        usuario.put("tipo_actividad", "Actividad de trabajo:select");

                        orden = new JSONArray();
                        orden.add("nombre");
                        orden.add("apellido_paterno");
                        orden.add("apellido_materno");
                        orden.add("correo");
                        orden.add("tipo_actividad");
                        orden.add("horario_entrada");
                        orden.add("horario_salida");

                        JSONObject opciones = new JSONObject();

                        JSONArray tipo_actividad = new JSONArray();

                        tipo_actividad.add((JSONObject) parser.parse("{\"valor\":\"Administrativa\",\"texto\":\"Administrativa\"}"));
                        tipo_actividad.add((JSONObject) parser.parse("{\"valor\":\"Operativa\",\"texto\":\"Operativa\"}"));
                        tipo_actividad.add((JSONObject) parser.parse("{\"valor\":\"Médico\",\"texto\":\"Médico\"}"));
                        tipo_actividad.add((JSONObject) parser.parse("{\"valor\":\"Operador de transporte\",\"texto\":\"Operador de transporte\"}"));
                        tipo_actividad.add((JSONObject) parser.parse("{\"valor\":\"Otro\",\"texto\":\"Otro\"}"));

                        opciones.put("tipo_actividad", tipo_actividad);

                        info_usuario = new JSONObject();
                        info_usuario.put("nombre", info_hoja.get("nombre"));
                        info_usuario.put("apellido_paterno", info_hoja.get("apellidopaterno"));
                        info_usuario.put("apellido_materno", info_hoja.get("apellidomaterno"));
                        info_usuario.put("correo", info_hoja.get("correo"));

                        body = new JSONObject();

                        body.put("sistema", sistema);
                        body.put("usuario", usuario);
                        body.put("orden", orden);
                        body.put("opciones", opciones);
                        body.put("info_usuario", info_usuario);
                        id360.put("body", body);

                        // agregar modulo de empleado
                        respuesta_individual.put("empleados", request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/codigo_canje/agregar_modulo", id360));

                        id360.put("codigo", codigo_canje.get("codigo"));
                        id360.put("descripcion", "Activación de perfil de condición de salud:");
                        id360.put("modulo", "perfil");
                        id360.put("titulo", "¿Actualmente presentas algún padecimiento enlistado a continuación?\nPuedes seleccionar ninguna, una o varias respuestas.");
                        id360.put("correo", info_hoja.get("correo"));
                        sistema = new JSONObject();

                        usuario = new JSONObject();
                        usuario.put("cancer", "Cancer:checkbox");
                        usuario.put("enfermedad_corazon", "Enfermedad del Corazón:checkbox");
                        usuario.put("enfermedad_renal", "Enfermedad Renal:checkbox");
                        usuario.put("inmunopresion", "Inmunosupresión:checkbox");
                        usuario.put("tabaquismo", "Tabaquismo:checkbox");
                        usuario.put("diabetes", "Diabetes:checkbox");
                        usuario.put("enfermedad_pulmonar", "Enfermedad pulmonar y/o asma:checkbox");
                        usuario.put("hipertension", "Hipertensión:checkbox");
                        usuario.put("obesidad", "Obesidad:checkbox");
                        usuario.put("ninguna", "Ninguna:checkbox");
                        usuario.put("embarazada", "¿Estás embarazada?:checkbox");
                        usuario.put("donador_organos", "¿Eres Donador de Organos?:checkbox");
                        usuario.put("padecimientos_medicos", "Pacedimientos Médicos:checkbox");
                        usuario.put("peso", "Peso:number");
                        usuario.put("estatura", "Estatura:number");

                        orden = new JSONArray();
                        orden.add("cancer");
                        orden.add("enfermedad_corazon");
                        orden.add("enfermedad_renal");
                        orden.add("inmunopresion");
                        orden.add("tabaquismo");
                        orden.add("diabetes");
                        orden.add("enfermedad_pulmonar");
                        orden.add("hipertension");
                        orden.add("obesidad");
                        orden.add("ninguna");
                        orden.add("embarazada");
                        orden.add("donador_organos");
                        orden.add("padecimientos_medicos");
                        orden.add("peso");
                        orden.add("estatura");

                        info_usuario = new JSONObject();
                        info_usuario.put("nombre", info_hoja.get("nombre"));
                        info_usuario.put("apellido_paterno", info_hoja.get("apellidopaterno"));
                        info_usuario.put("apellido_materno", info_hoja.get("apellidomaterno"));
                        info_usuario.put("correo", info_hoja.get("correo"));

                        body = new JSONObject();

                        body.put("sistema", sistema);
                        body.put("usuario", usuario);
                        body.put("orden", orden);
                        body.put("info_usuario", info_usuario);
                        id360.put("body", body);

                        // agregar modulo de perfil
                        respuesta_individual.put("perfil", request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/codigo_canje/agregar_modulo", id360));

                    }
                } else {

                    //eliminar el json del array json
                    info_hoja.put("no_procede", true);
                    respuesta_individual = respuesta(false, "El número de sucursal proporcionado no esta asociado a su cuenta.");

                }

                respuesta.put(info_hoja.get("numerodeempleado"), respuesta_individual);
                /**
                 * *****************************************************************
                 */

            }
        }
        System.out.println(respuesta);
        System.out.println(json);
        Thread newThread = new Thread(() -> {
            try {
                System.out.println("Inicia proceso de envio de correos");

                request.POST(config.getURL_CONTROLADOR() + "API/empresas360/correo_masivo", json);
            } catch (Exception ex) {
                System.out.println("Error: Yo no deberia fallar T_T");
                System.out.println(ex);
            }
        });
        newThread.start();

        return respuesta;
    }

    private String enviar_mails(JSONArray json2) {
        Thread newThread = new Thread(() -> {
            System.out.println("Inicia proceso de envio de correos");
            //Se realiza el registro y envia de la invitacion
            for (int i = 0; i < json2.size(); i++) {
                JSONArray hoja = (JSONArray) json2.get(i);
                for (int j = 0; j < hoja.size(); j++) {
                    JSONObject info_hoja = (JSONObject) hoja.get(j);
                    //Envio de correo
                    JSONObject datos = new JSONObject();
                    datos.put("nombre", info_hoja.get("nombre") + " " + info_hoja.get("apellidopaterno") + " " + info_hoja.get("apellidomaterno"));
                    datos.put("correo", info_hoja.get("correo"));
                    datos.put("codigo", info_hoja.get("codigo"));
                    datos.put("qr_code", info_hoja.get("qr_code"));
                    datos.put("empresa", info_hoja.get("empresa"));
                    datos.put("sucursal", info_hoja.get("sucursal"));
                    System.out.println("datos");
                    System.out.println(datos);
                    try {
                        System.out.println("enviando correo");
                        request.POST(config.getURL_CONTROLADOR() + "API/correo/invitacion_app360", datos);
                    } catch (IOException | ParseException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
        newThread.start();
        return "ok";
    }

    /**
     * *********************************** Estadisticos empleados
     * *************************************
     */
    @RequestMapping(value = "/API/empresas360/estadisticos_horario_empleados", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject empresas360_estadisticos_empleados(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        /*
        {
            "id360":""
        }
         */
        JSONObject respuesta = respuesta(false, "Usuario no encontrado");
        JSONObject perfil = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfil/empleado", json);
        if ((Boolean) perfil.get("success")) {
            respuesta = respuesta(true, "Usuario encontrado");
            respuesta.put("horario_entrada", perfil.get("horario_entrada"));
            respuesta.put("horario_salida", perfil.get("horario_salida"));
            respuesta.put("genero", perfil.get("genero"));
            respuesta.put("puesto", perfil.get("puesto"));
            respuesta.put("area", perfil.get("area"));
            double promedio_asistencia;
            double promedio_entrada;
            double promedio_salida;
            JSONObject primera_conexion = Query.select("SELECT date_created,time_created,date_updated,time_updated FROM registro_jornada_laboral WHERE id_usuario = '" + json.get("id360") + "' limit 1;");
            JSONObject ultima_conexion = Query.select("SELECT date_created,time_created,date_updated,time_updated FROM registro_jornada_laboral WHERE id_usuario = '" + json.get("id360") + "' ORDER BY id DESC LIMIT 1;");
            JSONObject total_conexiones = Query.select("SELECT count(id) as total FROM registro_jornada_laboral WHERE id_usuario = '" + json.get("id360") + "';");
            SimpleDateFormat date_format = new SimpleDateFormat("yyyy-M-dd");
            if (primera_conexion != null) {
                Date pc = date_format.parse(primera_conexion.get("date_created").toString());
                Date uc = date_format.parse(Query.getFecha());
                Calendar cI = Calendar.getInstance();
                Calendar cF = Calendar.getInstance();
                cI.setTime(pc);
                cF.setTime(uc);
                Query q = new Query();
                //Hacemos el calculo de dias habiles entre la fecha de primera conexion y la ultima conexion,
                //contando como dia habil la fecha de inicio en caso de que lo sea
                int dias_habiles = q.getDiasHabiles(cI, cF);
                //Calculamos el promedio de asistencia
                int tc = Integer.parseInt(total_conexiones.get("total").toString());
                promedio_asistencia = (tc / dias_habiles) * 100;
                respuesta.put("promedio_asistencia", promedio_asistencia + "%");
                //Obtenemos todas la conexiones del usuario
                JSONArray jornada_laboral = Query.execute("SELECT date_created,time_created,date_updated,time_updated FROM registro_jornada_laboral WHERE id_usuario = '" + json.get("id360") + "';");
                int antes_o_igual_hora_inicio = 0;
                int despues_hora_inicio = 0;
                int antes_hora_fin = 0;
                int despues_o_igual_hora_fin = 0;
                for (int i = 0; i < jornada_laboral.size(); i++) {
                    JSONObject jornada = (JSONObject) jornada_laboral.get(i);
                    if (q.comparaFechas(jornada.get("date_created") + " " + jornada.get("time_created"), jornada.get("date_created") + " " + perfil.get("horario_entrada")).equals("Antes")) {
                        antes_o_igual_hora_inicio += 1;
                    } else if (q.comparaFechas(jornada.get("date_created") + " " + jornada.get("time_created"), jornada.get("date_created") + " " + perfil.get("horario_entrada")).equals("Exacto")) {
                        antes_o_igual_hora_inicio += 1;
                    } else if (q.comparaFechas(jornada.get("date_created") + " " + jornada.get("time_created"), jornada.get("date_created") + " " + perfil.get("horario_entrada")).equals("Despues")) {
                        despues_hora_inicio += 1;
                    }

                    if (jornada.get("date_updated") != null && jornada.get("time_updated") != null) {
                        if (q.comparaFechas(jornada.get("date_updated") + " " + jornada.get("time_updated"), jornada.get("date_created") + " " + perfil.get("horario_salida")).equals("Antes")) {
                            antes_hora_fin += 1;
                        } else if (q.comparaFechas(jornada.get("date_updated") + " " + jornada.get("time_updated"), jornada.get("date_created") + " " + perfil.get("horario_salida")).equals("Exacto")) {
                            despues_o_igual_hora_fin += 1;
                        } else if (q.comparaFechas(jornada.get("date_updated") + " " + jornada.get("time_updated"), jornada.get("date_created") + " " + perfil.get("horario_salida")).equals("Despues")) {
                            despues_o_igual_hora_fin += 1;
                        }
                    }
                }
                promedio_entrada = antes_o_igual_hora_inicio - despues_hora_inicio;
                promedio_entrada = promedio_entrada / tc;
                promedio_entrada = promedio_entrada * 100;
                promedio_salida = despues_o_igual_hora_fin - antes_hora_fin;
                promedio_salida = promedio_salida / tc;
                promedio_salida = promedio_salida * 100;
                respuesta.put("promedio_entrada", String.format("%.2f", promedio_entrada) + "%");
                respuesta.put("promedio_salida", String.format("%.2f", promedio_salida) + "%");
                respuesta.put("total_conexiones", tc);
                respuesta.put("antes_o_igual_hora_inicio", antes_o_igual_hora_inicio);
                respuesta.put("despues_hora_inicio", despues_hora_inicio);
                respuesta.put("despues_o_igual_hora_fin", despues_o_igual_hora_fin);
                respuesta.put("antes_hora_fin", antes_hora_fin);
            }
        }
        return respuesta;
    }

    /**
     * Guardado de minuta *
     */
    @RequestMapping(value = "/API/empresas360/guardar_minuta_llamada_saliente", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject guardar_minuta(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("guardar_minuta");
        JSONObject respuesta = respuesta(false, "Algo salio mal. Intentelo de nuevo");
        if (json.containsKey("id_minuta")) {
            //se hace un update
            JSONObject where = new JSONObject();
            where.put("id", json.get("id_minuta"));
            if (Query.update(Query.createQueryUpdateAND("minutas_llamadas_salientes", json, where))) {
                respuesta = respuesta(true, "Minuta actualizada con exito.");
                respuesta.put("id_minuta", json.get("id_minuta"));
            }
        } else {
            //se hace el insert
            int id_minuta = Query.insert(Query.createQueryInsert("minutas_llamadas_salientes", json));
            if (id_minuta > 0) {
                respuesta = respuesta(true, "Minuta guardada con exito.");
                respuesta.put("id_minuta", id_minuta);
            }
        }

        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/modulo_videowall/stats", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject modulo_videowall_stats(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        System.out.println("modulo_videowall_stats");
        JSONObject respuesta = respuesta(true, "Informacion recabada");

        String query = "SELECT idUsuario AS id360 FROM directorio where tipo_usuario='" + json.get("tipo_usuario") + "' AND tipo_servicio='" + json.get("tipo_servicio") + "' AND tipo_area = '" + json.get("tipo_area") + "' AND activo=1;";
        if (json.get("tipo_servicio").toString().equals("0") && json.get("tipo_area").toString().equals("0")) {
            query = "SELECT idUsuario AS id360 FROM directorio where tipo_usuario='" + json.get("tipo_usuario") + "' AND activo=1;";
        } else if (json.get("tipo_area").toString().equals("0")) {
            query = "SELECT idUsuario AS id360 FROM directorio where tipo_usuario='" + json.get("tipo_usuario") + "' AND tipo_servicio='" + json.get("tipo_servicio") + "' AND activo=1;";
        }

        JSONArray total = Query.execute(query);
        respuesta.put("total", total.size());
        JSONArray empleados = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfiles/array", total);
        int empleados_descansando = 0;
        int empleados_laborando = 0;
        for (Iterator it = empleados.iterator(); it.hasNext();) {
            JSONObject empleado = (JSONObject) it.next();
            empleado.put("is_active", false);
            if ((Boolean) empleado.get("success")) {
                if (empleado.containsKey("horario_entrada") && empleado.containsKey("horario_salida")) {
                    //validar que el empleado este dentro de si jornada laboral 
                    String hora_inicio = empleado.get("horario_entrada").toString();
                    String hora_fin = empleado.get("horario_salida").toString();
                    DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
                    Date date = new Date();
                    Date hour_inicio = hourFormat.parse(hora_inicio);
                    Date hour_fin = hourFormat.parse(hora_fin);

                    String hora = hourFormat.format(date);

                    Date hour = hourFormat.parse(hora);

                    System.out.println(hour_inicio);
                    System.out.println(hour);
                    System.out.println(hour_fin);

                    System.out.println(hour.after(hour_inicio));
                    System.out.println(hour.before(hour_fin));

                    if (hour.after(hour_inicio) && hour.before(hour_fin)) {
                        empleado.put("is_active", true);
                        empleados_laborando++;
                    } else {
                        empleados_descansando++;
                    }

                } else {
                    empleados_descansando++;
                }
            } else {
                empleados_descansando++;
            }
        }
        respuesta.put("activos", empleados_laborando);
        respuesta.put("inactivos", empleados_descansando);
        respuesta.put("empleados", empleados);
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/chat", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject empresas360_chat(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        System.out.println("empresas360_chat");
        JSONObject respuesta = respuesta(false, "Informacion no almacenada");
        int resultSend = Query.insert(Query.createQueryInsertWithColumns("chat_empresarial", json));
        if (resultSend>=0) {
            respuesta = respuesta(true,"Mensaje guardado");
            respuesta.putAll(json);
            //Enviar por socket
            json.put("chat_empresarial", true);
            json.put("id",resultSend);
            SocketEndPoint.EnviarNotificacio_id360(json, (String) json.get("to_id360"));
        }
        return respuesta;
    }
    
    /*
    SERVICIO WEB PARA ELIMINAR MENSAJE DE UN CHAT
    */
    @RequestMapping(value = "/API/empresas360/eliminaMensaje", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject empresas360_eliminaMensaje(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        System.out.println("Eliminando mensaje...");
        JSONObject respuesta = respuesta(false, "Mensaje no eliminado");
        
        String query = "UPDATE chat_empresarial SET activo = 0, activo_id360 = 0, activo_to_id360 = 0 WHERE id = " + json.get("idMensaje");
        
        if (Query.update(query)) {
            respuesta = respuesta(true,"Mensaje eliminado");
            respuesta.putAll(json);
            //Enviar por socket
            json.put("eliminacion_mensaje_chat_empresarial", true);
            SocketEndPoint.EnviarNotificacio_id360(json, (String) json.get("to_id360"));
        }
        
        return respuesta;
    }
    
    @RequestMapping(value = "/API/empresas360/eliminaMensajeParaMi", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject empresas360_eliminaMensajeMio(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        System.out.println("Eliminando mensaje...");
        JSONObject respuesta = respuesta(false, "Mensaje no eliminado");
        
        String query = "UPDATE chat_empresarial SET activo_id360 = 0 WHERE id = " + json.get("idMensaje");
        
        if (Query.update(query)) {
            respuesta = respuesta(true,"Mensaje eliminado");
            respuesta.putAll(json);
            //Enviar por socket
            //json.put("eliminacion_mensaje_chat_empresarial_mio", true);
            //SocketEndPoint.EnviarNotificacio_id360(json, (String) json.get("to_id360"));
        }
        
        return respuesta;
    }
    
    /*
    NUEVO BACKUP, SOLO RECUPERAR 20 MENSAJES DE CADA USUARIO CON CHAT
    */
    @RequestMapping(value = "/API/empresas360/backup_chat", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray empresas360_backup_chat(@RequestBody JSONObject json){
        
        String query = ""
                + "SELECT" +
                "	replace(concat(p.id360,p.to_id360),'"+json.get("id360")+"','') as id360Chat," +
                "	p.*" +
                "FROM" +
                "  chat_empresarial p" +
                "  " +
                "  INNER JOIN (" +
                "		select" +
                "			replace(concat(p.id360,p.to_id360),'"+json.get("id360")+"','') as id360Chat," +
                "			group_concat( id order by date_created desc ) idsMessages" +
                "		from chat_empresarial p" +
                "		where (p.id360 = '"+json.get("id360")+"' OR p.to_id360 = '"+json.get("id360")+"')" +
                "               and (activo != 0)" +
                "		group by id360Chat" +
                "  ) messages" +
                "  ON replace(concat(p.id360,p.to_id360),'"+json.get("id360")+"','') = messages.id360Chat" +
                "  " +
                "  AND FIND_IN_SET(id, idsMessages) BETWEEN 1 AND 20" +
                "		" +
                "ORDER BY" +
                "       p.id;";
        
        System.out.println(query);
        JSONArray ids = Query.execute(query);
        return ids;
    }
    
    @RequestMapping(value = "/API/empresas360/carga_mas_mensajes_chat", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray carga_mas_mensajes_chat(@RequestBody JSONObject json){
        
        String query = "select * from chat_empresarial " +
                        "where (id360 = '"+json.get("id360-1")+"' and to_id360 = '"+json.get("id360-2")+"') " +
                        "or (id360 = '"+json.get("id360-2")+"' and to_id360 = '"+json.get("id360-1")+"') " +
                        "limit "+json.get("init")+","+json.get("limit")+";";
        
        JSONArray ids = Query.execute(query);
        return ids;
    }
    
    @RequestMapping(value = "/API/empresas360/usuarios_con_chat", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray usuarios_con_chat(@RequestBody JSONObject json){
        //String query = "select distinct replace(concat(id360,to_id360),'"+json.get("id360")+"','') as id360 from chat_empresarial where (id360 = '"+json.get("id360")+"' OR to_id360 = '"+json.get("id360")+"');";
        String query = "select count(*) as cantidadMensajes, " +
                        "replace(concat(id360,to_id360),'"+json.get("id360")+"','') as id360chat " +
                        "from chat_empresarial where (id360 = '"+json.get("id360")+"' OR to_id360 = '"+json.get("id360")+"') " +
                        "group by id360chat;";
        JSONArray ids = Query.execute(query);
        return ids;
    }
    
    @RequestMapping(value = "/API/empresas360/directorio/un_usuario", method = RequestMethod.POST)
    @ResponseBody
    public String directorio_un_usuario(@RequestBody String json) throws IOException, ParseException {
       
        return request.POST("https://plataforma911.ml/CONTROLADOR/API/cuenta360/perfiles/array", json);
        
    }
    
    /*
    
    SERVICIOS PARA CONSULTAR EL HISTORIAL DE JORNADAS LABORALES
    
    */
    
    @RequestMapping(value = "/API/empresas360/jornadas_laborales", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject jornadas_laborales_e(@RequestBody JSONObject json) {
        
        JSONObject respuesta = respuesta(false, "Error en la consulta");
        
        respuesta.put("data", json);
        
        String inicio = (String) json.get("inicio");
        String fin = (String) json.get("fin");
        
        String query = "";
        
        if( "".equals(fin) ){
            query = "SELECT * FROM registro_jornada_laboral where id_usuario = " + json.get("id") + " AND date_created = '"+json.get("inicio")+"'";
        }else{
            query = "SELECT * FROM empresas.directorio d LEFT JOIN empresas.registro_jornada_laboral r ON r.id_usuario = d.idUsuario where d.idUsuario = " + json.get("id");
        }
        
        JSONArray data = Query.execute(query);
        System.out.println(data);
        if (data != null && data.size()>0) {
            respuesta = respuesta(true, "ok");
            respuesta.put("data",data);
        } else {
            respuesta = respuesta(false, "Sin informacion");
        }
        
        return respuesta;
        
    }
    
    @RequestMapping(value = "/API/empresas360/jornadas_laborales/empresa", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject jornadas_laborales_empresa(@RequestBody JSONObject json) {
        
        JSONObject respuesta = respuesta(false, "Error en la consulta");
        
        respuesta.put("data", json);
        
        String inicio = (String) json.get("inicio");
        String fin = (String) json.get("fin");
        
        String query = "";
        
        if( "".equals(fin) ){
            query = "SELECT * FROM empresas.registro_jornada_laboral WHERE tipo_usuario = " + json.get("id") + " AND date_created = '"+json.get("inicio")+"' ORDER BY id_usuario";
        }else{
            //query = "SELECT * FROM empresas.directorio d LEFT JOIN empresas.registro_jornada_laboral r ON r.id_usuario = d.idUsuario where d.tipo_usuario = " + json.get("id") + " AND r.date_created >= '"+inicio+"' AND r.date_updated <= '"+fin+"' ORDER BY r.id_usuario";
            query = "SELECT * FROM empresas.directorio d LEFT JOIN empresas.registro_jornada_laboral r ON r.id_usuario = d.idUsuario where d.tipo_usuario = " + json.get("id") + " ORDER BY r.id_usuario";
        }
        
        JSONArray data = Query.execute(query);
        System.out.println(data);
        if (data != null && data.size()>0) {
            respuesta = respuesta(true, "ok");
            respuesta.put("data",data);
            respuesta.put("query",query);
        } else {
            respuesta = respuesta(false, "Sin informacion");
            respuesta.put("query", query);
        }
        
        return respuesta;
        
    }
    
    @RequestMapping(value = "/API/empresas360/jornadas_laborales/sucursal", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject jornadas_laborales_sucursal(@RequestBody JSONObject json) {
        
        JSONObject respuesta = respuesta(false, "Error en la consulta");
        
        respuesta.put("data", json);
        
        String inicio = (String) json.get("inicio");
        String fin = (String) json.get("fin");
        
        String query = "";
        
        if( "".equals(fin) ){
            query = "SELECT * FROM empresas.registro_jornada_laboral WHERE tipo_usuario = " + json.get("id") + " AND date_created = '"+json.get("inicio")+"' ORDER BY id_usuario";
        }else{
            //query = "SELECT * FROM empresas.directorio d LEFT JOIN empresas.registro_jornada_laboral r ON r.id_usuario = d.idUsuario where d.tipo_usuario = " + json.get("id") + " AND r.date_created >= '"+inicio+"' AND r.date_updated <= '"+fin+"' ORDER BY r.id_usuario";
            query = "SELECT * FROM empresas.directorio d LEFT JOIN empresas.registro_jornada_laboral r ON r.id_usuario = d.idUsuario where d.tipo_servicio = " + json.get("id") + " ORDER BY r.id_usuario";
        }
        
        JSONArray data = Query.execute(query);
        System.out.println(data);
        if (data != null && data.size()>0) {
            respuesta = respuesta(true, "ok");
            respuesta.put("data",data);
            respuesta.put("query",query);
        } else {
            respuesta = respuesta(false, "Sin informacion");
            respuesta.put("query", query);
        }
        
        return respuesta;
        
    }
    
    @RequestMapping(value = "/API/empresas360/jornadas_laborales/area", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject jornadas_laborales_area(@RequestBody JSONObject json) {
        
        JSONObject respuesta = respuesta(false, "Error en la consulta");
        
        respuesta.put("data", json);
        
        String inicio = (String) json.get("inicio");
        String fin = (String) json.get("fin");
        
        String query = "";
        
        if( "".equals(fin) ){
            query = "SELECT * FROM empresas.registro_jornada_laboral WHERE tipo_usuario = " + json.get("id") + " AND date_created = '"+json.get("inicio")+"' ORDER BY id_usuario";
        }else{
            //query = "SELECT * FROM empresas.directorio d LEFT JOIN empresas.registro_jornada_laboral r ON r.id_usuario = d.idUsuario where d.tipo_usuario = " + json.get("id") + " AND r.date_created >= '"+inicio+"' AND r.date_updated <= '"+fin+"' ORDER BY r.id_usuario";
            query = "SELECT * FROM empresas.directorio d LEFT JOIN empresas.registro_jornada_laboral r ON r.id_usuario = d.idUsuario where d.tipo_area = " + json.get("id") + " ORDER BY r.id_usuario";
        }
        
        JSONArray data = Query.execute(query);
        System.out.println(data);
        if (data != null && data.size()>0) {
            respuesta = respuesta(true, "ok");
            respuesta.put("data",data);
            respuesta.put("query",query);
        } else {
            respuesta = respuesta(false, "Sin informacion");
            respuesta.put("query", query);
        }
        
        return respuesta;
        
    }
    
    @RequestMapping(value = "/API/empresas360/jornadas_laborales/empresa/obtener_ids", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray jornadas_laborales_empresa_obtener_ids(@RequestBody JSONObject json) {
        String query = "SELECT d.idUsuario as id360, tu.razon_social as empresa, su.nombre as sucursal, ta.area FROM directorio d LEFT JOIN tipos_usuarios tu ON d.tipo_usuario = tu.id LEFT JOIN servicios_usuario su ON d.tipo_servicio = su.id LEFT JOIN tipo_area ta ON d.tipo_area = ta.id WHERE d.tipo_usuario = " + json.get("id");
        return Query.execute(query);
    }
    
    @RequestMapping(value = "/API/empresas360/jornadas_laborales/empresa/obtener_empleados", method = RequestMethod.POST)
    @ResponseBody
    public String jornadas_laborales_empresa_obtener_empleados(@RequestBody String json) throws IOException, ParseException {
       
        return request.POST("https://plataforma911.ml/CONTROLADOR/API/cuenta360/perfiles/array", json);
        
    }
    
}
