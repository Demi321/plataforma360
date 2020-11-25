/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Config.config;
import static Encriptacion.Main.generateString;
import Modelo.Query;
import java.io.IOException;
import javax.swing.text.html.HTML;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author web
 */
@Controller
@Async
public class ControladorReferenciasHospitalarias {

    private static String Dependencia = config.getDEPENDENCIA();

    private JSONObject respuesta(boolean success, String message) {
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", success);
        respuesta.put("failure", !success);
        respuesta.put("mensaje", message);
        return respuesta;
    }

    @RequestMapping(value = "/API/referencias_hospitalarias/solicitud_traslado", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject referencias_hospitalarias_solicitud_traslado(@RequestBody String jsonString) throws IOException, ParseException {
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
        String query = null;

        JSONObject registro = new JSONObject();
        registro = (JSONObject) json.clone();
        registro.remove("biometria_hematica");
        registro.remove("quimica_sanguinea");
        registro.remove("electrolitos_sericos");
        registro.remove("funcion_hepatica");
        registro.remove("tiempos_coagulacion");
        registro.remove("gasometria_arterial");

        registro.putAll(biometria_hematica);
        registro.putAll(quimica_sanguinea);
        registro.putAll(electrolitos_sericos);
        registro.putAll(gasometria_arterial);
        registro.putAll(funcion_hepatica);
        registro.putAll(tiempos_coagulacion);

        System.out.println(registro);
        int id = Query.insert(Query.createQueryInsert("referencias_hospitalarias", registro));
        System.out.println(id);

        //crear el registro eventos 
        if (id > 0) {

            //agregamos el registro e los cambios del evento 
            JSONObject e = new JSONObject();
            e.put("id_referencia_hospitalaria", id);
            e.put("id_estado_referencia_hospitalaria", "1"); //como se acava de realizar la solicitud le corresponde el estado 1
            e.put("tipo_usuario", json.get("tipo_usuario"));
            e.put("tipo_servicio", json.get("tipo_servicio"));
            e.put("to_tipo_usuario_institucion", json.get("to_tipo_usuario_institucion"));
            e.put("to_tipo_servicio_institucion", json.get("to_tipo_servicio_institucion"));
            System.out.println("e");
            System.out.println(e);
            Query.insert(Query.createQueryInsert("eventos_referencias_hospitalarias", e));

            json.put("procede", true);
            //Notificar institucion
            json.put("estado", "1");//como se acava de realizar la solicitud le corresponde el estado 1

            json.put("estado_label", "Solicitud enviada desde: " + Query.select("SELECT nombre FROM servicios_usuario WHERE id = '" + json.get("tipo_servicio") + "';").get("nombre"));//como se acava de realizar la solicitud le corresponde el estado 1
            json.put("nombre_estado", Query.select("SELECT nombre FROM estados_referencias_hospitalarias WHERE id = 1;").get("nombre"));
            json.put("solicitudes_referencia_hospitalaria", true);
            json.put("id", id);
            SocketEndPoint.EnviarNotificacion(json);

            //Notificar a ccb mediante socket a la institucion receptora
            json.put("estado_label", "Solicitud recibida por: " + Query.select("SELECT nombre FROM servicios_usuario WHERE id = '" + json.get("tipo_servicio") + "';").get("nombre"));
            json.put("tipo_usuario", json.get("to_tipo_usuario_institucion"));
            json.put("tipo_servicio", json.get("to_tipo_servicio_institucion"));
            SocketEndPoint.EnviarNotificacion(json);

        } else {
            json.put("procede", false);
            json.put("mensaje", "Algo ocurrio al intentar procesar tu solicitud."
                    + "Intentalo nuevamente.");
        }

        return json;
    }

    @RequestMapping(value = "/API/referencias_hospitalarias/cancelar_solicitud", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject referencias_hospitalarias_cancelar_solicitud(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("referencias_hospitalarias_cancelar_solicitud en: " + Dependencia);
        /*
            "id_solicitud"
         */
        JSONObject respuesta = respuesta(false, "La solicitud no se cancelo. Intentelo de nuevo.");
        String query = "UPDATE eventos_referencias_hospitalarias SET id_estado_referencia_hospitalaria = '4' WHERE id_referencia_hospitalaria = '" + json.get("id_solicitud") + "';";
        if (Query.update(query)) {

            JSONObject evento = Query.select("SELECT * FROM eventos_referencias_hospitalarias WHERE id_referencia_hospitalaria = '" + json.get("id_solicitud") + "';");
            respuesta = respuesta(true, "Solicitud cancelada por: " + Query.select("SELECT nombre FROM servicios_usuario WHERE id = '" + evento.get("tipo_servicio") + "';").get("nombre"));
            respuesta.put("referencia_hospitalaria_update", true);
            respuesta.put("id", json.get("id_solicitud"));
            respuesta.put("estado", "4");
            respuesta.put("nombre_estado", Query.select("SELECT nombre FROM estados_referencias_hospitalarias WHERE id = 4;").get("nombre"));
            if (evento.get("tipo_usuario") != null && evento.get("tipo_servicio") != null) {
                respuesta.put("tipo_usuario", evento.get("tipo_usuario"));
                respuesta.put("tipo_servicio", evento.get("tipo_servicio"));
                SocketEndPoint.EnviarNotificacion(respuesta);
            }
            if (evento.get("tipo_usuario_institucion") != null && evento.get("tipo_servicio_institucion") != null) {
                respuesta.put("tipo_usuario", evento.get("tipo_usuario_institucion"));
                respuesta.put("tipo_servicio", evento.get("tipo_servicio_institucion"));
                SocketEndPoint.EnviarNotificacion(respuesta);
            }
            if (evento.get("tipo_usuario_traslado") != null && evento.get("tipo_servicio_traslado") != null) {
                respuesta.put("tipo_usuario", evento.get("tipo_usuario_traslado"));
                respuesta.put("tipo_servicio", evento.get("tipo_servicio_traslado"));
                SocketEndPoint.EnviarNotificacion(respuesta);
            }
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/referencias_hospitalarias/aceptar_solicitud", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject referencias_hospitalarias_aceptar_solicitud(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("referencias_hospitalarias_aceptar_solicitud en: " + Dependencia);
        /*
            "id_solicitud"
            "tipo_usuario"
            "tipo_servicio"
            "to_tipo_usuario_traslado"
            "to_tipo_servicio_traslado"
            "responsable_institucion"
            "cama"
            "dia_recepcion"
            "hora_recepcion"
         */
        JSONObject respuesta = respuesta(false, "La solicitud no se pudo aceptar. Intentelo de nuevo.");
        //Validar que nadie la haya aceptado
        JSONObject evento = Query.select("SELECT * FROM eventos_referencias_hospitalarias WHERE id_referencia_hospitalaria = '" + json.get("id_solicitud") + "';");
        if (evento.get("tipo_usuario_institucion") == null && evento.get("tipo_servicio_institucion") == null) {

            String query = "UPDATE eventos_referencias_hospitalarias SET id_estado_referencia_hospitalaria = '2',  tipo_usuario_institucion = '" + json.get("tipo_usuario") + "', tipo_servicio_institucion = '" + json.get("tipo_servicio") + "', "
                    + "to_tipo_usuario_traslado = '" + json.get("to_tipo_usuario_traslado") + "', to_tipo_servicio_traslado = '" + json.get("to_tipo_servicio_traslado") + "',"
                    + "responsable_institucion = '" + json.get("responsable_institucion") + "', cama = '" + json.get("cama") + "', dia_recepcion = '" + json.get("dia_recepcion") + "',hora_recepcion = '" + json.get("hora_recepcion") + "', fecha_aceptacion = '"+Query.getFecha()+"' "
                    + "WHERE id_referencia_hospitalaria = '" + json.get("id_solicitud") + "';";
            if (Query.update(query)) {
                respuesta = respuesta(true, "Solicitud aceptada por: " + Query.select("SELECT nombre FROM servicios_usuario WHERE id = '" + json.get("tipo_servicio") + "';").get("nombre"));
                respuesta.put("referencia_hospitalaria_update", true);
                respuesta.put("solicitudes_referencia_hospitalaria", true);
                respuesta.put("id", json.get("id_solicitud"));
                respuesta.put("estado", "2");
                respuesta.put("nombre_estado", Query.select("SELECT nombre FROM estados_referencias_hospitalarias WHERE id = 2;").get("nombre"));

                if (evento.get("tipo_usuario") != null && evento.get("tipo_servicio") != null) {
                    respuesta.put("tipo_usuario", evento.get("tipo_usuario"));
                    respuesta.put("tipo_servicio", evento.get("tipo_servicio"));

                    SocketEndPoint.EnviarNotificacion(respuesta);
                }
                if (evento.get("to_tipo_usuario_institucion") != null && evento.get("to_tipo_servicio_institucion") != null) {
                    respuesta.put("tipo_usuario", evento.get("to_tipo_usuario_institucion"));
                    respuesta.put("tipo_servicio", evento.get("to_tipo_servicio_institucion"));
                    SocketEndPoint.EnviarNotificacion(respuesta);
                }
                if (json.get("to_tipo_usuario_traslado") != null && json.get("to_tipo_usuario_traslado") != null) {
                    respuesta.put("tipo_usuario", json.get("to_tipo_usuario_traslado"));
                    respuesta.put("tipo_servicio", json.get("to_tipo_servicio_traslado"));
                    json.put("tipo_usuario_institucion",json.get("tipo_usuario"));
                    json.put("tipo_servicio_institucion",json.get("tipo_servicio"));
                    json.remove("tipo_usuario_traslado");
                    json.remove("tipo_servicio_traslado");
                    json.remove("estado");
                    json.remove("nombre_estado");
                    json.remove("tipo_usuario");
                    json.remove("tipo_servicio");
                    respuesta.putAll(json);
                    respuesta.put("entrada", true);
                    
                    SocketEndPoint.EnviarNotificacion(respuesta);
                }
            }
        } else {
            respuesta = respuesta(false, "Otra institución ya está atendiendo la solicitud.");
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/referencias_hospitalarias/rechazar_solicitud", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject referencias_hospitalarias_rechazar_solicitud(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("referencias_hospitalarias_rechazar_solicitud en: " + Dependencia);
        /*
            "id_solicitud"
            "tipo_usuario"
            "tipo_servicio"
         */
        JSONObject respuesta = respuesta(false, "La solicitud no se pudo aceptar. Intentelo de nuevo.");
        //Validar que nadie la haya aceptado
        JSONObject evento = Query.select("SELECT * FROM eventos_referencias_hospitalarias WHERE id_referencia_hospitalaria = '" + json.get("id_solicitud") + "';");
        if (evento.get("to_tipo_usuario_institucion").toString().equals("0") && evento.get("to_tipo_servicio_institucion").toString().equals("0")) {
            int id_rechazo = Query.insert(Query.createQueryInsert("rechazos_referencias_hospitalarias", json));
            if (id_rechazo > 0) {
                respuesta = respuesta(true, "Solicitud rechazada por: " + Query.select("SELECT nombre FROM servicios_usuario WHERE id = '" + json.get("tipo_servicio") + "';").get("nombre"));
                respuesta.put("referencia_hospitalaria_update", true);
                respuesta.put("id", json.get("id_solicitud"));
                respuesta.put("estado", "3");
                respuesta.put("nombre_estado", Query.select("SELECT nombre FROM estados_referencias_hospitalarias WHERE id = 3;").get("nombre"));
                
                respuesta.put("tipo_usuario", json.get("tipo_usuario"));
                respuesta.put("tipo_servicio", json.get("tipo_servicio"));

                SocketEndPoint.EnviarNotificacion(respuesta);
            }
        } else {

            String query = "UPDATE eventos_referencias_hospitalarias SET id_estado_referencia_hospitalaria = '3',  tipo_usuario_institucion = '" + json.get("tipo_usuario") + "', tipo_servicio_institucion = '" + json.get("tipo_servicio") + "' "
                    + "WHERE id_referencia_hospitalaria = '" + json.get("id_solicitud") + "';";
            if (Query.update(query)) {
                respuesta = respuesta(true, "Solicitud rechazada por: " + Query.select("SELECT nombre FROM servicios_usuario WHERE id = '" + json.get("tipo_servicio") + "';").get("nombre"));
                respuesta.put("referencia_hospitalaria_update", true);
                respuesta.put("id", json.get("id_solicitud"));
                respuesta.put("estado", "3");
                respuesta.put("nombre_estado", Query.select("SELECT nombre FROM estados_referencias_hospitalarias WHERE id = 3;").get("nombre"));

                if (evento.get("tipo_usuario") != null && evento.get("tipo_servicio") != null) {
                    respuesta.put("tipo_usuario", evento.get("tipo_usuario"));
                    respuesta.put("tipo_servicio", evento.get("tipo_servicio"));

                    SocketEndPoint.EnviarNotificacion(respuesta);
                }
                if (evento.get("to_tipo_usuario_institucion") != null && evento.get("to_tipo_servicio_institucion") != null) {
                    respuesta.put("tipo_usuario", evento.get("to_tipo_usuario_institucion"));
                    respuesta.put("tipo_servicio", evento.get("to_tipo_servicio_institucion"));
                    SocketEndPoint.EnviarNotificacion(respuesta);
                }
//                if (evento.get("tipo_usuario_traslado") != null && evento.get("tipo_servicio_traslado") != null) {
//                    respuesta.put("tipo_usuario", evento.get("tipo_usuario_traslado"));
//                    respuesta.put("tipo_servicio", evento.get("tipo_servicio_traslado"));
//                    SocketEndPoint.EnviarNotificacion(respuesta);
//                }
            }
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/referencias_hospitalarias/aceptar_traslado", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject referencias_hospitalarias_aceptar_traslado(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("referencias_hospitalarias_aceptar_traslado en: " + Dependencia);
        /*
            "id_solicitud"
            "tipo_usuario"
            "tipo_servicio"
            "responsable_traslado"
            "ambulancia"
         */
        JSONObject respuesta = respuesta(false, "La solicitud no se pudo aceptar. Intentelo de nuevo.");
        //Validar que nadie la haya aceptado
        JSONObject evento = Query.select("SELECT * FROM eventos_referencias_hospitalarias WHERE id_referencia_hospitalaria = '" + json.get("id_solicitud") + "';");
        if (evento.get("tipo_usuario_traslado") == null && evento.get("tipo_servicio_traslado") == null) {

            String query = "UPDATE eventos_referencias_hospitalarias SET id_estado_referencia_hospitalaria = '5',  tipo_usuario_traslado = '" + json.get("tipo_usuario") + "', tipo_servicio_traslado = '" + json.get("tipo_servicio") + "',"
                    + "responsable_traslado = '" + json.get("responsable_traslado") + "', ambulancia = '" + json.get("ambulancia") + "'"
                    + "WHERE id_referencia_hospitalaria = '" + json.get("id_solicitud") + "';";
            if (Query.update(query)) {
                respuesta = respuesta(true, "Traslado aceptado por: " + Query.select("SELECT nombre FROM servicios_usuario WHERE id = '" + json.get("tipo_servicio") + "';").get("nombre"));
                respuesta.put("referencia_hospitalaria_update", true);
                
                respuesta.put("id", json.get("id_solicitud"));
                respuesta.put("estado", "5");
                respuesta.put("nombre_estado", Query.select("SELECT nombre FROM estados_referencias_hospitalarias WHERE id = 5;").get("nombre"));

                if (evento.get("tipo_usuario") != null && evento.get("tipo_servicio") != null) {
                    respuesta.put("tipo_usuario", evento.get("tipo_usuario"));
                    respuesta.put("tipo_servicio", evento.get("tipo_servicio"));

                    SocketEndPoint.EnviarNotificacion(respuesta);
                }
                if (evento.get("tipo_usuario_institucion") != null && evento.get("tipo_servicio_institucion") != null) {
                    respuesta.put("tipo_usuario", evento.get("tipo_usuario_institucion"));
                    respuesta.put("tipo_servicio", evento.get("tipo_servicio_institucion"));
                    SocketEndPoint.EnviarNotificacion(respuesta);
                }
                if (evento.get("to_tipo_usuario_traslado") != null && evento.get("to_tipo_servicio_traslado") != null) {
                    respuesta.put("tipo_usuario", evento.get("to_tipo_usuario_traslado"));
                    respuesta.put("tipo_servicio", evento.get("to_tipo_servicio_traslado"));
                    SocketEndPoint.EnviarNotificacion(respuesta);
                }
            }
        } else {
            respuesta = respuesta(false, "Otra institución ya está realizando el traslado.");
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/referencias_hospitalarias/iniciar_traslado", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject referencias_hospitalarias_iniciar_traslado(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("referencias_hospitalarias_iniciar_traslado en: " + Dependencia);
        /*
            "id_solicitud"
         */
        JSONObject respuesta = respuesta(false, "La solicitud no se pudo aceptar. Intentelo de nuevo.");
        //Validar que nadie la haya aceptado
        JSONObject evento = Query.select("SELECT * FROM eventos_referencias_hospitalarias WHERE id_referencia_hospitalaria = '" + json.get("id_solicitud") + "';");

        String query = "UPDATE eventos_referencias_hospitalarias SET id_estado_referencia_hospitalaria = '6' WHERE id_referencia_hospitalaria = '" + json.get("id_solicitud") + "';";
        if (Query.update(query)) {
            respuesta = respuesta(true, "Traslado iniciado por: " + Query.select("SELECT nombre FROM servicios_usuario WHERE id = '" + evento.get("tipo_servicio_traslado") + "';").get("nombre"));
            respuesta.put("referencia_hospitalaria_update", true);
            respuesta.put("id", json.get("id_solicitud"));
            respuesta.put("estado", "6");
            respuesta.put("nombre_estado", Query.select("SELECT nombre FROM estados_referencias_hospitalarias WHERE id = 6;").get("nombre"));

            if (evento.get("tipo_usuario") != null && evento.get("tipo_servicio") != null) {
                respuesta.put("tipo_usuario", evento.get("tipo_usuario"));
                respuesta.put("tipo_servicio", evento.get("tipo_servicio"));

                SocketEndPoint.EnviarNotificacion(respuesta);
            }
            if (evento.get("tipo_usuario_institucion") != null && evento.get("tipo_servicio_institucion") != null) {
                respuesta.put("tipo_usuario", evento.get("tipo_usuario_institucion"));
                respuesta.put("tipo_servicio", evento.get("tipo_servicio_institucion"));
                SocketEndPoint.EnviarNotificacion(respuesta);
            }
            if (evento.get("tipo_usuario_traslado") != null && evento.get("tipo_servicio_traslado") != null) {
                respuesta.put("tipo_usuario", evento.get("tipo_usuario_traslado"));
                respuesta.put("tipo_servicio", evento.get("tipo_servicio_traslado"));
                SocketEndPoint.EnviarNotificacion(respuesta);
            }
        }

        return respuesta;
    }

    @RequestMapping(value = "/API/referencias_hospitalarias/ingresado", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject referencias_hospitalarias_ingresado(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("referencias_hospitalarias_ingresado en: " + Dependencia);
        /*
            "id_solicitud"
         */
        JSONObject respuesta = respuesta(false, "La solicitud no se pudo aceptar. Intentelo de nuevo.");
        //Validar que nadie la haya aceptado
        JSONObject evento = Query.select("SELECT * FROM eventos_referencias_hospitalarias WHERE id_referencia_hospitalaria = '" + json.get("id_solicitud") + "';");

        String query = "UPDATE eventos_referencias_hospitalarias SET id_estado_referencia_hospitalaria = '7' WHERE id_referencia_hospitalaria = '" + json.get("id_solicitud") + "';";
        if (Query.update(query)) {
            respuesta = respuesta(true, "Paciente ingresado a: " + Query.select("SELECT nombre FROM servicios_usuario WHERE id = '" + evento.get("tipo_servicio_institucion") + "';").get("nombre"));
            respuesta.put("referencia_hospitalaria_update", true);
            respuesta.put("id", json.get("id_solicitud"));
            respuesta.put("estado", "7");
            respuesta.put("nombre_estado", Query.select("SELECT nombre FROM estados_referencias_hospitalarias WHERE id = 7;").get("nombre"));

            if (evento.get("tipo_usuario") != null && evento.get("tipo_servicio") != null) {
                respuesta.put("tipo_usuario", evento.get("tipo_usuario"));
                respuesta.put("tipo_servicio", evento.get("tipo_servicio"));

                SocketEndPoint.EnviarNotificacion(respuesta);
            }
            if (evento.get("tipo_usuario_institucion") != null && evento.get("tipo_servicio_institucion") != null) {
                respuesta.put("tipo_usuario", evento.get("tipo_usuario_institucion"));
                respuesta.put("tipo_servicio", evento.get("tipo_servicio_institucion"));
                SocketEndPoint.EnviarNotificacion(respuesta);
            }
            if (evento.get("tipo_usuario_traslado") != null && evento.get("tipo_servicio_traslado") != null) {
                respuesta.put("tipo_usuario", evento.get("tipo_usuario_traslado"));
                respuesta.put("tipo_servicio", evento.get("tipo_servicio_traslado"));
                SocketEndPoint.EnviarNotificacion(respuesta);
            }
        }

        return respuesta;
    }

    @RequestMapping(value = "/API/referencias_hospitalarias/alta", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject referencias_hospitalarias_alta(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("referencias_hospitalarias_alta en: " + Dependencia);
        /*
            "id_solicitud"
         */
        JSONObject respuesta = respuesta(false, "La solicitud no se pudo aceptar. Intentelo de nuevo.");
        //Validar que nadie la haya aceptado
        JSONObject evento = Query.select("SELECT * FROM eventos_referencias_hospitalarias WHERE id_referencia_hospitalaria = '" + json.get("id_solicitud") + "';");

        String query = "UPDATE eventos_referencias_hospitalarias SET id_estado_referencia_hospitalaria = '8' WHERE id_referencia_hospitalaria = '" + json.get("id_solicitud") + "';";
        
        if (Query.update(query)) {
            String codigo_alta = generateString().substring(0, 8).toUpperCase();
            System.out.println("/**********************************/");
            query = "UPDATE referencias_hospitalarias SET codigo_alta = '"+codigo_alta+"' WHERE id = '" + json.get("id_solicitud") + "';";
            System.out.println(query);
            Query.update(query);
            
            
            respuesta = respuesta(true, "Paciente dado de alta por: " + Query.select("SELECT nombre FROM servicios_usuario WHERE id = '" + evento.get("tipo_servicio_institucion") + "';").get("nombre"));
            respuesta.put("referencia_hospitalaria_update", true);
            respuesta.put("id", json.get("id_solicitud"));
            respuesta.put("codigo_alta", codigo_alta);
            respuesta.put("estado", "8");
            respuesta.put("nombre_estado", Query.select("SELECT nombre FROM estados_referencias_hospitalarias WHERE id = 8;").get("nombre"));
             
            if (evento.get("tipo_usuario") != null && evento.get("tipo_servicio") != null) {
                respuesta.put("tipo_usuario", evento.get("tipo_usuario"));
                respuesta.put("tipo_servicio", evento.get("tipo_servicio"));

                SocketEndPoint.EnviarNotificacion(respuesta);
            }
            if (evento.get("tipo_usuario_institucion") != null && evento.get("tipo_servicio_institucion") != null) {
                respuesta.put("tipo_usuario", evento.get("tipo_usuario_institucion"));
                respuesta.put("tipo_servicio", evento.get("tipo_servicio_institucion"));
                SocketEndPoint.EnviarNotificacion(respuesta);
            }
            if (evento.get("tipo_usuario_traslado") != null && evento.get("tipo_servicio_traslado") != null) {
                respuesta.put("tipo_usuario", evento.get("tipo_usuario_traslado"));
                respuesta.put("tipo_servicio", evento.get("tipo_servicio_traslado"));
                SocketEndPoint.EnviarNotificacion(respuesta);
            }
        }

        return respuesta;
    }
    @RequestMapping(value = "/API/referencias_hospitalarias/quitar_rechazo", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject referencias_hospitalarias_quitar_rechazo(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("referencias_hospitalarias_quitar_rechazo en: " + Dependencia);
        /*
            "id_solicitud"
         */
        JSONObject respuesta = respuesta(false, "No se pudo quitar el rechazo de la solicitud. Intentelo de nuevo.");
        //Validar que nadie la haya aceptado
        JSONObject evento = Query.select("SELECT * FROM eventos_referencias_hospitalarias WHERE id_referencia_hospitalaria = '" + json.get("id_solicitud") + "';");

        String query = "UPDATE eventos_referencias_hospitalarias SET id_estado_referencia_hospitalaria = '1', tipo_usuario_institucion = NULL, tipo_servicio_institucion = NULL WHERE id_referencia_hospitalaria = '" + json.get("id_solicitud") + "';";
        if (Query.update(query)) {
            respuesta = respuesta(true, "Se quito el rechazo de la solicitud por: " + Query.select("SELECT nombre FROM servicios_usuario WHERE id = '" + evento.get("tipo_servicio_institucion") + "';").get("nombre"));
            respuesta.put("referencia_hospitalaria_update", true);
            respuesta.put("id", json.get("id_solicitud"));
            respuesta.put("estado", "1");
            respuesta.put("nombre_estado", Query.select("SELECT nombre FROM estados_referencias_hospitalarias WHERE id = 1;").get("nombre"));

            if (evento.get("tipo_usuario") != null && evento.get("tipo_servicio") != null) {
                respuesta.put("tipo_usuario", evento.get("tipo_usuario"));
                respuesta.put("tipo_servicio", evento.get("tipo_servicio"));

                SocketEndPoint.EnviarNotificacion(respuesta);
            }
            if (evento.get("tipo_usuario_institucion") != null && evento.get("tipo_servicio_institucion") != null) {
                respuesta.put("tipo_usuario", evento.get("tipo_usuario_institucion"));
                respuesta.put("tipo_servicio", evento.get("tipo_servicio_institucion"));
                SocketEndPoint.EnviarNotificacion(respuesta);
            }
//            if (evento.get("tipo_usuario_traslado") != null && evento.get("tipo_servicio_traslado") != null) {
//                respuesta.put("tipo_usuario", evento.get("tipo_usuario_traslado"));
//                respuesta.put("tipo_servicio", evento.get("tipo_servicio_traslado"));
//                SocketEndPoint.EnviarNotificacion(respuesta);
//            }
        }

        return respuesta;
    }
}
