package com.dsa.graph.prims.algo;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class MinimumSpanningTree {
	int V;
	ArrayList<ArrayList<VertexWeightPair>> adj;

	public MinimumSpanningTree(int v) {
		V = v;
		adj = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<>());
		}
	}

	public void addEdge(int u, int v, int weight) {
		adj.get(u).add(new VertexWeightPair(v, weight));
		adj.get(v).add(new VertexWeightPair(u, weight));
	}

	public int minimumSpanning() {
		boolean[] visited = new boolean[V];
		PriorityQueue<VertexWeightPair> queue = new PriorityQueue<VertexWeightPair>();

		queue.add(new VertexWeightPair(0, 0));
		int mstWeight = 0;

		while (!queue.isEmpty()) {
			VertexWeightPair curr = queue.poll();
			int ver = curr.vertex;
			int weight = curr.weight;
			if (visited[ver]) {
				continue;
			}
			visited[ver] = true;
			mstWeight += weight;

			for (VertexWeightPair neg : adj.get(ver)) {
				if (!visited[neg.vertex]) {
					queue.add(new VertexWeightPair(neg.vertex, neg.weight));
				}
			}
		}
		return mstWeight;
	}

	public static void main(String[] args) {
		MinimumSpanningTree g = new MinimumSpanningTree(5);
		g.addEdge(0, 1, 2);
		g.addEdge(0, 3, 6);
		g.addEdge(1, 2, 3);
		g.addEdge(1, 3, 8);
		g.addEdge(1, 4, 5);
		g.addEdge(2, 4, 7);
		System.out.println(g.minimumSpanning());
	}

}
