/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.BackupDirectorio;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author moises
 */
public class Escalamiento {

      private static JSONObject data = null;
      private static int count = 0;

      public static void main(String[] s) throws ParseException, InterruptedException, IOException, org.json.simple.parser.ParseException {
            JSONArray array = new JSONArray();
            array.add("perfiles");
            array.add("firebases");
            array.add("ubicaciones");
            for (int i = 0; i < 100; i++) {
                  JSONObject json = new JSONObject();
                  json.put("numero", i);
                  json.put("tipo", array.get(i % 3));
                  AgregarData(json);
                  Thread.sleep(100);
            }
            ////System.out.println(data);
            //System.out.println(count);
            //System.out.println("Finalizado");
            //System.out.print("perfiles: ");
            //System.out.println(((JSONArray) data.get("perfiles")).size());
            //System.out.print("ubicaciones: ");
            //System.out.println(((JSONArray) data.get("ubicaciones")).size());
            //System.out.print("firebases: ");
            //System.out.println(((JSONArray) data.get("firebases")).size());

      }

      private static void clearData() {
            //System.out.println("Limpiando data");
            data = new JSONObject();
            data.put("fechaS", getFecha());
            data.put("horaS", getHora());
            data.put("data", new JSONArray());
            //System.out.println(data);
      }

      public static void AgregarData(JSONObject json) throws ParseException, IOException, org.json.simple.parser.ParseException {
            //System.out.println("Agregando json a data en: " + Config.config.getDEPENDENCIA());
            //System.out.println(json);
            if (data == null) {
                  clearData();
            }
            String fecha = getFecha();
            String hora = getHora();

            Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fecha + " " + hora);
            Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data.get("fechaS").toString() + " " + data.get("horaS").toString());

            ((JSONArray) data.get("data")).add(json);
            //System.out.println(json);
            //System.out.println("Agregado");
            if (!CompararFechas(date1, date2, Config.config.getTime())) {
                  EnviarData();
            }
      }

      public static void EnviarData() throws IOException, org.json.simple.parser.ParseException {
            //System.out.println("Tiempo agotado..... Enviando Data");
            JSONObject BackupData = (JSONObject) data.clone();
            clearData();
            if (!((JSONArray) BackupData.get("data")).isEmpty()) {
                  if (Config.config.getUrlSuperior() != null) {
                        try {
                              String urlSup = Config.config.getUrlSuperior() + "/API/PaquetesBackup";
                              //System.out.println(urlSup);
                              //System.out.println(Request.request.POST(urlSup, BackupData));
                        } catch (Exception e) {
                              //System.out.println("No se pudo realizar el post");
                              //Regresar el Backup al data
                              regresarBackup(BackupData);
                        }
                  }else{
                        /////  Mandamos el empaquetado de GPS's al servidor de tratamiento
                        //Request.request.POST("https://interpolaciongps.ml/gps/API/gpsCovid", BackupData);
                        
                        clearData();
                  }
                  
            }

      }

      public static void regresarBackup(JSONObject json) {
            if (data == null) {
                  clearData();
            }
            //System.out.println("Error. Regresando Backup -----> data");
            JSONArray dataBack = (JSONArray) json.get("data");

            for (int i = dataBack.size() - 1; i >= 0; i--) {
                  ((JSONArray) data.get("perfiles")).add(0, (JSONObject) dataBack.get(i));
            }
      }

      public static void RecibirData(String jsonString) throws ParseException, IOException, org.json.simple.parser.ParseException {
            //System.out.println("Reciviendo Data");
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonString);
            //System.out.println(json);
            if (data == null) {
                  clearData();
            }

//            JSONArray dataBack = (JSONArray) json.get("data");
            String array = json.get("data").toString();
            //System.out.println(array);
            JSONArray dataBack = (JSONArray) parser.parse(array);

            for (int i = 0; i < dataBack.size(); i++) {
                  ((JSONArray) data.get("data")).add((JSONObject) dataBack.get(i));
            }

            String fecha = getFecha();
            String hora = getHora();

            Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fecha + " " + hora);
            Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data.get("fechaS").toString() + " " + data.get("horaS").toString());

            if (!CompararFechas(date1, date2, 1)) {
                  EnviarData();
            }

            for (int i = 0; i < dataBack.size(); i++) {
                  BackupDirectorio.UpdateBackupDirectorio((JSONObject) dataBack.get(i));
            }

      }

      private static boolean CompararFechas(Date date1, Date date2, long limit) {
            long diferencia = (Math.abs(date1.getTime() - date2.getTime())) / 1000;//limit es el limite para que este dentro del rango (EN SEGUNDOS)
            return diferencia <= (limit);//pasamos el limite de tiempo a segundos
      }

      public static String getFecha() {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            return dateFormat.format(date);

      }
      public static String getFecha(int dias) {
          //sumarle dias a el date 
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, dias);
            date=calendar.getTime();
            return dateFormat.format(date);

      }
      public static String getFecha(String fecha, int dias) throws ParseException {
          //sumarle dias a el date 
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, dias);
            date=calendar.getTime();
            return dateFormat.format(date);

      }
      

      public static String getHora() {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            return hourFormat.format(date);
      }

}
