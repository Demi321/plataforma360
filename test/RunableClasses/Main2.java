/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Config.config;
import Modelo.Query;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.util.JSONPObject;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author moises
 */
public class Main2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
//        String text = "@RequestMapping(value = \"/comunicacion\", method = RequestMethod.POST)\n" +
//"    public String comunicacion(Model model, @RequestBody JSONObject json) throws ParseException, IOException {\n" +
//"        if (config.getInit() != null) {\n" +
//"            model.addAttribute(\"pathRecursos\", config.getServer().get(\"recursos\"));\n" +
//"            model.addAttribute(\"config\", config.getPersonalizacion().toString().replace(\"\\\"\", \"&quot;\"));\n" +
//"            model.addAttribute(\"FAVICON\", config.getPersonalizacion().get(\"favicon\"));\n" +
//"            model.addAttribute(\"title\", \"Claro360  - \" + config.getPersonalizacion().get(\"t1\"));\n" +
//"\n" +
//"            model.addAttribute(\"id_menu\", json.get(\"id_menu\"));\n" +
//"            model.addAttribute(\"id_usuario\", json.get(\"id_usuario\"));\n" +
//"            model.addAttribute(\"tipo_usuario\", json.get(\"tipo_usuario\"));\n" +
//"            model.addAttribute(\"tipo_servicio\", json.get(\"tipo_servicio\"));\n" +
//"            model.addAttribute(\"tipo_area\", json.get(\"tipo_area\"));\n" +
//"            model.addAttribute(\"alias\", json.get(\"alias\"));\n" +
//"            model.addAttribute(\"icono\", json.get(\"icono\"));\n" +
//"            model.addAttribute(\"categoria\", json.get(\"categoria\"));\n" +
//"        }\n" +
//"        return \"empresa/comunicacion\";\n" +
//"    }";
//        JSONParser parser = new JSONParser();
//        JSONArray array = (JSONArray)parser.parse("[\"Registrar Empresa\",\"Dashboard\",\"Estadística Global \",\"Salud en el Trabajo \",\"Recursos Humanos \",\"Administración y Finanzas \",\"Archivo\",\"Mis Reportes\",\"Uso de Cubrebocas\",\"Balanza\",\"Contabilidad\",\"Planeación Financiera\",\"Impuestos\",\"Estadística Global \",\"Salud en el Trabajo \",\"Recursos Humanos \",\"Administración y Finanzas \",\"Estadística Global \",\"Salud en el Trabajo \",\"Recursos Humanos \",\"Administración y Finanzas \",\"Mapa\"]");
//        //System.out.println(array);
//        for (int i = 0; i < array.size(); i++) {
//           String nombre = array.get(i).toString();
//           nombre = Normalizer.normalize(nombre, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replace(" ", "").toLowerCase();
//            //System.out.println(nombre);
//            System.out.println(text.replace("comunicacion", nombre));
//            
//        }
//JSONParser parser = new JSONParser();
//    JSONArray array = (JSONArray) parser.parse("[87,88]");
//        System.out.println(array);
//        for (int i = 0; i < array.size(); i++) {
//            System.out.println(array.get(i));
//            
//        }
        JSONObject menu = new JSONObject();
        menu.put("tipo_usuario", "124,124");
        menu.put("tipo_servicio", "3064,3065");
        menu.put("tipo_area", menu.get("fg"));
        String tipo_usuario = menu.get("tipo_usuario") != null ? !menu.get("tipo_usuario").toString().equals("")?menu.get("tipo_usuario").toString() : "0" : "0";
        String tipo_servicio = menu.get("tipo_servicio") != null ? !menu.get("tipo_servicio").toString().equals("")?menu.get("tipo_servicio").toString() : "0" : "0";
        String tipo_area = menu.get("tipo_area") != null ? !menu.get("tipo_area").toString().equals("")?menu.get("tipo_area").toString():"0" : "0";
        
        System.out.println(tipo_usuario);
        System.out.println(tipo_servicio);
        System.out.println(tipo_area);
        
        JSONParser parser = new JSONParser();
        JSONArray array_tipo_usuario = (JSONArray) parser.parse((Arrays.toString(tipo_usuario.split(","))));
        JSONArray array_tipo_servicio = (JSONArray) parser.parse((Arrays.toString(tipo_servicio.split(","))));
        JSONArray array_tipo_area = (JSONArray) parser.parse((Arrays.toString(tipo_area.split(","))));
        System.out.println(array_tipo_usuario);
        System.out.println(array_tipo_servicio);
        System.out.println(array_tipo_area);
    }

}
