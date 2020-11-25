/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.opentok.OpenTok;
import com.opentok.exception.OpenTokException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Vostro Placas
 */
public class CredencialesDAO {

//    public static boolean ActualizarCredenciales(CredencialesVO reg) {
//        boolean agregado = false;
//        try {
//            Conexion c = new Conexion();
//            Connection con = c.getConexion();
//            if (con != null) {
//
//                PreparedStatement ps;
//                ps = con.prepareStatement("UPDATE usuarios_movil "
//                        + "SET sesion=?,"
//                        + "token=?,"
//                        + "apikey=?"
//                        + " WHERE idUsuarios_Movil= ?;");
//                ps.setString(1, reg.getIdsesion());
//                ps.setString(2, reg.getToken());
//                ps.setString(3, reg.getApikey());
//                ps.setString(4, reg.getIdUsuarios_Movil());
//                ps.executeUpdate();
//                agregado = true;
//                ps.close();
//            }
//            c.cerrarConexion();
//        } catch (SQLException e) {
//            agregado = false;
//        }
//        return agregado;
//    }
    
    
    /*
    public static boolean ActualizarCredenciales(CredencialesVO reg) {
       //System.out.println(reg.getID());
       //System.out.println(reg.getIdUsuarios_Movil());
        //int id;
        //id = Integer.parseInt(reg.getID());
        boolean agregado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();
            if (con != null) {

                if (Integer.parseInt(reg.getID()) > 0) {
                    PreparedStatement ps;
                    ps = con.prepareStatement("UPDATE usuarios_movil "
                            + "SET credenciales=? "
                            + " WHERE idUsuarios_Movil= ?;");
                    ps.setString(1, reg.getID());
                    ps.setString(2, reg.getIdUsuarios_Movil());
                    ps.executeUpdate();
                    agregado = true;
                    ps.close();
                }
                else{
                    PreparedStatement ps;
                    ps = con.prepareStatement("UPDATE usuarios_movil "
                            + "SET credenciales=NULL "
                            + " WHERE idUsuarios_Movil= ?;");
                    ps.setString(1, reg.getIdUsuarios_Movil());
                    ps.executeUpdate();
                    agregado = true;
                    ps.close();
                }

            }
            c.cerrarConexion();
        } catch (SQLException e) {
            agregado = false;
        }
        return agregado;
    }*/

    public static CredencialesVO ConsultaCredenciales(CredencialesVO obj) {
        CredencialesVO object;
        //System.out.println("ConsultaCredencialesFUNCION");
       //System.out.println(obj.getIdUsuarios_Movil());
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {
                //System.out.println("Conexion establecida");
                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT credenciales FROM usuarios_movil WHERE idUsuarios_Movil=" + obj.getIdUsuarios_Movil() + " ;");
                if (rs.next()) {
                   //System.out.println(rs.getString("credenciales"));
                    rs = st.executeQuery("SELECT * FROM credenciales WHERE id=" + rs.getString("credenciales") + " ;");
                    if (rs.next()) {
                        object = new CredencialesVO(rs.getString("sesion"), rs.getString("token"), obj.getIdUsuarios_Movil(), rs.getString("apikey"));
                        //object.setID(rs.getString("credenciales"));
                        return object;
                    }

                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {

        }

        return object = null;
    }

//    public static CredencialesVO GenSesion(String id) throws OpenTokException {
//        int API_KEY = 46208642;
//        String API_SECRET = "d80bacdc33d27887654bc4c2b0192ee8ad81ccde";
//        // Set the following constants to your OpenTok API key and API secret.
//        // See https://tokbox.com/account/.
//        OpenTok opentok = new OpenTok(API_KEY, API_SECRET);
//
//        //Generate a basic session. Or you could use an existing session ID.
//        String sessionId = opentok.createSession().getSessionId();
//
//        String token = opentok.generateToken(sessionId);
//       //System.out.println(sessionId);
//       //System.out.println(token);
//
//        CredencialesVO credenciales = new CredencialesVO(sessionId, token, id, String.valueOf(API_KEY));
//        ActualizarCredenciales(credenciales);
//        return credenciales;
//    }

    public static CredencialesVO ConsultarCredenciales() {
        String credenciales = null;
        CredencialesVO c = null;
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {

                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT  apikey FROM credenciales where id='0'");

                if (rs.next()) {
                    String contador = rs.getString("apikey");
                    rs = st.executeQuery("SELECT  * FROM credenciales where id=('" + contador + "');");
                    if (rs.next()) {
                        credenciales = rs.getString("apikey") + "," + rs.getString("sesion") + "," + rs.getString("token");
                        c = new CredencialesVO(rs.getString("id"), rs.getString("apikey"), rs.getString("sesion"), rs.getString("token"), null);
                        //System.out.println(credenciales);
                        PreparedStatement ps;
                       
                        int cont = Integer.parseInt(contador) + 1;
                        if (cont > 1000) {
                            cont = 1;
                        }
                        ps = con.prepareStatement("UPDATE credenciales "
                                + "SET "
                                + "apikey = ? "
                                + " WHERE id = 0;");
                        ps.setString(1, String.valueOf(cont));
                        ps.executeUpdate();

                        ps.close();
                    }
                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return c;

    }
    
    public static CredencialesVO ConsultarCredenciales(int id) {

        CredencialesVO c = null;
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {

                Statement st;
                st = con.createStatement();

                ResultSet rs = st.executeQuery("SELECT  * FROM emergencia.credenciales where id=('" + id + "');");
                if (rs.next()) {
                    c = new CredencialesVO(rs.getString("id"), rs.getString("apikey"), rs.getString("sesion"), rs.getString("token"), null);
                    //System.out.println(credenciales);
                }

                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return c;

    }
    
        public static CredencialesVO ConsultaCredencialesPorID(String id) {
        CredencialesVO object=null;
        
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {
               
                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM emergencia.credenciales WHERE id='" + id + "';");
               
                    if (rs.next()) {
                        object = new CredencialesVO( rs.getString("apikey"), rs.getString("sesion"), rs.getString("token"));
                        //object.setID(rs.getString("credenciales"));
                        return object;
                    }

                
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {

        }

        return object;
    }

}
