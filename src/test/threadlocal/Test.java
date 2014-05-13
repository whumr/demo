package test.threadlocal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {

	static int test = 0, COUNT = 1000;
	
	public static void main(String[] args) throws InterruptedException {
		List<Thread> list = new ArrayList<Thread>();
		for (int i = 0; i < COUNT; i++) {
			Thread t = new TestTread3();
			list.add(t);
			t.start();
		}
		while (!list.isEmpty()) {
			for (Iterator<Thread> it = list.iterator(); it.hasNext();) {
				if (!it.next().isAlive())
					it.remove();
			}
		}
		System.out.println(test);
	}
	
	static class TestTread3 extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < COUNT; i++) {
				test++;
			}
		}
	}
}
