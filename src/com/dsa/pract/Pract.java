package com.dsa.pract;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dsa.graph.again.rev.Dsuni;
import com.dsa.graph.again.rev.VWg;
import com.dsa.graph.interview.question.geeksforgeeks.SnakeCell;
import com.dsa.graph.interview.question.geeksforgeeks.revision.SnakeCellDist;
import com.dsa.graph.interview.question.geeksforgeeks.revision3.PriceNodePair;
import com.dsa.graph.kruskal.algo.Edge;
import com.dsa.graph.revision2.dijkstra.VertexD;
import com.dsa.singlylinkedlist.revision.LisNodeRe;
import com.dsa.takuforward.graph.DisjointSetDS;
import com.dsa.takuforward.graph.NodeIndexPair;
import com.dsa.tree.TreeNode;
import com.dsa.tree.interview.question.geeksforgeeks.IncludeExcludePair;
import com.dsa.tree.interview.question.geeksforgeeks.TreeNod;
import com.dsa.tree.interview.question.geeksforgeeks.TreeNodeNextRight;
import com.dsa.tree.interview.question.geeksforgeeks.revision.NodeInfo;
import com.dsa.tree.interview.question.geeksforgeeks.revision.TreeNodNextRightNode;
import com.dsa.tree.revision.IncExcludePair;
import com.dsa.tree.revision.TreeNodeRev;
import com.faizan.java8Prac.Customer;
import com.faizan.java8Prac.Employee;

public class Pract {

	static boolean findTarget(TNode root, int target) {
		if (root == null) {
			return false;
		}
		HashSet<Integer> set = new HashSet<Integer>();
		return targetSum(root, set, target);
	}

	private static boolean targetSum(TNode root, HashSet<Integer> set, int target) {
		if (root == null) {
			return false;
		}
		if (targetSum(root.left, set, target)) {
			return true;
		}
		if (set.contains(target - root.data)) {
			return true;
		}
		set.add(root.data);
		return targetSum(root.right, set, target);
	}

	static boolean findTargetItrative(TNode root, int target) {

		if (root == null) {
			return false;
		}
		HashSet<Integer> set = new HashSet<Integer>();
		Queue<TNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TNode current = queue.poll();
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

	static int sum = 0;

	static void transformToGreaterSumTree(TNode root) {
		buildGreaterSumTree(root);
	}

	private static void buildGreaterSumTree(TNode root) {
		if (root == null) {
			return;
		}
		buildGreaterSumTree(root.right);
		int temp = root.data;
		root.data = sum;
		sum += temp;
		buildGreaterSumTree(root.left);

	}

	static int getMaxSum(TNode root) {
		IncExcludePair pair = maxNonAjacentSum(root);
		return Math.max(pair.include, pair.exclude);
	}

	private static IncExcludePair maxNonAjacentSum(TNode root) {
		if (root == null) {
			return new IncExcludePair(0, 0);
		}
		IncExcludePair left = maxNonAjacentSum(root.left);
		IncExcludePair right = maxNonAjacentSum(root.right);
		int incldue = root.data + left.exclude + right.exclude;
		int exclude = Math.max(left.include, left.exclude) + Math.max(right.include, right.exclude);

		return new IncExcludePair(incldue, exclude);
	}

	static ArrayList<Integer> TopoSort(ArrayList<ArrayList<Integer>> adj) {
		int V = adj.size();
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[V];

		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				dfs(i, adj, visited, stack);
			}
		}
		ArrayList<Integer> list = new ArrayList<>();
		while (!stack.isEmpty()) {
			list.add(stack.pop());
		}

