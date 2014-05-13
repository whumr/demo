package test.hazelcast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.hazelcast.config.Config;
import com.hazelcast.core.Cluster;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;

public class Node {

	static HazelcastInstance instance;
	static Cluster cluster;
	
	public static void main(String[] args) throws IOException {
		Config cfg = new Config();
        instance = Hazelcast.newHazelcastInstance(cfg);
        cluster = instance.getCluster();
        Map<Integer, String> mapCustomers = instance.getMap("customers");
        mapCustomers.put(1, "Joe");
        mapCustomers.put(2, "Ali");
        mapCustomers.put(3, "Avi");

        System.out.println("Customer with key 1: "+ mapCustomers.get(1));
        System.out.println("Map Size:" + mapCustomers.size());

        Queue<String> queueCustomers = instance.getQueue("customers");
        queueCustomers.offer("Tom");
        queueCustomers.offer("Mary");
        queueCustomers.offer("Jane");
        System.out.println("First customer: " + queueCustomers.poll());
        System.out.println("Second customer: "+ queueCustomers.peek());
        System.out.println("Queue size: " + queueCustomers.size());

        
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = r.readLine()) != null) {
        	final String x = "from " + cluster.getLocalMember().getUuid() + " " + s;
        	callOtherMembers(new MyWork(x));
        }
	}
	
	public static void callOtherMembers(Runnable runner) {
		cluster.getMembers();
		Set<Member> members = new HashSet<Member>();
        Member current = cluster.getLocalMember();
        for(Member member : cluster.getMembers()) {
        	if (!member.getUuid().equals(current.getUuid())) {
        		members.add(member);
        	}
        }
        if (members.size() > 0) {
        	instance.getExecutorService("test").executeOnMembers(runner, members);
        }
	}

	static class MyWork implements Runnable, Serializable {

		private static final long serialVersionUID = 9043399280893117322L;
		
		String content;
		
		public MyWork(String content) {
			this.content = content;
		}

		@Override
		public void run() {
			System.out.println(content);
		}
		
	}
}
