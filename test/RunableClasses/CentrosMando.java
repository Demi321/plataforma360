/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunableClasses;

import Encriptacion.Encriptar;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author moises
 */
public class CentrosMando {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {
                //System.out.println(con);
                Statement st;
                st = con.createStatement();
                String query="SELECT * FROM controller.centrosdemando;";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    //System.out.println(Encriptacion.Encriptar.Reverse(rs.getString("URL")));
                    PreparedStatement ps;
                    ps = con.prepareStatement("UPDATE centrosdemando SET nombre = '"+Encriptar.Next(rs.getString("nombre"))+"' , URL='"+Encriptar.Next(rs.getString("URL"))+"' WHERE id="+rs.getInt("id")+";");
//                    ps.setString(1, reg.getRuta());
//                    ps.setString(2, rs.getString("idregistro_rutas"));
                    ps.executeUpdate();
                    //agregado = true;
                    ps.close();
                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
}
