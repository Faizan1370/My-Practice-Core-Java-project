package com.dsa.takuforward.dynamic.programing;

import java.util.Arrays;

public class FrogJump {
	// DFS (Brute Force)
	public int frogJump(int[] h) {
		return dfsFrogJump(h.length - 1, h);
	}

	private int dfsFrogJump(int i, int[] h) {
		if (i == 0) {
			return 0;
		}
		int left = dfsFrogJump(i - 1, h) + Math.abs(h[i] - h[i - 1]);
		int right = Integer.MAX_VALUE;
		if (i > 1) {
			right = dfsFrogJump(i - 2, h) + Math.abs(h[i] - h[i - 2]);
		}
		return Math.min(left, right);
	}

	// Memoization (Top-Down DP)
	public int frogJump1(int[] h) {
		int n = h.length;
		int[] memo = new int[n];
		Arrays.fill(memo, -1);
		return dfsMemoFrogJump(n - 1, h, memo);
	}

	private int dfsMemoFrogJump(int i, int[] h, int[] memo) {
		if (i == 0)
			return 0;
		if (memo[i] != -1) {
			return memo[i];
		}
		int left = dfsMemoFrogJump(i - 1, h, memo) + Math.abs(h[i] - h[i - 1]);
		int right = Integer.MAX_VALUE;
		if (i > 1) {
			right = dfsMemoFrogJump(i - 2, h, memo) + Math.abs(h[i] - h[i - 2]);
		}

		return memo[i] = Math.min(left, right);
	}
	//Tabulation (Bottom-Up)
	public int frogJump2(int[] h) {
		 int n = h.length;

		    int[] dp = new int[n];
		    dp[0] = 0;
		    for(int i=1;i<n;i++) {
		    	int left = dp[i-1]+ Math.abs(h[i] - h[i - 1]);
		    	 int right = Integer.MAX_VALUE;
		         if (i > 1) {
		             right = dp[i - 2] + Math.abs(h[i] - h[i - 2]);
		         }
		         dp[i]=Math.min(left, right);
		    }
		    return dp[n - 1];
	}
	//space Optimized (Best)
	public int frogJump3(int[] h) {
	    int n = h.length;

	    int prev2 = 0; // dp[i-2]
	    int prev1 = 0; // dp[i-1]
	    for (int i = 1; i < n; i++) {
	    	int left =prev1+Math.abs(h[i] - h[i - 1]);
	    	 int right = Integer.MAX_VALUE;
	         if (i > 1) {
	             right = prev2 + Math.abs(h[i] - h[i - 2]);
	         }

	         int curr = Math.min(left, right);
	         prev2 = prev1;
	         prev1 = curr;
	    }
		return prev1;
	}

}
