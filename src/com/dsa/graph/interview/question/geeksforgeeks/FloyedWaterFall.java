package com.dsa.graph.interview.question.geeksforgeeks;

public class FloyedWaterFall {
	
	  public static void floydWarshall(int[][] dist) {
		  int n=dist.length;
		  
		  for(int k=0;k<n;k++) {
			  for(int i=0;i<n;i++) {
				  for(int j=0;j<n;j++) {
					  if (dist[i][k] + dist[k][j] < dist[i][j]) {
	                        dist[i][j] = dist[i][k] + dist[k][j];
	                    }
				  }
			  }
		  }
	  }
	  
	  public static void main(String[] args) {
		  int INF = 100000000;
	        int[][] dist = {
	            {0, 3, INF, 5},
	            {2, 0, INF, 4},
	            {INF, 1, 0, INF},
	            {INF, INF, 2, 0}
	        };

	        floydWarshall(dist);

	        for (int i = 0; i < dist.length; i++) {
	            for (int j = 0; j < dist.length; j++) {
	                System.out.print((dist[i][j] == INF ? "INF" : dist[i][j]) + " ");
	            }
	            System.out.println();
	        }
	}

}
