package test.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class WClient {

	public void setup(int port) throws Exception{
		Socket socket=new Socket("localhost",port);
		socket.setKeepAlive(true);
		
		final InputStream in = socket.getInputStream();
		
		new Thread(new Runnable() {
			
			@Override
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
		
		OutputStream ous=socket.getOutputStream();
		DataOutputStream dous=new DataOutputStream(ous);
		Scanner sc=new Scanner(System.in);
		String line;
		while((line=sc.nextLine()) != null) {
			dous.writeUTF(line);
			dous.flush();
		}
		dous.close();
		ous.close();
		Thread.sleep(3000);
	}
	
	public static void main(String[] args) throws Exception {
		new WClient().setup(1234);
	}
}