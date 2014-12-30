package test.rpc.common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import test.rpc.ifs.impl.EchoImpl;

public class Server {

	int port;
	boolean running;
	ServerSocket serverSocket;
	
	static Map<String, Class<?>> implMap;
	
	private static Server server;
	
	static {
		implMap = new HashMap<String, Class<?>>();
		implMap.put("test.rpc.ifs.Echo", EchoImpl.class);
	}
	
	private Server() {
	}
	
	public static void start(int port) throws IOException {
		if (server == null) {
			server = new Server();
			server.port = port;
		}
		if (!server.isRunning())
			server.start();
	}
	
	private void start() throws IOException {
		serverSocket = new ServerSocket(port);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						//read
						final Socket s = serverSocket.accept();
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								try {
									ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
									while (!s.isClosed()) {
										InvokeData data = (InvokeData) ois.readObject();
										
										//invoke
										invoke(data);
										
										//write
										ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
										oos.writeObject(data);
										oos.flush();	
									}
								} catch (IOException e) {
									e.printStackTrace();
								} catch (ClassNotFoundException e) {
									e.printStackTrace();
								}
							}
						}).start();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		running = true;
	}
	
	private void invoke(InvokeData data) {
		try {
			Class<?> clazz = implMap.get(data.getInterface_name());
			Object impl = clazz.newInstance();
			Method method = clazz.getMethod(data.getMethod_name(), data.getParameterTypes());
			
			Object result = method.invoke(impl, data.getParams());
			
			data.setResult(result);
			
			System.out.println("invoke: " + data.getInterface_name() + " -- " + data.getMethod_name() + "\t succeed....");
		} catch (Exception e) {
			System.out.println("invoke: " + data.getInterface_name() + " -- " + data.getMethod_name() + "\t fail....");
			e.printStackTrace();
		}
	}
	
	public boolean isRunning() {
		return running;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}