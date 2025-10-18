package com.faizan.leetcode.revision4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCodeRevision1 {
	
	public static boolean checkPalindrome(int num) {
		int temp=num;
		int sum=0;
		while(num>0) {
			int r=num%10;
			  sum = sum * 10 + r; 
		    num=num/10;
		}
		if(sum==temp) {
			return true;
		}else {
			return false;
		}
	}
	
	public static int romanToInt(String romanString) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('M', 1000);
		
		int result=0;
		char[] chars =romanString.toCharArray();
		int i,j;
		for(i=0,j=1;j<chars.length;i++,j++) {
			if(map.get(chars[i])>=map.get(chars[j])) {
				result += map.get(chars[i]);
			}else {
				result -=map.get(chars[i]);
			}
		}
		result +=map.get(chars[i]);
		return result;
	}
	public static int romanToInt1(String romanString) {
	    Map<Character, Integer> map = new HashMap<>();
	    map.put('I', 1);
	    map.put('V', 5);
	    map.put('X', 10);
	    map.put('L', 50);
	    map.put('C', 100);
	    map.put('D', 500);
	    map.put('M', 1000);

	    int result = 0;

	    for (int i = 1; i < romanString.length(); i++) {
	        int curr = map.get(romanString.charAt(i));
	        int prev = map.get(romanString.charAt(i - 1));

	        if (prev < curr) {
	            result -= prev; // subtract previous if smaller
	        } else {
	            result += prev; // otherwise, add previous
	        }
	    }

	    // finally, add the last character value
	    result += map.get(romanString.charAt(romanString.length() - 1));
	    return result;
	}
	
	public static boolean validParanthesis(String s) {
		Stack<Character> stack = new Stack<Character>();
		for(int i=0;i<s.length();i++) {
			char ch= s.charAt(i);
			if(ch =='(' || ch=='{' || ch=='[') {
				stack.push(ch);
			}else if(!stack.isEmpty() && ch==')' && stack.peek()=='('){
               stack.pop();
			}else if(!stack.isEmpty() && ch=='}' && stack.peek()=='{'){
				 stack.pop();
			}else if(!stack.isEmpty() && ch==']' && stack.peek()=='['){
				 stack.pop();
			}
		}
		return stack.isEmpty();
	}
	
	public static int insertPosition(int[] nums,int target) {
		int start=0,end=nums.length-1;
		int mid=0;
		while(start<=end) {
			mid=start+(end-start)/2;
			if(nums[mid]==target) {
				return mid;
			}else if(nums[mid]>target) {
				end=mid-1;
			}else {
				start=mid+1;
			}
		}
		return start;
		
	}
	
	public static int[] mergeSortedArray(int[] nums1,int m,int n,int[] nums2) {
		int p1=m-1;
		int p2=n-1;
		int p3=nums1.length-1;
		while(p3>0) {
			int element1 =(p1>=0)?nums1[p1]:Integer.MIN_VALUE;
			int element2 =(p2>=0)?nums2[p2]:Integer.MIN_VALUE;
			if(element1>element2) {
				nums1[p3]=element1;
				p3--;
				p1--;
			}else {
				nums1[p3]=element2;
				p3--;
				p2--;
			}
		}
		return nums1;
	}
	
	public static int bestTimeBuySel(int[] prices) {
		int min=Integer.MAX_VALUE;
		int maxProfit=0;
		for(int i=0;i<prices.length;i++) {
			if(prices[i]<min) {
				min=prices[i];
			}
			int currentProfit=prices[i]-min;
			if(currentProfit>maxProfit) {
				maxProfit=currentProfit;
			}
		}
		return maxProfit;
	}
	public static boolean validPalendrome(String s) {
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<s.length();i++) {
			if(Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i))) {
				builder.append(s.charAt(i));
			}
		}
		String neStr=builder.toString();
		neStr=neStr.toLowerCase();
		int start=0,end=neStr.length()-1;
		while(start<end) {
			if(neStr.charAt(start)!=neStr.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
	
	public static int singleNumber(int[] nums) {
		int result=0;
		for(int num:nums) {
			result ^=num;
		}
		return result;
	}
	 public static int majorityElement(int[] nums) {
		 int n=nums.length;
		return Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		 .entrySet()
		 .stream()
		 .filter(entry->entry.getValue()>n/2)
		 .map(entry->entry.getKey())
		 .findFirst()
		 .get();
	 }
	 
	 public static boolean containsDuplicate(int[] nums,int k) {
		 
		 for(int i=0;i<nums.length;i++) {
			 for(int j=i+1;j<nums.length;j++) {
				 if(nums[i]==nums[j] && Math.abs(i-j)<=k) {
					 return true;
				 }
			 }
			
		 }
		return false;
	 }
	 
	 public static boolean containsDuplicate1(int[] nums,int k) {
		 HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		 for(int i=0;i<nums.length;i++) {
			 if(map.containsKey(nums[i])) {
				 if(Math.abs(i-map.get(nums[i]))<=k) {
					 return true;
				 }
			 }else {
				 map.put(nums[i], i);
			 }
		 }
		return false;
	 }
	 
	 public  static int[] countingBits(int n) {
		 int[] result= new int[n+1];
		 for(int i=0;i<=n;i++) {
			 String binaryString = Integer.toBinaryString(i);
			 result[i]=countOnces(binaryString);
		 }
		return result;
	 }
	 private static int countOnces(String s) {
		 int count=0;
		 for(int i=0;i<s.length();i++) {
			 if(s.charAt(i)=='1') {
				 count++;
			 }
		 }
		return count;
	 }
	 
	 public static String[] reverse(String[] s) {
		 int start=0,end=s.length-1;
		 while(start<end) {
			 String temp=s[start];
			 s[start]= s[end];
			 s[end]=temp;
			 start++;
			 end--;
		 }
		return s;
	 }
	 
	 public static boolean ransomeNode(String ransomeNote, String magzine) {
		 Map<Character, Long> map = magzine.chars().mapToObj(c->(char)c)
		 .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		 
		 for(int i=0;i<ransomeNote.length();i++) {
			 if(map.containsKey(ransomeNote.charAt(i)) && map.get(ransomeNote.charAt(i))>0) {
				 map.put(ransomeNote.charAt(i), map.get(ransomeNote.charAt(i))-1);
			 }else {
				 return false;
			 }
		 }
		return true;
	 }
	 
	 public static char diffrence(String s,String t) {
		 int freq1=0,freq2=0;
		 for(int i=0;i<s.length();i++) {
			 freq1 +=s.charAt(i);
		 }
		 
		 for(int i=0;i<t.length();i++) {
			 freq2 +=t.charAt(i);
		 }
		 return (char) (freq2-freq1);
	 }
	 
	 public static char diffrence1(String s,String t) {
		 ArrayList<Character> list = new ArrayList<Character>();
		 for(int i=0;i<s.length();i++) {
			 list.add(s.charAt(i));
		 }
		 
		 for(int i=0;i<t.length();i++) {
			 if(!list.remove((Character)t.charAt(i))) {
				 return (Character) t.charAt(i);
			 }
		 }
		 throw new IllegalArgumentException("No extra character found or invalid input.");
	 }
	 
	 public static int maxConsecutiveOnes(int[] array) {
		 int cutrrentMax=0,max=0;
		 for(int num:array) {
			 if(num==1) {
				 cutrrentMax++;
			 }else {
				 cutrrentMax=0;
			 }
			 max=Math.max(cutrrentMax, max);
		 }
		return max;
	 }

	public static void main(String[] args) {
	int[] array= {1,1,0,1,1,1};
	System.out.println(maxConsecutiveOnes(array));
		
	}

}
