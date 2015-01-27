package test.deadlock;

import java.io.IOException;

public class DeadLockDemo {

	static Object lock1 = new Object(), lock2 = new Object();
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock1) {
					System.out.println("thread 1 get lock1");
					try {
						Thread.sleep(1000L);
						synchronized (lock2) {
							System.out.println("thread 1 get lock2");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock2) {
					System.out.println("thread 2 get lock2");
					try {
						Thread.sleep(1000L);
						synchronized (lock1) {
							System.out.println("thread 2 get lock1");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("..........");
	}
}
