package com.dsa.tree3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeImpl {
	TreeNod root;
	
	public void insert(int data) {
		TreeNod newNode= new TreeNod(data);
		if(root==null) {
			root=newNode;
			return;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TreeNod current = queue.poll();
			
			if(current.left==null) {
				current.left=newNode;
				return;
			}else {
				queue.add(current.left);
			}
			if(current.right==null) {
				current.right=newNode;
				return;
			}else {
				queue.add(current.right);
			}
		}
	}
	
	public void preOder() {
		preOrder(root);
	}
	public void preOrder(TreeNod node) {
		if(node==null) {
			return;
		}
		TreeNod current=node;
		System.out.print(current.data +" ");
		preOrder(current.left);
		preOrder(current.right);
	}
	
	public void preOderItrative() {
		if(root==null) {
			return;
		}
		Stack<TreeNod> stack = new Stack<TreeNod>();
		stack.push(root);
		
		while(!stack.isEmpty()){
			TreeNod current = stack.pop();
			System.out.print(current.data +" ");
			if(current.right!=null) {
				stack.push(current.right);
			}
			if(current.left!=null) {
				stack.push(current.left);
			}
		}
	}
	
	public void inOder() {
		inOrderUtil(root);
	}
	
	private void inOrderUtil(TreeNod node) {
		if(node==null) {
			return;
		}
		inOrderUtil(node.left);
		System.out.print(node.data +" ");
		inOrderUtil(node.right);
		
	}
	
	public void inOrderIntrative() {
		if(root==null) {
			return;
		}
		Stack<TreeNod> stack = new Stack<TreeNod>();
		TreeNod current =root;
		
		while(!stack.isEmpty() || current!=null) {
			while(current!=null) {
				stack.push(current);
				current=current.left;
			}
			current =stack.pop();
			System.out.print(current.data +" ");
			current=current.right;
		}
	}
	
	public void postOrder() {
		postOrderUtil(root);
	}

	private void postOrderUtil(TreeNod node) {
		if(node==null) {
			return;
		}
		postOrderUtil(node.left);
		postOrderUtil(node.right);
		System.out.print(node.data +" ");
		
	}
	
	public void postOrderItrative() {
		if(root==null) {
			return;
		}
		Stack<TreeNod> stack1= new Stack<TreeNod>();
		Stack<TreeNod> stack2= new Stack<TreeNod>();
		stack1.push(root);
		while(!stack1.isEmpty()) {
			TreeNod current = stack1.pop();
			stack2.push(current);
			
			if(current.left!=null) {
				stack1.push(current.left);
			}
			if(current.right!=null) {
				stack1.push(current.right);
			}
		}
		while(!stack2.isEmpty()) {
			System.out.print(stack2.pop().data +" ");
		}
	}
	
	public void postOrderIterativeOneStack() {
	    if (root == null) return;

	    Stack<TreeNod> stack = new Stack<>();
	    TreeNod current = root;
	    TreeNod lastVisited = null;

	    while (!stack.isEmpty() || current != null) {
	        // push all left children
	        while (current != null) {
	            stack.push(current);
	            current = current.left;
	        }

	        TreeNod peekNode = stack.peek();

	        // if right child exists and is not processed yet, move to it
	        if (peekNode.right != null && lastVisited != peekNode.right) {
	            current = peekNode.right;
	        } else {
	            System.out.print(peekNode.data + " ");
	            lastVisited = stack.pop();
	        }
	    }
	}

	public void postOrderIterativeOneStack1() {
	    if (root == null) {
	        return;
	    }

	    Stack<TreeNod> stack = new Stack<>();
	    TreeNod current = root;
	    TreeNod lastVisited = null;

	    while (!stack.isEmpty() || current != null) {
	        if (current != null) {
	            stack.push(current);
	            current = current.left;
	        } else {
	            TreeNod peekNode = stack.peek();

	            // if right child exists and not processed yet
	            if (peekNode.right != null && lastVisited != peekNode.right) {
	                current = peekNode.right;
	            } else {
	                System.out.print(peekNode.data + " ");
	                lastVisited = stack.pop();
	            }
	        }
	    }
	}
	
	public ArrayList<Integer> printLeftView() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		leftViewUtil(root,list,0);
		return list;
	}

	private void leftViewUtil(TreeNod node, ArrayList<Integer> list,int level) {
		if(node==null) {
			return;
		}
		if(list.size()==level) {
			list.add(node.data);
		}
		leftViewUtil(node.left, list, level+1);
		leftViewUtil(node.right, list, level+1);
	}
	
	public void leftViewItrative() {
		if(root==null) {
			return;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);
		while(!queue.isEmpty()) {
			int levelSize = queue.size();
			for(int i=0;i<levelSize;i++) {
				TreeNod current = queue.poll();
				if(i==0) {
					System.out.print(current.data +" ");
				}
				if(current.left!=null) {
					queue.add(current.left);
				}
				if(current.right!=null) {
					queue.add(current.right);
				}
			}
			
		}
	}
	
	public ArrayList<Integer> printrightView() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		rightViewUtil(root,list,0);
		return list;
	}

	private void rightViewUtil(TreeNod node, ArrayList<Integer> list,int level) {
		if(node==null) {
			return;
		}
		if(list.size()==level) {
			list.add(node.data);
		}
		rightViewUtil(node.right, list, level+1);
		rightViewUtil(node.left, list, level+1);
		
	}
	
	public void rightViewItrative() {
		if(root==null) {
			return;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);
		while(!queue.isEmpty()) {
			int levelSize = queue.size();
			for(int i=0;i<levelSize;i++) {
				TreeNod current = queue.poll();
				if(i==levelSize-1) {
					System.out.print(current.data +" ");
				}
				if(current.left!=null) {
					queue.add(current.left);
				}
				if(current.right!=null) {
					queue.add(current.right);
				}
			}
			
		}
	}

	public static void main(String[] args) {
		BinaryTreeImpl binaryTreeImpl = new BinaryTreeImpl();
		binaryTreeImpl.insert(50);
		binaryTreeImpl.insert(40);
		binaryTreeImpl.insert(60);
		binaryTreeImpl.insert(30);
		binaryTreeImpl.insert(45);
		binaryTreeImpl.insert(70);
		
		System.out.println(binaryTreeImpl.printLeftView());
		System.out.println();
		binaryTreeImpl.leftViewItrative();
		System.out.println();
		System.out.println(binaryTreeImpl.printrightView());
		System.out.println();
		binaryTreeImpl.rightViewItrative();
	}

}
