package com.faizan.string.prac.prac;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringPrac3 {
	
	public static void longestPrefix() {
		String[] array= {"Faizan","Farhan","Faiz"};
		String prefix=array[0];
		for(int i =1;i<array.length;i++) {
			while(!array[i].startsWith(prefix)) {
				prefix=prefix.substring(0,prefix.length()-1);
			}
			if(prefix.isEmpty()) {
				System.out.println("There is no Prefix");
				return;
			}
		}
		System.out.println(prefix);
	}
	public static void stringComppress() {
		String str="aaaabbbbcccddd";
		int count=1;
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<str.length()-1;i++) {
			if(str.charAt(i)==str.charAt(i+1)) {
				count++;
			}else {
				builder.append(str.charAt(i)).append(count);
				count=1;
			}
		}
		System.out.println(builder.toString());
		
	}
	
	public static void stringComppress1() {
		String str="aaaabbbbaafffcccddd";
		StringBuilder builder = new StringBuilder();
		
		Map<Character, Long> collect = str.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		for(Map.Entry<Character, Long> entry:collect.entrySet()) {
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
			char c= str.charAt(i);
			if(map.containsKey(c)) {
				start=Math.max(start, map.get(c)+1);
			}
			map.put(c, i);
			if(i-start+1>maxLen) {
				maxLen = i-start+1;
				maxStart=start;
			}
			
		}
		String substring=str.substring(maxStart, maxStart+maxLen);
		System.out.println(substring);
	}
	
	public static void main(String[] args) {
		longestSubstring();
	}

}
