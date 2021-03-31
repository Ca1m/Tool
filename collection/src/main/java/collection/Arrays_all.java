package collection;

import java.util.Arrays;
import java.util.Comparator;

public class Arrays_all {
	public static void main(String[] args) {
		
		String[] players = {"Bouboo", "Alex", "City"};
		// 比较器
		Comparator<String> comparator = new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
			}
		};
		// 排序
		Arrays.sort(players, comparator);
		
		System.out.println(Arrays.toString(players));
		// [Alex, Bouboo, City]
	}
}
