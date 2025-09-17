package com.dsa.graph.revision;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphImplRevision2 {
	
	ArrayList<LinkedList<Integer>> adj;
	int V;
	
	public GraphImplRevision2(int v) {
		V=v;
		adj= new ArrayList<>();
		for(int i=0;i<V;i++) {
			adj.add(new LinkedList<Integer>());
		}
	}
	public void addEdge(int source,int dest) {
		adj.get(source).add(dest);
		adj.get(dest).add(source);
	}
	public void addDirectedEdge(int source,int dest) {
		adj.get(source).add(dest);
	}
	
	public boolean findMinDist(int src, int dest) {
		boolean[] visited = new boolean[V];
		Queue<Integer> queue = new LinkedList<Integer>();
		int[] dist =new int[V];
		int[] pred= new int[V];
		Arrays.fill(visited, false);
		Arrays.fill(dist, -1);
		Arrays.fill(pred, -1);
		queue.add(src);
		visited[src]=true;
		dist[src]=0;
		pred[src]=src;
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			
			for(int neg:adj.get(curr)) {
				if(!visited[neg]) {
					visited[neg]=true;
					dist[neg]=dist[curr]+1;
					pred[neg]=curr;
					if(neg==dest) {
						System.out.println(getPath(src, dest, pred));
						System.out.println(getDistance(src, dest, pred));
						return true;
					}
				}
				
			}
		}
		return false;
		
	}
	
	public LinkedList<Integer> getPath(int src,int dest,int[] pred){
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(int at=dest; at!=-1;at=pred[at]) {
			list.add(at);
			if(at==src) {
				break;
			}
		}
		return list;
	}
	public int getDistance(int source, int dest, int[] pred) {
	    int distance = 0;
	    for (int at = dest; at != -1; at = pred[at]) {
	        if (at == source) {
	            break;
	        }
	        distance++;
	    }
	    return distance;
	} 
	public ArrayList<Integer> dfsOfGraph(){
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	boolean[] visited= new boolean[V];
    	for(int i=0;i<V;i++) {
    		if(!visited[i]) {
    			dfs(i,adj,visited,list);
    		}
    	}
		return list;
    }

	
	private void dfs(int ver, ArrayList<LinkedList<Integer>> adj, boolean[] visited, ArrayList<Integer> list) {
		visited[ver]=true;
		list.add(ver);
		
		for(int neg:adj.get(ver)) {
			if(!visited[neg]) {
				dfs(neg, adj, visited, list);
			}
		}
	}
	 public boolean detectCycle() {
		 boolean[] visited= new boolean[V];
		 int parent=-1;
		 for(int i=0;i<V;i++) {
			if( cycleDfs(i,adj,visited,parent)){
				return true;
			}
		 }
		return false;
	 }
	private boolean cycleDfs(int i, ArrayList<LinkedList<Integer>> adj, boolean[] visited, int parent) {
       		visited[i]=true;
       		
       		for(int neg:adj.get(i)) {
       			if(!visited[neg]) {
       				if(cycleDfs(neg, adj, visited, i)) {
       					return true;
       				}
       			}else if(parent!=neg) {
       				return true;
       			}
       		}
			return false;
	}
	
	public boolean detectCycleInDirected() {
		boolean[] visited= new boolean[V];
		boolean[] recStack= new boolean[V];
		
		for(int i=0;i<V;i++) {
		if(	directedDfsCycle(i,adj,visited,recStack)) {
			return true;
		}
		}
		return false;
	}
	private boolean directedDfsCycle(int i, ArrayList<LinkedList<Integer>> adj2, boolean[] visited, boolean[] recStack) {
		visited[i]=true;
		recStack[i]=true;
		
		for(int neg:adj.get(i)) {
			if(!visited[neg]) {
				if(directedDfsCycle(neg, adj2, visited, recStack)) {
					return true;
				}
			}else if(recStack[neg]) {
				return true;
			}
		}
		return false;
		
	}
	public int[] topoSort() {
		boolean[] visited= new boolean[V];
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0;i<V;i++) {
			if(!visited[i]) {
				dfsOfTopoSort(i,adj,visited,stack);
			}
		}
		int[] ans = new int[V];
		int i=0;
		while(!stack.isEmpty()) {
			ans[i++]=stack.pop();
		}
		return ans;
	}
	private void dfsOfTopoSort(int i, ArrayList<LinkedList<Integer>> adj2, boolean[] visited, Stack<Integer> stack) {
		visited[i]=true;
		
		for(int neg:adj2.get(i)) {
			if(!visited[neg]) {
				dfsOfTopoSort(neg, adj2, visited, stack);
			}
		}
		stack.push(i);
		
	}
	//Kahn's algorithn
	public int[] bfsTopoSort() {
		int[] inDeg= new int[V];
		
		for(LinkedList<Integer> list:adj) {
			for(int data:list) {
				inDeg[data]++;
			}
		}
		ArrayList<Integer> ans = new ArrayList<Integer>();
		bfs(adj,V,ans,inDeg);
		
		int[] result= new int[V];
		int i=0;
		for(int data:ans) {
			result[i]=data;
			i++;
		}
		return result;
		
	}
	
	
	private void bfs(ArrayList<LinkedList<Integer>> adj, int V, ArrayList<Integer> ans,
			int[] inDeg) {
		Queue<Integer> queue= new LinkedList<Integer>();
		
		for(int i=0;i<V;i++) {
			if(inDeg[i]==0) {
				queue.add(i);
			}
		}
		while(!queue.isEmpty()) {
			Integer current = queue.poll();
			ans.add(current);
			for(int neg:adj.get(current)) {
				if(--inDeg[neg]==0) {
					queue.add(neg);
				}
			}
			
		}
		
	}
	public static void main(String[] args) {
		GraphImplRevision2 graphImplRevision2 = new GraphImplRevision2(5);
		graphImplRevision2.addDirectedEdge(0, 1);
		graphImplRevision2.addDirectedEdge(2, 1);
		graphImplRevision2.addDirectedEdge(2, 4);
		graphImplRevision2.addDirectedEdge(1, 3);
		System.out.println(graphImplRevision2.detectCycleInDirected());
		System.out.println(Arrays.toString(graphImplRevision2.topoSort()));
		System.out.println(Arrays.toString(graphImplRevision2.bfsTopoSort()));
		
		
		
	}

}
