package top.yancc.java.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.mchange.v2.sql.filter.SynchronizedFilterDataSource;

public class Stream_01 {

	public static void main(String[] args) {
		
		// filter
		String[] names = {"lay", "", "The shy"};
		List<String> list = Arrays.asList(names);
		String result = list.stream().filter(name -> !name.isEmpty()).collect(Collectors.joining(", "));
		System.out.println(result);
		
		int count = (int) list.parallelStream().filter(string -> string.isEmpty()).count();
		System.out.println(count);
		
		// forEach + sorted
		Random random = new Random();
		random.ints().limit(3).sorted().forEach(System.out::println);
		
		// map
		List<Integer> numbers_1 = Arrays.asList(2, 4, 2);
		List<Integer> sequenceList = numbers_1.stream().map(i -> i*i).distinct().collect(Collectors.toList());
		sequenceList.forEach(System.out::println);
		
		// IntSummaryStatistics
		List<Integer> numbers = Arrays.asList(3, 2, 2, 4, 5, 9, 1);
		IntSummaryStatistics results = numbers.stream().mapToInt(i -> i).summaryStatistics();
		
		System.out.println("最大数是：" + results.getMax());
		System.out.println("最小数是：" + results.getMin());
		System.out.println("平均数是：" + results.getAverage());
		System.out.println("共和是：" + results.getSum());
		System.out.println("个数：" + results.getCount());
		
		
	}

	
}
