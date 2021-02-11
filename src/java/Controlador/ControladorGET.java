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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;
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
public class ControladorGET {

    private JSONObject respuesta(boolean r, String m) {
        JSONObject respuesta = new JSONObject();
        respuesta.put("success", r);
        respuesta.put("failure", !r);
        respuesta.put("mensaje", m);
        return respuesta;
    }

    @RequestMapping(value = "/API/config/proyect", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject config_proyect() {
        return config.getInit();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest sesion, Model model) throws ParseException, IOException {
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
        System.out.println(ValidarIP.Validacion_ip_publica(sesion, model, "Login"));
        return ValidarIP.Validacion_ip_publica(sesion, model, "login/Login");
    }

    @RequestMapping(value = "/makepage", method = RequestMethod.GET)
    public String make(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            //model.addAttribute("title", config.getAliasServicio() + " - " + config.getPersonalizacion().get("t1"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            model.addAttribute("salary", 1800);
            model.addAttribute("v", 1800);
            System.out.println(config.getPersonalizacion().get("favicon"));
            if (true) {
                JSONObject abc = new JSONObject();
                abc.put("a", 10);
                abc.put("b", 20);
                abc.put("c", 30);
                model.addAttribute("page_added", request.POST("http://localhost:8080/plataforma360/one", abc.toString()));
            }
        } else {
            System.out.println("Proyecto no inicializado");

            return "makepage/makepage";
        }
        System.out.println(ValidarIP.Validacion_ip_publica(sesion, model, "Login"));
        return ValidarIP.Validacion_ip_publica(sesion, model, "makepage/makepage");
    }

    @RequestMapping(value = "/one", method = RequestMethod.POST)
    public String one(Model model, @RequestBody JSONObject json) throws ParseException, IOException {
        model.addAttribute("a", json.get("a"));
        model.addAttribute("b", json.get("b"));
        return "makepage/one";
    }

    @RequestMapping(value = "/tablas", method = RequestMethod.GET)
    public String tablas(HttpServletRequest sesion, Model model) throws ParseException, IOException {

        model.addAttribute("pathRecursos", "https://empresas.claro360.com/p360_v4");
        model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
        model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
        //model.addAttribute("title", config.getAliasServicio() + " - " + config.getPersonalizacion().get("t1"));
        model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        System.out.println(config.getPersonalizacion().get("favicon"));

        return ValidarIP.Validacion_ip_publica(sesion, model, "tablas/tablas");
    }

    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String Login(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "login/Login");
        //return "Login";
    }

    @RequestMapping(value = "/dashboard2", method = RequestMethod.GET)
    public String empleado(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        JSONObject the_sesion = Revision.getCookie(sesion);
        if (the_sesion != null) {
            if (the_sesion.get("tipo_usuario") == null) {
                return ValidarIP.Validacion_ip_publica(sesion, model, "empresas360/registro_empresa");
            }
            if (the_sesion.get("tipo_usuario").toString().equals("0")) {
                System.out.println("Usuario Maestro");
            } else if (the_sesion.get("tipo_servicio").toString().equals("0")) {
                System.out.println("Usuario Nivel empresa");
                return ValidarIP.Validacion_ip_publica(sesion, model, "empresas360/empresa");
            } else if (the_sesion.get("tipo_area").toString().equals("0")) {
                System.out.println("Usuario nivel sucursal");
                return ValidarIP.Validacion_ip_publica(sesion, model, "empresas360/empresa");
            } else {
                System.out.println("empleado");
                return ValidarIP.Validacion_ip_publica(sesion, model, "empresas360/empleado");
            }
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "empresas360/empleado");
        //return "Login";
    }

    @RequestMapping(value = "/valido/{vista}/{token}", method = RequestMethod.GET)
    public String LoginToken(HttpServletRequest sesion, Model model, @PathVariable("vista") String vista, @PathVariable("token") String token) throws ParseException, java.text.ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        System.out.println(token);
        if (Acceso.checktoken(token)) {
            return vista;
        }
        //return ValidarIP.Validacion_ip_publica(sesion, model, "Login");
        //return "Login";
        return index(sesion, model);
    }

    @RequestMapping(value = "/CamarasUrbanas/API/{lat}/{lng}/{range}", method = RequestMethod.GET)
    @ResponseBody
    public String CamarasUrbanas(HttpServletRequest sesion, Model model, @PathVariable("lat") String lat, @PathVariable("lng") String lng, @PathVariable("range") String range) throws ParseException, java.text.ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        System.out.println("CamarasUrbanas: " + config.getDEPENDENCIA());
        return request.GET("http://mapagis.ml/api/camaras/lat:" + lat + ",lon:" + lng + ",rkm:" + range);

    }

    @RequestMapping(value = "/plantilla", method = RequestMethod.GET)
    public String plantilla(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            System.out.println("Cookies");
            System.out.println(Arrays.toString(sesion.getCookies()));
            for (Cookie c : sesion.getCookies()) {
//                if (c.getName().equals("myCookie")) {
//                    return c.getValue();
//                }
                System.out.println(c.getName());
            }
            System.out.println("****************");
        } else {
            System.out.println("Proyecto no inicializado");
            //return "plantilla/sinInicializar";
        }

        model.addAttribute("pathRecursos", "http://localhost:8080/p360");
        model.addAttribute("config", "{\"logo3pdf\":null,\"lottie\":\"https:\\/\\/seguridadsanitaria.claro360.com\\/lineamientos\\/resources\\/local_v3.03\\/json\\/lottie360.json\",\"logo2\":null,\"logo1\":null,\"logo1pdf\":\"https:\\/\\/seguridadsanitaria.claro360.com\\/lineamientos\\/resources\\/local_v3.03\\/Img\\/Logos\\/911.png\",\"proyecto\":\"lineamientos\",\"iconMap\":null,\"t2pdf\":\"Ciudad de México\",\"alias_proyecto\":\"Lineamientos\",\"dep_base\":false,\"lg2\":\"7\",\"lg1\":\"12\",\"logo_principal\":\"https:\\/\\/seguridadsanitaria.claro360.com\\/lineamientos\\/resources\\/local_v3.03\\/Img\\/Logos\\/Claro%20360.png\",\"lg3\":null,\"logo2pdf\":\"https:\\/\\/seguridadsanitaria.claro360.com\\/lineamientos\\/resources\\/local_v3.03\\/Img\\/Logos\\/Claro%20360.png\",\"logo_footer\":\"https:\\/\\/seguridadsanitaria.claro360.com\\/lineamientos\\/resources\\/local_v3.03\\/Img\\/Logos\\/Claro%20360.png\",\"favicon\":\"https:\\/\\/seguridadsanitaria.claro360.com\\/lineamientos\\/resources\\/local_v3.03\\/Img\\/favicon360.png\",\"pv1\":\"15\",\"ah2\":\"37\",\"ah1\":\"18\",\"pv3\":null,\"pv2\":\"17\",\"ah3\":null,\"t1pdf\":\"Plataforma Emergencia\",\"ph1\":\"12\",\"catalogo\":\"https:\\/\\/seguridadsanitaria.claro360.com\\/lineamientos\\/resources\\/local_v3.03\\/json\\/incidentes.json\",\"ph3\":null,\"socket\":\"wss:\\/\\/seguridadsanitaria.claro360.com\\/lineamientos\\/SocketNotifications\",\"ph2\":\"165\",\"recursos\":\"https:\\/\\/seguridadsanitaria.claro360.com\\/lineamientos\\/resources\\/local_v3.03\",\"t1\":\"\",\"logo_modal\":\"https:\\/\\/seguridadsanitaria.claro360.com\\/lineamientos\\/resources\\/local_v3.03\\/Img\\/Logos\\/Claro%20360.png\",\"t2\":\"Administrador\",\"tfooter\":\"© 360 HQ S.A de C.V 2019. Todos los derechos reservados\",\"t3\":\"\"}".toString().replace("\"", "&quot;"));
        //model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
        model.addAttribute("title", "Claro360  - ");
        return "plantilla/plantilla";
    }

    @RequestMapping(value = "/EstadisticosCCB", method = RequestMethod.GET)
    public String EstadisticosCCB(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            System.out.println("Cookies");
            System.out.println(Arrays.toString(sesion.getCookies()));
            for (Cookie c : sesion.getCookies()) {
//                if (c.getName().equals("myCookie")) {
//                    return c.getValue();
//                }
                System.out.println(c.getName());
            }
            System.out.println("****************");
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "CCB/estadisticas2";
    }

    @RequestMapping(value = "/EstadisticosCCB2", method = RequestMethod.GET)
    public String EstadisticosCCB2(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            System.out.println("Cookies");
            System.out.println(Arrays.toString(sesion.getCookies()));
            for (Cookie c : sesion.getCookies()) {
//                if (c.getName().equals("myCookie")) {
//                    return c.getValue();
//                }
                System.out.println(c.getName());
            }
            System.out.println("****************");
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "CCB/estadisticas";
    }

    @RequestMapping(value = "/plantilla2", method = RequestMethod.GET)
    public String plantilla2(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));

            System.out.println("Cookies");
            System.out.println(Arrays.toString(sesion.getCookies()));
            for (Cookie c : sesion.getCookies()) {
                if (c.getName().equals("username_v3.1_" + config.getDEPENDENCIA())) {
                    System.out.println(c.getValue());
                    byte[] decodedBytes = Base64.getDecoder().decode(c.getValue());
                    String decodedString = new String(decodedBytes);
                    System.out.println(decodedString);
                }
                System.out.println(c.getName());
            }
            System.out.println("****************");
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "plantilla/plantilla2";
    }

    @RequestMapping(value = "/preloading", method = RequestMethod.GET)
    public String preloading(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "info/preloading";
    }

    @RequestMapping(value = "/Administrador", method = RequestMethod.GET)
    public String Administrador(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "sos/Despacho");
    }

    @RequestMapping(value = "/Registro", method = RequestMethod.GET)
    public String Registro(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        //String option = EmpresasDAO.Consultar();
        //model.addAttribute("Empresas", option);
        return "SuperAdmin/Registro";
    }

    @RequestMapping(value = "/BuscarReporte", method = RequestMethod.GET)
    public String BuscarReporte(HttpServletRequest sesion, Model model) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }

        JSONObject data = new JSONObject();
        String query = "SELECT DISTINCT(fecha) FROM registro_llamadas;";
        JSONArray array = Query.execute(query);

        data.put("FechasRutas", array);
        model.addAttribute("data", data.toString().replace("\"", "&quot;"));

        return "sos/BusquedaReporte";
    }

    @RequestMapping(value = "/Empresa", method = RequestMethod.GET)
    public String Empresa(HttpServletRequest sesion, Model model) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        //initmonitorig();
        return "monitoreo/MonitoreoUnidades";
    }

    @RequestMapping(value = "/RutaHistorico", method = RequestMethod.GET)
    public String RutaHistorico(HttpServletRequest sesion, Model model) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "monitoreo/HistoricoRuta";
    }

    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public String unauthorized(HttpServletRequest sesion, Model model) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "info/401";
    }

    @RequestMapping(value = "/Operador", method = RequestMethod.GET)
    public String Operador(Model model, String id, HttpServletRequest sesion) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        String def = "Esperando Datos de perfil...";
        String NombrePerfil = def;
        String ApellidoP = def;
        String ApellidoM = def;
        String Fecha_nacimiento = def;
        String CorreoPerfil = def;
        String TelPerfil = def;
        String GenPerfil = def;
        String RhPerfil = def;
        String AlergiasPerfil = def;
        String CondicionMedica = def;
        String DireccionPerfil = def;
        String CPPerfil = def;
        String ContactoNombre = def;
        String ContactoNumero = def;

        model.addAttribute("NombrePerfil", NombrePerfil);
        model.addAttribute("ApellidoP", ApellidoP);
        model.addAttribute("ApellidoM", ApellidoM);
        model.addAttribute("Fecha_nacimiento", Fecha_nacimiento);
        model.addAttribute("CorreoPerfil", CorreoPerfil);
        model.addAttribute("TelPerfil", TelPerfil);
        model.addAttribute("GenPerfil", GenPerfil);
        model.addAttribute("RhPerfil", RhPerfil);
        model.addAttribute("AlergiasPerfil", AlergiasPerfil);
        model.addAttribute("CondicionMedica", CondicionMedica);
        model.addAttribute("DireccionPerfil", DireccionPerfil);
        model.addAttribute("CPPerfil", CPPerfil);
        model.addAttribute("ContactoNombre", ContactoNombre);
        model.addAttribute("ContactoNumero", ContactoNumero);

        //String option = ProyectoDAO.ConsultarTiposEmergencia();
        //model.addAttribute("TipoEmergencia", option);
        //model.addAttribute("CNI", option);
        String dependencias = ProyectoDAO.ConsultarDependenciasAsociadas();
        model.addAttribute("Dependencias", dependencias);

        return ValidarIP.Validacion_ip_publica(sesion, model, "sos/Operador");
    }

    @RequestMapping(value = "/OperadorEmpresa", method = RequestMethod.GET)
    public String OperadorEmpresa(Model model, String id, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        String def = "Esperando Datos de perfil...";
        String NombrePerfil = def;
        String ApellidoP = def;
        String ApellidoM = def;
        String Fecha_nacimiento = def;
        String CorreoPerfil = def;
        String TelPerfil = def;
        String GenPerfil = def;
        String RhPerfil = def;
        String AlergiasPerfil = def;
        String CondicionMedica = def;
        String DireccionPerfil = def;
        String CPPerfil = def;
        String ContactoNombre = def;
        String ContactoNumero = def;

        model.addAttribute("NombrePerfil", NombrePerfil);
        model.addAttribute("ApellidoP", ApellidoP);
        model.addAttribute("ApellidoM", ApellidoM);
        model.addAttribute("Fecha_nacimiento", Fecha_nacimiento);
        model.addAttribute("CorreoPerfil", CorreoPerfil);
        model.addAttribute("TelPerfil", TelPerfil);
        model.addAttribute("GenPerfil", GenPerfil);
        model.addAttribute("RhPerfil", RhPerfil);
        model.addAttribute("AlergiasPerfil", AlergiasPerfil);
        model.addAttribute("CondicionMedica", CondicionMedica);
        model.addAttribute("DireccionPerfil", DireccionPerfil);
        model.addAttribute("CPPerfil", CPPerfil);
        model.addAttribute("ContactoNombre", ContactoNombre);
        model.addAttribute("ContactoNumero", ContactoNumero);

        return "monitoreo/LlamadaGrupal";
    }

    @RequestMapping(value = "/Llamada/{id_llamada}/{apikey}/{sesion}/{token}", method = RequestMethod.GET)
    public String llamada_multiplataforma(
            Model model,
            /*@RequestParam("id") String id,*/
            @PathVariable("id_llamada") String id_llamada,
            @PathVariable("apikey") String apikey,
            @PathVariable("sesion") String sesion,
            @PathVariable("token") String token
    ) {

        System.out.println("llamada_multiplataforma:");
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }

        JSONObject credenciales = new JSONObject();
        credenciales.put("apikey", apikey);
        credenciales.put("sesion", sesion);
        credenciales.put("token", token);

        JSONObject registroLlamada = new JSONObject();
        registroLlamada.put("fecha", Query.getFecha());
        registroLlamada.put("hora", Query.getHora());
        registroLlamada.put("idLlamada", id_llamada);
