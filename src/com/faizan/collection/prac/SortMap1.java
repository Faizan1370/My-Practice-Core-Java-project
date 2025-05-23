package com.faizan.collection.prac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortMap1 {
	
	public static Map<Employee,Integer> getMapValues(){
		Map<Employee,Integer> map = new HashMap<Employee, Integer>();
		map.put(new Employee(4, "faizan", "Dev", 100), 67);
		map.put(new Employee(8, "sameer", "Test", 200), 23);
		map.put(new Employee(2, "rehan", "Dev", 50), 99);
		return map;
	 }
	
	public static void sortMap() {
		Map<Employee, Integer> mapValues = getMapValues();
		mapValues.entrySet().stream().sorted(Map.Entry.comparingByValue()).
		forEach(entry->System.out.println(entry));
	}
	
	public static void sortMap1() {
		Map<Employee, Integer> mapValues = getMapValues();
		mapValues.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparing(emp->emp.getSalary()))).
		forEach(entry->System.out.println(entry));
	}
	
	public static void sortMap2() {
		Map<Employee, Integer> mapValues = getMapValues();
		mapValues.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparing(Employee::getSalary).reversed())).
		forEach(entry->System.out.println(entry));
	}
	
	public static void sortMap3() {
		List<Entry<Employee,Integer>> list =new ArrayList<Map.Entry<Employee,Integer>>(getMapValues().entrySet());
		
		Collections.sort(list, new Comparator<Entry<Employee,Integer>>() {

			@Override
			public int compare(Entry<Employee, Integer> o1, Entry<Employee, Integer> o2) {
				// TODO Auto-generated method stub
				return o1.getKey().getSalary()-o2.getKey().getSalary();
			}
		});
		
		System.out.println(list);
		
	}
	
	public static void main(String[] args) {
		sortMap3();
	}

}
