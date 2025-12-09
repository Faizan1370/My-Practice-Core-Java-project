package com.dsa.graph.interview.question.geeksforgeeks.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TopoSortBFS {
	public static ArrayList<Integer> topSort(int V, int[][] edges){
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<Integer>());
		}
		for(int[] edge:edges) {
			adj.get(edge[0]).add(edge[1]);
		}
		int[] inDegree= new int[V];
		
		for(ArrayList<Integer> list :adj) {
			for(int e:list) {
				inDegree[e]++;
			}
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i=0;i<V;i++) {
			if(inDegree[i]==0) {
				queue.add(i);
			}
		}
		while(!queue.isEmpty()) {
			int current = queue.poll();
			ans.add(current);
			for(int neg:adj.get(current)) {
				if(--inDegree[neg]==0) {
					queue.add(neg);
				}
			}
		}
		return ans;
	}
	public static void main(String[] args) {
		 int v = 6;
	        int[][] edges = {{2, 3}, {3, 1}, {4, 0},
	                          {4, 1}, {5, 0}, {5, 2}};
	        
	        ArrayList<Integer> topSort = topSort(v, edges);
	        System.out.println(topSort);

	}

}
