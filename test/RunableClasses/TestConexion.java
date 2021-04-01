/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunableClasses;

import Config.config;
import Encriptacion.Encriptar;
import Modelo.Conexion;
import Modelo.Query;
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
      String query="";
      //query = "INSERT INTO `cursos`.`materia` (`nombre_materia`,`id_sucursal`,`id_usuario`) VALUES ('Matematicas',1,1);";
      //System.out.println(Query.insert(query));
      query="SELECT * FROM materia;";
        System.out.println(Query.select(query));
        System.out.println(consultaQuery(query));
        
        //String table = "materia";
       // query = "INSERT INTO grupo (nombre_grupo,id_sucursal) VALUES ('prueba2',1);"; 
        
        //System.out.println(Query.insert(query));

        /*System.out.println(Query.update(query));
        JSONObject json = new JSONObject();
         json.put("institucion", "demo update 2");
         json.put("columna1", "10");
         json.put("columna2", "20");
         json.put("columna3", "30");
         JSONObject where = new JSONObject();
         where.put("id", "2");
         System.out.println(Query.update(Query.createQueryUpdateANDwithColumns("tipo_usuario", json, where)));
        */
                 
      //usar metodo de insert
       /*query="INSERT INTO `cursos`.`tipo_usuario` (`institucion`) VALUES ('demo3');";
        
        System.out.println(Query.insert(query));
         query="Select * from tipo_usuario;";
         JSONObject json = new JSONObject();
         json.put("institucion", "demo 10");
         json.put("columna1", "1");
         json.put("columna2", "2");
         
         System.out.println(Query.insert(Query.createQueryInsertWithColumns("tipo_usuario", json)));
      // System.out.println(consultaQuery(query));
        System.out.println(Query.select(query));
        System.out.println(Query.execute(query));*/
        
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