		return list;
	}

	private static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
		visited[node] = true;

		for (int neg : adj.get(node)) {
			if (!visited[neg]) {
				dfs(neg, adj, visited, stack);
			}
		}
		stack.push(node);

	}

	static boolean canFinish(int n, int[][] prerequisites) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (int[] pre : prerequisites) {
			int dest = pre[0];
			int src = pre[1];
			adj.get(src).add(dest);
		}

		boolean[] visited = new boolean[n];
		boolean[] recStack = new boolean[n];

		for (int i = 0; i < n; i++) {
			if (!visited[i] && dfsTask(i, adj, visited, recStack)) {
				return false;
			}
		}
		return true;

	}

	private static boolean dfsTask(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] recStack) {
		visited[node] = true;
		recStack[node] = true;

		for (int neg : adj.get(node)) {
			if (!visited[neg]) {
				if (dfsTask(neg, adj, visited, recStack)) {
					return true;
				}
			} else if (recStack[neg]) {
				return true;
			}
		}
		recStack[node] = false;
		return false;
	}

	static int[] scheduleTask(int n, int[][] prerequisites) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (int[] pre : prerequisites) {
			int dest = pre[0];
			int src = pre[1];
			adj.get(src).add(dest);
		}

		boolean[] visited = new boolean[n];
		boolean[] recStack = new boolean[n];
		Stack<Integer> stack = new Stack<Integer>();

		for (int i = 0; i < n; i++) {
			if (!visited[i] && dfsSchduleTask(i, adj, visited, recStack, stack)) {
				return new int[] { 0 };
			}
		}
		int[] result = new int[n];
		int idx = 0;

		while (!stack.isEmpty()) {
			result[idx++] = stack.pop();
		}

		return result;

	}

	private static boolean dfsSchduleTask(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited,
			boolean[] recStack, Stack<Integer> stack) {
		visited[node] = true;
		recStack[node] = true;

		for (int neg : adj.get(node)) {
			if (!visited[neg]) {
				if (dfsSchduleTask(neg, adj, visited, recStack, stack)) {
					return true;
				}
			} else if (recStack[neg]) {
				return true;
			}
		}
		recStack[node] = false;
		stack.push(node);
		return false;
	}

	public static int largestBst(TNode root) {
		if (root == null) {
			return 0;
		}
		if (isValid(root, Long.MAX_VALUE, Long.MIN_VALUE)) {
			return size(root);
		}
		return Math.max(largestBst(root.left), largestBst(root.right));
	}

	private static int size(TNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + size(root.left) + size(root.right);
	}

	private static boolean isValid(TNode root, long maxValue, long minValue) {
		if (root == null) {
			return true;
		}
		if (root.data <= minValue || root.data >= maxValue) {
			return false;
		}
		return isValid(root.left, root.data, minValue) && isValid(root.right, maxValue, root.data);
	}

	public static ArrayList<Integer> printExtreameNode(TNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		Queue<TNode> queue = new LinkedList<TNode>();
		queue.add(root);

		boolean leftRight = true;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TNode current = queue.poll();

				if (leftRight && i == size - 1) {
					list.add(current.data);
				} else if (!leftRight && i == 0) {
					list.add(current.data);
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
		return list;
	}

	static ArrayList<Integer> kahns(ArrayList<ArrayList<Integer>> adj) {
		int V = adj.size();
		int[] indegree = new int[V];
		for (int u = 0; u < V; u++) {
			for (int n : adj.get(u)) {
				indegree[n]++;
			}
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < V; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}
		ArrayList<Integer> topo = new ArrayList<>();
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			topo.add(curr);
			for (int neg : adj.get(curr)) {
				if (--indegree[neg] == 0) {
					queue.add(neg);
				}
			}
		}
		return topo;

	}

	static boolean checkCycle(ArrayList<ArrayList<Integer>> adj) {
		int V = adj.size();
		int[] indegree = new int[V];
		for (int u = 0; u < V; u++) {
			for (int n : adj.get(u)) {
				indegree[n]++;
			}
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < V; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}
		int count = 0;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			count++;
			for (int neg : adj.get(curr)) {
				if (--indegree[neg] == 0) {
					queue.add(neg);
				}
			}
		}
		return count != V;

	}

	public static void builNextRightItrative(TreeNodNextRightNode root) {
		if (root == null) {
			return;
		}
		Queue<TreeNodNextRightNode> queue = new LinkedList<TreeNodNextRightNode>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNodNextRightNode prev = null;
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TreeNodNextRightNode current = queue.poll();
				if (prev != null) {
					prev.nextRight = current;
				}
				prev = current;
				if (current.left != null) {
					queue.add(current.left);
				}
				if (current.right != null) {
					queue.add(current.right);
				}
			}
			if (prev != null) {
				prev.nextRight = null;
			}

		}

	}

	TNode prev = null, head = null;

	void BinaryTree2DoubleLinkedList(TNode root) {
		if (root == null) {
			return;
		}
		BinaryTree2DoubleLinkedList(root.left);
		if (prev == null) {
			head = root;
		} else {
			prev.right = root;
			root.left = prev;
		}
		prev = root;
		BinaryTree2DoubleLinkedList(root.right);

	}

	int maxSum = Integer.MIN_VALUE;

	public int maxPathSum(TNode root) {
		maxSum = Integer.MIN_VALUE;
		maxPathSumUtil(root);
		return maxSum;
	}

	private int maxPathSumUtil(TNode root) {
		if (root == null) {
			return 0;
		}
		int left = Math.max(0, maxPathSumUtil(root.left));
		int right = Math.max(0, maxPathSumUtil(root.right));
		int current = root.data + left + right;
		maxSum = Math.max(maxSum, current);
		return root.data + Math.max(left, right);
	}

	static TNode sortedListToBST(LNode head) {
		ArrayList<Integer> list = new ArrayList<Integer>();

		while (head != null) {
			list.add(head.data);
			head = head.next;
		}

		return buildBst(list, 0, list.size() - 1);
	}

	private static TNode buildBst(ArrayList<Integer> list, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = (start + end + 1) / 2;
		TNode rootNode = new TNode(list.get(mid));
		rootNode.left = buildBst(list, start, mid - 1);
		rootNode.right = buildBst(list, mid + 1, end);
		return rootNode;
	}

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
		PriorityQueue<NdStops> pq = new PriorityQueue<NdStops>();
		pq.add(new NdStops(src, 0, 0));
		dist[src] = 0;
		stops[src] = 0;

		while (!pq.isEmpty()) {
			NdStops pair = pq.poll();
			int node = pair.vertex;
			int price = pair.price;
			int stop = pair.stops;

			if (node == dst) {
				return price;
			}
			if (stop > k) {
				continue;
			}

			for (int[] neg : adj.get(node)) {
				int negNode = neg[0];
				int negPrice = neg[1];

				int newCost = price + negPrice;
				int newStop = stop + 1;

				if (newCost < dist[negNode] || newStop < stops[negNode]) {
					dist[negNode] = newCost;
					stops[negNode] = newStop;
					pq.add(new NdStops(negNode, dist[negNode], stops[negNode]));
				}

			}

		}
		return -1;
	}

	public int findCheapestPriceBellManFord(int n, int[][] flights, int src, int dst, int k) {
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;
		for (int i = 0; i <= k; i++) {
			int[] temp = dist.clone();

			for (int[] flight : flights) {
				int u = flight[0];
				int v = flight[1];
				int cost = flight[2];

				if (dist[u] != Integer.MAX_VALUE && dist[u] + cost < temp[v]) {
					temp[v] = dist[u] + cost;
				}
			}
			dist = temp;
		}
		return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
	}

	public TNode deleteNode1(TNode root, int key) {
		return deleteNodeUtil(root, key);

	}

	private TNode deleteNodeUtil(TNode root, int key) {
		if (root == null) {
			return null;
		}
		if (root.data > key) {
			root.left = deleteNodeUtil(root.left, key);
		} else if (root.data < key) {
			root.right = deleteNodeUtil(root.right, key);
		} else {
			if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			}
			root.data = findMin(root.right);
			root.right = deleteNodeUtil(root.right, root.data);
		}
		return root;
	}

	private int findMin(TNode root) {
		while (root.left != null) {
			root = root.left;
		}
		return root.data;
	}

	public static int maxPathSumLeaf(TNode root) {
		if (root == null) {
			return 0;
		}
		int[] maxSum = { Integer.MIN_VALUE };
		maxPathSumUt(root, 0, maxSum);
		return maxSum[0];
	}

	private static void maxPathSumUt(TNode root, int currentSum, int[] maxSum) {
		if (root == null) {
			return;
		}
		currentSum += root.data;
		if (root.left == null && root.right == null) {
			maxSum[0] = Math.max(maxSum[0], currentSum);
		}
		maxPathSumUt(root.left, currentSum, maxSum);
		maxPathSumUt(root.right, currentSum, maxSum);

	}

	public static int maxPathSumLeaf1(TNode root) {
		if (root == null) {
			return 0;
		}
		int maxSum = Integer.MIN_VALUE;
		Queue<NodeSumPair> queue = new LinkedList<NodeSumPair>();
		queue.add(new NodeSumPair(root, 0));

		while (!queue.isEmpty()) {
			NodeSumPair pair = queue.poll();
			TNode node = pair.node;
			int currentSum = pair.sum;
			if (node.left == null && node.right == null) {
				maxSum = Math.max(maxSum, currentSum);
			}
			if (node.left != null) {
				queue.add(new NodeSumPair(node.left, currentSum + node.left.data));
			}
			if (node.right != null) {
				queue.add(new NodeSumPair(node.right, currentSum + node.right.data));
			}
		}
		return maxSum;
	}

	static int countPaths(int n, int[][] edgeList, int source, int destination) {
		int[] count = { 0 };
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (int[] edge : edgeList) {
			adj.get(edge[0]).add(edge[1]);
		}
		boolean[] visited = new boolean[n + 1];
		dfsPathCount(source, destination, visited, adj, count);
		return count[0];
	}

	private static void dfsPathCount(int node, int destination, boolean[] visited, ArrayList<ArrayList<Integer>> adj,
			int[] count) {
		visited[node] = true;
		if (node == destination) {
			count[0]++;
			return;
		}

		for (int neg : adj.get(node)) {
			if (!visited[neg]) {
				dfsPathCount(neg, destination, visited, adj, count);
			}
		}
		visited[node] = false;

	}

	static int countPathsBFS(int n, int[][] edgeList, int source, int destination) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}

		for (int[] edge : edgeList) {
			adj.get(edge[0]).add(edge[1]);
		}

		int count = 0;

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(source);

		while (!queue.isEmpty()) {
			int node = queue.poll();
			if (node == destination) {
				count++;
				continue;
			}

			for (int neg : adj.get(node)) {
				queue.add(neg);
			}
		}
		return count;
	}

	public static int[] rotateByK(int[] nums, int k) {
		int n = nums.length;
		if (n <= k || n == 0 || k <= 0) {
			System.out.println("invalid");
		}
		if (n == 0)
			return nums;
		k = k % n;
		reverse(nums, 0, n - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, n - 1);
		return nums;
	}

	private static void reverse(int[] nums, int start, int end) {
		if (start > end) {
			return;
		}
		int temp = nums[start];
		nums[start] = nums[end];
		nums[end] = temp;
		start++;
		end--;

	}

	public static void findMaxSumSubArray() {
		int currentSum = 0, maxSum = 0;
		int[] array = { 3, -2, -3, 4, 7 };
		for (int i = 0; i < array.length; i++) {
			currentSum += array[i];
			if (currentSum > maxSum) {
				maxSum = currentSum;
			}
			if (currentSum < 0) {
				currentSum = 0;
			}
		}
	}

	public static void findMaxSumSubArray1() {
		int[] array = { 3, -2, -3, 4, 7 };
		int currentSum = array[0];
		int maxSum = array[0];

		for (int i = 1; i < array.length; i++) {
			currentSum = Math.max(array[i], currentSum + array[i]);
			maxSum = Math.max(maxSum, currentSum);
		}

	}

	public static ArrayList<int[]> indices(int[] array, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		ArrayList<int[]> list = new ArrayList<int[]>();
		for (int i = 0; i < array.length; i++) {
			int compliment = target - array[i];
			if (map.containsKey(compliment)) {
				list.add(new int[] { map.get(compliment), i });
			}
			map.put(array[i], i);
		}
		return list;
	}

	public static int kthLargest(int[] nums, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for (int i = 0; i < nums.length; i++) {
			queue.add(nums[i]);
			if (queue.size() > k) {
				queue.poll();
			}
		}
		return queue.peek();
	}

	public static int kthSmallest(int[] nums, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>((a, b) -> b - a);
		for (int i = 0; i < nums.length; i++) {
			queue.add(nums[i]);
			if (queue.size() > k) {
				queue.poll();
			}
		}
		return queue.peek();
	}

	public static int[] twoSumSortedArray(int[] nums, int target) {
		int start = 0, end = nums.length - 1;
		while (start < end) {
			if ((nums[start] + nums[end] == target)) {
				return new int[] { start, end };
			} else if ((nums[start] + nums[end] > target)) {
				end--;
			} else {
				start++;
			}
		}
		return new int[] { -1, -1 };
	}

	public static void removeDeuplicate() {
		int[] array = { 7, 8, 3, 7, 4 };
		int[] result = new int[array.length];
		int k = 0;
		for (int i = 0; i < array.length; i++) {
			boolean isDup = false;
			for (int j = 0; j < k; j++) {
				if (array[i] == result[j]) {
					isDup = true;
					break;
				}
			}
			if (!isDup) {
				result[k] = array[i];
				k++;
			}
		}

	}

	static int countSingle(TNode root) {
		int[] count = { 0 };

		countSingleValue(root, count);
		return count[0];
	}

	private static boolean countSingleValue(TNode root, int[] count) {
		if (root == null) {
			return true;
		}
		boolean left = countSingleValue(root.left, count);
		boolean right = countSingleValue(root.right, count);

		if (!left || !right) {
			return false;
		}
		if (root.left != null && root.data != root.left.data) {
			return false;
		}
		if (root.right != null && root.data != root.right.data) {
			return false;
		}
		count[0]++;
		return true;

	}

	static int countSingleItrative(TNode root) {
		Stack<TNode> stack = new Stack<TNode>();
		TNode current = root;
		int count = 0;
		while (!stack.isEmpty() || current != null) {
			while (current.left != null) {
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();
			if (current.left != null && current.left.data != current.data) {
				continue;
			}
			if (current.right != null && current.right.data != current.data) {
				continue;
			}
			count++;
			current = current.right;

		}
		return count;
	}

	static boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
		int V = adj.size();

		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (!visited[i])
				if (dfsCyclye(i, visited, adj, -1)) {
					return true;
				}
		}
		return false;
	}

	private static boolean dfsCyclye(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj, int parent) {
		visited[node] = true;

		for (int neg : adj.get(node)) {
			if (!visited[neg]) {
				if (dfsCyclye(neg, visited, adj, node)) {
					return true;
				}
			} else if (neg != parent) {
				return true;
			}
		}
		return false;
	}

	public static void removeDeuplicate1() {
		int[] array = { 7, 8, 3, 7, 4 };
		int[] result = new int[array.length];
		int[] seen = new int[101];
		int k = 0;
		for (int i = 0; i < array.length; i++) {
			if (seen[array[i]] == 0) {
				seen[array[i]] = 1;
				result[k] = array[i];
				k++;
			}
		}

	}

	public static void removeDeuplicate2() {
		int[] array = { 7, 8, 3, 7, 4 };
		int[] result = new int[array.length];
		boolean[] seen = new boolean[256];
		int k = 0;
		for (int i = 0; i < array.length; i++) {
			if (!seen[array[i]]) {
				seen[array[i]] = true;
				result[k] = array[i];
				k++;
			}
		}

	}

	public static void insertElement() {
		int[] array = { 3, 5, 7, 2, 9 };
		int element = 6;
		int position = 3;

		for (int i = array.length - 1; i > position - 1; i--) {
			array[i] = array[i - 1];
		}
		array[position - 1] = element;

	}

	public static void deleteElement() {
		int[] array = { 3, 5, 7, 2, 9 };
		int delete = 5;

		for (int i = 0; i < array.length; i++) {
			if (array[i] == delete) {
				for (int j = i; j < array.length - 1; j++) {
					array[j] = array[j + 1];
				}
				break;
			}
		}

	}

	public static void movePosNeg() {
		int[] array = { -3, 5, -7, 2, 9 };
		int j = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] < 0) {
				if (i != j) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
				j++;
			}
		}

	}

	public static void selectionSort() {
		int[] array = { 10, 5, 7, 2, 9 };

		for (int i = 0; i < array.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[minIndex] > array[j]) {
					minIndex = j;
				}
			}
			int temp = array[i];
			array[i] = array[minIndex];
			array[minIndex] = temp;
		}
	}

	public static void insertionSort() {
		int[] array = { 3, 5, 7, 2, 9 };
		for (int i = 1; i < array.length; i++) {
			int key = array[i];
			int j = i - 1;
			while (j >= 0 && array[j] > key) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = key;
		}
	}

	public static boolean palinDrom(int n) {
		int temp = n;
		int sum = 0;

		while (n > 0) {
			int r = n % 10;
			sum = sum * 10 + r;
			n /= 10;
		}
		if (temp == sum) {
			return true;
		} else {
			return false;
		}
	}

	public static int romanToInteger(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 100);
		int result = 0;
		for (int i = 1; i < s.length(); i++) {
			if (map.get(s.charAt(i - 1)) > map.get(s.charAt(i))) {
				result -= map.get(s.charAt(i - 1));
			} else {
				result += map.get(s.charAt(i - 1));
			}
		}
		return result;

	}

	public static boolean validPara(String s) {
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '(' || ch == '{' || ch == '[') {
				stack.push(ch);
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				char top = stack.pop();
				if (ch == ')' && top != '(') {
					return false;
				}
				if (ch == '}' && top != '{') {
					return false;
				}
				if (ch == ']' && top != '[') {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

	public static int[] bitCount(int n) {
		int[] result = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			int bitCount = Integer.bitCount(i);
			result[i] = bitCount;
		}
		return result;
	}

	public static int[] mergeSortedArray(int[] nums, int[] nums2, int m, int n) {
		int p1 = m - 1;
		int p2 = n - 1;
		int p3 = nums.length - 1;
		while (p3 >= 0) {
			int element1 = (p1 >= 0) ? nums[p1] : Integer.MIN_VALUE;
			int element2 = (p2 >= 0) ? nums2[p2] : Integer.MIN_VALUE;
			if (element1 > element2) {
				nums[p3] = element1;
				p3--;
				p1--;
			} else {
				nums[p3] = element2;
				p3--;
				p2--;
			}
		}
		return nums2;
	}

	public static void evenNumber() {
		List<Integer> list = List.of(2, 4, 7, 9);
		list.stream().filter(num -> num % 2 == 0).forEach(num -> System.out.println(num));

	}

	public static void firstNonRepeatingChar() {
		String str = "faizan";
		Character character = str.chars().mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(entry -> entry.getValue() == 1L).map(entry -> entry.getKey()).findFirst().get();

	}

	public static void firstRepeatingChar() {
		String str = "faizan";
		Character character = str.chars().mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(entry -> entry.getValue() > 1L).map(entry -> entry.getKey()).findFirst().get();

	}

	public static void firstNonRepeatingChar1() {
		String str = "faizan";
		Character character = str.chars().mapToObj(ch -> (char) ch).filter(ch -> str.indexOf(ch) == str.lastIndexOf(ch))
				.findFirst().get();

	}

	public static void firstRepeatingChar1() {
		String str = "faizan";
		Character character = str.chars().mapToObj(ch -> (char) ch).filter(ch -> str.indexOf(ch) != str.lastIndexOf(ch))
				.findFirst().get();

	}

	public static void sortElement() {
		List<Integer> list = Arrays.asList(5, 7, 3, 89, 23, 54);
		list.stream().sorted().forEach(num -> System.out.println(num));

	}

	public static void cubeElemenetGreaterThanValue() {
		List<Integer> list = Arrays.asList(1, 7, 8, 2);
		list.stream().filter(num -> num * num * num > 50).forEach(num -> System.out.println(num));
	}

	public static void cubeElemenetGreaterThanValue1() {
		List<Integer> list = Arrays.asList(1, 7, 8, 2);
		list.stream().map(num -> num * num * num).filter(num -> num > 50).forEach(num -> System.out.println(num));
	}

	public static void mergerTwoStream() {
		List<Integer> list1 = Arrays.asList(4, 7, 2, 10, 8, 5, 10, 20, 40);
		List<Integer> list2 = Arrays.asList(23, 56, 45, 90);
		Stream<Integer> concat = Stream.concat(list1.stream(), list2.stream());

	}

	public static void maxElement() {
		List<Integer> list = Arrays.asList(4, 7, 3, 7, 10, 19);
		Integer a = list.stream().sorted(Comparator.reverseOrder()).findFirst().get();
		Integer b = list.stream().max(Comparator.comparingInt(num -> ((Integer) num).intValue()).reversed()).get();
		int asInt = list.stream().mapToInt(num -> (Integer) num).max().getAsInt();
	}

	static List<Employee> list = new ArrayList<>();
	static {
		Employee emp = new Employee(100, null, "Software Engineer", 100);
		Employee emp1 = new Employee(700, "Arun", "QA tester", 500);
		Employee emp2 = new Employee(400, "Minhaz", "C++ developer", 300);
		Employee emp3 = new Employee(300, "Kamlesh", "Software Engineer", 700);
		list.add(emp);
		list.add(emp1);
		list.add(emp2);
		list.add(emp3);
	}

	public static void groupByDept() {
		Map<String, List<Employee>> collect = list.stream().collect(Collectors.groupingBy(emp -> emp.getDesignation()));
	}

	public static void convertIntoMap() {
		List<String> list = List.of("abc", "abrt");
		Map<String, Integer> collect = list.stream().collect(Collectors.toMap(val -> val, val -> val.length()));
	}

	public static void groupByDepWithHieghestSal() {
		list.stream()
				.collect(Collectors.groupingBy(emp -> emp.getDesignation(),
						Collectors.maxBy(Comparator.comparingInt(emp -> emp.getSalary()))))
				.entrySet().stream().forEach(entry -> System.out.println(entry));
	}

	public static void kthHiehestSal() {
		int k = 2;
		Employee employee = list.stream().sorted(Comparator.comparingInt(emp -> emp.getSalary())).skip(k - 1)
				.findFirst().get();

	}

	public static void convertArrayToStream() {
		int[] array = { 5, 7, 7, 9, 3, 6 };
		Stream<Integer> boxed = Arrays.stream(array).boxed();
		Stream<Integer> mapToObj = Arrays.stream(array).mapToObj(num -> (Integer) num);

	}

	public static void completableFuture() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
			System.out.println("hello");
		}, executorService).exceptionally((ex) -> {
			System.out.println(ex);
			return null;
		});
		completableFuture.join();
		executorService.shutdown();
	}

	public static void completableFutureSuplyAs() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
			return "hi";
		}, executorService).thenApply(str -> str.toUpperCase()).exceptionally((ex) -> {
			return null;
		});
		String join = completableFuture.join();
		executorService.shutdown();

	}

	static int isSumProperty(TNode root) {
		if (root == null) {
			return 0;
		}
		return propertySum(root);
	}

	private static int propertySum(TNode root) {
		if (root == null) {
			return 0;
		}
		int sum = 0;

		if (root.left != null) {
			sum += root.left.data;
		}

		if (root.right != null) {
			sum += root.right.data;
		}
		return ((root.data == sum) && (propertySum(root.left) == 1) && (propertySum(root.right)) == 1) ? 1 : 0;
	}

	public static boolean isSumProperty1(TreeNod root) {
		if (root == null) {
			return true;
		}
		return sumpProp(root);
	}

	private static boolean sumpProp(TreeNod root) {
		if (root == null) {
			return true;
		}
		if (root.left == null && root.right == null) {
			return true;
		}
		int lh = (root.left != null) ? root.left.data : 0;
		int rh = (root.right != null) ? root.right.data : 0;
		if (root.data != lh + rh) {
			return false;
		}
		return sumpProp(root.left) && sumpProp(root.right);
	}

	public static boolean sumPropIt(TNode root) {
		if (root == null) {
			return true;
		}
		Queue<TNode> queue = new LinkedList<TNode>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TNode current = queue.poll();

			if (current.left == null && current.right == null) {
				continue;
			}
			int sum = 0;
			if (current.left != null) {
				sum += current.left.data;
				queue.add(current.left);
			}
			if (current.right != null) {
				sum += current.right.data;
				queue.add(current.right);
			}
			if (current.data != sum) {
				return false;
			}
		}
		return true;
	}

	static int countIslands(char[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int count = 0;
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && grid[i][j] == 'L') {
					dfsCountIsland(i, j, grid, visited);
					count++;
				}
			}
		}
		return count;
	}

	private static void dfsCountIsland(int r, int c, char[][] grid, boolean[][] visited) {
		int m = grid.length;
		int n = grid[0].length;

		visited[r][c] = true;
		int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

		for (int i = 0; i < 8; i++) {
			int nr = dr[i] + r;
			int nc = dc[i] + c;

			if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc] && grid[nr][nc] == 'L') {
				dfsCountIsland(nr, nc, grid, visited);
			}
		}

	}

	public static int getMinDiceThrows(int[] moves) {
		int n = moves.length;
		if (n == 0)
			return 0;

		boolean[] visited = new boolean[n];
		Queue<SnakeCellDist> queue = new LinkedList<>();
		queue.add(new SnakeCellDist(0, 0));
		visited[0] = true;

		while (!queue.isEmpty()) {
			SnakeCellDist pair = queue.poll();
			int v = pair.vertex;
			if (v == n - 1) {
				return pair.dist;
			}

			for (int dice = 1; dice <= 6 && v + dice < n; dice++) {
				int next = v + dice;
				int dest = (moves[next] != -1) ? moves[next] : next;

				if (!visited[dest]) {
					visited[dest] = true;
					queue.add(new SnakeCellDist(dest, pair.dist + 1));
				}
			}

		}
		return -1;
	}

	public static void combineTwoPredicate() {
		Predicate<String> predicate = (str) -> str.startsWith("A");
		Predicate<String> predicate2 = (str) -> str.startsWith("A");
		Predicate<String> and = predicate.and(predicate2);
		System.out.println(and.test("App"));
	}

	public static void combineTwoConsumer() {
		Consumer<String> consumer = (str) -> System.out.println(str);
		Consumer<String> consumer2 = (str) -> System.out.println(str);
		Consumer<String> andThen = consumer.andThen(consumer2);
		andThen.accept("hi");
	}

	public static void combineTwoSuuplier() {
		Supplier<String> supplier = () -> "hi";
		Supplier<String> supplier2 = () -> "bro";

		Supplier<String> supplier3 = () -> supplier.get() + supplier2.get();
	}

	public static void combineTwoFunction() {
		Function<Integer, Integer> function = (x) -> x + 2;
		Function<Integer, Integer> function2 = (x) -> x + 3;
		Function<Integer, Integer> andThen = function.andThen(function2);
		andThen.apply(3);
	}

	public static void avgSal() {
		double asDouble = list.stream().filter(emp -> emp.getDesignation().contains("Soft")).map(emp -> emp.getSalary())
				.mapToDouble(sal -> sal.doubleValue()).average().getAsDouble();

	}

	public static void reduceSum() {
		List<Integer> list1 = Arrays.asList(3, 5, 7, 8);
		Integer reduce = list1.stream().reduce(0, (a, b) -> a + b);
		Integer collect = list1.stream().collect(Collectors.summingInt(num -> num.intValue()));
		int sum2 = list1.stream().mapToInt(num -> num.intValue()).sum();
		Integer integer = list1.stream().reduce(Integer::sum).get();
	}

	public static void optional() {
		Optional<Object> optional = Optional.empty();
		System.out.println(optional);
		list.stream().forEach((emp) -> {
			Optional.ofNullable(emp.getName()).orElse("faiz");
		});
	}

	public static void mapFlatMap() {
		List<com.faizan.java8Prac.Customer> collect = Stream
				.of(new Customer(54, "abc@abc.com", "abc", Arrays.asList(916123456, 896745230)),
						new Customer(87, "harsih@abc.com", "harish", Arrays.asList(916123690, 89645230)),
						new Customer(77, "jameel@abc.com", "jameel", Arrays.asList(875123456, 763745654)))
				.collect(Collectors.toList());
		collect.stream().flatMap(cs -> cs.getNumbers().stream()).forEach(num -> System.out.println(num));

	}

	public static int buySell(int[] prices) {
		int min = Integer.MAX_VALUE;
		int maxProfit = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < min) {
				min = prices[i];
			}
			int currentProfit = prices[i] - min;
			maxProfit = Math.max(maxProfit, currentProfit);
		}
		return maxProfit;
	}

	public static int singleNumber(int[] nums) {
		int result = 0;
		for (int num : nums) {
			result ^= num;
		}
		return result;

	}

	public static boolean duplicate2(int[] nums, int k) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = 1; j < nums.length; j++) {
				if (nums[i] == nums[j] && Math.abs(i - j) <= k) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean ranSomeNote(String ransomeNote, String magzize) {
		Map<Character, Long> map = magzize.chars().mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		for (int i = 0; i < ransomeNote.length(); i++) {
			if (map.containsKey(ransomeNote.charAt(i)) && map.get(ransomeNote.charAt(i)) >= 1) {
				map.put(ransomeNote.charAt(i), map.get(ransomeNote.charAt(i)) - 1);
			} else {
				return false;
			}
		}
		return true;

	}

	static boolean isSubtree(TreeNod root1, TreeNod root2) {
		if (root1 == null) {
			return false;
		}
		if (root2 == null) {
			return true;
		}
		if (subTree(root1, root2)) {
			return true;
		}
		return isSubtree(root1.left, root2) || isSubtree(root1.right, root2);
	}

	private static boolean subTree(TreeNod root1, TreeNod root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null || root1.data != root2.data) {
			return false;
		}
		return subTree(root1.left, root2.left) && subTree(root1.right, root2.right);
	}

	public static int inorderSucc(TNode root, int k) {
		int result = -1;
		TNode current = root;
		while (current != null) {
			if (current.data > k) {
				result = current.data;
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return result;
	}

	public static int inorderPred(TNode root, int k) {
		int result = -1;
		TNode current = root;

		while (current != null) {
			if (current.data > k) {
				current = current.left;
			} else {
				result = current.data;
				current = current.right;
			}
		}
		return result;
	}

	public static TNode findSucc(TNode root, int target) {
		TNode[] prev = new TNode[1];
		TNode[] succ = new TNode[1];
		findSuccRec(root, prev, succ, target);
		return succ[0];

	}

	private static void findSuccRec(TNode root, TNode[] prev2, TNode[] succ, int target) {
		if (root == null || succ[0] != null) {
			return;
		}
		findSuccRec(root.left, prev2, succ, target);
		if (target == prev2[0].data && succ[0] == null) {
			succ[0] = root;
			return;
		}
		prev2[0] = root;

		findSuccRec(root.right, prev2, succ, target);

	}

	public TNode findSuccIt(TNode root, int target) {
		if (root == null) {
			return null;
		}
		TNode prev = null;
		Stack<TNode> stack = new Stack<TNode>();
		TNode current = root;

		while (!stack.isEmpty() || current != null) {
			while (current != null) {
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();
			if (prev != null && prev.data == target) {
				return current;
			}
			prev = current;
			current = current.right;
		}
		return null;
	}

	public boolean checkBst(TNode root) {
		if (root == null) {
			return true;
		}
		return checkBstUt(root, Long.MAX_VALUE, Long.MIN_VALUE);
	}

	private boolean checkBstUt(TNode root, long maxValue, long minValue) {
		if (root == null) {
			return true;
		}
		if (root.data < minValue || root.data > maxValue) {
			return false;
		}
		return checkBstUt(root.left, root.data, minValue) && checkBstUt(root.right, maxValue, root.data);
	}

	public String kthDistinct(String[] arr, int k) {
		String string = Arrays.stream(arr)
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
				.entrySet().stream().filter(entry -> entry.getValue() == 1L).map(entry -> entry.getKey()).skip(k - 1)
				.findFirst().orElse("");
		return string;
	}

	public int smallestIndexWithequalVal(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			if (i % 10 == nums[i]) {
				return i;
			}
		}
		return -1;
	}

	public static boolean equivalentString(String word1, String word2) {

		if (word1.length() != word2.length()) {
			return false;
		}
		int[] freq1 = new int[100];
		int[] freq2 = new int[100];
		for (int i = 0; i < word1.length(); i++) {
			freq1[word1.charAt(i) - 'a']++;
		}
		for (int i = 0; i < word2.length(); i++) {
			freq2[word2.charAt(i) - 'a']++;
		}
		for (int i = 0; i < freq1.length; i++) {
			if ((freq1[i] - freq2[i]) > 3) {
				return false;
			}
		}
		return true;
	}

	public static int countWords(String[] word1, String[] word2) {
		Map<String, Long> map = Arrays.stream(word1)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		for (String word : word2) {
			if (map.containsKey(word)) {
				map.put(word, map.get(word) - 1);
			}
		}
		int count = 0;
		for (String word : word2) {
			if (map.containsKey(word) && map.get(word) == 0) {
				count++;
			}
		}
		return count;

	}

	public static int countWords1(String[] word1, String[] word2) {
		Map<String, Long> map = Arrays.stream(word1)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		Map<String, Long> map1 = Arrays.stream(word2)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		int count = 0;
		for (Map.Entry<String, Long> entry : map.entrySet()) {
			if (map.containsKey(entry.getKey()) && map1.containsKey(entry.getKey()) && map.get(entry.getKey()) == 1L
					&& map1.get(entry.getKey()) == 1L) {
				count++;
			}
		}
		return count;
	}

	public ArrayList<Integer> zigzagTrav(TNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		Queue<TNode> queue = new LinkedList<TNode>();
		queue.add(root);
		boolean leftRight = true;

		while (!queue.isEmpty()) {
			int size = queue.size();
			LinkedList<Integer> temp = new LinkedList<Integer>();
			for (int i = 0; i < size; i++) {
				TNode current = queue.poll();

				if (leftRight) {
					temp.addLast(current.data);
				} else {
					temp.addFirst(current.data);
				}
				if (current.left != null) {
					queue.add(current.left);
				}
				if (current.right != null) {
					queue.add(current.right);
				}
			}
			list.addAll(temp);
			leftRight = !leftRight;
		}
		return list;
	}

	static int spanningTree(int V, ArrayList<ArrayList<int[]>> adj) {
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
		queue.add(new int[] { 0, 0 });
		int mst = 0;
		boolean[] visited = new boolean[V];

		while (!queue.isEmpty()) {
			int[] pair = queue.poll();
			int node = pair[0];
			int wt = pair[1];
			if (visited[node]) {
				continue;
			}
			mst += wt;
			visited[node] = true;

			for (int[] neg : adj.get(node)) {
				if (!visited[neg[0]]) {
					queue.add(new int[] { neg[0], neg[1] });
				}
			}
		}
		return mst;

	}

	static ArrayList<ArrayList<Integer>> verticalOrder(TNode root) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		if (root == null) {
			return list;
		}
		Map<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		Queue<HdNPair> queue = new LinkedList<HdNPair>();
		queue.add(new HdNPair(0, root));

		while (!queue.isEmpty()) {
			HdNPair pair = queue.poll();

			if (map.containsKey(pair.hd)) {
				map.get(pair.hd).add(pair.node.data);
			} else {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(pair.node.data);
				map.put(pair.hd, temp);
			}
			if (pair.node.left != null) {
				queue.add(new HdNPair(pair.hd - 1, pair.node.left));
			}
			if (pair.node.right != null) {
				queue.add(new HdNPair(pair.hd + 1, pair.node.right));
			}
		}
		for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}

	static int[] dijkstra(ArrayList<ArrayList<int[]>> adj, int src) {
		int V = adj.size();
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);

		PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> a[1] - a[1]);
		queue.add(new int[] { src, 0 });
		dist[src] = 0;

		while (!queue.isEmpty()) {
			int[] pair = queue.poll();
			int node = pair[0];
			int dest = pair[1];

			if (dest > dist[node]) {
				continue;
			}

			for (int[] neg : adj.get(node)) {
				int negNode = neg[0];
				int negDist = neg[1];

				if (dist[node] + negDist < dist[negNode]) {
					dist[negNode] = dist[node] + negDist;
					queue.add(new int[] { negNode, dist[negNode] });
				}
			}
		}
		return dist;
	}

	public static ArrayList<Integer> boundryTraversal(TNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		if (!leaf(root)) {
			result.add(root.data);
		}
		collectLeft(root.left, result);
		collectLeaf(root, result);
		collectRight(root.right, result);
		return result;
	}

	private static void collectRight(TNode root, ArrayList<Integer> result) {
		if (root == null || leaf(root)) {
			return;
		}
		if (root.right != null) {
			collectRight(root.right, result);
		} else if (root.left != null) {
			collectRight(root.left, result);
		}
		result.add(root.data);

	}

	private static void collectLeaf(TNode root, ArrayList<Integer> result) {
		if (root == null) {
			return;
		}
		if (leaf(root)) {
			result.add(root.data);
			return;
		}
		collectLeaf(root.left, result);
		collectLeaf(root.right, result);

	}

	private static void collectLeft(TNode root, ArrayList<Integer> result) {
		if (root == null || leaf(root)) {
			return;
		}
		result.add(root.data);
		if (root.left != null) {
			collectLeft(root.left, result);
		} else if (root.right != null) {
			collectLeft(root.right, result);
		}

	}

	private static boolean leaf(TNode root) {

		return (root.left == null && root.right == null);
	}
	static int[] bellmanFord(int V, int[][] edges, int src) {
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src]=0;
		
		for(int i=0;i<V-1;i++) {
			for(int[] edge:edges) {
				int u=edge[0];
				int v=edge[1];
				int w=edge[2];
				
				if(dist[u] !=Integer.MAX_VALUE && dist[u]+w< dist[v]) {
					dist[v]=dist[u]+w;
				}
			}
		}
		    for(int[] edge : edges) {
		        int u = edge[0];
		        int v = edge[1];
		        int wt = edge[2];

		        if(dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
		            return new int[]{-1};  // Negative cycle detected
		        }
		    }
		return dist;
	}
	 static int prexIndex=0;
	 static TNode buildTree(int[] inorder, int[] preorder) {
		 Map<Integer, Integer> mp = new HashMap<>();
	        for (int i = 0; i < inorder.length; i++)
	            mp.put(inorder[i], i);
	        
	        return buildRecursive(mp,preorder,0,inorder.length-1);
		 
	 }


	private static TNode buildRecursive(Map<Integer, Integer> mp, int[] preorder, int start, int end) {
		if(start>end) {
			return null;
		}
		int rootData = preorder[prexIndex++];
		TNode rootNode = new TNode(rootData);
		int rootIndex = mp.get(rootData);
		rootNode.left= buildRecursive(mp, preorder, start, rootIndex-1);
		rootNode.right =buildRecursive(mp, preorder, rootIndex+1, end);
		return rootNode;
	}
	public static TNode construct(List<Integer> pre) {
		TNode root=null;
		for(int key:pre) {
			root=insertBST(root,key);
		}
		return root;
	}

	private static TNode insertBST(TNode root, int key) {
		if(root==null) {
			return new TNode(key);
		}else if(root.data>key) {
			root.left=insertBST(root.left, key);
		}else {
			root.right=insertBST(root.right, key);
		}
		return root;
	}
	 static ArrayList<Integer> dfs7(ArrayList<ArrayList<Integer>> adj) {
		 int V = adj.size();
		 boolean[] visited = new boolean[V];
		 Stack<Integer> stack = new Stack<Integer>();
		 for(int i=0;i<V;i++) {
			 if(!visited[i]) {
				 dfsTopo(i,adj,visited,stack);
			 }
		 }
		 ArrayList<Integer> list = new ArrayList<Integer>();
		 while(!stack.isEmpty()) {
				list.add(stack.pop());
			}
		return list;
		 
	 }

	private static void dfsTopo(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
		visited[node]=true;
		
		for(int neg:adj.get(node)) {
			if(!visited[neg]) {
				dfsTopo(neg, adj, visited, stack);
			}
		}
		stack.push(node);
	}
	public static ArrayList<Integer> topSortBFS(int V, int[][] edges){
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for(int i=0;i<V;i++) {
			adj.add(new ArrayList<Integer>());
		}
		for(int[] edge:edges) {
			adj.get(edge[0]).add(edge[1]);
		}
		int[] inDegree= new int[V];
		for (int[] edge : edges) {
		    inDegree[edge[1]]++;
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i=0;i<V;i++) {
			if(inDegree[i]==0) {
				queue.add(i);
			}
		}
		while(!queue.isEmpty()) {
			int current = queue.poll();
			ans.add(current);
			for(int neg:adj.get(current)) {
				if(--inDegree[neg]==0) {
					queue.add(neg);
				}
			}
		}
		return ans;
		
	}
	public static String firstPlaindramic(String[] words) {
		for(String word:words) {
			if(isPalindrome(word)) {
				return word;
		  }
		}
		return "";
	}

	private static boolean isPalindrome(String word) {
		int start=0,end=word.length()-1;
		while(start<end) {
			if(word.charAt(start) !=word.charAt(end)) {
				return false;
			}
		}
		return true;
	}

	
	public static int evenDigitSum(int num) {
		int count=0;
		for(int i=1;i<=num;i++) {
			if(sumDigits(i) % 2==0) {
				count++;
			}
		}
		return count;
	}
	private static int sumDigits(int i) {
		int sum=0;
		while(i >0) {
			int r= i %10;
			sum +=r;
			i /=10;
		}
		return sum;
	}
	public static String[] sortPeople(int[] height,String[] names) {
		Integer[] indices= new Integer[height.length];
		for(int i=0;i<height.length;i++) {
			indices[i]=i;
		}
		System.out.println(Arrays.toString(indices));
		Arrays.sort(indices,(a,b)->height[b]-height[a]);
		System.out.println(Arrays.toString(indices));
		String[] result = new String[names.length];
		for(int i=0;i<height.length;i++) {
			result[i]=names[indices[i]];
		}
		return result;
	}
	public static String[] sortPeopleUsingMap(int[] height,String[] names) {
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=0;i<height.length;i++) {
			map.put(names[i],height[i]);
		}
		Arrays.sort(names, (a, b) -> map.get(b) - map.get(a));
		
		
		return names;
		
	}
	public static String[] sortPeopleUsingMap1(int[] height,String[] names) {
		HashMap<Integer, String> map = new HashMap<>();
		for(int i=0;i<height.length;i++) {
			map.put(height[i],names[i]);
		}
		Arrays.sort(height);
		String[] result = new String[names.length];
		int idx=0;
		for(int i=height.length-1;i>=0;i--) {
			result[idx++] = map.get(height[i]);
		}
		
		
		return result;
		
	}
	public static int distinctAvg(int[] nums) {
		Arrays.sort(nums);
		int start=0,end=nums.length-1;
		HashSet<Integer> set = new HashSet<Integer>();
		while(start<end) {
			set.add(nums[start]+nums[end]);
			start++;
			end--;
		}
		return set.size();
	}
	public static boolean circularSentence(String sentence) {
		if(sentence.charAt(0) != sentence.charAt(sentence.length()-1)) {
			return false;
		}
		for(int i=0;i<sentence.length();i++) {
			if(Character.isWhitespace(sentence.charAt(i))) {
				if(i==0 || i == sentence.length() - 1) {
					return false;
				}
				if(sentence.charAt(i-1) != sentence.charAt(i+1)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static int maxValue(String[] strs) {
		int max=Integer.MIN_VALUE;
		for(String word:strs) {
			if(chDigits(word)) {
				max =Math.max(max, Integer.parseInt(word));
			}else {
				max= Math.max(max, word.length());
			}
		}
		return max;
	}

	private static boolean chDigits(String word) {
		int count=0;
		for(int i=0;i<word.length();i++) {
			if(Character.isDigit(word.charAt(i))) {
				count++;
			}
			
		}
		return count==word.length();
	}
	public static int countSimiliarPair(String[] words) {
		int count=0;
		for(int i=0;i<words.length;i++) {
			for(int j=i+1;j<words.length;j++) {
				if(checkEquivalent(words[i],words[j])) {
					count++;
				}
			}
		}
		return count;
		}

	private static boolean checkEquivalent(String string, String string2) {
		HashSet<Character> set = new HashSet<Character>();
		HashSet<Character> set1 = new HashSet<Character>();
		for(int i=0;i<string.length();i++) {
			set.add(string.charAt(i));
		}
		for(int i=0;i<string2.length();i++) {
			set1.add(string2.charAt(i));
		}
		
		return set.equals(set1);
	}
	public int  minCommonVal(int[] nums1,int[] nums2) {
		HashSet<Integer> set = new HashSet<Integer>();
		for(int num:nums1) {
			set.add(num);
		}
		for(int num:nums2) {
			if(set.contains(num)) {
				return num;
			}
		}
		return -1;
	}
	
	public static int alternatDigitSum(int num) {
		int sum=0;
		String strNum=num+"";
		sum += Character.getNumericValue(strNum.charAt(0));
		for(int i=1;i<strNum.length();i++) {
			if(i %2==0) {
				sum += Character.getNumericValue(strNum.charAt(i));
			}else {
				sum -= Character.getNumericValue(strNum.charAt(i));
			}
		}
		return sum;
	}
	public static int alternatDigitSum1(int num) {
		int sum =0;
		int sign=1;
		
		while(num>0) {
			int digit = num %10;
			sum +=sign*digit;
			sign *=-1;
			num /=10;
		}
		return sum;
	}
	public static int arrayConcat1(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}
		int start=0,end=nums.length-1;
		int concatSum=0;
		while(start<end) {
			 String strSum = nums[start]+""+nums[end];
			 concatSum += Integer.parseInt(strSum);
			 start++;
			 end--;
		}
		return concatSum;
	}
	
	public static int countVowel(String[] words) {
		int count = 0;
		String vowleString = "AEIOUaeiou";
		for(String word:words) {
			if(vowleString.indexOf(word.charAt(0))!=-1 &&(vowleString.indexOf(word.charAt(word.length()-1)) !=-1)){
				count++;
			}
		}
		return count;
		
	}
	public static int delyedTime(int arrivalTime, int delayTime) {
		int arrTime=0;
		arrTime = arrivalTime+delayTime;
		if(arrTime>=24) {
			arrTime -=24;
		}
		return arrTime;
	}
	public static int sumOfMultiples(int n) {
		int sum = 0;
		
		for(int i=1;i<=n;i++) {
			if(i % 3 ==0 || i % 5 ==0 || i % 7==0) {
				sum +=i;
			}
		}
		return sum;
		
	}
	public static int arrayConcat2(int[] nums) {
		int sum = 0;
		int i=0,j=nums.length-1;
		while(i<j) {
			sum += nums[i] *10 +nums[j];
			i++;
			j--;
		}
		return sum;
	}
	public static int isWinner(int[] player1, int[] player2) {
		if(countScore(player1)>countScore(player2)) {
			return 1;
		} else if (countScore(player1) < countScore(player2)) {
			return 2;
		} else {
			return 0;
		}
	}

	private static int countScore(int[] player) {
		int score=0;
		for(int i=0;i<player.length;i++) {
			if(i==0) {
				score +=player[i];
			}else if(i==1) {
				if(player[i-1]==10) {
					score += 2*player[i];
				}else {
					score +=player[i];
				}
			}else if(i>1) {
				if(player[i-1]==10 || player[i-2]==10) {
					score += 2*player[i];
				}else {
					score +=player[i];
				}
			}
		}
		return score;
	}
	public static int[] distictDiff(int[] nums) {
		int start=1,end=nums.length-1;
		System.out.println(end);
		int[] result = new int[nums.length];
		int idx=0;
		while(idx<nums.length) {
			result[idx++]=start-end;
			start++;
			end--;
		}
		return result;
	}
	public static int[] distictDiff1(int[] nums) {
		int[] result = new int[nums.length];
		for(int i=0;i<nums.length;i++) {
			
			HashSet<Integer> set = new HashSet<Integer>();
			for(int j=0;j<=i;j++) {
				set.add(nums[j]);
			}
			HashSet<Integer> set1 = new HashSet<Integer>();
			for(int k=i+1;k<nums.length;k++) {
				set1.add(nums[k]);
			}
			result[i]=set.size()-set1.size();
		}
		return result;
	}
	
	public static int totalDistanceTravlled(int mainTank,int additonalTank) {
		int totalDistance=0;
		
		while(mainTank>=5 && additonalTank>0) {
			mainTank =(mainTank-5)+1;
			totalDistance +=50;
			additonalTank--;
		}
		return totalDistance +=mainTank*10;
	}
	  public static boolean isGood(int[] nums) {
		  Arrays.sort(nums);
		  int max= nums[nums.length-1];
		  if(nums.length !=(max+1)) {
			  return false;
		  }
		  HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		  for(int i=0;i<nums.length;i++) {
			  if(map.containsKey(nums[i])) {
				  map.put(nums[i], map.get(nums[i])+1);
			  }else {
				  map.put(nums[i], 1);
			  }
		  }
		  ArrayList<Integer> list = new ArrayList<Integer>(map.values());
		  int count=0;
		  for(int num:list) {
			  if(num>=2) {
				  count++;
			  }
		  }
		  if(map.get(max)==2 && count==1) {
			  return true;
		  }
		return false;
	  }
	  public static List<String> splitWordsbySeprator(List<String> words,String seprator){
		   List<String> list = new ArrayList<String>();
		   for(String word:words) {
			   String[] wordArr =  word.split("["+seprator+"]");
			   for(String word1:wordArr) {
				    if(!word1.isEmpty())
					  list.add(word1);
				  }
		   }
		   return list;
	  }
	  
	  public static int Accountbalance(int purchaseAmount) {
		  int rem = purchaseAmount % 10;
		  int balance =10-rem;
		  if (purchaseAmount < 0) return 100;
		    if (purchaseAmount > 100) return 0;
		  if(rem >5) {
			  purchaseAmount +=balance;
		  }else {
			  purchaseAmount -=rem;
		  }
		  
		  return 100-purchaseAmount;
	  }
	  public static int maxSum(int[] nums) {
		  int maxSum=-1;
		  for(int i=0;i<nums.length;i++) {
			  for(int j=i+1;j<nums.length;j++) {
				  if(findMax(nums[i])==findMax(nums[j])) {
					  maxSum =Math.max(maxSum, nums[i]+nums[j]);
				  }
			  }
		  }
		return maxSum;
	  }
	  

	private static int findMax(int num) {
		int maxDigit=0;
		while(num !=0) {
			int r= num %10;
			maxDigit = Math.max(maxDigit, r);
			num /=10;
		}
		return maxDigit;
	}
	public static int maxEqualDigitSum(int[] nums) {
		int ans=0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(int num:nums) {
			int maxDisgit= findMax(num);
			if(map.containsKey(maxDisgit)) {
				ans = Math.max(ans, num+map.get(maxDisgit));
				map.put(maxDisgit, Math.max(map.get(maxDisgit), num));
			}else {
				map.put(maxDisgit, num);
			}
		}
		return ans;
	} 

	public static int minOprations(int[] nums,int k) {
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i=1;i<=k;i++) {
			set.add(i);
		}
		int count=0;
		for(int i = nums.length - 1; i >= 0; i--) {
			if(set.contains(nums[i])) {
				set.remove(nums[i]);
				if(set.isEmpty()) {
					break;
				}
			}
			count++;
		}
		return count;
	}
	public static int minOperationsCorrectCode(int[] nums, int k) {
			HashSet<Integer> set = new HashSet<Integer>();
			int count=0;
			for(int i = nums.length - 1; i >= 0; i--) {
				if(nums[i]<=k) {
					set.add(nums[i]);
				}
				count++;
				if(set.size()==k) {
					 return count;
				}
			}
			return count;
	}
	  static int findDist(TNode root, int a, int b) {
		  TNode lcaNode = findLCA(root,a,b);
		  int d1 = findDis(lcaNode,a,0);
		  int d2 = findDis(lcaNode, b, 0);
		  return d1+d2;
		  
		  
	  }
	private static int findDis(TNode root, int a, int level) {
	   if(root==null) {
		   return -1;
	   }
	   if(root.data ==a) {
		   return level;
	   }
	   int left = findDis(root.left, a, level+1);
	   if(left==-1) {
		   return findDis(root.right, a, level+1);
	   }
		return left;
	}

	private static TNode findLCA(TNode root, int a, int b) {
		if(root==null) {
			return null;
		}
		if(root.data ==a || root.data ==b) {
			return root;
		}
		TNode left = findLCA(root.left, a, b);
		TNode right =findLCA(root.right, a, b);
		if(left !=null && right !=null){
			return root;
		}
		if(left ==null) {
			return right;
		}
		if(right==null) {
			return left;
		}
		return root;
	}
	public int kosaraju(int V, List<List<Integer>> adj) {
		boolean[] visited = new boolean[V];
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i=0;i<V;i++) {
			if(!visited[i]) {
				dfsK1(i,visited,stack,adj);
			}
		}
		ArrayList<ArrayList<Integer>> revAdj = new ArrayList<ArrayList<Integer>>();
		
		for(int i=0;i<V;i++) {
			revAdj.add(new ArrayList<Integer>());
		}
		for(int u=0;u<V;u++) {
			for(int n:adj.get(u)) {
				revAdj.get(n).add(u);
			}
		}
		int count=0;
		Arrays.fill(visited, false);
		while(!stack.isEmpty()) {
			int node = stack.pop();
			if(!visited[node]) {
				dfsK2(node,revAdj,visited);
				count++;
			}
		}
		return count;
	}

	private void dfsK2(int node, ArrayList<ArrayList<Integer>> revAdj, boolean[] visited) {
      visited[node]=true;
		
		for(int neg:revAdj.get(node)) {
			if(!visited[neg]) {
				dfsK2(neg, revAdj, visited);
			}
		}
		
	}

	private void dfsK1(int node, boolean[] visited, Stack<Integer> stack, List<List<Integer>> adj) {
		visited[node]=true;
		
		for(int neg:adj.get(node)) {
			if(!visited[neg]) {
				dfsK1(neg, visited, stack, adj);
			}
		}
		stack.push(node);
		
	}
	public static int highestAltitude(int[] gain) {
		int currentgain=0,maxGain=0;
		for(int i=0;i<gain.length;i++) {
			currentgain +=gain[i];
			maxGain=Math.max(maxGain, currentgain);
		}
		return maxGain;
	}
	public static int[] valueDiff(int[] nums,int indexDiff,int valueDiff) {
		for(int i=0;i<nums.length;i++) {
			for(int j=i+1;j<nums.length-1;j++) {
				if(Math.abs(i-j)>=indexDiff && Math.abs(nums[i]-nums[j])>=valueDiff) {
					return new int[] {i,j};
				}
			}
		}
		return new int[] {-1,-1};
	}
	public static boolean wordPattern(String pattern,String s) {
		String[] words = s.split(" ");
		if(pattern.length() !=words.length) {
			return false;
		}
		HashMap<Character, String> char_map= new  HashMap<Character, String>();
		HashMap<String, Character> word_map = new HashMap<String, Character>();
		
		for(int i=0;i<words.length;i++) {
			String word= words[i];
			char ch = pattern.charAt(i);
			
			if(!char_map.containsKey(ch)) {
				if(word_map.containsKey(word)) {
					return false;
				}else {
					char_map.put(ch, word);
					word_map.put(word, ch);
				}
			}else {
				if(!char_map.get(ch).equals(word)) {
					return false;
				}
			}
		}
		return true;
	}
	public static int[] leftSumRightSumDiff1(int[] nums) {
		int sum=0;
		for(int i=0;i<nums.length;i++) {
			sum +=nums[i];
		}
		int leftSum=0;
		int[] ans=new int[nums.length];
		for(int i=0;i<nums.length;i++) {
			int rightSum= sum-leftSum-nums[i];
			ans[i]=Math.abs(leftSum-rightSum);
			leftSum=leftSum+nums[i];
		}
		return ans;
	}
	public static List<ArrayList<Integer>> twoArrayDiff(int[] nums1,int[] nums2) {
		return Arrays.asList(findDiff(nums1,nums2),findDiff(nums2,nums1));
	}

	private static ArrayList<Integer> findDiff(int[] nums1, int[] nums2) {
		HashSet<Integer> set = new HashSet<Integer>();
		for(int num:nums1) {
			set.add(num);
		}
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for(int num:nums2) {
			if(!set.contains(num)) {
				ans.add(num);
			}
		}
		return ans;
	}
	public static boolean contigous1S0S(String s) {
		int currentOne=0,currentZero=0;
		int maxOne=0,maxZero=0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='1') {
				currentOne++;
				currentZero =0;
			}else {
				currentOne=0;
				currentZero++;
			}
			maxOne=Math.max(maxZero, currentZero);
			maxZero =Math.max(maxOne, currentOne);
		}
		return maxOne>maxZero;
	}
	
	public static int contigousChar(String s) {
		int current=1,max=1;
		for(int i=1;i<s.length();i++) {
			if(s.charAt(i-1)== s.charAt(i)){
				current++;
			}else {
				current=1;
			}
			max=Math.max(current, max);
		}
		return max;
		
	}
	public static int maxBallons(String text) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('b', 0);
        map.put('a', 0);
        map.put('l', 0);
        map.put('o', 0);
        map.put('n', 0);
        for(int i=0;i<text.length();i++) {
        	if(map.containsKey(text.charAt(i))) {
        		map.put(text.charAt(i), map.get(text.charAt(i))+1);
        	}
        }
        int min=map.get('b');
        min =Math.min(min, map.get('a'));
        min=Math.min(min, map.get('a'));
        min=Math.min(min, map.get('l')/2);
        min=Math.min(min, map.get('o')/2);
        min=Math.min(min, map.get('n'));
        return min;
        
	}
	
	public static int maxSumLeafPath(TNode root) {
		int[] maxSum= {Integer.MIN_VALUE};
		findMasxSumPath(root,0,maxSum);
		return maxSum[0];
	}
	

	private static void findMasxSumPath(TNode root, int currentSum, int[] maxSum) {
		if(root==null) {
			return;
		}
		currentSum +=root.data;
		if(root.left ==null && root.right==null) {
			maxSum[0]=Math.max(maxSum[0],currentSum);
			return;
		}
		findMasxSumPath(root.left, currentSum, maxSum);
		findMasxSumPath(root.right, currentSum, maxSum);
	}
	public static int maxPathSumLeaf2(TNode root) {
		if(root==null) {
			return 0;
		}
		Queue<NodeSumPair> queue = new LinkedList<NodeSumPair>();
		queue.add(new NodeSumPair(root, root.data));
		
		int maxSum=Integer.MIN_VALUE;
		while(!queue.isEmpty()) {
			NodeSumPair pair = queue.poll();
			TNode node =pair.node;
			int currentSum =pair.sum;
			if(node.left==null && node.right==null) {
				maxSum=Math.max(maxSum, currentSum);
			}
			if(node.left!=null) {
				queue.add(new NodeSumPair(node.left, currentSum+node.left.data));
			}
			if(node.right!=null) {
				queue.add(new NodeSumPair(node.right, currentSum+node.right.data));
			}
		}
		return maxSum;
	}
	  static boolean canFinish1(int n, int[][] prerequisites) {
		  boolean[] visited = new boolean[n];
		  boolean[] resStack = new boolean[n];
		  
		  ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	        for (int i = 0; i < n; i++) {
	            adj.add(new ArrayList<>());
	        }
	        for (int[] pre : prerequisites) {
	            int dest = pre[0];
	            int src = pre[1];
	            adj.get(src).add(dest);
	        }
	        
	        for(int i=0;i<n;i++) {
	        	if(!visited[i]) {
	        		if(dfsCanFinish(i,visited,adj,resStack)) {
	        			return false;
	        		}
	        	}
	        }
	        return true;
	        
		  
	  }

	private static boolean dfsCanFinish(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj,
			boolean[] recStack) {
		visited[node]=true;
		recStack[node]=true;
		
		for(int neg:adj.get(node)) {
			if(!visited[neg]) {
				if(dfsCanFinish(neg, visited, adj, recStack)) {
					return true;
				}
			}else if(recStack[neg]) {
				return true;
			}
		}
		recStack[node]=false;
		return false;
	}

	public static void main(String[] args) {
       int mainTank=5;
       int addtank=10;
       System.out.println(totalDistanceTravlled(mainTank, addtank));
	}

}
