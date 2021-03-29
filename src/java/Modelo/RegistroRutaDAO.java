/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Config.config;
import static java.lang.Float.parseFloat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Moises Juarez
 */
public class RegistroRutaDAO {

      public static boolean gpsLote(String json) throws ParseException {

            JSONParser parser = new JSONParser();
            JSONObject reg = (JSONObject) parser.parse(json);
            boolean agregado = false;

            try {
                  Conexion c = new Conexion();
                  Connection con = c.getConexion();
                  if (con != null) {
                        Statement st;
                        st = con.createStatement();
                        System.out.println("SELECT idregistro_rutas FROM registro_rutas  WHERE idUsuarios_Movil='" + reg.get("idUsuarios_Movil") + "' and Fecha = '" + reg.get("fecha") + "';");
                        ResultSet rs = st.executeQuery("SELECT idregistro_rutas FROM registro_rutas  WHERE idUsuarios_Movil='" + reg.get("idUsuarios_Movil") + "' and Fecha = '" + reg.get("fecha") + "';");

                        if (rs.next()) {
                              System.out.println("UPDATE registro_rutas SET `Ruta` = CONCAT(Ruta,\"" + reg.get("ruta") + "\") WHERE idregistro_rutas=" + rs.getString("idregistro_rutas") + ";");
//                    System.out.println("Actualizando");
//                    System.out.println(rs.getString("idregistro_rutas"));
                              PreparedStatement ps;

                              ps = con.prepareStatement("UPDATE registro_rutas SET `Ruta` = CONCAT(Ruta,\"" + reg.get("ruta") + "\") WHERE idregistro_rutas=" + rs.getString("idregistro_rutas") + ";");
//                    ps.setString(1, reg.getRuta());
//                    ps.setString(2, rs.getString("idregistro_rutas"));
                              ps.executeUpdate();
                              agregado = true;
                              ps.close();
                        } else {
                              System.out.println("INSERT INTO registro_rutas(idUsuarios_Movil,Fecha,ruta) VALUES ('" + reg.get("idUsuarios_Movil") + "','" + reg.get("fecha") + "','" + reg.get("ruta") + "');");
                              st.executeUpdate("INSERT INTO registro_rutas(idUsuarios_Movil,Fecha,ruta) VALUES ('" + reg.get("idUsuarios_Movil") + "','" + reg.get("fecha") + "','" + reg.get("ruta") + "');");
                              agregado = true;
                              st.close();
                        }
                  }
                  c.cerrarConexion();
            } catch (SQLException e) {
                  agregado = false;
            }
            return agregado;
      }

      public static boolean gpsLote(RegistroRutaVO reg) {

            boolean agregado = false;

            try {
                  Conexion c = new Conexion();
                  Connection con = c.getConexion();
                  if (con != null) {
                        Statement st;
                        st = con.createStatement();
                        System.out.println("SELECT idregistro_rutas FROM registro_rutas  WHERE idUsuarios_Movil='" + reg.getIdUsuarios_Movil() + "' and Fecha = '" + reg.getFecha() + "';");
                        ResultSet rs = st.executeQuery("SELECT idregistro_rutas FROM registro_rutas  WHERE idUsuarios_Movil='" + reg.getIdUsuarios_Movil() + "' and Fecha = '" + reg.getFecha() + "';");

                        if (rs.next()) {
                              System.out.println("UPDATE registro_rutas SET `Ruta` = CONCAT(Ruta,\"" + reg.getRuta() + "\") WHERE idregistro_rutas=" + rs.getString("idregistro_rutas") + ";");
                              System.out.println("Actualizando");
                              System.out.println(rs.getString("idregistro_rutas"));
                              PreparedStatement ps;

                              ps = con.prepareStatement("UPDATE registro_rutas SET `Ruta` = CONCAT(Ruta,\"" + reg.getRuta() + "\") WHERE idregistro_rutas=" + rs.getString("idregistro_rutas") + ";");
//                    ps.setString(1, reg.getRuta());
//                    ps.setString(2, rs.getString("idregistro_rutas"));
                              ps.executeUpdate();
                              agregado = true;
                              ps.close();
                        } else {
                              System.out.println("INSERT INTO registro_rutas(idUsuarios_Movil,Fecha,ruta) VALUES ('" + reg.getIdUsuarios_Movil() + "','" + reg.getFecha() + "',\"" + reg.getRuta() + "\");");
                              st.executeUpdate("INSERT INTO registro_rutas(idUsuarios_Movil,Fecha,ruta) VALUES ('" + reg.getIdUsuarios_Movil() + "','" + reg.getFecha() + "',\"" + reg.getRuta() + "\");");
                              agregado = true;
                              st.close();
                        }
                  }
                  c.cerrarConexion();
            } catch (SQLException e) {
                  agregado = false;
            }
            return agregado;
      }

