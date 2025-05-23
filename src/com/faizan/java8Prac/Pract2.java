package com.faizan.java8Prac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Pract2 {
	
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
	 
	 public static void groupBydept() {
		list.stream().collect(Collectors.groupingBy(emp->emp.getDesignation()))
		.entrySet()
		.stream()
		.forEach(enty->System.out.println(enty)); 
	 }
	 
	 public static void grooupBydeptHietSal() {
		 list.stream().collect(Collectors.groupingBy(emp->emp.getDesignation(),
				 Collectors.maxBy(Comparator.comparingInt(emp->emp.getSalary()))))
		 .entrySet()
		 .stream()
		 .forEach(entry->System.out.println(entry));
	     
	 }
	 
	 public static void kthHiestSal() {
		 int k=1;
		 Employee employee = list.stream().sorted(Comparator.comparing(emp->((Employee) emp).getSalary()).reversed())
		 .skip(k)
		 .findFirst()
		 .get();
		 System.out.println(employee);
	 }
	 
	  public static void findOnlyDuplicateElementsWithCount() {
		   List<String> list =Arrays.asList("AA","BB","CC","AA");
		   
		   list.stream().filter(word->Collections.frequency(list, word)>1)
           .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
           .entrySet()
           .stream()
           .forEach(entry->System.out.println(entry));
		   
	  }
	 
	 public static void main(String[] args) {
		//groupBydept();
		 //grooupBydeptHietSal();
		// kthHiestSal();
		 findOnlyDuplicateElementsWithCount();
	}

}
