package test.ssq;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SsqCompute {

	static String mine = "04 05 17 21 30 32 14";
	
	public static void main(String[] args) throws IOException {
		String[] ss = mine.split("\\s");
		Ssq ssq = new Ssq();
		for (int i = 0; i < 6; i++) 
			ssq.reds[i] = Integer.parseInt(ss[i].trim());
		ssq.blue = Integer.parseInt(ss[6].trim());
		
		List<Ssq> list = load();
		for (int i = 0; i < list.size(); i++) {
			int reward = ssq.compute(list.get(i));
			if (reward <= 4)
				System.out.println(i + "\t" + reward + "\t" + list.get(i));
		}
	}
	
	public static List<Ssq> load() throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("ssq.txt"));
		List<Ssq> list = new ArrayList<Ssq>();
		String s = null;
		while ((s = r.readLine()) != null) {
			if (!"".equals(s.trim())) {
				String[] ss = s.split("\\s");
				Ssq ssq = new Ssq();
				for (int i = 0; i < 6; i++) 
					ssq.reds[i] = Integer.parseInt(ss[i].trim());
				ssq.blue = Integer.parseInt(ss[6].trim());
				list.add(ssq);
			}
		}
		return list;
	}

}
