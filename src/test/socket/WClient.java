package test.socket;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WClient {

	public void setup(int port) throws Exception{
		Socket socket=new Socket("127.0.0.1",port);
		OutputStream ous=socket.getOutputStream();
		DataOutputStream dous=new DataOutputStream(ous);
		System.out.println("������....");
//		Scanner sc=new Scanner(System.in);
//		String line;
//		while((line=sc.nextLine()) != null) {
			dous.writeUTF("�Ұ���ɽ�ļ������");
			dous.writeUTF(null);
			dous.flush();
			dous.close();
//			System.out.println("line"+line);
//		}
//		System.out.println("����..");
		ous.close();
		Thread.sleep(3000);
	}
	
	public static void main(String[] args) throws Exception {
		new WClient().setup(9090);
	}
}