      public static boolean gps(RegistroRutaVO reg, JSONObject json) {
            boolean actualizaVersion = false;
            boolean agregado = false;

            try {
                  Conexion c = new Conexion();
                  Connection con = c.getConexion();
                  if (con != null) {
                        Statement st;
                        st = con.createStatement();

                        ResultSet rs = st.executeQuery("SELECT idregistro_rutas FROM registro_rutas  WHERE idUsuarios_Movil='" + reg.getIdUsuarios_Movil() + "' and Fecha = '" + reg.getFecha() + "';");

                        if (rs.next()) {
//                    System.out.println("Actualizando");
//                    System.out.println(rs.getString("idregistro_rutas"));
                              PreparedStatement ps;
                              ps = con.prepareStatement("UPDATE registro_rutas "
                                      + "SET Hora=?, Latitud=?, Longitud=?"
                                      + " WHERE idregistro_rutas= ?");
                              ps.setString(1, reg.getHora());
                              ps.setString(2, reg.getLatitud());
                              ps.setString(3, reg.getLongitud());
                              ps.setString(4, rs.getString("idregistro_rutas"));
                              ps.executeUpdate();
                              agregado = true;
                              ps.close();
                        } else {
                              actualizaVersion = true;
                              st.executeUpdate("INSERT INTO registro_rutas(idUsuarios_Movil,Fecha,Hora,Latitud,Longitud,Ruta) VALUES ('" + reg.getIdUsuarios_Movil() + "','" + reg.getFecha() + "','" + reg.getHora() + "','" + reg.getLatitud() + "','" + reg.getLongitud() + "',\"\");");
                              agregado = true;
                              st.close();
                        }
                  }
                  c.cerrarConexion();
            } catch (SQLException e) {
                  agregado = false;
            }
            if (actualizaVersion) {
                  if (json.containsKey("so") && json.containsKey("version") && json.containsKey("soversion")) {
                        String query = "UPDATE `usuarios_movil` SET `so` = '" + json.get("so") + "', `soversion` = '" + json.get("soversion") + "', `version` = '" + json.get("version") + "' WHERE (`idUsuarios_Movil` = '" + json.get("idUsuarios_Movil") + "');";
                        Query.update(query);
                  }

            }
            return agregado;
      }

