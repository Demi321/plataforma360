/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author moises
 */
public class Query {

    static public JSONArray execute(String query) {
        System.out.println(query);
        JSONArray jsonArray = new JSONArray();
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {
                //System.out.println(con);
                Statement st;
                st = con.createStatement();

                //System.out.println("esperando....");
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    //String name = rsmd.getColumnLabel(1);
                    //System.out.println(name);
                    int count = rsmd.getColumnCount();
                    //System.out.println(count);
                    JSONObject json = new JSONObject();
                    for (int i = 1; i <= count; i++) {
                        json.put(rsmd.getColumnLabel(i), rs.getString(i));
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
        System.out.println("Respuesta JSONArray en execute query");
        //System.out.println(jsonArray);
        return jsonArray;
    }

    static public JSONObject select(String query) {
        JSONArray jsonArray = new JSONArray();
        try {
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            if (con != null) {
                //System.out.println(con);
                Statement st;
                st = con.createStatement();

                //System.out.println("esperando....");
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    //String name = rsmd.getColumnLabel(1);
                    //System.out.println(name);
                    int count = rsmd.getColumnCount();
                    //System.out.println(count);
                    JSONObject json = new JSONObject();
                    for (int i = 1; i <= count; i++) {
                        json.put(rsmd.getColumnLabel(i), rs.getString(i));
                    }
                    jsonArray.add(json);
                    //System.out.println(json);
                }

                //System.out.println(jsonArray);
                st.close();
            }
            conn.cerrarConexion();
        } catch (SQLException e) {
            System.out.println(query);
            System.out.println(e);
        }
        System.out.println("Respuesta JSONArray en execute query");
        //System.out.println(jsonArray);
        if (!jsonArray.isEmpty()) {
            return (JSONObject) jsonArray.get(0);
        } else {
            System.out.println(query);
        }
        return null;
    }

    public static boolean update(String query) {
        System.out.println(query);
        boolean actualizado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {

                PreparedStatement ps;
                ps = con.prepareStatement(query);
                //ps.setString(1, activo);
                //ps.setString(2, id);
                ps.executeUpdate();
                if (ps.getUpdateCount() > 0) {
                    actualizado = true;
                }

                ps.close();

            }
            c.cerrarConexion();
        } catch (SQLException e) {
            actualizado = false;
            System.out.println(query);
            System.out.println(e);
        }
        return actualizado;
    }

    public static int insert(String query) {
        System.out.println(query);
        int id = -1;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {

                Statement st;
                st = con.createStatement();
                st.executeUpdate(query);

                ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID() AS id");
                if (rs.next()) {
                    id = rs.getInt("id");
                }
                st.close();

            }
            c.cerrarConexion();
        } catch (SQLException e) {
            id = -1;
            System.out.println(query);
            System.out.println(e);
        }

