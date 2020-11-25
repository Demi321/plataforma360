/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Config.config;
import com.google.gson.Gson;
import static java.lang.Float.parseFloat;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Vostro Placas
 */
public class ReporteDAO {

    public static boolean agregarReporte(JSONObject reporte) {
        boolean agregado = false;
        String query = null;
        if (reporte.containsKey("tipo_usuario") && reporte.containsKey("tipo_servicio")) {
            query = "INSERT INTO `reporte_llamadas` (`idLlamada`, `tipoLugar`, `numPiso`, `descripcionLugar`, `temergencia`, `prioridad`, "
                    + "`reporte`, `razonamiento`, `folioexterno`,`hora`,`fecha`,`bitacora`,`tipo_usuario`,`tipo_servicio`) "
                    + "VALUES "
                    + "('" + reporte.get("idLlamada") + "', '" + reporte.get("tipoLugar") + "', '"
                    + reporte.get("numPiso") + "', '" + reporte.get("descripcionLugar") + "', '"
                    + reporte.get("temergencia") + "', '" + reporte.get("prioridad") + "', '"
                    + reporte.get("reporte") + "', '" + reporte.get("razonamiento") + "', '"
                    + reporte.get("folioexterno") + "', '" + reporte.get("hora") + "', '"
                    + reporte.get("fecha") + "', '" + reporte.get("bitacora") + "', "
                    + "'" + reporte.get("tipo_usuario") + "', '" + reporte.get("tipo_servicio") + "');";
        } else {
            query = "INSERT INTO `reporte_llamadas` (`idLlamada`, `tipoLugar`, `numPiso`, `descripcionLugar`, `temergencia`, `prioridad`, "
                    + "`reporte`, `razonamiento`, `folioexterno`,`hora`,`fecha`,`bitacora`) "
                    + "VALUES "
                    + "('" + reporte.get("idLlamada") + "', '" + reporte.get("tipoLugar") + "', '"
                    + reporte.get("numPiso") + "', '" + reporte.get("descripcionLugar") + "', '"
                    + reporte.get("temergencia") + "', '" + reporte.get("prioridad") + "', '"
                    + reporte.get("reporte") + "', '" + reporte.get("razonamiento") + "', '"
                    + reporte.get("folioexterno") + "', '" + reporte.get("hora") + "', '"
                    + reporte.get("fecha") + "', '" + reporte.get("bitacora") + "');";
        }
        if (Query.insert(query) > 0) {
            agregado = true;
        } else {
            System.out.println("Error en agregarReporte de " + config.getDEPENDENCIA());
        }

        if (reporte.containsKey("tipo_usuario") && reporte.containsKey("tipo_servicio")) {
            query = "UPDATE `registro_llamadas` SET `bitacora`='" + reporte.get("bitacora") + "' "
                    + "WHERE `idRegistro_Llamadas`='" + reporte.get("idLlamada") + "' AND tipo_usuario = '" + reporte.get("tipo_usuario") + "' "
                    + "AND tipo_servicio = '" + reporte.get("tipo_servicio") + "';";
            Query.update(query);
        } else {
            query = "UPDATE `registro_llamadas` SET `bitacora`='" + reporte.get("bitacora") + "' WHERE `idRegistro_Llamadas`='" + reporte.get("idLlamada") + "';";
            Query.update(query);
        }
        return agregado;
    }

