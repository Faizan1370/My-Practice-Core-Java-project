package com.dsa.graph.interview.question.geeksforgeeks.revision2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class GraphIml {

	
	  public static void addEdge(ArrayList<ArrayList<Integer>> adj, int source,int
	  dest) { adj.get(source).add(dest); adj.get(dest).add(source); }
	 

	private static ArrayList<Integer> bfs(int V, ArrayList<ArrayList<Integer>> adj) {
		boolean[] visited = new boolean[V];
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				bfsTraverse(i, visited, adj, list);
			}
		}
		return list;

	}

	private static void bfsTraverse(int v, boolean[] visited, ArrayList<ArrayList<Integer>> adj,
			ArrayList<Integer> list) {
		visited[v] = true;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(v);

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			list.add(cur);

			for (int neg : adj.get(cur)) {
				if (!visited[neg]) {
					visited[neg] = true;
					queue.add(neg);
				}
			}
		}

	}

	public static ArrayList<Integer> dfs(int V, ArrayList<ArrayList<Integer>> adj) {
		boolean[] visited = new boolean[V];
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				dfsTraverse(i, visited, adj, list);
			}
		}
		return list;
	}

	private static void dfsTraverse(int v, boolean[] visited, ArrayList<ArrayList<Integer>> adj,
			ArrayList<Integer> list) {
		visited[v] = true;
		list.add(v);

		for (int neg : adj.get(v)) {
			if (!visited[neg]) {
				dfsTraverse(neg, visited, adj, list);
			}
		}

	}

	public static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
		boolean[] visited = new boolean[V];
		int parent = -1;
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				if (dfsCycle(i, visited, adj, parent)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean dfsCycle(int v, boolean[] visited, ArrayList<ArrayList<Integer>> adj, int parent) {
		visited[v] = true;

		for (int neg : adj.get(v)) {
			if (!visited[neg]) {
				if (dfsCycle(neg, visited, adj, v)) {
					return true;
				}
			} else if (parent != neg) {
				return true;
			}
		}

		return false;
	}

	public static int minDistace(int V, ArrayList<ArrayList<Integer>> adj, int src, int dest) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(src);
		boolean[] visited = new boolean[V];
		int[] dist = new int[V];
		visited[src] = true;
		dist[src] = 0;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			if (current == dest) {
				return dist[dest];
			}
			for (int neg : adj.get(current)) {
				if (!visited[neg]) {
					queue.add(neg);
					dist[neg] = dist[current] + 1;
					visited[neg] = true;
				}
			}
		}
		return -1;
	}

	public static int spanningTree(int V, int edges[][]) {
		ArrayList<ArrayList<PairNodeW>> adj = new ArrayList<ArrayList<PairNodeW>>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<PairNodeW>());
		}
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			int w = edge[2];
			adj.get(u).add(new PairNodeW(v, w));
			adj.get(v).add(new PairNodeW(u, w));
		}
		PriorityQueue<PairNodeW> queue = new PriorityQueue<PairNodeW>();
		queue.add(new PairNodeW(0, 0));
		int mst = 0;
		boolean[] visited = new boolean[V];

		while (!queue.isEmpty()) {
			PairNodeW pair = queue.poll();
			int vertex = pair.v;
			int weight = pair.w;

			if (visited[vertex]) {
				continue;
			}
			visited[vertex] = true;
			mst += weight;

			for (PairNodeW nodeW : adj.get(vertex)) {
				if (!visited[nodeW.v]) {
					queue.add(new PairNodeW(nodeW.v, nodeW.w));
				}
			}
		}
		return mst;
	}

	public static int[] shortestPath(int V, int edges[][]) {
		ArrayList<ArrayList<PairNodeW>> adj = new ArrayList<ArrayList<PairNodeW>>();
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<PairNodeW>());
		}
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			int w = edge[2];
			adj.get(u).add(new PairNodeW(v, w));
			adj.get(v).add(new PairNodeW(u, w));
		}
		PriorityQueue<PairNodeW> queue = new PriorityQueue<PairNodeW>();
		queue.add(new PairNodeW(0, 0));
		boolean[] visited = new boolean[V];
		dist[0] = 0;

		while (!queue.isEmpty()) {
			PairNodeW pair = queue.poll();
			int vertex = pair.v;

			if (visited[vertex]) {
				continue;
			}
			visited[vertex] = true;
			for (PairNodeW nodeW : adj.get(vertex)) {
				int v = nodeW.v;
				int w = nodeW.w;
				if (!visited[nodeW.v]) {
					if (dist[vertex] + w < dist[v]) {
						dist[v] = dist[vertex] + w;
						queue.add(new PairNodeW(v, dist[v]));
					}
				}
			}
		}
		return dist;
	}

	public static int noOfIsland(char[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		boolean[][] visited = new boolean[m][n];
		int count = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 'L' && !visited[i][j]) {
					dfsIsland(grid, i, j, visited);
					count++;
				}
			}
		}
		return count;
	}

	private static void dfsIsland(char[][] grid, int r, int c, boolean[][] visited) {
		visited[r][c] = true;

		// All 8 possible directions (vertical, horizontal, diagonal)
		int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

		for (int k = 0; k < 8; k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];
			if (isSafe(nr, nc, visited, grid)) {
				dfsIsland(grid, nr, nc, visited);
			}
		}

	}

	private static boolean isSafe(int r, int c, boolean[][] visited, char[][] grid) {
		int n = grid.length;
		int m = grid[0].length;

		// Cell is within bounds, contains land ('L'), and is not yet visited
		return (r >= 0 && r < n && c >= 0 && c < m && grid[r][c] == 'L' && !visited[r][c]);
	}
	
	public static int noOfIsland1(char[][] grid) {
	    int count = 0;

	    for (int i = 0; i < grid.length; i++) {
	        for (int j = 0; j < grid[0].length; j++) {
	            if (grid[i][j] == 'L') {
	                dfsIsland1(grid, i, j);
	                count++;
	            }
	        }
	    }
	    return count;
	}
	
	private static void dfsIsland1(char[][] grid, int r, int c) {
	    int m = grid.length;
	    int n = grid[0].length;

	    if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] != 'L') {
	        return;
	    }

	    // Mark visited
	    grid[r][c] = 'O';

	    int[] dr = {-1,-1,-1,0,0,1,1,1};
	    int[] dc = {-1,0,1,-1,1,-1,0,1};

	    for (int k = 0; k < 8; k++) {
	        dfsIsland1(grid, r + dr[k], c + dc[k]);
	    }
	}
	
	
	
	public static boolean isBipartite(int V,int[][]  edges) {
		int[] color = new int[V];
		Arrays.fill(color, -1);
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<Integer>());
		}
		for(int[] edge:edges) {
			int src =edge[0];
		    int dest =edge[1];
			addEdge(adj, src, dest);
		}
		for(int i=0;i<V;i++) {
			if (color[i] == -1) {
				if (!checkBipertite(i, adj, color)) {
					return false;
				}
			}
		}
		return true;
		
	}



	private static boolean checkBipertite(int v, ArrayList<ArrayList<Integer>> adj, int[] color) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(v);
		color[v]=0;
		
		while(!queue.isEmpty()) {
			int current =queue.poll();
			for(int neg:adj.get(current)) {
				if(color[neg]==-1) {
					color[neg]=1-color[current];
					queue.add(neg);
				}else if(color[neg]==color[current]) {
					return false;
				}
			}
		}
		return true;
	}
	public static boolean bipetiteCheck(int V,int[][] grid) {
		int[] color = new int[V];
		Arrays.fill(color, -1);
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<Integer>());
		}
		for(int[] edge:grid) {
			int src =edge[0];
		    int dest =edge[1];
			addEdge(adj, src, dest);
		}
		for(int i=0;i<V;i++) {
			if (color[i] == -1) {
			    if (!dfsCheck(i, 0, adj, color)) return false;
			}	
		}
		return true;
		

	}
	
	private static boolean dfsCheck(int node, int c,
	        ArrayList<ArrayList<Integer>> adj, int[] color) {

	    color[node] = c;

	    for (int neigh : adj.get(node)) {
	        if (color[neigh] == -1) {
	            if (!dfsCheck(neigh, 1 - c, adj, color))
	                return false;
	        } else if (color[neigh] == color[node]) {
	            return false;
	        }
	    }
	    return true;
	}
	 public static int[][] floodFill(int[][] img, int sr, int sc, int newColor) {
		 if(img[sr][sc]==newColor) {
			 return img;
		 }
		 int oldColor=img[sr][sc];
		 dfsFloodFill(img,sr,sc,oldColor,newColor);
		return img;
	 }



	private static void dfsFloodFill(int[][] img, int x, int y, int oldColor, int newColor) {
		
		if(x <0 || y <0 || x>=img.length || y>=img[0].length || img[x][y] !=oldColor) {
			return;
		}
		img[x][y]=newColor;
		
		dfsFloodFill(img, x+1, y, oldColor, newColor);
		dfsFloodFill(img, x-1, y, oldColor, newColor);
		dfsFloodFill(img, x, y+1, oldColor, newColor);
		dfsFloodFill(img, x, y-1, oldColor, newColor);
		
	}
	 public static boolean isCyclicDirectedGraph(ArrayList<ArrayList<Integer>> adj) {
		 int V=adj.size();
		 boolean[] visited = new boolean[V];
		 boolean[] recStack = new boolean[V];
		 
		 for(int i=0;i<V;i++) {
			 if(!visited[i]) {
				if(dfsCycleDirected(i,adj,visited,recStack)){
					return true;
				}
			 }
		 }
		return false;
	 }


	private static boolean dfsCycleDirected(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited,
			boolean[] recStack) {
		visited[v]=true;
		recStack[v]=true;
		
		for(int neg:adj.get(v)) {
			if(!visited[neg]) {
				if(dfsCycleDirected(neg, adj, visited, recStack)) {
					return true;
				}
			}else if(recStack[neg]) {
				return true;
			}
		}
		recStack[v]=false;
		return false;
	}
	public static ArrayList<Integer> topoSort(ArrayList<ArrayList<Integer>> adj) {
		int V=adj.size();
		//Stack<Integer> stack = new Stack<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		boolean[] visited = new boolean[V];
		
		for(int i=0;i<V;i++) {
			if(!visited[i]) {
				//dfsTopoUtil(i,adj,visited,stack);
				dfsTopoUtil(i,adj,visited,list);
			}
		}
		
	
		
		return list;
		
	}


	private static void dfsTopoUtil(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited,ArrayList<Integer> list) {
	  visited[v]=true;
	  
	  for(int neg :adj.get(v)) {
		  if(!visited[neg]) {
			  dfsTopoUtil(neg, adj, visited,list);
		  }
	  }
	 // stack.add(v);
	  list.add(0, v); // add to front also can use linked lits which provide to add first and last
		
	}

	public static int[] bellmanFord(int V, int[][] edges, int src) {
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src]=0;
		
		for(int count =0;count<V;count++) {
			for(int[] edge:edges) {
				int u=edge[0],v=edge[1],w=edge[2];
				if(dist[u] !=Integer.MAX_VALUE && dist[u]+w <dist[v]) {
					 // If this is the Vth relaxation, then there is
                    // a negative cycle
                    if (count == V - 1)
                        return new int[]{-1};
					dist[v]=dist[u]+w;
				}
			}
		}
		return dist;
	}
	public static boolean isCircle(String[] words) {
		int[] in= new int[26];
		int[] out = new int[26];
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 26; i++) {
            adj.add(new ArrayList<>());
        }
		for(String word:words) {
			int first =word.charAt(0)-'a';
			int last =word.charAt(word.length()-1)-'a';
			out[first]++;
			in[last]++;
			adj.get(first).add(last);
			if(!list.contains(first)) {
				list.add(first);
			}
			if(!list.contains(last)) {
				list.add(last);
			}
		}
		for(int c:list) {
			if(in[c]!=out[c])
				return false;
		}
		int start= list.get(0);
		   boolean[] visited = new boolean[26];
		dfsCircle(start,adj,visited);
		 for (int c : list) {
	            if (!visited[c]) return false;
	        }
		 return true;
	}

	private static void dfsCircle(int ch, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
		visited[ch]=true;
		for(int neg:adj.get(ch)) {
			if(!visited[neg]) {
				dfsCircle(neg, adj, visited);
			}
		}
		
	}
	 public static boolean checkPath(ArrayList<ArrayList<Integer>> adj, int u, int v) {
		 int n=adj.size();
		 boolean[] visited = new boolean[n];
			 if(dfsCheckPath(adj,u,v,visited)) {
				 return true;
		 }
			return false;
	 }


	private static boolean dfsCheckPath(ArrayList<ArrayList<Integer>> adj, int src, int dest,boolean[] visited) {
		
		if(src==dest) {
			return true;
		}
		visited[src]=true;
		
		for(int neg:adj.get(src)) {
			if(!visited[neg]) {
				if(dfsCheckPath(adj, neg, dest, visited)){
					return true;
				}
			}
		}
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public static int countPaths(int n, int[][] edgeList,
             int source, int destination) {
		int[] count= {0};
		boolean[] visited = new boolean[n+1];
		// Create adjacency list(1 - based indexing)
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edgeList) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
        }
        dfsCountPath(source,destination,graph,count,visited);
		return count[0];
		
	}


	private static void dfsCountPath(int source, int destination, List<List<Integer>> graph, int[] count,
			boolean[] visited) {
		visited[source]=true;
		if(source==destination) {
			count[0]++;
		}
		
		for(int neg:graph.get(source)) {
			if(!visited[neg]) {
				dfsCountPath(neg, destination, graph, count, visited);
			}
		}
		visited[source]=false;
		
	}
	
    static boolean bfs(int src, int dest, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[adj.size()];
        Queue<Integer> q = new LinkedList<>();

        visited[src] = true;
        q.offer(src);

        while (!q.isEmpty()) {
            // remove the front vertex
            int curr = q.poll();

            // destination reached
            if (curr == dest) return true;

            // visit all unvisited neighbors
            for (int neighbor : adj.get(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.offer(neighbor);
                }
            }
        }

        // no path found
        return false;
    }

    // Function to check if a path exists between u and v
    static boolean checkPathbfs(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        return bfs(u, v, adj);
    }


	public static void main(String[] args) {
		 int n = 5;

	        // Edge list: [u, v] represents u -> v
	        int[][] edgeList = {
	            {1, 2}, {1, 3}, {1, 5},
	            {2, 5}, {2, 4}, {3, 5}, {4, 3}
	        };

	        int source = 1;
	        int destination = 5;

	        System.out.println(countPaths(n, edgeList, source, destination));

		


	}


}
