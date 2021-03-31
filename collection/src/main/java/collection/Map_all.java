package collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Map_all {

	private static Map<String, String> map = new HashMap<>();

	public static void main(String[] args) {
		test_Map();
	}
	// 遍历Map
	public static void test_Map() {

		map.put("1", "请赐予我平静");
		map.put("2", "去接受我无法改变的");
		map.put("3", "给予我勇气");
		map.put("4", "去改变我能改变的");
		map.put("5", "赐我智慧");
		map.put("6", "分辨这两者的区别");

		System.out.println("遍历 key : 通过  key 获取 key + value");
		for (String key : map.keySet()) {
			System.out.println(key +" : "+ map.get(key));
		}
		
		System.out.println("\n遍历  values: ");
		for (String value : map.values()) {
			System.out.println(value);
		}

		System.out.println("\nentrySet : 获取 key + value");
		for (Entry<String, String> entry: map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		System.out.println("\nentrySet : 通过 Iterator 遍历 key + value ");
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			System.out.println(entry.getKey() +" : " + entry.getValue());
		}
	}
}
