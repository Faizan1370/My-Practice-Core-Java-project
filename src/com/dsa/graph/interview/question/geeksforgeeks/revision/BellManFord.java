package com.dsa.graph.interview.question.geeksforgeeks.revision;

import java.util.Arrays;

public class BellManFord {
	
	 public static int[] bellmanFord(int V, int[][] edges, int src) {
		 int[] dist = new int[V];
		 Arrays.fill(dist, Integer.MAX_VALUE);
		 dist[src]=0;
		 
		 for(int i=0;i<V;i++) {
			 for(int[] edge:edges) {
				 int u = edge[0];
	                int v = edge[1];
	                int wt = edge[2];
	                if(dist[u]!=Integer.MAX_VALUE && dist[u]+wt<dist[v]) {
						/*
						 * if(i==V-1) { return new int[] {-1}; }
						 */
	                	dist[v]= dist[u]+wt;
	                }
	                
			 }
		 }
		// Step 2: Check for negative-weight cycles
		    for(int[] edge : edges) {
		        int u = edge[0];
		        int v = edge[1];
		        int wt = edge[2];

		        if(dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
		            return new int[]{-1};  // Negative cycle detected
		        }
		    }
		return dist;
	 }
	 
	 public static void main(String[] args) {
		 // Number of vertices in the graph
		    int V = 5;

		    // Edge list representation: {source, destination, weight}
		    int[][] edges = new int[][] {
		        {1, 3, 2},    
		        {4, 3, -1},   
		        {2, 4, 1},    
		        {1, 2, 1},    
		        {0, 1, 5}     
		    };

		    // Source vertex for Bellman-Ford algorithm
		    int src = 0;

		    // Run Bellman-Ford algorithm from the source vertex
		    int[] ans = bellmanFord(V, edges, src);

		    // Print shortest distances from the source to all vertices
		    for (int dist : ans) 
		        System.out.print(dist + " ");
		}
	}