        return id;
    }

    public static String createQueryInsert(String table, JSONObject data) {
        Set<String> keys = data.keySet();
        Iterator<String> iterator = keys.iterator();
        JSONArray keys_null = new JSONArray();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (data.get(key).toString().equals("")) {
                keys_null.add(key);
            }
        }
        for (int i = 0; i < keys_null.size(); i++) {
            data.remove(keys_null.get(i));
        }

        String query = "DESCRIBE " + table + ";";
        JSONArray table_fields = Query.execute(query);
        query = "INSERT INTO " + table + " (";
        keys = data.keySet();
        iterator = keys.iterator();
        String labels = "";
        String values = "";

        /**
         * ************* cambio para agregar la fechas de creacion de registros
         * ****************
         */
        boolean has_datecreated = false;
        boolean has_dateupdated = false;
        boolean has_timecreated = false;
        boolean has_timeupdated = false;
        for (int i = 0; i < table_fields.size(); i++) {
            JSONObject column = (JSONObject) table_fields.get(i);
            if (column.get("Field").toString().equals("date_created")) {
                has_datecreated = true;
            }
            if (column.get("Field").toString().equals("time_created")) {
                has_timecreated = true;
            }
            if (column.get("Field").toString().equals("date_updated")) {
                has_dateupdated = true;
            }
            if (column.get("Field").toString().equals("time_updated")) {
                has_timeupdated = true;
            }

        }
        if (!has_datecreated) {
            Query.addColumnDATE(table, "date_created");
        }
        if (!has_dateupdated) {
            Query.addColumnDATE(table, "date_updated");
        }
        if (!has_timecreated) {
            Query.addColumnTIME(table, "time_created");
        }
        if (!has_timeupdated) {
            Query.addColumnTIME(table, "time_updated");
        }

        /**
         * ************* cambio para agregar la fechas de creacion de registros
         * ****************
         */
        while (iterator.hasNext()) {
            boolean field = false;
            String val = iterator.next();
            for (int i = 0; i < table_fields.size(); i++) {
                JSONObject column = (JSONObject) table_fields.get(i);
                if (val.equals(column.get("Field").toString())) {
                    field = true;
                    break;
                }
            }
//            labels += " " + val + ",";
//            values += " '" + data.get(val) + "',";
            if (field) {
                labels += " " + val + ",";
                values += " '" + data.get(val) + "',";
            } else {
                System.out.println("Informacion no puede almacenarce: " + val + " -> " + data.get(val));
            }
//            if (!field) {
//                System.out.println("Creando columna inexistente: -----> " + val);
//                if (val.contains("option_")) {
//                    Query.addColumnVarchar(table, val, 15);
//                } else {
//                    Query.addColumnTEXT(table, val);
//                }
//            }

        }
        labels += "date_created, time_created";
        values += "'" + getFecha() + "','" + getHora() + "'";
        query += labels + ") VALUES (" + values + ");";
        return query;
    }

    public static String createQueryInsertWithColumns(String table, JSONObject data) {
        Set<String> keys = data.keySet();
        Iterator<String> iterator = keys.iterator();
        JSONArray keys_null = new JSONArray();

        while (iterator.hasNext()) {
            String key = iterator.next();
            if (data.get(key).toString().equals("")) {
                keys_null.add(key);
            }
        }
        for (int i = 0; i < keys_null.size(); i++) {
            data.remove(keys_null.get(i));
        }

        String query = "DESCRIBE " + table + ";";
        JSONArray table_fields = Query.execute(query);
        query = "INSERT INTO " + table + " (";
        keys = data.keySet();
        iterator = keys.iterator();
        String labels = "";
        String values = "";

        /**
         * ************* cambio para agregar la fechas de creacion de registros
         * ****************
         */
        boolean has_datecreated = false;
        boolean has_dateupdated = false;
        boolean has_timecreated = false;
        boolean has_timeupdated = false;
        for (int i = 0; i < table_fields.size(); i++) {
            JSONObject column = (JSONObject) table_fields.get(i);
            if (column.get("Field").toString().equals("date_created")) {
                has_datecreated = true;
            }
            if (column.get("Field").toString().equals("time_created")) {
                has_timecreated = true;
            }
            if (column.get("Field").toString().equals("date_updated")) {
                has_dateupdated = true;
            }
            if (column.get("Field").toString().equals("time_updated")) {
                has_timeupdated = true;
            }

        }
        if (!has_datecreated) {
            Query.addColumnDATE(table, "date_created");
        }
        if (!has_dateupdated) {
            Query.addColumnDATE(table, "date_updated");
        }
        if (!has_timecreated) {
            Query.addColumnTIME(table, "time_created");
        }
        if (!has_timeupdated) {
            Query.addColumnTIME(table, "time_updated");
        }

        /**
         * ************* cambio para agregar la fechas de creacion de registros
         * ****************
         */
        while (iterator.hasNext()) {
            boolean field = false;
            String val = iterator.next();
            for (int i = 0; i < table_fields.size(); i++) {
                JSONObject column = (JSONObject) table_fields.get(i);
                if (val.equals(column.get("Field").toString())) {
                    field = true;
                    break;
                }
            }
            labels += " " + val + ",";
            values += " '" + data.get(val) + "',";
//            if (field) {
//                labels += " " + val + ",";
//                values += " '" + data.get(val) + "',";
//            } else {
//                //System.out.println("Informacion no puede almacenarce: "+val+" -> "+data.get(val));
//                System.out.println("Creando Columna no existente: " + val);
//
//            }
            if (!field) {
                System.out.println("Creando columna inexistente: -----> " + val);
                if (val.contains("sintoma_") || val.contains("proteccion_")) {
                    Query.addColumnVarchar(table, val, 15);
                }
                if (val.contains("option_")) {
                    Query.addColumnVarchar(table, val, 15);
                } else {
                    Query.addColumnTEXT(table, val);
                }
            }

        }
        labels += "date_created, time_created";
        values += "'" + getFecha() + "','" + getHora() + "'";
        query += labels + ") VALUES (" + values + ");";
        return query;
    }
    
    public static String createQueryInsertWithColumnsMultiple(String table, JSONArray dataM) {
        JSONObject data = (JSONObject) dataM.get(0);
        Set<String> keys = data.keySet();
        Iterator<String> iterator = keys.iterator();
        JSONArray keys_null = new JSONArray();
        
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (data.get(key).toString().equals("")) {
                keys_null.add(key);
            }
        }
        for (int i = 0; i < keys_null.size(); i++) {
            data.remove(keys_null.get(i));
        }
        
        String query = "DESCRIBE " + table + ";";
        JSONArray table_fields = Query.execute(query);
        query = "INSERT INTO " + table + " (";
        keys = data.keySet();
        iterator = keys.iterator();
        String labels = "";
        String values = "";

        /**
         * ************* cambio para agregar la fechas de creacion de registros
         * ****************
         */
        boolean has_datecreated = false;
        boolean has_dateupdated = false;
        boolean has_timecreated = false;
        boolean has_timeupdated = false;
        for (int i = 0; i < table_fields.size(); i++) {
            JSONObject column = (JSONObject) table_fields.get(i);
            if (column.get("Field").toString().equals("date_created")) {
                has_datecreated = true;
            }
            if (column.get("Field").toString().equals("time_created")) {
                has_timecreated = true;
            }
            if (column.get("Field").toString().equals("date_updated")) {
                has_dateupdated = true;
            }
            if (column.get("Field").toString().equals("time_updated")) {
                has_timeupdated = true;
            }
            
        }
        if (!has_datecreated) {
            Query.addColumnDATE(table, "date_created");
        }
        if (!has_dateupdated) {
            Query.addColumnDATE(table, "date_updated");
        }
        if (!has_timecreated) {
            Query.addColumnTIME(table, "time_created");
        }
        if (!has_timeupdated) {
            Query.addColumnTIME(table, "time_updated");
        }

        /**
         * ************* cambio para agregar la fechas de creacion de registros
         * ****************
         */
        while (iterator.hasNext()) {
            boolean field = false;
            String val = iterator.next();
            for (int i = 0; i < table_fields.size(); i++) {
                JSONObject column = (JSONObject) table_fields.get(i);
                if (val.equals(column.get("Field").toString())) {
                    field = true;
                    break;
                }
            }
            labels += " " + val + ",";
//            if (field) {
//                labels += " " + val + ",";
//                values += " '" + data.get(val) + "',";
//            } else {
//                //System.out.println("Informacion no puede almacenarce: "+val+" -> "+data.get(val));
//                System.out.println("Creando Columna no existente: " + val);
//
//            }
            if (!field) {
                System.out.println("Creando columna inexistente: -----> " + val);
                if (val.contains("sintoma_") || val.contains("proteccion_")) {
                    Query.addColumnVarchar(table, val, 15);
                }
                if (val.contains("option_")) {
                    Query.addColumnVarchar(table, val, 15);
                } else {
                    Query.addColumnTEXT(table, val);
                }
            }
            
        }
        
        int cantidadDatos = dataM.size();
        for(int x = 0; x<cantidadDatos; x++){
            JSONObject inf = (JSONObject) dataM.get(x);
            Set<String> keysInf = inf.keySet();
            Iterator<String> iteratorInf = keysInf.iterator();
            
            values += "(";
            while (iteratorInf.hasNext()) {
                String key = iteratorInf.next();
                values += " '" + inf.get(key) + "',";
            }
            values += "'" + getFecha() + "','" + getHora() + "'";
            values += "),";
            
        }
        
        System.out.println("Valor final dde values " + values);
        values = values.substring(0, values.length()-1);
        
        labels += "date_created, time_created";
        query += labels + ") VALUES " + values + ";";
        return query;
    }

    public static String createQueryUpdateAND(String table, JSONObject data, JSONObject where_keys) {
        Set<String> keys = data.keySet();
        Iterator<String> iterator = keys.iterator();
        JSONArray keys_null = new JSONArray();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (data.get(key) != null) {
                if (data.get(key).toString().equals("")) {
                    keys_null.add(key);
                }
            }
        }
        for (int i = 0; i < keys_null.size(); i++) {
            data.remove(keys_null.get(i));
        }
        String query = "DESCRIBE " + table + ";";
        JSONArray table_fields = Query.execute(query);
        query = "UPDATE " + table + " SET ";
        keys = data.keySet();
        iterator = keys.iterator();
        while (iterator.hasNext()) {
            String val = iterator.next();
            boolean field = false;
            for (int i = 0; i < table_fields.size(); i++) {
                JSONObject column = (JSONObject) table_fields.get(i);
                if (val.equals(column.get("Field").toString())) {
                    field = true;
                    break;
                }
            }
//            if(data.get(val)==null){
//                query += val + "= NULL, ";
//            }else{
//                query += val + "='" + data.get(val) + "', ";
//            }

            if (field) {
                query += val + "='" + data.get(val) + "', ";
            } else {
                System.out.println("Informacion no puede actualizarce: " + val + " -> " + data.get(val));
            }
//            if (!field) {
//                System.out.println("Creando columna inexistente: -----> " + val);
//                if (val.contains("option_")) {
//                    Query.addColumnVarchar(table, val, 15);
//                } else {
//                    Query.addColumnTEXT(table, val);
//                }
//            }

        }
        query += "date_updated='" + getFecha() + "', time_updated='" + getHora() + "' WHERE (";
        keys = where_keys.keySet();
        if (keys.size() <= 0) {
            return null;
        }
        iterator = keys.iterator();
        while (iterator.hasNext()) {
            String val = iterator.next();
            query += " " + val + "='" + where_keys.get(val) + "' AND";
        }
        query = query.substring(0, query.length() - 3) + ");";
        return query;
    }

    public static String createQueryUpdateANDwithColumns(String table, JSONObject data, JSONObject where_keys) {
        Set<String> keys = data.keySet();
        Iterator<String> iterator = keys.iterator();
        JSONArray keys_null = new JSONArray();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (data.get(key) != null) {
                if (data.get(key).toString().equals("")) {
                    keys_null.add(key);
                }
            }
        }
        for (int i = 0; i < keys_null.size(); i++) {
            data.remove(keys_null.get(i));
        }
        String query = "DESCRIBE " + table + ";";
        JSONArray table_fields = Query.execute(query);
        query = "UPDATE " + table + " SET ";
        keys = data.keySet();
        iterator = keys.iterator();
        while (iterator.hasNext()) {
            String val = iterator.next();
            boolean field = false;
            for (int i = 0; i < table_fields.size(); i++) {
                JSONObject column = (JSONObject) table_fields.get(i);
                if (val.equals(column.get("Field").toString())) {
                    field = true;
                    break;
                }
            }
            if (data.get(val) == null) {
                query += val + "= NULL, ";
            } else {
                query += val + "='" + data.get(val) + "', ";
            }

//            if(field){
//                query+=val+"='"+data.get(val) + "', ";
//            }else{
//                System.out.println("Informacion no puede actualizarce: "+val+" -> "+data.get(val));
//            }
            if (!field) {
                System.out.println("Creando columna inexistente: -----> " + val);
                if (val.contains("option_")) {
                    Query.addColumnVarchar(table, val, 15);
                } else {
                    Query.addColumnTEXT(table, val);
                }
            }

        }
        query += "date_updated='" + getFecha() + "', time_updated='" + getHora() + "' WHERE (";
        keys = where_keys.keySet();
        if (keys.size() <= 0) {
            return null;
        }
        iterator = keys.iterator();
        while (iterator.hasNext()) {
            String val = iterator.next();
            query += " " + val + "='" + where_keys.get(val) + "' AND";
        }
        query = query.substring(0, query.length() - 3) + ");";
        return query;
    }

    public static String createQueryUpdateOR(String table, JSONObject data, JSONObject where_keys) {
        String query = "UPDATE " + table + " SET ";
        Set<String> keys = data.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String val = iterator.next();
            query += val + "='" + data.get(val) + "', ";
        }
        query += "date_updated='" + getFecha() + "', time_updated='" + getHora() + "' WHERE (";
        keys = where_keys.keySet();
        iterator = keys.iterator();
        while (iterator.hasNext()) {
            String val = iterator.next();
            query += " " + val + "='" + where_keys.get(val) + "' OR";
        }
        query = query.substring(0, query.length() - 2) + ");";
        return query;
    }

    public static boolean addColumnVarchar(String table, String column, int tam) {
        String query = "ALTER TABLE `" + table + "` ADD COLUMN `" + column + "` VARCHAR(" + tam + ") NULL;";
        System.out.println(query);
        boolean actualizado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {

                PreparedStatement ps;
                ps = con.prepareStatement(query);
                //ps.setString(1, activo);
                //ps.setString(2, id);
                ps.execute();

                actualizado = true;

                ps.close();

            }
            c.cerrarConexion();
        } catch (SQLException e) {
            actualizado = false;
            System.out.println(e);
        }
        return actualizado;
    }

    public static boolean addColumnTEXT(String table, String column) {
        String query = "ALTER TABLE `" + table + "` ADD COLUMN `" + column + "` TEXT NULL;";
        System.out.println(query);
        boolean actualizado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {

                PreparedStatement ps;
                ps = con.prepareStatement(query);
                //ps.setString(1, activo);
                //ps.setString(2, id);
                ps.execute();

                actualizado = true;

                ps.close();

            }
            c.cerrarConexion();
        } catch (SQLException e) {
            actualizado = false;
            System.out.println(e);
        }
        return actualizado;
    }

    public static boolean addColumnTIME(String table, String column) {
        String query = "ALTER TABLE `" + table + "` ADD COLUMN `" + column + "` TIME NULL;";
        System.out.println(query);
        boolean actualizado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {

                PreparedStatement ps;
                ps = con.prepareStatement(query);
                //ps.setString(1, activo);
                //ps.setString(2, id);
                ps.execute();

                actualizado = true;

                ps.close();

            }
            c.cerrarConexion();
        } catch (SQLException e) {
            actualizado = false;
            System.out.println(e);
        }
        return actualizado;
    }

    public static boolean addColumnDATE(String table, String column) {
        String query = "ALTER TABLE `" + table + "` ADD COLUMN `" + column + "` DATE NULL;";
        System.out.println(query);
        boolean actualizado = false;
        try {
            Conexion c = new Conexion();
            Connection con = c.getConexion();

            if (con != null) {

                PreparedStatement ps;
                ps = con.prepareStatement(query);
                //ps.setString(1, activo);
                //ps.setString(2, id);
                ps.execute();

                actualizado = true;

                ps.close();

            }
            c.cerrarConexion();
        } catch (SQLException e) {
            actualizado = false;
            System.out.println(e);
        }
        return actualizado;
    }

    public static String getFecha() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    public static String getFecha(String fecha, int dias) throws ParseException {
        //sumarle dias a el date 
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        date = calendar.getTime();
        return dateFormat.format(date);
    }

    public static String getHora() {
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return hourFormat.format(date);
    }

    public int getDiasHabiles(Calendar fechaInicial, Calendar fechaFinal) {
        int diffDays = 0;
        //mientras la fecha inicial sea menor o igual que la fecha final se cuentan los dias
        while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)) {

            //si el dia de la semana de la fecha minima es diferente de sabado o domingo
//            System.out.println(fechaInicial.get(Calendar.DAY_OF_WEEK));
//            System.out.println(Calendar.SUNDAY);
//            System.out.println(Calendar.SATURDAY);
//            System.out.println("******************");
            if (fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                //se aumentan los días de diferencia entre min y max
                diffDays++;
            }
            //se suma 1 dia para hacer la validación del siguiente dia.
            fechaInicial.add(Calendar.DATE, 1);
        }
        return diffDays;
    }
    public String comparaFechas(String date1, String date2) throws ParseException{
        String res = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
        Date d1 = sdf.parse(date1);
        Date d2 = sdf.parse(date2);
        if (d1.before(d2)) {
            res = "Antes";
        }
        if (d1.after(d2)) {
            res = "Despues";
        }
        if (d1.equals(d2)) {
            res = "Exacto";
        }
        
        return res;
    }
    public String comparaHoras(String date1, String date2) throws ParseException{
        String res = "";
        if(StringUtils.countMatches(date1, ":")==0){
            date1+=":00:00";
        }else if(StringUtils.countMatches(date1, ":")==1){
            date1+=":00";
        }
        if(StringUtils.countMatches(date2, ":")==0){
            date2+=":00:00";
        }else if(StringUtils.countMatches(date2, ":")==1){
            date2+=":00";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date d1 = sdf.parse(date1);
        Date d2 = sdf.parse(date2);
        if (d1.before(d2)) {
            res = "<";
        }
        if (d1.after(d2)) {
            res = ">";
        }
        if (d1.equals(d2)) {
            res = "=";
        }
        return res;
    }

    public static void main(String[] args) throws ParseException {
        Query q = new Query();
//        System.out.println(q.comparaFechas("2020-10-30 04:05:45","2020-10-30 09:00:00"));
//        System.out.println(q.comparaFechas("2020-10-30 04:06:00","2020-10-30 19:00:00"));
//        
        System.out.println(q.comparaHoras("10","09:00:00"));
        System.out.println(q.comparaHoras("04:06:00","19:00:00"));
        System.out.println(q.comparaHoras("20:05:45","09:00:00"));
        System.out.println(q.comparaHoras("22:06:00","19:00:00"));
        JSONObject a = new JSONObject();
        a.remove("ok");
        
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
//        String dateI = "2020-10-30";
//        String dateF = "2020-11-11";
//        Date date_I = sdf.parse(dateI);
//        Date date_F = sdf.parse(dateF);
//        Calendar cI = Calendar.getInstance();
//        Calendar cF = Calendar.getInstance();
//        cI.setTime(date_I);
//        cF.setTime(date_F);
//        System.out.println(cI);
//        System.out.println(cF);
//        System.out.println(q.getDiasHabiles(cI, cF));
    }
    
    public static String getHora(String hora, int minutos) throws ParseException {
        //sumarle dias a el date 
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new SimpleDateFormat("HH:mm").parse(hora);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutos);
        date = calendar.getTime();
        
        return hourFormat.format(date);
        
    }
}
