package com.dsa.graph.interview.question.geeksforgeeks.revision;

import java.util.ArrayList;
import java.util.Stack;

public class TopoLoSort {
	public static ArrayList<Integer> topSort(int V, int[][] edges){
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<Integer>());
		}
		for(int[] edge:edges) {
			adj.get(edge[0]).add(edge[1]);
		}
		boolean[] visited= new boolean[V];
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0;i<V;i++) {
			if(!visited[i]) {
				dfsTopo(i,adj,visited,stack);
			}
		}
		ArrayList<Integer> ans = new ArrayList<Integer>();
		while(!stack.isEmpty()) {
			ans.add(stack.pop());
		}
		return ans;
		
	}

	private static void dfsTopo(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
		visited[v]=true;
		
		for(int neg:adj.get(v)) {
			if(!visited[neg]) {
				dfsTopo(neg, adj, visited, stack);
			}
		}
		stack.push(v);
		
	}
	
	public static void main(String[] args) {
		 int v = 6;
	        int[][] edges = {{2, 3}, {3, 1}, {4, 0},
	                          {4, 1}, {5, 0}, {5, 2}};
	        
	        ArrayList<Integer> topSort = topSort(v, edges);
	        System.out.println(topSort);

	}

}
