package com.dsa.graph.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphImplementationRevision {
	int V;
	ArrayList<LinkedList<Integer>> adj;
	
	public GraphImplementationRevision(int v) {
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
	public void addDirectedEdge(int source, int dest) {
	    adj.get(source).add(dest);  // only one direction
	}

	
	public int minDistance(int source,int dest) {
		Queue<Integer> queue= new LinkedList<Integer>();
		boolean[] visited=new boolean[V];
		int[] dist= new int[V];
		int[] pred=new int[V];
		Arrays.fill(dist, -1);
		Arrays.fill(pred, -1);
		queue.add(source);
		visited[source]=true;
		dist[source]=0;
		pred[source]=source;
		while(!queue.isEmpty()) {
			Integer current = queue.poll();
			for(int negbour:adj.get(current)) {
				if(!visited[negbour]) {
					visited[negbour]=true;
					dist[negbour]=dist[current]+1;
					pred[negbour]=current;
					queue.add(negbour);
					  // If we reached destination, stop BFS
                    if (negbour == dest) {
                        System.out.println("Shortest Path: " + getPath(source, dest, pred));
                        return dist[negbour];
                    }
				}
			}
			System.out.println(Arrays.toString(pred));
		}
		
		return -1;
	}
	 // Reconstruct path from src -> dest
    public List<Integer> getPath(int source, int dest, int[] pred) {
        LinkedList<Integer> path = new LinkedList<>();
        for (int at = dest; at != -1; at = pred[at]) {
            path.addFirst(at);
            if (at == source) break;
        }
        return path;
    }
    
   
    
    public ArrayList<Integer> dfsOfGraph(){
    	ArrayList<Integer> ans= new ArrayList<Integer>();
    	boolean[] visited= new boolean[V];
    	for(int i=0;i<V;i++) {
    		if(!visited[i]) {
    			dfs(i,adj,ans,visited);
    		}
    	}
    	
		return ans;
    }
    
    public void dfs(int v,ArrayList<LinkedList<Integer>> adj,ArrayList<Integer> ans,boolean[] visited) {
    	visited[v]=true;
    	ans.add(v);
    	for(int negibour:adj.get(v)) {
    		if(!visited[negibour]) {
    			dfs(negibour,adj,ans,visited);
    		}
    	}
    }
    
    public boolean detectCycle() {
    	boolean[] visited= new boolean[V];
    	int parent=-1;
    	for(int i=0;i<V;i++) {
    		if(!visited[i]) {
    			 if(dfsOfCycle(i,adj,visited,parent)) {
    				 return true;
    			 }
    		}
    	}
		return false;
    }
	
	private boolean dfsOfCycle(int i, ArrayList<LinkedList<Integer>> adj, boolean[] visited, int parent) {
		visited[i]=true;
		
		for(int neg:adj.get(i)) {
			if(!visited[neg]) {
				if(dfsOfCycle(neg, adj, visited, i)) {
					return true;
				}
			}else if(parent!=neg) {
				return true;
			}
		}
		return false;
		
	}
	
	public boolean isCycledInDirectedGraph() {
		boolean[] visited= new boolean[V];
		boolean[] recS=new boolean[V];
		for(int i=0;i<V;i++) {
			if(dfsOfCycleDirected(i,adj,visited,recS)) {
				return true;
			}
		}
		return false;
	}

	private boolean dfsOfCycleDirected(int i, ArrayList<LinkedList<Integer>> adj2, boolean[] visited, boolean[] recS) {
		visited[i]=true;
		recS[i] =true;
		for(int neg:adj2.get(i)) {
			if(!visited[neg]) {
				if(dfsOfCycleDirected(neg, adj2, visited, recS)) {
					return true;
				}
			}else if(recS[neg]) {
				return true;
			}
		}
		recS[i]=false;
		return false;
	}
	
	public int[] topoSort() {
		boolean[] visited= new boolean[V];
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i=0;i<V;i++) {
			if(!visited[i]) {
				dfsTopoSort(i,visited,adj,stack);
			}
			
		}
		int[] ans = new int[V];
		int i=0;
		while(!stack.isEmpty()) {
			ans[i++]=stack.pop();
		}
		return ans;
	}
	

	private void dfsTopoSort(int i, boolean[] visited, ArrayList<LinkedList<Integer>> adj2, Stack<Integer> stack) {
		visited[i]=true;
		
		for(int neg:adj2.get(i)) {
			if(!visited[neg]) {
				dfsTopoSort(neg, visited, adj2, stack);
			}
		}
		stack.push(i);
	}

	public static void main(String[] args) {
		GraphImplementationRevision g = new GraphImplementationRevision(5);
		g.addDirectedEdge(0, 1);
		g.addDirectedEdge(2, 1);
		g.addDirectedEdge(2, 4);
		g.addDirectedEdge(1, 3);
		
		//System.out.println(g.minDistance(0, 5));
		//System.out.println(g.dfsOfGraph());
		//System.out.println(g.detectCycle());
		System.out.println(Arrays.toString(g.topoSort()));
	}

}
