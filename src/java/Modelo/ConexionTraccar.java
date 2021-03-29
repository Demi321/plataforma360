package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ConexionTraccar {

    static public String bd = "traccar";
    static public String login = "root";
    //static public String password = "prueba12345";
    static public String password = "root";
    static public String url = "jdbc:mysql://52.205.151.208/" + bd;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public ConexionTraccar() {
        try {
            //

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url + "?" + "user=" + login + "&password=" + password + "&autoReconnect=true&useSSL=false&useTimezone&serverTimezone=UTC");

            if (conn != null) {
                //System.out.println("Connecting database [" + conn + "]OK");
            }

        } catch (SQLException e) {
            System.out.println("Exception conexion: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Exception driver: " + e.getMessage());
        }
    }

    public Connection getConexion() {
        return conn;
    }

    public void cerrarConexion() {
        //System.out.println("Cerrando conexion:[" + conn + "] OK");
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {

                System.out.println(e);
            }
        }

    }
}
