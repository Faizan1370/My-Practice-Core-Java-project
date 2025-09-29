package com.dsa.graph.bellman.algo;

import java.util.Arrays;

public class BellmanFord {
	
	public static int isNegativeCycle(int n,int[][] edges) {
		int[] dist= new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		//int src=0; // can start from any vertex if not given
		dist[0]=0;
		for(int count=1;count<n;count++) {
			for(int j=0;j<edges.length;j++) {
				int src=edges[j][0];
				int dest=edges[j][1];
				int weight =edges[j][2];
				if(dist[src] !=Integer.MAX_VALUE && dist[src]+weight<dist[dest]){
					dist[dest]= dist[src]+weight;
				}
			}
		}
		System.out.println(Arrays.toString(dist));
		for(int j=0;j<edges.length;j++) {
			int src=edges[j][0];
			int dest=edges[j][1];
			int weight =edges[j][2];
			if(dist[src] !=Integer.MAX_VALUE && dist[src]+weight<dist[dest]){
				return 1;
			}
		}
		return 0;
		
	}
	
	public static void main(String[] args) {
		int[][] edges= {{0,1,3},{0,2,1},{1,2,-4},{2,3,2},{3,1,4}}; // for negative just add -8 insted of -4
		int n=4;
		System.out.println(isNegativeCycle(n, edges));
	}

}
