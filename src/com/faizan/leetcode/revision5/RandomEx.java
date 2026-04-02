package com.faizan.leetcode.revision5;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RandomEx {
	
	public static boolean palinDrom(int n) {
		if(n<0) {
			return false;
		}
		int temp=n;
		int sum=0;
		while(n>0) {
			int r=n %10;
			sum =sum *10+r;
			n=n/10;
		}
		if(sum==temp) {
			return true;
		}
		return false;
	}
	public static int romanToInteger(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 100);
		int sum=0;
		int i,j;
		for(i=0,j=1;j<s.length();i++,j++) {
			if(map.get(s.charAt(i))>map.get(s.charAt(j))) {
				sum +=map.get(s.charAt(i));
			}else {
				sum -=map.get(s.charAt(i));
			}
		}
		sum += map.get(s.charAt(i));
		return sum;
		
	}
	public static int romanToInt(String s) {
		Map<Character, Integer> map = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);
		int sum = 0;
		for(int i=0;i<s.length();i++) {
			int value =map.get(s.charAt(i));
			
			if(i+1 <s.length() && value<map.get(s.charAt(i+1))) {
				sum -= value;
			} else {
				sum += value;
			}
		}
		return sum;
	}
	
	public static boolean validParan(String s) {
		Stack<Character> stack = new Stack<Character>();
		
		for(int i=0;i<s.length();i++) {
			char ch=s.charAt(i);
			if(ch =='(' || ch=='{' || ch=='[') {
				stack.push(ch);
			}else {
				if(stack.isEmpty()) {
					return false;
				}
				char top=stack.pop();
				if (ch == ')' && top != '(')
					return false;
				if (ch == '}' && top != '{')
					return false;
				if (ch == ']' && top != '[')
					return false;
			}
		}
		return stack.isEmpty();
	}
	
	public static int bestTimeBuySell(int[] prices) {
		int min=Integer.MAX_VALUE;
		int max=0;
		for(int i=0;i<prices.length;i++) {
			if(prices[i]<min) {
				min=prices[i];
			}
			int profit = prices[i]-min;
			max =Math.max(max, profit);
		}
		return max;
	}
	public static void main(String[] args) {
		int n=121;
		System.out.println(palinDrom(n));
	}

}
