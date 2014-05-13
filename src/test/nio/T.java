package test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class T {

	static InetSocketAddress id = new InetSocketAddress("127.0.0.1", 8888);
	
	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			new TestThread("TestThread " + i).start();
		}
	}

	static class TestThread extends Thread {
		
		public TestThread(String name) {
			super(name);
		}
		
		@Override
		public void run() {
			try {
				Socket s = new Socket();
				s.connect(id);
				s.getOutputStream().write((getName() + "\t send msg.").getBytes());
				s.getOutputStream().flush();
				
				int length = 0;
				byte[] bs = new byte[1024];
				while (s.getInputStream().available() > 0) {
					length = s.getInputStream().read(bs);
					System.out.println(new String(bs, 0, length));
				}
				
				s.close();
//				while (true) {
//					try {
//						Thread.sleep(1000L);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
