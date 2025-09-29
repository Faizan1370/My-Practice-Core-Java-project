package com.dsa.graph.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import com.dsa.graph.dijkstra.algo.MinimumPathDist;

public class MstDijkstras {
	
	ArrayList<ArrayList<VertexDPair>> adj;
	int V;
	
	public MstDijkstras(int v) {
		V=v;
		adj = new ArrayList<>();
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<>());
		}
		
	}
	public void addEdge(int u, int v, int dist) {
		adj.get(u).add(new VertexDPair(v, dist));
		adj.get(v).add(new VertexDPair(u, dist));
	}
	
	public int[] dijktra(int src) {
		int[] dist=new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src]=0;
		PriorityQueue<VertexDPair> queue = new PriorityQueue<VertexDPair>();
		queue.add(new VertexDPair(src,0));
		
		while(!queue.isEmpty()) {
			VertexDPair pair = queue.poll();
			int vertex=pair.vertex;
			int d=pair.dist;
			if(d>dist[vertex]) {
				continue;
			}
			for(VertexDPair neg:adj.get(vertex)) {
				int v=neg.vertex,dis=neg.dist;
				if(dist[vertex]+dis<dist[v]) {
					dist[v]=dist[vertex]+dis;
					queue.add(new VertexDPair(v, dist[v]));
				}
			}
		}
		return dist;
		
	}
	
	public static void main(String[] args) {
		MstDijkstras g = new MstDijkstras(5);
        g.addEdge(0, 1, 2);
        g.addEdge(0, 2, 4);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 3, 7);
        g.addEdge(2, 4, 3);
        g.addEdge(3, 4, 1);

        int src = 0;
        int[] dist = g.dijktra(src);

        System.out.println("Shortest distances from source " + src + ":");
        System.out.println(Arrays.toString(dist));
    }


}
