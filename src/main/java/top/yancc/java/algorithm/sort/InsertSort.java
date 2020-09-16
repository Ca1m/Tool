package top.yancc.java.algorithm.sort;

// 插入排序  
public class InsertSort {

	public static void main(String[] args) {
		
		int[] nums = new int[] {5, 11, 3, 1, 6, 2, 7, 9, 4};
		
		nums = insertSort(nums);

		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + ",");
		}
	}
	public static int[] insertSort(int[] nums) {
		for (int i = 1; i < nums.length; i++) {
			for (int j = i; j > 0; j--) {
				if (nums[j] < nums[j-1]) {
					swap(nums, j, j-1);
				} else {
					break;
				}
			}
		}
		return nums;
	}
	
	public static void swap(int[] nums, int left, int right) {
		int temp = nums[left];
		nums[left] = nums[right];
		nums[right] = temp;
	}
}
