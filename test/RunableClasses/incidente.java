/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunableClasses;

import Modelo.Post;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author moises
 */
public class incidente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException {
          JSONObject json  = new JSONObject();
        JSONObject json2  = new JSONObject();
        json2.put("codigo", "10001");
        json2.put("prefijo_estado", "sin");
        json.put("incidente", json2);
        System.out.println(Post.serieIncidentes(json.toString()));
    }
    
}
