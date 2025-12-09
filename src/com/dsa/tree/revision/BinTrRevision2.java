package com.dsa.tree.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;

import com.dsa.tree.interview.question.geeksforgeeks.TreeNod;

public class BinTrRevision2 {
	TreeNodeRev root;

	public void insert(int data) {
		TreeNodeRev newNode = new TreeNodeRev(data);
		if (root == null) {
			root = newNode;
			return;
		}
		Queue<TreeNodeRev> queue = new LinkedList<TreeNodeRev>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNodeRev current = queue.poll();

			if (current.left == null) {
				current.left = newNode;
				return;
			} else {
				queue.add(current.left);
			}
			if (current.right == null) {
				current.right = newNode;
				return;
			} else {
				queue.add(current.right);
			}
		}
	}

	static boolean isIdentical(TreeNodeRev r1, TreeNodeRev r2) {
		if (r1 == null && r2 == null) {
			return true;
		}
		if (r1 == null || r2 == null) {
			return false;
		}

		return r1.data == r2.data && isIdentical(r1.left, r2.left) && isIdentical(r1.right, r2.right);

	}

	public static boolean isIdenticalItrative(TreeNodeRev r1, TreeNodeRev r2) {
		if (r1 == null && r2 == null) {
			return true;
		}
		if (r1 == null || r2 == null) {
			return false;
		}

		Queue<TreeNodeRev> queue = new LinkedList<TreeNodeRev>();
		Queue<TreeNodeRev> queue1 = new LinkedList<TreeNodeRev>();
		queue.add(r1);
		queue1.add(r2);
		while (!queue.isEmpty() && !queue1.isEmpty()) {
			TreeNodeRev currentR1 = queue.poll();
			TreeNodeRev currentR2 = queue1.poll();
			if (currentR1.data != currentR2.data) {
				return false;
			}
			if (currentR1.left != null && currentR2.left != null) {
				queue.add(currentR1.left);
				queue1.add(currentR2.left);
			} else if (currentR1.left == null || currentR2.left == null) {
				return false;
			}

			if (currentR1.right != null && currentR2.right != null) {
				queue.add(currentR1.right);
				queue1.add(currentR2.right);
			} else if (currentR1.right == null || currentR2.right == null) {
				return false;
			}

		}
		return queue.isEmpty() && queue1.isEmpty();
	}

	public static void mirror(TreeNodeRev node) {
		if (node == null) {
			return;
		}
		mirror(node.left);
		mirror(node.right);

		TreeNodeRev temp = node.left;
		node.left = node.right;
		node.right = temp;

	}

	public static void levelOrder(TreeNodeRev node) {
		if (node == null) {
			return;
		}
		Queue<TreeNodeRev> queue = new LinkedList<TreeNodeRev>();
		queue.add(node);

		while (!queue.isEmpty()) {
			TreeNodeRev current = queue.poll();
			System.out.print(current.data + " ");
			if (current.left != null) {
				queue.add(current.left);
			}
			if (current.right != null) {
				queue.add(current.right);
			}
		}
	}

	public static void mirrorItrative(TreeNodeRev node) {
		if (node == null) {
			return;
		}
		Queue<TreeNodeRev> queue = new LinkedList<TreeNodeRev>();
		queue.add(node);

		while (!queue.isEmpty()) {
			TreeNodeRev current = queue.poll();

			TreeNodeRev temp = current.left;
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

	public static boolean symetric(TreeNodeRev node) {
		if (node == null) {
			return true;
		}
		return isMirror(node.left, node.right);
	}

	private static boolean isMirror(TreeNodeRev leftNode, TreeNodeRev rightNode) {
		if (leftNode == null && rightNode == null) {
			return true;
		}
		if (leftNode == null || rightNode == null || leftNode.data != rightNode.data) {
			return false;
		}
		return isMirror(leftNode.left, rightNode.right) && isMirror(leftNode.right, rightNode.left);
	}

	public static boolean symetricItrative(TreeNodeRev node) {
		if (node == null) {
			return true;
		}
		Queue<TreeNodeRev> queue1 = new LinkedList<TreeNodeRev>();
		queue1.add(node.left);
		queue1.add(node.right);

		while (!queue1.isEmpty()) {
			TreeNodeRev leftN = queue1.poll();
			TreeNodeRev rightN = queue1.poll();
			if (leftN == null && rightN == null) {
				continue;
			}
			if (leftN == null || rightN == null || leftN.data != rightN.data) {
				return false;
			}
			queue1.add(leftN.left);
			queue1.add(rightN.right);

			queue1.add(leftN.right);
			queue1.add(rightN.left);
		}

		return queue1.isEmpty();
	}

	public static boolean balanceTree(TreeNodeRev node) {
		if (node == null) {
			return true;
		}
		int lh = hieght(node.left);
		int rh = hieght(node.right);
		if (Math.abs(lh - rh) > 1) {
			return false;
		}

		return balanceTree(node.left) && balanceTree(node.right);

	}

	public static int hieght(TreeNodeRev node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(hieght(node.left), hieght(node.right));
	}

	public static boolean isBalanceRec(TreeNodeRev node) {
		return isBalancedRec(node) > 1;
	}

	public static int isBalancedRec(TreeNodeRev node) {
		if (node == null) {
			return 0;
		}
		int lh = isBalancedRec(node.left);
		int rh = isBalancedRec(node.right);
		if (lh == -1 || rh == -1 || Math.abs(lh - rh) > 1) {
			return -1;
		}
		return Math.max(lh, rh) + 1;
	}

	public static int childrenSum(TreeNodeRev node) {
		if (node == null) {
			return 0;
		}
		int sum = 0;
		if (node.left != null) {
			sum += node.left.data;
		}
		if (node.right != null) {
			sum += node.right.data;
		}
		if (node.data == sum) {
			return 1;
		}

		return ((childrenSum(node.left) == 1 && childrenSum(node.right) == 1)) ? 1 : 0;
	}

	public static int childrenSumItrative(TreeNodeRev node) {
		if (node == null) {
			return 0;
		}
		Queue<TreeNodeRev> queue = new LinkedList<TreeNodeRev>();
		queue.add(node);

		while (!queue.isEmpty()) {
			TreeNodeRev current = queue.poll();
			if (current.left == null || current.right == null) {
				continue;
			}
			int sum = 0;
			if (current.left != null) {
				sum += current.left.data;
			}
			if (current.right != null) {
				sum += current.right.data;
			}
			if (current.data != sum) {
				return 0;
			}
			if (current.left != null) {
				queue.add(current.left);
			}
			if (current.right != null) {
				queue.add(current.right);
			}

		}
		return 1;
	}

	public static TreeNodeRev arrToBst(int[] arr) {
		return arrToBstUtil(arr, 0, arr.length - 1);
	}

	private static TreeNodeRev arrToBstUtil(int[] arr, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = start + (end - start) / 2;
		TreeNodeRev root = new TreeNodeRev(arr[mid]);
		root.left = arrToBstUtil(arr, start, mid - 1);
		root.right = arrToBstUtil(arr, mid + 1, end);

		return root;
	}

	public static TreeNodeRev findSucc(TreeNodeRev node, int target) {
		if (node == null) {
			return null;
		}
		if (node.data == target && node.right != null) {
			return leftMost(node);
		}
		TreeNodeRev succ = null;
		TreeNodeRev current = node;
		while (current != null) {
			if (current.data > target) {
				succ = current;
				current = current.left;
			} else if (current.data <= target) {
				current = current.right;
			}
		}
		return succ;
	}

	public static TreeNodeRev leftMost(TreeNodeRev node) {
		TreeNodeRev current = node;
		while (current.left != null) {
			current = current.left;
		}
		return current;

	}

	public static TreeNodeRev balanceBST(TreeNodeRev node) {
		ArrayList<Integer> nodelist = new ArrayList<Integer>();
		storeInorder(node, nodelist);
		return buildBst(nodelist, 0, nodelist.size() - 1);
	}

	private static TreeNodeRev buildBst(ArrayList<Integer> nodelist, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = start + (end - start) / 2;
		TreeNodeRev root = new TreeNodeRev(nodelist.get(mid));

		root.left = buildBst(nodelist, start, mid - 1);
		root.right = buildBst(nodelist, mid + 1, end);

		return root;
	}

	private static void storeInorder(TreeNodeRev node, ArrayList<Integer> nodelist) {
		if (node == null) {
			return;
		}
		storeInorder(node.left, nodelist);
		nodelist.add(node.data);
		storeInorder(node.right, nodelist);

	}

	static int ans = 0;

	public static int diameter(TreeNodeRev node) {
		diameterUtil(node);
		return ans;
	}

	public static int diameterUtil(TreeNodeRev node) {
		if (node == null) {
			return 0;
		}
		int lh = diameterUtil(node.left);
		int rh = diameterUtil(node.right);
		ans = Math.max(ans, 1 + lh + rh);

		return 1 + Math.max(lh, rh);
	}

	public static boolean isSubtree(TreeNodeRev root1, TreeNodeRev root2) {
		if (root2 == null) {
			return true;
		}
		if (root1 == null) {
			return false;
		}
		if (areIdentical(root1, root2)) {
			return true;
		}
		return (isSubtree(root1.left, root2) || isSubtree(root1.right, root2));
	}

	private static boolean areIdentical(TreeNodeRev root1, TreeNodeRev root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null) {
			return false;
		}
		return (root1.data == root2.data && areIdentical(root1.left, root2.left)
				&& areIdentical(root1.right, root2.right));
	}

	public static boolean checkBst(TreeNodeRev node) {

		return checkBstUtil(node, Integer.MAX_VALUE, Integer.MIN_VALUE);
	}

	private static boolean checkBstUtil(TreeNodeRev node, int maxValue, int minValue) {
		if (node == null) {
			return true;
		}
		if (node.data > maxValue || node.data < minValue) {
			return false;
		}
		return checkBstUtil(node.left, node.data, minValue) && checkBstUtil(node.right, maxValue, node.data);
	}

	TreeNodeRev prev = null;

	public boolean isBSTInorder(TreeNodeRev node) {
		if (node == null) {
			return true;
		}
		if (!isBSTInorder(node.left)) {
			return false;
		}
		if (prev != null && node.data <= prev.data) {
			return false;
		}
		prev = node;
		return isBSTInorder(node.right);

	}

	static int count = 0;

	public static int countSingleValue(TreeNodeRev node) {
		findSingleValue(node);
		return count;
	}

	private static boolean findSingleValue(TreeNodeRev node) {
		if (node == null) {
			return true;
		}
		boolean left = findSingleValue(node.left);
		boolean right = findSingleValue(node.right);
		if (left == false || right == false) {
			return false;
		}
		if (node.left != null && node.data != node.left.data) {
			return false;
		}
		if (node.right != null && node.data != node.right.data) {
			return false;
		}

		count++;
		return true;

	}

	public static int height(TreeNodeRev node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(height(node.left), height(node.right));
	}

	public static List<Integer> zigZag(TreeNodeRev node) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int height = height(node);
		boolean leftToRight = true;
		for (int i = 1; i <= height; i++) {
			if (leftToRight) {
				leftToRight(node, i, list);
			} else {
				rightToLeft(node, i, list);
			}
			leftToRight = !leftToRight;
		}
		return list;
	}

	private static void rightToLeft(TreeNodeRev node, int level, ArrayList<Integer> list) {
		if (node == null) {
			return;
		}
		if (level == 1) {
			list.add(node.data);
			return;
		}
		rightToLeft(node.right, level - 1, list);
		rightToLeft(node.left, level - 1, list);

	}

	private static void leftToRight(TreeNodeRev node, int level, ArrayList<Integer> list) {
		if (node == null) {
			return;
		}
		if (level == 1) {
			list.add(node.data);
			return;
		}
		leftToRight(node.left, level - 1, list);
		leftToRight(node.right, level - 1, list);

	}

	public static List<Integer> vertticalTraver(TreeNodeRev node) {
		List<Integer> ans = new ArrayList<Integer>();
		Map<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		Queue<HdNodePair> queue = new LinkedList<HdNodePair>();
		queue.add(new HdNodePair(0, node));

		while (!queue.isEmpty()) {
			HdNodePair pair = queue.poll();
			int hd = pair.hd;
			TreeNodeRev curr = pair.node;

			if (map.containsKey(hd)) {
				map.get(hd).add(curr.data);
			} else {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(curr.data);
				map.put(hd, temp);
			}
			if (curr.left != null) {
				queue.add(new HdNodePair(hd - 1, curr.left));
			}
			if (curr.right != null) {
				queue.add(new HdNodePair(hd + 1, curr.right));
			}
		}
		for (Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
			ans.addAll(entry.getValue());
		}

		return ans;
	}

	public static List<Integer> zigzagItrative(TreeNodeRev node) {
		if (node == null) {
			return null;
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		Queue<TreeNodeRev> queue = new LinkedList<TreeNodeRev>();
		queue.add(node);
		boolean leftToRight = true;
		while (!queue.isEmpty()) {
			int size = queue.size();
			LinkedList<Integer> lList = new LinkedList<Integer>();

			for (int i = 0; i < size; i++) {
				TreeNodeRev curr = queue.poll();

				if (leftToRight) {
					lList.addLast(curr.data);
				} else {
					lList.addFirst(curr.data);
				}
				if (curr.left != null) {
					queue.add(curr.left);
				}
				if (curr.right != null) {
					queue.add(curr.right);
				}
			}
			list.addAll(lList);
			leftToRight = !leftToRight;
		}
		return list;
	}

	public static ArrayList<Integer> nodeKDistance(TreeNodeRev node, int targetValue, int k) {
		if (node == null) {
			return new ArrayList<Integer>();
		}
		TreeNodeRev target = findNode(node, targetValue);
		if (target == null)
			return new ArrayList<>(); // if target not found
		HashMap<TreeNodeRev, TreeNodeRev> map = new HashMap<TreeNodeRev, TreeNodeRev>();
		// markParent(node,null,map);
		markParentItrative(node, map);
		HashSet<TreeNodeRev> visited = new HashSet<TreeNodeRev>();
		Queue<TreeNodeRev> queue = new LinkedList<TreeNodeRev>();
		queue.add(target);
		visited.add(target);
		int distance = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			if (distance == k) {
				break;
			}

			for (int i = 0; i < size; i++) {
				TreeNodeRev current = queue.poll();
				for (TreeNodeRev neighbor : Arrays.asList(current.left, current.right, map.get(current))) {
					if (neighbor != null && !visited.contains(neighbor)) {
						visited.add(neighbor);
						queue.add(neighbor);
					}
				}
			}
			distance++;
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			list.add(queue.poll().data);
		}

		return list;

	}

	private static TreeNodeRev findNode(TreeNodeRev node, int targetValue) {
		if (node == null) {
			return null;
		}
		if (node.data == targetValue) {
			return node;
		}
		TreeNodeRev left = findNode(node.left, targetValue);
		if (left != null) {
			return left;
		}
		return findNode(node.right, targetValue);
	}

	private static void markParent(TreeNodeRev node, TreeNodeRev parent, HashMap<TreeNodeRev, TreeNodeRev> map) {
		if (node == null) {
			return;
		}
		map.put(node, parent);
		markParent(node.left, node, map);
		markParent(node.right, node, map);
	}

	private static void markParentItrative(TreeNodeRev node, HashMap<TreeNodeRev, TreeNodeRev> map) {
		if (node == null) {
			return;
		}
		Queue<TreeNodeRev> queue = new LinkedList<TreeNodeRev>();
		queue.add(node);
		map.put(node, null);

		while (!queue.isEmpty()) {
			TreeNodeRev current = queue.poll();
			if (current.left != null) {
				map.put(current.left, current);
				queue.add(current.left);
			}
			if (current.right != null) {
				map.put(current.right, current);
				queue.add(current.right);
			}
		}
	}

	public static ArrayList<Integer> bounryTraversal(TreeNodeRev node) {
		if (node == null) {
			return new ArrayList<Integer>();
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (!isLeef(node)) {
			list.add(node.data);
		}

		collectLeft(node.left, list);
		collectLeaves(node, list);
		collectRight(node.right, list);
		return list;
	}

	private static boolean isLeef(TreeNodeRev node) {
		return node.left == null && node.right == null;
	}

	private static void collectRight(TreeNodeRev node, ArrayList<Integer> list) {
		if (node == null || isLeef(node)) {
			return;
		}

		if (node.right != null) {
			collectRight(node.right, list);
		} else if (node.left != null) {
			collectRight(node.left, list);
		}
		list.add(node.data);

	}

	private static void collectLeaves(TreeNodeRev node, ArrayList<Integer> list) {
		if (node == null) {
			return;
		}
		if (isLeef(node)) {
			list.add(node.data);
			return;
		}
		collectLeaves(node.left, list);
		collectLeaves(node.right, list);

	}

	private static void collectLeft(TreeNodeRev node, ArrayList<Integer> list) {
		if (node == null || isLeef(node)) {
			return;
		}
		list.add(node.data);
		if (node.left != null) {
			collectLeft(node.left, list);
		} else if (node.right != null) {
			collectLeft(node.right, list);
		}
	}

	static int preIndex = 0;

	public static TreeNodeRev buildTree(int[] inOrder, int[] preOrder) {
		HashMap<Integer, Integer> inOrderIndex = new HashMap<Integer, Integer>();
		for (int i = 0; i < inOrder.length; i++) {
			inOrderIndex.put(inOrder[i], i);
		}
		preIndex = 0;
		return builtTreeUtil(preOrder, inOrderIndex, 0, inOrder.length - 1);

	}

	private static TreeNodeRev builtTreeUtil(int[] preOrder, HashMap<Integer, Integer> inOrderIndex, int start,
			int end) {
		if (start > end) {
			return null;
		}
		int rootVal = preOrder[preIndex++];
		TreeNodeRev rootNode = new TreeNodeRev(rootVal);
		int rootIndex = inOrderIndex.get(rootVal);
		rootNode.left = builtTreeUtil(preOrder, inOrderIndex, start, rootIndex - 1);
		rootNode.right = builtTreeUtil(preOrder, inOrderIndex, rootIndex + 1, end);

		return rootNode;
	}

	public static TreeNodeRev construct(List<Integer> pre) {
		TreeNodeRev rootNode = null;

		for (int key : pre) {
			rootNode = builBST(rootNode, key);
		}
		return rootNode;
	}

	private static TreeNodeRev builBST(TreeNodeRev rootNode, int key) {
		if (rootNode == null) {
			return new TreeNodeRev(key);
		} else if (rootNode.data > key) {
			rootNode.left = builBST(rootNode.left, key);
		} else if (rootNode.data < key) {
			rootNode.right = builBST(rootNode.right, key);
		}
		return rootNode;
	}

	static int idx = 0;

	public static TreeNodeRev construct1(int[] pre) {
		return constructBST(pre, Integer.MAX_VALUE);
	}

	public static TreeNodeRev constructBST(int[] pre, int bound) {
		if (idx == pre.length || pre[idx] > bound)
			return null;

		TreeNodeRev root = new TreeNodeRev(pre[idx++]);
		root.left = constructBST(pre, root.data);
		root.right = constructBST(pre, bound);
		return root;
	}

	public static int distBtwTwoNodes(TreeNodeRev node, int a, int b) {
		TreeNodeRev lc = lca(node, a, b);
		int d1 = findLevel(lc, a, 0);
		int d2 = findLevel(lc, b, 0);
		return d1 + d2;
	}

	public static int findLevel(TreeNodeRev node, int k, int level) {
		if (node == null) {
			return -1;
		}
		if (node.data == k) {
			return level;
		}
		int left = findLevel(node.left, k, level + 1);
		if (left == -1) {
			return findLevel(node.right, k, level + 1);
		}
		return left;
	}

	public static TreeNodeRev lca(TreeNodeRev node, int a, int b) {
		if (node == null) {
			return null;
		}
		if (node.data == a || node.data == b) {
			return node;
		}
		TreeNodeRev left = lca(node.left, a, b);
		TreeNodeRev right = lca(node.right, a, b);
		if (left == null) {
			return right;
		}
		if (right == null) {
			return left;
		}
		return node;
	}

	static int sum = 0;

	public static int findRangeSum(TreeNodeRev node, int low, int heigh) {
		rangeSum(node, low, heigh);
		return sum;
	}

	public static void rangeSum(TreeNodeRev node, int low, int heigh) {
		if (node == null) {
			return;
		}
		if (node.data >= low && node.data <= heigh) {
			sum += node.data;
		}
		rangeSum(node.left, low, heigh);
		rangeSum(node.right, low, heigh);
		

	}
	public static void rangeSumBST(TreeNodeRev node, int low, int high) {
	    if (node == null) return;

	    if (node.data >= low && node.data <= high)
	        sum += node.data;
	    
	    if (node.data > low) {
	    	rangeSumBST(node.left, low, high);
	    }
	        
	    
	    if (node.data < high) {
	    rangeSumBST(node.right, low, high);
	    }
	}
	
	public static int rangeSumIterative(TreeNodeRev node, int low, int high) {
	    if (node == null) {
	        return 0;
	    }

	    int rangeSum = 0;
	    Queue<TreeNodeRev> queue = new LinkedList<>();
	    queue.add(node);

	    while (!queue.isEmpty()) {
	        TreeNodeRev current = queue.poll();

	        if (current == null) continue;

	        // ✅ Add to sum if it's within the range
	        if (current.data >= low && current.data <= high) {
	            rangeSum += current.data;
	        }

	        // ✅ Only add left child if current > low
	        if (current.left != null && current.data > low) {
	            queue.add(current.left);
	        }

	        // ✅ Only add right child if current < high
	        if (current.right != null && current.data < high) {
	            queue.add(current.right);
	        }
	    }

	    return rangeSum;
	}



	public static void main(String[] args) {
		TreeNodeRev root = new TreeNodeRev(10);
		root.left = new TreeNodeRev(5);
		root.right = new TreeNodeRev(15);
		root.left.left = new TreeNodeRev(3);
		root.left.right = new TreeNodeRev(7);
		root.right.right = new TreeNodeRev(18);

		System.out.println(findRangeSum(root, 7, 15));
	}

}
