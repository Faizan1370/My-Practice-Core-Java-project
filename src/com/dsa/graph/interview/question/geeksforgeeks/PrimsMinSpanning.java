package com.dsa.graph.interview.question.geeksforgeeks;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PrimsMinSpanning {
	
	public int minimumSpanning(int V,int[][] edges) {
		ArrayList<ArrayList<VWPair>> adj= new ArrayList<ArrayList<VWPair>>();
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<VWPair>());
		}
		 for (int[] edge : edges) {
	            int src = edge[0], dest = edge[1], w = edge[2];
	            adj.get(src).add(new VWPair(dest, w));
	            adj.get(dest).add(new VWPair(src, w));
	        }

		boolean[] visited = new boolean[V];
		PriorityQueue<VWPair> queue = new PriorityQueue<VWPair>();
		queue.add(new VWPair(0, 0));
		int mst=0;
		while(!queue.isEmpty()) {
			VWPair pair = queue.poll();
			int v=pair.vertex;
			int w=pair.weight;
			if(visited[v]) {
				continue;
			}
			visited[v]=true;
			mst +=w;
			for(VWPair neg:adj.get(v)) {
				if(!visited[neg.vertex]) {
					queue.add(new VWPair(neg.vertex, neg.weight));
				}
			}
		}
		return mst;
	}
	
	public static void main(String[] args) {
		PrimsMinSpanning minSpanning = new PrimsMinSpanning();
		  int V = 3;
	        int[][] edges = {
	            {0, 1, 5},
	            {1, 2, 3},
	            {0, 2, 1}
	        };

	        PrimsMinSpanning prim = new PrimsMinSpanning();
	        System.out.println("MST Weight: " + minSpanning.minimumSpanning(V, edges)); // Expected output: 4

	}
	

}
