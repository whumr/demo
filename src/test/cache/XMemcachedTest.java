package test.cache;

import java.io.IOException;

import net.rubyeye.xmemcached.KeyIterator;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;

@SuppressWarnings("deprecation")
public class XMemcachedTest {

	static MemcachedClient memcachedClient;
	
	static String ip1 = "localhost:11211", ip2 = "192.168.96.128:11211", ip3 = "192.168.96.129:11211";
	
	public static void main(String[] args) throws Exception {
//		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
//				AddrUtil.getAddresses("localhost:11211"));
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses(ip1 + " " + ip2), new int[]{2, 1});
//		builder.setCommandFactory(new BinaryCommandFactory());
		builder.setSessionLocator(new KetamaMemcachedSessionLocator());
		memcachedClient = builder.build();
		try {
//			memcachedClient.flushAll();
			
			for (int i = 0; i < 10; i++) {
				memcachedClient.set("hello" + i, 0, "Hello, xmemcached" + i);
			}
			
			print();
			System.out.println(memcachedClient.get("hello0"));
			
			
//			System.out.println("~~~~~~~~~ 	clear	~~~~~~~~~~~~~");
//			memcachedClient.flushAll();
//			print();
			
//			System.out.println("~~~~~~~~~ 	remove	~~~~~~~~~~~~~");
//			
//			memcachedClient.removeServer("192.168.96.128:11211");
//			Thread.sleep(3000);
//			
//			print();
//			System.out.println(memcachedClient.get("hello4"));
			
//			System.out.println("~~~~~~~~~   set again ~~~~~~~~~~~~~");
//			for (int i = 0; i < 10; i++) {
//				memcachedClient.set("hello" + i, 0, "Hello, xmemcached" + i);
//			}
//			System.out.println(memcachedClient.get("hello4"));
//			
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
//			print();
//			
			System.out.println("~~~~~~~~~ 	add	~~~~~~~~~~~~~");
			memcachedClient.addServer(ip3);
//			System.out.println("~~~~~~~~~ 	reset	~~~~~~~~~~~~~");
//			reset();
			
			print(true);
			System.out.println(memcachedClient.get("hello0"));
			
		} catch (Exception e) {
			// ignore
		}
		try {
			// close memcached client
			memcachedClient.shutdown();
		} catch (IOException e) {
			System.err.println("Shutdown MemcachedClient fail");
			e.printStackTrace();
		}
	}
	
	static void reset() throws Exception {
		Thread.sleep(1000);
		for (int i = 0; i < 10; i++) {
			memcachedClient.set("hello" + i, 0, "Hello, xmemcached" + i);
		}
	}
	
	static void print() throws Exception {
		print(false);
	}

	static void print(boolean b) throws Exception {
		Thread.sleep(1000);
		
//		cliet
		
		KeyIterator it = memcachedClient.getKeyIterator(AddrUtil.getOneAddress(ip1));
		System.out.println("------\t" + ip1);
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(key + "\t" + memcachedClient.get(key));
		}

		it = memcachedClient.getKeyIterator(AddrUtil.getOneAddress(ip2));
		System.out.println("------\t" + ip2);
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(key + "\t" + memcachedClient.get(key));
		}
		
		if (b) {
			it = memcachedClient.getKeyIterator(AddrUtil.getOneAddress(ip3));
			System.out.println("------\t" + ip3);
			while (it.hasNext()) {
				String key = it.next();
				System.out.println(key + "\t" + memcachedClient.get(key));
			}
		}

	}
}
