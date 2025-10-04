package com.dsa.graph3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class ShotestPath {
	
	public static int[] dijktra(int V,int[][] edges,int src) {
		ArrayList<ArrayList<VertexWPair>> adj = new ArrayList<ArrayList<VertexWPair>>();
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<VertexWPair>());
		}
		for(int[] e:edges) {
			int u=e[0];
			int v=e[1];
			int w=e[2];
			adj.get(u).add(new VertexWPair(v, w));
			adj.get(v).add(new VertexWPair(u, w));
		}
		boolean[] visited = new boolean[V];
		int[] dist= new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<VertexWPair> queue = new PriorityQueue<VertexWPair>();
		queue.add(new VertexWPair(src, 0));
		
		while(!queue.isEmpty()) {
			VertexWPair pair = queue.poll();
			int vertex = pair.v;
			if(visited[vertex]) {
				continue;
			}
			dist[src]=0;
			visited[vertex] =true;
			
			
			for(VertexWPair neg:adj.get(vertex)) {
				if(!visited[neg.v]) {
					if(dist[neg.v]>dist[vertex]+neg.w) {
						dist[neg.v]=dist[vertex]+neg.w;
						queue.add(new VertexWPair(neg.v, neg.w));
					}
				
				 
				}
				
			}
		}
		return dist;
	}
	
	public static void main(String[] args) {
		int V = 5;
		int[][] edges = {
		    {0,1,2}, {0,2,4},
		    {1,2,1}, {1,3,7},
		    {2,4,3}, {3,4,1}
		};

		int[] dist = dijktra(V, edges, 0);
		System.out.println(Arrays.toString(dist));
	}

}
