package test.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailTestHtml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MailTestHtml m = new MailTestHtml();
		m.send();
	}

	private String host = "smtp.qq.com";
	private String username = "@qq.com";
	private String password = "";
	private String mail_to = "@qq.com";
	private String mail_subject = "֪ͨ";
	private String mail_body = "��Ҫ֪ͨ";
	private String personalName = "�°���";

	/**
	 * �˶δ�����4������ͨ�����ʼ�
	 */
	public void send() {
		try {
			Properties props = new Properties(); // ��ȡϵͳ����
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			// ����session,���ʼ����������ͨѶ��
			MimeMessage message = new MimeMessage(session);
			message.setHeader("Content-Type", "text/html; charset=UTF-8");
			message.setContent("Hello", "text/plain"); // �����ʼ���ʽ
			message.setSubject(mail_subject); // �����ʼ�����
			message.setText(mail_body); // �����ʼ�����
			message.setSentDate(new Date()); // �����ʼ���������

			
			
			Address address = new InternetAddress(username, personalName);
			// �����ʼ������ߵĵ�ַ
			message.setFrom(address); 
			// �����ʼ����շ��ĵ�ַ
			Address toAddress = new InternetAddress(mail_to); 
			message.addRecipient(Message.RecipientType.TO, toAddress);
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(""));
			
			//��Ӹ���
//			MimeBodyPart mbp = new MimeBodyPart();
//			mbp.setHeader("Content-Type", "application/octet-stream;charset=UTF-8");
//			mbp.setDataHandler(new DataHandler(new FileDataSource("test.txt")));
//			mbp.setContentLanguage(languages)
			
			MimeBodyPart mbp = new MimeBodyPart();
			mbp.setHeader("content-id", "֪ͨ.txt");
			mbp.attachFile(new File("test.txt"));
			MimeMultipart mp = new MimeMultipart();
			mp.addBodyPart(mbp);
			
			//���html����
			StringBuffer content = new StringBuffer();
			BufferedReader reader = new BufferedReader(new FileReader("html.txt"));
			String s = null;
			while((s = reader.readLine()) != null) {
				content.append(s);
			}
			BodyPart bp = new MimeBodyPart();
            bp.setContent("<meta http-equiv=Content-Type content=text/html; charset=UTF-8>" 
            		+ content.toString(), "text/html;charset=UTF-8");   
               
            mp.setSubType("related");   
            // �����������ʼ��ı�              
            mp.addBodyPart(bp); 
			
			
			message.setContent(mp);
			
			// �����ʼ�
			Transport transport = session.getTransport("smtp");
			transport.connect(host, username, password);
			Transport.send(message);

			System.out.println("send ok!");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
