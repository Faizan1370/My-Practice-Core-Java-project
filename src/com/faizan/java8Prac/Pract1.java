package com.faizan.java8Prac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Pract1 {
	 
	public static void findMaxNumber() {
		List<Integer> list = Arrays.asList(1,5,7,3,9);
		
		Integer integer = list.stream().max(Comparator.comparingInt(num->num.intValue())).get();
		System.out.println(integer);
		
		Integer min = list.stream().min(Comparator.comparingInt(num->num.intValue())).get();
		System.out.println(min);
	}
	
	 public static void findOnlyDuplicateElementsWithCount() {
		   List<String> list =Arrays.asList("AA","BB","CC","AA");
		   list.stream().filter(word->Collections.frequency(list, word)>1)
		   .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		   .entrySet()
		   .stream()
		   .forEach(entry->System.out.println(entry));
		   
		   
	 }
	 
	  public static void convertIntoMap() {
			 // List<String> list =Arrays.asList("abc","abrt");
			  List<String> list =List.of("abc","abrt");
			  Map<String, Integer> collect = list.stream().collect(Collectors.toMap(value->value, value->value.length()));
			  
	  }
	  
	  
	  static List<Employee> list =new ArrayList<>();
		 static{
			 Employee emp= new Employee(100, "Faizan", "Software Engineer",100);
			 Employee emp1= new Employee(700, "Arun", "QA tester",500);
			 Employee emp2= new Employee(400, "Minhaz", "C++ developer",300);
			 Employee emp3= new Employee(300, "Kamlesh", "Software Engineer",700);
			 list.add(emp);
			 list.add(emp1);
			 list.add(emp2);
			 list.add(emp3);
			
		}
		 
		 public static void hieghestSalByDept() {
			 list.stream().collect(Collectors.groupingBy(emp->emp.getDesignation(),
					 Collectors.maxBy(Comparator.comparing(emp->emp.getSalary()))))
			 .entrySet()
			 .stream()
			 .forEach(entry->System.out.println(entry));
		 }
		 
		 public static void findKthHiesetSal1() {
			 list.stream().collect(Collectors.groupingBy(emp->emp.getDesignation(),
					 Collectors.maxBy(Comparator.comparing(Employee::getSalary).reversed())))
			 .entrySet()
			 .stream()
			 .skip(2)
			 .forEach(entry->System.out.println(entry));
		 }
	
	public static void main(String[] args) {
		//findMaxNumber();
		//findOnlyDuplicateElementsWithCount();
		//hieghestSalByDept();
		findKthHiesetSal1();
	}

}
