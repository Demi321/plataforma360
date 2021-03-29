/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.opentok.exception.OpenTokException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Vostro Placas
 */
public class TestConexion {

    public static void main(String[] args) throws OpenTokException, InterruptedException, ParseException, IOException {
        //String credenciales = null;
        /* try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {
                System.out.println(con);
                Statement st;
                st = con.createStatement();
                
                    System.out.println("esperando....");
                    st.executeUpdate("INSERT INTO `sos`.`rutas` (`jsonruta`) VALUES ('1001');");
                    System.out.println("Elemento insertado...");
                Thread.sleep(10000);
                System.out.println("Ejecutando");
                
                ResultSet rs = st.executeQuery("select LAST_INSERT_ID();");
                if (rs.next()) {
                    System.out.println("Bingo");
                    System.out.println(rs.getString("LAST_INSERT_ID()"));
                }
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {
            System.out.println(e);
        }*/
        System.out.println("MaxMemory: " + Runtime.getRuntime().maxMemory() / 1024 / 1024);
        System.out.println("FeeMemory: " + Runtime.getRuntime().freeMemory() / 1024 / 1024);
        System.out.println("TotalMemory: " + Runtime.getRuntime().totalMemory() / 1024 / 1024);
        
        System.out.println();
        JSONArray array = Query.execute("SELECT * FROM cmx.wokko WHERE response = 'null';");
        for (Object object : array) {
//            System.out.println(object);
            JSONObject servicio = (JSONObject) object;
            Object id = servicio.get("id");
            System.out.println(id);
            JSONParser parser = new JSONParser();
            JSONObject request = (JSONObject) parser.parse(servicio.get("request").toString());
            System.out.println(request);
            Object respon = servicio.get("response");
            System.out.println(respon);
            JSONObject response = Request.request.POST("https://claro360.wokko.io:8443/referencia/sendClaroToWokko/", request);
            System.out.println(response);
            boolean updated = Query.update("UPDATE wokko SET response = '"+response+"' WHERE (`id` = '"+id+"');");
            if(updated){
                System.out.println("actualizado");
            }else{
                System.out.println("Error");
               break;
            }
        }
    }

}
