package com.dsa.graph.interview.question.geeksforgeeks.revision2;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KrusKlaMst {
	public static int kruskalsMST(int V, int[][] edges) {

		Arrays.sort(edges, Comparator.comparingInt(e -> e[2]));

		int cost = 0, count = 0;
		DSUKrusKal dsuKrusKal = new DSUKrusKal(V);

		for (int[] edge : edges) {
			int u = edge[0], v = edge[1], w = edge[2];
			if (dsuKrusKal.find(u) != dsuKrusKal.find(v)) {
				dsuKrusKal.union(u, v);
				cost += w;
				count++;
				if (count == V - 1) {
					break;
				}
			}
		}
		return cost;

	}

	public static int kruskalsMST1(int V, int[][] edges) {
		 List<KrusKalEdge> edgeList = new ArrayList<>();

		    for (int[] e : edges) {
		        edgeList.add(new KrusKalEdge(e[0], e[1], e[2]));
		    }
		    Collections.sort(edgeList);  // uses compareTo automatically
		    DSUKrusKal dsu = new DSUKrusKal(V);
		    int mstWeight = 0;
		    int count = 0;
		    
		    for(KrusKalEdge edge: edgeList) {
		    	if(dsu.find(edge.u) != dsu.find(edge.v)) {
		    		dsu.union(edge.u, edge.v);
		    		   mstWeight += edge.w;   // ✔ add weight
		               count++;              // ✔ count edge
		    	}
		    	
		    	if(count==V-1) {
		    		break;
		    	}
		    }
			return mstWeight;
	}

	public static void main(String[] args) {
		// An edge contains, weight, source and destination
		int[][] edges = { { 0, 1, 10 }, { 1, 3, 15 }, { 2, 3, 4 }, { 2, 0, 6 }, { 0, 3, 5 } };

		System.out.println(kruskalsMST1(4, edges));
	}

}
