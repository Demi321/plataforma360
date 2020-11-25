package Modelo;

import Config.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Conexion {

    //static public String bd = "sinaloa";
    static public String login = config.getMYSQL_login();
    static public String password = config.getMYSQL_password();
//    static public String login = "usrsos";
//    static public String password = "@soporte321SOS";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public Conexion() {

        try {
            String bd = config.getMYSQL_BD();
            String url = "jdbc:mysql://localhost/" + bd;
            //System.out.println(login);
            //System.out.println(password);
            //System.out.println(bd);
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
