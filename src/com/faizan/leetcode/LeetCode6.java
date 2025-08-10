package com.faizan.leetcode;

import java.util.HashMap;

public class LeetCode6 {
	
	public static boolean checkAlmostEquivalent(String word1,String word2) {
		int[] freq1 =new int[26];
		int[] freq2= new int[26];
		 
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
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for(int i=0;i<word1.length;i++) {
			if(map.containsKey(word1[i])) {
				map.put(word1[i], map.get(word1[i])+1);
			}else {
				map.put(word1[i], 1);
			}
		}
		for(String word:word2) {
			if(map.containsKey(word)) {
				if(map.get(word)<=1) {
					map.put(word, map.get(word)-1);
				}
			}
		}
		int count=0;
		for(String word:word2) {
			if(map.get(word)==0) {
				count++;
			}
		}
		return count;
	}
	
	public static String firstPalindromic(String[] words) {
		for(int i=0;i<words.length;i++) {
			if(isPalindrome(words[i])) {
				return words[i];
			}
		}
		return "";
		
	}
	private static boolean isPalindrome(String str) {
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
	
	public static String captilizeTitle(String title) {
		title=title.toLowerCase();
		String[] words = title.split(" ");
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<words.length;i++) {
			if(words[i].length()<3) {
				builder.append(words[i] + " ");
			}else {
				char ch = words[i].charAt(0);
				ch= Character.toUpperCase(ch);
				String new_word=ch+words[i].substring(1);
				builder.append(new_word + " ");
			}
		}
		return builder.toString().trim();
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
		while(n!=0) {
			int r=n%10;
			sum=sum+r;
			n=n/10;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println(countEven(4));
		//String title="First leTTer oF Each Worlds";
		//System.out.println(captilizeTitle(title));
		//String[] words= {"abc","ada","radar","racecal","madam"};
		//System.out.println(firstPalindromic(words));
		//String[] word1= {"leetcode","is","amazing","as","is"};
		//String[] word2 = {"leetcode","amazing","is"};
		//System.out.println(countWords(word1, word2));
		//String word1="abcdeef";
		//String word2 ="abaaacc";
		//System.out.println(checkAlmostEquivalent(word1, word2));
	}

}
