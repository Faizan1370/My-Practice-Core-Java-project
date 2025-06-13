package com.faizan.collection.prac.prac4;

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
	
	public static void sortList() {
		getEmployees().stream().sorted(Comparator.comparingInt(emp->emp.getSalary()))
		.forEach(emp->System.out.println(emp));
	}
	
	public static void sortList1() {
		getEmployees().stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed()).forEach(emp->System.out.println(emp));
	}
	
	public static void sortList2() {
		getEmployees().stream().sorted(Comparator.comparingInt(emp->((Employee)emp).getSalary()).reversed()).forEach(emp->System.out.println(emp));
	}
	
	public static void sortList3() {
		List<Employee> list = getEmployees();
		Collections.sort(list, new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				// TODO Auto-generated method stub
				return o1.getSalary()-o2.getSalary();
			}
		});
		System.out.println(list);
	}
	
	public static void sortList4() {
		List<Employee> list = getEmployees();
		Collections.sort(list,

			(Employee o1, Employee o2)-> {
				// TODO Auto-generated method stub
				return o1.getSalary()-o2.getSalary();
			
		});
		System.out.println(list);
	}
	
	public static void sortList5() {
		List<Employee> list = getEmployees();
		Collections.sort(list,

			(o1,  o2)-> 
				// TODO Auto-generated method stub
				 o1.getSalary()-o2.getSalary()
			
		);
		System.out.println(list);
	}
	
	public static void main(String[] args) {
		sortList5();
	}
}
