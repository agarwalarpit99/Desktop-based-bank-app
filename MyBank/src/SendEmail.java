import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		try{
		  String SMTP_HOST = "smtp.gmail.com";
		  String FROM_ADDRESS = "arpitagarwal916.aa@gmail.com";
		  String PASSWORD = "arpit4u1@";
		  String FROM_NAME = "Self";
		  String TO_NAME = "";
		  String TO_ADDRESS = "damodarsingh.ds9501@gmail.com";
		  Properties props = new Properties();  
		  props.put("mail.smtp.host", SMTP_HOST);
          props.put("mail.smtp.auth", "true");
          props.put("mail.debug", "false");
          props.put("mail.smtp.ssl.enable", "true");
          Session session = Session.getInstance(props, new SocialAuth());
          Message msg = new MimeMessage(session);

          InternetAddress from = new InternetAddress(FROM_ADDRESS, FROM_NAME);
          msg.setFrom(from);

          InternetAddress to = new InternetAddress(TO_ADDRESS, TO_NAME);
          msg.setRecipient(Message.RecipientType.TO,to);

          msg.setSubject("This is Send by Arpit");
          msg.setContent("HELLLO..","text/plain");
          
	      
	         // Send message
	         Transport.send(msg);
	         System.out.println("Sent message successfully....");
	      }
			catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
		}
	   }
class SocialAuth extends Authenticator {

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("arpitagarwal916.aa@gmail.com","arpit4u1@");
    }
}
