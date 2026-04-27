package com.faizan.string.prac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringExer3 {
	public static void longestPrefix() {
		String[] words = { "faizan", "faizal", "farhan" };
		String prfix =words[0];
		for(int i=1;i<words.length;i++) {
			while(words[i].startsWith(prfix)) {
				prfix = prfix.substring(0,prfix.length()-1);
			}
			if(prfix.isEmpty()) {
				System.out.println("no preffix");
			}
		}
		if(!prfix.isEmpty()) {
			System.out.println(prfix);
		}
	}
	public static void stringCompreses() {
		String str = "aaabbbbcccc";
		int count=1;
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<str.length()-1;i++) {
			if(str.charAt(i)==str.charAt(i+1)){
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
		int start=0,maxStart=0,maxLen=0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i=0;i<str.length();i++) {
			if(map.containsKey(str.charAt(i))) {
				start=Math.max(start, map.get(str.charAt(i))+1);
			}
			map.put(str.charAt(i), i);
			if(i-start+1>maxLen) {
				maxLen=i-start+1;
				start=maxStart;
			}
		}
		System.out.println(maxLen);
		System.out.println(str.substring(maxStart,maxStart+maxLen));
	}
	public static boolean checkAnagram(String s,String t) {
		int[] counts=new int[26];
		for(int i=0;i<s.length();i++) {
			char ch= s.charAt(i);
			counts[ch -'a']++;
		}
		for(int i=0;i<t.length();i++) {
			char ch= t.charAt(i);
			counts[ch -'a']--;
		}
		for(int count:counts) {
			if(count !=0) {
				return false;
			}
		}
		return true;
	}
	public static boolean checkAnagram1(String s,String t) {
		ArrayList<Character> list = new ArrayList<Character>();
		for(int i=0;i<s.length();i++) {
			list.add(s.charAt(i));
		}
		for(int i=0;i<t.length();i++) {
			if(!list.remove((Character)t.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		System.out.println(checkAnagram("faizan", "nazia"));
	}

}
