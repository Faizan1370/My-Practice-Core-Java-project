package com.faizan.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class Leetcode3 {
	static PriorityQueue<Integer> integers = new PriorityQueue<Integer>(
			new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return o2-o1;
				}
			});
			
	
	public static int lastWeight(int[] stones) {
		for(int i=0;i<stones.length;i++) {
			integers.add(stones[i]);
		}
		
		while(integers.size()>1) {
			int stone1 = integers.poll();
			int stone2 =integers.poll();
			if(stone1!=stone2) {
				integers.add(stone1-stone2);
			}
		}
		if(integers.isEmpty()) {
			return 0;
		}
		return integers.poll();
	}
	
	public static String removeAdjacentDuplicate(String s) {
		Stack<Character> stack = new Stack<Character>();
		for(int i=0;i<s.length();i++) {
			char ch =s.charAt(i);
			if(stack.isEmpty()) {
				stack.push(ch);
			}else if(ch== stack.peek()) {
				stack.pop();
			}else {
				stack.push(ch);
			}
		}
		StringBuilder builder = new StringBuilder();
		while(!stack.isEmpty()) {
			builder.append(stack.pop());
		}
		return builder.reverse().toString();
	}
	
	public static int balanceStringSplit(String s) {
		if(s==null || s.length()==0) {
			return 0;
		}
		int count=0;
		int left=0; // for L counter
		int right=0; //For R counter
		
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='L') {
				left++;
			}else {
				right++;
			}
			if(left==right) {
				count++;
			}
		}
		return count;
	}
	
	public static int findNumbers(int[] nums) {
		if(nums.length==0) {
			return 0;
		}
		int count=0;
		for(int i=0;i<nums.length;i++) {
			int numsCount =0;
			while(nums[i]!=0) {
				nums[i]=nums[i]/10;
				numsCount++;
			}
			if(numsCount%2==0) {
				count++;
			}
		}
		return count;
	}
	
	
	public static int countNegatives(int[][] grid) {
		int totalRows=grid.length;
		int totaCols=grid[0].length;
		int row=0,col=totaCols-1,count=0;
		while(row<totalRows && col>=0) {
			if(grid[row][col]<0) {
				col--;
				count +=totalRows-row;
				//System.out.println(count +" "+row +" "+totalRows);
			}else {
				row++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		int[][] grid= {{4,3,2,-1},{3,2,1,-1},{1,1,-1,-2},{-1,-1,-2,-3}};
		System.out.println(countNegatives(grid));
		//int[] nums= {12,345,2,6,7896};
		//System.out.println(findNumbers(nums));
		
		//String s="RLRRLLRLRL";
		//String s="RLRRRLLRLL";
		//System.out.println(balanceStringSplit(s));
		//String s="abbaca";
		//System.out.println(removeAdjacentDuplicate(s));
		//int[] stones= {2,7,4,1,8,1};
		//System.out.println(lastWeight(stones));
	}

}
