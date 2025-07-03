package com.faizan.leetcode.revision;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCodeRevision1 {
	
	public static boolean validPalindrome(String s) {
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<s.length();i++) {
			char ch=s.charAt(i);
			if(Character.isDigit(ch) || Character.isLetter(ch)) {
				builder.append(ch);
			}
		}
		int start=0;
		String str=builder.toString().toLowerCase();
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
	
	public static int findSingleNum(int[] nums) {
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
	public static int singleNumber(int[] array) {
		int result=0;
		for(int num :array) {
			result ^=num;
		}
		return result;
	}
	
	public static int majorityElement(int[] nums) {
		int majority=nums.length/2;
		Integer integer = Arrays.stream(nums).mapToObj(num->(Integer)num)
				.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
				.entrySet()
				.stream()
				.filter(entry->entry.getValue()>majority)
				.map(entry->entry.getKey())
				.findFirst()
				.get();
		return integer;
	}
	
	public static boolean checkDuplicate(int[] nums) {
		HashSet<Integer> hashSet = new HashSet<Integer>();
		for(int i=0;i<nums.length;i++) {
			if(hashSet.contains(nums[i])) {
				return true;
			}else {
				hashSet.add(nums[i]);
			}
		}
		return false;
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
	public static boolean validAnagram(String s,String s1) {
		char[] charArray = s.toCharArray();
		char[] charArray2 = s.toCharArray();
		if(charArray.length !=charArray2.length) {
			return false;
		}
		Arrays.sort(charArray);
		Arrays.sort(charArray2);
		
		for(int i=0;i<charArray.length;i++) {
			if(charArray[i]!=charArray[i]) {
				return false;
			}
		}
		return true;
		
	}
	
	public static int[] moveZero(int[] nums) {
		int index=0;
		for(int i=0;i<nums.length;i++) {
			if(nums[i]!=0) {
				nums[index]=nums[i];
				index++;
			}
		}
		for(int i=index;i<nums.length;i++) {
			nums[i]=0;
		}
		return nums;
	}
	
	public static int[] countingBits(int n) {
		int[] ans=new int[n+1];
		//ans[0]=0;
		for(int i=0;i<=n;i++) {
			String binary = Integer.toBinaryString(i);
			int count=0;
			for(int j=0;j<binary.length();j++) {
				if(binary.charAt(j)=='1') {
					count++;
				}
			}
			ans[i]=count;
		}
		return ans;
	}
	public static String[] reverseString(String[] s) {
		int start=0;
		int end=s.length-1;
		while(start<end) {
			String temp=s[start];
			s[start]=s[end];
			s[end]=temp;
			start++;
			end--;
		}
		return s;
		
	}
	public static void main(String[] args) {
		String[] s= {"f","a","z"};
		System.out.println(Arrays.toString(reverseString(s)));
		//int n=2;
		//System.out.println(Arrays.toString(countingBits(n)));
		//int[] nums= {1,0,3,5,0,4,0,56};
		//System.out.println(Arrays.toString(moveZero(nums)));
		//String s="faizan", s1="nazaif";
		//System.out.println(validAnagram(s, s1));
		//int[] nums= {1,2,3,1};
		//int k=3;
		//System.out.println(checkDuplicate2(nums,k));
		//int[] nums= {2,5,7,9,8,1,2};
		//System.out.println(checkDuplicate(nums));
		//int[] nums= {3,2,3};
		//System.out.println(majorityElement(nums));
		//int[] nums= {1,2,2,3,3};
		//System.out.println(singleNumber(nums));
		//String s="A Man, A Plan , A canal :Panama";
		//System.out.println(validPalindrome(s));
	}

}
