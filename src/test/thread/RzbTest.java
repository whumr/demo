package test.thread;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

public class RzbTest {

	static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss SSS");
	static int total = 200, size = 20;
	static long time = System.currentTimeMillis() + 2000;
	static Vector<Buyer> v = new Vector<Buyer>();
	
	public static void main(String[] args) throws Exception {
		ArrayList<Buyer> list = new ArrayList<Buyer>();
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			Buyer buyer = new Buyer();
			buyer.money = 10 + r.nextInt(50);
			list.add(buyer);
			new Thread(buyer).start();
		}
		while (System.currentTimeMillis() < time || v.size() < size) {
		}
		int last = total;
		for (Buyer buyer : v) {
			if (last > 0) {
				buyer.bought = Math.min(buyer.money, last);
				buyer.deal_time = System.currentTimeMillis();
				last -= buyer.bought;
			}
			Thread.sleep(r.nextInt(200));
			if (last <= 0)
				break;
		}
		for (int i = 0; i < list.size(); i++) {
			Buyer buyer = list.get(i);
//			if (buyer.bought > 0)
				System.out.println(i + "\t" + buyer);
		}
	}
	
	static class Buyer implements Runnable {
		int money, bought;
		long submit_time, deal_time;
		@Override
		public void run() {
			while (System.currentTimeMillis() < time) {
			}
			v.add(this);
			submit_time = System.currentTimeMillis();
		}
		
		@Override
		public String toString() {
			return new StringBuilder().append("money:").append(money)
					.append(", bought:").append(bought)
					.append(", submit:").append(sdf.format(new Date(submit_time)))
					.append(", deal:").append(sdf.format(new Date(deal_time)))
					.toString();
		}
	}
}
