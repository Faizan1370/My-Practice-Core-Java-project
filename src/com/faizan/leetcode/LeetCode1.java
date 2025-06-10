package com.faizan.leetcode;

import java.lang.module.FindException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LeetCode1 {
	public static boolean validPalindrome() {
		String s ="A Man, A Plan , A canal :Panama";
		StringBuilder builder = new StringBuilder();
		
		for(int i=0;i<s.length();i++){
			char ch=s.charAt(i);
			if(Character.isLetter(ch) || Character.isDigit(ch)) {
				builder.append(ch);
			}
		}
			String result = builder.toString().toLowerCase();
			System.out.print(result);
			int start =0;
			int end = result.length()-1;
			while(start<end) {
				if(result.charAt(start) != result.charAt(end)) {
					return false;
				}
				start++;
				end--;
			}
				
			return true;
				}
	
	public static int sinegleNumber(int[] array) {
		 Integer integer = Arrays.stream(array).mapToObj(num->(Integer)num)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet()
		.stream()
		.filter(entry->entry.getValue()==1L)
		.map(entry->entry.getKey())
		.findFirst()
		.get();
		return integer;
		
		
		
	}
	
	public static int singleNumber(int[] array) {
		int result=0;
		for(int num :array) {
			result ^=num;
		}
		return result;
	}
	
	public static int majorityElement(int[] array) {
		int majority=array.length/2;
		Integer integer = Arrays.stream(array).mapToObj(num->(Integer)num)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet()
		.stream()
		.filter(entry->entry.getValue()>majority)
		.map(entry->entry.getKey())
		.findFirst()
		.get();
		return integer;
	}
	
	public static boolean containsDuplicte(int[] array) {
		
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i=0;i<array.length;i++) {
			if(set.contains(array[i])) {
				return true;
			}else {
				set.add(array[i]);
			}
		}
		return false;
	}
	
	public static boolean duplicate11(int[] array) {
		int k=2;
		Map<Integer,Integer> map = new HashMap<Integer, Integer>();
		for(int i=0;i<array.length;i++) {
			if(!map.containsKey(array[i])) {
				map.put(array[i], i);
			}else {
				int diff=Math.abs(map.get(array[i])-i);
				if(diff<=k) {
					return true;
				}else {
					map.put(array[i], i);
				}
			}
		}
		return false;
	}
	
	public static void moveZero(int[] array) {
	  int index=0;
	  for(int i=0;i<array.length;i++) {
		  if(array[i]!=0) {
			  array[index]=array[i];
			  index++;
		  }
	  }
	  for(int j=index;j<array.length;j++) {
		  array[j]=0;
	  }
	  System.out.println(Arrays.toString(array));
	}
	public static int[] countBits(int n) {
		int[] ans=new int[n+1];
		ans[0]=0;
		for(int i=1;i<=n;i++) {
			String binary=Integer.toBinaryString(i);
			int count=0;
			for(int j=0;j<binary.length();j++) {
				if(binary.charAt(j)=='1') {
					count++;
				}
			}
			ans[i]=count;
		}
		return ans;
	}
	
	/*
	 * public int guessNumber(int n) { int start =0; int end=n; while(start<=end) {
	 * int mid =start+(end-start)/2; //here guess method will be provided by
	 * leetcode if(guess(mid)==0) { return mid; }else if(guess(mid) ==1){ start
	 * =mid+1; }else { end=mid-1; } } return -1; }
	 */
	
	public static boolean conConstruct(String ransomeNote,String magazine){
		Map<Character, Long> collect = magazine.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		int count=0;
		for(int i=0;i<ransomeNote.length();i++){
			char ch= ransomeNote.charAt(i);
			if(collect.containsKey(ch) && collect.get(ch)>0) {
				count++;
				collect.put(ch,collect.get(ch)-1);
			}else {
				break;
			}
			
		}
		if(ransomeNote.length()== count) {
			return true;
		}
		return false;
	}
	
	public static char findDiffrence(String s,String t) {
		int s_sum=0;
		int t_sum=0;
		for(int i =0;i<s.length();i++) {
			s_sum +=s.charAt(i);
		}
		
		for(int i =0;i<t.length();i++) {
			t_sum +=t.charAt(i);
		}
		int result= t_sum-s_sum;
		return (char)result;
	}
	
	public static char findDifferenceWithMap(String s, String t) {
	    Map<Character, Integer> map = new HashMap<>();

	    for (char ch : t.toCharArray()) {
	        map.put(ch, map.getOrDefault(ch, 0) + 1);
	    }

	    for (char ch : s.toCharArray()) {
	        map.put(ch, map.get(ch) - 1);
	        if (map.get(ch) == 0) {
	            map.remove(ch);
	        }
	    }

	    // The only key left is the extra character
	    for (char ch : map.keySet()) {
	        return ch;
	    }

	    return '\0'; // just in case
	}
	
	public static int maxConsecutiveOne(int[] nums) {
		int count=0;
		int maxCount=0;
		for(int i=0;i<nums.length;i++) {
			if(nums[i]==1) {
				count++;
				maxCount =Math.max(count, maxCount);
			}else {
				count=0;
			}
		}
		return maxCount;
	}
	
	public static boolean directCapitals(String str) {
		if(allUpper(str) || allLower(str) || firstCap(str)) {
			return true;
		}
		
		
		return false;
	}
	
	private static boolean allUpper(String str) {
		int allUpperCount=0;
		for(int i=0;i<str.length();i++) {
			char ch = str.charAt(i);
			if(Character.isUpperCase(ch)) {
				allUpperCount++;
			}
		}
		if(allUpperCount==str.length()) {
			return true;
		}
		return false;
	}
	
	private static boolean allLower(String str) {
		int allLowerCount=0;
		
		for(int i=0;i<str.length();i++) {
			char ch = str.charAt(i);
			if(Character.isLowerCase(ch)) {
				allLowerCount++;
			}
		}
		if(allLowerCount==str.length()) {
			return true;
		}
		return false;
	}
	
	private  static boolean firstCap(String str) {
		String substring = str.substring(1);
		if(Character.isUpperCase(str.charAt(0)) && allLower(substring)) {
			return true;
		}
		return false;
	}
	
	public static String reverseStringEachWord(String str) {
		String[] words = str.split(" ");
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<words.length-2;i++) {
			String reverserEachWord = reverserEachWord(words[i]);
			builder.append(reverserEachWord + " ");
		}
		String reverserEachWord = reverserEachWord(words[words.length-1]);
		builder.append(reverserEachWord);
		return builder.toString();
		
		
	}
	
	private static String reverserEachWord(String word) {
		String result="";
		for(int i=word.length()-1;i>=0;i--) {
			result +=word.charAt(i);
		}
		return result;
	}

	
	public static void main(String[] args) {
		 System.out.println(reverserEachWord("Hi Buddy")); 
		//System.out.println(directCapitals("AGhc"));
		//int[] array = {1,1,0,1,1,1};
		//System.out.println(maxConsecutiveOne(array));
		
		//System.out.println(findDiffrence("abcd", "abcde")); 
		/*
		 * int[] countBits = countBits(2);
		 * System.out.println(Arrays.toString(countBits));
		 */
		
		//System.out.println(conConstruct("aa", "aab"));
		
	}
	}


