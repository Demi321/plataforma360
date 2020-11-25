/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Config.config;
import Request.request;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.ui.Model;

/**
 *
 * @author moises
 */
public class ValidarIP {
    private static final String[] IP_HEADER_CANDIDATES = {
        "X-Forwarded-For",
        "Proxy-Client-IP",
        "WL-Proxy-Client-IP",
        "HTTP_X_FORWARDED_FOR",
        "HTTP_X_FORWARDED",
        "HTTP_X_CLUSTER_CLIENT_IP",
        "HTTP_CLIENT_IP",
        "HTTP_FORWARDED_FOR",
        "HTTP_FORWARDED",
        "HTTP_VIA",
        "REMOTE_ADDR"};
    
    
    public static String Validacion_ip_publica(HttpServletRequest sesion, Model model,String vista) throws ParseException, IOException{
        System.out.println(config.isVALIDAR_IP());
        if(config.isVALIDAR_IP()){
            JSONObject json=new JSONObject();
        JSONParser parser = new JSONParser();
        json.put("url",config.getPATH()+config.getDEPENDENCIA());
        boolean has_ip = false;
        for (String header : IP_HEADER_CANDIDATES) {

            String ip = sesion.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                json.put("ip", ip);
                JSONObject respuesta= (JSONObject) parser.parse(request.POST(config.getURL_CONTROLADOR()+"API/Validar_ip_publica", json.toString()));
                if((boolean)respuesta.get("autorizado")){
                    has_ip = true;
                    respuesta.put("ip_publica", ip);
                    model.addAttribute("ip_publica", respuesta);
                    model.addAttribute("vista", vista);
                return "info/preloading";
                }
                
            }
        }
        if (!has_ip) {
             json.put("ip", sesion.getRemoteAddr());
             JSONObject respuesta= (JSONObject) parser.parse(request.POST(config.getURL_CONTROLADOR()+"API/Validar_ip_publica", json.toString()));
                if((boolean)respuesta.get("autorizado")){
                    has_ip = true;
                    respuesta.put("ip_publica", sesion.getRemoteAddr());
                    model.addAttribute("ip_publica", respuesta.toString().replace("\"", "&quot"));
                    model.addAttribute("vista", vista);
                return "info/preloading";
                }     
        }
        return "info/401";
        }else{
            System.out.println(vista);
            return vista;
        }
        
    }

    public static boolean Validacion_ip_local(JSONObject json) throws IOException {
        String respuesta=request.POST(config.getURL_CONTROLADOR()+"API/Validar_ip_local", json.toString());
        System.out.println(respuesta);
        return Boolean.parseBoolean(respuesta);
    }
    
}
