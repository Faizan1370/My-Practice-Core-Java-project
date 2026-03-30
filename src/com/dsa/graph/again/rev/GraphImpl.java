package com.dsa.graph.again.rev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class GraphImpl {

	static void bfsConnected(ArrayList<ArrayList<Integer>> adj, int src, boolean[] visited, ArrayList<Integer> res) {
		Queue<Integer> queue = new LinkedList<Integer>();
		visited[src] = true;
		queue.add(src);

		while (!queue.isEmpty()) {
			int current = queue.poll();
			res.add(current);
			for (int negh : adj.get(current)) {
				if (!visited[negh]) {
					visited[negh] = true;
					queue.add(negh);
				}
			}
		}
	}

	static ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		int V = adj.size();
		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				bfsConnected(adj, i, visited, res);
			}
		}
		return res;
	}

	public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		int V = adj.size();
		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				dfsConnected(adj, i, visited, res);
			}
		}
		return res;
	}

	private void dfsConnected(ArrayList<ArrayList<Integer>> adj, int v, boolean[] visited, ArrayList<Integer> res) {
		visited[v] = true;
		res.add(v);

		for (int negh : adj.get(v)) {
			if (!visited[negh]) {
				dfsConnected(adj, negh, visited, res);
			}
		}

	}

	public static int countIslands(char[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		boolean[][] visited = new boolean[m][n];
		int isLands = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 'L' && !visited[i][j]) {
					dfsIsland(grid, i, j, visited);
					isLands++;
				}
			}
		}
		return isLands;
	}

	private static void dfsIsland(char[][] grid, int r, int c, boolean[][] visited) {
		visited[r][c] = true;
		int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };
		for (int i = 0; i < 8; i++) {
			int nr = dr[i] + r;
			int nc = dc[i] + c;

			if (isSafe(grid, nr, nc, visited)) {
				dfsIsland(grid, nr, nc, visited);
			}
		}

	}

	static boolean isSafe(char[][] grid, int r, int c, boolean[][] visited) {
		int n = grid.length;
		int m = grid[0].length;

		// Cell is within bounds, contains land ('L'), and is not yet visited
		return (r >= 0 && r < n && c >= 0 && c < m && grid[r][c] == 'L' && !visited[r][c]);
	}

	public static boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
		int[] color = new int[V];
		Arrays.fill(color, -1);

		for (int i = 0; i < V; i++) {
			if (color[i] == -1) {
				color[i] = 0;
				if (!isBipertiteUtil(i, adj, color)) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isBipertiteUtil(int v, ArrayList<ArrayList<Integer>> adj, int[] color) {
		for (int neg : adj.get(v)) {
			if (color[neg] == -1) {
				color[neg] = 1 - color[v];
				if (!isBipertiteUtil(neg, adj, color)) {
					return false;
				}
			} else if (color[neg] == color[v]) {
				return false;
			}
		}
		return true;
	}

	public static boolean bipartiteIterative(int V, ArrayList<ArrayList<Integer>> adj) {
		int[] color = new int[V];
		Arrays.fill(color, -1); // Initialize with -1

		for (int i = 0; i < V; i++) {
			if (color[i] != -1) {
				continue;
			}

			Queue<Integer> queue = new LinkedList<>();
			queue.add(i);
			color[i] = 0; // Start with color 0

			while (!queue.isEmpty()) {
				int v = queue.poll();

				for (int neighbor : adj.get(v)) {
					if (color[neighbor] == -1) {
						color[neighbor] = 1 - color[v]; // Alternate colors 0/1
						queue.add(neighbor);
					} else if (color[neighbor] == color[v]) {
						return false; // Conflict found
					}
				}
			}
		}
		return true; // Correct return statement
	}

	public static int getMinDiceThrows(int[] moves) {
		int n = moves.length;
		boolean[] visited = new boolean[n];
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { 0, 0 });
		visited[0] = true;

		while (!queue.isEmpty()) {
			int[] pair = queue.poll();
			int v = pair[0];
			int dist = pair[1];
			if (v == n - 1) {
				return dist;
			}

			for (int dice = 1; dice <= 6 && v + dice < n; dice++) {
				int next = v + dice;
				int dest = (moves[next] != -1) ? moves[next] : next;

				if (!visited[dest]) { // Check DESTINATION, not intermediate
					visited[dest] = true;
					queue.add(new int[] { dest, dist + 1 }); // dist + 1, not dice + 1
				}
			}
		}
		return -1;

	}

	public int[][] fillColor(int[][] image, int sr, int sc, int newColor) {
		if (image[sr][sc] == newColor) {
			return image;
		}

		dfsFillColor(image, sr, sc, image[sr][sc], newColor);
		return image;
	}

	private void dfsFillColor(int[][] image, int x, int y, int oldColor, int newColor) {
		// Check boundaries and if pixel matches old color
		if (x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] != oldColor) {
			return;
		}
		image[x][y] = newColor;
		dfsFillColor(image, x + 1, y, oldColor, newColor);
		dfsFillColor(image, x - 1, y, oldColor, newColor);
		dfsFillColor(image, x, y + 1, oldColor, newColor);
		dfsFillColor(image, x, y - 1, oldColor, newColor);

	}

	public int[][] floodFillBFS(int[][] image, int sr, int sc, int newColor) {
		if (image[sr][sc] == newColor) {
			return image;
		}

		int oldColor = image[sr][sc];
		int rows = image.length;
		int cols = image[0].length;

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { sr, sc });
		image[sr][sc] = newColor;

		int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		while (!queue.isEmpty()) {
			int[] cell = queue.poll();
			int x = cell[0], y = cell[1];

			for (int[] dir : directions) {
				int nx = x + dir[0];
				int ny = y + dir[1];

				if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && image[nx][ny] == oldColor) {
					image[nx][ny] = newColor;
					queue.add(new int[] { nx, ny });
				}
			}
		}

		return image;
	}

	static boolean isCyclic(ArrayList<ArrayList<Integer>> adj) {
		int V = adj.size();
		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];

		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				if (dfsCycleDirect(i, adj, recStack, visited)) {
					return true;
				}
			}

		}
		return false;
	}

	private static boolean dfsCycleDirect(int v, ArrayList<ArrayList<Integer>> adj, boolean[] recStack,
			boolean[] visited) {
		visited[v] = true;
		recStack[v] = true;

		for (int neg : adj.get(v)) {
			if (!visited[neg]) {
				if (dfsCycleDirect(neg, adj, recStack, visited)) {
					return true;
				}
			} else if (recStack[neg]) {
				return true;
			}
		}
		recStack[v] = false;
		return false;
	}

	public static int mstKruskal(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {
		ArrayList<KrEdg> edges = new ArrayList<KrEdg>();
		boolean[][] added = new boolean[V][V]; // Track added edges

		for (int u = 0; u < V; u++) {
			for (ArrayList<Integer> neg : adj.get(u)) {
				int v = neg.get(0);
				int w = neg.get(1);
				// if(u<v) { // not correct
				if (u < v && !added[u][v]) {
					edges.add(new KrEdg(u, v, w));
					added[u][v] = true;
				}
				// Avoid adding duplicate edges
				// if(!added[u][v]) {// second approach
				// edges.add(new KrEdg(u, v, w));
				// added[u][v] = true;
				// added[v][u] = true; // Mark both directions
				// }
				// }
			}

		}
		Collections.sort(edges);
		DSUn dsUn = new DSUn(V);
		int mstWeight = 0, count = 0;

		for (KrEdg ed : edges) {
			if (dsUn.find(ed.u) != dsUn.find(ed.v)) {
				dsUn.union(ed.u, ed.v);
				mstWeight += ed.w;
				count++;
				if (count == V - 1) {
					break;
				}
			}
		}
		return mstWeight;
	}

	public static int spanningTreePrims(int V, ArrayList<ArrayList<VWg>> adj) {
		PriorityQueue<VWg> queue = new PriorityQueue<VWg>();
		boolean[] visited = new boolean[V];
		queue.add(new VWg(0, 0));
		int mst = 0;

		while (!queue.isEmpty()) {
			VWg pair = queue.poll();
			int v = pair.v;
			int w = pair.w;

			if (visited[v]) {
				continue;
			}
			visited[v] = true;
			mst += w;
			for (VWg ed : adj.get(v)) {
				if (!visited[ed.v]) {
					queue.add(new VWg(ed.v, ed.w));
				}

			}
		}
		return mst;

	}

	static int[] dijkstra(ArrayList<ArrayList<int[]>> adj, int src) {
		int V = adj.size();
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);

		dist[src] = 0;
		// distance and node
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
		queue.add(new int[] { 0, src });

		while (!queue.isEmpty()) {
			int[] pair = queue.poll();
			int distance = pair[0];
			int vertex = pair[1];

			if (distance > dist[vertex]) {
				continue;
			}
			for (int[] neighbor : adj.get(vertex)) {
				int edgeWeight = neighbor[0]; // weight
				int neighborNode = neighbor[1]; // node

				int newDist = distance + edgeWeight;

				if (newDist < dist[neighborNode]) {
					dist[neighborNode] = newDist;
					// CORRECT: {newDistance, neighborNode}
					queue.add(new int[] { newDist, neighborNode });
				}
			}
		}
		return dist;
	}

	static int[] dijkstra1(ArrayList<ArrayList<VWg>> adj, int src) {
		int V = adj.size();
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);

		dist[src] = 0;
		// distance and node
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
		queue.add(new int[] { 0, src });

		while (!queue.isEmpty()) {
			int[] pair = queue.poll();
			int distance = pair[0];
			int vertex = pair[1];

			if (distance > dist[vertex]) {
				continue;
			}
			for (VWg neighbor : adj.get(vertex)) {
				int edgeWeight = neighbor.w; // weight
				int neighborNode = neighbor.v; // node

				int newDist = distance + edgeWeight;

				if (newDist < dist[neighborNode]) {
					dist[neighborNode] = newDist;
					// CORRECT: {newDistance, neighborNode}
					queue.add(new int[] { newDist, neighborNode });
				}
			}
		}
		return dist;
	}

	static int[] bellmanFord(int V, int[][] edges, int src) {
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;

		// Step 1: Relax all edges V-1 times
		for (int i = 0; i < V - 1; i++) { // V-1 iterations
			for (int[] edge : edges) {
				int u = edge[0];
				int v = edge[1];
				int w = edge[2];

				if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
					dist[v] = dist[u] + w;
				}
			}
		}

		// Step 2: Check for negative cycles (Vth iteration)
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			int w = edge[2];

			if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
				// Negative cycle detected
				return new int[] { -1 };
			}
		}

		return dist;
	}

	public static ArrayList<Integer> topoSort(ArrayList<ArrayList<Integer>> adj) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int V = adj.size();
		boolean[] visited = new boolean[V];
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				dfsTopoSort(i, adj, visited, stack);
			}
		}
		while (!stack.isEmpty()) {
			list.add(stack.pop());
		}
		return list;
	}

	private static void dfsTopoSort(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
		visited[v] = true;

		for (int neg : adj.get(v)) {
			if (!visited[neg]) {
				dfsTopoSort(neg, adj, visited, stack);
			}
		}
		stack.push(v);

	}

	public static ArrayList<Integer> topologicalSortKahn(ArrayList<ArrayList<Integer>> adj) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int V = adj.size();
		int[] inDegree = new int[V];

		for (int u = 0; u < V; u++) {
			for (int v : adj.get(u)) {
				inDegree[v]++;
			}
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < V; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}
		while (!queue.isEmpty()) {
			int u = queue.poll();
			list.add(u);

			for (int neg : adj.get(u)) {
				if (--inDegree[neg] == 0) {
					queue.add(neg);
				}
			}
		}
		return list;
	}

	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		// Build adjacency list
		ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<>());
		for (int[] f : flights) {
			adj.get(f[0]).add(new int[] { f[1], f[2] });
		}
		// Distance and stops tracking
		int[] dist = new int[n];
		int[] stopsArr = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(stopsArr, Integer.MAX_VALUE);

		dist[src] = 0;
		stopsArr[src] = 0;

		PriorityQueue<VerPriceStops> pq = new PriorityQueue<VerPriceStops>();
		pq.add(new VerPriceStops(src, 0, 0));

		while (!pq.isEmpty()) {
			VerPriceStops curr = pq.poll();
			int city = curr.vertex;
			int cost = curr.price;
			int stops = curr.stpos;
			if(city==dst) {
				return cost;
			}
			 if (stops > k) continue;
			 for(int[] e:adj.get(city)) {
				 int next =e[0];
				 int price =e[1];
				 int newCost=cost+price;
				 int newStops=stops+1;
				 if(newCost<dist[next] || newStops<stopsArr[next]) {
					 dist[next] = newCost;
	                    stopsArr[next] = newStops;
	                    pq.add(new VerPriceStops(next, newCost, newStops));
				 }
			 }
		}
		return -1;
	}
	 public boolean canFinish(int numCourses, int[][] prerequisites) {
		 ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		 for(int i=0;i<numCourses;i++) {
			 adj.add(new ArrayList<Integer>());
		 }
		 for(int[] p:prerequisites) {
			 adj.get(p[0]).add(p[1]);
		 }
		 boolean[] visited = new boolean[numCourses];
		 boolean[] recStack = new boolean[numCourses];
		 for(int i=0;i<numCourses;i++) {
			 if(!visited[i]){
				 if(dfsCanFinish(i,adj,visited,recStack)) {
					 return false;
				 } 
			 }
			 
		 }
		return true;
	 }

	private boolean dfsCanFinish(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] recStack) {
		visited[v]=true;
		recStack[v] =true;
		
		for(int neg:adj.get(v)) {
			if(!visited[neg]) {
				if(dfsCanFinish(neg, adj, visited, recStack)) {
					return true;
				}
			}else if(recStack[neg]) {
				return true;
			}
		}
		recStack[v]=false;
		return false;
	}
	public static boolean checkPath(ArrayList<ArrayList<Integer>> adj, int u, int v) {
		int V=adj.size();
		boolean[] visited = new boolean[V];
		return dfsCheckPath(u,v,adj,visited);
	}

	private static boolean dfsCheckPath(int u, int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
		if(u==v) {
			return true;
		}
		visited[u]=true;
		for(int neg:adj.get(u)) {
			if(!visited[neg]) {
				if(dfsCheckPath(neg, v, adj, visited)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean checkPathItrative(ArrayList<ArrayList<Integer>> adj, int u, int v) {
		boolean[] visited = new boolean[adj.size()];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(u);
		visited[u]=true;
		
		while(!queue.isEmpty()) {
			int ver = queue.poll();
			if(ver==v) {
				return true;
			}
			for(int neg:adj.get(ver)) {
				if(!visited[neg]) {
					visited[neg]=true;
					queue.add(neg);
				}
			}
			
		}
		return false;
	}
	public static int countPaths(int n, int[][] edgeList, int source, int destination) {
		ArrayList<ArrayList<Integer>> adj= new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<n;i++) {
			adj.add(new ArrayList<Integer>());
		}
		for(int[] edge:edgeList) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}
		int[] count= {1};
		boolean[] visited= new boolean[n];
		dfsCheckPathCount(source,destination,adj,visited,count);
		return count[0];
	}

	private static void dfsCheckPathCount(int source, int destination, ArrayList<ArrayList<Integer>> adj,
			boolean[] visited, int[] count) {
		if(source==destination) {
			count[0]++;
			return;
		}
		visited[source]=true;
		
		for(int neg:adj.get(source)) {
			if(!visited[neg]) {
				dfsCheckPathCount(neg, destination, adj, visited, count);
			}
		}
		visited[source]=false;
		
	}
}
