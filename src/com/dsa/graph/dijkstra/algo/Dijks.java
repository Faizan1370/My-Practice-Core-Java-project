package com.dsa.graph.dijkstra.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijks {
	
   public static int[] shortestPathDijktra(int src,int[][] grid) {
	   int[] dist = new int[grid.length];
	    Arrays.fill(dist, Integer.MAX_VALUE);
	    dist[src] = 0;

	    PriorityQueue<int[]> pq =
	        new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

	    pq.add(new int[]{src, 0});
		while(!pq.isEmpty()) {
			int[] pair = pq.poll();
			int v = pair[0];
			int w=pair[1];
			  if (w > dist[v]) continue;
			 for(int[] edge:grid) {
				 int next = edge[0];
				 int nxtW=edge[1];
				 if(dist[v]+nxtW<dist[next]) {
					 dist[next]=dist[v]+nxtW;
					 pq.add(new int[]{next, dist[next]});
				 }
			 }
		}
		return dist;
		
	}
   public int[] bellManFord(int[][] grid,int V) {
	   int[] dist = new int[V];
	   Arrays.fill(dist, Integer.MAX_VALUE);
	   int count=0;
	   for(int i=0;i<V;i++) {
		   for(int j=0;j<V;j++) {
			   int src=grid[j][0];
				int dest=grid[j][1];
				int weight =grid[j][2];
			   if(dist[src] !=Integer.MAX_VALUE && dist[src]+weight <dist[dest]) {
				   dist[dest]=dist[src]+weight;
			   }
			   if(count==V-1) {
				   return new int[] {0};
			   }
		   }
	   }
	return dist;
   }
   static ArrayList<Integer> dijkstra1(ArrayList<ArrayList<int[]>> adj, int src) {
	   int V=adj.size();
	   ArrayList<ArrayList<VertexDistPair>> list = new ArrayList<ArrayList<VertexDistPair>>();
	   
	   
	   PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a,b)->a[0]-b[0]);
	   
	   int[] dist = new int[V];
       Arrays.fill(dist, Integer.MAX_VALUE);
	   
       dist[src]=0;
       queue.add(new int[] {0,src});
       
       while(!queue.isEmpty()) {
    	int[] pair=   queue.poll();
    	
    	int d=pair[0];
    	int u= pair[1];
    	  // If this distance is not the latest shortest one, skip it
        if (d > dist[u])
            continue;
        for(int[] edge:adj.get(u)) {
     	   int v=edge[0];
     	   int w=edge[1];
     	   if(dist[u]+w <dist[v]) {
     		   dist[v]=dist[u]+w;
     		   queue.add(new int[] {dist[v],v});
     	   }
        }
       }
       ArrayList<Integer> result = new ArrayList<>();
       for (int d : dist)
           result.add(d);

       // Return the final shortest distances from the source
       return result;
      
   }


}
