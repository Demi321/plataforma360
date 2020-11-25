/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;
import org.json.simple.JSONObject;

public class Acceso {

    private static ArrayList<JSONObject> TokenList = new ArrayList<JSONObject>();
    private static Iterator<JSONObject> tokenIterator = TokenList.iterator();

    public static JSONObject generateToken() {
        String uuid = UUID.randomUUID().toString();
        JSONObject json = new JSONObject();
        json.put("token", uuid);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
        Date date = new Date();
        json.put("DateServer", dateFormat.format(date));
        TokenList.add(json);
        return json;
    }

    public static boolean checktoken(String token) throws java.text.ParseException {
        System.out.println("Voy a revisar el token");
        tokenIterator = TokenList.iterator();
        while (tokenIterator.hasNext()) {
            JSONObject json = tokenIterator.next();
            Date date1 = new SimpleDateFormat("yyyy-M-d HH:mm:ss").parse(json.get("DateServer").toString());
            Date date2 = new Date();
            long diferencia = (Math.abs(date1.getTime() - date2.getTime())) / 1000;
            long limit = (120 * 1000) / 1000L;//limite de tiempo

            if (diferencia > limit) {
                tokenIterator.remove();
            } else if (json.get("token").equals(token)) {
                tokenIterator.remove();
                return true;
            }
        }
        return false;

    }
}
