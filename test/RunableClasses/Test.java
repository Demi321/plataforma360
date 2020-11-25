/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunableClasses;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author moises
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException {
        JSONObject j = null;
        JSONObject u = new JSONObject();
        if (j != null) {
            u.putAll(j);
        }
        
        u.put("online_web", true);
        System.out.println(u);
        
    }

}
