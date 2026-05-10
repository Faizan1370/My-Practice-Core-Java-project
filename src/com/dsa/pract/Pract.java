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
import com.dsa.graph.kruskal.algo.Edge;
import com.dsa.graph.revision2.dijkstra.VertexD;
import com.dsa.singlylinkedlist.revision.LisNodeRe;
import com.dsa.takuforward.graph.NodeIndexPair;
import com.dsa.tree.TreeNode;
import com.dsa.tree.interview.question.geeksforgeeks.IncludeExcludePair;
import com.dsa.tree.interview.question.geeksforgeeks.TreeNod;
import com.dsa.tree.interview.question.geeksforgeeks.revision.NodeInfo;
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
       return  dfsCheckPath(u,v,adj,visited);
         
	}

	private static boolean dfsCheckPath(int currrent, int dest, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
		visited[currrent]=true;
		if(currrent==dest) {
			return true;
		}
		for(int ng:adj.get(currrent)) {
			if(!visited[ng]) {
				if(dfsCheckPath(ng, dest, adj, visited)) {
					return true;
				}
			}
		}
		return false;
		
	}
	static int checkPathCount(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        int V = adj.size();
        boolean[] visited = new boolean[V];
        int[] count = {0};
        dfsCheckPathCount(u,v,adj,visited,count);
        return count[0];
        
	}

	private static void dfsCheckPathCount(int currrent, int dest, ArrayList<ArrayList<Integer>> adj, boolean[] visited, int[] count) {
		visited[currrent]=true;
		if(currrent==dest) {
			count[0]++;
			return;
		}
		for(int ng:adj.get(currrent)) {
			if(!visited[ng]) {
				dfsCheckPathCount(ng, dest, adj, visited,count);
				
			}
		}
		visited[currrent]=false;
		
	}

	public static void main(String[] args) {
	}

}
