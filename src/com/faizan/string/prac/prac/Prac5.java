package com.faizan.string.prac.prac;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Prac5 {
	
	public static void longestPrefix() {
		String[] words= {"faizan","faizal","farhan"};
		String prefix=words[0];
		for(int i=1;i<words.length;i++) {
			while(!words[i].startsWith(prefix)) {
				prefix = prefix.substring(0,prefix.length()-1);
			}
			if(prefix.isEmpty()) {
				System.out.println("no");
			}
		}
		if(!prefix.isEmpty()) {
			System.out.println("yes");
		}
	}
	
	public static void stringCompreses() {
		String str="aaabbbbcccc";
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
		String str="aaabbbbcccc";
		StringBuilder builder = new StringBuilder();
		Map<Character, Long> map = str.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		for(Map.Entry<Character, Long> entry :map.entrySet()) {
			builder.append(entry.getKey()).append(entry.getValue());
		}
		System.out.println(builder.toString());
	}
	
	public static void longestSubstring() {
		String str="hello";
		int start=0,maxStart=0,maxLen=0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		
		for(int i=0;i<str.length();i++) {
			if(map.containsKey(str.charAt(i))) {
				start=Math.max(start, map.get(str.charAt(i))+1);
			}
			map.put(str.charAt(i), i);
			if(i-start+1>maxLen) {
				maxLen=i-maxStart+1;
				maxStart=start;
			}
		}
		System.out.println(str.substring(maxStart,maxStart+maxLen));
	}
		
	
	public static void main(String[] args) {
		longestSubstring();
	}

}
