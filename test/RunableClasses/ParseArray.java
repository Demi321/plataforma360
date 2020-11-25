/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunableClasses;

import static Modelo.Escalamiento.getFecha;
import static Modelo.Escalamiento.getHora;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author moises
 */
public class ParseArray {

      public static JSONObject data = null;
      /**
       * @param args the command line arguments
       */
      public static void main(String[] args) throws ParseException {
              if (data == null) {
                  clearData();
            }
            // TODO code application logic here
            String s = "{\"horaS\":\"23:05:28\",\"data\":[{\"img\":\"https:\\/\\/viict.guardianacional360.ml\\/20btn\\/API\\/ConsultarImg\\/perfil\\/5526695594\",\"lng\":-106.39632896455986,\"hora\":\"17:05:27\",\"idUsuario\":\"5526695594\",\"icon\":\"https:\\/\\/viict.guardianacional360.ml\\/20btn\\/API\\/ConsultarImg\\/icono\\/5526695594\",\"soversion\":\"9\",\"firebase\":\"d69GLw_5u20:APA91bFabMCae9btBsOwmsOOff28g9asNfVwKesRXcbNOk_aLFy3-K8HzydRxDcNuuMUGtPm-CkD7SR8GHfx803DYsMEnsP5WLQn_7-yoKV7rSMXKgV0oYAks-3NQ2mjZ_XRTx2k-Lek\",\"version\":\"1\",\"nombre\":\"Moi\",\"fecha\":\"2020-03-02\",\"apellido_paterno\":\"J\",\"apellido_materno\":\"H\",\"url_plataforma\":\"https:\\/\\/viict.guardianacional360.ml\\/20btn\",\"gps_publico\":\"false\",\"so\":\"Android\",\"lat\":23.310788230424322},{\"fecha\":\"2020-03-02\",\"lng\":-106.39632760729091,\"hora\":\"17:06:34\",\"ActualizaGPS\":true,\"soversion\":\"9\",\"idUsuarios_Movil\":\"5526695594\",\"so\":\"Android\",\"version\":\"1\",\"lat\":23.31078843412362}],\"fechaS\":\"2020-03-02\"}";
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(s);
            System.out.println(json);
            
             JSONArray dataBack = (JSONArray)parser.parse(json.get("data").toString());

            for (int i = 0; i < dataBack.size(); i++) {
                  ((JSONArray) data.get("data")).add((JSONObject) dataBack.get(i));
                  System.out.println((JSONObject) dataBack.get(i));
            }
            System.out.println(data);
      }
      
      private static void clearData() {
            System.out.println("Limpiando data");
            data = new JSONObject();
            data.put("fechaS", getFecha());
            data.put("horaS", getHora());
            data.put("data", new JSONArray());
            System.out.println(data);
      }

      
}
