package com.dsa.graph.interview.question.geeksforgeeks.revision;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class PrimsMst {
	public static int spanningTree(int V, int edges[][]) {
		
		ArrayList<ArrayList<MstPrimVertex>> adj = new ArrayList<ArrayList<MstPrimVertex>>();
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<MstPrimVertex>());
		}
		for(int[] edge:edges) {
			int u=edge[0];
			int v=edge[1];
			int w=edge[2];
			adj.get(u).add(new MstPrimVertex(v, w));
			adj.get(v).add(new MstPrimVertex(u, w));
		}
		PriorityQueue<MstPrimVertex> queue = new PriorityQueue<MstPrimVertex>();
		boolean[] visited= new boolean[V];
		queue.add(new MstPrimVertex(0, 0));
		int mst=0;
		
		while(!queue.isEmpty()) {
			MstPrimVertex pair = queue.poll();
			int v=pair.vertex;
			int w=pair.weight;
			
			if(visited[v]) {
				continue;
			}
			visited[v]=true;
			mst +=w;
			for(MstPrimVertex neg : adj.get(v)) {
				if(!visited[neg.vertex]) {
					queue.add( new MstPrimVertex(neg.vertex, neg.weight));
				}
			}
		}
		return mst;
		
	}
	
	public static void main(String[] args) {
		 int graph[][] = new int[][] {{0,1,5},
             {1,2,3},
             {0,2,1}};
    System.out.println(spanningTree(3, graph));
	}
	
	

}
