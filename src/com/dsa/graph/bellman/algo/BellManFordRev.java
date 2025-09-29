package com.dsa.graph.bellman.algo;

import java.util.Arrays;

import com.dsa.graph.kruskal.algo.Edge;

public class BellManFordRev {
	public static int isNegativeCycyle(int n,int[][] edges) {
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0]=0;
		for(int count=1;count<n;count++) {
			for(int j=0;j<edges.length;j++) {
				int src=edges[j][0];
				int dest=edges[j][1];
				int weeigt=edges[j][2];
				if(dist[src]+weeigt<dist[dest]) {
					dist[dest]=dist[src]+weeigt;
				}
			}
		}
		for(int j=0;j<edges.length;j++) {
			int src=edges[j][0];
			int dest=edges[j][1];
			int weeigt=edges[j][2];
			if(dist[src]+weeigt<dist[dest]) {
				return 1;
			}
		}
		return 0;
	}
	public static void main(String[] args) {
		int[][] edges= {{0,1,3},{0,2,1},{1,2,-8},{2,3,2},{3,1,4}}; // for negative just add -8 insted of -4
		int n=4;
		System.out.println(isNegativeCycyle(n, edges));
	}

}
