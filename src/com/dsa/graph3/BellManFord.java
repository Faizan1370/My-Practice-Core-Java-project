package com.dsa.graph3;

import java.util.Arrays;

public class BellManFord {
	
	public static int isNegativeCycle(int n,int[][] edges) {
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0]=0;
		for(int count=1;count<n;count++) {
			for(int j=0;j<edges.length;j++) {
				int u=edges[j][0];
				int v=edges[j][1];
				int w=edges[j][2];
				if(dist[u]!=Integer.MAX_VALUE && dist[u]+w<dist[v]) {
					dist[v]=dist[u]+w;
				}
			}
		}
		for(int j=0;j<edges.length;j++) {
			int u=edges[j][0];
			int v=edges[j][1];
			int w=edges[j][2];
			if(dist[u]!=Integer.MAX_VALUE && dist[u]+w<dist[v]) {
				return 1;
			}
		}
		return 0;
	}
	public static void main(String[] args) {
		int[][] edges= {{0,1,3},{0,2,1},{1,2,-8},{2,3,2},{3,1,4}}; // for negative just add -8 insted of -4
		int n=4;
		System.out.println(isNegativeCycle(n, edges));
	}

}
