package com.faizan.string.prac.prac;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class StringPrac {
	
	public static void findLongestPrefix() {
		String[] array = {"faizan","farhan","faizal"};
		String prefix = array[0];
		
		for(int i=1;i<array.length;i++) {
			while(!array[i].startsWith(prefix)) {
				prefix= prefix.substring(0,prefix.length()-1);
			}
		}
		System.out.println(prefix);
	}
	
	public static void stringCompresse() {
		String str="aabbbcccdddd";
		StringBuilder builder =new StringBuilder();
		int count=1;
		 for(int i=0;i<str.length()-1;i++) {
			 if(str.charAt(i) == str.charAt(i+1)) {
				 count++;
			 }else {
				 builder.append(str.charAt(i)).append(count);
				 count=1;
			 }
		 }
		 builder.append(str.charAt(str.length()-1)).append(count);
		 System.out.println(builder.toString());
	}
	
	public static String lengthOfLongestSubstring(String s) {
        int n = s.length(), maxLen = 0;
        int left = 0, right = 0;
        int maxStart = 0; // Start index of max substring
        HashSet<Character> seen = new HashSet<>();

        while (right < n) {
            if (!seen.contains(s.charAt(right))) {
                seen.add(s.charAt(right++));
                if (right - left > maxLen) {
                    maxLen = right - left;
                    maxStart = left;
                }
            } else {
                seen.remove(s.charAt(left++));
            }
        }

        return s.substring(maxStart, maxStart + maxLen);
    }

	
	public static void main(String[] args) {
		//findLongestPrefix();
		//stringCompresses();
		String str="faizan";
	     String lengthOfLongestSubstring = lengthOfLongestSubstring(str);
		System.out.println(lengthOfLongestSubstring);
	}

}
