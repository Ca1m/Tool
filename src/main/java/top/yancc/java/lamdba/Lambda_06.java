package top.yancc.java.lamdba;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Lambda_06 {

	public static void main(String[] args) {

		List<Person> javaProgrammers = new ArrayList<Person>() {
			{
				add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
				add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
				add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
				add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
				add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
				add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
			}
		};
		
		System.out.println("所有程序员的姓名：");
		javaProgrammers.forEach(p -> System.out.println(p.getFirstName() +" "+ p.getLastName() +" "+ p.getSalary()));
		
		System.out.println("涨薪之后的工资情况：");
		Consumer<Person> giveRaise = e -> e.setSalary(e.getSalary() / 100 * 5 + e.getSalary());
		javaProgrammers.forEach(giveRaise);
		javaProgrammers.forEach(p -> System.out.println(p.getFirstName() +" "+ p.getLastName() +" "+ p.getSalary()));
		
		System.out.println("工资超过 1400 的员工信息：");
		javaProgrammers.stream().filter(p -> (p.getSalary() > 1400)).forEach(p -> System.out.println(p.getName() + " " + p.getSalary()));
		
		Predicate<Person> ageFilter = p -> p.getAge() > 24;
		Predicate<Person> salaryFilter = p -> p.getSalary() > 1400;
		
		System.out.println("年龄超过 24岁，工资超过 1400 的员工：");
		javaProgrammers.stream().filter(ageFilter).filter(salaryFilter).forEach(p -> System.out.println(p.getName() + " " + p.getSalary()));
		
		
		// 按姓名排序
		List<Person> sortedList = javaProgrammers.stream().sorted((p1, p2) -> (p1.getFirstName().compareTo(p2.getFirstName()))).limit(5).collect(Collectors.toList());
		
		sortedList.forEach(p -> System.out.println(p.getName()));
		
		
		
		
		
		
		
	}
}