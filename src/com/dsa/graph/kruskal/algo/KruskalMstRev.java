package com.dsa.graph.kruskal.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalMstRev {
	public static int mst(int V,ArrayList<ArrayList<ArrayList<Integer>>> adj) {
		ArrayList<EdgeRev> edges=new ArrayList<EdgeRev>();
		
		for(int u=0;u<V;u++) {
			for(ArrayList<Integer> list :adj.get(u)) {
				int v=list.get(0);
				int w=list.get(1);
				if(u<v) {
					edges.add(new EdgeRev(u, v, w));
				}
			}
		}
		Collections.sort(edges);
		DSURev dsuRev = new DSURev(V);
		int mstWeight=0,count=0;
		for(EdgeRev e:edges) {
			if(dsuRev.find(e.u)!=dsuRev.find(e.v)) {
				dsuRev.union(e.u, e.v);
				mstWeight+=e.w;
				count++;
				if(count==V-1) {
					break;
				}
			}
		}
		return mstWeight;
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
