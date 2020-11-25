/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author moises
 */
public class paises {
      private JSONObject json;

      public static JSONObject getJson() throws ParseException {
            String jsonCV = "{\n" +
"  \"alto\": [\n" +
"    \"China\",\n" +
"    \"Corea del Norte\",\n" +
"    \"Corea del Sur\",\n" +
"    \"Italia\",\n" +
"    \"España\",\n" +
"    \"Francia\",\n" +
"    \"Alemania\",\n" +
"    \"Reino Unido\",\n" +
//"    \"Holanda\",\n" +
"    \"Países Bajos\",\n" +
"    \"Bélgica\",\n" +
"    \"Noruega\",\n" +
"    \"Austria\",\n" +
"    \"Suiza\",\n" +
//"    \"Irán\",\n" +
"    \"Iran\",\n" +
"    \"Estados Unidos\",\n" +
"    \"Suecia\"\n" +
"  ],\n" +
"  \"medio\": [\n" +
"    \"Dinamarca\",\n" +
"    \"Japón\",\n" +
"    \"Malasia\",\n" +
"    \"Portugal\",\n" +
"    \"República Checa\",\n" +
"    \"Canadá\",\n" +
//"    \"Qatar\",\n" +
"    \"Catar\",\n" +
"    \"Australia\",\n" +
"    \"Grecia\",\n" +
"    \"Finlandia\",\n" +
"    \"Israel\",\n" +
"    \"Eslovenia\",\n" +
"    \"Singapur\",\n" +
"    \"Brasil\",\n" +
"    \"Bahamas\",\n" +
"    \"Estonia\",\n" +
"    \"Polonia\",\n" +
"    \"Islandia\",\n" +
"    \"Tailandia\",\n" +
"    \"Indonesia\",\n" +
"    \"Egipto\",\n" +
"    \"Chile\",\n" +
//"    \"Iraq\",\n" +
"    \"Irak\",\n" +
"    \"Luxemburgo\",\n" +
"    \"India\",\n" +
"    \"Arabia Saudita Kuwait\",\n" +
"    \"Arabia Saudí\",\n" +
"    \"Líbano\",\n" +
"    \"San Marino\"\n" +
"  ]\n" +
"}";
            JSONParser parser = new JSONParser();
            
            return (JSONObject) parser.parse(jsonCV);
      }
      
}
