import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailSender {
	
    final String username = "esgi.dev@gmail.com";
    final String password = "Pass123!";
    
    private Session session;
    private Properties props;
    
    
    public GmailSender() {
        props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        this.session = Session.getInstance(props,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    
    }
    
    public boolean sendMessage(String to, String obj , String msg) {
		System.out.println("GmailSender : Tentative d'envoi de mail");
	    	try {
	    			Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(username));
	            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
	            message.setSubject(obj);
	            message.setText(msg);
	            Transport.send(message);
	            System.out.println("GmailSender : Message envoyé avec succès");
	            return true;
	            
	    	}catch(MessagingException e) {
            System.out.println("GmailSender : Echec de l'envoi");
	    		e.printStackTrace();
	    		return false;
	    		
	    	}
    
    }

}
