package com.faizan.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode10 {
	
	public static boolean isAcronym(List<String> words,String s) {
		StringBuilder builder = new StringBuilder();
		for(String str:words) {
		 builder.append(str.charAt(0));	
		}
		if(builder.toString().equals(s)) {
			return true;
		}
		return false;
	}
	
	public static int minOperation(List<Integer> nums,int k) {
		Set<Integer> set = new HashSet<Integer>();
		int count =0;
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
	
	public static int diffrenceOfSum(int n,int m) {
		int num1=0,num2=0;
		for(int i=1;i<=n;i++) {
			if(i%m!=0) {
				num1 +=i;
			}else {
				num2 +=i;
			}
		}
		return num1-num2;
	}
	
	public static int largestGain(int[] gain) {
		int currentgain=0;
		int max=0;
		for(int i=0;i<gain.length;i++) {
			currentgain +=gain[i];
			max=Math.max(currentgain, max);
		}
		return max;
				
	}
	
	public static int[] findIndices(int[] nums,int indexDiff,int valDiff) {
		for(int i=0;i<nums.length;i++) {
			for(int j=0;j<nums.length;j++) {
				if(Math.abs(i-j)>=indexDiff && Math.abs(nums[i]-nums[j])>=valDiff) {
					return new int[] {i,j};
				}
			}
		}
		return new int[] {-1,-1};
	}
	
	public static String toLowerCase(String s) {
		return s.toLowerCase();
	}
	
	public static void main(String[] args) {
		System.out.println(toLowerCase("Faizan"));
		//int[] nums= {5,1,4,1};
		//int indexDiff=2,valDiff=4;
		//System.out.println(Arrays.toString(findIndices(nums, indexDiff, valDiff)));		
		//int[] gain= {-5,1,5,0,-7};
		//System.out.println(largestGain(gain));
		//int n=10,m=3;
		//System.out.println(diffrenceOfSum(n, m));
		//List<Integer> list = Arrays.asList(3,1,5,4,2);
		//int k=2;
		//System.out.println(minOperation(list, k));
		//List<String> words =Arrays.asList("alce","bob","charlie");
		//String s ="abc";
		//System.out.println(isAcronym(words, s));
	}

}
