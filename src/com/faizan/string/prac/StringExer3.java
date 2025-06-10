package com.faizan.string.prac;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringExer3 {
	
	public static void longestPrefix() {
		String[] array = {"faizan","faizal","harhan"};
		String prefx =array[0];
		
		for(int i=1;i<array.length;i++) {
			while(!array[i].startsWith(prefx)) {
				prefx = prefx.substring(0,prefx.length()-1);
			}
			if(prefx.isEmpty()) {
				System.out.println("no prefix found");
			}
		}
		
		System.out.println(prefx);
	}
	
	public static void srtingCompresses() {
		String str = "aaabbbbcccdd";
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
	
	public static void srtingCompresses1() {
		String str = "aaabbbaabbbbcccdccd";
		StringBuilder builder = new StringBuilder();
		Map<Character, Long> collect = str.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		
		for(Entry<Character, Long> enrty :collect.entrySet()) {
			builder.append(enrty.getKey()).append(enrty.getValue());
		}
		System.out.println(builder.toString());
	}
	
	public static void srtingCompresses2() {
		String str = "aaabbbaabbbbcccdccd";
		char[] charArray = str.toCharArray();
		Arrays.sort(charArray);
		StringBuilder builder = new StringBuilder();
		int count =1;
		for(int i=0;i<charArray.length-1;i++) {
			if(charArray[i]==charArray[i+1]) {
			count++;
			}else {
				builder.append(charArray[i]).append(count);
				count=1;
			}
		}
		builder.append(charArray[charArray.length-1]).append(count);
		System.out.println(builder.toString());
	}
	
	
	public static void main(String[] args) {
		//longestPrefix();
	   srtingCompresses2();
	}

}
