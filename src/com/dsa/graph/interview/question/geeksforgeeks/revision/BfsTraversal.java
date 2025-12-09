package com.dsa.graph.interview.question.geeksforgeeks.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import com.faizan.array.prac.Sort1;

public class BfsTraversal {

	public static int bfsGraph(ArrayList<ArrayList<Integer>> adj, int source, int dest) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		boolean visited[] = new boolean[adj.size()];
		int[] dist = new int[adj.size()];
		int[] pred = new int[adj.size()];
		Arrays.fill(dist, -1);
		Arrays.fill(pred, -1);

		Queue<Integer> queue = new LinkedList<Integer>();

		visited[source] = true;
		queue.add(source);
		dist[source] = 0;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			list.add(current);
			for (int neg : adj.get(current)) {
				if (!visited[neg]) {
					visited[neg] = true;
					dist[neg] = dist[current] + 1;
					queue.add(neg);
					if (neg == dest) {
						return dist[neg];
					}
				}
			}
		}
		return -1;

	}

	public static ArrayList<Integer> dfsGraph(ArrayList<ArrayList<Integer>> adj) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		boolean[] visited = new boolean[adj.size()];
		for (int i = 0; i < adj.size(); i++) {
			if (!visited[i]) {
				dfs(i, adj, visited, list);
			}
		}
		return list;
	}

	private static void dfs(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> list) {
		visited[v] = true;
		list.add(v);

		for (int neg : adj.get(v)) {
			if (!visited[neg]) {
				dfs(neg, adj, visited, list);
			}
		}
	}

	public static int countIslands(char[][] grid) {
		int n = grid.length;
		int m = grid[0].length;

		boolean[][] visited = new boolean[n][m];
		int isLands = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
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

		for (int k = 0; k < 8; k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];
			if (isSafe(grid, nr, nc, visited)) {
				dfsIsland(grid, nr, nc, visited);
			}
		}
	}

	private static boolean isSafe(char[][] grid, int r, int c, boolean[][] visited) {
		int n = grid.length;
		int m = grid[0].length;

		return (r >= 0 && r < n && c >= 0 && c < m && grid[r][c] == 'L' && !visited[r][c]);
	}

	public static boolean detectCylcle(ArrayList<ArrayList<Integer>> adj) {
		int V = adj.size();
		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				if (dfsCycle(i, adj, visited, -1)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean dfsCycle(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, int parent) {
		visited[v] = true;

		for (int neg : adj.get(v)) {
			if (!visited[neg]) {
				if (dfsCycle(neg, adj, visited, v)) {
					return true;
				}
			} else if (neg != parent) {
				return true;
			}
		}
		return false;
	}

	public static boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
		int[] color = new int[V];
		Arrays.fill(color, -1);

		for (int i = 0; i < V; i++) {
			if (color[i] == -1) {
				color[i] = 0;
				if (!isBipertiteRecusrsive(i, adj, color)) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isBipertiteRecusrsive(int v, ArrayList<ArrayList<Integer>> adj, int[] color) {

		for (int neg : adj.get(v)) {
			if (color[neg] == -1) {
				color[neg] = 1 - color[v];
				if (!isBipertiteRecusrsive(neg, adj, color)) {
					return false;
				}
			} else if (color[neg] == color[v]) {
				return false;
			}
		}
		return true;
	}
	
	 public boolean isBipartite1(int[][] graph) {
	        int n = graph.length;
	        int[] color = new int[n]; // 0 = uncolored, 1 = red, -1 = blue

	        for (int i = 0; i < n; i++) {
	            if (color[i] != 0) continue; // already colored

	            Queue<Integer> q = new LinkedList<>();
	            q.add(i);
	            color[i] = 1;

	            while (!q.isEmpty()) {
	                int node = q.poll();
	                for (int neighbor : graph[node]) {
	                    if (color[neighbor] == 0) {
	                        color[neighbor] = -color[node]; // alternate color
	                        q.add(neighbor);
	                    } else if (color[neighbor] == color[node]) {
	                        return false; // same color on adjacent nodes → not bipartite
	                    }
	                }
	            }
	        }

	        return true;
	    }

	private static boolean isBipartiteUiil(int v, ArrayList<ArrayList<Integer>> adj, int[] color) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(v);
		color[v] = 0;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (int neg : adj.get(current)) {
				if (color[neg] == -1) {
					color[neg] = 1 - color[current];
					queue.add(neg);
				} else if (color[neg] == color[current]) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isBipartite1(int V, ArrayList<ArrayList<Integer>> adj) {
		int[] color = new int[V];
		boolean[] visted = new boolean[V];
		Arrays.fill(color, -1);

		for (int i = 0; i < V; i++) {
			if (!visted[i]) {
				color[i] = 0;
				if (!isBipertiteRecusrsive1(i, adj, color, visted)) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isBipertiteRecusrsive1(int v, ArrayList<ArrayList<Integer>> adj, int[] color,
			boolean[] visted) {
		visted[v] = true;
		for (int neg : adj.get(v)) {
			if (!visted[neg]) {
				color[neg] = 1 - color[v];
				if (!isBipertiteRecusrsive1(neg, adj, color, visted)) {
					return false;
				}
			} else if (color[neg] == color[v]) {
				return false;
			}
		}
		return true;
	}

	public static int getMinDiceThrows(int[] moves) {
		int n = moves.length;
		boolean[] visited = new boolean[n];
		Queue<SnakeCellDist> queue = new LinkedList<SnakeCellDist>();
		queue.add(new SnakeCellDist(0, 0));
		visited[0] = true;

		while (!queue.isEmpty()) {
			SnakeCellDist cell = queue.poll();
			int v = cell.vertex;
			if (v == n - 1) {
				return cell.dist;
			}
			for (int dice = 1; dice <= 6 && v + dice < n; dice++) {
				int next = v + dice;
				if (!visited[next]) {
					visited[next] = true;
					int dest = (moves[next] != -1) ? moves[next] : next;
					queue.add(new SnakeCellDist(dest, cell.dist + 1));
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
		if (x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] != oldColor) {
			return;
		}
		image[x][y] = newColor;
		dfsFillColor(image, x + 1, y, oldColor, newColor);
		dfsFillColor(image, x - 1, y, oldColor, newColor);
		dfsFillColor(image, x, y + 1, oldColor, newColor);
		dfsFillColor(image, x, y - 1, oldColor, newColor);
	}

	public static boolean cycleInDirected(ArrayList<ArrayList<Integer>> adj) {
		int V = adj.size();
		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (dfsDirected(i, adj, visited, recStack)) {
				return true;
			}
		}
		return false;

	}

	private static boolean dfsDirected(int i, ArrayList<ArrayList<Integer>> adj, boolean[] visited,
			boolean[] recStack) {
		visited[i] = true;
		recStack[i] = true;
		for (int neg : adj.get(i)) {
			if (!visited[neg]) {
				if (dfsDirected(neg, adj, visited, recStack)) {
					return true;
				}
			} else if (recStack[neg]) {
				return true;
			}
		}
		recStack[i] = false;
		return false;
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
		System.out.println(Arrays.toString(in));
		System.out.println(Arrays.toString(out));
		System.out.println(Arrays.toString(present));

		return isConnected(words, present);
	}

	private static boolean isConnected(String[] words, boolean[] present) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 26; i++) {
			adj.add(new ArrayList<>());
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
		dfsCircle(start, visited, adj);
		for (int i = 0; i < 26; i++) {
			if (present[i] && !visited[i]) {
				return false;
			}
		}
		return true;
	}

	private static void dfsCircle(int start, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
		visited[start] = true;
		for (int neg : adj.get(start)) {
			if (!visited[neg]) {
				dfsCircle(neg, visited, adj);
			}
		}
	}

	static boolean checkPath(ArrayList<ArrayList<Integer>> adj, int u, int v) {
		boolean[] visited = new boolean[adj.size()];
		return dfsCheckPath(adj, u, v, visited);
	}

	private static boolean dfsCheckPath(ArrayList<ArrayList<Integer>> adj, int u, int v, boolean[] visited) {
		if (visited[u]) {
			return false;
		}
		if (u == v) {
			return true;
		}
		visited[u] = true;
		for (int neg : adj.get(u)) {
			if (!visited[neg] && dfsCheckPath(adj, neg, v, visited)) {
				return true;
			}
		}
		return false;
	}

	public static boolean bfsCheckPath(int src, int dest, ArrayList<ArrayList<Integer>> adj) {
		boolean[] visited = new boolean[adj.size()];
		Queue<Integer> queue = new LinkedList<Integer>();
		visited[src] = true;
		queue.add(src);

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
		for (int i = 0; i <= n; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (int[] edge : edgeList) {
			adj.get(edge[0]).add(edge[1]);
		}
		int[] count = new int[1];
		boolean[] visited = new boolean[n + 1];
		dfsOfPath(adj, visited, count, source, destination);
		return count[0];
	}

	private static void dfsOfPath(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int[] count, int source,
			int destination) {
		if (source == destination) {
			count[0]++;
			return;
		}
		visited[source] = true;
		for (int neg : adj.get(source)) {
			if (!visited[neg]) {
				dfsOfPath(adj, visited, count, neg, destination);
			}
		}
		visited[source] = false;

	}

	public static int xShape(char[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 'X') {
					ans++;
					dfsXshape(grid, i, j);
				}
			}
		}
		return ans;
	}

	private static void dfsXshape(char[][] grid, int x, int y) {
		if (x < 0 || y < 0 || x >= grid.length || y >= grid[x].length) {
			return;
		}
		if (grid[x][y] == 'X') {
			grid[x][y] = 'O';
			dfsXshape(grid, x + 1, y);
			dfsXshape(grid, x, y + 1);
			dfsXshape(grid, x - 1, y);
			dfsXshape(grid, x, y - 1);
		}
	}

	public static void main(String[] args) {
		char[][] grid = { { 'X', 'O', 'X' }, { 'O', 'X', 'O' }, { 'X', 'X', 'X' } };

		// Function call
		System.out.println(xShape(grid));
	}

}
