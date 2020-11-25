/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunableClasses;

import Modelo.Escalamiento;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author moises
 */
public class ThreadMoi {

      /**
       * @param args the command line arguments
       */
      public static void main(String[] args) {
            JSONArray array = new JSONArray();
            array.add("perfiles");
            array.add("firebases");
            array.add("ubicaciones");
            // TODO code application logic here
            for (int i = 0; i < 10; i++) {
                  Escalamiento data = new Escalamiento();
                  JSONObject json = new JSONObject();
                  json.put("numero", i);
                  json.put("tipo", array.get(i % 3));

                  //data.setJson(json);
                 //Thread t= new Thread(data);
                // t.setName("hilo"+i);
                // t.start();
            }
      }

}
