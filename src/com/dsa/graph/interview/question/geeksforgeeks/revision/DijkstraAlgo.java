package com.dsa.graph.interview.question.geeksforgeeks.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class DijkstraAlgo {
	static ArrayList<Integer> dijkstra(ArrayList<ArrayList<int[]>> adj, int src) {
		int V=adj.size();
		PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->a[0]-a[1]);
		queue.add(new int[] {0,src});
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] visted= new boolean[V];
		dist[src]=0;
		
		while(!queue.isEmpty()) {
			int[] pair = queue.poll();
			int d=pair[0];
			int u=pair[1];
			if(visted[u]) {
				continue;
			}
			visted[u]=true;
			for(int[] p:adj.get(u)) {
				int v=p[0];
				int w=p[1];
				if(dist[u]+w<dist[v]) {
					dist[v]=dist[u]+w;
					queue.add(new int[] {dist[v],v});
				}
			}
		}
		 ArrayList<Integer> result = new ArrayList<>();
	        for (int d : dist)
	            result.add(d);
		return result;
		
	}
	
	public static void main(String[] args) {
		  int V = 4;
	        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();

	        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

	        // ---- Provide Input Here ----
	        // Graph:
	        // 0 -> (1,4), (2,1)
	        // 2 -> (1,2), (3,5)
	        // 1 -> (3,1)

	        adj.get(0).add(new int[]{1, 4});
	        adj.get(0).add(new int[]{2, 1});
	        adj.get(2).add(new int[]{1, 2});
	        adj.get(1).add(new int[]{3, 1});
	        adj.get(2).add(new int[]{3, 5});

	        // Run Dijkstra from source = 0
	        System.out.println(dijkstra(adj, 0));
	}

}
