package com.dsa.takuforward.dynamic.programing;

import java.util.Arrays;

public class ClimbingStairs {
	// DFS / Recursion (Brute Force)
	public int climbStairs(int n) { // very slow
		return dfsClimbingStairs(n);
	}

	private int dfsClimbingStairs(int n) {
		if (n == 0) {
			return 1;
		}
		if (n < 0) {
			return 0;
		}
		return dfsClimbingStairs(n - 1) + dfsClimbingStairs(n - 2);
	}

	// Recursion + Memoization (Top-Down DP)
	public int climbStairs1(int n) {
		int[] memo = new int[n + 1];
		Arrays.fill(memo, -1);
		return dfsMemoClimbin(n, memo);

	}

	private int dfsMemoClimbin(int n, int[] memo) {
		if (n == 0)
			return 1;
		if (n < 0)
			return 0;
		if (memo[n] != -1) {
			return memo[n];
		}
		memo[n] = dfsMemoClimbin(n - 1, memo) + dfsMemoClimbin(n - 1, memo);
		return memo[n];
	}
	//Bottom-Up DP (Tabulation)
	public int climbStairs3(int n) {
		 if (n <= 2) return n;
		 int[] dp = new int[n + 1];
		 dp[1]=1;
		 dp[2]=2;
		 for(int i=3;i<=n;i++) {
			 dp[i]=dp[i-1]+dp[i-2];
		 }
		return dp[n];
	}
	//Space Optimized (Best)
	public int climbStairs4(int n) {
		if (n <= 2) return n;

	    int prev2 = 1; // dp[i-2]
	    int prev1 = 2; // dp[i-1]

	    for (int i = 3; i <= n; i++) {
	        int curr = prev1 + prev2;
	        prev2 = prev1;
	        prev1 = curr;
	    }

	    return prev1;
	}
	//fibonacci style
	public int climbStairs5(int n) {
	    if (n <= 1) return 1;

	    int a = 0, b = 1;

	    for (int i = 1; i <= n; i++) {
	        int c = a + b;
	        a = b;
	        b = c;
	    }

	    return b;
	}

}
