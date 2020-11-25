/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunableClasses;

import Config.config;
import Encriptacion.Encriptar;
import Modelo.Conexion;
import com.opentok.exception.OpenTokException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Vostro Placas
 */
public class TestConexion {

    public static void main(String[] args) throws OpenTokException, InterruptedException, Exception {
      String query="Select * from proyecto";
        System.out.println(consultaQuery(query));
    }
    
    
    static public JSONArray consultaQuery(String query){
        JSONArray jsonArray = new JSONArray();
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {
                System.out.println(con);
                Statement st;
                st = con.createStatement();

                System.out.println("esperando....");
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    String name = rsmd.getColumnName(1);
                    //System.out.println(name);
                    int count = rsmd.getColumnCount();
                    //System.out.println(count);
                    JSONObject json = new JSONObject();
                    for(int i=1;i<=count;i++){
                        json.put(rsmd.getColumnName(i),rs.getString(i));
                    }
                    jsonArray.add(json);
                    //System.out.println(json);
                }
                
                //System.out.println(jsonArray);
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return jsonArray;
    }

}
