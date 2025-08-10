package com.faizan.leetcode.revision1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCodeRevision2 {
	
	
	public static int[] countBits(int n) {
		int[] ans=new int[n+1];
		
		for(int i=0;i<=n;i++) {
		     String binaryString = Integer.toBinaryString(i);
		     int count=0;
		     for(int j=0;j<binaryString.length();j++) {
		    	 if(binaryString.charAt(j)=='1') {
		    		 count++;
		    	 }
		     }
		     ans[i]=count;
		}
		return ans;
	}
	
	public static String[] reverseString(String[] s) {
		int left=0;
		int right=s.length-1;
		while(left<right) {
			String temp=s[left];
			s[left]=s[right];
			s[right]=temp;
			left++;
			right--;
		}
		return s;
	}
	
	public static boolean rensomNote(String ransomNote,String maxzine) {
		Map<Character, Long> collect = maxzine.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		
		for(int i=0;i<ransomNote.length();i++) {
			if(collect.containsKey(ransomNote.charAt(i)) && collect.get(ransomNote.charAt(i))>=1) {
				collect.put(ransomNote.charAt(i), collect.get(ransomNote.charAt(i))-1);
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
	public static char findDifferenceUsingList(String s, String t) {
	    List<Character> list = new ArrayList<>();
	    
	    for (char c : s.toCharArray()) {
	        list.add(c);
	    }
	    
	    for (char c : t.toCharArray()) {
	        if (!list.remove((Character) c)) {
	            return c; // Found extra char
	        }
	    }
	    
	    throw new IllegalArgumentException("No extra character found or invalid input.");
	}
	
	public static int maxCosecutive(int[] nums) {
		int currentCount=0;
		int maxCount=0;
		
		for(int i=0;i<nums.length;i++) {
			if(nums[i]==1) {
				currentCount++;
				if(currentCount>maxCount) {
					maxCount =currentCount;
				}
			}else {
				currentCount=0;
			}
		}
		return maxCount;
	}
	
	public static boolean directCapital(String s) {
		if(allLower(s) || allUpper(s) || firstCap(s)) {
			return true;
		}
		return false;
	}
	
    private static boolean allLower(String s) {
    	int count=0;
    	for(int i=0;i<s.length();i++) {
    		if(Character.isLowerCase(s.charAt(i))) {
    			count++;
    		}
    	}
    	
    	return count==s.length();
    }
    
    private static boolean allUpper(String s) {
    	int count=0;
    	for(int i=0;i<s.length();i++) {
    		if(Character.isUpperCase(s.charAt(i))) {
    			count++;
    		}
    	}
    	
    	return count==s.length();
    }
    
    private static boolean firstCap(String s) {
    	if(Character.isUpperCase(s.charAt(0)) && allLower(s.substring(1))) {
    		return true;
    	}
    	return false;
    }
    
    public static String reverseString(String s) {
    	StringBuilder builder = new StringBuilder();
    	String[] words = s.split(" ");
    	for(int i=0;i<words.length;i++) {
    		 if(i!=words.length-1) {
    			 builder.append(reverseWord(words[i])).append(" ");
    		 }else {
    			 builder.append(reverseWord(words[i]));
    		 }
    		
    	}
    	return builder.toString();
    }
    private static String reverseWord(String word) {
		String result="";
		for(int i=word.length()-1;i>=0;i--) {
			result +=word.charAt(i);
		}
		return result;
	}
    
    public static int binarySearch(int[] nums,int num) {
    	int left=0;
    	int right=nums.length-1;
    	int mid=0;
    	 while(left<=right) {
    		  mid=left+(right-left)/2;
    		 if(nums[mid]==num) {
    			return mid; 
    		 }else if(num>nums[mid]) {
    			 left=mid+1;
    		 }else {
    			 right=mid-1;
    		 }
    		 
    	 }
		return -1;
    }
    
    public static int jwelsStones(String jwels,String stones) {
    	HashSet<Character> set = new HashSet<Character>();
    	for(int i=0;i<jwels.length();i++) {
    		set.add(jwels.charAt(i));
    	}
    	int count=0;
    	for(int i=0;i<stones.length();i++) {
    		if(set.contains(stones.charAt(i))) {
    			count++;
    		}
    	}
		return count;
    }
    
    public static int[][] transposeMatrix(int[][] matrix){
    	int rows=matrix.length;
    	int cols=matrix[0].length;
    	int[][] result=new int[cols][rows];
    	
    	for(int i=0;i<rows;i++) {
    		for(int j=0;j<cols;j++){
    			result[j][i]=matrix[i][j];
    		}
    	}
		return result;
    }


	
	public static void main(String[] args) {
		int[][] matrix = { {1, 4, 6}, {5, 7, -2}, {4, -10, 12} }; 
		  int[][] tranposeMatrix = transposeMatrix(matrix); 
		  for (int[] row : tranposeMatrix) {
		  System.out.println(Arrays.toString(row)); 
		  }
		//String jwels="aA";
		//String stones="aAAbbbbbb";
		//System.out.println(jwelsStones(jwels, stones));
		//int[] nums= {1,2,3,4,5,6,7,8,9};
		//int num=6;
		//System.out.println(binarySearch(nums, num));
		//String s="Helo bro";
		//System.out.println(reverseString(s));
		//String s="FG";
		//System.out.println(directCapital(s));
		//int[] nums= {1,1,0,1,1,1};
		//System.out.println(maxCosecutive(nums));
		//String s="abcd";
		//String t="abcde";
		//System.out.println(findDifferenceUsingList(s, t));
		//String ransomNote="aa";
		//String maxzine="ab";
		//System.out.println(rensomNote(ransomNote, maxzine));
		//String[] s= {"h","e","l","l","o"};
		//System.out.println(Arrays.toString(reverseString(s)));
		//int n= 5;
		//System.out.println(Arrays.toString(countBits(n)));
	

	}}
