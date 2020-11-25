/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encriptacion;

import Controlador.ControladorPOST;
import Config.config;
import Modelo.Query;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author global-human
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
//        System.out.println(Encriptar.Next("referencias360"));
//        System.out.println(config.getSECRETKEY());
//        System.out.println(Encriptar.Reverse("AYW/5xAbmDixpq0qXvDXDw=="));
//        System.out.println(Encriptar.Reverse("5sZqSDQjmLpowpHGKVRZ9Q=="));
//        System.out.println(Encriptar.Reverse("05N7KQchOYs="));
//        // TODO code application logic here
//INSERT INTO `usuario_sys` (`usuario`, `nombre`, `apellidos`, `correo`, `contrasenia`, `tipo`, `estatus`, `sesion`, `disponibilidad`, `registrado_por_usuario`,`puede_registrar`,`tipo_usuario`,`tipo_servicio`) VALUES ('usuariomoises', 'moises', 'juarez', 'moises@mail.com', 'wkU0A4xw+xc=', 'Administrador', '1', '0', '1', NULL,'false','19','29');]]

        // String encriptado = Encriptar.Reverse("c7Y5q7I4OjfTp995lDGyjQ==");
        // System.out.println(encriptado);
        // System.out.println(generateString());
        // System.out.println("Credenciales de acceso a la CRUM");
        //System.out.println(Query.execute("SELECT * FROM usuarios;"));
        //JSONArray array = Query.execute("SELECT * FROM usuarios;");
        //JSONArray queries = new JSONArray();
        //for (int i = 0; i < 30 ; i++) {
        //JSONObject usuario = (JSONObject) array.get(i);
        //String contraseña = generateString().substring(0, 8);
//            String query = "INSERT INTO `usuario_sys` (`usuario`, `nombre`, `apellidos`, `correo`, `contrasenia`, `tipo`, `estatus`, `sesion`, `disponibilidad`, `registrado_por_usuario`,`puede_registrar`,`tipo_usuario`,`tipo_servicio`) VALUES "
//                    + "('" + usuario.get("usuario") + "', '" + usuario.get("nombre") + "', '" + usuario.get("apellidos") + "', '" + usuario.get("correo") + "', '" + Encriptar.Next(contraseña) + "', 'Administrador', '1', '0', '1', NULL,'false','19','29');";
////            System.out.println(query);
//            queries.add(query);
//            System.out.println("Usuario: " + usuario.get("usuario"));
//            System.out.println("Contraseña: " + contraseña);
        //  System.out.println(contraseña);
        //}
//        for (int i = 0; i < queries.size(); i++) {
//            System.out.println(queries.get(i));
//        }
//
//        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//        System.out.println("Enter username");
//
//        String userName = myObj.nextLine();  // Read user input
//        System.out.println("Username is: " + userName);  // Output user input
//        System.out.println("FINALIZO");
//        System.out.println("");
//        String string1 = generateString().substring(0, 8);
//        String string2 = generateString().substring(0, 8);
//        String string3 = generateString().substring(0, 8);
//        String string4 = generateString().substring(0, 8);
//        String string5 = generateString().substring(0, 8);
//        String string6 = generateString().substring(0, 8);
//        String string7 = generateString().substring(0, 8);
//        String string8 = generateString().substring(0, 8);
//        String string9 = generateString().substring(0, 8);
//        String string10 = generateString().substring(0, 8);
//        String string11 = generateString().substring(0, 8);
//        String string12 = generateString().substring(0, 8);
//        String string13 = generateString().substring(0, 8);
//        String string14 = generateString().substring(0, 8);
//        String string15 = generateString().substring(0, 8);
//        String string16 = generateString().substring(0, 8);
//        String string17 = generateString().substring(0, 8);
//        String string18 = generateString().substring(0, 8);
//        String string19 = generateString().substring(0, 8);
//        String string20 = generateString().substring(0, 8);
//        
//        
//        System.out.println(string1);
//        System.out.println(string2);
//        System.out.println(string3);
//        System.out.println(string4);
//        System.out.println(string5);
//        System.out.println(string6);
//        System.out.println(string7);
//        System.out.println(string8);
//        System.out.println(string9);
//        System.out.println(string10);
//        System.out.println(string11);
//        System.out.println(string12);
//        System.out.println(string13);
//        System.out.println(string14);
//        System.out.println(string15);
//        System.out.println(string16);
//        System.out.println(string17);
//        System.out.println(string18);
//        System.out.println(string19);
//        System.out.println(string20);
//        System.out.println("");
//
//        System.out.println(Encriptar.Next(string1));
//        System.out.println(Encriptar.Next(string2));
//        System.out.println(Encriptar.Next(string3));
//        System.out.println(Encriptar.Next(string4));
//        System.out.println(Encriptar.Next(string5));
//        System.out.println(Encriptar.Next(string6));
//        System.out.println(Encriptar.Next(string7));
//        System.out.println(Encriptar.Next(string8));
//        System.out.println(Encriptar.Next(string9));
//        System.out.println(Encriptar.Next(string10));
//        System.out.println(Encriptar.Next(string11));
//        System.out.println(Encriptar.Next(string12));
//        System.out.println(Encriptar.Next(string13));
//        System.out.println(Encriptar.Next(string14));
//        System.out.println(Encriptar.Next(string15));
//        System.out.println(Encriptar.Next(string16));
//        System.out.println(Encriptar.Next(string17));
//        System.out.println(Encriptar.Next(string18));
//        System.out.println(Encriptar.Next(string19));
//        System.out.println(Encriptar.Next(string20));
System.out.println(Encriptar.Reverse("veLRke5lMvbdNwq7g1Ia6w=="));

    }

    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

}
