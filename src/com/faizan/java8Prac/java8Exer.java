package com.faizan.java8Prac;

import java.io.ObjectInputStream.GetField;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;



public class java8Exer {
	
	public static void evenNumber() {
		List<Integer> list = Arrays.asList(8,5,3,2);
		list.stream().filter(num->num%2==0).forEach(num->System.out.println(num));
	}
	
	public static void firstNonRepeatingChar() {
		String str= "Faizan";
		 Character character = str.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new ,Collectors.counting()))
		.entrySet()
		.stream()
		.filter(entry->entry.getValue()==1L)
		.map(c->c.getKey())
		.findFirst()
		.get();
		System.out.println(character);
	}
	
	public static void firstNonRepeatingChar1() {
		String str= "Faizan";
		 Character character = str.chars().mapToObj(c->(char)c)
		.filter(c->str.indexOf(c)==str.lastIndexOf(c))
		.findFirst()
		.get();
		 System.out.println(character);
	}
	
	public static void firstRepeatingChar() {
		String str= "Faizan";
		 Character character = str.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new ,Collectors.counting()))
		.entrySet()
		.stream()
		.filter(entry->entry.getValue()>1L)
		.map(c->c.getKey())
		.findFirst()
		.get();
		System.out.println(character);
	}
	

	public static void firstRepeatingChar1() {
		String str= "Faizan";
		 Character character = str.chars().mapToObj(c->(char)c)
		.filter(c->str.indexOf(c)!=str.lastIndexOf(c))
		.findFirst()
		.get();
		 System.out.println(character);
	}
	
	public static void sortElement() {
		List<Integer> list = Arrays.asList(5,7,3,89,23,54);
		list.stream().sorted().forEach(num->System.out.println(num));
	}
	
	public static void sortReveseElement() {
		List<Integer> list = Arrays.asList(5,7,3,89,23,54);
		list.stream().sorted(Collections.reverseOrder()).forEach(num->System.out.println(num));
	}
	
	public static void sortReveseElement1() {
		List<Integer> list = Arrays.asList(5,7,3,89,23,54);
		list.stream().sorted(Comparator.reverseOrder()).forEach(num->System.out.println(num));
	}
	
	 public static void mergerTwoStream() {
		   List<Integer> list1 =Arrays.asList(4,7,2,10,8,5,10,20,40);
		   List<Integer> list2 =Arrays.asList(23,56,45,90);
		   Stream<Integer> concat = Stream.concat(list1.stream(), list2.stream());
	 }
	 
	
	  public static void cubeElemenetGreaterThanValue() {
		   List<Integer> list =Arrays.asList(1,7,8,2);
		   list.stream().map(num->num*num*num).filter(num->num>50).forEach(num->System.out.println(num)); 
	  }
	  
	  public static void countWordListString() {
		   
		   List<String> list =Arrays.asList("AA","BB","CC","AA");
		   list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
		   .entrySet()
		   .stream()
		   .forEach(entry->System.out.println(entry));
		   
	  }
	  
	  public static void countCharInString() {
		  String str= "Hello Buddy";
		  str.chars().mapToObj(c->(char)c)
		  .collect(Collectors.groupingBy(Function.identity() , Collectors.counting()))
		  .entrySet()
		  .stream()
		  .filter(entry->!Character.isWhitespace(entry.getKey()))
		  .forEach(System.out::println);
	  }
	  
	  public static void countCharInString1() {
		  String str= "Hello Buddy";
		  Arrays.stream(str.split("")).map(String :: toLowerCase)
		  .filter(charstr->!charstr.trim().isEmpty())
		  .collect(Collectors.groupingBy(Function.identity() , Collectors.counting()))
		  .entrySet()
		  .stream()
		  .forEach(System.out::println);
	  }
	  
	  public static void findOnlyDuplicateElementsWithCount() {
		   List<String> list =Arrays.asList("AA","BB","CC","AA");
		   list.stream().filter(word->Collections.frequency(list, word)>1)
		   .collect(Collectors.groupingBy(Function.identity() , Collectors.counting()))
		   .entrySet()
		   .stream()
		   .forEach(System.out::println);
		   
	  }
	  
	  public static void findOnlyDuplicateElementsWithCount2() {
		   List<String> list =Arrays.asList("AA","BB","CC","AA");
		   list.stream().collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
		   .entrySet()
		   .stream()
		   .filter(entry->entry.getValue()>1L)
		   .forEach(entry->System.out.println(entry));
	  }
	  
	  
	  public static void findOnlyDuplicateElementsWithCount1() {
		   List<String> list =Arrays.asList("AA","BB","CC","AA");
		   list.stream().  filter(t->Collections.frequency(list, t)>1)
		   .collect(Collectors.groupingBy(Function.identity() , Collectors.counting()))
		   .entrySet()
		   .stream()
		  // .filter(entry->entry.getValue()>1L)
		   .forEach(System.out::println);
		   
	  }
	  
	  public static void maxElement() {
		   List<Integer> list =Arrays.asList(4,7,3,7,10,19);
		 //  System.out.println(list.stream().max(Integer::compare).get());
		   Integer integer = list.stream().max(Comparator.comparingInt(num->num.intValue())).get();
		   System.out.println(integer);
		   
	  }
	
	  public static void convertIntoMap() {
		 // List<String> list =Arrays.asList("abc","abrt");
		  List<String> list =List.of("abc","abrt");
		  list.stream().collect(Collectors.toMap(value->value, value->value.length())).
		   entrySet().stream() .forEach(System.out::println);
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
		 
		 public static void groupByDep() {
			 list.stream().collect(Collectors.groupingBy(emp->emp.getDesignation()))
			 .entrySet()
			 .stream()
			 .forEach(System.out::println);
		 }
		 public static void groupByDepWithCount() {
			 list.stream().collect(Collectors.groupingBy(emp->emp.getDesignation(),Collectors.counting()))
			 .entrySet()
			 .stream()
			 .forEach(System.out::println); 
		 }
		 
		 public static void groupByDepWithHieghestSal() {
			 list.stream().collect(Collectors.
					 groupingBy(emp->emp.getDesignation(),Collectors.maxBy(Comparator.comparingInt(emp->emp.getSalary()))))
			 .entrySet()
			 .stream()
			 .forEach(System.out::println);
		 }
		 
		 public static void findKthHiesetSal() {
			 int k=2;
			 Optional<Employee> first = list.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed())
			 .skip(k)
			 .findFirst();
			 System.out.println(first.get());
		 }
		 
		 public static void findKthHiesetSal1() {
			 int k=2;
			Employee employee = list.stream().sorted(Comparator.comparing(emp->((Employee) emp).getSalary()).reversed())
			.skip(k)
			.findFirst()
			.get();
			System.out.println(employee);
			
		 }
		 
		 
		 public static void convertStreamToIntStream() {
			 List<Integer> list = List.of(4,7,5,9);
			 IntStream mapToInt = list.stream().mapToInt(num->num.intValue());
			 mapToInt.forEach(num->System.out.println(num));
		 }
	    
		 public static void converIntStreamToStream() {
			 IntStream intStream = IntStream.of(4,7,3,8);
			 List<Integer> collect = intStream.mapToObj(num->(Integer)num).collect(Collectors.toList());
		List<Integer> collect2 = intStream.boxed().collect(Collectors.toList());
		 }
		 public static void convertStereamToDouble() {
			 List<Integer> list = List.of(4,7,5,9);
			 DoubleStream mapToDouble = list.stream().mapToDouble(doble->doble.doubleValue());
			mapToDouble.forEach(num->System.out.println(num));
		 }
		 
		 public static void convertArrayToStream() {
			 int[] array = {5,7,7,9,3,6};
			// Arrays.stream(array) //convert to intStream
			  Stream<Integer> mapToObj = Arrays.stream(array).mapToObj(integ->(Integer)integ);
			 mapToObj.forEach(num->System.out.println(num));
		 }
		 
		 public static void convertStringtoIntstream() {
			 String str ="Faizan";
			 IntStream chars = str.chars();
		 }
		 
		 public static void completableFuture() {
			 CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
				 System.out.println("Hello Completable futur");
				// int i =10/0;
			 }).exceptionally(ex->{
				 System.out.println("exception");
				 return null;
			 });
			 
			 completableFuture.join();
		 }
		 
		 public static void completableFutureSuplyAsync() {
			 ExecutorService executorService = Executors.newFixedThreadPool(1);
			CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
				System.out.println("Async running in thread: " + Thread.currentThread().getName()); 
				return "Supply Asycn";
			},executorService).thenApply(msg->msg.toUpperCase())
					.thenApply(msg->msg.substring(0,8)).exceptionally(ex->{
						return "exception occcurede";
					});
			String join = completableFuture.join();
			System.out.println(join);
			System.out.println(Thread.currentThread().getName());
			executorService.shutdown();
		 }
		 
		 public static void combineTwoPredicate() {
				Predicate<String> pred = (str)->str.startsWith("A");
				Predicate<String> pred1 = (str)->str.length()>3;
				
				Predicate<String> comPredicate = pred.and(pred1);
				
				System.out.println(comPredicate.test("Apple"));
				System.out.println(comPredicate.test("App"));
			 }
			 
		 
		 public static void combineTwoComsumer() {
			 Consumer<String> consumer = (str)->System.out.println(str.toUpperCase());
			 Consumer<String> consumer1 = (str)->System.out.println(str.toUpperCase());
			 
			 Consumer<String> andThen = consumer.andThen(consumer1);
			 andThen.accept("hello");
		 }
		 
		 public static void combineTwoSupplier() {
			 Supplier<String> supplier = ()->"Hello bro";
			 Supplier<String> supplier1 = ()->"! How are you ?";
			 
			 Supplier<String> combinedSupplier = ()->supplier.get() + " "+supplier1.get();
			 System.out.println(combinedSupplier.get());
		 }
		 
		 public static void combineTwoFunctions() {
			 Function<Integer, Integer> function = (i)->i*3;
			 Function<Integer, Integer> add10 = x -> x + 10;
			 
			 Function<Integer, Integer> andThen = function.andThen(add10);
			 System.out.println(andThen.apply(6));
		 }
		 
	  
	
	public static void main(String[] args) {
		groupByDepWithHieghestSal();
		//evenNumber();
		//firstNonRepeatingChar();
		//firstRepeatingChar1();
		//firstNonRepeatingChar1();
		//firstRepeatingChar();
		//sortElement();
		//sortReveseElement();
		//cubeElemenetGreaterThanValue();
		//countWordListString();
		//countCharInString1();
		//findOnlyDuplicateElementsWithCount();
		//maxElement();
		//convertIntoMap();
		//groupByDep();
		//groupByDepWithCount();
		//groupByDepWithHieghestSal();
		//findKthHiesetSal1();
		//convertStreamToIntStream();
		//convertStereamToDouble();
		//convertArrayToStream();
		//completableFuture();
		 //completableFutureSuplyAsync();
		//findOnlyDuplicateElementsWithCount2();
		//combineTwoPredicate();
		//combineTwoComsumer();
		//combineTwoSupplier();
		//combineTwoFunctions();
		//sortReveseElement1();
	}

}
