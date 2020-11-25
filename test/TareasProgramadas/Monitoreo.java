/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TareasProgramadas;

import Config.config;
import Modelo.Query;
import Request.request;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author moises
 */
public class Monitoreo {

    public static int index = 0;

    public static void initmonitorig() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                JSONParser parser = new JSONParser();
                JSONArray idArchivo = new JSONArray();
                System.out.println("Revisando cuentas archivadas de opentok");
                String query = "SELECT idRegistro_Llamadas,apikey,sesion as idSesion FROM registro_llamadas WHERE estatus = 0;";
                System.out.println(query);
                JSONArray array = Query.execute(query);
                for (int i = 0; i < array.size(); i++) {
                    JSONObject registro = (JSONObject) array.get(i);
                    System.out.println(registro);
                    //registro.put("idSesion", registro.get("sesion"));
                    String respuesta = null;
                    try {
                        respuesta = request.POST(config.getURL_CONTROLADOR() + "API/opentok/listArchives", registro.toString());
                    } catch (IOException ex) {
                        Logger.getLogger(Monitoreo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(respuesta);
                    if (respuesta != null) {
                        try {
                            JSONObject lista = (JSONObject) parser.parse(respuesta);
                            JSONArray items = (JSONArray) lista.get("items");
                            System.out.println(items);
                            boolean procede = true;
                            for (int j = 0; j < items.size(); j++) {
                                JSONObject archivo = (JSONObject) items.get(j);
                                System.out.println(archivo);
                                if (!archivo.get("status").toString().equals("uploaded")) {
                                    procede = false;
                                }
                            }

                            if (procede) {
                                for (int j = 0; j < items.size(); j++) {
                                    JSONObject archivo = (JSONObject) items.get(j);
                                    
                                    idArchivo.add(archivo.get("id"));
                                }
                                System.out.println(registro);
                                query = "UPDATE registro_llamadas SET idArchivo = '"+idArchivo.toString()+"', estatus = 1 WHERE idRegistro_Llamadas = '"+registro.get("idRegistro_Llamadas").toString()+"';";
                                System.out.println(query);
                                Query.update(query);
                            }
                            
                        } catch (ParseException ex) {
                            //Logger.getLogger(Monitoreo.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println(ex);
                        }
                    }
                }
            }
        }, 0, (1000*60));
    }

}
