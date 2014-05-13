package test.thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class X {

	private static int n;
	
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		int count = 10, size = 100;
		List<Thread> list = new ArrayList<Thread>();
		for (int i = 0; i < count; i++) {
			Thread t = new XThread(i, count, size);
			list.add(t);
			t.start();
		}
		while (!list.isEmpty()) {
			for (Iterator<Thread> it = list.iterator(); it.hasNext();) {
				if (!it.next().isAlive())
					it.remove();
			}
		}
		System.out.println(System.currentTimeMillis() - time);
	}

	static class XThread extends Thread {
		
		int index, count, size;
		
		public XThread(int index, int count, int size) {
			super("XThread - " + index);
			this.index = index;
			this.count = count;
			this.size = size;
		}
		
		@Override
		public void run() {
			while (n < size)
				if (n % count == index)
					n++;
			//System.out.println(getName() + "\t" + n++);
		}
	}
	
}