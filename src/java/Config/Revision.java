/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.ui.Model;

/**
 *
 * @author moises
 */
public class Revision {

    public static String autorizacion(HttpServletRequest sesion, Model model, String id) {
        //Inicializacion local automatica
//        InicializacionLocal.AutoInit();

        //Revision de inicializacion del proyecto 
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }

        //Revision de sesion 
        JSONObject misesion = null;
        if (id != null) {
            Cookie[] cookies = sesion.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    System.out.println(c.getName());
                    if (c.getName().contains("username_")) {
                        JSONParser parser = new JSONParser();
                        try {
                            String value = new String(Base64.decodeBase64(c.getValue()));
                            misesion = (JSONObject) parser.parse(value);
                            System.out.println(misesion);
                        } catch (ParseException ex) {
                            System.out.println("Error en la lectura de la sesion");
                        }
                        break;
                    }
                }
            }

            if (misesion == null) {
                System.out.println("Sin sesion activa");
                return "plantilla/sinSesion";
            }

            //Permiso para acceder a la vista 
            boolean permiso = false;
            String[] modulos = misesion.get("modulos").toString().split(",");
            for (int i = 0; i < modulos.length; i++) {
                if (modulos[i].equals(id)) {
                    permiso = true;
                    break;
                }
            }
            if (!permiso) {
                System.out.println("Modulo no autorizado");
                return "plantilla/sinSesion";
            }
        }
        model.addAttribute("pathRecursos", config.getServer().get("recursos"));
        model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
        model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
        model.addAttribute("title", "Claro360  - Lineamientos de sanidad" + config.getPersonalizacion().get("t1"));

        return null;
    }
    
    public static JSONObject getCookie(HttpServletRequest sesion) {
        //Inicializacion local automatica
//        InicializacionLocal.AutoInit();

        //Revision de inicializacion del proyecto 
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return null;
        }

        //Revision de sesion 
        JSONObject misesion = null;
            Cookie[] cookies = sesion.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("username_v3.1_plataforma360_dev")) {
                        JSONParser parser = new JSONParser();
                        try {
                            String value = new String(Base64.decodeBase64(c.getValue()));
                            misesion = (JSONObject) parser.parse(value);
                            misesion.put("v", c.getName());
                            //System.out.println(misesion);
                        } catch (ParseException ex) {
                            System.out.println("Error en la lectura de la sesion");
                        }
                        break;
                    }
                }
            }
            //System.out.println(misesion);
        return misesion;
    }

    

}
