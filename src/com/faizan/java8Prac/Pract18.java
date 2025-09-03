package com.faizan.java8Prac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

public class Pract18 {

	public static void evenNumber() {
		List<Integer> list = Arrays.asList(4, 6, 8, 2, 3567, 5, 23, 12);
		list.stream().filter(num -> num % 2 == 0).forEach(num -> System.out.println(num));

	}

	public static void firstNonRepeatingChar() {
		String str = "faizan";
		Character character = str.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(entry -> entry.getValue() == 1L).map(entry -> entry.getKey()).findFirst().get();
		System.out.println(character);

	}

	public static void firstNonRepeatingChar1() {
		String str = "faizan";
		Character character = str.chars().mapToObj(c -> (char) c).filter(c -> str.indexOf(c) == str.lastIndexOf(c))
				.findFirst().get();
		System.out.println(character);

	}

	public static void firstRepeatingChar() {
		String str = "faizan";
		Character character = str.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(entry -> entry.getValue() > 1L).map(entry -> entry.getKey()).findFirst().get();
		System.out.println(character);

	}

	public static void firstRepeatingChar1() {
		String str = "faizan";
		Character character = str.chars().mapToObj(c -> (char) c).filter(c -> str.indexOf(c) != str.lastIndexOf(c))
				.findFirst().get();
		System.out.println(character);

	}

	public static void sortElement() {
		List<Integer> list = Arrays.asList(5, 7, 3, 89, 23, 54);
		list.stream().sorted().forEach(num -> System.out.println(num));

	}

	public static void cubeElemenetGreaterThanValue() {
		List<Integer> list = Arrays.asList(1, 7, 8, 2);
		list.stream().filter(num -> num * num * num > 50).forEach(num -> System.out.println(num));

	}

	public static void cubeElemenetGreaterThanValue1() {
		List<Integer> list = Arrays.asList(1, 7, 8, 2);
		list.stream().map(num -> num * num * num).filter(num -> num > 50).forEach(num -> System.out.println(num));

	}

	public static void mergerTwoStream() {
		List<Integer> list1 = Arrays.asList(4, 7, 2, 10, 8, 5, 10, 20, 40);
		List<Integer> list2 = Arrays.asList(23, 56, 45, 90);

		Stream<Integer> concat = Stream.concat(list1.stream(), list2.stream());

	}

	public static void countWordListString() {

		List<String> list = Arrays.asList("AA", "BB", "CC", "AA");
		list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.forEach(entry -> System.out.println(entry));

	}

	public static void maxElement() {
		List<Integer> list = Arrays.asList(4, 7, 3, 7, 10, 19);
		int asInt = list.stream().mapToInt(num -> num.intValue()).max().getAsInt();
		System.out.println(asInt);
		Integer integer = list.stream().max(Comparator.comparingDouble(num -> num)).get();
		System.out.println(integer);
	}

	public static void minElement() {
		List<Integer> list = Arrays.asList(4, 7, 3, 7, 10, 19);
		int asInt = list.stream().mapToInt(num -> num.intValue()).min().getAsInt();
		System.out.println(asInt);
		Integer integer = list.stream().min(Comparator.comparingDouble(num -> num)).get();
		System.out.println(integer);
	}

	public static void findOnlyDuplicateElementsWithCount() {
		List<String> list = Arrays.asList("AA", "BB", "CC", "AA");
		list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(entry -> entry.getValue() > 1L).forEach(entry -> System.out.println(entry));
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

	public static void groupByDept() {
		list.stream().collect(Collectors.groupingBy(emp -> emp.getDesignation())).entrySet().stream()
				.forEach(emp -> System.out.println(emp));
	}

	public static void convertIntoMap() {
		List<String> list = List.of("abc", "abrt");
		Map<String, String> collect = list.stream()
				.collect(Collectors.toMap(value -> value, value -> value.toUpperCase()));
	}

	public static void groupByDepWithHieghestSal() {
		list.stream()
				.collect(Collectors.groupingBy(emp -> emp.getDesignation(),
						Collectors.maxBy(Comparator.comparingInt(emp -> emp.getSalary()))))
				.entrySet().stream().forEach(emp -> System.out.println(emp));
	}

	public static void kthHiehestSal() {
		int k = 2;
		Employee employee = list.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed()).skip(k - 1)
				.findFirst().get();
		System.out.println(employee);
	}

