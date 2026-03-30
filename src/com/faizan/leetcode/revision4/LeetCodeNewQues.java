package com.faizan.leetcode.revision4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class LeetCodeNewQues {
	public static String maxOddBinaryNumber(String s) {
		int oneCount=0;
		int zeroCount=0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='1') {
				oneCount++;
			}else {
				zeroCount++;
			}
		}
		StringBuilder builder= new StringBuilder();
		for(int i=0;i<oneCount-1;i++) {
			builder.append("1");
		}
		for(int i=0;i<zeroCount;i++) {
			builder.append("0");
		}
		builder.append("1");
		return builder.toString();
	}
	
	public static int[] intersection(int[] nums1,int[] nums2) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int num:nums1) {
			map.put(num, 1);
			
		}
		for(int num:nums2) {
			if(map.containsKey(num) && map.get(num)==1) {
				list.add(num);
				map.put(num, 0);
			}
		}
		int[] res = new int[list.size()];
		for(int i=0;i<list.size();i++) {
			res[i]=list.get(i);
		}
		return res;
	}
	
	public static int[] intersection1(int[] nums1, int[] nums2) {
	    HashSet<Integer> set = new HashSet<>();
	    HashSet<Integer> resSet = new HashSet<>();

	    for (int num : nums1) set.add(num);

	    for (int num : nums2) {
	        if (set.contains(num)) {
	            resSet.add(num);
	        }
	    }

	    int[] res = new int[resSet.size()];
	    int i = 0;

	    for (int num : resSet) {
	        res[i++] = num;
	    }

	    return res;
	}
	public static int minNumberOfBoxes(int[] apple, int[] boxes) {
	    int sum = 0;
	    for (int ap : apple) {
	        sum += ap;
	    }

	    Arrays.sort(boxes); // ascending

	    int count = 0;

	    for (int i = boxes.length - 1; i >= 0; i--) {
	        sum -= boxes[i];
	        count++;

	        if (sum <= 0) {
	            break;
	        }
	    }

	    return count;
	}
	public static int pivotInteger(int n) {
	    int left = 1, right = n;
	    int leftSum = 1, rightSum = n;

	    while (left < right) {
	        if (leftSum < rightSum) {
	            left++;
	            leftSum += left;
	        } else {
	            right--;
	            rightSum += right;
	        }

	        if (leftSum == rightSum) {
	            return left + 1; // pivot
	        }
	    }

	    return -1;
	}
	public static int sumOfEncryptedInt(int[] nums) {
		int totalSum=0;
		
		for(int num:nums) {
			int temp=num;
			int maxDigit=0;
			int digits=0;
			
			while(temp>0) {
				int rem =temp %10;
				maxDigit =Math.max(maxDigit, rem);
				digits++;
				temp /=10;
			}
			int once=0;
			for(int i=0;i<digits;i++) {
				once += once *10+1; // 111...
			}
			totalSum += maxDigit * once;
		}
		return totalSum;
	}
	public static boolean stringExistence(String s) {
		StringBuilder builder = new StringBuilder(s);
		String revString = builder.reverse().toString();
		ArrayList<String> revList = new ArrayList<String>();
		for(int i=0;i<=revString.length()-2;i++) {
			String revStr = revString.substring(i,i+2);
			revList.add(revStr);
		}
		
		for(int i=0;i<=s.length()-2 ;i++) {
			String subStr= s.substring(i,i+2);
				if(revList.contains(subStr)) {
					return true;
				}
			}
		return false;
	}
	
	public int maximumLengthSubstring(String s) {
	    int[] freq = new int[26];
	    int left = 0;
	    int maxLen = 0;

	    for (int right = 0; right < s.length(); right++) {
	        char ch = s.charAt(right);
	        freq[ch - 'a']++;

	        // shrink window if any char occurs more than 2 times
	        while (freq[ch - 'a'] > 2) {
	            freq[s.charAt(left) - 'a']--;
	            left++;
	        }

	        maxLen = Math.max(maxLen, right - left + 1);
	    }

	    return maxLen;
	}
	public static boolean isIsomorphic(String s,String t) {
		return checkIso(s).equals(checkIso(t));
	}
	private static String checkIso(String s) {
		StringBuilder builder = new StringBuilder();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i=0;i<s.length();i++) {
			char ch=s.charAt(i);
			if(!map.containsKey(ch)) {
				map.put(ch, i);
			}
			builder.append(map.get(ch)).append("-");
		}
		return builder.toString();
	}
	
	public static boolean isIsomorphicOptimized(String s, String t) {
	    if (s.length() != t.length()) return false;

	    HashMap<Character, Character> mapST = new HashMap<>();
	    HashMap<Character, Character> mapTS = new HashMap<>();

	    for (int i = 0; i < s.length(); i++) {
	        char c1 = s.charAt(i);
	        char c2 = t.charAt(i);

	        if (mapST.containsKey(c1) && mapST.get(c1) != c2) return false;
	        if (mapTS.containsKey(c2) && mapTS.get(c2) != c1) return false;

	        mapST.put(c1, c2);
	        mapTS.put(c2, c1);
	    }

	    return true;
	}
	public static int maxDepth(String s) {
		int max=0,openBrackets=0;
		
		for(char ch:s.toCharArray()) {
			if(ch=='(') {
				openBrackets++;
				 max = Math.max(max, openBrackets); // update here
			}else if(ch==')') {
				openBrackets--;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		String s="egg"; String t="add";
		System.out.println(isIsomorphic(s, t));
	}

}
