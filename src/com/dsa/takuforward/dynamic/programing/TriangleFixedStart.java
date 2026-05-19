package com.dsa.takuforward.dynamic.programing;

import java.util.Arrays;
import java.util.List;

public class TriangleFixedStart {
	// Recursion (Brute)
	public int minimumTotal(List<List<Integer>> triangle) {
		return dfs(0, 0, triangle);
	}

	private int dfs(int i, int j, List<List<Integer>> triangle) {
		int n = triangle.size();
		if (i == n - 1) {
			triangle.get(i).get(j);
		}
		int down = dfs(i + 1, j, triangle);
		int diagonal = dfs(i + 1, j + 1, triangle);

		return triangle.get(i).get(j) + Math.min(down, diagonal);
	}

	// Memoization (Top-Down)
	public int minimumTotal1(List<List<Integer>> triangle) {
		int n = triangle.size();
		int[][] memo = new int[n][n];
		for (int[] me : memo) {
			Arrays.fill(me, -1);
		}
		return dfs1(0, 0, triangle, memo);
	}

	private int dfs1(int i, int j, List<List<Integer>> triangle, int[][] memo) {
		int n = triangle.size();
		if (i == n - 1) {
			triangle.get(i).get(j);
		}
		if (memo[i][j] != -1) {
			return memo[i][j];
		}
		int down = dfs1(i + 1, j, triangle, memo);
		int diagonal = dfs1(i + 1, j + 1, triangle, memo);

		return memo[i][j] = triangle.get(i).get(j) + Math.min(down, diagonal);
	}

	// Tabulation (Bottom-Up
	public int minimumTotal2(List<List<Integer>> triangle) {

		int n = triangle.size();
		int dp[][] = new int[n][n];

		for (int j = 0; j < n; j++) {
			dp[n - 1][j] = triangle.get(n - 1).get(j);
		}
		// bottom-up
		for (int i = n - 2; i >= 0; i--) {

			for (int j = 0; j <= i; j++) {
				int down = dp[i + 1][j];
				int diagonal = dp[i + 1][j + 1];
				dp[i][j] = triangle.get(i).get(j) + Math.min(down, diagonal);
			}
		}
		return dp[0][0];
	}
     //Space Optimized (Best)
	public int minimumTotal4(List<List<Integer>> triangle) {

	    int n = triangle.size();

	    int[] front = new int[n];

	    // last row
	    for (int j = 0; j < n; j++) {
	        front[j] = triangle.get(n - 1).get(j);
	    }

	    // bottom-up
	    for (int i = n - 2; i >= 0; i--) {

	        int[] curr = new int[n];

	        for (int j = 0; j <= i; j++) {

	            int down = front[j];

	            int diagonal = front[j + 1];

	            curr[j] =
	                triangle.get(i).get(j)
	                + Math.min(down, diagonal);
	        }

	        front = curr;
	    }

	    return front[0];
	}
}
