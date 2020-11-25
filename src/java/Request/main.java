/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Request;

import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author moises
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException {
        String arrayReporte = request.POST("https://sedena360.ml/SEDENA/TraeReporte55s", "");
        JSONObject reporte = new JSONObject();
        JSONParser parser = new JSONParser();
        // TODO code application logic here
        reporte.put("alias_proyecto", arrayReporte==null?"ERROR":(JSONArray) parser.parse(arrayReporte));
        System.out.println(reporte);
    }
    
}
