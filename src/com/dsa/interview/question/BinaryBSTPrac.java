package com.dsa.interview.question;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryBSTPrac {
	TreeNod root;

	public void insert(int data) {
		TreeNod newNode = new TreeNod(data);
		if (root == null) {
			root = newNode;
			return;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNod current = queue.poll();

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

	public int height() {
		return heightUtil(root);
	}

	private int heightUtil(TreeNod node) {
		if (node == null) {
			return -1;
		}
		return Math.max(heightUtil(node.left), heightUtil(node.right)) + 1;
	}
	
	public int heightItrative() {
		if(root==null) {
			return 0;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);
		int depth=0;
		while(!queue.isEmpty()) {
			int levelSize=queue.size();
			for(int i=0;i<levelSize;i++) {
				TreeNod current = queue.poll();
				if(current.left!=null) {
					queue.add(current.left);
				}
				if(current.right!=null) {
					queue.add(current.right);
				}
			}
			depth++;
		}
		return depth-1;
	}
	
	public static boolean isDentical(TreeNod r1,TreeNod r2) {
		if(r1 ==null && r2==null) {
			return true;
		}
		if(r1 ==null || r2==null) {
			return false;
		}
		
		return r1.data==r2.data &&
			  isDentical(r1.left, r2.left)
			  && isDentical(r1.right, r2.right);
		
	}

	public static void main(String[] args) {
		/*
		 * BinaryBSTPrac binaryTreeImpl = new BinaryBSTPrac();
		 * binaryTreeImpl.insert(50); binaryTreeImpl.insert(40);
		 * binaryTreeImpl.insert(60); binaryTreeImpl.insert(30);
		 * binaryTreeImpl.insert(45); binaryTreeImpl.insert(70);
		 * System.out.println(binaryTreeImpl.height());
		 * System.out.println(binaryTreeImpl.heightItrative());
		 */
		 TreeNod r1 = new TreeNod(1);
	        r1.left = new TreeNod(2);
	        r1.right = new TreeNod(3);
	        r1.left.left = new TreeNod(4);

	        // Representation of input binary tree 2
	        //        1
	        //       / \
	        //      2   3
	        //     /
	        //    4
	        TreeNod r2 = new TreeNod(1);
	        r2.left = new TreeNod(2);
	        r2.right = new TreeNod(3);
	        r2.left.left = new TreeNod(4);

	        if (isDentical(r1, r2))
	            System.out.println("Yes");
	        else
	            System.out.println("No");

	}

}
