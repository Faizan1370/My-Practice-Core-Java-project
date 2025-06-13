package com.faizan.string.prac.prac;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StriingPrac2 {
	
	public static void findLongestprefix() {
		String[] array = {"faizan","farhan","faiz"};
		String prefix=array[0];
		
		for(int i=1;i<array.length;i++) {
			while(!array[i].startsWith(prefix)) {
				prefix = prefix.substring(0, prefix.length()-1);
			}
			if(prefix.isEmpty()) {
				System.out.println("no Prefix found");
			}
		}
		System.out.println(prefix);
	}
	
	public static void stringCompreses() {
		String str ="aaabbbbccccc";
		StringBuilder builder = new StringBuilder();
		int count=1;
	    for(int i=0;i<str.length()-1;i++) {
	    	if(str.charAt(i)== str.charAt(i+1)) {
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
		String str ="aaabbccbbaaabbyyyccccc";
		StringBuilder builder = new StringBuilder();
		
		Map<Character, Long> collect = str.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		
		for(Entry<Character,Long> entry:collect.entrySet()) {
			builder.append(entry.getKey()).append(entry.getValue());
		}
		System.out.println(builder.toString());
	}
	
	
	public static void longestSubstring() {
		String str="hiamfaizanhowareyou";
		int start=0;
		int maxStart=0;
		int maxLen=0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		
		for(int i=0;i<str.length();i++) {
			char ch= str.charAt(i);
			if(map.containsKey(ch)) {
				start=Math.max(start, map.get(ch)+1);
			}
			map.put(ch, i);
			if(i-start+1>maxLen) {
				maxLen=i-start+1;
				maxStart=start;
			}
		}
		String substring = str.substring(maxStart, maxLen+maxStart);
		System.out.println(substring);
		
	}
	
	public static void main(String[] args) {
		//findLongestprefix();
		longestSubstring();
	}

}
