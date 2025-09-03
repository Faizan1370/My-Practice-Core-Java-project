package com.faizan.leetcode;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCode11 {
	
	public static int findChampion(int[][] grid) {
		int row =grid.length;
		int col=grid[0].length;
		
		
		for(int i=0;i<row;i++) {
			int count=0;
			for(int j=0;j<col;j++) {
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
	
	public static boolean arrayStringEqual(String[] words1,String[] words2) {
		StringBuilder builder = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		for(String str:words1) {
			builder.append(str);
		}
		for(String str:words2) {
			builder2.append(str);
		}
		
		return builder.toString().equals(builder2.toString());
	}
	
	//This will work if array is sorted Otherwise use count frequncey
	public static int elementAppering25Per(int[] nums) {
	  int limit = nums.length/4; //25%
	  for(int i=0;i<nums.length-limit;i++) {
		  if(nums[i]== nums[i+limit]) {
			  return nums[i];
		  }
	  }
	return -1;
	}
	public static int elementAppering25Per1(int[] nums) {
		  int target = nums.length/4; //25%
		  Integer integer = Arrays.stream(nums).mapToObj(num->(Integer)num)
		  .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		  .entrySet()
		  .stream()
		  .filter(entry->entry.getValue()>target)
		  .map(entry->entry.getKey())
		  .findFirst()
		  .get();
		  return integer;
	}

}
