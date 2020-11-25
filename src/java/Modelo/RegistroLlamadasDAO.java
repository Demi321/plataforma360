/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Config.config;
import Encriptacion.Encriptar;
import Request.request;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Vostro Placas
 */
public class RegistroLlamadasDAO {

    public static boolean nuevaLlamada(RegistroLlamadasVO sesion, String url,JSONObject json) {
        boolean agregado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {

                Statement st;
                st = con.createStatement();
                String query = "INSERT INTO registro_llamadas (`idRegistro_Llamadas`,`fecha`,`hora`,`Modo_Llamada_idModo_Llamada`,`Usuarios_Movil_idUsuarios_Movil`,`ruta_video`,`latitud`,`longitud`,`apikey`,`sesion`)  VALUES "
                        + "('" + sesion.getIdRegistro_Llamadas() + "','" + sesion.getFecha() + "','" + sesion.getHora() + "','" + sesion.getIdModo_Llamada() + "','" + sesion.getIdUsuarios_Movil() + "','" + url+ "','" + sesion.getLatitud()+"','" + sesion.getLongitud()+ "', '"+json.get("apikey")+"','"+json.get("sesion")+"');";
                System.out.println(query);
                st.executeUpdate(query);
                agregado = true;

                st.close();
            }
            c.cerrarConexion();
        } catch (SQLException e) {
            agregado = false;
        }
        return agregado;
    }
    
    public static boolean nuevaLlamada(JSONObject registro, JSONObject Opentok){
        System.out.println("registrando nuevaLlamada con tipo_usuario: "+registro.get("tipo_usuario")+" y tipo_servicio: "+registro.get("tipo_servicio"));
        String query = "INSERT INTO registro_llamadas (`idRegistro_Llamadas`,`fecha`,`hora`,`Modo_Llamada_idModo_Llamada`,`Usuarios_Movil_idUsuarios_Movil`,`ruta_video`,`latitud`,`longitud`,`apikey`,`sesion`,`tipo_usuario`,`tipo_servicio`)  VALUES "
                + "('" + registro.get("idLlamada") + "','" + registro.get("fecha") + "','" + registro.get("hora") + "','" + registro.get("modo") + "','" + registro.get("idUsuarios_Movil") + "','" + registro.get("url")+ "',"
                + "'" + registro.get("latitud")+"','" + registro.get("longitud")+ "', '"+Opentok.get("apikey")+"','"+Opentok.get("sesion")+"', "
                + "'" + registro.get("tipo_usuario")+"','" + registro.get("tipo_servicio")+"');";
        try {
            Query.insert(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        
    }

//    public static boolean agregarSesion(RegistroLlamadasVO sesion) {
//        boolean agregado = false;
//        try {
//            Conexion c = new Conexion();
//            Connection con = c.getConexion();
//
//            if (con != null) {
//
//                Statement st;
//                st = con.createStatement();
//                st.executeUpdate("INSERT INTO registro_llamadas (`fecha`,`hora`,`latitud`,`longitud`,`altitud`,`ruta_generada`,`ruta_video`,`ruta_chat`,`reporte_llamada`,`Tipo_Emergencia_idTipo_Emergencia`,`Modo_Llamada_idModo_Llamada`,`Usuario_Sys_idUsuario_Sys`,`Usuarios_Movil_idUsuarios_Movil`,`Prioridad_Llamada_idPrioridad_Llamada`)  VALUES "
//                        + "('" + sesion.getFecha() + "','" + sesion.getHora() + "','" + sesion.getLatitud() + "','" + sesion.getLongitud() + "','" + sesion.getAltitud() + "','" + sesion.getRuta_generada() + "','" + sesion.getRuta_video() + "','" + sesion.getRuta_chat() + "','" + sesion.getReporte_llamada() + "','" + sesion.getIdTipo_Emergencia() + "','" + sesion.getIdModo_Llamada() + "','" + sesion.getIdUsuario_Sys() + "','" + sesion.getIdUsuarios_Movil() + "','" + sesion.getIdPrioridad_Llamada() + "');");
//                agregado = true;
//
//                st.close();
//            }
//            c.cerrarConexion();
//        } catch (SQLException e) {
//            agregado = false;
//        }
//        return agregado;
//    }
    public static boolean ActualizarRegistroLlamada(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject sesion = (JSONObject) parser.parse(json);
        boolean agregado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {

                PreparedStatement ps;
                ps = con.prepareStatement("UPDATE registro_llamadas "
                        + "SET latitud=?, "
                        + "longitud=?, "
                        + "altitud=?, "
                        + "ruta_generada=?, "
                        + "Modo_Llamada_idModo_Llamada_Finalizada=?, "
                        + "Atendida=?, "
                        + "Usuario_Sys_idUsuario_Sys=? , "
                        + "prefijoFolio=? "
                        + " WHERE idRegistro_Llamadas= ?;");
                ps.setString(1, sesion.get("latitud").toString());
                ps.setString(2, sesion.get("longitud").toString());
                ps.setString(3, sesion.get("altitud").toString());
                ps.setString(4, sesion.get("ruta_generada").toString());
                //ps.setString(5, sesion.get("ruta_video").toString());
                ps.setString(5, sesion.get("idModo_Llamada_Finalizada").toString());
                ps.setString(6, sesion.get("hora").toString());
                ps.setString(7, sesion.get("idUsuario_Sys").toString());
                ps.setString(8, sesion.get("prefijoFolio").toString());
                ps.setString(9, sesion.get("idRegistro_Llamadas").toString());
                ps.executeUpdate();
                agregado = true;
                ps.close();
            }
            c.cerrarConexion();
        } catch (SQLException e) {
            agregado = false;
        }
        return agregado;
    }

    public static boolean LlamadaFinalizada(String json) throws ParseException {
        System.out.println(json);
        JSONParser parser = new JSONParser();
        JSONObject sesion = (JSONObject) parser.parse(json);
        boolean agregado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {
                System.out.println(sesion.get("direccion"));
                //System.out.println("UPDATE registro_llamadas "
//                        + "SET chat="+sesion.getChat()+", "
//                        + "Finalizada="+sesion.getHora()+" "
//                        + " WHERE idRegistro_Llamadas= "+sesion.getIdRegistro_Llamadas()+";");
                PreparedStatement ps;
                ps = con.prepareStatement("UPDATE registro_llamadas "
                        + "SET direccion=?,"
                        + "chat=?, "
                        + "Finalizada=? , "
                        + "Folio=? , "
                        + "folioIncidentes=? , "
                        + "estado=? , "
                        + "municipio=? , "
                        + "colonia=? , "
                        + "codigopostal=? , "
                        + "prefijoFolio=? , "
                        + "bitacora=? "
                        + " WHERE idRegistro_Llamadas= ?;");

                ps.setString(1, sesion.get("direccion").toString());
                ps.setString(2, sesion.get("chat").toString());
                ps.setString(3, sesion.get("hora").toString());
                ps.setString(4, sesion.get("Folio").toString());
                ps.setString(5, sesion.get("folioIncidentes").toString());
                ps.setString(6, sesion.get("estado").toString());
                ps.setString(7, sesion.get("municipio").toString());
                ps.setString(8, sesion.get("colonia").toString());
                ps.setString(9, sesion.get("codigopostal").toString());
                ps.setString(10, sesion.get("prefijoFolio").toString());
                ps.setString(11, sesion.get("bitacora").toString());
                ps.setString(12, sesion.get("idRegistro_Llamadas").toString());
                System.out.println(ps);
                ps.executeUpdate();
                agregado = true;
                ps.close();
            }
            c.cerrarConexion();
        } catch (SQLException e) {
            agregado = false;
        }
        return agregado;
    }

    public static boolean agregarRuta(RegistroLlamadasVO reg) {
        boolean agregado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {

                PreparedStatement ps;
                ps = con.prepareStatement("UPDATE registro_llamadas "
                        + "SET ruta_generada=?"
                        + " WHERE idRegistro_Llamadas= ?;");
                ps.setString(1, reg.getRuta_generada());
                ps.setString(2, reg.getIdRegistro_Llamadas());
                ps.executeUpdate();
                agregado = true;
                ps.close();
            }
            c.cerrarConexion();
        } catch (SQLException e) {
            agregado = false;
        }
        return agregado;
    }

    public static boolean ConsultarRegistro_Lamadas(RegistroLlamadasVO sesion) {

        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {

                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM registro_llamadas WHERE idRegistro_Llamadas='" + sesion.getIdRegistro_Llamadas() + "' ;");

                if (rs.next()) {
                    sesion.setIdRegistro_Llamadas(rs.getString("idRegistro_Llamadas"));
                    return true;
                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {

        }
        return false;
    }
    public static boolean ConsultarRegistro_Lamadas(JSONObject json) {
        String query = "SELECT idRegistro_Llamadas FROM registro_llamadas WHERE idRegistro_Llamadas = '"+json.get("idLlamada")+"' "
                + "AND tipo_usuario = '"+json.get("tipo_usuario")+"' AND tipo_servicio = '"+json.get("tipo_servicio")+"';";
        if (Query.select(query) != null) {
            return true;
        } else {
            return false;
        }
    }

    public static JSONObject GenerarReporte(String IdLlamada, String origen, String hora) throws Exception {
        //String registroLlamada = "";
        System.out.println("Estoy en GenerarReporte de --> " + config.getDEPENDENCIA() + " y estos son los valores que me pasaron");
        System.out.println(IdLlamada);
        System.out.println(origen);
        System.out.println(hora);
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if (con != null) {

                String reporteLlamada = repLlamada(IdLlamada, hora);
                System.out.println("Regrese de repLlamada con los siguientes valores -->");
                System.out.println(reporteLlamada);
                if (reporteLlamada != null) {
                    JSONObject jsonObj = new JSONObject();
                    JSONObject RegistroLlamada = new JSONObject();
                    JSONObject ReporteLlamada = new JSONObject();
                    JSONObject infoTickets = new JSONObject();

                    JSONParser parser = new JSONParser();
                    Statement st;
                    st = con.createStatement();
                    String query = "SELECT * FROM registro_llamadas WHERE idRegistro_Llamadas=\"" + IdLlamada + "\";";
                    System.out.println(query);
                    ResultSet rs = st.executeQuery(query);

                    //System.out.println("idLamada::::: " + IdLlamada);
                    if (rs.next()) {

                        String url = config.getURL_CONTROLADOR() + "API/SolicitaFolioExterno";
                        JSONObject j = new JSONObject();
                        j.put("idLlamada", IdLlamada);
                        String resultado = request.POST(url, j.toString());

                        String folioexterno = ((JSONObject) parser.parse(resultado)).get("folioexterno").toString();
                        String dependencias = ((JSONObject) parser.parse(resultado)).get("dependencias").toString();

                        if (!folioexterno.equals("") && folioexterno != null && !folioexterno.equals("null")) {
                            infoTickets.put("folioexterno", folioexterno);
                        } else {
                            infoTickets.put("folioexterno", "");
                        }

                        if (!dependencias.equals("") && dependencias != null && !dependencias.equals("null")) {
                            infoTickets.put("dependencias", dependencias);
                        } else {
                            infoTickets.put("dependencias", "");
                        }

                        System.out.println("InfoTickets ---> ");
                        System.out.println(infoTickets.toString());
//                    
//                    infoTickets.put("folioexterno", ((JSONObject) parser.parse(resultado)).get("folioexterno"));
//                    infoTickets.put("dependencias", ((JSONObject) parser.parse(resultado)).get("dependencias"));

                        String modoLlamada = rs.getString("Modo_Llamada_idModo_Llamada");
                        String modoLlamadaF = rs.getString("Modo_Llamada_idModo_Llamada_Finalizada");
//                    String prio = rs.getString("Prioridad_Llamada_idPrioridad_Llamada");
                        String c = rs.getString("Usuario_Sys_idUsuario_Sys");
//                    System.out.println("Prioridad llamada :::: " + prio);
                        //System.out.println("SELECT img FROM " + origen.toLowerCase() + ".usuarios_movil where idUsuarios_Movil = \"" + idUsrM + "\";");
                        String mLlamadaI = modo_llamada(modoLlamada);
                        System.out.println("Regrese de mod_llamada con los siguientes valores -->");
                        System.out.println(mLlamadaI);
                        String mLlamadaF = modo_llamadaF(modoLlamadaF);
                        System.out.println("Regrese de mod_llamadaF con los siguientes valores -->");
                        System.out.println(mLlamadaF);

                        String cargo = Cargo(c);
                        System.out.println("Regrese de cargo con los siguientes valores -->");
                        System.out.println(cargo);
//                    System.out.println("cargo:: " + cargo);


                        /*
                        Cosas que se van a agregar al registro de la llamada
                         */
                        RegistroLlamada.put("idLlamada", IdLlamada);
                        RegistroLlamada.put("Folio", rs.getString("Folio"));
                        RegistroLlamada.put("fecha", rs.getString("fecha"));
                        RegistroLlamada.put("hora", rs.getString("hora"));
                        RegistroLlamada.put("latitud", rs.getDouble("latitud"));
                        RegistroLlamada.put("longitud", rs.getDouble("longitud"));
                        //RegistroLlamada.put("altitud", rs.getDouble("altitud"));
                        RegistroLlamada.put("direccion", rs.getString("direccion"));
                        RegistroLlamada.put("estado", rs.getString("estado"));
                        RegistroLlamada.put("municipio", rs.getString("municipio"));
                        RegistroLlamada.put("colonia", rs.getString("colonia"));
                        RegistroLlamada.put("codigopostal", rs.getString("codigopostal"));
                        //RegistroLlamada.put("ruta_generada", rs.getString("ruta_generada"));
                        RegistroLlamada.put("ruta_video", rs.getString("ruta_video"));

                        try {
                            //String chat_arreglado=rs.getString("chat").replace("\"", "'");
                            //chat_arreglado=rs.getString("chat").replace("'", "\'");
                            //System.out.println(chat_arreglado);
                            if (rs.getString("chat") != null) {
                                JSONArray jsnarray = (JSONArray) parser.parse(rs.getString("chat"));
                                RegistroLlamada.put("chat", jsnarray);
                            } else {
                                System.out.println("Error el chat es null");
                                RegistroLlamada.put("chat", "");
                            }

                        } catch (SQLException | ParseException e) {
                            System.out.println("Error al parsear el chat");
                            RegistroLlamada.put("chat", "");
                            System.out.println(e);
                        }

                        RegistroLlamada.put("reporte_llamada", rs.getString("reporte_llamada"));
                        RegistroLlamada.put("modo_llamada_inicial", mLlamadaI);
                        RegistroLlamada.put("modo_llamada_final", mLlamadaF);
                        RegistroLlamada.put("id_usuario_sys", rs.getString("Usuario_Sys_idUsuario_Sys"));

                        System.out.println("Esto es lo que viene en RegistroLlamada");
                        System.out.println(RegistroLlamada.toString());

                        try {

                            JSONObject Cargo = (JSONObject) parser.parse(cargo);
                            RegistroLlamada.put("Nombre", Cargo.get("Nombre"));
                            RegistroLlamada.put("apellidos", Cargo.get("apellidos"));
                        } catch (ParseException e) {
                            RegistroLlamada.put("Nombre", "");
                            RegistroLlamada.put("apellidos", "");
                            System.out.println("jhbasdvhjdasjasddas");
                            System.out.println(e);
                        }

                        RegistroLlamada.put("usuario_movil", rs.getString("Usuarios_Movil_idUsuarios_Movil"));
                        RegistroLlamada.put("atendida", rs.getString("Atendida"));
                        RegistroLlamada.put("finalizada", rs.getString("Finalizada"));
                        System.out.println("kajshdkajhsdkjhasdkhaskdlj");
                        System.out.println(RegistroLlamada);

                        //System.out.println(rs.findColumn("bitacora"));
                        ResultSetMetaData metadata = rs.getMetaData();
                        int NumeroColumnas = metadata.getColumnCount();
                        boolean existe = false;
                        for (int i = 1; i < NumeroColumnas; i++) {

                            System.out.println(metadata.getColumnLabel(i));
                            if ("bitacora".equals(metadata.getCatalogName(i))) {

                                existe = true;
                                break;
                            }

                        }
                        JSONObject bitacora = new JSONObject();
                        System.out.println("bitacora");
                        System.out.println(rs.getString("bitacora"));
                        if (rs.getString("bitacora") != null && rs.getString("bitacora") != "null" && rs.getString("bitacora") != "") {
                            bitacora = (JSONObject) parser.parse(rs.getString("bitacora"));
                            RegistroLlamada.put("bitacora", bitacora);
                        } else {
                            bitacora.put("vacio", true);
                            bitacora.put("h_desconexion_operador", "");
                            bitacora.put("h_captura_reporte", "");
                            bitacora.put("h_conexion_usuario", "");
                            bitacora.put("h_conexion_operador", "");
                            bitacora.put("h_atencion_inicio", "");
                            bitacora.put("h_recepcion", "");
                            bitacora.put("h_desconexion_usuario", "");

                            RegistroLlamada.put("bitacora", bitacora);
                        }
                        //JSONObject bitacora = (JSONObject) parser.parse(rs.getString("bitacora"));

                        /* 
                        Cosas que se van a agregar al reporte de la llamada
                         */
                        //jsonObj.put("Tipo_Emergencia_idTipo_Emergencia", rs.getInt("Tipo_Emergencia_idTipo_Emergencia"));
                        try {

                            if (reporteLlamada != null) {
                                System.out.println("reporteLlamada");
                                System.out.println(reporteLlamada);
                                JSONParser p = new JSONParser();
                                JSONObject repLlamada = (JSONObject) p.parse(reporteLlamada);
                                ReporteLlamada.put("idReporte", repLlamada.get("idReporte"));
                                ReporteLlamada.put("tipoLugar", repLlamada.get("tipoLugar"));
                                ReporteLlamada.put("numPiso", repLlamada.get("numPiso"));
                                ReporteLlamada.put("descripcionLugar", repLlamada.get("descripcionLugar"));
                                ReporteLlamada.put("temergencia", repLlamada.get("temergencia"));
                                ReporteLlamada.put("prioridad", repLlamada.get("prioridad"));
                                ReporteLlamada.put("reporte", repLlamada.get("Reporte"));

                                ReporteLlamada.put("razonamiento", repLlamada.get("razonamiento"));
                                ReporteLlamada.put("folioexterno", repLlamada.get("folioexterno_reporte"));
                                ReporteLlamada.put("hora", repLlamada.get("hora_reporte"));
                                ReporteLlamada.put("fecha", repLlamada.get("fecha_reporte"));
                            } else {
                                ReporteLlamada.put("idReporte", "");
                                ReporteLlamada.put("tipoLugar", "");
                                ReporteLlamada.put("numPiso", "");
                                ReporteLlamada.put("descripcionLugar", "");
                                ReporteLlamada.put("temergencia", "");
                                ReporteLlamada.put("prioridad", "");
                                ReporteLlamada.put("reporte", "");

                                ReporteLlamada.put("razonamiento", "");
                                ReporteLlamada.put("folioexterno", "");
                                ReporteLlamada.put("hora", "");
                                ReporteLlamada.put("fecha", "");
                            }

                            //ReporteLlamada.put("bitacora", repLlamada.get("bitacora"));
                        } catch (ParseException e) {
                            System.out.println(e);
                            ReporteLlamada.put("idReporte", "");
                            ReporteLlamada.put("tipoLugar", "");
                            ReporteLlamada.put("numPiso", "");
                            ReporteLlamada.put("descripcionLugar", "");
                            ReporteLlamada.put("temergencia", "");
                            ReporteLlamada.put("prioridad", "");
                            ReporteLlamada.put("reporte", "");

                            ReporteLlamada.put("razonamiento", "");
                            ReporteLlamada.put("folioexterno", "");
                            ReporteLlamada.put("hora", "");
                            ReporteLlamada.put("fecha", "");

                        }

                        jsonObj.put("RegistroLlamada", RegistroLlamada);
                        jsonObj.put("ReporteLlamada", ReporteLlamada);
                        jsonObj.put("infoTickets", infoTickets);
                    }
                    st.close();
                    return jsonObj;
                } else {
                    return null;
                }

            }
            conn.cerrarConexion();
        } catch (SQLException e) {
            System.out.println(e);
            //System.out.println("No hay conexion en GenerarReporte");
        }
        return null;
    }

    public static String modo_llamada(String modoLlamada) {
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if (con != null) {
                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM modo_llamada WHERE idModo_Llamada = \"" + modoLlamada + "\";");
                if (rs.next()) {
                    String resultado = rs.getString("nombre");
                    return resultado;
                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Algo salio mal en la funcion modo_llamada");
        }
        return null;
    }

    public static String modo_llamadaF(String modoLlamadaF) {
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if (con != null) {
                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM modo_llamada WHERE idModo_Llamada = \"" + modoLlamadaF + "\";");
                if (rs.next()) {
                    String resultado = rs.getString("nombre");
                    return resultado;
                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Algo salio mal en la funcion modo_llamadaF");
        }
        return null;
    }

    public static String repLlamada(String IdLlamada, String hora) {
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if (con != null) {
                JSONObject jsonObj = new JSONObject();
                Statement st;
                st = con.createStatement();
                String query = "SELECT * FROM reporte_llamadas WHERE idLlamada =\"" + IdLlamada + "\" AND hora= \"" + hora + "\";";
                System.out.println(query);
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    jsonObj.put("idReporte", rs.getString("idReporte"));
                    jsonObj.put("tipoLugar", rs.getString("tipoLugar"));
                    jsonObj.put("numPiso", rs.getString("numPiso"));
                    jsonObj.put("descripcionLugar", rs.getString("descripcionLugar"));
                    jsonObj.put("temergencia", rs.getString("temergencia"));
                    jsonObj.put("prioridad", rs.getString("prioridad"));
                    jsonObj.put("Reporte", rs.getString("reporte"));

                    jsonObj.put("folioexterno_reporte", rs.getString("folioexterno"));
                    jsonObj.put("razonamiento", rs.getString("razonamiento"));
                    jsonObj.put("hora_reporte", rs.getString("hora"));
                    jsonObj.put("fecha_reporte", rs.getString("fecha"));

                    //jsonObj.put("bitacora", rs.getString("bitacora"));
//                    String resultado = "','idReporte':'" + rs.getString("idReporte") + 
//                            "','tipoLugar':'" + rs.getString("tipoLugar") + 
//                            "','numPiso':'" + rs.getString("numPiso") + 
//                            "','descripcionLugar':'" + rs.getString("descripcionLugar") + 
//                            "','temergencia':'" + rs.getString("temergencia") + 
//                            "','Reporte':'" + rs.getString("reporte");
                    return jsonObj.toString();
                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Algo salio mal en la funcion repLlamada");
            System.out.println(e);
        }
        return null;
    }

    public static String Cargo(String cargo) {
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if (con != null) {
                JSONObject jsonObj = new JSONObject();
                Statement st;
                st = con.createStatement();
                System.out.println("SELECT nombre,apellidos FROM usuario_sys where idUsuario_Sys =\"" + cargo + "\";");
                ResultSet rs = st.executeQuery("SELECT nombre,apellidos FROM usuario_sys where idUsuario_Sys =\"" + cargo + "\";");
                if (rs.next()) {
                    jsonObj.put("Nombre", rs.getString("nombre"));
                    jsonObj.put("apellidos", rs.getString("apellidos"));

                    //String resultado = "','Nombre':'" + rs.getString("nombre") + "','apellidos':'" + rs.getString("apellidos");
                    //System.out.println(resultado);
                    return jsonObj.toString();
                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Algo salio mal en la funcion repLlamada");
        }
        return null;
    }
}
