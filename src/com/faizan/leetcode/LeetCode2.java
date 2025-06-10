package com.faizan.leetcode;

import java.util.Arrays;
import java.util.HashSet;

public class LeetCode2 {
	
	public static int jewlesAndStones() {
		String jewles="aA";
		String stones ="aAAAbbbb";
		HashSet<Character> set= new HashSet<Character>();
		int count=0;
		for(int i =0;i<jewles.length();i++) {
			set.add(jewles.charAt(i));
		}
		for(int i =0;i<stones.length();i++) {
			if(set.contains(stones.charAt(i))) {
				count++;
			}
		}
		return count;
	}
	
	public static int[][] tranposeMatrix(int[][] matrix) {
		int rows =matrix.length;
		int cols=matrix[0].length;
		int[][] transpose= new int[cols][rows];
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				transpose[j][i]= matrix[i][j];
			}
		}
		return transpose;
	}
	
	public static ListNode middleNode(ListNode head) {
		ListNode current=head;
		ListNode slow=head;
		ListNode fast=head;
		 while(current!=null && current.next!=null) {
			 slow=slow.next;
			 fast=fast.next.next;
		 }
		return slow;
	}
	
	public static boolean validMountainArray(int[] arr) {
		int len=arr.length;
		if(len<3) {
			return false;
		}
		int i=0,j=len-1;
		while(i+1<len && arr[i]<arr[i+1] ) {
			i++;
		}
		while(j>0 && arr[j-1]>arr[j]) {
			j--;
		}
		if(i>0 && j<len-1) {
		if(i==j) {
			return true;
		}
		}
		return false;
	}
	
	public static int[] sortedSqaures(int[] nums) {
		int[] result= new int[nums.length];
		int left =0;
		int right=nums.length-1;
		for(int i=nums.length-1;i>=0;i--) {
			int leftSquare=nums[left]*nums[left];
			int rightSquare=nums[right]*nums[right];
			if(leftSquare>rightSquare) {
				result[i]=leftSquare;
				left++;
			}else {
				result[i]=rightSquare;
				right--;
			}
		}
		return result;
				
	}
	public static void main(String[] args) {
		int[] nums= {-4,-1,0,3,10};
		System.out.println(Arrays.toString(sortedSqaures(nums)));
		//int[] arr= {0,2,3,4,5,2,1,0};
		//System.out.println(validMountainArray(arr));
		/*
		 * int[][] matrix = { {1, 4, 6}, {5, 7, -2}, {4, -10, 12} }; int[][]
		 * tranposeMatrix = tranposeMatrix(matrix); for (int[] row : tranposeMatrix) {
		 * System.out.println(Arrays.toString(row)); }
		 */
	}

}
