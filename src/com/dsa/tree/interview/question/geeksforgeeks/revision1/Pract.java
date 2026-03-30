package com.dsa.tree.interview.question.geeksforgeeks.revision1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.dsa.graph.interview.question.geeksforgeeks.SnakeCell;

public class Pract {

	public static ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int V = adj.size();
		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (!visited[i])
				bfsGraph(i, adj, list, visited);
		}
		return list;
	}

	public static void bfsGraph(int v, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> list, boolean[] visited) {

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(v);
		visited[v] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			list.add(current);

			for (int neg : adj.get(current)) {
				if (!visited[neg]) {
					visited[neg] = true;
					queue.add(neg);
				}
			}
		}
	}

	public static ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int V = adj.size();
		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				dfsGraph(i, visited, adj, list);
			}
		}
		return list;
	}

	private static void dfsGraph(int v, boolean[] visited, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> list) {
		visited[v] = true;
		list.add(v);

		for (int neg : adj.get(v)) {
			if (!visited[neg]) {
				dfsGraph(neg, visited, adj, list);
			}
		}
	}

	static int[] dijkstra(ArrayList<ArrayList<int[]>> adj, int src) {
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);

		int[] dist = new int[adj.size()];
		Arrays.fill(dist, Integer.MAX_VALUE);

		queue.add(new int[] { src, 0 });
		dist[src] = 0;

		while (!queue.isEmpty()) {
			int[] pair = queue.poll();
			int u = pair[0];
			int w = pair[1];
			if (w > dist[u]) {
				continue;
			}

			for (int[] p : adj.get(u)) {
				int v = p[0];
				int wt = p[1];

				if (dist[u] + wt < dist[v]) {
					dist[v] = dist[u] + wt;
					queue.add(new int[] { v, dist[v] });
				}
			}
		}
		return dist;

	}

	public static int[] sqaureOfSotredArry(int[] nums) {
		int start = 0, end = nums.length - 1;
		int[] result = new int[nums.length];
		int indx = nums.length - 1;
		while (start <= end) {
			if ((nums[start] * nums[start]) >= (nums[end] * nums[end])) {
				result[indx] = nums[start] * nums[start];
				start++;
				indx--;
			} else if ((nums[start] * nums[start]) < (nums[end] * nums[end])) {

				result[indx] = nums[end] * nums[end];
				end--;
				indx--;
			}
		}
		return result;
	}

	public static int lastStoneWeight(int[] stones) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>((a, b) -> b - a);

		for (int stone : stones) {
			queue.add(stone);
		}
		while (queue.size() > 1) {
			int first = queue.poll();

			int second = queue.poll();
			if (first != second) {
				queue.add(first - second);
			}
		}
		return queue.peek();
	}

	public static String removeAllAjacent(String s) {
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (stack.isEmpty()) {
				stack.push(ch);
			} else if (stack.peek() == ch) {
				stack.pop();
			} else {
				stack.push(ch);
			}
		}
		StringBuilder builder = new StringBuilder();
		for (char c : stack) { // Enhanced for loop iterates from bottom
			builder.append(c);
		}
		return builder.toString();
	}

	public static int balancedString(String s) {
		int lCount = 0, rCount = 0, count = 0;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == 'L') {
				lCount++;
			}
			if (ch == 'R') {
				rCount++;
			}
			if (lCount == rCount) {
				count++;
			}
		}
		return count;
	}

	public static int evenNumOfDigits(int[] nums) {
		int count = 0;
		for (int num : nums) {
			if ((countDigit(num) & 1) == 0) {
				count++;
			}
		}
		return count;
	}

	private static int countDigit(int num) {
		int count = 0;
		while (num > 0) {
			num = num / 10;
			count++;
		}
		return count;
	}

	public static int[] bellmanFord(int V, int[][] edges, int src) {
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;
		for (int i = 0; i < V - 1; i++) {
			for (int[] edge : edges) {
				int u = edge[0];
				int v = edge[1];
				int w = edge[2];

				if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
					dist[v] = dist[u] + w;
				}
			}
		}
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			int w = edge[2];

			if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
				return new int[-1];
			}
		}
		return dist;
	}

	static int countIslands(char[][] grid) {
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

		int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] dc = { -1, 0, 1, -1, 1, 1, 0, -1 };

		for (int k = 0; k < 8; k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];
			if (isSafe(grid, nr, nc, visited)) {
				dfsIsland(grid, nr, nc, visited);
			}
		}

	}

	private static boolean isSafe(char[][] grid, int r, int c, boolean[][] visited) {
		int m = grid.length;
		int n = grid[0].length;
		return (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 'L' && !visited[r][c]);
	}

	public static boolean isBipartite(int V, int[][] edges) {
		int[] color = new int[V];
		Arrays.fill(color, -1);
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<Integer>());
		}
		// ✅ Correct (using edge values):
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			adj.get(u).add(v);
			adj.get(v).add(u); // for undirected graph
		}
		for (int i = 0; i < V; i++) {
			if (color[i] == -1) {
				color[i] = 0;
				if (!bfsPertite(i, color, adj)) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean bfsPertite(int v, int[] color, ArrayList<ArrayList<Integer>> adj) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(v);
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

	private static boolean dfsPertite(int v, int[] color, ArrayList<ArrayList<Integer>> adj) {
		for (int negh : adj.get(v)) {
			if (color[negh] == -1) {
				color[negh] = 1 - color[v];
				if (!dfsPertite(negh, color, adj)) {
					return false;
				}
			} else if (color[negh] == color[v]) {
				return false;
			}
		}
		return true;
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

	static int[][] floodFill(int[][] img, int sr, int sc, int newColor) {
		if (img[sr][sc] == newColor) {
			return img;
		}
		int oldColor = img[sr][sc];
		dfsFloodFill(img, sr, sc, oldColor, newColor);
		return img;
	}

	private static void dfsFloodFill(int[][] img, int x, int y, int oldColor, int newColor) {
		if (x < 0 || y < 0 || x >= img.length || y >= img[0].length || oldColor != img[x][y]) {
			return;
		}
		img[x][y] = newColor;
		dfsFloodFill(img, x + 1, y, oldColor, newColor);
		dfsFloodFill(img, x - 1, y, oldColor, newColor);
		dfsFloodFill(img, x, y + 1, oldColor, newColor);
		dfsFloodFill(img, x, y - 1, oldColor, newColor);

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
		if (x < 0 || y < 0 || x >= m || y >= n || mat[x][y] != 'O') {
			return;
		}
		mat[x][y] = 'S';
		markSafe(mat, x + 1, y);
		markSafe(mat, x - 1, y);
		markSafe(mat, x, y + 1);
		markSafe(mat, x, y - 1);

	}

	public static void main(String[] args) {

	}

}
