package com.dsa.graph.interview.question.geeksforgeeks.revision;

import java.util.Arrays;
import java.util.Comparator;

public class KrusKal2 {
	public static int kruskalsMST(int V, int[][] edges) {
		
		Arrays.sort(edges,Comparator.comparingInt(e->e[2]));
		  DSUKru dsu = new DSUKru(V);
	      int cost = 0, count = 0;
		
		for(int[] edge:edges) {
			int x=edge[0];
			int y=edge[1];
			int w=edge[2];
			if(dsu.find(x)!=dsu.find(y)) {
				dsu.union(x, y);
				cost +=w;
				count++;
				if(count==V-1) {
					break;
				}
			}
		}
		return cost;
		
	}
	public static void main(String[] args) {
		int[][] edges = {
			    {0, 1, 1},
			    {1, 2, 2},
			    {0, 2, 3}
			};

		  System.out.println(kruskalsMST(4, edges));
	}
}
