package test;

import java.util.ArrayList;

public class Chuanjiaoshi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 传教士左岸人数，右岸人数
		int M = 4, C = 3, m = 0, c = 0;
		System.out.println("M=" + M + ", C=" + C + ", m=" + m + ", c=" + c
				+ ", boat=left");
		if (!dfs(M, C, m, c))
			System.out.println("no way");
	}

	static boolean flag = true; // true:表示在右岸
	static ArrayList<String> visit = new ArrayList<String>(); // 记录已经访问过的状态

	public static boolean dfs(int M, int C, int m, int c) {
		if (M < 0 || C < 0 || m < 0 || c < 0) // 非法
			return false;
		if ((M > 0 && C > M) || (m > 0 && c > m)) // 野人会吃牧师
			return false;

		if (flag && M == 0 && C == 0 || (!flag && m == 0 && c == 0)) // 全部运输过去
			return true;

		// 检查该节点是否出现过
		String s;
		if (!flag)
			s = "M=" + M + ", C=" + C + ", m=" + m + ", c=" + c + ", boat=left";
		else
			s = "M=" + m + ", C=" + c + ", m=" + M + ", c=" + C
					+ ", boat=right";
		for (int i = 0; i < visit.size(); i++)
			if (visit.get(i).equals(s)) // 该状态已经搜索过了
				return false;
		visit.add(s);
		flag = !flag;
		if (dfs(m + 2, c, M - 2, C)) {
			System.out.println("2,0");
			System.out.println(s);
			return true;
		} else if (dfs(m, c + 2, M, C - 2)) {
			System.out.println("0,2");
			System.out.println(s);
			return true;
		} else if (dfs(m + 1, c + 1, M - 1, C - 1)) {
			System.out.println("1,1");
			System.out.println(s);
			return true;
		} else if (dfs(m + 1, c, M - 1, C)) {
			System.out.println("1,0");
			System.out.println(s);
			return true;
		} else if (dfs(m, c + 1, M, C - 1)) {
			System.out.println("0,1");
			System.out.println(s);
			return true;
		}
		flag = !flag;
		visit.remove(visit.size() - 1);
		return false;
	}

}
