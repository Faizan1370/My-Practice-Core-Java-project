package com.faizan.collection.prac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortList {
	
	public static List<Employee> getEmployees(){
		return Stream.of(
				new Employee(4, "faizan", "Dev", 100)
				,new Employee(2, "rehanb", "Dev", 50),
				new Employee(8, "sameer", "Test", 200)).collect(Collectors.toList());
	}
	
	public static void sortListOfIntgers() {
		//List<Integer> list = List.of(2,5,7,8,2);// Immutable list but so cant sort or use stream beucase it returns new list
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(4);
		list.add(0);
		Collections.sort(list);
		System.out.println(list);
	}
	
	public static void sortListEmmployeeOnSal() {
		List<Employee> sortedList = getEmployees();
		Collections.sort(sortedList,new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				
				return o1.getSalary()-o2.getSalary();
			}
		});
		System.out.println(sortedList);
	}
	
	public static void sortListEmmployeeOnSal1() {
		List<Employee> sortedList = getEmployees();
		Collections.sort(sortedList,(Employee o1, Employee o2) ->{
				
				return o1.getSalary()-o2.getSalary();
			}
		);
		System.out.println(sortedList);
	}
	public static void sortListEmmployeeOnSal2() {
		List<Employee> sortedList = getEmployees();
		Collections.sort(sortedList,(o1, o2) -> o1.getSalary()-o2.getSalary());
		System.out.println(sortedList);
	}
	public static void sortListEmmployeeOnSal3() {
	 List<Employee> collect = getEmployees().stream().sorted((o1, o2) -> o1.getSalary()-o2.getSalary()).collect(Collectors.toList());
	 System.out.println(collect);
	}
	
	public static void sortListEmmployeeOnSal4() {
		 List<Employee> collect = getEmployees().stream().sorted(Comparator.comparing(emp->emp.getSalary())).collect(Collectors.toList());
		 System.out.println(collect);
		}
	
	public static void sortListEmmployeeOnSal5() {
		 List<Employee> collect = getEmployees().stream().sorted(Comparator.
				 comparingInt(Employee::getSalary)).collect(Collectors.toList()); //or can use comparing with emp->emp.getSalary()
		 System.out.println(collect);
		}
		
	
	
	public static void main(String[] args) {
		//sortListOfIntgers();
		sortListEmmployeeOnSal5();
	}

}
