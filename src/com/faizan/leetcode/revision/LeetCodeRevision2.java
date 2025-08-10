package com.faizan.leetcode.revision;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LeetCodeRevision2 {
	
	public static boolean ransomNote(String rensomNote,String magzine) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		
		for(int i =0;i<magzine.length();i++) {
			char ch=magzine.charAt(i);
			if(map.containsKey(ch)) {
				map.put(ch,map.get(ch)+1);	
			}else {
				map.put(ch, 1);
			}
			
		}
		int count=0;
		for(int i =0;i<rensomNote.length();i++) {
			char ch= rensomNote.charAt(i);
			if(map.containsKey(ch) && map.get(ch)>0) {
				map.put(ch, map.get(ch)-1);
				count++;
			}else {
				break;
			}
		}
		if(count==rensomNote.length()) {
			return true;
		}
		return false;		
	}
	
	public static char findDiffence(String s,String t) {
		int s_sum=0;
		int t_sum=0;
		
		for(int i=0;i<s.length();i++) {
			s_sum +=s.charAt(i);
		}
		
		for(int i=0;i<t.length();i++) {
			t_sum +=t.charAt(i);
		}
		return (char) (t_sum-s_sum);
	}
	
	public static int maxConsecutiveOnes(int[] array) {
		int count=0;
		int maxCount=0;
		for(int i=0;i<array.length;i++) {
			if(array[i]==1) {
				count++;
				maxCount=Math.max(count, maxCount);
			}else {
				count=0;
			}
		}
		return maxCount;
		
	}
	
	public static boolean directCapital(String s) {
		if(allCap(s) || allLow(s) || firstCap(s)) {
			return true;
		}
		return false;
		
	}
	
	private static boolean allCap(String s) {
		int count=0;
		for(int i=0;i<s.length();i++) {
			if(Character.isUpperCase(s.charAt(i))) {
				count++;
			}
		}
		
		return s.length()==count;
	}
	
	private static boolean allLow(String s) {
		int count=0;
		for(int i=0;i<s.length();i++) {
			if(Character.isLowerCase(s.charAt(i))) {
				count++;
			}
		}
		
		return s.length()==count;
	}
	
	private static boolean firstCap(String s) {
		String substrig =s.substring(1);
		if(Character.isUpperCase(s.charAt(0)) && allLow(substrig)) {
			return true;
		}
		return false;
	}
	
	public static String reverseString(String s) {
		String[] split = s.split(" ");
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<split.length;i++){
			if(i!=split.length-1) {
			builder.append(reverseWord(split[i])+" ");
			}else {
				builder.append(reverseWord(split[i]));
			}
		}
		return builder.toString();
	}
	
	private static String reverseWord(String word) {
		String result="";
		for(int i=word.length()-1;i>=0;i--) {
			result +=word.charAt(i);
		}
		return result;
	}
	
	public static int findElement(int[] nums,int element) {
		int start=0;
		int end=nums.length-1;
		int mid=0;
		while(start<end) {
			mid=start+(end-start)/2;
			if(nums[mid]==element) {
				return mid;
			}else if(nums[mid]<element) {
				start=mid+1;
			}else if(nums[mid]>element) {
				end=mid-1;
			}
		}
		return -1;
	}
	
	public static int jwelsAndStones(String jwels,String stones) {
		HashSet<Character> set = new HashSet<Character>();
		int count=0;
		for(int i=0;i<jwels.length();i++) {
			set.add(jwels.charAt(i));
		}
		for(int i=0;i<stones.length();i++) {
			if(set.contains(stones.charAt(i))) {
				count++;
			}
		}
		return count;
	}

	public static int[][] transposeMattrix(int[][] matrix){
		int rows=matrix.length;
		int cols=matrix[0].length;
		
		int[][] result = new int[cols][rows];
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				result[j][i]=matrix[i][j];
			}
		}
		return result;
		
	}
	
	public static ListNode findMiddleElement(ListNode head) {
		if(head==null) {
			return null;
		}
		ListNode slow=head;
		ListNode fast=head;
		
		while(fast !=null && fast.next!=null) {
			slow=slow.next;
			fast=fast.next.next;
		}
		return slow;
	}
	
	public static boolean validMountainArray(int[] array) {
		int len=array.length;
		int i=0;
		int j=len-1;
		if(len<3) {
			return false;
		}
		while(i+1<len && array[i]<array[i+1]) {
			i++;
		}
		System.out.println(i);
		while(j>0 && array[j-1]>array[j]) {
			j--;
		}
		System.out.println(j);
		
		if(i>0 && j<len-1) {
			if(i==j) {
				return true;
			}
		}
		return false;
		
	}
	
	
	public static void main(String[] args) {
		int[] arr= {0,1,2,3,4,5,3,2,1,0};
		System.out.println(validMountainArray(arr));
		
		 // int[][] matrix = { {1, 4, 6}, {5, 7, -2}, {4, -10, 12} }; 
		  //int[][] tranposeMatrix = transposeMattrix(matrix); 
		  //for (int[] row : tranposeMatrix) {
		  //System.out.println(Arrays.toString(row)); 
		 // }
		 
		//String jwels="aA";
		//String stones="aAAbbb";
		//System.out.println(jwelsAndStones(jwels, stones));
		//int[] nums= {1,4,7,9,10,11,15};
		//int element=11;
		//System.out.println(findElement(nums, element));
		//String s="Hello World";
		//System.out.println(reverseString(s));
		//String s="AAA";
		//System.out.println(directCapital(s));
		//int[] array= {1,1,0,1,1,1};
		//System.out.println(maxConsecutiveOnes(array));
		//String s="a";
		//String t="ab";
		//System.out.println(findDiffence(s, t));
		//String resomNote="aa";
		//String magzine="ab";
		//System.out.println(ransomNote(resomNote, magzine));
	}

}
