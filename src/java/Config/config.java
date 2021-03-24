/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import Recursos.InicializacionLocal;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author moises
 */
public class config {

    private static JSONObject init = null;
    private static JSONObject proyecto = new JSONObject();
    private static JSONObject server = new JSONObject();
    private static JSONObject personalizacion = new JSONObject();
    private static JSONArray inferiores = new JSONArray();
    private static JSONArray semejantes = new JSONArray();
    private static JSONArray superiores = new JSONArray();

    private static String DEPENDENCIA = null;
    private static boolean DEPENDENCIA_BASE = false;
    private static boolean VALIDAR_IP = false;
    private static boolean LISTA_BLANCA = false;

    //private static String SECRETKEY = null;
//    private static String SECRETKEY = "plataforma36084159c9f-b6d1-4d89-8ff7-06aeb0430128";
    private static String SECRETKEY = "CONTROLADOR84159c9f-b6d1-4d89-8ff7-06aeb0430128";
    private static String TOKEN_INCIDENTES = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiYzU5YTE3MmNhZGUwMDQwMjgwYmFjN2UzYjAxNDNiYjNjMzBhN2FlNjNkMzgyYmQzZjZiNTAyZDI4ZTczNmI3OWE3ZDVmOWM3MzRkZDEwZjQiLCJpYXQiOjE1OTM0NDkzMzMsIm5iZiI6MTU5MzQ0OTMzMywiZXhwIjoxNjI0OTg1MzMzLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.pqzP8GJ4ov2-3oIqLGjOOZR70EFpYIcNcA464b-Nen88Qe_C8MJ_Zvm-ZSi8TixW1uaEEWmGMsOb7rrO6mPP1u5M9JnqawqR6l-MLBkWXbEJC8P6rMhKvUyLs5v5MLh5OhxbfwO8H0pgu8M2EL4tKRblvHEHsXU0JI0R0g7xn6TCl--kOZFwWtEJou7v2S37DgyO1JHtfR4JP7P5rHHyH5CqUO7pvlbGgIVME6IX7RgddZvQVl3KRwZgbdA6bSK-GyntcHnAoyIi9WqdDE24fnzi9zjephrNQKm9gse8rv5kPdvM9ISCFifTEGj3nsI2PsiBKPcHWFZFTzE8t6eCM7hcSzDqrDMATi2MdZ1j4QhDMk14NKMahHPPvsPYijpPApJKn5FxFVezVu-QWsY2qNLX7Zv6kTiWvFmijLbPpthHeZQzMSWA8m4WK69IH7jbIN61-sY575XQEFEJYZ4UepoXyiQWM9jHFb2tVcBSyE1PY9Qee9bUhjz8KWBaWGDg4dcvboTbN0TmJYq9ikNYjSP2Sy9vO0aMT9JF6he3nP3TQoijMP7g9nVTnDIzrp0c7LTeix54Vks9QXK5-pMcb1XUOtaZWqjVV3ZAf0tKw1nDIFQ7nvKoEPBwS7bGxm5rOfNouZdJsjwdVBo-Ftj9JMVoaTHOBUa2jTtBS_hJdXs";
//    private static String URL_Incidentes = "https://incidentes.ml/api/incidentes";
    private static String URL_Incidentes = "https://reportes.claro360.com/api/incidentes";

    private static String PATH = null;
    //private static String MYSQL_BD = null;
    private static String MYSQL_BD = "cursos";
    private static String MYSQL_login = "usrdesarrollo";
    //private static String MYSQL_login = "root";
    private static String MYSQL_password = "@D3sarroll0123";
    //private static String MYSQL_password = "prueba12345";
    private static String URL_CONTROLADOR = "https://plataforma911.ml/CONTROLADOR/";
    //private static String URL_CONTROLADOR = "http://localhost:8080/CONTROLADOR/";

    private static String idSuperior = null;
    private static String idServicio = null;
    private static String aliasServicio = null;
    private static String urlTop = null;
    
    private static  String urlSuperior=null;
    private static int time = 2;
    private static String SESION = "username_v3.2_plataforma360";
    public static boolean initialize() {
        System.out.println("initialize");
        
        try {
            if (init != null) {
                System.out.println(init);
                proyecto = (JSONObject) init.get("proyecto");
                server = (JSONObject) init.get("server");
                personalizacion = (JSONObject) init.get("personalizacion");
                inferiores = (JSONArray) init.get("inferiores");
                semejantes = (JSONArray) init.get("semejantes");
                superiores = (JSONArray) init.get("superiores");
                DEPENDENCIA = (String) proyecto.get("proyecto");
                DEPENDENCIA_BASE = (boolean) server.get("dep_base");
                VALIDAR_IP = (boolean) server.get("validar_ip");
                LISTA_BLANCA = (boolean) server.get("lista_blanca");
                SECRETKEY = DEPENDENCIA + "84159c9f-b6d1-4d89-8ff7-06aeb0430128";
                idSuperior = (String) proyecto.get("idSuperior");
                idServicio = (String) proyecto.get("idServicio");
                aliasServicio = (String) proyecto.get("alias_proyecto");
                urlTop = (String) proyecto.get("urlTop");
                PATH = (String) proyecto.get("path");
                MYSQL_BD = (String) server.get("bd");
                for(int i=0;i<superiores.size();i++){
                      JSONObject superior = (JSONObject) superiores.get(i);
                      if(superior.get("idServicio").toString().equals(idSuperior)){
                            urlSuperior=superior.get("url").toString();
                            break;
                      }
                }
                Controlador.ControladorPOST.setDependencia(DEPENDENCIA);
                Controlador.ControladorReferenciasHospitalarias.setDependencia(DEPENDENCIA);
                Controlador.Empresas360.setDependencia(DEPENDENCIA);
                
                return true;
            }else{
                System.out.println("init False");
            }
            
        } catch (Exception e) {
            return false;
        }
        return false;
    }

