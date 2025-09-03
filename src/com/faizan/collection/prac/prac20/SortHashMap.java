package com.faizan.collection.prac.prac20;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.faizan.collection.prac.Employee;

public class SortHashMap {
	
	public static Map<Employee, Integer> getMapValues() {
		Map<Employee, Integer> map = new HashMap<Employee, Integer>();
		map.put(new Employee(4, "faizan", "Dev", 100), 67);
		map.put(new Employee(8, "sameer", "Test", 200), 23);
		map.put(new Employee(2, "rehan", "Dev", 50), 99);
		return map;
	}
	
	
	public static void sortMap1() {
		getMapValues().entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		.forEach(entry->System.out.println(entry));
	}
	

	public static void sortMap2() {
		getMapValues().entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparingInt(Employee::getSalary).reversed()))
		.forEach(entry->System.out.println(entry));
	}
	
	public static void sortMap3() {
		ArrayList<Map.Entry<Employee,Integer>> list = new ArrayList<Map.Entry<Employee,Integer>>(getMapValues().entrySet());
		Collections.sort(list, (o1, o2)-> 
				 o1.getValue()-o2.getValue()
		);
		System.out.println(list);
	}
	
	
	public static void main(String[] args) {
		sortMap3();
	}

}
