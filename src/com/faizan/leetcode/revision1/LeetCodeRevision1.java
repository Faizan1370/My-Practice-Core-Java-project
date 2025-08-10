package com.faizan.leetcode.revision1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeetCodeRevision1 {
	
	public static int romanToInterger(String s) {
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
		hashMap.put('I', 1);
		hashMap.put('V', 5);
		hashMap.put('X', 10);
		hashMap.put('L', 50);
		hashMap.put('C', 100);
		hashMap.put('D', 500);
		hashMap.put('M', 1000);
      int result =0;
		
		int i,j;
		for(i=0,j=1;j<s.length();i++,j++) {
			if(hashMap.get(s.charAt(i))>=hashMap.get(s.charAt(j))) {
				result +=hashMap.get(s.charAt(i));
			}else {
				result -=hashMap.get(s.charAt(i));
			}
		}
		
		result +=hashMap.get(s.charAt(i));
		return result;
	}
	public static boolean checkValidParantesis(String s) {
		Stack<Character> stack = new Stack<Character>();
		for(int i=0;i<s.length();i++) {
			char ch=s.charAt(i);
			if(ch== '(' || ch=='{' || ch=='[') {
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
	
	public static int searchInsertPosition(int[] nums,int target) {
		int left=0;
		int right=nums.length-1;
		int mid=0;
		while(left<=right) {
			mid =left+(right-left)/2;
			if(target == nums[mid]) {
				return mid;
			}else if(target>nums[mid]) {
				left=mid+1;
			}else {
				right=mid-1;
			}
		}
		return left;
	}
	
	public static int lengthLastWord(String s) {
		String[] words = s.split(" ");
		return words[words.length-1].length();
	}
	
	public static int[] mergeArray(int[] nums,int[] nums2,int m,int n) {
		int p1=m-1;
		int p2=n-1;
		int p3=nums.length-1;
		while(p3>=0) {
			int element1,element2;
			element1=(p1>=0)?nums[p1]:Integer.MIN_VALUE;
			element2=(p2>=0)?nums2[p2]:Integer.MIN_VALUE;
			if(element1>element2) {
				nums[p3]=element1;
				p3--;
				p1--;
			}else {
				nums[p3]=element2;
				p3--;
				p2--;
			}
		}
		return nums;
	}
	
	public static int maxProfitBuySel(int[] prices) {
		int currentProfit=0;
		int maxProfit=0;
		int min=Integer.MAX_VALUE;
		
		for(int i=0;i<prices.length;i++) {
			if(prices[i]<min) {
				min=prices[i];
			}
			currentProfit = prices[i]-min;
			if(currentProfit>maxProfit) {
				maxProfit=currentProfit;
			}
		}
		return maxProfit;
	}
	
	public static boolean validPalindromic(String s) {
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<s.length();i++) {
			if(Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i))) {
				builder.append(s.charAt(i));
			}
		}
		String str=builder.toString().toLowerCase();
		int start=0;
		int end=str.length()-1;
		while(start<end) {
			if(str.charAt(start)!=str.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
	public static int singleNumber(int[] nums) {
		Integer integer = Arrays.stream(nums).mapToObj(num->(Integer)num)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet()
		.stream()
		.filter(entry->entry.getValue()==1L)
		.map(entry->entry.getKey())
		.findFirst()
		.get();
		return integer;
	}
	
	public static int usingXORSingleNumber(int[] nums) {
		int result=0;
		for(int num:nums) {
			result ^=num;
		}
		return result;
	}
	
	public static int majorityElement(int[] nums) {
		
		int n=nums.length;
		  Integer integer = Arrays.stream(nums).mapToObj(num->(Integer)num)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet()
		.stream()
		.filter(entry->entry.getValue()>n/2)
		.map(entry->entry.getKey()).findFirst().get();
		 return integer;
	}
	
	public static boolean checkDuplicate(int[] nums) {
		HashSet<Integer> set= new HashSet<Integer>();
		for(int i=0;i<nums.length;i++) {
			if(set.contains(nums[i])) {
				return false;
			}else {
				set.add(nums[i]);
			}
		}
		return true;
	}
	
	public static boolean checkDuplicate2(int[] nums,int k) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(int i=0;i<nums.length;i++) {
			if(!map.containsKey(nums[i])) {
				map.put(nums[i], i);
			}else {
				int diff=Math.abs(map.get(nums[i])-i);
				if(diff<=k) {
					return true;
				}else {
					map.put(nums[i], i);
				}
			}
		}
		return false;
	}
	
	public static boolean checkAnagram(String s1,String s2) {
		char[] charArray = s1.toCharArray();
		char[] charArray2 = s2.toCharArray();
		if(s1.length()!=s2.length()) {
			return false;
		}
		Arrays.sort(charArray);
		Arrays.sort(charArray2);
		
		for(int i=0;i<charArray.length;i++) {
			if(charArray[i]!=charArray2[i]) {
				return false;
			}
		}
		return true;
	}
	public static int[] moveZero(int[] nums) {
		int count=0;
		for(int i=0;i<nums.length;i++) {
			if(nums[i]!=0) {
				nums[count]=nums[i];
				count++;
			}
		}
		for(int i=count;i<nums.length;i++) {
			nums[i]=0;
		}
		return nums;
	}
	
	public static void main(String[] args) {
		int[] nums= {4,6,8,0,1,0,0,2};
		System.out.println(Arrays.toString(moveZero(nums)));
		//String s1="faizan";
		//String s2="naziaf";
		//System.out.println(checkAnagram(s1, s2));
		//int[] nums= {1,2,3,1,6,7};
		//int k=1;
		//System.out.println(checkDuplicate2(nums, k));
		//int[] nums= {1,3,5,7,1};
		//System.out.println(checkDuplicate(nums));
		//int[] nums= {3,2,3};
		//System.out.println(majorityElement(nums));
		//int[] nums= {2,2,1};
		//System.out.println(usingXORSingleNumber(nums));
		//String s="A Man, A Plan , A canal :Panama";
		//System.out.println(validPalindromic(s));
		//int[] prices = {7,1,5,3,6,4};
		//System.out.println(maxProfitBuySel(prices));
		//int[] nums= {1,2,3,0,0,0};
		//int[] nums2= {4,5,6};
		//int m=3,n=3;
		//System.out.println(Arrays.toString(mergeArray(nums, nums2, m, n)));
		//String s="Hello bro";
		//System.out.println(lengthLastWord(s));
		//int[] arrray = {1,3,6,8,9};
		//int target=2;
		//System.out.println(searchInsertPosition(arrray, target));
		//String s="(){}]";
		//System.out.println(checkValidParantesis(s));
		//String s="IX";
		//System.out.println(romanToInterger(s));
	}

	
}
