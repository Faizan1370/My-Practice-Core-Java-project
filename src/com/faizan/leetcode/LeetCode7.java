package com.faizan.leetcode;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class LeetCode7 {
	
	public static String[] sortPeople(String[] people,int[] heights) {
		HashMap<Integer, String> map= new HashMap<Integer, String>();
		
		for(int i=0;i<people.length;i++) {
			map.put(heights[i], people[i]);
		}
		Arrays.sort(heights);
		String[] resultArray = new String[people.length];
		int index=0;
		for(int i=heights.length-1;i>=0;i--) {
			resultArray[index]=map.get(heights[i]);
			index++;
		}
		return resultArray;
	}
	
	public static String[] sortPeople1(String[] people, int[] heights) {
	    HashMap<Integer, String> map = new HashMap<>();
	    for (int i = 0; i < people.length; i++) {
	        map.put(heights[i], people[i]);
	    }
	    
	    Map<Integer, String> sortedMap = map.entrySet().stream()
	        .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
	        .collect(Collectors.toMap(
	            Map.Entry::getKey,
	            Map.Entry::getValue,
	            (e1, e2) -> e1,
	            LinkedHashMap::new
	        ));
	    
	    String[] resultArray = new String[people.length];
	    int index = 0;
	    for (String str : sortedMap.values()) {
	        resultArray[index++] = str;
	    }
	    return resultArray;
	}
	public static int distictAverage(int[] nums) {
		Arrays.sort(nums);
		int left=0,right=nums.length-1;
		Set<Integer> set= new HashSet<Integer>();
		while(left<right) {
			int avg = nums[left]+nums[right]; // if some is distinct then avg would be distinct that's why we only consider the sum
			set.add(avg);
			left++;
			right--;
		}
		return set.size();
	}
	
	public static double[] convertTemprature(double celsius) {
		double[] ans = new double[2];
		double kelvin=celsius+273.25;
		double fahrenheit= celsius*1.80+32.00;
		ans[0]=kelvin;
		ans[1]=fahrenheit;
		return ans;
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
	
		public static int maximuValue(String[] strs) {
		  int max=0;
		  int value=0;
		  for(int i=0;i<strs.length;i++) {
			  if(stringTypeCheck(strs[i])==1) {
				  value=Integer.parseInt(strs[i]);
			  }
			  if(stringTypeCheck(strs[i])== -1 || stringTypeCheck(strs[i])==2) {
				  value=strs[i].length();
			  }
			  max=Math.max(max, value);
		  }
		return max;
		}
	
	private static int stringTypeCheck(String str) {
		int count=0;
		for(int i=0;i<str.length();i++) {
			
			if(Character.isDigit(str.charAt(i))){
				count++;
			}
		}
		if(count==str.length()) {
			return 1;
		}
		if(count==0) {
			return -1;
		}
		return 2; // have digit and char
	}
	
	public static int smiliarPair(String[] words) {
		int count=0;
		for(int i=0;i<words.length-1;i++) {
			for(int j=i+1;j<words.length;j++) {
				if(checkSimiliar(words[i], words[j])) {
					count++;
				}
			}
		}
		return count;
	}
	
	private static boolean checkSimiliar(String s,String t) {
		HashSet<Character> sSet= new HashSet<Character>();
		for(int i=0;i<s.length();i++) {
			sSet.add(s.charAt(i));
		}
		HashSet<Character> tSet= new HashSet<Character>();
		for(int i=0;i<t.length();i++) {
			tSet.add(t.charAt(i));
		}
		if(sSet.equals(tSet)) {
			return true;
		}
		return false;
	}
	
	public static int maxNumberCount(int[] nums) {
		int pos=0;
		int neg=0;
		for(int i=0;i<nums.length;i++) {
			if(nums[i]<0) {
				neg++;
			}else if(nums[i]>0) {
				pos++;
			}
		}
		return Math.max(pos, neg);
	}
	
	public static int getCommon(int[] nums1,int[] nums2) {
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
			if(i%2==1) {
				sum -=Integer.parseInt(num.charAt(i) +"");
			}else {
				sum +=Integer.parseInt(num.charAt(i) +"");
			}
		}
		return sum;
	}
	
	public static long findArrayConcat(int[] nums) {
		int left=0;
		int right=nums.length-1;
		long total=0;
		while(left<right) {
			String conVal=String.valueOf(nums[left])+String.valueOf(nums[right]);
			total +=Long.parseLong(conVal);
			left++;
			right--;
		}
		
		return total;
	}

	
	public static void main(String[] args) {
		int[] nums= {7,52,2,4};
		System.out.println(findArrayConcat(nums));
		//System.out.println(alternateDigitSum(521));
		//int[] nums1= {1,2,3}; int[] nums2= {2,3};
		//System.out.println(getCommon(nums1, nums2));
		//int[] nums= {-2,-1,-1,1,1,2};
		//System.out.println(maxNumberCount(nums));
		//String[] words= {"ab","aabb","abcd","bac","aabc"};
		//System.out.println(smiliarPair(words));
		//String[] strs= {"alic3","bob","3","4","000000"};
		//System.out.println(maximuValue(strs));
		//System.out.println(isCircularSentence("leetcode exercises should delightful"));
		//double celsius =36.50;
		//System.out.println(Arrays.toString(convertTemprature(celsius)));
		//int[] nums= {4,1,4,0,3,5};
		//System.out.println(distictAverage(nums));
		//String[] people = {"Marry","Jonh","Emma"};
		//int[] heights= {180,165,170};
		//System.out.println(Arrays.toString(sortPeople1(people, heights)));
	}

}
