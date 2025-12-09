package com.dsa.graph.interview.question.geeksforgeeks.revision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgo {
	
	public static int mst(int V,ArrayList<ArrayList<ArrayList<Integer>>> adj) {
		 ArrayList<EdgeKrus> edges = new ArrayList<EdgeKrus>();
		
		 for(int u=0;u<V;u++) {
			 for(ArrayList<Integer> neg:adj.get(u)) {
				 int v=neg.get(0);
				 int w = neg.get(1);
				 if(u<v) {
					 edges.add(new EdgeKrus(u, v, w));
				 }
			 }
		 }
		 Collections.sort(edges);
		 DSUKru dsu = new DSUKru(V);
		 int mst=0,count=0;
		 
		 for(EdgeKrus edge:edges) {
			 int x=edge.u;
			 int y=edge.v;
			 int w= edge.w;
			 if(dsu.find(x)!=dsu.find(y)) {
				 dsu.union(x, y);
				 mst +=w;
				 count++;
				 if(count==V-1) {
					 break;
				 }
				
		}
		 }
		return mst;
        		
		 
	 }
	public static void main(String[] args) {
		 int V = 4;
		    ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
		    for (int i = 0; i < V; i++) {
		        adj.add(new ArrayList<>());
		    }
		    // Add edges: u, v, w
		    adj.get(0).add(new ArrayList<>(List.of(1, 1)));
		    adj.get(0).add(new ArrayList<>(List.of(2, 3)));
		    adj.get(1).add(new ArrayList<>(List.of(0, 1)));
		    adj.get(1).add(new ArrayList<>(List.of(2, 3)));
		    adj.get(1).add(new ArrayList<>(List.of(3, 4)));
		    adj.get(2).add(new ArrayList<>(List.of(0, 3)));
		    adj.get(2).add(new ArrayList<>(List.of(1, 3)));
		    adj.get(2).add(new ArrayList<>(List.of(3, 2)));
		    adj.get(3).add(new ArrayList<>(List.of(1, 4)));
		    adj.get(3).add(new ArrayList<>(List.of(2, 2)));

		    System.out.println("MST Weight = " + mst(V, adj));
	}

}
