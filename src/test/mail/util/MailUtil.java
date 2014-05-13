package test.mail.util;

public class MailUtil {

	private static MailUtil mailUtil;
	
	private MailUtil() {
	}
	
	public static MailUtil getInstance() {
		if(mailUtil == null)
			mailUtil = new MailUtil();
		return mailUtil;
	}
	
	public void sendMail() {
		
	}
	
	
}