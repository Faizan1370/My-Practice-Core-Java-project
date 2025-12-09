package com.faizan.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCode13 {
	
	public static int climbStairs(int n) { // we can solve using fibonaci 
		int[] dp = new int[n+1];
		dp[0]=1;
		dp[1]=1;
		for(int i=2;i<=n;i++) {
			dp[i]=dp[i-1]+dp[i-2];
		}
		
		return dp[n];
	}
	public static int climbStairs1(int n) {
		int a=0;
		int b=1;
		int ans=0;
		for(int i=1;i<=n;i++) {
			ans=a+b;
			a=b;
			b=ans;
		}
		return ans;
	}
	
	public static int missingInetger(int[] nums) {
		Set<Integer> set = new HashSet<Integer>();
		int sum=nums[0],maxSum=nums[0];
		for(int num:nums) {
			set.add(num);
		}
		for(int i=1;i<nums.length;i++) {
			if(nums[i]-nums[i-1]==1) {
				sum +=nums[i];
			}else {
				sum=0;
			}
			maxSum=Math.max(maxSum, sum);
			while(set.contains(maxSum)) {
				maxSum++;
			}
		}
		return maxSum;
	}
	
	public static int areaOfMaxDiagonal(int[][] dimensions) {
		int maxArea=0;
		double maxDiagonal=0;
		for(int[] dimension:dimensions) {
			int l =dimension[0];
			int w=dimension[1];
			double diagonla =Math.sqrt(l*l +w *w);
			int area= l*w;
			if(diagonla>maxDiagonal) {
				maxDiagonal = diagonla;
				maxArea=area;
			}else if(diagonla==maxDiagonal) {
				maxArea=Math.max(area, maxArea);
			}
			
		}
		return maxArea;
	}
	public static int maxElementFrequeqyCount(int[] nums) {
		Map<Integer, Long> map = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		 int asInt = map.entrySet().stream().map(entry->entry.getValue()).mapToInt(num->num.intValue()).max().getAsInt();
		 int count=0;
		 for(int num:nums) {
			 if(map.get(num).intValue()==asInt) {
				 count++;
			 }
		 }
		return count;
	}
	
	public  static int minCost(int[] nums) {
		 if (nums.length < 3) {
		        return Arrays.stream(nums).sum(); // or define your behavior
		    }

		int first=nums[0];
		int firstMin=Integer.MAX_VALUE;
		int secondMin=Integer.MAX_VALUE;
		
		for(int i=1;i<nums.length;i++) {
			if(nums[i]<firstMin) {
				secondMin=firstMin;
				firstMin=nums[i];
			}else if(secondMin>nums[i]) {
				secondMin=nums[i];
			}
		}
		return first+firstMin+secondMin;
	}
	
	public static int[] duplicateAndMissing(int[] nums) {
		Map<Integer, Long> map = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		int duplicate=0;
		int missing=0;
		for(int i=1;i<=nums.length;i++) {
			if(map.containsKey(i)) {
				if(map.get(i).intValue()==2) {
					duplicate=i;
				}
			}else {
				missing=i;
			}
		}
		return new int[] {duplicate,missing};
	}
	
	public static int countKeyChanges(String s) {
		int count=0;
		s=s.toLowerCase();
		for(int i=1;i<s.length();i++) {
			if(s.charAt(i)!=s.charAt(i-1)) {
				count++;
			}
		}
		return count;
	}
	
	public static int returnToboundryCount(int[] nums) {
		int count=0;
		int sum=0;
		for(int i=0;i<nums.length;i++) {
			sum +=nums[i];
			if(sum==0) {
				count++;
			}
		}
		return count;
	}
	
	public static boolean isPowerOfTwo(int n) {
		if(n<=0) {
			return false;
		}
		int bitCount = Integer.bitCount(n);
		if(bitCount==1) {
			return true;
		}
		return false;
	}
	
	public static int missingNum(int[] nums) {
		int n=nums.length;
		int originalSum=0;
		for(int i=0;i<nums.length;i++) {
			originalSum +=nums[i];
		}
		int sum=n*(n+1)/2;
		return sum-originalSum;
	}
	
	public static void main(String[] args) {
		int[] nums= {3,0,1};
		System.out.println(missingNum(nums));
	}

}