      public static JSONObject BuscarElementosCercanos(RegistroRutaVO p) {
            JSONArray jsonArray = new JSONArray();
            try {
                  Conexion conn = new Conexion();
                  Connection con = conn.getConexion();

                  if (con != null) {
//                RegistroRutaVO[] reg = new RegistroRutaVO[100];
//                String res = "[";
                        //System.out.println("Si hay conexion");
                        //double d_min = 0;
                        //System.out.println(p.getRango());
                        int i = 0;
                        /*  double rango = (0.0007 * parseFloat(p.getRango()));
                double latmax = parseFloat(p.getLatitud()) + rango;
                double latmin = parseFloat(p.getLatitud()) - rango;
                double longmax = parseFloat(p.getLongitud()) + rango;
                double longmin = parseFloat(p.getLongitud()) - rango;
                double R = 6372795.477598;
                double rad = Math.PI / 180;
                double LatA = rad * parseFloat(p.getLatitud());
                double LonA = rad * parseFloat(p.getLongitud());    */

                        Statement st;
                        st = con.createStatement();

//                ResultSet rs = st.executeQuery("SELECT idregistro_rutas, idUsuarios_Movil, Fecha, Hora, Latitud, Longitud,estadoNotificacion FROM registro_rutas "
//                        + "WHERE "
//                        + "latitud<'" + (latmax) + "' AND latitud>'" + (latmin) + "' "
//                        + "AND "
//                        + "Longitud>'" + (longmax) + "' AND Longitud<'" + (longmin) + "' "
//                        + "AND "
//                        + "Fecha = '" + p.getFecha() + "';");
                        String query0 = "SELECT idregistro_rutas, idUsuarios_Movil, Fecha, Hora, Latitud, Longitud,estadoNotificacion ,( 6371 * acos(cos(radians(" + parseFloat(p.getLatitud()) + ")) * cos(radians(Latitud)) * cos(radians(Longitud) - radians(" + parseFloat(p.getLongitud()) + "))+ sin(radians(" + parseFloat(p.getLatitud()) + ")) * sin(radians(Latitud)))) AS distancia FROM registro_rutas where Fecha = \"" + p.getFecha() + "\" having distancia < " + parseFloat(p.getRango()) + ";";

                        ResultSet rs = st.executeQuery(query0);

                        while (rs.next()) {
                              Statement st2 = con.createStatement();
                              String query = "SELECT FireBaseKey,nombre, apellido_materno, apellido_paterno,icon,idUsuarios_Movil,img FROM usuarios_movil where idUsuarios_Movil=\"" + rs.getString("idUsuarios_Movil") + "\";";
                              ResultSet rs2 = st2.executeQuery(query);
                              if (rs2.next()) {
                                    JSONObject elemento = new JSONObject();
                                    elemento.put("FireBaseKey", rs2.getString("FireBaseKey"));
                                    elemento.put("apellido_materno", rs2.getString("apellido_materno"));
                                    elemento.put("apellido_paterno", rs2.getString("apellido_paterno"));
                                    elemento.put("icon", rs2.getString("icon"));
                                    elemento.put("idUsuario_Movil", rs2.getString("idUsuarios_Movil"));
                                    elemento.put("img", rs2.getString("img"));
                                    elemento.put("nombre", rs2.getString("nombre"));
                                    elemento.put("origen", config.getDEPENDENCIA());

                                    JSONObject json = new JSONObject();
                                    json.put("actualizada", true);
                                    json.put("idUsuario_Movil", rs.getString("idUsuarios_Movil"));
                                    json.put("hora", rs.getString("Hora"));
                                    json.put("fecha", rs.getString("Fecha"));
                                    json.put("lat", rs.getFloat("Latitud"));
                                    json.put("lng", rs.getFloat("Longitud"));
                                    json.put("estadoNotificacion", rs.getString("estadoNotificacion"));

                                    elemento.put("posicion", json);

                                    jsonArray.add(elemento);
                              } else {
                                    System.out.println("ERROR...");
                              }

                              //reg[i] = new RegistroRutaVO(rs.getString("idregistro_rutas"), rs.getString("idUsuarios_Movil"), rs.getString("Fecha"), rs.getString("Hora"), rs.getString("Latitud"), rs.getString("Longitud"));
//NOTA: LatA, LatB, LonA y LonB ya tienen que esta multiplicada por rad
                              /* double LatB = rad * parseFloat(rs.getString("Latitud"));
                    double LonB = rad * parseFloat(rs.getString("Longitud"));
                    double d = R * Math.acos(Math.sin(LatA) * Math.sin(LatB) + Math.cos(LatA) * Math.cos(LatB) * Math.cos(LonA - LonB));
                    reg[i].setRango(String.valueOf(d));

                    res += "{\"id\":\"" + rs.getString("idregistro_rutas") + "\",\"idUsuarios_Movil\":\"" + rs.getString("idUsuarios_Movil") + "\",\"fecha\":\"" + rs.getString("Fecha") + "\",\"hora\":\"" + rs.getString("Hora") + "\",\"latitud\":\"" + rs.getString("Latitud") + "\",\"longitud\":\"" + rs.getString("Longitud") + "\",\"rango\":\"" + String.valueOf(d) + "\"},";
                               */
                              //float x = (rs.getFloat("derecha") + rs.getFloat("izquierda")) / 2;
                              //float y = (rs.getFloat("superior") + rs.getFloat("inferior")) / 2;
                              //System.out.println(x+" , "+y);
                              //double d = Math.sqrt(Math.pow(Float.parseFloat(coordenadas.getLongitud()) - x, 2) + Math.pow(Float.parseFloat(coordenadas.getLatitud()) - y, 2));
                              //System.out.println(d);
//                    if (d_min == 0 || d < d_min) {
//                        d_min=d;
//                        System.out.println("if");
//                        reporte = new ReporteVO(rs.getString("tipoLugar"), rs.getString("numPiso"), rs.getString("descripcionLugar"), rs.getFloat("superior"), rs.getFloat("inferior"), rs.getFloat("derecha"), rs.getFloat("izquierda"), rs.getInt("altura"));
//                        System.out.println(reporte.getDescripcionLugar());
//                    }
                              i++;
                              rs.absolute(i);
//tipoLugar, String numPiso, String descripcionLugar, float superior, float inferior, float derecha, float izquierda, int altura 
//ReporteVO reporte = new ReporteVO(rs.getString("tipoLugar"),rs.getString("numPiso "),rs.getString("descripcionLugar"),rs.getFloat("superior"),rs.getFloat("inferior"),rs.getFloat("derecha"),rs.getFloat("izquierda"),rs.getInt("altura"));
                              st2.close();
                        }
                        /* if (!"[".equals(res)) {
                    res = res.substring(0, res.length() - 1);
                }

                res += "]";*/

                        st.close();
                        JSONObject respuesta = new JSONObject();
                        respuesta.put("elementos", jsonArray);
                        return respuesta;
                  }
                  conn.cerrarConexion();
            } catch (SQLException e) {
                  System.out.println("Error en BuscarElementosCercanos en " + config.getDEPENDENCIA());
                  System.out.println(e);
            }

            return null;

      }

