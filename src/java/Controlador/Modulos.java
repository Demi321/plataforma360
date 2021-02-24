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

public class Modulos {

    private JSONObject respuesta(boolean success, String message) {
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", success);
        respuesta.put("failure", !success);
        respuesta.put("mensaje", message);
        return respuesta;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String home(HttpServletRequest sesion, Model model) throws ParseException, IOException, java.text.ParseException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            //model.addAttribute("title", config.getAliasServicio() + " - " + config.getPersonalizacion().get("t1"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            System.out.println(config.getServer().get("recursos"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        JSONObject the_sesion = Revision.getSesion(sesion);
        //the_sesion = new JSONObject();
        if (the_sesion == null) {
            return "login/Login";
        } else {

            JSONParser parser = new JSONParser();
            JSONObject id360 = new JSONObject();
            id360.put("id360", the_sesion.get("id_usuario"));

            //Carga de perfil de usuario 
            JSONObject perfil = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfil", id360);

            JSONObject empresa = new JSONObject();
            JSONArray sucursales = new JSONArray();
            JSONArray directorio = new JSONArray();

            //cargar informacion de la empresa si es que se tiene 
            if (the_sesion.get("tipo_usuario") != null && the_sesion.get("tipo_usuario") != "") {
                empresa = (JSONObject) parser.parse(request.GET(config.getPATH() + config.getDEPENDENCIA() + "/API/lineamientos/info_empresa/" + the_sesion.get("tipo_usuario")));

                //cargar informacion de las sucursales
                sucursales = (JSONArray) parser.parse(request.GET(config.getPATH() + config.getDEPENDENCIA() + "/API/lineamientos/listado_sucursales/" + the_sesion.get("tipo_usuario")));

                //Cargamos el directorio de la sucursal
                JSONObject info_dir = new JSONObject();
                info_dir.put("fecha", Query.getFecha());
                info_dir.put("hora", Query.getHora());
                info_dir.put("tipo_usuario", the_sesion.get("tipo_usuario"));
                info_dir.put("tipo_servicio", the_sesion.get("tipo_servicio"));
                info_dir.put("tipo_area", "0");
                directorio = (JSONArray) request.POST(config.getPATH() + config.getDEPENDENCIA() + "/API/ConsultarDirectorio", info_dir).get("directorio");

            }

//            JSONObject estatus_jornada  = (JSONObject)parser.parse(request.GET(config.gJSONObjectetPATH() + config.getDEPENDENCIA()+"/API/lineamientos/listado_sucursales/"+the_sesion.get("tipo_usuario")));
//            JSONObject estatus_jornada  = (JSONObject)parser.parse(request.GET(config.getPATH() + config.getDEPENDENCIA()+"_dev_moises/API/lineamientos/listado_sucursales/"+the_sesion.get("tipo_usuario")));
            JSONObject estatus_jornada = null;
            if (perfil.get("horario_salida") != null && perfil.get("horario_salida") != "") {
                estatus_jornada = empresas360_get_status_horario_laboral(id360.get("id360").toString(), perfil.get("horario_salida").toString());
            } else {
                estatus_jornada = empresas360_get_status_horario_laboral(id360.get("id360").toString(), "");
            }

            //cargar informacion sobre los reportes
            JSONObject reportes_realizados = empresas360_get_ids_reportes(id360);

            model.addAttribute("perfil_usuario", perfil);
            model.addAttribute("empresa_usuario", empresa);
            model.addAttribute("sucursales_usuario", sucursales);
            model.addAttribute("directorio_usuario", directorio);
            model.addAttribute("jornada_usuario", estatus_jornada);
            model.addAttribute("reportes_usuario", reportes_realizados);

            //recuperar los menus del usuario 
            JSONObject solicitud_menus = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/get/menu", id360);
//            JSONObject solicitud_menus = (JSONObject) parser.parse("{  \"success\": true,  \"failure\": false,  \"mensaje\": \"Menus escontrados.\",  \"menus\": [    {      \"icono\": \"<i class=\\\"fas fa-house-user\\\"></i>\",      \"id_menu\": \"39\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": null,      \"nombre\": \"Home Empleado\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Home\",      \"id\": \"39\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"108\"    },    {      \"icono\": \"<i class=\\\"fas fa-user-circle\\\"></i>\",      \"id_menu\": \"1\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Trabajo\",      \"nombre\": \"Mi Perfil\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Mi Perfil\",      \"id\": \"1\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"100\"    },    {      \"icono\": \"<i class=\\\"fas fa-id-card-alt\\\"></i>\",      \"id_menu\": \"4\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Trabajo\",      \"nombre\": \"Entrada y Salida\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Entrada y Salida\",      \"id\": \"4\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"103\"    },        {      \"icono\": \"<i class=\\\"fas fa-clipboard-list\\\"></i>\",      \"id_menu\": \"40\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Reportes\",      \"nombre\": \"Reporte Sintomas\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Reporte de Sintomas\",      \"id\": \"40\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"109\"    },    {      \"icono\": \"<i class=\\\"fas fa-clipboard-list\\\"></i>\",      \"id_menu\": \"41\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Reportes\",      \"nombre\": \"Reporte Proteccion Personal\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Reporte de Equipo de Proteccion Personal\",      \"id\": \"41\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"110\"    },    {      \"icono\": \"<i class=\\\"fas fa-clipboard-list\\\"></i>\",      \"id_menu\": \"42\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Reportes\",      \"nombre\": \"Reporte Actividades\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Reporte de Actividades\",      \"id\": \"42\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"111\"    },    {      \"icono\": \"<i class=\\\"fas fa-clipboard-list\\\"></i>\",      \"id_menu\": \"45\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Reportes\",      \"nombre\": \"Reporte Evento Incidente\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Reporte de Eventos o Incedentes\",      \"id\": \"45\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"112\"    },    {      \"icono\": \"<i class=\\\"fas fa-clipboard-list\\\"></i>\",      \"id_menu\": \"46\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Reportes\",      \"nombre\": \"Cuestionario Tamizaje\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Cuestionario de Tamizaje\",      \"id\": \"46\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"113\"    },    {      \"icono\": \"<i class=\\\"fas fa-clipboard-list\\\"></i>\",      \"id_menu\": \"51\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Reportes\",      \"nombre\": \"Reporte Seguridad Sanitaria\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Reporte de de Seguridad Sanitaria\",      \"id\": \"51\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"114\"    },    {      \"icono\": \"<i class=\\\"fas fa-comments\\\"></i>\",      \"id_menu\": \"5\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Trabajo\",      \"nombre\": \"Comunicación\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"0\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Comunicación\",      \"id\": \"39\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"104\"    }  ]}");
            if ((Boolean) solicitud_menus.get("success")) {

                JSONArray menus = (JSONArray) solicitud_menus.get("menus");
                model.addAttribute("modulos", "");
                for (int i = 0; i < menus.size(); i++) {
                    JSONObject menu = (JSONObject) menus.get(i);

                    if (menu.get("url").toString().equals(config.getPATH() + config.getDEPENDENCIA() + "/")) {
                        //solicitar vista
                        menu.remove("url");
                        model.addAttribute("modulos", model.asMap().get("modulos") + request.POST_vista(config.getPATH() + "plataforma360_dev_moises/" + Normalizer.normalize(menu.get("nombre").toString(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replace(" ", "").toLowerCase(), menu.toString()));

                        //System.out.println(Normalizer.normalize(menu.get("nombre").toString(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replace(" ", "").toLowerCase());
                    } else {
                        //agregar menu externo 
                        model.addAttribute("modulos", model.asMap().get("modulos") + request.POST_vista(config.getPATH() + "plataforma360_dev_moises/modulo_vacio", menu.toString()));

                    }

                }
                //return solicitud_menus.toString();

                //JSONObject modulo = new JSONObject();
                //model.addAttribute("modulo_0", request.POST("https://empresas.claro360.com/plataforma360_dev_moises/Mi Perfil", modulo.toString()));
                return "Home/home";
            }
            //El usuario no tiene menus generados posiblemente necesite volver a iniciar sesion para generarlos
            //Eliminando sesion 
            Revision.deleteSesion(sesion);
            return "login/Login";

        }

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = {"/dashboard_dev"}, method = RequestMethod.GET)
    public String dashboard_dev(HttpServletRequest sesion, Model model) throws ParseException, IOException, java.text.ParseException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            //model.addAttribute("title", config.getAliasServicio() + " - " + config.getPersonalizacion().get("t1"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            System.out.println(config.getServer().get("recursos"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        JSONObject the_sesion = Revision.getSesion(sesion);
        //the_sesion = new JSONObject();
        if (the_sesion == null) {
            return "login/Login";
        } else {

            JSONParser parser = new JSONParser();
            JSONObject id360 = new JSONObject();
            id360.put("id360", the_sesion.get("id_usuario"));

            //Carga de perfil de usuario 
            JSONObject perfil = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfil", id360);

            //cargar informacion de la empresa
            JSONObject empresa = (JSONObject) parser.parse(request.GET(config.getPATH() + config.getDEPENDENCIA() + "/API/lineamientos/info_empresa/" + the_sesion.get("tipo_usuario")));

            //cargar informacion de las sucursales
            JSONArray sucursales = (JSONArray) parser.parse(request.GET(config.getPATH() + config.getDEPENDENCIA() + "/API/lineamientos/listado_sucursales/" + the_sesion.get("tipo_usuario")));

            //cargar informacion de las sucursales
//            JSONObject estatus_jornada  = (JSONObject)parser.parse(request.GET(config.gJSONObjectetPATH() + config.getDEPENDENCIA()+"/API/lineamientos/listado_sucursales/"+the_sesion.get("tipo_usuario")));
//            JSONObject estatus_jornada  = (JSONObject)parser.parse(request.GET(config.getPATH() + config.getDEPENDENCIA()+"_dev_moises/API/lineamientos/listado_sucursales/"+the_sesion.get("tipo_usuario")));
            JSONObject estatus_jornada = null;
            if (perfil.get("horario_salida") != null && perfil.get("horario_salida") != "") {
                estatus_jornada = empresas360_get_status_horario_laboral(id360.get("id360").toString(), perfil.get("horario_salida").toString());
            } else {
                estatus_jornada = empresas360_get_status_horario_laboral(id360.get("id360").toString(), "");
            }

            //cargar informacion sobre los reportes
            JSONObject reportes_realizados = empresas360_get_ids_reportes(id360);

            //Cargamos el directorio de la sucursal
            JSONObject info_dir = new JSONObject();
            info_dir.put("fecha", Query.getFecha());
            info_dir.put("hora", Query.getHora());
            info_dir.put("tipo_usuario", the_sesion.get("tipo_usuario"));
            info_dir.put("tipo_servicio", the_sesion.get("tipo_servicio"));
            info_dir.put("tipo_area", "0");
            JSONArray directorio = (JSONArray) request.POST(config.getPATH() + config.getDEPENDENCIA() + "/API/ConsultarDirectorio", info_dir).get("directorio");

            model.addAttribute("perfil_usuario", perfil);
            model.addAttribute("empresa_usuario", empresa);
            model.addAttribute("sucursales_usuario", sucursales);
            model.addAttribute("directorio_usuario", directorio);
            model.addAttribute("jornada_usuario", estatus_jornada);
            model.addAttribute("reportes_usuario", reportes_realizados);

            //recuperar los menus del usuario 
            JSONObject solicitud_menus = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/get/menu/dev", id360);
//            JSONObject solicitud_menus = (JSONObject) parser.parse("{  \"success\": true,  \"failure\": false,  \"mensaje\": \"Menus escontrados.\",  \"menus\": [    {      \"icono\": \"<i class=\\\"fas fa-house-user\\\"></i>\",      \"id_menu\": \"39\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": null,      \"nombre\": \"Home Empleado\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Home\",      \"id\": \"39\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"108\"    },    {      \"icono\": \"<i class=\\\"fas fa-user-circle\\\"></i>\",      \"id_menu\": \"1\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Trabajo\",      \"nombre\": \"Mi Perfil\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Mi Perfil\",      \"id\": \"1\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"100\"    },    {      \"icono\": \"<i class=\\\"fas fa-id-card-alt\\\"></i>\",      \"id_menu\": \"4\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Trabajo\",      \"nombre\": \"Entrada y Salida\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Entrada y Salida\",      \"id\": \"4\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"103\"    },        {      \"icono\": \"<i class=\\\"fas fa-clipboard-list\\\"></i>\",      \"id_menu\": \"40\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Reportes\",      \"nombre\": \"Reporte Sintomas\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Reporte de Sintomas\",      \"id\": \"40\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"109\"    },    {      \"icono\": \"<i class=\\\"fas fa-clipboard-list\\\"></i>\",      \"id_menu\": \"41\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Reportes\",      \"nombre\": \"Reporte Proteccion Personal\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Reporte de Equipo de Proteccion Personal\",      \"id\": \"41\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"110\"    },    {      \"icono\": \"<i class=\\\"fas fa-clipboard-list\\\"></i>\",      \"id_menu\": \"42\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Reportes\",      \"nombre\": \"Reporte Actividades\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Reporte de Actividades\",      \"id\": \"42\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"111\"    },    {      \"icono\": \"<i class=\\\"fas fa-clipboard-list\\\"></i>\",      \"id_menu\": \"45\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Reportes\",      \"nombre\": \"Reporte Evento Incidente\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Reporte de Eventos o Incedentes\",      \"id\": \"45\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"112\"    },    {      \"icono\": \"<i class=\\\"fas fa-clipboard-list\\\"></i>\",      \"id_menu\": \"46\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Reportes\",      \"nombre\": \"Cuestionario Tamizaje\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Cuestionario de Tamizaje\",      \"id\": \"46\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"113\"    },    {      \"icono\": \"<i class=\\\"fas fa-clipboard-list\\\"></i>\",      \"id_menu\": \"51\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Reportes\",      \"nombre\": \"Reporte Seguridad Sanitaria\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"463\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Reporte de de Seguridad Sanitaria\",      \"id\": \"51\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"114\"    },    {      \"icono\": \"<i class=\\\"fas fa-comments\\\"></i>\",      \"id_menu\": \"5\",      \"date_updated\": null,      \"visible\": \"1\",      \"date_created\": \"2021-01-15\",      \"categoria\": \"Trabajo\",      \"nombre\": \"Comunicación\",      \"time_updated\": null,      \"url\": \"https://empresas.claro360.com/plataforma360/\",      \"tipo_area\": \"0\",      \"id_usuario\": \"9991336774\",      \"time_created\": \"18:13:11\",      \"alias\": \"Comunicación\",      \"id\": \"39\",      \"orden\": null,      \"tipo_usuario\": \"124\",      \"tipo_servicio\": \"3064\",      \"activo\": \"1\",      \"order\": \"104\"    }  ]}");
            if ((Boolean) solicitud_menus.get("success")) {

                JSONArray menus = (JSONArray) solicitud_menus.get("menus");
                model.addAttribute("modulos", "");
                for (int i = 0; i < menus.size(); i++) {
                    JSONObject menu = (JSONObject) menus.get(i);

                    if (menu.get("url").toString().equals(config.getPATH() + config.getDEPENDENCIA() + "/")) {
                        //solicitar vista
                        model.addAttribute("modulos", model.asMap().get("modulos") + request.POST_vista(config.getPATH() + "plataforma360_dev_moises/" + Normalizer.normalize(menu.get("nombre").toString(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replace(" ", "").toLowerCase(), menu.toString()));

                        //System.out.println(Normalizer.normalize(menu.get("nombre").toString(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replace(" ", "").toLowerCase());
                    } else {
                        //agregar menu externo 
                        model.addAttribute("modulos", model.asMap().get("modulos") + request.POST_vista(config.getPATH() + "plataforma360_dev_moises/modulo_vacio", menu.toString()));

                    }

                }
                //return solicitud_menus.toString();

                //JSONObject modulo = new JSONObject();
                //model.addAttribute("modulo_0", request.POST("https://empresas.claro360.com/plataforma360_dev_moises/Mi Perfil", modulo.toString()));
                return "Home/home";
            }
            //El usuario no tiene menus generados posiblemente necesite volver a iniciar sesion para generarlos
            //Eliminando sesion 
            Revision.deleteSesion(sesion);
            return "login/Login";

        }

    }

    @RequestMapping(value = "/modulo_vacio", method = RequestMethod.POST)
    public String modulo_vacio(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

        }

        model.addAttribute("json", json);

//        model.addAttribute("id_menu", json.get("id_menu"));
//        model.addAttribute("id_usuario", json.get("id_usuario"));
//        model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//        model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//        model.addAttribute("tipo_area", json.get("tipo_area"));
//        model.addAttribute("alias", json.get("alias"));
//        model.addAttribute("icono", json.get("icono"));
//        model.addAttribute("categoria", json.get("categoria"));
//        model.addAttribute("nombre", json.get("nombre"));
        model.addAttribute("id", json.get("id"));
//        model.addAttribute("icono_categoria", json.get("icono_categoria"));
//        model.addAttribute("url", json.get("url"));
        return "Home/modulo_vacio";
    }

    @RequestMapping(value = "/miperfil", method = RequestMethod.POST)
    public String miperfil(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            
            model.addAttribute("json", json);

//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        System.out.println(config.getServer().get("recursos"));
        return "empresa_empleado/perfil";
    }

    @RequestMapping(value = "/nuevoreporte", method = RequestMethod.POST)
    public String nuevoreporte(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            
            model.addAttribute("json", json);

//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "Home/modulo_reporte";
    }

    @RequestMapping(value = "/reporteseguridadsanitaria", method = RequestMethod.POST)
    public String reporteseguridadsanitaria(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            
            model.addAttribute("json", json);

//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa_empleado/reporte_seguridad_sanitaria";
    }

    @RequestMapping(value = "/entradaysalida", method = RequestMethod.POST)
    public String entradaysalida(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            
            model.addAttribute("json", json);

//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa_empleado/entrada_salida";
    }

//    @RequestMapping(value = "/comunicacion", method = RequestMethod.POST)
//    public String comunicacion(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
//        if (config.getInit() != null) {
//            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
//            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
//            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
//            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
//
//            model.addAttribute("id_menu", json.get("id_menu"));
//                model.addAttribute("id_usuario", json.get("id_usuario"));
//                model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//                model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//                model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
//            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
//        }
//        return "Home/modulo_comunicacion";
//    }
    @RequestMapping(value = "/registrarempresa", method = RequestMethod.POST)
    public String registrarempresa(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            
            model.addAttribute("json", json);

//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/registrarempresa";
    }

    @RequestMapping(value = "/notas", method = RequestMethod.POST)
    public String notas(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            
            model.addAttribute("json", json);

//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/notas";
    }

    @RequestMapping(value = "/recordatorios", method = RequestMethod.POST)
    public String recordatorios(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            
            model.addAttribute("json", json);

//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/recordatorios";
    }

    @RequestMapping(value = "/agenda", method = RequestMethod.POST)
    public String agenda(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            
            model.addAttribute("json", json);

//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/agenda";
    }

    @RequestMapping(value = "/misucursal", method = RequestMethod.POST)
    public String misucursal(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/misucursal";
    }

    @RequestMapping(value = "/areasdetrabajo", method = RequestMethod.POST)
    public String areasdetrabajo(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/areasdetrabajo";
    }

    @RequestMapping(value = "/plantillalaboral", method = RequestMethod.POST)
    public String plantillalaboral(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/plantillalaboral";
    }

    @RequestMapping(value = "/reportejornadaslaborales", method = RequestMethod.POST)
    public String reportejornadaslaborales(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            
            model.addAttribute("json", json);

//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/reportejornadaslaborales";
    }

    @RequestMapping(value = "/monitoreodeempleados", method = RequestMethod.POST)
    public String monitoreodeempleados(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/monitoreodeempleados";
    }

    @RequestMapping(value = "/videowallempleados", method = RequestMethod.POST)
    public String videowallempleados(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/videowallempleados";
    }

    @RequestMapping(value = "/miempresa", method = RequestMethod.POST)
    public String miempresa(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/miempresa";
    }

    @RequestMapping(value = "/registrarsucursal", method = RequestMethod.POST)
    public String registrarsucursal(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/registrarsucursal";
    }

    @RequestMapping(value = "/missucursales", method = RequestMethod.POST)
    public String missucursales(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            
            model.addAttribute("json", json);

//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/missucursales";
    }

    @RequestMapping(value = "/homeempleado", method = RequestMethod.POST)
    public String homeempleado(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            
            model.addAttribute("json", json);

//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa_empleado/home_empleado";
    }

    @RequestMapping(value = "/reportesintomas", method = RequestMethod.POST)
    public String reportesintomas(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            
            model.addAttribute("json", json);

//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa_empleado/reporte_sintomas";
    }

    @RequestMapping(value = "/reporteproteccionpersonal", method = RequestMethod.POST)
    public String reporteproteccionpersonal(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            
            model.addAttribute("json", json);

//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa_empleado/reporte_proteccion_personal";
    }

    @RequestMapping(value = "/reporteactividades", method = RequestMethod.POST)
    public String reporteactividades(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            
            model.addAttribute("json", json);

//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa_empleado/reporte_actividades";
    }

    @RequestMapping(value = "/reporteeventoincidente", method = RequestMethod.POST)
    public String reporteeventoincidente(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            
            model.addAttribute("json", json);

//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa_empleado/reporte_evento_incidente";
    }

    @RequestMapping(value = "/cuestionariotamizaje", method = RequestMethod.POST)
    public String cuestionariotamizaje(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            
            model.addAttribute("json", json);

//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa_empleado/cuestionario_tamizaje";
    }

    @RequestMapping(value = "/comunicacion", method = RequestMethod.POST)
    public String comunicacion(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/comunicacion";
    }

    /**
     * ******************
     */
    @RequestMapping(value = "/panelfinanciero", method = RequestMethod.POST)
    public String dashboard(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            
            model.addAttribute("json", json);

//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/panelfinanciero";
    }

    //cambiar url duda
    @RequestMapping(value = "/estadisticaglobal", method = RequestMethod.POST)
    public String estadisticaglobal(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/estadisticaglobal";
    }

    @RequestMapping(value = "/saludeneltrabajo", method = RequestMethod.POST)
    public String saludeneltrabajo(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/saludeneltrabajo";
    }

    //cambiar url
    @RequestMapping(value = "/recursoshumanos", method = RequestMethod.POST)
    public String recursoshumanos(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/recursoshumanos";
    }

    @RequestMapping(value = "/archivo", method = RequestMethod.POST)
    public String archivo(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/archivo";
    }

    @RequestMapping(value = "/misreportes", method = RequestMethod.POST)
    public String misreportes(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/misreportes";
    }

    @RequestMapping(value = "/usodecubrebocas", method = RequestMethod.POST)
    public String usodecubrebocas(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/usodecubrebocas";
    }

    @RequestMapping(value = "/balanza", method = RequestMethod.POST)
    public String balanza(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/balanza";
    }

    @RequestMapping(value = "/contabilidad", method = RequestMethod.POST)
    public String contabilidad(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/contabilidad";
    }

    @RequestMapping(value = "/planeacionfinanciera", method = RequestMethod.POST)
    public String planeacionfinanciera(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/planeacionfinanciera";
    }

    @RequestMapping(value = "/impuestos", method = RequestMethod.POST)
    public String impuestos(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/impuestos";
    }

    //cambiar url
    @RequestMapping(value = "/administracionyfinanzas", method = RequestMethod.POST)
    public String administracionyfinanzas(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/administracionyfinanzas";
    }

    @RequestMapping(value = "/mapa", method = RequestMethod.POST)
    public String mapa(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/mapa";
    }

    @RequestMapping(value = "/historialdellamadas", method = RequestMethod.POST)
    public String historialdellamadas(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/historialdellamadas";
    }

    @RequestMapping(value = "/operaciones", method = RequestMethod.POST)
    public String operaciones(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/operaciones";
    }

    @RequestMapping(value = "/paneldatosrrhh", method = RequestMethod.POST)
    public String paneldatosrrhh(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/paneldatosrrhh";
    }

    @RequestMapping(value = "/protocolodevacunacioncovid", method = RequestMethod.POST)
    public String protocolodevacunacioncovid(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/protocolodevacunacioncovid";
    }

    @RequestMapping(value = "/invitacionempresarial", method = RequestMethod.POST)
    public String invitacionempresarial(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/invitacionempresarial";
    }

    @RequestMapping(value = "/perfildesalud", method = RequestMethod.POST)
    public String perfildesalud(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/perfildesalud";
    }

    @RequestMapping(value = "/perfillaboral", method = RequestMethod.POST)
    public String perfillaboral(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            model.addAttribute("json", json);
            
//            model.addAttribute("id_menu", json.get("id_menu"));
//            model.addAttribute("id_usuario", json.get("id_usuario"));
//            model.addAttribute("tipo_usuario", json.get("tipo_usuario"));
//            model.addAttribute("tipo_servicio", json.get("tipo_servicio"));
//            model.addAttribute("tipo_area", json.get("tipo_area"));
//            model.addAttribute("alias", json.get("alias"));
//            model.addAttribute("icono", json.get("icono"));
//            model.addAttribute("categoria", json.get("categoria"));
//            model.addAttribute("nombre", json.get("nombre"));
            model.addAttribute("id", json.get("id"));
//            model.addAttribute("icono_categoria", json.get("icono_categoria"));
        }
        return "empresa/perfillaboral";
    }

    /**
     * ******************
     */
    /**
     * ************************Servicios *************************
     */
    @RequestMapping(value = "/API/empresas360/rendimiento_laboral", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject empresas360_rendimiento_laboral(@RequestBody String jsonObj) throws IOException, ParseException, java.text.ParseException {
        System.out.println("empresas360_rendimiento_laboral");
        /* JSON de Entrada
        {
            "ids":[{"id360":""},{"id360":""},...{"id360":""}],
            "fecha_inicio_semana":"",
            "fecha_actual":"",
        }
         */
 /*
        {
            "mensaje":"",
            "success":"",//true o flase
            "failure":"",//true o false
            "info":{
                    "99974523":{
                                "2021-01-04":{
                                                "retardo":"", //true o false
                                                "falta":"", //true o false
                                                "horas_trabajadas":
                                             },
                                "total_retardos": 2,
                                "total_faltas": 0,
                                "total_horas_trabajadas": 163.0,
                                "total_conexiones": 18
                               },...{}
                    }
        }
         */
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonObj);
        JSONObject respuesta = respuesta(false, "Algo ocurrio, intentelo de nuevo.");
        JSONArray ids = (JSONArray) json.get("ids");
        JSONArray perfiles = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfiles/array", ids);
        boolean success = false;
        JSONObject info = new JSONObject();
        for (int k = 0; k < perfiles.size(); k++) {
            JSONObject perfil = (JSONObject) perfiles.get(k);
            if (((Boolean) perfil.get("success"))) {
                success = true;
                //Creamos el json que contendra la info de nuestro usuario
                JSONObject usr = new JSONObject();
                /**
                 * ******************************
                 */
                String query = "SELECT * FROM registro_jornada_laboral WHERE id_usuario = '" + perfil.get("id360") + "' "
                        + "AND (date_created >= '" + json.get("fecha_inicio_semana") + "' AND date_created <= '" + json.get("fecha_actual") + "');";
                JSONArray registro_jornada_laboral = Query.execute(query);
                //Agregamos el numero de conexiones
                usr.put("total_conexiones", registro_jornada_laboral.size());
                //Calculamos el total de horas trabajadas
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha1 = null;
                Date fecha2 = null;
                double total_horas_trabajadas = 0.0;
                int total_retardos = 0;
                int total_faltas = 0;
                for (int i = 0; i < registro_jornada_laboral.size(); i++) {
                    JSONObject registro = (JSONObject) registro_jornada_laboral.get(i);
                    // Creamos el json que contendra la info de cada una de las fechas
                    JSONObject fecha_diaria = new JSONObject();
                    double total_horas = 0.0;
                    /**
                     * **********************************
                     */
                    fecha1 = dateFormat.parse(registro.get("date_created").toString());
                    fecha2 = dateFormat.parse(Query.getFecha());
                    DateFormat format = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
                    Date d1 = null;
                    Date d2 = null;
                    long diferencia = 0;
                    long minutos = 0;
                    if (!fecha1.equals(fecha2)) {
                        //Es una fecha anterior al dia de hoy
                        if (registro.get("time_updated") != null) {
                            //calculamos las horas entre el inicio y el fin
                            d1 = format.parse(registro.get("date_created") + " " + registro.get("time_created"));
                            d2 = format.parse(registro.get("date_updated") + " " + registro.get("time_updated"));
                            diferencia = d1.getTime() - d2.getTime();
                            minutos += Math.abs(TimeUnit.MILLISECONDS.toMinutes(diferencia));
                            //Obtenemos los descansos
                            JSONArray descansos = Query.execute("SELECT * FROM registro_descanso_jornada_laboral WHERE id360 = '" + perfil.get("id360") + "' AND date_created = '" + registro.get("date_created") + "';");
                            for (int j = 0; j < descansos.size(); j++) {
                                JSONObject descanso = (JSONObject) descansos.get(j);
                                d1 = format.parse(descanso.get("fecha_pausa") + " " + descanso.get("hora_pausa"));
                                if (descanso.get("hora_reinicio") != null) {
                                    d2 = format.parse(descanso.get("fecha_reinicio") + " " + descanso.get("hora_reinicio"));
                                    diferencia = d1.getTime() - d2.getTime();
                                    minutos -= Math.abs(TimeUnit.MILLISECONDS.toMinutes(diferencia));
                                }//Se debe hacer algo en el caso de que no se encuentre una hora de reinicio de dias anteriores???
                            }
                            //convertimos los minutos a horas
                            double horas = minutos / 60;
                            total_horas = horas;
                            total_horas_trabajadas += Double.parseDouble(String.format("%.2f", horas));
                        } else {
                            //si no tiene el time_updated que marca la hora de finalizacion de jornada se usara la parte de los descansos
                            JSONArray descansos = Query.execute("SELECT * FROM registro_descanso_jornada_laboral WHERE id360 = '" + perfil.get("id360") + "' AND date_created = '" + registro.get("date_created") + "';");
                            for (int j = 0; j < descansos.size(); j++) {
                                JSONObject descanso = (JSONObject) descansos.get(j);
                                if (j == 0) {
                                    d1 = format.parse(registro.get("date_created") + " " + registro.get("time_created"));
                                    d2 = format.parse(descanso.get("fecha_pausa") + " " + descanso.get("hora_pausa"));
                                    diferencia = d1.getTime() - d2.getTime();
                                    minutos += Math.abs(TimeUnit.MILLISECONDS.toMinutes(diferencia));
                                } else {
                                    d1 = format.parse(descanso.get("fecha_pausa") + " " + descanso.get("hora_pausa"));
                                    if (descanso.get("hora_reinicio") != null) {
                                        d2 = format.parse(descanso.get("fecha_reinicio") + " " + descanso.get("hora_reinicio"));
                                        diferencia = d1.getTime() - d2.getTime();
                                        minutos -= Math.abs(TimeUnit.MILLISECONDS.toMinutes(diferencia));
                                    }//Se debe hacer algo en el caso de que no se encuentre una hora de reinicio de dias anteriores???
                                }
                            }
                            //convertimos los minutos a horas
                            double horas = minutos / 60;
                            total_horas = horas;
                            total_horas_trabajadas += Double.parseDouble(String.format("%.2f", horas));
                        }
                    } else {
                        //calculamos las horas entre el inicio y el fin
                        d1 = format.parse(registro.get("date_created") + " " + registro.get("time_created"));
                        d2 = format.parse(Query.getFecha() + " " + Query.getHora());
                        diferencia = d1.getTime() - d2.getTime();
                        minutos += Math.abs(TimeUnit.MILLISECONDS.toMinutes(diferencia));
                        //Obtenemos los descansos
                        JSONArray descansos = Query.execute("SELECT * FROM registro_descanso_jornada_laboral WHERE id360 = '" + perfil.get("id360") + "' AND date_created = '" + registro.get("date_created") + "';");
                        for (int j = 0; j < descansos.size(); j++) {
                            JSONObject descanso = (JSONObject) descansos.get(j);
                            d1 = format.parse(descanso.get("fecha_pausa") + " " + descanso.get("hora_pausa"));
                            if (descanso.get("hora_reinicio") != null) {
                                d2 = format.parse(descanso.get("fecha_reinicio") + " " + descanso.get("hora_reinicio"));
                                diferencia = d1.getTime() - d2.getTime();
                                minutos -= Math.abs(TimeUnit.MILLISECONDS.toMinutes(diferencia));
                            }//Se debe hacer algo en el caso de que no se encuentre una hora de reinicio de dias anteriores???
                        }
                        //convertimos los minutos a horas
                        double horas = minutos / 60;
                        total_horas = horas;
                        total_horas_trabajadas += Double.parseDouble(String.format("%.2f", horas));
                    }

                    //obtenemos el numero de retardos y faltas
                    //Fecha1 = fecha y hora del registro del usuario
                    fecha1 = format.parse(registro.get("date_created") + " " + registro.get("time_created"));
                    //Fecha2 = fecha y hora de la tolerancia del registro 
                    fecha2 = format.parse(registro.get("date_created") + " " + Query.getHora(perfil.get("horario_entrada").toString(), 15));
                    //Fecha3 = fecha y hora de entrada del usuario 
                    Date fecha3 = format.parse(registro.get("date_created") + " " + perfil.get("horario_entrada").toString());
                    Date fecha4 = format.parse(registro.get("date_created") + " " + perfil.get("horario_salida").toString());
                    // f1 = 19:20:01
                    // f2 = 09:15:00
                    // f3 = 09:00:00
                    // f4 = 19:00:00
                    if (!fecha1.before(fecha2) && fecha1.before(fecha4)) {
                        total_retardos += 1;
                    } else if (fecha1.after(fecha4)) {
                        total_faltas += 1;
                    }
                    //Agregamos las horas trabajadas en el dia y si tuvo una falta o un retardo

                    fecha_diaria.put("horas_trabajadas", total_horas);
                    fecha_diaria.put("retardo", false);
                    fecha_diaria.put("falta", false);
                    if (!fecha1.before(fecha2) && fecha1.before(fecha4)) {
                        fecha_diaria.put("retardo", true);
                    } else if (fecha1.after(fecha4)) {
                        fecha_diaria.put("falta", true);
                    }
                    usr.put(registro.get("date_created"), fecha_diaria);
                }
                usr.put("total_horas_trabajadas", total_horas_trabajadas);
                usr.put("total_retardos", total_retardos);
                usr.put("total_faltas", total_faltas);

                info.put(perfil.get("id360"), usr);
            }
        }
        if (success) {
            respuesta = respuesta(true, "Rendimiento laboral");
            respuesta.put("info", info);
        }

        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/registro/inicio_jornada_laboral", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject registro_inicio_jornada_laboral(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        System.out.println("registro_inicio_jornada_laboral: ");
        //el servicio se vorvera recurrente para evitar horas perdidas por el cierre incorrecto de la plataforma o usuarios que finalizan su jornada al dia siguiente
        /*
        -id360
        -id_usuario
        -tipo_usuario
        -tipo_servicio
        -tipo_area
        -apikey * moviles no
        -idsesion * moviles no
        -token * moviles no
        -id_socket * moviles no
        -activo
        -web *
        -app *
        -lat
        -lng
         */
        if (json.containsKey("id360")) {
            json.put("id_usuario", json.get("id360"));
        }
        JSONObject respuesta = respuesta(false, "No fue posible iniciar tu jornada. ¿Desea intentar de nuevo?");

        //revisar si ya existe un registro del dia 
        String query = "SELECT * FROM registro_jornada_laboral WHERE id_usuario='" + json.get("id_usuario") + "' AND date_created='" + Modelo.Escalamiento.getFecha() + "'";

        JSONObject registro = Query.select(query);
        int id;
        if (registro == null) {
            //Guardar informacion el la BD
            id = Query.insert(Query.createQueryInsertWithColumns("registro_jornada_laboral", json));
            if (id > 0) {
                respuesta = respuesta(true, "Jornada Iniciada");
            }
        } else {
            //Actualizar información
            id = Integer.parseInt(registro.get("id").toString());
            if(registro.get("contadorDesconexion")==null){
               registro.put("contadorDesconexion","2");//es 2 por que se supone que ya hay un registro mas el que se esta actualizando 
            }
            int contadorDesconexion = Integer.parseInt(registro.get("contadorDesconexion").toString());
            contadorDesconexion++;
            json.put("contadorDesconexion", contadorDesconexion);
            if (json.containsKey("app")) {
                json.remove("apikey");
                json.remove("idsesion");
                json.remove("token");
                json.remove("id_socket");
            }
            JSONObject where = new JSONObject();
            where.put("id", id);
            where.put("id_usuario", json.get("id_usuario"));

//            String queryUpdate = "UPDATE registro_jornada_laboral "
//                    + "SET tipo_area='" + json.get("tipo_area") + "', "
//                    + "apikey='" + json.get("apikey") + "', "
//                    + "id_usuario='" + json.get("id_usuario") + "', "
//                    + "id_socket='" + json.get("id_socket") + "', "
//                    + "tipo_usuario='" + json.get("tipo_usuario") + "', "
//                    + "tipo_servicio='" + json.get("tipo_servicio") + "', "
//                    + "idsesion='" + json.get("idsesion") + "', "
//                    + "token='" + json.get("token") + "', "
//                    + "activo='1', "
//                    + "date_updated='" + json.get("fecha") + "', "
//                    + "time_updated='" + json.get("hora") + "', "
//                    + "time_finished = NULL, "
//                    + "contadorDesconexion = contadorDesconexion+ " + json.get("aumentaConexion")
//                    + " WHERE ( id_usuario='" + json.get("id_usuario") + "' AND id='" + id + "' );";
            if (Query.update(Query.createQueryUpdateAND("registro_jornada_laboral", json, where))) {
                respuesta = respuesta(true, "Jornada Reanudada");
            }

        }
        if (json.containsKey("lat") && json.containsKey("lng")) {
            if (json.get("lat") != null && json.get("lng") != null) {
                ControladorPOST cp = new ControladorPOST();
                JSONObject gps = new JSONObject();
                gps.put("idUsuarios_Movil", json.get("id_usuario"));
                gps.put("fecha", Query.getFecha());
                gps.put("hora", Query.getHora());
                gps.put("latitud", json.get("lat"));
                gps.put("longitud", json.get("lng"));
                cp.gpsvehicular(gps);
            }
        }

        JSONObject reg = Query.select("SELECT * FROM registro_jornada_laboral WHERE id='" + id + "';");

        //Notificacion por socket del inicio de envio de video por tipo de usuario 
//        if (json.get("activo").toString().equals("1")) {
        reg.put("video_empleado", true);
        respuesta.putAll(reg);
        SocketEndPoint.EnviarNotificacio_jerarqia(reg, json.get("tipo_usuario").toString(), json.get("tipo_servicio").toString(), json.get("tipo_area").toString());
//        }

        // modificar el modulo de empleados para poner empleado en jornada
        JSONObject modulo_empleados = new JSONObject();
        modulo_empleados.put("id360", json.get("id_usuario"));
        modulo_empleados.put("modulo", "empleados");
        modulo_empleados.put("en_jornada", "1");

//        if (json.containsKey("reporte")) {
//            modulo_empleados.put("en_jornada", "0");
//        }
//        if (json.containsKey("web")) {
        respuesta.put("modulo_empleados", request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/registro_modulo", modulo_empleados));
        //Notificacion a aplicacion movil de que el usuario entra en jornada
        JSONObject notificacion_movil = new JSONObject();
        notificacion_movil.put("id360", json.get("id_usuario"));
        notificacion_movil.put("type", "300");
        notificacion_movil.put("en_jornada", "1");
//            if (json.containsKey("reporte")) {
//                notificacion_movil.put("en_jornada", "0");
//            }
        respuesta.put("notificacion_movil", request.POST(config.getURL_CONTROLADOR() + "API/moviles/notification/llamada_multiplataforma", notificacion_movil));
//        }
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/registro/horario_laboral_pausa", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject registro_horario_laboral_pausa(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("registro_horario_laboral_pausa");
        /*
        {
            "id360":"",
            "tipo_usuario":"",
            "tipo_servicio":"",
            "tipo_area":"",
            "hora_pausa":"", *
            "fecha_pausa":"", *
        -web*
        -app*
        }
         */
        json.put("hora_pausa", Query.getHora());
        json.put("fecha_pausa", Query.getFecha());

        JSONObject respuesta = respuesta(false, "No fue posible pausar tu jornada");
        int id_reg = Query.insert(Query.createQueryInsertWithColumns("registro_descanso_jornada_laboral", json));
        if (id_reg > 0) {
            respuesta = respuesta(true, "Jornada Pausada");
            respuesta.put("id_registro_descanso", id_reg);
            // modificar el modulo de empleados para quitar empleado en jornada
            JSONObject modulo_empleados = new JSONObject();
            modulo_empleados.put("id360", json.get("id360"));
            modulo_empleados.put("modulo", "empleados");
            modulo_empleados.put("en_jornada", "0");

//        if (json.containsKey("reporte")) {
//            modulo_empleados.put("en_jornada", "0");
//        }
//        if (json.containsKey("web")) {
            respuesta.put("modulo_empleados", request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/registro_modulo", modulo_empleados));
            //Notificacion a aplicacion movil de que el usuario sale de jornada
            JSONObject notificacion_movil = new JSONObject();
            notificacion_movil.put("id360", json.get("id360"));
            notificacion_movil.put("type", "300");
            notificacion_movil.put("en_jornada", "0");
//            if (json.containsKey("reporte")) {
//                notificacion_movil.put("en_jornada", "0");
//            }
            respuesta.put("notificacion_movil", request.POST(config.getURL_CONTROLADOR() + "API/moviles/notification/llamada_multiplataforma", notificacion_movil));
//        }
        }

        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/registro/horario_laboral_reinicio", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject registro_horario_laboral_reinicio(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        System.out.println("registro_horario_laboral_reinicio");
        /*
        {
            "hora_reinicio":"", *
            "fecha_reinicio":"", *
            "id_registro_descanso":"" ***
            -web
            -app
            -id360
        }
         */
        json.put("hora_reinicio", Query.getHora());
        json.put("fecha_reinicio", Query.getFecha());

        json.put("reinicio", 1);
        String query = "Select * from registro_descanso_jornada_laboral where id360='" + json.get("id360") + "' "
                + "and (date_created = '" + Query.getFecha() + "' OR date_created = '" + Query.getFecha(Query.getFecha(), -1) + "') "
                + "ORDER BY id DESC LIMIT 1;";
        JSONObject reg = Query.select(query);

        JSONObject respuesta = respuesta(false, "No fue posible reanudar tu jornada");
        if (reg != null) {
            JSONObject where = new JSONObject();
//            where.put("id", json.get("id_registro_descanso"));
            where.put("id", reg.get("id"));
            if (Query.update(Query.createQueryUpdateAND("registro_descanso_jornada_laboral", json, where))) {
                respuesta = respuesta(true, "Jornada Reanudada");
            }
        }

        //El proceso de notificacion y cambio de estatus de empleado en jornada se realizara al volver a registrar el inicio de jornada
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/registro/horario_laboral_finalizar", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject registro_horario_laboral_finalizar(@RequestBody JSONObject json) throws IOException, ParseException {
        System.out.println("registro_horario_laboral_pausa");
        /*
        {
            "id360":"",
            "tipo_usuario":"",
            "tipo_servicio":"",
            "tipo_area":"",
            "hora_pausa":"", *
            "fecha_pausa":"", *
        -web*
        -app*
        }
         */
        json.put("time_finished", Query.getHora());
        JSONObject respuesta = respuesta(false, "No fue posible finalizar tu jornada");

        //revisar si ya existe un registro del dia 
        String query = "SELECT * FROM registro_jornada_laboral WHERE id_usuario='" + json.get("id360") + "' AND date_created='" + Modelo.Escalamiento.getFecha() + "'";

        JSONObject registro = Query.select(query);
        int id;
        if (registro != null) {
            //Actualizar información
            id = Integer.parseInt(registro.get("id").toString());

            JSONObject where = new JSONObject();
            where.put("id", id);
            where.put("id_usuario", json.get("id360"));

            if (Query.update(Query.createQueryUpdateAND("registro_jornada_laboral", json, where))) {
                respuesta = respuesta(true, "Jornada Finalizada");

                // modificar el modulo de empleados para quitar empleado en jornada
                JSONObject modulo_empleados = new JSONObject();
                modulo_empleados.put("id360", json.get("id360"));
                modulo_empleados.put("modulo", "empleados");
                modulo_empleados.put("en_jornada", "0");

//        if (json.containsKey("reporte")) {
//            modulo_empleados.put("en_jornada", "0");
//        }
//        if (json.containsKey("web")) {
                respuesta.put("modulo_empleados", request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/registro_modulo", modulo_empleados));
                //Notificacion a aplicacion movil de que el usuario sale de jornada
                JSONObject notificacion_movil = new JSONObject();
                notificacion_movil.put("id360", json.get("id360"));
                notificacion_movil.put("type", "300");
                notificacion_movil.put("en_jornada", "0");
//            if (json.containsKey("reporte")) {
//                notificacion_movil.put("en_jornada", "0");
//            }
                respuesta.put("notificacion_movil", request.POST(config.getURL_CONTROLADOR() + "API/moviles/notification/llamada_multiplataforma", notificacion_movil));
//        }

            }

        }

        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/reporte_actividades", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject empresas360_reporte_actividadesa(@RequestBody JSONObject json) {
        System.out.println("empresas360_reporte_actividadesa");
        /*
        {
            "id360":"",
            "tipo_usuario":"",
            "tipo_servicio":"",
            "tipo_ares":"",
            "hora_entrada":"",
            "hora_guardado_reporte":"",
            "reporte":"",
            "url_archivo":""//No se si solo se podra subir una evidencia, o se puede subir mas, en caso de que se pueda subir mas se podria mandar un arreglo con las url's
        }
         */
        JSONObject respuesta = respuesta(false, "Ocurrió un error al enviar tu reporte de trabajo, inténtalo más tarde.");
        String query = "SELECT id FROM registro_jornada_laboral WHERE id_usuario = '" + json.get("id360") + "' AND id = '" + json.get("id_jornada") + "';";
        JSONObject id_reporte = Query.select(query);
        if (id_reporte == null) {
            //Crear registro
//            if (Query.insert(Query.createQueryInsertWithColumns("reporte_actividades", json)) > 0) {
            respuesta = respuesta(false, "Algo ocurrio, tu reporte no se guardo.");
//            }
        } else {
            //Actualizar el registro
            JSONObject where = new JSONObject();
            where.put("id", id_reporte.get("id"));
            if (Query.update(Query.createQueryUpdateAND("registro_jornada_laboral", json, where))) {
                respuesta = respuesta(true, "Tu reporte de trabajo se ha enviado correctamente.");
            }
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/cuestionario_tamizaje", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject empresas360_cuestionario_tamizaje(@RequestBody JSONObject json) {
        System.out.println("empresas360_cuestionario_tamizaje");
        JSONObject respuesta = respuesta(false, "Ocurrió un error.");
        if (Query.insert(Query.createQueryInsertWithColumns("cuestionario_tamizaje", json)) > 0) {
            respuesta = respuesta(true, "Tu cuestionario de tamizaje se ha enviado correctamente.");
        }

        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/get_ids_reportes", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject empresas360_get_ids_reportes(@RequestBody JSONObject json) throws IOException, ParseException, java.text.ParseException {
        System.out.println("empresas360_get_ids_reportes");
        JSONObject respuesta = new JSONObject();
        JSONObject reporte_trabajo = Query.select("SELECT * FROM registro_jornada_laboral WHERE "
                + "id_usuario = '" + json.get("id360") + "' AND "
                + "(date_created = '" + Query.getFecha() + "' "
                + "OR (date_created = '" + Query.getFecha(Query.getFecha(), -1) + "' )) ORDER BY id DESC LIMIT 1;");
        respuesta.put("has_reporte_trabajo", false);
        if (reporte_trabajo != null) {
            respuesta.put("has_reporte_trabajo", true);
            respuesta.put("reporte_trabajo", reporte_trabajo);
        }
        JSONObject reporte_sintomas = Query.select("SELECT * FROM reporte_sintomas WHERE id360 = '" + json.get("id360") + "' AND date_created = DATE(NOW());");
        respuesta.put("has_reporte_sintomas", false);
        if (reporte_sintomas != null) {
            respuesta.put("has_reporte_sintomas", true);
            respuesta.put("reporte_sintomas", reporte_sintomas);
        }
        JSONObject equipo_proteccion = Query.select("SELECT * FROM reporte_equipo_proteccion WHERE id360 = '" + json.get("id360") + "' AND date_created = DATE(NOW());");
        respuesta.put("has_equipo_proteccion", false);
        if (equipo_proteccion != null) {
            respuesta.put("has_equipo_proteccion", true);
            respuesta.put("equipo_proteccion", equipo_proteccion);
        }
        JSONObject reporte_seguridad_sanitaria = request.POST("https://seguridadsanitaria.claro360.com/lineamientos/API/centro_trabajo/get_reporte_evidencia", json);
        //JSONObject reporte_seguridad_sanitaria = null;
        respuesta.put("has_reporte_seguridad_sanitaria", false);
        if (reporte_seguridad_sanitaria != null) {
            respuesta.put("has_reporte_seguridad_sanitaria", true);
            respuesta.put("reporte_seguridad_sanitaria", reporte_seguridad_sanitaria);
        }

        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/tiempo_en_jornada/{id360}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject empresas360_tiempo_en_jornada(@PathVariable("id360") String id360) throws java.text.ParseException {
        System.out.println("empresas360_tiempo_en_jornada");
        JSONObject respuesta = respuesta(false, "El usuario no esta en jornada");
        String query = "SELECT date_created,time_created FROM registro_jornada_laboral WHERE id_usuario = '" + id360 + "' AND activo = 1 ORDER BY id DESC limit 1;";
        JSONObject time = Query.select(query);
        DateFormat format = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
        if (time != null) {
            respuesta = respuesta(true, "Tiempo en jornada");
            Date hora1 = format.parse(time.get("date_created").toString() + " " + time.get("time_created").toString());
            Date hora2 = format.parse(Query.getFecha() + " " + Query.getHora());
            int hr1 = hora1.getHours();
            int min1 = hora1.getMinutes();
            int seg1 = hora1.getSeconds();
            int hr2 = hora2.getHours();
            int min2 = hora2.getMinutes();
            int seg2 = hora2.getSeconds();
            int hora = hr2 - hr1;
            int min = min2 - min1;
            int seg = seg2 - seg1;
            if (seg < 0) {
                seg = seg + 60;
                min = min - 1;
            }
            if (min < 0) {
                min = min + 60;
                hora = hora - 1;
            }
            String h = "";
            String m = "";
            String s = "";
            if (hora < 10) {
                h = "0" + hora;
            } else {
                h = "" + hora;
            }
            if (min < 10) {
                m = "0" + min;
            } else {
                m = "" + min;
            }
            if (seg < 10) {
                s = "0" + seg;
            } else {
                s = "" + seg;
            }
            respuesta.put("tiempo_en_jornada", h + ":" + m + ":" + s);
        }
        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/get_status_horario_laboral/{id360}/{hora}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject empresas360_get_status_horario_laboral(@PathVariable("id360") String id360, @PathVariable("hora") String hora) throws java.text.ParseException {
        System.out.println("empresas360_get_status_horario_laboral");
        //la hora es la hora de la salida
        JSONObject respuesta = respuesta(false, "Registro no encontrado.");
        //si se va a buscar registro de un dia anterior debe tambien ser despues de su hora de inicio de jornada 
        String query = "";
        if (hora != "") {

            query = "SELECT * FROM registro_jornada_laboral "
                    + "WHERE id_usuario = '" + id360 + "' AND "
                    + "(date_created = '" + Query.getFecha() + "' OR (date_created = '" + Query.getFecha(Query.getFecha(), -1) + "' AND time_created>'" + hora + "')) "
                    + "ORDER BY id DESC LIMIT 1;";
        } else {
            query = "SELECT * FROM registro_jornada_laboral "
                    + "WHERE id_usuario = '" + id360 + "' AND "
                    + "(date_created = '" + Query.getFecha() + "') "
                    + "ORDER BY id DESC LIMIT 1;";
        }
        JSONObject registro_jornada = Query.select(query);
        JSONObject ultimo_descanso = null;
        if (registro_jornada != null) {
            respuesta = respuesta(true, "Registro encontrado");

            respuesta.put("id_jornada", registro_jornada.get("id"));
            respuesta.put("reporte", registro_jornada.get("reporte"));
            respuesta.put("fecha_inicio_jornada", registro_jornada.get("date_created"));
            respuesta.put("hora_inicio_jornada", registro_jornada.get("time_created"));

            //si se va a buscar registro de un dia anterior debe tambien ser despues de su hora de inicio de jornada 
//            query = "SELECT * FROM registro_descanso_jornada_laboral "
//                    + "WHERE id360 = '" + id360 + "' AND "
//                    + "(fecha_pausa = '" + registro_jornada.get("date_created") + "' OR "
//                    + "fecha_pausa = '" + Query.getFecha(registro_jornada.get("date_created").toString(), -1) + "') ORDER BY id DESC;";
            //si se va a buscar registro de un dia anterior debe tambien ser despues de su hora de inicio de jornada 
            if (hora != "") {
                query = "SELECT * FROM registro_descanso_jornada_laboral "
                        + "WHERE id360 = '" + id360 + "' AND "
                        + "(fecha_pausa = '" + Query.getFecha() + "' OR ("
                        + "fecha_pausa = '" + Query.getFecha(Query.getFecha(), -1) + "' and time_created>'" + hora + "')) ORDER BY id DESC;";
            } else {
                query = "SELECT * FROM registro_descanso_jornada_laboral "
                        + "WHERE id360 = '" + id360 + "' AND "
                        + "(fecha_pausa = '" + Query.getFecha() + "' ) ORDER BY id DESC;";
            }

            JSONArray registro_descanso = Query.execute(query);
            if (!registro_descanso.isEmpty()) {
                JSONObject primer_descanso = new JSONObject();
                primer_descanso.put("fecha_descanso", ((JSONObject) registro_descanso.get(0)).get("fecha_pausa"));
                primer_descanso.put("hora_descanso", ((JSONObject) registro_descanso.get(0)).get("hora_pausa"));
                primer_descanso.put("fecha_reincorporacion", ((JSONObject) registro_descanso.get(0)).get("fecha_reinicio"));
                primer_descanso.put("hora_reincorporacion", ((JSONObject) registro_descanso.get(0)).get("hora_reinicio"));
                ultimo_descanso = new JSONObject();
                ultimo_descanso.put("fecha_descanso", ((JSONObject) registro_descanso.get(registro_descanso.size() - 1)).get("fecha_pausa"));
                ultimo_descanso.put("hora_descanso", ((JSONObject) registro_descanso.get(registro_descanso.size() - 1)).get("hora_pausa"));
                ultimo_descanso.put("fecha_reincorporacion", ((JSONObject) registro_descanso.get(registro_descanso.size() - 1)).get("fecha_reinicio"));
                ultimo_descanso.put("hora_reincorporacion", ((JSONObject) registro_descanso.get(registro_descanso.size() - 1)).get("hora_reinicio"));
                respuesta.put("primer_descanso", primer_descanso);
                respuesta.put("ultimo_descanso", ultimo_descanso);
            }
            respuesta.put("descansos", registro_descanso);
            if (registro_jornada.get("time_finished") != null) {
//                respuesta.put("fecha_termino_jornada", registro_jornada.get("date_created"));
                respuesta.put("fecha_termino_jornada", registro_jornada.get("date_updated"));
                respuesta.put("hora_termino_jornada", registro_jornada.get("time_finished"));
            }
        }
        //iniciamos con un estado de finalizada
        respuesta.put("estatus_jornada", "finalizada");
        //revisar si el usuario tiene una conexion iniciada
        if (registro_jornada != null) {
            respuesta.put("estatus_jornada", "iniciada");
            //revisar si ya realizo algun descanso el usuario
            if (ultimo_descanso != null) {
                respuesta.put("estatus_jornada", "pausada");
                //revisar si ya se ha reincorporado el usuario
                if (ultimo_descanso.get("hora_reincorporacion") != null) {
                    respuesta.put("estatus_jornada", "pausada");
                }
            }
            //revisar si ya se ha finalizado la jornada del usuario
            if (registro_jornada.get("time_finished") != null) {
                respuesta.put("estatus_jornada", "finalizada");
            }
        }

//        query = "SELECT * FROM registro_jornada_laboral WHERE id_usuario = '" + id360 + "' AND activo = 1 ORDER BY id DESC limit 1;";
//        JSONObject registro_jornada2 = Query.select(query);
//        if (registro_jornada2 != null) {
//            respuesta.put("estatus_jornada", "iniciada");
//            query = "SELECT * FROM registro_descanso_jornada_laboral "
//                    + "WHERE id360 = '" + id360 + "' AND "
//                    + "(fecha_pausa = '" + registro_jornada2.get("date_created") + "' OR "
//                    + "fecha_pausa = '" + Query.getFecha(registro_jornada2.get("date_created").toString(), 1) + "') "
//                    + "ORDER BY id DESC limit 1;";
//            JSONObject registro_descanso2 = Query.select(query);
//            if (registro_descanso2 != null) {
//                respuesta.put("estatus_jornada", "pausada");
//                if (registro_descanso2.get("hora_reinicio") != null || registro_descanso2.get("fecha_reinicio") != null) {
//                    respuesta.put("estatus_jornada", "reanudada");
//                }
//            }
//        } else {
//            respuesta.put("estatus_jornada", "finalizada");
//        }
        return respuesta;
    }

    @RequestMapping(value = "/API/cuenta360/registro_modulo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject update_modulo(@RequestBody JSONObject json) throws IOException, ParseException {
        json.put("modulo", "empleados");
        JSONObject respuesta = respuesta(true, "Informacion actualizada correctamente");
        respuesta.put("empleados", request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/registro_modulo", json));
        json.put("modulo", "perfil");
        respuesta.put("perfil", request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/registro_modulo", json));
        return respuesta;
    }

}
