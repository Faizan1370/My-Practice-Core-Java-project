package com.faizan.leetcode.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LeetCodeRevision8 {
	
	public static int maxSum(int[] nums) {
		int maxSum=0;
		for(int i=0;i<nums.length;i++) {
			for(int j=i+1;j<nums.length;j++) {
				if(maxDigit(nums[i])== maxDigit(nums[j])) {
					maxSum=Math.max(maxSum, nums[i]+nums[j]);
				}
			}
		}
		return maxSum;
	}
	
	private static int maxDigit(int num) {
		int max=0;
		while(num!=0) {
			if(num%10>max) {
				max=num%10;
			}
			num=num/10;
		}
		return max;
	}
	
	public static boolean checkAcronym(String[] words,String s) {
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<words.length;i++) {
			builder.append(words[i].charAt(0));
		}
		
		return builder.toString().equals(s);
	}
	
	public static int minimuOpration(List<Integer> nums,int k) {
		int count=0;
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i=1;i<=k;i++) {
			set.add(i);
		}
		
		for(int i=nums.size()-1;i>=0;i--) {
			count++;
			if(set.contains(nums.get(i))) {
				
				set.remove(nums.get(i));
				if(set.isEmpty()) {
					break;
				}
			}
		}
		return count;
	}
	
	public static int nonDivisibleDiff(int n,int m) {
		int sumNum1=0;
		int sumNum2=0;
		for(int i=1;i<=n;i++) {
			if(i%m!=0) {
				sumNum1 +=i;
			}else {
				sumNum2 +=i;
			}
		}
		
		return sumNum1-sumNum2;
	}
	
	public static boolean wordPattern(String pattern,String s) {
		String[] words = s.split(" ");
		HashMap<Character, String> char_map= new HashMap<Character, String>();
		HashMap<String, Character>  word_map= new HashMap<String, Character>();
		
		for(int i=0;i<words.length;i++) {
			String word=words[i];
			char ch=pattern.charAt(i);
			if(!char_map.containsKey(ch)) {
				if(word_map.containsKey(word)) {
					return false;
				}else {
					char_map.put(ch, word);
					word_map.put(word, ch);
				}
			}else {
				if(!char_map.get(ch).equals(word)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static int[] leftSumRightSumDiff(int[] nums) {
		int[] l_sum= new int[nums.length];
		int[] r_sum= new int[nums.length];
		
		int sum=0;
		for(int i=1;i<nums.length;i++) {
			sum +=nums[i-1];
			l_sum[i]=sum;
		}
		sum=0;
		for(int i=nums.length-1;i>=0;i--) {
			r_sum[i]=sum;
			sum +=nums[i];
		}
		
		for(int i=1;i<nums.length;i++) {
			nums[i]=Math.abs(l_sum[i]-r_sum[i]);
		}
		return nums;
	}
	public static List<List<Integer>> findDiffrence(int[] nums1,int[] nums2){
		return Arrays.asList(getCommonValues(nums1, nums2),getCommonValues(nums2, nums1));
	}
	public static List<Integer> getCommonValues(int[] nums1,int[] nums2){
		HashSet<Integer> set1= new HashSet<Integer>();
		HashSet<Integer> set2= new HashSet<Integer>();
		
		for(int num:nums2) {
			set2.add(num);
		}
		for(int num :nums1) {
			if(!set2.contains(num)) {
				set1.add(num);
			}
		}
		
		return new ArrayList<Integer>(set1);
	}
	public static int noOfEmp(int[] hours) {
		int count=0;
		for(int i=0;i<hours.length;i++) {
			if(hours[i]>=2) {
				count++;
			}
		}
		return count;
	}
	
	public static boolean longestContigous(String s) {
		int oneCount=0;
		int zeroCount=0;
		int currenZeroCount=0,currentOnceCount=0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='1') {
				currentOnceCount++;
				currenZeroCount=0;
				oneCount=Math.max(oneCount, currentOnceCount);
				
			}else {
				currenZeroCount++;
				currentOnceCount=0;
				zeroCount=Math.max(zeroCount, currenZeroCount);
			}
		}
		return oneCount>zeroCount;
	}
	
	public static int contigousChar(String s) {
		if(s==null || s.length()==0) {
			return 0;
		}
		int current=1;
		int max=1;
		
		for(int i=1;i<s.length();i++) {
			if(s.charAt(i)==s.charAt(i-1)) {
				current++;
			}else {
				current=1;
			}
			max=Math.max(max, current);
		}
		return max;
	}
	
	public static void main(String[] args) {
	String s="faizan";
	System.out.println(contigousChar(s));
	}

}
