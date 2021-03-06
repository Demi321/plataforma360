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
    
    @RequestMapping(value = "/detalle_tarea", method = RequestMethod.GET)
    private String detalle_tarea(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        String query=null;
        query="select e.id_evaluacion, e.titulo_evaluacion, e.descripcion, g.nombre_grupo, m.nombre_materia, t.descripcion as tipo, e.fecha_entrega from cursos.evaluacion e\n" +
                "inner join cursos.grupo g on e.id_grupo = g.id_grupo\n" +
                "inner join cursos.materia m on e.id_materia = m.id_materia\n" +
                "inner join cursos.tipoevaluacion t on e.id_tipo_evaluacion = t.id_tipo_evaluacion\n" +
                "where e.id_evaluacion = "+sesion.getParameter("indice")+";";
        JSONArray tarea = new JSONArray();
        tarea = Query.selectArray(query);
        
        System.out.println(tarea);

        model.addAttribute("tarea", tarea);
        
        System.out.println("INDICE: "+sesion.getParameter("indice"));
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_alumno/detalle_tarea");
        //return "Login";
    }
    
    @RequestMapping(value = "/ver_tarea_alumno", method = RequestMethod.GET)
    private String ver_tarea_alumno(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        String query=null;
         query="select g.id_grupo, e.id_evaluacion, e.titulo_evaluacion, e.descripcion, g.nombre_grupo, m.nombre_materia, t.descripcion as tipo, e.fecha_entrega from cursos.evaluacion e\n" +
                "inner join cursos.grupo g on e.id_grupo = g.id_grupo\n" +
                "inner join cursos.materia m on e.id_materia = m.id_materia\n" +
                "inner join cursos.tipoevaluacion t on e.id_tipo_evaluacion = t.id_tipo_evaluacion\n" +
                "where e.id_evaluacion = "+sesion.getParameter("id_evaluacion")+";";
        JSONArray tarea = new JSONArray();
        tarea = Query.selectArray(query);
        
        System.out.println(tarea);

        model.addAttribute("tarea", tarea);
        model.addAttribute("id_usuario", sesion.getParameter("id_usuario"));
        
        System.out.println("INDICE: "+sesion.getParameter("indice"));
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_profesor/tarea_alumno");
        //return "Login";
    }
    
    @RequestMapping(value = "/ver_tarea_entregada_alumno", method = RequestMethod.GET)
    private String ver_tarea_entregada_alumno(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        String query=null;
         query="select a.calificacion,e.id_evaluacion, e.titulo_evaluacion, e.descripcion, g.nombre_grupo, m.nombre_materia, t.descripcion as tipo, e.fecha_entrega from cursos.evaluacion_alumno a\n" +
                "inner join cursos.evaluacion e on a.id_evaluacion = e.id_evaluacion\n" +
                "inner join cursos.grupo g on e.id_grupo = g.id_grupo\n" +
                "inner join cursos.materia m on e.id_materia = m.id_materia\n" +
                "inner join cursos.tipoevaluacion t on e.id_tipo_evaluacion = t.id_tipo_evaluacion\n" +
                "where a.id_usuario = "+sesion.getParameter("id_usuario")+" and e.id_usuario = 1;";
        JSONArray tareas = new JSONArray();
        tareas = Query.selectArray(query);
        
        System.out.println(tareas);

        model.addAttribute("tareas", tareas);
        model.addAttribute("id_usuario", sesion.getParameter("id_usuario"));
        
        //System.out.println("INDICE: "+sesion.getParameter("indice"));
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_profesor/tarea_entregada_alumno");
        //return "Login";
    }
    
    @RequestMapping(value = "/tarea_alumno", method = RequestMethod.GET)
    private String tarea_alumno(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        String query=null;
        query="SELECT  e.id_evaluacion, e.titulo_evaluacion, e.descripcion, g.nombre_grupo, m.nombre_materia, t.descripcion as tipo, e.fecha_entrega FROM cursos.registro_alumno a\n" +
                "inner join cursos.evaluacion e on a.id_grupo = e.id_grupo\n" +
                "inner join cursos.grupo g on e.id_grupo = g.id_grupo\n" +
                "inner join cursos.materia m on e.id_materia = m.id_materia\n" +
                "inner join cursos.tipoevaluacion t on e.id_tipo_evaluacion = t.id_tipo_evaluacion\n" +
                "where a.id_usuario = 12;";
        JSONArray tareas = new JSONArray();
        tareas = Query.selectArray(query);
        
        System.out.println(tareas);

        model.addAttribute("tareas", tareas);
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_alumno/tareas");
        //return "Login";
    }
    
    @RequestMapping(value = "/evaluacion_alumno", method = RequestMethod.GET)
    private String evaluacion_alumno(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        String query=null;
        query="SELECT t.descripcion, m.nombre_materia, g.nombre_grupo, e.fecha_entrega, e.titulo_evaluacion, a.calificacion FROM cursos.evaluacion_alumno a\n" +
            "inner join cursos.evaluacion e on  a.id_evaluacion = e.id_evaluacion\n" +
            "inner join cursos.grupo g on e.id_grupo = g.id_grupo\n" +
            "inner join cursos.materia m on e.id_materia = m.id_materia\n" +
            "inner join cursos.tipoevaluacion t on e.id_tipo_evaluacion = t.id_tipo_evaluacion\n" +
            "where a.id_usuario = 12;";
        JSONArray tareas = new JSONArray();
        tareas = Query.selectArray(query);
        
        System.out.println(tareas);

        model.addAttribute("tareas", tareas);
        
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_alumno/evaluacion");
        //return "Login";
    }
    
    @RequestMapping(value = "/horario_alumno", method = RequestMethod.GET)
    private String horario_alumno(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
         String query=null;
        query="select g.nombre_grupo, m.nombre_materia, gh.* from cursos.registro_alumno c\n" +
                "inner join cursos.grupo_horario gh on gh.id_grupo = c.id_grupo\n" +
                "inner join  cursos.grupo g on c.id_grupo = g.id_grupo\n" +
                "inner join  cursos.materia m on gh.id_materia = m.id_materia\n" +
                "where c.id_usuario = 12;";
        JSONArray horario = new JSONArray();
        horario = Query.selectArray(query);
        
        System.out.println(horario);

        model.addAttribute("horario", horario);
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
        String query=null;
                
        query="select g.nombre_grupo, m.nombre_materia, c.* from cursos.grupo_horario c \n" +
        "inner join  cursos.grupo g on c.id_grupo = g.id_grupo\n" +
        "inner join  cursos.materia m on c.id_materia = m.id_materia\n" +
        "where c.id_usuario = 1;";
        JSONArray horario = new JSONArray();
        horario = Query.selectArray(query);
        
        System.out.println(horario);

        model.addAttribute("horario", horario);
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_profesor/horario");
        //return "Login";
    }
    
    
    @RequestMapping(value = "/evaluacion_profesor", method = RequestMethod.GET)
    private String evaluacion_profesor(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        System.out.println(sesion.getParameter("id_grupo"));
        String query=null;
                
        query="select distinct c.id_grupo,g.nombre_grupo from cursos.grupo_horario c \n" +
        "inner join  cursos.grupo g on c.id_grupo = g.id_grupo\n" +
        "where c.id_usuario = 1;";
        JSONArray grupo = new JSONArray();
        grupo = Query.selectArray(query);
        System.out.println(grupo);
         model.addAttribute("grupo", grupo);
         model.addAttribute("id_evaluacion", sesion.getParameter("id_evaluacion"));
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_profesor/evaluacion");
        //return "Login";
    }
    
    @RequestMapping(value = "/calificacion_profesor", method = RequestMethod.GET)
    private String calificacion_profesor(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        System.out.println(sesion.getParameter("id_grupo"));
        String query=null;
                
        query="select distinct c.id_grupo,g.nombre_grupo from cursos.grupo_horario c \n" +
        "inner join  cursos.grupo g on c.id_grupo = g.id_grupo\n" +
        "where c.id_usuario = 1;";
        JSONArray grupo = new JSONArray();
        grupo = Query.selectArray(query);
        System.out.println(grupo);
         model.addAttribute("grupo", grupo);
         model.addAttribute("id_evaluacion", sesion.getParameter("id_evaluacion"));
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_profesor/calificacion");
        //return "Login";
    }
    
    @RequestMapping(value = "/tareas_profesor", method = RequestMethod.GET)
    private String tarea_profesor(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        
        String query=null;
                
        query="select distinct c.id_grupo,g.nombre_grupo from cursos.grupo_horario c \n" +
        "inner join  cursos.grupo g on c.id_grupo = g.id_grupo\n" +
        "where c.id_usuario = 1;";
        JSONArray grupo = new JSONArray();
        grupo = Query.selectArray(query);
        
        query="select * from tipoevaluacion;";
        JSONArray tipo_evaluacion = new JSONArray();
        tipo_evaluacion = Query.selectArray(query);
        
        query="select e.id_grupo, e.id_evaluacion, e.titulo_evaluacion, e.descripcion, g.nombre_grupo, m.nombre_materia, t.descripcion as tipo, e.fecha_entrega from cursos.evaluacion e\n" +
                "inner join cursos.grupo g on e.id_grupo = g.id_grupo\n" +
                "inner join cursos.materia m on e.id_materia = m.id_materia\n" +
                "inner join cursos.tipoevaluacion t on e.id_tipo_evaluacion = t.id_tipo_evaluacion\n" +
                "where e.id_usuario = 1;";
        JSONArray tareas = new JSONArray();
        tareas = Query.selectArray(query);
        
        System.out.println(tareas);

        model.addAttribute("grupo", grupo);
        model.addAttribute("tareas", tareas);
        model.addAttribute("tipo_evaluacion", tipo_evaluacion);
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_profesor/tareas");
        //return "Login";
    }
    @RequestMapping(value = "/calificar_tarea", method = RequestMethod.GET)
    private String calificar_tarea(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        
        System.out.println(sesion.getParameter("id_grupo"));
        String query=null;
                
        query="select c.id_grupo,g.nombre_grupo, c.id_usuario from cursos.registro_alumno c \n" +
        "inner join  cursos.grupo g on c.id_grupo = g.id_grupo\n" +
        "where c.id_grupo = "+sesion.getParameter("id_grupo")+";";
        JSONArray alumnos = new JSONArray();
        alumnos = Query.selectArray(query);
         model.addAttribute("alumnos", alumnos);
         model.addAttribute("id_evaluacion", sesion.getParameter("id_evaluacion"));
         
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_profesor/calificar_tarea");
        //return "Login";
    }
    
    @RequestMapping(value = "/calificar_alumno", method = RequestMethod.GET)
    private String calificar_alumno(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        
        System.out.println(sesion.getParameter("id_grupo"));
        String query=null;
                
        query="select * from materia where id_materia = "+sesion.getParameter("id_materia")+";";
        JSONArray materia = new JSONArray();
        materia = Query.selectArray(query);
         model.addAttribute("materia", materia);
         
         query="select * from grupo where id_grupo = "+sesion.getParameter("id_grupo")+";";
        JSONArray grupo = new JSONArray();
        grupo = Query.selectArray(query);
         model.addAttribute("grupo", grupo);
         model.addAttribute("id_usuario", sesion.getParameter("id_usuario"));
         
         query="select * from calificacion_alumno where id_materia = "+sesion.getParameter("id_materia")+" and id_grupo = "+sesion.getParameter("id_grupo")+" and id_usuario = "+sesion.getParameter("id_usuario")+";";
        JSONArray calificacion = new JSONArray();
        calificacion = Query.selectArray(query);
        if(calificacion != null){
            JSONObject calif = new JSONObject();
            calif = (JSONObject) calificacion.get(0);
            System.out.println(calificacion);
            model.addAttribute("calificacion", calif.get("calificacion"));
        }else{
            model.addAttribute("calificacion", 0);
        }
         
        return ValidarIP.Validacion_ip_publica(sesion, model, "escuela_profesor/calificacion_alumno");
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
        String query="SELECT * FROM grupo;";
         
         JSONArray jsonArray = new JSONArray();
         jsonArray = Query.consultaQuery(query);
         model.addAttribute("grupos", jsonArray);
         System.out.println(jsonArray.get(0));
        return "administracion_cursos/grupo";
        //return "Login";
    }
    @RequestMapping(value = "/registro_materia", method = RequestMethod.GET)
    private String registro_materia(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        String query="SELECT * FROM materia;";
         
         JSONArray jsonArray = new JSONArray();
         jsonArray = Query.consultaQuery(query);
         model.addAttribute("materias", jsonArray);
         System.out.println(jsonArray.get(0));
         //model.addAttribute("numeroMaterias", jsonArray.size());

        return "administracion_cursos/materia";
        //return "Login";
    }
    @RequestMapping(value = "/registro_alumno", method = RequestMethod.GET)
    private String registro_alumno(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        String query="SELECT * FROM grupo;";
         
         JSONArray jsonArray = new JSONArray();
         jsonArray = Query.consultaQuery(query);
         
          query="select g.nombre_grupo, c.* from cursos.registro_alumno c \n" +
        "inner join  cursos.grupo g on c.id_grupo = g.id_grupo;";
          JSONArray alumno = new JSONArray();
         alumno = Query.consultaQuery(query);
          
         model.addAttribute("grupo", jsonArray);
         model.addAttribute("alumno", alumno);
        return "administracion_cursos/registro_alumno";
        //return "Login";
    }
    @RequestMapping(value = "/API/registro/alumno_grupo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_registro_alumno_grupo(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println(json);
        
        //int bandera = (int) json.get("bandera");
        //String table = "materia";
        String query = "INSERT INTO registro_alumno (id_usuario,id_grupo) "
                + "VALUES ("+json.get("registra_alumno")+","+json.get("registra_grupo")+");"; 
        
        int id = Query.insert(query);
        
       
        
        JSONObject respuesta=respuesta(true, "Alumno Inscrito");
        respuesta.put("json", json);        
        //respuesta.put("id", id);

        return respuesta;
        //return "Login";
    }
    
    @RequestMapping(value = "/API/registro/entrega_tarea", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_registro_entrega_tarea(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println(json);
        
        //int bandera = (int) json.get("bandera");
        //String table = "materia";
        String query = "INSERT INTO cursos.evaluacion_alumno (id_evaluacion,id_usuario,url) "
                + "VALUES ('"+json.get("id_evaluacion")+"','"+json.get("id_usuario")+"','"+json.get("url")+"');"; 
        
        int id = Query.insert(query);
         
       System.out.println(query);
        
        JSONObject respuesta=respuesta(true, "Tarea Entregada");
        respuesta.put("json", json);        
        respuesta.put("id", id);

        return respuesta;
        //return "Login";
    }
    
    @RequestMapping(value = "/API/elimina/alumno_horario", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_elimina_alumno_horario(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println(json);
        
        int id_alumnno = (int) json.get("id_alumno");
        //String table = "materia";
        String query = "DELETE FROM registro_alumno WHERE (id_usuario = "+id_alumnno+");"; 
        
        Query.update(query);
       
        JSONObject respuesta=respuesta(true, "Alumno No Inscrito");
        respuesta.put("id", id_alumnno);        
        //respuesta.put("id", id);

        return respuesta;
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
    
     @RequestMapping(value = "/API/registro/tarea", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_registro_tarea(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println(json);
        String table = "materia";
        String query = "INSERT INTO evaluacion (titulo_evaluacion,descripcion,id_grupo,id_materia,id_tipo_evaluacion,fecha_entrega,id_usuario) "
                + "VALUES ('"+json.get("titulo_tarea")+"','"+json.get("nombre_tarea")+"','"+json.get("grupo_tarea")+"','"+json.get("materia_tarea")+"','"+json.get("tipo_tarea")+"','"+json.get("fecha_entrega")+"',1);"; 
        
        Query.insert(query);
        
        JSONObject respuesta=respuesta(true, "Tarea Creada");
        respuesta.put("json", json);
        return respuesta;
        //return "Login";
    }
    
    @RequestMapping(value = "/API/registro/grupo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_registro_grupo(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println(json);
        String table = "materia";
        String query = "INSERT INTO grupo (nombre_grupo,id_sucursal,nivel) VALUES"
                + " ('"+json.get("Registrar_materia")+"',"+json.get("Registrar_sucursal")+","+json.get("nivel_grupo")+");"; 
        
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
        
        int bandera = (int) json.get("bandera");
        //String table = "materia";
        String query = "INSERT INTO grupo_horario (id_usuario,id_grupo,id_materia,lunes,martes,miercoles,jueves,viernes) "
                + "VALUES ("+json.get("profesorhorario"+bandera)+","+json.get("id_grupo")+","+json.get("materia_horario"+bandera)+",'"
                +json.get("lunes_horario"+bandera)+"','"+json.get("martes_horario"+bandera)+"','"+json.get("miercoles_horario"+bandera)+"','"
                +json.get("jueves_horario"+bandera)+"','"+json.get("viernes_horario"+bandera)+"');"; 
        
        int id = Query.insert(query);
        
       
        
        JSONObject respuesta=respuesta(true, "Horario Creado");
        respuesta.put("json", json);        
        //respuesta.put("id", id);

        return respuesta;
        //return "Login";
    }
    @RequestMapping(value = "/API/registro/calificacion_alumnos", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_registro_calificacion_alumnos(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println(json);
        
        //int bandera = (int) json.get("bandera");
        //String table = "materia";
        String query = "INSERT INTO calificacion_alumno (id_usuario,id_grupo,id_materia,calificacion) "
                + "VALUES ("+json.get("id_usuario")+","+json.get("id_grupo")+","+json.get("id_materia")+",'"+json.get("calificacion")+"');"; 
        
        int id = Query.insert(query);
        
       
        
        JSONObject respuesta=respuesta(true, "Alumno Calificado");
        respuesta.put("json", json);        
        //respuesta.put("id", id);

        return respuesta;
        //return "Login";
    }
    
    @RequestMapping(value = "/API/calificar/tarea", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_calificar_tarea(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println(json);
        
        String query = "SELECT * FROM evaluacion_alumno WHERE id_evaluacion = "+json.get("id_evaluacion")+" AND id_usuario = "+json.get("id_usuario")+";";
        JSONObject jason = new JSONObject();
        jason = Query.select(query);
         
        //int bandera = (int) json.get("bandera");
        if(jason == null){
             System.out.println("UNO");
             query = "INSERT INTO evaluacion_alumno (id_evaluacion,id_usuario,calificacion) "
                + "VALUES ("+json.get("id_evaluacion")+","+json.get("id_usuario")+","+json.get("calificacion")+");"; 
            int id = Query.insert(query);
        }else{
             System.out.println("DOS");
             query = "UPDATE evaluacion_alumno SET calificacion = '"+json.get("calificacion")+"' "
                    + "WHERE id_evaluacion = "+json.get("id_evaluacion")+" AND id_usuario = "+json.get("id_usuario")+";"; 
            Query.insert(query);
        }
        //String table = "materia";
        
        JSONObject respuesta=respuesta(true, "Alumno Calificado");
        respuesta.put("json", json);        
        //respuesta.put("id", id);

        return respuesta;
        //return "Login";
    }
    
    @RequestMapping(value = "/API/elimina/grupo_horario", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_elimina_grupo_horario(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println(json);
        
        int id_grupo = (int) json.get("id_grupo");
        //String table = "materia";
        String query = "DELETE FROM grupo_horario WHERE (id_grupo_horario = "+id_grupo+");"; 
        
        Query.update(query);
       
        JSONObject respuesta=respuesta(true, "Horario Eliminado");
        respuesta.put("id", id_grupo);        
        //respuesta.put("id", id);

        return respuesta;
        //return "Login";
    }
    
    @RequestMapping(value = "/API/elimina/grupo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_elimina_grupo(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println(json);
        
        int id_grupo = (int) json.get("id_grupo");
        //String table = "materia";
        String query = "SELECT count(id_grupo) FROM grupo_horario WHERE (id_grupo = "+id_grupo+");"; 
        
        
        JSONObject jason = new JSONObject();
        jason = Query.select(query);
        
        //int indice = (int) jason.get("count(id_grupo)");
       
        System.out.println( jason.get("count(id_grupo)")+"hola");
        JSONObject respuesta = new JSONObject();
        
        if( jason.get("count(id_grupo)").equals("0")){
            query = "DELETE FROM grupo WHERE (id_grupo = "+id_grupo+");";
            Query.update(query);
                   
             respuesta=respuesta(true, "Horario Eliminado");
            respuesta.put("id", id_grupo);        
            //respuesta.put("id", id);
            return respuesta;
           
        }else{
             respuesta=respuesta(true, "Horario No Eliminado, revise que que no contenga horario");
            respuesta.put("id", id_grupo);        
            //respuesta.put("id", id);

            return respuesta;
            

        }
    }
    @RequestMapping(value = "/API/elimina/materia", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_elimina_materia(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println(json);
        
        int id_materia = (int) json.get("id_materia");
        //String table = "materia";
        String query = "SELECT count(id_materia) FROM grupo_horario WHERE (id_materia = "+id_materia+");"; 
        
        
        JSONObject jason = new JSONObject();
        jason = Query.select(query);
        
        //int indice = (int) jason.get("count(id_grupo)");
       
        //System.out.println( jason.get("count(id_grupo)")+"hola");
        JSONObject respuesta = new JSONObject();
        
        if( jason.get("count(id_materia)").equals("0")){
            query = "DELETE FROM materia WHERE (id_materia = "+id_materia+");";
            Query.update(query);
                   
             respuesta=respuesta(true, "Materia Eliminada");
            respuesta.put("id", id_materia);        
            //respuesta.put("id", id);
            return respuesta;
           
        }else{
             respuesta=respuesta(true, "Materia No Eliminada, revise que que no contenga horario");
            respuesta.put("id", id_materia);        
            //respuesta.put("id", id);

            return respuesta;
            

        }
    }
    
    @RequestMapping(value = "/registro_grupo_horario", method = RequestMethod.GET)
    private String registro_grupo_horario(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        String autorizacion = Revision.autorizacion(sesion, model, null);
        if (autorizacion != null) {
            return autorizacion;
        }
        int grupo = Integer.parseInt(sesion.getParameter("indice"));
        String query="SELECT id_grupo, nombre_grupo FROM grupo where id_grupo = "+grupo+";";
        JSONArray jsonO = new JSONArray();
        jsonO = Query.selectArray(query);
        
        query="select g.nombre_grupo, m.nombre_materia, c.* from cursos.grupo_horario c \n" +
        "inner join  cursos.grupo g on c.id_grupo = g.id_grupo\n" +
        "inner join  cursos.materia m on c.id_materia = m.id_materia\n" +
        "where c.id_grupo = "+grupo+";";
        JSONArray horario = new JSONArray();
        horario = Query.selectArray(query);
        
        query="SELECT * FROM materia;";
        JSONArray materias = new JSONArray();
        materias = Query.selectArray(query);
        
       
        System.out.println(horario);

        model.addAttribute("grupo", jsonO);
        model.addAttribute("materias", materias);
        model.addAttribute("horario", horario);
         
        return "administracion_cursos/registro_grupo_horario";
        //return "Login";
    }
    
    @RequestMapping(value = "/API/consulta/materia", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_consulta_materia(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println(json);
        
        String query="select c.id_materia, m.nombre_materia from cursos.grupo_horario c \n" +
        "inner join  cursos.materia m on c.id_materia = m.id_materia\n" +
        "where c.id_grupo = "+json.get("id_grupo")+" and c.id_usuario = "+json.get("id_usuario")+" ;";
        JSONArray materia = new JSONArray();
        materia = Query.selectArray(query);
        
        JSONObject respuesta=respuesta(true, "materias obtenidas");
        respuesta.put("json", json);        
        respuesta.put("materia", materia);

        return respuesta;
        //return "Login";
    }
    @RequestMapping(value = "/API/revisar/tarea", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_revisar_tarea(@RequestBody JSONObject json) throws ParseException, IOException {
        System.out.println(json);
        
        String query="select url from cursos.evaluacion_alumno \n" +
        "where id_evaluacion = "+json.get("id_evaluacion")+" and id_usuario = "+json.get("id_usuario")+" ;";
        JSONArray url = new JSONArray();
        url = Query.selectArray(query);
        System.out.println("URL: "+url);
        if(url == null){
            JSONObject respuesta=respuesta(false, "Tarea no entregada");
             return respuesta;
        }else{
           
            JSONObject p = (JSONObject) url.get(0);
             if(p.get("url") == null){
                 JSONObject respuesta=respuesta(true, "Tarea no entregada");
                respuesta.put("json", json);        
                respuesta.put("url", p.get("url"));
                 return respuesta;
             }
            JSONObject respuesta=respuesta(true, "Tarea entregada");
            respuesta.put("json", json);        
            respuesta.put("url", p.get("url"));
             return respuesta;
        }
        //return "Login";
    }
     @RequestMapping(value = "/API/consulta/alumnos", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_consulta_alumnos(@RequestBody JSONObject json) throws ParseException, IOException {
        
        System.out.println(json);
        
        String query=null;
                
        query="select c.id_grupo,g.nombre_grupo, c.id_usuario from cursos.registro_alumno c \n" +
        "inner join  cursos.grupo g on c.id_grupo = g.id_grupo\n" +
        "where c.id_grupo = "+json.get("id_grupo")+";";
        
        JSONArray alumnos = new JSONArray();
        alumnos = Query.selectArray(query);
        
        JSONObject respuesta=respuesta(true, "Alumnos inscritos");
        respuesta.put("json", json); 
        respuesta.put("alumnos", alumnos); 
        
        return respuesta;
        
        //return "Login";
    }
     @RequestMapping(value = "/API/consulta/calificacion_alumnos", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_consulta_calificacion_alumnos(@RequestBody JSONObject json) throws ParseException, IOException {
        
        System.out.println(json);
        
        String query=null;
                
        query="select c.id_grupo,g.nombre_grupo, c.id_usuario from cursos.registro_alumno c \n" +
        "inner join  cursos.grupo g on c.id_grupo = g.id_grupo\n" +
        "where c.id_grupo = "+json.get("id_grupo")+";";
        
        query = "select c.id_grupo,g.nombre_grupo, c.id_usuario, h.id_materia, m.nombre_materia  from cursos.registro_alumno c \n" +
                "inner join  cursos.grupo_horario h on c.id_grupo = h.id_grupo\n" +
                "inner join  cursos.materia m on h.id_materia = m.id_materia\n" +
                "inner join  cursos.grupo g on c.id_grupo = g.id_grupo\n" +
                "where c.id_grupo = "+json.get("id_grupo")+" and h.id_materia = "+json.get("id_materia")+";";
        
        JSONArray alumnos = new JSONArray();
        alumnos = Query.selectArray(query);
        
        JSONObject respuesta=respuesta(true, "Alumnos inscritos");
        respuesta.put("json", json); 
        respuesta.put("alumnos", alumnos); 
        
        return respuesta;
        
        //return "Login";
    }
    
     @RequestMapping(value = "/API/registra/calificacion_alumnos", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    private JSONObject api_registra_calificacion_alumnos(@RequestBody JSONObject json) throws ParseException, IOException {
        
        System.out.println(json);
        
        String query=null;
                
       query="select * from calificacion_alumno where id_materia = "+json.get("id_materia")+" and id_grupo = "+json.get("id_grupo")+" and id_usuario = "+json.get("id_usuario")+";";
        
       JSONArray calificacion = new JSONArray();
        calificacion = Query.selectArray(query);
        if(calificacion == null){
            query ="INSERT INTO calificacion_alumno (id_usuario, id_grupo, id_materia, calificacion) "
                    + "VALUES ('"+json.get("id_usuario")+"', '"+json.get("id_grupo")+"', '"+json.get("id_materia")+"', '"+json.get("calificacion")+"');";
        }else{
            query="UPDATE calificacion_alumno SET calificacion = '"+json.get("calificacion")+"' WHERE id_materia = "+json.get("id_materia")+" and id_grupo = "+json.get("id_grupo")+" and id_usuario = "+json.get("id_usuario")+";";
        }
        
        JSONObject respuesta=respuesta(true, "Alumno Calificado");
        respuesta.put("json", json); 
        //respuesta.put("alumnos", alumnos); 
        
        return respuesta;
        
        //return "Login";
    }
}
