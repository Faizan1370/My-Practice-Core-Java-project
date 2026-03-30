package com.dsa.tree.again.rev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.Stack;

import com.dsa.tree.TreeNode;
import com.dsa.tree.interview.question.geeksforgeeks.TreeNod;
import com.dsa.tree.interview.question.geeksforgeeks.revision.TreeNodNextRightNode;
import com.dsa.tree.revision.IncExcludePair;

public class TreeRev {

	public static int getMaxSum(TreeNode root) {
		IncExcludePair pair = getMaxSumUtil(root);
		return Math.max(pair.include, pair.exclude);
	}

	private static IncExcludePair getMaxSumUtil(TreeNode node) {
		if (node == null) {
			return new IncExcludePair(0, 0);
		}
		IncExcludePair left = getMaxSumUtil(node.left);
		IncExcludePair right = getMaxSumUtil(node.right);
		int include = left.include + right.include;
		int exclude = Math.max(left.include, left.exclude) + Math.max(right.include, right.exclude);

		return new IncExcludePair(include, exclude);
	}

	public static ArrayList<Integer> extremeNodes(TreeNode root) {
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
				TreeNode curr = queue.poll();

				if (leftRight && i == 0) {
					list.add(curr.data);
				} else if (!leftRight && i == size - 1) {
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
				TreeNodNextRightNode curr = queue.poll();
				if (prev != null) {
					prev.nextRight = curr;
				}
				prev = curr;
				if (curr.left != null) {
					queue.add(curr.left);
				}
				if (curr.right != null) {
					queue.add(curr.right);
				}
			}
			// also can check prev null
			prev.nextRight = null;
		}
	}

	public ArrayList<Integer> printAns(TreeNode root, int key) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		HashMap<Integer, Integer> parentMap = new HashMap<Integer, Integer>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		parentMap.put(root.data, null);

		while (!queue.isEmpty()) {

			TreeNode curr = queue.poll();
			if (curr.data == key) {
				break;
			}
			if (curr.left != null) {
				queue.add(curr.left);
				parentMap.put(curr.left.data, curr.data);
			}
			if (curr.right != null) {
				queue.add(curr.right);
				parentMap.put(curr.right.data, curr.data);
			}
		}
		Integer curr = key;
		while (parentMap.get(curr) != null) {
			curr = parentMap.get(curr);
			list.add(curr);
		}
		return list;
	}

	public ArrayList<Integer> printAncestors(TreeNode root, int key) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		dfsAns(root, key, list);
		return list;
	}

	private boolean dfsAns(TreeNode root, int key, ArrayList<Integer> list) {
		if (root == null) {
			return false;
		}
		if (root.data == key) {
			return true;
		}
		if (dfsAns(root.left, key, list) || dfsAns(root.right, key, list)) {
			list.add(root.data);
			return true;
		}
		return false;

	}

	public ArrayList<Integer> printAns2(TreeNode root, int target) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		HashMap<TreeNode, TreeNode> parentMap = new HashMap<TreeNode, TreeNode>();
		buildParnetMap(root, null, parentMap);
		TreeNode targetNode = findTargetNode(root, target);
		if (targetNode == null) {
			return list;
		}
		TreeNode current = parentMap.get(targetNode);
		while (current != null) {
			list.add(current.data);
			current = parentMap.get(current);
		}
		return list;
	}

	private TreeNode findTargetNode(TreeNode root, int target) {
		if (root == null) {
			return null;
		}
		if (root.data == target) {
			return root;
		}
		TreeNode left = findTargetNode(root.left, target);
		if (left != null) {
			return left;
		}
		return findTargetNode(root.right, target);
	}

	private void buildParnetMap(TreeNode root, TreeNode parent, HashMap<TreeNode, TreeNode> parentMap) {
		if (root == null) {
			return;
		}
		parentMap.put(root, parent);
		buildParnetMap(root.left, root, parentMap);
		buildParnetMap(root.right, root, parentMap);

	}

	public static TreeNode buildTree(int[] inorder, int[] preorder) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		int[] preIndex = { 0 };
		return buildTree(preorder, map, 0, inorder.length - 1, preIndex);

	}

	private static TreeNode buildTree(int[] preorder, HashMap<Integer, Integer> map, int start, int end,
			int[] preIndex) {
		if (start > end) {
			return null;
		}
		int rootValue = preorder[preIndex[0]++];
		int index = map.get(rootValue);
		TreeNode rootNode = new TreeNode(rootValue);
		rootNode.left = buildTree(preorder, map, start, index - 1, preIndex);
		rootNode.right = buildTree(preorder, map, index + 1, end, preIndex);

		return rootNode;

	}
}
