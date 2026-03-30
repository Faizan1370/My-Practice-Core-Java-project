package com.dsa.pract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.dsa.graph.again.rev.Dsuni;
import com.dsa.graph.revision2.dijkstra.VertexD;
import com.dsa.singlylinkedlist.revision.LisNodeRe;
import com.dsa.tree.TreeNode;
import com.dsa.tree.interview.question.geeksforgeeks.IncludeExcludePair;
import com.dsa.tree.interview.question.geeksforgeeks.TreeNod;

public class Pract {

	public static boolean bitwiseTrailingZero(int[] nums) {
		int count = 0;
		for (int num : nums) {
			if ((num & 1) == 0) {
				count++;
				if (count >= 2) {
					return true;
				}
			}
		}
		return false;
	}

	public static int assignCockie(int[] g, int[] s) {
		Arrays.sort(g);
		Arrays.sort(s);
		int children = 0;
		int cokcie = 0;
		while (children < g.length && cokcie < s.length) {
			if (g[children] <= s[cokcie]) {
				children++;
			}
			cokcie++;

		}
		return cokcie;

	}

	static boolean isSubtree(TNode root1, TNode root2) {
		if (root1 == null) {
			return false;
		}
		if (root2 == null) {
			return true;
		}
		if (areIdentical(root1, root2)) {
			return true;
		}
		return isSubtree(root1.left, root2) || isSubtree(root1.right, root2);

	}

