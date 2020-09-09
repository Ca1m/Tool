package top.yancc.java.lamdba;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Lambda_04 {

	public static void main(String[] args) {
		
		// List 遍历
		String[] atp = {"Rafael", "Djokovic", "Wawrinka" };
		List<String> players = Arrays.asList(atp);
		// forEach 
		for (String player : players) { 
			System.out.println(player + "; ");
		}
		// forEach + Lambda
		players.forEach(player -> System.out.println(player + "; "));
		
		players.forEach(System.out::println);
		
		// Map 遍历
		Map<String, String> map = new HashMap<>();
		map.put("1", "Rafael");
		map.put("2", "Djokovic");
		// forEach
		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "," + entry.getValue());
		}
		// forEach + Lambda
		map.forEach((K, V) -> System.out.println(K + ";" + V));
		
	}
}

