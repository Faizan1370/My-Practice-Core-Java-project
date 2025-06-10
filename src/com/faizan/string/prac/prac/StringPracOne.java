package com.faizan.string.prac.prac;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringPracOne {
	
	public static void findLongestPrefix() {
		String[] array= {"faizan","fhgrhan","adaizal"};
		String prefix=array[0];
		
		for(int i=1;i<array.length;i++) {
			while(!array[i].startsWith(prefix)) {
				prefix=prefix.substring(0,prefix.length()-1);
			}
			if(prefix.isEmpty()) {
				System.out.println("no prefiox found");
			}
		}
		if(!prefix.isEmpty()) {
		System.out.println("longest prefix :"+prefix);
		}
	}
	
	public static void stringComppress() {
		String str="aaaabbbbcccddd";
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
	
	public static void stringComppress1() {
		String str="aaaabbbbaafffcccddd";
		StringBuilder builder = new StringBuilder();
		
		Map<Character, Long> collect = str.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		;
		for(Map.Entry<Character,Long> entry :collect.entrySet()) {
			builder.append(entry.getKey()).append(entry.getValue());
		}
		System.out.println(builder.toString());
	}
	
	public static void findAnagram() {
		String str="faizan";
		String str1="naziaf";
		char[] charArray = str.toCharArray();
		char[] charArray2 = str1.toCharArray();
		boolean isAnagram=false;
		
		Arrays.sort(charArray);
		Arrays.sort(charArray2);
		if(charArray.length!=charArray2.length) {
			isAnagram=false;
			System.out.println("Not anagram");
			return;
		}
		for(int i=0;i<charArray.length;i++) {
			if(charArray[i]==charArray2[i]) {
				isAnagram=true;
			}else {
				isAnagram=false;
				break;
			}
		}
		
		if(isAnagram) {
			System.out.println("String are anagrams :");
		}
		
	}
	
	public static void findPalin() {
		String str="radar";
		int start=0;
		int end =str.length()-1;
		boolean isPalindrome=false;
		
		while(start<end) {
			if(str.charAt(start)==str.charAt(end)) {
				isPalindrome=true;
			}else {
				isPalindrome=false;
				break;
			}
			start++;
			end--;
		}
		if(isPalindrome) {
			System.out.println("Palin");
		}else {
			System.out.println("not Palin");
		}
	}
	
	
	public static void countCharDidgit() {
		String str="faizan@  345/;'";
		int countDigit=0;
		int countChar=0;
		int countSpace=0;
		int other=0;
		
		for(int i=0;i<str.length();i++) {
			if(Character.isDigit(str.charAt(i))) {
				countDigit++;
			}else if(Character.isWhitespace(str.charAt(i))) {
				countSpace++;
			}else if(Character.isAlphabetic(str.charAt(i))){
				countChar++;
			}else {
				other++;
			}
		}
		System.out.println(countChar +" "+countDigit +" "+countSpace + " "+other);
	}
	
	public static void longestSubstring() {
		String str="faizan";
		Map<Character,Integer> map = new HashMap<Character, Integer>();
		int maxLen=0;
		int start=0;
		int maxStart=0;
		
		for(int end =0;end<str.length();end++) {
			char c= str.charAt(end);
			if(map.containsKey(c)) {
				start=Math.max(start, map.get(c)+1);
			}
			map.put(c, end);
			if(end-start+1>maxLen) {
				maxLen=end-start+1;
				maxStart=start;
			}
		}
		 String substring = str.substring(maxStart, maxStart+maxLen);
		 System.out.println(substring);
	}
	public static void main(String[] args) {
		//findLongestPrefix();
		//stringComppress1();
		//findAnagram();
		//findPalin();
		//countCharDidgit();
		longestSubstring();
	}

}
