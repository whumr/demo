package test.objectio;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Server {
	
	private int port;

	public Server(int port) {
		this.port = port;
	}

	public void startRead() {
		try {
			ServerSocket ss = new ServerSocket(port);
			while (true) {
				Socket s = ss.accept();
				s.getInputStream();
				
				ObjectInput in = new ObjectInputStream(s.getInputStream());
				Obj o1 = new Obj();
				o1.readExternal(in);
				System.out.println(o1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void startWrite(int port) {
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Socket s;
		try {
			s = new Socket("127.0.0.1", port);
			ObjectOutput out = new ObjectOutputStream(s.getOutputStream());
			List<String> list = new ArrayList<String>();
			list.add("aa");
			list.add("bb");
			list.add("cc");
			Obj o = new Obj("test", 11, list);
			o.writeExternal(out);
			out.flush();
			out.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		final int port1 = 12341, port2 = 12342;
		final Server s1 = new Server(port1);
		final Server s2 = new Server(port2);
		new Thread(new Runnable() {
			@Override
			public void run() {
				s1.startRead();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				s2.startWrite(port1);
			}
		}).start();
	}

}
