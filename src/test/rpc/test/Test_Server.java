package test.rpc.test;

import java.io.IOException;

import test.rpc.common.Server;

public class Test_Server {

	public static void main(String[] args) {
		try {
			Server.start(15784);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
