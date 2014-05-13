package test.jvm;

import java.util.ArrayList;
import java.util.Random;

public class X {

	static Random r = new Random();
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		while (true) {
			list.add(ran());
		}
	}

	static String ran() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 1000; i++) {
			sb.append((char)(97 + r.nextInt(26)));
		}
		return sb.toString();
//		return sb.toString().intern();
	}
}
