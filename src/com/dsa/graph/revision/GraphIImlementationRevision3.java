package com.dsa.graph.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import com.dsa.graph.prims.algo.VertexWeightPair;

public class GraphIImlementationRevision3 {
	int V;
	ArrayList<ArrayList<Integer>> adj; 
	
	public GraphIImlementationRevision3(int v) {
		V=v;
		adj= new ArrayList<>();
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<>());
		}
	}
	
	public void addEdge(int source,int dest) {
		adj.get(source).add(dest);
		adj.get(dest).add(source);
	}
	
	public void addEdgeDirected(int source,int dest) {
		adj.get(source).add(dest);
	}
	
	public int minDistance(int source,int dest) {
		boolean[] visited = new boolean[V];
		int[] dist = new int[V];
		int[] pred=new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(pred, -1);
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(source);
		visited[source] =true;
		pred[source]=0;
		dist[source]=0;
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			//for(int i=0;i<adj.get(curr).size();i++) {
				//int neg = adj.get(curr).get(i);
			for(int neg:adj.get(curr)) {
				if(!visited[neg]) {
					visited[neg]=true;
					dist[neg]=dist[curr]+1;
					pred[neg]=curr;
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
	public LinkedList<Integer> getPath(int source,int dest,int[] pred){
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(int at=dest;at!=-1;at=pred[at]) {
			list.add(at);
			if(at==source) {
				break;
			}
		}
		return list;
	}
	
	public ArrayList<Integer> dsfTraverse(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		boolean[] visited= new boolean[V];
		for(int i=0;i<V;i++) {
			if(!visited[i]) {
				dfs(i,adj,visited,list);
			}
		}
		return list;
	}
	
	private void dfs(int i, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> list) {
		visited[i]=true;
		list.add(i);
		
		for(int neg:adj.get(i)) {
			if(!visited[neg]) {
				dfs(neg, adj, visited, list);
			}
		}
		
	}
	
	public boolean detectCycle() {
		boolean[] visited= new boolean[V];
		int parent=-1;
		for(int i=0;i<V;i++) {
			if(!visited[i]) {
				if(dfsCycle(i,adj,visited,parent)){
					return true;
				}
			}
		}
		return false;
	}

	private boolean dfsCycle(int i, ArrayList<ArrayList<Integer>> adj, boolean[] visited, int parent) {
		visited[i]=true;
		
		for(int neg:adj.get(i)) {
			if(!visited[neg]) {
				if(dfsCycle(neg, adj, visited, neg)) {
					return true;
				}
			}else if(parent!=neg) {
				return true;
			}
		}
		return false;
	}
	
	public boolean detectCycleDirected() {
		boolean[] visited= new boolean[V];
		boolean[] recS= new boolean[V];
		for(int i=0;i<V;i++) {
			if(!visited[i]) {
				if(dfsCycleDirected(i,adj,visited,recS)){
					return true;
				}
			}
		}
		return false;
	}

	private boolean dfsCycleDirected(int i, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] recS) {
		visited[i]=true;
		recS[i]=true;
		for(int neg:adj.get(i)) {
			if(!visited[neg]) {
				if(dfsCycleDirected(neg, adj, visited,recS)) {
					return true;
				}
			}else if(recS[neg]) {
				return true;
			}
		}
		recS[i]=false;
		return false;
	}
	
	public LinkedList<Integer> topoSort(){
		boolean[] visited= new boolean[V];
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0;i<V;i++) {
			if(!visited[i]) {
				dfsTopoSort(i,adj,visited,stack);
			}
		}
		LinkedList<Integer> list = new LinkedList<Integer>();
		int i=0;
		while(!stack.isEmpty()) {
			list.add(stack.pop());
		}
		return list;
	}

	private void dfsTopoSort(int i, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
		visited[i]=true;
		
		for(int neg:adj.get(i)) {
			if(!visited[neg]) {
				dfsTopoSort(neg, adj, visited, stack);
			}
		}
		stack.push(i);
	}
	
	public ArrayList<Integer> topoSortKohnsAl(){
		int[] inDeg= new int[V];
		
		for(ArrayList<Integer> list:adj) {
			for(int e:list) {
				inDeg[e]++;
			}
		}
		ArrayList<Integer> ansList = new ArrayList<Integer>();
		 bfs(adj,V,0,ansList,inDeg);
		return ansList;
	}

	private void bfs(ArrayList<ArrayList<Integer>> adj, int V, int v, ArrayList<Integer> ansList, int[] inDeg) {
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i=0;i<V;i++) {
			if(inDeg[i]==0) {
				queue.add(i);
			}
		}
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			ansList.add(curr);
			for(int neg:adj.get(curr)) {
				if(--inDeg[neg]==0) {
					queue.add(neg);
				}
			}
		}
		
	}
	

	public static void main(String[] args) {
		GraphIImlementationRevision3 impl = new GraphIImlementationRevision3(6);
		impl.addEdge(0, 1);
		impl.addEdge(0, 2);
		impl.addEdge(0, 4);
		impl.addEdge(2, 3);
		impl.addEdge(5, 4);
		impl.addEdge(5, 3);
		impl.addEdge(2, 4);
		impl.addEdge(1, 4);
		System.out.println(impl.minDistance(0, 5));
		System.out.println(impl.dsfTraverse());
		System.out.println(impl.detectCycle());
		
	}

}
