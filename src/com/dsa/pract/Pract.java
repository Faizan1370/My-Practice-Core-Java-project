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

	public static int[] rotateByK(int[] nums, int k) {
		int n = nums.length;
		if (n == 0 || n <= k || k <= 0) {
			throw new RuntimeException("invalid input");
		}
		k = k % n;
		reverse(nums, 0, n - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, n - 1);
		return nums;
	}

	public static void findMaxSumSubArray() {
		int currentSum = 0, maxSum = 0;
		int[] array = { 3, -2, -3, 4, 7 };
		for (int i = 0; i < array.length; i++) {
			currentSum = currentSum + array[i];
			if (currentSum > maxSum) {
				maxSum = currentSum;
			}
			if (currentSum < 0) {
				currentSum = 0;
			}
		}
		System.out.println(maxSum);
	}

	public static void findMaxSumSubArray1() {
		int currentSum = 0, maxSum = 0;
		int[] array = { 3, -2, -3, 4, 7 };
		for (int i = 0; i < array.length; i++) {
			currentSum = Math.max(array[i], currentSum + array[i]);
			maxSum = Math.max(currentSum, maxSum);
		}
		System.out.println(maxSum);

	}

	public static ArrayList<int[]> indices(int[] array, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		ArrayList<int[]> list = new ArrayList<int[]>();
		for (int i = 0; i < array.length; i++) {
			int compliment = target - array[i];
			if (map.containsKey(compliment)) {
				list.add(new int[] { i, map.get(compliment) });
			} else {
				map.put(array[i], i);
			}
		}
		return list;
	}

	public static int[] twoSumSortedArray(int[] nums, int target) {
		int start = 0, end = nums.length - 1;
		while (start < end) {
			if ((nums[start] + nums[end]) == target) {
				return new int[] { start, end };
			} else if ((nums[start] + nums[end]) > target) {
				end--;
			} else {
				start++;
			}
		}
		return new int[] { -1, -1 };
	}

	private static void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}

	}

	public static int kthLargest(int[] nums, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for (int num : nums) {
			queue.add(num);
			if (queue.size() > k) {
				queue.remove();
			}
		}
		return queue.peek();
	}

	public static int kthSmallest(int[] nums, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>((a, b) -> b - a);
		for (int num : nums) {
			queue.add(num);
			if (queue.size() > k) {
				queue.remove();
			}
		}
		return queue.peek();
	}

	public static void removeDeuplicate() {
		int[] array = { 7, 8, 3, 7, 4 };
		int[] result = new int[array.length];
		int k = 0;
		for (int i = 0; i < array.length; i++) {
			boolean isDuplicate = false;
			for (int j = 0; j < k; j++) {
				if (result[j] == array[i]) {
					isDuplicate = true;
					break;
				}
			}
			if (!isDuplicate) {
				result[k] = array[i];
				k++;
			}
		}
		System.out.println(Arrays.toString(result));

	}

	public static void removeDeuplicate1() {
		int[] array = { 7, 8, 3, 7, 4 };
		int[] result = new int[array.length];
		boolean[] seen = new boolean[101];
		int k = 0;
		for (int i = 0; i < array.length; i++) {
			if (!seen[array[i]]) {
				seen[array[i]] = true;
				result[k] = array[i];
				k++;
			}
		}
		System.out.println(Arrays.toString(result));

	}

	public static void removeDeuplicate2() {
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
		System.out.println(Arrays.toString(result));

	}

	public static void insertElement() {
		int[] array = { 3, 5, 7, 2, 9 };
		int element = 6;
		int position = 3;
		for (int i = array.length - 1; i > position - 1; i--) {
			array[i] = array[i - 1];
		}
		array[position - 1] = element;
		System.out.println(Arrays.toString(array));

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
		System.out.println(Arrays.toString(array));

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
		System.out.println(Arrays.toString(array));

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
		System.out.println(Arrays.toString(array));

	}

	public static String shufflingString(String s, int[] indices) {
		if (s.length() != indices.length) {
			return "";
		}
		char[] chrs = new char[indices.length];
		for (int i = 0; i < indices.length; i++) {
			chrs[indices[i]] = s.charAt(i);
		}

		return new String(chrs);
	}

	public static double meanOfArray(int[] arr) {
		Arrays.sort(arr);
		int limit = arr.length / 4; // 25%
		int sum = 0;
		for (int i = limit; i < arr.length - limit; i++) {
			sum += arr[i];
		}
		return (double) sum / (arr.length - (2 * limit));
	}

	public static ArrayList<Integer> sortArrayByFreq(int[] nums) {
		Map<Integer, Long> map = Arrays.stream(nums).mapToObj(num -> (Integer) num)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int num : nums) {
			list.add(num);
		}
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (map.get(o1) != map.get(o2)) {
					return (int) (map.get(o1) - map.get(o2));
				} else {
					return o2 - o1;
				}
			}
		});
		return list;

	}

	public static String goalParser(String s) {
		StringBuilder builder = new StringBuilder();
		int i = 0;
		while (i < s.length()) {
			if (s.charAt(i) == 'G') {
				builder.append("G");
				i = i + 1;
			} else if (s.charAt(i) == '(' && s.charAt(i + 1) == ')') {
				builder.append("o");
				i = i + 2;
			} else {
				builder.append("al");
				i = i + 4;
			}
		}
		return builder.toString();
	}

	public static int[] findRedundantConnection(int[][] edges) {
		int n = edges.length;
		DSSet dsSet = new DSSet(n);
		int[] result = new int[2];
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			if (dsSet.unionRed(u, v)) {
				result = edge;
			}
		}
		return result;
	}

	public static int sumOfUniqueElements(int[] nums) {
		final int[] sum = { 0 };
		Arrays.stream(nums).mapToObj(num -> (Integer) num)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(entry -> entry.getValue() == 1L).map(entry -> entry.getKey()).forEach(num -> {
					sum[0] += num; // Now it works!
				});
		return sum[0];
	}

	public static String concatAlternatechars(String p, String q) {
		String longest = "";
		int len1 = p.length();
		int len2 = q.length();
		int min = Math.min(len1, len2);
		if (len1 == min) {
			longest = q;
		}
		if (len2 == min) {
			longest = p;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < min; i++) {
			builder.append(p.charAt(i));
			builder.append(q.charAt(i));
		}
		builder.append(longest.substring(min));
		return builder.toString();

	}

	public static int signOfProduct(int[] nums) {
		int negCount = 0;
		for (int num : nums) {
			if (num == 0) {
				return 0; // Product is zero
			}
			if (num < 0) {
				negCount++;
			}
		}
		return (negCount % 2 == 0) ? 1 : -1;
	}

	public static boolean checkPanagram(String s) {
		HashSet<Character> set = new HashSet<Character>();
		s = s.toLowerCase();
		for (int i = 0; i < s.length(); i++) {
			if (Character.isAlphabetic(s.charAt(i))) {
				set.add(s.charAt(i));
			}

		}
		return set.size() == 26;
	}

	public static String sortingSentence(String s) {
		String[] words = s.split(" ");
		String[] rs = new String[words.length];
		for (String word : words) {
			// Get the position number (last character)
			int position = Integer.parseInt(word.substring(word.length() - 1));
			// Get the actual word (without the number)
			String actualWord = word.substring(0, word.length() - 1);
			rs[position - 1] = actualWord; // 1-indexed to 0-indexed
		}

		return String.join(" ", rs);
	}

	public static int[] concatArray(int[] nums) {
		int n = nums.length;
		int[] res = new int[2 * n];
		for (int i = 0; i < nums.length; i++) {
			res[i] = nums[i];
			res[n + i] = nums[i];
		}
		return res;
	}

	public static boolean checkEqualOccurence(String s) {
		int size = s.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.map(entry -> entry.getValue()).collect(Collectors.toSet()).size();
		return size == 1;
	}

	public int findCircleNum(int[][] isConnected) {
		int n = isConnected.length;
		boolean[] visited = new boolean[n];
		int provinces = 0;

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfsProvince(i, visited, isConnected);
				provinces++;
			}
		}
		return provinces;
	}

	private void dfsProvince(int city, boolean[] visited, int[][] isConnected) {
		visited[city] = true;

		for (int j = 0; j < isConnected.length; j++) {
			if (isConnected[city][j] == 1 && !visited[j]) {
				dfsProvince(j, visited, isConnected);
			}
		}

	}

	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
		int n = rooms.size();
		boolean[] visited = new boolean[n];
		dfsRooms(0, visited, rooms);
		for (boolean v : visited) {
			if (!v) {
				return false;
			}
		}
		return true;
	}

	private void dfsRooms(int room, boolean[] visited, List<List<Integer>> rooms) {
		visited[room] = true;
		for (int nxt : rooms.get(room)) {
			if (!visited[nxt]) {
				dfsRooms(nxt, visited, rooms);
			}
		}
	}
	public int countIslands(char[][] grid) {
		int m=grid.length;
		int n=grid[0].length;
		boolean[][] visited = new boolean[m][n];
		int islands=0;
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(!visited[i][j] && grid[i][j]=='L') {
					//bfsCountIsland(i,j,grid,visited);
					dfsIsland(i,j,grid,visited);
					islands++;
				}
			}
		}
		return islands;
	}

	private void dfsIsland(int r, int c, char[][] grid, boolean[][] visited) {
		int[] dr= {-1,-1,-1,0,1,1,1,0};
		int[] dc= {-1,0,1,1,1,0,-1,-1};
		visited[r][c]=true;
		
		for(int i=0;i<8;i++) {
			int nr=r+dr[i];
			int nc=c+dc[i];
			if(nr>=0 && nr<grid.length && nc>=0 && nc<grid[0].length && !visited[nr][nc] && grid[nr][nc]=='L') {
				dfsIsland(nr, nc, grid, visited);
			}
		}
		
	}

	private void bfsCountIsland(int r, int c, char[][] grid, boolean[][] visited) {
		int row =grid.length;
		int col =grid[0].length;
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {r,c});
		visited[r][c]=true;
		int[] dr= {-1,-1,-1,0,1,1,1,0};
		int[] dc= {-1,0,1,1,1,0,-1,-1};
		
		while(!queue.isEmpty()) {
			int[] pair = queue.poll();
			for(int i=0;i<8;i++) {
				int nr=pair[0]+dr[i];
				int nc=pair[1]+dc[i];
				if(nr>=0 && nr<row && nc>=0 && nc<col && !visited[nr][nc] && grid[nr][nc]=='L') {
					queue.add(new int[] {nr,nc});
					visited[nr][nc]=true;
				}
			}
			
			
		}
		
	}
	public int getMinimuDiceThrows(int[] moves) {
		int n = moves.length;
		boolean[] visited = new boolean[n];
		Queue<SnakeCell> queue = new LinkedList<SnakeCell>();
		queue.add(new SnakeCell(0, 0));
		visited[0]=true;
		while (!queue.isEmpty()) {
			SnakeCell pair = queue.poll();
			if(pair.vertex==n-1) {
				return pair.dist;
			}
			for(int dice =1;dice<=6 && dice<n ; dice++) {
				int next = pair.vertex+dice;
				int dest=(moves[next] !=-1?moves[next]:next);
				if(!visited[dest]) {
					visited[next]=true;
					visited[dest]=true;
					queue.add(new SnakeCell(dest, pair.dist+1));
				}
				
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		String s = "is2 sentence4 This1 a3";
		System.out.println(sortingSentence(s));
	}

}
