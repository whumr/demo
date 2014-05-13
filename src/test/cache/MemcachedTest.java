package test.cache;

import java.io.IOException;
import java.io.Serializable;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcachedTest {
	
	private static MemCachedClient cachedClient = new MemCachedClient();

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		SockIOPool pool = SockIOPool.getInstance();
		//服务器列表及其权重
		String[] servers = {"127.0.0.1:11211"};
		Integer[] weights = {3};
		//设置服务器信息
		pool.setServers(servers);
		pool.setWeights(weights);
		//设置初始连接数、最小连接数、最大连接数、最大处理时间
		pool.setInitConn(10);
		pool.setMinConn(10);
		pool.setMaxConn(1000);
		pool.setMaxIdle(1000*60*60);
		//设置连接池守护线程的睡眠时间
		pool.setMaintSleep(60);
		//设置TCP参数，连接超时
		pool.setNagle(false);
		pool.setSocketTO(60);
		pool.setSocketConnectTO(0);
		//初始化并启动连接池
		pool.initialize();
		//压缩设置，超过指定大小的都压缩
//		cachedClient.setCompressEnable(true);
//		cachedClient.setCompressThreshold(1024*1024);
		
//		Random ran = new Random();
//		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
//		int i = 0, n = 1;
//		while (r.readLine() != null) {
//			for (; i < 1024 * n; i++) {
//				User user = new User("test" + i, String.valueOf(ran.nextInt(100000)));
//				cachedClient.add(user.name, user, 60);
//			}
//			n ++;
//			System.out.println("finished...");
//			System.out.println(cachedClient.get("test100"));
//		}
		
		System.out.println(cachedClient.delete("test"));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 1024 * 1024; i++) {
			sb.append("x");
		}
		User u = new User("test", sb.toString());
		boolean b = cachedClient.add(u.name, u, 60);
		System.out.println(b);
		System.out.println(cachedClient.get("test"));
	}

}

class User implements Serializable {
	private static final long serialVersionUID = 4000638698123315199L;
	
	String name, pass;

	public User(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}
	
	public String toString() {
		return "name:" + name + ",pass:" + pass;
	}
}