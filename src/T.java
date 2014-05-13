import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class T {
	public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String s = "wew，sds,ss   wqe";
		String[] ss = s.split(",|，|\\s+");
		for (int i = 0; i < ss.length; i++) {
			System.out.println(ss[i]);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.parse("11:30:00").toString());
		System.out.println(sdf.format(new Date()));
		
		System.out.println(sdf.parse(sdf.format(new Date())).before(sdf.parse("11:30:00")));
		
		System.out.println(new Date(sdf2.parse(sdf2.format(new Date())).getTime() + sdf.parse("11:30:00").getTime()));
		
		Calendar cl = Calendar.getInstance();
//		for (int i = 0; i < 10; i++) {
			cl.setTimeInMillis(System.currentTimeMillis());
			System.out.println(cl.get(Calendar.HOUR));
			System.out.println(cl.get(Calendar.MINUTE));
			System.out.println(cl.get(Calendar.SECOND));
			System.out.println("---------");
//			Thread.sleep(3000);
//		}
		
		System.out.println(TIME_FORMAT.format(new Date(1398735180000L)));
		System.out.println(1398735180000L);
		System.out.println(DATE_FORMAT.parse("2014/04/28 15:03:06").getTime());
		int i = (int)(DATE_FORMAT.parse("2014/04/28 15:03:06").getTime() / 1000);
		System.out.println(i);
		System.out.println(TIME_FORMAT.format(new Date(i * 1000L)));
		System.out.println(TIME_FORMAT.format(new Date(DATE_FORMAT.parse("2014/04/28 15:03:06").getTime())));
	}

}
