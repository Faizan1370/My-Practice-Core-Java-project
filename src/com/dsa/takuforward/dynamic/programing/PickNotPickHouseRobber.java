package com.dsa.takuforward.dynamic.programing;

import java.util.Arrays;

public class PickNotPickHouseRobber {
   //Recursion (Brute)
	public int rob(int[] nums) {
		return dfsRob(nums.length-1,nums);
	}

	private int dfsRob(int i, int[] nums) {
		if(i<0) {
			return 0;
		}
		if(i==0) {
			return nums[0];
		}
		int pick=nums[i]+dfsRob(i-2, nums);
		int notPick =dfsRob(i-1,nums);
		
		return Math.max(pick, notPick);
	}
	//Memoization (Top-Down)
	public int rob1(int[] nums) {
		int n=nums.length;
		int[] memo = new int[n];
		Arrays.fill(memo, -1);
		return dfsRob1(n-1,nums,memo);
	}

	private int dfsRob1(int i, int[] nums, int[] memo) {
		if(i<0) {
			return 0;
		}
		if(i==0) {
			return nums[0];
		}
		if(memo[i]!=-1) {
			return memo[i];
		}
		int pick = nums[i]+dfsRob1(i-2,nums,memo);
		int notPick=dfsRob1(i-1,nums,memo);
		return memo[i]=Math.max(pick, notPick);
	}
	//Tabulation (Bottom-Up)
	public int rob2(int[] nums) {
		int n = nums.length;
	    if (n == 1) return nums[0];

	    int[] dp = new int[n];
	    dp[0] = nums[0];
	    
	    for(int i=1;i<n;i++) {
	    	int pick=nums[i];
	    	if(i>2) {
	    		pick +=dp[i-2];
	    	}
	    	int notPick=dp[i-1];
	    	dp[i]=Math.max(pick, notPick);
	    }
		return dp[n-1];
	}
	//Space Optimized (Best)
	public int rob3(int[] nums) {
		int n = nums.length;
		int prev2 = 0;        // dp[i-2]
		int prev1 = nums[0];  // dp[i-1]
		for(int i=1;i<n;i++) {
			int pick=nums[i]+prev2;
			int notPick =prev1;
			int cur=Math.max(pick, notPick);
			prev2=prev1;
			prev1=cur;
		}
		return prev1;

	}
}
