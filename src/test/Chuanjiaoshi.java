package test;

import java.util.ArrayList;

public class Chuanjiaoshi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ����ʿ���������Ұ�����
		int M = 4, C = 3, m = 0, c = 0;
		System.out.println("M=" + M + ", C=" + C + ", m=" + m + ", c=" + c
				+ ", boat=left");
		if (!dfs(M, C, m, c))
			System.out.println("no way");
	}

	static boolean flag = true; // true:��ʾ���Ұ�
	static ArrayList<String> visit = new ArrayList<String>(); // ��¼�Ѿ����ʹ���״̬

	public static boolean dfs(int M, int C, int m, int c) {
		if (M < 0 || C < 0 || m < 0 || c < 0) // �Ƿ�
			return false;
		if ((M > 0 && C > M) || (m > 0 && c > m)) // Ұ�˻����ʦ
			return false;

		if (flag && M == 0 && C == 0 || (!flag && m == 0 && c == 0)) // ȫ�������ȥ
			return true;

		// ���ýڵ��Ƿ���ֹ�
		String s;
		if (!flag)
			s = "M=" + M + ", C=" + C + ", m=" + m + ", c=" + c + ", boat=left";
		else
			s = "M=" + m + ", C=" + c + ", m=" + M + ", c=" + C
					+ ", boat=right";
		for (int i = 0; i < visit.size(); i++)
			if (visit.get(i).equals(s)) // ��״̬�Ѿ���������
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
