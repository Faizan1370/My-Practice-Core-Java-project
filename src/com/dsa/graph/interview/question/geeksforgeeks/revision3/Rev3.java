package com.dsa.graph.interview.question.geeksforgeeks.revision3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import com.dsa.graph.interview.question.geeksforgeeks.SnakeCell;

public class Rev3 {

	public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		ArrayList<ArrayList<int[]>> adj = new ArrayList<ArrayList<int[]>>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<int[]>());
		}
		for (int[] f : flights) {
			adj.get(f[0]).add(new int[] { f[1], f[2] });
		}
		int[] dist = new int[n];
		int[] stops = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(stops, Integer.MAX_VALUE);
		PriorityQueue<PriceNodePair> queue = new PriorityQueue<PriceNodePair>();
		queue.add(new PriceNodePair(src, 0, 0));
		dist[src] = 0;
		stops[src] = 0;

		while (!queue.isEmpty()) {
			PriceNodePair pair = queue.poll();
			int city = pair.v;
			int price = pair.price;
			int st = pair.stops;
			if (city == dst)
				return price;
			if (st > k) {
				continue;
			}
			for (int[] edge : adj.get(city)) {
				int dest = edge[0];
				int cost = edge[1];

				if (dist[city] + cost < dist[dest]) {
					dist[dest] = dist[city] + cost;
					int stop = st + 1;
					queue.add(new PriceNodePair(dest, dist[dest], stop));
				}
			}
		}
		return -1;
	}

	public int findCheapestPriceBellManFord(int n, int[][] flights, int src, int dst, int k) {

		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);

		dist[src] = 0;

		// Relax edges K+1 times
		for (int i = 0; i <= k; i++) {

			int[] temp = dist.clone();

			for (int[] flight : flights) {
				int u = flight[0];
				int v = flight[1];
				int cost = flight[2];

				if (dist[u] == Integer.MAX_VALUE)
					continue;

				if (dist[u] + cost < temp[v]) {
					temp[v] = dist[u] + cost;
				}
			}

			dist = temp;
		}

		return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
	}
	public int findCheapestPriceBellManFord1(int n, int[][] flights, int src, int dst, int k) {
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);

		dist[src] = 0;
		for(int i=0;i<=k;i++) {
			int[] temp =dist.clone();
			
			for(int[] flight:flights) {
				int u = flight[0];
				int v = flight[1];
				int cost = flight[2];
				if(dist[u] !=Integer.MAX_VALUE && dist[u] + cost < temp[v]) {
					temp[v] = dist[u] + cost;
				}
			}
			dist=temp;
		}
		return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
	}

	public static int findCheapestPrice1(int n, int[][] flights, int src, int dst, int k) {
		ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<>());

		for (int[] f : flights) {
			adj.get(f[0]).add(new int[] { f[1], f[2] });
		}

		PriorityQueue<PriceNodePair> pq = new PriorityQueue<>();
		pq.add(new PriceNodePair(src, 0, 0));

		while (!pq.isEmpty()) {
			PriceNodePair cur = pq.poll();

			if (cur.v == dst)
				return cur.price;
			if (cur.stops > k)
				continue;

			for (int[] e : adj.get(cur.v)) {
				pq.add(new PriceNodePair(e[0], cur.price + e[1], cur.stops + 1));
			}
		}
		return -1;
	}

	public static int findCircleNum(int[][] isConnected) {
		int n = isConnected.length;
		int provinenes = 0;
		boolean[] visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfs(i, visited, isConnected);
				provinenes++;
			}
		}
		return provinenes;
	}

	private static void dfs(int i, boolean[] visited, int[][] isConnected) {
		visited[i] = true;

		for (int j = 0; j < isConnected.length; j++) {
			if (isConnected[i][j] == 1 && !visited[j]) {
				dfs(j, visited, isConnected);
			}
		}

	}

	public static int[] findRedundantConnection(int[][] edges) {
		int n = edges.length;
		int[] parent = new int[n + 1];
		int[] rank = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}
		DisjoinUnion disjoinUnion = new DisjoinUnion();
		int[] result = new int[2];
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			if (disjoinUnion.union(u, v, parent, rank)) {
				result = edge;
			}
		}
		return result;

	}

	public static int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
		ArrayList<ArrayList<int[]>> adj = new ArrayList<ArrayList<int[]>>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<int[]>());
		}
		for (int[] f : flights) {
			adj.get(f[0]).add(new int[] { f[1], f[2] });
		}
		int[] dist = new int[n];
		int[] stops = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(stops, Integer.MAX_VALUE);
		PriorityQueue<PriceNodePair> pq = new PriorityQueue<PriceNodePair>();
		pq.add(new PriceNodePair(src, 0, 0));
		dist[src] = 0;
		stops[src] = 0;

		while (!pq.isEmpty()) {
			PriceNodePair pair = pq.poll();
			int city = pair.v;
			int price = pair.price;
			int st = pair.stops;
			if (city == dst) {
				return price;
			}
			if (st > k) {
				continue;
			}
			for (int[] neg : adj.get(city)) {
				int next = neg[0];
				int cost = neg[1];

				int newCost = price + cost;
				int newStop = st + 1;
				if (newCost < dist[next]) {
					dist[next] = newCost;
					stops[next] = newStop;
					pq.add(new PriceNodePair(next, newCost, newStop));
				}
			}
		}
		return -1;
	}
	public static int findCheapestPrice3(int n, int[][] flights, int src, int dst, int k) {
		ArrayList<ArrayList<int[]>> adj = new ArrayList<ArrayList<int[]>>();
		for(int i=0;i<n;i++) {
			adj.add(new ArrayList<int[]>());
		}
		for(int[] flight:flights) {
			int city=flight[0];
			int dest = flight[1];
			int cost = flight[2];
			adj.get(city).add(new int[] {dest,cost});
		}
		int[] dist = new int[n];
		int[] stops = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(stops, Integer.MAX_VALUE);
		PriorityQueue<PriceNodePair> queue = new PriorityQueue<PriceNodePair>();
		queue.add(new PriceNodePair(src, 0, 0));
		dist[src]=0;
		stops[src]=0;
		
		while(!queue.isEmpty()) {
			PriceNodePair pair = queue.poll();
			int city =pair.v;
			int cost = pair.price;
			int st = pair.stops;
			if(city==dst) {
				return cost;
			}
			if(st>k) {
				continue;
			}
			for(int[] neg:adj.get(city)) {
				int dest = neg[0];
				int price =neg[1];
				int newStops = st+1;
				int newCost = cost+price;
				if(newCost<dist[dest] || newStops < stops[dest]) {
					dist[dest]=newCost;
					stops[dest]=newStops;
					queue.add(new PriceNodePair(dest, newCost, newStops));
				}
			}
		}
		return -1;
	}

	static ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[adj.size()];

		queue.add(0);
		visited[0] = true;
		while (!queue.isEmpty()) {
			int current = queue.poll();
			list.add(current);
			for (int negh : adj.get(current)) {
				if (!visited[negh]) {
					visited[negh] = true;
					queue.add(negh);
				}
			}

		}
		return list;

	}

	public static ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		boolean[] visited = new boolean[adj.size()];
		for (int i = 0; i < adj.size(); i++) {
			if (!visited[i]) {
				dfsUtil(i, visited, adj, list);
			}
		}
		return list;
	}

	private static void dfsUtil(int v, boolean[] visited, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> list) {
		visited[v] = true;
		list.add(v);

		for (int ng : adj.get(v)) {
			if (!visited[ng]) {
				dfsUtil(ng, visited, adj, list);
			}
		}
	}

	public static ArrayList<Integer> dfsTopoSort(ArrayList<ArrayList<Integer>> adj) {
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[adj.size()];
		ArrayList<Integer> list = new ArrayList<Integer>();
		dfsTopoSortUtil(0, adj, visited, stack);
		while (!stack.isEmpty()) {
			list.add(stack.pop());
		}
		return list;
	}

	private static void dfsTopoSortUtil(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited,
			Stack<Integer> stack) {
		visited[v] = true;

		for (int neg : adj.get(v)) {
			if (!visited[neg]) {
				dfsTopoSortUtil(neg, adj, visited, stack);
			}
		}
		stack.push(v);

	}

	public static boolean isBipartite(int V, int[][] edges) {
		int[] color = new int[V];
		Arrays.fill(color, -1);
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (int[] edge : edges) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}
		for (int i = 0; i < V; i++) {
			if (color[i] == -1) {
				color[i] = 0;
				if (!dfsBipertite(i, adj, color)) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean dfsBipertite(int v, ArrayList<ArrayList<Integer>> adj, int[] color) {

		for (int negh : adj.get(v)) {
			if (color[negh] == -1) {
				color[negh] = 1 - color[v];
				if (!dfsBipertite(negh, adj, color)) {
					return false;
				}
			} else if (color[v] == color[negh]) {
				return false;
			}
		}
		return true;

	}

	public static boolean isBipartiteItrative(int V, int[][] edges) {
		int[] color = new int[V];
		Arrays.fill(color, -1);
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (int[] edge : edges) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}
		for (int i = 0; i < V; i++) {
			if (color[i] == -1) {
				color[i] = 0;
				if (!bfsBipertite(i, adj, color)) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean bfsBipertite(int v, ArrayList<ArrayList<Integer>> adj, int[] color) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(v);
		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (int negh : adj.get(current)) {
				if (color[negh] == -1) {
					color[negh] = 1 - color[current];
					queue.add(negh);
				} else if (color[negh] == color[current]) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isCyclic(ArrayList<ArrayList<Integer>> adj) {
		int V = adj.size();
		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];

		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				if (dfsCyclyDirected(i, adj, visited, recStack)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean dfsCyclyDirected(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited,
			boolean[] recStack) {
		visited[v] = true;
		recStack[v] = true;

		for (int negh : adj.get(v)) {
			if (!visited[negh]) {
				if (dfsCyclyDirected(negh, adj, visited, recStack)) {
					return true;
				}
			} else if (recStack[negh]) {
				return true;
			}
		}
		recStack[v] = false;
		return false;
	}

	public static boolean isCyclicUndirected(ArrayList<ArrayList<Integer>> adj) {
		int V = adj.size();
		boolean[] visited = new boolean[V];

		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				if (dfsCyclyUnDirected(i, adj, visited, i)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean dfsCyclyUnDirected(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, int parent) {
		visited[v] = true;

		for (int negh : adj.get(v)) {
			if (!visited[negh]) {
				if (dfsCyclyUnDirected(negh, adj, visited, v)) {
					return true;
				}
			} else if (parent != negh) {
				return true;
			}
		}
		return false;
	}

	public static int getMinDiceThrows(int[] moves) {
		int n = moves.length;
		boolean[] visited = new boolean[n];
		Queue<SnakeCell> queue = new LinkedList<SnakeCell>();
		queue.add(new SnakeCell(0, 0));
		visited[0] = true;

		while (!queue.isEmpty()) {
			SnakeCell current = queue.poll();
			int v = current.vertex;
			if (v == n - 1) {
				return current.dist;
			}
			for (int dice = 1; dice <= 6 && v + dice < n; dice++) {
				int next = dice + v;
				int dest = (moves[next] != -1) ? moves[next] : next;
				if (!visited[dest]) {
					visited[dest] = true;
					queue.add(new SnakeCell(dest, current.dist + 1));
				}

			}
		}
		return -1;
	}

	public int[][] fillColor(int[][] image, int sr, int sc, int newColor) {
		if (image[sr][sc] == newColor) {
			return image;
		}
		dfsFloodFill(image, sr, sc, image[sr][sc], newColor);
		return image;
	}

	private void dfsFloodFill(int[][] image, int x, int y, int oldColor, int newColor) {
		if (x < 0 || x >= image.length || y < 0 || y >= image[0].length || oldColor != image[x][y]) {
			return;
		}
		image[x][y] = newColor;
		dfsFloodFill(image, x + 1, y, oldColor, newColor);
		dfsFloodFill(image, x - 1, y, oldColor, newColor);
		dfsFloodFill(image, x, y + 1, oldColor, newColor);
		dfsFloodFill(image, x, y - 1, oldColor, newColor);

	}

	public static ArrayList<ArrayList<Character>> replaceSurrounded(char[][] mat) {
		int m = mat.length;
		int n = mat[0].length;
		for (int i = 0; i < m; i++) {
			if (mat[i][0] == 'O') {
				markSafe(mat, i, 0);
			}
			if (mat[i][n - 1] == 'O') {
				markSafe(mat, i, n - 1);
			}
		}
		for (int j = 0; j < n; j++) {
			if (mat[0][j] == 'O') {
				markSafe(mat, 0, j);
			}
			if (mat[m - 1][j] == 'O') {
				markSafe(mat, m - 1, j);
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (mat[i][j] == 'O') {
					mat[i][j] = 'X';
				} else if (mat[i][j] == 'S') {
					mat[i][j] = 'O';
				}
			}
		}
		ArrayList<ArrayList<Character>> result = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			ArrayList<Character> row = new ArrayList<Character>();
			for (int j = 0; j < n; j++) {
				row.add(mat[i][j]);
			}
			result.add(row);
		}
		return result;
	}

	private static void markSafe(char[][] mat, int x, int y) {
		int m = mat.length;
		int n = mat[0].length;
		if (x < 0 || x >= m || y < 0 || y >= n || mat[x][y] != 'O') {
			return;
		}
		mat[x][y] = 'S';
		markSafe(mat, x + 1, y);
		markSafe(mat, x - 1, y);
		markSafe(mat, x, y + 1);
		markSafe(mat, x, y - 1);

	}

	public static boolean checkPath(ArrayList<ArrayList<Integer>> adj, int u, int v) {
		boolean[] visited = new boolean[adj.size()];
		return dfsCheckPath(adj, visited, u, v);
	}

	private static boolean dfsCheckPath(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int u, int v) {

		if (u == v) {
			return true;
		}
		visited[u] = true;
		for (int neg : adj.get(u)) {
			if (!visited[neg]) {
				if (dfsCheckPath(adj, visited, neg, v)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean bfsCheckPath(int src, int dest, ArrayList<ArrayList<Integer>> adj) {

		boolean[] visited = new boolean[adj.size()];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(src);
		visited[src] = true;
		while (!queue.isEmpty()) {
			int current = queue.poll();
			if (current == dest) {
				return true;
			}
			for (int neg : adj.get(current)) {
				if (!visited[neg]) {
					visited[neg] = true;
					queue.add(neg);
				}
			}
		}
		return false;
	}

	public static int countPaths(int n, int[][] edgeList, int source, int destination) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (int[] edge : edgeList) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}
		int[] count = { 1 };
		boolean[] visited = new boolean[n];
		dfsCountPaths(source, destination, count, adj, visited);
		return count[0];
	}

	private static void dfsCountPaths(int source, int destination, int[] count, ArrayList<ArrayList<Integer>> adj,
			boolean[] visited) {
		if (source == destination) {
			count[0]++;
			return;
		}
		visited[source] = true;
		for (int neg : adj.get(source)) {
			if (!visited[neg]) {
				dfsCountPaths(neg, destination, count, adj, visited);
			}
		}
		visited[source] = false;

	}

	public static boolean canBeChained(String[] words) {
		int[] in = new int[26];
		int[] out = new int[26];
		boolean[] present = new boolean[26];
		for (String word : words) {
			int first = word.charAt(0) - 'a';
			int last = word.charAt(word.length() - 1) - 'a';
			out[first]++;
			in[last]++;
			present[first] = present[last] = true;
		}
		for (int i = 0; i < 26; i++) {
			if (in[i] != out[i]) {
				return false;
			}
		}
		return isConneted(words, present);
	}

	private static boolean isConneted(String[] words, boolean[] present) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 26; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (String word : words) {
			int first = word.charAt(0) - 'a';
			int last = word.charAt(word.length() - 1) - 'a';
			adj.get(first).add(last);
		}
		int start = -1;
		for (int i = 0; i < 26; i++) {
			if (present[i]) {
				start = i;
				break;
			}
		}
		if (start == -1) {
			return true;
		}
		boolean[] visited = new boolean[26];
		dfsCircular(start, adj, visited);
		for (int i = 0; i < 26; i++) {
			if (present[i] && !visited[i]) {
				return false;
			}
		}
		return true;
	}

	private static void dfsCircular(int start, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
		visited[start] = true;
		for (int neg : adj.get(start)) {
			if (!visited[neg]) {
				dfsCircular(neg, adj, visited);
			}
		}
	}

	public static boolean canBeChained1(String[] words) {
		int[] in = new int[26];
		int[] out = new int[26];
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> charUsed = new ArrayList<Integer>();
		for (int i = 0; i < 26; i++) {
			adj.add(new ArrayList<>());
		}

		for (String word : words) {
			int first = word.charAt(0) - 'a';
			int last = word.charAt(word.length() - 1) - 'a';
			out[first]++;
			in[last]++;
			adj.get(first).add(last);
			if (!charUsed.contains(first)) {
				charUsed.add(first);
			}
			if (!charUsed.contains(last)) {
				charUsed.add(last);
			}
		}
		for (int c : charUsed) {
			if (in[c] != out[c]) {
				return false;
			}
		}
		int start = charUsed.get(0);
		boolean[] visited = new boolean[26];
		dfsCir(start, adj, visited);
		for (int c : charUsed) {
			if (!visited[c]) {
				return false;
			}
		}
		return true;
	}

	private static void dfsCir(int start, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
		visited[start] = true;
		for (int neg : adj.get(start)) {
			if (!visited[neg]) {
				dfsCir(neg, adj, visited);
			}
		}

	}

	private static boolean isConnected(String[] words, boolean[] present) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < 26; i++)
			adj.add(new ArrayList<>());

		int start = -1;
		for (String word : words) {
			int u = word.charAt(0) - 'a';
			int v = word.charAt(word.length() - 1) - 'a';
			adj.get(u).add(v);
			if (start == -1)
				start = u;
		}

		boolean[] visited = new boolean[26];
		dfs(start, adj, visited);

		for (int i = 0; i < 26; i++) {
			if (present[i] && !visited[i])
				return false;
		}
		return true;
	}

	private static void dfs(int u, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
		visited[u] = true;
		for (int v : adj.get(u)) {
			if (!visited[v])
				dfs(v, adj, visited);
		}
	}

	public static boolean scheduleCourse(int V, int[][] edges) { // cyclye check
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (int[] edge : edges) {
			adj.get(edge[0]).add(edge[1]);
		}
		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				if (dfsCourse(i, adj, visited, recStack)) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean dfsCourse(int u, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] recStack) {
		visited[u] = true;
		recStack[u] = true;

		for (int neg : adj.get(u)) {
			if (!visited[neg]) {
				if (dfsCourse(neg, adj, visited, recStack)) {
					return true;
				}
			} else if (recStack[neg]) {
				return true;
			}

		}
		recStack[u] = false;
		return false;
	}

	public static boolean scheduleCourseItrative(int V, int[][] edges) {
		int[] inDeg = new int[V];
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (int[] edg : edges) {
			int u = edg[0];
			int v = edg[1];
			adj.get(u).add(v);
		}
		for (ArrayList<Integer> list : adj) {
			for (int data : list) {
				inDeg[data]++;
			}
		}
		int count = 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < V; i++) {
			if (inDeg[i] == 0) {
				queue.add(i);
			}
		}
		while (!queue.isEmpty()) {
			int current = queue.poll();
			count++;
			for (int neg : adj.get(current)) {
				if (--inDeg[neg] == 0) {
					queue.add(neg);
				}
			}
		}
		/*
		 * for(int i=0;i<V;i++) { if(inDeg[i]!=0) { return false; } }
		 */
		return count == V;
	}

	public int[] schedduleCourseOrTask(int numTask, int[][] prerequisites) { // topo sort with cycly check
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < numTask; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (int[] pr : prerequisites) {
			adj.get(pr[1]).add(pr[0]); // y → x
		}
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[numTask];
		boolean[] recStack = new boolean[numTask];

		for (int i = 0; i < numTask; i++) {
			if (!visited[i]) {
				if (dfsschedduleCourseOrTask(i, adj, visited, recStack, stack)) {
					return new int[0];
				}
			}
		}
		int[] res = new int[numTask];
		int idx = 0;
		while (!stack.isEmpty()) {
			res[idx++] = stack.pop();
		}
		return res;
	}

	private boolean dfsschedduleCourseOrTask(int u, ArrayList<ArrayList<Integer>> adj, boolean[] visited,
			boolean[] recStack, Stack<Integer> stack) {
		visited[u] = true;
		recStack[u] = true;
		for (int neg : adj.get(u)) {
			if (!visited[neg]) {
				if (dfsschedduleCourseOrTask(neg, adj, visited, recStack, stack)) {
					return true;
				}
			} else if (recStack[neg]) {
				return true;
			}
		}
		recStack[u] = false;
		stack.push(u);
		return false;
	}

	public static void main(String[] args) {
		char[][] mat = { { 'X', 'X', 'X', 'X' }, { 'X', 'O', 'X', 'X' }, { 'X', 'O', 'O', 'X' }, { 'X', 'O', 'X', 'X' },
				{ 'X', 'X', 'O', 'O' } };
		ArrayList<ArrayList<Character>> replaceSurrounded = replaceSurrounded(mat);
		for (ArrayList<Character> list : replaceSurrounded) {
			System.out.println(list);
		}
	}

}
