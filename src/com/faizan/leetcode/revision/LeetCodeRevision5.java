package com.faizan.leetcode.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCodeRevision5 {
	
	
	public static boolean checkStringEquivalent(String word1,String word2) {
		int[] freq1= new int[26];
		int[] freq2=new int[26];
		
		for(int i=0;i<word1.length();i++) {
			freq1[word1.charAt(i)-'a']++;
		}
		for(int i=0;i<word2.length();i++) {
			freq2[word2.charAt(i)-'a']++;
		}
		int count=0;
		for(int i=0;i<26;i++) {
			if(Math.abs(freq1[i]-freq2[i])>3) {
				count++;
				break;
			}
		}
		if(count==1) {
			return false;
		}
		return true;
	}
	
	public static int countWords(String[] word1,String[] word2) {
	    Map<String, Long> map = Arrays.stream(word1).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
	    
	     for(String word:word2) {
	    	if(map.containsKey(word) && map.get(word)<=1) {
	    		map.put(word, map.get(word)-1);
	    	}
	    	
	    }
	     int count=0;
	     for(String word:word2) {
	    	if(map.containsKey(word) && map.get(word)==0) {
	    		count++;
	    	}
	    	}
	     
	     
		return count;
	}
	
	public static String firstPlandromicString(String[] words) {
		for(int i=0;i<words.length;i++) {
			if(checkPalindrome(words[i])) {
				return words[i];
			}
		}
		return "";
	}
	
	private static boolean checkPalindrome(String word) {
		int start=0;
		int end =word.length()-1;
		while(start<end) {
			if(word.charAt(start)!=word.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
	
	public static String capatilizeTitle(String title) {
		String[] words=title.split(" ");
		StringBuilder  builder = new StringBuilder();
		for(int i=0;i<words.length;i++) {
			if(words[i].length()<=2) {
				builder.append(allLower(words[i]) + " ");
			}else {
				builder.append(firstCap(words[i]) + " ");
			}
		}
		return builder.toString().trim();
	}
	
	public static String allLower(String word) {
			 return word.toLowerCase();
	}
	public static String firstCap(String word) {
		return word.substring(0, 1).toUpperCase()+allLower(word.substring(1));
	}
	
	public static String capitalizeTitle1(String title) {
	    String[] words = title.split(" ");
	    List<String> result = new ArrayList<>();

	    for (String word : words) {
	        if (word.length() <= 2) {
	            result.add(word.toLowerCase());
	        } else {
	            result.add(word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase());
	        }
	    }
	    return String.join(" ", result);
	}
	
	
	public static int countEven(int num) {
		int count=0;
		for(int i=1;i<=num;i++) {
			if(sumOfDigits(i)%2==0) {
				count++;
			}
			
		}
		return count;
	}
	
	private static int sumOfDigits(int n) {
		int sum=0;
		while(n>0) {
			int r=n%10;
			sum=sum+r;
			n=n/10;
		}
		return sum;
	}
	
	public static String[] sortPeople(String[] names,int[] weight) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		
		for(int i=0;i<names.length;i++) {
			map.put(weight[i], names[i]);
		}
		
		Arrays.sort(weight);
		String[] ressult= new String[names.length];
		int index=0;
		for(int i=weight.length-1;i>=0;i--) {
			ressult[index]=map.get(weight[i]);
			index++;
		}
		return ressult;
	}
	
	public static String[] sortPeople1(String[] names, int[] weight) {
	    Integer[] indices = new Integer[names.length];
	    for (int i = 0; i < names.length; i++) {
	        indices[i] = i;
	    }

	    // Sort indices based on weight in descending order
	    Arrays.sort(indices, (a, b) -> weight[b] - weight[a]);

	    String[] result = new String[names.length];
	    for (int i = 0; i < indices.length; i++) {
	        result[i] = names[indices[i]];
	    }

	    return result;
	}
	
	public static int distinctAvg(int[] nums) {
		HashSet<Integer> set= new HashSet<Integer>();
		Arrays.sort(nums);
		int left=0;
		int right=nums.length-1;
		
		while(left<right) {
			int avg=(nums[left]+nums[right])/2;
			set.add(avg);
			left++;
			right--;
		}
		return set.size();
	}
	
	public static double[] temprateureConvert(double celsius) {
		double[] ans= new double[2];
		double kelvin=celsius+273.25;
		double fahrenheit= celsius*1.80+32.00;
		ans[0]=kelvin;
		ans[1]=fahrenheit;
		
		return ans;
	}



	
	public static void main(String[] args) {
		double celsius=36.50;
		System.out.println(Arrays.toString(temprateureConvert(celsius)));
		//int[] nums= {4,1,4,0,3,5};
		//System.out.println(distinctAvg(nums));
		//String[] names= {"Marry","John","Emma"};
		//int[] weight= {180,180,170};
		//System.out.println(Arrays.toString(sortPeople1(names, weight)));
		int num=4;
		System.out.println(countEven(num));
		//String title="First Title of each word";
		//System.out.println(capatilizeTitle(title));
		//String[] words = {"leetcode","is","mam","radar"};
		//System.out.println(firstPlandromicString(words));
		//String[] word1 = {"leetcode","is","good","platform"};
		//String[] word2= {"It","is","good","platform","by","leetcode"};
		//System.out.println(countWords(word1, word2));
		//String word1="abcdeef", word2="abaaacc";
		//System.out.println(checkStringEquivalent(word1, word2));
	}
	

}
