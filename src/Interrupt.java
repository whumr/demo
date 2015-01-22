
public class Interrupt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Thread t = Thread.currentThread();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
				}
				System.out.println(Thread.currentThread().getName() + "\t interrupt \t" + t.getName());
				t.interrupt();
			}
		}).start();
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
	}

}
