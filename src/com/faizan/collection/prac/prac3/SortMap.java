package com.faizan.collection.prac.prac3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.faizan.collection.prac.Employee;

public class SortMap {
	
	 public static Map<Employee,Integer> getMapValues(){
			Map<Employee,Integer> map = new HashMap<Employee, Integer>();
			map.put(new Employee(4, "faizan", "Dev", 100), 67);
			map.put(new Employee(8, "sameer", "Test", 200), 23);
			map.put(new Employee(2, "rehan", "Dev", 50), 99);
			return map;
		 }
	 
	 public static void sortMap() {
		 getMapValues().entrySet().stream()
		 .sorted(Map.Entry.comparingByValue())
		 .forEach(emp->System.out.println(emp));
	 }
	 
	 public static void sortMap1() {
		 getMapValues().entrySet().stream()
		 .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		 .forEach(emp->System.out.println(emp));
	 }
	 
	 public static void sortMap2() {
		 getMapValues().entrySet().stream()
		 .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(Employee::getSalary)))
		 .forEach(emp->System.out.println(emp));
	 }
	 
	 public static void sortMap3() {
		 getMapValues().entrySet().stream()
		 .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(Employee::getSalary).reversed()))
		 .forEach(emp->System.out.println(emp));
	 }
	 public static void sortMap4() {
		 List<Entry<Employee,Integer>> list = new ArrayList<>(getMapValues().entrySet());
		 
		 Collections.sort(list, new Comparator<Entry<Employee, Integer>>() {

			@Override
			public int compare(Entry<Employee, Integer> o1, Entry<Employee, Integer> o2) {
				// TODO Auto-generated method stub
				return o1.getValue()-o2.getValue();
			}
		});
		 System.out.println(list);
	 }
	 
	 public static void sortMap5() {
		 List<Entry<Employee,Integer>> list = new ArrayList<>(getMapValues().entrySet());
		 
		 Collections.sort(list,(Entry<Employee, Integer> o1, Entry<Employee, Integer> o2)-> {
				// TODO Auto-generated method stub
				return o1.getValue()-o2.getValue();
			
		});
		 System.out.println(list);
	 }
	 
	 public static void sortMap6() {
		 List<Entry<Employee,Integer>> list = new ArrayList<>(getMapValues().entrySet());
		 
		 Collections.sort(list,(o1, o2)-> o1.getValue()-o2.getValue());
		 System.out.println(list);
	 }
	 
	 public static void sortMap7() {
		 List<Entry<Employee,Integer>> list = new ArrayList<>(getMapValues().entrySet());
		 
		 Collections.sort(list,(o1, o2)-> o1.getKey().getSalary()-o2.getKey().getSalary());
		 System.out.println(list);
	 }
	 
	 
	 public static void main(String[] args) {
		sortMap7();
	}

}
