package com.dsa.graph.interview.question.geeksforgeeks;

import java.util.ArrayList;
import java.util.Stack;

public class TopoSort {
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
		boolean[] visited = new boolean[V];
		Stack<Integer> stack = new Stack<Integer>();
		for(int i =0;i<V;i++) {
			if(!visited[i]) {
				dfsTopo(adj,i,stack,visited);
			}
			
		}
		while(!stack.isEmpty()) {
			ans.add(stack.pop());
		}
		return ans;
	}

	private void dfsTopo(ArrayList<ArrayList<Integer>> adj, int v, Stack<Integer> stack, boolean[] visited) {
		visited[v]=true;
		for(int neg:adj.get(v)) {
			if(!visited[neg]) {
				dfsTopo(adj, neg, stack, visited);
			}
		}
		stack.push(v);
	}
	public static void main(String[] args) {
		TopoSort sort = new TopoSort();
		 int v = 6;
	        int[][] edges = {{2, 3}, {3, 1}, {4, 0},
	                          {4, 1}, {5, 0}, {5, 2}};
	        
	        ArrayList<Integer> topSort = sort.topSort(v, edges);
	        System.out.println(topSort);

	}

}
