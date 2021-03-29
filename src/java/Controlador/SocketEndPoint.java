/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Config.config;
import static Controlador.BackupDirectorio.UpdateBackupDirectorio;
import Modelo.Query;
import Modelo.ReporteElemento;
import Request.request;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import org.json.JSONObject;

/**
 *
 * @author Moises Juarez
 */
@ServerEndpoint("/SocketNotifications")
public class SocketEndPoint {

    private static Set<Session> chatroomUsers = Collections.synchronizedSet(new HashSet<Session>());

    //ServerReporteElemento
    private static ArrayList<JSONObject> BackupReportes = new ArrayList<JSONObject>();
    private static boolean has_Backup = false;
    //ServerFolios
    private static ArrayList<JSONObject> BackupIncidentes = new ArrayList<JSONObject>();
    //Server
    private static ArrayList<JSONObject> BackupLlamadas = new ArrayList<JSONObject>();
    private static ArrayList<JSONObject> BackupCOVID = new ArrayList<JSONObject>();

    @OnOpen
    public void handleOpen(Session userSession) throws IOException {
        chatroomUsers.add(userSession);
        //System.out.println("Alguien se conecto al webSocket");
        JSONObject json = new JSONObject();
        json.put("inicializacionSG", true);
        json.put("idSocket", userSession.getId());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        json.put("fecha_conexion", dateFormat.format(date));
        json.put("hora_conexion", hourFormat.format(date));
        EnviarNotificacionIndividual(json, userSession.getId());

    }

    @OnMessage
    public void handleMessage(String message, Session userSession) throws IOException, ParseException, java.text.ParseException {
        JSONObject mensaje = JSONObject_Parse(message);

        if (userSession.getUserProperties().get("idUsuario") == null) {
            //inicializarSesion(userSession, mensaje);
            //Corresponderia al primer mensaje por lo que se asignarian valores a las propiedad de la sesion
            //System.out.println("Inicializando sesion");
            mensaje.put("idUsuario", mensaje.containsKey("idUsuario") ? mensaje.get("idUsuario") : "");
            mensaje.put("tipo_usuario", mensaje.containsKey("tipo_usuario") ? mensaje.get("tipo_usuario") : "");
            mensaje.put("tipo_servicio", mensaje.containsKey("tipo_servicio") ? mensaje.get("tipo_servicio") : "");
            mensaje.put("tipo_area", mensaje.containsKey("tipo_area") ? mensaje.get("tipo_area") : "");

            userSession.getUserProperties().put("idUsuario", mensaje.get("idUsuario")); //El id de usuario sera el id del estado de quien se logueo
            userSession.getUserProperties().put("id360", mensaje.get("idUsuario")); //El id de usuario sera el id del estado de quien se logueo
            userSession.getUserProperties().put("tipo_usuario", mensaje.get("tipo_usuario")); //El id de usuario sera el id del estado de quien se logueo
            userSession.getUserProperties().put("tipo_servicio", mensaje.get("tipo_servicio")); //El id de usuario sera el id del estado de quien se logueo
            userSession.getUserProperties().put("tipo_area", mensaje.get("tipo_area")); //El id de usuario sera el id del estado de quien se logueo
            userSession.getUserProperties().put("gpsOTS", false); //El id de usuario sera el id del estado de quien se logueo

            //revisar si es la primera conexion del usuario 
            if (is_primera_conexion(mensaje.get("idUsuario").toString())) {
                //Notoficar que un usuario se ha conectado... (Por jerarquia)
                mensaje.put("usuario_conectado", true);
                EnviarNotificacion(mensaje);
            }
        } else {
            //System.out.println("Sesion Inicializada");
            //System.out.println(userSession.getUserProperties().get("idUsuario"));
            //System.out.println(userSession.getUserProperties().get("tipo_usuario"));
            //System.out.println(userSession.getUserProperties().get("tipo_servicio"));
        }

        if (mensaje.containsKey("developer")) {
            userSession.getUserProperties().put("developer", true); //El id de usuario sera el id del estado de quien se logueo
        }
        //System.out.println(mensaje);

        //ServerReporteElemento
        if (!has_Backup) {
            JSONArray jsonA = ReporteElemento.traeReportes();
            for (int i = 0; i < jsonA.size(); i++) {
                JSONObject jsonObj = (JSONObject) jsonA.get(i);
                jsonObj.put("reporteelemento", true);
                BackupReportes.add(jsonObj);
            }
            has_Backup = true;
        }
        if (mensaje.containsKey("Back")) {
            mensaje.put("Backup", BackupReportes.toString());
            //userSession.getBasicRemote().sendText(mensaje.toString());
            JSONArray Reportes = new JSONArray();
            Iterator<JSONObject> iterator = BackupReportes.iterator();
            while (iterator.hasNext()) {
                JSONObject ReporteElemento = iterator.next();
                EnviarNotificacionIndividual(ReporteElemento, mensaje.get("idSocket").toString());

            }

        }
        if (mensaje.containsKey("Tam")) {
            String tam = Integer.toString(BackupReportes.size());
            mensaje.put("TamBackup", tam);
            //userSession.getBasicRemote().sendText(mensaje.toString());
            EnviarNotificacionIndividual(mensaje, mensaje.get("idSocket").toString());

        }

        if (mensaje.containsKey("quitar_reporte")) {
            EnviarNotificacion(mensaje);
        }
        if (mensaje.containsKey("Elimina")) {
            Iterator<JSONObject> iterator = BackupReportes.iterator();
            while (iterator.hasNext()) {
                JSONObject ReporteElemento = iterator.next();
                if (ReporteElemento.get("id").toString().equals(mensaje.get("id").toString())) {
                    iterator.remove();
                    EnviarNotificacion(mensaje);
                    break;
                }
            }

        }

        if (mensaje.containsKey("BuscarIncidente")) {
            double lat = (double) mensaje.get("lat");
            double lng = (double) mensaje.get("lng");

            JSONArray Incidentes = new JSONArray();
            Iterator<JSONObject> iterator = BackupIncidentes.iterator();

            int n = 0;
            while (iterator.hasNext()) {

                JSONObject jsonElemento = iterator.next();
                Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jsonElemento.get("fechaServidor").toString() + " " + jsonElemento.get("horaServidor").toString());
                Date date2 = new Date();
                if (!CompararFechas(date1, date2, 120)) {
                    iterator.remove();
                } else {
                    //Se trata de un incidente reciente por lo que se calculara la distancia del actual
                    double rad = Math.PI / 180;
                    double latElemento = (double) jsonElemento.get("lat");
                    double lngElemento = (double) jsonElemento.get("lng");

                    double d = (6372795.477598) * Math.acos(Math.sin(latElemento * rad) * Math.sin(lat * rad) + Math.cos(latElemento * rad) * Math.cos(lat * rad) * Math.cos((lngElemento * rad) - (lng * rad)));
                    jsonElemento.put("distancia", d);
                    if (d < 200) {
                        Incidentes.add(jsonElemento);
                        n++;
                    }
                }
            }
            //userSession.getBasicRemote().sendText(Incidentes.toString()); 
            EnviarNotificacionIndividual(Incidentes, mensaje.get("idSocket").toString());
        }

        if (mensaje.containsKey("AgregarIncidente")) {
            BackupIncidentes.add(mensaje);
            EnviarNotificacion(mensaje);
        }
        if (mensaje.containsKey("registrar_incidente")) {
            request.POST(config.getURL_CONTROLADOR() + "API/incidente/registro", message);
        }
        if (mensaje.containsKey("buscar_incidente")) {
            JSONParser parser = new JSONParser();
            JSONObject incidentesCercanos = new JSONObject();
            incidentesCercanos.put("incidentesCercanos", (JSONArray) parser.parse(request.POST(config.getURL_CONTROLADOR() + "API/incidente/buscar", message)));
            EnviarNotificacionIndividual(incidentesCercanos, mensaje.get("idSocket").toString());

        }

