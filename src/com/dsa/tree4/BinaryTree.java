package com.dsa.tree4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class BinaryTree {
	TrN root;

	public void insert(int data) {
		TrN newNode = new TrN(data);
		if (root == null) {
			root = newNode;
			return;
		}
		Queue<TrN> queue = new LinkedList<TrN>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TrN current = queue.poll();
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

	public void inOrder() {
		inOrderUtil(root);
	}

	private void inOrderUtil(TrN node) {
		if (node == null) {
			return;
		}
		inOrderUtil(node.left);
		System.out.print(node.data + " ");
		inOrderUtil(node.right);

	}

	public void inOrderItrative() {
		if (root == null) {
			return;
		}
		Stack<TrN> stack = new Stack<TrN>();
		TrN current = root;

		while (!stack.isEmpty() || current != null) {
			while (current != null) {
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();
			System.out.print(current.data + " ");
			current = current.right;
		}
	}

	public void preOrder() {
		preOrderUtil(root);
	}

	private void preOrderUtil(TrN node) {
		if (node == null) {
			return;
		}
		System.out.print(node.data + " ");
		preOrderUtil(node.left);
		preOrderUtil(node.right);

	}

	public void preOrderItrative() {
		if (root == null) {
			return;
		}
		Stack<TrN> stack = new Stack<TrN>();
		stack.add(root);

		while (!stack.isEmpty()) {
			TrN current = stack.pop();
			System.out.print(current.data + " ");
			if (current.right != null) {
				stack.push(current.right);
			}
			if (current.left != null) {
				stack.push(current.left);
			}
		}
	}

	public void postOrder() {
		postOrderUtil(root);
	}

	private void postOrderUtil(TrN node) {
		if (node == null) {
			return;
		}
		postOrderUtil(node.left);
		postOrderUtil(node.right);
		System.out.print(node.data + " ");

	}

	public void postOrderItrative() {
		if (root == null) {
			return;
		}
		Stack<TrN> stack1 = new Stack<TrN>();
		Stack<TrN> stack2 = new Stack<TrN>();
		stack1.push(root);
		while (!stack1.isEmpty()) {
			TrN current = stack1.pop();
			stack2.push(current);
			if (current.left != null) {
				stack1.push(current.left);
			}

			if (current.right != null) {
				stack1.push(current.right);
			}

		}
		while (!stack2.isEmpty()) {
			System.out.print(stack2.pop().data + " ");
		}
	}

	public List<Integer> leftView() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		leftViewUtil(root, list, 0);
		return list;
	}

	private void leftViewUtil(TrN node, ArrayList<Integer> list, int level) {
		if (node == null) {
			return;
		}
		if (list.size() == level) {
			list.add(node.data);
		}
		leftViewUtil(node.left, list, level + 1);
		leftViewUtil(node.right, list, level + 1);

	}

	public void leftViewItrative() {
		if (root == null) {
			return;
		}
		Queue<TrN> queue = new LinkedList<TrN>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TrN current = queue.poll();
				if (i == 0) {
					System.out.print(current.data + " ");
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

	public List<Integer> rightView() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		rightViewUtil(root, list, 0);
		return list;
	}

	private void rightViewUtil(TrN node, ArrayList<Integer> list, int level) {
		if (node == null) {
			return;
		}
		if (list.size() == level) {
			list.add(node.data);
		}
		rightViewUtil(node.right, list, level + 1);
		rightViewUtil(node.left, list, level + 1);

	}

	public void rightViewItrative() {
		if (root == null) {
			return;
		}
		Queue<TrN> queue = new LinkedList<TrN>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TrN current = queue.poll();
				if (i == size - 1) {
					System.out.print(current.data + " ");
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

	public int diameter() {
		if (root == null) {
			return 0;
		}
		int[] ans = new int[1];
		ans[0] = 0;
		diameterUtil(root, ans);
		return ans[0];
	}

	private int diameterUtil(TrN node, int[] ans) {
		if (node == null) {
			return 0;
		}
		int lh = diameterUtil(node.left, ans);
		int rh = diameterUtil(node.right, ans);
		ans[0] = Math.max(ans[0], lh + rh);

		return 1 + Math.max(lh, rh);

	}

	public int LCA(int n1, int n2) {
		if (root == null) {
			return -1;
		}
		return LCAUtil(root, n1, n2).data;
	}

	private TrN LCAUtil(TrN node, int n1, int n2) {
		if (node == null) {
			return null;
		}
		if (node.data == n1 || node.data == n2) {
			return node;
		}

	    // Search in left and right subtrees
	    TrN left = LCAUtil(node.left, n1, n2);
	    TrN right = LCAUtil(node.right, n1, n2);
	    if(left!=null && right !=null) {
	    	return node;
	    }
	    return left != null ? left : right;
	}
	
	public  List<Integer> printTopView(){
	   List<Integer> list = new ArrayList<Integer>();
	   if(root==null) {
		   return list;
	   }
	   Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
	   Queue<TrNHD> quque = new LinkedList<>();
	   quque.add(new TrNHD(0, root));
	   while(!quque.isEmpty()) {
		   TrNHD pair = quque.poll();
		   int hd= pair.hd;
		   TrN current =pair.node;
		   if(!map.containsKey(hd)) {
			  map.put(hd, current.data);
		   }
		   if(current.left!=null) {
			   quque.add(new TrNHD(hd-1, current.left));
		   }
		   if(current.right!=null) {
			   quque.add(new TrNHD(hd+1, current.right));
		   }
		
	   }
	   for(int val:map.values()) {
			  list.add(val);
		  }
	return list;
	}
	public  List<Integer> printBottomView(){
		   List<Integer> list = new ArrayList<Integer>();
		   if(root==null) {
			   return list;
		   }
		   Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		   Queue<TrNHD> quque = new LinkedList<>();
		   quque.add(new TrNHD(0, root));
		   while(!quque.isEmpty()) {
			   TrNHD pair = quque.poll();
			   int hd= pair.hd;
			   TrN current =pair.node;
				  map.put(hd, current.data);
			   if(current.left!=null) {
				   quque.add(new TrNHD(hd-1, current.left));
			   }
			   if(current.right!=null) {
				   quque.add(new TrNHD(hd+1, current.right));
			   }
			
		   }
		   for(int val:map.values()) {
				  list.add(val);
			  }
		return list;
		}
	TrN head =null;
	TrN prev =null;
	public void converDLL() {
		if(root==null) {
			return;
		}
		convertDllUtil(root);
		printDll();
	}

	private void convertDllUtil(TrN node) {
		if(node==null) {
			return;
		}
		convertDllUtil(node.left);
		if(prev==null) {
			head= node;
		}else {
			node.left=prev;
			prev.right=node;
		}
		prev=node;
		convertDllUtil(node.right);
	}
	public void printDll() {
		if(head==null) {
			return;
		}
		while(head!=null) {
			System.out.print(head.data +" ");
			head=head.right;
		}
	}

	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree();
		binaryTree.insert(5);
		binaryTree.insert(7);
		binaryTree.insert(3);
		binaryTree.insert(4);
		binaryTree.insert(2);
		binaryTree.insert(9);
		binaryTree.converDLL();

	}

}
