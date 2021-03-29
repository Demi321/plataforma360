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
public class GrupoDAO {

    public static String[] BuscarGrupos(String IdUsrSys) {
        String[] grupos = new String[100];
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {

                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT idgruposUsuarioSys, nombre FROM grupos_usuario_sys WHERE idUsuario_Sys=" + IdUsrSys + " AND estado='1';");
                int i = 0;
                while (rs.next()) {
                    
                    //System.out.println(rs.getString("idgruposUsuarioSys"));
                    //System.out.println(rs.getString("nombre"));
                    grupos[i*2]=rs.getString("idgruposUsuarioSys");
                    
                    grupos[i*2+1]=rs.getString("nombre");
                    i++;
                    rs.absolute(i);
                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {

        }

        return grupos;
    }

    public static String[] BuscarIntegrantes(String id_Grupo) {
        //System.out.println("\nIntegrantesDAO\n");
        String[] integrantes = new String[102];
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {

                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT idUsuarios_Movil FROM grupos_usuario_movil where idgruposUsuarioSys="+id_Grupo+" AND estado='1';");
                int i = 0;
                while (rs.next()) {
                    
                    //System.out.println(rs.getString("idUsuarios_Movil"));
                    integrantes[i*2]=rs.getString("idUsuarios_Movil");
                    i++;
                    rs.absolute(i);
                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {

        }

        return integrantes;
    }

    public static String[] BuscarNombresIntegrantes(String[] integrantes) {
        //System.out.println("\nIntegrantesNombresDAO\n");
        
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {
                int k=0;
                while(integrantes[k]!=null){
                    Statement st;
                    st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT nombre, apellido_paterno, apellido_materno FROM usuarios_movil WHERE  idUsuarios_Movil="+integrantes[k]+";");
                   
                    if (rs.next()) {

                        //System.out.println(rs.getString("nombre"));
                        integrantes[k+1] = rs.getString("nombre")+" "+rs.getString("apellido_paterno")+" "+rs.getString("apellido_materno");
                        
                    }else{
                        System.out.println("ERROR: Usuario movil no esta en la base de datos.");
                    }
                    st.close(); 
                    k+=2;
                }

                
            }
            conn.cerrarConexion();
        } catch (SQLException e) {

        }

        return integrantes;
        
    }

    public static boolean NuevoGrupo(String idSys, String grupo) {
    boolean agregado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

//            System.out.println(c);
//            System.out.println(con);
            if (con != null) {

                Statement st;
                st = con.createStatement();
               //System.out.println("INSERT INTO `grupos_usuario_sys` (`idUsuario_Sys`, `nombre`, `estado`) VALUES ('"+idSys+"', '"+grupo+"','1');");
               String query="INSERT INTO `grupos_usuario_sys` (`idUsuario_Sys`, `nombre`, `estado`) VALUES ('"+idSys+"', '"+grupo+"','1');";
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

    public static boolean NuevoIntegrante(String id_Grupo, String idUsuarios_Movil) {
       boolean agregado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            //System.out.println(c);
            //System.out.println(con);
            if (con != null) {

                Statement st;
                st = con.createStatement();
                st.executeUpdate("INSERT INTO `grupos_usuario_movil` (`idgruposUsuarioSys`, `idUsuarios_Movil`, `estado`) VALUES ('"+id_Grupo+"', '"+idUsuarios_Movil+"','1');");
                agregado = true;
                st.close();
            }
            c.cerrarConexion();
        } catch (SQLException e) {
            agregado = false;
        }
        return agregado;
    }
    
    public static boolean ExisteUsuarioEnGrupo(String id_Grupo, String idUsuarios_Movil) {
        boolean existe = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();
            if (con != null) {

                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM grupos_usuario_movil where idUsuarios_Movil="+idUsuarios_Movil+" AND idgruposUsuarioSys="+id_Grupo+" AND estado='1';");

                existe = rs.next();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }

    public static boolean ExisteGrupo(String idSys, String grupo) {
     boolean existe = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();
            if (con != null) {

                Statement st;
                st = con.createStatement();
               //System.out.println("SELECT * FROM grupos_usuario_sys where idUsuario_Sys="+idSys+" AND nombre='"+grupo+"' AND estado='1';");
                ResultSet rs = st.executeQuery("SELECT * FROM grupos_usuario_sys where idUsuario_Sys="+idSys+" AND nombre='"+grupo+"' AND estado='1';");

                existe = rs.next();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }

    public static boolean EliminarIntegrante(String id_Grupo, String idUsuarios_Movil) {
        boolean eliminado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();
            if (con != null) {PreparedStatement ps;
                ps = con.prepareStatement("UPDATE grupos_usuario_movil "
                        + "SET estado=? "
                        + "where "
                        + "idgruposUsuarioSys=? "
                        + "AND "
                        + "idUsuarios_Movil=?; ");
                ps.setString(1, "0");
                ps.setString(2, id_Grupo);
                ps.setString(3, idUsuarios_Movil);
                ps.executeUpdate();
                Statement st;
                st = con.createStatement();
               ResultSet rs = st.executeQuery("SELECT * FROM grupos_usuario_movil where idgruposUsuarioSys="+id_Grupo+" AND idUsuarios_Movil="+idUsuarios_Movil+" AND estado='1';");

                eliminado = !rs.next();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return eliminado;
    }

    public static boolean EliminarGrupo(String id_Grupo) {
        boolean eliminado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();
            if (con != null) {PreparedStatement ps;
                ps = con.prepareStatement("UPDATE grupos_usuario_sys "
                        + "SET estado=? "
                        + "where "
                        + "idgruposUsuarioSys=? ");
                ps.setString(1, "0");
                ps.setString(2, id_Grupo);
                ps.executeUpdate();
                Statement st;
                st = con.createStatement();
               ResultSet rs = st.executeQuery("SELECT * FROM grupos_usuario_sys where idgruposUsuarioSys="+id_Grupo+" AND estado='1';");

                eliminado = !rs.next();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return eliminado;
    }

}
