package com.faizan.java8Prac;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Pract39 {
	
	public static void combineTwoPredicate() {
		Predicate<String> predicate = (str)->str.startsWith("A");
		Predicate<String> predicate2 = (str)->str.startsWith("h");
		Predicate<String> and = predicate.and(predicate2);
		System.out.println(and.test("App"));
	}
	public static void combineTwoConsumer() {
		Consumer<String> consumer =(str)->System.out.println(str);
		Consumer<String> consumer2 =(str)->System.out.println(str);
		Consumer<String> andThen = consumer.andThen(consumer2);
		andThen.accept("hi");
	}
	
	public static void compineTwoSuppplier() {
		Supplier<String> supplier =()->"hi";
		Supplier<String> supplier2 =()->"bro";
		Supplier<String> supplier3 =()-> supplier.get() +" "+supplier2.get();
		
		System.out.println(supplier3.get());
	}
	public static void combineTwoFunction() {
		Function<Integer, Integer> function =(x)->x+2;
		Function<Integer, Integer> function2 =(x)->x+3;
		Function<Integer, Integer> andThen = function.andThen(function2);
		System.out.println(andThen.apply(4));
	}
	static List<Employee> list = new ArrayList<>();
	static {
		Employee emp = new Employee(100, null, "Software Engineer", 100);
		Employee emp1 = new Employee(700, "Arun", "QA tester", 500);
		Employee emp2 = new Employee(400, "Minhaz", "C++ developer", 300);
		Employee emp3 = new Employee(300, "Kamlesh", "Software Engineer", 700);
		list.add(emp);
		list.add(emp1);
		list.add(emp2);
		list.add(emp3);
	}
	public static double avgSal() {
		double asDouble = list.stream().filter(emp->emp.getDesignation().contains("Soft"))
		.map(emp->emp.getSalary()).mapToDouble(sal->sal.doubleValue()).average().getAsDouble();
		return asDouble;
	}
	public static void groupByDepWithHieghestSal() {
		list.stream().collect(Collectors.groupingBy(emp->emp.getDesignation(),
				Collectors.maxBy(Comparator.comparingInt(emp->((Employee) emp).getSalary()).reversed())))
		.entrySet().stream().forEach(entry->System.err.println(entry));
	}
	public static void main(String[] args) {
		groupByDepWithHieghestSal();
	}

}
