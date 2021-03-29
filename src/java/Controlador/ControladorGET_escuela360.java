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
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @RequestMapping(value = "/inicio_alumno", method = RequestMethod.GET)
    private String inicio_alumno(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_alumno/plantilla_alumno");
        //return "Login";
    }
    @RequestMapping(value = "/perfil_alumno", method = RequestMethod.GET)
    private String perfil_alumno(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_alumno/perfil");
        //return "Login";
    }
    @RequestMapping(value = "/tarea_alumno", method = RequestMethod.GET)
    private String tarea_alumno(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_alumno/tareas");
        //return "Login";
    }
    
    @RequestMapping(value = "/evaluacion_alumno", method = RequestMethod.GET)
    private String evaluacion_alumno(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_alumno/evaluacion");
        //return "Login";
    }
    
    @RequestMapping(value = "/horario_alumno", method = RequestMethod.GET)
    private String horario_alumno(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_alumno/horario");
        //return "Login";
    }
    
    @RequestMapping(value = "/inicio_profesor", method = RequestMethod.GET)
    private String inicio_profesor(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_profesor/plantilla_profesor");
        //return "Login";
    }
    
    @RequestMapping(value = "/perfil_profesor", method = RequestMethod.GET)
    private String perfil_profesor(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_profesor/perfil");
        //return "Login";
    }
    
    @RequestMapping(value = "/horario_profesor", method = RequestMethod.GET)
    private String horario_profesor(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_profesor/horario");
        //return "Login";
    }
    
    
    @RequestMapping(value = "/evaluacion_profesor", method = RequestMethod.GET)
    private String evaluacion_profesor(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_profesor/evaluacion");
        //return "Login";
    }
    
    @RequestMapping(value = "/tareas_profesor", method = RequestMethod.GET)
    private String tarea_profesor(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_profesor/tareas");
        //return "Login";
    }
    
     @RequestMapping(value = "/registro_institucion", method = RequestMethod.GET)
    private String registro_institucion(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return "administracion_cursos/registro_institucion";
        //return "Login";
    }
    @RequestMapping(value = "/registro_sucursal", method = RequestMethod.GET)
    private String registro_sucursal(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return "administracion_cursos/registro_sucursal";
        //return "Login";
    }
    @RequestMapping(value = "/inicio_institucion", method = RequestMethod.GET)
    private String inicio_institucion(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return "administracion_cursos/plantilla_admin";
        //return "Login";
    }
    @RequestMapping(value = "/registro_grupo", method = RequestMethod.GET)
    private String registro_grupo(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return "administracion_cursos/grupo";
        //return "Login";
    }
    @RequestMapping(value = "/registro_materia", method = RequestMethod.GET)
    private String registro_materia(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return "administracion_cursos/materia";
        //return "Login";
    }
    @RequestMapping(value = "/registro_alumno", method = RequestMethod.GET)
    private String registro_alumno(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return "administracion_cursos/registro_alumno";
        //return "Login";
    }
    
    @RequestMapping(value = "/API/registro_institucion", method = RequestMethod.GET)
    @ResponseBody
    private String api_registro_institucion(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        return "{\"nombre\":\"administracion_cursos/registro_institucion\"}";
        //return "Login";
    }
    @RequestMapping(value = "/API/registro_institucion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_registro_institucion_post(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println(json);
        JSONObject respuesta=respuesta(true, "informacion recibida");
        respuesta.put("json", json);
        return respuesta;
        //return "Login";
    }
    
    @RequestMapping(value = "/API/registro/materia", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_registro_materia(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println(json);
        String table = "materia";
        String query = "INSERT INTO materia (nombre_materia,id_sucursal,id_usuario) VALUES ('"+json.get("Registrar_materia")+"',"+json.get("Registrar_sucursal")+","+json.get("Registrar_profesor")+");"; 
        
        Query.insert(query);
        
        JSONObject respuesta=respuesta(true, "Materia Creada");
        respuesta.put("json", json);
        return respuesta;
        //return "Login";
    }
    
    @RequestMapping(value = "/API/registro/grupo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_registro_grupo(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println(json);
        String table = "materia";
        String query = "INSERT INTO grupo (nombre_grupo,id_sucursal) VALUES ('"+json.get("Registrar_materia")+"',"+json.get("Registrar_sucursal")+");"; 
        
        int id = Query.insert(query);
        
        JSONObject respuesta=respuesta(true, "Grupo Creado");
        respuesta.put("json", json);        
        respuesta.put("id", id);

        return respuesta;
        //return "Login";
    }
    
    @RequestMapping(value = "/API/registro/grupo_horario", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_registro_grupo_horario(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println(json);
        
        //String table = "materia";
        String query = "INSERT INTO profesor_horario (id_usuario,id_grupo,id_materia) "
                + "VALUES ("+json.get("profesor_grupo")+","+json.get("grupo_escuela")+","+json.get("materia_grupo")+");"; 
        
        int id = Query.insert(query);
        
        String lunes = (String) json.get("hora_lunes");
        String martes = (String) json.get("hora_martes");
        String miercoles = (String) json.get("hora_miercoles");
        String jueves = (String) json.get("hora_jueves");
        String viernes = (String) json.get("hora_viernes");

         if (!lunes.isEmpty()){
             query = "INSERT INTO grupo_horario (id_grupo,id_materia,id_horario,id_dia) "
                + "VALUES ("+json.get("grupo_escuela")+","+json.get("materia_grupo")+","+json.get("hora_lunes")+",1);"; 
            Query.insert(query);
         }
          if (!martes.isEmpty()){
             query = "INSERT INTO grupo_horario (id_grupo,id_materia,id_horario,id_dia) "
                + "VALUES ("+json.get("grupo_escuela")+","+json.get("materia_grupo")+","+json.get("hora_martes")+",2);"; 
            Query.insert(query);
         }
           if (!miercoles.isEmpty()){
             query = "INSERT INTO grupo_horario (id_grupo,id_materia,id_horario,id_dia) "
                + "VALUES ("+json.get("grupo_escuela")+","+json.get("materia_grupo")+","+json.get("hora_miercoles")+",3);"; 
            Query.insert(query);
         }
            if (!jueves.isEmpty()){
             query = "INSERT INTO grupo_horario (id_grupo,id_materia,id_horario,id_dia) "
                + "VALUES ("+json.get("grupo_escuela")+","+json.get("materia_grupo")+","+json.get("hora_jueves")+",4);"; 
            Query.insert(query);
         }
             if (!viernes.isEmpty()){
             query = "INSERT INTO grupo_horario (id_grupo,id_materia,id_horario,id_dia) "
                + "VALUES ("+json.get("grupo_escuela")+","+json.get("materia_grupo")+","+json.get("hora_viernes")+",5);"; 
            Query.insert(query);
         }
        
        JSONObject respuesta=respuesta(true, "Grupo Creado");
        respuesta.put("json", json);        
        //respuesta.put("id", id);

        return respuesta;
        //return "Login";
    }
    
    @RequestMapping(value = "/registro_grupo_horario", method = RequestMethod.GET)
    private String registro_grupo_horario(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        int grupo = Integer.parseInt(sesion.getParameter("indice"));
        model.addAttribute("id_grupo", grupo);
        return "administracion_cursos/registro_grupo_horario";
        //return "Login";
    }
}
