package test.lcs;

public class Lcs {

	public static void main(String[] args) {
		String s1 = "abcbdab";
		String s2 = "bdcaba";
		getLCSLength(s1, s2);
	}

	public static void getLCSLength(String str1, String str2) {
		char[] x = str1.toCharArray();
		char[] y = str2.toCharArray();

		int[][] c = new int[x.length + 1][y.length + 1];

		for (int i = 1; i < x.length + 1; ++i) {
			for (int j = 1; j < y.length + 1; ++j) {
				if (x[i - 1] == y[j - 1])
					c[i][j] = c[i - 1][j - 1] + 1;
				else if (c[i - 1][j] >= c[i][j - 1])
					c[i][j] = c[i - 1][j];
				else
					c[i][j] = c[i][j - 1];

			}
		}
		printLCS(c, x, y, x.length, y.length);
	}

	public static void printLCS(int[][] c, char[] x, char[] y, int i, int j) {
		if (i == 0 || j == 0)
			return;
		if (x[i - 1] == y[j - 1]) {
			printLCS(c, x, y, i - 1, j - 1);
			System.out.print(x[i - 1]);
		} else if (c[i - 1][j] >= c[i][j - 1])
			printLCS(c, x, y, i - 1, j);
		else
			printLCS(c, x, y, i, j - 1);
	}
}
