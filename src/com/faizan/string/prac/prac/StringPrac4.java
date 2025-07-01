package com.faizan.string.prac.prac;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringPrac4 {
	
	public static void longestPrefix() {
		String[] array= {"Faizan","Farhan","Faiz"};
		
		String prfix =array[0];
		
		for(int i=1;i<array.length;i++) {
			while(!array[i].startsWith(prfix)) {
				prfix=prfix.substring(0,prfix.length()-1);
			}
			if(prfix.isBlank()) {
				System.out.println("no prefix found");
			}
		}
		System.out.println(prfix);
	}
	
	public static void stringCompreses() {
		String str="aaabbbbbcccffffyyyy";
		StringBuilder builder = new StringBuilder();
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
		System.out.println(builder.toString());
	}
	public static void stringCompreses1() {
		String str="aaabbbbbccaaccddyycffffyyyy";
		StringBuilder builder = new StringBuilder();
		Map<Character, Long> collect = str.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		
		for(Map.Entry<Character, Long> entry :collect.entrySet()) {
			builder.append(entry.getKey()).append(entry.getValue());
		}
		System.out.println(builder.toString());
		
	}
	
	public static void longestSubstring() {
		String str="faizan";
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int maxLen=0;
		int start=0;
		int maxStart=0;
		
		for(int i=0;i<str.length();i++) {
			if(map.containsKey(str.charAt(i))) {
				start=Math.max(start, map.get(str.charAt(i))+1);
			}
			map.put(str.charAt(i), 1);
			if(i-start+1>maxLen) {
				maxLen = i-start+1;
				maxStart=start;
			}
		}
		String substring = str.substring(maxStart,maxStart+maxLen);
		System.out.println(substring);
		
	}
	
	public static void main(String[] args) {
		longestSubstring();
	}

}
