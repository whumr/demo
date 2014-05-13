package test.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolTest {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		
		MyCall c1 = new MyCall();
		MyCall c2 = new MyCall();
		
		Future<Integer> f1 = executor.submit(c1);
		Future<Integer> f2 = executor.submit(c2);
		
		NoCall n1 = new NoCall();
		NoCall n2 = new NoCall();
		
		Future<?> f3 = executor.submit(n1);
		Future<?> f4 = executor.submit(n2);
		
		boolean b1 = true, b2 = true, b3 = true, b4 = true;
		while (true && (b1 || b2 || b3 || b4)) {
			try {
				if (f1.isDone() && b1) {
					System.out.println("f1 finished... " + f1.get());
					b1 = false;
				}
				if (f2.isDone() && b2) {
					System.out.println("f2 finished... " + f2.get());
					b2 = false;
				}
				if (f3.isDone() && b3) {
					System.out.println("f3 finished... " + f3.get());
					b3 = false;
				}
				if (f4.isDone() && b4) {
					System.out.println("f4 finished... " + f4.get());
					b4 = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
	}

}

class MyCall implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		Random r = new Random();
		int i = 1000 + r.nextInt(3000);
		Thread.sleep(i);
		return i;
	}

}

class NoCall implements Runnable {

	@Override
	public void run() {
		Random r = new Random();
		int i = 1000 + r.nextInt(3000);
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
