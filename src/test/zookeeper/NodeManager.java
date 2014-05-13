package test.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class NodeManager {

	static int CLIENT_PORT = 2181, CONNECTION_TIMEOUT = 5000;
	static String path = "/workers";
	static String child_path = path + "/worker";
	static ZooKeeper zk;
	static MyWatcher mw;
	static MyWoker[] workers;
	
	public static void main(String[] args) throws Exception {
		mw = new MyWatcher();
		zk = new ZooKeeper("127.0.0.1:" + CLIENT_PORT, CONNECTION_TIMEOUT, mw); 
		workers = new MyWoker[]{new MyWoker("woker1"), new MyWoker("woker2")};
		
		zk.create(path, "0".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT); 
		System.out.println(
				zk.create(child_path, "0".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL)
				);
		
		zk.exists(child_path, true);
		
		Thread.sleep(1000);
		
		zk.setData(child_path, "0".getBytes(), -1);
		
		
		Thread.sleep(5000);
		// 删除子目录节点
		zk.delete(child_path, -1);
		// 删除父目录节点
		zk.delete(path, -1);
		// 关闭连接
		zk.close();
	}
	
	
	static class MyWatcher implements Watcher {
		
		@Override
		public void process(WatchedEvent event) {
			System.out.println(event.getType());
			if (event.getType() == EventType.NodeCreated ||
					event.getType() == EventType.NodeChildrenChanged ||
					event.getType() == EventType.NodeDataChanged) {
				try {
					String key = new String(zk.getData(child_path, true, null));
					workers[Integer.parseInt(key)].work();
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
		
	}

	static class MyWoker {
		String name;
		
		public MyWoker(String name) {
			this.name = name;
		}
		
		public void work() {
			System.out.println(name + " is working...");
			try {
				int index = (int)(zk.getData(child_path, true, null)[0]);
				index = (++index) % workers.length;
				Thread.sleep(500);
				zk.setData(child_path, (index + "").getBytes(), -1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

