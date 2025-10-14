package com.faizan.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class LeetCode12 {
	
	public static int minOprationInAltenate(String s) {
		
		int startWith1=0;//1010 
		int startWith0=0;//0101
		
		for(int i=0;i<s.length();i++) {
			if(i%2==0) {
				if(s.charAt(i)=='0') {
					startWith1++;
				}else {
					startWith0++;
				}
			}else {
				if(s.charAt(i)=='1') {
					startWith1++;
				}else {
					startWith0++;
				}
			}
		}
		return Math.min(startWith1, startWith0);
	}
	
	public static boolean isMonolatic(int[] nums) {
		boolean isIncreasing=true;
		boolean isDecreasing=true;
		
		for(int i=1;i<nums.length;i++) {
			if(nums[i-1]>nums[i]) {
				isIncreasing=false;
			}else if(nums[i-1]<nums[i]) {
				isDecreasing=false;
			}
		}
		if(isDecreasing || isIncreasing) {
			return true;
		}
		return isDecreasing;
	}
	
	
	public static int[] minimumNumberGame(int[] nums) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		int[] arr = new int[nums.length];
		for(int num:nums) {
			queue.add(num);
		}
		int i=0;
		while(!queue.isEmpty()) {
			int alice = queue.poll();
			int bob = queue.poll();
			arr[i++]=bob;
			arr[i++]=alice;
		}
		return arr;
	}
	
	
	//if nums length is Odd
	 public static int[] minimumNumberGame1(int[] nums) {
	        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
	        int[] arr = new int[nums.length];
	        
	        for (int num : nums) {
	            queue.add(num);
	        }
	        
	        int i = 0;
	        while (!queue.isEmpty()) {
	            int alice = queue.poll();  // smallest
	            if (!queue.isEmpty()) {
	                int bob = queue.poll(); // second smallest
	                arr[i++] = bob;
	                arr[i++] = alice;
	            } else {
	                arr[i++] = alice; // odd length safety
	            }
	        }
	        return arr;
	    }
	

	 public static int[] minimumNumberGame2(int[] nums) {
		 Arrays.sort(nums);
		 int[] arr = new int[nums.length];
		 
		 for(int i=0;i<nums.length;i+=2) {
			 	int alice = nums[i];
	            int bob = nums[i + 1];
	            arr[i] = bob;
	            arr[i + 1] = alice;
		 }
		return arr;
	 }
	 
	 public static int incRemovableSubArray(int[] nums) {
		 int count=0;
		 int n=nums.length;
		 for(int i=0;i<n;i++) {
			 for(int j=i;j<n;j++) {
				if(isIncreasingSubArray(nums, i, j)) {
					count++;
				}
			 }
		 }
		return count;
	 }
	 
	 public static boolean isIncreasingSubArray(int[] nums,int start,int end) {
		 int len=nums.length;
		 int prev=0;
		 for(int i=0;i<len;i++) {
			 if(i<=end && i>=start) {
				 continue;
			 }
			 if(nums[i]<=prev) {
				 return false;
			 }
			 prev=nums[i];
			 
		 }
		return true;
	 }
	 
	 public static List<String> stringMatching(String[] words){
		List<String> list = new ArrayList<String>();
		for(int i=0;i<words.length;i++) {
			for(int j=0;j<words.length;j++) {
				String word1=words[i];
				String word2=words[j];
				if(word1.length()>=word2.length()) {
					continue;
				}
				if(isSubstring(words[i], words[j])) {
					list.add(words[i]);
					break;
				}
				
			}
		}
		return list;
	 }
	public static boolean isSubstring(String word1,String word2) {
		if(word2.contains(word1)) {
			return true;
		}
		return false;
	}
	
	public static boolean makeEqual(String[] words) {
		int[] frq= new int[26];
		if(words.length==1) {
			return true;
		}
		for(String word:words) {
			for(int i=0;i<word.length();i++) {
				frq[word.charAt(i)-'a']++;
			}
		}
		for(int i:frq) {
			if(i%(words.length)!=0) {
				return false;
			}
		}
		return true;
	}
	public static int largestSubsrting(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int max=-1;
		for(int right=0;right<s.length();right++) {
			char ch = s.charAt(right);
			if(map.containsKey(ch)) {
				int left = map.get(ch);
				max=Math.max(max, right-left-1);
			}else {
				map.put(ch, right);
			}
		}
		return max;
	}
	 public static boolean hasTrailingZeros(int[] nums) {
		 int evenCount=0;
		 for(int num:nums) {
			 if(num%2==0) {
				 evenCount++;
			 }
			 if(evenCount>=2) {
				 return true;
			 }
		 }
		return false;
	 }
	 public static boolean hasTrailingZeros1(int[] nums) {
		 int evenCount=0;
		 for(int num:nums) {
			 if((num & 1)==0) {
				 evenCount++;
			 }
			 if(evenCount>=2) {
				 return true;
			 }
		 }
		return false;
	 }
	 
	 public static int findContentChildren(int[] g,int[] s) {
		 Arrays.sort(g);
		 Arrays.sort(s);
		 int children=0;
		 int cockie=0;
		 while(cockie<s.length && children<g.length) {
			 if(g[children]<=s[cockie]) {
				 children++;
			 }
			 cockie++;
		 }
		return children;
	 }
	 
	public static boolean towHalfAlike(String s) {
		 s=s.toLowerCase();
		 String a=s.substring(0,s.length()/2);
		 String b= s.substring(s.length()/2);
		 int aCount=countVowel(a);
		 int bCount=countVowel(b);
		 
		 return aCount==bCount;
	 }
	private static int countVowel(String a) {
		int count=0;
		String vowles="aeiou";
		for(int i=0;i<a.length();i++) {
			if(vowles.indexOf(a.charAt(i))!=-1) {
				count++;
			}
			/*
			 * if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') { count++;
			 * }
			 */ // faster approach
		}
		
		return count;
	}
	
	

	private static int countVowel1(String a) {
		Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');
	    int count = 0;
	    for (char ch : a.toCharArray()) {
	        if (VOWELS.contains(ch)) {
	            count++;
	        }
	    }
	    return count;
	}


	public static void main(String[] args) {
		String s= "book";
		System.out.println(towHalfAlike(s));
		
	}

}
