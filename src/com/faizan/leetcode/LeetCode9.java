package com.faizan.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LeetCode9 {
	
	public static int distanceTravled(int mainTank,int additionalTank) {
		int tavelledDistance=0;
		
		while(mainTank>=5 && additionalTank>0) {
			tavelledDistance +=50;
			mainTank =(mainTank -5)+1;
			additionalTank--;
		}
		tavelledDistance +=mainTank*10;
		return tavelledDistance;
	}
	
	public static boolean isGood(int[] nums) {
		Arrays.sort(nums);
		int max = nums[nums.length-1];
		if(nums.length !=(max+1)) {
			return false;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0;i<nums.length;i++) {
			if(map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i])+1);
			}else {
				map.put(nums[i], 1);
			}
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>(map.values());
		int count=0;
		for(Integer num:list) {
			if(num>=2) {
				count++;
			}
		}
		if(map.get(max)==2 && count==1) {
			return true;
		}
		return false;
	}
	
	public static List<String> splitWordsbySeprator(List<String> words,String seprator){
		List<String> list = new ArrayList<String>();
		for(int i =0;i<words.size();i++) {
			String[] wordArray =words.get(i).split("["+seprator+"]");
			for(String word:wordArray) {
				if(word !="") {
					list.add(word);
				}
			}
		}
		return list;
	}
	
	public static int roundedAmount(int purchaseAmount) {
		int rem=purchaseAmount %10;
		int balance= 10-rem;
		if(rem<5) {
			purchaseAmount = purchaseAmount-rem;
		}else {
			purchaseAmount = purchaseAmount+balance;
		}
		return 100-purchaseAmount;
	}
	public static int maxSum(int[] nums) {
		int max=-1;
		for(int i=0;i<nums.length;i++) {
			for(int j=i+1;j<nums.length;j++) {
				if(maxDigitInNum(nums[i])== maxDigitInNum(nums[j])) {
					max= Math.max(max, (nums[i]+nums[j]));
				}
			}
		}
		return max;
	}
	
	private static int maxDigitInNum(int num) {
		int maxDigit=0;
		while(num !=0) {
			if((num%10)>maxDigit) {
				maxDigit =num %10;
			}
			num=num/10;
		}
		return maxDigit;
	}
	public static void main(String[] args) {
		int[] nums= {51,71,17,24,42};
		System.out.println(maxSum(nums));
		//int purchaseAmount=16;
		//System.out.println(roundedAmount(purchaseAmount));
		//List<String> words= Arrays.asList ("first.second.third","four.five","six");
		//String seprator =".";
		//System.out.println(splitWordsbySeprator(words, seprator));
		//int[] nums= {1,2,3,3};
		//System.out.println(isGood(nums));
		//System.out.println(distanceTravled(5, 10));
	}

}
