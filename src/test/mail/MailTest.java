package test.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MailTest m = new MailTest();
		m.send();
	}

	private String host = "smtp.qq.com";
	private String username = "2979559743";
	private String password = "fuck_qq";
	private String mail_to = "2313797419@qq.com";
	private String mail_from = "2979559743@qq.com";
	private String mail_subject = "sss";
	private String mail_body = "ddddddddddwew";

	private String personalName = "ssss";

	public void send() {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", 25);
			props.put("mail.smtp.socketFactory.port", 465);
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.socketFactory.fallback", false);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			
			
			Session session = Session.getDefaultInstance(props,
					new Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					});
			session.setDebug(true);
			MimeMessage message = new MimeMessage(session);
			message.setContent("Hello", "text/plain");
			message.setSubject(mail_subject);
			message.setText(mail_body);
			message.setSentDate(new Date()); 

			Address address = new InternetAddress(mail_from, personalName);
			message.setFrom(address);
			Address toAddress = new InternetAddress(mail_to);
			message.addRecipient(Message.RecipientType.TO, toAddress);
//			message.addRecipient(Message.RecipientType.CC, new InternetAddress("dddd"));
			
			Transport transport = session.getTransport("smtp");
			transport.connect(host, username, password);
			Transport.send(message);

			System.out.println("send ok!");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
