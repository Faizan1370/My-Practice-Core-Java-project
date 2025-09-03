package com.dsa.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphImple {

	private int V;
	private ArrayList<ArrayList<Integer>> adj;

	public GraphImple(int v) {
		V = v;
		adj = new ArrayList<>();
		for (int i = 0; i < v; i++) {
			adj.add(new ArrayList<>());
		}
	}

	public void addEdge(int u, int v) {
		adj.get(u).add(v);
		adj.get(v).add(u);
	}

	public int minDistance(int src, int dest) {
		boolean[] visited = new boolean[V];
		int[] dist = new int[V];
		Arrays.fill(dist, -1);
		Queue<Integer> queue = new LinkedList<>();
		queue.add(src);
		visited[src] = true;
		dist[src] = 0;

		while (!queue.isEmpty()) {
			int node = queue.poll();
			if (node == dest) {
				return dist[node];
			}
			for (int neigbour : adj.get(node)) {
				if (!visited[neigbour]) {
					visited[neigbour] = true;
					dist[neigbour] = dist[node] + 1;
					queue.add(neigbour);
				}
			}
		}
		return -1;

	}

	public ArrayList<Integer> dfsOfGraph() {
		boolean[] visited = new boolean[V];
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				dfs(i, adj, visited, ans);
			}
		}

		return ans;
	}

	public void dfs(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> ans) {
		visited[v] = true;

		ans.add(v);
		for (Integer neigbour : adj.get(v)) {
			if (!visited[neigbour]) {
				dfs(neigbour, adj, visited, ans);
			}
		}
	}

	public boolean isCycleUndirectedGraph() {
		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				if (cycyleDfs(visited, adj, i, -1)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean cycyleDfs(boolean[] visited, ArrayList<ArrayList<Integer>> adj, int v, int parent) {
		visited[v] = true;

		for (Integer neghbour : adj.get(v)) {
			if (!visited[neghbour]) {
				if (cycyleDfs(visited, adj, neghbour, v)) {
					return true;
				}
			} else if (parent != neghbour) {
				return true;
			}
		}
		return false;
	}

	public boolean isCycledirectedGraph() {
		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];

		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				if (dfsDirectedGraph(i, visited, adj, recStack)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean dfsDirectedGraph(int i, boolean[] visited, ArrayList<ArrayList<Integer>> adj, boolean[] recStack) {
		visited[i] = true;
		recStack[i] = true;
		for (Integer neg : adj.get(i)) {
			if (!visited[neg]) {
				if (dfsDirectedGraph(neg, visited, adj, recStack)) {
					return true;
				}
			} else if (recStack[neg]) {
				return true;
			}

		}
		recStack[i] = false;

		return false;

	}
	
	public int[] topoSort() {
		boolean[] visiter = new boolean[V];
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0;i<V;i++) {
			if(!visiter[i]) {
				topoDfs(i, adj, visiter, stack);
			}
		}
		int[] ans = new int[V];
		int i=0;
		while(!stack.isEmpty()) {
			ans[i++]=stack.pop();
		}
		return ans;
	}
	public void topoDfs(int i,ArrayList<ArrayList<Integer>> adj, boolean[] visiter, Stack<Integer> stack) {
		visiter[i]=true;
		for(Integer negh:adj.get(i)) {
			if(!visiter[negh]) {
				topoDfs(negh, adj, visiter, stack); 
			}
		}
		stack.push(i);
	}

	public static void main(String[] args) {
		GraphImple g = new GraphImple(6);
		g.addEdge(0, 3);
		g.addEdge(0, 2);
		g.addEdge(2, 3);
		g.addEdge(2, 1);
		g.addEdge(3, 1);
		g.addEdge(1, 4);
		g.addEdge(5, 4);
		g.addEdge(5, 1);

		// System.out.println("Minimum distance: " + g.minDistance(0, 5));
		System.out.println(Arrays.toString(g.topoSort()));
	}

}
