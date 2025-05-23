package com.faizan.collection.prac;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortLis1 {
	
	public static List<Employee> getEmployees(){
		return Stream.of(
				new Employee(4, "faizan", "Dev", 100)
				,new Employee(2, "rehanb", "Dev", 50),
				new Employee(8, "sameer", "Test", 200)).collect(Collectors.toList());
	}
	
	public static void sortListBySal() {
		List<Employee> employees = getEmployees();
		Collections.sort(employees, new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				// TODO Auto-generated method stub
				return o1.getSalary()-o2.getSalary();
			}
		});
		System.out.println(employees);
	}
	
	public static void sortListBySal1() {
		List<Employee> employees = getEmployees();
		Collections.sort(employees,

		(Employee o1, Employee o2)-> {
		
				return o1.getSalary()-o2.getSalary();
			}
		);
		System.out.println(employees);
	}
	
	
	public static void sortListBySal2() {
		List<Employee> employees = getEmployees();
		Collections.sort(employees,(o1,  o2)-> o1.getSalary()-o2.getSalary());
		System.out.println(employees);
	}
	
	public static void sortListBySal3() {
		List<Employee> employees = getEmployees();
		employees.stream().sorted(Comparator.comparingInt(emp->((Employee)emp).getSalary()).reversed())
		.forEach(emp->System.out.println(emp));
	}
	public static void sortListBySal4() {
		List<Employee> employees = getEmployees();
		employees.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed())
		.forEach(emp->System.out.println(emp));
	}
	
	public static void main(String[] args) {
		sortListBySal3();
	}

}
