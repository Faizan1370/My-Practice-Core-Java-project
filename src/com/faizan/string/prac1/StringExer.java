package com.faizan.string.prac1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringExer {
	
	public static void longestPrefix() {
		String[] array= {"Farhan","Faizan","Faizal"};
		String prefix=array[0];
		
		for(int i =1;i<array.length;i++) {
			while(!array[i].startsWith(prefix)) {
				prefix = prefix.substring(0,prefix.length()-1);
			}
			if(prefix.isEmpty()) {
				System.out.println("no prefix found");
			}
		}
		System.out.println(prefix);
		
	}
	
	public static void stringCompreesse() {
		String str="aaaabbbbccccdd";
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
	
	//If chars is not in order
	public static void stringCompreesse1() {
		String str="aaaabbaahhhhbbcccaacddbbbb";
		StringBuilder builder = new StringBuilder();
		Map<Character, Long> collect = str.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
	   for(Entry<Character,Long> entry:collect.entrySet()){
		  builder.append(entry.getKey()).append(entry.getValue()); 
	   }
	   System.out.println(builder.toString());
	}
	

	public static void findAnagram() {
		String str="faizan";
		String str1="naziaf";
		boolean isAnagram=false;
		
		char[] charArray = str.toCharArray();
		char[] charArray2 = str1.toCharArray();
		Arrays.sort(charArray);
		Arrays.sort(charArray2);
		if(charArray.length!=charArray2.length) {
			isAnagram=false;
			return;
		}
		for(int i =0;i<charArray.length;i++) {
			if(charArray[i]!=charArray2[i]) {
				isAnagram=false;
				break;
			}else {
				isAnagram=true;
			}
		}
		if(isAnagram) {
			System.out.println("Both are anagram");
		}else {
			System.out.println("both are not anagram");
		}
		
		
	}
	
	public static void palindrom() {
		String str="radar";
		int start=0;
		int end=str.length()-1;
		boolean isPlindrome=false;
		
		while(start<end) {
			if(str.charAt(start) !=str.charAt(end)) {
				isPlindrome=false;
				break;
			}else{
				isPlindrome =true;
			}
			start++;
			end--;
		}
		if(isPlindrome) {
			System.out.println("yes it is Palidrom");
		}else {
			System.out.println("No it is not Palindrom");
		}
				
	}
	
	public static void removeDuplicateCharStream() {
	    String str = "faizan";

	    String result = str.chars()
	        .mapToObj(c -> (char) c)
	        .filter(new HashSet<>()::add)  // creates and uses HashSet directly
	        .map(String::valueOf)
	        .collect(Collectors.joining());

	    System.out.println(result); // Output: faizn
	}
	
	public static void removeDeuplicate() {
		String str = "faizan";
		StringBuilder builder = new StringBuilder();
		boolean[] seen = new boolean[256];
		
		for(int i=0;i<str.length();i++) {
			if(!seen[str.charAt(i)]) {
				builder.append(str.charAt(i));
				seen[str.charAt(i)] =true;
				
			}
			
			
		}
		System.out.println(builder.toString());
				}
	
	public static void findLongestSubstring() {
		String str="Hellobhi";
		int n=str.length(),maxStart=0,left=0,right=0,maxLen=0;
		HashSet<Character> seen = new HashSet<Character>();
		
		while(right<n) {
			 if (!seen.contains(str.charAt(right))) {
				seen.add(str.charAt(right));
				right++;
				if(right-left>maxLen) {
					maxLen=right-left;
					maxStart=left;
				}
			 }else {
				 System.out.println(str.charAt(left));
				 seen.remove(str.charAt(left++));
			 }
		}
		System.out.println(maxLen +" "+maxStart);
		System.out.println(str.substring(maxStart,maxStart+maxLen));
	}

	
	public static void main(String[] args) {
		findLongestSubstring();
		
	}

}
