package test.thread;


public class Main {

	private static String lock = "lock";
	
	public static void main(String[] args) throws Exception {
		System.out.println(System.currentTimeMillis());
		new Thread1(lock).start();
		new Thread2(lock).start();
		Thread.sleep(2000);
		synchronized (lock) {
//			lock.wait(5000);
			lock.notifyAll();
		}
		System.out.println(System.currentTimeMillis());
	}

}

class Thread1 extends Thread {
	private Object lock;

	public Thread1(Object lock) {
		super();
		this.lock = lock;
		this.setName("thread1");
	}
	
	@Override
	public void run() {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		synchronized (lock) {
			try {
				lock.wait(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(getName() + "\t" + System.currentTimeMillis());
		}
	}
}

class Thread2 extends Thread {
	private Object lock;

	public Thread2(Object lock) {
		super();
		this.lock = lock;
		this.setName("thread2");
	}
	
	@Override
	public void run() {
		synchronized (lock) {
			System.out.println(getName() + "\t" + System.currentTimeMillis());
		}
	}
}