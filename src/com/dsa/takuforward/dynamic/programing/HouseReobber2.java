package com.dsa.takuforward.dynamic.programing;

import java.util.Arrays;

public class HouseReobber2 {
  //Recursion (Brute)
	public int rob(int[] nums) {
		int  n=nums.length;
		if (n == 1) return nums[0];
		int case1 = dfsRob(nums,0,n-2);
		int case2 = dfsRob(nums,1,n-1);
		return Math.max(case1, case2);
	}

	private int dfsRob(int[] nums, int i, int end) {
		if(i>end) {
			return 0;
		}
		int pick = nums[i]+dfsRob(nums, i+2, end);
		int notPick =dfsRob(nums, i+1, end);
		return Math.max(pick, notPick);
	}
	//Memoization (Top-Down)
	public int rob1(int[] nums) {
		 int n = nums.length;
		    if (n == 1) return nums[0];
		    int[] memo1 = new int[n];
		    int[] memo2 = new int[n];
		    Arrays.fill(memo1, -1);
		    Arrays.fill(memo2, -1);
		    int case1=dfsRob1(nums,0,n-2,memo1);
		    int case2 =dfsRob1(nums, 1, n-1, memo2);
		    return Math.max(case1, case2);
	}

	private int dfsRob1(int[] nums, int i, int end, int[] memo) {
		 if (i > end) return 0;

		    if (memo[i] != -1) return memo[i];
		    int pick=nums[i]+dfsRob1(nums,i+2,end,memo);
		    int notPcik=dfsRob1(nums, i+1, end, memo);
		return memo[i]=Math.max(pick, notPcik);
	}
	//bulation (Bottom-Up)
	public int rob2(int[] nums) {
	    int n = nums.length;
	    if (n == 1) return nums[0];

	    int case1 = robLinear(nums, 0, n - 2);
	    int case2 = robLinear(nums, 1, n - 1);

	    return Math.max(case1, case2);
	}

	private int robLinear(int[] nums, int start, int end) {
	    int len = end - start + 1;
	    int[] dp = new int[len];

	    dp[0] = nums[start];

	    for (int i = 1; i < len; i++) {
	        int pick = nums[start + i];
	        if (i > 1) pick += dp[i - 2];

	        int notPick = dp[i - 1];

	        dp[i] = Math.max(pick, notPick);
	    }

	    return dp[len - 1];
	}
	//Space Optimized (Best)
	public int rob3(int[] nums) {
	    int n = nums.length;
	    if (n == 1) return nums[0];

	    int case1 = robLinear1(nums, 0, n - 2);
	    int case2 = robLinear1(nums, 1, n - 1);

	    return Math.max(case1, case2);
	}

	private int robLinear1(int[] nums, int start, int end) {
	    int prev2 = 0;
	    int prev1 = 0;

	    for (int i = start; i <= end; i++) {
	        int pick = nums[i] + prev2;
	        int notPick = prev1;

	        int curr = Math.max(pick, notPick);

	        prev2 = prev1;
	        prev1 = curr;
	    }

	    return prev1;
	}
}