	public static void convertStreamToIntStream() {
		List<Integer> list = List.of(4, 7, 5, 9);
		IntStream mapToInt = list.stream().mapToInt(num -> num.intValue());
	}

	public static void convertArrayToStream() {
		int[] array = { 5, 7, 7, 9, 3, 6 };
		Stream<Integer> boxed = Arrays.stream(array).boxed();
		Stream<Integer> mapToObj = Arrays.stream(array).mapToObj(num -> (Integer) num);

	}

	public static void completableFuture() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
			System.out.println("hi");
		}, executorService).exceptionally(ex -> {
			return null;
		});
		completableFuture.join();
		executorService.shutdown();

	}
	 public static void completableFutureSuppply() {
		 ExecutorService executorService = Executors.newFixedThreadPool(1);
		 CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
			 return "hi";
		 },executorService).thenApply(str->str.toUpperCase()).exceptionally(ex->{
			return null; 
		 });
		 System.out.println(completableFuture.join());
		 executorService.shutdown();
	 }
	 public static void converIntStreamToStream() {
		 IntStream intStream = IntStream.of(2,4,6);
		 Stream<Integer> mapToObj = intStream.mapToObj(num->(Integer)num);
		 
	 }
	 
	 public static void combineTwoPredicate() {
		 Predicate<String> predicate =(str)->str.startsWith("A");
		 Predicate<String> predicate2 =(str)->str.startsWith("A");
		 
		 Predicate<String> and = predicate.and(predicate2);
		 System.out.println(and.test("App"));
	 
	 }
	 public static void combineTwoConsumer() {
		 Consumer<String> consumer =(str)->System.out.println(str);
		 Consumer<String> consumer2 =(str)->System.out.println(str);
		 Consumer<String> andThen = consumer.andThen(consumer2);
		andThen.accept("hi");
	 }
	 public static void combineTwoSuuplier() {
		 Supplier<String> supplier =()->"hi";
		 Supplier<String> supplier2 =()->"bro";
		 Supplier<String> supplier3 =()-> supplier.get() +" "+supplier2.get();
		 System.out.println(supplier3.get());
	 }
	 public static void combineTwoFunction() {
		 Function<Integer, Integer> function =(x)->x*2;
		 Function<Integer, Integer> function2 =(x)->x+1;
		 Function<Integer, Integer> andThen = function.andThen(function2);
		 System.out.println(andThen.apply(9));
	 }
	  public static void findSumReduce() {
		 List<Integer> list1 = Arrays.asList(3,4,6,2);
		 Integer sum = list1.stream().distinct().collect(Collectors.summingInt(num->num.intValue()));
		 Double sum1 = list1.stream().distinct().collect(Collectors.summingDouble(num->num.doubleValue()));
		 Integer sum2 = list1.stream().reduce(0,(a,b)->a+b);
		 int sum3 = list1.stream().mapToInt(num->num.intValue()).sum();
		Integer sum4 = list1.stream().reduce(Integer::sum).get();
		 List<String> words = Arrays.asList("Sahil","ameer","Khan");
		 String string = words.stream().reduce((word1,word2)->word1.length()>word2.length()?word1:word2).get();
		 double asDouble = list.stream().filter(emp->emp.getDesignation().contains("Soft")).map(emp->emp.getSalary()).mapToDouble(num->num.doubleValue()).average().getAsDouble();
		 System.out.println(sum +" "+sum1+" "+sum2+" "+sum3 +" "+sum4 +" "+string +" "+asDouble);
	  }
	  public static void optiona() {
		  list.stream().forEach(emp->{
			  //System.out.println(Optional.of(emp.getName()));
			  System.out.println(Optional.ofNullable(emp.getName()).orElse("Faiz"));
			  System.out.println(Optional.ofNullable(emp.getName()).map(name->name.toUpperCase()) .orElse("Default Name"));
		  });
	  }
		 public static void mapFlatMap() {
			 List<com.faizan.java8Prac.Customer> collect = Stream.of(
					 new Customer(54, "abc@abc.com", "abc", Arrays.asList(916123456,896745230)),
					 new Customer(87, "harsih@abc.com", "harish", Arrays.asList(916123690,89645230)),
					 new Customer(77, "jameel@abc.com", "jameel", Arrays.asList(875123456,763745654))).collect(Collectors.toList());
			 collect.stream().flatMap(cs->cs.getNumbers().stream()).forEach(num->System.out.println(num));
		 }

	public static void main(String[] args) {
		mapFlatMap();
	}

}
