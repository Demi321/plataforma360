/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

/**
 *
 * @author Moises Juarez
 */
public class FirebasePOST {


    public static void sendFirebaseDeleteUsr(String Auth, String Key) {
        System.out.println("In");
        String query_url = "https://fcm.googleapis.com/fcm/send";
        String json = "{\"to\": \"" + Key + "\",\n"
                + "\n"
                + "                    \"data\": {\n"
                + "                        \"title\": \"Instruccion\",\n"
                + "                        \"text\": \"Centro de mando solicito video\",\n"
                + "                        \"type\": \"6\",\n"
                + "                        \"sound\": \"default\",\n"
                + "                    },\n"
                + "                    \"android\": {\n"
                + "                        \"priority\": \"high\",\n"
                + "                        \"sound\": \"default\"\n"
                + "                    }}";
        try {
            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Authorization", Auth);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
           //System.out.println(result);
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
}
