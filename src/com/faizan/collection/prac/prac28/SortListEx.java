package com.faizan.collection.prac.prac28;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.faizan.collection.prac.Employee;

public class SortListEx {
	
	public static List<Employee> getEmployee(){

		return Stream.of(new Employee(4, "faizan", "Dev", 100), new Employee(2, "rehanb", "Dev", 50),
				new Employee(8, "sameer", "Test", 200)).collect(Collectors.toList());
	}
	
	public static void sortList1() {
		getEmployee().stream().sorted(Comparator.comparingInt(emp->((Employee) emp).getSalary()).reversed()).
		forEach(emp->System.out.println(emp));
	}
	public static void sortList2() {
		getEmployee().stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed()).
		forEach(emp->System.out.println(emp));
	}
	public static void sortList3() {
		List<Employee> employee = getEmployee();
		Collections.sort(employee,new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				// TODO Auto-generated method stub
				return o1.getSalary()-o2.getSalary();
			}
		});
	}
	public static void sortList4() {
		List<Employee> employee = getEmployee();
		Collections.sort(employee,( o1,o2  )-> o1.getSalary()-o2.getSalary());
		System.out.println(employee);
	}
	public static void main(String[] args) {
		sortList4();
	}

}
