package com.dsa.graph.kruskal.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dsa.pract.DisjointSet;

public class KruskalMST {
	public static int mst(int V,ArrayList<ArrayList<ArrayList<Integer>>> adj) {
		List<Edge> edges= new  ArrayList<Edge>();
		
		for(int u=0;u<V;u++) {
			for(ArrayList<Integer> neg:adj.get(u)) {
				int v=neg.get(0);
				int w= neg.get(1);
				if(u<v) {
					edges.add(new Edge(u, v, w));
				}
			}
		}
		Collections.sort(edges);
		
		DSU dsu = new DSU(V);
		int mstWeight=0,count=0;
		
		for(Edge e:edges) {
			if(dsu.find(e.u)!=dsu.find(e.v)) {
				dsu.union(e.u, e.v);
				mstWeight+=e.w;
				count++;
				if(count ==V-1) {
					break;
				}
			}
		}
		return mstWeight;
	}
	public int makeConnected(int n, int[][] connections) {
	    if(connections.length < n - 1) return -1;

	    DisjointSet ds = new DisjointSet(n);

	    for(int[] edge : connections) {
	        ds.union(edge[0], edge[1]);
	    }

	    int components = 0;

	    for(int i = 0; i < n; i++) {
	        if(ds.find(i) == i) {
	            components++;
	        }
	    }

	    return components - 1;
	}
	public int makeConnectedFullVersion(int n, int[][] connections) {
	    DisjointSet ds = new DisjointSet(n);
	    int extraEdges = 0;

	    for(int[] edge : connections) {
	        int u = edge[0];
	        int v = edge[1];

	        if(ds.find(u) == ds.find(v)) {
	            extraEdges++;  // 🔥 redundant edge
	        } else {
	            ds.union(u, v);
	        }
	    }

	    int components = 0;

	    for(int i = 0; i < n; i++) {
	        if(ds.find(i) == i) {
	            components++;
	        }
	    }

	    if(extraEdges >= components - 1) {
	        return components - 1;
	    }

	    return -1;
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
