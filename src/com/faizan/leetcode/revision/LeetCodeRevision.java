package com.faizan.leetcode.revision;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class LeetCodeRevision {
	
	public static int romanToInt(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('M', 1000);
		int result =0;
		
		int i,j;
		for(i=0,j=1;j<s.length();i++,j++) {
			if(map.get(s.charAt(i))>=map.get(s.charAt(j))) {
				result +=map.get(s.charAt(i));
			}else {
				result -=map.get(s.charAt(i));
			}
		
		}
		result +=map.get(s.charAt(i));
		return result;
	}
	
	public static boolean validParan(String s) {
		Stack<Character> stack= new Stack<Character>();
		
		for(int i=0;i<s.length();i++) {
			char ch=s.charAt(i);
			if(ch=='(' || ch=='{' || ch=='[') {
				stack.push(ch);
			}else if(!stack.isEmpty() && ch==')' && stack.peek()=='(') {
				stack.pop();
			}else if(!stack.isEmpty() && ch=='}' && stack.peek()=='{') {
				stack.pop();
			}else if(!stack.isEmpty() && ch==']' && stack.peek()=='[') {
				stack.pop();
			}else {
				return false;
			}
		}
		return stack.isEmpty();
		
	}
	public static boolean validParan1(String s) {
		boolean isValid=false;
		for(int i=1;i<s.length();i++) {
			if(s.charAt(i) ==')' && s.charAt(i-1)=='(') {
				isValid=true;
			}else if(s.charAt(i) =='}' && s.charAt(i-1)=='{'){
				isValid=true;
			}else if(s.charAt(i) ==']' && s.charAt(i-1)=='[') {
				isValid =true;
			}else {
				isValid=false;
			}
		}
		return isValid;
	}
	
	public static int serachInsertPosition(int[] nums,int target) {
		int left=0;
		int right=nums.length-1;
		int mid=0;
		while(left<right) {
			mid=left+(right-left)/2;
			if(nums[mid]==target) {
				return mid;
			}else if(target<nums[mid]) {
				right=mid-1;
			}else {
				left=mid+1;
			}
		}
		return left;
	}
	public static int lengthOflastWord(String s) {
		String[] words = s.split(" ");
		return words[words.length-1].length();
	}
	
	public static int[] mergeTwoSortedArray(int[] array1,int m,int[] array2,int n) {
		int p1=m-1;
		int p2=n-1;
		int p3=array1.length-1;
		while(p3>=0) {
			int element1,elemne2;
			element1=(p1>=0)?array1[p1] :Integer.MIN_VALUE;
			elemne2 =(p2>=0)?array2[p2] :Integer.MIN_VALUE;
			if(element1>elemne2) {
				array1[p3]=element1;
				p3--;
				p1--;
			}else {
				array1[p3]=elemne2;
				p3--;
				p2--;
			}
		}
	    return array1;
	}
	
	public static int maxProfit(int[] prices) {
		int maxProfit=0;
		int min=Integer.MAX_VALUE;
		for(int i=0;i<prices.length;i++) {
			if(prices[i]<min) {
				min=prices[i];
			}
			System.out.println(prices[i]+" "+min);
			int currenProfit=prices[i]-min;
			if(currenProfit>maxProfit) {
				maxProfit=currenProfit;
			}
		}
		return maxProfit;
	}
	
	public static void maxProfitTwoLoops(int[] prices) {

	    // First loop: Find the minimum price and its index
	    int minPrice = Integer.MAX_VALUE;
	    int minIndex = -1;
	    for (int i = 0; i < prices.length; i++) {
	        if (prices[i] < minPrice) {
	            minPrice = prices[i];
	            minIndex = i;
	        }
	    }

	    // Second loop: Find the max price after the min index
	    int maxPrice = minPrice;
	    for (int i = minIndex + 1; i < prices.length; i++) {
	        if (prices[i] > maxPrice) {
	            maxPrice = prices[i];
	        }
	    }

	    int maxProfit = maxPrice - minPrice;
	    System.out.println("Max Profit: " + maxProfit);
	}

	
	
	public static void main(String[] args) {
		int[] preices= {7,1,5,3,6,4};
		maxProfit(preices);
		//maxProfitTwoLoops(preices);
		//int[] array1= {1,2,3,0,0,0};
		//int m=3;
		//int n=3;
		//int[] array2= {2,5,6};
		//System.out.println(Arrays.toString(mergeTwoSortedArray(array1, m, array2, n)));
		//System.out.println(lengthOflastWord("Hello World"));
		//int[] arrray = {1,3,6,8,9};
		//int target=2;
		//System.out.println(serachInsertPosition(arrray, target));
		//String s="(){}[](";
		//System.out.println(validParan1(s));
		//String s="IV";
		//System.out.println(romanToInt(s));
		
	}

}
