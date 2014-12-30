package test.rpc.common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class Client {

	int port;
	String host;
	Socket socket;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	private static Client client;
	private Client(String host, int port) throws IOException{
		socket = new Socket(host, port);
		oos = new ObjectOutputStream(socket.getOutputStream());
	}
	
	public static Client getInstance(String host, int port) throws IOException {
		if(client == null) {
			client = new Client(host, port);
		}
		return client;
	}
	
	public Object invoke(Class<?> interfaze, String method, Object[] params) throws IOException, ClassNotFoundException {
		//data
		InvokeData data = new InvokeData();
		data.setInterface_name(interfaze.getName());
		data.setMethod_name(method);
		data.setParams(params);
		data.setParameterTypes(getParamTypes(interfaze, method, params));
		
		//write
		oos.writeObject(data);
		oos.flush();

		//read
		ois = new ObjectInputStream(socket.getInputStream());
		data = (InvokeData) ois.readObject();
		
		//get result
		return data.getResult();
	}

	private Class<?>[] getParamTypes(Class<?> interfaze, String method, Object[] params) {
		Method[] methods = interfaze.getMethods();
		for (Method m : methods) {
			if (m.getName().equals(method)) {
				Class<?>[] pts = m.getParameterTypes();
				if (pts != null && params != null && pts.length == params.length) {
					boolean match = true;
					for (int i = 0; i < pts.length; i++) {
						if (!pts[i].equals(params[i].getClass())) {
							match = false;
							break;
						}
					}
					if (match)
						return pts;
				}
			}
		}
		return null;
	}
	
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}