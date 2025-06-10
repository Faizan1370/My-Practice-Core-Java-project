package com.faizan.java8Prac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pract3 {
	
	public static void maxValue() {
		int[] array= {4,6,7,8,1,56};
		 IntStream stream = Arrays.stream(array);
		 Integer integer = stream.boxed().max(Comparator.comparingInt(val->val.intValue())).get();
		 System.out.println(integer);	
	}
	
	public static void minValue(){
		int[] array= {4,6,7,8,1,56};
		Integer integer = Arrays.stream(array).mapToObj(val->(Integer)val)
		.min(Comparator.comparingInt(val->val.intValue())).get();
		System.out.println(integer);
	}
	
	public static void findEven() {
		List<Integer> list =List.of(3,6,2,9,9);
		list.stream().filter(num->num%2==0).forEach(num->System.out.println(num));
	}
	public static void startWithGivenNumer() {
		int startWithNum =1;
		List<Integer> list =List.of(13,6,12,19,59);
		list.stream().map(num->String.valueOf(num))
		.filter(num->num.startsWith(String.valueOf(startWithNum)))
		.forEach(num->System.out.println(num));
	}
	
	public static void removeDuplicate() {
		List<Integer> list =List.of(13,6,6,19,59);
		list.stream().distinct().forEach(num->System.out.println(num));
	}
	
	public static void findOnlyDuplicate() {
	    List<Integer> list = List.of(13, 6, 6, 19, 59);

	    Set<Integer> seen = new HashSet<>();

	    list.stream()
	        .filter(num -> !seen.add(num)) // If add() returns false, it's a duplicate
	        .distinct() // Optional: to avoid printing the same duplicate multiple times
	        .forEach(System.out::println);
	}
	
	public static void countElement() {
		 List<Integer> list = List.of(13, 6, 6, 19, 59);
		 long count = list.stream().count();
		 System.out.println(count);
	}
	
	public static void firstNonRepeatingChar() {
		String str="faizan";
		Character character = str.chars().mapToObj(c->(char)c)
		.filter(c->str.indexOf(c)==str.lastIndexOf(c))
		.findFirst()
		.get();
		System.out.println(character);
		
		}
	
	
	public static void firstNonRepeatingChar1() {
		String str="faizan";
		 Character character = str.chars().mapToObj(c->(char)c)
		.collect(Collectors.
	  groupingBy(Function.identity(),LinkedHashMap::new ,Collectors.counting()))
		.entrySet()
		.stream()
		.filter(entry->entry.getValue()==1L)
		.map(entry->entry.getKey())
		.findFirst()
		.get();
		 System.out.println(character);
		
		
		
	}
	
	public static void findFirstRepeatingChar() {
		String str="faizan";
		Character character = str.chars().mapToObj(c->(char)c)
		.filter(c->str.indexOf(c)!=str.lastIndexOf(c))
		.findFirst()
		.get();
		System.out.println(character);
	}
	
	public static void findFirstrepeatingchar1() {
		String str="faizan";
		Character character = str.chars()
		.mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),
				LinkedHashMap::new ,Collectors.counting()))
		.entrySet()
		.stream()
		.filter(enrty->enrty.getValue()>1L)
		.map(entry->entry.getKey())
		.findFirst()
		.get();
		System.out.println(character);
	}
	
	public static void sortElement() {
		List<Integer> list = List.of(3,6,2,9,5,7);
		list.stream().sorted().forEach(num->System.out.println(num));
	}
	public static void reverseSortElement() {
		List<Integer> list = List.of(3,6,2,9,5,7);
		list.stream().sorted(Collections.reverseOrder()).forEach(num->System.out.println(num));
	}
	
	public static void reverseSortElement1() {
		List<Integer> list = List.of(3,6,2,9,5,7);
		list.stream().sorted(Comparator.reverseOrder()).forEach(num->System.out.println(num));
	}
	
	public static void findElementCubeGreaterThanTarget() {
		int targetElement=50;
		List<Integer> list = List.of(3,6,2,9,5,7);
		list.stream().filter(num->num*num*num>targetElement)
		.forEach(num->System.out.println(num));
	}
	public static void countChar() {
		String str="hello";
		str.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet()
		.stream()
		.forEach(entry->System.out.println(entry));
	}
	
	public static void findOnylDeuplicateElementWithCount() {
		List<String> list =Arrays.asList("AA","BB","CC","AA");
		list.stream().filter(str->Collections.frequency(list, str)>1)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet()
		.stream()
		.forEach(entry->System.out.println(entry));
		
	}
	
	public static void findOnylDeuplicateElementWithCount1() {
		List<String> list =Arrays.asList("AA","BB","CC","AA");
		list.stream()
		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
		.entrySet()
		.stream()
		.filter(entry->entry.getValue()>1L)
		.forEach(entry->System.out.println(entry));
		
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
	 
	 public static void groupByDept() {
		 list.stream().collect(Collectors.groupingBy(emp->emp.getDesignation()))
		 .entrySet()
		 .stream()
		 .forEach(entry->System.out.println(entry));
	 }
	 
	 public static void groupByDepWithCount() {
		 list.stream().collect(Collectors.groupingBy(emp->emp.getDesignation(),Collectors.counting()))
		 .entrySet()
		 .stream()
		 .forEach(entry->System.out.println(entry));
	 }

	 public static void groupByDepWithHieghestSal() {
		 list.stream()
		 .collect(Collectors.groupingBy(Employee::getDesignation ,Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))))
		 .entrySet()
		 .stream()
		 .forEach(enrty->System.out.println(enrty));
	
		 
	 }
	 
	 public static void findKthHiesetSal() {
		 int k=2;
		 Employee employee = list.stream()
		 .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
		 .skip(k-1)
		 .findFirst()
		 .get();
		 System.out.println(employee);
	 }
	 
	 public static void completableFuture() {
		 CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
			System.out.println("Run Async"); 
			//int i=10/0;
		 }).exceptionally(ex->{
			 System.out.println("exception :"+ex.getMessage());
			 return null;
		 });
		 completableFuture.join();
	 }
	 
	 public static void compSupplyAs() {
		 ExecutorService executorService = Executors.newFixedThreadPool(1);  
		 CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
			 return "Suuply Async";
		 },executorService)
				 .thenApply(str->str.toUpperCase())
				 .thenApply(str->str.substring(0,6))
				 .exceptionally(ex->{
			 System.out.println(ex.getMessage());
			 return null;
		 });
	
		 System.out.println(completableFuture.join());
		 executorService.shutdown();
	 }
	 
	 public static void combineTwoPredicate() {
		 
		 Predicate<String> predicate = (str)->str.startsWith("A");
		 Predicate<String> predicate1 = (str)->str.startsWith("A");
		 
		Predicate<String> predicate2 = predicate.and(predicate1);
		System.out.println(predicate2.test("Apple"));
	 }
	 
	 public static void combinedTwoConsumr() {
		 Consumer<String> consumer =(str)->System.out.println(str.toLowerCase());
		 Consumer<String> consumer1 =(str)->System.out.println(str.toLowerCase());
		 
		 Consumer<String> consumer2 = consumer.andThen(consumer1);
		 consumer2.accept("faizan");
	 }
	 
	 public static void combineTwoSupplier() {
		 Supplier<String> supplier =()->"hello bro";
		 Supplier<String> supplier2 =()->"How are you?";
		 
		 Supplier<String> supplier3 = ()->supplier.get() +" "+supplier2.get();
		 System.out.println(supplier3.get());
	 }
	 
	 public static void combineTwoFunction() {
		 Function<Integer, Integer> function =(i)->i*3;
		 Function<Integer, Integer> function2 =(x)->x+10;
		 
		 Function<Integer, Integer> function3 = function.andThen(function2);
		 System.out.println(function3.apply(2));
	 }
	 
	 public static void covertInMap() {
		 List<String> list =List.of("abc","abrt");
		 Map<String, Integer> collect = list.stream()
		 .collect(Collectors.toMap(val->val, val->val.length()));
		 System.out.println(collect);
	 }
	 
	 public static void op() {
		 String str=null;
		 Optional<String> optional = Optional.of(str);
		 System.out.println(optional.get());
	 }
	 public static void op1() {
		 Optional<String> optional = Optional.empty();
		 System.out.println(optional.get());
	 }
	 
	 public static void op3() {
		 Optional<String> optional = Optional.ofNullable(null);
		 System.out.println(optional.orElse("Default value"));
	 }
	 
	 public static void reduceConcat() {
		    List<String> names = List.of("Alice", "Bob", "Charlie");

		    String result = names.stream()
		                         .reduce("", (a, b) -> a + b + " ");

		    System.out.println(result.trim()); // Output: Alice Bob Charlie
		}
	 
	 public static void sum() {
		 List<Integer> list = List.of(2,4,9,5);
		 Integer reduce = list.stream().reduce(0,(a,b)->a+b);
		 list.stream().mapToInt(num->num.intValue()).sum();
		 System.out.println(reduce);
	 }
	 
	 public static void findSumSal() {
		 Integer collect = list.stream().collect(Collectors.summingInt(Employee::getSalary));
		 System.out.println(collect);
	 }

	 

	
	
	
	public static void main(String[] args) {
		findSumSal();
		//sum();
		//op3();
		//covertInMap();
		//combineTwoFunction();
		//combineTwoSupplier();
		//combinedTwoConsumr();
		//combineTwoPredicate();
		//completableFuture();
		//compSupplyAs();
		//maxValue();
		//minValue();
		//findEven();
	//	startWithGivenNumer();
		//removeDuplicate();
		//findOnlyDuplicate();
		//countElement();
		//firstNonRepeatingChar1();
		//findFirstrepeatingchar1();
		//sortElement();
		//reverseSortElement();
		//reverseSortElement1();
		//findElementCubeGreaterThanTarget();
		//countChar();
		//findOnylDeuplicateElementWithCount();
		//groupByDept();
		//groupByDepWithCount();
		//groupByDepWithHieghestSal();
		//findKthHiesetSal();
	
	}

}
