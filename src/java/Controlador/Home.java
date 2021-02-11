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
import Modelo.Grupos;
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
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import sun.misc.BASE64Decoder;

/**
 *
 * @author Moises Juarez
 */
@Controller
@Async
public class Home {
/*
    private JSONObject respuesta(boolean r, String m) {
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", r);
        respuesta.put("failure", !r);
        respuesta.put("mensaje", m);
        return respuesta;
    }

    @RequestMapping(value = "/model", method = RequestMethod.GET)
    public String model(HttpServletRequest sesion, Model model) throws ParseException, IOException {

        //return solicitud_menus.toString();
        //JSONObject modulo = new JSONObject();
        model.addAttribute("modela");
        model.addAttribute("model1", "Comunicación");

        model.asMap().put("model2", model.asMap().get("model1") + "bingo");
        System.out.println(model);
        System.out.println(model.toString());
        System.out.println(model.asMap());
        System.out.println(model.asMap().toString());

        return "Home2/newjsp";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(HttpServletRequest sesion, Model model) throws ParseException, IOException {
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
        JSONObject the_sesion = Revision.getSesion(sesion);

        if (the_sesion == null) {
            return "login/Login";
        } else {
            //recuperar los menus del usuario 
            JSONObject id360 = new JSONObject();
            id360.put("id360", the_sesion.get("id_usuario"));
            JSONObject solicitud_menus = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/get/menu", id360);
            JSONArray menus = (JSONArray) solicitud_menus.get("menus");
            model.addAttribute("modulos","");
            for (int i = 0; i < menus.size(); i++) {
                JSONObject menu = (JSONObject) menus.get(i);
                
                if (menu.get("url").toString().equals(config.getPATH()+config.getDEPENDENCIA()+"/")) {
                    //solicitar vista
                    model.addAttribute("modulos",model.asMap().get("modulos") + request.POST("https://empresas.claro360.com/plataforma360_dev_moises/"+Normalizer.normalize(menu.get("nombre").toString(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replace(" ", "").toLowerCase(), menu.toString()));

                    //System.out.println(Normalizer.normalize(menu.get("nombre").toString(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replace(" ", "").toLowerCase());
                } else {
                    //agregar menu externo 
                    model.addAttribute("modulos",model.asMap().get("modulos") + request.POST("https://empresas.claro360.com/plataforma360_dev_moises/modulo_vacio", menu.toString()));

                }
                
            }
            //return solicitud_menus.toString();

            //JSONObject modulo = new JSONObject();
            //model.addAttribute("modulo_0", request.POST("https://empresas.claro360.com/plataforma360_dev_moises/Mi Perfil", modulo.toString()));
            return "Home2/home";
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
        model.addAttribute("alias", json.get("alias"));
        model.addAttribute("nombre", json.get("nombre"));
        model.addAttribute("nombre_id", json.get("nombre").toString().replace(" ", ""));
        model.addAttribute("icono", json.get("icono"));
        model.addAttribute("categoria", json.get("categoria"));
        model.addAttribute("url", json.get("url"));

        return "Home2/modulo_vacio";
    }

    @RequestMapping(value = "/miperfil", method = RequestMethod.POST)
    public String miperfil(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        }
        return "Home2/modulo_perfil";
    }
    @RequestMapping(value = "/nuevoreporte", method = RequestMethod.POST)
    public String nuevoreporte(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        }
        return "Home2/modulo_reporte";
    }
    @RequestMapping(value = "/reporteseguridadsanitaria", method = RequestMethod.POST)
    public String reporteseguridadsanitaria(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        }
        return "Home2/modulo_reporte_ss";
    }
    @RequestMapping(value = "/entradaysalida", method = RequestMethod.POST)
    public String entradaysalida(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        }
        return "Home2/modulo_entrada_salida";
    }
    @RequestMapping(value = "/comunicacion", method = RequestMethod.POST)
    public String comunicacion(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        }
        return "Home2/modulo_comunicacion";
    }
    @RequestMapping(value = "/notas", method = RequestMethod.POST)
    public String notas(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        }
        return "Home2/modulo_notas";
    }
    @RequestMapping(value = "/recordatorios", method = RequestMethod.POST)
    public String recordatorios(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        }
        return "Home2/modulo_recordatorios";
    }
    @RequestMapping(value = "/agenda", method = RequestMethod.POST)
    public String agenda(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        }
        return "Home2/modulo_agenda";
    }
    @RequestMapping(value = "/misucursal", method = RequestMethod.POST)
    @ResponseBody
    public String misucursal(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        }
//        return "Home2/modulo_perfil";
        return "";
    }
    @RequestMapping(value = "/areasdetrabajo", method = RequestMethod.POST)
    public String areasdetrabajo(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        }
        return "Home2/modulo_areas_de_trabajo";
    }
    @RequestMapping(value = "/plantillalaboral", method = RequestMethod.POST)
    public String plantillalaboral(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        }
        return "Home2/modulo_plantillas_laborales";
    }
    @RequestMapping(value = "/reportejornadaslaborales", method = RequestMethod.POST)
    public String reportejornadaslaborales(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        }
        return "Home2/modulo_jornadas_laborales";
    }
    @RequestMapping(value = "/monitoreodeempleados", method = RequestMethod.POST)
    public String monitoreodeempleados(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        }
        return "Home2/modulo_monitoreo_personal";
    }
    @RequestMapping(value = "/videowallempleados", method = RequestMethod.POST)
    public String videowallempleados(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        }
        return "Home2/modulo_videowall";
    }
    @RequestMapping(value = "/miempresa", method = RequestMethod.POST)
    public String miempresa(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        }
        return "Home2/modulo_mi_empresa";
    }
    @RequestMapping(value = "/registrarsucursal", method = RequestMethod.POST)
    public String registrarsucursal(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        }
        return "Home2/modulo_registrar_sucursal";
    }
    @RequestMapping(value = "/missucursales", method = RequestMethod.POST)
    public String missucursales(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        }
        return "Home2/modulo_mis_sucursales";
    }
*/
}
