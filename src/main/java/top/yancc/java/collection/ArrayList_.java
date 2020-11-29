package top.yancc.java.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ArrayList_ {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList();
		
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add(3,"7");
		
		
		list.clear();
		list.forEach(System.out::print);
		list.hashCode();
		list.iterator();
		list.toArray();
		
		
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.print(string);
		}
		
		
		
	}

}
