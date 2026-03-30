package com.dsa.takuforward.graph;

public class DisjointInsideCount {
	 int[] parent, rank;
	    int count;

	   public DisjointInsideCount(char[][] grid) {
	        int m = grid.length, n = grid[0].length;

	        parent = new int[m * n];
	        rank = new int[m * n];
	        count = 0;

	        for (int i = 0; i < m; i++) {
	            for (int j = 0; j < n; j++) {
	                int id = i * n + j;

	                if (grid[i][j] == 'L') {
	                    parent[id] = id;
	                    count++;
	                } else {
	                    parent[id] = -1;
	                }
	            }
	        }
	     
	   }
	   public int find(int x) {
           if (parent[x] != x) {
               parent[x] = find(parent[x]);
           }
           return parent[x];
       }
	  public void union(int x, int y) {
	        int px = find(x);
	        int py = find(y);

	        if (px == py) return;

	        if (rank[px] < rank[py]) parent[px] = py;
	        else if (rank[px] > rank[py]) parent[py] = px;
	        else {
	            parent[py] = px;
	            rank[px]++;
	        }

	        count--; // 🔥 handled inside
	    }
	  int getCount() {
	        return count;
	    }
}
