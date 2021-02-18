/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Config.Revision;
import Config.config;
import Modelo.GrupoDAO;
import Modelo.Query;
import Modelo.UsuarioMovilDAO;
import Modelo.ValidarIP;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Moises Juarez
 */
@Controller
@Async

public class Controller2 {

    private JSONObject respuesta(boolean success, String message) {
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", success);
        respuesta.put("failure", !success);
        respuesta.put("mensaje", message);
        return respuesta;
    }

    
    @RequestMapping(value = "/jose", method = RequestMethod.GET)
    public String jose(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            //model.addAttribute("title", config.getAliasServicio() + " - " + config.getPersonalizacion().get("t1"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
//        return ValidarIP.Validacion_ip_publica(sesion, model, "login/Login");
        return "login/Login";
    }
    
    @RequestMapping(value = "/jose/variable", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject jose2(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            //model.addAttribute("title", config.getAliasServicio() + " - " + config.getPersonalizacion().get("t1"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return null;
        }
//        return ValidarIP.Validacion_ip_publica(sesion, model, "login/Login");
        return respuesta(true, "El servicio funciona");
    }
    
    @RequestMapping(value = "/jose/post", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject post(Model model, @RequestBody JSONObject json) {
        
        return json;
    }
    @RequestMapping(value = "/API/empresas360/consulta_vistatutorial", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject consulta_vistatutorial(Model model, @RequestBody JSONObject json) {
        /*
        este servicio recuperara el estatus de si una vista ya se mostro la parte turorial
        -id360
        -id_menu
        */
        
        String query = "SELECT * from vistas_tutorial WHERE id360='"+json.get("id360")+"' and id_menu='"+json.get("id_menu")+"'";
        JSONObject estatus_vista = Query.select(query);
        if(estatus_vista==null){
           //Como no se encontro ningun registro se agregara con un estatus de no visto
           Query.insert(Query.createQueryInsert("vistas_tutorial", json));
           return respuesta(false, "Tutorial aun no visualizado");
        }else{
            if(estatus_vista.get("visto").toString().equals("0")){
                return respuesta(false, "Tutorial aun no visualizado");
            }
        }
        return respuesta(true, "Tutorial visualizado");
    }
    
    @RequestMapping(value = "/jose/post2", method = RequestMethod.POST)
    public String post2(Model model, @RequestBody JSONObject json) {
        //consultar bajdera para vista tutorial 
        
        
        model.addAttribute("Alerta", json);
        return "login/Login";
    }
    


}
