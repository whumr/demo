package test.mina;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Exception {
//		for (int i = 0; i < 10; i++) {
			new Client().start();
//		}
	}
	
	public void start() throws IOException {
		Socket socket = new Socket("localhost", 1234);
		final OutputStream out = socket.getOutputStream();
		final InputStream in = socket.getInputStream();
		
		new Thread(new Runnable() {
			public void run() {
				BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
				String s = null;
				while (true) {
					try {
						s = r.readLine();
						if (s != null && !"".equals(s.trim())) {
							
							out.write(s.getBytes());
							out.flush();
						}
//						Thread.sleep(1000L);
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				byte[] buffer = new byte[1024];
				int length;
				while (true) {
					try {
						while(in.available() > 0) {
							if((length = in.read(buffer)) > 0) {
								System.out.println(new String(buffer, 0, length));
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
						break;
					}
				}
			}
		}).start();
	}

}