package com.dsa.takuforward.dynamic.programing;

import java.util.Arrays;

public class FailingPathSum {
	// Recursion (Brute)
	public int maxFallingPathSum(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		int maxi = Integer.MIN_VALUE;

		for (int j = 0; j < n; j++) {
			maxi = Math.max(maxi, dfs(m - 1, j, matrix));
		}
		return maxi;

	}

	private int dfs(int i, int j, int[][] matrix) {
		int n = matrix[0].length;
		if (j < 0 || j >= n) {
			return Integer.MIN_VALUE;
		}
		// first row
		if (i == 0) {
			return matrix[0][j];
		}
		int up = dfs(i - 1, j, matrix);
		int lg = dfs(i - 1, j - 1, matrix);
		int rg = dfs(i - 1, j + 1, matrix);
		int bestPrev = Math.max(up, Math.max(lg, rg));
		if (bestPrev == Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}
		return matrix[i][j] + bestPrev;
	}

//Memoization (Top-Down)
	public int maxFallingPathSum1(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		int maxi = Integer.MIN_VALUE;
		int[][] memo = new int[m][n];

		for (int[] row : memo) {
			Arrays.fill(row, Integer.MIN_VALUE);
		}

		for (int j = 0; j < n; j++) {
			maxi = Math.max(maxi, dfs1(m - 1, j, matrix, memo));
		}
		return maxi;

	}

	private int dfs1(int i, int j, int[][] matrix, int[][] memo) {
		int n = matrix[0].length;
		if (j < 0 || j >= n) {
			return Integer.MIN_VALUE;
		}
		// first row
		if (i == 0) {
			return matrix[0][j];
		}
		if (memo[i][j] != Integer.MIN_VALUE) {
			return memo[i][j];
		}
		int up = dfs1(i - 1, j, matrix, memo);
		int lg = dfs1(i - 1, j - 1, matrix, memo);
		int rg = dfs1(i - 1, j + 1, matrix, memo);
		int bestPrev = Math.max(up, Math.max(lg, rg));
		if (bestPrev == Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}
		return memo[i][j] = (matrix[i][j] + bestPrev);
	}

	// Tabulation (Bottom-Up)
	public int maxFallingPathSum2(int[][] matrix) {

		int m = matrix.length;
		int n = matrix[0].length;

		int[][] dp = new int[m][n];
		
		for(int j=0;j<n;j++) {
			dp[0][j]=matrix[0][j];
		}
		for(int i=1;i<m;i++) {
			for(int j=0;j<n;j++) {
				int up = dp[i-1][j];
				int lg = Integer.MIN_VALUE;
				if(j>0) {
					lg=dp[i - 1][j - 1];
				}
				int rg = Integer.MIN_VALUE;
				 if (j < n - 1) {
					 rg =dp[i-1][j+1];
				 }

		            dp[i][j] =
		                matrix[i][j]
		                + Math.max(up,
		                  Math.max(lg, rg));
			}
		}
		  int maxi = Integer.MIN_VALUE;

		  for(int j=0;j<n;j++) {
			  maxi=Math.max(maxi, dp[m-1][j]);
		  }
		return maxi;
	}
	//Space Optimized (Best)
	public int maxFallingPathSum3(int[][] matrix) {

	    int m = matrix.length;
	    int n = matrix[0].length;

	    int[] prev = new int[n];

	    // first row
	    for (int j = 0; j < n; j++) {
	        prev[j] = matrix[0][j];
	    }

	    for (int i = 1; i < m; i++) {

	        int[] curr = new int[n];

	        for (int j = 0; j < n; j++) {

	            int up = prev[j];

	            int leftDiag = Integer.MIN_VALUE;
	            if (j > 0) {
	                leftDiag = prev[j - 1];
	            }

	            int rightDiag = Integer.MIN_VALUE;
	            if (j < n - 1) {
	                rightDiag = prev[j + 1];
	            }

	            curr[j] =
	                matrix[i][j]
	                + Math.max(up,
	                  Math.max(leftDiag, rightDiag));
	        }

	        prev = curr;
	    }

	    int maxi = Integer.MIN_VALUE;

	    for (int val : prev) {
	        maxi = Math.max(maxi, val);
	    }

	    return maxi;
	}
}
