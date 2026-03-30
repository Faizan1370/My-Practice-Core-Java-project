package com.dsa.graph.kruskal.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Krussss {
	 public static int kruskalsMST(int V, int[][] edges) {
		 Arrays.sort(edges, Comparator.comparingInt(e->e[2]));
			
		 
		 DSU dsu = new DSU(V);
		 int count=0,mst=0;
		 
		 for(int[] e:edges) {
			  int x = e[0], y = e[1], w = e[2];
			  
			  if(dsu.find(x)!= dsu.find(y)) {
				  dsu.union(x, y);
				  mst +=w;
				  count++;
				  if(count==V-1) {
					  break;
				  }
			  }
			 
		 }
		return mst;
	 }

}
