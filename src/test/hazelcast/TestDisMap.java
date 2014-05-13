package test.hazelcast;

import java.util.Map;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class TestDisMap {

	public static void main(String[] args) throws Exception {
		Config cfg = new Config();
		final HazelcastInstance instance1 = Hazelcast.newHazelcastInstance(cfg);
        Map<String, String> mapCustomers1 = instance1.getMap("customers");
        mapCustomers1.put("instance1", "instance1");
        
        final HazelcastInstance instance2 = Hazelcast.newHazelcastInstance(cfg);
        Map<String, String> mapCustomers2 = instance2.getMap("customers");
        mapCustomers2.put("instance2", "instance2");
        
        System.out.println("mapCustomers1 == mapCustomers2\t" + (mapCustomers1 == mapCustomers2));
        System.out.println("mapCustomers1.equals(mapCustomers2)\t" + mapCustomers1.equals(mapCustomers2));
        
        System.out.println("---------");
        
        HazelcastInstance instance3 = Hazelcast.newHazelcastInstance(cfg);
        Map<String, String> mapCustomers3 = instance3.getMap("customers");
        mapCustomers3.put("instance3", "instance3");

        System.out.println("mapCustomers1 == mapCustomers3\t" + (mapCustomers1 == mapCustomers3));
        System.out.println("mapCustomers1.equals(mapCustomers3)\t" + mapCustomers1.equals(mapCustomers3));
     
        
        new Thread(new Runnable() {
			@Override
			public void run() {
				instance1.shutdown();
			}
		}).start();
        new Thread(new Runnable() {
        	@Override
        	public void run() {
        		instance2.shutdown();
        	}
        }).start();
        
//        instance1.shutdown();
        Thread.sleep(5000L);
//        System.out.println(mapCustomers1.get("instance1"));
//        System.out.println(mapCustomers2.get("instance1"));
//        System.out.println(mapCustomers3.get("instance1"));

//        instance2.shutdown();
//        Thread.sleep(5000L);
//        System.out.println(mapCustomers1.get("instance2"));
//        System.out.println(mapCustomers2.get("instance2"));
        System.out.println(mapCustomers3.get("instance1"));
        System.out.println(mapCustomers3.get("instance2"));
        System.out.println(mapCustomers3.get("instance3"));
        
        instance3.shutdown();
//        Thread.sleep(5000L);
//        System.out.println(mapCustomers1.get("instance3"));
//        System.out.println(mapCustomers2.get("instance3"));
//        System.out.println(mapCustomers3.get("instance3"));
        
//        instance1.getCluster().getMembers()
	}

}
