    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package Modelo;

    import Config.config;
    import static Controlador.ControladorPOST.ComplementoInfoIntegrantes;
    import Request.request;
    import java.io.IOException;
    import java.util.ArrayList;
    import org.json.simple.JSONArray;
    import org.json.simple.JSONObject;
    import org.json.simple.parser.JSONParser;
    import org.json.simple.parser.ParseException;

    /**
     *
     * @author web
     */
    public class Grupos {

        public static JSONArray infoUsuarios(String aliasServicio) throws ParseException {
            System.out.println("USUARIOS ---------->");
            System.out.println(aliasServicio);
            JSONArray usr = new JSONArray();
            String query = "SELECT idUsuarios_Movil,telefono,U.nombre,apellido_paterno,apellido_materno,FireBaseKey,D.aliasServicio ";
            query += "FROM usuarios_movil U inner join directorio D where U.idUsuarios_Movil = D.idUsuario and D.activo = 1 and D.aliasServicio =\"" + aliasServicio + "\";";
            System.out.println(query);
            JSONArray infoUsr = Query.execute(query);
            if (!infoUsr.isEmpty()) {
                query = "SELECT idUsuarios_Movil,Hora,Fecha,Latitud,Longitud FROM registro_rutas WHERE Fecha = DATE(NOW());";
                System.out.println(query);
                JSONArray rutaUsr = Query.execute(query);
                if (!rutaUsr.isEmpty()) {
                    for (int i = 0; i < infoUsr.size(); i++) {
                        JSONObject json1 = (JSONObject) infoUsr.get(i);
                        for (int j = 0; j < rutaUsr.size(); j++) {
                            JSONObject json2 = (JSONObject) rutaUsr.get(j);
                            if (json1.get("idUsuarios_Movil").toString().equals(json2.get("idUsuarios_Movil").toString())) {
                                JSONObject gps = new JSONObject();
                                JSONObject ult = new JSONObject();
                                ult.put("lat", json2.get("Latitud"));
                                ult.put("lng", json2.get("Longitud"));

                                gps.put("lat", json2.get("Latitud"));
                                gps.put("lng", json2.get("Longitud"));
                                gps.put("hora", json2.get("Hora"));
                                gps.put("fecha", json2.get("Fecha"));
                                gps.put("ult", ult);
                                json1.put("gps", gps);

                                break;
                            }
                        }
                        if (!json1.containsKey("gps")) {
                            JSONObject gps = new JSONObject();
                            JSONObject ult = new JSONObject();
                            ult.put("lat", "");
                            ult.put("lng", "");

                            gps.put("lat", "");
                            gps.put("lng", "");
                            gps.put("hora", "");
                            gps.put("fecha", "");
                            gps.put("ult", ult);
                            json1.put("gps", gps);
                        }
                        usr.add(json1);
                    }
                } else {
                    for (int i = 0; i < infoUsr.size(); i++) {
                        JSONObject json1 = (JSONObject) infoUsr.get(i);
                        JSONObject gps = new JSONObject();
                        JSONObject ult = new JSONObject();
                        ult.put("lat", "");
                        ult.put("lng", "");

                        gps.put("lat", "");
                        gps.put("lng", "");
                        gps.put("hora", "");
                        gps.put("fecha", "");
                        gps.put("ult", ult);
                        json1.put("gps", gps);
                        usr.add(json1);
                    }
                }
            }

            return usr;
        }

        public JSONArray GruposAutomaticos(String tipo_usuario, String tipo_servicio) {
            System.out.println("ConsultarBackupDirectorioPersonalizado: ");
            //Consulta de Grupos Automaticos

            JSONArray GruposAutomaticos = new JSONArray();
            String query = null;
            //Construir query por tipo de usuario y tipo de servicio
            //preguntar si es usuario maestro a nivel estatal
            if (tipo_usuario.equals("0")&&tipo_servicio.equals("0")) {
                query = "SELECT distinct G.idgruposUsuarioSys,G.nombre,G.idServicio,G.idSuperior, G.tipo_usuario, G.tipo_servicio, D.urlServicio "
                        + "FROM grupos_usuario_sys G, directorio D "
                        + "where estado = 3 and G.nombre = D.aliasServicio and D.activo=1;";
            } else {
                //preguntar si es usuario maestro a nivel dependencia
                if (tipo_servicio.equals("0")) {
                    query = "SELECT distinct G.idgruposUsuarioSys,G.nombre,G.idServicio,G.idSuperior, G.tipo_usuario, G.tipo_servicio, D.urlServicio "
                            + "FROM grupos_usuario_sys G, directorio D "
                            + "where estado = 3 and G.nombre = D.aliasServicio and D.activo=1 and G.tipo_usuario=" + tipo_usuario + ";";
                } else {
                    query = "SELECT distinct G.idgruposUsuarioSys,G.nombre,G.idServicio,G.idSuperior, G.tipo_usuario, G.tipo_servicio, D.urlServicio "
                            + "FROM grupos_usuario_sys G, directorio D "
                            + "where estado = 3 and G.nombre = D.aliasServicio and D.activo=1 and G.tipo_usuario=" + tipo_usuario + " and G.tipo_servicio=" + tipo_servicio + ";";
                }
            }

            System.out.println(query);

            JSONArray Grupos_sys = Query.execute(query);

            for (int i = 0; i < Grupos_sys.size(); i++) {
                JSONArray integrantes_grupo = new JSONArray();
                JSONObject grupo = (JSONObject) Grupos_sys.get(i);
                JSONArray integrantes = Query.execute("Select idUsuarios_Movil from grupos_usuario_movil where idgruposUsuarioSys = " + grupo.get("idgruposUsuarioSys") + " and estado = 3;");
                for (int j = 0; j < integrantes.size(); j++) {
                    JSONObject integrante = (JSONObject) integrantes.get(j);
                    integrantes_grupo.add(integrante.get("idUsuarios_Movil").toString());
                }
                grupo.put("integrantes", integrantes_grupo);
                GruposAutomaticos.add(grupo);
            }
            // ya tenemos grupos automaticos
            return GruposAutomaticos;
        }
        public JSONArray GruposAutomaticosEmpresas360(String tipo_usuario, String tipo_servicio) {
            System.out.println("ConsultarBackupDirectorioPersonalizado: ");
            //Consulta de Grupos Automaticos

            JSONArray GruposAutomaticos = new JSONArray();
            String query = null;
            //Construir query por tipo de usuario y tipo de servicio
            //preguntar si es usuario maestro a nivel estatal
            if (tipo_usuario.equals("0")&&tipo_servicio.equals("0")) {
                query = "SELECT distinct G.idgruposUsuarioSys,G.nombre,G.idServicio,G.idSuperior, G.tipo_usuario, G.tipo_servicio "
                        + "FROM grupos_usuario_sys G "
                        + "where estado = 3 ORDER BY tipo_servicio, nombre;";
            } else {
                //preguntar si es usuario maestro a nivel dependencia
                if (tipo_servicio.equals("0")) {
                    query = "SELECT distinct G.idgruposUsuarioSys,G.nombre,G.idServicio,G.idSuperior, G.tipo_usuario, G.tipo_servicio "
                            + "FROM grupos_usuario_sys G "
                            + "where estado = 3 and G.tipo_usuario=" + tipo_usuario + " ORDER BY tipo_servicio, nombre;";
                } else {
                    query = "SELECT distinct G.idgruposUsuarioSys,G.nombre,G.idServicio,G.idSuperior, G.tipo_usuario, G.tipo_servicio "
                            + "FROM grupos_usuario_sys G "
                            + "where estado = 3 and G.tipo_usuario=" + tipo_usuario + " and G.tipo_servicio=" + tipo_servicio + " ORDER BY tipo_servicio, nombre;";
                }
            }

            System.out.println(query);

            JSONArray Grupos_sys = Query.execute(query);

            for (int i = 0; i < Grupos_sys.size(); i++) {
                JSONArray integrantes_grupo = new JSONArray();
                JSONObject grupo = (JSONObject) Grupos_sys.get(i);
                //si es usuario maestro
                if (tipo_servicio.equals("0")) {
                    String nombre_sucursal = "Select nombre from servicios_usuario where id='"+grupo.get("tipo_servicio")+"';";

                    grupo.put("nombre",grupo.get("nombre").toString()+" - "+Query.select(nombre_sucursal).get("nombre"));
                }

                JSONArray integrantes = Query.execute("Select idUsuarios_Movil from grupos_usuario_movil where idgruposUsuarioSys = " + grupo.get("idgruposUsuarioSys") + " and estado = 3;");
                for (int j = 0; j < integrantes.size(); j++) {
                    JSONObject integrante = (JSONObject) integrantes.get(j);
                    integrantes_grupo.add(integrante.get("idUsuarios_Movil").toString());
                }
                grupo.put("integrantes", integrantes_grupo);
                GruposAutomaticos.add(grupo);
            }
            // ya tenemos grupos automaticos
            return GruposAutomaticos;
        }

        public JSONArray GruposAutomaticos_Modulo(String tipo_usuario, String tipo_servicio,String persona,String id360) {
            System.out.println("ConsultarBackupDirectorioPersonalizado: ");
            //Consulta de Grupos Automaticos

            JSONArray GruposAutomaticos = new JSONArray();
            String query = null;
            //Construir query por tipo de usuario y tipo de servicio
            //preguntar si es usuario maestro a nivel estatal
            if (tipo_usuario.equals("0")&&tipo_servicio.equals("0")) {
                query = "SELECT distinct G.idgruposUsuarioSys,G.nombre,G.idServicio,G.idSuperior, G.tipo_usuario, G.tipo_servicio, D.urlServicio "
                        + "FROM grupos_usuario_sys G, directorio D "
                        + "where estado = 3 and G.nombre = D.aliasServicio and D.activo=1;";
            } else {
                //preguntar si es usuario maestro a nivel dependencia
                if (tipo_servicio.equals("0")) {
                    query = "SELECT distinct G.idgruposUsuarioSys,G.nombre,G.idServicio,G.idSuperior, G.tipo_usuario, G.tipo_servicio, D.urlServicio "
                            + "FROM grupos_usuario_sys G, directorio D "
                            + "where estado = 3 and G.nombre = D.aliasServicio and D.activo=1 and G.tipo_usuario=" + tipo_usuario + ";";
                } else {
                    query = "SELECT distinct G.idgruposUsuarioSys,G.nombre,G.idServicio,G.idSuperior, G.tipo_usuario, G.tipo_servicio, D.urlServicio "
                            + "FROM grupos_usuario_sys G, directorio D "
                            + "where estado = 3 and G.nombre = D.aliasServicio and D.activo=1 and G.tipo_usuario=" + tipo_usuario + " and G.tipo_servicio=" + tipo_servicio + ";";
                }
            }

            System.out.println(query);

            JSONArray Grupos_sys = Query.execute(query);

            for (int i = 0; i < Grupos_sys.size(); i++) {
                JSONArray integrantes_grupo = new JSONArray();
                JSONObject grupo = (JSONObject) Grupos_sys.get(i);
                if (persona.equals("1")) {
                    query = "Select idUsuarios_Movil from grupos_usuario_movil "
                            + "where idgruposUsuarioSys = " + grupo.get("idgruposUsuarioSys") + " and estado = 3 AND idUsuarios_Movil='"+id360+"';";
                }else{
                    query = "Select idUsuarios_Movil from grupos_usuario_movil where idgruposUsuarioSys = " + grupo.get("idgruposUsuarioSys") + " and estado = 3;";
                }

                JSONArray integrantes = Query.execute(query);
                for (int j = 0; j < integrantes.size(); j++) {
                    JSONObject integrante = (JSONObject) integrantes.get(j);
                    integrantes_grupo.add(integrante.get("idUsuarios_Movil").toString());
                }
                grupo.put("integrantes", integrantes_grupo);
                GruposAutomaticos.add(grupo);
            }
            // ya tenemos grupos automaticos
            return GruposAutomaticos;
        }

        public static JSONArray GruposAutomaticos(/*@RequestBody String json*/) {
            JSONObject json;
            //JSONParser parser = new JSONParser();
            JSONArray jsonArray;
            JSONArray jsonArray2;
            JSONArray GruposAutomaticos = new JSONArray();

            String query = "SELECT distinct G.idgruposUsuarioSys,G.nombre,G.idServicio,G.idSuperior, G.tipo_usuario, G.tipo_servicio, D.urlServicio "
                    + "FROM grupos_usuario_sys G, directorio D where estado = 3 and G.nombre = D.aliasServicio AND D.activo=1;"; //Si no funciona quitar el AND D.activo=1
            System.out.println(query);
            jsonArray = Query.execute(query);
            //System.out.println(jsonArray);
            if (!jsonArray.isEmpty()) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONArray integrantes = new JSONArray();
                    JSONObject jsonObj = (JSONObject) jsonArray.get(i);
                    //Si no funciona descomentar la query comentada de abajo y comentar la descomentada
                    //query = "Select idUsuarios_Movil from grupos_usuario_movil where idgruposUsuarioSys = " + jsonObj.get("idgruposUsuarioSys") + " and estado = 3;";
                    query = "Select G.idUsuarios_Movil,D.activo from grupos_usuario_movil G, directorio D "
                            + "where G.idUsuarios_Movil = D.idUsuario and G.idgruposUsuarioSys = '" + jsonObj.get("idgruposUsuarioSys") + "' and G.estado = 3 and D.activo=1";
                    System.out.println(query);
                    //jsonArray2 = (JSONArray) parser.parse(request.POST(jsonObj.get("urlServicio") + "/idUsuarios", query));
                    jsonArray2 = Query.execute(query);
                    //System.out.println("JSONARRAY2 --------->");
                    //System.out.println(jsonArray2);
                    if (!jsonArray2.isEmpty()) {
                        for (int j = 0; j < jsonArray2.size(); j++) {
                            json = (JSONObject) jsonArray2.get(j);
                            integrantes.add(json.get("idUsuarios_Movil").toString());
                        }
                        //System.out.println("INTEGRANTES --->");
                        //System.out.println(integrantes);
                        //jsonObj.put("integrantes", integrantes);
                        //System.out.println(jsonObj.get("integrantes"));
                        //integrantes.clear();
                    }
                    jsonObj.put("integrantes", integrantes);
                    jsonObj.put("online", false);
                    //integrantes.clear();
                    GruposAutomaticos.add(jsonObj);
                    //System.out.println(GruposAutomaticos.get(i));
                }

            }
            //        System.out.println("GRUPOS AUTOMATICOS ------>");
            //        System.out.println(GruposAutomaticos);
            return GruposAutomaticos;
        }

        public JSONArray integrantes(JSONArray grupos_automaticos) throws IOException, ParseException {
            String query = null;
            JSONArray array_integrantes = new JSONArray();
            for (int i = 0; i < grupos_automaticos.size(); i++) {
                JSONObject grupo = (JSONObject) grupos_automaticos.get(i);
                JSONArray integrantes = (JSONArray) grupo.get("integrantes");
                for (int j = 0; j < integrantes.size(); j++) {
                    // rellenar datos locales
                    //Directorio
                    query = "SELECT * FROM directorio WHERE idUsuario='" + integrantes.get(j) + "' AND activo=1;";
                    System.out.println(query);
                    JSONObject integrante = Query.select(query);
                    if (integrante == null) {
                        System.out.println("No se encontro:" + integrantes.get(j));
                        integrante = new JSONObject();
                    }else{
                        query = "SELECT area AS aliasServicio FROM tipo_area WHERE id = '"+integrante.get("tipo_area")+"';";
                        System.out.println(query);
                        JSONObject area =   Query.select(query);
                        if(area!=null){
                            integrante.putAll(area);
                        }else{
                            integrante.put("aliasServicio", "");
                        }
                    }
                    System.out.println(integrante);
                    //GPS
                    query = "SELECT Hora AS hora,Fecha AS fecha,Latitud AS lat,Longitud AS lng FROM registro_rutas WHERE Fecha = DATE(NOW()) AND idUsuarios_Movil='" + integrantes.get(j) + "';";
                    JSONObject gps = Query.select(query);
                    if (gps == null) {
                        gps = new JSONObject();
                        gps.put("lat", "");
                        gps.put("lng", "");
                        gps.put("fecha", "");
                        gps.put("hora", "");
                    }
                    JSONObject ult = new JSONObject();
                    ult.put("lat", "");
                    ult.put("lng", "");
                    gps.put("ult", ult);
                    integrante.put("gps", gps);

                    //PERFIL
                    //colocar id360
                    integrante.put("id360", integrantes.get(j));
                    integrante.put("idUsuarios_Movil", integrantes.get(j));
                    array_integrantes.add(integrante);
                    //query="";
                    //                }else{
                    //                    System.out.println("No se encontro:"+ integrantes.get(j) );
                    //                }

                }
            }

            //Integrantes sin perfil
            System.out.println(array_integrantes);

            array_integrantes = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfiles/array", array_integrantes);

            //Integrantes con perfil
            System.out.println(array_integrantes);

            return array_integrantes;
        }
        public JSONArray integrantesEmpresas360(JSONArray grupos_automaticos) throws IOException, ParseException {
            String query = null;
            JSONArray array_integrantes = new JSONArray();
            for (int i = 0; i < grupos_automaticos.size(); i++) {
                JSONObject grupo = (JSONObject) grupos_automaticos.get(i);
                JSONArray integrantes = (JSONArray) grupo.get("integrantes");
                for (int j = 0; j < integrantes.size(); j++) {
                    // rellenar datos locales
                    //Directorio
                    query = "SELECT * FROM directorio WHERE idUsuario='" + integrantes.get(j) + "' AND activo=1;";
                    System.out.println(query);
                    JSONObject integrante = Query.select(query);
                    if (integrante == null) {
                        System.out.println("No se encontro:" + integrantes.get(j));
                        integrante = new JSONObject();
                    }else{
                        System.out.println("Integrante encontrado");
                        query = "SELECT area AS aliasServicio FROM tipo_area WHERE id = '"+integrante.get("tipo_area")+"';";
                        System.out.println(query);
                        JSONObject area =   Query.select(query);
                        if(area!=null){
                            integrante.putAll(area);
                        }else{
                            integrante.put("aliasServicio", "");
                        }
                    }
                    System.out.println(integrante);
                    //GPS
                    query = "SELECT Hora AS hora,Fecha AS fecha,Latitud AS lat,Longitud AS lng FROM registro_rutas WHERE Fecha = DATE(NOW()) AND idUsuarios_Movil='" + integrantes.get(j) + "';";
                    JSONObject gps = Query.select(query);
                    if (gps == null) {
                        gps = new JSONObject();
                        gps.put("lat", "");
                        gps.put("lng", "");
                        gps.put("fecha", "");
                        gps.put("hora", "");
                    }
                    JSONObject ult = new JSONObject();
                    ult.put("lat", "");
                    ult.put("lng", "");
                    gps.put("ult", ult);
                    integrante.put("gps", gps);

                    //PERFIL
                    //colocar id360
                    integrante.put("id360", integrantes.get(j));
                    integrante.put("idUsuarios_Movil", integrantes.get(j));
                    array_integrantes.add(integrante);
                    //query="";
                    //                }else{
                    //                    System.out.println("No se encontro:"+ integrantes.get(j) );
                    //                }

                }
            }

            //Integrantes sin perfil
            System.out.println(array_integrantes);

            array_integrantes = request.POST(config.getURL_CONTROLADOR() + "API/cuenta360/perfiles/array", array_integrantes);

            //Integrantes con perfil
            System.out.println(array_integrantes);

            return array_integrantes;
        }

        public static JSONArray Integrantes(JSONArray GruposAutomaticos) throws ParseException, IOException {
            //GruposAutomaticos.clear();
            JSONObject json = new JSONObject();
            JSONArray jsonArray;
            JSONParser parser = new JSONParser();
            JSONArray usuariosMovil = new JSONArray();
            String query = "SELECT distinct G.*, D.urlServicio FROM grupos_usuario_sys G, directorio D "
                    + "where estado = 3 and G.nombre = D.aliasServicio  AND activo=1;"; //Si no funciona quitar  AND activo=1
            System.out.println(query);
            jsonArray = Query.execute(query);
            if (!jsonArray.isEmpty()) {
                //for (int i = jsonArray.size() - 1; i >= 0; i--) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    System.out.println(jsonArray.get(i));
                    JSONObject jsonObj;
                    jsonObj = (JSONObject) jsonArray.get(i);
                    //query = "SELECT idUsuarios_Movil FROM grupos_usuario_movil where idgruposUsuarioSys = " + jsonObj.get("idgruposUsuarioSys") + " and estado = 3;";
                    //System.out.println(query);
                    JSONArray usuarios = new JSONArray();
                    //if (!usuarios.isEmpty()) {
                    //System.out.println(usuarios);
                    System.out.println(jsonObj.get("urlServicio").toString());
                    System.out.println(config.getPATH() + config.getDEPENDENCIA());
                    System.out.println("son iguales ??? ");
                    System.out.println(jsonObj.get("urlServicio").toString().equals(config.getPATH() + config.getDEPENDENCIA()));
                    if (jsonObj.get("urlServicio").toString().equals(config.getPATH() + config.getDEPENDENCIA())) {

                        usuarios = Grupos.infoUsuarios(jsonObj.get("nombre").toString());
                        for (int j = 0; j < usuarios.size(); j++) {
                            usuariosMovil.add((JSONObject) usuarios.get(j));
                        }
                    } else {
                        String usr = request.POST(jsonObj.get("urlServicio").toString() + "/integrantes", jsonObj.get("nombre").toString());
                        if (usr != null) {
                            usuarios = (JSONArray) parser.parse(usr);
                            for (int j = 0; j < usuarios.size(); j++) {
                                usuariosMovil.add((JSONObject) usuarios.get(j));
                            }
                        } else {
                            System.out.println("Algo salio mal al ejecutar el POST en --->" + jsonObj.get("urlServicio").toString());
                        }
                    }
                }
            }

            return usuariosMovil;
        }

        public JSONArray GruposPersonalizados(String idUsuarioSys) {
            JSONObject json;
            //JSONParser parser = new JSONParser();
            JSONArray jsonArray;
            JSONArray jsonArray2;
            JSONArray GruposPersonalizados = new JSONArray();

            String query = "SELECT * FROM grupos_usuario_sys WHERE estado = 1 AND idUsuario_Sys='" + idUsuarioSys + "';";
            jsonArray = Query.execute(query);
            //System.out.println(jsonArray);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONArray integrantes = new JSONArray();
                JSONObject jsonObj = (JSONObject) jsonArray.get(i);
                query = "Select idUsuarios_Movil from grupos_usuario_movil where idgruposUsuarioSys = " + jsonObj.get("idgruposUsuarioSys") + " and estado = 1;";
                System.out.println(query);
                //jsonArray2 = (JSONArray) parser.parse(request.POST(jsonObj.get("urlServicio") + "/idUsuarios", query));
                jsonArray2 = Query.execute(query);

                //System.out.println("JSONARRAY2 --------->");
                //System.out.println(jsonArray2);
                for (int j = 0; j < jsonArray2.size(); j++) {
                    json = (JSONObject) jsonArray2.get(j);
                    integrantes.add(json.get("idUsuarios_Movil").toString());
                }
                //System.out.println("INTEGRANTES --->");
                //System.out.println(integrantes);
                jsonObj.put("integrantes", integrantes);
                //System.out.println(jsonObj.get("integrantes"));
                //integrantes.clear();

                //integrantes.clear();
                GruposPersonalizados.add(jsonObj);
                //System.out.println(GruposAutomaticos.get(i));
            }

            //System.out.println("GRUPOS AUTOMATICOS ------>");
            System.out.println(GruposPersonalizados);
            return GruposPersonalizados;
        }
        public JSONArray GruposPersonalizadosEmpresas360(String idUsuarioSys) {
            JSONObject json;
            //JSONParser parser = new JSONParser();
            JSONArray jsonArray;
            JSONArray jsonArray2;
            JSONArray GruposPersonalizados = new JSONArray();

            String query = "SELECT * FROM grupos_usuario_sys WHERE estado = 1 AND idUsuario_Sys='" + idUsuarioSys + "';";
            jsonArray = Query.execute(query);
            //System.out.println(jsonArray);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONArray integrantes = new JSONArray();
                JSONObject jsonObj = (JSONObject) jsonArray.get(i);
                query = "Select idUsuarios_Movil from grupos_usuario_movil where idgruposUsuarioSys = " + jsonObj.get("idgruposUsuarioSys") + " and estado = 1;";
                System.out.println(query);
                //jsonArray2 = (JSONArray) parser.parse(request.POST(jsonObj.get("urlServicio") + "/idUsuarios", query));
                jsonArray2 = Query.execute(query);

                //System.out.println("JSONARRAY2 --------->");
                //System.out.println(jsonArray2);
                for (int j = 0; j < jsonArray2.size(); j++) {
                    json = (JSONObject) jsonArray2.get(j);
                    integrantes.add(json.get("idUsuarios_Movil").toString());
                }
                //System.out.println("INTEGRANTES --->");
                //System.out.println(integrantes);
                jsonObj.put("integrantes", integrantes);
                //System.out.println(jsonObj.get("integrantes"));
                //integrantes.clear();

                //integrantes.clear();
                GruposPersonalizados.add(jsonObj);
                //System.out.println(GruposAutomaticos.get(i));
            }

            //System.out.println("GRUPOS AUTOMATICOS ------>");
            System.out.println(GruposPersonalizados);
            return GruposPersonalizados;
        }

        public static JSONArray GrupoAutomatico(String aliasServicio) {
            JSONObject json;
            JSONArray jsonArray;
            JSONArray jsonArray2;
            JSONArray GruposAutomaticos = new JSONArray();

            String query = "select idServicio,idSuperior,idgruposUsuarioSys,nombre, tipo_usuario, tipo_servicio from grupos_usuario_sys where estado = 3 and nombre= '" + aliasServicio + "';";
            System.out.println(query);
            jsonArray = Query.execute(query);
            //System.out.println(jsonArray);
            if (!jsonArray.isEmpty()) {

                JSONObject grupo = (JSONObject) jsonArray.get(0);
                JSONArray integrantes = new JSONArray();
                query = "select idUsuario,urlServicio from directorio where activo = 1 and aliasServicio = '" + aliasServicio + "';";
                System.out.println(query);
                jsonArray2 = Query.execute(query);
                if (!jsonArray2.isEmpty()) {
                    for (int i = 0; i < jsonArray2.size(); i++) {
                        json = (JSONObject) jsonArray2.get(i);
                        integrantes.add(json.get("idUsuario").toString());
                        grupo.put("urlServicio", json.get("urlServicio"));
                    }
                }
                grupo.put("integrantes", integrantes);
                GruposAutomaticos.add(grupo);
            }

            return GruposAutomaticos;
        }
    }
