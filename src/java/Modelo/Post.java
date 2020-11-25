/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Config.config;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Moises Juarez
 */
public class Post {

    //public static final String path="https://sosdemo.ml/";
    //public static final String path="https://demoglobal.ml/";
    //public static final String path="http://172.19.0.86:8080/";
    //public static final String path = "https://plataformaemergencias.ml/";
    public static final String path = config.getPATH();
    public static final String URL_Incidentes = config.getURL_Incidentes();

    public static void InsertarLlamada(UsuarioMovilVO usr, CredencialesVO c, RegistroLlamadasVO reg) {
        String query_url = "http://172.19.0.86:8080/Dependencia/SOS";
        String json = "{\n"
                + "            \"idRegistro_Llamadas\": " + reg.getIdRegistro_Llamadas() + ",\n"
                + "            \"idUsuarios_Movil\": " + usr.getIdUsuarios_Movil() + ",\n"
                + "            \"apikey\": " + c.getApikey() + ",\n"
                + "            \"idsesion\": " + c.getIdsesion() + ",\n"
                + "            \"token\": " + c.getToken() + ",\n"
                + "            \"fecha\": " + reg.getFecha() + ",\n"
                + "            \"hora\": " + reg.getHora() + ",\n"
                + "            \"idModo_Llamada\": " + reg.getIdModo_Llamada() + ",\n"
                + "            \"idTipo_Emergencia\": '0',\n"
                + "            \"idUsuario_Sys\": '0',\n"
                + "            \"idPrioridad_Llamada\": '0',\n"
                + "            \"idStream\": " + usr.getIdStream() + ",\n"
                + "            \"Img\": " + usr.getImg() + ",\n"
                + "            \"nombre\": " + usr.getNombre() + ",\n"
                + "            \"apellido_paterno\": " + usr.getApellido_paterno() + ",\n"
                + "            \"apellido_materno\": " + usr.getApellido_materno() + ",\n"
                + "            \"fecha_nacimiento\": " + usr.getFecha_nacimiento() + ",\n"
                + "            \"correo\": " + usr.getCorreo() + ",\n"
                + "            \"telefono\": " + usr.getTelefono() + ",\n"
                + "            \"genero\": " + usr.getGenero() + ",\n"
                + "            \"rh\": " + usr.getRh() + ",\n"
                + "            \"alergias\": " + usr.getAlergias() + ",\n"
                + "            \"condicion_medica\": " + usr.getCondicion_medica() + ",\n"
                + "            \"direccion\": " + usr.getDireccion() + ",\n"
                + "            \"cp\": " + usr.getCp() + ",\n"
                + "            \"contacto_nombre\": " + usr.getContacto_nombre() + ",\n"
                + "            \"contacto_telefono\": " + usr.getContacto_telefono() + "\n"
                + "        }";
        try {
            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            //conn.setRequestProperty("Authorization", Auth);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            System.out.println(result);
//            System.out.println("result after Reading JSON Response");
//            org.json.JSONObject myResponse = new org.json.JSONObject(result);
//            System.out.println("jsonrpc- " + myResponse.getString("jsonrpc"));
//            System.out.println("id- " + myResponse.getInt("id"));
//            System.out.println("result- " + myResponse.getString("result"));
            in.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String ConsultarPerfil(String idUsr, String path) {
        String r = null;
        //String query_url = "http://172.19.0.86:8080/Dependencia/SOS";
        String query_url = path;
        //String query_url = "https://sosdemo.ml/" + origen + "/Consulta";
        //String query_url = "http://172.19.0.86:8080/" + origen + "/Consulta";
        String json = "{\n"
                + "            \"idUsuarios_Movil\": " + idUsr + "\n"
                + "        }";
        try {
            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            //conn.setRequestProperty("Authorization", Auth);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            r = result;

//            System.out.println("result after Reading JSON Response");
//            org.json.JSONObject myResponse = new org.json.JSONObject(result);
//            System.out.println("jsonrpc- " + myResponse.getString("jsonrpc"));
//            System.out.println("id- " + myResponse.getInt("id"));
//            System.out.println("result- " + myResponse.getString("result"));
            in.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }

    public static String GenerarTicket(String origen, String fecha, String hora) {
        String r = null;
        String query_url = config.getURL_CONTROLADOR() + "API/ticket";
        //String query_url = "https://sosdemo.ml/CONTROLADOR/API/ticket";
        //String query_url = "http://localhost:8080/CONTROLADOR/API/ticket";
        //String query_url = "https://demoglobal.ml/" + origen + "/Consulta";
        String json = "{\n"
                + "            \"origen\": \"" + origen + "\",\n"
                + "            \"fecha\": \"" + fecha + "\",\n"
                + "            \"hora\": \"" + hora + "\"\n"
                + "        }";
        System.out.println(json);
        try {
            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            //conn.setRequestProperty("Authorization", Auth);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            r = result;

//            System.out.println("result after Reading JSON Response");
//            org.json.JSONObject myResponse = new org.json.JSONObject(result);
//            System.out.println("jsonrpc- " + myResponse.getString("jsonrpc"));
//            System.out.println("id- " + myResponse.getInt("id"));
//            System.out.println("result- " + myResponse.getString("result"));
            in.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }

    /*public static String serieIncidentes(String json) throws ParseException {
        System.out.println("buscando serie de Incidentes");
        String r = null;
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        String query_url = URL_Incidentes + "/serie";
        String Auth = "Bearer " + config.getTOKEN_INCIDENTES();
        try {
            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Authorization", Auth);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(jsonObj.toString().getBytes("UTF-8"));
            os.close();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            r = result;
            in.close();
            conn.disconnect();

        } catch (Exception e) {
            System.out.println(e);
        }

        return r;
    }*/
    //**************************
    
    public static String serieIncidentes(String json) throws IOException, ParseException{
        JSONParser parser = new JSONParser();
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(URL_Incidentes+ "/serie")
                .post(body)
                .addHeader("Authorization", "Bearer "+config.getTOKEN_INCIDENTES())
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        if(response.toString().contains("code=200")){
          return response.body().string();
        }
        else{
            return null;
        }
        
        
    }
    
    //*****************************

    public static String update(String json) throws ParseException, IOException {
        System.out.println("actualizando reporte de Incidentes");
        JSONParser parser = new JSONParser();
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(URL_Incidentes+ "/update")
                .post(body)
                .addHeader("Authorization", "Bearer "+config.getTOKEN_INCIDENTES())
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        //if(response.toString().contains("code=200")){
          return response.body().string();
//        }else{
//            return null;
//        }
    }

    public static String reportedependencias(String json) throws ParseException {
        System.out.println("reporte dependencias");
        String r = null;
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        String query_url = URL_Incidentes + "/reportedependencias";
        String Auth = "Bearer " + config.getTOKEN_INCIDENTES();
        try {
            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Authorization", Auth);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(jsonObj.toString().getBytes("UTF-8"));
            os.close();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            r = result;
            in.close();
            conn.disconnect();

        } catch (Exception e) {
            System.out.println(e);
        }

        return r;
    }

    public static String GenerarTicket(JSONObject json) {
        String r = null;
        String query_url = config.getURL_CONTROLADOR() + "API/ticket";
        System.out.println(json);
        try {
            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            //conn.setRequestProperty("Authorization", Auth);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(json.toString().getBytes("UTF-8"));
            os.close();
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            r = result;
            in.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }

}