      public static RegistroRutaVO BuscarElemento(String id, String fecha) {
            System.out.println(id);
            try {
                  Conexion conn = new Conexion();
                  Connection con = conn.getConexion();

                  if (con != null) {

                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date();
                        RegistroRutaVO reg = new RegistroRutaVO(id);
                        String[] ids = id.split(",");
                        String query = "SELECT * FROM registro_rutas WHERE Fecha='" + fecha/*dateFormat.format(date)*/ + "'  AND (idUsuarios_Movil='" + ids[0] + "'";
                        int j = 0;

                        for (int i = 1; i < ids.length; i++) {
                              query += " OR idUsuarios_Movil='" + ids[i] + "'";
                        }
                        query += ");";
                        Statement st;
                        st = con.createStatement();
                        System.out.println(query);
                        ResultSet rs = st.executeQuery(query);
                        //System.out.println(rs.next());
                        if (rs.next()) {

                              reg = new RegistroRutaVO(rs.getString("idregistro_rutas"), rs.getString("cp"), rs.getString("idUsuarios_Movil"), rs.getString("Fecha"), rs.getString("Hora"), rs.getString("Latitud"), rs.getString("Longitud"), rs.getString("Ruta"), rs.getString("estadoNotificacion"));
                              j++;
                              rs.absolute(j);
                              System.out.println(reg.getRuta());
                        }
                        st.close();
                        return reg;
                  }
                  conn.cerrarConexion();
            } catch (SQLException e) {
                  System.out.println(e);
            }
            return null;
      }

