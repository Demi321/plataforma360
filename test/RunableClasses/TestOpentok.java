/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunableClasses;

import Encriptacion.Encriptar;
import com.opentok.Archive;
import com.opentok.ArchiveMode;
import com.opentok.MediaMode;
import com.opentok.OpenTok;
import com.opentok.Role;
import com.opentok.Session;
import com.opentok.SessionProperties;
import com.opentok.TokenOptions;
import com.opentok.exception.OpenTokException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Vostro Placas
 */
public class TestOpentok {

    public static void startsession(String sessionId, String name) throws OpenTokException {
        System.out.println("Archiving");
        OpenTok opentok = new OpenTok(46345502, "36026f1a607d7675138715777563080d0c5e83c3");
// A simple Archive (without a name)
        Archive archive = opentok.startArchive(sessionId, name);
        // Store this archiveId in the database for later use
        String archiveId = archive.getId();
        System.out.println(archiveId);
    }
    public static void opentok() throws OpenTokException {

        int apiKey = 46345502;
        String apiSecret = "36026f1a607d7675138715777563080d0c5e83c3";
        
        OpenTok opentok;
        String sessionId;
        
        opentok = new OpenTok(apiKey, apiSecret);
        System.out.println("Objeto opentok creado");
            Session session = opentok.createSession(new SessionProperties.Builder()
                    .mediaMode(MediaMode.ROUTED)
                    .archiveMode(ArchiveMode.ALWAYS)
                    .build());
            sessionId = session.getSessionId();
            System.out.println(sessionId);
            TokenOptions tokenOpts = new TokenOptions.Builder()
                    .role(Role.MODERATOR)
                    .expireTime((System.currentTimeMillis() / 1000) + (1 * 24 * 60 * 60)) // in a day
                    .build();
            String token = opentok.generateToken(sessionId, tokenOpts);
            System.out.println(token);
            
        
    }
    
    
    public static void main(String Arg[]) throws OpenTokException {

        int apiKey = 46332801;
        String apiSecret = "4c96ad884aa411ee9158c653e66afb65452a8916";
        String EncriptedSecret = Encriptar.Next(apiSecret);
        OpenTok opentok;
        String sessionId;
        String bd;
        opentok = new OpenTok(apiKey, apiSecret);
        /////////////////////////////////////////////////////////
        bd = "controller";
        System.out.println("DELETE FROM  `controller`.`credenciales`;");
        System.out.println("INSERT INTO `controller`.`proyecto` (`apikey`, `apiSecret`, `FireBaseAuthorization`) SELECT \"" + apiKey + "\" , \"" + EncriptedSecret + "\" , FireBaseAuthorization FROM controller.proyecto order by id limit 1;");
        for (int i = 0; i < 5; i++) {
            //System.out.println(i);
            SessionProperties sessionProperties = new SessionProperties.Builder()
                    .mediaMode(MediaMode.ROUTED)
                    .build();
//            //Generate a basic session. Or you could use an existing session ID.
//            sessionId = opentok.createSession(sessionProperties).getSessionId();

// A session that is automatically archived (it must used the routed media mode)
            Session session = opentok.createSession(new SessionProperties.Builder()
                    .mediaMode(MediaMode.ROUTED)
                    .archiveMode(ArchiveMode.ALWAYS)
                    .build());
            sessionId = session.getSessionId();
            TokenOptions tokenOpts = new TokenOptions.Builder()
                    .role(Role.MODERATOR)
                    .expireTime((System.currentTimeMillis() / 1000) + (30 * 24 * 60 * 60)) // in one month
                    .build();
            String token = opentok.generateToken(sessionId, tokenOpts);
            //System.out.println(token);

            System.out.println("INSERT INTO  `credenciales` (`id`, `apikey`, `sesion`, `token`) VALUES ('" + i + "', '" + apiKey + "', '" + sessionId + "', '" + token + "');");

        }

//         OpenTok opentok2;
//        
//        apiKey = 46278422;
//        apiSecret = "9bdf708ef1bf8ca2d6b8bb11dec320ee6050693d";
//        System.out.println("MAIN:");
//        opentok2 = new OpenTok(apiKey, apiSecret);
//        /////////////////////////////////////////////////////////
//        for (int i = 0; i < 200; i++) {
//            System.out.println(i);
//            SessionProperties sessionProperties = new SessionProperties.Builder()
//                    .mediaMode(MediaMode.ROUTED)
//                    .build();
//            //Generate a basic session. Or you could use an existing session ID.
//            sessionId = opentok2.createSession(sessionProperties).getSessionId();
//            TokenOptions tokenOpts = new TokenOptions.Builder()
//                    .role(Role.PUBLISHER)
//                    .expireTime((System.currentTimeMillis() / 1000) + (30 * 24 * 60 * 60)) // in one week
//                    .build();
//            String token = opentok2.generateToken(sessionId, tokenOpts);
//            System.out.println(token);
//            try {
//                Conexion c = new Conexion();
//                Connection con = c.getConexion();
//
//                if (con != null) {
//
//                    Statement st;
//                    st = con.createStatement();
//                    st.executeUpdate("INSERT INTO `" + bd + "`.`credenciales` (`apikey`, `sesion`, `token`) VALUES ('" + apiKey + "', '" + sessionId + "', '" + token + "');");
//                    st.close();
//                }
//                c.cerrarConexion();
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//
//        }
        /////////////////////////////////////////////////////////
//        bd= "bomberos";
//       for (int i = 0; i < 200; i++) {
//            System.out.println(i);
//            SessionProperties sessionProperties = new SessionProperties.Builder()
//                .mediaMode(MediaMode.ROUTED)
//                .build();
//        //Generate a basic session. Or you could use an existing session ID.
//            sessionId = opentok.createSession(sessionProperties).getSessionId();
//            TokenOptions tokenOpts = new TokenOptions.Builder()
//                    .role(Role.PUBLISHER)
//                    .expireTime((System.currentTimeMillis() / 1000) + (30 * 24 * 60 * 60)) // in one week
//                    .build();
//            String token = opentok.generateToken(sessionId, tokenOpts);
//            System.out.println(token);
//            try {
//                Conexion c = new Conexion();
//                Connection con = c.getConexion();
//
//                if (con != null) {
//
//                    Statement st;
//                    st = con.createStatement();
//                    st.executeUpdate("INSERT INTO `" + bd + "`.`credenciales` (`apikey`, `sesion`, `token`) VALUES ('" + apiKey + "', '" + sessionId + "', '" + token + "');");
//                    st.close();
//                }
//                c.cerrarConexion();
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//
//        }
//       /////////////////////////////////////////////////////////
//        bd= "cdmx";
//       for (int i = 0; i < 200; i++) {
//            System.out.println(i);
//            SessionProperties sessionProperties = new SessionProperties.Builder()
//                .mediaMode(MediaMode.ROUTED)
//                .build();
//        //Generate a basic session. Or you could use an existing session ID.
//            sessionId = opentok.createSession(sessionProperties).getSessionId();
//            TokenOptions tokenOpts = new TokenOptions.Builder()
//                    .role(Role.PUBLISHER)
//                    .expireTime((System.currentTimeMillis() / 1000) + (30 * 24 * 60 * 60)) // in one week
//                    .build();
//            String token = opentok.generateToken(sessionId, tokenOpts);
//            System.out.println(token);
//            try {
//                Conexion c = new Conexion();
//                Connection con = c.getConexion();
//
//                if (con != null) {
//
//                    Statement st;
//                    st = con.createStatement();
//                    st.executeUpdate("INSERT INTO `" + bd + "`.`credenciales` (`apikey`, `sesion`, `token`) VALUES ('" + apiKey + "', '" + sessionId + "', '" + token + "');");
//                    st.close();
//                }
//                c.cerrarConexion();
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//
//        }
//       /////////////////////////////////////////////////////////
//        bd= "cruz-roja";
//       for (int i = 0; i < 200; i++) {
//            System.out.println(i);
//            SessionProperties sessionProperties = new SessionProperties.Builder()
//                .mediaMode(MediaMode.ROUTED)
//                .build();
//        //Generate a basic session. Or you could use an existing session ID.
//            sessionId = opentok.createSession(sessionProperties).getSessionId();
//            TokenOptions tokenOpts = new TokenOptions.Builder()
//                    .role(Role.PUBLISHER)
//                    .expireTime((System.currentTimeMillis() / 1000) + (30 * 24 * 60 * 60)) // in one week
//                    .build();
//            String token = opentok.generateToken(sessionId, tokenOpts);
//            System.out.println(token);
//            try {
//                Conexion c = new Conexion();
//                Connection con = c.getConexion();
//
//                if (con != null) {
//
//                    Statement st;
//                    st = con.createStatement();
//                    st.executeUpdate("INSERT INTO `" + bd + "`.`credenciales` (`apikey`, `sesion`, `token`) VALUES ('" + apiKey + "', '" + sessionId + "', '" + token + "');");
//                    st.close();
//                }
//                c.cerrarConexion();
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//
//        }
//       /////////////////////////////////////////////////////////
//        bd= "guardia-nacional";
//       for (int i = 0; i < 200; i++) {
//            System.out.println(i);
//            SessionProperties sessionProperties = new SessionProperties.Builder()
//                .mediaMode(MediaMode.ROUTED)
//                .build();
//        //Generate a basic session. Or you could use an existing session ID.
//            sessionId = opentok.createSession(sessionProperties).getSessionId();
//            TokenOptions tokenOpts = new TokenOptions.Builder()
//                    .role(Role.PUBLISHER)
//                    .expireTime((System.currentTimeMillis() / 1000) + (30 * 24 * 60 * 60)) // in one week
//                    .build();
//            String token = opentok.generateToken(sessionId, tokenOpts);
//            System.out.println(token);
//            try {
//                Conexion c = new Conexion();
//                Connection con = c.getConexion();
//
//                if (con != null) {
//
//                    Statement st;
//                    st = con.createStatement();
//                    st.executeUpdate("INSERT INTO `" + bd + "`.`credenciales` (`apikey`, `sesion`, `token`) VALUES ('" + apiKey + "', '" + sessionId + "', '" + token + "');");
//                    st.close();
//                }
//                c.cerrarConexion();
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//
//        }
//       /////////////////////////////////////////////////////////
//        bd= "policia-estatal";
//       for (int i = 0; i < 200; i++) {
//            System.out.println(i);
//            SessionProperties sessionProperties = new SessionProperties.Builder()
//                .mediaMode(MediaMode.ROUTED)
//                .build();
//        //Generate a basic session. Or you could use an existing session ID.
//            sessionId = opentok.createSession(sessionProperties).getSessionId();
//            TokenOptions tokenOpts = new TokenOptions.Builder()
//                    .role(Role.PUBLISHER)
//                    .expireTime((System.currentTimeMillis() / 1000) + (30 * 24 * 60 * 60)) // in one week
//                    .build();
//            String token = opentok.generateToken(sessionId, tokenOpts);
//            System.out.println(token);
//            try {
//                Conexion c = new Conexion();
//                Connection con = c.getConexion();
//
//                if (con != null) {
//
//                    Statement st;
//                    st = con.createStatement();
//                    st.executeUpdate("INSERT INTO `" + bd + "`.`credenciales` (`apikey`, `sesion`, `token`) VALUES ('" + apiKey + "', '" + sessionId + "', '" + token + "');");
//                    st.close();
//                }
//                c.cerrarConexion();
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//
//        }
//       /////////////////////////////////////////////////////////
//        bd= "policia-federal";
//       for (int i = 0; i < 200; i++) {
//            System.out.println(i);
//            SessionProperties sessionProperties = new SessionProperties.Builder()
//                .mediaMode(MediaMode.ROUTED)
//                .build();
//        //Generate a basic session. Or you could use an existing session ID.
//            sessionId = opentok.createSession(sessionProperties).getSessionId();
//            TokenOptions tokenOpts = new TokenOptions.Builder()
//                    .role(Role.PUBLISHER)
//                    .expireTime((System.currentTimeMillis() / 1000) + (30 * 24 * 60 * 60)) // in one week
//                    .build();
//            String token = opentok.generateToken(sessionId, tokenOpts);
//            System.out.println(token);
//            try {
//                Conexion c = new Conexion();
//                Connection con = c.getConexion();
//
//                if (con != null) {
//
//                    Statement st;
//                    st = con.createStatement();
//                    st.executeUpdate("INSERT INTO `" + bd + "`.`credenciales` (`apikey`, `sesion`, `token`) VALUES ('" + apiKey + "', '" + sessionId + "', '" + token + "');");
//                    st.close();
//                }
//                c.cerrarConexion();
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//
//        }
//       /////////////////////////////////////////////////////////
//        bd= "policia-municipal";
//       for (int i = 0; i < 200; i++) {
//            System.out.println(i);
//            SessionProperties sessionProperties = new SessionProperties.Builder()
//                .mediaMode(MediaMode.ROUTED)
//                .build();
//        //Generate a basic session. Or you could use an existing session ID.
//            sessionId = opentok.createSession(sessionProperties).getSessionId();
//            TokenOptions tokenOpts = new TokenOptions.Builder()
//                    .role(Role.PUBLISHER)
//                    .expireTime((System.currentTimeMillis() / 1000) + (30 * 24 * 60 * 60)) // in one week
//                    .build();
//            String token = opentok.generateToken(sessionId, tokenOpts);
//            System.out.println(token);
//            try {
//                Conexion c = new Conexion();
//                Connection con = c.getConexion();
//
//                if (con != null) {
//
//                    Statement st;
//                    st = con.createStatement();
//                    st.executeUpdate("INSERT INTO `" + bd + "`.`credenciales` (`apikey`, `sesion`, `token`) VALUES ('" + apiKey + "', '" + sessionId + "', '" + token + "');");
//                    st.close();
//                }
//                c.cerrarConexion();
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//
//        }
//        /////////////////////////////////////////////////////////
//        bd= "proteccion_civil";
//       for (int i = 0; i < 200; i++) {
//            System.out.println(i);
//            SessionProperties sessionProperties = new SessionProperties.Builder()
//                .mediaMode(MediaMode.ROUTED)
//                .build();
//        //Generate a basic session. Or you could use an existing session ID.
//            sessionId = opentok.createSession(sessionProperties).getSessionId();
//            TokenOptions tokenOpts = new TokenOptions.Builder()
//                    .role(Role.PUBLISHER)
//                    .expireTime((System.currentTimeMillis() / 1000) + (30 * 24 * 60 * 60)) // in one week
//                    .build();
//            String token = opentok.generateToken(sessionId, tokenOpts);
//            System.out.println(token);
//            try {
//                Conexion c = new Conexion();
//                Connection con = c.getConexion();
//
//                if (con != null) {
//
//                    Statement st;
//                    st = con.createStatement();
//                    st.executeUpdate("INSERT INTO `" + bd + "`.`credenciales` (`apikey`, `sesion`, `token`) VALUES ('" + apiKey + "', '" + sessionId + "', '" + token + "');");
//                    st.close();
//                }
//                c.cerrarConexion();
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//
//        }
//        /////////////////////////////////////////////////////////
//        bd= "sedena";
//       for (int i = 0; i < 200; i++) {
//            System.out.println(i);
//            SessionProperties sessionProperties = new SessionProperties.Builder()
//                .mediaMode(MediaMode.ROUTED)
//                .build();
//        //Generate a basic session. Or you could use an existing session ID.
//            sessionId = opentok.createSession(sessionProperties).getSessionId();
//            TokenOptions tokenOpts = new TokenOptions.Builder()
//                    .role(Role.PUBLISHER)
//                    .expireTime((System.currentTimeMillis() / 1000) + (30 * 24 * 60 * 60)) // in one week
//                    .build();
//            String token = opentok.generateToken(sessionId, tokenOpts);
//            System.out.println(token);
//            try {
//                Conexion c = new Conexion();
//                Connection con = c.getConexion();
//
//                if (con != null) {
//
//                    Statement st;
//                    st = con.createStatement();
//                    st.executeUpdate("INSERT INTO `" + bd + "`.`credenciales` (`apikey`, `sesion`, `token`) VALUES ('" + apiKey + "', '" + sessionId + "', '" + token + "');");
//                    st.close();
//                }
//                c.cerrarConexion();
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//
//        }
//        /////////////////////////////////////////////////////////
//        bd= "sspf";
//       for (int i = 0; i < 200; i++) {
//            System.out.println(i);
//            SessionProperties sessionProperties = new SessionProperties.Builder()
//                .mediaMode(MediaMode.ROUTED)
//                .build();
//        //Generate a basic session. Or you could use an existing session ID.
//            sessionId = opentok.createSession(sessionProperties).getSessionId();
//            TokenOptions tokenOpts = new TokenOptions.Builder()
//                    .role(Role.PUBLISHER)
//                    .expireTime((System.currentTimeMillis() / 1000) + (30 * 24 * 60 * 60)) // in one week
//                    .build();
//            String token = opentok.generateToken(sessionId, tokenOpts);
//            System.out.println(token);
//            try {
//                Conexion c = new Conexion();
//                Connection con = c.getConexion();
//
//                if (con != null) {
//
//                    Statement st;
//                    st = con.createStatement();
//                    st.executeUpdate("INSERT INTO `" + bd + "`.`credenciales` (`apikey`, `sesion`, `token`) VALUES ('" + apiKey + "', '" + sessionId + "', '" + token + "');");
//                    st.close();
//                }
//                c.cerrarConexion();
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//
//        }
        System.out.println("Done");
    }

}
