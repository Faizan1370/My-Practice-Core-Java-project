package com.faizan.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

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
	public static void main(String[] args) {
		String[] words= {"abc","aabc","bc"};
		System.out.println(makeEqual(words));
		
	}

}
