/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Query;
import java.io.IOException;
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

public class Controller1 {

    private JSONObject respuesta(boolean success, String message) {
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", success);
        respuesta.put("failure", !success);
        respuesta.put("mensaje", message);
        return respuesta;
    }

    /*Consultar listado de proyectos*/
    @RequestMapping(value = "/API/empresas360/consultar_proyectos", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray consultar_proyectos(@RequestBody JSONObject json) throws IOException, ParseException {

        String query = "SELECT " +
                        "    * " +
                        "FROM " +
                        "    proyectos_empresas " +
                        "WHERE " +
                        "    tipo_usuario = '"+json.get("tipo_usuario")+"';";
        
        return Query.execute(query);

    }
    
    /*Consultar listado de proyectos*/
    @RequestMapping(value = "/API/empresas360/eliminar_todos_los_archivos", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject eliminar_todos_los_archivos(@RequestBody JSONObject json) throws IOException, ParseException {

        JSONObject respuesta = respuesta(false, "Error al eliminar los archivos");
        
        String query = "UPDATE archivos_empresas SET activo_id360 = if(id360 = '" + json.get("id360") + "', 0, activo_id360), activo_to_id360 = if(to_id360 = '" + json.get("id360") + "', 0, activo_to_id360) WHERE id360 = '"+json.get("id360")+"' or to_id360 = '"+json.get("id360")+"';";
        
        if( Query.update(query) ){
            respuesta = respuesta(true, "Archivos eliminados");
        }
        respuesta.put("query", query);
        
        return respuesta;

    }
    
    /*Consultar listado de proyectos*/
    @RequestMapping(value = "/API/empresas360/eliminar_archivos_seleccionados", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject eliminar_archivos_seleccionados(@RequestBody String string) throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(string);
        JSONObject respuesta = respuesta(false, "Error al eliminar los archivos");
        
        String query = "UPDATE archivos_empresas SET activo_id360 = if(id360 = '" + json.get("id360") + "', 0, activo_id360), activo_to_id360 = if(to_id360 = '" + json.get("id360") + "', 0, activo_to_id360) WHERE agrupador in ( ";

        JSONArray agrupadores = (JSONArray) json.get("agrupadores");
        int cantidadAgrupadores = agrupadores.size();
        
        for(int x = 0; x<cantidadAgrupadores; x++){
            query += "'" + agrupadores.get(x) + "',";
        }
        
        query = query.substring(0, query.length()-1) + ") ";
        
        if( Query.update(query) ){
            respuesta = respuesta(true, "Archivos eliminados");
        }
        respuesta.put("query", query);
        
        return respuesta;

    }
    
    /*Guardar archivo enviado*/
    @RequestMapping(value = "/API/empresas360/guardar_archivo_empresas", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject empresas360_guardar_archivo(@RequestBody String string) throws IOException, ParseException {
        
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(string);
        JSONObject respuesta = respuesta(false, "Error al guardar el archivo");
        
        String uniqueID = UUID.randomUUID().toString();
        
        String queryProyecto = "SELECT " +
                                "    * " +
                                "FROM " +
                                "    proyectos_empresas " +
                                "WHERE " +
                                "    nombre_proyecto = '"+json.get("proyecto").toString()+"' " +
                                "    AND tipo_usuario = '"+json.get("tipo_usuario").toString()+"';";
        JSONArray resultSelect = Query.execute(queryProyecto);
        int id_proyecto = 0;
        
        if( resultSelect.size() > 0 ){
            
            JSONObject data = (JSONObject) resultSelect.get(0);
            id_proyecto = Integer.parseInt( data.get("id_proyecto").toString() );
            
        }else{
            
            JSONObject dataInsert = new JSONObject();
            dataInsert.put("nombre_proyecto", json.get("proyecto").toString() );
            dataInsert.put("tipo_usuario", json.get("tipo_usuario").toString() );
            dataInsert.put("tipo_servicio", json.get("tipo_servicio").toString() );
            dataInsert.put("tipo_area", json.get("tipo_area").toString() );
            
            id_proyecto = Query.insert(  Query.createQueryInsert("proyectos_empresas", dataInsert) );
            
        }
        
        json.put("id_proyecto", id_proyecto);
        json.put("agrupador", uniqueID);
        json.remove("proyecto");
        
        JSONArray destinatarios = (JSONArray) json.get("destinatarios");
        JSONArray jsonParaInsert = new JSONArray();
        int cantidadDestinatarios = destinatarios.size();
        
        for( int x = 0; x<cantidadDestinatarios; x++ ){
            
            JSONObject aux = (JSONObject) json.clone();
            aux.put("to_id360", destinatarios.get(x));
            aux.remove("destinatarios");
            
            jsonParaInsert.add(x,aux);
            
        }
        
        int insert = Query.insert( Query.createQueryInsertWithColumnsMultiple("archivos_empresas", jsonParaInsert) );
        
        if( insert >= 0 ){
            
            respuesta = respuesta(true, "Archivos guardados");
            respuesta.putAll(json);
            json.put("archivo_recibido", true);
            
            for( int x = 0; x<cantidadDestinatarios; x++ ){
            
                SocketEndPoint.EnviarNotificacio_id360(json, destinatarios.get(x).toString());

            }
            
        }
        
        return respuesta;
        
    }
    
    /*Consultar archivo enviado*/
    @RequestMapping(value = "/API/empresas360/consultar_archivos_empresas", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray consultar_archivos_empresas(@RequestBody String string) throws IOException, ParseException {
        
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(string);
        
        String query = "SELECT " +
                        "    *, ar.date_created as fecha_envio, ar.time_created as hora_envio, " +
                        "   ( " +
                        "       SELECT " +
                        "           GROUP_CONCAT(a.to_id360) " +
                        "	FROM " +
                        "           archivos_empresas a " +
                        "	WHERE a.agrupador = ar.agrupador " +
                        "    ) as destinatarios " +
                        "FROM " +
                        "    archivos_empresas ar " +
                        "LEFT JOIN " +
                        "    proyectos_empresas pm ON pm.id_proyecto = ar.id_proyecto " +
                        "WHERE " +
                        "    (id360 = '"+json.get("id360")+"' OR to_id360 = '"+json.get("id360")+"') AND if(id360 = '"+json.get("id360")+"', activo_id360, activo_to_id360) = 1 " +
                        "GROUP BY agrupador";
        
        return Query.execute( query );
        
    }
    
    /*Consultar archivo enviado*/
    @RequestMapping(value = "/API/empresas360/consultar_archivos_empresas_enviados_por_mi", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray consultar_archivos_empresas_enviados_por_mi(@RequestBody String string) throws IOException, ParseException {
        
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(string);
        
        String query = "SELECT " +
                        "    *, ar.date_created as fecha_envio, ar.time_created as hora_envio, " +
                        "   ( " +
                        "       SELECT " +
                        "           GROUP_CONCAT(a.to_id360) " +
                        "	FROM " +
                        "           archivos_empresas a " +
                        "	WHERE a.agrupador = ar.agrupador " +
                        "    ) as destinatarios " +
                        "FROM " +
                        "    archivos_empresas ar " +
                        "LEFT JOIN " +
                        "    proyectos_empresas pm ON pm.id_proyecto = ar.id_proyecto " +
                        "WHERE " +
                        "    id360 = '"+json.get("id360")+"' AND if(id360 = '"+json.get("id360")+"', activo_id360, activo_to_id360) = 1 " +
                        "GROUP BY agrupador";
        
        return Query.execute( query );
        
    }
    
    /*Consultar archivo enviado*/
    @RequestMapping(value = "/API/empresas360/consultar_archivos_empresas_enviados_por_mi_a", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray consultar_archivos_empresas_enviados_por_mi_a(@RequestBody String string) throws IOException, ParseException {
        
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(string);
        
        String query = "SELECT " +
                        "    *, ar.date_created as fecha_envio, ar.time_created as hora_envio, " +
                        "   ( " +
                        "       SELECT " +
                        "           GROUP_CONCAT(a.to_id360) " +
                        "	FROM " +
                        "           archivos_empresas a " +
                        "	WHERE a.agrupador = ar.agrupador " +
                        "    ) as destinatarios " +
                        "FROM " +
                        "    archivos_empresas ar " +
                        "LEFT JOIN " +
                        "    proyectos_empresas pm ON pm.id_proyecto = ar.id_proyecto " +
                        "WHERE " +
                        "    (id360 = '"+json.get("id360")+"'  AND to_id360 = '"+json.get("to_id360")+"') AND if(id360 = '"+json.get("id360")+"', activo_id360, activo_to_id360) = 1 " +
                        "GROUP BY agrupador";
        
        return Query.execute( query );
        
    }
    
    /*Consultar archivo enviado*/
    @RequestMapping(value = "/API/empresas360/consultar_archivos_empresas_enviados_a_mi", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray consultar_archivos_empresas_enviados_a_mi(@RequestBody String string) throws IOException, ParseException {
        
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(string);
        
        String query = "SELECT " +
                        "    *, ar.date_created as fecha_envio, ar.time_created as hora_envio, " +
                        "   ( " +
                        "       SELECT " +
                        "           GROUP_CONCAT(a.to_id360) " +
                        "	FROM " +
                        "           archivos_empresas a " +
                        "	WHERE a.agrupador = ar.agrupador " +
                        "    ) as destinatarios " +
                        "FROM " +
                        "    archivos_empresas ar " +
                        "LEFT JOIN " +
                        "    proyectos_empresas pm ON pm.id_proyecto = ar.id_proyecto " +
                        "WHERE " +
                        "    to_id360 = '"+json.get("id360")+"' AND if(id360 = '"+json.get("id360")+"', activo_id360, activo_to_id360) = 1 " +
                        "GROUP BY agrupador";
        
        return Query.execute( query );
        
    }
    
    @RequestMapping(value = "/API/empresas360/consultar_archivos_empresas_enviados_a_mi_de", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray consultar_archivos_empresas_enviados_a_mi_de(@RequestBody String string) throws IOException, ParseException {
        
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(string);
        
        String query = "SELECT " +
                        "    *, ar.date_created as fecha_envio, ar.time_created as hora_envio, " +
                        "   ( " +
                        "       SELECT " +
                        "           GROUP_CONCAT(a.to_id360) " +
                        "	FROM " +
                        "           archivos_empresas a " +
                        "	WHERE a.agrupador = ar.agrupador " +
                        "    ) as destinatarios " +
                        "FROM " +
                        "    archivos_empresas ar " +
                        "LEFT JOIN " +
                        "    proyectos_empresas pm ON pm.id_proyecto = ar.id_proyecto " +
                        "WHERE " +
                        "    (to_id360 = '"+json.get("id360")+"' AND id360 = '"+json.get("to_id360")+"') AND if(id360 = '"+json.get("id360")+"', activo_id360, activo_to_id360) = 1 " +
                        "GROUP BY agrupador";
        
        return Query.execute( query );
        
    }

}
