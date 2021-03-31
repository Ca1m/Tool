package algorithm.sort;


// 希尔排序    
// len = len / 2，
// 找到待插入数据
// 插入排序
public class ShellSort {

	public static void main(String[] args) {
		
		int[] nums = new int[] {5, 11, 3, 1, 6, 2, 7, 9, 4};
		
		nums = shellSort(nums);
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + ",");
		}
	}

	public static int[] shellSort(int[] nums) {
		
		int size = nums.length / 2;
		
		while (size != 0) {  // 当完成 size = 1 的排序之后，退出
			for (int i = 0; i + size < nums.length; i++) { // 控制 size 次数，依次遍历 size = 4 2 1 的不同组别
				for (int j = i + size; j < nums.length; j = j + size) { //  插入排序，选择 带插入元素
					if (nums[j] < nums[j - size]) {    // 判断 待 插入元素是否 需要做 插入
						
						for (int j2 = j; j2 - size >= 0; j2 = j2 - size) { // 插入排序，注意角标从 0  开始
							if (nums[j2] < nums[j2 - size]) {
								swap(nums, j2, j2 - size);
							}
						}
					}
				}
			}
			size /= 2; 
		}
		return nums;
	}
	
	
	public static void swap(int[] nums, int left, int right) {
		int temp = nums[left];
		nums[left] = nums[right];
		nums[right] = temp;
	}
}