//        registroLlamada.put("idOperador", idSys);
//        registroLlamada.put("modo", modo);
//        registroLlamada.put("participantes", jsonIntegrantes);

        JSONObject Notificacion = new JSONObject();
        Notificacion.put("registro_llamada", registroLlamada);
        Notificacion.put("credenciales", credenciales);

        model.addAttribute("data", Notificacion.toString().replace("\"", "&quot;"));

        return "empresas360/llamada_entrante";
    }

    @RequestMapping(value = "/Llamada/{id_llamada}/{apikey}/{sesion}/{token}/{id360}/{access_token}", method = RequestMethod.GET)
    public String llamada_multiplataforma_access_token(
            Model model,
            /*@RequestParam("id") String id,*/
            @PathVariable("id_llamada") String id_llamada,
            @PathVariable("apikey") String apikey,
            @PathVariable("sesion") String sesion,
            @PathVariable("token") String token,
            @PathVariable("id360") String id360,
            @PathVariable("access_token") String access_token
    ) throws IOException, ParseException {

        System.out.println("llamada_multiplataforma:");
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }

        JSONObject credenciales = new JSONObject();
        credenciales.put("apikey", apikey);
        credenciales.put("sesion", sesion);
        credenciales.put("token", token);

        JSONObject registroLlamada = new JSONObject();
        registroLlamada.put("fecha", Query.getFecha());
        registroLlamada.put("hora", Query.getHora());
        registroLlamada.put("idLlamada", id_llamada);
