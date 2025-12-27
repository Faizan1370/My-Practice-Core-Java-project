package com.faizan.leetcode.revision5;

import java.util.HashSet;

public class Revi2 {
	
	public static int accountBalnce(int purchaseAmount) {
		int balalnceAmount=0;
		int r=purchaseAmount %10;
		if(r>=5) {
			balalnceAmount =10-r;
			purchaseAmount +=balalnceAmount;
		}else {
			purchaseAmount -=r;
		}
		
		return 100-purchaseAmount;
		
	}
	
	public static int maxPairSum(int[] nums) {
		int maxSum=0;
		for(int i=0;i<nums.length;i++) {
			for(int j=i+1;j<nums.length;j++) {
				if(maxDigit(nums[i])==maxDigit(nums[j])) {
					maxSum =Math.max(maxSum, nums[i]+nums[j]);
				}
			}
			
		}
		return maxSum;
	}
	private static int maxDigit(int num) {
		int maxDigit=0;
		while(num>0) {
			int r=num %10;
			maxDigit=Math.max(maxDigit, r);
			num=num/10;
		}
		return maxDigit;
	}
	public static boolean acronym(String[] words,String s) {
		StringBuilder builder = new StringBuilder();
		for(String word:words) {
			builder.append(word.charAt(0));
		}
		return builder.toString().equals(s);
	}
	
	public static int minOperation(int[] nums,int k) {
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i=1;i<=k;i++) {
			set.add(i);
		}
		int count=0;
		for(int num:nums) {
			if(set.contains(num)) {
				set.remove(num);
			}
			if(set.isEmpty()) {
				return count;
			}
			count++;
			
		}
		return count;
	}
	public static int sumDiff(int n,int m) {
		int sum1=0,sum2=0;
		for(int num=1;num<=n;num++) {
			if(num % m ==0) {
				sum2 +=num;
			}else {
				sum1 +=num;
			}
		}
		return sum1-sum2;
	}
	public static int highestAltitude(int[] gain) {
	int currentgain=0,maxGain=0;
	for(int ga :gain) {
		currentgain = currentgain+ga;
		maxGain= Math.max(currentgain, maxGain);
	}
	return maxGain;
	}

	public static void main(String[] args) {
		int[] gain= {-5,1,5,0,-7};
		System.out.println(highestAltitude(gain));
	}
}
