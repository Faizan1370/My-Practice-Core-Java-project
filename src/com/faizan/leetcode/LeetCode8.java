package com.faizan.leetcode;

import java.util.Arrays;
import java.util.HashSet;

public class LeetCode8 {
	public static int vowelStrings(String[] words,int left,int right) {
		int count=0;
		for(int i=left;i<=right;i++) {
		 if(checkVowel(words[i])) {
			 count++;
		 }
		}
		return count;
	}
	
	private static boolean checkVowel(String str) {
		if(str.charAt(0)=='a' || str.charAt(0)=='e' || str.charAt(0)=='i' || str.charAt(0)=='o' || str.charAt(0)=='u') {
			if(str.charAt(str.length()-1)=='a' || str.charAt(str.length()-1)=='e' ||str.charAt(str.length()-1)=='i' ||
					str.charAt(str.length()-1)=='o' || str.charAt(str.length()-1)=='u') {
				return true;
			}
		}
		return false;
	}
	
	public static int findArrivalDelayedTime(int arrivalTime,int delayedTime) {
		int arrival=arrivalTime+delayedTime;
		if(arrival %24 >=0) {
			arrival=arrival % 24;
		}
		return arrival;
	}
   public static int sumOfMultiples(int n) {
	   int sum=0;
	   for(int i=1;i<=n;i++) {
		   if(i % 3 ==0 || i % 5 ==0 || i % 7 ==0) {
			   sum +=i;
		   }
	   }
	return sum;
   }
   
   public static int isWinner(int[] player1,int[] player2) {
	   int p1_score=countScore(player1);
	   int p2_score =countScore(player2);
	   if(p1_score>p2_score) {
		   return 1;
	   }else if(p2_score>p1_score) {
		   return 2;
	   }
	return 0;
   }
   
   private static int countScore(int[] arr) {
	   int score=0;
	   for(int i=0;i<arr.length;i++) {
		   if(i==1) {
			   if(arr[i-1]==10) {
				 score += (2*arr[i]);  
			   }else {
				   score +=arr[i];
			   }
		   }else if(i>1) {
			   if(arr[i-1]==10 ||arr[i-2]==10) {
					 score += (2*arr[i]);  
				   }else {
					   score +=arr[i];
				   } 
		   }else {
			   score +=arr[i];
		   }
	   }
	return score;
	   
   }
   
   public static int[] distinctDiffrenceArray(int[] nums) {
	  
	   
	   int[] diff = new int[nums.length];
	   
	   for(int i=0;i<nums.length;i++) {
		   HashSet<Integer> prefix = new HashSet<Integer>();
		   for(int j=0;j<=i;j++) {
			   prefix.add(nums[j]);
		   }
		   HashSet<Integer> suffix = new HashSet<Integer>();
		   for(int j=i+1;j<nums.length;j++) {
			   suffix.add(nums[j]);
		   }
		   diff[i] =prefix.size()-suffix.size();
	   }
	return diff;
	   
	   
   }
	
	public static void main(String[] args) {
		int[] nums= {1,2,3,4,5};
		System.out.println(Arrays.toString(distinctDiffrenceArray(nums)));
		//int[] player1= {4,10,7,9};
		//int[] player2= {6,5,2,3};
		//System.out.println(isWinner(player1, player2));
		//System.out.println(sumOfMultiples(7));
		//int arrivalTime =13, delayedTime=11;
		//System.out.println(findArrivalDelayedTime(arrivalTime, delayedTime));
		//String[] words = {"are","any","u"};
		//int left=0,right=2;
		//System.out.println(vowelStrings(words, left, right));
	}
}
