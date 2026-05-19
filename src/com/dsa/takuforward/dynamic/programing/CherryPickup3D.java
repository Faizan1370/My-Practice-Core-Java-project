package com.dsa.takuforward.dynamic.programing;

import java.util.Arrays;

public class CherryPickup3D {
	//Recursion (Brute)
	public int cherryPickup(int[][] grid) {
		int m =grid.length;
		int n=grid[0].length;
		return dfs(0,0,n-1,grid);
	}

	private int dfs(int i, int j1, int j2, int[][] grid) {
		int m = grid.length;
	    int n = grid[0].length;
		if(j1<0 || j1>=n || j2<0 || j2>=n) {
			return Integer.MIN_VALUE;
		}
		  // last row
	    if (i == m - 1) {
	    	if(j1==j2) {
	    		return grid[i][j1];
	    	}
	    	return grid[i][j1]+grid[i][j2];
	    }
	    int maxi=Integer.MIN_VALUE;
	    for(int d1=-1;d1<=1;d1++) {
	    	  for (int d2 = -1; d2 <= 1; d2++) {
	    		  int value;
	    		  if (j1 == j2) {
	                  value = grid[i][j1];
	              } else {
	                  value = grid[i][j1] + grid[i][j2];
	              }
	    		  value +=dfs(i+1,j1+d1,j2+d2,grid);
	    		  maxi = Math.max(maxi, value);
	    	  }
	    }
		return maxi;
	}
	
	//Memoization (Top-Down)
	public int cherryPickup1(int[][] grid) {
		int m =grid.length;
		int n=grid[0].length;
		int[][][] memo = new int[m][n][n];

	    for (int[][] mat : memo) {
	        for (int[] row : mat) {
	            Arrays.fill(row, -1);
	        }
	    }
		return dfs1(0,0,n-1,grid,memo);
	}

	private int dfs1(int i, int j1, int j2, int[][] grid,int[][][] memo) {
		int m = grid.length;
	    int n = grid[0].length;
		if(j1<0 || j1>=n || j2<0 || j2>=n) {
			return Integer.MIN_VALUE;
		}
		  // last row
	    if (i == m - 1) {
	    	if(j1==j2) {
	    		return grid[i][j1];
	    	}
	    	return grid[i][j1]+grid[i][j2];
	    }
	    if (memo[i][j1][j2] != -1) {
	        return memo[i][j1][j2];
	    }
	    int maxi=Integer.MIN_VALUE;
	    for(int d1=-1;d1<=1;d1++) {
	    	  for (int d2 = -1; d2 <= 1; d2++) {
	    		  int value;
	    		  if (j1 == j2) {
	                  value = grid[i][j1];
	              } else {
	                  value = grid[i][j1] + grid[i][j2];
	              }
	    		  value +=dfs1(i+1,j1+d1,j2+d2,grid,memo);
	    		  maxi = Math.max(maxi, value);
	    	  }
	    }
		return  memo[i][j1][j2] = maxi;
	}
	//Tabulation (Bottom-Up)
	public int cherryPickup2(int[][] grid) {

	    int m = grid.length;
	    int n = grid[0].length;

	    int[][][] dp = new int[m][n][n];

	    // Base case -> last row
	    for (int j1 = 0; j1 < n; j1++) {

	        for (int j2 = 0; j2 < n; j2++) {

	            if (j1 == j2) {
	                dp[m - 1][j1][j2] = grid[m - 1][j1];
	            } else {
	                dp[m - 1][j1][j2] =
	                    grid[m - 1][j1] + grid[m - 1][j2];
	            }
	        }
	    }

	    // Bottom-up
	    for (int i = m - 2; i >= 0; i--) {

	        for (int j1 = 0; j1 < n; j1++) {

	            for (int j2 = 0; j2 < n; j2++) {

	                int maxi = Integer.MIN_VALUE;

	                for (int d1 = -1; d1 <= 1; d1++) {

	                    for (int d2 = -1; d2 <= 1; d2++) {

	                        int nj1 = j1 + d1;
	                        int nj2 = j2 + d2;

	                        int value;

	                        // current row cherries
	                        if (j1 == j2) {
	                            value = grid[i][j1];
	                        } else {
	                            value =
	                                grid[i][j1] + grid[i][j2];
	                        }

	                        // boundary check
	                        if (nj1 >= 0 && nj1 < n &&
	                            nj2 >= 0 && nj2 < n) {

	                            value += dp[i + 1][nj1][nj2];

	                        } else {

	                            value = Integer.MIN_VALUE;
	                        }

	                        maxi = Math.max(maxi, value);
	                    }
	                }

	                dp[i][j1][j2] = maxi;
	            }
	        }
	    }

	    return dp[0][0][n - 1];
	}
	//Space Optimized Code
	public int cherryPickup3(int[][] grid) {

	    int m = grid.length;
	    int n = grid[0].length;

	    int[][] front = new int[n][n];

	    // Base case -> last row
	    for (int j1 = 0; j1 < n; j1++) {

	        for (int j2 = 0; j2 < n; j2++) {

	            if (j1 == j2) {
	                front[j1][j2] = grid[m - 1][j1];
	            } else {
	                front[j1][j2] =
	                    grid[m - 1][j1] + grid[m - 1][j2];
	            }
	        }
	    }

	    // Bottom-up
	    for (int i = m - 2; i >= 0; i--) {

	        int[][] curr = new int[n][n];

	        for (int j1 = 0; j1 < n; j1++) {

	            for (int j2 = 0; j2 < n; j2++) {

	                int maxi = Integer.MIN_VALUE;

	                for (int d1 = -1; d1 <= 1; d1++) {

	                    for (int d2 = -1; d2 <= 1; d2++) {

	                        int nj1 = j1 + d1;
	                        int nj2 = j2 + d2;

	                        int value;

	                        // current cherries
	                        if (j1 == j2) {
	                            value = grid[i][j1];
	                        } else {
	                            value =
	                                grid[i][j1] + grid[i][j2];
	                        }

	                        // valid move
	                        if (nj1 >= 0 && nj1 < n &&
	                            nj2 >= 0 && nj2 < n) {

	                            value += front[nj1][nj2];

	                        } else {

	                            value = Integer.MIN_VALUE;
	                        }

	                        maxi = Math.max(maxi, value);
	                    }
	                }

	                curr[j1][j2] = maxi;
	            }
	        }

	        front = curr;
	    }

	    return front[0][n - 1];
	}
	

}
