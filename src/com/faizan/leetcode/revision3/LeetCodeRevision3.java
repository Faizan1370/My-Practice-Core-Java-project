package com.faizan.leetcode.revision3;

import java.util.Arrays;

public class LeetCodeRevision3 {
	
	public static int findChampion(int[][] grid) {
		
		for(int i=0;i<grid.length;i++) {
			int count=0;
			for(int j=0;j<grid[0].length;j++) {
				if(grid[i][j]==1) {
					count++;
				}
				
			}
			if(count==grid.length-1) {
				return i;
			}
		}
		return -1;
	}
	
	public static boolean checkStringEq(String[] word1,String[] word2) {
		StringBuilder builder = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		for(String word:word1) {
			builder.append(word);
		}
		for(String word:word2) {
			builder2.append(word);
		}
		return builder.toString().equals(builder2.toString());
		
	}
	
	public static int elementAppear(int[] arr) {
		int limit = arr.length / 4;
		for(int i=0;i<arr.length-limit;i++) {
			if(arr[i]==arr[i+limit]) {
				return arr[i];
			}
		}
		return -1;
	}
	public static int[] findCommonElements(int[] nums1,int[] nums2) {
		int[] count1=new int[101];
		int[] count2=new int[101];
		int[] ans=new int[2];
		
		for(int i=0;i<nums1.length;i++) {
			count1[nums1[i]]++;
		}
		for(int i=0;i<nums2.length;i++) {
			count2[nums2[i]]++;
		}
		int result=0,result2=0;
		for(int i=1;i<=100;i++) {
			
			if(count2[i]>=1) {
				result +=count1[i];
			}
			if(count1[i]>=1) {
				result2 +=count2[i];
			}
			
		}
		ans[0]=result;
		ans[1]=result2;
		
		return ans;
	}
	public static int specailPosition(int[][] mat) {
		int row =mat.length;
		int col=mat[0].length;
		int count=0;
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				
				if(mat[i][j]==0) {
					continue;
				}
				boolean flag=false;
				for(int r=0;r<row;r++) {
					if(r!=i && mat[r][j]==1) {
						flag=true;
						break;
					}
				}
				for(int c=0;c<col;c++) {
					if(c!=j && mat[i][c]==1) {
						flag=true;
						break;
					}
				}
				if(!flag) {
					count++;
				}
			}
		}
		return count;
	}
	public static void main(String[] args) {
	int[][] mat= {{1,0,0},{0,0,1},{1,0,0}};
	System.out.println(specailPosition(mat));
	}

}
