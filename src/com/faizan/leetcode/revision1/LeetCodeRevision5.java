package com.faizan.leetcode.revision1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeetCodeRevision5 {
	
	public static int signProduct(int[] nums) {
		int neg=0;
		for(int i=0;i<nums.length;i++) {
			if(nums[i]<0) {
				neg++;
			}else if(nums[i]==0) {
				return 0;
			}
		}
		if(neg%2==0) {
			return 1;
		}else {
			return -1;
		}
	}
	
	public static boolean checkEquivalent(String word1,String word2) {
		int[] freq_1=new int[26];
		int[] freq_2=new int[26];
		
		for(int i=0;i<word1.length();i++) {
			freq_1[word1.charAt(i)-'a']++;
		}
		
		for(int i=0;i<word2.length();i++) {
			freq_2[word2.charAt(i)-'a']++;
		}
		int count=0;
		for(int i=0;i<26;i++) {
			if(Math.abs(freq_1[i]-freq_2[i])>3) {
				count++;
				break;
			}
		}
		if(count==1) {
			return false;
		}
		
		return true; 
	}
	
	public static int coountWord(String[] word1,String[] word2) {
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
	
	public static String firstPalindramicString(String[] words) {
		
		for(String word:words) {
			if(checkPalindram(word)) {
				return word;
			}
			
		}
		
		return "";
		
	}
	
	private static boolean checkPalindram(String word) {
		int left=0;
		int right=word.length()-1;
		while(left<right) {
			
			if(word.charAt(left)!=word.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
	
	public static String capitalize(String s) {
		String[] words = s.split(" ");
		StringBuilder builder = new StringBuilder();
		for(String word:words) {
			if(word.length()<=2) {
			builder.append(allLower(word)).append(" ");	
			}else {
				builder.append(firstCap(word)).append(" ");
			}
		}
		return builder.toString().trim();
	}
	
	private static String allLower(String word) {
		return word.toLowerCase();
	}
	
	private static String firstCap(String word) {
		StringBuilder builder = new StringBuilder();
		builder.append(Character.toUpperCase(word.charAt(0)))
		.append(word.substring(1));
		
		return builder.toString();
		
	}
	
	public static int countDigit(int num) {
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
			sum =sum+r;
			n=n/10;
		}
		return sum;
	}
	public static String[] sortPeople(String[] names,int[] weights) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		String[] result= new String[weights.length];
		for(int i=0;i<weights.length;i++) {
			map.put(weights[i], names[i]);
			
		}
		Arrays.stream(weights).mapToObj(num->(Integer)num).sorted(Comparator.reverseOrder());
		for(int i=0;i<weights.length;i++) {
			result[i]=map.get(weights[i]);
		}
		return result;
	}
	
	public static String[] sortPeople1(String[] names,int[] weights) {
		Integer[] indices=new Integer[names.length];
		for(int i=0;i<names.length;i++) {
			indices[i]=i;
		}
		Arrays.sort(indices,(a,b)->weights[b]-weights[a]);
		String[] result= new String[names.length];
		for(int i=0;i<indices.length;i++) {
			result[i]=names[indices[i]];
		}
		return result;
		
	}
	public static int distinctAvg(int[] nums) {
		HashSet<Integer> set= new HashSet<Integer>();
		int left=0;
		int right=nums.length-1;
		while(left<right) {
			int avg =(nums[left]+nums[right])/2;
			set.add(avg);
			left++;
			right--;
		}
		return set.size();
	}
	
	public static double[] tempratorConv(double num) {
	   double[] result=new double[2];
	   result[0]=num+273.15;
	   result[1]=num*1.80+32.00;
	   return result;
	}
	
	public static boolean circularSentence(String sentence) {
		String[] words = sentence.split(" ");
		if(words.length==1) {
			return sentence.charAt(0) == sentence.charAt(sentence.length() - 1);
		}
		for(int i=1;i<sentence.length();i++) {
			if(!Character.isLetter(sentence.charAt(i))) {
				if(sentence.charAt(i-1) != sentence.charAt(i+1)) {
					return false;
				}
			}
		}
		return sentence.charAt(0)== sentence.charAt(sentence.length()-1);
	}
	
	public static int maximumValue(String[] strs) {
		int value=0,maxValue=0;
		for(String word:strs) {
			if(checkWorld(word)==1) {
				value=Integer.parseInt(word);
			}
			if(checkWorld(word)==-1 || checkWorld(word)==2) {
				value=word.length();
			}
			maxValue=Math.max(value, maxValue);
		}
		return maxValue;
	}
	
	private static int checkWorld(String word) {
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
	public static int countPairs(String[] words) {
		int count =0;
		for(int i=1;i<words.length;i++) {
		   if(checkWords(words[i],words[i-1])) {
			  count++; 
		   }
		}
		return count;
	}
	
	private static boolean checkWords(String word1,String word2) {
		HashSet<Character> set1= new HashSet<Character>();
		HashSet<Character> set= new HashSet<Character>();
		
		for(int i=0;i<word1.length();i++) {
			set.add(word1.charAt(i));
		}
		for(int i=0;i<word2.length();i++) {
			set1.add(word2.charAt(i));
		}
		
		return set.equals(set1);
	}
	
	public static int maxCountPosNeg(int[] nums) {
		int n_count=0;
		int pos_count=0;
		for(int num:nums) {
			if(num<0) {
				n_count++;
			}else {
				pos_count++;
			}
		}
		return Math.max(n_count, pos_count);
	}
	
	public static int minimumCommonValue(int[] nums1,int[] nums2) {
		HashSet<Integer> hashSet = new HashSet<Integer>();
		for(int num:nums1) {
			hashSet.add(num);
		}
		for(int num:nums2) {
			if(hashSet.contains(num)) {
				return num;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		int[] nums1= {1,2,3};
		int[] nums2= {2,3};
		System.out.println(minimumCommonValue(nums1, nums2));
		//int[] nums= {-1,4,5,-2,3,-7};
		//System.out.println(maxCountPosNeg(nums));
		//String[] words= {"aba","aabb","abcd","bac","aabc"};
		//System.out.println(countPairs(words));
		//String[] words= {"alic3","bob","3","4","00000"};
		//System.out.println(maximumValue(words));
		//String str="leetcode exercises should delightfull";
		//System.out.println(circularSentence(str));
		//double cel=36.50;
		//System.out.println(Arrays.toString(tempratorConv(cel)));
		//int[] nums= {4,1,4,0,3,5};
		//System.out.println(distinctAvg(nums));
		//String[] name= {"marry","john","Emma"};
		//int[] weighs= {180,165,170};
		//System.out.println(Arrays.toString(sortPeople1(name, weighs)));
		//int num=4;
		//System.out.println(countDigit(num));
		//String words="Hi Bro How are you";
		//System.out.println(capitalize(words));
		//String[] words= {"abc","aba","madam"};
		//System.out.println(firstPalindramicString(words));
		//String[] word1 = {"leetode","is","amazing","as","is"};
		//String[] word2= {"leetode","amazing","is"};
		//System.out.println(coountWord(word1, word2));
		//String word1="abcdeef";
		//String word2="abaaacc";
		//System.out.println(checkEquivalent(word1, word2));
		//int[] nums= {-1,-2,-3,-4,3,2,1};
		//System.out.println(signProduct(nums));
	}

}
