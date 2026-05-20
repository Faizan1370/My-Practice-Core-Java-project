package com.dsa.takuforward.dynamic.programing;

import java.util.Arrays;

public class CanPartition {
	// Recursion (Brute)
	public boolean canPartition(int[] nums) {
		int sum = 0;
		for (int x : nums) {
			sum += x;
		}
		if (sum % 2 != 0)
			return false;
		int target = sum / 2;

		return dfs(nums.length - 1, target, nums);
	}

	private boolean dfs(int i, int target, int[] nums) {
		if (target == 0)
			return true;
		if (i == 0)
			return nums[0] == target;
		boolean notTake = dfs(i - 1, target, nums);
		boolean take = false;
		if (nums[i] <= target) {
			take = dfs(i - 1, target - nums[i], nums);
		}

		return take || notTake;
	}
	// Memoization (Top-Down)

	public boolean canPartition1(int[] nums) {

		int sum = 0;
		for (int x : nums)
			sum += x;

		if (sum % 2 != 0)
			return false;

		int target = sum / 2;

		int n = nums.length;

		int[][] memo = new int[n][target + 1];

		for (int[] row : memo)
			Arrays.fill(row, -1);

		return dfs(n - 1, target, nums, memo);
	}

	private boolean dfs(int i, int target, int[] nums, int[][] memo) {

		if (target == 0)
			return true;

		if (i == 0)
			return nums[0] == target;

		if (memo[i][target] != -1)
			return memo[i][target] == 1;

		boolean notTake = dfs(i - 1, target, nums, memo);

		boolean take = false;

		if (nums[i] <= target) {
			take = dfs(i - 1, target - nums[i], nums, memo);
		}

		memo[i][target] = (take || notTake) ? 1 : 0;

		return take || notTake;
	}
	//Tabulation (Bottom-Up)
	public boolean canPartition2(int[] nums) {

	    int sum = 0;
	    for (int x : nums) sum += x;

	    if (sum % 2 != 0) return false;

	    int target = sum / 2;
	    int n = nums.length;

	    boolean[][] dp =
	        new boolean[n][target + 1];

	    // base case
	    for (int i = 0; i < n; i++) {
	        dp[i][0] = true;
	    }

	    if (nums[0] <= target) {
	        dp[0][nums[0]] = true;
	    }

	    for (int i = 1; i < n; i++) {

	        for (int t = 1; t <= target; t++) {

	            boolean notTake = dp[i - 1][t];

	            boolean take = false;

	            if (nums[i] <= t) {
	                take = dp[i - 1][t - nums[i]];
	            }

	            dp[i][t] = take || notTake;
	        }
	    }

	    return dp[n - 1][target];
	}
	//Space Optimization (Best)
	public boolean canPartition3(int[] nums) {

	    int sum = 0;
	    for (int x : nums) sum += x;

	    if (sum % 2 != 0) return false;

	    int target = sum / 2;
	    int n = nums.length;

	    boolean[] prev =
	        new boolean[target + 1];

	    prev[0] = true;

	    if (nums[0] <= target) {
	        prev[nums[0]] = true;
	    }

	    for (int i = 1; i < n; i++) {

	        boolean[] curr =
	            new boolean[target + 1];

	        curr[0] = true;

	        for (int t = 1; t <= target; t++) {

	            boolean notTake = prev[t];

	            boolean take = false;

	            if (nums[i] <= t) {
	                take = prev[t - nums[i]];
	            }

	            curr[t] = take || notTake;
	        }

	        prev = curr;
	    }

	    return prev[target];
	}

}
