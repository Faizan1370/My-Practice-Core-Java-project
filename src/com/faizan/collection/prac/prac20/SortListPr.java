package com.faizan.collection.prac.prac20;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.faizan.collection.prac.Employee;

public class SortListPr {
	
	public static List<Employee> getEmployees() {

		return Stream.of(new Employee(4, "faizan", "Dev", 100), new Employee(2, "rehanb", "Dev", 50),
				new Employee(8, "sameer", "Test", 200)).collect(Collectors.toList());
	}
   
	public static void sortList1() {
		getEmployees().stream().sorted(Comparator.comparingInt(emp->emp.getSalary()))
		.forEach(emp->System.out.println(emp));
	}
	
	public static void sortList2() {
		getEmployees().stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed())
		.forEach(emp->System.out.println(emp));
	}
	public static void sortList3() {
		List<Employee> employees = getEmployees();
		Collections.sort(employees, new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getSalary()-o2.getSalary();
			}
			
		});
		System.out.println(employees);
		
	}
	
	public static void main(String[] args) {
		sortList3();
	}

}
