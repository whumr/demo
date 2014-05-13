package test.cglib;

import java.util.logging.Logger;

import net.sf.cglib.beans.BeanCopier;

import org.apache.commons.beanutils.BeanUtils;

public class PropertyCopyTest {

	static final private Logger logger = Logger.getLogger("x");

	public static class Other {

		String userName;

		String password;

		int age;

		String dateTime;

		/**
		 * @return Returns the age.
		 */
		public int getAge() {
			return age;
		}

		/**
		 * @param age
		 *            The age to set.
		 */
		public void setAge(int age) {
			this.age = age;
		}

		/**
		 * @return Returns the dateTime.
		 */
		public String getDateTime() {
			return dateTime;
		}

		/**
		 * @param dateTime
		 *            The dateTime to set.
		 */
		public void setDateTime(String dateTime) {
			this.dateTime = dateTime;
		}

		/**
		 * @return Returns the password.
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * @param password
		 *            The password to set.
		 */
		public void setPassword(String password) {
			this.password = password;
		}

		/**
		 * @return Returns the userName.
		 */
		public String getUserName() {
			return userName;
		}

		/**
		 * @param userName
		 *            The userName to set.
		 */
		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String toString() {

			StringBuffer sb = new StringBuffer();
			sb.append("Other[");
			sb.append("UserName=");
			sb.append(this.userName);
			sb.append(",");
			sb.append("Password=");
			sb.append(this.password);
			sb.append(",");
			sb.append("Age=");
			sb.append(this.age);
			sb.append(",");
			sb.append("DateTime=");
			sb.append(this.dateTime);
			sb.append("]");

			return sb.toString();
		}
	}

	public static class Myth {

		String userName;

		String password;

		int age;

		long dateTime;

		/**
		 * @return Returns the dateTime.
		 */
		public long getDateTime() {
			return dateTime;
		}

		/**
		 * @param dateTime
		 *            The dateTime to set.
		 */
		public void setDateTime(long dateTime) {
			this.dateTime = dateTime;
		}

		/**
		 * @return Returns the age.
		 */
		public int getAge() {
			return age;
		}

		/**
		 * @param age
		 *            The age to set.
		 */
		public void setAge(int age) {
			this.age = age;
		}

		/**
		 * @return Returns the password.
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * @param password
		 *            The password to set.
		 */
		public void setPassword(String password) {
			this.password = password;
		}

		/**
		 * @return Returns the userName.
		 */
		public String getUserName() {
			return userName;
		}

		/**
		 * @param userName
		 *            The userName to set.
		 */
		public void setUserName(String userName) {
			this.userName = userName;
		}
	}

	private Myth init() {

		Myth myth = new Myth();
		myth.setUserName("jjyao");
		myth.setPassword("1111");
		myth.setAge(24);
		myth.setDateTime(System.currentTimeMillis());

		return myth;

	}

	public static void main(String[] args) throws Exception {

		Other other = new Other();
		Myth myth = new PropertyCopyTest().init();
			
		BeanCopier copier = BeanCopier.create(Myth.class, Other.class, false);
		
		long beginTime = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			copier.copy(myth, other, null);
		}
		logger.info("" + other);
		logger.info(" Use Cglib,It costs time : "
				+ (System.currentTimeMillis() - beginTime));

		long beginTime2 = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			BeanUtils.copyProperties(other, myth);

		}
		logger.info("" + other);
		logger.info(" Use BeanUtils,It costs time : "
				+ (System.currentTimeMillis() - beginTime2));

	}
}
