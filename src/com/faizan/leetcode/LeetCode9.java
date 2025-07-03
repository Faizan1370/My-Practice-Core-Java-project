package com.faizan.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode9 {
	
	public static int distanceTravled(int mainTank,int additionalTank) {
		int tavelledDistance=0;
		
		while(mainTank>=5 && additionalTank>0) {
			tavelledDistance +=50;
			mainTank =(mainTank -5)+1;
			additionalTank--;
		}
		tavelledDistance +=mainTank*10;
		return tavelledDistance;
	}
	
	public static boolean isGood(int[] nums) {
		Arrays.sort(nums);
		int max = nums[nums.length-1];
		if(nums.length !=(max+1)) {
			return false;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0;i<nums.length;i++) {
			if(map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i])+1);
			}else {
				map.put(nums[i], 1);
			}
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>(map.values());
		int count=0;
		for(Integer num:list) {
			if(num>=2) {
				count++;
			}
		}
		if(map.get(max)==2 && count==1) {
			return true;
		}
		return false;
	}
	
	public static List<String> splitWordsbySeprator(List<String> words,String seprator){
		List<String> list = new ArrayList<String>();
		for(int i =0;i<words.size();i++) {
			String[] wordArray =words.get(i).split("["+seprator+"]");
			for(String word:wordArray) {
				if(word !="") {
					list.add(word);
				}
			}
		}
		return list;
	}
	
	public static int roundedAmount(int purchaseAmount) {
		int rem=purchaseAmount %10;
		int balance= 10-rem;
		if(rem<5) {
			purchaseAmount = purchaseAmount-rem;
		}else {
			purchaseAmount = purchaseAmount+balance;
		}
		return 100-purchaseAmount;
	}
	public static int maxSum(int[] nums) {
		int max=-1;
		for(int i=0;i<nums.length;i++) {
			for(int j=i+1;j<nums.length;j++) {
				if(maxDigitInNum(nums[i])== maxDigitInNum(nums[j])) {
					max= Math.max(max, (nums[i]+nums[j]));
				}
			}
		}
		return max;
	}
	
	private static int maxDigitInNum(int num) {
		int maxDigit=0;
		while(num !=0) {
			if((num%10)>maxDigit) {
				maxDigit =num %10;
			}
			num=num/10;
		}
		return maxDigit;
	}
	
	public static boolean wordPattern(String pattern,String s) {
		HashMap<Character, String> char_map= new HashMap<Character, String>();
		HashMap<String, Character> word_map=new HashMap<String, Character>();
		String[] words = s.split(" ");
		if(words.length != pattern.length()) {
			return false;
		}
		for(int i=0;i<words.length;i++) {
			char ch=pattern.charAt(i);
			String word=words[i];
			if(!char_map.containsKey(ch)) {
				if(word_map.containsKey(word)) {
					return false;
				}else {
					char_map.put(ch, word);
					word_map.put(word, ch);
				}
				
			}else {
				String mappedWord=char_map.get(ch);
				if(!mappedWord.equals(word)) {
					return false;
				}
				
			}
		}
		return true;
	}
	public static int[] leftRightDifference(int[] nums) {
		int[] leftSum= new int[nums.length];
		int[] rightSum= new int[nums.length];
		leftSum[0]=0;
		rightSum[nums.length-1]=0;
		int l_index=1;
		int r_index=0;
		for(int i=1;i<nums.length;i++) {
			leftSum[l_index++] = sumArray(nums, 0, i-1);
		}
		for(int i=0;i<nums.length;i++) {
			rightSum[r_index++] = sumArray(nums, i+1, nums.length-1);
		}
		int[] answre= new int[nums.length];
		for(int i=0;i<answre.length;i++) {
			answre[i] = Math.abs(rightSum[i]-leftSum[i]);
		}
		return answre;
		
	}
	private static int sumArray(int[] array,int start,int end) {
		int sum=0;
		for(int i=start;i<=end;i++) {
			sum +=array[i];
		}
		return sum;
	}
	
	public static List<List<Integer>> findDiffernces(int[] nums1,int[] nums2){
	return Arrays.asList(getCommonValues(nums1, nums2),getCommonValues(nums2, nums1));	
	}
	
	private static List<Integer> getCommonValues(int[] nums1,int[] nums2){
		Set<Integer> set1 = new HashSet<Integer>();
		Set<Integer> set2 = new HashSet<Integer>();
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
	public static int numOfEmpsMetTarget(int[] hours ,int target) {
		int count=0;
		for(int i=0;i<hours.length;i++) {
			if(hours[i]>=target) {
				count++;
			}
		}
		return count;
	}
	public static boolean checkZeroOnce(String s) {
		int oneCount=0;
		int maxOneCount=0;
		int zeroCount=0;
		int maxZeroCount=0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='1') {
				oneCount++;
				zeroCount=0;
			}else {
				zeroCount++;
				oneCount=0;
			}
			maxZeroCount=Math.max(maxZeroCount, zeroCount);
			maxOneCount=Math.max(maxOneCount, oneCount);
			
		}
		if(maxOneCount>maxZeroCount) {
			return true;
		}
		return false;
	}
	
	public static int maxPower(String s) {
		if(s.length()==0 || s==null) {
			return 0;
		}
		int maxStreak=1,currentStreak=1;
		for(int i=1;i<s.length();i++) {
			if(s.charAt(i)==s.charAt(i-1)) {
				currentStreak++;
			}else {
				currentStreak=1;
			}
			maxStreak = Math.max(currentStreak, maxStreak);
			
		}
		return maxStreak;
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
		min=Math.min(min, map.get('a'));
		min=Math.min(min, map.get('l')/2);
		min=Math.min(min, map.get('o')/2);
		min=Math.min(min, map.get('n'));
		return min;
		}
	public static void main(String[] args) {
		String s="alebolko";
		System.out.println(maxNumberOfBallons(s));
		//String s= "leetcode";
		//System.out.println(maxPower(s));
		//String s="110001";
		//System.out.println(checkZeroOnce(s));
		//int[] hours= {0,1,2,3,4};
		//int target=2;
		//System.out.println(numOfEmpsMetTarget(hours, target));
		//int[] nums1= {1,2,3};
		//int[] nums2= {2,4,6};
		//System.out.println(findDiffernces(nums1, nums2));
		//int[] nums= {10,4,8,3};
		//System.out.println(Arrays.toString(leftRightDifference(nums)));
		//String pattern="abba";
		//String s="dog cat cat dog";
		//System.out.println(wordPattern(pattern, s));
		//int[] nums= {51,71,17,24,42};
		//System.out.println(maxSum(nums));
		//int purchaseAmount=16;
		//System.out.println(roundedAmount(purchaseAmount));
		//List<String> words= Arrays.asList ("first.second.third","four.five","six");
		//String seprator =".";
		//System.out.println(splitWordsbySeprator(words, seprator));
		//int[] nums= {1,2,3,3};
		//System.out.println(isGood(nums));
		//System.out.println(distanceTravled(5, 10));
	}

}
