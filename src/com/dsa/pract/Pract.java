package com.dsa.pract;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
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
import java.util.function.Function;
import java.util.stream.Collectors;

import com.dsa.graph.again.rev.Dsuni;
import com.dsa.graph.again.rev.VWg;
import com.dsa.graph.interview.question.geeksforgeeks.SnakeCell;
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

public class Pract {

	static boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
		int V = adj.size();

		boolean[] visited = new boolean[V];

		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				if (dfs(i, visited, adj, -1)) {
					return true;
				}
			}
		}
		return false;

	}

	private static boolean dfs(int v, boolean[] visited, ArrayList<ArrayList<Integer>> adj, int parent) {
		visited[v] = true;

		for (int negh : adj.get(v)) {
			if (!visited[negh]) {
				if (dfs(negh, visited, adj, v)) {
					return true;
				}
			} else if (parent != negh) {
				return true;
			}
		}
		return false;
	}

	static boolean isCycleDirected(ArrayList<ArrayList<Integer>> adj) {
		int V = adj.size();

		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];

		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				if (dfsDirected(i, visited, adj, recStack)) {
					return true;
				}
			}
		}
		return false;

	}

	private static boolean dfsDirected(int v, boolean[] visited, ArrayList<ArrayList<Integer>> adj,
			boolean[] recStack) {
		visited[v] = true;
		recStack[v] = true;

		for (int negh : adj.get(v)) {
			if (!visited[negh]) {
				if (dfsDirected(negh, visited, adj, recStack)) {
					return true;
				}
			} else if (recStack[negh]) {
				return true;
			}
		}
		recStack[v] = false;
		return false;
	}

	public static String firstPalindromic(String[] arr) {
		if (arr.length == 0) {
			return "";
		}
		for (String word : arr) {
			if (isPalindrome(word)) {
				return word;
			}
		}
		return "";
	}

	private static boolean isPalindrome(String word) {
		int start = 0, end = word.length() - 1;
		while (start < end) {
			if (word.charAt(start) != word.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}

	public static int countEvenDigitSum(int n) {
		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (isEvenDigitSum(i)) {
				count++;
			}
		}
		return count;
	}

	private static boolean isEvenDigitSum(int num) {
		int sum = 0;
		while (num > 0) {
			int r = num % 10;
			sum += r;
			num /= 10;
		}
		return (sum % 2 == 0);
	}

	public static String[] sortPeople(String[] names, int[] height) {
		if (height.length != names.length) {
			return new String[] { "", "" };
		}
		Integer[] indices = new Integer[height.length];
		for (int i = 0; i < height.length; i++) {
			indices[i] = i;
		}
		Arrays.sort(indices, (a, b) -> height[b] - height[a]);
		String[] result = new String[names.length];
		for (int i = 0; i < indices.length; i++) {
			result[i] = names[indices[i]];
		}
		return result;
	}

	public static int distinctAvg(int[] nums) {
		Arrays.sort(nums);
		int start = 0, end = nums.length - 1;
		HashSet<Integer> set = new HashSet<Integer>();
		while (start < end) {
			int sum = nums[start] + nums[end]; // if sum same avg would be same
			if (!set.contains(sum)) { // can remove set auto remove duplicates
				set.add(sum);
			}

			start++;
			end--;
		}
		return set.size();
	}

	static TNode prev = null;
	static TNode succ = null;

	public static TNode inorderSucceor(TNode root, int target) {
		prev = null;
		succ = null;
		getSuccessor(root, target);
		return succ;
	}

	private static void getSuccessor(TNode root, int target) {
		if (root == null || succ != null) {
			return;
		}
		getSuccessor(root.left, target);
		if (prev != null && prev.data == target && succ == null) {
			succ = root;
			return;
		}
		prev = root;

		getSuccessor(root.right, target);
	}

	public static TNode inroderSuccessor1(TNode root, int target) {
		if (root == null) {
			return null;
		}
		TNode curr = root;
		TNode succ = null;
		while (curr != null) {
			if (curr.data > target) {
				curr = succ;
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
		return succ;
	}

	public static TNode succItravticeStack(TNode root, int target) {
		Stack<TNode> stack = new Stack<TNode>();
		TNode prev = null;
		TNode cuNode = root;
		while (!stack.isEmpty() || cuNode != null) {
			while (cuNode != null) {
				stack.push(cuNode);
				cuNode = cuNode.left;
			}
			cuNode = stack.pop();
			if (prev != null && prev.data == target) {
				return cuNode;
			}
			prev = cuNode;
			cuNode = cuNode.right;
		}
		return null;

	}

	static int kthLargest(TNode root, int k) {
		int[] count = { 0 };
		int[] largest = { -1 };
		kthLargest(root, k, count, largest);
		return largest[0];
	}

	private static void kthLargest(TNode root, int k, int[] count, int[] largest) {
		if (root == null || largest[0] != -1) {
			return;
		}
		kthLargest(root.right, k, count, largest);
		count[0]++;
		if (count[0] == k && largest[0] == -1) {
			largest[0] = root.data;
			return;
		}

		kthLargest(root.left, k, count, largest);
	}

	static ArrayList<Integer> topoSortKahn(ArrayList<ArrayList<Integer>> adj) {
		int V = adj.size();
		int[] indegree = new int[V];
		for (int u = 0; u < V; u++) {
			for (int v : adj.get(u)) {
				indegree[v]++;
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

			for (int ng : adj.get(curr)) {
				if (--indegree[ng] == 0) {
					queue.add(ng);
				}
			}
		}
		return topo;
	}

	public static boolean circularSentence(String sentence) {
		if (sentence.charAt(0) != sentence.charAt(sentence.length() - 1)) {
			return false;
		}
		for (int i = 0; i < sentence.length(); i++) {
			if (Character.isWhitespace(sentence.charAt(i))) {

				if (i == 0 || i == sentence.length() - 1) {
					return false;
				}
				if (sentence.charAt(i + 1) != sentence.charAt(i - 1)) {
					return false;
				}
			}
		}
		return true;
	}

	public static int maxValue(String[] strs) {
		int max = 0;
		for (String word : strs) {
			if (chDigits(word)) {
				max = Math.max(max, Integer.parseInt(word));
			} else {
				max = Math.max(max, word.length());
			}
		}
		return max;
	}

	private static boolean chDigits(String word) {
		int count = 0;
		for (int i = 0; i < word.length(); i++) {
			if (Character.isDigit(word.charAt(i))) {
				count++;
			}
		}
		return count == word.length();
	}

	public static int countSimiliarPair(String[] words) {
		int count = 0;
		for (int i = 0; i < words.length; i++) {
			for (int j = i + 1; j < words.length; j++) {
				if (checkEquivalent(words[i], words[j])) {
					count++;
				}
			}
		}
		return count;
	}

	private static boolean checkEquivalent(String word1, String word2) {
		HashSet<Character> set = new HashSet<Character>();
		HashSet<Character> set1 = new HashSet<Character>();
		for (int i = 0; i < word1.length(); i++) {
			set.add(word1.charAt(i));
		}
		for (int i = 0; i < word2.length(); i++) {
			set1.add(word2.charAt(i));
		}

		return set.equals(set1);
	}

	public static int maxPosNeg(int[] nums) {
		int negCount = 0, posCount = 0;
		for (int num : nums) {
			if (num < 0) {
				negCount++;
			} else if (num > 0) {
				posCount++;
			}
		}
		return Math.max(negCount, posCount);
	}

	public static int smallestCommon(int[] nums1, int[] nums2) {
		HashSet<Integer> set = new HashSet<Integer>();
		for (int num : nums1) {
			set.add(num);
		}
		int min = Integer.MAX_VALUE;
		for (int num : nums2) {
			if (set.contains(num)) {
				min = Math.min(min, num);
			}
		}
		return min == Integer.MAX_VALUE ? -1 : min;

	}

	public static int smallestCommon1(int[] nums1, int[] nums2) {
		int i = 0, j = 0;

		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] == nums2[j]) {
				return nums1[i];
			} else if (nums1[i] < nums2[j]) {
				i++;
			} else {
				j++;
			}
		}
		return -1;
	}

	int diameter = 0;

	public int diameter(TNode root) {
		diameterUtil(root);
		return diameter;
	}

	private int diameterUtil(TNode root) {
		if (root == null) {
			return 0;
		}
		int lh = diameterUtil(root.left);
		int rh = diameterUtil(root.right);
		diameter = Math.max(diameter, 1 + lh + rh);

		return Math.max(lh, rh) + 1;

	}

	static boolean isSubtree(TNode root1, TNode root2) {
		if (root2 == null)
			return true;

		// Main tree empty but subtree not → false
		if (root1 == null)
			return false;
		if (areIden(root1, root2)) {
			return true;
		}

		return isSubtree(root1.left, root2) || isSubtree(root1.right, root2);

	}

	private static boolean areIden(TNode root1, TNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null || root1.data != root2.data) {
			return false;
		}
		return areIden(root1.left, root2.left) && areIden(root1.right, root2.right);
	}

	public boolean checkBst(TNode root) {
		return dfsCheckBst(root, Long.MAX_VALUE, Long.MIN_VALUE);
	}

	private boolean dfsCheckBst(TNode root, long maxValue, long minValue) {
		if (root == null) {
			return true;
		}
		if (root.data <= minValue || root.data >= maxValue) {
			return false;
		}
		return dfsCheckBst(root.left, root.data, minValue) && dfsCheckBst(root.right, maxValue, root.data);
	}

	TNode pr = null;

	public boolean checkBst1(TNode root) {
		if (root == null) {
			return true;
		}
		return chBst(root);
	}

	private boolean chBst(TNode root) {
		if (root == null) {
			return true;
		}
		if (!chBst(root.left)) {
			return false;
		}
		if (pr != null && pr.data >= root.data) {
			return false;
		}
		pr = root;
		return chBst(root.right);
	}

	public static int kruskalsMST(int V, int[][] edges) {
		Arrays.sort(edges, Comparator.comparing(e -> e[2]));
		DSSet dsSet = new DSSet(V);
		int cost = 0, count = 0;
		for (int[] edge : edges) {
			int u = edge[0], v = edge[1], w = edge[2];

			if (dsSet.find(u) != dsSet.find(v)) {
				dsSet.union(u, v);
				cost += w;
				count++;
				if (count == V - 1) {
					break;
				}
			}
		}
		return cost;
	}

	public int countIsland(int[][] grid) {
		int count = 0;
		int m = grid.length;
		int n = grid[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 'L') {
					count++;
				}
			}
		}
		DSSet dsu = new DSSet(m * n);

		int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 'L') {
					for (int[] d : dirs) {
						int ni = i + d[0];
						int nj = j + d[1];
						if (ni >= 0 && nj >= 0 && ni < m && nj < n && grid[ni][nj] == 'L') {
							int u = i * n + j;
							int v = ni * n + nj;

							if (dsu.find(u) != dsu.find(v)) {
								dsu.union(u, v);
								count--; // ✅ only here
							}
						}
					}
				}
			}
		}
		return count;

	}

	public static int alternateDigitSum(int n) {
		String strNum = n + "";
		int sum = Character.getNumericValue(strNum.charAt(0));
		for (int i = 1; i < strNum.length(); i++) {
			if (i % 2 == 0) {
				sum += Character.getNumericValue(strNum.charAt(i));
			} else {
				sum -= Character.getNumericValue(strNum.charAt(i));
			}
		}
		return sum;
	}

	public static int alternateDigitSum1(int n) {
		int rev = 0;
		int sum = 0;
		int sign = 1;

		while (n > 0) {
			rev = rev * 10 + (n % 10);
			n /= 10;
		}
		while (rev > 0) {
			int digit = rev % 10;
			sum += sign * digit;
			sign *= -1;
			rev /= 10;
		}
		return sum;
	}

	public static int concateValue(int[] nums) {
		int sum = 0;
		int start = 0, end = nums.length - 1;
		while (start < end) {
			sum += Integer.parseInt(String.valueOf(nums[start] + String.valueOf(nums[end])));
			start++;
			end--;
		}
		if (nums.length % 2 != 0) {
			sum += nums[start];
		}
		return sum;
	}

	public static int isWinner(int[] player1, int[] player2) {
		if (countScore(player1) > countScore(player2)) {
			return 1;
		} else if (countScore(player2) > countScore(player1)) {
			return 2;
		} else {
			return 0;
		}
	}

	private static int countScore(int[] player) {
		int score = 0;
		for (int i = 1; i < player.length; i++) {
			if (i == 1) {
				if (player[i - 1] >= 10) {
					score += 2 * player[i];
				} else {
					score += player[i];
				}
			} else if (i > 1) {
				if (player[i - 1] >= 10 || player[i - 2] >= 10) {
					score += 2 * player[i];
				} else {
					score += player[i];
				}
			} else {
				score += player[i];
			}
		}
		return score;
	}

	public static int[] distinctDiffrenceArray(int[] nums) {
		int[] diff = new int[nums.length];

		for (int i = 0; i < nums.length; i++) {
			HashSet<Integer> prefix = new HashSet<Integer>();
			HashSet<Integer> suffix = new HashSet<Integer>();

			for (int j = 0; j <= i; j++) {
				prefix.add(nums[i]);
			}
			for (int j = i + 1; j < nums.length; j++) {
				suffix.add(nums[i]);
			}
			diff[i] = prefix.size() - suffix.size();
		}
		return diff;
	}

	public static int totatDistanceTravled(int mainTank, int addTank) {
		int totalDistnce = 0;

		while (mainTank >= 5 && addTank > 0) {
			mainTank = (mainTank - 5) + 1;
			addTank--;
			totalDistnce = totalDistnce + 50;
		}
		totalDistnce += mainTank * 10;
		return totalDistnce;
	}

	public static boolean isGood(int[] nums) {
		Arrays.sort(nums);
		int max = nums[nums.length - 1];
		if (nums.length != (max + 1)) {
			return false;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		ArrayList<Integer> list = new ArrayList<Integer>(map.values());
		int count = 0;
		for (int num : list) {
			if (num >= 2) {
				count++;
			}
		}
		if (map.get(max) == 2 && count == 1) {
			return true;
		}
		return false;
	}

	public static List<String> splitWordsbySeprator(List<String> words, String seprator) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < words.size(); i++) {
			String[] wordArr = words.get(i).split("[" + seprator + "]");
			for (String word : wordArr) {
				list.add(word);
			}
		}
		return list;
	}

	public static int purchseAmount(int purchaseAmount) {
		int rem = purchaseAmount % 10;
		int balance = 10 - rem;
		if (purchaseAmount < 0 || purchaseAmount > 100) {
			return 100;
		}
		if (rem > 5) {
			purchaseAmount = purchaseAmount - rem;
		} else {
			purchaseAmount = purchaseAmount + balance;
		}
		return 100 - purchaseAmount;
	}

	public static int maxEqualDigitSum(int[] nums) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (maxDigit(nums[i]) == maxDigit(nums[j])) {
					max = Math.max(max, (nums[i] + nums[j]));
				}
			}
		}
		return max;
	}

	private static int maxDigit(int num) {
		int maxDigit = Integer.MIN_VALUE;
		while (num > 0) {
			int rem = num % 10;
			maxDigit = Math.max(maxDigit, rem);
			num /= 10;
		}
		return maxDigit;
	}

	public static int minOprations(int[] nums, int k) {
		HashSet<Integer> set = new HashSet<Integer>();
		int count = 0;
		for (int i = 1; i <= k; i++) {
			set.add(i);
		}
		for (int num : nums) {
			if (set.contains(num)) {
				set.remove(num);
				if (set.isEmpty()) {
					break;
				}
			}
			count++;
		}
		return count;
	}

	public static int highestAltitude(int[] gain) {
		int currentGain = 0, maxGain = 0;
		for (int g : gain) {
			currentGain += g;
			maxGain = Math.max(maxGain, currentGain);
		}
		return maxGain;
	}

	public static ArrayList<Integer> zigzagTravrsal(TNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		Queue<TNode> queue = new LinkedList<TNode>();
		queue.add(root);
		boolean leftToRight = true;

		while (!queue.isEmpty()) {
			int size = queue.size();
			LinkedList<Integer> temp = new LinkedList<Integer>();
			for (int i = 0; i < size; i++) {
				TNode cur = queue.poll();

				if (leftToRight) {
					temp.addLast(cur.data);
				} else {
					temp.addFirst(cur.data);
				}
				if (cur.left != null) {
					queue.add(cur.left);
				}
				if (cur.right != null) {
					queue.add(cur.right);
				}
			}
			list.addAll(temp);
			leftToRight = !leftToRight;
		}
		return list;
	}

	static int spanningTree(int V, ArrayList<ArrayList<int[]>> adj) {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);

		boolean[] visited = new boolean[V];
		int res = 0;

		pq.add(new int[] { 0, 0 });

		while (!pq.isEmpty()) {
			int[] pair = pq.poll();
			int wt = pair[0];
			int nd = pair[1];

			if (visited[nd]) {
				continue;
			}
			res += wt;
			visited[nd] = true;

			for (int[] v : adj.get(nd)) {
				if (!visited[v[0]]) {
					pq.add(new int[] { v[1], v[0] });
				}
			}
		}
		return res;
	}

	static int[] bellmanFord(int V, int[][] edges, int src) {
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;

		for (int i = 0; i < V - 1; i++) {
			for (int[] edge : edges) {
				int u = edge[0];
				int v = edge[1];
				int wt = edge[2];
				if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
					dist[v] = dist[u] + wt;
				}
			}
		}
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			int wt = edge[2];
			if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
				return new int[] { -1, -1 };
			}
		}
		return dist;
	}

	public static int[] indicesDiff(int[] nums, int valueDiff, int indexDiff) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (Math.abs(i - j) >= indexDiff && Math.abs(nums[i] - nums[j]) == valueDiff) {
					return new int[] { i, j };
				}
			}
		}
		return new int[] { -1, -1 };
	}

	public static boolean wordPattern(String s, String pattern) {
		String[] words = s.split(" ");
		if (words.length != pattern.length()) {
			return false;
		}
		HashMap<Character, String> charMap = new HashMap<Character, String>();
		HashMap<String, Character> wordMap = new HashMap<String, Character>();
		for (int i = 0; i < words.length; i++) {
			char ch = pattern.charAt(i);
			String word = words[i];
			if (!charMap.containsKey(ch)) {
				if (wordMap.containsKey(word)) {
					return false;
				} else {
					charMap.put(ch, word);
					wordMap.put(word, ch);
				}
			} else if (!charMap.get(ch).equals(word)) {
				return false;
			}
		}
		return true;
	}

	public static int[] leftRightSumDiffArr(int[] nums) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		int leftSum = 0;
		int[] ans = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			int rightSum = sum - leftSum - nums[i];
			ans[i] = Math.abs(leftSum - rightSum);
			leftSum += nums[i];
		}
		return ans;
	}

	static int[] dijkstra(ArrayList<ArrayList<int[]>> adj, int src) {
		int V = adj.size();
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
		pq.add(new int[] { src, 0 });
		dist[src] = 0;

		while (!pq.isEmpty()) {
			int[] pair = pq.poll();
			int u = pair[0];
			int d = pair[1];

			if (d > dist[u]) {
				continue;
			}
			for (int[] ng : adj.get(u)) {
				int v = ng[0];
				int dis = ng[1];
				if (dist[u] + dis < dist[v]) {
					dist[v] = dist[u] + dis;
					pq.add(new int[] { v, dist[v] });
				}
			}
		}
		return dist;

	}

	static boolean canFinish(int n, int[][] prerequisites) {
		boolean[] visited = new boolean[n];
		boolean[] recStack = new boolean[n];
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		for (int[] pre : prerequisites) {
			int dest = pre[0];
			int src = pre[1];
			adj.get(src).add(dest);
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				if (dfsTask(i, adj, visited, recStack)) {
					return false;
				}
			}
		}
		return true;

	}

	private static boolean dfsTask(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] recStack) {
		visited[v] = true;
		recStack[v] = true;

		for (int ng : adj.get(v)) {
			if (!visited[ng]) {
				if (dfsTask(ng, adj, visited, recStack)) {
					return true;
				}
			} else if (recStack[ng]) {
				return true;
			}

		}
		recStack[v] = false;
		return false;
	}

	static boolean canFinish1(int n, int[][] prerequisites) {
		int[] indegree = new int[n];
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		for (int[] pre : prerequisites) {
			int dest = pre[0];
			int src = pre[1];
			adj.get(src).add(dest);
			indegree[dest]++;
		}
		Queue<Integer> queue = new LinkedList<>();

		// Add all indegree 0 nodes
		for (int i = 0; i < n; i++) {

			if (indegree[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			int node = queue.poll();

			for (int ng : adj.get(node)) {
				if (--indegree[ng] == 0) {
					queue.add(ng);
				}
			}
		}
		// If any node still has indegree > 0
		// then cycle exists
		for (int deg : indegree) {

			if (deg != 0) {
				return false;
			}
		}

		return true;

	}

	static ArrayList<Integer> boundaryTraversal(TNode root) {
		ArrayList<Integer> res = new ArrayList<>();
		if (root == null)
			return res;
		// add root if not leaf
		if (!isLeaf(root)) {
			res.add(root.data);
		}
		collecLeft(root.left, res);
		collectLeavs(root, res);
		collectRight(root.right, res);
		return res;

	}

	private static void collectRight(TNode root, ArrayList<Integer> res) {
		if (root == null || isLeaf(root)) {
			return;
		}
		if (root.right != null) {
			collectRight(root.right, res);
		} else if (root.left != null) {
			collectRight(root.left, res);
		}
		res.add(root.data);

	}

	private static void collectLeavs(TNode root, ArrayList<Integer> res) {
		if (root == null) {
			return;
		}
		if (isLeaf(root)) {
			res.add(root.data);
			return;
		}
		collectLeavs(root.left, res);
		collectLeavs(root.right, res);

	}

	private static void collecLeft(TNode root, ArrayList<Integer> res) {
		if (root == null || isLeaf(root)) {
			return;
		}
		res.add(root.data);
		if (root.left != null) {
			collecLeft(root.left, res);
		} else if (root.right != null) {
			collecLeft(root.right, res);
		}

	}

	private static boolean isLeaf(TNode root) {
		return (root.left == null && root.right == null);
	}

	public static List<ArrayList<Integer>> arrayDiff(int[] nums1, int[] nums2) {
		return Arrays.asList(arrDiffUtil(nums1, nums2), arrDiffUtil(nums2, nums1));
	}

	private static ArrayList<Integer> arrDiffUtil(int[] nums1, int[] nums2) {
		HashSet<Integer> set1 = new HashSet<Integer>();
		HashSet<Integer> set2 = new HashSet<Integer>();
		for (int num : nums2) {
			set1.add(num);
		}
		for (int num : nums1) {
			if (!set1.contains(num)) {
				set2.add(num);
			}
		}
		return new ArrayList<Integer>(set2);
	}

	public static int maxBallons(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('b', 0);
		map.put('a', 0);
		map.put('l', 0);
		map.put('o', 0);
		map.put('n', 0);

		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
			}
		}
		int min = map.get('b');
		min = Math.min(min, map.get('a'));
		min = Math.min(min, map.get('o') / 2);
		min = Math.min(min, map.get('n'));
		return min;

	}

	public static int conseciveChar(String s) {
		int current = 1, max = 1;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i - 1) == s.charAt(i)) {
				current++;
			} else {
				current = 1;
			}
			max = Math.max(max, current);
		}
		return max;
	}

	static TNode buildTree(int[] inorder, int[] preorder) {
		HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			indexMap.put(inorder[i], i);
		}
		int[] preIndex = { 0 };
		return buildTreeUtil(preIndex, preorder, indexMap, 0, inorder.length - 1);
	}

	private static TNode buildTreeUtil(int[] preIndex, int[] preorder, HashMap<Integer, Integer> indexMap, int start,
			int end) {
		if (start > end) {
			return null;
		}
		int rootVal = preorder[preIndex[0]++];
		TNode rootNode = new TNode(rootVal);
		int index = indexMap.get(rootVal);
		rootNode.left = buildTreeUtil(preIndex, preorder, indexMap, start, index - 1);
		rootNode.right = buildTreeUtil(preIndex, preorder, indexMap, index + 1, end);
		return rootNode;
	}

	public static TNode preToBST(int[] pre) {
		TNode root = null;
		for (int node : pre) {
			root = insertNode(root, node);
		}
		return root;

	}

	private static TNode insertNode(TNode root, int node) {
		TNode newNode = new TNode(node);

		if (root == null) {
			return newNode;
		}
		TNode curr = root;
		TNode parent = null;

		while (curr != null) {
			parent = curr;
			if (curr.data > node) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
		if (parent.data > node) {
			parent.left = new TNode(node);
		} else {
			parent.right = new TNode(node);
		}
		return root;
	}

	public static ArrayList<Integer> ancestors(TNode root, int key) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		HashMap<TNode, TNode> parentMap = new HashMap<TNode, TNode>();
		TNode targetNode = buidPrentMapBFS(root, parentMap, key);
		if (targetNode == null) {
			return list;
		}
		// move upward using parent map
		TNode current = parentMap.get(targetNode);

		while (current != null) {
			list.add(current.data);
			current = parentMap.get(current);
		}

		return list;

	}

	private static TNode buidPrentMapBFS(TNode root, HashMap<TNode, TNode> parentMap, int key) {
		if (root == null) {
			return null;
		}
		Queue<TNode> queue = new LinkedList<TNode>();
		queue.add(root);
		parentMap.put(root, null);
		TNode targetNode = null;
		while (!queue.isEmpty()) {
			TNode current = queue.poll();
			if (key == current.data) {
				targetNode = current;
			}
			if (current.left != null) {
				parentMap.put(current.left, current);
			}
			if (current.right != null) {
				parentMap.put(current.right, current);
			}
		}
		return targetNode;

	}

	public static ArrayList<Integer> ancestors1(TNode root, int key) {
		ArrayList<Integer> list = new ArrayList<>();

		findAncestors(root, key, list);

		return list;
	}

	private static boolean findAncestors(TNode root, int key, ArrayList<Integer> list) {
		if (root == null) {
			return false;
		}
		if (root.data == key) {
			return true;
		}
		boolean left = findAncestors(root.left, key, list);
		boolean right = findAncestors(root.right, key, list);
		if (left || right) {
			list.add(root.data);
			return true;
		}
		return false;

	}

	static boolean checkPath(ArrayList<ArrayList<Integer>> adj, int u, int v) {
		int V = adj.size();
		boolean[] visited = new boolean[V];
		return dfsCheckPath(u, v, adj, visited);

	}

	private static boolean dfsCheckPath(int currrent, int dest, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
		visited[currrent] = true;
		if (currrent == dest) {
			return true;
		}
		for (int ng : adj.get(currrent)) {
			if (!visited[ng]) {
				if (dfsCheckPath(ng, dest, adj, visited)) {
					return true;
				}
			}
		}
		return false;

	}

	static int checkPathCount(ArrayList<ArrayList<Integer>> adj, int u, int v) {
		int V = adj.size();
		boolean[] visited = new boolean[V];
		int[] count = { 0 };
		dfsCheckPathCount(u, v, adj, visited, count);
		return count[0];

	}

	private static void dfsCheckPathCount(int currrent, int dest, ArrayList<ArrayList<Integer>> adj, boolean[] visited,
			int[] count) {
		visited[currrent] = true;
		if (currrent == dest) {
			count[0]++;
			return;
		}
		for (int ng : adj.get(currrent)) {
			if (!visited[ng]) {
				dfsCheckPathCount(ng, dest, adj, visited, count);

			}
		}
		visited[currrent] = false;

	}

	public TNode removeKeys(TNode root, int l, int r) {
		return removeUtil(root, l, r);
	}

	private TNode removeUtil(TNode root, int l, int r) {
		if (root == null) {
			return null;
		}
		TNode left = removeUtil(root.left, l, r);
		TNode right = removeUtil(root.right, l, r);
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

	private TNode removeUtil1(TNode root, int l, int r) {
		if (root == null) {
			return null;
		}
		if (root.data < l) {
			removeUtil1(root.right, l, r);
		}
		if (root.data > r) {
			return removeUtil1(root.left, l, r);
		}
		removeUtil1(root.left, l, r);
		removeUtil1(root.right, l, r);

		return root;
	}

	public TNode removeItrative(TNode root, int l, int r) {
		if (root == null) {
			return null;
		}

		while (root != null && (root.data < l || root.data > r)) {
			if (root.data < l) {
				root = root.right;
			} else {
				root = root.left;
			}
		}
		TNode current = root;

		while (current != null) {
			while (current.left != null && current.left.data < l) {
				current.left = current.left.right;
			}
			current = current.left;
		}
		current = root;
		while (current != null) {
			while (current.right != null && current.right.data > r) {
				current.right = current.right.left;
			}
			current = current.right;
		}
		return root;
	}

	static boolean findTarget(TNode root, int target) {
		if (root == null) {
			return false;
		}
		HashSet<Integer> set = new HashSet<>();
		return pairSum(root, target, set);
	}

	private static boolean pairSum(TNode root, int target, HashSet<Integer> set) {
		if (root == null) {
			return false;
		}
		if (pairSum(root.left, target, set)) {
			return true;
		}
		if (set.contains(target - root.data)) {
			return true;
		}
		set.add(root.data);
		return pairSum(root.right, target, set);
	}

	public boolean findTagetItrative(TNode root, int target) {
		if (root == null) {
			return false;
		}
		HashSet<Integer> set = new HashSet<Integer>();
		Queue<TNode> queue = new LinkedList<TNode>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TNode curr = queue.poll();
			if (set.contains(target - curr.data)) {
				return true;
			}
			set.add(curr.data);
			if (curr.left != null) {
				queue.add(curr.left);
			}
			if (curr.right != null) {
				queue.add(curr.right);
			}
		}
		return false;
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
		PriorityQueue<PriceNodePair> pq = new PriorityQueue<>();
		pq.add(new PriceNodePair(src, 0, 0));
		dist[src] = 0;
		stops[src] = 0;

		while (!pq.isEmpty()) {
			PriceNodePair pair = pq.poll();
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
				int newStops = st + 1;
				int newCost = cost + price;

				if (newCost < dist[dest] || newStops < stops[dest]) {
					dist[dest] = newCost;
					stops[dest] = newStops;
					pq.add(new PriceNodePair(dest, newCost, newStops));
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

				if (dist[u] == Integer.MAX_VALUE) {
					continue;
				}
				if (dist[u] + cost < dist[v]) {
					temp[v] = dist[u] + cost;
				}
			}
			dist = temp;
		}
		return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
	}

	public int findChampion(int[][] teams) {

		for (int i = 0; i < teams.length; i++) {
			int count = 0;
			for (int j = 0; j < teams[0].length; j++) {
				if (teams[i][j] == 1) {
					count++;
				}
				if (count == teams.length - 1) {
					return i;
				}
			}
		}
		return -1;
	}

	public static int elementAppear25Per(int[] arr) {
		int limit = arr.length / 4;

		for (int i = 0; i < arr.length - limit; i++) {
			if (arr[i] == arr[i + limit]) {
				return arr[i];
			}
		}
		return -1;
	}

	public static int[] findIntersectionValues(int[] nums1, int[] nums2) {

		HashSet<Integer> set1 = new HashSet<>();
		HashSet<Integer> set2 = new HashSet<>();
		for (int num : nums1) {
			set1.add(num);
		}
		for (int num : nums2) {
			set2.add(num);
		}
		int result1 = 0;
		int result2 = 0;
		for (int num : nums1) {
			if (set2.contains(num)) {
				result1++;
			}
		}
		for (int num : nums2) {
			if (set1.contains(num)) {
				result2++;
			}
		}

		return new int[] { result1, result2 };

	}

	public static int numSpecial(int[][] mat) {
		int row = mat.length;
		int col = mat[0].length;
		int count = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (mat[i][j] == 0) {
					continue;
				}
				boolean flag = false;
				for (int r = 0; r < row; r++) {
					if (r != i && mat[r][j] == 1) {
						flag = true;
						break;
					}
				}
				for (int c = 0; c < col; c++) {
					if (c != j && mat[i][c] == 1) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					count++;
				}
			}
		}
		return count;

	}

	public static int numSpecial1(int[][] mat) {

		int row = mat.length;
		int col = mat[0].length;

		int[] rowCount = new int[row];
		int[] colCount = new int[col];

		// Count ones in rows and columns
		for (int i = 0; i < row; i++) {

			for (int j = 0; j < col; j++) {

				if (mat[i][j] == 1) {

					rowCount[i]++;
					colCount[j]++;
				}
			}
		}

		int count = 0;

		// Find special positions
		for (int i = 0; i < row; i++) {

			for (int j = 0; j < col; j++) {

				if (mat[i][j] == 1 && rowCount[i] == 1 && colCount[j] == 1) {

					count++;
				}
			}
		}

		return count;
	}

	static int sum = 0;

	public static void transformTree(TNode root) {
		if (root == null) {
			return;
		}
		transformGreaterSum(root);
	}

	private static void transformGreaterSum(TNode root) {
		if (root == null) {
			return;
		}
		transformGreaterSum(root.right);
		int temp = root.data;
		root.data = sum;
		sum += temp;
		transformGreaterSum(root.left);

	}

	static int getMaxSum(TNode root) {
		if (root == null)
			return 0;

		return getMaxSumUtil(root);
	}

	private static int getMaxSumUtil(TNode root) {
		if (root == null) {
			return 0;
		}
		int include = root.data;
		if (root.left != null) {
			include += getMaxSumUtil(root.left.left) + getMaxSumUtil(root.left.right);
		}
		if (root.right != null) {
			include += getMaxSumUtil(root.right.left) + getMaxSumUtil(root.right.right);
		}
		int exclude = getMaxSumUtil(root.left) + getMaxSumUtil(root.right);
		return Math.max(include, exclude);
	}

	static int getMaxSum1(TNode root) {
		IncExcludePair max = maxSumUtil(root);
		return Math.max(max.include, max.exclude);
	}

	private static IncExcludePair maxSumUtil(TNode root) {
		if (root == null) {
			return new IncExcludePair(0, 0);
		}
		IncExcludePair left = maxSumUtil(root.left);
		IncExcludePair right = maxSumUtil(root.right);
		int iclude = root.data + left.exclude + right.exclude;
		int exclude = Math.max(left.include, left.exclude) + Math.max(right.exclude, right.include);
		return new IncExcludePair(iclude, exclude);
	}

	public static int largestBst(TNode root) {
		if (root == null) {
			return 0;
		}
		return largestBstUtil(root);
	}

	private static int largestBstUtil(TNode root) {
		if (root == null) {
			return 0;
		}
		if (isBst(root)) {
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

	private static boolean isBst(TNode root) {
		if (root == null) {
			return true;
		}
		return checkBst(root, Long.MAX_VALUE, Long.MIN_VALUE);
	}

	private static boolean checkBst(TNode root, long maxValue, long minValue) {
		if (root == null) {
			return true;
		}
		if (root.data <= minValue || root.data >= maxValue) {
			return false;
		}
		return checkBst(root.left, root.data, minValue) && checkBst(root.right, maxValue, root.data);
	}

	public ArrayList<Integer> extremeNode(TNode root) {
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
				TNode curr = queue.poll();
				if (leftRight && i == size - 1) {
					list.add(curr.data);
				} else if (!leftRight && i == 0) {
					list.add(curr.data);
				}
				if (curr.left != null) {
					queue.add(curr.left);
				}
				if (curr.right != null) {
					queue.add(curr.right);
				}
			}
			leftRight = !leftRight;
		}
		return list;
	}

	public static void builNextRightItrative(TreeNodNextRightNode root) {
		if (root == null) {
			return;
		}
		Queue<TreeNodNextRightNode> queue = new LinkedList<TreeNodNextRightNode>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			TreeNodNextRightNode prev = null;

			for (int i = 0; i < size; i++) {
				TreeNodNextRightNode cur = queue.poll();
				if (prev != null) {
					prev.nextRight = cur;
				}
				prev = cur;
				if (cur.left != null) {
					queue.add(cur.left);
				}
				if (cur.right != null) {
					queue.add(cur.right);
				}
			}
			if (prev != null) {
				prev.nextRight = null;
			}

		}

	}

	public static boolean isSubsequence(String s, String t) {

		int i = 0, j = 0;

		while (i < s.length() && j < t.length()) {
			if (s.charAt(i) == t.charAt(j)) {
				i++;
			}
			j++;
		}
		return i == s.length();
	}

	public static boolean plantFlower1(int[] flower, int k) { // properly correct
		int count = 0;
		for (int i = 0; i < flower.length; i++) {
			if ((i == 0 || flower[i - 1] == 0) && (i == flower.length - 1 || flower[i + 1] == 0)) {
				flower[i] = 1;
				count++;
				if (count >= k) {
					return true;
				}
			}
		}
		return false;
	}

	public static int removeElement(int[] nums, int val) {
		int i = 0;
		int len = nums.length;
		while (i < len) {
			if (nums[i] == val) {
				nums[i] = nums[len - 1];
				len--;
			} else {
				i++;
			}
		}
		return len;
	}

	public String greatestCommonDiv(String str1, String str2) {

	    if (!(str1 + str2).equals(str2 + str1)) {
	        return "";
	    }

	    int len = gcd(str1.length(), str2.length());

	    return str1.substring(0, len);
	}

	private int gcd(int num1, int num2) {
		if (num2 == 0) {
			return num1;
		}
		return gcd(num2, num1 % num2);
	}
	public int numberOfIsland1(int[][] grid) {
		int m = grid.length;
		int n= grid[0].length;
		int count=0;
		DisjointSetDS disjointSetDS = new DisjointSetDS(m * n);
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(grid[i][j]=='L') {
					count++;
				}
			}
		}
		int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
		int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if (grid[i][j] == 'L') {
				for(int k=0;k<8;k++) {
					int nr=i+dr[k];
					int nc = j+dc[k];
					if(nr>=0 && nc>=0 && nr<m && nc <n && grid[nr][nc]=='L') {
						if(disjointSetDS.unionWithOutSideCount(i * n+j, nr * n+nc)) {
							count--;
						}
					}
				}
			}
			}
		}
		return count;
		
	}
	public int kosaraju(int V, List<List<Integer>> adj) {
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[V];
		
		for(int i=0;i<V;i++) {
			if(!visited[i]) {
				dfsScc(i,visited,adj,stack);
			}
		}
		ArrayList<ArrayList<Integer>> revAdj = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<V;i++) {
			revAdj.add(new ArrayList<Integer>());
		}
		for(int u=0;u<V;u++) {
			for(int v:adj.get(u)) {
				revAdj.get(v).add(u);
			}
		}
		int count=0;
		Arrays.fill(visited, false);
		  while (!stack.isEmpty()) {

		        int node = stack.pop();

		        if (!visited[node]) {

		            dfsScc1(node, visited, revAdj);

		            count++;
		        }
		    }
		  return count;
	}

	private void dfsScc1(int v, boolean[] visited, ArrayList<ArrayList<Integer>> revAdj) {
		visited[v]=true;
		
		for(int ng:revAdj.get(v)) {
			if(!visited[ng]) {
				dfsScc1(ng, visited, revAdj);
			}
		}
		
	}

	private void dfsScc(int v, boolean[] visited, List<List<Integer>> adj, Stack<Integer> stack) {
		visited[v]=true;
		
		for(int ng:adj.get(v)) {
			if(!visited[ng]) {
				dfsScc(ng, visited, adj, stack);
			}
		}
		stack.push(v);
		
	}
	
	public static int pivotIndex(int[] nums) {
		int sum=0;
		for(int num:nums) {
			sum +=num;
		}
		int leftSum=0;
		for(int i=0;i<nums.length;i++) {
			if(leftSum == (sum-leftSum-nums[i])) {
				return i;
			}
			leftSum +=nums[i];
		}
		return -1;
	}
	public static boolean uniqueOcuurence(int[] nums) {
		Map<Integer, Long> map = Arrays.stream(nums).mapToObj(num->(Integer)num)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		return map.size()==new HashSet<Long>(map.values()).size();
	}
	public static String reverseVowel(String s) {
		char[] words = s.toCharArray();
		String vowels = "AEIOUaeiuo";
		int start=0,end=words.length-1;
		while(start<end) {
			while(start<end && vowels.indexOf(words[start])==-1) {
				start++;
			}
			while(start<end && vowels.indexOf(words[end])==-1) {
				end--;
			}
			char temp = words[start];
			words[start]=words[end];
			words[end]=temp;
			start++;
			end--;
		}
		return new String(words);
	}
	public static List<Integer> containingChar(String[] words, char ch) {
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<words.length;i++) {
			if(words[i].indexOf(ch)!=-1) {
				list.add(i);
			}
		}
		return list;
	}

	public static int maxWordsSentence(String[] sentences) {
		int count=0;
		for(String word:sentences) {
			String[] words = word.split(" ");
			count = Math.max(count, word.length());
		}
		return count;
	}
	public static int[] findMissingRepeating(int[][] grid) {
		int n = grid.length;
		int a = 0, b = 0;
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(set.contains(grid[i][j])) {
					a=grid[i][j];
				}
				set.add(grid[i][j]);
			}
		}
		for (int i = 1; i <= n * n; i++) {
			if(!set.contains(i)) {
				b=i;
				break;
			}
		}
		return new int[] {a,b};
	}
	int time = 0;

	public List<List<Integer>> findBridges(int n, List<List<Integer>> connections) {
		List<List<Integer>> adj = new ArrayList<List<Integer>>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (List<Integer> edge : connections) {
			adj.get(edge.get(0)).add(edge.get(1));
			adj.get(edge.get(1)).add(edge.get(0));
		}
		boolean[] visited = new boolean[n];
		int[] disc = new int[n];
		int[] low = new int[n];
		List<List<Integer>> bridges = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfsBridge(i, -1, adj, visited, disc, low, bridges);
			}
		}
		return bridges;
	}
	private void dfsBridge(int u, int parent, List<List<Integer>> adj, boolean[] visited, int[] disc, int[] low,
			List<List<Integer>> bridges) {
		visited[u]=true;
		disc[u] = low[u] = time++;
		for(int v:adj.get(u)) {
			if(v==parent) {
				continue;
			}
			if (!visited[v]) {
				dfsBridge(v, u, adj, visited, disc, low, bridges);
				low[u]=Math.min(low[v], low[u]);
				if(low[v]>disc[u]) {
					bridges.add(Arrays.asList(u,v));
				}
			}else {
				low[u] = Math.min(low[u], disc[v]);
			}
		}
		
	}
	public static int percentageOfLetter(String s,char ch) {
		int len = s.length();
		int count=0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)==ch) {
				count++;
			}
		}
		return (count *100)/len;
	}
	public static int wordsWithGivePrefix(String[] words,String prefix) {
		int count=0;
		for(String word:words) {
			if(word.startsWith(prefix)) {
				count++;
			}
		}
		return count;
	}
	public static int buyChoclates(int[] prices,int money) {
		Arrays.sort(prices);
		int total = prices[0]+prices[1];
		
		return (total>money?money :(money-total));
	}
	public static int maxScoreAfterSplit(String s) {
		int oneCount=0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='1') {
				oneCount++;
			}
		}
		int zeroCount=0;
		int maxScore=0;
		for(int i=0;i<s.length()-1;i++) {
			if(s.charAt(i)=='0') {
			   zeroCount++;
			}else {
				oneCount--;
			}
			maxScore=Math.max(maxScore, zeroCount+oneCount);
		}
		return maxScore;
	}
	public static double maxSubarray(int[] nums, int k) {
		int maxSum = 0;
		for (int i = 0; i <= nums.length - k; i++) {
			int currentSum = 0;
			for(int j=i;j<k+i;j++) {
				currentSum += nums[j];
			}
			maxSum=Math.max(maxSum, currentSum);
		}
		double res = maxSum / (double) k;
		return res;
	}
	 public List<Integer> articulationPoints(int n, List<List<Integer>> adj) {
		 boolean[] visited = new boolean[n];
		  int[] low = new int[n];
		  int[] tin = new int[n];
		  boolean[] isArticulation = new boolean[n];
		  for(int i=0;i<n;i++) {
			  if(!visited[i]) {
				  dfsArti(i,-1,visited,isArticulation,adj,low,tin);
			  }
		  }
		  List<Integer> result = new ArrayList<>();
	        for (int i = 0; i < n; i++) {
	            if (isArticulation[i]) result.add(i);
	        }

	        return result;
	 }
	

	 private void dfsArti(int node, int  parent, boolean[] visited, boolean[] isArticulation, List<List<Integer>> adj, int[] low,
				int[] tin) {
		visited[node]=true;
		low[node] =tin[node]=time++;
		int childCount=0;
		
		for(int neg:adj.get(node)) {
			 if(neg==parent) {
				 continue;
			 }
			 if(!visited[neg]) {
				 dfsArti(neg, node, visited, isArticulation, adj, low, tin);
				 low[node] = Math.min(low[node], low[neg]);
				 if(low[neg]>=tin[node] && parent !=-1) {
					 isArticulation[node]=true;
				 }
				 childCount++;
				 
			 }else {
				 low[node] = Math.min(low[node], tin[neg]);
			 }
			 if(parent ==-1 && childCount>1) {
				 isArticulation[node]=true;
			 }
		}
		
	}
	 public void floydWarshall(int[][] dist, int n) {
		 
		 for(int k=0;k<n;k++) {
			 
			 for(int i=0;i<n;i++) {
				 
				 for(int j=0;j<n;j++) {
					 if(dist[i][k] !=Integer.MAX_VALUE &&   dist[k][j] != Integer.MAX_VALUE) {
						 dist[i][j]=Math.min(dist[i][j], dist[i][k]+dist[k][j]);
					 }
				 }
			 }
		 }
	 }

	public static void main(String[] args) {
	String s="foobar"; char ch='o';
	System.out.println(percentageOfLetter(s, ch));
	}

}
