package com.faizan.collection.prac.prac1;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.faizan.collection.prac.Employee;

public class SortList {
	
	public static List<Employee> getEmployees(){
		
		return Stream.of(
				new Employee(4, "faizan", "Dev", 100)
				,new Employee(2, "rehanb", "Dev", 50),
				new Employee(8, "sameer", "Test", 200))
				.collect(Collectors.toList());
	}
	
	public static void sortListBySal() {
		List<Employee> list = getEmployees();
		Collections.sort(list, new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				
				return o1.getSalary()-o2.getSalary();
			}
		});
		System.out.println(list);
	}
	
	public static void sortListBySal1() {
		List<Employee> list = getEmployees();
		Collections.sort(list,(Employee o1, Employee o2)-> {
				
				return o1.getSalary()-o2.getSalary();
			});
		System.out.println(list);
	}
	
	public static void sortListBySal2() {
		List<Employee> list = getEmployees();
		Collections.sort(list,(o1, o2)-> 
				 o1.getSalary()-o2.getSalary());
		System.out.println(list);
	}
	
	public static void sortListBySal3() {
		List<Employee> list = getEmployees();
		//list.stream().sorted(Comparator.comparing(emp->((Employee) emp).getSalary()).reversed()).forEach(emp->System.out.println(emp));
		list.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).forEach(emp->System.out.println(emp));
		//System.out.println(list);
	}
	
	
	
	public static void main(String[] args) {
     sortListBySal3();	
	}

}
