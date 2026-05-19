package com.dsa.takuforward.dynamic.programing;

import java.util.Arrays;

public class SubsetSumToK {

	// Recursion (Brute)
	public boolean subsetSumToK(int[] arr, int target) {
		int n = arr.length;

		return dfs(n - 1, target, arr);
	}

	private boolean dfs(int i, int target, int[] arr) {
		// target achieved
		if (target == 0) {
			return true;
		}
		// first element
		if (i == 0) {
			return arr[0] == target;
		}
		boolean notTake = dfs(i - 1, target, arr);
		boolean take = false;
		if (target >= arr[i]) {
			take = dfs(i - 1, target - arr[i], arr);
		}

		return take || notTake;
	}

	// memoization
	public boolean subsetSumToK1(int[] arr, int target) {
		int n = arr.length;
		int[][] memo = new int[n][target + 1];

		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}

		return dfs1(n - 1, target, arr, memo);
	}

	private boolean dfs1(int i, int target, int[] arr, int[][] memo) {
		// target achieved
		if (target == 0) {
			return true;
		}
		// first element
		if (i == 0) {
			return arr[0] == target;
		}
		if (memo[i][target] != -1) {
			return memo[i][target] == 1;
		}
		boolean notTake = dfs1(i - 1, target, arr, memo);
		boolean take = false;
		if (target >= arr[i]) {
			take = dfs1(i - 1, target - arr[i], arr, memo);
		}
		memo[i][target] = (take || notTake) ? 1 : 0;

		return take || notTake;
	}

	// Tabulation (Bottom-Up)
	public boolean subsetSumToK2(int[] arr, int k) {

		int n = arr.length;

		boolean[][] dp = new boolean[n][k + 1];
		// target = 0 always possible
		for (int i = 0; i < n; i++) {
			dp[i][0] = true;
		}
		if (arr[0] <= k) {
			dp[0][arr[0]] = true;
		}
		for (int i = 1; i < n; i++) {

			for (int target = 1; target <= k; target++) {
				boolean notTake = dp[i - 1][target];

				boolean take = false;

				if (arr[i] <= target) {
					take = dp[i - 1][target - arr[i]];
				}
				dp[i][target] = take || notTake;
			}
		}
		 return dp[n - 1][k];
	}
	//Space Optimization
	public boolean subsetSumToK3(int[] arr, int k) {

	    int n = arr.length;

	    boolean[] prev =
	        new boolean[k + 1];

	    prev[0] = true;

	    if (arr[0] <= k) {
	        prev[arr[0]] = true;
	    }

	    for (int i = 1; i < n; i++) {

	        boolean[] curr =
	            new boolean[k + 1];

	        curr[0] = true;

	        for (int target = 1;
	             target <= k;
	             target++) {

	            boolean notTake =
	                prev[target];

	            boolean take = false;

	            if (arr[i] <= target) {
	                take =
	                    prev[target - arr[i]];
	            }

	            curr[target] =
	                take || notTake;
	        }

	        prev = curr;
	    }

	    return prev[k];
	}


}
