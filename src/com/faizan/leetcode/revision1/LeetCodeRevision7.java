package com.faizan.leetcode.revision1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LeetCodeRevision7 {

	public static boolean isGood(int[] nums) {
		Arrays.sort(nums);
		int max = nums[nums.length - 1];
		if (nums.length != max + 1) {
			return false;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i]) + 1);
			} else {
				map.put(nums[i], 1);
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>(map.values());
		int count = 0;
		for (int num : list) {
			if (num >= 2) {
				count++;
			}
		}
		if (map.get(max) == 2 && count == 1) {
			return true;
		}
		return false;
	}

	public static List<String> splitWordsbySeprator(List<String> words, String seprator) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < words.size(); i++) {
			String[] wordArray = words.get(i).split("[" + seprator + "]");
			for (String str : wordArray) {
				list.add(str);
			}
		}
		return list;
	}
	public static int roundedAmount(int purchaseAmount) {
		int rem =purchaseAmount %10;
		int balance= 10-rem;
		if(rem<5) {
			purchaseAmount=purchaseAmount-rem;
		}else {
			purchaseAmount =purchaseAmount+balance;
		}
		return 100-purchaseAmount;
		
	}
	public static int maxSum(int[] nums) {
		int maxSum=0;
		for(int i=0;i<nums.length;i++) {
			for(int j=i+1;j<nums.length;j++) {
				if(maxDigit(nums[i])==maxDigit(nums[j])) {
					maxSum=Math.max(maxSum, nums[i]+nums[j]);
				}
			}
		}
		return maxSum;
	}
	private static int maxDigit(int num) {
		int maxNum=0;
		while(num!=0) {
			 if(num%10>maxNum) {
				 maxNum=num%10;
			 }
			 num=num/10;
		}
		return maxNum;
	}
	
	public static boolean acronym(String[] words,String str) {
		StringBuffer buffer = new StringBuffer();
		for(String word:words) {
			buffer.append(word.charAt(0)); 
		}
		return buffer.toString().equals(str);
	}
	
	public static int minimumOpration(int[] nums,int k) {
		HashSet<Integer> set= new HashSet<Integer>();
		for(int i=1;i<=k;i++) {
			set.add(i);
		}
		int count=0;
		for(int i=0;i<nums.length;i++) {
			if(set.contains(nums[i])) {
				set.remove(nums[i]);
				if(set.isEmpty()) {
					break;
				}
			}
		
			count++;
			
		}
		return count;
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
	
	public static int sumDiff(int n,int m) {
		int sum1=0;
		int sum2=0;
		
		for(int i=1;i<=n;i++) {
			if(i%m!=0) {
				sum1+=i;
			}else {
				sum2+=i;
			}
		}
		
		return (sum1-sum2);
	}
	
	public static int largetGain(int[] gain) {
		int currentGain=0,largestGain=0;
		for(int i=0;i<gain.length;i++) {
			currentGain=currentGain+gain[i];
			largestGain=Math.max(largestGain, currentGain);
		}
		return largestGain;
	}
	public static int[] findIndices(int[] nums,int indexDiff,int valueDiff) {
		
		for(int i=0;i<nums.length;i++) {
			for(int j=i+1;j<nums.length;j++) {
				if(Math.abs(i-j)>=indexDiff && Math.abs(nums[i]-nums[j])>=valueDiff) {
					return new int[] {i,j};
				}
			}
		}
		 return new int[] {-1,-1};
	}
	
	public static String toLower(String str) {
		return str.toLowerCase();
	}

	public static void main(String[] args) {
	  System.out.println(toLower("HRtf"));
	}

}
