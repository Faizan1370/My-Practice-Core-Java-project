package com.dsa.graph.interview.question.geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphImple2 {
	
	 public int maxBPM(boolean bpGraph[][],int N,int M) {
		int[] matchR = new int[N];
		Arrays.fill(matchR, -1);
		int result=0;
		for(int u=0;u<M;u++) {
			 boolean seen[] =new boolean[N] ;
			 
			 if(bpm(bpGraph,u,seen,matchR,N,M)) {
				 result++;
			 }
		}
		return result;
	}

	private boolean bpm(boolean[][] bpGraph, int u, boolean[] seen, int[] matchR,int N,int M) {
		for(int v=0;v<N;v++) {
			if(bpGraph[u][v] && !seen[v]) {
				seen[v]=true;
				
				if (matchR[v] < 0 || bpm(bpGraph, matchR[v], seen, matchR, N, M)) {
					matchR[v]=u;
					return true;
				}
			}
			 
		}
		return false;
	}
	static boolean checkPath(ArrayList<ArrayList<Integer>> adj, int u, int v) {
		boolean[] visited = new boolean[adj.size()];
		return dfsCheckPath(adj,u,v,visited);
	}
	
	private static boolean dfsCheckPath(ArrayList<ArrayList<Integer>> adj, int u, int v, boolean[] visited) {
		if(visited[u]) {
			return false;
		}
		if(u==v) {
			return true;
		}
		visited[u]=true;
		for(int neg:adj.get(u)) {
			if(!visited[neg] && dfsCheckPath(adj, neg, v, visited)) {
				return true;
			}
		}
		return false;
	}

	  // Function to add undirected edge
    static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    static boolean checkPathBFS(ArrayList<ArrayList<Integer>> adj, int u, int v) { 
    	boolean[] visited = new boolean[adj.size()];
    	Queue<Integer> queue = new LinkedList<Integer>();
    	queue.add(u);
    	visited[u]=true;
    	
    	while(!queue.isEmpty()) {
    		int current = queue.poll();
    		if(current==v) {
    			return true;
    		}
    		for(int neg:adj.get(current)) {
    			if(!visited[neg]) {
    				visited[neg]=true;
    				queue.add(neg);
    			}
    		}
    	}
		return false;
    			
    }
   public static int countPaths(int n, int[][] edgeList,
            int source, int destination) {
         ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
         for(int i=0;i<=n;i++) {
        	 adj.add(new ArrayList<Integer>());
         }
         for(int[] edge:edgeList) {
        	 adj.get(edge[0]).add(edge[1]);
         }
         int[] count = new int[1];
         boolean[] visited= new boolean[n+1];
         dfsOfPath(source,destination,adj,visited,count);
         
         return count[0];
    }
    

    private static void dfsOfPath(int source, int destination, ArrayList<ArrayList<Integer>> adj, boolean[] visited,
		int[] count) {
    	if(source==destination) {
    		count[0]++;
    		return;
    	}
    	visited[source]=true;
    	for(int neg:adj.get(source)) {
    		if(!visited[neg]) {
    			dfsOfPath(neg, destination, adj, visited, count);
    		}
    	}
    	visited[source]=false;
	
}
    
    public static int countPathsTopoSort(int n, int[][] edgeList,
            int source, int destination) {
         ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
         for(int i=0;i<=n;i++) {
        	 adj.add(new ArrayList<Integer>());
         }
         for(int[] edge:edgeList) {
        	 adj.get(edge[0]).add(edge[1]);
         }
         boolean[] visited= new boolean[n+1];
         Stack<Integer> stack = new Stack<Integer>();
         for(int i=0;i<=n;i++) {
        	 if(!visited[i]) {
        		 dfsOfTopoCountPaths(i,visited,stack,adj);
        	 }
         }
      // Step 3: Initialize paths array
         int[] paths = new int[n + 1];
         paths[source] = 1; // One way to be at the source
         
        while(!stack.isEmpty()) {
        	int node=stack.pop();
        	for(int neg:adj.get(node)) {
        		paths[neg]=paths[neg]+paths[node];
        	}
        }
        
        return paths[destination];
         
    }

	private static void dfsOfTopoCountPaths(int i, boolean[] visited, Stack<Integer> stack, ArrayList<ArrayList<Integer>> adj ) {
		visited[i]=true;
		for(int neg:adj.get(i)) {
			if(!visited[neg]) {
				dfsOfTopoCountPaths(neg, visited, stack, adj);
			}
		}
		stack.add(i);
	}
	public static int xShape(char[][] grid) {
		int m=grid.length;
		int n=grid[0].length;
		int ans=0;
		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(grid[i][j]=='X') {
					ans++;
					dfsOfXShape(grid,i,j);
				}
			}
		}
		for(char[] ch :grid) {
			System.out.println(Arrays.toString(ch));
		}
		return ans;
	}

	private static void dfsOfXShape(char[][] grid, int x, int y) {
		if(x<0 || y<0 || x>=grid.length || y>=grid[x].length ) {
			return;
		}
		if(grid[x][y]=='X') {
			grid[x][y]=0;
			dfsOfXShape(grid, x+1, y);
			dfsOfXShape(grid, x, y+1);
			dfsOfXShape(grid, x-1, y);
			dfsOfXShape(grid, x, y-1);
			
		}
	}

	public static void main(String[] args) {
		char[][] grid = { { 'X', 'O', 'X' },
                { 'O', 'X', 'O' },
                { 'X', 'X', 'X' } };

// Function call
System.out.println(xShape(grid));
    }
}
