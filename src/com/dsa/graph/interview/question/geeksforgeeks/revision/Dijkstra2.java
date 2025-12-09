package com.dsa.graph.interview.question.geeksforgeeks.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra2 {
	
	
	public static int[] minDistance(ArrayList<ArrayList<VertextDiDit>> adj ,int src) {
		int V=adj.size();
		PriorityQueue<VertextDiDit> queue = new PriorityQueue<VertextDiDit>();
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] visited = new boolean[V];
		queue.add(new VertextDiDit(src, 0));
		dist[src]=0;
		
		while(!queue.isEmpty()) {
			VertextDiDit pair = queue.poll();
			int u=pair.vertex;
			//int d=pair.dist;
			if(visited[u]) {
				continue;
			}
			visited[u]=true;
			for(VertextDiDit vd : adj.get(u)) {
				int v=vd.vertex;
				int ds=vd.dist;
				if(dist[u]+ds<dist[v]) {
					dist[v]=dist[u]+ds;
					queue.add(new VertextDiDit(v, dist[v]));
				}
			}
		}
		return dist;
		
	}
	
	public static void main(String[] args) {

	    int V = 5;

	    ArrayList<ArrayList<VertextDiDit>> adj = new ArrayList<>();
	    for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

	    // u, v, w
	    addEdge(adj, 0, 1, 2);
	    addEdge(adj, 0, 4, 1);
	    addEdge(adj, 1, 2, 3);
	    addEdge(adj, 4, 2, 2);
	    addEdge(adj, 2, 3, 6);
	    addEdge(adj, 4, 3, 4);

	    int[] result = Dijkstra2.minDistance(adj, 0);

	    System.out.println("Shortest distances from src 0:");
	    System.out.println(Arrays.toString(result));
	}

	static void addEdge(ArrayList<ArrayList<VertextDiDit>> adj, int u, int v, int w) {
	    adj.get(u).add(new VertextDiDit(v, w));
	    adj.get(v).add(new VertextDiDit(u, w));  // if graph is undirected
	}

}
