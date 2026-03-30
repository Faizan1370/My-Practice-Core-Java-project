package com.faizan.leetcode.revision5;

import java.util.ArrayList;
import java.util.List;

public class DynmicRev2 {
	
	 public static String longestPalindrome(String s) {
		 String longest="";
		 
		 for(int i=0;i<s.length();i++) {
			 for(int j=i;j<s.length();j++) {
				 String sub=s.substring(i,j+1);
				 if(checkPalin(sub) && longest.length()<sub.length()) {
					 longest=sub;
				 }
			 }
		 }
		return longest;
	 }

	private static boolean checkPalin(String sub) {
		int start=0,end=sub.length()-1;
		while(start<end) {
			if(sub.charAt(start)!=sub.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
	public static List<String> generateParenthesis(int n) {
		 ArrayList<String> list = new ArrayList<String>();
		 backtracking(list,"",n,n);
		return list;
	 }
	private static void backtracking(ArrayList<String> list, String current, int open, int close) {
		if(open==0 && close==0) {
			list.add(current);
			return;
		}
		if(open>0) {
			backtracking(list, current+"(", open-1, close);
		}
		if(close>open) {
			backtracking(list, current +")", open, close-1);
		}
	}
	 public static int climbStairs(int n) {
		 int a=0,b=1;
		 int c=0;
		 for(int i=1;i<=n;i++) {
			 c=a+b;
			 a=b;
			 b=c;
		 }
		return c;
	 }
	 
	 public static int maxProfit(int[] prices) {
		 int min=Integer.MAX_VALUE;
		 int maxPrfit=0;
		 for(int i=0;i<prices.length;i++) {
			 if(prices[i]<min) {
				 min=prices[i];
			 }
			 int currentProfit=prices[i]-min;
			 maxPrfit=Math.max(maxPrfit, currentProfit);
		 }
		return maxPrfit;
	 }
	 public static int maxProfit2(int[] prices) {
		 int profit=0;
		 for(int i=1;i<prices.length;i++) {
			 if(prices[i]>prices[i-1]) {
				 profit += prices[i]-prices[i-1];
			 }
		 }
		return profit;
	 }

	public static void main(String[] args) {
		int n=3;
		System.out.println(generateParenthesis(n));
	}

}
