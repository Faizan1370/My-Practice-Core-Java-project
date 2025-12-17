package com.faizan.collection.prac.prac27;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.faizan.collection.prac.Employee;

public class SortMapBB {
	public static Map<Employee, Integer> getMapValues() {
		Map<Employee, Integer> map = new HashMap<Employee, Integer>();
		map.put(new Employee(4, "faizan", "Dev", 100), 67);
		map.put(new Employee(8, "sameer", "Test", 200), 23);
		map.put(new Employee(2, "rehan", "Dev", 50), 99);
		return map;
	}
	
	public static void sort1() {
		getMapValues().entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparingInt(emp->((Employee) emp).getSalary()).reversed()))
		.forEach(entry->System.out.println(entry));
	}
	public static void sort2() {
		List<Map.Entry<Employee, Integer>> list = new ArrayList<Map.Entry<Employee,Integer>>(getMapValues().entrySet());
		
		Collections.sort(list, new Comparator<Map.Entry<Employee, Integer>>() {

			@Override
			public int compare(Entry<Employee, Integer> o1, Entry<Employee, Integer> o2) {
				return o1.getValue()-o2.getValue();
			}
		});
		System.out.println(list);
	}
  public static void main(String[] args) {
	sort2();
}

}