    public static boolean GuardarLugar(ReporteVO reporte) {
        boolean agregado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

//            System.out.println(c);
//            System.out.println(con);
            if (con != null) {

                Statement st;
                st = con.createStatement();

                st.executeUpdate("INSERT INTO  `lugares_registrados` ("
                        + "`cp`, `x1`, `x2`, `y1`, `y2`, `z`, `tipoLugar`, `numPiso`, `descripcionLugar`) "
                        + "VALUES "
                        + "('" + reporte.getCp() + "', '" + reporte.getIzquierda() + "', '" + reporte.getDerecha() + "', '" + reporte.getInferior() + "', '" + reporte.getSuperior() + "', '" + reporte.getAltura() + "', '" + reporte.getTipoLugar() + "', '" + reporte.getNumPiso() + "', '" + reporte.getDescripcionLugar() + "');");
                agregado = true;
                st.close();
            }
            c.cerrarConexion();
        } catch (SQLException e) {
            agregado = false;
        }
        return agregado;
    }

    public static ReporteVO BuscarLugarAntecedente(RegistroLlamadasVO coordenadas) {

        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {

                double R = 6372795.477598;
                double rad = Math.PI / 180;
                double LatA = rad * parseFloat(coordenadas.getLatitud());
                double LonA = rad * parseFloat(coordenadas.getLongitud());
                //Definimos margen de error en la altitud
                int margen = 10;
                double d_min = 0;
                int i = 0;
                ReporteVO reporte = null;
                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * "
                        + "FROM lugares_registrados "
                        + "where y2<" + coordenadas.getLatitud() + " "
                        + "AND y1>" + coordenadas.getLatitud() + " "
                        + "AND x2 > " + coordenadas.getLongitud() + " "
                        + "AND x1 < " + coordenadas.getLongitud() + " "
                        + "AND z+" + margen + ">" + coordenadas.getAltitud() + " AND z-" + margen + "<" + coordenadas.getAltitud() + ";");
                while (rs.next()) {
                    double LatB = rad * ((rs.getFloat("y1") + rs.getFloat("y2")) / 2);
                    double LonB = rad * ((rs.getFloat("x1") + rs.getFloat("x2")) / 2);
                    double d = R * Math.acos(Math.sin(LatA) * Math.sin(LatB) + Math.cos(LatA) * Math.cos(LatB) * Math.cos(LonA - LonB));

                    if (d_min == 0 || d < d_min) {
                        d_min = d;
                        reporte = new ReporteVO(rs.getString("tipoLugar"), rs.getString("numPiso"), rs.getString("descripcionLugar"), rs.getFloat("y2"), rs.getFloat("y1"), rs.getFloat("x2"), rs.getFloat("x1"), rs.getFloat("z"));
                    }
                    i++;
                    rs.absolute(i);
//tipoLugar, String numPiso, String descripcionLugar, float superior, float inferior, float derecha, float izquierda, int altura 
//ReporteVO reporte = new ReporteVO(rs.getString("tipoLugar"),rs.getString("numPiso "),rs.getString("descripcionLugar"),rs.getFloat("superior"),rs.getFloat("inferior"),rs.getFloat("derecha"),rs.getFloat("izquierda"),rs.getInt("altura"));
                }

                st.close();
                return reporte;
            }
            conn.cerrarConexion();
        } catch (SQLException e) {

        }

        return null;

    }

    public static String reporteCiudadano(String json) throws ParseException {
        System.out.println(json);
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        String query = "";
        System.out.println("jsonObj");
        System.out.println(jsonObj.toString());
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if (con != null) {
                Statement st/*, st2*/;
                st = con.createStatement();
                //st2 = con.createStatement();
                if (jsonObj.get("anonimo").equals("true")) {
                    jsonObj.put("idUsuario_Movil", null);
                }
                if (!jsonObj.containsKey("id")) {
                    jsonObj.put("id", null);
                    jsonObj.put("Incidente", null);
                    jsonObj.put("Prioridad", null);
                }
                query = "INSERT INTO  `reporte_ciudadano` ("
                        //`idReporte_Ciudadano`,
                        + "`idReporte_Ciudadano`,`lat`, `lng`, `reporte`, `fecha`, `hora`, `Usuarios_Movil_idUsuarios_Movil`, `anonimo`, `estado`,`img`,`id`,`tipo_emergencia`,`prioridad`) "
                        + "VALUES "
                        + "('"
                        + jsonObj.get("idHistorial_reportes") + "', '"
                        + jsonObj.get("lat") + "', '"
                        + jsonObj.get("lng") + "', '"
                        + jsonObj.get("reporte") + "', '"
                        + jsonObj.get("fecha") + "', '"
                        + jsonObj.get("hora") + "', '"
                        + jsonObj.get("idUsuario_Movil") + "', '"
                        + jsonObj.get("anonimo")
                        + "', 'enviado','"
                        + jsonObj.get("img") + "', '"
                        + jsonObj.get("id") + "', '"
                        + jsonObj.get("Incidente") + "', '"
                        + jsonObj.get("Prioridad")
                        + "');";
                System.out.println(query);
                st = con.createStatement();
                st.executeUpdate(query);
                //String query3 = "SELECT LAST_INSERT_ID() AS idReporteCiudadano";
                //System.out.println(query3);
                //ResultSet rs = st.executeQuery(query3);

                //st = con.createStatement();
                //if (rs.next()) {
                //    String query2="SELECT estado FROM reporte_ciudadano WHERE idReporte_Ciudadano = \"" + rs.getString("idReporteCiudadano") + "\" ;";
                //    System.out.println(query2);
                //    ResultSet rs2 = st.executeQuery(query2);
                //    if (rs2.next()) {
                //        System.out.println("ok");
                //    }
                //}
                //System.out.println("id:  " + jsonObj.get("id"));
//                    if (jsonObj.get("id") != null && !jsonObj.get("id").toString().equals("")) {
//                        query = "INSERT INTO  `reporte_ciudadano` ("
//                                //`idReporte_Ciudadano`,
//                                + "`idReporte_Ciudadano`,`lat`, `lng`, `reporte`, `fecha`, `hora`, `anonimo`, `estado`, `img`, `id`, `tipo_emergencia`, `prioridad`) "
//                                + "VALUES "
//                                + "('"
//                                + jsonObj.get("idHistorial_reportes") + "', '"
//                                + jsonObj.get("lat") + "', '"
//                                + jsonObj.get("lng") + "', '"
//                                + jsonObj.get("reporte") + "', '"
//                                + jsonObj.get("fecha") + "', '"
//                                + jsonObj.get("hora") + "', '"
//                                + jsonObj.get("anonimo")
//                                + "', 'enviado','"
//                                + jsonObj.get("img") + "', '"
//                                + jsonObj.get("id") + "', '"
//                                + jsonObj.get("Incidente") + "', '"
//                                + jsonObj.get("Prioridad")
//                                + "');";
//                        st.executeUpdate(query);
//                        //ResultSet rs = st.executeQuery("SELECT idReporte_Ciudadano, estado FROM reporte_ciudadano WHERE lat = \"" + jsonObj.get("lat") + "\" AND lng = \"" + jsonObj.get("lng") + "\" AND fecha = \"" + jsonObj.get("fecha") + "\" AND hora = \"" + jsonObj.get("hora") + "\" ;");
//                        ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID() AS idReporteCiudadano");
//                        st = con.createStatement();
//                        if (rs.next()) {
//                            ResultSet rs2 = st.executeQuery("SELECT estado FROM reporte_ciudadano WHERE idReporte_Ciudadano = \"" + rs.getString("idReporteCiudadano") + "\" ;");
//                            if (rs2.next()) {
//                                return "{\"folio\":\"" + rs.getString("idReporteCiudadano") + "\", \"estado\":\"" + rs2.getString("estado") + "\"}";
//                            }
//                        }
//
//                    } else {
//                        query = "INSERT INTO  `reporte_ciudadano` ("
//                                //`idReporte_Ciudadano`,
//                                + "`idReporte_Ciudadano`,`lat`, `lng`, `reporte`, `fecha`, `hora`, `anonimo`, `estado`, `img`) "
//                                + "VALUES "
//                                + "('"
//                                + jsonObj.get("idHistorial_reportes") + "', '"
//                                + jsonObj.get("lat") + "', '"
//                                + jsonObj.get("lng") + "', '"
//                                + jsonObj.get("reporte") + "', '"
//                                + jsonObj.get("fecha") + "', '"
//                                + jsonObj.get("hora") + "', '"
//                                + jsonObj.get("anonimo")
//                                + "', 'enviado','"
//                                + jsonObj.get("img") + "');";
//                        st.executeUpdate(query);
//                        //ResultSet rs = st.executeQuery("SELECT idReporte_Ciudadano, estado FROM reporte_ciudadano WHERE lat = \"" + jsonObj.get("lat") + "\" AND lng = \"" + jsonObj.get("lng") + "\" AND fecha = \"" + jsonObj.get("fecha") + "\" AND hora = \"" + jsonObj.get("hora") + "\" ;");
//                        ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID() AS idReporteCiudadano");
//                        st = con.createStatement();
//                        if (rs.next()) {
//                            ResultSet rs2 = st.executeQuery("SELECT estado FROM reporte_ciudadano WHERE idReporte_Ciudadano = \"" + rs.getString("idReporteCiudadano") + "\" ;");
//                            if (rs2.next()) {
//                                return "{\"folio\":\"" + rs.getString("idReporteCiudadano") + "\", \"estado\":\"" + rs2.getString("estado") + "\"}";
//                            }
//                        }
//                    }
//                } else {
//                    if (jsonObj.get("id") != null && !jsonObj.get("id").toString().equals("")) {
//                        query = "INSERT INTO  `reporte_ciudadano` ("
//                                //`idReporte_Ciudadano`,
//                                + "`idReporte_Ciudadano`,`lat`, `lng`, `reporte`, `fecha`, `hora`, `Usuarios_Movil_idUsuarios_Movil`, `anonimo`, `estado`,`img`,`id`,`tipo_emergencia`,`prioridad`) "
//                                + "VALUES "
//                                + "('"
//                                + jsonObj.get("idHistorial_reportes") + "', '"
//                                + jsonObj.get("lat") + "', '"
//                                + jsonObj.get("lng") + "', '"
//                                + jsonObj.get("reporte") + "', '"
//                                + jsonObj.get("fecha") + "', '"
//                                + jsonObj.get("hora") + "', '"
//                                + jsonObj.get("idUsuario_Movil") + "', '"
//                                + jsonObj.get("anonimo")
//                                + "', 'enviado','"
//                                + jsonObj.get("img") + "', '"
//                                + jsonObj.get("id") + "', '"
//                                + jsonObj.get("Incidente") + "', '"
//                                + jsonObj.get("Prioridad")
//                                + "');";
//                        st.executeUpdate(query);
//                        ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID() AS idReporteCiudadano");
//                        st = con.createStatement();
//                        if (rs.next()) {
//                            ResultSet rs2 = st.executeQuery("SELECT estado FROM reporte_ciudadano WHERE idReporte_Ciudadano = \"" + rs.getString("idReporteCiudadano") + "\" ;");
//                            if (rs2.next()) {
//                                return "{\"folio\":\"" + rs.getString("idReporteCiudadano") + "\", \"estado\":\"" + rs2.getString("estado") + "\"}";
//                            }
//                        }
//                    } else {
//                        query = "INSERT INTO  `reporte_ciudadano` ("
//                                //`idReporte_Ciudadano`,
//                                + "`idReporte_Ciudadano`,`lat`, `lng`, `reporte`, `fecha`, `hora`, `Usuarios_Movil_idUsuarios_Movil`, `anonimo`, `estado`,`img`) "
//                                + "VALUES "
//                                + "('"
//                                + jsonObj.get("idHistorial_reportes") + "', '"
//                                + jsonObj.get("lat") + "', '"
//                                + jsonObj.get("lng") + "', '"
//                                + jsonObj.get("reporte") + "', '"
//                                + jsonObj.get("fecha") + "', '"
//                                + jsonObj.get("hora") + "', '"
//                                + jsonObj.get("idUsuario_Movil") + "', '"
//                                + jsonObj.get("anonimo")
//                                + "', 'enviado','"
//                                + jsonObj.get("img")
//                                + "');";
//                        st.executeUpdate(query);
//                        ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID() AS idReporteCiudadano");
//                        st = con.createStatement();
//                        if (rs.next()) {
//                            ResultSet rs2 = st.executeQuery("SELECT estado FROM reporte_ciudadano WHERE idReporte_Ciudadano = \"" + rs.getString("idReporteCiudadano") + "\" ;");
//                            if (rs2.next()) {
//                                return "{\"folio\":\"" + rs.getString("idReporteCiudadano") + "\", \"estado\":\"" + rs2.getString("estado") + "\"}";
//                            }
//                        }
//                    }
//                }
                st.close();
                return "{\"folio\":\"" + jsonObj.get("idHistorial_reportes") + "\", \"estado\":\"enviado\"}";
            }
            conn.cerrarConexion();
        } catch (Exception e) {
            System.out.println("ERROR reporteCiudadano" + config.getDEPENDENCIA());
            System.out.println(e);
        }
        return "";
    }
