/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Config.config;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.util.JSONPObject;
import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author web
 */
public class ReporteElemento {

    public static JSONObject agregarReporte(JSONObject json) {
        JSONObject response = new JSONObject();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        
        String query = "INSERT INTO `ReporteElemento` (`id`,`idUsuario_Movil`, `que`, `quien`, `cuando`, `donde`, `latusuario`, `lngusuario`, `latincidente`, `lngincidente`, `informacionadicional`,`estado`,`fecha_recepcion`,`hora_recepcion`) "
                + "VALUES ('" + json.get("id") + "','" + json.get("idUsuario") + "', '" 
                + json.get("que") + "', '" + json.get("quien") + "', '" 
                + json.get("cuando") + "', '" + json.get("donde") + "', '" 
                + json.get("latusuario") + "', '" + json.get("lngusuario") + "', '" 
                + json.get("latincidente") + "', '" + json.get("lngincidente") + "', '" 
                + json.get("informacionadicional") + "','enviado','"+dateFormat.format(date)+"','"+hourFormat.format(date)+"');";
        if (Query.update(query)) {
            response.put("estado", "enviado");
            response.put("folio", json.get("id"));
        } else {
            response.put("estado", "fallo");
            response.put("folio", null);
        }
        return response;
    }

    public static JSONArray traeReportes() {
        //String query="SELECT * from ReporteElemento where estado='enviado';";
        String query = "SELECT R.*, U.nombre,U.apellido_paterno,U.apellido_materno FROM usuarios_movil U, ReporteElemento R WHERE R.idUsuario_Movil = U.idUsuarios_Movil AND estado = 'enviado';";
        return Query.execute(query);
    }

    public static JSONArray traeReporteID(JSONObject json) {
        String query = "SELECT * from ReporteElemento where id='" + json.get("folio") + "';";
        return Query.execute(query);
    }

    public static JSONArray buscaSimilares(JSONObject jsonObj) {

        int h = Integer.parseInt((jsonObj.get("hora").toString()).substring(0, 2));
        int hmenos = h - 1;
        int hmas = h + 1;
        if (hmenos < 0) {
            hmenos = 23;
        }
        if (hmas == 24) {
            hmas = 0;
        }

        String horamenos = hmenos + jsonObj.get("hora").toString().substring(2, jsonObj.get("hora").toString().length());
        String horamas = hmas + jsonObj.get("hora").toString().substring(2, jsonObj.get("hora").toString().length());

//        System.out.println("Hora menos");
//        System.out.println(horamenos);
//        System.out.println("Hora mas");
//        System.out.println(horamas);
        String cuando1 = jsonObj.get("fecha") + " " + horamenos;
        String cuando2 = jsonObj.get("fecha") + " " + horamas;
//        System.out.println("cuando1");
//        System.out.println(cuando1);
//        System.out.println("cuando2");
//        System.out.println(cuando2);

        String query = "SELECT * ,( 6371 * acos(cos(radians(" + jsonObj.get("lat") + "))* cos(radians(latincidente))* cos(radians(lngincidente) - radians(" + jsonObj.get("lng") + "))+ sin(radians(" + jsonObj.get("lat") + ")) * sin(radians(latincidente)))) AS distancia FROM ReporteElemento where estado = 'revisado' and cuando >= '" + cuando1 + "'and cuando <= '" + cuando2 + "' and id != '" + jsonObj.get("id") + "'having distancia < 0.5;";

        System.out.println(query);

        return Query.execute(query);
    }

    public static JSONObject actualizaReporteElemento(JSONObject json) throws ParseException {
        JSONObject jsonObj = new JSONObject();
        JSONParser parser = new JSONParser();
        System.out.println(json.get("reporte"));
        JSONObject reporte = (JSONObject) parser.parse(json.get("reporte").toString());
        String query = "UPDATE ReporteElemento SET estado='revisado',"
                + "razonamiento='"+reporte.get("razonamiento")+"',"
                + "idUsuario_Sys = '"+reporte.get("idUsuario_Sys")+"',"
                + "fecha_revision='"+reporte.get("fecha_revision")+"',"
                + "hora_revision='"+reporte.get("hora_revision")+"' where id='" + json.get("id") + "'";
        boolean actualizado = Query.update(query);
        if (actualizado) {
            jsonObj.put("status", 200);
            return jsonObj;
        }
        return null;
    }
}