      public static String ZoomAndCenter(String id, String fecha) {
            System.out.println(id);
            try {
                  Conexion conn = new Conexion();
                  Connection con = conn.getConexion();

                  if (con != null) {

                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date();
                        double R = 6372795.477598;
                        double rad = Math.PI / 180;
                        double lat = 0;
                        double lng = 0;
                        double d = -1;
                        String[] ids = id.split(",");
                        String query = "SELECT * FROM registro_rutas WHERE Fecha='" + /*dateFormat.format(date)*/ fecha + "'  AND (idUsuarios_Movil='" + ids[0] + "'";
                        int j = 0;
                        int k = 0;
                        for (int i = 1; i < ids.length; i++) {
                              query += " OR idUsuarios_Movil='" + ids[i] + "'";
                        }
                        query += ");";
                        Statement st;
                        st = con.createStatement();
                        //System.out.println(query);
                        ResultSet rs = st.executeQuery(query);

                        while (rs.next()) {
                              System.out.println("----------------------------------" + rs.getFloat("Latitud"));
                              if (rs.getFloat("Latitud") != 0) {
                                    lat += rs.getFloat("Latitud");
                                    lng += rs.getFloat("Longitud");
                                    k++;
                              }

                              //reg = new RegistroRutaVO(rs.getString("idregistro_rutas"), rs.getString("cp"), rs.getString("idUsuarios_Movil"), rs.getString("Fecha"), rs.getString("Hora"), rs.getString("Latitud"), rs.getString("Longitud"), rs.getString("Ruta"), rs.getString("estadoNotificacion"));
                              j++;
                              rs.absolute(j);
//                    
//                    ResultSetMetaData rsMetaData = rs.getMetaData();
//                int numberOfColumns = rsMetaData.getColumnCount();
//                // get the column names; column indexes start from 1
//                for (int i = 1; i < numberOfColumns + 1; i++) {
//                    String columnName = rsMetaData.getColumnName(i);
//                    // Get the name of the column's table name
//                    if ("Latitud".equals(columnName)) {
//                        System.out.println("Bingo!");
//                    }
//                }
                        }
                        if (k > 0) {
                              lat = lat / k;
                              lng = lng / k;
                        }

                        j = 0;
                        rs.absolute(j);

                        //System.out.println(lat +"   "+lng);
                        while (rs.next()) {
//                    System.out.println(rs.getFloat("Latitud"));
//                    System.out.println(rs.getFloat("Longitud"));
                              if (rs.getFloat("Latitud") != 0) {
                                    double d_aux = R * Math.acos(Math.sin(rs.getFloat("Latitud") * rad) * Math.sin(lat * rad) + Math.cos(rs.getFloat("Latitud") * rad) * Math.cos(lat * rad) * Math.cos((rs.getFloat("Longitud") * rad) - (lng * rad)));
//                    System.out.println(d);
                                    if (d_aux > d) {
                                          d = d_aux;
                                    }
                              }

                              j++;
                              rs.absolute(j);
                        }

                        st.close();
                        return "{\"lat\":" + lat + ",\"lng\":" + lng + ",\"zoom\":" + d + "}";
                  }
                  conn.cerrarConexion();
            } catch (SQLException e) {
                  System.out.println(e);
            }
            return null;
      }

      public static String LogRutas() {

            String c = "";
            try {
                  Conexion conn = new Conexion();
                  Connection con = conn.getConexion();

                  if (con != null) {

                        int i = 0;
                        Statement st;
                        st = con.createStatement();
                        ResultSet rs = st.executeQuery("SELECT  * FROM rutas;");

                        while (rs.next()) {
                              String ruta = rs.getString("jsonruta").replace('~', '"');
                              ruta = ruta.replace("ruta\": \"", "ruta\": [");
                              ruta = ruta.replace("\", \"fecha\":", "], \"fecha\":");
                              c += "<br>" + ruta + "<br><br><br>";
                              i++;
                              rs.absolute(i);
                        }
                        System.out.println(c);
                        st.close();
                  }
                  conn.cerrarConexion();
            } catch (SQLException e) {
                  System.out.println(e);
            }

            return c;

      }

      public static void saveRuta(String r, String id) {

            try {
                  Conexion conn = new Conexion();
                  Connection con = conn.getConexion();

                  if (con != null) {

                        Statement st;
                        st = con.createStatement();
                        System.out.println("INSERT INTO rutas (`jsonruta`,`id_Usuario_Movil`) VALUES (\"" + r + "\",'" + id + "');");
                        st.executeUpdate("INSERT INTO rutas (`jsonruta`,`id_Usuario_Movil`) VALUES (\"" + r + "\",'" + id + "');");

                        st.close();
                  }
                  conn.cerrarConexion();
            } catch (SQLException e) {
                  System.out.println(e);
            }

      }

