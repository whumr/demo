package test.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class WServer {
	
	@SuppressWarnings("unused")
	public void setup(int port){
		try{
			ServerSocket s=new ServerSocket(port);
			s.setReceiveBufferSize(8);
			System.out.println("开始监听...");
			final Socket client=s.accept();
//			client.setKeepAlive(true);
//			System.out.println(client.getKeepAlive());
			InputStream ins=client.getInputStream();
			DataInputStream dins=new DataInputStream(ins);
			
			DataOutputStream dout = new DataOutputStream(client.getOutputStream());
			
//			new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
//					try {
//						while(true) {
//							
//							Thread.sleep(1000);
//							System.out.println(client.isConnected() + "\t" + client.isClosed() + "\t" + client.isInputShutdown() + "\t" + client.isOutputShutdown());
//						}
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
////					try {
////						client.close();
////					} catch (IOException e) {
////						e.printStackTrace();
////					}
//				}
//			}).start();
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					Scanner sc=new Scanner(System.in);
					String line;
					while((line=sc.nextLine()) != null) {
						try {
							client.getOutputStream().write(line.getBytes());
							client.getOutputStream().flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
			
//			byte[] bytes = new byte[1024];
//			int length = 0;
//			String line;
//			while(!client.isClosed()){
//				while((length = ins.read(bytes)) != -1) {
//					System.out.println(length + "\t" + bytes[0]);
//					line = new String(bytes, 0, length);
//					System.out.println("Server..line.." + line);
//					client.getOutputStream().write(("Server..line.." + line).getBytes());
//					client.getOutputStream().flush();
////					dout.writeUTF("Server..line.."+line);
//				}
//				System.out.println(length + "\t" + bytes[0]);
////				if (ins.available() < 0) 
////					System.out.println("...............");
//			}
			System.out.println(client.isConnected() + "\t" + client.isClosed() + "\t" + client.isInputShutdown() + "\t" + client.isOutputShutdown());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		 new WServer().setup(1234);
	}
}