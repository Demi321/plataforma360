/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Vostro Placas
 */
public class UsuarioSysDAO {
    
    public static boolean agregarUsuario(String Nombre, String Apellidos, String Correo, String Rol, String Contrasenia) {
         boolean agregado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            //System.out.println(c);
            //System.out.println(con);
            if (con != null) {

                Statement st;
                st = con.createStatement();
                st.executeUpdate("INSERT INTO usuario_sys (nombre,apellidos,correo,contrasenia,tipo,estatus,sesion,disponibilidad) VALUES "
                        + "('" + Nombre + "','" + Apellidos + "','" + Correo + "','" + Contrasenia + "','" + Rol + "','1','0','0');");
                agregado = true;
                st.close();
            }
            c.cerrarConexion();
        } catch (SQLException e) {
            agregado = false;
        }
        return agregado;
    }
}
