package com.dsa.takuforward.dynamic.programing;

import java.util.Arrays;

public class FrogJumpKSteps {
	// Recursion (Brute)
	public int frogJumpK(int[] h, int k) {
		return dfsFrogJumpK(h.length - 1, h, k);
	}

	private int dfsFrogJumpK(int i, int[] h, int k) {
		if (i == 0) {
			return 0;
		}
		int minCost = Integer.MAX_VALUE;
		for (int j = 1; j <= k; j++) {
			if (i - j >= 0) {
				int cost = dfsFrogJumpK(i - j, h, k) + Math.abs(h[i] - h[i - j]);
				minCost = Math.min(minCost, cost);
			}
		}
		return minCost;
	}

	// Memoization (Top-Down)
	public int frogJumpK1(int[] h, int k) {
		int n = h.length;
		int[] memo = new int[n];
		Arrays.fill(memo, -1);
		return dfsFrogJumpK1(n - 1, h, k, memo);
	}

	private int dfsFrogJumpK1(int i, int[] h, int k, int[] memo) {
		if (i == 0) {
			return 0;
		}
		int minCost = Integer.MAX_VALUE;
		if (memo[i] != -1) {
			return memo[i];
		}
		for (int j = 1; j <= k; j++) {
			if (i - j >= 0) {
				int cost = dfsFrogJumpK1(i - j, h, k, memo) + Math.abs(h[i] - h[i - j]);
				minCost = Math.min(minCost, cost);
			}

		}
		return memo[i] = minCost;
	}

	// Tabulation (Bottom-Up)
	public int frogJumpK2(int[] h, int k) {
		int n = h.length;
		int[] dp = new int[n];
		dp[0] = 0;
		for (int i = 1; i < n; i++) {
			int minCost = Integer.MAX_VALUE;
			for (int j = 1; j <= k; j++) {
				if (i - j >= 0) {
					int cost = dp[i - j] + Math.abs(h[i] - h[i - j]);
					minCost = Math.min(minCost, cost);
				}
			}
			dp[i] = minCost;
		}
		return dp[n-1];
		

	}
	//Adding saftey check
	private int dfsFrogJumpKSatfey(int i, int[] h, int k) {
	    if (i == 0) return 0;

	    int minCost = Integer.MAX_VALUE;

	    for (int j = 1; j <= k; j++) {
	        if (i - j >= 0) {
	            int prev = dfsFrogJumpK(i - j, h, k);

	            if (prev != Integer.MAX_VALUE) {  // ✅ safety
	                int cost = prev + Math.abs(h[i] - h[i - j]);
	                minCost = Math.min(minCost, cost);
	            }
	        }
	    }
	    return minCost;
	}
	private int dfsFrogJumpK1Saftey(int i, int[] h, int k, int[] memo) {
	    if (i == 0) return 0;

	    if (memo[i] != -1) return memo[i];

	    int minCost = Integer.MAX_VALUE;

	    for (int j = 1; j <= k; j++) {
	        if (i - j >= 0) {
	            int prev = dfsFrogJumpK1(i - j, h, k, memo);

	            if (prev != Integer.MAX_VALUE) {  // ✅ overflow check
	                int cost = prev + Math.abs(h[i] - h[i - j]);
	                minCost = Math.min(minCost, cost);
	            }
	        }
	    }

	    return memo[i] = minCost;
	}
	
	public int frogJumpK2Saftey(int[] h, int k) {
	    int n = h.length;
	    int[] dp = new int[n];
	    Arrays.fill(dp, Integer.MAX_VALUE);

	    dp[0] = 0;

	    for (int i = 1; i < n; i++) {
	        int minCost = Integer.MAX_VALUE;

	        for (int j = 1; j <= k; j++) {
	            if (i - j >= 0 && dp[i - j] != Integer.MAX_VALUE) { // ✅ check
	                int cost = dp[i - j] + Math.abs(h[i] - h[i - j]);
	                minCost = Math.min(minCost, cost);
	            }
	        }

	        dp[i] = minCost;
	    }

	    return dp[n - 1];
	}

}
