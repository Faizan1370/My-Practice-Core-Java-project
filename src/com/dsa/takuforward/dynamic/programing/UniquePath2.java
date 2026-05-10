package com.dsa.takuforward.dynamic.programing;

import java.util.Arrays;

public class UniquePath2 {
     
	//Recursion (Brute)
	public int uniquePathsWithObstacles(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		return dfsUniquePath2(m - 1, n - 1, grid);
	}

	private int dfsUniquePath2(int i, int j, int[][] grid) {
		if (i >= 0 && j >= 0 && grid[i][j] == -1) {
			return 0;
		}
		if (i == 0 && j == 0) {
			return 1;
		}

		if (i < 0 || j < 0) {
			return 0;
		}
		int up=dfsUniquePath2(i-1, j, grid);
		int left = dfsUniquePath2(i, j-1, grid);
		return left+up;
	}
	//Memoization (Top-Down)
	public int uniquePathsWithObstacles1(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int[][] memo = new int[m][n];
		for(int[] arr:memo) {
			Arrays.fill(arr, -1);
		}

		return dfsUniquePath21(m - 1, n - 1, grid,memo);
	}

	private int dfsUniquePath21(int i, int j, int[][] grid,int[][] memo) {
		if (i >= 0 && j >= 0 && grid[i][j] == -1) {
			return 0;
		}
		if (i == 0 && j == 0) {
			return 1;
		}

		if (i < 0 || j < 0) {
			return 0;
		}
		if(memo[i][j]!=-1) {
			return memo[i][j];
		}
		int up=dfsUniquePath2(i-1, j, grid);
		int left = dfsUniquePath2(i, j-1, grid);
		return memo[i][j] = left+up;
	}
	//Tabulation (Bottom-Up)
	public int uniquePathsWithObstacles2(int[][] grid) {
		int m=grid.length;
		int n=grid[0].length;
		
		int[][] dp = new int[m][n];
		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(grid[i][j]==-1) {
					dp[i][j]=0;
					continue;
				}
				if(i==0 && j==0) {
					dp[i][j]=1;
					continue;
				}
				  int up = 0;
		          int left = 0;
		          if(i>0) {
		        	  up =dp[i-1][j];
		          }
		          if(j>0) {
		        	  left=dp[i][j-1];
		          }
		          dp[i][j] = up + left;
			}
		}
		return dp[m-1][n-1];
	}
	//Space Optimized (Best)
	public int uniquePathsWithObstacles3(int[][] grid) {

	    int m = grid.length;
	    int n = grid[0].length;

	    int[] prev = new int[n];

	    for (int i = 0; i < m; i++) {

	        int[] curr = new int[n];

	        for (int j = 0; j < n; j++) {

	            if (grid[i][j] == 1) {
	                curr[j] = 0;
	                continue;
	            }

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
