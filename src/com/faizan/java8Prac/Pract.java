package com.faizan.java8Prac;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pract {
	
	public static void duplicateStrinArrayElementWithCount() {
		List<String> list =Arrays.asList("faiz","faiz","gsn","fhgh");
		list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet()
		.stream()
		.filter(str->str.getValue()>1L)
		.forEach(entry->System.out.println(entry));
	}
	
	public static void deplicaInIntegerArray() {
		int[] a = {4,6,8,3,8};
		Arrays.stream(a).mapToObj(num->(Integer)num)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
				.entrySet()
				.stream()
				.filter(str->str.getValue()>1L)
				.forEach(entry->System.out.println(entry));
	}
	
	public static void streamToIntstream() {
		List<Integer> list =Arrays.asList(2,5,7,8);
		IntStream mapToInt = list.stream().mapToInt(num->num.intValue());
	}
	public static void main(String[] args) {
		//duplicateStrinArrayElementWithCount();
		deplicaInIntegerArray();
	}

}
