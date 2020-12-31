/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import Config.config;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author moises
 */
public class InicializacionLocal {

    public static JSONObject getLocalInit() {
        JSONObject json = new JSONObject();

        JSONArray inferiores = new JSONArray();
        JSONArray superiores = new JSONArray();
        JSONArray semejantes = new JSONArray();
       
        
        JSONObject proyecto = new JSONObject();
        proyecto.put("alias_proyecto", "Empresas360");
        proyecto.put("path", "https://empresas.claro360.com/");
        proyecto.put("idSuperior", null);
        proyecto.put("icon", null);
        proyecto.put("proyecto", "plataforma360");
        proyecto.put("urlTop", null);
        proyecto.put("idServicio", null);
        proyecto.put("url", "https://empresas.claro360.com/plataforma360");

        JSONObject personalizacion = new JSONObject();
        personalizacion.put("logo3pdf", null);
        personalizacion.put("lottie", "https://empresas.claro360.com/p360_v4/json/lottie360.json");
        personalizacion.put("logo2", null);
        personalizacion.put("logo1", null);
        personalizacion.put("logo1pdf", "https://empresas.claro360.com/p360_v4/Img/Logos/911.png");
        personalizacion.put("proyecto", "plataforma360_dev");
        personalizacion.put("iconMap", null);
        personalizacion.put("t2pdf", "Empresas360");
        personalizacion.put("alias_proyecto", "Empresas360");
        personalizacion.put("dep_base", false);
        personalizacion.put("lg2", "7");
        personalizacion.put("lg1", "12");
        personalizacion.put("logo_principal", "https://empresas.claro360.com/p360_v4/Img/Logos/Claro%20360.png");
        personalizacion.put("lg3", null);
        personalizacion.put("logo2pdf", "https://empresas.claro360.com/p360_v4/Img/Logos/Claro%20360.png");
        personalizacion.put("logo_footer", "https://empresas.claro360.com/p360_v4/Img/Logos/Claro%20360.png");
        personalizacion.put("favicon", "https://empresas.claro360.com/p360_v4/Img/favicon360.png");
        personalizacion.put("pv1", "15");
        personalizacion.put("ah2", "37");
        personalizacion.put("ah1", "18");
        personalizacion.put("pv3", null);
        personalizacion.put("pv2", "17");
        personalizacion.put("ah3", null);
        personalizacion.put("t1pdf", "Plataforma Emergencia");
        personalizacion.put("ph1", "12");
        personalizacion.put("catalogo", "https://empresas.claro360.com/p360_v4/json/incidentes.json");
        personalizacion.put("ph3", null);
        personalizacion.put("socket", "wss://empresas.claro360.com/plataforma360_dev/SocketNotifications");
        personalizacion.put("ph2", "165");
        personalizacion.put("recursos", "https://empresas.claro360.com/p360_v4");
        personalizacion.put("t1", "Empresas");
        personalizacion.put("logo_modal", "https://empresas.claro360.com/p360_v4/Img/Logos/Claro%20360.png");
        personalizacion.put("t2", "Empresas360");
        personalizacion.put("tfooter", "© 360 HQ S.A de C.V 2020. Todos los derechos reservados");
        personalizacion.put("t3", "");

        JSONObject server = new JSONObject();
        server.put("bd", "empresas");
        server.put("dep_base", false);
        server.put("lista_blanca", false);
        server.put("recursos", "https://empresas.claro360.com/p360_v4_dev");
        server.put("validar_ip", false);

        json.put("inferiores", inferiores);
        json.put("superiores", superiores);
        json.put("semejantes", semejantes);
        json.put("proyecto", proyecto);
        json.put("personalizacion", personalizacion);
        json.put("server", server);

        return json;
    }
    public static boolean AutoInit(){
        JSONObject json = InicializacionLocal.getLocalInit();
         config.setInit(json);
         return config.initialize();
    }
}
