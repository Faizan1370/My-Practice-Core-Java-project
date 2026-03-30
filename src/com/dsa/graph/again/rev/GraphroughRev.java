package com.dsa.graph.again.rev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class GraphroughRev {
	public static boolean isBipartite(int V,int[][]  edges) {
		int[] color = new int[V];
		Arrays.fill(color, -1);
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<Integer>());
		}
		for(int[] edge:edges) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}
		for(int i=0;i<V;i++) {
			if(color[i]==-1) {
				if(!dfsBipertite(i,edges,color,adj)){
					return false;
				}
			}
		}
		return true;
		
	}

	private static boolean dfsBipertite(int v, int[][] edges, int[] color,ArrayList<ArrayList<Integer>> adj) {
		color[v]=1;
		
		for(int neg:adj.get(v)) {
			if(color[neg]==-1) {
				color[neg] = 1 - color[v];   // opposite color
				if(!dfsBipertite(neg, edges, color, adj)) {
					return false;
				}
			}else if(color[v]==color[neg]) {
				return false;
			}
		}
		return true;
		
		
	}
	public static boolean isBip(int V,int[][]  edges) {
		int[] color = new int[V];
		Arrays.fill(color, -1);
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<Integer>());
		}
		for(int[] edge:edges) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}
		for(int i=0;i<V;i++) {
			if(color[i]==-1) {
				if(!bdfUtilBip(i,edges,color,adj)){
					return false;
				}
			}
		}
		return true;
	}

	private static boolean bdfUtilBip(int v, int[][] edges, int[] color, ArrayList<ArrayList<Integer>> adj) {
		Queue<Integer> queue = new LinkedList<Integer>();
		color[v]=1;
		queue.add(v);
		
		while(!queue.isEmpty()) {
			int u=queue.poll();
			for(int neg:adj.get(u)) {
				if(color[neg]==-1) {
					color[neg]=1-color[u];
					queue.add(neg);
				}else if(color[u]==color[neg]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static int countIsland(char[][] grid) {
		int m=grid.length;
		int n=grid[0].length;
		int count=0;
		
		boolean[][] visited = new boolean[m][n];
		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(grid[i][j]=='L' && !visited[i][j]) {
					dfsIslandCount(grid,i,j,visited);
					count++;
				}
			}
		}
		return count;
				
	
	}

	private static void dfsIslandCount(char[][] grid, int r, int c, boolean[][] visited) {
		visited[r][c]=true;
		
		  int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	      int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	      
	      for(int i=0;i<8;i++) {
	    	  int nr=dr[i]+r;
	    	  int nc=dc[i]+c;
	    	  
	    	  if(isSafe(grid,nr,nc,visited)) {
	    		  dfsIslandCount(grid, nr, nc, visited);
	    	  }
	      }
		
	}

	private static boolean isSafe(char[][] grid, int r, int c, boolean[][] visited) {
		int m=grid.length;
		int n=grid[0].length;
		return (r >=0 && r<m && c>=0 && c<n && !visited[r][c] && grid[r][c]=='L');
	}
	
	public static int getMinDiceThrows(int[] move) {
		int n=move.length;
		boolean[] visited = new boolean[n];
		Queue<int[]> q= new LinkedList<int[]>();
		q.add(new int[] {0,0});
		visited[0] = true;
		
		while(!q.isEmpty()){
			int[] pair = q.poll();
			int v =pair[0];
			int dist=pair[1];
			
			if(v==n-1) {
				return dist;
			}
			for(int dice=1;dice<=6 && v+dice<n; dice++) {
				int next = v + dice;
				int dest =(move[next] !=-1)?move[next]:next;
				visited[next]=true;
				visited[dest]=true;
				q.add(new int[] {dest,dist+1});
			}
		}
		return -1;
	}
	static int[][] floodFill(int[][] img, int sr, int sc, int newColor) {
		if(img[sr][sc]==newColor) {
			return img;
		}
		int oldColor=img[sr][sc];
		dfsFill(img,sr,sc,oldColor,newColor);
		return img;
	}
	private static void dfsFill(int[][] img, int x, int y, int oldColor, int newColor) {
		if(x<0 || x>=img.length || y<0 || y>=img[0].length || img[x][y]!=oldColor) {
			return;
		}
		img[x][y]=newColor;
		dfsFill(img, x+1, y, oldColor, newColor);
		dfsFill(img, x-1, y, oldColor, newColor);
		dfsFill(img, x, y+1, oldColor, newColor);
		dfsFill(img, x, y-1, oldColor, newColor);
		
	}
	
	 static boolean isCyclic(ArrayList<ArrayList<Integer>> adj) {
		 int V=adj.size();
		 int[] inDeg= new int[V];
		 Queue<Integer> queue = new LinkedList<Integer>();
		 for(int u=0;u<V;u++) {
			 for(int v:adj.get(u)) {
				 inDeg[v]++;
			 }
		 }
		 for(int u=0;u<V;u++) {
			 if(inDeg[u]==0) {
				 queue.add(u);
			 }
		 }
		 int visited =0;
		 while(!queue.isEmpty()) {
			 Integer curr = queue.poll();
			 visited++;
			 for(int neg:adj.get(curr)) {
				 if(--inDeg[neg]==0) {
					 queue.add(neg);
				 }
			 }
		 }
		return visited !=V;
	 }
	 static boolean isCyclicDfs(ArrayList<ArrayList<Integer>> adj) {
		 int V=adj.size();
		 boolean[] visited = new boolean[V];
		 boolean[] recStack = new boolean[V];
		 
		 for(int i=0;i<V;i++) {
			 if(!visited[i]) {
				 if(dfsCycle(i,adj,visited,recStack)) {
					 return true;
				 }
			 }
		 }
		return false;
		 
	 }

	private static boolean dfsCycle(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] recStack) {
		visited[v]=true;
		recStack[v]=true;
		
		for(int neg:adj.get(v)) {
			if(!visited[neg]) {
				if(dfsCycle(neg, adj, visited, recStack)) {
					return true;
				}
			}else if(recStack[neg]) {
				return true;
			}
		}
		recStack[v]=false;
		return false;
	}
	static int[] bellmanFord(int V, int[][] edges, int src) {
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src]=0;
		for(int i=0;i<V-1;i++) {
			for(int[] edge:edges) {
				int u=edge[0];
				int v=edge[1];
				int wt=edge[2];
				
				if(dist[u]!=Integer.MAX_VALUE && dist[u]+wt<dist[v]) {
					dist[v]=dist[u]+wt;
				}
			}
		}
		for(int[] edge:edges) {
			int u=edge[0];
			int v=edge[1];
			int wt=edge[2];
			
			if(dist[u]!=Integer.MAX_VALUE && dist[u]+wt<dist[v]) {
				return new int[-1];
			}
		}
		return dist;
	}
	static int[] dijkstra(ArrayList<ArrayList<int[]>> adj, int src) {
		int V=adj.size();
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a,b)->a[0]-b[0]);
		dist[src]=0;
		queue.add(new int[] {0,src});
		
		while(!queue.isEmpty()) {
			int[] pair = queue.poll();
			int u=pair[1];
			int wt=pair[0];
			if(wt>dist[u]) {
				continue;
			}
			for(int[] edge:adj.get(u)) {
				int v=edge[0];
				int w=edge[1];
				
				if(dist[u]+w<dist[v]) {
					dist[v]=dist[u]+w;
					queue.offer(new int[] {dist[v],v});
				}
			}
			
		}
		return dist;
	}
	static int[] dijkstra1(ArrayList<ArrayList<int[]>> adj, int src) {
		int V=adj.size();
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<VWg> q = new PriorityQueue<VWg>();
		q.offer(new VWg(src, 0));
		dist[src]=0;
		
		while(!q.isEmpty()) {
			VWg pair = q.poll();
			int u=pair.v;
			int wt =pair.w;
			
			if(wt>dist[u]) {
				continue;
			}
			for(int[] ed:adj.get(u)) {
				int v=ed[0];
				int w=ed[1];
				
				if(dist[u]+w<dist[v]) {
					dist[v]=dist[u]+w;
					q.offer(new VWg(v,dist[v]));
				}
			}
			
		}
		return dist;
		
	}
	public static int spanningTreePrims(int V, ArrayList<ArrayList<VWg>> adj) {
		boolean[] visited = new boolean[V];
		PriorityQueue<VWg> q= new PriorityQueue<VWg>();
		int mst=0;
		q.add(new VWg(0, 0));
		
		while(!q.isEmpty()) {
			VWg pair = q.poll();
			int u=pair.v;
			int wt = pair.w;
			if(visited[u]) {
				continue;
			}
			visited[u]=true;
			mst +=wt;
			for(VWg ed:adj.get(u)) {
				if(!visited[ed.v]) {
					q.offer(new VWg(ed.v, ed.w));
				}
			}
		}
		return mst;
	}
	 public static int kruskalsMST(int V, int[][] edges) {
		Arrays.sort(edges,Comparator.comparingInt(e->e[2]));
		int cost=0,count=0;
		Dsuni dsuni = new Dsuni(V);
		
		for(int[] edge:edges) {
			int u=edge[0],v=edge[1],w=edge[2];
			
			if(dsuni.find(u)!=dsuni.find(v)) {
				dsuni.union(u, v);
				cost +=w;
				count++;
				if(count==V-1) {
					break;
				}
			}
		}
		return cost;
		 
	 }

	public static void main(String[] args) {
		 int n = 30;
	        int[] moves = new int[n];
	        Arrays.fill(moves, -1);

	        // Ladders
	        moves[2] = 21;
	        moves[4] = 7;
	        moves[10] = 25;
	        moves[19] = 28;

	        // Snakes
	        moves[26] = 0;
	        moves[20] = 8;
	        moves[16] = 3;
	        moves[18] = 6;

	        System.out.println(getMinDiceThrows(moves));
	}

}