//    public static String IDreporteCiudadano(String json) throws ParseException {
//        JSONParser parser = new JSONParser();
//        JSONObject jsonObj = (JSONObject) parser.parse(json);
//        try {
//            Conexion conn = new Conexion();
//            Connection con = conn.getConexion();
//            if (con != null) {
//                Statement st;
//                st = con.createStatement();
//                ResultSet rs = st.executeQuery("SELECT * FROM reporte_ciudadano where Usuarios_Movil_idUsuarios_Movil = \"" + jsonObj.get("idUsuario_Movil") + "\";");
//                //System.out.println("SELECT * FROM reporte_ciudadano where Usuarios_Movil_idUsuarios_Movil = \"" + jsonObj.get("idUsuario_Movil") + "\";");
//                String respuesta = "{\"reportes\":[";
//                String arrayJson = "";
//                System.out.println("::: Aqui :::");
//
//                while (rs.next()) {
//                    arrayJson = arrayJson + "{\"idReporte\":\"" + rs.getString("idReporte_Ciudadano") + "\","
//                            + "\"lat\":\"" + rs.getDouble("lat") + "\","
//                            + "\"lng\":\"" + rs.getDouble("lng") + "\","
//                            + "\"reporte\":\"" + rs.getString("reporte") + "\","
//                            + "\"fecha\":\"" + rs.getDate("fecha") + "\","
//                            + "\"hora\":\"" + rs.getString("hora") + "\","
//                            + "\"idUsuario_Movil\":\"" + rs.getString("Usuarios_Movil_idUsuarios_Movil") + "\","
//                            + "\"anonimo\":\"" + rs.getString("anonimo") + "\","
//                            + "\"estado\":\"" + rs.getString("estado") + "\","
//                            + "\"img\":\"data:image/png;base64," + rs.getString("img")+ "\"},";
//                }
//                arrayJson = arrayJson.substring(0, arrayJson.length() - 1);
//                respuesta = respuesta + arrayJson + "]}";
//                st.close();
//                return respuesta;
//
//            }
//        } catch (Exception e) {
//        }
//
//        return "";
//    }

    public static String IDreporteCiudadano(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if (con != null) {
                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM reporte_ciudadano where Usuarios_Movil_idUsuarios_Movil = \"" + jsonObj.get("idUsuario_Movil") + "\";");
                String respuesta = "{\"reportes\":[";
                String arrayJson = "";
                System.out.println("::: Aqui :::");

                while (rs.next()) {
                    arrayJson = arrayJson + "{\"idReporte\":\"" + rs.getString("idReporte_Ciudadano") + "\","
                            + "\"lat\":\"" + rs.getDouble("lat") + "\","
                            + "\"lng\":\"" + rs.getDouble("lng") + "\","
                            + "\"reporte\":\"" + rs.getString("reporte") + "\","
                            + "\"fecha\":\"" + rs.getDate("fecha") + "\","
                            + "\"hora\":\"" + rs.getString("hora") + "\","
                            + "\"idUsuario_Movil\":\"" + rs.getString("Usuarios_Movil_idUsuarios_Movil") + "\","
                            + "\"anonimo\":\"" + rs.getString("anonimo") + "\","
                            + "\"estado\":\"" + rs.getString("estado") + "\"},";
                    //+ "\"img\":\"data:image/png;base64," + rs.getString("img").replaceAll("\n", "") 
                }
                arrayJson = arrayJson.substring(0, arrayJson.length() - 1);
                respuesta = respuesta + arrayJson + "]}";
                st.close();
                return respuesta;

            }
        } catch (Exception e) {
        }

        return "";
    }

