/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Vostro Placas
 */
public class ProyectoDAO {

    public static boolean ActualizarDatos(ProyectoVO p) {

        boolean actualizado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {

//                PreparedStatement ps;
//                ps = con.prepareStatement("UPDATE proyecto "
//                        + "SET "
//                        + "apikey=?,"
//                        + "heroku=?, "
//                        + "FireBaseAuthorization=? "
//                        + " WHERE id= ?;");
//                ps.setString(1, p.getApikey());
//                ps.setString(2, p.getHeroku());
//                ps.setString(3, p.getFireBaseAuthorization());
//                ps.setString(4, p.getId());
//                ps.executeUpdate();
//                actualizado = true;
//                ps.close();
                Statement st;
                st = con.createStatement();
                st.executeUpdate("INSERT INTO proyecto(`apikey`,`heroku`,`FireBaseAuthorization`) VALUES "
                        + "('" + p.getApikey() + "','" + p.getHeroku() + "','" + p.getFireBaseAuthorization() + "')");
                actualizado = true;
                st.close();
            }
            c.cerrarConexion();
        } catch (SQLException e) {
            actualizado = false;
        }
        return actualizado;
    }

    public static ProyectoVO consultar() {
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {

                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT  * FROM proyecto ORDER by id DESC LIMIT 1");

                if (rs.next()) {
                    ProyectoVO p = new ProyectoVO(rs.getString("id"), rs.getString("apikey"), rs.getString("heroku"), rs.getString("FireBaseAuthorization"));

                    return p;
                }
                st.close();
            }
            c.cerrarConexion();
        } catch (SQLException e) {

        }
        ProyectoVO p = null;
        return p;
    }

    public static String consultarFirebaseAuthorization() {
        String Auth = "";
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {

                Statement st;
                st = con.createStatement();
                String query = "SELECT  FireBaseAuthorization FROM proyecto ORDER by id DESC LIMIT 1;";
                System.out.println(query);
                ResultSet rs = st.executeQuery(query);

                if (rs.next()) {
                    Auth = rs.getString("FireBaseAuthorization");
                    return Auth;
                }
                st.close();
            }
            c.cerrarConexion();
        } catch (SQLException e) {
        }

        return Auth;
    }

    public static String ConsultarTiposEmergencia() {
        String option = "[";
        int i = 0;
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {

                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM tipo_emergencia;");

                while (rs.next()) {
                    //System.out.println(rs.getString("nombre"));

                    //option += "<option value=\"" + rs.getString("idTipo_Emergencia") + "\">" + rs.getString("nombre") + "</<option>";
                    option += "{&quot;id&quot;:" + rs.getInt("idTipo_Emergencia") + ",&quot;Incidente&quot;:&quot;" + rs.getString("nombre") + "&quot;,&quot;Prioridad&quot;:&quot;" + rs.getString("prioridad") + "&quot;},";
                    i++;
                    rs.absolute(i);
                }
                option=option.substring(0, option.length() - 1);
                option+="]";
                //System.out.println(option);
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {

        }

        return option;
    }

    public static String ConsultarDependenciasAsociadas() {
        String dependencias = "";
        String data = "";
        int i = 0;
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {
                System.out.println("conexion");

                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM dependencias WHERE activo=1;");

                while (rs.next()) {

                    data += rs.getString("id") + "," + rs.getString("nombre") + "," + rs.getString("url") + "," + rs.getString("alias") +"," + rs.getString("icon") + "|";
                    dependencias += "<div class=\"col-4 p-1\">  <label class=\"labelDependencias\" ><input type=\"checkbox\" class=\"btnlike\" id=\"" + rs.getString("id") + "\" > <span id=\"span" + rs.getString("id") + "\">" + rs.getString("alias") + " </span></label></div>";
                    i++;
                    rs.absolute(i);
                }
                if (!"".equals(dependencias)) {
                    dependencias += "<input type=\"hidden\" id=\"DependenciasID\"  value=\"" + data.substring(0, data.length() - 1) + "\">";
                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {

        }

        return dependencias;
    }
    public static String ConsultarDependenciasAsociadas(String tipo_usuario,String tipo_servicio) {
        String dependencias = "";
        String data = "";
        int i = 0;
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {
                System.out.println("conexion");

                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM dependencias WHERE activo=1 and tipo_usuario = '"+tipo_usuario+"' AND tipo_servicio='"+tipo_servicio+"';");

                while (rs.next()) {

                    data += rs.getString("id") + "," + rs.getString("nombre") + "," + rs.getString("url") + "," + rs.getString("alias") +"," + rs.getString("icon") + "|";
                    dependencias += "<div class=\"col-4 p-1\">  <label class=\"labelDependencias\" ><input type=\"checkbox\" class=\"btnlike\" id=\"" + rs.getString("id") + "\" > <span id=\"span" + rs.getString("id") + "\">" + rs.getString("alias") + " </span></label></div>";
                    i++;
                    rs.absolute(i);
                }
                if (!"".equals(dependencias)) {
                    dependencias += "<input type=\"hidden\" id=\"DependenciasID\"  value=\"" + data.substring(0, data.length() - 1) + "\">";
                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {

        }

        return dependencias;
    }

    public static String DependenciasAsociadas() {

        String dependencias = "";
        String data = "";
        int i = 0;
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {
                System.out.println("conexion");

                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM dependencias WHERE activo < 2;");

                while (rs.next()) {

                    data += "{\"id\":\"" + rs.getString("id") + "\","
                            + "\"nombre\":\"" + rs.getString("nombre") + "\","
                            + "\"url\":\"" + rs.getString("url") + "\","
                            + "\"alias\":\"" + rs.getString("alias") + "\","
                            + "\"icon\":\"" + rs.getString("icon") + "\","
                            + "\"activo\":\"" + rs.getString("activo") + "\"},";

                    i++;
                    rs.absolute(i);
                }
                
                if(data.length() > 0){
                    dependencias = "[" + data.substring(0, data.length() - 1) + "]";
                }else{
                    dependencias = "[]";
                }
                
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {

        }

        return dependencias;

    }

    public static boolean AgregarDependencia(String nombre, String alias, String url,String icon) {
        boolean agregado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {

                Statement st;
                st = con.createStatement();
                st.executeUpdate("INSERT INTO `dependencias` (`nombre`, `url`, `alias`, `icon`, `activo`) VALUES ('" + nombre + "', '" + url + "', '" + alias + "', '" + icon + "','" + 0 + "');");
                agregado = true;
                st.close();
            }
            c.cerrarConexion();
        } catch (SQLException e) {
            agregado = false;
        }
        return agregado;

    }

    public static boolean DependenciaCambiarEstado(String id, String activo) {

        boolean actualizado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {

                PreparedStatement ps;
                ps = con.prepareStatement("UPDATE dependencias SET activo=? WHERE id=?;");
                ps.setString(1, activo);
                ps.setString(2, id);
                ps.executeUpdate();
                actualizado = true;
                ps.close();

            }
            c.cerrarConexion();
        } catch (SQLException e) {
            actualizado = false;
        }
        return actualizado;
    }

}
