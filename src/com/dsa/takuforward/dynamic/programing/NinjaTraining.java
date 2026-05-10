package com.dsa.takuforward.dynamic.programing;

import java.util.Arrays;

public class NinjaTraining {
	// Recursion (Brute)
	public int ninjaTraining(int n, int[][] points) {
		return dfs(n - 1, 3, points);
	}

	private int dfs(int day, int last, int[][] points) {
		if (day == 0) {
			int max = 0;
			for (int task = 0; task < 3; task++) {
				if (task != last) {
					max = Math.max(max, points[0][task]);
				}

			}
			return max;
		}
		int max = 0;
		for (int task = 0; task < 3; task++) {
			if (task != last) {
				int val = points[day][task] + dfs(day - 1, last, points);
				max = Math.max(max, val);
			}
		}
		return max;
	}

	// Memoization (Top-Down)
	public int ninjaTraining1(int n, int[][] points) {
		int[][] memo = new int[n][4];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return dfs1(n - 1, 3, points, memo);

	}

	private int dfs1(int day, int last, int[][] points, int[][] memo) {
		if (day == 0) {
			int max = 0;
			for (int task = 0; task < 3; task++) {
				if (task != last) {
					max = Math.max(max, points[0][task]);
				}
			}
			return max;
		}
		if (memo[day][last] != -1) {
			return memo[day][last];
		}
		int max = 0;

		for (int task = 0; task < 3; task++) {
			if (task != last) {
				int val = points[day][task] + dfs1(day - 1, task, points, memo);
				max = Math.max(max, val);
			}
		}

		return memo[day][last] = max;
	}

	// Tabulation (Bottom-Up)
	public int ninjaTraining2(int n, int[][] points) {
		int[][] dp = new int[n][4];

		// Base case (day 0)
		dp[0][0] = Math.max(points[0][1], points[0][2]);
		dp[0][1] = Math.max(points[0][0], points[0][2]);
		dp[0][2] = Math.max(points[0][0], points[0][1]);
		dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));
		for (int day = 1; day < n; day++) {
			for (int last = 0; last < 4; last++) {
				dp[day][last] = 0;

				for (int task = 0; task < 3; task++) {
					if (task != last) {
						int val = points[day][task] + dp[day - 1][task];
						dp[day][last] = Math.max(dp[day][last], val);
					}
				}
			}

		}
		return dp[n - 1][3];
	}
	//Space Optimized (Best)
	public int ninjaTraining3(int n, int[][] points) {
	    int[] prev = new int[4];

	    // Base case
	    prev[0] = Math.max(points[0][1], points[0][2]);
	    prev[1] = Math.max(points[0][0], points[0][2]);
	    prev[2] = Math.max(points[0][0], points[0][1]);
	    prev[3] = Math.max(points[0][0],
	                Math.max(points[0][1], points[0][2]));
	    
	    for (int day = 1; day < n; day++) {
	        int[] curr = new int[4];

	        for (int last = 0; last < 4; last++) {
	            curr[last] = 0;

	            for (int task = 0; task < 3; task++) {
	                if (task != last) {
	                    int val = points[day][task] + prev[task];
	                    curr[last] = Math.max(curr[last], val);
	                }
	            }
	        }

	        prev = curr;
	    }

	    return prev[3];
	}
}
