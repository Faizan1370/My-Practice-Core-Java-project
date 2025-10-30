package com.dsa.graph.interview.question.geeksforgeeks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TopoSortKahns {
	public ArrayList<Integer> topSort(int V, int[][] edges){
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<Integer>());
		}
		 // Build adjacency list
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }
		
		int[] inDegree= new int[V];
		for(ArrayList<Integer> list :adj) {
			for(int data:list) {
				inDegree[data]++;
			}
		}
		bfs(adj,V,ans,inDegree);
		return ans;
	}

	private void bfs(ArrayList<ArrayList<Integer>> adj, int V, ArrayList<Integer> ans, int[] inDegree) {
		Queue<Integer> queue= new LinkedList<Integer>();
		for(int i=0;i<V;i++) {
    	  if(inDegree[i]==0) {
    		  queue.offer(i);
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
	}
	
	
	
	public static void main(String[] args) {
		  int V = 6;
	        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {4, 5}, {5, 1}, {5, 2}};
	        TopoSortKahns kahns = new TopoSortKahns();
	        ArrayList<Integer> topSort = kahns.topSort(V, edges);
	        System.out.println(topSort);
	}

}