      public static int getTime() {
            return time;
      }

    
      public static String getUrlSuperior() {
            return urlSuperior;
      }

    
    public static JSONObject getInit() {
       if (config.getInit_value() == null) {
           JSONObject json = InicializacionLocal.getLocalInit();
           config.setInit(json);
           config.initialize();
       }
        return init;
    }

    public static String getSESION() {
        return SESION;
    }
    
    
    public static JSONObject getInit_value() {
        return init;
    }

    public static void setInit(JSONObject init) {
        config.init = init;
    }

    public static JSONObject getProyecto() {
        return proyecto;
    }

    public static void setProyecto(JSONObject proyecto) {
        config.proyecto = proyecto;
    }

    public static JSONObject getServer() {
        return server;
    }

    public static void setServer(JSONObject server) {
        config.server = server;
    }

    public static JSONObject getPersonalizacion() {
        return personalizacion;
    }

    public static void setPersonalizacion(JSONObject personalizacion) {
        config.personalizacion = personalizacion;
    }

    public static JSONArray getJsonInferiores() {
        return inferiores;
    }

    public static JSONArray getInferiores() {
        JSONArray array = new JSONArray();
        for (int i = 0; i < inferiores.size(); i++) {

            array.add(((JSONObject) inferiores.get(i)).get("url"));
        }
        return array;
    }

    public static void setInferiores(JSONArray inferiores) {
        config.inferiores = inferiores;
    }

    public static JSONArray getJsonSemejantes() {
        return semejantes;
    }

    public static JSONArray getSemejantes() {
        JSONArray array = new JSONArray();
        for (int i = 0; i < semejantes.size(); i++) {

            array.add(((JSONObject) semejantes.get(i)).get("url"));
        }
        return array;
    }

    public static void setSemejantes(JSONArray semejantes) {
        config.semejantes = semejantes;
    }

    public static JSONArray getSuperiores() {
        JSONArray array = new JSONArray();
        if(true){
            return array;
        }
        for (int i = 0; i < superiores.size(); i++) {

            array.add(((JSONObject) superiores.get(i)).get("url"));
        }
        return array;
        
    }

    public static JSONArray getJsonSuperiores() {
        return superiores;
    }

    public static void setSuperiores(JSONArray superiores) {
        config.superiores = superiores;
    }

    public static String getDEPENDENCIA() {
        return DEPENDENCIA;
    }

    public static void setDEPENDENCIA(String DEPENDENCIA) {
        config.DEPENDENCIA = DEPENDENCIA;
    }

    public static boolean isDEPENDENCIA_BASE() {
        return DEPENDENCIA_BASE;
    }

    public static void setDEPENDENCIA_BASE(boolean DEPENDENCIA_BASE) {
        config.DEPENDENCIA_BASE = DEPENDENCIA_BASE;
    }

    public static boolean isVALIDAR_IP() {
        return VALIDAR_IP;
    }

    public static void setVALIDAR_IP(boolean VALIDAR_IP) {
        config.VALIDAR_IP = VALIDAR_IP;
    }

    public static boolean isLISTA_BLANCA() {
        return LISTA_BLANCA;
    }

    public static void setLISTA_BLANCA(boolean LISTA_BLANCA) {
        config.LISTA_BLANCA = LISTA_BLANCA;
    }

    public static String getSECRETKEY() {
        return SECRETKEY;
    }

    public static void setSECRETKEY(String SECRETKEY) {
        config.SECRETKEY = SECRETKEY;
    }

    public static String getTOKEN_INCIDENTES() {
        return TOKEN_INCIDENTES;
    }

    public static void setTOKEN_INCIDENTES(String TOKEN_INCIDENTES) {
        config.TOKEN_INCIDENTES = TOKEN_INCIDENTES;
    }

    public static String getURL_Incidentes() {
        return URL_Incidentes;
    }

    public static void setURL_Incidentes(String URL_Incidentes) {
        config.URL_Incidentes = URL_Incidentes;
    }

    public static String getPATH() {
        return PATH;
    }

    public static void setPATH(String PATH) {
        config.PATH = PATH;
    }

    public static String getMYSQL_BD() {
        return MYSQL_BD;
    }

    public static void setMYSQL_BD(String MYSQL_BD) {
        config.MYSQL_BD = MYSQL_BD;
    }

    public static String getMYSQL_login() {
        return MYSQL_login;
    }

    public static void setMYSQL_login(String MYSQL_login) {
        config.MYSQL_login = MYSQL_login;
    }

    public static String getMYSQL_password() {
        return MYSQL_password;
    }

    public static void setMYSQL_password(String MYSQL_password) {
        config.MYSQL_password = MYSQL_password;
    }

    public static String getURL_CONTROLADOR() {
        return URL_CONTROLADOR;
    }

    public static void setURL_CONTROLADOR(String URL_CONTROLADOR) {
        config.URL_CONTROLADOR = URL_CONTROLADOR;
    }

    public static String getIdSuperior() {
        return idSuperior;
    }

    public static void setIdSuperior(String idSuperior) {
        config.idSuperior = idSuperior;
    }

    public static String getIdServicio() {
        return idServicio;
    }

    public static void setIdServicio(String idServicio) {
        config.idServicio = idServicio;
    }

    public static String getAliasServicio() {
        return aliasServicio;
    }

    public static void setAliasServicio(String aliasServicio) {
        config.aliasServicio = aliasServicio;
    }

    public static String getUrlTop() {
        return urlTop;
    }

    public static void setUrlTop(String urlTop) {
        config.urlTop = urlTop;
    }

}
