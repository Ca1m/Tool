package algorithm.sort;

// 冒泡排序  相邻比较，大的右移
public class BubbleSort {

	public static void main(String[] args) {
		
		int[] nums = new int[] {5, 11, 3, 1, 6, 2, 7, 9, 4};
		nums = bubbleSort(nums);
		
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + ",");
		}
	}
	
	public static int[] bubbleSort(int[] nums) {
		boolean flag = true;
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len - 1 - i; j++) {
				if (nums[j] > nums[j+1]) {
					flag = !flag;  					// 讨巧
					swap(nums, j, j+1);
				}
			}
			if (flag) {
				break;
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
