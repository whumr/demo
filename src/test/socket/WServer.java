package test.socket;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WServer {
	
	public void setup(int port){
		try{
			ServerSocket s=new ServerSocket(port);
			System.out.println("¿ªÊ¼¼àÌý...");
			Socket client=s.accept();
			
			InputStream ins=client.getInputStream();
			DataInputStream dins=new DataInputStream(ins);
			String line;
//			while(ins.available() > 0){
				while((line=dins.readUTF())!=null)
					System.out.println("Server..line.."+line);
//			}
			dins.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		 new WServer().setup(9090);
	}
}