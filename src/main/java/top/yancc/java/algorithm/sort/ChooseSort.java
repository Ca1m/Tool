package top.yancc.java.algorithm.sort;

// 选择排序  选择最小的
// 空间复杂度 O(1)  时间复杂度 O(2n)
public class ChooseSort {

	public static void main(String[] args) {
		
		int[] nums = new int[] {5, 11, 3, 1, 6, 2, 7, 9, 4};

		nums = chanceSort(nums);
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i]+",");
		}
	}

	public static int[] chanceSort(int[] nums) {
		for (int i = 0; i < nums.length-1; i++) {
			int min = i; // 记录最小值的位置
			for (int j = i+1; j < nums.length; j++) {
				if (nums[i] > nums[j]) {
					min = j;
				}
			}
			swap(nums, i, min);
		}
		return nums;
	}
	public static void swap(int[] nums, int left, int right) {
		int temp = nums[left];
		nums[left] = nums[right];
		nums[right] = temp;
	}
}
