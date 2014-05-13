package test.hash;

import java.util.HashMap;
import java.util.Map;

public class Test_hashmap {

	private String t;
	
	public Test_hashmap(String t) {
		super();
		this.t = t;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "abc";
		System.out.println(s == "abc");
		Test_hashmap t1 = new Test_hashmap("a");
		Test_hashmap t2 = new Test_hashmap("b");
		Test_hashmap t3 = new Test_hashmap("c");
		Test_hashmap t4 = new Test_hashmap("d");
		Test_hashmap t5 = new Test_hashmap("1");
		Map<Test_hashmap, String> map = new HashMap<Test_hashmap, String>();
		map.put(t4, "d");
		map.put(t3, "c");
		map.put(t2, "b");
		map.put(t1, "a");
		map.put(t5, "1");
		System.out.println(map.size());
		for (Test_hashmap t : map.keySet()) {
			System.out.println(t.getT() + "\t" + map.get(t));
		}
	}
	
	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return t.equals("1") ? 0 : 1;
	}
}
