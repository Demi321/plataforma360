/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Query;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.json.simple.JSONArray;
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
 * @author Moises Juarez
 */
@Controller
@Async

public class ControllerComunicacion {

    private JSONObject respuesta(boolean success, String message) {
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", success);
        respuesta.put("failure", !success);
        respuesta.put("mensaje", message);
        return respuesta;
    }

     /* 
    AÑADIR USUARIO COMO FAVORITO
     */
    @RequestMapping(value = "/API/empresas360/agregar_usuario_favorito", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject agregar_usuario_favorito(@RequestBody JSONObject json) throws IOException, ParseException {

        JSONObject respuesta = respuesta(false, "Error al agregar al usuario");

        String queryUpdate = "UPDATE usuarios_favoritos "
                + "SET estatus_favorito = 1, date_updated = '" + json.get("fecha") + "', time_updated = '" + json.get("hora") + "' "
                + "WHERE id360 = '" + json.get("id360") + "' AND id360_favorito = '" + json.get("id360Favorito") + "' ";

        if (!Query.update(queryUpdate)) {

            String queryInsert = "INSERT INTO usuarios_favoritos (id360, id360_favorito, date_created, time_created, esGrupo) "
                    + " VALUES ('" + json.get("id360") + "', '" + json.get("id360Favorito") + "', '" + json.get("fecha") + "', '" + json.get("hora") + "', '" + json.get("esGrupo") + "') ";

            if (Query.insert(queryInsert) > 0) {
                respuesta = respuesta(true, "Usuario agregado como favorito");
                json.put("nuevo_usuario_favorito", true);
                SocketEndPoint.EnviarNotificacio_id360(json, json.get("id360").toString());
            }

        } else {
            json.put("nuevo_usuario_favorito", true);
            SocketEndPoint.EnviarNotificacio_id360(json, json.get("id360").toString());
            respuesta = respuesta(true, "Usuario agregado como favorito");
        }

        return respuesta;

    }

    /*
    ELIMINAR USUARIO COMO FAVORITO
     */
    @RequestMapping(value = "/API/empresas360/eliminar_usuario_favorito", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject eliminar_usuario_favorito(@RequestBody JSONObject json) throws IOException, ParseException {

        JSONObject respuesta = respuesta(false, "Error al eliminar al usuario");

        String queryUpdate = "UPDATE usuarios_favoritos "
                + "SET estatus_favorito = 0, date_updated = '" + json.get("fecha") + "', time_updated = '" + json.get("hora") + "' "
                + "WHERE id360 = '" + json.get("id360") + "' AND id360_favorito = '" + json.get("id360Favorito") + "' ";

        if (Query.update(queryUpdate)) {
            json.put("elimina_usuario_favorito", true);
            SocketEndPoint.EnviarNotificacio_id360(json, json.get("id360").toString());
            respuesta = respuesta(true, "Usuario eliminado como favorito");
        }

        return respuesta;

    }
    
    /*
    Consultar participantes de grupo
     */
    @RequestMapping(value = "/API/empresas360/consultar_participantes_grupos", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray consultar_participantes_grupos(@RequestBody JSONObject json) throws IOException, ParseException {

        String query = "select id_participantes from participantes_grupos_chat_empresarial where estatus_en_grupo = 1 and id_grupo = "+json.get("idGroup")+";";
        
        return Query.execute(query);

    }

    /*
    Consultar USUARIO COMO FAVORITO
     */
    @RequestMapping(value = "/API/empresas360/consultar_usuario_favorito", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray consultar_usuario_favorito(@RequestBody JSONObject json) throws IOException, ParseException {

        String query = "SELECT 	* , " +
                        "   ( " +
                        "	select 	count(*) " +
                        "	from 	chat_empresarial " +
                        "	where 	((id360 = '" + json.get("id360") + "' and to_id360 = f.id360_favorito) " +
                        "	or 		(id360 = f.id360_favorito and to_id360 = '" + json.get("id360") + "')) " +
                        "	and leido = 0 and to_id360 = '" + json.get("id360") + "' " +
                        "   ) as cantidadMensajesNoLeidos, " +
                        "   ( " +
                        "	select 	group_concat( id ) " +
                        "	from 	chat_empresarial " +
                        "	where 	((id360 = '" + json.get("id360") + "' and to_id360 = f.id360_favorito) " +
                        "	or 		(id360 = f.id360_favorito and to_id360 = '" + json.get("id360") + "')) " +
                        "	and leido = 0 and to_id360 = '" + json.get("id360") + "' " +
                        "   ) as mensajesNoLeidos " +
                        "FROM usuarios_favoritos f " +
                        "WHERE id360 = '" + json.get("id360") + "' " +
                        "AND estatus_favorito = 1;";
        
        return Query.execute(query);

    }
    
    /*
    OBTENER CONFIGURACION DEL USUARIO (TONOS, ETC)
     */
    @RequestMapping(value = "/API/empresas360/configuracionUsuario", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray empresas360_configuracionUsuario(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        System.out.println("Obteniendo configuracion...");

        String query = "SELECT * FROM config_user WHERE id360 = " + json.get("id360");

        JSONArray config = Query.execute(query);
        return config;
    }

    /*
    CAMBIAR LA CONFIGURACION DEL USUARIO (TONOS, ETC)
     */
    @RequestMapping(value = "/API/empresas360/cambiaConfiguracionUsuario", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject empresas360_cambiaConfiguracionUsuario(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        System.out.println("Cambiando ´onfiguracion...");

        JSONObject respuesta = respuesta(true, "Informacion almacenada");

        String query = "UPDATE config_user SET "
                + "tono_mensaje = '" + json.get("tono_mensaje") + "', "
                + "tono_llamada = '" + json.get("tono_llamada") + "', "
                + "date_updated = '" + json.get("fecha") + "', "
                + "time_updated = '" + json.get("hora") + "' "
                + "WHERE id360 = '" + json.get("id360") + "' ";

        boolean update = Query.update(query);
        respuesta.put("update", update);

        if (!update) {

            String queryI = "INSERT INTO config_user (id360, tono_mensaje, tono_llamada, date_created, time_created) VALUES ("
                    + "'" + json.get("id360") + "' , '" + json.get("tono_mensaje") + "' , '" + json.get("tono_llamada") + "' , '" + json.get("fecha") + "' , '" + json.get("hora") + "'"
                    + ")";

            int insert = Query.insert(queryI);
            respuesta.put("insert", insert);
            respuesta.put("queryinsert", queryI);

        }

        respuesta.put("queryupdate", query);

        return respuesta;
    }
    
    /*
    SERVICIO PARA BUSCAR MENSAJES DENTRO DE UN CHAT
    */
    @RequestMapping(value = "/API/empresas360/buscar_mensajes", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray buscar_mensajes(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        
        String conversacion = " ( (id360 = '"+json.get("id360-1")+"' and to_id360 = '"+json.get("id360-2")+"') or (id360 = '"+json.get("id360-2")+"' and to_id360 = '"+json.get("id360-1")+"') ) ";
        String eliminados = "and ( if(id360 = '"+json.get("id360-2")+"', activo_id360, activo_to_id360) = 1 ) ";
        String grupos = "";
        if(json.containsKey("esGrupo")){
            conversacion = " ( idGroup = '"+json.get("id360-1")+"' ) ";
            eliminados = " ";
            grupos = " and to_id360 = '"+json.get("id360-2")+"' ";
        }
                
        String query = "select * " +
                        "from chat_empresarial " +
                        "where "+conversacion+" " +
                        eliminados +
                        "and activo = 1 " +
                        "and message like '%"+json.get("busqueda")+"%' "+
                        "and id360 is not null" +
                        grupos;
        
        return Query.execute(query);
        
    }
    
    /*
    SERVICIO PARA BUSCAR MENSAJES DE TIPO IMAGEN EN UN CHAT
    */
    @RequestMapping(value = "/API/empresas360/buscar_mensajes_imagenes", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray buscar_mensajes_imagenes(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        
        String conversacion = " ( (id360 = '"+json.get("id360-1")+"' and to_id360 = '"+json.get("id360-2")+"') or (id360 = '"+json.get("id360-2")+"' and to_id360 = '"+json.get("id360-1")+"') ) ";
        String eliminados = "and ( if(id360 = '"+json.get("id360-2")+"', activo_id360, activo_to_id360) = 1 ) ";
        String grupos = "";
        if(json.containsKey("esGrupo")){
            conversacion = " ( idGroup = '"+json.get("id360-1")+"' ) ";
            eliminados = " ";
            grupos = " and to_id360 = '"+json.get("id360-2")+"' ";
        }
                
        String query = "select * " +
                        "from chat_empresarial " +
                        "where "+conversacion+" " +
                        eliminados +
                        "and activo = 1 " +
                        "and ( type='jpg' or type='png' or type='jpeg' or type='gif' ) "+
                        grupos;
        
        return Query.execute(query);
        
    }
    
    /*
    SERVICIO PARA BUSCAR MENSAJES DE TIPO ARCHIVO
    */
    @RequestMapping(value = "/API/empresas360/buscar_mensajes_archivos", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray buscar_mensajes_archivos(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        
        String conversacion = " ( (id360 = '"+json.get("id360-1")+"' and to_id360 = '"+json.get("id360-2")+"') or (id360 = '"+json.get("id360-2")+"' and to_id360 = '"+json.get("id360-1")+"') ) ";
        String eliminados = "and ( if(id360 = '"+json.get("id360-2")+"', activo_id360, activo_to_id360) = 1 ) ";
        String grupos = "";
        if(json.containsKey("esGrupo")){
            conversacion = " ( idGroup = '"+json.get("id360-1")+"' ) ";
            eliminados = " ";
            grupos = " and to_id360 = '"+json.get("id360-2")+"' ";
        }
                
        String query = "select * " +
                        "from chat_empresarial " +
                        "where "+conversacion+" " +
                        eliminados +
                        "and activo = 1 " +
                        "and ( type!='jpg' and type!='png' and type!='jpeg' and type!='gif' and type!='text' )"+
                        grupos;
        
        return Query.execute(query);
        
    }
    
    /*
    SERVICIO PARA BUSCAR MENSAJES DE TIPO HIPERVINCULO
    */
    @RequestMapping(value = "/API/empresas360/buscar_mensajes_vinculos", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray buscar_mensajes_vinculos(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        
        String conversacion = " ( (id360 = '"+json.get("id360-1")+"' and to_id360 = '"+json.get("id360-2")+"') or (id360 = '"+json.get("id360-2")+"' and to_id360 = '"+json.get("id360-1")+"') ) ";
        String eliminados = "and ( if(id360 = '"+json.get("id360-2")+"', activo_id360, activo_to_id360) = 1 ) ";
        String grupos = "";
        if(json.containsKey("esGrupo")){
            conversacion = " ( idGroup = '"+json.get("id360-1")+"' ) ";
            eliminados = " ";
            grupos = " and to_id360 = '"+json.get("id360-2")+"' ";
        }
                
        String query = "select * " +
                        "from chat_empresarial " +
                        "where "+conversacion+" " +
                        eliminados +
                        "and activo = 1 " +
                        "and type = 'text' " +
                        "and (message like '%https://%' or message like '%http://%' or message like '%www.%' ) "+
                        grupos;
        
        return Query.execute(query);
        
    }

    /*
    SERVICIO PARA ENVIAR UN NUEVO MENSAJE DE CUALQUIER TIPO A TRAVES
    DEL CHAT EMPRESARIAL
     */
    @RequestMapping(value = "/API/empresas360/chat", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject empresas360_chat(@RequestBody String string) throws IOException, ParseException, java.text.ParseException {
        System.out.println("empresas360_chat");

        /* TRANSFORMAR LA INFORMACION DE LLEGADA */
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(string);
        JSONObject respuesta = respuesta(false, "Informacion no almacenada");
        
        if( json.get("message").equals("") ) return respuesta;
        
        /* CAMBIAR EL COTEJAMIENTO DE LA DB */
        Query.update("SET NAMES 'utf8mb4'");
        
        /* VARIABLES DE CONTROL */
        String queryChat = "", uniqueID = "";
        JSONArray participantes = new JSONArray();
        int cantidadParticipantes = 0;
        
        /* VALIDAR SI EL MENSAJE CORRESPONDE A UN GRUPO DE CHAT */
        if (json.containsKey("idGroup")) {
            
            json.put("leido","0");
            
            /* CONSULTAR LOS PARTICIPANTES DEL GRUPO */
            String queryParticipantes = "SELECT " +
                                        "    id_participantes " +
                                        "FROM " +
                                        "    participantes_grupos_chat_empresarial " +
                                        "WHERE " +
                                        "    estatus_en_grupo = 1 AND id_grupo = " + json.get("idGroup");
            
            /*  SI EL MENSAJE ES UNA RESPUESTA, ES NECESARIO CONSULTAR EL ID DEL MENSAJE CORRESPONDIENDO
                A CADA USUARIO A TRAVES DEL AGRUPADOR DE MENSAJES DE GRUPO*/
            if (json.containsKey("idResponse")) {
                queryParticipantes =    "SELECT " +
                                        "    id_participantes, " +
                                        "    (SELECT " +
                                        "            id " +
                                        "        FROM " +
                                        "            chat_empresarial " +
                                        "        WHERE " +
                                        "            to_id360 = pg.id_participantes " +
                                        "                AND agrupador_mensajes_grupos = (SELECT " +
                                        "                    agrupador_mensajes_grupos " +
                                        "                FROM " +
                                        "                    chat_empresarial " +
                                        "                WHERE " +
                                        "                    id = "+json.get("idResponse")+")) AS idMensajeRespondido " +
                                        "FROM " +
                                        "    participantes_grupos_chat_empresarial pg " +
                                        "WHERE " +
                                        "    estatus_en_grupo = 1 AND id_grupo = " + json.get("idGroup");
            }
            
            participantes = Query.execute(queryParticipantes);
            cantidadParticipantes = participantes.size();
            /*  GENERAR UNA CADENA ALEATORIA PARA AGRUPAR LOS MENSAJES DE GRUPO
                SE ENVIARA EL MISMO MENSAJE A CADA UNO DE LOS PARTICIPANTES,
                Y ADEMAS DE TENER EL ID DEL GRUPO, TENDRAN ESTA LLAVE PARA PODER
                AGRUPARLOS
            */
            uniqueID = UUID.randomUUID().toString();
            
            JSONArray jsonParaInsert = new JSONArray();
            /* CREAR UN ARREGLO CON CADA MENSAJE QUE SE ENVIARA */
            for (int x = 0; x < cantidadParticipantes; x++) {
                JSONObject p = (JSONObject) participantes.get(x);
                JSONObject aux = (JSONObject) json.clone();
                String idParticipante = p.get("id_participantes").toString();
                
                /* PARA CADA MENSAJE CAMBIAREMOS EL TO_ID360 */
                aux.replace("to_id360", idParticipante);
                aux.put("agrupador_mensajes_grupos", uniqueID);
                
                /* EN CADA INSERT HAY QUE CAMBIAR EL ID DEL MENSAJE RESPONDIDO (EN CASO DE QUE ASI SE REQUIERA) */
                if (json.containsKey("idResponse")) {
                    aux.replace("idResponse", p.get("idMensajeRespondido").toString() );
                }
                
                /* PARA EL MENSAJE DE LA PERSONA QUE ENVIA EL MENSAJE, SE DEBE PONER EN LEIDO */
                if( json.containsKey("id360") && "".equals(json.get("id360").toString()) && idParticipante.equals(json.get("id360").toString())){
                    aux.replace("leido","1");
                }
                
                jsonParaInsert.add(x,aux);
                
            }

            /* CREAMOS EL QUERY DE INSERT */
            queryChat = Query.createQueryInsertWithColumnsMultiple("chat_empresarial", jsonParaInsert);
            
        }else{
            /* EN CASO DE SER UN MENSAJE INDIVIDUAL SOLAMENTE SE CREA LA QUERY CON EL JSON DE ENVIO */
            queryChat = Query.createQueryInsertWithColumns("chat_empresarial", json);
        }
        
        /* EJECUTAR LA QUERY */
        int resultSend = Query.insert(queryChat);
        
        if (resultSend >= 0) {

            respuesta = respuesta(true, "Mensaje guardado");
            respuesta.putAll(json);
            json.put("chat_empresarial", true);
            json.put("destacado","0");
            
            /* GUARDAMOS EL ID DEL MENSJAE RECIENTEMENTE ENVIADO */
            json.put("id", resultSend);

            if (json.containsKey("idGroup")) {
                
                /*  SI ES UN MENSAJE DE GRUPO, SE ENVIARA A CADA UNO DE LOS PARITCIPANTES ACTIVOS DEL MISMO
                    POR LO TANTO SE REQUIERE EL ID DE CADA UNO DE LOS MENSAJES QUE SE ENVIO*/
                
                String queryParticipantes = "SELECT " +
                                            "    id_participantes, " +
                                            "    (SELECT " +
                                            "            id " +
                                            "        FROM " +
                                            "            chat_empresarial " +
                                            "        WHERE " +
                                            "            agrupador_mensajes_grupos = '"+uniqueID+"' " +
                                            "                AND to_id360 = pg.id_participantes) AS idMensaje " +
                                            "FROM " +
                                            "    participantes_grupos_chat_empresarial pg " +
                                            "WHERE " +
                                            "    estatus_en_grupo = 1 AND id_grupo = "+ json.get("idGroup");
                
                /* SI EL MENSAJE ES UNA RESPUESTA, CONSULTAR EL ID DE RESPUESTA DE CADA PARTICIPANTE Y EL MENSAJE DE RESPUESTA TEXTUAL*/
                if (json.containsKey("idResponse")) {
                    queryParticipantes =    "SELECT " +
                                            "    id_participantes, " +
                                            "    (SELECT " +
                                            "            id " +
                                            "        FROM " +
                                            "            chat_empresarial " +
                                            "        WHERE " +
                                            "            agrupador_mensajes_grupos = '"+uniqueID+"' " +
                                            "                AND to_id360 = pg.id_participantes) AS idMensaje, " +
                                            "    (SELECT " +
                                            "            message " +
                                            "        FROM " +
                                            "            chat_empresarial " +
                                            "        WHERE " +
                                            "            id = "+json.get("idResponse")+") AS mensajeRespondido, " +
                                            "    (SELECT " +
                                            "            id " +
                                            "        FROM " +
                                            "            chat_empresarial " +
                                            "        WHERE " +
                                            "            to_id360 = pg.id_participantes " +
                                            "                AND agrupador_mensajes_grupos = (SELECT " +
                                            "                    agrupador_mensajes_grupos " +
                                            "                FROM " +
                                            "                    chat_empresarial " +
                                            "                WHERE " +
                                            "                    id = "+json.get("idResponse")+")) AS idMensajeRespondido " +
                                            "FROM " +
                                            "    participantes_grupos_chat_empresarial pg " +
                                            "WHERE " +
                                            "    estatus_en_grupo = 1 AND id_grupo = " + json.get("idGroup");
                }
                
                participantes = Query.execute(queryParticipantes);
                cantidadParticipantes = participantes.size();
                
                json.put("p",participantes);
                
                for (int x = 0; x < cantidadParticipantes; x++) {
                    JSONObject p = (JSONObject) participantes.get(x);
                    JSONObject aux = (JSONObject) json.clone();
                    
                    /* A CADA USUARIO SE LE ENVIA EL MENSAJE CON EL ID QUE LE CORRESPONDA DESPUES DE REALIZAR EL BROADCAST */
                    aux.replace("id", p.get("idMensaje").toString());
                    
                    if (json.containsKey("idResponse")) {
                        
                        /* EN CASO DE UNA RESPUESTA, SE DEBE DE IGUAL FORMA CAMBIAR EL ID DEL MENSAJE RESPONDIDO PARA CADA USUARIO */
                        JSONObject mensajeRespondido = new JSONObject();
                        mensajeRespondido.put("id", p.get("idMensajeRespondido").toString() );
                        mensajeRespondido.put("message", p.get("mensajeRespondido").toString() );
                        
                        aux.put("mensajeRespondido", mensajeRespondido);
                        aux.replace("idResponse", p.get("idMensajeRespondido").toString());
                        
                    }
                    
                    SocketEndPoint.EnviarNotificacio_id360(aux, p.get("id_participantes").toString());
                }

            } else {
                
                /*
                SI ES UN MENSAJE INDIVIDUAL, SOLO SE ENVIA LA NOTIFICACION POR SOCKET AL USUARIO QUE
                ENVIA Y AL USUARIO QUE RECIBE
                */
                
                if (json.containsKey("idResponse")) {
                    /*
                    SI ES UNA RESPUESTA, CONSULTAMOS LA INFORMACION DEL MENSAJE ENVIADO
                    */
                    String querySelect = "SELECT message, id FROM chat_empresarial WHERE id = " + json.get("idResponse");
                    JSONObject dResponse = Query.select(querySelect);
                    json.put("respuesta", true);
                    json.put("mensajeRespondido", dResponse);
                }
                
                /* ENVIAR POR SOCKET AL DESTINATARIO */
                SocketEndPoint.EnviarNotificacio_id360(json, json.get("to_id360").toString());
                /* ENVIAR POR SOCKET AL REMITENTE */
                json.put("chat_empresarial_mio", true);
                SocketEndPoint.EnviarNotificacio_id360(json, json.get("id360").toString());
            }

        }
        return respuesta;
    }

    /*
    OBTENER CONFIGURACION DEL USUARIO (TONOS, ETC)
     */
    @RequestMapping(value = "/API/empresas360/infoGrupo", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray empresas360_infoGrupo(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        System.out.println("Obteniendo configuracion...");

        String query = "SELECT * FROM grupos_chat_empresarial where id_grupo = " + json.get("id_grupo");

        JSONArray config = Query.execute(query);
        return config;
    }

    /*
    SERVICIO PARA AGREGAR UN NUEVO GRUPO DE CHAT EMPRESARIAL, ESTE RECIBE LOS DATOS 
    DEL GRUPO Y LOS PARTICIPANTES QUE SE AGREGARAN
     */
    @RequestMapping(value = "/API/empresas360/crear_grupo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject empresas360_crear_grupo(@RequestBody String string) throws IOException, ParseException, java.text.ParseException {
        System.out.println("Creando grupo");
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(string);
        JSONObject respuesta = respuesta(false, "Grupo no creado");

        String query = "INSERT INTO grupos_chat_empresarial (nombre_grupo, icono_grupo, descripcion_grupo, date_created, time_created, idUser) VALUES ("
                + " '" + json.get("nombre_grupo") + "' , '" + json.get("icono_grupo") + "' , '" + json.get("descripcion_grupo") + "' , '" + json.get("fecha") + "' , '" + json.get("hora") + "' , '" + json.get("idUser") + "' "
                + ")";

        int resultCreated = Query.insert(query);
        if (resultCreated >= 0) {
            respuesta = respuesta(true, "Grupo creado");
            respuesta.putAll(json);
            respuesta.put("id_grupo", resultCreated);

            /*
            AGREGAR PARTICIPANTES
             */
            JSONArray participantes = (JSONArray) json.get("participantes");
            System.out.println(json);
            System.out.println(participantes);
            String queryInsert = "INSERT INTO participantes_grupos_chat_empresarial (id_grupo, id_participantes, rol) VALUES (" + resultCreated + ", '" + json.get("idUser") + "', 1) , ";
            int cantidadParticipantes = participantes.size();

            for (int x = 0; x < cantidadParticipantes; x++) {
                queryInsert += " (" + resultCreated + ", '" + participantes.get(x) + "', 0) , ";
                System.out.println(queryInsert);
            }

            System.out.println("Query final" + queryInsert);
            queryInsert = queryInsert.substring(0, queryInsert.length() - 2);

            int insert = Query.insert(queryInsert);
            if (insert >= 0) {

                //Enviar por socket
                json.put("grupo_chat_empresarial", true);
                json.put("id_grupo", resultCreated);
                json.put("destacado", "0");

                String agregados = "";
                for (int x = 0; x < cantidadParticipantes; x++) {
                    SocketEndPoint.EnviarNotificacio_id360(json, participantes.get(x).toString());
                    agregados += participantes.get(x).toString() + ",";
                }
                respuesta.put("invitacion enviada a", agregados);
                respuesta.put("participantesAgregados", true);

            } else {
                respuesta.put("participantesAgregados", false);
            }

        }
        respuesta.put("query", query);
        return respuesta;
    }

    /*
    SERVICIO PARA EDITAR UN MENSAJE QUE HAYA SIDO ENVIADO ANTERIORME
     */
    @RequestMapping(value = "/API/empresas360/edita_mensaje", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject empresas360_edita_mensaje(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        System.out.println("Editando mensaje");

        JSONObject respuesta = respuesta(false, "Mensaje no editado");

        String query = "update chat_empresarial set "
                + "oldMessage = message, "
                + "message = '" + json.get("mensaje_editado") + "', "
                + "date_updated = '" + json.get("fecha_edita") + "', "
                + "time_updated = '" + json.get("hora_edita") + "' where id = " + json.get("idMensaje");
        
        int id_grupo = 0;
        String agrupador = "";
        
        if(json.containsKey("esGrupo")){
            
            String querySelect = "select agrupador_mensajes_grupos, idGroup from chat_empresarial where id = " + json.get("idMensaje");
            JSONArray resultSelect = Query.execute(querySelect);
            JSONObject dataResult = (JSONObject) resultSelect.get(0);
            agrupador = dataResult.get("agrupador_mensajes_grupos").toString();
            id_grupo = Integer.parseInt(dataResult.get("idGroup").toString());
            
            query = "update chat_empresarial set "
                    + "oldMessage = message, "
                    + "message = '" + json.get("mensaje_editado") + "', "
                    + "date_updated = '" + json.get("fecha_edita") + "', "
                    + "time_updated = '" + json.get("hora_edita") + "' where agrupador_mensajes_grupos = '"+agrupador+"' ";
            
        }

        if (Query.update(query)) {
            respuesta = respuesta(true, "Mensaje editado");
            respuesta.putAll(json);

            String querySelect = "SELECT *, (SELECT message FROM chat_empresarial sc WHERE sc.id = idResponse) as mensajeRespuesta FROM chat_empresarial WHERE id = " + json.get("idMensaje");
            JSONObject dataNew = Query.select(querySelect);
            respuesta.put("nuevo", dataNew);
            json.put("nuevo", dataNew);

            //Enviar por socket
            json.put("edicion_mensaje_chat_empresarial", true);
            
            if(json.containsKey("esGrupo")){
                
                String queryParticipantes = "SELECT " +
                                            "    id_participantes, " +
                                            "    (SELECT " +
                                            "            id " +
                                            "        FROM " +
                                            "            chat_empresarial " +
                                            "        WHERE " +
                                            "            to_id360 = pg.id_participantes " +
                                            "			AND agrupador_mensajes_grupos = '"+agrupador+"') as idMensaje " +
                                            "FROM " +
                                            "    participantes_grupos_chat_empresarial pg " +
                                            "WHERE " +
                                            "    estatus_en_grupo = 1 AND id_grupo = " + id_grupo;
                
                JSONArray participantes = Query.execute(queryParticipantes);
                int cantidadParticipantes = participantes.size();
                
                for (int x = 0; x < cantidadParticipantes; x++) {
                    JSONObject p = (JSONObject) participantes.get(x);
                    String id_participante  = p.get("id_participantes").toString();
                    String id_mensaje       = p.get("idMensaje").toString();
                    
                    JSONObject aux = (JSONObject) json.clone();
                    aux.replace("idMensaje", id_mensaje);
                    
                    if(id_participante.equals(json.get("id360"))){
                        aux.put("edicion_mensaje_chat_empresarial_mio", true);
                        SocketEndPoint.EnviarNotificacio_id360(aux, id_participante);
                        continue;
                    }
                    
                    SocketEndPoint.EnviarNotificacio_id360(aux, id_participante);
                }
                
            }else{
                System.out.println("No es grupo");
                System.out.println("Para" + json.get("to_id360"));
                SocketEndPoint.EnviarNotificacio_id360(json, (String) json.get("to_id360"));
                json.put("edicion_mensaje_chat_empresarial_mio", true);
                System.out.println("De" + json.get("id360"));
                SocketEndPoint.EnviarNotificacio_id360(json, (String) json.get("id360"));
            }
            
        }else{
            respuesta.put("query",query);
        }

        return respuesta;

    }

    /*
    SERVICIO PARA HACER ADMINISTRADOR UN PARTICIPANTE DE GRUPO
     */
    @RequestMapping(value = "/API/empresas360/agrega_administrador_grupo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject agrega_administrador_grupo(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        JSONObject respuesta = respuesta(false, "Error al agregar administrador");

        String query = "update participantes_grupos_chat_empresarial "
                + "set rol = 1 "
                + "where id_grupo = " + json.get("idGroup") + " and id_participantes = '" + json.get("id360") + "' ";

        if (Query.update(query)) {
            respuesta = respuesta(true, "Administrador Agregado");
            json.put("eres_administrador", true);
            SocketEndPoint.EnviarNotificacio_id360(json, (String) json.get("to_id360"));
        }

        return respuesta;
    }

    /*
    SERVICIO PARA ELIMINAR PARTICIPANTE DEL GRUPO
     */
    @RequestMapping(value = "/API/empresas360/elimina_participante_grupo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject elimina_participante_grupo(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        JSONObject respuesta = respuesta(false, "Error al quitar participante");

        String query = "update participantes_grupos_chat_empresarial "
                + "set estatus_en_grupo = 0 "
                + "where id_grupo = " + json.get("idGroup") + " and id_participantes = '" + json.get("id360") + "' ";

        if (Query.update(query)) {
            respuesta = respuesta(true, "Usuario Eliminado");
            json.put("eliminado_grupo", true);
            
            String queryParticipantes = "select id_participantes from participantes_grupos_chat_empresarial where estatus_en_grupo = 1 and id_grupo = " + json.get("idGroup");
            JSONArray participantes = Query.execute(queryParticipantes);
            int cantidadParticipantes = participantes.size();
            
            for (int x = 0; x < cantidadParticipantes; x++) {
                JSONObject p = (JSONObject) participantes.get(x);
                SocketEndPoint.EnviarNotificacio_id360(json, p.get("id_participantes").toString());
            }
            
            SocketEndPoint.EnviarNotificacio_id360(json, json.get("id360").toString());
            
        }

        return respuesta;
    }

    /*
    SERVICIO PARA CAMBIAR PARAMETRO DE UN GRUPO (ICONO, TITULO O DESCRIPCION)
     */
    @RequestMapping(value = "/API/empresas360/cambiar_parametro_grupo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject cambiar_parametro_grupo(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        JSONObject respuesta = respuesta(false, "Error al cambiar el parametro");

        String query = "update grupos_chat_empresarial set "
                + json.get("columna") + " = '" + json.get("valor") + "', "
                + "date_updated = '" + json.get("fecha") + "', "
                + "time_updated = '" + json.get("hora") + "' "
                + "where id_grupo = '" + json.get("idGroup") + "';";

        if (Query.update(query)) {
            respuesta = respuesta(true, "Valor cambiado exitosamente");
            json.put("cambio_parametro_grupo", true);
            
            String queryParticipantes = "select id_participantes from participantes_grupos_chat_empresarial where id_grupo = " + json.get("idGroup");
            JSONArray participantes = Query.execute(queryParticipantes);
            int cantidadParticipantes = participantes.size();

            String agregados = "";
            for (int x = 0; x < cantidadParticipantes; x++) {
                JSONObject p = (JSONObject) participantes.get(x);
                SocketEndPoint.EnviarNotificacio_id360(json, p.get("id_participantes").toString());
                agregados += p.get("id_participantes").toString() + ",";
            }
            respuesta.put("enviado a", agregados);
            
        }
        
        return respuesta;
    }

    /*
    SERVICIO PARA QUITAR PERMISOS DE ADMINISTRADOR 
     */
    @RequestMapping(value = "/API/empresas360/elimina_administrador_grupo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject elimina_administrador_grupo(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        JSONObject respuesta = respuesta(false, "Error al quitar administrador");

        String query = "update participantes_grupos_chat_empresarial "
                + "set rol = 0 "
                + "where id_grupo = " + json.get("idGroup") + " and id_participantes = '" + json.get("id360") + "' ";

        if (Query.update(query)) {
            respuesta = respuesta(true, "Administrador Eliminado");
            json.put("eliminado_administrador", true);
            SocketEndPoint.EnviarNotificacio_id360(json, (String) json.get("to_id360"));
        }

        return respuesta;
    }

    /*
    SERVICIO PARA AGREGAR PARTICIPANTES DENTRO DE UN GRUPO DE CHAT EMPRESARIAL
     */
    @RequestMapping(value = "/API/empresas360/agrega_participantes_grupo_chat_empresarial", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject empresas360_agrega_participantes_grupo_chat_empresarial(@RequestBody String string) throws IOException, ParseException, java.text.ParseException {
        System.out.println("Agregando participante");

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(string);
        JSONObject respuesta = respuesta(false, "Participantes no añadidos");

        JSONArray participantes = (JSONArray) json.get("participantes");
        String queryInsert = "INSERT INTO participantes_grupos_chat_empresarial (id_grupo, id_participantes) VALUES ";
        String queryUpdate = "UPDATE participantes_grupos_chat_empresarial SET estatus_en_grupo = 1 WHERE id_grupo = " + json.get("idGrupo") + " AND id_participantes IN ( ";
        int cantidadParticipantes = participantes.size();
        for (int x = 0; x < cantidadParticipantes; x++) {
            queryInsert += " (" + json.get("idGrupo") + ", " + participantes.get(x) + ") , ";
            queryUpdate += " " + participantes.get(x) + " , ";
        }

        System.out.println(queryInsert);
        queryInsert = queryInsert.substring(0, queryInsert.length() - 2);
        queryUpdate = queryUpdate.substring(0, queryUpdate.length() - 2);
        queryUpdate += ");";

        Query.insert(queryInsert);
        Query.update(queryUpdate);
        respuesta = respuesta(true, "Participantes añadidos");
        //Enviar por socket
        json.put("agregado_grupo_chat_empresarial", true);
        json.put("id_grupo", json.get("idGrupo"));

        String queryParticipantes = "select id_participantes from participantes_grupos_chat_empresarial where estatus_en_grupo = 1 and id_grupo = " + json.get("idGrupo");
        participantes = Query.execute(queryParticipantes);
        cantidadParticipantes = participantes.size();
        String enviados = "";
        
        for (int x = 0; x < cantidadParticipantes; x++) {
            JSONObject p = (JSONObject) participantes.get(x);
            System.out.println( p.get("id_participantes").toString() );
            SocketEndPoint.EnviarNotificacio_id360(json, p.get("id_participantes").toString());
            enviados += p.get("id_participantes") + ",";
        }
        json.put("agregados", enviados);
        respuesta.put("agregadis", enviados);

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

        String query = "UPDATE chat_empresarial SET activo = 0 WHERE id = " + json.get("idMensaje");
        int id_grupo = 0;
        String agrupador = "";
        
        if(json.containsKey("esGrupo")){
            
            String querySelect = "select agrupador_mensajes_grupos, idGroup from chat_empresarial where id = " + json.get("idMensaje");
            JSONArray resultSelect = Query.execute(querySelect);
            JSONObject dataResult = (JSONObject) resultSelect.get(0);
            agrupador = dataResult.get("agrupador_mensajes_grupos").toString();
            id_grupo = Integer.parseInt(dataResult.get("idGroup").toString());
            
            query = "UPDATE chat_empresarial SET activo = 0 WHERE agrupador_mensajes_grupos = '"+agrupador+"' ";
            
        }

        if (Query.update(query)) {
            respuesta = respuesta(true, "Mensaje eliminado");
            respuesta.putAll(json);
            //Enviar por socket
            json.put("eliminacion_mensaje_chat_empresarial", true);
            
            if(json.containsKey("esGrupo")){
                
                String queryParticipantes = "SELECT " +
                                            "    id_participantes, " +
                                            "    (SELECT " +
                                            "            id " +
                                            "        FROM " +
                                            "            chat_empresarial " +
                                            "        WHERE " +
                                            "            to_id360 = pg.id_participantes " +
                                            "			AND agrupador_mensajes_grupos = '"+agrupador+"') as idMensaje " +
                                            "FROM " +
                                            "    participantes_grupos_chat_empresarial pg " +
                                            "WHERE " +
                                            "    estatus_en_grupo = 1 AND id_grupo = " + id_grupo;
                
                JSONArray participantes = Query.execute(queryParticipantes);
                int cantidadParticipantes = participantes.size();
                
                for (int x = 0; x < cantidadParticipantes; x++) {
                    JSONObject p = (JSONObject) participantes.get(x);
                    String id_participante  = p.get("id_participantes").toString();
                    String id_mensaje       = p.get("idMensaje").toString();
                    
                    JSONObject aux = (JSONObject) json.clone();
                    aux.replace("idMensaje", id_mensaje);
                    
                    if(id_participante.equals(json.get("id360"))){
                        aux.put("eliminacion_mensaje_chat_empresarial_mio", true);
                        SocketEndPoint.EnviarNotificacio_id360(aux, id_participante);
                        continue;
                    }
                    
                    SocketEndPoint.EnviarNotificacio_id360(aux, id_participante);
                }
                
            }else{
                SocketEndPoint.EnviarNotificacio_id360(json, (String) json.get("to_id360"));
                json.put("eliminacion_mensaje_chat_empresarial_mio", true);
                SocketEndPoint.EnviarNotificacio_id360(json, (String) json.get("id360"));
            }

        }

        return respuesta;
    }

    /*
    SERVICIO PARA ELIMINAR UN MENSAJE SOLO PARA EL USUARIO QUE LO DESA BORRAR
    (NO AMBAS PARTES)
     */
    @RequestMapping(value = "/API/empresas360/eliminaMensajeParaMi", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject empresas360_eliminaMensajeMio(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        System.out.println("Eliminando mensaje...");
        JSONObject respuesta = respuesta(false, "Mensaje no eliminado");

        String query = "update  chat_empresarial "
                + "set     activo_id360 = if(id360 = '" + json.get("idUser") + "', 0, activo_id360),"
                + "    activo_to_id360 = if(to_id360 = '" + json.get("idUser") + "', 0, activo_to_id360)"
                + "where   id = " + json.get("idMensaje");

        if (Query.update(query)) {
            respuesta = respuesta(true, "Mensaje eliminado");
            respuesta.putAll(json);
            json.put("eliminacion_mensaje_chat_empresarial_solo_mio", true);
            SocketEndPoint.EnviarNotificacio_id360(json, (String) json.get("idUser"));
        }

        return respuesta;
    }
    
    /*
    SERVICIO PARA CONSULTAR LOS MENSAJES DESTACADOS DE UNA PERSONA
    */
    @RequestMapping(value = "/API/empresas360/consultar_mensajes_destacados", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray consultar_mensajes_destacados(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {

        String query = "select *, if(id360 = '" + json.get("idUser") + "', date(fecha_destacado_id360), date(fecha_destacado_to_id360)) as fechaDestacado " +
                        "from chat_empresarial " +
                        "where if(id360 = '" + json.get("idUser") + "', destacado_id360, destacado_to_id360) = 1 " +
                        "and if(id360 = '" + json.get("idUser") + "', activo_id360, activo_to_id360) = 1 " +
                        "and activo = 1 " +
                        "order by if(id360 = '" + json.get("idUser") + "', fecha_destacado_id360, fecha_destacado_to_id360) desc;";

        return Query.execute(query);
    }
    
    /*
    SERVICIO PARA DESTACAR UN MENSAJE
     */
    @RequestMapping(value = "/API/empresas360/destacar_mensaje", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject empresas360_destacar_mensaje(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        System.out.println("Destacando mensaje...");
        JSONObject respuesta = respuesta(false, "Mensaje no destacado");

        String query = "update 	chat_empresarial p " +
                        "set  	destacado_id360 = if(id360 = '" + json.get("idUser") + "', 1, destacado_id360), " +
                        "	destacado_to_id360 = if(to_id360 = '" + json.get("idUser") + "', 1, destacado_to_id360), " +
                        "       fecha_destacado_id360 = if(id360 = '" + json.get("idUser") + "', NOW(), fecha_destacado_id360)," +
                        "       fecha_destacado_to_id360 = if(to_id360 = '" + json.get("idUser") + "', NOW(), fecha_destacado_to_id360) " +
                        "where  id = " + json.get("idMensaje");

        if (Query.update(query)) {
            respuesta = respuesta(true, "Mensaje destacado");
            respuesta.putAll(json);
            json.put("mensaje_destacado", true);
            
            query = "select *, if(id360 = '" + json.get("idUser") + "', date(fecha_destacado_id360), date(fecha_destacado_to_id360)) as fechaDestacado " +
                    "from chat_empresarial " +
                    "where id = " + json.get("idMensaje");
            json.put("data", Query.select(query));
            
            SocketEndPoint.EnviarNotificacio_id360(json, (String) json.get("idUser"));
        }

        respuesta.put("query",query);
        return respuesta;
    }
    
    /*
    SERVICIO PARA ELIMINAR MENSAJE DESTACADO
     */
    @RequestMapping(value = "/API/empresas360/eliminar_destacado", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject empresas360_eliminar_destacado(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        System.out.println("Borrando mensaje destacado...");
        JSONObject respuesta = respuesta(false, "Mensaje no eliminado como destacado");

        String query = "update 	chat_empresarial p " +
                        "set  	destacado_id360 = if(id360 = '" + json.get("idUser") + "', 0, destacado_id360), " +
                        "	destacado_to_id360 = if(to_id360 = '" + json.get("idUser") + "', 0, destacado_to_id360) " +
                        "where  id = " + json.get("idMensaje");

        if (Query.update(query)) {
            respuesta = respuesta(true, "Elimina mensaje destacado");
            respuesta.putAll(json);
            json.put("eliminado_mensaje_destacado", true);
            
            query = "select *, if(id360 = '" + json.get("idUser") + "', date(fecha_destacado_id360), date(fecha_destacado_to_id360)) as fechaDestacado " +
                    "from chat_empresarial " +
                    "where id = " + json.get("idMensaje");
            json.put("data", Query.select(query));
            
            SocketEndPoint.EnviarNotificacio_id360(json, (String) json.get("idUser"));
        }

        return respuesta;
    }
    
    /*
    SERVICIO PARA ELIMINAR TODO EL HISTORIAL DE MENSAJES QUE SE TENGA CON UN CONTACTO
     */
    @RequestMapping(value = "/API/empresas360/vaciarChat", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject empresas360_vaciarChat(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        System.out.println("Eliminando chat...");
        JSONObject respuesta = respuesta(false, "Chat no eliminado");

        String query = "update  chat_empresarial "
                + "set     activo_id360 = if(id360 = '" + json.get("idUser") + "', 0, activo_id360), "
                + "    activo_to_id360 = if(to_id360 = '" + json.get("idUser") + "', 0, activo_to_id360) "
                + "where   (id360 = '" + json.get("idUser") + "' and to_id360 = '" + json.get("idContact") + "') "
                + "or  (id360 = '" + json.get("idContact") + "' and to_id360 = '" + json.get("idUser") + "');";

        if (Query.update(query)) {
            respuesta = respuesta(true, "Chat eliminado");
            respuesta.putAll(json);
        }

        return respuesta;
    }

    /*
    NUEVO BACKUP, SOLO RECUPERAR 20 MENSAJES DE CADA USUARIO CON CHAT
     */
    @RequestMapping(value = "/API/empresas360/backup_chat", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray empresas360_backup_chat(@RequestBody JSONObject json) throws java.text.ParseException {

        String query = ""
                + "SELECT"
                + " replace(concat(p.id360,p.to_id360),'" + json.get("id360") + "','') as id360Chat,"
                + " p.*, "
                + " (SELECT message FROM chat_empresarial sc WHERE sc.id = p.idResponse) as mensajeRespuesta, "
                + " if(p.id360 = '" + json.get("id360") + "', p.destacado_id360, p.destacado_to_id360) as destacado "
                + "FROM"
                + "  chat_empresarial p"
                + "  "
                + "  INNER JOIN ("
                + "     select"
                + "         replace(concat(p.id360,p.to_id360),'" + json.get("id360") + "','') as id360Chat,"
                + "         group_concat( id order by date_created desc ) idsMessages"
                + "     from chat_empresarial p"
                + "     where (p.id360 = '" + json.get("id360") + "' OR p.to_id360 = '" + json.get("id360") + "')"
                + "             and ( if(p.id360 = '" + json.get("id360") + "', activo_id360, activo_to_id360) = 1 )"
                + "             and ( p.idGroup IS NULL )"
                + "     group by id360Chat"
                + "  ) messages"
                + "  ON replace(concat(p.id360,p.to_id360),'" + json.get("id360") + "','') = messages.id360Chat"
                + "  "
                + "  AND FIND_IN_SET(id, idsMessages) BETWEEN 1 AND 20"
                + "     "
                + "ORDER BY"
                + "       p.id;";

        String queryGrupos = "select "
                + "	p.*, "
                + "	(select message from chat_empresarial sc where sc.id = p.idResponse) as mensajeRespuesta, "
                + "     if(p.id360 = '" + json.get("id360") + "', p.destacado_id360, p.destacado_to_id360) as destacado "
                + "from "
                + "	chat_empresarial p "
                + "	inner join ("
                + "		 select "
                + "                 p.idGroup, "
                + "                 group_concat( p.id order by p.id desc ) idsMessages "
                + "		 from	chat_empresarial p "
                + "		 where 	p.idGroup in ( "
                + "			select distinct pg.id_grupo from participantes_grupos_chat_empresarial pg "
                + "                     where pg.id_grupo = p.idGroup and pg.id_participantes = '" + json.get("id360") + "' "
                + "		 ) and ( if(p.id360 = '" + json.get("id360") + "', activo_id360, activo_to_id360) = 1 ) "
                + "                 and p.to_id360 = '" + json.get("id360") + "'"
                + "		 group by p.idGroup "
                + "              order by p.id"
                + "    ) messages "
                + "    on p.idGroup = messages.idGroup "
                + "	and FIND_IN_SET(id, idsMessages) BETWEEN 1 AND 20 "
                + "     "
                + "where p.idGroup IS NOT NULL "
                + "order by p.id;";

        JSONArray mensajesNormales = Query.execute(query);
        JSONArray mensajesGrupos = Query.execute(queryGrupos);

        mensajesNormales.addAll(mensajesGrupos);

        return ordenaBackupBurbuja(mensajesNormales);
    }

    public JSONArray ordenaBackupBurbuja(JSONArray bakcup) throws java.text.ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1, date2;

        JSONObject auxiliar, arregloJ, arregloJ1;
        for (int i = 2; i < bakcup.size(); i++) {
            for (int j = 1; j < (bakcup.size() - 1); j++) {

                arregloJ = (JSONObject) bakcup.get(j);
                arregloJ1 = (JSONObject) bakcup.get(j + 1);

                date1 = dateFormat.parse(arregloJ.get("date_created").toString() + " " + arregloJ.get("time_created").toString() );
                date2 = dateFormat.parse(arregloJ1.get("date_created").toString() + " " + arregloJ1.get("time_created").toString() );

                if (date1.after(date2)) {
                    auxiliar = arregloJ;
                    bakcup.set(j, arregloJ1);
                    bakcup.set(j + 1, auxiliar);
                }
            }
        }
        //bakcup.add(consultaEmojis());
        return bakcup;

    }

    /*
    SERVICIO PARA CARGAR MAS MENSAJES DE UNA CONVERSACION ESPECIFICA
     */
    @RequestMapping(value = "/API/empresas360/carga_mas_mensajes_chat", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray carga_mas_mensajes_chat(@RequestBody JSONObject json) {

        String orderBy = "";

        if (json.containsKey("movil")) {
            orderBy = " order by id desc ";
        }

        String query = "select *, (SELECT message FROM chat_empresarial sc WHERE sc.id = p.idResponse) as mensajeRespuesta from chat_empresarial p "
                + "where ( (id360 = '" + json.get("id360-1") + "' and to_id360 = '" + json.get("id360-2") + "') "
                + "or (id360 = '" + json.get("id360-2") + "' and to_id360 = '" + json.get("id360-1") + "') ) "
                + " and if(p.id360 = '" + json.get("id360-2") + "', activo_id360, activo_to_id360) = 1 "
                + orderBy + " "
                + "limit " + json.get("init") + "," + json.get("limit") + ";";
        
        if (json.containsKey("grupo")) {
            
            query = "select *, (SELECT message FROM chat_empresarial sc WHERE sc.id = p.idResponse) as mensajeRespuesta from chat_empresarial p "
                + "where idGroup = '" + json.get("id360-1") + "' "
                + " and if(p.id360 = '" + json.get("id360-2") + "', activo_id360, activo_to_id360) = 1 "
                + " and to_id360 = '" + json.get("id360-2") + "' "
                + orderBy + " "
                + "limit " + json.get("init") + "," + json.get("limit") + ";";
            
        }

        JSONArray ids = Query.execute(query);
        return ids;
    }

    /*
    SERVICIO PARA MARCAR MENSAJES COMO LEIDOS
     */
    @RequestMapping(value = "/API/empresas360/marcar_chats_leidos", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject marcar_chats_leidos(@RequestBody String string) throws ParseException {
        JSONObject respuesta = respuesta(false, "No se pudieron marcar los mensajes");

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(string);

        JSONArray chats = (JSONArray) json.get("chats");
        int cantidadChats = chats.size();
        String query = "UPDATE chat_empresarial SET leido = 1 WHERE id IN (";

        for (int x = 0; x < cantidadChats; x++) {
            query += chats.get(x) + ", ";
        }

        query = query.substring(0, query.length() - 2);
        query = query + ")";

        if (Query.update(query)) {
            respuesta = respuesta(true, "Mensajes marcados");
        } else {
            respuesta.put("query", query);
        }

        return respuesta;
    }

    /*
    SERVICIO PARA CARGAR UN ARRAY DE USUARIOS CON LOS QUE SE TIENE PREVIA CONVERSACION
     */
    @RequestMapping(value = "/API/empresas360/usuarios_con_chat", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray usuarios_con_chat(@RequestBody JSONObject json) {
        //String query = "select distinct replace(concat(id360,to_id360),'"+json.get("id360")+"','') as id360 from chat_empresarial where (id360 = '"+json.get("id360")+"' OR to_id360 = '"+json.get("id360")+"');";
        Query.update("SET sql_mode = '';");
        
        String queryI = "select      count(*) as cantidadMensajes, " +
                        "           replace(concat(id360,to_id360),'" + json.get("id360") + "','') as id360chat, " +
                        "           null as esGrupo, " +
                        "           0 as estatusEnGrupo, " +
                        "           ( " +
                        "               select count(*) " +
                        "               from usuarios_favoritos f " +
                        "               where f.id360 = '" + json.get("id360") + "' " +
                        "               and f.id360_favorito = if(p.id360 = '" + json.get("id360") + "', p.to_id360, p.id360) and f.estatus_favorito = 1 " +
                        "           ) as esFavorito, " +
                        "           ( " +
                        "               select 	count(*)  " +
                        "               from 	chat_empresarial  " +
                        "               where 	((id360 = '" + json.get("id360") + "' and to_id360 = id360chat)  " +
                        "               or 	(id360 = id360chat and to_id360 = '" + json.get("id360") + "')) " +
                        "               and leido = 0 and to_id360 = '" + json.get("id360") + "' " +
                        "               and idGroup is null" +
                        "               and id360 is not null" +
                        "           ) as cantidadMensajesNoLeidos, " +
                        "           ( " +
                        "		select 	group_concat( id ) " +
                        "               from 	chat_empresarial  " +
                        "               where 	((id360 = '" + json.get("id360") + "' and to_id360 = id360chat)  " +
                        "               or 	(id360 = id360chat and to_id360 = '" + json.get("id360") + "')) " +
                        "               and leido = 0 and to_id360 = '" + json.get("id360") + "' " +
                        "               and idGroup is null" +
                        "               and id360 is not null" +
                        "           ) as mensajesNoLeidos " +
                        "from  " +
                        "           chat_empresarial p " +
                        "where  " +
                        "           (id360 = '" + json.get("id360") + "' OR to_id360 = '" + json.get("id360") + "')  " +
                        "           and ( if(id360 = '" + json.get("id360") + "', activo_id360, activo_to_id360) = 1 ) " +
                        "           and idGroup is null " +
                        " " +
                        "group by   id360chat;";
        
        String queryG = "select     count(distinct agrupador_mensajes_grupos) as cantidadMensajes,  " +
                        "           p.idGroup as id360chat,  " +
                        "           p.idGroup as esGrupo, " +
                        "           (" +
                        "               select 	pg.estatus_en_grupo " +
                        "               from 	participantes_grupos_chat_empresarial pg " +
                        "               where 	pg.id_participantes = '" + json.get("id360") + "' and pg.id_grupo = p.idGroup " +
                        "           ) as estatusEnGrupo, " +
                        "           ( " +
                        "               select 	count(*)  " +
                        "               from 	usuarios_favoritos f " +
                        "               where 	f.id360 = '" + json.get("id360") + "'  " +
                        "               and 	f.id360_favorito = p.idGroup and f.estatus_favorito = 1 " +
                        "           ) as esFavorito, " +
                        "           ( " +
                        "               select 	count(*)  " +
                        "               from 	chat_empresarial  " +
                        "               where 	((id360 is null or id360 <> '" + json.get("id360") + "') and to_id360 = '" + json.get("id360") + "')  " +
                        "               and 	leido = 0 " +
                        "               and	idGroup = id360chat " +
                        "               and     id360 is not null" +
                        "           ) as cantidadMensajesNoLeidos, " +
                        "           ( " +
                        "		select 	group_concat( id ) " +
                        "               from 	chat_empresarial  " +
                        "               where 	((id360 is null or id360 <> '" + json.get("id360") + "') and to_id360 = '" + json.get("id360") + "')  " +
                        "               and 	leido = 0 " +
                        "               and	idGroup = id360chat " +
                        "               and     id360 is not null" +
                        "           ) as mensajesNoLeidos " +
                        "from  " +
                        "           chat_empresarial p " +
                        "where  " +
                        "           ( if(id360 = '" + json.get("id360") + "', activo_id360, activo_to_id360) = 1 ) " +
                        "           and idGroup in  ( " +
                        "                               select 	gc.id_grupo  " +
                        "                               from 	participantes_grupos_chat_empresarial gc  " +
                        "                               where 	gc.id_participantes = '" + json.get("id360") + "' " +
                        "                           ) " +
                        "group by 	id360chat;";
        
        JSONArray usuariosIndividuales  = Query.execute(queryI);
        JSONArray usuariosGrupos        = Query.execute(queryG);
        usuariosIndividuales.addAll(usuariosGrupos);
        
        return usuariosIndividuales;
    }

    /*
    SERVICIO PARA CARGAR INFORMACION DE UN GRUPO DE GRUPOS
     */
    @RequestMapping(value = "/API/empresas360/informacion_grupos", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray informacion_grupos(@RequestBody String string) throws IOException, ParseException, java.text.ParseException {

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(string);

        /*
        AGREGAR PARTICIPANTES
         */
        JSONArray grupos = (JSONArray) json.get("grupos");
        String query = "SELECT 	"
                + "		g.*, "
                + "        ( "
                + "			SELECT "
                + "				group_concat( concat(p.id_participantes, \"-\", p.rol) ) "
                + "			FROM participantes_grupos_chat_empresarial p "
                + "			WHERE p.id_grupo = g.id_grupo and p.estatus_en_grupo = 1 "
                + "        ) as participantes, "
                + "        (" 
                + "             select 	pg.estatus_en_grupo " 
                + "             from 	participantes_grupos_chat_empresarial pg " 
                + "             where 	pg.id_participantes = '" + json.get("idUser") + "' and pg.id_grupo = g.id_grupo " 
                + "        ) as estatusEnGrupo " 
                + "FROM 	grupos_chat_empresarial g WHERE id_grupo IN ( ";
        int cantidadGrupos = grupos.size();

        for (int x = 0; x < cantidadGrupos; x++) {
            query += grupos.get(x) + " , ";
        }

        query = query.substring(0, query.length() - 2);
        query += " );";

        JSONArray mensajes = Query.execute(query);

        return mensajes;
    }
    
    @RequestMapping(value = "/API/empresas360/emojis", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject emojis(String string) {

        return consultaEmojis();

    }
    
    public JSONObject consultaEmojis(){
        String query = "SELECT * FROM emojis";
        
        JSONArray emojisDB = Query.execute(query);
        int cantidadEmojis = emojisDB.size();
        JSONObject emojis = new JSONObject();
        emojis.put("cantidad",emojisDB.size());
        emojis.put("query",query);
        emojis.put("emojis",emojisDB);
        
        for(int x = 0; x<cantidadEmojis; x++){
            JSONObject emoji = (JSONObject) emojisDB.get(x);
            emojis.put(emoji.get("codigo"), emoji);
        }

        return emojis;
    }

}
