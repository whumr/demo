package test.mail.util;

public interface MailContext {

	public String[] getReceivers();
	
	public void setReceivers(String... receivers);
	
	public void setSender();
}