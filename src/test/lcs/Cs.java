package test.lcs;

public class Cs {

	public static void main(String[] args) {
		String s1 = "adcfgeheyightkdeffcser";
		String s2 = "theightyye";
		
		int[][] array = new int[s1.length() + 1][s2.length() + 1];
		//填充矩阵
		int max = 0, x = 0;
		for (int i = 1; i < s1.length() + 1; i++) {
			char c1 = s1.charAt(i - 1);
			for (int j = 1; j < s2.length() + 1; j++) {
				if (c1 == s2.charAt(j - 1)) {
					array[i][j] = array[i - 1][j - 1] + 1;
					if (array[i][j] > max) {
						max = array[i][j];
						x = i - 1;
					}
				} else {
					array[i][j] = 0;
				}
					
			}
		}
		//输出最大串
		for (int i = max; i > 0; i--) {
			System.out.print(s1.charAt(x + 1 - i));
		}
		System.out.println();
	}

}
