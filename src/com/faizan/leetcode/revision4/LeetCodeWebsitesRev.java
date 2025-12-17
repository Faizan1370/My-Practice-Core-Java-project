package com.faizan.leetcode.revision4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dsa.tree1.TreeNode;

public class LeetCodeWebsitesRev {
	
	public static List<String> generatePara(int n){
		List<String> list = new ArrayList<String>();
		backTrack(list,"",n,n);
		System.out.println(list.size());
		return list;
	}

	private static void backTrack(List<String> list, String current, int open, int close) {
		if(open==0 && close==0) {
			list.add(current);
			return;
		}
		if(open>0) {
			backTrack(list,current +"(",open-1,close);
		}
		if(close>open) {
			backTrack(list,current +")",open,close-1);
		}
	}
	
	public static int[] removeDuplicate(int[] nums) {
		if(nums.length==0) {
			return nums;
		}
		int i=1;
		for(int j=1;j<nums.length;j++) {
			if(nums[i-1]!=nums[j]) {
				nums[i]=nums[j];
				i++;
			}
		}
		
		return Arrays.copyOf(nums, i);
	}
	 public static String intToRoman(int num) {
		 Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		 map.put(1000, "M");
	        map.put(900, "CM");
	        map.put(500, "D");
	        map.put(400, "CD");
	        map.put(100, "C");
	        map.put(90, "XC");
	        map.put(50, "L");
	        map.put(40, "XL");
	        map.put(10, "X");
	        map.put(9, "IX");
	        map.put(5, "V");
	        map.put(4, "IV");
	        map.put(1, "I");
	        
	        StringBuilder builder = new StringBuilder();
	        for(Map.Entry<Integer, String> entry:map.entrySet()) {
	        	int value = entry.getKey();
	        	String symbol = entry.getValue();
	        	while(num>=value) {
	        		builder.append(symbol);
	        		num -=value;
	        	}
	        	
	        }
	        return builder.toString();
	 }
	 public static boolean wordPattern(String pattern,String s) {
		 String[] words=s.split(" ");
		 if(words.length !=pattern.length()) {
			 return false;
		 }
		 HashMap<Character, String> char_map = new HashMap<Character, String>();
		 HashMap<String, Character> word_map = new HashMap<>();
		 
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
				 if(!char_map.get(ch).equals(word)) {
					 return false;
				 }
			 }
		 }
		 return true;
	 }

	public static void main(String[] args) {
		int num=5;
		System.out.println(intToRoman(num));
	}

}
