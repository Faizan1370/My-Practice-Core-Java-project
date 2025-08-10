package com.faizan.leetcode.revision;

import java.util.HashSet;
import java.util.Set;

public class LeetCodeRevision6 {
	
	public static boolean circularSentence(String sentence) {
	    String[] words = sentence.split(" ");

	    // If only one word, check first and last character
	    if (words.length == 1) {
	        return sentence.charAt(0) == sentence.charAt(sentence.length() - 1);
	    }

	    // Check that for every space, the character before and after are equal
	    for (int i = 1; i < sentence.length() - 1; i++) {
	        if (!Character.isLetter(sentence.charAt(i))) {
	            if (sentence.charAt(i - 1) != sentence.charAt(i + 1)) {
	                return false;
	            }
	        }
	    }

	    // Final check for circularity: first and last character
	    return sentence.charAt(0) == sentence.charAt(sentence.length() - 1);
	}

	
	public static boolean isCircularSentence(String sentence) {
		String[] words=sentence.split(" ");
		if(words.length==1 && sentence.charAt(0)==sentence.charAt(sentence.length()-1)) {
			return true;
		}
		
		if(words.length==1 && sentence.charAt(0)!=sentence.charAt(sentence.length()-1)) {
			return false;
		}
		
		for(int i=0;i<sentence.length();i++) {
			char ch=sentence.charAt(i);
			if(!Character.isLetter(ch) && sentence.charAt(i-1)!=sentence.charAt(i+1) || (sentence.charAt(0)!= sentence.charAt(sentence.length()-1))) {
				return false;
			}
		}
		return true;
	}
	
	public static int maximumValue(String[] strs) {
		int maxValue=0;
		int value=0;
		for(int i=0;i<strs.length;i++) {
		    if(checkWord(strs[i])==1) {
		    	value=Integer.parseInt(strs[i]);
		    }
		    
		    
		    if(checkWord(strs[i])==-1 || checkWord(strs[i])==2) {
		    	value=strs[i].length();
		    }
		    maxValue =Math.max(value, maxValue);
		}
		return maxValue;
	}
	
	private static int checkWord(String word) {
		int count=0;
		for(int i=0;i<word.length();i++) {
			if(Character.isDigit(word.charAt(i))) {
				count++;
			}
		}
		if(count==word.length()) {
			return 1;
		}
		if(count==0) {
			return -1;
		}
		
		return 2;
				
	}
	
	public static int countPair(String[] words) {
		int count=0;
		for(int i=0;i<words.length-1;i++) {
			for(int j=i+1;j<words.length;j++) {
				if(checSimiliarWord(words[i], words[j])) {
					count++;
				}
			}
		}
		return count;
	}
	
	private static boolean checSimiliarWord(String word1,String word2) {
		HashSet<Character> set= new HashSet<Character>();
		HashSet<Character> set1= new HashSet<Character>();
		for(int i=0;i<word1.length();i++) {
			set.add(word1.charAt(i));
		}
		for(int i=0;i<word2.length();i++) {
			set1.add(word2.charAt(i));
		}
		
		return set.equals(set1);
	}
	
	public static int maxPosNeg(int[] nums) {
		int negCount=0;
		int posCount=0;
		
		for(int num:nums) {
			if(num<0) {
				negCount++;
			}else {
				posCount++;
			}
		}
		return Math.max(negCount, posCount);
		}
	
	public static int minimumCommonValue(int[] nums1,int[] nums2) {
		//int minCommonValue=Integer.MAX_VALUE;
		HashSet<Integer> set=new HashSet<Integer>();
			for(int num:nums1) {
				set.add(num);
			}
			for(int num:nums2) {
				if(set.contains(num)) {
					return num;
				}
			}
		
		return -1;
		
	}
	
	public static int getCommon(int[] nums1,int[] nums2) { // prpper
		int result =-1;
		Set<Integer> set= new HashSet<Integer>();
		
		for(int num :nums1) {
			set.add(num);
		}
		for(int num:nums2) {
			if(set.contains(num)) {
				result = num;
				break;
			}
		}
		return result;
	}
	
	public static int alternateDigitSum(int n) {
		String num=n+"";
		int sum =Integer.parseInt(num.charAt(0) +"");
		for(int i=1;i<num.length();i++) {
			if(i%2!=0) {
				sum -=Integer.parseInt(num.charAt(i) +"");
			}else {
				sum +=Integer.parseInt(num.charAt(i) +"");
			}
		}
		return sum;
	}
	
	public static int arrayConcat(int[] nums) {
		int left=0;
		int right=nums.length-1;
		String str="";
		int sum=0;
		while(left<right) {
		   str=String.valueOf(nums[left])+String.valueOf(nums[right]);
		   sum +=Integer.parseInt(str);
		   left++;
		   right--;
		}
		
		 // If odd-length, include middle element
	    if (left == right) {
	        sum += nums[left];
	    }
		return sum;
	}
	
	public static int sumOfMultiples(int n) {
		int sum=0;
		for(int i=1;i<=n;i++) {
			if(i%3==0 || i%5==0 || i%7==0) {
				sum +=i;
			}
		}
		return sum;
	}
	
	
	
	public static void main(String[] args) {
		int n=7;
		System.out.println(sumOfMultiples(n));
		//int[] nums= {7,52,2,4,7};
		//System.out.println(arrayConcat(nums));
		//int n=521;
		//System.out.println(alternateDigitSum(n));
		//int[] nums1= {1,2,3};
		//int[] nums2= {2,4};
		//System.out.println(minimumCommonValue(nums1, nums2));
		//int[] nums= {-1,-2,-3,1,2,3,5};
		//System.out.println(maxPosNeg(nums));
		//String[] words= {"ab","aabb","abcd","bac","aabc"};
		//System.out.println(countPair(words));
		//String[] strs= {"alice3","bob","3","4","00000"};
		//System.out.println(maximumValue(strs));
		//String sentence="leetcode excrciues should deligghtfull";
		//System.out.println(isCircularSentence(sentence));
	}

}
