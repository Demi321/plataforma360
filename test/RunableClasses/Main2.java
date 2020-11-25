/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Config.config;
import Modelo.Query;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.util.JSONPObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author moises
 */
public class Main2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println(new JSONObject());
//       System.out.println(Encriptacion.Encriptar.Reverse("tOE+tPBaRfR1GzdFzdpOS7GmrSpe8NcP"));
//System.out.println(Encriptacion.Encriptar.Reverse("IJ8yl+ZhgwKxpq0qXvDXDw=="));
//        try {
//            int a = Integer.parseInt(null);
//            System.out.println(a);
//        } catch (Exception e) {
//            System.out.println(e);
//            System.out.println("a en un nombre");
//        }
//System.out.println(Encriptacion.Encriptar.Reverse("eZ1W73qgTP+xpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("E5wyeyH5G5mxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("a/72rztqviyxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("3ZwEwRsiSG2xpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("e7q8QU1ZSASxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("wSHtXB0KNn+xpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("+N9f6pg/C0Gxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("AYW/5xAbmDixpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("96UcD4h8/J+xpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("sbnVr1vWDH2xpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("T1ReT7jbcUWxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("MelewzbKVNKxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("jKHffaZNM9Cxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("8/Ybivgd2tixpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("rjRGqeHeTlOxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("qWyl9m5WcDaxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("0Xp+gyHXWBKxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("hAIaUIjl0POxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("llrfdPXqVOyxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("AslF1YpGkQqxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("oRQzv7YzRESxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("asG/72RoarKxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("DAA9GvX1CGixpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("PDn3kcT7m2Oxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("K/K+OBh9pIKxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("2nbBngOtjrexpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("98PtyxQreLKxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("YRib0swEMNaxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("wTnfXSqS+5yxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("mi5hyysQPYKxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("txw3e7HFXJGxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("eZXdeoKLZv+xpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("2Nzf/AUmpRCxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("qKUrQyQAW5expq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("Z7QepnAg5F2xpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("haLk3EYoAHCxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("QC4WnuNtR+Cxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("qMJKleDpnbyxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("xr7xVIxHKcKxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("wqC9wfv+s/+xpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("dqzfb0wipKqxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("iRo+qOcig7h7xcAbGrQOsQ=="));
//System.out.println(Encriptacion.Encriptar.Reverse("1LrXu8lftk4="));
//System.out.println(Encriptacion.Encriptar.Reverse("WklMvY2iyRixpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("2JDtLj6RIO+xpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("mwOzllnQEd/Ne3L7wTeiXg=="));
//System.out.println(Encriptacion.Encriptar.Reverse("kcgfTbMnBHaxpq0qXvDXDw=="));
//System.out.println(Encriptacion.Encriptar.Reverse("e+jmslc3y6A="));
//System.out.println(Encriptacion.Encriptar.Reverse("EwRshwcfEco="));
//System.out.println(Encriptacion.Encriptar.Reverse("pXqgJlpQtJmxpq0qXvDXDw=="));
        
        
//        System.out.println(-1>0?"Positivo":(-3>-2?"mas negativo":"menos negativo"));
//        System.out.println(Modelo.Escalamiento.getFecha(2));
//        System.out.println(Modelo.Escalamiento.getFecha("2020-01-01",2));
        /*JSONParser parser = new JSONParser();
        JSONArray personal = (JSONArray) parser.parse("[5579461339,5579461340,5579461341,5579461342]");
        String query = "SELECT FireBaseKey FROM usuarios_movil WHERE ";
        for (int i = 0; i < personal.size(); i++) {
            query += "idUsuarios_Movil = '"+personal.get(i)+"' || ";            
        }
        query = query.substring(0,(query.length()-4));
        query += ";";
        System.out.println(query);
        JSONArray firebasenot = Query.execute(query);
        System.out.println(firebasenot);*/
        
        
        
//          DateTimeFormatter fmt = DateTimeFormatter.ofPattern("M/dd/yyyy");
//                  LocalDate fechaNac = LocalDate.parse("03/23/2013", fmt);
//                  System.out.println(fechaNac);
//        JSONArray array2 = new JSONArray();
//        String calificaciones="10,8,9,7,6,4,8,7,9";
//        String[] array = calificaciones.split(",");
//        int[] arreglo = new int[30];
//        System.out.println(calificaciones);
//        System.out.println(Arrays.toString(array));
//        
//        for (int i = 0; i < array.length; i++) {
//            
//          array2.add(Integer.parseInt(array[i]));
//            
//        }
//        System.out.println(array2);
//        
//        double promedio=0;
//        for (int i = 0; i < array2.size(); i++) {
//            promedio+= Integer.parseInt(array2.get(i).toString());
//        }
//        System.out.println(promedio);
//       System.out.println(array2.size());
//       promedio=promedio/array2.size();
//        System.out.println(promedio);
        
        // TODO code application logic here
    }
    
}
