package com.faizan.leetcode.revision1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCodeRevision4 {
	
	public static int secondLargestDigit(String s) {
		int largest=-1;
		int secondLarget=-1;
		for(int i=0;i<s.length();i++) {
			char ch=s.charAt(i);
			if(Character.isDigit(ch)) {
				if(Character.getNumericValue(ch)>largest) {
					secondLarget=largest;
					largest=Character.getNumericValue(ch);
				}else if(Character.getNumericValue(ch)>secondLarget) {
					secondLarget=Character.getNumericValue(ch);
				}
			}
		}
		return secondLarget;
	}
	
	public static String firstKWords(String s,int k) {
		String[] words= s.split(" ");
		if(words.length<k) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<k;i++) {
			if(i!=k) {
			builder.append(words[i]).append(" ");
		  }else {
			builder.append(words[i]);
		}
		}
		return builder.toString();
	}
	
	public static boolean sentencePangram(String s) {
		HashSet<Character> set = new HashSet<Character>();
		for(int i=0;i<s.length();i++){
			set.add(s.charAt(i));
		}
		
		return set.size()==26;
	}
	
	public static String soortingSentence(String s) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		String[] words = s.split(" ");
		for(String word:words) {
			char ch=word.charAt(word.length()-1);
			map.put(Character.getNumericValue(ch), word.substring(0,word.length()-1));
		}
		StringBuilder builder = new StringBuilder();
		for(int i=1;i<=words.length;i++) {
			 builder.append(map.get(i));
		        if (i != words.length) {
		            builder.append(" ");
		        }
		}
		return builder.toString();
	}
	public static String sortingSentence1(String s) {
		String[] words=s.split(" ");
		String[] result=new String[words.length];
		
		  for (String word : words) {
		        int index = Character.getNumericValue(word.charAt(word.length() - 1));
		        result[index - 1] = word.substring(0, word.length() - 1);
		    }
		  
		  return String.join(" ", result);
	}
	
	public static int[] contactArray(int[] nums) {
		int[] result = new int[(nums.length)*2];
		for(int i=0;i<nums.length;i++) {
			result[i]=nums[i];
			result[i+nums.length]=nums[i];
		}
		return result;
	}
	
	public static boolean checkEqualOccurence(String s) {
		Map<Character, Long> map = s.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		
		return new HashSet<>(map.values()).size() == 1;
		 
	}
	
	public static String kthDistinctWord(String[] arr,int k) {
		if(arr.length<k) {
			return "";
		}
		LinkedHashMap<String, Integer> map= new LinkedHashMap<String, Integer>();
		for(int i=0;i<arr.length;i++) {
			if(map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i])+1);
			}else {
				map.put(arr[i], 1);
			}
		}
		ArrayList<String> list = new ArrayList<String>();
		for(Map.Entry<String, Integer> entry:map.entrySet()) {
			if(entry.getValue()==1) {
				list.add(entry.getKey());
			}
		}
		if(list.size()<k) {
			return "";
		}
		return list.get(k-1);
	}
	public static String kthDistictElement(String[] array ,int k) {
		 List<String> list = Arrays.stream(array).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()))
		 .entrySet()
		 .stream()
		 .filter(entry->entry.getValue()==1L)
		 .map(entry->entry.getKey())
		 .collect(Collectors.toList());
		 if(list.size()<k) {
			 return "";
		 }
		 return list.get(k-1);
	}
	
	public static int smallestIndex(int[] nums) {
		for(int i=0;i<nums.length;i++) {
			int mod=nums[i]%10;
			if(mod==nums[i]) {
				return i;
			}
		}
		return -1;
	}
	
	public static int smallestIndexCorrect(int[] nums) {
		for(int i=0;i<nums.length;i++) {
			
			if(i%10==nums[i]) {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] nums= {0,1,2};
		System.out.println(smallestIndex(nums));
		//String[] arr= {"d","b","c","b","c","a"};
		//int k=2;
		//System.out.println(kthDistinctWord(arr, k));
		//String s="aabb";
		//System.out.println(checkEqualOccurence(s));
		//int[] nums= {1,2,1};
		//System.out.println(Arrays.toString(contactArray(nums)));
		//String s="is2 sentence4 This1 a3";
		//System.out.println(sortingSentence1(s));
		//String sentence="thequickbrownfoxjumpsoverthelazydog";
		//System.out.println(sentencePangram(sentence));
		//String s="Hellow how are you buudy";
		//int k=4;
		//System.out.println(firstKWords(s, k));
	//  String s="afa1213ofd";
	//  System.out.println(secondLargestDigit(s));
	}

}
