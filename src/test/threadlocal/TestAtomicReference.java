package test.threadlocal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicReference {

	static Resource rs = new Resource();
	static Resource rs2 = new Resource();
	static int test = 0;
	static AtomicInteger ai = new AtomicInteger(0);
	
	static String s = "xx";
	
	static int COUNT = 2000;
	
	public static void main(String[] args) {
		
		long time = System.currentTimeMillis();
		
//		List<Thread> l1 = new ArrayList<Thread>();
//		for (int i = 0; i < COUNT; i++) {
//			TestTread tt = new TestTread();
//			tt.start();
//			l1.add(tt);
//		}
//		checkFinish(l1);
//		System.out.println(ai.get() + "\t" + (System.currentTimeMillis() - time));

		time = System.currentTimeMillis();
		List<Thread> l2 = new ArrayList<Thread>();
		for (int i = 0; i < COUNT; i++) {
			TestTread2 tt = new TestTread2();
			tt.start();
			l2.add(tt);
		}
		checkFinish(l2);
		System.out.println(rs2.number + "\t" + (System.currentTimeMillis() - time));

//		time = System.currentTimeMillis();
//		List<Thread> l3 = new ArrayList<Thread>();
//		for (int i = 0; i < COUNT; i++) {
//			TestTread3 tt = new TestTread3();
//			tt.start();
//			l3.add(tt);
//		}
//		checkFinish(l3);
//		System.out.println(test + "\t" + (System.currentTimeMillis() - time));
	}

	private static void checkFinish(List<Thread> list) {
		while (!list.isEmpty()) {
			for (Iterator<Thread> it = list.iterator(); it.hasNext();) {
				if (!it.next().isAlive())
					it.remove();
			}
		}
	}
	
	static class TestTread extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < COUNT; i++) {
				ai.incrementAndGet();
			}
		}
	}
	
	static class TestTread2 extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < COUNT; i++) {
				synchronized (s.intern()) {
					rs2.number++;
				}
			}
		}
	}

	static class TestTread3 extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < COUNT; i++) {
				synchronized (rs) {
					test++;
				}
			}
		}
	}
}
