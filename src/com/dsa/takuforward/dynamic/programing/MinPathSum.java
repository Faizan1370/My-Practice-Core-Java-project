package com.dsa.takuforward.dynamic.programing;

import java.util.Arrays;

public class MinPathSum {

	// Recursion (Brute)
	public int minPathSum(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		return dfs(m - 1, n - 1, grid);
	}

	private int dfs(int i, int j, int[][] grid) {

		if (i == 0 && j == 0) {
			return grid[0][0];
		}

		if (i < 0 || j < 0) {
			return Integer.MAX_VALUE;
		}

		int up = dfs(i - 1, j, grid);
		int left = dfs(i, j - 1, grid);

		int minPrev = Math.min(up, left);

		// overflow protection
		if (minPrev == Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}

		return grid[i][j] + minPrev;
	}

	// Memoization (Top-Down)
	public int minPathSum1(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		int[][] memo = new int[m][n];
		for (int[] arr : memo) {
			Arrays.fill(arr, -1);
		}

		return dfs1(m - 1, n - 1, grid, memo);
	}

	private int dfs1(int i, int j, int[][] grid, int[][] memo) {

		if (i == 0 && j == 0) {
			return grid[0][0];
		}

		if (i < 0 || j < 0) {
			return Integer.MAX_VALUE;
		}

		if (memo[i][j] != -1) {
			return memo[i][j];
		}

		int up = dfs1(i - 1, j, grid, memo);
		int left = dfs1(i, j - 1, grid, memo);

		int minPrev = Math.min(up, left);

		if (minPrev == Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}

		return memo[i][j] = grid[i][j] + minPrev;
	}

	// Tabulation (Bottom-Up)
	public int minPathSum2(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		int[][] dp = new int[m][n];

		dp[0][0] = grid[0][0];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int up = Integer.MAX_VALUE;
				int left = Integer.MAX_VALUE;

				if (i > 0) {
					up = dp[i - 1][j];
				}

				if (j > 0) {
					left = dp[i][j - 1];
				}
				int minPrev = Math.min(up, left);

				if (minPrev != Integer.MAX_VALUE) {
					dp[i][j] = grid[i][j] + minPrev;
				}
			}
		}
		return dp[m - 1][n - 1];
	}
	////Space Optimized (Best)
	public int minPathSum3(int[][] grid) {

	    int m = grid.length;
	    int n = grid[0].length;

	    int[] prev = new int[n];

	    for (int i = 0; i < m; i++) {

	        int[] curr = new int[n];

	        for (int j = 0; j < n; j++) {

	            if (i == 0 && j == 0) {
	                curr[j] = grid[0][0];
	                continue;
	            }

	            int up = Integer.MAX_VALUE;
	            int left = Integer.MAX_VALUE;

	            if (i > 0) {
	                up = prev[j];
	            }

	            if (j > 0) {
	                left = curr[j - 1];
	            }

	            int minPrev = Math.min(up, left);

	            if (minPrev != Integer.MAX_VALUE) {
	                curr[j] = grid[i][j] + minPrev;
	            }
	        }

	        prev = curr;
	    }

	    return prev[n - 1];
	}
}