//        registroLlamada.put("idOperador", idSys);
//        registroLlamada.put("modo", modo);
//        registroLlamada.put("participantes", jsonIntegrantes);

        JSONObject Notificacion = new JSONObject();
        Notificacion.put("registro_llamada", registroLlamada);
        Notificacion.put("credenciales", credenciales);

        model.addAttribute("data", Notificacion.toString().replace("\"", "&quot;"));

        JSONObject json = new JSONObject();
        json.put("access_token", access_token);
        json.put("id360", id360);
        json.put("force_validation", true);
        JSONObject usuario = Request.request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/validate/access_token", json);
        if ((boolean) usuario.get("success")) {
            ControladorPOST cp = new ControladorPOST();
            usuario = cp.data_login(usuario);
            model.addAttribute("cuenta360", usuario.toString().replace("\"", "&quot;"));
        }

        return "empresas360/llamada_entrante";
    }

    @RequestMapping(value = "/MonitoreoLlamadas", method = RequestMethod.GET)
    public String MonitoreoLlamadas(HttpServletRequest sesion, Model model) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "MonitoreoLlamadas";
    }

    @RequestMapping(value = "/Proyecto", method = RequestMethod.GET)
    public String proyecto(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        ProyectoVO p = ProyectoDAO.consultar();
        String apikey = p.getApikey();
        String heroku = p.getHeroku();
        String FireBaseAuthorization = p.getFireBaseAuthorization();
        model.addAttribute("apikey", apikey);
        model.addAttribute("heroku", heroku);
        model.addAttribute("FireBaseAuthorization", FireBaseAuthorization);
        return "SuperAdmin/proyecto";
    }

    @RequestMapping(value = "/Reporte/{idLlamada}/{origen}/{hora}", method = RequestMethod.GET)
    public String ReporteOrigen(HttpSession sesion, Model model, @PathVariable("origen") String origen, @PathVariable("idLlamada") String idLlamada, @PathVariable("hora") String hora) throws ParseException, Exception {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        System.out.println(idLlamada);
        System.out.println(origen);
        System.out.println(hora);
        JSONObject reporte = RegistroLlamadasDAO.GenerarReporte(idLlamada, origen, hora);
        System.out.println("Reporte " + reporte);

        System.out.println("---------------------------------------------------------");
        if (reporte != null) {

            JSONObject perfil = new JSONObject();
            JSONObject RegistroLlamada = new JSONObject();
            RegistroLlamada = (JSONObject) reporte.get("RegistroLlamada");
            JSONParser parser = new JSONParser();
            //boolean dependenciaBase = config.isDEPENDENCIA_BASE();
            if (origen.equals("ciudadano") || origen.equals("C4norte")) {
                System.out.println("Esto en el json a manda ------>");
                System.out.println(RegistroLlamada.toString());
                System.out.println("A esta direccion");
                System.out.println(config.getURL_CONTROLADOR() + "Consulta");
                perfil = (JSONObject) parser.parse(Post.ConsultarPerfil(RegistroLlamada.get("usuario_movil").toString(), config.getURL_CONTROLADOR() + "Consulta"));
            } else {
                String query = "SELECT url FROM dependencias WHERE nombre = '" + origen + "';";
                System.out.println(query);
                JSONArray json = Query.execute(query);
                if (!json.isEmpty()) {
                    JSONObject url = (JSONObject) json.get(0);

                    perfil = (JSONObject) parser.parse(Post.ConsultarPerfil(RegistroLlamada.get("usuario_movil").toString(), /*config.getPATH() + origen*/ url.get("url") + "/Consulta"));
                }

            }
            if (perfil != null) {
                perfil.replace("Img", "");
                perfil.replace("icon", "");
                System.out.println("Este es el perfil solicitado sin img e icon --->");
                System.out.println(perfil);

                reporte.put("PerfilUsuario", perfil);
            } else {

                perfil.put("nombre", "");
                perfil.put("apellido_paterno", "");
                perfil.put("apellido_materno", "");
                perfil.put("Img", "");
                perfil.put("icon", "");
                reporte.put("PerfilUsuario", perfil);
            }

            String rep = reporte.toString().replace("\"", "&quot;");

            model.addAttribute("reporte", rep);

        }
        return "sos/Reporte";
    }

    @RequestMapping(value = "/ReporteCiudadano", method = RequestMethod.GET)
    public String ReporteCiudadano(HttpServletRequest sesion, Model model) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "ReporteCiudadano";
    }

    @RequestMapping(value = "/DatosProyecto", method = RequestMethod.GET)
    @ResponseBody
    public String DatosProyecto(HttpServletRequest sesion, Model model) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        ProyectoVO p = ProyectoDAO.consultar();
        if (p != null) {
            Gson gson = new Gson();
            String json = gson.toJson(p);
            return json;
        }
        return null;
    }

    @RequestMapping(value = "/DependenciasAsociadas", method = RequestMethod.GET)
    @ResponseBody
    public String DependenciasAsociadas(HttpServletRequest sesion) {
        String d = ProyectoDAO.DependenciasAsociadas();
        System.out.println(d);
        if (d != null) {
            return d;
        }
        return null;
    }

    /**
     * ******************************************************************************
     */
    @RequestMapping(value = "/ReporteElemento", method = RequestMethod.GET)
    public String ReporteElemento(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        String dependencias = ProyectoDAO.ConsultarDependenciasAsociadas();
        model.addAttribute("Dependencias", dependencias);
        return "ReporteElemento/ReporteElemento";
    }

    @RequestMapping(value = "/GeneraCredenciales", method = RequestMethod.GET)
    @ResponseBody
    public String GeneraCredenciales() throws IOException {

        System.out.println("get credenciales: " + config.getDEPENDENCIA());

//        String url = config.getURL_CONTROLADOR() + "API/Credenciales";
////        String response = ;
//        return request.POST(url, "{}");
        return request.GET("https://meeting.claro360.com/getRoom");
    }

    @RequestMapping(value = "/Llamada", method = RequestMethod.GET)
    public String EnviarOperador2(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "monitoreo/LlamadaGrupal";
    }

    @RequestMapping(value = "/API/ConsultarBackupDirectorio", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject ConsultarBackupDirectorio() throws ParseException, IOException {
        //initmonitorig();
        System.out.println("ConsultarBackupDirectorio: ");

        return getBackupDirectorio();
    }

    @RequestMapping(value = "/API/ConsultarBackupDirectorio/server/{tipo_usuario}/{tipo_servico}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject ConsultarBackupDirectorioPersonalizado_server(@PathVariable("tipo_usuario") String tipo_usuario, @PathVariable("tipo_servico") String tipo_servicio) throws ParseException, IOException {
        //initmonitorig();
        System.out.println("ConsultarBackupDirectorioPersonalizado: ");
        JSONObject jsonObj = (JSONObject) getBackupDirectorio();
        JSONParser parser = new JSONParser();
        JSONObject BackUp = (JSONObject) parser.parse(jsonObj.toString());

        String query = "";
        JSONArray gruposAutomaticos = (JSONArray) BackUp.get("GruposAutomaticos");
        JSONArray integrantesEliminar = new JSONArray();
        if (!"0".equals(tipo_usuario) && "0".equals(tipo_servicio)) {
            query = "select TU.tipo_usuario, SU.nombre  from tipos_usuarios TU, servicios_usuario SU where TU.id = " + tipo_usuario + " AND TU.id=SU.idTipoUsuario;";
            JSONObject servicio = Query.select(query);
            String aliasServicio = config.getAliasServicio() + " " + servicio.get("tipo_usuario");
            for (int i = 0; i < gruposAutomaticos.size(); i++) {
                JSONObject grupoAutomatico = (JSONObject) gruposAutomaticos.get(i);
                if (!grupoAutomatico.get("nombre").toString().contains(aliasServicio)) {
                    JSONArray array = (JSONArray) grupoAutomatico.get("integrantes");
                    for (int j = 0; j < array.size(); j++) {
                        integrantesEliminar.add(array.get(j));
                    }
                    gruposAutomaticos.remove(i);
                    i--;
                }
            }
            JSONArray integrantes = (JSONArray) BackUp.get("integrantes");
            for (int i = 0; i < integrantes.size(); i++) {
                JSONObject json = (JSONObject) integrantes.get(i);
                for (int j = 0; j < integrantesEliminar.size(); j++) {
                    if (json.get("idUsuarios_Movil").toString().equals(integrantesEliminar.get(j).toString())) {
                        integrantes.remove(i);
                        i--;
                        integrantesEliminar.remove(j);
                        break;
                    }
                }
            }
            return BackUp;
        } else if ("0".equals(tipo_usuario)) {
            return BackUp;
        } else {
            query = "select TU.tipo_usuario, SU.nombre  from tipos_usuarios TU, servicios_usuario SU where TU.id = " + tipo_usuario + " AND SU.id=" + tipo_servicio + " AND TU.id=SU.idTipoUsuario;";
            System.out.println(query);
            JSONObject servicio = Query.select(query);
            String aliasServicio = config.getAliasServicio() + " " + servicio.get("tipo_usuario") + " " + servicio.get("nombre");
            for (int i = 0; i < gruposAutomaticos.size(); i++) {
                JSONObject grupoAutomatico = (JSONObject) gruposAutomaticos.get(i);
                if (!grupoAutomatico.get("nombre").toString().equals(aliasServicio)) {
                    JSONArray array = (JSONArray) grupoAutomatico.get("integrantes");
                    for (int j = 0; j < array.size(); j++) {
                        integrantesEliminar.add(array.get(j));
                    }
                    gruposAutomaticos.remove(i);
                    i--;
                }
            }
            JSONArray integrantes = (JSONArray) BackUp.get("integrantes");
            for (int i = 0; i < integrantes.size(); i++) {
                JSONObject json = (JSONObject) integrantes.get(i);
                for (int j = 0; j < integrantesEliminar.size(); j++) {
                    if (json.get("idUsuarios_Movil").toString().equals(integrantesEliminar.get(j).toString())) {
                        integrantes.remove(i);
                        i--;
                        integrantesEliminar.remove(j);
                        break;
                    }
                }
            }
            return BackUp;
        }
    }

    @RequestMapping(value = "/API/ConsultarBackupDirectorio/{tipo_usuario}/{tipo_servicio}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject ConsultarBackupDirectorioPersonalizado(@PathVariable("tipo_usuario") String tipo_usuario, @PathVariable("tipo_servicio") String tipo_servicio) throws ParseException, IOException {
        //initmonitorig();
        System.out.println("ConsultarBackupDirectorioPersonalizado: ");
        //Consulta de Grupos Automaticos 
        JSONObject respuesta = new JSONObject();
        JSONArray GruposAutomaticos = new JSONArray();
        String query = null;
        //Construir query por tipo de usuario y tipo de servicio
        //preguntar si es usuario maestro a nivel estatal
        if (tipo_usuario.equals("0")) {
            query = "SELECT distinct G.idgruposUsuarioSys,G.nombre,G.idServicio,G.idSuperior, G.tipo_usuario, G.tipo_servicio, D.urlServicio "
                    + "FROM grupos_usuario_sys G, directorio D "
                    + "where estado = 3 and G.nombre = D.aliasServicio and D.activo=1;";
        } else {
            //preguntar si es usuario maestro a nivel dependencia
            if (tipo_servicio.equals("0")) {
                query = "SELECT distinct G.idgruposUsuarioSys,G.nombre,G.idServicio,G.idSuperior, G.tipo_usuario, G.tipo_servicio, D.urlServicio "
                        + "FROM grupos_usuario_sys G, directorio D "
                        + "where estado = 3 and G.nombre = D.aliasServicio and D.activo=1 and G.tipo_usuario=" + tipo_usuario + ";";
            } else {
                query = "SELECT distinct G.idgruposUsuarioSys,G.nombre,G.idServicio,G.idSuperior, G.tipo_usuario, G.tipo_servicio, D.urlServicio "
                        + "FROM grupos_usuario_sys G, directorio D "
                        + "where estado = 3 and G.nombre = D.aliasServicio and D.activo=1 and G.tipo_usuario=" + tipo_usuario + " and G.tipo_servicio=" + tipo_servicio + ";";
            }
        }

        System.out.println(query);

        JSONArray Grupos_sys = Query.execute(query);

        for (int i = 0; i < Grupos_sys.size(); i++) {
            JSONArray integrantes_grupo = new JSONArray();
            JSONObject grupo = (JSONObject) Grupos_sys.get(i);
            JSONArray integrantes = Query.execute("Select idUsuarios_Movil from grupos_usuario_movil where idgruposUsuarioSys = " + grupo.get("idgruposUsuarioSys") + " and estado = 3;");
            for (int j = 0; j < integrantes.size(); j++) {
                JSONObject integrante = (JSONObject) integrantes.get(j);
                integrantes_grupo.add(integrante.get("idUsuarios_Movil").toString());
            }
            grupo.put("integrantes", integrantes_grupo);
            GruposAutomaticos.add(grupo);
        }
        // ya tenemos grupos automaticos 
        respuesta.put("GruposAutomaticos", GruposAutomaticos);
        System.out.println(respuesta);
        Grupos grupos = new Grupos();
        respuesta.put("integrantes", grupos.integrantes(GruposAutomaticos));
        System.out.println(respuesta);
        //Obtener Grupos personalizados.

        return respuesta;
    }

    @RequestMapping(value = "/API/ConsultarBackupDirectorioLITE", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject ConsultarBackupDirectorioLITE() throws ParseException, IOException {
        System.out.println("ConsultarBackupDirectorio Lite: ");

        JSONObject json = getBackupDirectorio();
        JSONParser parser = new JSONParser();
        JSONObject newbackup = (JSONObject) parser.parse(json.toString());

        JSONArray integrantes = (JSONArray) newbackup.get("integrantes");
        for (int i = 0; i < integrantes.size(); i++) {
            JSONObject integrante = (JSONObject) integrantes.get(i);
            integrante.remove("icon");
            integrante.remove("img");
        }

        return newbackup;
    }

    @RequestMapping(value = "/API/ConsultarImg/perfil/{ID}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] ConsultarImgPerfil(@PathVariable("ID") String id) throws FileNotFoundException, IOException {
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return null;
        }
        System.out.println("ConsultarImg: " + id + " " + config.getDEPENDENCIA());
        String query = "SELECT img from usuarios_movil WHERE idUsuarios_Movil = '" + id + "'";
        JSONArray array = Query.execute(query);
        if (!array.isEmpty()) {
            byte[] imageByte;
            try {
                if (((JSONObject) array.get(0)).get("img") == null) {
                    System.out.println("Imagen NullPointerException  " + id);

                } else {
                    if (((JSONObject) array.get(0)).get("img").toString().equalsIgnoreCase("null")) {
                        System.out.println("imagen en nulo -_-");

                    } else {
                        Base64.Decoder decoder = Base64.getDecoder();
                        //BASE64Decoder decoder = new BASE64Decoder();
                        //imageByte = decoder.decodeBuffer(((JSONObject) array.get(0)).get("img").toString().replaceAll("\n", ""));
                        imageByte = decoder.decode(((JSONObject) array.get(0)).get("img").toString().replaceAll("\n", ""));
                        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                        return IOUtils.toByteArray(bis);
                    }

                }
                Base64.Decoder decoder = Base64.getDecoder();
                imageByte = decoder.decode(Recursos.imagenes.GetImg());
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                return IOUtils.toByteArray(bis);
            } catch (IOException e) {
                System.out.println(e);
                Base64.Decoder decoder = Base64.getDecoder();
                imageByte = decoder.decode(Recursos.imagenes.GetImg());
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                return IOUtils.toByteArray(bis);
            }
        }

        System.out.println("retornando null");
        return null;

    }

    @RequestMapping(value = "/API/ConsultarImg/icono/{ID}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] ConsultarImgIcono(@PathVariable("ID") String id) throws IOException {
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return null;
        }
        System.out.println("ConsultarImg: " + id + " " + config.getDEPENDENCIA());
        String query = "SELECT icon from usuarios_movil WHERE idUsuarios_Movil = '" + id + "'";
        JSONArray array = Query.execute(query);
        if (!array.isEmpty()) {
            byte[] imageByte;
            try {
                if (((JSONObject) array.get(0)).get("icon") == null) {
                    System.out.println("Icon NullPointerException  " + id);
                } else {
                    if (((JSONObject) array.get(0)).get("icon").toString().equalsIgnoreCase("null")) {
                        System.out.println("ICONO en nulo -_-");
                    } else {
                        //BASE64Decoder decoder = new BASE64Decoder();
                        //imageByte = decoder.decodeBuffer(((JSONObject) array.get(0)).get("icon").toString().replaceAll("\n", ""));
                        Base64.Decoder decoder = Base64.getDecoder();
                        imageByte = decoder.decode(((JSONObject) array.get(0)).get("icon").toString().replaceAll("\n", ""));
                        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                        return IOUtils.toByteArray(bis);
                    }
                }
                Base64.Decoder decoder = Base64.getDecoder();
                imageByte = decoder.decode(Recursos.imagenes.GetIcon());
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                return IOUtils.toByteArray(bis);

            } catch (Exception e) {

                System.out.println(e);
                Base64.Decoder decoder = Base64.getDecoder();
                imageByte = decoder.decode(Recursos.imagenes.GetIcon());
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                return IOUtils.toByteArray(bis);
            }
        }
        System.out.println("retornando null");
        return null;
    }

    @RequestMapping(value = "/API/BackupDirectorio/MapeoIntegrantes", method = RequestMethod.GET)
    @ResponseBody
    public static JSONArray MapeoIntegrantes() throws ParseException, IOException {
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return new JSONArray();
        }
        JSONParser parser = new JSONParser();
        JSONArray integrantes = new JSONArray();
        String query = "SELECT idUsuario AS idUsuarios_Movil,urlServicio,aliasServicio FROM directorio where activo =1;";
        JSONArray directorio = Query.execute(query);
        JSONObject directorio_agrupado = new JSONObject();
        for (int i = 0; i < directorio.size(); i++) {
            if (directorio_agrupado.containsKey(((JSONObject) directorio.get(i)).get("urlServicio"))) {
                ((JSONArray) directorio_agrupado.get(((JSONObject) directorio.get(i)).get("urlServicio"))).add((JSONObject) directorio.get(i));
            } else {
                JSONArray array = new JSONArray();
                array.add(directorio.get(i));
                directorio_agrupado.put(((JSONObject) directorio.get(i)).get("urlServicio"), array);
            }
        }
        String[] urlServicios = (String[]) directorio_agrupado.keySet().toArray(new String[0]);
        for (String urlServicio : urlServicios) {
            if (urlServicio.equals(config.getPATH() + config.getDEPENDENCIA())) {
                directorio_agrupado.put(urlServicio, ComplementoInfoIntegrantes((directorio_agrupado.get(urlServicio)).toString()));
            } else {
                System.out.println(((JSONArray) directorio_agrupado.get(urlServicio)).toString());
                directorio_agrupado.put(urlServicio, (JSONArray) parser.parse(request.POST(urlServicio + "/API/BackupDirectorio/ComplementoInfoIntegrantes", ((JSONArray) directorio_agrupado.get(urlServicio)).toString())));
            }
            for (int i = 0; i < ((JSONArray) directorio_agrupado.get(urlServicio)).size(); i++) {
                integrantes.add((JSONObject) ((JSONArray) directorio_agrupado.get(urlServicio)).get(i));
            }
        }
        return integrantes;
    }

    @RequestMapping(value = "/Administracion", method = RequestMethod.GET)
    public String Administracion(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "SuperAdmin/AdministracionUsr";
    }

    @RequestMapping(value = "/Notificaciones_TestCOVID19", method = RequestMethod.GET)
    public String TestCOVID19(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "notificacion/covid19";
    }

    //--------------------Cambios front ------------------//
    @RequestMapping(value = "/ReporteHospital", method = RequestMethod.GET)
    public String ReporteHospital(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null || true) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            //System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "notificacion/reporte";
    }

    @RequestMapping(value = "/RegistroInstitucion", method = RequestMethod.GET)
    public String RegistroInstitucion(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null || true) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            //System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "login/RegistroInstitucion";
    }

