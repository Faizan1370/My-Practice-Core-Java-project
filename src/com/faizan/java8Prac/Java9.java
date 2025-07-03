package com.faizan.java8Prac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.faizan.collection.prac.prac6.ConsureentMapProb;

public class Java9 {
	
	public static void evenNumber() {
		List<Integer> list =Arrays.asList(2,1,3,5,7,8);
		list.stream().filter(num->num%2==0)
		.forEach(num->System.out.println(num));
		}
	
	public static void firstNonRepeatingChar() {
		String str="faizan";
		Character character = str.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
		.entrySet()
		.stream()
		.filter(entry->entry.getValue()==1L)
		.map(entry->entry.getKey())
		.findFirst()
		.get()
		;
		
		System.out.println(character);
		
	}
	public static void firstNonRepeatingChar1() {
		String str="faizan";
		Character character = str.chars().mapToObj(c->(char)c)
		.filter(ch->str.indexOf(ch)==str.lastIndexOf(ch))
		.findFirst()
		.get();
		System.out.println(character);
	}
	
	public static void firstRepeatingChar() {
		String str="faizan";
		Character character = str.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
		.entrySet()
		.stream()
		.filter(entry->entry.getValue()>1L)
		.map(entry->entry.getKey())
		.findFirst()
		.get()
		;
		
		System.out.println(character);
		
	}
	public static void firstRepeatingChar1() {
		String str="faizan";
		Character character = str.chars().mapToObj(c->(char)c)
		.filter(ch->str.indexOf(ch)!=str.lastIndexOf(ch))
		.findFirst()
		.get();
		System.out.println(character);
	}
	public static void sortElement() {
		List<Integer> list = Arrays.asList(5,7,3,89,23,54);
		list.stream().sorted(Comparator.reverseOrder()).forEach(num->System.out.println(num));
		
	}
	public static void mergerTwoStream() {
		List<Integer> list1 =Arrays.asList(4,7,2,10,8,5,10,20,40);
		List<Integer> list2 =Arrays.asList(23,56,45,90);
		 Stream<Integer> concat = Stream.concat(list1.stream(),list2.stream());
	}
	public static void cubeElemenetGreaterThanValue() {
		 List<Integer> list =Arrays.asList(1,7,8,2);
		 
		 list.stream().map(num->num*num*num).filter(num->num>50)
		 .forEach(num->System.out.println(num));
		 
		 list.stream().filter(num->num*num*num>50)
		 .forEach(num->System.out.println(num));
		 
	}
	 public static void countWordListString() {
		   
		   List<String> list =Arrays.asList("AA","BB","CC","AA");
		   list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		   .entrySet()
		   .stream()
		   .forEach(entry->System.out.println(entry));
		   
	 }
	 public static void maxElement() {
		  List<Integer> list =Arrays.asList(4,7,3,7,10,19);
		  
		  Integer integer = list.stream().max(Comparator.comparingInt(num->num.intValue()))
		  .get();
		  System.out.println(integer);
		  
	 }
	 public static void minElement() {
		  List<Integer> list =Arrays.asList(4,7,3,7,10,19);
		  
		  Integer integer = list.stream().min(Comparator.comparingInt(num->num.intValue()))
		  .get();
		  System.out.println(integer);
		  
	 }
	 public static void findOnlyDuplicateElementsWithCount() {
		   List<String> list =Arrays.asList("AA","BB","CC","AA");
		   list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		   .entrySet()
		   .stream()
		   .filter(entry->entry.getValue()>1L)
		   .forEach(entry->System.out.println(entry));
		   
	 }
	 public static void findOnlyDuplicateElementsWithCount1() {
		   List<String> list =Arrays.asList("AA","BB","CC","AA");
		   list.stream().filter(word->Collections.frequency(list, word)>1).
		   collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
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
	 public static void groupByDept() {
		 list.stream().collect(Collectors.groupingBy(emp->emp.getDesignation()))
		 .entrySet()
		 .stream()
		 .forEach(entry->System.out.println(entry));
	 }
	 public static void groupByDepWithHieghestSal() {
		 list.stream().collect(Collectors.groupingBy(emp->emp.getDesignation(),Collectors.maxBy(Comparator.comparingInt(emp->emp.getSalary()))))
		 .entrySet()
		 .stream()
		 .forEach(entry->System.out.println(entry)); 
	 }
	 public static void kthHiehestSal() {
		 int k=2;
		 
		 Employee employee = list.stream()
		 .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
		 .skip(k-1)
		 .findFirst()
		 .get();
		 System.out.println(employee);
		 
		 
	 }
	 public static void convertStreamToIntStream() {
		 List<Integer> list = List.of(4,7,5,9);
		 IntStream mapToInt = list.stream().mapToInt(num->num.intValue());
		 
	 }
	 public static void convertArrayToStream() {
		 int[] array = {5,7,7,9,3,6};
		 Stream<Integer> mapToObj = Arrays.stream(array)
		 .mapToObj(num->(Integer)num);
	 }
	 public static void converIntStreamToStream() {
		 IntStream stre= IntStream.of(3,5,7,1,8);
		 Stream<Integer> mapToObj = stre.mapToObj(num->(Integer)num);
	 }
	 public static void completableFuture() {
		 ExecutorService executorService = Executors.newFixedThreadPool(1);
		 CompletableFuture<Void> completableFuture =CompletableFuture.runAsync(()->{
			 System.out.println("hello");
		 },executorService).exceptionally((ex)->{
			 System.out.println(ex.getMessage());
			return null; 
		 });
		completableFuture.join();
		executorService.shutdown();
	 }
	 
	 public static void completablFutureSuppluAsync() {
		 ExecutorService  executorService= Executors.newFixedThreadPool(1);
		 CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
			 return "hello bro";
		 },executorService).thenApply(str->str.toUpperCase()).exceptionally((ex)->{
			
			 return null;
		 });
		 System.out.println(completableFuture.join());
		 executorService.shutdown();
	 }
	 public static void combineTwoPredicate() {
		 Predicate<String> predicate =(str)->str.startsWith("A");
		 Predicate<String> predicate2 = (str)->str.startsWith("A");
		 Predicate<String> and = predicate.and(predicate2);
	 }
	 
	 public static void combineTwoSupplier() {
		 Supplier<String> supplier =()->"Hi bro";
		 Supplier<String> supplier2 =()->"How are you?";
		 
		 Supplier<String> supplier3 =()-> supplier.get() +" "+supplier2.get();
		System.out.println(supplier3.get()); ;
	 }
	 
	 public static void combineTwoConsumer() {
		 Consumer<String> consumer =(str)->System.out.println(str);
		 Consumer<String> consumer2 = (str)->System.out.println(str);
		 Consumer<String> andThen = consumer.andThen(consumer2);
		 andThen.accept("hi");
	 }
	 
	 public static void combineTwoFunction() {
		 Function<Integer, Integer> function =(num)->num*3;
		 Function<Integer, Integer> function2=(x)->x+10;
		 Function<Integer, Integer> andThen = function.andThen(function2);
		 System.out.println(andThen.apply(10));
	 }
	public static void main(String[] args) {
		combineTwoFunction();
	}

}
