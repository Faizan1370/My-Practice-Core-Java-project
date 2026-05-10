package com.dsa.takuforward.dynamic.programing;

import java.util.Arrays;

public class UniquePaths {
	//Recursion (Brute)
	public int uniquePaths(int m, int n) {
		return dfs(m-1,n-1);
	}

	private int dfs(int i, int j) {
		if(i==0 || j==0) {
			return 1;
		}
		if(i<0 || j<0) {
			return 0;
		}
		int up =dfs(i-1,j);
		int left = dfs(i,j-1);
		return up+left;
	}
	//Memoization (Top-Down)
	public int uniquePaths1(int m, int n) {
		 int[][] memo = new int[m][n];

		    for (int[] row : memo) {
		        Arrays.fill(row, -1);
		    }
		return dfs1(m-1,n-1,memo);
	}

	private int dfs1(int i, int j, int[][] memo) {
		if(i==0 || j==0) {
			return 1;
		}
		if(memo[i][j] !=-1) {
			return memo[i][j];
			}
		if(i<0 || j<0) {
			return 0;
		}
		int up =dfs1(i-1,j,memo);
		int left = dfs1(i,j-1,memo);
		return memo[i][j]= up+left;
	}
	//Tabulation (Bottom-Up)
	public int uniquePaths2(int m, int n) {
		int[][] dp = new int[m][n];
		 dp[0][0] = 1;
		 
		 for(int i=0;i<m;i++) {
			 for(int j=0;j<n;j++) {
				 if (i == 0 && j == 0) continue;
				  int up=0;
				  int left=0;
				  if(i>0) {
					  up = dp[i-1][j];
				  }
				  if(j>0) {
					  left = dp[i][j-1];
				  }
				  dp[i][j] = up + left;
			 }
		 }
		return dp[m-1][n-1];
	}
	//Space Optimized (Best)
	public int uniquePaths3(int m, int n) {
	    int[] prev = new int[n];

	    for (int i = 0; i < m; i++) {
	        int[] curr = new int[n];

	        for (int j = 0; j < n; j++) {

	            if (i == 0 && j == 0) {
	                curr[j] = 1;
	                continue;
	            }

	            int up = 0;
	            int left = 0;

	            if (i > 0) up = prev[j];

	            if (j > 0) left = curr[j - 1];

	            curr[j] = up + left;
	        }

	        prev = curr;
	    }

	    return prev[n - 1];
	}
}
