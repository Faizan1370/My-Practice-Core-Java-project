package com.dsa.graph.revision;

import java.util.ArrayList;
import java.util.PriorityQueue;

import com.dsa.graph.prims.algo.MinimumSpanningTree;

public class MstPrims {
	int V;
	ArrayList<ArrayList<VertexWPair>> adj;
	
	public MstPrims(int v) {
		V=v;
		adj = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<>());
		}
	}
	
	public void addEdge(int u,int v,int w) {
		adj.get(u).add(new VertexWPair(v, w));
		adj.get(v).add(new VertexWPair(u, w));
	}
	
	public int mst() {
		boolean[] visited= new boolean[V];
		PriorityQueue<VertexWPair> queue = new PriorityQueue<VertexWPair>();
		queue.add(new VertexWPair(0, 0));
		int ans =0;
		while(!queue.isEmpty()) {
			VertexWPair pair = queue.poll();
			int w=pair.weight;
			int v=pair.vertex;
			
			if(visited[v]) {
				continue;
			}
			ans +=w;
			visited[v]=true;
			for(VertexWPair neg:adj.get(v)) {
				if(!visited[neg.vertex]) {
					queue.add(new VertexWPair(neg.vertex, neg.weight));
				}
			}
		}
		return ans;
	}
	
	
	public static void main(String[] args) {
		MstPrims g = new MstPrims(5);
		g.addEdge(0, 1, 2);
		g.addEdge(0, 3, 6);
		g.addEdge(1, 2, 3);
		g.addEdge(1, 3, 8);
		g.addEdge(1, 4, 5);
		g.addEdge(2, 4, 7);
		System.out.println(g.mst());
	}


}
