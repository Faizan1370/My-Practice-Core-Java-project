package com.faizan.string.prac;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Ex2 {
	public static void checkLogestPrefixInStringAarray() {
		String[] array = {"faizan","farhan","faizal"};
		String prefix=array[0];
		
		for(int i=1;i<array.length;i++) {
			while(!array[i].startsWith(prefix)) {
				prefix = prefix.substring(0,prefix.length()-1);
				 if (prefix.isEmpty()) {
		                System.out.println(""); // No common prefix
		                return;
		            }
			}
		}
		System.out.println("prefix is :"+prefix);
	}
	
	public static void stringCompreses() {
		String str = "aaacccdddtttt";
		StringBuilder builder = new 
				StringBuilder();
		int count=1;
		
		for(int i=0;i<str.length()-1;i++) {
			if(str.charAt(i)==str.charAt(i+1)) {
			  count++;	
			}else {
				builder.append(str.charAt(i)).append(count);
				count=1;
			}
		}
		
		builder.append(str.charAt(str.length()-1)).append(count);
		
		System.out.println("Builder :"+builder.toString());
		
	}
	
	public static void sortStringArray1() {
		String[] array= {"Zimbabwe", "South-Africa", "India", "America", "Yugoslavia", " Australia", "Denmark", "France", "Netherlands", "Italy", "Germany"};
		
		for(int i=0;i<array.length-1;i++) {
			for(int j=i+1;j<array.length;j++) {
				if(array[i].compareTo(array[j])>0) {
					String temp =array[i];
					array[i]=array[j];
					array[j]=temp;
				}
			}
		}
		
		System.out.println(Arrays.toString(array));
		
	}
	
	public static void checkFirstNonRepeatingChar1() {
		String str="faizan";
		Character character = str.chars().mapToObj(c->(char)c)
		.filter(c->str.indexOf(c)==str.lastIndexOf(c))
		.findFirst()
		.get();
		
		System.out.println(character);
		
	}
	
	public static void checkFirstRepeatingChar1() {
		String str="faizan";
		Character character = str.chars().mapToObj(c->(char)c)
		.filter(c->str.indexOf(c)!=str.lastIndexOf(c))
		.findFirst()
		.get();
		
		System.out.println(character);
		
	}
	public static void findDuplicateWithCount() {
		String str="faizan";
        str.chars().mapToObj(c->(char)c)
        .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
        .entrySet()
        .stream()
        .filter(entry->entry.getValue()>1L)
        //.findFirst() // find first duplicate
        //.map(entry->entry.getKey())  print only key
        .forEach(entry->System.out.println(entry));
	}
	
	public static void main(String[] args) {
		//checkLogestPrefixInStringAarray();
		//stringCompreses();
		//sortStringArray1();
		//checkFirstNonRepeatingChar1();
		//checkFirstRepeatingChar1();
		findDuplicateWithCount();
	}

}
