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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONObject;

/**
 *
 * @author Vostro Placas
 */
public class UsuarioMovilDAO {

    public static boolean agregarUsuario(UsuarioMovilVO usr) {
        boolean agregado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

//            System.out.println(c);
//            System.out.println(con);
            if (con != null) {

                Statement st;
                st = con.createStatement();

                st.executeUpdate("INSERT INTO usuarios_movil(`idUsuarios_Movil`,`nombre`,`apellido_paterno`,`apellido_materno`,`correo`,`telefono`,`contacto_nombre`,`contacto_telefono`,`alergias`,`rh`,`fecha_nacimiento`,`genero`,`direccion`,`cp`,`condicion_medica`,`img`) VALUES "
                        + "('" + usr.getIdUsuarios_Movil() + "','" + usr.getNombre() + "','" + usr.getApellido_paterno() + "','" + usr.getApellido_materno() + "','" + usr.getCorreo() + "','" + usr.getTelefono() + "','" + usr.getContacto_nombre() + "','" + usr.getContacto_telefono() + "','" + usr.getAlergias() + "','" + usr.getRh() + "','" + usr.getFecha_nacimiento() + "','" + usr.getGenero() + "','" + usr.getDireccion() + "','" + usr.getCp() + "','" + usr.getCondicion_medica() + "','" + usr.getImg() + "')");
                agregado = true;
                st.close();
            }
            c.cerrarConexion();
        } catch (SQLException e) {
            agregado = false;
        }
        return agregado;
    }

    public static boolean agregarUsuario(JSONObject usr) {
        boolean agregado = false;
        try {
            String query = "INSERT INTO usuarios_movil("
                    + "`idUsuarios_Movil`,`nombre`,`apellido_paterno`,`apellido_materno`,`correo`,"
                    + "`telefono`,`contacto_nombre`,`contacto_telefono`,`alergias`,`rh`,`fecha_nacimiento`,"
                    + "`genero`,`direccion`,`cp`,`condicion_medica`,`img`, `tipo_usuario`,`tipo_servicio`) VALUES "
                    + "('" + usr.get("idUsuarios_Movil") + "','" + usr.get("nombre") + "','" + usr.get("apellido_paterno")
                    + "','" + usr.get("apellido_materno") + "','" + usr.get("correo") + "','" + usr.get("telefono")
                    + "','" + usr.get("contacto_nombre") + "','" + usr.get("contacto_telefono") + "','" + usr.get("alergias")
                    + "','" + usr.get("rh") + "','" + usr.get("fecha_nacimiento") + "','" + usr.get("genero")
                    + "','" + usr.get("direccion") + "','" + usr.get("cp") + "','" + usr.get("condicion_medica")
                    + "','" + usr.get("Img") + "', '" + usr.get("tipo_usuario") + "', '" + usr.get("tipo_servicio") + "');";
            Query.insert(query);
            agregado = true;
        } catch (Exception e) {
            agregado = false;
        }
        return agregado;
    }

    public static UsuarioMovilVO Consultar(String id) {

        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {

                Statement st;
                st = con.createStatement();
                System.out.println("SELECT * FROM usuarios_movil WHERE idUsuarios_Movil=" + id + " ;");
                ResultSet rs = st.executeQuery("SELECT * FROM usuarios_movil WHERE idUsuarios_Movil=" + id + " ;");

                if (rs.next()) {
                    UsuarioMovilVO usr = new UsuarioMovilVO(id, rs.getString("nombre"), rs.getString("apellido_paterno"), rs.getString("apellido_materno"), rs.getString("fecha_nacimiento"), rs.getString("correo"), rs.getString("telefono"), rs.getString("genero"), rs.getString("rh"), rs.getString("alergias"), rs.getString("condicion_medica"), rs.getString("direccion"), rs.getString("cp"), rs.getString("contacto_nombre"), rs.getString("contacto_telefono"), rs.getString("img"), rs.getString("icon"));

                    return usr;
                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {

        }
        UsuarioMovilVO usr = null;
        return usr;
    }

    public static boolean ExisteUsuario(String id) {
        boolean existe = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();
            if (con != null) {

                Statement st;
                st = con.createStatement();
                String query = "SELECT * FROM usuarios_movil WHERE idUsuarios_Movil='" + id + "';";
                System.out.println(query);
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    existe = true;
                }

                //existe = rs.next();
                st.close();
            }
            c.cerrarConexion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }

    public static boolean ExisteUsuarioDirectorio(String id) {
        boolean existe = false;
        UsuarioMovilVO usr = null;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();
            if (con != null) {

                Statement st;
                st = con.createStatement();
                String query = "SELECT id FROM directorio where idUsuario='" + id + "' AND activo=1;";
                ResultSet rs = st.executeQuery(query);
                existe = rs.next();
                st.close();
            }
            c.cerrarConexion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }

    public static String UsuarioFirebase(String id) {
        String existe = "";
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();
            if (con != null) {

                Statement st;
                st = con.createStatement();
                String query = "SELECT FireBaseKey FROM usuarios_movil WHERE idUsuarios_Movil='" + id + "';";
                System.out.println(query);
                ResultSet rs = st.executeQuery(query);

                existe = rs.getString("FireBaseKey");
                st.close();
            }
            c.cerrarConexion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }

    public static boolean ActualizarDatos(UsuarioMovilVO usr) {

        boolean actualizado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {
                System.out.println("ActualizarDatos");
                PreparedStatement ps;
                ps = con.prepareStatement("UPDATE usuarios_movil "
                        + "SET "
                        + "nombre=?,"
                        + "apellido_paterno=?,"
                        + "apellido_materno=?,"
                        + "fecha_nacimiento=?,"
                        + "correo=?,"
                        + "telefono=?,"
                        + "genero=?,"
                        + "rh=?,"
                        + " alergias=?,"
                        + "condicion_medica=?,"
                        + "direccion=?,"
                        + " cp=?,"
                        + "contacto_nombre=?,"
                        + "contacto_telefono=?"
                        + " WHERE idUsuarios_Movil= ?;");
                ps.setString(1, usr.getNombre());
                ps.setString(2, usr.getApellido_paterno());
                ps.setString(3, usr.getApellido_materno());
                ps.setString(4, usr.getFecha_nacimiento());
                ps.setString(5, usr.getCorreo());
                ps.setString(6, usr.getTelefono());
                ps.setString(7, usr.getGenero());
                ps.setString(8, usr.getRh());
                ps.setString(9, usr.getAlergias());
                ps.setString(10, usr.getCondicion_medica());
                ps.setString(11, usr.getDireccion());
                ps.setString(12, usr.getCp());
                ps.setString(13, usr.getContacto_nombre());
                ps.setString(14, usr.getContacto_telefono());
                ps.setString(15, usr.getIdUsuarios_Movil());
                ps.executeUpdate();
                actualizado = true;
                System.out.println("ActualizarDatos---ok");
                ps.close();
            }
            c.cerrarConexion();
        } catch (SQLException e) {
            actualizado = false;
        }
        return actualizado;
    }

    public static boolean ActualizarDatos(JSONObject usr) {

        boolean actualizado = false;
        try {
            String query = "UPDATE usuarios_movil SET "
                    + "nombre = '" + usr.get("nombre") + "', apellido_paterno = '" + usr.get("apellido_paterno") + "',"
                    + "apellido_materno = '" + usr.get("apellido_materno") + "', fecha_nacimiento='" + usr.get("fecha_nacimiento") + "',"
                    + "correo='" + usr.get("correo") + "', telefono='" + usr.get("telefono") + "',genero='" + usr.get("genero") + "',"
                    + "rh='" + usr.get("rh") + "',alergias = '" + usr.get("alergias") + "', condicion_medica= '" + usr.get("condicion_medica") + "',"
                    + "direccion='" + usr.get("direccion") + "', cp='" + usr.get("cp") + "', contacto_nombre='" + usr.get("contacto_nombre") + "',"
                    + "contacto_telefono = '" + usr.get("contacto_telefono") + "', tipo_usuario = '"+usr.get("tipo_usuario")+"',"
                    + "tipo_servicio = '"+usr.get("tipo_servicio")+"' WHERE idUsuarios_Movil = '" + usr.get("idUsuarios_Movil") + "';";
            actualizado = Query.update(query);
            System.out.println("ActualizarDatos---ok");
        } catch (Exception e) {
            actualizado = false;
        }
        return actualizado;
    }

    public static boolean ActualizarFoto(UsuarioMovilVO usr) {
        boolean actualizado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {

                PreparedStatement ps;
                ps = con.prepareStatement("UPDATE usuarios_movil "
                        + "SET "
                        + "img=?, "
                        + "icon=?"
                        + " WHERE idUsuarios_Movil= ?;");
                ps.setString(1, usr.getImg());
                ps.setString(2, usr.getIcon());
                ps.setString(3, usr.getIdUsuarios_Movil());
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

    public static boolean ActualizarFoto(JSONObject usr) {
        boolean actualizado = false;
        try {
            String query = "UPDATE usuarios_movil SET img='" + usr.get("Img") + "', icon='" + usr.get("icon") + "' WHERE idUsuarios_Movil = '" + usr.get("idUsuarios_Movil") + "';";
            actualizado = Query.update(query);
        } catch (Exception e) {
            actualizado = false;
        }
        return actualizado;
    }

    public static boolean llamando(String idUsuarios_Movil, String modo) {
        boolean agregado = false;
        try {

            Conexion c = new Conexion();
            Connection con = c.getConexion();
//
//            System.out.println(c);
//            System.out.println(con);
            if (con != null) {
                Statement st;
                st = con.createStatement();
                st.executeUpdate("INSERT INTO emergencia.llamando(idUsuarios_Movil,modo_llamada) VALUES ('" + idUsuarios_Movil + "','" + modo + "');");
                agregado = true;
                st.close();
            }
            c.cerrarConexion();
        } catch (SQLException e) {

        }
        return agregado;

    }

    public static void Atendido(String id) {
        //boolean agregado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();
//
//            System.out.println(c);
//            System.out.println(con);
            if (con != null) {

                Statement st;
                st = con.createStatement();
                System.out.println("DELETE FROM emergencia.llamando WHERE idUsuarios_Movil=" + id + ";");
                st.executeUpdate("DELETE FROM emergencia.llamando WHERE idUsuarios_Movil=" + id + ";");
                // agregado = true;
                st.close();
            }
            c.cerrarConexion();
        } catch (SQLException e) {
            // agregado = false;
        }
        //return agregado;
    }

    public static boolean EnLinea(UsuarioMovilVO obj) {
        boolean existe = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();
            if (con != null) {

                Statement st;
                st = con.createStatement();
                System.out.println("SELECT id FROM emergencia.llamando WHERE idUsuarios_Movil='" + obj.getIdUsuarios_Movil() + "';");
                ResultSet rs = st.executeQuery("SELECT id FROM emergencia.llamando WHERE idUsuarios_Movil='" + obj.getIdUsuarios_Movil() + "';");

                existe = rs.next();
                st.close();
            }
            c.cerrarConexion();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }

    public static UsuarioMovilVO ObtenerCoordenadas(String idUsuarios_Movil) {
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {

                DateFormat dateFormat = new SimpleDateFormat("d-M-yyyy");
                Date date = new Date();
                // System.out.println(dateFormat.format(date));
                Statement st;
                st = con.createStatement();
                System.out.println("SELECT * FROM registro_rutas WHERE idUsuarios_Movil=" + idUsuarios_Movil + " AND Fecha='" + dateFormat.format(date) + "';");
                ResultSet rs = st.executeQuery("SELECT * FROM registro_rutas WHERE idUsuarios_Movil=" + idUsuarios_Movil + " AND Fecha='" + dateFormat.format(date) + "';");

                if (rs.next()) {
                    UsuarioMovilVO usr = new UsuarioMovilVO(idUsuarios_Movil, rs.getString("Latitud"), rs.getString("Longitud"));

                    return usr;
                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {

        }
        UsuarioMovilVO usr = null;
        return usr;
    }

    public static String Img(String idUsuarios_Movil) {
        String img = null;
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {

                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT img FROM usuarios_movil WHERE idUsuarios_Movil=" + idUsuarios_Movil + ";");

                if (rs.next()) {

                    img = rs.getString("img");
                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {

        }

        return img;
    }

    public static boolean gps(UsuarioMovilVO usr, DateVO date) {

        boolean agregado = false;

        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();
            if (con != null) {
                Statement st;
                st = con.createStatement();

                ResultSet rs = st.executeQuery("SELECT idregistro_rutas FROM registro_rutas  WHERE idUsuarios_Movil='" + usr.getIdUsuarios_Movil() + "' and Fecha = '" + date.getFecha() + "';");

                if (rs.next()) {
//                    System.out.println("Actualizando");
//                    System.out.println(rs.getString("idregistro_rutas"));
                    PreparedStatement ps;
                    ps = con.prepareStatement("UPDATE registro_rutas "
                            + "SET Hora=?, Latitud=?, Longitud=?"
                            + " WHERE idregistro_rutas= ?");
                    ps.setString(1, date.getHora());
                    ps.setString(2, usr.getLatitud());
                    ps.setString(3, usr.getLongitud());
                    ps.setString(4, rs.getString("idregistro_rutas"));
                    ps.executeUpdate();
                    agregado = true;
                    ps.close();
                } else {
                    st.executeUpdate("INSERT INTO registro_rutas(idUsuarios_Movil,Fecha,Hora,Latitud,Longitud) VALUES ('" + usr.getIdUsuarios_Movil() + "','" + date.getFecha() + "','" + date.getHora() + "','" + usr.getLatitud() + "','" + usr.getLongitud() + "');");
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

    public static UsuarioMovilVO FireBaseKey(String idUsuarios_Movil) {
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {

                Statement st;
                st = con.createStatement();
                String query = "SELECT FireBaseKey FROM usuarios_movil WHERE idUsuarios_Movil='" + idUsuarios_Movil + "';";
                System.out.println(query);
                ResultSet rs = st.executeQuery(query);

                if (rs.next()) {
                    UsuarioMovilVO usr = new UsuarioMovilVO(idUsuarios_Movil, rs.getString("FireBaseKey"));

                    return usr;
                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {

        }
        UsuarioMovilVO usr = new UsuarioMovilVO(idUsuarios_Movil, "null");
        return usr;

    }

    public static boolean SetFireBaseKey(String id, String FirebaseKey) {
        boolean actualizado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {

                PreparedStatement ps;
                ps = con.prepareStatement("UPDATE usuarios_movil "
                        + "SET "
                        + "FireBaseKey=?"
                        + " WHERE idUsuarios_Movil= ?;");
                ps.setString(1, FirebaseKey);
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

    public static String IdLlamada(String IdUsuarios_Movil) {
        String id = null;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();
            if (con != null) {

                Statement st;
                st = con.createStatement();
                System.out.println("SELECT id FROM emergencia.llamando WHERE idUsuarios_Movil='" + IdUsuarios_Movil + "';");
                ResultSet rs = st.executeQuery("SELECT id FROM emergencia.llamando WHERE idUsuarios_Movil='" + IdUsuarios_Movil + "';");

                if (rs.next()) {

                    id = rs.getString("id");
                }
                st.close();
            }
            c.cerrarConexion();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public static JSONObject ModoLlamada(String IdModo) {
        JSONObject modo = new JSONObject();
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();
            if (con != null) {

                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM modo_llamada where idModo_Llamada ='" + IdModo + "';");

                if (rs.next()) {

                    modo.put("id", rs.getString("idModo_Llamada"));
                    modo.put("nombre", rs.getString("nombre"));
                    modo.put("clave", rs.getString("clave"));
                }
                st.close();
            }
            c.cerrarConexion();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modo;
    }
}
