package com.dsa.graph.revision3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class GrpahImle {
	int V;
	ArrayList<ArrayList<Integer>> adj;
	
	public GrpahImle(int v) {
		V=v;
		adj= new ArrayList<>();
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<Integer>());
		}
	}
	
	public void addEdge(int source,int dest) {
		adj.get(source).add(dest);
		adj.get(dest).add(source);
	}
	
	public int minDistance(int source,int dest) {
		boolean[] visited= new boolean[V];
		int[] pred= new int[V];
		int[] dist = new int[V];
		Arrays.fill(pred, -1);
		Arrays.fill(dist, Integer.MAX_VALUE);
		visited[source]=true;
		dist[source]=0;
		Queue<Integer> queue =new LinkedList<Integer>();
		queue.add(source);
		pred[source]=source;
		while(!queue.isEmpty()) {
			int current = queue.poll();
			for(int neg:adj.get(current)) {
				if(!visited[neg]) {
					visited[neg]=true;
					dist[neg]=dist[current]+1;
					pred[neg]=current;
					queue.add(neg);
					if(neg==dest) {
						System.out.println(getPath(source, dest, pred));
						return dist[neg];
					}
				}
			}
		}
		return -1;
	}
	
	public ArrayList<Integer> getPath(int source,int dest,int[] pred) {
		ArrayList<Integer> list = new ArrayList<Integer>();
 		for(int at=dest;at!=-1;at=pred[at]) {
			list.add(at);
			if(at== source) {
				break;
			}
		}
		return list;
	}
	
	public ArrayList<Integer> dfsGraph() {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		boolean[] visited = new boolean[V];
		for(int i=0;i<V;i++) {
			if(!visited[i]) {
				dfs(i,adj,visited,ans);
			}
			
		}
		return ans;
	}
	private void dfs(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> ans) {
		visited[v]=true;
		ans.add(v);
		
		for(int neg:adj.get(v)) {
			if(!visited[neg]) {
				dfs(neg, adj, visited, ans);
			}
		}
	}
	
	public boolean detectCycle() {
		boolean[] visited = new boolean[V];
		int parent=-1;
		for(int i=0;i<V;i++) {
			if(dfcCycle(i,adj,visited,parent)) {
				return true;
			}
		}
		return false;
	}

	private boolean dfcCycle(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, int parent) {
		visited[v]=true;
		
		for(int neg:adj.get(v)) {
			if(!visited[neg]) {
				if(dfcCycle(neg, adj, visited, v)){
					return true;
				}
			}else if(parent!=neg) {
				return true;
			}
		}
		return false;
	}
	
	public boolean detectCycleDirected() {
		boolean[] visited = new boolean[V];
		boolean[] recS= new boolean[V];
		for(int i=0;i<V;i++) {
			if(dfsCycle(i,adj,visited,recS)) {
				return true;
			}
		}
		return false;
	}

	private boolean dfsCycle(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited,  boolean[] recS) {
		visited[v]=true;
		recS[v]=true;
		for(int neg:adj.get(v)) {
			if(!visited[neg]) {
				 if (dfsCycle(neg, adj, visited, recS)) return true;
			}else if(recS[neg]) {
				return true;
			}
		}
		recS[v]=false;
		return false;
	}
	
	public int[]  topoSort() {
		boolean[] visited = new boolean[V];
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0;i<V;i++) {
			topoDfs(i,adj,visited,stack);
		}
		int[] ans = new int[V];
		int i=0;
		while(!stack.isEmpty()) {
			ans[i++]=stack.pop();
		}
		return ans;
	}

	private void topoDfs(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited,  Stack<Integer> stack) {
		visited[v]=true;
		for(int neg:adj.get(v)) {
			if(!visited[neg]) {
				topoDfs(neg, adj, visited, stack);
			}
		}
		stack.push(v);
	}
	
	public ArrayList<Integer> topoKohns() {
		int[] inDeg= new int[V];
		for(ArrayList<Integer> list:adj) {
			for(int e:list) {
				inDeg[e]++;
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i=0;i<V;i++) {
			if(inDeg[i]==0) {
				queue.add(i);
			}
		}
		while(!queue.isEmpty()) {
			int v = queue.poll();
			list.add(v);
			for(int neg:adj.get(v)) {
				if(--inDeg[neg]==0) {
					queue.add(neg);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		GrpahImle impl = new GrpahImle(6);
		impl.addEdge(0, 1);
		impl.addEdge(0, 2);
		impl.addEdge(0, 4);
		impl.addEdge(2, 3);
		impl.addEdge(5, 4);
		impl.addEdge(5, 3);
		impl.addEdge(2, 4);
		impl.addEdge(1, 4);
		
		System.out.println(impl.minDistance(0, 3));
		System.out.println(impl.dfsGraph());
		System.out.println(impl.detectCycle());
		
	}

}
