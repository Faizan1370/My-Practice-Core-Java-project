package com.faizan.collection.prac.prac29;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.faizan.collection.prac.Employee;

public class SList {
	public static List<Employee> getEmployee(){

		return Stream.of(new Employee(4, "faizan", "Dev", 100), new Employee(2, "rehanb", "Dev", 50),
				new Employee(8, "sameer", "Test", 200)).collect(Collectors.toList());
	}
	public void sortList() {
		getEmployee().stream().sorted(Comparator.comparingInt(Employee:: getSalary).reversed())
		.forEach(emp->System.out.println(emp));
	}
	public void sortList2() {
		List<Employee> employee = getEmployee();
		Collections.sort(employee,( o1,  o2)-> o1.getSalary()-o2.getSalary());
	}
	

}
