package com.dsa.graph.revision2.prims;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MstPrims {
	
	
	public int spaningTree(int V,ArrayList<ArrayList<ArrayList<Integer>>> adj) {
		boolean[] visited = new boolean[V];
		PriorityQueue<VertexWP> queue = new PriorityQueue<VertexWP>();
		
		queue.add(new VertexWP(0, 0));
		int ans=0;
		while(!queue.isEmpty()) {
			VertexWP pair = queue.poll();
			int vertex= pair.vertex;
			int weight= pair.weight;
			
			if(visited[vertex]) {
				continue;
			}
			ans+=weight;
			visited[vertex]=true;
			ArrayList<ArrayList<Integer>> neigbours= adj.get(vertex);
			
			for(ArrayList<Integer> neg :neigbours) {
				int v=neg.get(0);
				int w = neg.get(1);
				if(!visited[v]) {
					queue.add(new VertexWP(v, w));
				}
			}
		}
		return ans;
		
	}
	public static void main(String[] args) {
		MstPrims mstPrims = new MstPrims();
		ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
		int V=6;
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<>());
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

	    int mstWeight = mstPrims.spaningTree(V, adj);
	    System.out.println("MST weight = " + mstWeight);
		
		
	}
	 static void addEdge(ArrayList<ArrayList<ArrayList<Integer>>> adj,int u,int v,int w) {
			adj.get(u).add(new ArrayList<Integer>(Arrays.asList(v,w)));
			adj.get(v).add(new ArrayList<Integer>(Arrays.asList(u,w)));
			
		}

}
