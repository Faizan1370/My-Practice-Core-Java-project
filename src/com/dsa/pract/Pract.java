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
				if (current.left != null) {
					queue.add(current.left);
				}
				if (current.right != null) {
					queue.add(current.right);
				}
			}

		}
	}

	static TNode removekeys(TNode root, int l, int r) {
		if (root == null) {
			return null;
		}
		TNode left = removekeys(root.left, l, r);
		TNode right = removekeys(root.right, l, r);
		if (root.data >= l && root.data <= r) {
			root.left = left;
			root.right = right;
			return root;
		} else if (root.data < l) {
			return right;
		} else {
			return left;
		}
	}

	static TNode removekeys1(TNode root, int l, int r) {
		if (root == null) {
			return null;
		}
		if (root.data < l) {
			return removekeys(root.right, l, r);
		}
		if (root.data > r) {
			return removekeys(root.left, l, r);
		}
		root.left = removekeys(root.left, l, r);
		root.right = removekeys(root.right, l, r);
		return root;

	}

	public static int maxSum(TreeNod root) {
		IncludeExcludePair max = maxSumUtil(root);
		return Math.max(max.include, max.exclude);
	}

	private static IncludeExcludePair maxSumUtil(TreeNod root) {
		if (root == null) {
			return new IncludeExcludePair(0, 0);
		}
		IncludeExcludePair left = maxSumUtil(root.left);
		IncludeExcludePair right = maxSumUtil(root.left);
		int include = root.data + left.include + right.include;
		int exclude = Math.max(left.include, left.exclude) + Math.max(right.include, right.exclude);
		return new IncludeExcludePair(include, exclude);
	}

	public ArrayList<Integer> leftView(TNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		leftViewUt(root, list, 0);
		return list;
	}

	private void leftViewUt(TNode root, ArrayList<Integer> list, int level) {
		if (root == null) {
			return;
		}
		if (list.size() == level) {
			list.add(root.data);
		}
		leftViewUt(root.left, list, level + 1);
		leftViewUt(root.right, list, level + 1);

	}

	public void printTopView(TNode root) {
		if (root == null) {
			return;
		}
		Map<Integer, TNode> map = new TreeMap<Integer, TNode>();
		Queue<HdNodeP> queue = new LinkedList<HdNodeP>();
		queue.add(new HdNodeP(0, root));

		while (!queue.isEmpty()) {
			HdNodeP pair = queue.poll();
			int hd = pair.hd;
			TNode node = pair.node;

			if (!map.containsKey(hd)) {
				map.put(hd, node);
			}
			if (node.left != null) {
				queue.add(new HdNodeP(hd - 1, node.left));
			}
			if (node.right != null) {
				queue.add(new HdNodeP(hd + 1, node.right));
			}
		}
	}

	int count = 0, largest = 0;

	public int kthLargest(TNode root, int k) {
		kthLargestUt(root, k);
		return largest;
	}

	private void kthLargestUt(TNode root, int k) {
		if (root == null) {
			return;
		}
		kthLargestUt(root.right, k);
		count++;
		if (count == k) {
			largest = root.data;
			return;
		}
		kthLargestUt(root.left, k);

	}

	public static ArrayList<Integer> KDistanceNodes(TNode root, int target, int k) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		HashMap<TNode, TNode> parentMap = new HashMap<TNode, TNode>();
		TNode targetNode = buildParentMap(root, parentMap, target);
		if (targetNode == null) {
			return list;
		}
		HashSet<TNode> visited = new HashSet<TNode>();
		Queue<TNode> queue = new LinkedList<TNode>();
		queue.add(targetNode);
		visited.add(targetNode);
		int dist = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			if (dist == k) {
				break;
			}
			for (int i = 0; i < size; i++) {
				TNode current = queue.poll();
				if (current.left != null && !visited.contains(current.left)) {
					visited.add(current.left);
					queue.add(current.left);
				}
				if (current.right != null && !visited.contains(current.right)) {
					visited.add(current.right);
					queue.add(current.right);
				}
				TNode parentNode = parentMap.get(current);
				if (parentNode != null && !visited.contains(parentNode)) {
					visited.add(parentNode);
					queue.add(parentNode);
				}
			}
			dist++;
		}
		for (TNode n : queue) {
			list.add(n.data);
		}
		return list;

	}

	private static TNode buildParentMap(TNode root, HashMap<TNode, TNode> parentMap, int target) {
		Queue<TNode> queue = new LinkedList<TNode>();
		queue.add(root);
		parentMap.put(root, null);
		TNode targetNode = null;

		while (!queue.isEmpty()) {
			TNode parent = queue.poll();
			if (parent.data == target) {
				targetNode = parent;
			}
			if (parent.left != null) {
				parentMap.put(parent.left, parent);
				queue.add(parent.left);
			}
			if (parent.right != null) {
				parentMap.put(parent.right, parent);
				queue.add(parent.right);
			}
		}
		return targetNode;
	}

	public TNode insert(TNode node, int data) {
		if (node == null) {
			return new TNode(data);
		}
		if (node.data > data) {
			node.left = insert(node.left, data);
		} else if (node.data < data) {
			node.right = insert(node.right, data);
		} else {
			return node;
		}
		return node;
	}

	public TNode insertItrative(TNode node, int data) {
		TNode newNode = new TNode(data);

		TNode current = node;
		TNode parent = null;

		while (current != null) {
			parent = current;
			if (current.data > data) {
				current = current.left;
			} else if (current.data < data) {
				current = current.right;
			} else {
				return node;
			}
		}
		if (parent == null) {
			parent = newNode;
		} else if (parent.data > data) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		return node;
	}

	public boolean checkBst(TNode root) {
		return checkBstUtil(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	private boolean checkBstUtil(TNode node, long minValue, long maxValue) {
		if (node == null) {
			return true;
		}
		if (node.data <= minValue || node.data >= maxValue) {
			return false;
		}
		return checkBstUtil(node.left, minValue, node.data) && checkBstUtil(node.right, node.data, maxValue);
	}

	TNode prev = null;

	public boolean chBst(TNode node) {
		if (node == null) {
			return true;
		}
		if (!chBst(node.left)) {
			return false;
		}
		if (prev != null && node.data <= prev.data) {
			return false;
		}
		prev = node;
		return chBst(node.right);
	}

	public int floor(TNode root, int key) {
		int floor = -1;
		if (root == null) {
			return floor;
		}
		TNode current = root;
		while (current != null) {
			if (current.data == key) {
				return current.data;
			} else if (current.data > key) {
				current = current.left;
			} else {
				floor = current.data;
				current = current.right;
			}
		}
		return floor;

	}

	public int ceil(TNode root, int key) {
		int ceil = -1;
		if (root == null) {
			return ceil;
		}
		TNode current = root;
		while (current != null) {
			if (current.data == key) {
				return current.data;
			} else if (current.data > key) {
				ceil = current.data;
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return ceil;

	}

	TNode pr = null;
	TNode succ = null;

	public TNode inorderSuceeor(TNode root, int key) {
		if (root == null) {
			return null;
		}
		succUt(root, key);
		return succ;
	}

	private void succUt(TNode node, int key) {
		if (node == null || succ != null) {
			return;
		}
		succUt(node.left, key);
		if (pr != null && pr.data == key && succ == null) {
			succ = node;
			return;
		}
		succUt(node.right, key);

	}

	public TNode getSuccItrative(TNode root, int key) {
		if (root == null) {
			return null;
		}
		Stack<TNode> stack = new Stack<TNode>();
		TNode current = root;
		TNode prev = null;
		while (!stack.isEmpty() || current != null) {
			while (current != null) {
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();

			if (prev != null && prev.data == key) {
				return current;
			}
			prev = current;

			current = current.right;
		}
		return null;
	}

	int btime = 0;

	public int burnTreeRecursive(TNode root, int target) {
		Depth depth = new Depth(-1);
		burnUtil(root, target, depth);
		return btime;
	}

	private int burnUtil(TNode root, int target, Depth depth) {
		if (root == null) {
			return 0;
		}
		if (root.data == target) {
			depth.d = 0;
			return 1;
		}
		Depth ld = new Depth(-1);
		Depth rd = new Depth(-1);

		int lh = burnUtil(root.left, target, ld);
		int rh = burnUtil(root.right, target, rd);
		if (ld.d != -1) {
			btime = Math.max(btime, rh + ld.d + 1);
			depth.d = ld.d + 1;
		} else if (rd.d != -1) {
			btime = Math.max(btime, lh + rd.d + 1);
			depth.d = rd.d + 1;
		}

		return Math.max(lh, rh) + 1;

	}

	public ArrayList<Integer> verticalTraversal(TNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		Map<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		Queue<HdNodeP> queue = new LinkedList<HdNodeP>();
		queue.add(new HdNodeP(0, root));

		while (!queue.isEmpty()) {
			HdNodeP pair = queue.poll();
			int hd = pair.hd;
			TNode node = pair.node;

			map.computeIfAbsent(hd, k -> new ArrayList<>()).add(node.data);
			// VERY IMPORTANT 🔥
			if (node.left != null) {
				queue.add(new HdNodeP(hd - 1, node.left));
			}
			if (node.right != null) {
				queue.add(new HdNodeP(hd + 1, node.right));
			}
		}
		// can traversak the maop and put into list using addAll
		// Collect result
		for (ArrayList<Integer> val : map.values()) {
			list.addAll(val);
		}

		return list;
	}

	public static int btHeight(TNode root) {
		if (root == null) {
			return 0;
		}
		return bnHUtil(root);
	}

	private static int bnHUtil(TNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(bnHUtil(root.left), bnHUtil(root.right));
	}

	public static int heightItrtive(TNode root) {
		if (root == null) {
			return 0;
		}
		Queue<TNode> queue = new LinkedList<TNode>();
		queue.add(root);
		int height = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TNode current = queue.poll();

				if (current.left != null) {
					queue.add(current.left);
				}
				if (current.right != null) {
					queue.add(current.right);
				}
			}
			height++;
		}
		return height;
	}

	static boolean isIdentical(TNode r1, TNode r2) {
		if (r1 == null && r2 == null) {
			return true;
		}
		if (r1 == null || r2 == null) {
			return false;
		}
		return (r1.data == r2.data && isIdentical(r1.left, r2.left) && isIdentical(r1.right, r2.right));
	}

	public static boolean isIdenItrative(TNode r1, TNode r2) {
		if (r1 == null && r2 == null) {
			return true;
		}
		if (r1 == null || r2 == null) {
			return false;
		}
		Queue<TNode> queue = new LinkedList<TNode>();
		queue.add(r1);
		queue.add(r2);

		while (!queue.isEmpty()) {
			TNode curr1 = queue.poll();
			TNode curr2 = queue.poll();

			if (curr1 == null && curr2 == null) {
				continue;
			}
			if (curr1 == null || curr2 == null || curr1.data != curr2.data) {
				return false;
			}
			queue.add(curr1.left);
			queue.add(curr2.left);
			queue.add(curr1.right);
			queue.add(curr2.right);
		}
		return true;
	}

	static void mirror(TNode root) {
		if (root == null) {
			return;
		}
		mirror(root.left);
		mirror(root.right);

		TNode temp = root.left;
		root.left = root.right;
		root.right = temp;
	}

	public void mirrorItrative(TNode root) {
		if (root == null) {
			return;
		}
		Queue<TNode> queue = new LinkedList<TNode>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TNode current = queue.poll();

			TNode temp = current.left;
			current.left = current.right;
			current.right = temp;

			if (current.left != null) {
				queue.add(current.left);
			}
			if (current.right != null) {
				queue.add(current.right);
			}
		}
	}

	static boolean isSymmetric(TNode root) {
		if (root == null) {
			return true;
		}

		return isMiror(root.left, root.right);
	}

	private static boolean isMiror(TNode node1, TNode node2) {
		if (node1 == null && node2 == null) {
			return true;
		}
		if (node1 == null || node2 == null || node1.data != node2.data) {
			return false;
		}
		return isMiror(node1.left, node2.right) && isMiror(node1.right, node2.left);
	}
	
	public static boolean isSymtric(TNode root) {
		if(root==null) {
			return true;
		}
		Queue<TNode> queue = new LinkedList<TNode>();
		queue.add(root.left);
		queue.add(root.right);
		
		while(!queue.isEmpty()) {
			TNode node1 = queue.poll();
			TNode node2 = queue.poll();
			if (node1 == null && node2 == null) {
			continue;
			}
			if (node1 == null || node2 == null || node1.data != node2.data) {
				return false;
			}

	        // 🔥 Correct mirror pairing
	        queue.add(node1.left);
	        queue.add(node2.right);

	        queue.add(node1.right);
	        queue.add(node2.left);;
			
		}
		return true;
	}
	public static boolean isBalance(TNode root) {
		return isBalanceUtil(root)>0;
	}

	private static int isBalanceUtil(TNode root) {
	 if(root==null) {
		 return 0;
	 }
	 int lh = isBalanceUtil(root.left);
	 int rh= isBalanceUtil(root.right);
	 if(lh==-1 || rh==-1 || Math.abs(lh-rh)>1)
		return -1;
	 return Math.max(lh, rh)+1;
	}
	public static boolean isSumProperty(TreeNod root) {
		if(root==null) {
			return true;
		}
		 // leaf node
	    if (root.left == null && root.right == null) {
	        return true;
	    }
		int left =(root.left !=null)?root.left.data :0;
		int right =(root.right !=null)? root.right.data:0;
		if(root.data != left+right) {
			return false;
		}
		return isSumProperty(root.left) && isSumProperty(root.right);
		
	}
	public static boolean isSumItrative(TreeNod root) {
		if(root==null) {
			return true;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TreeNod current = queue.poll();
			if(current.left==null && current.right==null){
				continue;
			}
			if(current.data != current.left.data +current.right.data) {
				return false;
			}
			if(current.left!=null) {
				queue.add(current.left);
			}
			if(current.right!=null) {
				queue.add(current.right);
			}
		}
		return true;
		
		
	}
	
	public static int romanToInt(String s) {
		int sum=0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 1);
		for(int i=1;i<s.length();i++) {
			if(map.get(s.charAt(i))>map.get(s.charAt(i-1))) {
				sum -=map.get(s.charAt(i-1));
			}else {
				sum +=map.get(s.charAt(i-1));
			}
		}
		sum +=map.get(s.charAt(s.length()-1));
		return sum;
	}
	
	public static boolean validPara(String s) {
		Stack<Character> stack = new Stack<Character>();
		
		for(int i=0;i<s.length();i++) {
			char ch = s.charAt(i);
			if(ch =='(' || ch=='[' || ch== '{') {
				stack.push(ch);
			}else {
				 // ❗ check empty first
	            if (stack.isEmpty()) return false;

	            char top = stack.pop();

	            if (top == '(' && ch != ')') return false;
	            if (top == '{' && ch != '}') return false;
	            if (top == '[' && ch != ']') return false;
			}
		}
		return stack.isEmpty();
	}
	public static int[] countingBits(int n) {
		int[] res = new int[n+1];
		for(int i=0;i<=n;i++) {
			String binaryString = Integer.toBinaryString(i);
			res[i]= countOne(binaryString);
		}
		return res;
	}


	private static int countOne(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				count++;
			}
		}
		return count;
	}


	public static void main(String[] args) {

	}

}
