/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Config.Revision;
import Config.config;
import static Controlador.BackupDirectorio.getBackupDirectorio;
import static Controlador.ControladorPOST.ComplementoInfoIntegrantes;
import Modelo.Escalamiento;
import Modelo.Post;
import Modelo.ProyectoDAO;
import Modelo.ProyectoVO;
import Modelo.Query;
import Modelo.RegistroLlamadasDAO;
import Modelo.ValidarIP;
import Request.request;
import com.google.gson.Gson;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.ResponseWrapper;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
//import sun.misc.BASE64Decoder;

/**
 *
 * @author Moises Juarez
 */
@Controller
@Async
public class ControladorGET_escuela360 {

    private JSONObject respuesta(boolean r, String m) {
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", r);
        respuesta.put("failure", !r);
        respuesta.put("mensaje", m);
        return respuesta;
    }

    @RequestMapping(value = "/administracion_directiva", method = RequestMethod.GET)
    private String administracion_directiva(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela/administracion_directiva");
        //return "Login";
    }
    
    @RequestMapping(value = "/planeacion_docente", method = RequestMethod.GET)
    private String planeacion_docente(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela/planeacion_docente");
        //return "Login";
    }
    
    @RequestMapping(value = "/actividad_estudiantil", method = RequestMethod.GET)
    private String actividad_estudiantil(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela/actividad_estudiantil");
        //return "Login";
    }
    @RequestMapping(value = "/agregar_perfil", method = RequestMethod.GET)
    private String agregar_perfil(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela/agregar_perfil");
        //return "Login";
    }
    @RequestMapping(value = "/API/GET/listado_personal/{tipo_usuario}/{tipo_servicio}", method = RequestMethod.GET)
    @ResponseBody
    private JSONArray listado_personal(HttpServletRequest sesion, Model model,@PathVariable("tipo_usuario") String tipo_usuario,@PathVariable("tipo_servicio") String tipo_servicio) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            //return autorizacion;
            return new JSONArray();
        }
        //Recuperar personal de una institucion
        
        JSONArray personal = Query.execute("SELECT * FROM personal_escuela WHERE tipo_usuario='"+tipo_usuario+"' AND tipo_servicio = '"+tipo_servicio+"';");
        for (int i = 0; i < personal.size(); i++) {
            JSONObject p = (JSONObject) personal.get(i);
            p.put("id360", p.get("id_usuario"));
        }
        //recuperar sus perfiles basicos 
        JSONArray personal_perfiles = request.POST(config.getURL_CONTROLADOR()+"API/cuenta360/perfiles/array", personal);
        for (int i = 0; i < personal_perfiles.size(); i++) {
            JSONObject p = (JSONObject) personal_perfiles.get(i);
            JSONArray id_grupos = Query.execute("SELECT id_grupo FROM profesores WHERE id_usuario = '"+p.get("id360")+"';");
            JSONArray grupos = new JSONArray();
            for (int j = 0; j < id_grupos.size(); j++) {
                JSONObject id = (JSONObject) id_grupos.get(j);
                JSONObject nombre_grupo = Query.select("SELECT nombre FROM grupos_academicos WHERE id='"+id.get("id_grupo")+"';");
                grupos.add(nombre_grupo.get("nombre"));
            }
            p.put("grupos", grupos);
        }
        return personal_perfiles;
        //return "Login";
    }
    @RequestMapping(value = "/API/GET/listado_grupos/{tipo_servicio}", method = RequestMethod.GET)
    @ResponseBody
    private JSONArray listado_grupos(HttpServletRequest sesion, Model model,@PathVariable("tipo_servicio") String tipo_servicio) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            //return autorizacion;
            return new JSONArray();
        }
        //Recuperar los grupos de una escuela
        JSONArray grupos = Query.execute("SELECT * FROM grupos_academicos WHERE id_institucion_academica='"+tipo_servicio+"';");
        for (int i = 0; i < grupos.size(); i++) {
            JSONObject g = (JSONObject) grupos.get(i);
            JSONArray profesores = Query.execute("SELECT id_usuario AS id360 FROM profesores WHERE id_grupo = '"+g.get("id")+"';");
            g.put("profesores", profesores);
        }
        return grupos;
        //return "Login";
    }
}
