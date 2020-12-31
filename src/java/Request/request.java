/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Request;

import Config.config;
import Modelo.Query;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author moises
 */
public class request {

    public static String POST_GPS(JSONObject json) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("usuario_id", json.get("idUsuarios_Movil").toString())
                .addFormDataPart("lat", json.get("latitud").toString())
                .addFormDataPart("lng", json.get("longitud").toString())
                .addFormDataPart("fecha", json.get("fecha").toString())
                .addFormDataPart("hora", json.get("hora").toString())
                .build();
        Request request = new Request.Builder()
                .url("https://gps360.ml/api/gps")
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        if (response.toString().contains("code=200")) {
            return response.body().string();
        } else {
            System.out.println(response.body().string());
            return null;
        }
        //return response.body().string();
    }

    public static JSONArray POST(String url, JSONArray json) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        if (response.toString().contains("code=200")) {
            return (JSONArray) parser.parse(response.body().string());
        } else {
            return null;
        }

    }

    public static JSONObject POST(String url, JSONObject json) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        if (response.toString().contains("code=200")) {
            return (JSONObject) parser.parse(response.body().string());
        } else {
            System.out.println(response);
            return null;
        }

    }

    public static String POST(String url, String json) throws IOException {
        System.out.println(url);
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        if (response.toString().contains("code=200")) {
            return response.body().string();
        } else {
            System.out.println(response.body().string());
            return null;
        }

    }

    public static String GET(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static JSONObject pushNot(String firebase, String tittle, String message) throws IOException, ParseException {

        JSONObject bodyJson = new JSONObject();

        bodyJson.put("to", firebase);
        bodyJson.put("content_available", true);
        JSONObject notification = new JSONObject();
        notification.put("title", tittle);
        notification.put("body", message);
        bodyJson.put("notification", notification);
        JSONObject android = new JSONObject();
        android.put("priority", "high");
        android.put("sound", "default");
        bodyJson.put("android", android);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, bodyJson.toString());
        Request request = new Request.Builder()
                .url("https://fcm.googleapis.com/fcm/send")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "key=AIzaSyDDLI8K3zbSHiH1DzrhnepBI76vLWmWiKI")
                .build();
        Response response = client.newCall(request).execute();
        if (response.toString().contains("code=200")) {
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(response.body().string());
        } else {
            return null;
        }

    }
    
    
    

}
