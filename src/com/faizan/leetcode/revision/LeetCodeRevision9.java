package com.faizan.leetcode.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LeetCodeRevision9 {
	
	public static int largestGain(int[] gain) {
		int currentGain=0;
		int largestGain=0;
		for(int i=0;i<gain.length;i++) {
			currentGain = currentGain+gain[i];
			largestGain=Math.max(largestGain, currentGain);
		}
		return largestGain;
	}
	
	public static int[] findIndices(int[] nums, int indexDiff,int valueDiff) {
		
		for(int i=0;i<nums.length;i++) {
			for(int j=i+1;i<nums.length;j++) {
			  if(Math.abs(i-j)>=indexDiff && Math.abs(nums[i]-nums[j])>=valueDiff) {
				  return new int[] {i,j};
			  }
			}
		}
		return new int[] {-1,-1};
	}
	
	public static String toLowerCase(String s) {
		return s.toLowerCase();
	}
	
	public static boolean wordPattern(String pattern,String s) {
		String[] words = s.split(" ");
		if(words.length!=pattern.length()) {
			return false;
		}
		HashMap<Character, String> char_map= new HashMap<Character, String>();
		HashMap<String, Character> word_map=new HashMap<String, Character>();
		for(int i=0;i<words.length;i++) {
			char ch= pattern.charAt(i);
			String word=words[i];
			if(!char_map.containsKey(pattern.charAt(i))) {
				if(word_map.containsKey(word)) {
					return false;
				}else {
					char_map.put(ch, word);
					word_map.put(word, ch);
				}
			}else {
				if(!char_map.get(ch).equals(word)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static int[] leftSumRightSum(int[] nums) {
		int[] l_sum=new int[nums.length];
		int[] r_sum=new int[nums.length];
		//int l_index=1;
		//int r_index=nums.length-1;
		int sum=0;
		for(int i=1;i<nums.length;i++) {
			sum +=nums[i-1];
			l_sum[i]=sum;
		}
		sum=0;
		for(int i=nums.length-1;i>=0;i--) {
			r_sum[i]=sum;
			sum +=nums[i];	
		}
		
		for(int i=0;i<nums.length;i++) {
			nums[i]=Math.abs(l_sum[i]-r_sum[i]);
		}
		return nums;
		
	}
	
	public static int[] leftRightDifference(int[] nums) {
	    int n = nums.length;
	    int[] result = new int[n];
	    int leftSum = 0;
	    int rightSum = Arrays.stream(nums).sum();

	    for (int i = 0; i < n; i++) {
	        rightSum -= nums[i];
	        result[i] = Math.abs(leftSum - rightSum);
	        leftSum += nums[i];
	    }

	    return result;
	}
	
	public static List<List<Integer>> findDiffrence(int[] nums1,int[] nums2){
		return Arrays.asList(getCommonValues(nums1, nums2),getCommonValues(nums2, nums1));
	}
	
	public static List<Integer> getCommonValues(int[] nums1,int[] nums2){
		HashSet<Integer> set1 = new HashSet<Integer>();
		HashSet<Integer> set2 = new HashSet<Integer>();
		
		for(int num:nums2) {
			set2.add(num);
		}
		for(int num:nums1) {
			if(!set2.contains(num)) {
				set1.add(num);
				}
			}
		
		return new ArrayList<Integer>(set1);
	}
	
	
	
	public static int numberOfEmp(int[] hours,int target ) {
		int count=0;
		for(int i=0;i<hours.length;i++) {
			if(hours[i]>=target) {
				count++;
			}
		}
		return count;
	}

	
	public static boolean longestContigius1s(String s) {
		int longestOne=0;
		int longestZero=0;
		int currentOnces=0;
		int currentZeros=0;
		
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='1') {
				longestOne++;
				currentZeros=0;
			}else if(s.charAt(i)=='0') {
				longestZero++;
				currentOnces=0;
			}
			  longestOne = Math.max(longestOne, currentOnces);
		       longestZero = Math.max(longestZero, currentZeros);
		}
		
		
		return longestOne>longestZero;
	}
	
	public static int maxPower(String s) {
		if(s==null || s.length()==0){
			return 0;
		}
		int currentStreek=1;
		int maxStreek=1;
		
		for(int i=1;i<s.length();i++) {
			if(s.charAt(i) == s.charAt(i-1)) {
				currentStreek++;
			}else {
				currentStreek=1;
			}
			maxStreek=Math.max(currentStreek, maxStreek);
		}
		
		
		return maxStreek;
	}
	public static int maxNumberOfBallons(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('b', 0);
		map.put('a', 0);
		map.put('l', 0);
		map.put('o', 0);
		map.put('n', 0);
		
		for(int i=0;i<s.length();i++) {
			if(map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), map.get(s.charAt(i))+1);
			}
		}
		int min = map.get('b');
		min =Math.min(min, map.get('a'));
		min =Math.min(min, map.get('l')/2);
		min =Math.min(min, map.get('o')/2);
		min =Math.min(min, map.get('n'));
		
		return min;
		
	}
	public static void main(String[] args) {
		String s="alabolno";
		System.out.println(maxNumberOfBallons(s));
		//String s= "leetCode";
		//System.out.println(maxPower(s));
		//String s="110001";
		//System.out.println(longestContigius1s(s));
		
		//int[] hours= {0,1,2,3,4};
		//int target=2;
		//System.out.println(numberOfEmp(hours, target));
		//int[] nums1= {1,2,3};
		//int[] nums2= {2,4,6};
		//System.out.println(findDiffrence(nums1, nums2));
		//int[] nums= {10,4,8,3};
		//System.out.println(Arrays.toString(leftRightDifference(nums))); 
		//String pattern="abba";
		//String s="dog cat cat dog";
		//System.out.println(wordPattern(pattern, s));
		//String str="FGJKfh";
		//System.out.println(toLowerCase(str));
		//int[] nums= {5,1,4,1};
		//int indecDiff=2,valueDiff=4;
		//System.out.println(Arrays.toString(findIndices(nums, indecDiff, valueDiff)));
	//	int[] gain= {-5,1,5,0,-7};
	//	System.out.println(largestGain(gain));
	}

}
