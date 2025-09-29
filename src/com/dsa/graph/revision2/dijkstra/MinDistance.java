package com.dsa.graph.revision2.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MinDistance {
	public static int[] dijkstra(int V,int[][] edges,int src) {
		ArrayList<ArrayList<VertexD>> adj = new ArrayList<ArrayList<VertexD>>();
		
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<VertexD>());
		}
		for(int[] e:edges) {
			int u=e[0],v=e[1],d=e[2];
			adj.get(u).add(new VertexD(v, d));
			adj.get(v).add(new VertexD(u, d));
		}
		int[] dist = new int[V];
		boolean[] visted= new boolean[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<VertexD> queue = new PriorityQueue<VertexD>();
		dist[src]=0;
		
		queue.add( new VertexD(src, 0));
		while(!queue.isEmpty()) {
			VertexD pair = queue.remove();
			int ver=pair.vertex;
			//int dis=pair.distance;
			if(visted[ver]) {
				continue;
			}
			visted[src]=true;
			/*
			 * if(dis>dist[ver]) { continue; } // first
			 */
			for(VertexD neg:adj.get(ver)) {
				int vert=neg.vertex;
				int dista=neg.distance;
				if(dist[ver]+dista<dist[vert]) {
					dist[vert]=dist[ver]+dista;
					queue.add(new VertexD(vert, dist[vert]));
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

		int[] dist = dijkstra(V, edges, 0);
		System.out.println(Arrays.toString(dist));

	}

}
