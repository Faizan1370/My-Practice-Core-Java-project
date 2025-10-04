package com.dsa.graph.bellman.algo;

import java.util.HashMap;
import java.util.Map;

public class BellOptimizaeStartFromAnyNode {
	public static int isNegativeCycle(int n, int[][] edges, int source) {
		// Using Map to handle arbitrary node numbering
		Map<Integer, Integer> dist = new HashMap<>();
		
		// Initialize all nodes with "infinite" distance
		for (int[] edge : edges) {
			dist.putIfAbsent(edge[0], Integer.MAX_VALUE);
			dist.putIfAbsent(edge[1], Integer.MAX_VALUE);
		}
		dist.put(source, 0);
		
		// Relax edges (n - 1) times
		for (int count = 1; count < n; count++) {
			for (int[] edge : edges) {
				int u = edge[0], v = edge[1], w = edge[2];
				if (dist.get(u) != Integer.MAX_VALUE && dist.get(u) + w < dist.get(v)) {
					dist.put(v, dist.get(u) + w);
				}
			}
		}
		
		// Check for negative cycle
		for (int[] edge : edges) {
			int u = edge[0], v = edge[1], w = edge[2];
			if (dist.get(u) != Integer.MAX_VALUE && dist.get(u) + w < dist.get(v)) {
				return 1; // negative cycle found
			}
		}
		return 0;
	}

}
