package test.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest2 {

	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3));
		
		MyRunner r1 = new MyRunner("r1");
		MyRunner r2 = new MyRunner("r2");
		MyRunner r3 = new MyRunner("r3");
		MyRunner r4 = new MyRunner("r4");
		MyRunner r5 = new MyRunner("r5");
		executor.execute(r1);
		executor.execute(r2);
		executor.execute(r3);
		executor.execute(r4);
		executor.execute(r5);
//		executor.submit(r1);
//		executor.submit(r2);
//		executor.submit(r3);
//		executor.submit(r4);
//		executor.submit(r5);
		executor.shutdown();
	}
}


class MyRunner implements Runnable {
	String name;
	public MyRunner(String name) {
		super();
		this.name = name;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName() + "\t" + this.name + "\tfinished....");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}