	private static boolean areIdentical(TNode root1, TNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null) {
			return false;
		}
		return (root1.data == root2.data && areIdentical(root1.left, root2.left)
				&& areIdentical(root1.right, root2.right));
	}

	static int countIslands(char[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		boolean[][] visited = new boolean[m][n];
		int countIsland = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && grid[i][j] == 'L') {
					bfsIsland(i, j, grid, visited);
					countIsland++;
				}
			}
		}
		return countIsland;
	}

	private static void bfsIsland(int r, int c, char[][] grid, boolean[][] visited) {
		int m = grid.length;
		int n = grid[0].length;
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { r, c });
		visited[r][c] = true;
		int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
		int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };

		while (!queue.isEmpty()) {
			int[] pair = queue.poll();
			int row = pair[0];
			int col = pair[1];

			for (int i = 0; i < 8; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];
				if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc] && grid[nr][nc] == 'L') {
					visited[nr][nc] = true;
					queue.add(new int[] { nr, nc });
				}
			}
		}

	}

	public int findCheapestPrice1(int n, int[][] flights, int src, int dst, int k) {
		int[] dist = new int[n];
		int[] stopsArr = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(stopsArr, Integer.MAX_VALUE);
		ArrayList<ArrayList<int[]>> adj = new ArrayList<ArrayList<int[]>>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<int[]>());
		}
		for (int[] flight : flights) {
			adj.get(flight[0]).add(new int[] { flight[1], flight[2] });
		}
		PriorityQueue<NPriceStops> pq = new PriorityQueue<NPriceStops>();
		pq.add(new NPriceStops(src, 0, 0));
		dist[src] = 0;
		stopsArr[src] = 0;
		while (!pq.isEmpty()) {
			NPriceStops pair = pq.poll();
			int city = pair.node;
			int cost = pair.price;
			int stops = pair.stops;
			if (city == dst) {
				return cost;
			}
			if (stops > k) {
				continue;
			}
			for (int[] f : adj.get(city)) {
				int next = f[0];
				int price = f[1];

				int newCost = cost + price;
				int newStops = stops + 1;

				if (newCost < dist[next] || newStops < stopsArr[next]) {
					dist[next] = newCost;
					stopsArr[next] = newStops;
					pq.add(new NPriceStops(next, newCost, newStops));
				}
			}
		}
		return -1;
	}

	public int findCheapestPriceBell(int n, int[][] flights, int src, int dst, int k) {
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;

		for (int i = 0; i <= k; i++) {

			int[] temp = dist.clone();
			for (int[] f : flights) {
				int node = f[0];
				int dest = f[1];
				int price = f[2];
				if (dist[node] != Integer.MAX_VALUE && dist[node] + price < temp[dest]) {
					temp[dest] = dist[node] + price;
				}
			}
			dist = temp;
		}
		return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
	}

	public static int kruskalsMST(int V, int[][] edges) {
		Arrays.sort(edges, Comparator.comparingInt(e -> e[2]));
		int cost = 0, count = 0;
		Dsuni dsuni = new Dsuni(V);

		for (int[] ed : edges) {
			int u = ed[0], v = ed[1], w = ed[2];
			if (dsuni.find(u) != dsuni.find(v)) {
				dsuni.union(u, v);
				cost += w;
				count++;
				if (count == V - 1) {
					break;
				}
			}
		}
		return cost;

	}

	public static int minCost(int[] nums) {
		if (nums.length < 3) {
			return Arrays.stream(nums).sum(); // or define your behavior
		}
		int first = nums[0];
		int firstMin = Integer.MAX_VALUE;
		int secondMin = Integer.MAX_VALUE;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < firstMin) {
				secondMin = firstMin;
				firstMin = nums[i];
			} else if (nums[i] < secondMin) {
				secondMin = nums[i];
			}
		}

		return first + firstMin + secondMin;

	}

	public static int minCos1t(int[] nums) {
		if (nums.length < 3) {
			return Arrays.stream(nums).sum(); // or define your behavior
		}
		Arrays.sort(nums);
		return nums[0] + nums[1] + nums[2];
	}

	public static int[] setMismatch(int[] nums) {
		int[] ans = new int[2];
		HashSet<Integer> set = new HashSet<Integer>();
		for (int num : nums) {
			if (set.contains(num)) {
				ans[0] = num;
			} else {
				set.add(num);
			}
		}
		for (int i = 1; i <= nums.length; i++) {
			if (!set.contains(i)) {
				ans[1] = i;
				break;
			}
		}
		return ans;

	}

	public static int changingKey(String s) {
		int count = 0;
		s = s.toLowerCase();
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) != s.charAt(i - 1)) {
				count++;
			}
		}
		return count;

	}

	public static int antBoundry(int[] nums) {
		int sum = 0, count = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (sum == 0) {
				count++;
			}
		}
		return count;
	}

	public static boolean isPowerOfTwo(int n) {
		if (n <= 0) {
			return false;
		}
		int bitC = Integer.bitCount(n);
		if (bitC == 1) {
			return true;
		}
		return false;
	}

	public static int missingNumber(int[] nums) {
		int n = nums.length;
		int sum = n * (n + 1) / 2;
		for (int num : nums) {
			sum -= num;
		}
		return sum;
	}

	public int findProvinanceNum(int[][] isConnected) {
		int n = isConnected.length;
		DisjointSet disjointSet = new DisjointSet(n);
		int provinance = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (isConnected[i][j] == 1) {
					disjointSet.union(i, j);
				}
			}
		}
		for (int i = 0; i < n; i++) {
			if (disjointSet.find(i) == i) {
				provinance++;
			}
		}
		return provinance;
	}

	public boolean checkPairSum(TNode root, int target) {
		HashSet<Integer> set = new HashSet<Integer>();
		return findPairSum(root, target, set);

	}

	private boolean findPairSum(TNode root, int target, HashSet<Integer> set) {
		if (root == null) {
			return false;
		}
		if (findPairSum(root.left, target, set)) {
			return true;
		}
		if (set.contains(root.data - target)) {
			return true;
		}
		set.add(root.data);
		return findPairSum(root.right, target, set);
	}

	public static boolean pairSumItrative(TNode root, int target) {
		if (root == null) {
			return false;
		}
		HashSet<Integer> set = new HashSet<Integer>();
		Queue<TNode> queue = new LinkedList<TNode>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TNode current = queue.poll();

			/*
			 * if(current.left==null && current.right==null) { continue; }
			 */
			if (set.contains(target - current.data)) {
				return true;
			}
			set.add(current.data);
			if (current.left != null) {
				queue.add(current.left);

			}
			if (current.right != null) {
				queue.add(current.right);

			}
		}
		return false;

	}

	public static void transaformGreaterTree(TNode root) {
		if (root == null) {
			return;
		}
		int[] sum = { 0 };
		transformUtil(root, sum);
	}

	private static void transformUtil(TNode node, int[] sum) {
		if (node == null) {
			return;
		}
		transformUtil(node.right, sum);
		int temp = node.data;
		node.data = sum[0];
		sum[0] += temp;
		transformUtil(node.left, sum);

	}

	static ArrayList<Integer> extremeNodes(TNode root) {
		ArrayList<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Queue<TNode> queue = new LinkedList<TNode>();
		queue.add(root);
		boolean leftRight = true;
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TNode current = queue.poll();
				if (leftRight && i == size - 1) {
					res.add(current.data);
				} else if (!leftRight && i == 0) {
					res.add(current.data);
				}
				if (current.left != null) {
					queue.add(current.left);
				}
				if (current.right != null) {
					queue.add(current.right);
				}
			}
			leftRight = !leftRight;
		}
		return res;

	}

	public static ArrayList<Integer> extremeNode(TNode root) {
		ArrayList<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		boolean leftRight = true;
		dfsExtremeNode(root, res, 0, leftRight);
		return res;
	}

	private static void dfsExtremeNode(TNode root, ArrayList<Integer> res, int level, boolean leftRight) {
		if (root == null) {
			return;
		}

	}

	public static void connectNode(NodeWithNextRight root) {
		if (root == null) {
			return;
		}
		Queue<NodeWithNextRight> queue = new LinkedList<NodeWithNextRight>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				NodeWithNextRight current = queue.poll();
				 if (i < size - 1) {
				        current.nextRight = queue.peek();
				    } else {
				        current.nextRight = null;
				    }
				if(current .left !=null) {
					queue.add(current.left);
				}
				if(current .right !=null) {
					queue.add(current.right);
				}
			}

		}
	}
	 static TNode removekeys(TNode root, int l, int r) {
		 if(root==null) {
			 return null;
		 }
		 TNode left = removekeys(root.left, l, r);
		 TNode right = removekeys(root.right, l, r);
		 if(root.data >=l && root.data<=r) {
			 root.left=left;
			 root.right=right;
			 return root;
		 }else if(root.data<l) {
			 return right;
		 }else {
			 return left;
		 }
	 }
	 static TNode removekeys1(TNode root, int l, int r) {
		 if(root==null) {
			 return null;
		 }
		 if(root.data<l) {
			 return removekeys(root.right, l, r);
		 }
		 if(root.data>r) {
			 return removekeys(root.left, l, r);
		 }
		root.left= removekeys(root.left, l, r);
		root.right= removekeys(root.right, l, r);
		 return root;
		
	 }
	 public static int maxSum(TreeNod root) {
		 IncludeExcludePair max= maxSumUtil(root);
		 return Math.max(max.include, max.exclude);
	 }
	 

	private static IncludeExcludePair maxSumUtil(TreeNod root) {
		if(root==null) {
			return new IncludeExcludePair(0, 0);
		}
		IncludeExcludePair left =maxSumUtil(root.left);
		IncludeExcludePair right =maxSumUtil(root.left);
		int include = root.data +left.include+right.include;
		int exclude = Math.max(left.include, left.exclude) + Math.max(right.include, right.exclude);
		return new IncludeExcludePair(include, exclude);
	}

	public ArrayList<Integer> leftView(TNode root){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(root==null) {
			return list;
		}
		leftViewUt(root,list,0);
		return list;
	}

	private void leftViewUt(TNode root, ArrayList<Integer> list, int level) {
		if(root==null) {
			return;
		}
		if(list.size()==level) {
			list.add(root.data);
		}
		leftViewUt(root.left, list, level+1);
		leftViewUt(root.right, list, level+1);
		
	}
	public void printTopView(TNode root) {
		if(root==null) {
			return;
		}
		Map<Integer,TNode> map = new TreeMap<Integer, TNode>();
		Queue<HdNodeP> queue = new LinkedList<HdNodeP>();
		queue.add(new HdNodeP(0, root));
		
		while(!queue.isEmpty()) {
			HdNodeP pair = queue.poll();
			int hd=pair.hd;
			TNode node = pair.node;
			
			if(!map.containsKey(hd)) {
				map.put(hd, node);
			}
			if(node.left !=null) {
				queue.add(new HdNodeP(hd-1, node.left));
			}
			if(node.right !=null) {
				queue.add(new HdNodeP(hd+1, node.right));
			}
		}
	}

	public static void main(String[] args) {

	}

}
