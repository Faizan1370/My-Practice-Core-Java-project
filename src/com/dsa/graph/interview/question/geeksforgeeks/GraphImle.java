package com.dsa.graph.interview.question.geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphImle {

	public void bfsGraph(ArrayList<ArrayList<Integer>> adj, int v, boolean[] visited, ArrayList<Integer> res) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(v);
		visited[v] = true;

		while (!queue.isEmpty()) {
			int ver = queue.poll();
			res.add(ver);
			for (int neg : adj.get(ver)) {
				if (!visited[neg]) {
					visited[neg] = true;
					queue.add(neg);
				}
			}
		}
	}

	public ArrayList<Integer> bfsDisconnectd(ArrayList<ArrayList<Integer>> adj) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		int V = adj.size();
		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				bfsGraph(adj, i, visited, res);
			}
		}
		return res;
	}

	public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
		int V = adj.size();
		boolean[] visited = new boolean[V];
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				dfsRecur(adj, i, visited, res);
			}

		}
		return res;
	}

	private void dfsRecur(ArrayList<ArrayList<Integer>> adj, int v, boolean[] visited, ArrayList<Integer> res) {
		visited[v] = true;
		res.add(v);
		for (int neg : adj.get(v)) {
			if (!visited[neg]) {
				dfsRecur(adj, neg, visited, res);
			}
		}
	}

	public int countIslands(char[][] grid) {
		int row = grid.length;
		int col = grid[0].length;

		// Make a bool array to mark visited cells.
		// Initially all cells are unvisited
		boolean[][] visited = new boolean[row][col];
		int count = 0;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (grid[r][c] == 'L' && !visited[r][c]) {
					dfsIland(grid, r, c, visited);
					count++;
				}

			}
		}
		return count;

	}

	public void dfsIland(char[][] grid, int r, int c, boolean[][] visited) {
		int[] rNbr = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] cNbr = { -1, 0, 1, -1, 1, -1, 0, 1 };
		visited[r][c] = true;

		for (int i = 0; i < 8; i++) {
			int newR = r + rNbr[i];
			int newC = c + cNbr[i];
			if (isSafe(grid, newR, newC, visited)) {
				dfsIland(grid, newR, newC, visited);
			}
		}
	}

	private boolean isSafe(char[][] grid, int r, int c, boolean[][] visited) {
		int row = grid.length;
		int col = grid[0].length;

		return (r >= 0) && (r < row) && (c >= 0) && (c < col) && grid[r][c] == 'L' && !visited[r][c];
	}

	public boolean detectCycle(int V, int[][] edges) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<Integer>());
		}

		for (int[] edge : edges) {
			int src = edge[0], dest = edge[1];
			addEdge(src, dest, adj);
		}
		boolean[] visited = new boolean[V];
		int parent = -1;
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				if (dfsCycleCheck(i, adj, visited, parent)) {
					return true;
				}
			}

		}
		return false;

	}

	private boolean dfsCycleCheck(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, int parent) {
		visited[v] = true;
		for (int neg : adj.get(v)) {
			if (!visited[neg]) {
				if (dfsCycleCheck(neg, adj, visited, v)) {
					return true;
				}
			} else if (parent != neg) {
				return true;
			}
		}
		return false;

	}

	public boolean isBipartite(int V, int[][] edges) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		int[] color = new int[V];
		Arrays.fill(color, -1);
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (int[] edge : edges) {
			int src = edge[0], dest = edge[1];
			addEdge(src, dest, adj);
		}
		for (int i = 0; i < V; i++) {
			if (color[i] == -1) {
				if (!checkBipertite(i, adj, color)) {
					return false;
				}
			}

		}
		return true;
	}

	private boolean checkBipertite(int v, ArrayList<ArrayList<Integer>> adj, int[] color) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(v);
		color[v] = 0;

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int neg : adj.get(cur)) {
				if (color[neg] == -1) {
					color[neg] = 1 - color[cur];
					queue.add(neg);
				} else if (color[cur] == color[neg]) {
					return false;
				}
			}
		}

		return true;
	}

	public int getMinimuDiceThrows(int[] moves) {
		int n = moves.length;
		boolean[] visited = new boolean[n];
		Queue<SnakeCell> queue = new LinkedList<>();
		visited[0] = true;
		queue.add(new SnakeCell(0, 0));

		while (!queue.isEmpty()) {
			SnakeCell cell = queue.poll();
			int v = cell.vertex;
			if (v == n - 1) {
				return cell.dist;
			}
			for (int dice = 1; dice <= 6 && v + dice < n; dice++) {
				int next = v + dice;
				if (!visited[next]) {
					int dest = (moves[next] != -1) ? moves[next] : next;

	                visited[next] = true;  // FIX #1
	                visited[dest] = true;  // FIX #2 (important)
					queue.add(new SnakeCell(dest, cell.dist + 1));
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

	public static int[][] directions() {
		int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		return directions;
	}

	public static int[][] floodFillItrative(int[][] image, int sr, int sc, int newColor) {
		if (image[sr][sc] == newColor) {
			return image;
		}
		int oldColor = image[sr][sc];
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { sr, sc });
		image[sr][sc] = newColor;

		while (!queue.isEmpty()) {
			int[] fornt = queue.poll();
			int x = fornt[0];
			int y = fornt[1];

			for (int[] direction : directions()) {
				int nx = x + direction[0];
				int ny = y + direction[1];

				if (nx >= 0 && nx < image.length && ny >= 0 && ny < image[0].length && image[nx][ny] == oldColor) {
					image[nx][ny] = newColor;
					queue.add(new int[] { nx, ny });
				}
			}
		}

		return image;

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
			for (int j = 0; j < n; j++) {
				if (mat[0][j] == 'O') {
					markSafe(mat, 0, j);
				}
				if (mat[m - 1][j] == 'O') {
					markSafe(mat, m - 1, j);
				}
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
			ArrayList<Character> row = new ArrayList<>();
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

	public boolean cycleInDirected(int V, int[][] edges) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (int[] edge : edges) {
			addEdgeDirected(adj, edge[0], edge[1]);
		}
		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				if (dfsDirectedCycle(adj, i, visited, recStack)) {
					return true;
				}
			}
		}
		return false;

	}

	private boolean dfsDirectedCycle(ArrayList<ArrayList<Integer>> adj, int v, boolean[] visited, boolean[] recStack) {
		visited[v] = true;
		recStack[v] = true;
		for (int neg : adj.get(v)) {
			if (!visited[neg]) {
				if (dfsDirectedCycle(adj, neg, visited, recStack)) {
					return true;
				}
			} else if (recStack[neg]) {
				return true;
			}
		}
		recStack[v] = false;
		return false;
	}

	static boolean dfsScheuleCourse(int node, ArrayList<ArrayList<Integer>> adj, int[] visited) {
		visited[node] = 1;

		for (int neg : adj.get(node)) {
			if (visited[neg] == 1) {
				return false;
			} else if (visited[neg] == 0) {
				if (!dfsScheuleCourse(neg, adj, visited)) {
					return false;
				}
			}
		}
		visited[node] = 2;
		return true;
	}

	// Function to check if all courses can be completed
	static boolean canFinish(int n, int[][] prerequisites) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		for (int[] pre : prerequisites) {
			int dest = pre[0];
			int src = pre[1];
			adj.get(src).add(dest);
		}

		// 0 = unvisited, 1 = visiting, 2 = visited
		int[] visited = new int[n];

		for (int i = 0; i < n; i++) {
			if (visited[i] == 0) {
				if (!dfsScheuleCourse(i, adj, visited)) {
					// cycle detected
					return false;
				}
			}
		}

		return true;
	}

	public static boolean scheduleCourse(int V, int[][] edges) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			adj.get(u).add(v);
		}
		int[] inDeg = new int[V];
		for (ArrayList<Integer> list : adj) {
			for (int data : list) {
				inDeg[data]++;
			}
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < V; i++) {
			if (inDeg[i] == 0) {
				queue.add(i);
			}

		}
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			for(int neg:adj.get(curr)) {
				if(--inDeg[neg]==0) {
					queue.add(neg);
				}
			}
		}
		for(int i=0;i<V;i++) {
			if(inDeg[i]!=0) {
				return false;
			}
		}
		return true;
		
	}
	
	public int[] schedduleCourseOrTask(int numTask,int[][] prerequisites) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<numTask;i++) {
			adj.add(new ArrayList<Integer>());
		}
		for(int[] pre:prerequisites) {
			int u=pre[0];
			int v=pre[1];
			adj.get(v).add(u);
		}
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[numTask];
		boolean[] recStack = new boolean[numTask];
		for(int i=0;i<numTask;i++) {
			if(!visited[i]) {
				if(!dfsschedduleCourseOrTask(i,adj,visited,recStack,stack)) {
					return new int[0];
				}
			}
		}
		int[] res = new int[numTask];
        int idx = 0;
        while (!stack.isEmpty()) res[idx++] = stack.pop();
        return res;
	}

	private boolean dfsschedduleCourseOrTask(int i, ArrayList<ArrayList<Integer>> adj, boolean[] visited,
			boolean[] recStack, Stack<Integer> stack) {
		visited[i]=true;
		recStack[i]=true;
		
		for(int neg:adj.get(i)) {
			if(!visited[neg]) {
				if(!dfsschedduleCourseOrTask(neg, adj, visited, recStack, stack)) {
					return false;
				}
			}else if(recStack[neg]) {
				return false;
			}
		}
		recStack[i]=false;
		stack.push(i);
		return true;
	}
	public  List<Integer> schedduleCourseOrTask1(int V, int[][] edges) {
	    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	    for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
	    for (int[] edge : edges) adj.get(edge[1]).add(edge[0]);

	    int[] inDeg = new int[V];
	    for (ArrayList<Integer> list : adj)
	        for (int v : list)
	            inDeg[v]++;

	    Queue<Integer> q = new LinkedList<>();
	    for (int i = 0; i < V; i++)
	        if (inDeg[i] == 0) q.add(i);

	    List<Integer> topo = new ArrayList<>();
	    while (!q.isEmpty()) {
	        int curr = q.poll();
	        topo.add(curr);
	        for (int nei : adj.get(curr))
	            if (--inDeg[nei] == 0)
	                q.add(nei);
	    }

	    // If topo size < V → cycle
	    return topo.size() == V ? topo : new ArrayList<>();
	}
	
	public static boolean canBeChained(String[] words) {
		int[] in = new int[26];
		int[] out = new int[26];
		boolean[] present= new boolean[26];
		for(String word:words) {
			int first = word.charAt(0)-'a';
			int last = word.charAt(word.length()-1)-'a';
			out[first]++;
			in[last]++;
			present[first]=present[last]=true;
		}
		for(int i=0;i<26;i++) {
			if(in[i]!=out[i]) {
				return false;
			}
		}
		
		return isConneted(words,present);
		
	}


	
	private static boolean isConneted(String[] words, boolean[] present) {
		 // Build adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
        	adj.add(new ArrayList<>());
        }
        for (String word : words) {
            int first = word.charAt(0) - 'a';
            int last = word.charAt(word.length() - 1) - 'a';
            adj.get(first).add(last);
        }
        int start=-1;
        for(int i=0;i<26;i++) {
        	if(present[i]) {
        		start=i;
        		break;
        	}
        }
        if(start==-1) {
        	return true;
        }
        boolean[] visited = new boolean[26];
        dfsCircular(start,adj,visited);
        for(int i=0;i<26;i++) {
        	if(present[i] && !visited[i]) {
        		return false;
        	}
        }
		return true;
	}

	private static void dfsCircular(int start, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
		visited[start]=true;
		for(int neg:adj.get(start)) {
			if(!visited[neg]) {
				dfsCircular(neg, adj, visited);
			}
		}
	}
	
	public static boolean canBeChained1(String[] words) {
        int[] in = new int[26];
        int[] out = new int[26];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        ArrayList<Integer> charsUsed = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            adj.add(new ArrayList<>());
        }

        // Build graph
        for (String word : words) {
            int first = word.charAt(0) - 'a';
            int last = word.charAt(word.length() - 1) - 'a';

            out[first]++;
            in[last]++;
            adj.get(first).add(last);

            // store unique characters in list
            if (!charsUsed.contains(first)) charsUsed.add(first);
            if (!charsUsed.contains(last)) charsUsed.add(last);
        }

        // 1️⃣ In-degree == Out-degree
        for (int c : charsUsed) {
            if (in[c] != out[c]) return false;
        }

        // 2️⃣ Connectivity check using DFS
        int start = charsUsed.get(0); // pick first used character as start
        boolean[] visited = new boolean[26];
        dfs(start, adj, visited);

        // 3️⃣ Check all used characters visited
        for (int c : charsUsed) {
            if (!visited[c]) return false;
        }
        System.out.println(Arrays.toString(in));
        System.out.println(Arrays.toString(out));
        System.out.println(Arrays.toString(visited));

        return true;
    }

    private static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[node] = true;
        for (int nei : adj.get(node)) {
            if (!visited[nei]) dfs(nei, adj, visited);
        }
    }

	public static void main(String[] args) {
		GraphImle graphImle = new GraphImle();
		String[] arr1 = {"ab", "bc", "cd", "da"};  // ✅ Circle possible
        //String[] arr2 = {"ab", "bc", "cd"};        // ❌ Circle not possible

        System.out.println(canBeChained1(arr1)); // true
       // System.out.println(canBeChained1(arr2)); // false
	}
	
	public void addEdgeDirected(ArrayList<ArrayList<Integer>> adj, int src, int dest) {
		adj.get(src).add(dest);
	}


	private static void addEdge(int src, int dest, ArrayList<ArrayList<Integer>> adj) {
		adj.get(src).add(dest);
		adj.get(dest).add(src);
	}

}
