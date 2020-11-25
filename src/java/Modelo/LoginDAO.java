/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Encriptacion.Encriptar;
import com.opentok.exception.OpenTokException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Vostro Placas
 */
public class LoginDAO {

    public static JSONObject login(String usr, String pwd) {
        //UsuarioSysVO usrsys = new UsuarioSysVO("0");
        JSONObject usrsys = new JSONObject();
        usrsys.put("idUsuario_Sys", "0");

        String query = "SELECT * FROM usuario_sys WHERE correo='" + usr + "' AND contrasenia='" + pwd + "';";
        System.out.println(query);
        JSONArray jsonArray = Query.execute(query);
        if (jsonArray.size() > 0) {
            JSONObject respuesta = (JSONObject) jsonArray.get(0);
            respuesta.remove("contrasenia");
//            query = "SELECT * FROM configuracion_personalizada WHERE idUsuario_Sys = '" + respuesta.get("idUsuario_Sys") + "';";
//            System.out.println(query);
//            JSONObject conf = Query.select(query);
//            if (conf != null) {
//                respuesta.put("configuracion", conf);
//            } else {
//                query = "SELECT * FROM configuracion_personalizada WHERE id = '1';"; //configuracion default
//                System.out.println(query);
//                conf = Query.select(query);
//                respuesta.put("configuracion", conf);
//            }
//            System.out.println("***************");
//            System.out.println(respuesta);
            return respuesta;
        } else {
            query = "SELECT * FROM usuario_sys WHERE usuario='" + usr + "' AND contrasenia='" + Encriptar.Next(pwd) + "';";
            System.out.println(query);
            jsonArray = Query.execute(query);
            if (jsonArray.size() > 0) {
                JSONObject respuesta = (JSONObject) jsonArray.get(0);
                respuesta.remove("contrasenia");
//                query = "SELECT * FROM configuracion_personalizada WHERE idUsuario_Sys = '" + respuesta.get("idUsuario_Sys") + "';";
//                System.out.println(query);
//                JSONObject conf = Query.select(query);
//                if (conf != null) {
//                    respuesta.put("configuracion", conf);
//                } else {
//                    query = "SELECT * FROM configuracion_personalizada WHERE id = '1';"; //configuracion default
//                    System.out.println(query);
//                    conf = Query.select(query);
//                    respuesta.put("configuracion", conf);
//                }
//                System.out.println("***************");
//                System.out.println(respuesta);
                return respuesta;
            }
        }

        /*try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if (con != null) {

                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM usuario_sys WHERE correo='" + usr + "' AND contrasenia='" + pwd + "'");
                if (rs.next()) {
                    //System.out.println("Autorizado");
                    //usrsys = new UsuarioSysVO(rs.getString("idUsuario_Sys"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("correo"), "x", rs.getString("tipo"), rs.getString("estatus"), rs.getString("sesion"), rs.getString("disponibilidad"));
                    
                    String id = rs.getString("idUsuario_Sys");

                    PreparedStatement ps;
                    ps = con.prepareStatement("UPDATE usuario_sys "
                            + "SET "
                            + "disponibilidad=?"
                            + " WHERE idUsuario_Sys= ?;");
                    ps.setString(1, "1");
                    ps.setString(2, id);
                    ps.executeUpdate();
                    ps.close();

                    //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",usrsys.getNombre());
                    //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Bienvenido",usrsys.getNombre());
                } else {
                    rs = st.executeQuery("SELECT * FROM usuario_sys WHERE usuario='" + usr + "' AND contrasenia='" + Encriptar.Next(pwd) + "'");
                    if (rs.next()) {
                        //System.out.println("Autorizado");
                        usrsys = new UsuarioSysVO(rs.getString("idUsuario_Sys"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("correo"), "x", rs.getString("tipo"), rs.getString("estatus"), rs.getString("sesion"), rs.getString("disponibilidad"));
                        String id = rs.getString("idUsuario_Sys");

                        PreparedStatement ps;
                        ps = con.prepareStatement("UPDATE usuario_sys "
                                + "SET "
                                + "disponibilidad=?"
                                + " WHERE idUsuario_Sys= ?;");
                        ps.setString(1, "1");
                        ps.setString(2, id);
                        ps.executeUpdate();
                        ps.close();

                        //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",usrsys.getNombre());
                        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Bienvenido",usrsys.getNombre());
                    }
                }

                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {
        }
        //System.out.println("Login correcto");*/
        return usrsys;
    }

    public static UsuarioSysVO login(String usr) {
        UsuarioSysVO usrsys = new UsuarioSysVO("0");
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if (con != null) {

                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM usuario_sys WHERE usuario='" + usr + "'");
                if (rs.next()) {
                    //System.out.println("Autorizado");
                    usrsys = new UsuarioSysVO(rs.getString("idUsuario_Sys"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("correo"), "x", rs.getString("tipo"), rs.getString("estatus"), rs.getString("sesion"), rs.getString("disponibilidad"));
                    String id = rs.getString("idUsuario_Sys");

                    PreparedStatement ps;
                    ps = con.prepareStatement("UPDATE usuario_sys "
                            + "SET "
                            + "disponibilidad=?"
                            + " WHERE idUsuario_Sys= ?;");
                    ps.setString(1, "1");
                    ps.setString(2, id);
                    ps.executeUpdate();
                    ps.close();

                    //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",usrsys.getNombre());
                    //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Bienvenido",usrsys.getNombre());
                }
            }
            conn.cerrarConexion();
        } catch (SQLException e) {
        }
        //System.out.println("Login correcto");

        return usrsys;
    }

}
