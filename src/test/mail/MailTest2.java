package test.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MailTest2 m = new MailTest2();
		m.send();
	}

	private String host = "";

	private String username = "";

	private String mail_to = "@qq.com";

	private String mail_from = username;

	private String mail_subject = "�������";

	private String mail_body = "����ʣ��ռ䲻��5%";

	private String personalName = "�°���";

	/**
	 * �˶δ�����4������ͨ�����ʼ�
	 */
	public void send() {
		try {
			Properties props = new Properties(); // ��ȡϵͳ����
			props.put("mail.smtp.host", host);

			Session session = Session.getDefaultInstance(props);
			// ����session,���ʼ����������ͨѶ��
			MimeMessage message = new MimeMessage(session);
			message.setContent("Hello", "text/plain"); // �����ʼ���ʽ
			message.setSubject(mail_subject); // �����ʼ�����
			message.setText(mail_body); // �����ʼ�����
			message.setSentDate(new Date()); // �����ʼ���������

			Address address = new InternetAddress(mail_from, personalName);
			message.setFrom(address); // �����ʼ������ߵĵ�ַ
			Address toAddress = new InternetAddress(mail_to); // �����ʼ����շ��ĵ�ַ
			message.addRecipient(Message.RecipientType.TO, toAddress);

			Transport transport = session.getTransport("smtp");
			transport.connect(host, username);
			Transport.send(message); // �����ʼ�

			System.out.println("send ok!");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
