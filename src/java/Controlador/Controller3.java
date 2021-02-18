/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Config.Revision;
import Config.config;
import Modelo.Query;
import Request.request;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Moises Juarez
 */
@Controller
@Async

public class Controller3 {

    private JSONObject respuesta(boolean success, String message) {
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", success);
        respuesta.put("failure", !success);
        respuesta.put("mensaje", message);
        return respuesta;
    }
    @RequestMapping(value = "/API/empresa_dashboard", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject empresa_dashboard(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("empresa_dashboard ------>>>>>>>>");   
        JSONObject sucursales =  new JSONObject();
        JSONObject respuesta = respuesta(false, "Ocurrio un error. Intentarlo mas tarde.");
        String empresa = "SELECT * FROM tipos_usuarios WHERE id = "+ json.get("tipo_usuario");
        JSONArray infoempresa = Query.execute(empresa);
        String sucursal = "SELECT count(id) AS numero_sucursales FROM servicios_usuario WHERE idTipoUsuario = "+ json.get("tipo_usuario") + " and activo = 1";
        sucursales = Query.select(sucursal);
                    
        /*JSONArray array_id360 = new JSONArray();
        String sucursal1 = "SELECT count(idUsuario) FROM directorio WHERE tipo_usuario = "+ json.get("tipo_usuario") + " and activo = 1";
        JSONArray sucual1 = Query.execute(sucursal1);
        for (int i = 0; i < sucual1.size(); i++) {
            JSONObject elemento = new JSONObject();
            elemento.put("id360", ((JSONObject) sucual1.get(i)).get("idUsuario"));                 
            array_id360.add(elemento);
        }
        
        JSONArray plataforma360Global = Request.request.POST(config.getURL_CONTROLADOR()+ "API/cuenta360/perfiles/array", array_id360);
        */
        sucursales.put("razon_social", ((JSONObject) infoempresa.get(0)).get("razon_social"));        
        sucursales.put("date_updated", ((JSONObject) infoempresa.get(0)).get("date_update"));
        sucursales.put("date_created", ((JSONObject) infoempresa.get(0)).get("date_created"));
        sucursales.put("registro_patronal", ((JSONObject) infoempresa.get(0)).get("registro_patronal"));
        sucursales.put("activa", ((JSONObject) infoempresa.get(0)).get("activa"));
        sucursales.put("time_updated", ((JSONObject) infoempresa.get(0)).get("time_updated"));
        sucursales.put("rfc", ((JSONObject) infoempresa.get(0)).get("rfc"));
        sucursales.put("token", ((JSONObject) infoempresa.get(0)).get("token"));
        sucursales.put("logotipo", ((JSONObject) infoempresa.get(0)).get("logotipo"));
        sucursales.put("correo", ((JSONObject) infoempresa.get(0)).get("correo"));
        sucursales.put("RegistroApp", ((JSONObject) infoempresa.get(0)).get("RegistroApp"));
        sucursales.put("time_created", ((JSONObject) infoempresa.get(0)).get("time_created"));
        sucursales.put("id", ((JSONObject) infoempresa.get(0)).get("id"));
        sucursales.put("telefono", ((JSONObject) infoempresa.get(0)).get("telefono"));
        sucursales.put("tipo_usuario", ((JSONObject) infoempresa.get(0)).get("tipo_usuario"));
        sucursales.put("logotipo_empresa", ((JSONObject) infoempresa.get(0)).get("logotipo_empresa"));
        sucursales.put("activo", ((JSONObject) infoempresa.get(0)).get("activo"));                                                                                                                                                                                                                                              
        
        return  sucursales;              
    }
    @RequestMapping(value = "/API/cantidad_empleados", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONArray cantidad_empleados(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println("cantidad_empleados ------>>>>>>>>");
        JSONArray respuesta = new JSONArray();
        JSONObject servicio = new JSONObject();
        String query = "SELECT id FROM servicios_usuario WHERE activo = 1 and idTipoUsuario = " + json.get("tipo_servicio");
        JSONArray array = Query.execute(query);        
        JSONArray array_id360 = new JSONArray();
        String query1="";
        JSONObject object  = new JSONObject();            
        for (int i = 0; i < array.size(); i++) {
            object = (JSONObject) array.get(i); 
            query1 = "SELECT idUsuario from empresas.directorio WHERE tipo_servicio = "+ object.get("id") + " and activo =1;";
            JSONArray res = Query.execute(query1);
            if ( res != null) {
                for (int j = 0; j < res.size(); j++) {
                    JSONObject elemento = new JSONObject();
                    elemento.put("id360", ((JSONObject) res.get(j)).get("idUsuario"));     
                    System.out.println(elemento);
                    array_id360.add(elemento);
                }        
                respuesta = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfiles/array", array_id360);                        
                /*for (int k = 0; k < respuesta.size(); k++) {
                    servicio = ((JSONObject) array.get(i));
                    
                    if (!servicio.get("genero").toString().equals("Hombre")) {
                       //array.remove(i);
                        System.out.println("GENERO HOMBRE ");
                       //i--;
                       //return servicio;
                    }else{
                        System.out.println("GENERO MUJER");
                    }                   
                }*/
            }                                   
        }   
        return respuesta;
    }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
    

}
