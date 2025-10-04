package com.dsa.graph3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class GraphImle {
	
	public int minimumSpanning(int V,ArrayList<ArrayList<ArrayList<Integer>>> adj) {
		boolean[] visited = new boolean[V];
		PriorityQueue<VertexWPair> queue = new PriorityQueue<VertexWPair>();
		queue.add(new VertexWPair(0, 0));
		int ans=0;
		
		while(!queue.isEmpty()) {
			VertexWPair pair = queue.poll();
			int vertex = pair.v;
			if(visited[vertex]) {
				continue;
			}
			ans +=pair.w;
			
			visited[vertex]=true;
			
			ArrayList<ArrayList<Integer>> negs=adj.get(vertex);
			for(ArrayList<Integer> list :negs) {
				int v=list.get(0);
				int w=list.get(1);
				if(!visited[v]) {
					queue.add(new VertexWPair(v, w));
				}
			}
		}
		return ans;
	}
	public static void main(String[] args) {
		GraphImle graphImle = new GraphImle();
		ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
		int V=6;
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<ArrayList<Integer>>());
		}
		   // Add edges (u, v, w)
	    addEdge(adj, 0, 1, 2);
	    addEdge(adj, 0, 2, 4);
	    addEdge(adj, 1, 2, 3);
	    addEdge(adj, 1, 3, 5);
	    addEdge(adj, 2, 4, 6);
	    addEdge(adj, 3, 4, 7);
	    addEdge(adj, 3, 5, 8);
	    addEdge(adj, 4, 5, 9);
	    
	    System.out.println(graphImle.minimumSpanning(V, adj));

	}
	
	static void addEdge(ArrayList<ArrayList<ArrayList<Integer>>> adj,int src,int dest,int w) {
		adj.get(src).add(new ArrayList<Integer>(Arrays.asList(dest,w)));
		adj.get(dest).add(new ArrayList<Integer>(Arrays.asList(src,w)));
	}

}
