package test.rpc.test;

import java.io.IOException;

import test.rpc.common.Client;
import test.rpc.ifs.Echo;

public class Test_Client {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Client client = Client.getInstance("127.0.0.1", 15784);
		Object result = client.invoke(Echo.class, "echo", new Object[]{"xxxxx"});
		
		System.out.println(result);

		
		result = client.invoke(Echo.class, "date", null);
		
		System.out.println(result);
		
		client.close();
	}
}