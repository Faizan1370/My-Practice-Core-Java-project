package com.dsa.graph.revision2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class GraphImp {
	int V;
	ArrayList<ArrayList<Integer>> adj;
	
	public GraphImp(int v) {
		V=v;
		adj= new ArrayList<>();
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<Integer>());
		}
	}
	public void addEdge(int source,int destination) {
		adj.get(source).add(destination);
		adj.get(destination).add(source);
	}
	
	public int minDistance(int source,int dest) {
		boolean[] visited= new boolean[V];
		int[] dist=new int[V];
		int[] pred = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(pred, -1);
		visited[source]=true;
		dist[source]=0;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(source);
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			for(int neg:adj.get(curr)) {
				if(!visited[neg]) {
					visited[neg]=true;
					dist[neg]=dist[curr]+1;
					pred[neg]=curr;
					queue.add(neg);
					if(neg==dest) {
						System.out.println("get Path :"+getPath(source, dest, pred));
						return 1;
					}
				}
			}
		}
		return -1;
	}
	
	public ArrayList<Integer> getPath(int source,int dest,int[] pred){
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for(int at=dest;at!=-1;at=pred[at]) {
			ans.add(at);
			if(at==source) {
				break;
			}
		}
		return ans;
	}
	
	public List<Integer> dfsTraverse(){
		boolean[] visited = new boolean[V];
		ArrayList<Integer> ansList= new ArrayList<Integer>();
		for(int i=0;i<V;i++) {
			if(!visited[i]) {
				dfs(i,adj,ansList,visited);
			}
			
		}
		return ansList;
	}
	
	private void dfs(int i, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> ansList, boolean[] visited) {
		visited[i]=true;
		ansList.add(i);
		for(int neg:adj.get(i)) {
			if(!visited[neg]) {
				dfs(neg, adj, ansList, visited);
			}
		}
	}
	
	public boolean detectCycle() {
		boolean[] visited = new boolean[V];
		int parent =-1;
		for(int i=0;i<V;i++) {
			if(!visited[i]) {
				if(dfsCycleDetect(i,adj,visited,parent)){
					return true;
				}
			}
		}
		return false;
	}
	private boolean dfsCycleDetect(int i, ArrayList<ArrayList<Integer>> adj, boolean[] visited, int parent) {
		visited[i]=true;
		for(int neg:adj.get(i)) {
			if(!visited[neg]) {
				if(dfsCycleDetect(neg, adj, visited, i)) {
					return true;
				}
			}else if(parent!=neg) {
				return true;
			}
		}
		return false;
	}
	
	public boolean detectDirectedCycle() {
		boolean[] visited = new boolean[V];
		boolean[] recS= new boolean[V];
		for(int i=0;i<V;i++) {
			if(!visited[i]) {
				if(dfsCycleDirectedDetect(i,adj,visited,recS)){
					return true;
				}
			}
		}
		return false;
	}
	private boolean dfsCycleDirectedDetect(int i, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] recS) {
		visited[i]=true;
		recS[i]=true;
		for(int neg:adj.get(i)) {
			if(!visited[neg]) {
				if(dfsCycleDirectedDetect(neg, adj, visited,recS)) {
					return true;
				}
			}else if(recS[neg]) {
				return true;
			}
		}
		recS[i]=false;
		return false;
	}
	public boolean isNegtiveCycle(int n,int[][] edges) {
		int[] dist= new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0]=0;
		
		for(int count=1;count<n;count++) {
		for(int i=0;i<edges.length;i++) {
			int u=edges[i][0];
			int v=edges[i][1];
			int w=edges[i][2];
			if(dist[u] != Integer.MAX_VALUE && dist[u]+w<dist[v]) {
				dist[v]=dist[u]+w;
			}
		}
		}
		
		
		for(int i=0;i<edges.length;i++) {
			int u=edges[i][0];
			int v=edges[i][1];
			int w=edges[i][2];
			if(dist[u] !=Integer.MAX_VALUE && dist[u]+w<dist[v]) {
				return true;
			}
		
		}
		return false;
		
 	}
	public static void main(String[] args) {
		GraphImp imp = new GraphImp(6);
		imp.addEdge(0, 1);
		imp.addEdge(0, 2);
		imp.addEdge(0, 4);
		imp.addEdge(2, 3);
		imp.addEdge(3, 4);
		imp.addEdge(3, 5);
		imp.addEdge(5, 4);
		imp.addEdge(4, 1);
		imp.minDistance(0, 5);
		System.out.println(imp.dfsTraverse());
		System.out.println(imp.detectCycle());
	}

}
