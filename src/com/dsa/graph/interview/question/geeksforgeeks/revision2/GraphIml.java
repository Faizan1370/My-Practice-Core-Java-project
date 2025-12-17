package com.dsa.graph.interview.question.geeksforgeeks.revision2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphIml {
	
	/*
	 * public static void addEdge(ArrayList<ArrayList<Integer>> adj, int source,int
	 * dest) { adj.get(source).add(dest); adj.get(dest).add(source); }
	 */

	private static ArrayList<Integer> bfs(int V, ArrayList<ArrayList<Integer>> adj) {
		boolean[] visited = new boolean[V];
		ArrayList<Integer> list = new ArrayList<Integer>();		
		for(int i=0;i<V;i++) {
			if(!visited[i]) {
				bfsTraverse(i,visited,adj,list);
			}
		}
		return list;
		
	}
	
	private static void bfsTraverse(int v, boolean[] visited, ArrayList<ArrayList<Integer>> adj,
			ArrayList<Integer> list) {
		visited[v]=true;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(v);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			list.add(cur);
			
			for(int neg:adj.get(cur)) {
				if(!visited[neg]) {
					visited[neg]=true;
					queue.add(neg);
				}
			}
		}
		
	}
	
	public static ArrayList<Integer> dfs(int V, ArrayList<ArrayList<Integer>> adj){
		boolean[] visited = new boolean[V];
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i=0;i<V;i++) {
			if(!visited[i]) {
				dfsTraverse(i,visited,adj,list);
			}
		}
		return list;
	}


	private static void dfsTraverse(int v, boolean[] visited, ArrayList<ArrayList<Integer>> adj,
			ArrayList<Integer> list) {
		visited[v]=true;
		list.add(v);
		
		for(int neg :adj.get(v)) {
			if(!visited[neg]) {
				dfsTraverse(neg, visited, adj, list);
			}
		}
		
	}
	   public static boolean isCycle(int V,ArrayList<ArrayList<Integer>> adj) {
		   boolean[] visited = new boolean[V];
		   int parent =-1;
		   for(int i=0;i<V;i++) {
				if(!visited[i]) {
					if(dfsCycle(i,visited,adj,parent)){
						return true;
					}
				}
			}
		return false;
	   }

	private static boolean dfsCycle(int v, boolean[] visited, ArrayList<ArrayList<Integer>> adj,int parent) {
		visited[v]=true;
		
		for(int neg:adj.get(v)) {
			if(!visited[neg]) {
				if(dfsCycle(neg, visited, adj, v)) {
					return true;
				}
			}else if(parent !=neg) {
				return true;
			}
		}
		
		return false;
	}

	public static void main(String[] args) {
		int[][] mat = {{1, 2}, {0, 2}, {0, 1, 3, 4}, {2}, {2}};
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		int V= mat.length;
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<Integer>());
		}
		  // Fill adjacency list
        for(int u=0; u<V; u++) {
            for(int v : mat[u]) {
                adj.get(u).add(v);
                // If you want undirected graph and avoid duplicates, optionally:
                 if(!adj.get(v).contains(u)) {
                	 adj.get(v).add(u);
                 }
            }
        }
       System.out.println(isCycle(V, adj));

		
	}


}
