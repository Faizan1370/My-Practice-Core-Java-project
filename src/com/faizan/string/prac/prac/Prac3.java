package com.faizan.string.prac.prac;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Prac3 {
	
	public static void longestPrefix() {
		String[] words= {"faizan","farhan","faiz"};
		String prefix =words[0];
		
		for(int i=1;i<words.length;i++) {
			while(!words[i].startsWith(prefix)) {
				prefix =prefix.substring(0,prefix.length()-1);
				
			}
			if(prefix.isEmpty()) {
				System.out.println("no prefix found");
			}
		}
		if(!prefix.isEmpty()) {
			System.out.println(prefix);
		}
		
	}
	
	public static void stringCompresse() {
		String str="aabbbcccdddd";
		int count=1;
		StringBuilder builder= new StringBuilder();
		
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
	
	public static void stringCompresse1() {
		String str="aabbbcccddddaa";
		StringBuilder builder= new StringBuilder();
		Map<Character, Long> collect = str.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		for(Map.Entry<Character, Long> entry:collect.entrySet()) {
			builder.append(entry.getKey()).append(entry.getValue());
		}
		System.out.println(builder.toString());
	}
	public static void longestUbstring() {
		String str = "faizan";
		int start = 0;         // The starting index of the current window (substring)
		int maxStart = 0;      // The starting index of the longest substring found
		int maxLen = 0;        // Length of the longest substring found
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(int end=0;end<str.length();end++) {
			char ch=str.charAt(end);
			if(map.containsKey(ch)) {
				start=Math.max(start, map.get(ch)+1);
			}
			map.put(ch, end);
			if(end-start+1>maxLen) {
				maxLen=end-start+1;
				maxStart=start;
			}
			
		}
		System.out.println(str.substring(maxStart,maxStart+maxLen));
	}
	
	public static void main(String[] args) {
		longestUbstring();
	}

}
