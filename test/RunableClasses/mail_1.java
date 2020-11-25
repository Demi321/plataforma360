/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunableClasses;

import Config.config;
import com.sun.mail.imap.IMAPMessage;
import com.sun.mail.smtp.SMTPMessage;
import com.sun.mail.smtp.SMTPTransport;
import com.sun.mail.util.MailSSLSocketFactory;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.json.simple.JSONObject;

/**
 *
 * @author web
 */
public class mail_1 {

    public static void main(String[] args){
        //sendEmail();
        

        //mail2.sendEmail(session, toEmail, fromEmail,password,"TLSEmail Testing Subject", "TLSEmail Testing Body");
    }
    
//   
    public static boolean sendEmail(JSONObject json) {
        boolean enviado = false;
        // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
        String remitente = "soporte.global.sistemas.cc@gmail.com";  //Para la dirección nomcuenta@gmail.com
        String clave = "Global12345";
        //Properties props = System.getProperties();
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "465"); //El puerto SMTP seguro de Google
//        props.put("mail.smtp.socketFactory.port", "587");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.socketFactory.fallback", "false");

        //System.out.println(props);

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
        session.getProperties().put("mail.smtp.starttls.enable", "true");

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(json.get("correo").toString()));   //Se podrían añadir varios de la misma manera
            message.setSubject(json.get("asunto").toString());
            message.setText(json.get("cuerpo").toString());
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            enviado = true;
            
        } catch (MessagingException me) {
            //System.out.println("Error en sendEmail de ---->"+config.getDEPENDENCIA());
            me.printStackTrace();   //Si se produce un error
            return enviado;
        }
        return enviado;
    }
}
