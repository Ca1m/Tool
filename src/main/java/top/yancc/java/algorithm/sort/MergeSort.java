package top.yancc.java.algorithm.sort;

// 归并排序 : 精髓 ： 分为给单个元素之后，单个元素一定是有序的
public class MergeSort {

	public static void main(String[] args) {

		int[] nums = new int[] { 5, 11, 3, 1, 6, 2, 7, 9, 4 };

		//nums = iterationSort(nums);
		nums = recursionSort(nums);
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + ",");
		}
	}

	// 迭代  先分割，再合并 ===》需要一个新的空间 
	public static int[] iterationSort(int[] nums) {
		
		int len = nums.length;
		int[] result = new int[len];
		
		for (int block = 1; block < len*2; block *= 2) {  // 奇数个值
			for (int start = 0; start < len; start += 2 * block) {
				
				int low = start;
				int mid = (start + block) < len ? (start + block) : len;
				int high = (start + 2 * block) < len ? (start + 2 * block) : len;
				// 两个块的  起始角标 及 结束角标
				int start1 = low, end1 = mid;
				int start2 = mid, end2 = high;
				// 开始对两个 block 进行归并排序
				while (start1 < end1 && start2 < end2) {
					result[low++] = nums[start1] < nums[start2] ? nums[start1++] : nums[start2++];
				}
				while (start1 < end1) {
					result[low++] = nums[start1++];
				}
				while (start2 < end2) {
					result[low++] = nums[start2++];
				}
			}
			int[] temp = nums; // 新建对象
			nums = result;
			result = temp;
		}
		return nums;
	}
	
	// 递归
	public static int[] recursionSort(int[] nums) {
		
		return recursionFun(nums, 0, nums.length-1);
	}
	
	public static int[] recursionFun(int[] arr, int left, int right) {
		
		if (left < right) {
			int mid = (right + left) / 2;
			arr = recursionFun(arr, left, mid); // 左部分
			arr = recursionFun(arr, mid + 1, right);
			
			merge(arr, left, mid, right);
		}
		return arr;
	}
	
	public static void merge(int[] arr, int left, int mid, int right) {
		
		int[] a = new int[right - left + 1];
		int i = left;
		int j = mid + 1;
		int k = 0;
		
		while (i <= mid && j <= right) {
			a[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
		}
		while (i <= mid) {
			a[k++] = arr[i++];
		}
		while (j <= mid) {
			a[k++] = arr[j++];
		}
		for (int e = 0; e < k; e++) {
			arr[left++] = a[e];
		}
	}
	
	
	
	public static void swap(int[] nums, int left, int right) {
		int temp = nums[left];
		nums[left] = nums[right];
		nums[right] = temp;
	}
}



