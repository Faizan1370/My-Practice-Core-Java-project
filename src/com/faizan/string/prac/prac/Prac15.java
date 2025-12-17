package com.faizan.string.prac.prac;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Prac15 {
	public static void longestPrefix() {
		String[] words = { "faizan", "faizal", "farhan" };
		String prefix=words[0];
		
		for(int i=1;i<words.length;i++) {
			while(!words[i].startsWith(prefix)) {
				prefix= prefix.substring(0,prefix.length()-1);
			}
			if(prefix.isEmpty()) {
				System.out.println("no");
				return;
			}
		}
		if(!prefix.isEmpty()) {
			System.out.println(prefix);
		}
	}
	public static void stringCompreses() {
		String str = "aaabbbbcccc";
		int count =1;
		StringBuilder builder = new StringBuilder();
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
		StringBuilder builder = new StringBuilder();
		String str = "aaabbbbccbbcc";
		str.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet()
		.stream()
		.forEach(entry->{
			builder.append(entry.getKey()).append(entry.getValue());
		});
		System.out.println(builder.toString());
		
	}
	public static void longestSubstring() {
		String str = "faizan";
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int start=0,maxStart=0,maxLen=0;
		
		for(int i=0;i<str.length();i++) {
			if(map.containsKey(str.charAt(i))) {
				start=Math.max(start, map.get(str.charAt(i))+1);
			}
			map.put(str.charAt(i), i);
			if(i-start+1>maxLen) {
				maxLen =i-start+1;
				maxStart=start;
			}
		}
		System.out.println(str.substring(maxStart,maxLen+maxStart));
	}
	public static void longestSubstring1() {
	    String s = "faizan";
	    int[] index = new int[128]; // stores last index
	    Arrays.fill(index, -1);

	    int left = 0, bestLen = 0, bestStart = 0;

	    for (int right = 0; right < s.length(); right++) {
	        char c = s.charAt(right);
	        left = Math.max(left, index[c] + 1);

	        index[c] = right;

	        if (right - left + 1 > bestLen) {
	            bestLen = right - left + 1;
	            bestStart = left;
	        }
	    }

	    System.out.println(s.substring(bestStart, bestStart + bestLen));
	}

	public static void main(String[] args) {
		longestSubstring1();
	}

}
