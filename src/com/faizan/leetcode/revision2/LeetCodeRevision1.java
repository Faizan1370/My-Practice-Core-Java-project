package com.faizan.leetcode.revision2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCodeRevision1 {
	
	public static int romanToInt(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('v', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		int sum=0;
		
		for(int i=1;i<s.length();i++) {
			if(map.get(s.charAt(i))>=map.get(s.charAt(i-1))) {
				sum -=map.get(s.charAt(i-1));
			}else {
				sum+=map.get(s.charAt(i-1));
			}
		}
		sum +=map.get(s.charAt(s.length()-1));
		return sum;
	}
	
	public static boolean validParntesis(String s) {
		Stack<Character> stack= new Stack<Character>();
		
		for(int i=0;i<s.length();i++) {
			char ch=s.charAt(i);
				if(ch=='(' || ch== '{' || ch=='[') {
					stack.push(ch);
				}else if(!stack.isEmpty() && ch==')' && stack.peek()=='('){
					stack.pop();
					
				}else if(!stack.isEmpty() && ch=='}' && stack.peek()=='{'){
					stack.pop();
				}else if(!stack.isEmpty() && ch==']' && stack.peek()=='['){
					stack.pop();
				}else {
					return false;
				}
		}
		return stack.isEmpty();
	}
	
	public static int insertPosition(int[] nums,int target) {
		int left=0;
		int right=nums.length-1;
		int mid=0;
		
		for(int i=0;i<nums.length;i++) {
			mid= left+(right-left)/2;
			if(nums[mid]==target) {
				return mid;
			}else if(target>nums[mid]) {
				left=mid+1;
			}else {
				right=mid-1;
			}
		}
		return left;
	}
	public static int[] mergeSotedArray(int[] nums1, int[] nums2, int m,int n) {
		int p1=m-1;
		int p2=n-1;
		int p3=nums1.length-1;
		while(p3>0) {
			int element1,element2 = 0;
			 if(p1>=0) {
				 element1=nums1[p1];
			 }else {
				 element1=Integer.MIN_VALUE;
			 }
			 if(p2>=0) {
				 element2=nums2[p2];
			 }else {
				 element2=Integer.MIN_VALUE;
			 }
			 if(element1>element2) {
				 nums1[p3]=element1;
				 p1--;
				 p3--;
			 }else {
				 nums1[p3]=element2;
				 p2--;
				 p3--;
			 }
		}
		return nums1;
		
	}
	
	public static int maxProfit(int[] prices) {
		int currentProfit=0;
		int maxProfit=0;
		int min=Integer.MAX_VALUE;
		for(int i=0;i<prices.length;i++) {
			if(min>prices[i]) {
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
			char ch=s.charAt(i);
			if(Character.isLetter(ch) || Character.isDigit(ch)) {
				builder.append(ch);
			}
		}
		String str = builder.toString().toLowerCase();
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
		.map(entry->entry.getKey())
		.findFirst()
		.get();
		return integer;
	}
	
	public static boolean checkDuplicate(int[] nums) {
		HashSet<Integer> hashSet= new HashSet<Integer>();
		for(int i=0;i<nums.length;i++) {
			if(hashSet.contains(nums[i])) {
				return false;
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
				int dif=(map.get(nums[i])-i);
				if(dif<=k) {
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
		if(charArray.length!=charArray2.length) {
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
		for(int num:nums) {
			if(num!=0) {
				nums[count]=num;
				count++;
			}
		}
		for(int i=count;i<nums.length;i++) {
			nums[i]=0;
		}
		return nums;
	}
	
	public static int[] countBits(int n) {
		int[] ans= new int[n+1];
		ans[0]=0;
		for(int i=1;i<=n;i++) {
			String binaryString = Integer.toBinaryString(i);
			ans[i]=count1s(binaryString);
		}
		return ans;
	}
	
	private static int count1s(String s) {
		int count=0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='1') {
				count++;
			}
		}
		return count;
	}
	
	public static String reverseString(String s) {
		return new StringBuilder().append(s).reverse().toString();
	}
	
	public static boolean ransomNote(String ransomeNote,String magzine) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i=0;i<magzine.length();i++) {
			if(map.containsKey(magzine.charAt(i))) {
				map.put(magzine.charAt(i), map.get(magzine.charAt(i))+1);
			}else {
				map.put(magzine.charAt(i),1);
			}
		}
		
		for(int i=0;i<ransomeNote.length();i++) {
			if(map.containsKey(ransomeNote.charAt(i)) && map.get(ransomeNote.charAt(i))>=1) {
				map.put(ransomeNote.charAt(i), map.get(ransomeNote.charAt(i))-1);
			}else {
				return false;
			}
		}
		return true;
	}
	
	public static char findDiffrences(String s,String t) {
		int t_sum=0;
		int s_sum=0;
		
		for(int i=0;i<t.length();i++){
			t_sum+=t.charAt(i);
		}
		for(int i=0;i<s.length();i++){
			s_sum+=t.charAt(i);
		}
		
		return (char) (t_sum-s_sum);
				
	}
	
	public static char findDiffrences1(String s,String t) {
		ArrayList<Character> list = new ArrayList<Character>();
		
		for(int i=0;i<s.length();i++) {
			list.add(s.charAt(i));
		}
		for(int i=0;i<t.length();i++) {
			if(!list.remove((Character)t.charAt(i))) {
				return t.charAt(i);
			}
		}
		return 0;
	}
	
	public static int maxConsecutive(int[] array) {
		int currentMax=0;
		int max=0;
		
		for(int i=0;i<array.length;i++) {
			if(array[i]==1) {
				currentMax++;
				if(currentMax>max) {
					max=currentMax;
				}
			}else {
				currentMax=0;
			}
		}
		return max;
	}

	
	public static void main(String[] args) {
		int[] array= {1,1,0,1,1,1};
		System.out.println(maxConsecutive(array));
	}

}
