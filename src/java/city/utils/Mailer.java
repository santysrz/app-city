/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.utils;

import city.modelo.Activacion;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author sfsuarez
 */
public class Mailer {
    
    private final Properties properties = new Properties();
	
	private final String password = "sfsuarezgmailsanty.srz2018" ;
        
        private final String smtp_server = "smtp.gmail.com" ;
        
        private final int port_server = 587 ;
        
        private final String cuenta_soporte = "santy.srz@gmail.com";
 
	private Session session;
 
	private void init() {
 
		properties.put("mail.smtp.host", smtp_server );
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port",port_server);
		properties.put("mail.smtp.mail.sender",cuenta_soporte);
		properties.put("mail.smtp.user", cuenta_soporte);
		properties.put("mail.smtp.auth", "true");
 
		session = Session.getDefaultInstance(properties);
	}
 
	public ObjectHand sendEmail(String asunto, String mensaje, String mail_usuario, Activacion activacion){
               ObjectHand handlerMail = null;
		init();
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail_usuario));
			message.setSubject(asunto);
			message.setText(mensaje,"utf-8","html");
			Transport t = session.getTransport("smtp");
			t.connect((String)properties.get("mail.smtp.user"), password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
                        System.out.println ("Lllegas mailer");
                        handlerMail = new ObjectHand("OK","El mail fue enviado Correctamente a:" + mail_usuario,activacion);
                        
		}catch (MessagingException me){
                    
                    //Logger.getLogger(Mailer.class.getName()).log(Level.WARNING, null, me);
                      System.out.println ("Error mailes aca");
                        System.out.println("Error: " + me.getMessage());
                        
                        handlerMail = new ObjectHand("ERROR",me.getMessage(),null);
			
		}
		
            return handlerMail;
	}
}
