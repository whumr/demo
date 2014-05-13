package test.threadlocal;

public class TestBasic {

//	static final Resource rs = new Resource();
//	static {
//		rs.name = "xx";
//	}
	
	static ThreadLocal<Resource> tl = new ThreadLocal<Resource>() {
		@Override
		protected Resource initialValue() {
			return new Resource();
		}
	};
	
	public static void main(String[] args) {
		new TestTread().start();
		new TestTread().start();
		new TestTread().start();
		new TestTread().start();
	}
	
	static class TestTread extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				Resource r = tl.get();
				System.out.println(Thread.currentThread().getName() + "\t" + r.name);
				r.name = r.name + i;
				tl.set(r);
			}
		}
	}
}

class Resource {
	String name;
	int number;
}