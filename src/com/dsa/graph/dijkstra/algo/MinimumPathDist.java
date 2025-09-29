package com.dsa.graph.dijkstra.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import com.dsa.graph.prims.algo.VertexWeightPair;

public class MinimumPathDist {
	
	ArrayList<ArrayList<VertexDistPair>> adj;
	int V;
	
	public MinimumPathDist(int v) {
		V=v;
		adj = new ArrayList<>();
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<>());
		}
		
	}
	public void addEdge(int u, int v, int dist) {
		adj.get(u).add(new VertexDistPair(v, dist));
		adj.get(v).add(new VertexDistPair(u, dist));
	}
	
	public int[] dijsktra(int src) {
		int[] dist= new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[src]=0;
		PriorityQueue<VertexDistPair> queue = new PriorityQueue<VertexDistPair>();
		queue.add(new VertexDistPair(src, 0));
		while(!queue.isEmpty()) {
			VertexDistPair curr = queue.poll();
			int vertex=curr.vertex;
			int d= curr.dist;
			if(d>dist[vertex]) {
				continue;
			}
			for(VertexDistPair neg:adj.get(vertex)) {
				int v = neg.vertex,dis=neg.dist;
				if(dist[vertex]+dis <dist[v]) {
					dist[v]=dist[vertex]+dis;
					queue.add(new VertexDistPair(v, dist[v]));
				}
			}
		}
		return dist;
	}
	
	 public static void main(String[] args) {
	        MinimumPathDist g = new MinimumPathDist(5);
	        g.addEdge(0, 1, 2);
	        g.addEdge(0, 2, 4);
	        g.addEdge(1, 2, 1);
	        g.addEdge(1, 3, 7);
	        g.addEdge(2, 4, 3);
	        g.addEdge(3, 4, 1);

	        int src = 0;
	        int[] dist = g.dijsktra(src);

	        System.out.println("Shortest distances from source " + src + ":");
	        System.out.println(Arrays.toString(dist));
	    }
	

}
