/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Config.config;
import Request.request;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author web
 */
public class AdministracionServicios {
    public static void main(String [] args) throws ParseException, IOException{
        System.out.println(ServiciosCompletos());
    }
    

    
    public static JSONArray ServiciosCompletos() throws ParseException, IOException {
        JSONArray arrayServicios = new JSONArray();
        JSONArray array1 = config.getSemejantes();
        JSONArray array2 = config.getSuperiores();
        JSONArray array3 = config.getInferiores();
        for (int i = 0; i < array1.size(); i++) {
            arrayServicios.add(array1.get(i));
        }
        for (int i = 0; i < array2.size(); i++) {
            arrayServicios.add(array2.get(i));
        }
        for (int i = 0; i < array3.size(); i++) {
            arrayServicios.add(array3.get(i));
        }
        return arrayServicios;
    }
    
}
