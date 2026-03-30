package com.dsa.takuforward.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class GraphImpe {

	public int numberOfIsland(int[][] grid) {
		int m = grid.length, n = grid[0].length;

		DisjointSetDS disjointSetDS = new DisjointSetDS(m * n);
		int count = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 'L') {
					count++;
				}
			}
		}
		int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
		int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 'L') {
					for (int k = 0; k < 8; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						if (nr >= 0 && nc >= 0 && nr < m && nc < n && grid[nr][nc] == 'L') {
							if (disjointSetDS.unionWithOutSideCount(i * n + j, nr * n + nc)) {
								count--;
							}
						}
					}

				}
			}
		}
		return count;
	}

	public int numIslands(char[][] grid) {
		int m = grid.length, n = grid[0].length;

		DisjointInsideCount dsu = new DisjointInsideCount(grid);
		int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
		int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {

				if (grid[i][j] == 'L') {

					for (int k = 0; k < 8; k++) {
						int ni = i + dr[k];
						int nj = j + dc[k];

						if (ni >= 0 && nj >= 0 && ni < m && nj < n && grid[ni][nj] == 'L') {

							dsu.union(i * n + j, ni * n + nj);
						}
					}
				}
			}
		}

		return dsu.getCount();
	}

	public int numIslandsUsingSet(char[][] grid) {
		int m = grid.length, n = grid[0].length;

		DisjointSetDS dsu = new DisjointSetDS(m * n);
		int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
		int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {

				if (grid[i][j] == 'L') {

					for (int k = 0; k < 8; k++) {
						int ni = i + dr[k];
						int nj = j + dc[k];

						if (ni >= 0 && nj >= 0 && ni < m && nj < n && grid[ni][nj] == 'L') {

							dsu.unionWithOutSideCount(i * n + j, ni * n + nj);
						}
					}
				}
			}
		}

		HashSet<Integer> set = new HashSet<>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 'L') {
					set.add(dsu.find(i * n + j));
				}
			}
		}

		return set.size();
	}

	int time = 0;

	public List<List<Integer>> findBridges(int n, List<List<Integer>> connections) {
		List<List<Integer>> adj = new ArrayList<List<Integer>>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (List<Integer> edge : connections) {
			adj.get(edge.get(0)).add(edge.get(1));
			adj.get(edge.get(1)).add(edge.get(0));
		}
		boolean[] visited = new boolean[n];
		int[] disc = new int[n]; // time of insertion
		int[] low = new int[n]; // lowest time of insertion
		List<List<Integer>> bridges = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfsBridge(i, -1, adj, visited, disc, low, bridges);
			}
		}
		return bridges;

	}

	private void dfsBridge(int u, int parent, List<List<Integer>> adj, boolean[] visited, int[] disc, int[] low,
			List<List<Integer>> bridges) {
		visited[u] = true;
		disc[u] = low[u] = time++;

		for (int v : adj.get(u)) {
			if (v == parent) {
				continue;
			}
			if (!visited[v]) {
				dfsBridge(v, u, adj, visited, disc, low, bridges);
				low[u] = Math.min(low[u], low[v]);
				if (low[v] > disc[u]) {
					bridges.add(Arrays.asList(u, v));
				}
			} else {
				// back edge
				low[u] = Math.min(low[u], disc[v]);
			}
		}

	}

	// find strongly connected components
	public int kosaraju(int V, List<List<Integer>> adj) {
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[V];
		// Step 1: fill stack
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				dfs1(i, adj, visited, stack);
			}
		}
		 // Step 2: reverse graph
        List<List<Integer>> revAdj = new ArrayList<>();
        for (int i = 0; i < V; i++) revAdj.add(new ArrayList<>());

        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                revAdj.get(v).add(u);
            }
        }
        // Step 3: DFS on reversed graph
        Arrays.fill(visited, false);
        int count = 0;
        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (!visited[node]) {
                dfs2(node, revAdj, visited);
                count++; // 🔥 one SCC found
            }
        }
		return count;

	}

	private void dfs2(int u, List<List<Integer>> revAdj, boolean[] visited) {
		visited[u] = true;
		for (int v : revAdj.get(u)) {
			if (!visited[v]) {
				dfs2(v, revAdj, visited);
			}
		}
		
	}

	private void dfs1(int u, List<List<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
		visited[u] = true;
		for (int v : adj.get(u)) {
			if (!visited[v]) {
				dfs1(v, adj, visited, stack);
			}
		}
		stack.add(u);

	}

}
