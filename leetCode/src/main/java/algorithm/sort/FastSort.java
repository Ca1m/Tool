package algorithm.sort;

// 快速排序
public class FastSort {

	public static void main(String[] args) {
		
		int[] nums = new int[] {3, 1, 4};
		
		nums = recursionSort(nums, 0, nums.length - 1);
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i]+",");
		}
	}
	public static int[] recursionSort(int[] arr, int left, int right) {
		
		if (left < right) {
			// 排序，并返回已排序的 中轴元素weizh
			int mid = partition(arr, left, right);
			// 分割 左边的数组
			arr = recursionSort(arr, left, mid - 1);
			// 分割 右边的数组
			arr = recursionSort(arr, mid + 1, right);
		}
		return arr;
	}
	
	public static int partition(int[] arr, int left, int right) {
		
		int pos = arr[left];
		int i = left + 1;
		int j = right;
		while (true) {
			// 左边的元素 小于，一直++，找到大于 pos 的元素，next
			while (i <= j && arr[i] <= pos) i++;  // i++ == j  j/j--
			// 右边的元素 大于，一直 --，找到小于 pos 的元素，next
			while (i <= j && arr[j] >= pos) j--;  // j-- == i  i/i--
			if (i > j) { 
				System.out.println(i + "," + j);
				break;
			}
			swap(arr, i, j);
		}
		arr[left] = arr[j];
		arr[j] = pos;
		return j;
	}
	
	
	
	public static void swap(int[] arr, int left, int right) {
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}
	
}
