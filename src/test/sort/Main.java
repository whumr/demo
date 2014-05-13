package test.sort;

public class Main {
	
	static int[] test = {4,2,7,3,8,4,11,5,9};
	public static void main(String[] args) {
//		quickSort(test, 0, test.length - 1);
		qSort(test, 0, test.length - 1);
		for (int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}
	}
	
	////////////////方式三：减少交换次数，提高效率/////////////////////
	static void quickSort(int[] targetArr,
			int start, int end) {
		int i = start, j = end;
		int key = targetArr[start];

		while (i < j) {
			// 按j--方向遍历目标数组，直到比key小的值为止
			while (j > i && targetArr[j] >= key) {
				j--;
			}
			if (i < j) {
				// targetArr[i]已经保存在key中，可将后面的数填入
				targetArr[i] = targetArr[j];
			}
			// 按i++方向遍历目标数组，直到比key大的值为止
			while (i < j && targetArr[i] < key) {
				i++;
			}
			if (i < j) {
				// targetArr[j]已保存在targetArr[i]中，可将前面的值填入
				targetArr[j] = targetArr[i];
			}
		}
		// 此时i==j
		targetArr[i] = key;

		if (i - start > 1) {
			// 递归调用，把key前面的完成排序
			quickSort(targetArr, start, i - 1);
		}
		if (end - j > 1) {
			// 递归调用，把key后面的完成排序
			quickSort(targetArr, j + 1, end);
		}
	}

	private static void qSort(int[] array, int start, int end) {
		int v = array[start], i = start, j = end;
		while (i < j) {
			while (i < j && array[j] >= v)
				j --;
			if (i < j)
				array[i] = array[j];
			while (i < j && array[i] < v)
				i ++;
			if (i < j)
				array[j] = array[i];
		}
		if (i != start)
			array[i] = v;
		if (i - start > 1)
			qSort(array, start, i - 1);
		if (end - j > 1)
			qSort(array, j + 1, end);
	}
}