      public static JSONObject BuscarElementoFromUserSys(String id, String fecha) {

            JSONObject respuesta = new JSONObject();

            respuesta.put("idSys", id);
            respuesta.put("fecha", fecha);
            JSONArray jsonArray = new JSONArray();
            if (true) {
                  respuesta.put("elementos", jsonArray);
                  return respuesta;
            }
            try {
                  Conexion conn = new Conexion();
                  Connection con = conn.getConexion();

                  if (con != null) {

                        int j = 0;

                        //String query = "SELECT idUsuarios_Movil, Hora, Latitud, Longitud, estadoNotificacion FROM registro_rutas where idUsuarios_Movil in ( SELECT idUsuarios_Movil FROM grupos_usuario_movil where idgruposUsuarioSys in (SELECT idgruposUsuarioSys FROM grupos_usuario_sys where idUsuario_Sys='" + id + "')) AND fecha='" + fecha + "';";
                        //String query = "SELECT idUsuarios_Movil,nombre,apellido_paterno,apellido_materno,img,icon,FireBaseKey FROM usuarios_movil where idUsuarios_Movil in ( SELECT idUsuarios_Movil FROM grupos_usuario_movil where idgruposUsuarioSys in (SELECT idgruposUsuarioSys FROM grupos_usuario_sys where idUsuario_Sys='" + id + "'));";
                        String query = "SELECT idUsuarios_Movil,nombre,apellido_paterno,apellido_materno,img,icon,FireBaseKey FROM usuarios_movil where idUsuarios_Movil in ( SELECT idUsuarios_Movil FROM grupos_usuario_movil where idgruposUsuarioSys in (SELECT idgruposUsuarioSys FROM grupos_usuario_sys where idUsuario_Sys='" + id + "' AND estado=1) AND estado=1);";
                        Statement st;
                        st = con.createStatement();
                        System.out.println(query);
                        ResultSet rs = st.executeQuery(query);
                        //System.out.println(rs.next());
                        while (rs.next()) {
                              JSONObject elemento = new JSONObject();
                              elemento.put("idUsuario_Movil", rs.getString("idUsuarios_Movil"));
                              elemento.put("nombre", rs.getString("nombre"));
                              elemento.put("apellido_paterno", rs.getString("apellido_paterno"));
                              elemento.put("apellido_materno", rs.getString("apellido_materno"));
                              elemento.put("img", rs.getString("img"));
                              elemento.put("icon", rs.getString("icon"));
                              elemento.put("FireBaseKey", rs.getString("FireBaseKey"));
                              //System.out.println(elemento.get("idUsuario_Movil"));
                              //Buscar informacion de perfil
                              //System.out.println(elemento);

                              JSONObject posicion = new JSONObject();
                              //perfil = UsuarioMovilDAO.ConsultarLite(elemento.get("idUsuarios_Movil").toString());
                              posicion = UltimaPosicionElemento(elemento.get("idUsuario_Movil").toString(), fecha);

                              //System.out.println(perfil);
                              elemento.put("posicion", posicion);
                              jsonArray.add(elemento);
                              //j++;
                              //rs.absolute(j);

                        }
                        respuesta.put("elementos", jsonArray);
                        st.close();

                  }
                  conn.cerrarConexion();
            } catch (SQLException e) {
                  System.out.println(e);
            }
            return respuesta;
      }

      public static JSONObject UltimaPosicionElemento(String id, String fecha) {
            //System.out.println(id);
            JSONObject json = new JSONObject();
            json.put("actualizada", false);
            try {
                  Conexion conn = new Conexion();
                  Connection con = conn.getConexion();

                  if (con != null) {
                        String query = "SELECT idUsuarios_Movil, Hora, Latitud, Longitud, estadoNotificacion FROM registro_rutas where idUsuarios_Movil='" + id + "' AND fecha ='" + fecha + "';";

                        Statement st;
                        st = con.createStatement();
                        System.out.println(query);
                        ResultSet rs = st.executeQuery(query);
                        //System.out.println(rs.next());
                        if (rs.next()) {
                              json.put("actualizada", true);
                              json.put("idUsuario_Movil", rs.getString("idUsuarios_Movil"));
                              json.put("hora", rs.getString("Hora"));
                              json.put("lat", rs.getFloat("Latitud"));
                              json.put("lng", rs.getFloat("Longitud"));
                              json.put("estadoNotificacion", rs.getString("estadoNotificacion"));

                        }
                        st.close();
                  }
                  conn.cerrarConexion();
            } catch (SQLException e) {
                  System.out.println(e);
            }
            return json;
      }
}