        if (mensaje.containsKey("eliminarcard")) {
            EnviarNotificacion(mensaje);
            ////System.out.println(username + " : " + message);
        }
        if (mensaje.containsKey("BackupLlamadas")) {
            //System.out.println("BackupLlamadas");
            Iterator<JSONObject> iterator = BackupLlamadas.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonElemento = iterator.next();
                //System.out.println(jsonElemento);
                Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jsonElemento.get("fechaServidor").toString() + " " + jsonElemento.get("horaServidor").toString());
                Date date2 = new Date();
                if (!CompararFechas(date1, date2, 5)) {
                    //System.out.println("Eliminar");
                    //System.out.println(jsonElemento);
                    iterator.remove();
                } else {
                    //System.out.println("Notificar");
                    //System.out.println(jsonElemento);
                    Session s = userSession;
                    JSONObject m = jsonElemento;
                    boolean notificar = false;
                    if (s.getUserProperties().get("tipo_usuario") != null && s.getUserProperties().get("tipo_servicio") != null) {
                        //System.out.println(s.getUserProperties().get("tipo_usuario"));
                        //System.out.println(s.getUserProperties().get("tipo_servicio"));
                        //preguntar si es usuario maestro a nivel estatal
                        if (((String) s.getUserProperties().get("tipo_usuario")).equals("0")) {
                            notificar = true;
                        }
                        //preguntar si corresponde al tipo de usuario
                        if (((String) s.getUserProperties().get("tipo_usuario")).equals(((String) m.get("tipo_usuario")))) {
                            //preguntar si es usuario maestro a nivel dependencia
                            if (((String) s.getUserProperties().get("tipo_servicio")).equals("0")) {
                                notificar = true;
                            }
                            //preguntar si corresponde al tipo de usuario
                            if (((String) s.getUserProperties().get("tipo_servicio")).equals(m.get("tipo_servicio").toString())) {
                                notificar = true;
                            }
                        }
                    }
                    //System.out.println("notifica: " + notificar);
                    if (notificar) {
                        s.getBasicRemote().sendText(m.toString());
                    }
                    //EnviarNotificacionIndividual(jsonElemento, mensaje.get("idSocket").toString());
                }
            }
        }
        if (mensaje.containsKey("BackupCardLlamada")) {
            String query = "SELECT d.idUsuarios_Movil, d.Nombre, r.fecha, r.hora, r.Modo_Llamada_idModo_Llamada as id_modo, r.Modo_Llamada_idModo_Llamada_Finalizada as id_fin "
                    + "FROM usuarios_movil d, registro_llamadas r "
                    + "WHERE d.idUsuarios_Movil = r.Usuarios_Movil_idUsuarios_Movil "
                    + "AND r.tipo_usuario = '" + mensaje.get("tipo_usuario") + "' AND r.tipo_servicio = '" + mensaje.get("tipo_servicio") + "'  order by r.idRegistro_Llamadas DESC limit 50;";

            //AND r.fecha = date(now())
            JSONArray cards = Query.execute(query);
            if (!cards.isEmpty()) {
                for (int i = 0; i < cards.size(); i++) {
                    JSONObject card = (JSONObject) cards.get(i);
                    query = "SELECT nombre FROM modo_llamada WHERE idModo_Llamada = '" + card.get("id_fin") + "' "
                            + "OR idModo_Llamada = '" + card.get("id_modo") + "';";
                    JSONArray array = Query.execute(query);
                    card.put("modo_fin", array.get(0));
                    card.put("modo_llamada", array.get(1));
                }
                JSONObject resp = new JSONObject();
                resp.put("info", cards);
                resp.put("agrega_cards", true);
                EnviarNotificacionIndividual(resp, mensaje.get("idSocket").toString());
            }

        }

        if (mensaje.containsKey("EliminarLlamada")) {
            //System.out.println("EliminarLlamada".toUpperCase());
            Iterator<JSONObject> iterator = BackupLlamadas.iterator();
            while (iterator.hasNext()) {
                JSONObject llamada = iterator.next();
                JSONObject RegistroLlamada = (JSONObject) llamada.get("RegistroLlamada");
                //System.out.print(RegistroLlamada);
                //System.out.print(" vs ");
                //System.out.print(mensaje);

                if (mensaje.get("idLlamada").toString().equals(RegistroLlamada.get("idLlamada").toString())) {
                    //System.out.println("removiendo");
                    //System.out.println(llamada);
                    iterator.remove();
                    //EnviarNotificacion(mensaje);
                    break;
                } else {
                    //System.out.println("No se removio");
                    //System.out.println(llamada);
                }
            }

        }
        if (mensaje.containsKey("gpsOTS") || mensaje.containsKey("ActualizaGPS")) {
            UpdateBackupDirectorio(mensaje);
            Modelo.Escalamiento.AgregarData(mensaje);

//            JSONObject servicio = new JSONObject();
//            servicio.put("url", config.getPATH() + config.getDEPENDENCIA());
//            String url = request.POST(config.getURL_CONTROLADOR() + "API/ServicioSuperior", servicio.toString());
//            JSONParser parser = new JSONParser();
//            JSONArray arrayServicios = (JSONArray) parser.parse(url);
//            for (int i = 0; i < arrayServicios.size(); i++) {
//                request.POST(arrayServicios.get(i).toString() + "/API/UpdateBackupDirectorio", mensaje.toString());
//            }
        }

        if (mensaje.containsKey("notificacionescovid")) {
            //System.out.println("notificacionescovid".toUpperCase());
            //Enviando notificacion a todos para que sepan que ya se realizo el reporte de la notificacion
            EnviarNotificacion(mensaje);

            //Se elimina del backup
            Iterator<JSONObject> iterator = BackupCOVID.iterator();
            while (iterator.hasNext()) {
                JSONObject reporte = iterator.next();
                //System.out.print(reporte);
                //System.out.print(" vs ");
                //System.out.print(mensaje);

                if (mensaje.get("serie").toString().equals(reporte.get("serie").toString())) {
                    //System.out.println("removiendo");
                    //System.out.println(reporte);
                    iterator.remove();
                    //EnviarNotificacion(mensaje);
                    break;
                }
            }

            //Se actualiza el registro en la BD
            String query = "UPDATE `testCovid` SET `reporte` = '" + mensaje.get("reporte") + "', `fecha_reporte` = '" + mensaje.get("fecha") + "', `hora_reporte` = '" + mensaje.get("hora") + "' WHERE (`serie` = '" + mensaje.get("serie") + "');";
            Query.update(query);

            //Actualizar incidentes
        }

        if (mensaje.containsKey("BackupCOVID")) {
            //System.out.println("BackupCOVID");
            Iterator<JSONObject> iterator = BackupCOVID.iterator();
            while (iterator.hasNext()) {
                JSONObject reporte = iterator.next();
                //System.out.println("Notificar");
                //System.out.println(reporte);
                EnviarNotificacionIndividual(reporte, mensaje.get("idSocket").toString());

            }
        }

        //**************************** BACKUPS ******************************//
        if (mensaje.containsKey("backup_ccb")) {

            //mandar estadisticas
            String query = "SELECT COUNT(id) AS total_solicitudes FROM solicitudes where  activo = 1;"; // total de solicitudes
            JSONObject total_solicitudes = Query.select(query);
            query = "SELECT COUNT(id)   AS total_solicitudes_pendientes FROM solicitudes WHERE estado = 'Enviada'  AND activo = 1;"; // total de solicitudes pendientes
            JSONObject total_solicitudes_pendientes = Query.select(query);
            query = "SELECT COUNT(id)   AS total_solicitudes_proceso FROM solicitudes WHERE estado = 'Aceptada por UTC-19' AND activo = 1;"; // total de solicitudes en proceso
            JSONObject total_solicitudes_proceso = Query.select(query);
            query = "SELECT COUNT(id)   AS total_solicitudes_ruta FROM solicitudes WHERE estado = 'Aceptada por CRUM' AND activo = 1;"; // total de solicitudes en ruta
            JSONObject total_solicitudes_ruta = Query.select(query);
            query = "SELECT COUNT(id)   AS total_solicitudes_rechazado FROM solicitudes WHERE estado LIKE 'Rechazad%' AND activo = 1;"; // total de solicitudes rechazadas
            JSONObject total_solicitudes_rechazado = Query.select(query);
            query = "SELECT COUNT(id)   AS total_solicitudes_rechazado_auto FROM rechazos_automaticos;"; // total de solicitudes rechazadas automaticas
            JSONObject total_solicitudes_rechazado_auto = Query.select(query);
            query = "SELECT COUNT(id)   AS total_solicitudes_finalizadas FROM solicitudes WHERE estado LIKE 'Ingresado en%' AND activo = 1;"; // total de solicitudes finalizadas
            JSONObject total_solicitudes_finalizadas = Query.select(query);

            JSONObject Solicitudes = new JSONObject();
            Solicitudes.put("total_solicitudes", total_solicitudes.get("total_solicitudes"));
            Solicitudes.put("total_solicitudes_pendientes", total_solicitudes_pendientes.get("total_solicitudes_pendientes"));
            Solicitudes.put("total_solicitudes_proceso", total_solicitudes_proceso.get("total_solicitudes_proceso"));
            Solicitudes.put("total_solicitudes_ruta", total_solicitudes_ruta.get("total_solicitudes_ruta"));
            Solicitudes.put("total_solicitudes_rechazado", Integer.parseInt(total_solicitudes_rechazado.get("total_solicitudes_rechazado").toString()) + Integer.parseInt(total_solicitudes_rechazado_auto.get("total_solicitudes_rechazado_auto").toString()));
            Solicitudes.put("total_solicitudes_finalizadas", total_solicitudes_finalizadas.get("total_solicitudes_finalizadas"));
            Solicitudes.put("estadisticas", true);
            EnviarNotificacionIndividual(Solicitudes, mensaje.get("idSocket").toString());

            query = "SELECT * FROM  solicitudes where (fecha = \"" + mensaje.get("fecha_calendario") + "\" OR (estado NOT LIKE \"%Rechaz%\" AND estado NOT LIKE \"%Ingresado%\" AND estado NOT LIKE \"%Cancelad%\" AND estado NOT LIKE \"%Alta%\" AND estado NOT LIKE \"%Contrarreferencia%\")) AND activo=1;"; //where fecha (si es calendarizado....)
            //System.out.println(query);
            JSONArray solicitudes = Query.execute(query);
            for (int i = 0; i < solicitudes.size(); i++) {
                JSONObject solicitud = (JSONObject) solicitudes.get(i);
                solicitud.put("bandeja_entrada", true);
                solicitud.put("backup_solicitudes", true);
                EnviarNotificacionIndividual(solicitud, mensaje.get("idSocket").toString());
            }
        }

        if (mensaje.containsKey("backup_ccb_fecha")) {
            String query = "SELECT * FROM  solicitudes where fecha = '" + mensaje.get("fecha_calendario") + "' AND activo = 1;"; //where fecha (si es calendarizado....)
            //System.out.println(query);
            JSONArray solicitudes = Query.execute(query);
            for (int i = 0; i < solicitudes.size(); i++) {
                JSONObject solicitud = (JSONObject) solicitudes.get(i);
                solicitud.put("bandeja_entrada", true);
                solicitud.put("backup_solicitudes", true);
                EnviarNotificacionIndividual(solicitud, mensaje.get("idSocket").toString());
            }
        }
        if (mensaje.containsKey("backup_ccb_id")) {
            String query = "SELECT * FROM  solicitudes where id = '" + mensaje.get("folio") + "' AND activo = 1;"; //where fecha (si es calendarizado....)
            //System.out.println(query);
            JSONArray solicitudes = Query.execute(query);
            for (int i = 0; i < solicitudes.size(); i++) {
                JSONObject solicitud = (JSONObject) solicitudes.get(i);
                solicitud.put("bandeja_entrada", true);
                solicitud.put("backup_solicitudes", true);
                solicitud.put("focus", true);
                EnviarNotificacionIndividual(solicitud, mensaje.get("idSocket").toString());
            }
        }
        if (mensaje.containsKey("referencia_contrareferencia")) {
            String query = "SELECT * FROM  solicitudes WHERE"
                    + " (tipo_usuario = '" + mensaje.get("tipo_usuario") + "' AND tipo_servicio = '" + mensaje.get("tipo_servicio") + "' AND activo = 1 AND (fecha ='" + mensaje.get("fecha_calendario") + "' OR estado='Enviada')) OR (to_tipo_usuario = '" + mensaje.get("tipo_usuario") + "' AND to_tipo_servicio = '" + mensaje.get("tipo_servicio") + "' AND activo = 1 AND (fecha ='" + mensaje.get("fecha_calendario") + "'  OR estado='Enviada')) ;"; //AND fecha = '" + mensaje.get("fecha") + "'
            System.out.println(query);
            JSONArray solicitudes = Query.execute(query);
            for (int i = 0; i < solicitudes.size(); i++) {
                JSONObject solicitud = (JSONObject) solicitudes.get(i);
                solicitud.put("bandeja_entrada", true);
                solicitud.put("backup_solicitudes", true);
                EnviarNotificacionIndividual(solicitud, mensaje.get("idSocket").toString());
            }
        }
        //Enviar peticiones realizadas ..
        if (mensaje.containsKey("backup_institucion")) {
            //mandar estadisticas

            // traer peticiones realizadas ..
            String query = "SELECT * FROM  solicitudes WHERE tipo_usuario = '" + mensaje.get("tipo_usuario") + "' AND tipo_servicio = '" + mensaje.get("tipo_servicio") + "' AND activo = 1 AND fecha ='" + mensaje.get("fecha_calendario") + "';"; //AND fecha = '" + mensaje.get("fecha") + "'
            JSONArray solicitudes = Query.execute(query);
            for (int i = 0; i < solicitudes.size(); i++) {
                JSONObject solicitud = (JSONObject) solicitudes.get(i);
                solicitud.put("bandeja_entrada", true);
                solicitud.put("backup_solicitudes", true);
                EnviarNotificacionIndividual(solicitud, mensaje.get("idSocket").toString());
            }
        }
        if (mensaje.containsKey("backup_institucion_fecha")) {
            String query = "SELECT * FROM  solicitudes WHERE fecha = '" + mensaje.get("fecha_calendario") + "' AND activo = 1 AND tipo_usuario = '" + mensaje.get("tipo_usuario") + "' AND tipo_servicio = '" + mensaje.get("tipo_servicio") + "';"; //where fecha (si es calendarizado....)
            //System.out.println(query);
            JSONArray solicitudes = Query.execute(query);
            for (int i = 0; i < solicitudes.size(); i++) {
                JSONObject solicitud = (JSONObject) solicitudes.get(i);
                solicitud.put("bandeja_entrada", true);
                solicitud.put("backup_solicitudes", true);
                EnviarNotificacionIndividual(solicitud, mensaje.get("idSocket").toString());
            }
        }
        if (mensaje.containsKey("referencia_contrareferencia_fecha")) {
            String query = "SELECT * FROM  solicitudes WHERE fecha = '" + mensaje.get("fecha_calendario") + "' AND activo = 1 AND ((tipo_usuario = '" + mensaje.get("tipo_usuario") + "' AND tipo_servicio = '" + mensaje.get("tipo_servicio") + "') OR (to_tipo_usuario = '" + mensaje.get("tipo_usuario") + "' AND to_tipo_servicio = '" + mensaje.get("tipo_servicio") + "'));"; //where fecha (si es calendarizado....)
            //System.out.println(query);
            JSONArray solicitudes = Query.execute(query);
            for (int i = 0; i < solicitudes.size(); i++) {
                JSONObject solicitud = (JSONObject) solicitudes.get(i);
                solicitud.put("bandeja_entrada", true);
                solicitud.put("backup_solicitudes", true);
                EnviarNotificacionIndividual(solicitud, mensaje.get("idSocket").toString());
            }
        }
        if (mensaje.containsKey("referencia_contrareferencia_id")) {
            String query = "SELECT * FROM  solicitudes WHERE id = '" + mensaje.get("folio") + "' AND activo = 1 AND ((tipo_usuario = '" + mensaje.get("tipo_usuario") + "' AND tipo_servicio = '" + mensaje.get("tipo_servicio") + ") OR (to_tipo_usuario = '" + mensaje.get("tipo_usuario") + "' AND to_tipo_servicio = '" + mensaje.get("tipo_servicio") + "'))';"; //where fecha (si es calendarizado....)
            //System.out.println(query);
            JSONArray solicitudes = Query.execute(query);
            for (int i = 0; i < solicitudes.size(); i++) {
                JSONObject solicitud = (JSONObject) solicitudes.get(i);
                solicitud.put("bandeja_entrada", true);
                solicitud.put("backup_solicitudes", true);
                solicitud.put("focus", true);
                EnviarNotificacionIndividual(solicitud, mensaje.get("idSocket").toString());
            }
        }
        if (mensaje.containsKey("backup_institucion_id")) {
            String query = "SELECT * FROM  solicitudes WHERE id = '" + mensaje.get("folio") + "' AND activo = 1 AND tipo_usuario = '" + mensaje.get("tipo_usuario") + "' AND tipo_servicio = '" + mensaje.get("tipo_servicio") + "';"; //where fecha (si es calendarizado....)
            //System.out.println(query);
            JSONArray solicitudes = Query.execute(query);
            for (int i = 0; i < solicitudes.size(); i++) {
                JSONObject solicitud = (JSONObject) solicitudes.get(i);
                solicitud.put("bandeja_entrada", true);
                solicitud.put("backup_solicitudes", true);
                solicitud.put("focus", true);
                EnviarNotificacionIndividual(solicitud, mensaje.get("idSocket").toString());
            }
        }

        //falta tabla de convenios....
        if (mensaje.containsKey("backup_crum")) {

            //mandar estadisticas
//            String query = "SELECT COUNT(id) AS total_solicitudes FROM solicitudes WHERE  activo = 1;"; // total de solicitudes
//            JSONObject total_solicitudes = Query.select(query);
//            query = "SELECT COUNT(id)   AS total_solicitudes_pendientes FROM solicitudes WHERE estado = 'Enviada' AND activo = 1;"; // total de solicitudes pendientes
//            JSONObject total_solicitudes_pendientes = Query.select(query);
//            query = "SELECT COUNT(id)   AS total_solicitudes_proceso FROM solicitudes WHERE estado = 'Aceptada por UTC-19' AND activo = 1;"; // total de solicitudes en proceso
//            JSONObject total_solicitudes_proceso = Query.select(query);
//            query = "SELECT COUNT(id)   AS total_solicitudes_ruta FROM solicitudes WHERE estado = 'Aceptada por CRUM' AND activo = 1;"; // total de solicitudes en ruta
//            JSONObject total_solicitudes_ruta = Query.select(query);
//            query = "SELECT COUNT(id)   AS total_solicitudes_rechazado FROM solicitudes WHERE estado LIKE 'Rechazad%' AND activo = 1;"; // total de solicitudes rechazadas
//            JSONObject total_solicitudes_rechazado = Query.select(query);
//            query = "SELECT COUNT(id)   AS total_solicitudes_rechazado_auto FROM rechazos_automaticos;"; // total de solicitudes rechazadas automaticas
//            JSONObject total_solicitudes_rechazado_auto = Query.select(query);
//            query = "SELECT COUNT(id)   AS total_solicitudes_finalizadas FROM solicitudes WHERE estado LIKE 'Ingresado en%' AND activo = 1;"; // total de solicitudes finalizadas
//            JSONObject total_solicitudes_finalizadas = Query.select(query);
//
//            JSONObject Solicitudes = new JSONObject();
//            Solicitudes.put("total_solicitudes", total_solicitudes.get("total_solicitudes"));
//            Solicitudes.put("total_solicitudes_pendientes", total_solicitudes_pendientes.get("total_solicitudes_pendientes"));
//            Solicitudes.put("total_solicitudes_proceso", total_solicitudes_proceso.get("total_solicitudes_proceso"));
//            Solicitudes.put("total_solicitudes_ruta", total_solicitudes_ruta.get("total_solicitudes_ruta"));
//            Solicitudes.put("total_solicitudes_rechazado", Integer.parseInt(total_solicitudes_rechazado.get("total_solicitudes_rechazado").toString()) + Integer.parseInt(total_solicitudes_rechazado_auto.get("total_solicitudes_rechazado_auto").toString()));
//            Solicitudes.put("total_solicitudes_finalizadas", total_solicitudes_finalizadas.get("total_solicitudes_finalizadas"));
//            Solicitudes.put("estadisticas", true);
//            EnviarNotificacionIndividual(Solicitudes, mensaje.get("idSocket").toString());
            // traer las solicitudes pendientes....
            /*
                - Aceptado por UTC-19
                - Aceptado por CRUM
                - Rechazado por CRUM
                - Atendido
             */
            String query = "SELECT * FROM  solicitudes WHERE "
                    + "estado = 'Aceptada por UTC-19' OR "
                    + "(estado = 'Aceptada por CRUM' OR "
                    + "estado = 'Rechazada por CRUM' OR "
                    + "estado = 'Aceptada por SUCRE' OR "
                    + "estado = 'Rechazada por SUCRE' OR "
                    + "estado = 'En traslado' OR "
                    + "estado = 'En traslado SUCRE' OR "
                    + "estado = 'Dado de Alta por UTC-19' OR "
                    + "estado = 'Contrarreferencia' OR "
                    + "estado = 'No ingresó en UTC-19' OR "
                    + "estado = 'Ingresado en UTC-19') AND activo = 1 AND fecha = '" + mensaje.get("fecha_calendario") + "';";
            //System.out.println("Query en backup_crum");
            //System.out.println(query);

            JSONArray solicitudes = Query.execute(query);
            for (int i = 0; i < solicitudes.size(); i++) {
                JSONObject solicitud = (JSONObject) solicitudes.get(i);
                solicitud.put("bandeja_entrada", true);
                solicitud.put("backup_solicitudes", true);
                EnviarNotificacionIndividual(solicitud, mensaje.get("idSocket").toString());
            }
        }
        if (mensaje.containsKey("backup_crum_fecha")) {
            String query = "SELECT * FROM  solicitudes where fecha = '" + mensaje.get("fecha_calendario") + "' AND activo = 1;"; //where fecha (si es calendarizado....)
            //System.out.println(query);
            JSONArray solicitudes = Query.execute(query);
            for (int i = 0; i < solicitudes.size(); i++) {
                JSONObject solicitud = (JSONObject) solicitudes.get(i);
                solicitud.put("bandeja_entrada", true);
                solicitud.put("backup_solicitudes", true);
                EnviarNotificacionIndividual(solicitud, mensaje.get("idSocket").toString());
            }
        }
        if (mensaje.containsKey("backup_crum_id")) {
            String query = "SELECT * FROM  solicitudes where id = '" + mensaje.get("folio") + "' AND activo = 1;"; //where fecha (si es calendarizado....)
            //System.out.println(query);
            JSONArray solicitudes = Query.execute(query);
            for (int i = 0; i < solicitudes.size(); i++) {
                JSONObject solicitud = (JSONObject) solicitudes.get(i);
                solicitud.put("bandeja_entrada", true);
                solicitud.put("backup_solicitudes", true);
                solicitud.put("focus", true);
                EnviarNotificacionIndividual(solicitud, mensaje.get("idSocket").toString());
            }
        }

        //Traer test covid del personal ccb
        if (mensaje.containsKey("backup_utc19covid")) {
            String query = "SELECT * FROM  personal_ccb WHERE fecha = '" + mensaje.get("fecha_calendario") + "';";

            JSONArray testCovid = Query.execute(query);
            for (int i = 0; i < testCovid.size(); i++) {
                JSONObject test = (JSONObject) testCovid.get(i);
                query = "SELECT nombre, apellido_paterno , apellido_materno,fecha_nacimiento,acceso_ccb FROM usuarios_movil "
                        + "WHERE idUsuarios_Movil = '" + test.get("idUsuario") + "';";
                JSONObject perfil = Query.select(query);
                perfil.put("img", config.getPATH() + config.getDEPENDENCIA() + "/API/ConsultarImg/perfil/" + test.get("idUsuario"));
                perfil.put("icon", config.getPATH() + config.getDEPENDENCIA() + "/API/ConsultarImg/icono/" + test.get("idUsuario"));

                test.put("Perfil", perfil);
                query = "SELECT DISTINCT fecha FROM personal_ccb WHERE idUsuario='" + test.get("idUsuario") + "';";
                JSONArray array = Query.execute(query);
                JSONArray fechas = new JSONArray();
                for (int j = 0; j < array.size(); j++) {
                    JSONObject Fecha = (JSONObject) array.get(j);
                    fechas.add(Fecha.get("fecha"));
                }
                test.put("fechas_test", fechas);
                test.put("utc19covid", true);
                test.put("backup_utc19covid", true);
                EnviarNotificacionIndividual(test, mensaje.get("idSocket").toString());
            }
            query = "SELECT * FROM agenda_nuevas_llamada WHERE estado_llamada IS NULL AND fecha_nuevaLlamada='" + mensaje.get("fecha_calendario") + "'  AND rol_usuario = 1;";
            JSONArray nuevas_llamadas = Query.execute(query);
            for (int i = 0; i < nuevas_llamadas.size(); i++) {
                JSONObject llamada = (JSONObject) nuevas_llamadas.get(i);
                query = "SELECT fecha_nacimiento,genero,acceso_ccb FROM usuarios_movil "
                        + "WHERE idUsuarios_Movil = '" + llamada.get("idUsuario") + "';";
                JSONObject perfil = Query.select(query);
                perfil.put("img", config.getPATH() + config.getDEPENDENCIA() + "/API/ConsultarImg/perfil/" + llamada.get("idUsuario"));
                perfil.put("icon", config.getPATH() + config.getDEPENDENCIA() + "/API/ConsultarImg/icono/" + llamada.get("idUsuario"));
                perfil.put("nombre", llamada.get("nombre"));

                llamada.put("Perfil", perfil);
                llamada.put("utc19covid", true);
                llamada.put("backup_utc19covid", true);
                EnviarNotificacionIndividual(llamada, mensaje.get("idSocket").toString());
            }
        }
        if (mensaje.containsKey("backup_utc19covid_fecha")) {
            String query = "SELECT * FROM  personal_ccb WHERE fecha = '" + mensaje.get("fecha_calendario") + "';";

            JSONArray testCovid = Query.execute(query);
            for (int i = 0; i < testCovid.size(); i++) {
                JSONObject test = (JSONObject) testCovid.get(i);
                query = "SELECT nombre, apellido_paterno , apellido_materno,fecha_nacimiento,acceso_ccb FROM usuarios_movil "
                        + "WHERE idUsuarios_Movil = '" + test.get("idUsuario") + "';";
                JSONObject perfil = Query.select(query);
                perfil.put("img", config.getPATH() + config.getDEPENDENCIA() + "/API/ConsultarImg/perfil/" + test.get("idUsuario"));
                perfil.put("icon", config.getPATH() + config.getDEPENDENCIA() + "/API/ConsultarImg/icono/" + test.get("idUsuario"));

                test.put("Perfil", perfil);
                query = "SELECT DISTINCT fecha FROM personal_ccb WHERE idUsuario='" + test.get("idUsuario") + "';";
                JSONArray array = Query.execute(query);
                JSONArray fechas = new JSONArray();
                for (int j = 0; j < array.size(); j++) {
                    JSONObject Fecha = (JSONObject) array.get(j);
                    fechas.add(Fecha.get("fecha"));
                }
                test.put("fechas_test", fechas);
                test.put("utc19covid", true);
                test.put("backup_utc19covid", true);
                EnviarNotificacionIndividual(test, mensaje.get("idSocket").toString());
            }
            query = "SELECT * FROM agenda_nuevas_llamada WHERE fecha_nuevaLlamada = '" + mensaje.get("fecha_calendario") + "'  AND rol_usuario = 1;";
            JSONArray nuevas_llamadas = Query.execute(query);
            for (int i = 0; i < nuevas_llamadas.size(); i++) {
                JSONObject llamada = (JSONObject) nuevas_llamadas.get(i);
                query = "SELECT fecha_nacimiento,genero,acceso_ccb FROM usuarios_movil "
                        + "WHERE idUsuarios_Movil = '" + llamada.get("idUsuario") + "';";
                JSONObject perfil = Query.select(query);
                perfil.put("img", config.getPATH() + config.getDEPENDENCIA() + "/API/ConsultarImg/perfil/" + llamada.get("idUsuario"));
                perfil.put("icon", config.getPATH() + config.getDEPENDENCIA() + "/API/ConsultarImg/icono/" + llamada.get("idUsuario"));
                perfil.put("nombre", llamada.get("nombre"));

                llamada.put("Perfil", perfil);
                llamada.put("utc19covid", true);
                llamada.put("backup_utc19covid", true);
                EnviarNotificacionIndividual(llamada, mensaje.get("idSocket").toString());
            }
        }
        //Traer test covid de los pacientes
        if (mensaje.containsKey("backup_pacientes_testcovid")) {
            String query = "SELECT * FROM  ccb_test_pacientes WHERE fecha = '" + mensaje.get("fecha_calendario") + "';";

            JSONParser parser = new JSONParser();
            JSONArray testCovid = Query.execute(query);
            JSONArray usuarios = new JSONArray();
            for (int i = 0; i < testCovid.size(); i++) {
                usuarios.add(((JSONObject) testCovid.get(i)).get("idUsuario"));
            }
            if (!usuarios.isEmpty()) {
                usuarios = (JSONArray) parser.parse(request.POST(config.getURL_CONTROLADOR() + "API/ConsultaVariosPerfiles", usuarios.toString()));
                for (int i = 0; i < testCovid.size(); i++) {
                    JSONObject test = (JSONObject) testCovid.get(i);
                    for (int j = 0; j < usuarios.size(); j++) {
                        if (test.get("idUsuario").toString().equals(((JSONObject) usuarios.get(j)).get("idUsuarios_Movil").toString())) {
                            test.put("Perfil", usuarios.get(j));
                            break;
                        }
                    }
                    test.put("pacientes_testcovid", true);
                    test.put("backup_pacientes_testcovid", true);
                    EnviarNotificacionIndividual(test, mensaje.get("idSocket").toString());
                }
            }
            query = "SELECT * FROM agenda_nuevas_llamada WHERE estado_llamada IS NULL AND fecha_nuevaLlamada='" + mensaje.get("fecha_calendario") + "'  AND rol_usuario = 2;";
            JSONArray nuevas_llamadas = Query.execute(query);
            for (int i = 0; i < nuevas_llamadas.size(); i++) {
                usuarios.add(((JSONObject) nuevas_llamadas.get(i)).get("idUsuario"));
            }
            if (!usuarios.isEmpty()) {
                usuarios = (JSONArray) parser.parse(request.POST(config.getURL_CONTROLADOR() + "API/ConsultaVariosPerfiles", usuarios.toString()));

                for (int i = 0; i < nuevas_llamadas.size(); i++) {
                    JSONObject llamada = (JSONObject) nuevas_llamadas.get(i);
                    for (int j = 0; j < usuarios.size(); j++) {
                        if (llamada.get("idUsuario").toString().equals(((JSONObject) usuarios.get(j)).get("idUsuarios_Movil").toString())) {
                            llamada.put("Perfil", usuarios.get(j));
                            break;
                        }
                    }
                    llamada.put("pacientes_testcovid", true);
                    llamada.put("backup_pacientes_testcovid", true);
                    EnviarNotificacionIndividual(llamada, mensaje.get("idSocket").toString());
                }
            }
        }
        if (mensaje.containsKey("backup_pacientes_testcovid_fecha")) {
            String query = "SELECT * FROM  ccb_test_pacientes WHERE fecha = '" + mensaje.get("fecha_calendario") + "';";

            JSONParser parser = new JSONParser();
            JSONArray testCovid = Query.execute(query);
            JSONArray usuarios = new JSONArray();
            for (int i = 0; i < testCovid.size(); i++) {
                usuarios.add(((JSONObject) testCovid.get(i)).get("idUsuario"));
            }
            if (!usuarios.isEmpty()) {
                usuarios = (JSONArray) parser.parse(request.POST(config.getURL_CONTROLADOR() + "API/ConsultaVariosPerfiles", usuarios.toString()));
                for (int i = 0; i < testCovid.size(); i++) {
                    JSONObject test = (JSONObject) testCovid.get(i);
                    for (int j = 0; j < usuarios.size(); j++) {
                        if (test.get("idUsuario").toString().equals(((JSONObject) usuarios.get(j)).get("idUsuarios_Movil").toString())) {
                            test.put("Perfil", usuarios.get(j));
                            break;
                        }
                    }
                    test.put("pacientes_testcovid", true);
                    test.put("backup_pacientes_testcovid_fecha", true);
                    EnviarNotificacionIndividual(test, mensaje.get("idSocket").toString());
                }
            }
            query = "SELECT * FROM agenda_nuevas_llamada WHERE estado_llamada IS NULL AND fecha_nuevaLlamada='" + mensaje.get("fecha_calendario") + "'  AND rol_usuario = 2;";
            JSONArray nuevas_llamadas = Query.execute(query);
            for (int i = 0; i < nuevas_llamadas.size(); i++) {
                usuarios.add(((JSONObject) nuevas_llamadas.get(i)).get("idUsuario"));
            }
            if (!usuarios.isEmpty()) {
                usuarios = (JSONArray) parser.parse(request.POST(config.getURL_CONTROLADOR() + "API/ConsultaVariosPerfiles", usuarios.toString()));

                for (int i = 0; i < nuevas_llamadas.size(); i++) {
                    JSONObject llamada = (JSONObject) nuevas_llamadas.get(i);
                    for (int j = 0; j < usuarios.size(); j++) {
                        if (llamada.get("idUsuario").toString().equals(((JSONObject) usuarios.get(j)).get("idUsuarios_Movil").toString())) {
                            llamada.put("Perfil", usuarios.get(j));
                            break;
                        }
                    }
                    llamada.put("pacientes_testcovid", true);
                    llamada.put("backup_pacientes_testcovid_fecha", true);
                    EnviarNotificacionIndividual(llamada, mensaje.get("idSocket").toString());
                }
            }

        }

        if (mensaje.containsKey("backup_pacientes_diarios")) {
            String query = "select count(id) AS pacientes_ingresados,fecha from solicitudes  where estado like \"%Ingresado en UTC-19%\" group by fecha;";
            JSONArray ingreso_pacientes = Query.execute(query);
            JSONObject respuesta = new JSONObject();
            respuesta.put("pacientes_diarios", true);
            respuesta.put("data", ingreso_pacientes);
            EnviarNotificacionIndividual(respuesta, mensaje.get("idSocket").toString());
        }
        if (mensaje.containsKey("backup_contrareferencias")) {
            String query = "select count(id) AS pacientes_contrarreferencia,nombre_institucion from solicitudes  where estado like \"%Contrarreferencia%\" group by nombre_institucion;";
            JSONArray contrareferencias = Query.execute(query);
            JSONObject respuesta = new JSONObject();
            respuesta.put("contrarreferencia", true);
            respuesta.put("data", contrareferencias);
            EnviarNotificacionIndividual(respuesta, mensaje.get("idSocket").toString());
        }
        if (mensaje.containsKey("backup_pacientes_ambulancia")) {
            String query = "select count(id) AS CRUM from solicitudes WHERE ambulancia is not NULL AND ambulancia !=\"\" AND estado like \"%Ingresado en UTC-19%\";";
            //System.out.println(query);
            JSONObject crum = Query.select(query);
            //System.out.println(crum);
            query = "select count(id) AS SUCRE from solicitudes WHERE ambulanciaSUCRE is not NULL AND ambulanciaSUCRE !=\"\" AND estado like \"%Ingresado en UTC-19%\";";
            //System.out.println(query);
            JSONObject sucre = Query.select(query);
            //System.out.println(sucre);
            JSONArray pacientes_ambulancia = new JSONArray();
            pacientes_ambulancia.add(crum);
            pacientes_ambulancia.add(sucre);
            //System.out.println(pacientes_ambulancia);
            JSONObject respuesta = new JSONObject();
            respuesta.put("pacientes_ambulancia", true);
            respuesta.put("data", pacientes_ambulancia);
            //System.out.println(respuesta);
            EnviarNotificacionIndividual(respuesta, mensaje.get("idSocket").toString());
        }
        if (mensaje.containsKey("backup_hospitaldeorigen")) {
            String query = "select count(id) AS pacientes_enviados,nombre_institucion from solicitudes  where estado like \"%Ingresado en UTC-19%\" group by nombre_institucion;";
            JSONArray hospitaldeorigen = Query.execute(query);
            JSONObject respuesta = new JSONObject();
            respuesta.put("hospitaldeorigen", true);
            respuesta.put("data", hospitaldeorigen);
            EnviarNotificacionIndividual(respuesta, mensaje.get("idSocket").toString());
        }
        if (mensaje.containsKey("backup_estadisticas_graficas")) {
            JSONObject estadisticas = new JSONObject();
            String query = "SELECT \n"
                    + "(SELECT count(*) from rechazos_automaticos)+(SELECT count(*) from solicitudes where activo=1) AS total_solicitudes,\n"
                    + "(SELECT count(*) from rechazos_automaticos) AS no_procedentes,\n"
                    + "(SELECT count(*) from solicitudes where activo=1) AS procedentes,\n"
                    + "(SELECT count(*) from solicitudes where estado =\"Rechazada UTC-19\" AND activo=1) AS no_aceptadas,\n"
                    + "(SELECT count(*) from solicitudes where estado =\"Cancelada\"  AND activo=1) AS canceladas,\n"
                    + "(SELECT count(*) from solicitudes where estado LIKE \"%Enviada%\"  AND activo=1) AS revision,\n"
                    + "(SELECT count(*) from solicitudes where estado !=\"Rechazada UTC-19\" AND estado != \"Enviada\" AND activo=1) AS aceptadas,\n"
                    + "(SELECT count(*) from solicitudes where estado LIKE \"%Ingresado%\" AND activo=1) AS ingresado,\n"
                    + "(SELECT count(*) from solicitudes where estado LIKE \"%Dado de Alta%\" AND activo=1) AS alta,\n"
                    + "(SELECT count(*) from solicitudes where estado LIKE \"%Contrarr%\" AND activo=1) AS contrarreferencia,\n"
                    + "(SELECT count(id) from solicitudes WHERE ambulancia is not NULL AND ambulancia !=\"\" AND estado like \"%Ingresado en UTC-19%\" AND activo=1) AS crum,\n"
                    + "(SELECT count(id) from solicitudes WHERE ambulanciaSUCRE is not NULL AND ambulanciaSUCRE !=\"\" AND estado like \"%Ingresado en UTC-19%\" AND activo=1) AS sucre,\n"
                    + "(SELECT count(idUsuarios_Movil) FROM usuarios_movil where tipo_usuario=20) AS personal_utc,\n"
                    + "(SELECT count(idUsuarios_Movil) FROM usuarios_movil where tipo_usuario=19) AS personal_crum,\n"
                    + "(SELECT count(idUsuarios_Movil) FROM usuarios_movil where tipo_usuario=21) AS personal_sucre,\n"
                    + "(SELECT count(id) FROM vinculacion_familiar where activo=1) AS vinculaciones_familiares,\n"
                    + "(SELECT count(id) FROM vinculacion_paciente where activo=1) AS vinculaciones_pacientes,\n"
                    + "(SELECT count(id) FROM ccb_monitoreo_remoto) AS llamadas_programadas,\n"
                    + "(SELECT count(id) FROM ccb_test_pacientes) AS test_pacientes,\n"
                    + "(SELECT count(id) FROM personal_ccb) AS test_doctores\n"
                    + ";";
            estadisticas = Query.select(query);

            JSONObject respuesta = new JSONObject();
            respuesta.put("estadisticas_graficas", true);
            respuesta.put("data", estadisticas);
            EnviarNotificacionIndividual(respuesta, mensaje.get("idSocket").toString());
        }

        if (mensaje.containsKey("rreferencia_hospitalaria_solicitudes_id")) {

            JSONArray solicitudes = new JSONArray();
            JSONArray aux = new JSONArray();
            //buscar mis solicitudes
            //* solicitudes, estado, evento tipo_usuario y tipo servicio 
            String query = "SELECT E.nombre AS nombre_estado, R.* FROM estados_referencias_hospitalarias E, eventos_referencias_hospitalarias R "
                    + "WHERE E.id = R.id_estado_referencia_hospitalaria AND id_referencia_hospitalaria = '" + mensaje.get("folio") + "' ;"; //AND id_estado_referencia_hospitalaria = 1

            aux = Query.execute(query);

            for (int i = 0; i < aux.size(); i++) {
                solicitudes.add((JSONObject) aux.get(i));
            }

            query = "SELECT * FROM referencias_hospitalarias WHERE ";
            for (int i = 0; i < solicitudes.size(); i++) {
                System.out.println(i + " vs " + solicitudes.size());
                JSONObject solicitud = (JSONObject) solicitudes.get(i);
                query += " id = " + solicitud.get("id_referencia_hospitalaria");

                if (i < (solicitudes.size() - 1)) {
                    query += " OR ";
                } else {
                    query += " ORDER BY id; ";
                }
            }
            JSONArray data_solicitudes = new JSONArray();
            if (!solicitudes.isEmpty()) {
                data_solicitudes = Query.execute(query);
            }

            System.out.println(data_solicitudes.size() + "  vs  " + solicitudes.size());

            for (int i = 0; i < data_solicitudes.size(); i++) {
                JSONObject data_solicitud = (JSONObject) data_solicitudes.get(i);
                for (int j = 0; j < solicitudes.size(); j++) {
                    JSONObject solicitud = (JSONObject) solicitudes.get(j);
                    if (data_solicitud.get("id").toString().equals(solicitud.get("id_referencia_hospitalaria").toString())) {
                        solicitud.remove("id");
                        data_solicitud.putAll(solicitud);
                        data_solicitud.put("estado", data_solicitud.get("id_estado_referencia_hospitalaria"));
                        data_solicitud.put("solicitudes_referencia_hospitalaria", true);
                        EnviarNotificacionIndividual(data_solicitud, mensaje.get("idSocket").toString());
                        break;
                    }
                }
            }

        }

        //traer solicitudes recibidas, enviadas, o solicitadas en general traer las solicitudes //para las que cecesiten atencion instituciones
        if (mensaje.containsKey("referencia_hospitalaria_solicitudes_pendientes")) {
            System.out.println("referencia_hospitalaria_solicitudes_pendientes");
            JSONArray solicitudes = new JSONArray();
            JSONArray aux = new JSONArray();
            //buscar mis solicitudes
            //* solicitudes, estado, evento tipo_usuario y tipo servicio 
            String query = "SELECT E.nombre AS nombre_estado, R.* FROM estados_referencias_hospitalarias E, eventos_referencias_hospitalarias R "
                    + "WHERE tipo_usuario = '" + mensaje.get("tipo_usuario") + "' AND tipo_servicio = '" + mensaje.get("tipo_servicio") + "' AND E.id = R.id_estado_referencia_hospitalaria AND (R.id_estado_referencia_hospitalaria = 1 OR R.date_created = '" + mensaje.get("fecha_calendario") + "');"; //AND id_estado_referencia_hospitalaria = 1

            aux = Query.execute(query);
            for (int i = 0; i < aux.size(); i++) {
                solicitudes.add((JSONObject) aux.get(i));
            }

            //buscar solicitudes recibidas como institucion
            //* solicitudes, estado, evento to_tipo_usuario_institucion y to_tipo servicio_institucion
            query = "SELECT E.nombre AS nombre_estado, R.* FROM estados_referencias_hospitalarias E, eventos_referencias_hospitalarias R "
                    + "WHERE to_tipo_usuario_institucion = '" + mensaje.get("tipo_usuario") + "' AND to_tipo_servicio_institucion = '" + mensaje.get("tipo_servicio") + "'  AND E.id = R.id_estado_referencia_hospitalaria AND (id_estado_referencia_hospitalaria = 1 OR R.date_created = '" + mensaje.get("fecha_calendario") + "');"; // AND id_estado_referencia_hospitalaria = 1
            aux = Query.execute(query);
            for (int i = 0; i < aux.size(); i++) {
                solicitudes.add((JSONObject) aux.get(i));
            }

            //buscar solicitudes recibidas como institucion publicas 0 0
            //* solicitudes, estado, evento 0 , 0 y estado estado 1
            query = "SELECT E.nombre AS nombre_estado, R.* FROM estados_referencias_hospitalarias E, eventos_referencias_hospitalarias R "
                    + "WHERE to_tipo_usuario_institucion = '0' AND to_tipo_servicio_institucion = '0'  AND tipo_usuario !='" + mensaje.get("tipo_usuario") + "' AND tipo_servicio !='" + mensaje.get("tipo_servicio") + "' AND E.id = R.id_estado_referencia_hospitalaria AND (id_estado_referencia_hospitalaria = 1 OR R.date_created = '" + mensaje.get("fecha_calendario") + "');"; //AND id_estado_referencia_hospitalaria = 1
            aux = Query.execute(query);
            for (int i = 0; i < aux.size(); i++) {
                solicitudes.add((JSONObject) aux.get(i));
            }

            //buscar solicitudes recibidas para traslado
            //* solicitudes, estado, evento to_tipo_usuario_traslado y to_tipo_servicio_traslado
            query = "SELECT E.nombre AS nombre_estado, R.* FROM estados_referencias_hospitalarias E, eventos_referencias_hospitalarias R "
                    + "WHERE to_tipo_usuario_traslado = '" + mensaje.get("tipo_usuario") + "' AND to_tipo_servicio_traslado = '" + mensaje.get("tipo_servicio") + "' AND E.id = R.id_estado_referencia_hospitalaria AND (id_estado_referencia_hospitalaria = 2 OR R.date_created = '" + mensaje.get("fecha_calendario") + "');";// AND id_estado_referencia_hospitalaria = 2
            aux = Query.execute(query);
            for (int i = 0; i < aux.size(); i++) {
                solicitudes.add((JSONObject) aux.get(i));
            }

            //buscar solicitudes recibidas para traslado 0 0
            //* solicitudes, estado, evento 0 , 0 y estado estado 4
            query = "SELECT E.nombre AS nombre_estado, R.* FROM estados_referencias_hospitalarias E, eventos_referencias_hospitalarias R "
                    + "WHERE to_tipo_usuario_traslado = '0' AND to_tipo_servicio_traslado = '0'  AND E.id = R.id_estado_referencia_hospitalaria AND (id_estado_referencia_hospitalaria = 2 OR R.date_created = '" + mensaje.get("fecha_calendario") + "');";//AND id_estado_referencia_hospitalaria = 2
            aux = Query.execute(query);
            for (int i = 0; i < aux.size(); i++) {
                solicitudes.add((JSONObject) aux.get(i));
            }

            //ordenar las solicitudes 
            System.out.println(solicitudes);

            query = "SELECT * FROM referencias_hospitalarias WHERE ";
            for (int i = 0; i < solicitudes.size(); i++) {
                System.out.println(i + " vs " + solicitudes.size());
                JSONObject solicitud = (JSONObject) solicitudes.get(i);
                query += " id = " + solicitud.get("id_referencia_hospitalaria");

                if (i < (solicitudes.size() - 1)) {
                    query += " OR ";
                } else {
                    query += " ORDER BY id; ";
                }
            }
            JSONArray data_solicitudes = new JSONArray();
            if (!solicitudes.isEmpty()) {
                data_solicitudes = Query.execute(query);
            }

            System.out.println(data_solicitudes.size() + "  vs  " + solicitudes.size());

            for (int i = 0; i < data_solicitudes.size(); i++) {
                JSONObject data_solicitud = (JSONObject) data_solicitudes.get(i);
                for (int j = 0; j < solicitudes.size(); j++) {
                    JSONObject solicitud = (JSONObject) solicitudes.get(j);
                    if (data_solicitud.get("id").toString().equals(solicitud.get("id_referencia_hospitalaria").toString())) {
                        solicitud.remove("id");
                        data_solicitud.putAll(solicitud);
                        data_solicitud.put("estado", data_solicitud.get("id_estado_referencia_hospitalaria"));
                        data_solicitud.put("solicitudes_referencia_hospitalaria", true);
                        EnviarNotificacionIndividual(data_solicitud, mensaje.get("idSocket").toString());
                        break;
                    }
                }
            }

//            JSONObject respuesta = new JSONObject();
//            respuesta.put("solicitudes_referencia_hospitalaria", true);
//            respuesta.put("data", data_solicitudes);
//            System.out.println(respuesta);
//            EnviarNotificacionIndividual(respuesta, mensaje.get("idSocket").toString());
        }
        //traer solicitudes bajo demanda por fecha
        if (mensaje.containsKey("referencia_hospitalaria_solicitudes_fecha")) {
            System.out.println("referencia_hospitalaria_solicitudes_fecha");
            JSONArray solicitudes = new JSONArray();
            //buscar mis solicitudes
            //* solicitudes, estado, evento tipo_usuario y tipo servicio //mias
            String query = "SELECT * FROM eventos_referencias_hospitalarias WHERE tipo_usuario = '" + mensaje.get("tipo_usuario") + "' AND tipo_servicio = '" + mensaje.get("tipo_servicio") + "' AND date_created = '" + mensaje.get("fecha") + "';";
            solicitudes.add(Query.execute(query));

            //buscar solicitudes recibidas como institucion
            //* solicitudes, estado, evento to_tipo_usuario_institucion y to_tipo servicio_institucion //para mi
            query = "SELECT * FROM eventos_referencias_hospitalarias WHERE to_tipo_usuario_institucion = '" + mensaje.get("tipo_usuario") + "' AND to_tipo_servicio_institucion = '" + mensaje.get("tipo_servicio") + "' AND date_created = '" + mensaje.get("fecha") + "';";
            solicitudes.add(Query.execute(query));

            //buscar solicitudes recibidas como institucion publicas 0 0
            //* solicitudes, estado, evento 0 , 0 y estado estado 1 //publicas pero no para mi
            query = "SELECT * FROM eventos_referencias_hospitalarias WHERE to_tipo_usuario_institucion = '0' AND to_tipo_servicio_institucion = '0' AND date_created = '" + mensaje.get("fecha") + "' AND tipo_usuario_institucion ='" + mensaje.get("tipo_usuario") + "' AND tipo_servicio_institucion ='" + mensaje.get("tipo_servicio") + "';";
            solicitudes.add(Query.execute(query));

            //buscar solicitudes recibidas para traslado
            //* solicitudes, estado, evento to_tipo_usuario_traslado y to_tipo_servicio_traslado
            query = "SELECT * FROM eventos_referencias_hospitalarias WHERE to_tipo_usuario_traslado = '" + mensaje.get("tipo_usuario") + "' AND to_tipo_servicio_traslado = '" + mensaje.get("tipo_servicio") + "' AND fecha_aceptacion = '" + mensaje.get("fecha") + "';";
            solicitudes.add(Query.execute(query));
            //buscar solicitudes recibidas para traslado 0 0
            //* solicitudes, estado, evento 0 , 0 y estado estado 4
            query = "SELECT * FROM eventos_referencias_hospitalarias WHERE to_tipo_usuario_traslado = '0' AND to_tipo_servicio_traslado = '0' AND fecha_aceptacion = '" + mensaje.get("fecha") + "' AND tipo_usuario_traslado ='" + mensaje.get("tipo_usuario") + "' AND tipo_servicio_traslado ='" + mensaje.get("tipo_servicio") + "';";
            solicitudes.add(Query.execute(query));

            //ordenar las solicitudes 
            query = "SELECT * FROM referencias_hospitalarias WHERE ";
            for (int i = 0; i < solicitudes.size(); i++) {
                System.out.println(i + " vs " + solicitudes.size());
                JSONObject solicitud = (JSONObject) solicitudes.get(i);
                query += " id = " + solicitud.get("id_referencia_hospitalaria");
                if (i < (solicitudes.size() - 1)) {
                    query += " OR ";
                } else {
                    query += " ORDER BY id; ";
                }
            }
            JSONArray data_solicitudes = new JSONArray();
            if (!solicitudes.isEmpty()) {
                data_solicitudes = Query.execute(query);
            }

            System.out.println(data_solicitudes.size() + "  vs  " + solicitudes.size());

            for (int i = 0; i < data_solicitudes.size(); i++) {
                JSONObject data_solicitud = (JSONObject) data_solicitudes.get(i);
                for (int j = 0; j < solicitudes.size(); j++) {
                    JSONObject solicitud = (JSONObject) solicitudes.get(j);
                    if (data_solicitud.get("id").toString().equals(solicitud.get("id_referencia_hospitalaria").toString())) {
                        solicitud.remove("id");
                        data_solicitud.putAll(solicitud);
                        data_solicitud.put("estado", data_solicitud.get("id_estado_referencia_hospitalaria"));
                        data_solicitud.put("solicitudes_referencia_hospitalaria", true);
                        EnviarNotificacionIndividual(data_solicitud, mensaje.get("idSocket").toString());
                        break;
                    }
                }
            }

//            JSONObject respuesta = new JSONObject();
//            respuesta.put("solicitudes_referencia_hospitalaria", true);
//            respuesta.put("data", data_solicitudes);
//            System.out.println(respuesta);
//            EnviarNotificacionIndividual(respuesta, mensaje.get("idSocket").toString());
        }

        if (mensaje.containsKey("notificacion")) {
            EnviarNotificacio_id360(mensaje, mensaje.get("id360").toString());
        }
        if (mensaje.containsKey("estatus_usuarios_linea")) {
            /*
            mensaje:"{
            
            estatus_usuarios_linea:true
            data:[{id360:1},{id360:2},{id360:3},{id360:4},....,n]
            }
             */
            JSONArray users = consultar_usuarios_linea((JSONArray) mensaje.get("data"));
            mensaje.put("data", users);
            EnviarNotificacionIndividual(mensaje, mensaje.get("idSocket").toString());
        }

    }

    @OnClose
    public void handleClose(Session userSession) throws IOException {

        int n_conexion = 0;
        //Obtenemos los datos de quien se desconecto 
        JSONObject mensaje = new JSONObject();
        Iterator<Session> iterator = chatroomUsers.iterator();
        while (iterator.hasNext()) {
            Session s = iterator.next();
            try {
                if (s.getUserProperties().get("id360").toString().equals(userSession.getUserProperties().get("id360").toString())) {
                    n_conexion++;
                    mensaje.put("tipo_usuario", userSession.getUserProperties().get("tipo_usuario"));
                    mensaje.put("tipo_servicio", userSession.getUserProperties().get("tipo_servicio"));
                    mensaje.put("tipo_area", userSession.getUserProperties().get("tipo_area"));
                    mensaje.put("id360", userSession.getUserProperties().get("id360"));
                }
                if (n_conexion > 1) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("fallo");
                System.out.println(e);
            }

        }
        //Remover la sesion 
        chatroomUsers.remove(userSession);

        //si es la ultima conexion enviamos una notificacion 
        if (n_conexion <= 1) {
            mensaje.put("usuario_desconectado", true);
            EnviarNotificacion(mensaje);
        }
    }

    @OnError
    public void onError(Throwable t) {
        //System.out.println("Error en SocketEndPoint " + config.getDEPENDENCIA());
        ////System.out.println(t);
    }

    private JSONObject JSONObject_Parse(String string) {
        JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        try {
            json = (JSONObject) parser.parse(string);
        } catch (ParseException ex) {
            //System.out.println("ERROR(Parseo): " + SocketEndPoint.class.getName() + "   " + string);
            //Logger.getLogger(SocketEndPoint.class.getName()).log(Level.SEVERE, "Error de parseo", ex);

        }
        return json;
    }

    static boolean revisar_socket(String idSocket) {
        Iterator<Session> iterator = chatroomUsers.iterator();
        while (iterator.hasNext()) {
            Session s = iterator.next();

            try {
                System.out.println(s.getId() + " vs " + idSocket);
                if (s.getId().equals(idSocket)) {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("falla");
                System.out.println(e.getMessage());
            }

        }
        return false;
    }

    public static String EnviarNotificacion(JSONObject m) throws IOException {
        //System.out.println(m);
        //System.out.println("Enviando Notificacion a todos--->" + config.getDEPENDENCIA());
        Iterator<Session> iterator = chatroomUsers.iterator();
        while (iterator.hasNext()) {
            Session s = iterator.next();
            boolean notificar = false;
            try {
                if (s.getUserProperties().get("tipo_usuario") != null) {
                    System.out.println("/*/*/*/*/*/*/*/*/*/*/*/");
                    System.out.println(s.getUserProperties());
                    System.out.println(m);
                }

                if (s.getUserProperties().get("tipo_usuario") != null && s.getUserProperties().get("tipo_servicio") != null && s.getUserProperties().get("tipo_area") != null) {
                    System.out.println("a");
                    //preguntar si es usuario maestro a nivel estatal
                    if (((String) s.getUserProperties().get("tipo_usuario")).equals("0")
                            && ((String) s.getUserProperties().get("tipo_servicio")).equals("0")
                            && ((String) s.getUserProperties().get("tipo_area")).equals("0")) {
                        notificar = true;
                        System.out.println("b");
                    }
                    //preguntar si corresponde al tipo de usuario
                    if (((String) s.getUserProperties().get("tipo_usuario")).equals(((String) m.get("tipo_usuario")))) {
                        //preguntar si es usuario maestro a nivel dependencia
                        System.out.println("d");
                        if (((String) s.getUserProperties().get("tipo_servicio")).equals("0")
                                && ((String) s.getUserProperties().get("tipo_area")).equals("0")) {
                            notificar = true;
                            System.out.println("e");
                        }
                        //preguntar si corresponde al tipo de usuario
                        if (((String) s.getUserProperties().get("tipo_servicio")).equals((String) m.get("tipo_servicio"))) {
                            if (((String) s.getUserProperties().get("tipo_area")).equals("0")) {
                                notificar = true;
                                System.out.println("f");
                            }
                            //preguntar si corresponde al tipo de usuario
                            if (((String) s.getUserProperties().get("tipo_area")).equals((String) m.get("tipo_area"))) {
                                notificar = true;
                                System.out.println("g");
                            }
                        }
                    }
                }
                System.out.println("notifica: " + notificar);

                if (notificar) {
                    s.getBasicRemote().sendText(m.toString());
                }
            } catch (Exception e) {
                //System.out.println("falla");
                //System.out.println(e);
            }

        }
        return "done";
    }

    public static String actualizar_disponibilidad_sesion(String id360, boolean f) {
        //System.out.println(m);
        //System.out.println("Enviando Notificacion a todos--->" + config.getDEPENDENCIA());
        Iterator<Session> iterator = chatroomUsers.iterator();
        while (iterator.hasNext()) {
            Session s = iterator.next();

            try {
                if (s.getUserProperties().get("gpsOTS") != null && s.getUserProperties().get("id360") != null) {
                    if (s.getUserProperties().get("id360").toString().equals(id360)) {
                        s.getUserProperties().put("gpsOTS", f);
                    }
                }

            } catch (Exception e) {
                System.out.println("falla");
                System.out.println(e);
            }
        }
        return "done";
    }

    public static void EnviarNotificacionIndividual(JSONObject m, String idSocket) throws IOException {
        //System.out.println("Enviando Notificacion individual " + config.getDEPENDENCIA());
        Iterator<Session> iterator = chatroomUsers.iterator();
        while (iterator.hasNext()) {
            try {
                Session usuario = iterator.next();
                //Enviara la llamada a todos los usuarios que tengan el mismo id de estado?
                if (usuario.getId().equals(idSocket)) {
                    usuario.getBasicRemote().sendText(m.toString());
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error enviando mensaje:");
                System.out.println(e.getMessage());
            }

        }
    }
    ///////////////////////////************************

    public static void EnviarNotificacio_id360_array(JSONObject m, JSONArray id360) throws IOException {
        //System.out.println("Enviando Notificacion al municipio " + config.getDEPENDENCIA());
        boolean notificado = false;
        Iterator<Session> iterator = chatroomUsers.iterator();
        while (iterator.hasNext()) {
            try {
                
                Session usuario = iterator.next();
                for (int i = 0; i < id360.size(); i++) {
                    JSONObject id = (JSONObject) id360.get(i);
                    if(usuario.getUserProperties().get("id360").equals(id.get("to_id360"))){
                        usuario.getBasicRemote().sendText(m.toString());
                        break;
                    }
                }
//                if (usuario.getUserProperties().get("id360").equals(id360)) {
//                    //System.out.println("Not Enviada");
//                    usuario.getBasicRemote().sendText(m.toString());
//                    notificado = true;
//                }
            } catch (Exception e) {
                System.out.println("Error enviando mensaje:");
                System.out.println(e.getMessage());
            }

        }
        //return notificado;
    }
    public static boolean EnviarNotificacio_id360(JSONObject m, String id360) throws IOException {
        //System.out.println("Enviando Notificacion al municipio " + config.getDEPENDENCIA());
        boolean notificado = false;
        Iterator<Session> iterator = chatroomUsers.iterator();
        while (iterator.hasNext()) {
            try {
                Session usuario = iterator.next();
                if (usuario.getUserProperties().get("id360").equals(id360)) {
                    //System.out.println("Not Enviada");
                    usuario.getBasicRemote().sendText(m.toString());
                    notificado = true;
                }
            } catch (Exception e) {
                System.out.println("Error enviando mensaje:");
                System.out.println(e.getMessage());
            }

        }
        return notificado;
    }

    public static boolean EnviarNotificacio_jerarqia(JSONObject m, String tipo_usuario, String tipo_servicio, String tipo_area) throws IOException {
        //System.out.println("Enviando Notificacion al municipio " + config.getDEPENDENCIA());
        boolean notificado = false;
        Iterator<Session> iterator = chatroomUsers.iterator();
        while (iterator.hasNext()) {
            try {
                Session usuario = iterator.next();
                if (usuario.getUserProperties().get("tipo_usuario").equals(tipo_usuario)
                        && usuario.getUserProperties().get("tipo_servicio").equals("0")
                        && usuario.getUserProperties().get("tipo_area").equals("0")) {
                    //System.out.println("Not Enviada");
                    usuario.getBasicRemote().sendText(m.toString());
                    notificado = true;
                } else if (usuario.getUserProperties().get("tipo_usuario").equals(tipo_usuario)
                        && usuario.getUserProperties().get("tipo_servicio").equals(tipo_servicio)
                        && usuario.getUserProperties().get("tipo_area").equals("0")) {
                    usuario.getBasicRemote().sendText(m.toString());
                    notificado = true;

                } else if (usuario.getUserProperties().get("tipo_usuario").equals(tipo_usuario)
                        && usuario.getUserProperties().get("tipo_servicio").equals(tipo_servicio)
                        && usuario.getUserProperties().get("tipo_area").equals(tipo_area)) {
                    usuario.getBasicRemote().sendText(m.toString());
                    notificado = true;

                }
            } catch (Exception e) {
                System.out.println("Error enviando mensaje:");
                System.out.println(e.getMessage());
            }

        }
        return notificado;
    }

    public static boolean EnviarNotificacio_tipo_usuario(JSONObject m, String tipo_usuario) throws IOException {
        //System.out.println("Enviando Notificacion al municipio " + config.getDEPENDENCIA());
        boolean notificado = false;
        Iterator<Session> iterator = chatroomUsers.iterator();
        while (iterator.hasNext()) {
            try {
                Session usuario = iterator.next();
                if (usuario.getUserProperties().get("tipo_usuario").equals(tipo_usuario)
                        && usuario.getUserProperties().get("tipo_servicio").equals("0")
                        && usuario.getUserProperties().get("tipo_area").equals("0")) {
                    //System.out.println("Not Enviada");
                    usuario.getBasicRemote().sendText(m.toString());
                    notificado = true;
                }
            } catch (Exception e) {
                System.out.println("Error enviando mensaje:");
                System.out.println(e.getMessage());
            }

        }
        return notificado;
    }

    public static boolean EnviarNotificacio_tipo_servicio(JSONObject m, String tipo_servicio) throws IOException {
        //System.out.println("Enviando Notificacion al municipio " + config.getDEPENDENCIA());
        boolean notificado = false;
        Iterator<Session> iterator = chatroomUsers.iterator();
        while (iterator.hasNext()) {
            try {
                Session usuario = iterator.next();
                if (usuario.getUserProperties().get("tipo_servicio").equals(tipo_servicio)) {
                    //System.out.println("Not Enviada");
                    usuario.getBasicRemote().sendText(m.toString());
                    notificado = true;
                }
            } catch (Exception e) {
                System.out.println("Error enviando mensaje:");
                System.out.println(e.getMessage());
            }

        }
        return notificado;
    }

    public static boolean EnviarNotificacio_tipo_area(JSONObject m, String tipo_area) throws IOException {
        //System.out.println("Enviando Notificacion al municipio " + config.getDEPENDENCIA());
        boolean notificado = false;
        Iterator<Session> iterator = chatroomUsers.iterator();
        while (iterator.hasNext()) {
            try {
                Session usuario = iterator.next();
                if (usuario.getUserProperties().get("tipo_area").equals(tipo_area)) {
                    //System.out.println("Not Enviada");
                    usuario.getBasicRemote().sendText(m.toString());
                    notificado = true;
                }
            } catch (Exception e) {
                System.out.println("Error enviando mensaje:");
                System.out.println(e.getMessage());
            }

        }
        return notificado;
    }

    public static void EnviarNotificacio_id360(JSONArray m, String id360) throws IOException {
        //System.out.println("Enviando Notificacion al municipio " + config.getDEPENDENCIA());
        Iterator<Session> iterator = chatroomUsers.iterator();
        while (iterator.hasNext()) {
            try {
                Session usuario = iterator.next();
                if (usuario.getUserProperties().get("id360").equals(id360)) {
                    //System.out.println("Not Enviada");
                    usuario.getBasicRemote().sendText(m.toString());
                }
            } catch (Exception e) {
                System.out.println("Error enviando mensaje:");
                System.out.println(e.getMessage());
            }

        }
    }

    ///////////////////////////************************
    public static void EnviarNotificacionIndividual(JSONArray m, String idSocket) throws IOException {
        //System.out.println("Enviando Notificacion individual " + config.getDEPENDENCIA());
        Iterator<Session> iterator = chatroomUsers.iterator();
        while (iterator.hasNext()) {
            Session usuario = iterator.next();
            if (usuario.getId().equals(idSocket)) {
                usuario.getBasicRemote().sendText(m.toString());
                break;
            }

        }
    }

    private static String buildJsonData(String name, String message) {

        JsonObject jsonObject = Json.createObjectBuilder().add(name, message).build();
        StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
            jsonWriter.write(jsonObject);
        }
        return stringWriter.toString();
    }

    public static boolean CompararFechas(Date date1, Date date2, long limit) {
        long diferencia = (Math.abs(date1.getTime() - date2.getTime())) / 1000;//limit es el limite para que este dentro del rango (EN MINUTOS)
        return diferencia <= (limit * 60);//pasamos el limite de tiempo a segundos
    }

    public static void AddBackupReporteElemento(JSONObject json) {
        BackupReportes.add(json);
    }

    public static void AddBackupCOVID19(JSONObject json) {

        BackupCOVID.add(json);

    }

    public static void AddBackupLlamada(JSONObject json) {
        Iterator<JSONObject> iterator = BackupLlamadas.iterator();
        while (iterator.hasNext()) {
            JSONObject llamada = iterator.next();
            JSONObject RegistroLlamadaBackup = (JSONObject) llamada.get("RegistroLlamada");
            JSONObject RegistroLlamada = (JSONObject) json.get("RegistroLlamada");

            if (RegistroLlamadaBackup.get("idUsuarios_Movil") == RegistroLlamada.get("idUsuarios_Movil")) {
                iterator.remove();
                break;
            }

        }

        BackupLlamadas.add(json);
    }

    public static void NotificarCOVID(JSONObject json) throws IOException {
        EnviarNotificacion(json);
        AddBackupCOVID19(json);
    }

    public static void NotificarLlamada(JSONObject json) throws IOException {
        System.out.println("Notificando llamada a tipo_usuario = " + json.get("tipo_usuario")
                + " y tipo_servcio = " + json.get("tipo_servicio"));
        EnviarNotificacion(json);
        AddBackupLlamada(json);
    }

    public static JSONArray consultar_usuarios_linea(JSONArray users) throws IOException {
        String query = null;
        for (Object user : users) {
            ((JSONObject) user).put("online_web", false);
            //obtener conexion en la app - Buscar en los registros gps 
            query = "SELECT idUsuarios_Movil as id360, Fecha as fecha, Hora as hora FROM registro_rutas where idUsuarios_Movil='" + ((JSONObject) user).get("id360") + "' order by idregistro_rutas desc limit 1;";
            JSONObject user_movil = Query.select(query);
            if (user_movil != null) {
                ((JSONObject) user).putAll(user_movil);
            }

            Iterator<Session> iterator = chatroomUsers.iterator();
            while (iterator.hasNext()) {
                try {
                    Session s = iterator.next();
                    if (s.getUserProperties().get("id360").equals(((JSONObject) user).get("id360").toString())) {
                        //System.out.println("Not Enviada");

                        ((JSONObject) user).put("online_web", true); //tiempo real 
                        ((JSONObject) user).put("tipo_usuario", s.getUserProperties().get("tipo_usuario")); //tiempo real 
                        ((JSONObject) user).put("tipo_servicio", s.getUserProperties().get("tipo_servicio")); //tiempo real 

                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Error enviando mensaje:");
                    System.out.println(e.getMessage());
                }

            }
        }
        return users;
    }

    public static boolean is_primera_conexion(String id360) throws IOException {
        boolean primera_conexion = true;

        Iterator<Session> iterator = chatroomUsers.iterator();
        while (iterator.hasNext()) {
            try {
                Session s = iterator.next();
                if (s.getUserProperties().get("id360").equals(id360)) {
                    primera_conexion = false;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error enviando mensaje:");
                System.out.println(e.getMessage());
            }
        }
        return primera_conexion;
    }

    private JSONObject respuesta(boolean success, String message) {
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", success);
        respuesta.put("failure", !success);
        respuesta.put("mensaje", message);
        return respuesta;
    }

}
