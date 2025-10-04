package com.dsa.interview.question;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
		if (root == null) {
			return 0;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);
		int depth = 0;
		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			for (int i = 0; i < levelSize; i++) {
				TreeNod current = queue.poll();
				if (current.left != null) {
					queue.add(current.left);
				}
				if (current.right != null) {
					queue.add(current.right);
				}
			}
			depth++;
		}
		return depth - 1;
	}

	public static boolean isDentical(TreeNod r1, TreeNod r2) {
		if (r1 == null && r2 == null) {
			return true;
		}
		if (r1 == null || r2 == null) {
			return false;
		}

		return r1.data == r2.data && isDentical(r1.left, r2.left) && isDentical(r1.right, r2.right);

	}

	public static boolean isIdenticalItrative(TreeNod r1, TreeNod r2) {
		if (r1 == null && r2 == null) {
			return true;
		}
		if (r1 == null || r2 == null) {
			return false;
		}

		Queue<TreeNod> queue1 = new LinkedList<TreeNod>();
		Queue<TreeNod> queue2 = new LinkedList<TreeNod>();
		queue1.add(r1);
		queue2.add(r2);

		while (!queue1.isEmpty() && !queue2.isEmpty()) {
			TreeNod node1 = queue1.poll();
			TreeNod node2 = queue2.poll();

			if (node1.data != node2.data) {
				return false;
			}
			if (node1.left != null && node2.left != null) {
				queue1.add(node1.left);
				queue2.add(node2.left);
			} else if (node1.left != null || node2.left != null) {
				return false;
			}

			if (node1.right != null && node2.right != null) {
				queue1.add(node1.right);
				queue2.add(node2.right);
			} else if (node1.right != null || node2.right != null) {
				return false;
			}
		}
		return queue1.isEmpty() && queue2.isEmpty();
	}
	
	public void mirror() {
		mirror(root);
	}

	public void mirror(TreeNod node) {
		if (node == null) {
			return;
		}
		mirror(node.left);
		mirror(node.right);
		TreeNod temp = node.left;
		node.left = node.right;
		node.right = temp;
	}

	public void mirrorItratve() {
		if (root == null) {
			return;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNod current = queue.poll();

			TreeNod temp = current.left;
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
	
	public void levelOrder() {
		if(root==null) {
			return;
			
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TreeNod current = queue.poll();
			System.out.print(current.data +" ");
			if(current.left!=null) {
				queue.add(current.left);
			}
			if(current.right!=null) {
				queue.add(current.right);
			}
		}
	}
	
	public boolean isMirror(TreeNod leftSub,TreeNod rightSub) {
		if(leftSub ==null && rightSub==null) {
			return true;
		}
		if(leftSub ==null || rightSub==null || leftSub.data != rightSub.data) {
			return false;
		}
		
		return isMirror(leftSub.left, rightSub.right) && isMirror(leftSub.right, rightSub.left);
	}
	
	public boolean isSymtric() {
		if(root==null) {
			return true;
		}
		return isMirror(root.left, root.right);
	}
	public boolean isSymtricItrative() {
		if(root==null) {
		return true;
		}
		Stack<TreeNod> s1 = new Stack<TreeNod>();
		Stack<TreeNod> s2 = new Stack<TreeNod>();
		
		s1.push(root.left);
		s2.push(root.right);
		
		while(!s1.isEmpty() && !s2.isEmpty()) {
			TreeNod node1 = s1.pop();
			TreeNod node2 = s2.pop();
			
			if(node1 ==null && node2==null) {
				continue;
			}
			if(node1 ==null || node2==null || node1.data !=node2.data) {
				return false;
			}
			s1.push(node1.left);
			s2.push(node2.right);
			
			s1.push(node1.right);
			s2.push(node2.left);
		}
		return s1.isEmpty() && s2.isEmpty();
	}
	
	public boolean isSymtricItrativeUsingQueue() {
		if(root==null) {
		return true;
		}
		
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root.left);
		queue.add(root.right);
		
		
		while(!queue.isEmpty()) {
			TreeNod node1 = queue.poll();
			TreeNod node2 = queue.poll();
			
			if(node1 ==null && node2==null) {
				continue;
			}
			if(node1 ==null || node2==null || node1.data !=node2.data) {
				return false;
			}
			queue.add(node1.left);
			queue.add(node2.right);
			queue.add(node1.right);
			queue.add(node2.left);
		
		}
		return true;
	}
	public boolean checkBananced() {
		return isBalanced(root);
	}
	public boolean isBalanced(TreeNod node) {
		if(node==null) {
			return true;
		}
		int lh=heightUtil(node.left);
		int rh = heightUtil(node.right);
		
		if(Math.abs(lh-rh)>1) {
			return false;
		}
		
		return isBalanced(node.left) && isBalanced(node.right);
		
	}
	
	public boolean isBalanceRec() {
		return isBalancedRec(root) > 0;
	}
	
	public int isBalancedRec(TreeNod node) {
		if(node==null) {
			return 0;
		}
		int lh = isBalancedRec(node.left);
		int rh = isBalancedRec(node.right);
		if(lh==-1 || rh==-1 || Math.abs(lh-rh)>1) {
			return -1;
		}
		
		return 1+Math.max(lh, rh);
		
	}
	
	public int childrenSum(TreeNod node) {
		if(node==null) {
			return 1;
		}
		int sum=0;
		if(node.left!=null) {
			sum += node.left.data;
		}
		if(node.right!=null) {
			sum +=node.right.data;
		}
		
		return ((node.data==sum) && (childrenSum(node.left)==1) && (childrenSum(node.right)==1))?1:0;
	}
	
	public int childrenSumItrative() {
		if(root==null) {
			return 1;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			int sum=0;
			TreeNod current = queue.poll();
			
			if(current.left ==null || current.right ==null) {
				continue;
			}
			if(current.left!=null) {
				sum +=current.left.data;
			}
			if(current.right!=null) {
				sum +=current.right.data;
			}
			if(sum!=current.data) {
				return 0;
			}
			if(current.left!=null) {
				queue.add(current.left);
			}
			if(current.right!=null) {
				queue.add(current.right);
			}
		}
		return 1;
		 
	}
	public TreeNod sortedArrtoBst(int[] arr) {
		return sortedArryBST(arr, 0, arr.length-1);
	}
	
	public TreeNod sortedArryBST(int[] arr,int start,int end) {
		if(start>end) {
			return null;
		}
		int mid=start+(end-start)/2;
		TreeNod root = new TreeNod(arr[mid]);
		
		root.left=sortedArryBST(arr, start, mid-1);
		
		root.right=sortedArryBST(arr, mid+1, end);
		return root;
	}


	public static void main(String[] args) {
		
		  BinaryBSTPrac binaryTreeImpl = new BinaryBSTPrac();
		  binaryTreeImpl.insert(50); binaryTreeImpl.insert(40);
		  binaryTreeImpl.insert(60); binaryTreeImpl.insert(30);
		  binaryTreeImpl.insert(45); binaryTreeImpl.insert(70);
		  binaryTreeImpl.levelOrder();
		  System.out.println();
		  binaryTreeImpl.mirrorItratve();
		  System.out.println();
		  binaryTreeImpl.levelOrder();
		 
		/*
		 * TreeNod r1 = new TreeNod(1); r1.left = new TreeNod(2); r1.right = new
		 * TreeNod(3); r1.left.left = new TreeNod(4);
		 * 
		 * // Representation of input binary tree 2 // 1 // / \ // 2 3 // / // 4 TreeNod
		 * r2 = new TreeNod(1); r2.left = new TreeNod(2); r2.right = new TreeNod(3);
		 * r2.left.left = new TreeNod(4);
		 * 
		 * if (isIdenticalItrative(r1, r2)) System.out.println("Yes"); else
		 * System.out.println("No");
		 */

	}

}
