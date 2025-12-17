package com.faizan.java8Prac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
import java.util.stream.Stream;

public class Pract38 {
	public static void evenNumber() {
		List<Integer> list = List.of(2, 4, 7, 9);
		list.stream().filter(num -> (num & 1) == 0).forEach(num -> System.out.println(num));
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
		Stream.concat(list1.stream(), list2.stream()).forEach(num -> System.out.println(num));
	}

	public static void countWordListString() {
		List<String> list = Arrays.asList("AA", "BB", "CC", "AA");
		list.stream().collect(Collectors.groupingBy(str -> str, Collectors.counting())).entrySet().stream()
				.forEach(entry -> System.out.println(entry));
	}

	public static void maxElement() {
		List<Integer> list = Arrays.asList(4, 7, 3, 7, 10, 19);
		Integer integer = list.stream().max(Comparator.comparingInt(num -> num.intValue())).get();
		int asInt = list.stream().mapToInt(num -> num.intValue()).max().getAsInt();
		System.out.println(integer + " " + asInt);
	}

	public static void minElement() {
		List<Integer> list = Arrays.asList(4, 7, 3, 7, 10, 19);
		Integer integer = list.stream().max(Comparator.comparingInt(num -> ((Integer) num).intValue()).reversed())
				.get();
		int asInt = list.stream().mapToInt(num -> num.intValue()).min().getAsInt();
		System.out.println(integer + " " + asInt);
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
				.forEach(entry -> System.out.println(entry));
	}

	public static void convertIntoMap() {
		List<String> list = List.of("abc", "abrt");
		list.stream().collect(Collectors.toMap(Function.identity(), val -> val.length())).entrySet().stream()
				.forEach(entry -> System.out.println(entry));
	}

	public static void groupByDepWithHieghestSal() {
		list.stream()
				.collect(Collectors.groupingBy(emp -> emp.getDesignation(),
						Collectors.maxBy(Comparator.comparingInt(emp -> ((Employee) emp).getSalary()).reversed())))
				.entrySet().stream().forEach(entry -> System.out.println(entry));

	}

	public static void kthHiehestSal() {
		int k = 2;
		Employee employee = list.stream()
				.sorted(Comparator.comparingInt(emp -> ((Employee) emp).getSalary()).reversed()).skip(k - 1).findFirst()
				.get();
		System.out.println(employee);
	}

	public static void convertArrayToStream() {
		int[] array = { 5, 7, 7, 9, 3, 6 };
		Stream<Integer> boxed = Arrays.stream(array).boxed();
		Stream<Integer> mapToObj = Arrays.stream(array).mapToObj(num -> (Integer) num);

	}

	public static void completableFuture() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		CompletableFuture<Void> completableFuture = CompletableFuture
				.runAsync(() -> System.out.println("hi"), executorService).exceptionally((ex) -> {
					return null;
				});
		completableFuture.join();
		executorService.shutdown();

	}

	public static void completableFutureSuplyAs() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
			return "hi";
		}, executorService).thenApply(str -> str.toUpperCase()).exceptionally((ex) -> {
			return null;
		});
		String join = completableFuture.join();
		System.out.println(join);
		executorService.shutdown();
	}

	public static void combineTwoPredicate() {
		Predicate<String> predicate = (str) -> str.startsWith("A");
		Predicate<String> predicate2 = (str) -> str.startsWith("A");
		Predicate<String> and = predicate.and(predicate2);
		System.out.println(and.test("App"));
	}

	public static void combineTwoConsumer() {
		Consumer<String> consumer = (str) -> System.out.println(str);
		Consumer<String> consumer2 = (str) -> System.out.println(str);
		Consumer<String> andThen = consumer.andThen(consumer2);
		andThen.accept("hi");
	}

	public static void combineTwoSuuplier() {
		Supplier<String> supplier = () -> {
			return "hi";
		};
		Supplier<String> supplier1 = () -> {
			return "Br0";
		};
		Supplier<String> supplier2 = () -> supplier.get() + " " + supplier1.get();
		System.out.println(supplier2.get());
	}

	public static void combineTwoFunction() {
		Function<Integer, Integer> function = (x) -> x * 2;
		Function<Integer, Integer> function2 = (x) -> x * 2;
		Function<Integer, Integer> andThen = function.andThen(function2);
		System.out.println(andThen.apply(3));
	}

	public static void avgSal() {
		double asDouble = list.stream().filter(emp -> emp.getDesignation().contains("Soft")).map(emp -> emp.getSalary())
				.mapToDouble(sal -> sal.doubleValue()).average().getAsDouble();
		System.out.println(asDouble);
	}
	public static void reduceSum() {
		List<Integer> list1 = Arrays.asList(3, 5, 7, 8);
		Integer reduce = list1.stream().reduce(0,(a,b)->a+b);
		Integer collect = list1.stream().collect(Collectors.summingInt(num->num.intValue()));
		int sum = list1.stream().mapToInt(num->num.intValue()).sum();
	    Integer integer = list1.stream().reduce(Integer::sum).get();
	    System.out.println(reduce +" "+collect +" "+sum +" "+integer);
	}
	public static void optional() {
		Optional<Object> optional = Optional.empty();
		System.out.println(optional);
		list.stream().forEach(emp ->{
			System.out.println(Optional.ofNullable(emp.getName()).orElse("Faiz"));
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
