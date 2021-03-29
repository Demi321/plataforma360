/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Vostro Placas
 */
public class EmpresasDAO {

    public static String Consultar() {
        String option = null;
        int i = 0;
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {

                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT empresa FROM empresas;");

                while (rs.next()) {

                    option += "<option>" + rs.getString("empresa") + "</<option>";
                    i++;
                    rs.absolute(i);
                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {

        }

        return option;
    }
}
