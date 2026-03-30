package com.dsa.tree.again.rev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import com.dsa.tree.TreeNode;
import com.dsa.tree.interview.question.geeksforgeeks.IncludeExcludePair;
import com.dsa.tree.interview.question.geeksforgeeks.TreeNod;
import com.dsa.tree.interview.question.geeksforgeeks.revision.TreeNodNextRightNode;
import com.dsa.tree.revision.IncExcludePair;

public class BstTreeImpl {

	public TreeNode insert(TreeNode root, int data) {
		if (root == null) {
			return new TreeNode(data);
		}
		if (root.data > data) {
			root.left = insert(root.left, data);
		} else if (root.data < data) {
			root.right = insert(root.right, data);
		}
		return root;
	}

	public TreeNode insertIterative(TreeNode root, int data) {
		TreeNode newNode = new TreeNode(data);

		if (root == null) {
			return newNode; // ✅ root updated
		}

		TreeNode current = root;
		TreeNode parent = null;

		while (current != null) {
			parent = current;
			if (data < current.data) {
				current = current.left;
			} else if (data > current.data) {
				current = current.right;
			} else {
				// duplicate value → ignore (or handle as needed)
				return root;
			}
		}

		if (data < parent.data) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}

		return root;
	}

	public TreeNode delete(TreeNode root, int target) {
		return deleteUtil(root, target);
	}

	private TreeNode deleteUtil(TreeNode node, int target) {
		if (node == null) {
			return null;
		}
		if (node.data > target) {
			node.left = delete(node.left, target);
		} else if (node.data < target) {
			node.right = delete(node.right, target);
		} else {
			if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			} else {
				node.data = findMin(node.right);
				node.right = delete(node.right, node.data);
			}
		}
		return node;
	}

	private int findMin(TreeNode node) {
		while (node.left != null) {
			node = node.left;
		}
		return node.data;
	}

	public boolean checkBst(TreeNode root) {
		return checkBstUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean checkBstUtil(TreeNode node, int minValue, int maxValue) {
		if (node == null) {
			return true;
		}
		if (node.data <= minValue || node.data >= maxValue) {
			return false;
		}
		return checkBstUtil(node.left, minValue, node.data) && checkBstUtil(node.right, node.data, maxValue);
	}

	public boolean validBst(TreeNode root) {
		TreeNode[] prev = new TreeNode[1]; // acts like a mutable reference
		return validBstUtil(root, prev);
	}

	private boolean validBstUtil(TreeNode node, TreeNode[] prev) {
		if (node == null) {
			return true;
		}

		if (!validBstUtil(node.left, prev)) {
			return false;
		}

		if (prev[0] != null && node.data <= prev[0].data) {
			return false;
		}

		prev[0] = node;

		return validBstUtil(node.right, prev);
	}

	public int floor(TreeNode root, int key) {
		int ans = -1;
		if (root == null) {
			return ans;
		}
		while (root != null) {
			if (root.data == key) {
				return root.data;
			}
			if (root.data > key) {
				root = root.left;
			} else {
				ans = root.data;
				root = root.right;
			}
		}
		return ans;
	}

	public int ceil(TreeNode root, int key) {
		int ans = -1;

		while (root != null) {
			if (root.data == key) {
				return root.data; // exact match = best ceil
			}
			if (root.data > key) {
				ans = root.data; // possible ceil
				root = root.left; // try smaller >= key
			} else {
				root = root.right;
			}
		}
		return ans;
	}

	public boolean twoSum(TreeNode root, int target) {
		HashSet<Integer> set = new HashSet<Integer>();
		return twoSumUtil(root, set, target);
	}

	private boolean twoSumUtil(TreeNode node, HashSet<Integer> set, int target) {
		if (node == null) {
			return false;
		}
		if (twoSumUtil(node.left, set, target)) {
			return true;
		}
		if (set.contains(target - node.data)) {
			return true;
		}
		set.add(node.data);
		return twoSumUtil(node.right, set, target);
	}

	public boolean twoSumIt(TreeNode root, int target) {
		if (root == null) {
			return false;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		HashSet<Integer> set = new HashSet<Integer>();

		while (!queue.isEmpty()) {
			TreeNode curret = queue.poll();
			// we can check null and contiobue
			if (set.contains(target - curret.data)) {
				return true;
			}
			set.add(curret.data);
			if (curret.left != null) {
				queue.add(curret.left);
			}
			if (curret.right != null) {
				queue.add(curret.right);
			}
		}
		return false;
	}

	public ArrayList<Integer> verticalTraversal(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		Map<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		Queue<HdVPair> queue = new LinkedList<HdVPair>();
		queue.add(new HdVPair(0, root));

		while (!queue.isEmpty()) {
			HdVPair pair = queue.poll();
			int hd = pair.hd;
			TreeNode current = pair.node;
			if (map.containsKey(hd)) {
				map.get(hd).add(current.data);
			} else {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(current.data);
				map.put(hd, temp);
			}
			if (current.left != null) {
				queue.add(new HdVPair(hd - 1, current.left));
			}
			if (current.right != null) {
				queue.add(new HdVPair(hd + 1, current.right));
			}
		}
		for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
			list.addAll(entry.getValue());
		}
		return list;
	}

	public int hUtil(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return Math.max(hUtil(root.left), hUtil(root.right)) + 1;
	}

	public int heightIt(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		int height = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TreeNode current = queue.poll();
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

	public boolean isIdentical(TreeNode r1, TreeNode r2) {
		if (r1 == null && r2 == null) {
			return true;
		}
		if (r1 == null || r2 == null) {
			return false;
		}

		return (r1.data == r2.data) && isIdentical(r1.left, r2.left) && isIdentical(r1.right, r2.right);
	}

	public boolean indeticalItrative(TreeNode r1, TreeNode r2) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(r1);
		queue.add(r2);

		while (!queue.isEmpty()) {
			TreeNode c1 = queue.poll();
			TreeNode c2 = queue.poll();
			if (c1 == null && c2 == null) {
				continue;
			}
			if (c1 == null || c2 == null || c1.data != c2.data) {
				return false;
			}
			queue.add(c1.left);
			queue.add(c2.left);
			queue.add(c1.right);
			queue.add(c2.right);
		}
		return true;
	}

	public void mirror(TreeNode root) {
		if (root == null) {
			return;
		}
		mirror(root.left);
		mirror(root.right);
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
	}

	public void mirroIt(TreeNode root) {
		if (root == null) {
			return;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode current = queue.poll();
			TreeNode temp = current.left;
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

	static boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (isSymUtil(root.left, root.right)) {
			return true;
		}
		return false;
	}

	private static boolean isSymUtil(TreeNode r1, TreeNode r2) {
		if (r1 == null && r2 == null)
			return true;
		if (r1 == null || r2 == null || r1.data != r2.data)
			return false;
		return isSymUtil(r1.left, r2.right) && isSymUtil(r1.right, r2.left);
	}

	public boolean balnceTree(TreeNode root) {
		return checkBal(root) != -1;
	}

	private int checkBal(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int lh = checkBal(node.left);
		int rh = checkBal(node.right);
		if (lh == -1 || rh == -1 || Math.abs(lh - rh) > 1) {
			return -1;
		}
		return 1 + Math.max(lh, rh);
	}

	public boolean isBalanced(TreeNode root) {
		// Renamed for clarity: isBalanced instead of balanceTree
		return checkHeight(root) != -1; // -1 indicates imbalance
	}

	private int checkHeight(TreeNode node) {
		// Base case: null node has height 0
		if (node == null) {
			return 0;
		}

		// Check left subtree
		int leftHeight = checkHeight(node.left);
		if (leftHeight == -1) {
			return -1; // Left subtree is unbalanced
		}

		// Check right subtree
		int rightHeight = checkHeight(node.right);
		if (rightHeight == -1) {
			return -1; // Right subtree is unbalanced
		}

		// Check current node's balance
		if (Math.abs(leftHeight - rightHeight) > 1) {
			return -1; // Current node is unbalanced
		}

		// Return height of current node
		return Math.max(leftHeight, rightHeight) + 1;
	}

	public boolean isSum(TreeNode root) {
		if (root == null) {
			return true;
		}

		return checkSum(root);
	}

	public boolean checkSum(TreeNode root) {
		// Base case: empty tree or leaf node
		if (root == null || (root.left == null && root.right == null)) {
			return true;
		}
		int sum = 0;
		if (root.left != null) {
			sum += root.left.data;
		}
		if (root.right != null) {
			sum += root.right.data;
		}
		return (root.data == sum) && checkSum(root.left) && checkSum(root.right);
	}

	public boolean childrenSum(TreeNode root) {
		if (root == null) {
			return true;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode current = queue.poll();
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

	public TreeNode sortedArrayToBst(int[] array) {
		int start = 0, end = array.length - 1;
		return arrayToBst(array, start, end);
	}

	public TreeNode arrayToBst(int[] array, int start, int end) {

		int mid = start + (end - start) / 2;
		TreeNode rootNode = new TreeNode(array[mid]);
		rootNode.left = arrayToBst(array, start, mid - 1);
		rootNode.right = arrayToBst(array, mid + 1, end);
		return rootNode;
	}

	public static ArrayList<Integer> KDistanceNodes(TreeNode root, int target, int k) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		HashMap<TreeNode, TreeNode> parentMap = new HashMap<TreeNode, TreeNode>();
		buildParentMap(root, null, parentMap);
		TreeNode targetNode = findNode(root, target);
		if (targetNode == null) {
			return list; // Target not found
		}
		HashSet<TreeNode> visited = new HashSet<TreeNode>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(targetNode);
		visited.add(targetNode);
		int dist = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			if (dist == k) {
				break;
			}

			for (int i = 0; i < size; i++) {
				TreeNode current = queue.poll();
				if (current.left != null) {
					queue.add(current.left);
					visited.add(current.left);
				}
				if (current.right != null) {
					queue.add(current.right);
					visited.add(current.right);
				}
				TreeNode parentNode = parentMap.get(current);
				if (parentNode != null && !visited.contains(parentNode)) {
					queue.add(parentNode);
					visited.add(parentNode);
				}
			}
			dist++;
		}
		for (TreeNode n : queue) {
			list.add(n.data);
		}
		return list;
	}

	private static TreeNode findNode(TreeNode root, int target) {
		if (root == null) {
			return null;
		}
		if (root.data == target) {
			return root;
		}
		TreeNode left = findNode(root.left, target);
		if (left != null) {
			return left;
		}
		return findNode(root.right, target);
	}

	private static void buildParentMap(TreeNode root, TreeNode parent, HashMap<TreeNode, TreeNode> parentMap) {
		if (root == null) {
			return;
		}
		parentMap.put(root, parent);
		buildParentMap(root.left, root, parentMap);
		buildParentMap(root.right, root, parentMap);

	}

	public static int getSucc(TreeNode root, int target) {
		TreeNode[] prev = new TreeNode[1];
		TreeNode[] succ = new TreeNode[1];
		Arrays.fill(prev, null);
		Arrays.fill(succ, null);

		getSuccInorderUtil(root, target, prev, succ);
		return succ[0].data;

	}

	public static void getSuccInorderUtil(TreeNode root, int target, TreeNode[] prev, TreeNode[] succ) {
		if (root == null || succ[0] != null) {
			return;
		}
		getSuccInorderUtil(root.left, target, prev, succ);
		if (prev[0] != null && prev[0].data == target && succ[0] == null) {
			succ[0] = root;
			return;
		}
		prev[0] = root;
		getSuccInorderUtil(root.right, target, prev, succ);

	}

	public static int succItrative(TreeNode root, int target) {
		if (root == null) {
			return -1;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode current = root;
		TreeNode prev = null;

		while (!stack.isEmpty() || current != null) {
			while (current != null) {
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();
			if (prev != null && prev.data == target) {
				return current.data;
			}
			prev = current;
			current = current.right;
		}
		return -1;
	}

	public static Integer successorOptimized(TreeNode root, int target) {
		Integer successor = null;
		TreeNode current = root;

		while (current != null) {
			if (current.data > target) {
				// Found a greater value → potential successor
				successor = current.data;
				current = current.left; // Look for smaller but still > target
			} else {
				// Go right to find larger values
				current = current.right;
			}
		}

		return successor;
	}

	public static int kthLargest(TreeNode root, int k) {
		int[] count = { 0 };
		int[] largest = { 0 };
		kthUtil(root, k, count, largest);
		return largest[0];
	}

	private static void kthUtil(TreeNode root, int k, int[] count, int[] largest) {
		if (root == null) {
			return;
		}
		kthUtil(root.right, k, count, largest);
		count[0]++;
		if (count[0] == k) {
			largest[0] = root.data;
			return;
		}
		kthUtil(root.left, k, count, largest);
	}

	public static int kthLarIt(TreeNode root, int k) {
		if (root == null) {
			return -1;
		}
		Stack<TreeNode> stack = new Stack<>();
		TreeNode current = root;
		int dist = 0;

		while (!stack.isEmpty() || current != null) {
			while (current != null) {
				stack.push(current);
				current = current.right;
			}
			current = stack.pop();
			dist++;
			if (dist == k) {
				return current.data;
			}

			current = current.left;
		}
		return -1;
	}

	public static TreeNode balanceAnyBinaryTree(TreeNode root) {
		if (root == null)
			return null;

		// 1. Collect ALL values from the tree
		List<Integer> values = new ArrayList<>();
		collectValues(root, values);

		// 2. Sort the values (CRITICAL for non-BST!)
		Collections.sort(values);

		// 3. Build balanced BST from sorted values
		return buildBSTFromSorted(values, 0, values.size() - 1);
	}

	private static void collectValues(TreeNode node, List<Integer> values) {
		if (node == null)
			return;
		values.add(node.data);
		collectValues(node.left, values);
		collectValues(node.right, values);
	}

	private static TreeNode buildBSTFromSorted(List<Integer> values, int left, int right) {
		if (left > right)
			return null;

		int mid = left + (right - left) / 2;
		TreeNode root = new TreeNode(values.get(mid));

		root.left = buildBSTFromSorted(values, left, mid - 1);
		root.right = buildBSTFromSorted(values, mid + 1, right);

		return root;
	}

	public int diameter(TreeNode root) {
		int[] ans = { 0 };
		diameterUtil(root, ans);
		return ans[0];
	}

	private int diameterUtil(TreeNode root, int[] ans) {
		if (root == null) {
			return 0;
		}
		int lh = diameterUtil(root.left, ans);
		int rh = diameterUtil(root.right, ans);
		ans[0] = Math.max(ans[0], 1 + lh + rh);

		return 1 + Math.max(lh, rh);
	}

	public boolean checkSubtree(TreeNode root1, TreeNode root2) {
		if (root2 == null) {
			return true;
		}
		if (root1 == null) {
			return false;
		}
		if (areIdentical(root1, root2)) {
			return true;
		}

		return checkSubtree(root1.left, root2) || checkSubtree(root1.right, root2);

	}

	private boolean areIdentical(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null || root1.data != root2.data) {
			return false;
		}

		return areIdentical(root1.left, root2.left) && areIdentical(root1.right, root2.right);
	}

	public boolean isValidBst(TreeNode root) {
		if (root == null) {
			return true;
		}
		return bstUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean bstUtil(TreeNode root, int minValue, int maxValue) {
		if (root == null) {
			return true;
		}
		if (root.data <= minValue || root.data >= maxValue) {
			return false;

		}
		return bstUtil(root.left, minValue, root.data) && bstUtil(root.right, root.data, maxValue);
	}

	public boolean checkBst1(TreeNode root) {
		if (root == null) {
			return true;
		}
		TreeNode[] prev = new TreeNode[1];
		prev[0] = null;
		return cBstUtil(root, prev);
	}

	private boolean cBstUtil(TreeNode root, TreeNode[] prev) {
		if (root == null) {
			return true;
		}
		if (!cBstUtil(root.left, prev)) {
			return false;
		}
		if (prev[0] != null && root.data <= prev[0].data) {
			return false;
		}
		prev[0] = root;
		return cBstUtil(root.right, prev);
	}

	int count = 0;

	public int singleValued(TreeNode root) {
		if (root == null) {
			return 0;
		}
		singleValuedUtil(root);
		return count;
	}

	private boolean singleValuedUtil(TreeNode root) {
		if (root == null) {
			return true;
		}
		boolean left = singleValuedUtil(root.left);
		boolean right = singleValuedUtil(root.right);
		if (left == false || right == false) {
			return false;
		}
		if (root.left != null && root.data != root.left.data) {
			return false;
		}
		if (root.right != null && root.data != root.right.data) {
			return false;
		}
		count++;
		return true;

	}


	public int singleValuedItr(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int count = 0;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode current = queue.poll();
			if (isSingleValued(current)) {
				count++;
			}
			if (current.left != null) {
				queue.add(current.left);
			}
			if (current.right != null) {
				queue.add(current.right);
			}
		}
		return count;
	}

	private boolean isSingleValued(TreeNode node) {
		if (node.left != null && node.left.data != node.data) {
			return false;
		}
		if (node.right != null && node.right.data != node.data) {
			return false;
		}
		return true;
	}

	public ArrayList<Integer> zigzagTra(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		boolean leftToRight = true;
		queue.add(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			LinkedList<Integer> temp = new LinkedList<Integer>();
			for (int i = 0; i < size; i++) {
				TreeNode current = queue.poll();

				if (leftToRight) {
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
			leftToRight = !leftToRight;
		}
		return list;
	}

	public ArrayList<Integer> zigzagTravarse(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		boolean leftToRight = true;
		int height = heightIt(root);
		for (int i = 1; i <= height; i++) {
			if (leftToRight) {
				collectLeft(root.left, i, list);
			} else {
				collectRight(root.right, i, list);
			}
			leftToRight = !leftToRight;
		}
		return list;

	}

	private void collectRight(TreeNode node, int level, ArrayList<Integer> list) {
		if (node == null) {
			return;
		}
		if (level == 1) {
			list.add(node.data);
		}
		collectRight(node.right, level - 1, list);
		collectRight(node.left, level - 1, list);

	}

	private void collectLeft(TreeNode node, int level, ArrayList<Integer> list) {
		if (node == null) {
			return;
		}
		if (level == 1) {
			list.add(node.data);
		}
		collectLeft(node.left, level - 1, list);
		collectLeft(node.right, level - 1, list);

	}

	public static ArrayList<Integer> boundaryTraversal(TreeNod root) {
		ArrayList<Integer> list = new ArrayList<>();
		if (root == null)
			return list;

		// Add root if not leaf
		if (!isLeaf(root)) {
			list.add(root.data);
		}

		// Left boundary (top-down, exclude leaves)
		addLeftBoundary(root.left, list);

		// All leaves (left to right)
		addLeaves(root, list);

		// Right boundary (bottom-up, exclude leaves)
		addRightBoundary(root.right, list);

		return list;
	}

	// FIXED: Follow only the boundary path!
	private static void addLeftBoundary(TreeNod node, ArrayList<Integer> list) {
		if (node == null || isLeaf(node))
			return;

		list.add(node.data); // Add before going down (top-down)

		// KEY FIX: Only follow ONE path - left if exists, else right
		if (node.left != null) {
			addLeftBoundary(node.left, list);
		} else {
			addLeftBoundary(node.right, list);
		}
	}

	// FIXED: Follow only the boundary path, add in reverse!
	private static void addRightBoundary(TreeNod node, ArrayList<Integer> list) {
		if (node == null || isLeaf(node))
			return;

		// KEY FIX: Only follow ONE path - right if exists, else left
		if (node.right != null) {
			addRightBoundary(node.right, list);
		} else {
			addRightBoundary(node.left, list);
		}

		list.add(node.data); // Add after recursion (bottom-up)
	}

	// Leaves collection stays the same
	private static void addLeaves(TreeNod node, ArrayList<Integer> list) {
		if (node == null)
			return;

		if (isLeaf(node)) {
			list.add(node.data);
			return;
		}

		addLeaves(node.left, list);
		addLeaves(node.right, list);
	}

	private static boolean isLeaf(TreeNod node) {
		return node != null && node.left == null && node.right == null;
	}

	private boolean isLeaf(TreeNode node) {
		return (node.left == null && node.right == null);
	}

	public static TreeNode buildTree(int[] inorder, int[] preorder) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		int[] preIndex = { 0 };
		return buildTreeUtil(preorder, map, 0, preorder.length - 1, preIndex);
	}

	private static TreeNode buildTreeUtil(int[] preorder, HashMap<Integer, Integer> map, int start, int end,
			int[] preIndex) {
		if (start > end) {
			return null;
		}
		int rootValue = preorder[preIndex[0]++];
		int index = map.get(rootValue);
		TreeNode rootNode = new TreeNode(rootValue);
		rootNode.left = buildTreeUtil(preorder, map, start, index - 1, preIndex);
		rootNode.right = buildTreeUtil(preorder, map, index + 1, end, preIndex);
		return rootNode;
	}

	public static TreeNode construct(List<Integer> pre) {
		TreeNode root = null;
		for (int key : pre) {
			root = buildTreePreUtil(root, key);
		}
		return root;
	}

	private static TreeNode buildTreePreUtil(TreeNode root, int key) {
		if (root == null) {
			return new TreeNode(key);
		} else if (root.data > key) {
			root.left = buildTreePreUtil(root.left, key);
		} else {
			root.right = buildTreePreUtil(root.right, key);
		}
		return root;
	}

	public static int distInTwoNodes(TreeNode root, int a, int b) {
		if (root == null) {
			return 0;
		}
		TreeNode lcaNode = lcaUtil(root, a, b);
		// int d1= findLevel(lcaNode,a,0);
		// int d2= findLevel(lcaNode,b,0);
		int d1 = findLevelIt(lcaNode, a);
		int d2 = findLevelIt(lcaNode, b);

		return d1 + d2;
	}

	public static int findLevelIt(TreeNode node, int k) {
		if (node == null) {
			return 0;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(node);
		int dist = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TreeNode current = queue.poll();
				if (current.data == k) {
					return dist;
				}
				if (current.left != null) {
					queue.add(current.left);
				}
				if (current.right != null) {
					queue.add(current.right);
				}
			}
			dist++;
		}
		return dist;
	}

	private static int findLevel(TreeNode lcaNode, int a, int level) {
		if (lcaNode == null) {
			return -1;
		}
		if (lcaNode.data == a) {
			return level;
		}
		int left = findLevel(lcaNode.left, a, level + 1);
		if (left == -1) {
			return findLevel(lcaNode.right, a, level);
		}
		return left;
	}

	private static TreeNode lcaUtil(TreeNode root, int a, int b) {
		if (root == null) {
			return null;
		}
		if (root.data == a || root.data == b) {
			return root;
		}
		TreeNode left = lcaUtil(root.left, a, b);
		TreeNode right = lcaUtil(root.right, a, b);
		if (left != null && right != null) {
			return root;
		}
		if (left == null) {
			return right;
		}
		if (right == null) {
			return left;
		}
		return root;
	}

	public static int maxSumLeaf(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int[] maxSum = { 0 };

		maxSumUtil(root, 0, maxSum);
		return maxSum[0];
	}

	private static void maxSumUtil(TreeNode root, int currentSum, int[] maxSum) {
		if (root == null) {
			return;
		}
		currentSum += root.data;
		if (root.left == null && root.right == null) {
			if (currentSum > maxSum[0]) {
				maxSum[0] = currentSum;
			}
			return;
		}
		maxSumUtil(root.left, currentSum, maxSum);
		maxSumUtil(root.right, currentSum, maxSum);
	}

	// Custom wrapper - MORE ELEGANT!
	class TreeNodeWithSum {
		TreeNode node;
		int cumulativeSum;

		TreeNodeWithSum(TreeNode node, int cumulativeSum) {
			this.node = node;
			this.cumulativeSum = cumulativeSum;
		}
	}

	public int maxSumLeafWithWrapper(TreeNode root) {
		if (root == null)
			return 0;

		Queue<TreeNodeWithSum> queue = new LinkedList<>();
		queue.add(new TreeNodeWithSum(root, root.data));

		int maxSum = Integer.MIN_VALUE;

		while (!queue.isEmpty()) {
			TreeNodeWithSum current = queue.poll();

			// Leaf check
			if (current.node.left == null && current.node.right == null) {
				maxSum = Math.max(maxSum, current.cumulativeSum);
			}

			// Left child
			if (current.node.left != null) {
				queue.add(new TreeNodeWithSum(current.node.left, current.cumulativeSum + current.node.left.data));
			}

			// Right child
			if (current.node.right != null) {
				queue.add(new TreeNodeWithSum(current.node.right, current.cumulativeSum + current.node.right.data));
			}
		}

		return maxSum;
	}

	public int evenOddDiff(TreeNode root) {
		int[] evenSum = { 0 };
		int[] oddSum = { 0 };

		evenOddUtil(root, evenSum, oddSum, 0);
		return evenSum[0] - oddSum[0];
	}

	private void evenOddUtil(TreeNode root, int[] evenSum, int[] oddSum, int level) {
		if (root == null) {
			return;
		}
		if ((level & 1) == 0) {
			evenSum[0] += root.data;
		} else {
			oddSum[0] += root.data;
		}
		evenOddUtil(root.left, evenSum, oddSum, level + 1);
		evenOddUtil(root.right, evenSum, oddSum, level + 1);

	}

	public int evenOddItrative(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int evenSum = 0, oddSum = 0;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		int level = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TreeNode current = queue.poll();

				if (level % 2 == 0) {
					evenSum += current.data;
				} else {
					oddSum += current.data;
				}
			}
			level++;
		}
		return evenSum - oddSum;
	}

	static TreeNode removekeys(TreeNode root, int l, int r) {
		if (root == null) {
			return null;
		}
		TreeNode left = removekeys(root.left, l, r);
		TreeNode right = removekeys(root.right, l, r);
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

	public TreeNode removeKeys1(TreeNode node, int l, int r) {
		if (node == null) {
			return null;
		}
		if (node.data < l) {
			return removeKeys1(node.right, l, r);
		}
		if (node.data > r) {
			return removeKeys1(node.left, l, r);
		}
		removeKeys1(node.left, l, r);
		removeKeys1(node.right, l, r);

		return node;

	}

	public TreeNode removeKeyIt(TreeNode root, int l, int r) {
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
		TreeNode curr = root;

		while (curr != null) {
			while (curr.left != null && curr.left.data < l) {
				curr.left = curr.left.right;
			}
			curr = curr.left;
		}
		curr = root;
		// Step 3: Fix right subtree
		while (curr != null) {
			while (curr.right != null && curr.right.data > r) {
				curr.right = curr.right.left;
			}
			curr = curr.right;
		}

		return root;

	}

	public void greateSumTree(TreeNode root) {
		if (root == null) {
			return;
		}
		int[] sum = { 0 };
		greateSum(root, sum);
	}

	private void greateSum(TreeNode root, int[] sum) {
		if (root == null) {
			return;
		}
		greateSum(root.right, sum);
		int temp = root.data;
		root.data = sum[0];
		sum[0] += temp;
		greateSum(root.left, sum);
	}

	public void greaterSumItrative(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode current = root;
		int sum = 0;

		while (!stack.isEmpty() || current != null) {
			while (current != null) {
				stack.push(current);
				current = current.right;
			}
			current = stack.pop();
			int temp = current.data;
			current.data = sum;
			sum += temp;
			current = current.left;
		}

	}

	public IncludeExcludePair nonAdjSumMax(TreeNode root) {
		if (root == null) {
			return new IncludeExcludePair(0, 0);
		}
		IncludeExcludePair left = nonAdjSumMax(root.left);
		IncludeExcludePair right = nonAdjSumMax(root.right);
		int include = root.data + left.include + right.include;
		int exclude = Math.max(left.include, left.exclude) + Math.max(right.include, right.exclude);

		return new IncludeExcludePair(include, exclude);

	}

	public int lagestBst(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int max = 0;
		if (chValidBst(root)) {
			int size = size(root);
			max = Math.max(max, size);
		}
		// If not, check left and right subtrees
		return Math.max(lagestBst(root.left), lagestBst(root.right));
	}

	private int size(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + size(root.left) + size(root.right);
	}

	private boolean chValidBst(TreeNode root) {
		if (root == null) {
			return true;
		}
		return chBstUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean chBstUtil(TreeNode root, int minValue, int maxValue) {
		if (root == null) {
			return true;
		}
		if (root.data <= minValue || root.data >= maxValue) {
			return false;
		}
		return chBstUtil(root.left, minValue, root.data) && chBstUtil(root.right, root.data, maxValue);
	}

	public ArrayList<Integer> extremeNode(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		boolean leftRight = true;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TreeNode current = queue.poll();

				if (leftRight && i == 0) {
					list.add(current.data);
				} else if (!leftRight && i == size - 1) {
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

}