//    @RequestMapping(value = "/RegistroPaciente", method = RequestMethod.GET)
//    public String RegistroPaciente(Model model, HttpServletRequest sesion) {
//        if (config.getInit() != null||true) {
//            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
//            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
//            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
//            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
//            //System.out.println(config.getPersonalizacion().get("favicon"));
//        } else {
//            System.out.println("Proyecto no inicializado");
//            return "plantilla/sinInicializar";
//        }
//        return "SuperAdmin/RegistroPaciente";
//    }
    //---------------------------------------------------------//
    @RequestMapping(value = "/API/puestos_trabajo", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray puestos_trabajo() {
        if (config.getInit() != null) {
        } else {
            System.out.println("Proyecto no inicializado");
            return new JSONArray();
        }
        return Query.execute("SELECT  * FROM puesto_trabajo;");
    }

    @RequestMapping(value = "/API/especialidades_medicas", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray especialidades_medicas() {
        if (config.getInit() != null) {
        } else {
            System.out.println("Proyecto no inicializado");
            return new JSONArray();
        }
        return Query.execute("SELECT  * FROM especialidades_medicas;");
    }

    //-------------------------------------
    @RequestMapping(value = "/prueba", method = RequestMethod.GET)
    public String prueba(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "SuperAdmin/prueba";
    }

    ///****************************************************************************/
    @RequestMapping(value = "/API/Solicitudes", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray Solicitudes() {
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return new JSONArray();
        }
        //estados de las solicitudes
        /*
        - Enviada
        - Rechazado CTC-19
        - Aceptado por CTC-19
        - Aceptado por CRUM
        - Rechazado por CRUM
        - Ingresado en UTC-19
        - No ingreso en UTC-19
         */
        // traer todas las solucitudes no atendidas 

        return new JSONArray();
    }

    @RequestMapping(value = "/API/SolicitudData/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject SolicitudData(@PathVariable String id) {
        if (config.getInit() == null) {
            System.out.println("Proyecto no inicializado");
            return new JSONObject();
        } else {
            String query = "SELECT * FROM solicitudes WHERE id = '" + id + "';";
            return Query.select(query);
        }
    }

    ///// VISTAS PARA CCB CRUM E INSTITUCIONES MEDICAS
    @RequestMapping(value = "/CCB", method = RequestMethod.GET)
    public String CCB(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            //model.addAttribute("pathRecursos","http://recursos360.ml/911" );
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            //model.addAttribute("title", config.getAliasServicio()+ " - " + config.getPersonalizacion().get("t1"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            //System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "CCB/CCB";
    }

    @RequestMapping(value = "/CRUM", method = RequestMethod.GET)
    public String CRUM(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            //model.addAttribute("pathRecursos","http://recursos360.ml/911" );
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            //model.addAttribute("title", config.getAliasServicio()+ " - " + config.getPersonalizacion().get("t1"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            //System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "CCB/CRUM";
    }

    @RequestMapping(value = "/SEUM", method = RequestMethod.GET)
    public String SEUM(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            //model.addAttribute("pathRecursos","http://recursos360.ml/911" );
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            //model.addAttribute("title", config.getAliasServicio()+ " - " + config.getPersonalizacion().get("t1"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            //System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "CCB/CRUM";
    }

    @RequestMapping(value = "/Institucion", method = RequestMethod.GET)
    public String Institucion(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
//            model.addAttribute("pathRecursos","http://recursos360.ml/911" );
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            // model.addAttribute("title", config.getAliasServicio()+ " - " + config.getPersonalizacion().get("t1"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            //System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "CCB/Institucion";
    }

    @RequestMapping(value = "/referencia_contrareferencia", method = RequestMethod.GET)
    public String referencia_contrareferencia(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
//            model.addAttribute("pathRecursos","http://recursos360.ml/911" );
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            // model.addAttribute("title", config.getAliasServicio()+ " - " + config.getPersonalizacion().get("t1"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            //System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "referencias_hospitalarias/Institucion";
    }

    @RequestMapping(value = "/vinculacion_familiar", method = RequestMethod.GET)
    public String vinculacion_familiar(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
//            model.addAttribute("pathRecursos","http://recursos360.ml/911" );
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            //model.addAttribute("title", config.getAliasServicio()+ " - " + config.getPersonalizacion().get("t1"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            //System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "CCB/llamada_familiar";
    }

    @RequestMapping(value = "/SolicitudTraslado", method = RequestMethod.GET)
    public String SolicitudTraslado(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
//            model.addAttribute("pathRecursos","http://recursos360.ml/911" );
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            //System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "CCB/SolicitudTraslado";
    }

    @RequestMapping(value = "/referencia_hospitalaria", method = RequestMethod.GET)
    public String referencia_hospitalaria(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
//            model.addAttribute("pathRecursos","http://recursos360.ml/911" );
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            //System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "referencias_hospitalarias/SolicitudTraslado";
    }

    @RequestMapping(value = "/RegistroPaciente", method = RequestMethod.GET)
    public String RegistroPaciente(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
//            model.addAttribute("pathRecursos","http://recursos360.ml/911" );
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            //System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "CA/RegistroPaciente";
    }

    @RequestMapping(value = "/SUCRE", method = RequestMethod.GET)
    public String SUCRE(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            //model.addAttribute("pathRecursos","http://recursos360.ml/911" );
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            //System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "CCB/SUCRE";
    }

    @RequestMapping(value = "/MonitoreoRemoto", method = RequestMethod.GET)
    public String MonitoreoRemoto(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            //model.addAttribute("pathRecursos","http://recursos360.ml/911" );
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            //System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "CCB/MonitoreoRemoto";
    }

    @RequestMapping(value = "/TestCovidCCB", method = RequestMethod.GET)
    public String TestCovidCCB(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            //model.addAttribute("pathRecursos","http://recursos360.ml/911" );
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            //System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "CCB/TestCovidCCB";
    }

    @RequestMapping(value = "/TestCovidPacientesCCB", method = RequestMethod.GET)
    public String TestCovidPacientesCCB(Model model, HttpServletRequest sesion) {
        if (config.getInit() != null) {
            //model.addAttribute("pathRecursos","http://recursos360.ml/911" );
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            //System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return "CCB/TestCovidPacientesCCB";
    }

    @RequestMapping(value = "/API/directorioCCB", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray directorioCCB() {
        if (config.getInit() != null) {

        } else {
            System.out.println("Proyecto no inicializado");
            return new JSONArray();
        }
        return Query.execute("SELECT idUsuario,Nombre from directorio WHERE tipo_usuario = 20 AND tipo_servicio = 30 AND (urlServicio LIKE '%cmx.sos911.ml%' OR urlServicio LIKE '%bcn.sos911.ml%') AND activo = '1';");
    }

    @RequestMapping(value = "/API/directorioCRUM", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray directorioCRUM() {
        if (config.getInit() != null) {

        } else {
            System.out.println("Proyecto no inicializado");
            return new JSONArray();
        }
        return Query.execute("SELECT idUsuario,Nombre from directorio WHERE tipo_usuario = 19 AND tipo_servicio = 29 AND (urlServicio LIKE '%cmx.sos911.ml%' OR urlServicio LIKE '%bcn.sos911.ml%') AND activo = '1';");
    }

    @RequestMapping(value = "/API/directorioSUCRE", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray directorioSUCRE() {
        if (config.getInit() != null) {

        } else {
            System.out.println("Proyecto no inicializado");
            return new JSONArray();
        }
        return Query.execute("SELECT idUsuario,Nombre from directorio WHERE tipo_usuario = 21 AND tipo_servicio = 48 AND (urlServicio LIKE '%cmx.sos911.ml%' OR urlServicio LIKE '%bcn.sos911.ml%') AND activo = '1';");
    }

    @RequestMapping(value = "/API/directorio/pacientesCCB", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray pacientesCCB() {
        if (config.getInit() != null) {

        } else {
            System.out.println("Proyecto no inicializado");
            return new JSONArray();
        }
        return Query.execute("SELECT id,estado,fecha,hora,nombre,apellidop_paciente,apellidom_paciente from solicitudes where activo = 1;");
    }

    @RequestMapping(value = "/API/referencias_hospitalarias/pacientes/{tipo_usuario}/{tipo_servicio}", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray referencias_hospitalarias_pacientes(@PathVariable("tipo_usuario") String tipo_usuario, @PathVariable("tipo_servicio") String tipo_servicio) {
        if (config.getInit() != null) {

        } else {
            System.out.println("Proyecto no inicializado");
            return new JSONArray();
        }
        JSONArray referencias = Query.execute("SELECT id_referencia_hospitalaria as id FROM eventos_referencias_hospitalarias\n"
                + " where (tipo_usuario = '" + tipo_usuario + "' and  tipo_servicio='" + tipo_servicio + "')  \n"
                + " OR (to_tipo_usuario_traslado='" + tipo_usuario + "' and to_tipo_servicio_traslado='" + tipo_servicio + "') \n"
                + " or (to_tipo_usuario_institucion='" + tipo_usuario + "' and to_tipo_servicio_institucion='" + tipo_servicio + "') \n"
                + " or (to_tipo_servicio_institucion = 0 and to_tipo_usuario_institucion = 0 and tipo_servicio_institucion is null and tipo_usuario_institucion is null)\n"
                + " or (to_tipo_servicio_traslado = 0 and to_tipo_usuario_traslado = 0 and tipo_servicio_traslado is null and tipo_usuario_traslado is null);");

        JSONArray pacientes = new JSONArray();

        for (Object referencia : referencias) {
            JSONObject r = (JSONObject) referencia;
            r = Query.select("SELECT id,estado,fecha,hora,nombre,apellidop_paciente,apellidom_paciente  from referencias_hospitalarias where id = '" + r.get("id") + "';");
            if (r != null) {
                pacientes.add(r);
            }
        }
        return pacientes;
    }

    @RequestMapping(value = "/API/directorio/pacientesCCB/ingresados", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray pacientesCCB_ingresados() {
        if (config.getInit() != null) {

        } else {
            System.out.println("Proyecto no inicializado");
            return new JSONArray();
        }
        return Query.execute("SELECT id,estado,fecha_cambio as fecha,hora,nombre,apellidop_paciente,apellidom_paciente from solicitudes where estado = 'Ingresado en UTC-19' AND activo = 1;");
    }

    @RequestMapping(value = "/API/altas/generaragenda", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject generaragenda() throws java.text.ParseException {
        String query = "SELECT id, fecha_cambio from solicitudes where estado ='Dado de Alta por UTC-19'; ";
        JSONObject resp = new JSONObject();
        JSONArray array = Query.execute(query);
        for (int j = 0; j < array.size(); j++) {

            JSONObject json = (JSONObject) array.get(j);
            resp.put(json.get("id"), new JSONObject());
            ///Agendar las 10 llamadas de monitoreo remoto 
            //llamada de 24 hrs
            int dias = 1;
            query = "INSERT INTO ccb_monitoreo_remoto (`folio`,`fecha`,`hora`,`estatus_llamada`,`fecha_llamada`) "
                    + "VALUES ('" + json.get("id") + "','" + Modelo.Escalamiento.getFecha() + "', '" + Modelo.Escalamiento.getHora() + "',"
                    + "'Programada','" + Escalamiento.getFecha(json.get("fecha_cambio").toString(), dias) + "');";
            System.out.println(query);
            System.out.println(Query.insert(query));
            //3 llamadas cada 48 hrs
            for (int i = 0; i < 3; i++) {
                dias += 2;
                query = "INSERT INTO ccb_monitoreo_remoto (`folio`,`fecha`,`hora`,`estatus_llamada`,`fecha_llamada`) "
                        + "VALUES ('" + json.get("id") + "','" + Modelo.Escalamiento.getFecha() + "', '" + Modelo.Escalamiento.getHora() + "',"
                        + "'Programada','" + Escalamiento.getFecha(json.get("fecha_cambio").toString(), dias) + "');";
                System.out.println(query);
                System.out.println(Query.insert(query));
            }
            //6 llamadas cada 7 dias 
            for (int i = 0; i < 6; i++) {
                dias += 7;
                query = "INSERT INTO ccb_monitoreo_remoto (`folio`,`fecha`,`hora`,`estatus_llamada`,`fecha_llamada`) "
                        + "VALUES ('" + json.get("id") + "','" + Modelo.Escalamiento.getFecha() + "', '" + Modelo.Escalamiento.getHora() + "',"
                        + "'Programada','" + Escalamiento.getFecha(json.get("fecha_cambio").toString(), dias) + "');";
                System.out.println(query);
                System.out.println(Query.insert(query));
            }
        }
        return resp;
    }

    @RequestMapping(value = "/API/altas/fechas_llamadas", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray fechas_llamadas() {
        String query = "SELECT DISTINCT fecha_llamada FROM ccb_monitoreo_remoto where activo = 1;";
        JSONArray fechas = Query.execute(query);
        return fechas;
    }

    @RequestMapping(value = "/API/altas/fechas_llamadas/{fecha}", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray fechas_llamadas(@PathVariable("fecha") String fecha) {
        String query = "select m.folio,m.estatus_llamada,m.hora,m.numero_llamada,s.nombre,s.apellidop_paciente, s.apellidom_paciente,"
                + "s.fecha_cambio,s.hora_cambio, s.genero,s.fecha_nacimiento,s.edad, s.comorbilidades,s.telefono_paciente,"
                + "s.nombre_responsable,s.apellidop_responsable, s.apellidom_responsable,s.telefono_contacto,s.correo_contacto,"
                + "s.nombre_doctor_responsable,s.fecha_ingreso,s.fecha_sintomas from ccb_monitoreo_remoto m, solicitudes s "
                + "where m.fecha_llamada = '" + fecha + "' and m.folio=s.id and m.activo=1;";
        JSONArray fechas = Query.execute(query);
        for (int i = 0; i < fechas.size(); i++) {
            JSONObject json = (JSONObject) fechas.get(i);
            query = "SELECT idUsuario FROM vinculacion_paciente WHERE folio = '" + json.get("folio") + "' AND activo = 1;";
            JSONObject idUsr = Query.select(query);
            if (idUsr != null) {
                json.put("idUsuario", idUsr.get("idUsuario"));
                json.put("vinculado", true);
            } else {
                json.put("vinculado", false);
            }
        }
        return fechas;
    }

    @RequestMapping(value = "/API/test/fechas_testCovid", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray fechas_testCovid() {
        String query = "SELECT DISTINCT fecha FROM personal_ccb;";// where activo = 1
        JSONArray fechas = Query.execute(query);
        query = "SELECT DISTINCT fecha_nuevaLlamada AS fecha FROM agenda_nuevas_llamada WHERE rol_usuario=1;";
        JSONArray array = Query.execute(query);
        for (int i = 0; i < array.size(); i++) {
            fechas.add(array.get(i));
        }
        return fechas;
    }

    @RequestMapping(value = "/API/test/fechas_testCovid/{fecha}", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray fechas_testCovid(@PathVariable("fecha") String fecha) {
        String query = "SELECT * FROM  personal_ccb WHERE fecha = '" + fecha + "';";

        JSONArray testCovid = Query.execute(query);
        for (int i = 0; i < testCovid.size(); i++) {
            JSONObject test = (JSONObject) testCovid.get(i);
            query = "SELECT nombre, apellido_paterno , apellido_materno,fecha_nacimiento,acceso_ccb FROM usuarios_movil "
                    + "WHERE idUsuarios_Movil = '" + test.get("idUsuario") + "';";
            JSONObject perfil = Query.select(query);
            perfil.put("img", config.getPATH() + config.getDEPENDENCIA() + "/API/ConsultarImg/perfil/" + test.get("idUsuario"));
            perfil.put("icon", config.getPATH() + config.getDEPENDENCIA() + "/API/ConsultarImg/icono/" + test.get("idUsuario"));
            test.put("Perfil", perfil);
            query = "SELECT DISTINCT fecha FROM personal_ccb WHERE idUsuario='" + test.get("idUsuario") + "';";
            JSONArray array = Query.execute(query);
            JSONArray fechas = new JSONArray();
            for (int j = 0; j < array.size(); j++) {
                JSONObject Fecha = (JSONObject) array.get(j);
                fechas.add(Fecha.get("fecha"));
            }
            test.put("fechas_test", fechas);

        }
        query = "SELECT * FROM agenda_nuevas_llamada WHERE fecha_nuevaLlamada = '" + fecha + "' AND rol_usuario = 1;";
        JSONArray nuevas_llamadas = Query.execute(query);
        for (int i = 0; i < nuevas_llamadas.size(); i++) {
            JSONObject llamada = (JSONObject) nuevas_llamadas.get(i);
            query = "SELECT fecha_nacimiento,genero,acceso_ccb FROM usuarios_movil "
                    + "WHERE idUsuarios_Movil = '" + llamada.get("idUsuario") + "';";
            JSONObject perfil = Query.select(query);
            perfil.put("img", config.getPATH() + config.getDEPENDENCIA() + "/API/ConsultarImg/perfil/" + llamada.get("idUsuario"));
            perfil.put("icon", config.getPATH() + config.getDEPENDENCIA() + "/API/ConsultarImg/icono/" + llamada.get("idUsuario"));
            perfil.put("nombre", llamada.get("nombre"));

            llamada.put("Perfil", perfil);
            testCovid.add(llamada);
        }
        return testCovid;
    }

    //Fechas y test covid de los pacientes
    @RequestMapping(value = "/API/test/fechas_testCovid_pacientes", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray fechas_testCovid_pacientes() {
        String query = "SELECT DISTINCT fecha FROM ccb_test_pacientes;";// where activo = 1
        JSONArray fechas = Query.execute(query);
        query = "SELECT DISTINCT fecha_nuevaLlamada AS fecha FROM agenda_nuevas_llamada WHERE rol_usuario=2;";
        JSONArray array = Query.execute(query);
        for (int i = 0; i < array.size(); i++) {
            fechas.add(array.get(i));
        }
        return fechas;
    }

    @RequestMapping(value = "/API/test/fechas_testCovid_pacientes/{fecha}", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray fechas_testCovid_pacientes(@PathVariable("fecha") String fecha) throws IOException, ParseException {
        String query = "SELECT * FROM  ccb_test_pacientes WHERE fecha = '" + fecha + "';";

        JSONParser parser = new JSONParser();
        JSONArray testCovid = Query.execute(query);
        JSONArray usuarios = new JSONArray();
        for (int i = 0; i < testCovid.size(); i++) {
            usuarios.add(((JSONObject) testCovid.get(i)).get("idUsuario"));
        }
        if (!usuarios.isEmpty()) {
            usuarios = (JSONArray) parser.parse(request.POST(config.getURL_CONTROLADOR() + "API/ConsultaVariosPerfiles", usuarios.toString()));
            for (int i = 0; i < testCovid.size(); i++) {
                JSONObject test = (JSONObject) testCovid.get(i);
                for (int j = 0; j < usuarios.size(); j++) {
                    if (test.get("idUsuario").toString().equals(((JSONObject) usuarios.get(j)).get("idUsuarios_Movil").toString())) {
                        test.put("Perfil", usuarios.get(j));
                        break;
                    }
                }
            }
        }
        query = "SELECT * FROM agenda_nuevas_llamada WHERE estado_llamada IS NULL AND fecha_nuevaLlamada='" + fecha + "'  AND rol_usuario = 2;";
        JSONArray nuevas_llamadas = Query.execute(query);
        for (int i = 0; i < testCovid.size(); i++) {
            usuarios.add(((JSONObject) nuevas_llamadas.get(i)).get("idUsuario"));
        }
        if (!usuarios.isEmpty()) {
            usuarios = (JSONArray) parser.parse(request.POST(config.getURL_CONTROLADOR() + "API/ConsultaVariosPerfiles", usuarios.toString()));
            for (int i = 0; i < nuevas_llamadas.size(); i++) {
                JSONObject llamada = (JSONObject) nuevas_llamadas.get(i);
                for (int j = 0; j < usuarios.size(); j++) {
                    if (llamada.get("idUsuario").toString().equals(((JSONObject) usuarios.get(j)).get("idUsuarios_Movil").toString())) {
                        llamada.put("Perfil", usuarios.get(j));
                        break;
                    }
                }
                testCovid.add(llamada);
            }
        }

        return testCovid;
    }

    @RequestMapping(value = "/pruebas_aws", method = RequestMethod.GET)
    public String pruebas_aws(Model model) {
        if (config.getInit() != null) {
            //model.addAttribute("pathRecursos","http://recursos360.ml/911" );
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            //System.out.println(config.getPersonalizacion().get("favicon"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }

        return "aws/pruebas_aws";
    }

    @RequestMapping(value = "/API/cuenta360/access_token/{id360}/{access_token}", method = RequestMethod.GET)
    public String Login(
            HttpServletRequest sesion,
            Model model,
            @PathVariable("id360") String id360,
            @PathVariable("access_token") String access_token)
            throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        JSONObject json = new JSONObject();
        json.put("access_token", access_token);
        json.put("id360", id360);
        JSONObject usuario = Request.request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/validate/access_token", json);
        if ((boolean) usuario.get("success")) {
            ControladorPOST cp = new ControladorPOST();
            usuario = cp.data_login(usuario);
            model.addAttribute("cuenta360", usuario.toString().replace("\"", "&quot;"));
        }

        return "login/access_token";
    }

    @RequestMapping(value = "/API/cuenta360/access_token/{id360}/{access_token}/{perfil}/{ruta}", method = RequestMethod.GET)
    public String Login2(
            HttpServletRequest sesion,
            Model model,
            @PathVariable("id360") String id360,
            @PathVariable("access_token") String access_token,
            @PathVariable("ruta") String ruta,
            @PathVariable("perfil") String perfil)
            throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        JSONObject json = new JSONObject();
        json.put("access_token", access_token);
        json.put("id360", id360);
        JSONObject usuario = Request.request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/validate/access_token", json);
        if ((boolean) usuario.get("success")) {
            ControladorPOST cp = new ControladorPOST();
            usuario = cp.data_login(usuario);
            usuario.put("perfil", perfil);
            usuario.put("ruta", ruta);
            model.addAttribute("cuenta360", usuario.toString().replace("\"", "&quot;"));

        }

        return "login/access_token";
    }

    /*Cambios prueba fernando*/
    @RequestMapping(value = "/API/cuenta360/access_token/{id360}/{access_token}/section/{seccion}/{tipo_usuario}/{tipo_servicio}/{tipo_area}", method = RequestMethod.GET)
    public String Login2(
            HttpServletRequest sesion,
            Model model,
            @PathVariable("id360") String id360,
            @PathVariable("access_token") String access_token,
            @PathVariable("seccion") String seccion,
            @PathVariable("tipo_usuario") String tipo_usuario,
            @PathVariable("tipo_servicio") String tipo_servicio,
            @PathVariable("tipo_area") String tipo_area)
            throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        JSONObject json = new JSONObject();
        json.put("access_token", access_token);
        json.put("id360", id360);
        JSONObject usuario = Request.request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/validate/access_token", json);
        if ((boolean) usuario.get("success")) {
            ControladorPOST cp = new ControladorPOST();
            usuario = cp.data_login(usuario);
            usuario.put("seccion", seccion);
            model.addAttribute("cuenta360", usuario.toString().replace("\"", "&quot;"));

        }

        return "login/access_token";
    }

    /**
     * **********************
     */
    @RequestMapping(value = "/API/empresas360/info/{tipo_servicio}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject empresas360_info(@PathVariable("tipo_servicio") String tipo_servicio) {
        JSONObject respuesta = respuesta(false, "Proyecto no inicializado");
        if (config.getInit() == null) {
            return respuesta;
        }
        respuesta = respuesta(false, "Institucion no registrada");
        String query = "SELECT T.tipo_usuario AS empresa , T.logotipo, T.logotipo_empresa, U.nombre AS centro_trabajo, U.direccion \n"
                + "FROM tipos_usuarios T, servicios_usuario U \n"
                + "WHERE U.idTipoUsuario = T.id AND U.id='" + tipo_servicio + "';";
        JSONObject info = Query.select(query);
        if (info != null) {
            respuesta = respuesta(true, "Informacion encontrada");
            respuesta.putAll(info);
        }

        return respuesta;
    }

    @RequestMapping(value = "/API/empresas360/perfil/{id360}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject empresas360_perfil(@PathVariable("id360") String id360) throws IOException, ParseException {
        JSONObject respuesta = respuesta(false, "Proyecto no inicializado");
        if (config.getInit() == null) {
            return respuesta;
        }
        JSONObject json = new JSONObject();
        json.put("id360", id360);

        JSONObject perfil_user = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfil360", json);
        respuesta = respuesta(false, "Ocurrio un error intentelo mas tarde.");
        if ((Boolean) perfil_user.get("success")) {
            JSONArray modulo_empleados = (JSONArray) perfil_user.get("empleados");
            if (modulo_empleados.size() > 0) {
                for (int i = 0; i < modulo_empleados.size(); i++) {
                    JSONObject empleado = (JSONObject) modulo_empleados.get(i);
                    JSONObject reporte_sintomas = Query.select("SELECT * FROM reporte_sintomas "
                            + "WHERE id360 = '" + json.get("id360") + "' "
                            + "AND tipo_usuario = '" + empleado.get("tipo_usuario") + "' "
                            + "AND tipo_servicio = '" + empleado.get("tipo_servicio") + "' ORDER BY id DESC LIMIT 1;");

                    //Solicitamos el ultimo reporte de proteccion
                    JSONObject reporte_equipo_proteccion = Query.select("SELECT * FROM reporte_equipo_proteccion "
                            + "WHERE id360 = '" + json.get("id360") + "' "
                            + "AND tipo_usuario = '" + empleado.get("tipo_usuario") + "' "
                            + "AND tipo_servicio = '" + empleado.get("tipo_servicio") + "' ORDER BY id DESC LIMIT 1;");

                    //Solicitamos la informacion de movilidad al laboral
                    JSONObject movilidad_espacio_laboral = Query.select("SELECT * FROM movilidad_espacio_laboral "
                            + "WHERE id360 = '" + json.get("id360") + "';");

                    //Solicitamos la informacion correspondiente al registro familiar
                    JSONArray registro_familiar = Query.execute("SELECT * FROM registro_familiar "
                            + "WHERE id360 = '" + json.get("id360") + "';");

                    //Solicitamos la informacion del registro familiar en grupo carso
                    JSONArray registro_familiar_grupo_carso = Query.execute("SELECT * FROM registro_familiar_grupo_carso "
                            + "WHERE id360 = '" + json.get("id360") + "';");

                    //Solicitamos la informacion correspondiente al registro de hijos
                    JSONArray registro_hijos = Query.execute("SELECT * FROM registro_hijos "
                            + "WHERE id360 = '" + json.get("id360") + "';");

                    //Solicitamos la informacion correspondiente a la empresa  ******
                    /*JSONObject info_empresa = Query.select("SELECT T.tipo_usuario AS empresa , T.logotipo, T.logotipo_empresa, "
                            + "U.nombre AS centro_trabajo, U.direccion "
                            + "FROM tipos_usuarios T, servicios_usuario U "
                            + "WHERE U.idTipoUsuario = T.id AND U.id = '" + empleado.get("tipo_servicio") + "';");*/
                    respuesta = respuesta(true, "Información encontrada");
                    respuesta.put("modulo_empleado", empleado);
                    respuesta.put("reporte_sintomas", reporte_sintomas);
                    respuesta.put("reporte_equipo_proteccion", reporte_equipo_proteccion);
                    respuesta.put("movilidad_espacio_laboral", movilidad_espacio_laboral);
                    respuesta.put("registro_familiar", registro_familiar);
                    respuesta.put("registro_familiar_grupo_carso", registro_familiar_grupo_carso);
                    respuesta.put("registro_hijos", registro_hijos);
                }
            } else {
                respuesta = respuesta(false, "No se cuenta con el modulo de empleados.");
            }

        } else {
            respuesta = respuesta(false, "Usuario no encontrado.");
        }
        return respuesta;

//        
//        respuesta = respuesta(true, "Información encontrada");
//        String query = "";
//        query = "SELECT * FROM movilidad_espacio_laboral WHERE id360='" + id360 + "';";
//        respuesta.put("movilidad_espacio_laboral", Query.execute(query));
//
//        query = "SELECT * FROM registro_familiar WHERE id360='" + id360 + "';";
//        respuesta.put("registro_familiar", Query.execute(query));
//
//        query = "SELECT * FROM registro_familiar_grupo_carso WHERE id360='" + id360 + "';";
//        respuesta.put("registro_familiar_grupo_carso", Query.execute(query));
//
//        query = "SELECT * FROM registro_hijos WHERE id360='" + id360 + "';";
//        respuesta.put("registro_familiar_hijos", Query.execute(query));
//
//        query = "SELECT * FROM reporte_sintomas WHERE id360='" + id360 + "' ORDER BY id DESC LIMIT 1;";
//        respuesta.put("ultimo_reporte_sintomas", Query.execute(query));
//
//        query = "SELECT * FROM reporte_equipo_proteccion WHERE id360='" + id360 + "' ORDER BY id DESC LIMIT 1;";
//        respuesta.put("ultimo_reporte_equipo_proteccion", Query.execute(query));
//
//        return respuesta;
    }

    /**
     * *
     *
     * API para modificar los modulo de forma local
     *
     *
     *
     */
    @RequestMapping(value = "/API/ListarUsuarios", method = RequestMethod.GET, consumes = "application/json")
    @ResponseBody
    public JSONArray ListarUsuarios() throws ParseException, IOException {
//        System.out.println(json);
        String query = "SELECT * FROM usuario_sys WHERE estatus = 1;";
        JSONArray url = Query.execute(query);

        return url;
    }

    @RequestMapping(value = "/API/ListarModulos", method = RequestMethod.GET, consumes = "application/json")
    @ResponseBody
    public JSONArray ListarModulos() throws ParseException, IOException {
//        System.out.println(json);
        String query = "SELECT * FROM modulos";
        JSONArray url = Query.execute(query);
        return url;
    }

    @RequestMapping(value = "/API/plataforma360/info/{tipo_servicio}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject plataforma360_info(@PathVariable("tipo_servicio") String tipo_servicio) {
        JSONObject respuesta = respuesta(false, "Proyecto no inicializado");
        if (config.getInit() == null) {
            return respuesta;
        }

        String query = "SELECT T.tipo_usuario, S.nombre FROM tipos_usuarios T, servicios_usuario S WHERE S.idTipoUsuario = T.id AND S.id='" + tipo_servicio + "';";
        respuesta = Query.select(query);

        return respuesta;
    }

    @RequestMapping(value = "/API/JSON/RegistroPlataforma360", method = RequestMethod.GET)
    @ResponseBody
    public String RegistroPlataforma360() throws IOException {

        return request.GET(config.getURL_CONTROLADOR() + "API/JSON/RegistroPlataforma360");
    }

    @RequestMapping(value = "/RegistroPlantillaLaboral", method = RequestMethod.GET)
    private String RegistroPlantillaLaboral(HttpServletRequest sesion, Model model) throws ParseException, IOException {
        if (config.getInit() != null) {
            model.addAttribute("pathRecursos", config.getServer().get("recursos"));
            model.addAttribute("config", config.getPersonalizacion().toString().replace("\"", "&quot;"));
            model.addAttribute("FAVICON", config.getPersonalizacion().get("favicon"));
            model.addAttribute("title", "Claro360  - " + config.getPersonalizacion().get("t1"));
        } else {
            System.out.println("Proyecto no inicializado");
            return "plantilla/sinInicializar";
        }
        return ValidarIP.Validacion_ip_publica(sesion, model, "SuperAdmin/registro_plantilla");
        //return "Login";
    }

    @RequestMapping(value = "/API/empresas360/catalogo_areas", method = RequestMethod.GET)
    @ResponseBody
    private JSONArray catalogo_areas() throws ParseException, IOException {
        return Query.execute("SELECT * FROM catalogo_areas;");
    }

    @RequestMapping(value = "/API/empresas360/catalogo_lineamientos", method = RequestMethod.GET)
    @ResponseBody
    private JSONArray catalogo_lineamientos() throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        return (JSONArray) parser.parse(request.GET("https://seguridadsanitaria.claro360.com/lineamientos/API/GET/reporte_evidencias/categorias"));
    }

    @RequestMapping(value = "/API/empresas360/info_empresa/{tipo_usuario}", method = RequestMethod.GET)
    @ResponseBody
    private JSONObject info_empresa(@PathVariable("tipo_usuario") String tipo_usuario) {

        return Query.select("SELECT * FROM tipos_usuarios WHERE id='" + tipo_usuario + "';");
    }

    @RequestMapping(value = "/API/empresas360/info_sucursal/{servicios_usuario}", method = RequestMethod.GET)
    @ResponseBody
    private JSONObject info_sucursal(@PathVariable("servicios_usuario") String servicios_usuario) {

        return Query.select("SELECT * FROM servicios_usuario WHERE id='" + servicios_usuario + "';");
    }

    @RequestMapping(value = "/API/empresas360/info_sucursales/{tipo_usuario}", method = RequestMethod.GET)
    @ResponseBody
    private JSONArray info_sucursales(@PathVariable("tipo_usuario") String tipo_usuario) {

        return Query.execute("SELECT * FROM servicios_usuario WHERE idTipoUsuario='" + tipo_usuario + "';");
    }

    @RequestMapping(value = "/API/empresas360/info_area/{tipo_area}", method = RequestMethod.GET)
    @ResponseBody
    private JSONObject info_area(@PathVariable("tipo_area") String tipo_area) {

        return Query.select("SELECT * FROM tipo_area WHERE id='" + tipo_area + "';");
    }

    @RequestMapping(value = "/API/empresas360/listado_areas/{tipo_usuario}/{tipo_servicio}", method = RequestMethod.GET)
    @ResponseBody
    private JSONArray listado_areas(@PathVariable("tipo_usuario") String tipo_usuario, @PathVariable("tipo_servicio") String tipo_servicio) {
        String query = "SELECT A.id,A.tipo_usuario,A.tipo_servicio, A.area, S.nombre FROM tipo_area A, servicios_usuario S "
                + "WHERE A.tipo_usuario=S.idTipoUsuario AND A.tipo_servicio=S.id AND S.activo='1' AND A.tipo_usuario='" + tipo_usuario + "' AND A.tipo_servicio='" + tipo_servicio + "';";
        if (tipo_servicio.equals("0")) {
            query = "SELECT A.id,A.tipo_usuario,A.tipo_servicio, A.area, S.nombre FROM tipo_area A, servicios_usuario S "
                    + "WHERE A.tipo_usuario=S.idTipoUsuario AND A.tipo_servicio=S.id AND S.activo='1' AND A.tipo_usuario='" + tipo_usuario + "';";
        }
        return Query.execute(query);
    }

    @RequestMapping(value = "/API/lineamientos/info_empresa/{tipo_usuario}", method = RequestMethod.GET)
    @ResponseBody
    private String info_empresa_lineamientos(@PathVariable("tipo_usuario") String tipo_usuario) throws IOException {

        return request.GET("https://seguridadsanitaria.claro360.com/lineamientos/API/lineamientos/info_empresa/" + tipo_usuario);
    }

    @RequestMapping(value = "/API/lineamientos/listado_sucursales/{tipo_usuario}", method = RequestMethod.GET)
    @ResponseBody
    private String listado_sucursales_lineamientos(@PathVariable("tipo_usuario") String tipo_usuario) throws IOException {

        return request.GET("https://seguridadsanitaria.claro360.com/lineamientos/API/lineamientos/listado_sucursales/" + tipo_usuario);
    }

    @RequestMapping(value = "/API/lineamientos/info_sucursal/{servicios_usuario}", method = RequestMethod.GET)
    @ResponseBody
    private String info_sucursal_lineamientos(@PathVariable("servicios_usuario") String servicios_usuario) throws IOException {

        return request.GET("https://seguridadsanitaria.claro360.com/lineamientos/API/lineamientos/info_sucursal/" + servicios_usuario);
    }

    /**
     * ************ modulo 1***************
     */
    /**
     * *****************************************************
     *******************************************************
     *
     * SERVICIO WEB PARA OBTENER UN PARÁMETRO
     *
     *******************************************************
     *******************************************************
     */
    /*
        Servicio para obtener toda la información de un registro
     */
    @RequestMapping(value = "/API/empresas360/obtenData/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject obtenData(
            @PathVariable("id") String id
    ) {

        JSONObject response = respuesta(false, "Error en la petición");

        String query = "SELECT visibilidad_app, vinculacion, lista_blanca, token_vinculacion, edicion_individual FROM servicios_usuario WHERE id = " + id;

        JSONObject data = Query.select(query);
        if (data != null) {
            response = respuesta(true, "ok");
            response.putAll(data);
        } else {
            response = respuesta(false, "Sin informacion");
        }

        return response;
    }

    /*
        Servicio para obtener la matricula de usuarios que pertenecen a una empresa
     */
    @RequestMapping(value = "/API/empresas360/obtenMatricula/{idEmpresa}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject obtenMatricula(
            @PathVariable("idEmpresa") String idEmpresa
    ) {
        JSONObject response = respuesta(false, "Error en la petición");

        String query = "SELECT id,nombre,edicion_individual FROM servicios_usuario WHERE id_empresa = " + idEmpresa + " AND idTipoUsuario != 0 ORDER BY nombre";

        JSONArray data = Query.execute(query);
        if (data != null) {
            response = respuesta(true, "ok");
            response.put("data", data);
        } else {
            response = respuesta(false, "Sin informacion");
        }
        return response;
    }

    @RequestMapping(value = "/API/referencia_hospitalaria/directorio_referencias/{tipo_servicio}", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray directorio_referencias(@PathVariable("tipo_servicio") String tipo_servicio) {
        JSONObject response = respuesta(false, "Error en la petición");
        String query = "SELECT S.id AS tipo_servicio, S.idTipoUsuario AS tipo_usuario, S.nombre FROM referencias R, servicios_usuario S WHERE R.hospitalaria=1 AND R.tipo_servicio='" + tipo_servicio + "' AND S.id=R.to_tipo_servicio;";
        return Query.execute(query);
    }

    @RequestMapping(value = "/API/referencia_hospitalaria/directorio_traslado/{tipo_servicio}", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray directorio_traslado(@PathVariable("tipo_servicio") String tipo_servicio) {
        JSONObject response = respuesta(false, "Error en la petición");
        String query = "SELECT S.id AS tipo_servicio, S.idTipoUsuario AS tipo_usuario, S.nombre FROM referencias R, servicios_usuario S WHERE R.traslado=1 AND R.tipo_servicio='" + tipo_servicio + "' AND S.id=R.to_tipo_servicio;";
        return Query.execute(query);
    }

    /**
     * ******************************************* Referencias Hospitalarias
     * *********************************************************************
     */
    @RequestMapping(value = "/API/referencias_hospitalarias/instituciones_traslado", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray instituciones_traslado() {
        String query = "SELECT * FROM servicios_usuario WHERE idTipoUsuario = 19 OR idTipoUsuario = 21;";
        return Query.execute(query);
    }

    @RequestMapping(value = "/API/referencias_hospitalarias/personal_traslado/{tipo_usuario}/{tipo_servicio}", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray personal_traslado(@PathVariable("tipo_usuario") String tipo_usuario, @PathVariable("tipo_servicio") String tipo_servicio) {
        String query = "SELECT idUsuario,Nombre from directorio WHERE tipo_usuario = '" + tipo_usuario + "' AND tipo_servicio = '" + tipo_servicio + "';";
        return Query.execute(query);
    }

    /*
        Servicio para traer las notas de un tipo de usuario
     */
    @RequestMapping(value = "/API/empresas360/get_notas/{id360}", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray empresas360_get_notas(@PathVariable("id360") String id360) {
        String query = "SELECT id AS id_nota,id360,tipo_usuario,tipo_servicio,tipo_area,titulo,nota "
                + "from notas WHERE id360 = '" + id360 + "' AND activo = 1;";
        return Query.execute(query);
    }

    @RequestMapping(value = "/API/catalogo_cni", method = RequestMethod.GET)
    @ResponseBody
    public String catalogo_cni() throws IOException {
        return request.GET(config.getURL_CONTROLADOR() + "API/catalogo_cni");
    }

    @RequestMapping(value = "/API/GET/reporte_evidencias/categorias", method = RequestMethod.GET)
    @ResponseBody
    public String categorias_reporte_evidencias() throws IOException {
        return request.GET("https://seguridadsanitaria.claro360.com/lineamientos/API/GET/reporte_evidencias/categorias");
    }

}
