package com.dsa.graph3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MstKruskal {
	
	public int mst(int V,ArrayList<ArrayList<ArrayList<Integer>>> adj) {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		for(int u=0;u<V;u++) {
			for(ArrayList<Integer> neg:adj.get(u)) {
				int v=neg.get(0);
				int w=neg.get(1);
				if(u<v) {
					edges.add(new Edge(u, v, w));
				}
			}
		}
		Collections.sort(edges);
		DisjointSetUnion disjointSetUnion = new DisjointSetUnion(V);
		int mstWeight=0,count=0;
		for(Edge ed:edges) {
			if(disjointSetUnion.find(ed.u) != disjointSetUnion.find(ed.v)) {
				disjointSetUnion.union(ed.u, ed.v);
				mstWeight +=ed.w;
				count++;
				if(count == V-1) {
					break;
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		MstKruskal kruskal = new MstKruskal();
		int V=5;
		ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<ArrayList<Integer>>());
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

	    System.out.println("MST Weight = " + kruskal.mst(V, adj));
	}

}