//    public static String FolioreporteCiudadano(String json) throws ParseException {
//        JSONParser parser = new JSONParser();
//        JSONObject jsonObj = (JSONObject) parser.parse(json);
//        try {
//            Conexion conn = new Conexion();
//            Connection con = conn.getConexion();
//            if (con != null) {
//                Statement st;
//                st = con.createStatement();
//                ResultSet rs = st.executeQuery("SELECT * FROM reporte_ciudadano where idReporte_Ciudadano = \"" + jsonObj.get("idReporte") + "\";");
//                System.out.println("SELECT * FROM reporte_ciudadano where idReporte_Ciudadano = \"" + jsonObj.get("idReporte") + "\";");
//                if (rs.next()) {
//                    return "{\"idReporte\":\"" + rs.getString("idReporte_Ciudadano") + "\","
//                            + "\"lat\":\"" + rs.getDouble("lat") + "\","
//                            + "\"lng\":\"" + rs.getDouble("lng") + "\","
//                            + "\"reporte\":\"" + rs.getString("reporte") + "\","
//                            + "\"fecha\":\"" + rs.getDate("fecha") + "\","
//                            + "\"hora\":\"" + rs.getString("hora") + "\","
//                            + "\"idUsuario_Movil\":\"" + rs.getString("Usuarios_Movil_idUsuarios_Movil") + "\","
//                            + "\"anonimo\":\"" + rs.getString("anonimo") + "\","
//                            + "\"estado\":\"" + rs.getString("estado") + "\","
//                            + "\"img\":\""/*data:image/png;base64,"*/ + rs.getString("img")/*.replaceAll("\n", "")*/ + "\"}";
//                }
//                st.close();
//            }
//        } catch (Exception e) {
//        }
//        return "";
//    }
    public static String FolioreporteCiudadano(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if (con != null) {
                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM reporte_ciudadano where idReporte_Ciudadano = \"" + jsonObj.get("idReporte") + "\";");
                System.out.println("SELECT * FROM reporte_ciudadano where idReporte_Ciudadano = \"" + jsonObj.get("idReporte") + "\";");
                if (rs.next()) {
                    return "{\"idReporte\":\"" + rs.getString("idReporte_Ciudadano") + "\","
                            + "\"lat\":\"" + rs.getDouble("lat") + "\","
                            + "\"lng\":\"" + rs.getDouble("lng") + "\","
                            + "\"reporte\":\"" + rs.getString("reporte") + "\","
                            + "\"fecha\":\"" + rs.getDate("fecha") + "\","
                            + "\"hora\":\"" + rs.getString("hora") + "\","
                            + "\"idUsuario_Movil\":\"" + rs.getString("Usuarios_Movil_idUsuarios_Movil") + "\","
                            + "\"anonimo\":\"" + rs.getString("anonimo") + "\","
                            + "\"estado\":\"" + rs.getString("estado") + "\"}";
                    //+ "\"img\":\""/*data:image/png;base64,"*/ + rs.getString("img")/*.replaceAll("\n", "")*/ 
                }
                st.close();
            }
        } catch (Exception e) {
        }
        return "";
    }

    public static String imgFolio(String json) throws ParseException {
        System.out.println("consultando img");
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if (con != null) {
                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM reporte_ciudadano where idReporte_Ciudadano = \"" + jsonObj.get("idReporte") + "\";");
                if (rs.next()) {
                    return rs.getString("img");
                }
                st.close();
            }
        } catch (Exception e) {
        }
        return "";
    }

    public static String traeReportes() throws ParseException {
//        JSONParser parser = new JSONParser();
//        JSONObject jsonObj = (JSONObject) parser.parse(json);
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if (con != null) {
                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM reporte_ciudadano where estado != \"" + "revisado" + "\";");
                System.out.println("SELECT * FROM reporte_ciudadano where estado != \"" + "revisado" + "\";");
                JSONObject json = new JSONObject();
                int i = 1;
                ResultSet rs2;
                st = con.createStatement();
                while (rs.next()) {
                    JSONObject reporte = new JSONObject();
                    reporte.put("idReporte", rs.getString("idReporte_Ciudadano"));
                    reporte.put("lat", rs.getString("lat"));
                    reporte.put("lng", rs.getString("lng"));
                    reporte.put("reporte", rs.getString("reporte"));
                    reporte.put("fecha", rs.getString("fecha"));
                    reporte.put("hora", rs.getString("hora"));
                    reporte.put("idUsuario_Movil", rs.getString("Usuarios_Movil_idUsuarios_Movil"));
                    reporte.put("anonimo", rs.getString("anonimo"));
                    reporte.put("estado", rs.getString("estado"));
                    reporte.put("id", rs.getString("id"));
                    reporte.put("Incidente", rs.getString("tipo_emergencia"));
                    reporte.put("Prioridad", rs.getString("prioridad"));
                    //System.out.println("SELECT * FROM imagenes where idimagenes=\""+rs.getString("img")+"\";");
                    String[] imagen = rs.getString("img").split(",");
                    //System.out.println("length   " + imagen.length);
                    JSONObject imagenes = new JSONObject();

                    if (imagen.length > 1) {
                        System.out.println("Voy a entrar en el for :D");

                        for (int j = 0; j < imagen.length; j++) {
                            //System.out.println("SELECT * FROM imagenes where idimagenes=\"" + imagen[j] + "\";");
                            rs2 = st.executeQuery("SELECT * FROM controller.imagenes where idimagenes=\"" + imagen[j] + "\";");
                            JSONObject datos = new JSONObject();
                            if (rs2.next()) {
                                datos.put("src", "data:image/png;base64," + rs2.getString("img"));
                                datos.put("fecha", rs2.getString("fecha"));
                                datos.put("hora", rs2.getString("hora"));
                                datos.put("idMovil", rs2.getString("Usuarios_Movil_idUsuarios_Movil"));

                                System.out.println(datos);

                            }
                            imagenes.put(j, datos);
                            //System.out.println("SELECT * FROM imagenes where idimagenes=\"" + imagen[j] + "\";");
                        }
                        reporte.put("img", imagenes);
                    } else {
                        //System.out.println("SELECT * FROM imagenes where idimagenes=\"" + rs.getString("img") + "\";");
                        rs2 = st.executeQuery("SELECT * FROM controller.imagenes where idimagenes=\"" + rs.getString("img") + "\";");
                        if (rs2.next()) {
                            JSONObject datos = new JSONObject();
                            datos.put("src", "data:image/png;base64," + rs2.getString("img"));
                            //datos.put("src", "data:image/jpeg;base64," + rs2.getString("img"));
                            datos.put("fecha", rs2.getString("fecha"));
                            datos.put("hora", rs2.getString("hora"));
                            datos.put("idMovil", rs2.getString("Usuarios_Movil_idUsuarios_Movil"));
                            imagenes.put(0, datos);
                        }

                        reporte.put("img", imagenes);
                        //System.out.println("::::::::::::::::::::::::::::::::::::::::");
                        //System.out.println(reporte);
                    }

                    //rs2 = st.executeQuery("SELECT * FROM imagenes where idimagenes=\"" + rs.getString("img") + "\";");
                    //String [] imagenes = rs2.getString("img").split(",");
                    //System.out.println(imagenes);
                    //if (rs2.next()) {
                    //    reporte.put("img", "data:image/png;base64," + rs2.getString("img"));
                    //}
                    json.put(i, reporte);
                    i++;
                }
                st.close();

                return json.toString();
            }
        } catch (Exception e) {
        }
        return "{\"fail\":\"0\"}";
    }

    public static String actualizaReporte(String json) throws ParseException {
        System.out.println("actualizando estdo del reporte");
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if (con != null) {
                Statement st;
                st = con.createStatement();
                String query = "UPDATE reporte_ciudadano SET estado = \"revisado\", reporteoperador = \"" + jsonObj.get("reporteOperador") + "\", latOperador=\"" + jsonObj.get("lat") + "\", lngOperador=\"" + jsonObj.get("lng") + "\", id=\"" + jsonObj.get("id") + "\", tipo_emergencia = \"" + jsonObj.get("Incidente") + "\", prioridad=\"" + jsonObj.get("Prioridad") + "\", serie_incidentes = \"" + jsonObj.get("serie") + "\" WHERE idReporte_Ciudadano = \"" + jsonObj.get("idReporte") + "\";";
                System.out.println(query);
                st.executeUpdate(query);
                st.close();
                return "{\"success\":\"1\"}";
            }
        } catch (Exception e) {
        }
        return "{\"fail\":\"0\"}";
    }

    public static String guardaImg(String json) throws ParseException {
        System.out.println("actualizando imagen");
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if (con != null) {
                Statement st;
                st = con.createStatement();

//                System.out.println("INSERT INTO `imagenes` (img,fecha,hora,Usuarios_Movil_idUsuarios_Movil) "
//                        + "VALUES "
//                        + "(\""+jsonObj.get("img")+"\", \""+jsonObj.get("fecha")+"\", \""+jsonObj.get("hora")+"\",\""+jsonObj.get("idUsuario")+"\") ;");
                st.executeUpdate("INSERT INTO `imagenes` (img,fecha,hora,Usuarios_Movil_idUsuarios_Movil) "
                        + "VALUES "
                        + "(\"" + jsonObj.get("img") + "\", \"" + jsonObj.get("fecha") + "\", \"" + jsonObj.get("hora") + "\",\"" + jsonObj.get("idUsuario_Movil") + "\") ;");
                ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID() AS idImg");
                if (rs.next()) {
                    return "{\"id\":\"" + rs.getString("idImg") + "\"}";
                }
                st.close();

            }
        } catch (Exception e) {
        }
        return "{\"fail\":\"0\"}";
    }

    public static String buscaImg(String json) throws ParseException {
        System.out.println("buscando imagen");
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if (con != null) {
                Statement st;
                st = con.createStatement();
                //System.out.println("SELECT * FROM imagenes WHERE idimagenes = \"" + jsonObj.get("id") + "\"");
                ResultSet rs = st.executeQuery("SELECT * FROM imagenes WHERE idimagenes = \"" + jsonObj.get("id") + "\";");
                if (rs.next()) {
//                    String res = "{\"img\":\"" + rs.getString("img") + "\","
//                            + "\"fecha\":\"" + rs.getString("fecha") + "\","
//                            + "\"hora\":\"" + rs.getString("hora") + "\","
//                            + "\"idUsuario\":\"" + rs.getString("Usuarios_Movil_idUsuarios_Movil") + "\"}";
                    //System.out.println(res);
                    jsonObj.put("src", rs.getString("img"));
                    jsonObj.put("fecha", rs.getString("fecha"));
                    jsonObj.put("hora", rs.getString("hora"));
                    jsonObj.put("idUsuario", rs.getString("Usuarios_Movil_idUsuarios_Movil"));

                    return jsonObj.toString();
                }
                st.close();

            }
        } catch (Exception e) {
        }
        return "{\"fail\":\"0\"}";
    }

    public static String buscaSimilares(String json) throws ParseException {
        System.out.println("buscando reportes similares");
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if (con != null) {
                Statement st;
                st = con.createStatement();
                JSONObject repSimilares = new JSONObject();
                int i = 0;
                String query = "SELECT * ,( 6371 * acos(cos(radians(" + jsonObj.get("lat") + "))* cos(radians(lat))* cos(radians(lng) - radians(" + jsonObj.get("lng") + "))+ sin(radians(" + jsonObj.get("lat") + ")) * sin(radians(lat)))) AS distancia FROM reporte_ciudadano where estado = 'revisado' and fecha = '" + jsonObj.get("fecha") + "'and idReporte_Ciudadano != '" + jsonObj.get("id") + "'having distancia < 0.5;";
                System.out.println(query);
                ResultSet rs = st.executeQuery(query);
                ArrayList<String> arrayList = new ArrayList<>();
                //System.out.println("voy a entrar al while en buscaSimilares");
                while (rs.next()) {
                    //System.out.println(i);
                    String aux = rs.getString("serie_incidentes");
                    //System.out.println("---------------------------------------------------------");
                    //System.out.println(arrayList.get(i));

                    if (!arrayList.contains(aux)) {
                        arrayList.add(aux);

                        JSONObject reporte = new JSONObject();
                        reporte.put("idReporte", rs.getString("idReporte_Ciudadano"));
                        reporte.put("lat", rs.getString("lat"));
                        reporte.put("lng", rs.getString("lng"));
                        reporte.put("reporte", rs.getString("reporte"));
                        reporte.put("reporteoperador", rs.getString("reporteoperador"));
                        reporte.put("id", rs.getString("id"));
                        reporte.put("Incidente", rs.getString("tipo_emergencia"));
                        reporte.put("Prioridad", rs.getString("prioridad"));
                        reporte.put("serie", rs.getString("serie_incidentes"));
                        repSimilares.put(i, reporte);
                        i += 1;
                    } else {
                        int key = arrayList.lastIndexOf(aux);
                        //System.out.println("key ::: "+key+" ::: i ::: "+i);
                        repSimilares.remove(key);
                        arrayList.add(aux);
                        //arrayList.remove(key);
                        JSONObject reporte = new JSONObject();
                        reporte.put("idReporte", rs.getString("idReporte_Ciudadano"));
                        reporte.put("lat", rs.getString("lat"));
                        reporte.put("lng", rs.getString("lng"));
                        reporte.put("reporte", rs.getString("reporte"));
                        reporte.put("reporteoperador", rs.getString("reporteoperador"));
                        reporte.put("id", rs.getString("id"));
                        reporte.put("Incidente", rs.getString("tipo_emergencia"));
                        reporte.put("Prioridad", rs.getString("prioridad"));
                        reporte.put("serie", rs.getString("serie_incidentes"));
                        repSimilares.put(i, reporte);
                        i += 1;
                    }

                }

                st.close();
                return repSimilares.toString();
            }
        } catch (Exception e) {
        }
        return "{\"fail\":\"0\"}";
    }

}
