/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunableClasses;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author moises
 */
public class Horario_laboral {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        String hora_inicio = "09:00:00";
        String hora_fin = "19:00:00";
        
        
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        Date hour_inicio = hourFormat.parse(hora_inicio);
        Date hour_fin = hourFormat.parse(hora_fin);
        
        String hora = hourFormat.format(date);
        
        Date hour = hourFormat.parse(hora);
        
        
        System.out.println(hour_inicio);
        System.out.println(hour);
        System.out.println(hour_fin);
        
        System.out.println(hour.after(hour_inicio));
        System.out.println(hour.before(hour_fin));
        
        if (hour.after(hour_inicio) && hour.before(hour_fin)) {
            System.out.println("ok");
        } else {
            System.out.println("no");
        }
    }

}
