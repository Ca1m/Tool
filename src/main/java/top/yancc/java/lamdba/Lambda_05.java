package top.yancc.java.lamdba;

import java.util.Arrays;
import java.util.Comparator;

// 使用 Lambda 排序集合
public class Lambda_05 {

	public static void main(String[] args) {
		
		String[] players = {"c", "a", "b"};
		// 正常排序
		Arrays.sort(players, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
			}
		});
		// Lambda 排序 ： 一共有三种方式
		// 关于返回值的问题有两种实现方式
		Comparator<String> comparator = (s1, s2) -> (s1.compareTo(s2)); // 1. 不指定返回值
		//Comparator<String> comparator = (s1, s2) -> { return s1.compareTo(s2); }; // 2. 指定返回值
		Arrays.sort(players, comparator);
		
		// Lambda 最简洁的方式
		//Arrays.sort(players, (s1, s2) -> s1.compareTo(s2)); 

		
		// 输出显示
		for (String player : players) {
			System.out.print(player + ",");
		}
	}
}
