package com.faizan.leetcode.revision5;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DynamicProRev {
	
	 public static String longestPalindrome(String s) {
		 String longest="";
		 
		 for(int i=0;i<s.length();i++) {
			 for(int j=i;j<s.length();j++) {
				 String sub=s.substring(i,j+1);
				 if(checkPlaind(sub) && sub.length()>longest.length()) {
					 longest=sub;
				 }
			 }
		 }
		return longest;
	 }

	private static boolean checkPlaind(String sub) {
		int start=0,end=sub.length()-1;
		while(start<end) {
			if(sub.charAt(end)!=sub.charAt(start)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
	 public static List<String> generateParenthesis(int n) {
		 List<String> list = new ArrayList<String>();
		 backtrack(list,"",n,n);
		return list;
	 }
	private static void backtrack(List<String> list, String current, int open, int close) {
		if(open==0 && close==0) {
			list.add(current);
			return;
		}
		if(open>0) {
			backtrack(list, current+"(", open-1, close);
		}
		if(close>open) {
			backtrack(list, current+")", open, close-1);
		}
		
	}
	public static int maxSubArray(int[] nums) {
		int currentSum=0,maxSum=0;
		 for(int i=0;i<nums.length;i++) {
			 currentSum = Math.max(nums[i], currentSum+nums[i]);
			 maxSum=Math.max(maxSum, currentSum);
		 }
		return maxSum;
	}
	 public static int uniquePaths(int m, int n) {
		 int[][] dp = new int[m][n];
		 for(int i=0;i<m;i++) {
			 dp[i][0]=1;
		 }
		 for(int i=0;i<n;i++) {
			 dp[0][i]=1;
		 }
		 for(int i=1;i<m;i++) {
			 for(int j=1;j<n;j++) {
				 dp[i][j]=dp[i-1][j]+dp[i][j-1];
			 }
		 }
		 return dp[m-1][n-1];
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
			 if(min>prices[i]) {
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
	 
	 public static int longestParantesis(String s) { // not correct
		 if(s.length()==0) {
			 return 0;
		 }
		 Stack<Character> stack = new Stack<Character>();
		 int maxLen=0, count=0;;
		 for(int i=0;i<s.length();i++) {
			 char ch = s.charAt(i);
			 
			 if(stack.isEmpty() && ch=='(') {
				 stack.push(ch);
			 }else if(!stack.isEmpty() && stack.peek()=='(' && ch==')') {
				 count +=2;
				 stack.pop();
			 }else {
				 count=0;
			 }
			 maxLen = Math.max(maxLen, count);
			 
		 }
		return maxLen;
	 }
	 
	 public static int longestValidParentheses(String s) {
	        Stack<Integer> stack = new Stack<>();
	        stack.push(-1); // base index

	        int maxLen = 0;

	        for (int i = 0; i < s.length(); i++) {
	            if (s.charAt(i) == '(') {
	                stack.push(i);
	            } else {
	                stack.pop();

	                if (stack.isEmpty()) {
	                    stack.push(i); // reset base
	                } else {
	                    maxLen = Math.max(maxLen, i - stack.peek());
	                }
	            }
	        }
	        return maxLen;
	    }
	 

	public static void main(String[] args) {
	String s="())";
	System.out.println(longestValidParentheses(s));
	}

}
