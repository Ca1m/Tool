package top.yancc.java.algorithm.search;

//  二分查找法
public class BinarySearch {
	public static void main(String[] args) {
		
		int[] nums = {5,6,7,9,11};
		
		int index = binarySearch(nums, 9);
		if (index == -1) {
			System.out.println("没有该元素.");
		}
		System.out.println("数组角标为：" + index);
	}
	public static int binarySearch(int[] nums, int value) {
		
		int left = 0;
		int right = nums.length - 1;
		
		while (left <= right) {
			
			int mid = (left + right)>>>1;
			if (value == nums[mid]) {
				return mid;
			}
			if (value < nums[mid]) {
				right = mid - 1;
			} else if (value > nums[mid]) {
				left = mid + 1;
			}
		}
		return -1;
	}
}
