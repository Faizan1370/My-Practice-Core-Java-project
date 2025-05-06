package com.faizan.string.prac;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringExer1 {
	
	public static void countCharWithCount() {
		String str="I am ridder";
		str.chars().mapToObj(c->(char)c)
		.filter(c->!c.isWhitespace(c))
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet()
		.stream()
		.forEach(System.out::println);
	}
	
	public static void sortStringArray() {
		String[] array= {"Zimbabwe", "South-Africa", "India", "America", "Yugoslavia", " Australia", "Denmark", "France", "Netherlands", "Italy", "Germany"};
		Collections.sort(Arrays.asList(array));
		System.out.println(Arrays.toString(array));
		
		Arrays.stream(array).sorted().forEach(data->System.out.println(data));
	}
	
	public static void checkAnagramString()
	{
		String str="faizan";
		char[] charArray = str.toCharArray();
		String str1="naziaf";
		char[] charArray1 = str1.toCharArray();
		boolean isAnagram =false;
		Arrays.sort(charArray);
		Arrays.sort(charArray1);
		if(charArray.length !=charArray1.length) {
			isAnagram=false;
		}
		for(int i=0;i<charArray.length;i++) {
			if(charArray[i]!= charArray1[i]) {
				isAnagram=false;
				break;
			}else {
				isAnagram =true;
			}
		}
		System.out.println("Anagram or not :"+isAnagram);
		
	}
	
	public static void findLogestPrefixInStrinArray() {
		String[] array = {"faizan","farhan","dd"};
		String prefix = array[0];
		
		for(int i=1;i<array.length;i++) {
			while(!array[i].startsWith(prefix)) {
				prefix = prefix.substring(0,prefix.length()-1);
				 if (prefix.isEmpty()) {
		                System.out.println(""); // No common prefix
		                return;
		            }
			}
		}
		System.out.println("longest Prefix :"+prefix);
	}
	
	public static void stringCompreses() {
		String str = "aaacccdddtttt";
		StringBuilder builder = new StringBuilder();
		int count =1;
		 for(int i=0;i<str.length()-1;i++) {
			 if(str.charAt(i)==str.charAt(i+1)) {
				 count++;
			 }else {
				 builder.append(str.charAt(i)).append(count);
				 count=1;
			 }
		 }
		 builder.append(str.charAt(str.length()-1)).append(count);
		 System.out.println(builder.toString());
	}
	public static void checkFirstRepeatingChar1() {
		String str="Faizan";
		 String string = Arrays.stream(str.split("")).
		 filter(c->str.indexOf(c)!=str.lastIndexOf(c))
		 .findFirst().get();
		 System.out.println(string);
		
	}

	public static void checkFirstRepeatingChar2() {
		String str="Faizan";
		 Character character = str.chars().mapToObj(c->(char)c)
		 .filter(c->str.indexOf(c)!= str.lastIndexOf(c))
		 .findFirst()
		 .get();
		 System.out.println(character);
	}
	
	public static void removeDuplicateFromString() {
		String str = "Hello";
		List<Character> collect = str.chars().distinct().mapToObj(c->(char)c)
		.collect(Collectors.toList());
		System.out.println(collect);
	}

	public static void removeDuplicateFromString1() {
		String str = "Hello";
		String collect = str.chars().distinct().mapToObj(c->String.valueOf((char)c))
		.collect(Collectors.joining());
		System.out.println(collect);
		
	}
	public static void removeDuplicateManual() {
		String str = "Hello";
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<str.length();i++) {
			if(builder.indexOf(String.valueOf(str.charAt(i)))==-1) {
				builder.append(str.charAt(i));
			}
		}
		System.out.println(builder.toString());
	}
	public static void main(String[] args) {
		//countCharWithCount();
		//sortStringArray();
		//checkAnagramString();
		//findLogestPrefixInStrinArray();
		//stringCompreses();
		//checkFirstRepeatingChar2();
		//removeDuplicateFromString1();
		removeDuplicateManual();
	}

}
