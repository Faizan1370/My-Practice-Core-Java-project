package com.dsa.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedDeque;

public class BinarySearchTreeImpl {
	
	 private TreeNode root;
	 
	public void insert(int data) {
		root=insert(root, data);
		
	}
	
	private TreeNode insert(TreeNode root,int data) {
		if(root==null) {
			 return new TreeNode(data);
		}
		if(data<root.data) {
			root.left=insert(root.left, data);
		}else {
			root.right=insert(root.right, data);
		}
		return root;
	}
	
	public void preOrder() {
		preOrder(root);
	}
	
	private void preOrder(TreeNode root) {
		if(root==null) {
			return;
		}
		System.out.print(root.data +" ");
		preOrder(root.left);
		preOrder(root.right);
	}
	
	public void postOrder() {
		postOrder(root);
	}
	
	private void postOrder(TreeNode root) {
		if(root==null) {
			return;
		}
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.data +" ");
	}
	
	public void inOrder() {
		inOrder(root);
	}
	
	private void inOrder(TreeNode root) {
		if(root==null) {
			return;
		}
		inOrder(root.left);
		System.out.print(root.data +" ");
		inOrder(root.right);
	}
	
	public TreeNode search(int data) {
		 TreeNode current=root;
		 
		 while(current!=null) {
			 if(current.data==data) {
				 return current;
			 }else if(current.data>data){
				 current=current.left;
			 }else {
				 current=current.right;
			 }
		 }
		return null;
	}
	
	public int size() {
		 return size(root);
	}
	
	private int size(TreeNode root) {
		if(root==null) {
			return 0;
		}
		return 1+size(root.left)+size(root.right);
	}
	
	public int height() {
		return height(root);
	}
	
	private int height(TreeNode root) {
		if(root==null) {
			return 0;
		}
		return 1+Math.max(height(root.left), height(root.right));
	}
	
	public int findMin() {
		TreeNode current=root;
		while(current.left !=null) {
			current =current.left;
		}
		return current.data;
	}
	
	public int findMax() {
		TreeNode current=root;
		while(current.right !=null) {
			current =current.right;
		}
		return current.data;
	}
	
	public int countLeaves() {
		return countLeave(root);
	}
	
	private int countLeave(TreeNode root) {
		if(root==null) {
			return 0;
		}
		if(root.left==null && root.right==null) {
			return 1;
		}
		return countLeave(root.left)+countLeave(root.right);
	}
	
	public boolean isValidBST() {
		return isValidBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
		
	}
	private boolean isValidBst(TreeNode root,int min,int max) {
		if(root==null) {
			return true;
		}
		if(root.data <=min || root.data>=max) {
			return false;
		}
		return isValidBst(root.left, min, root.data) && isValidBst(root.right, root.data, max);
	}
	public void levelOrder() {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			TreeNode current = queue.poll();
			System.out.print(current.data +" ");
			
			if(current.left!=null) {
				queue.add(current.left);
			}
			if(current.right!=null) {
				queue.add(current.right);
			}
		}
		
	}
	 public void insertIterative(int value) {
		 TreeNode newNode= new TreeNode(value);
		 if(root==null) {
			 root=newNode;
			 return;
		 }
		 Queue<TreeNode> queue= new ConcurrentLinkedDeque<TreeNode>();
		 queue.add(root);
		 
		 while(!queue.isEmpty()) {
			  TreeNode currentNOde = queue.poll();
			  if(currentNOde.left==null) {
				  currentNOde.left=newNode;
				  return;
			  }else {
				  queue.add(currentNOde.left);
			  }
			  
			  if(currentNOde.right==null) {
				  currentNOde.right=newNode;
				  return;
			  }else {
				  queue.add(currentNOde.right);
			  }
			  
		 }
		 
	 }
	 
	 public void insertIterative1(int value) {
		 TreeNode newNode= new TreeNode(value);
		 if(root==null) {
			 root=newNode;
			 return;
		 }
		 Stack<TreeNode> stack= new Stack<TreeNode>();
		 stack.add(root);
		 
		 while(!stack.isEmpty()) {
			  TreeNode currentNode = stack.pop();
			  if (currentNode.left == null) {
		            currentNode.left = newNode;
		            return;
		        } else {
		            stack.push(currentNode.left); // Push left to explore later
		        }

		        if (currentNode.right == null) {
		            currentNode.right = newNode;
		            return;
		        } else {
		            stack.push(currentNode.right); // Push right to explore later
		        }
			 
		 }
		 
	 }
	 
	 public void insertIntoBST(int value) {
		    TreeNode newNode = new TreeNode(value);
		    if (root == null) {
		        root = newNode;
		        return;
		    }

		    TreeNode current = root;
		    TreeNode parent = null;

		    while (current != null) {
		        parent = current;
		        if (value < current.data) {
		            current = current.left;
		        } else {
		            current = current.right;
		        }
		    }

		    if (value < parent.data) {
		        parent.left = newNode;
		    } else {
		        parent.right = newNode;
		    }
		}
	 public void iterativePreorderTranversal() {
		 if(root==null) {
			 System.out.println("No node in Tree");
			 return;
		 }
		 Stack<TreeNode> stack = new Stack<TreeNode>();
		 stack.push(root);
		 
		 while(!stack.isEmpty()){
			 TreeNode currentNode = stack.pop();
			 System.out.print(currentNode.data +" ");
			 
			 if(currentNode.right!=null) {
				 stack.push(currentNode.right); 
			 }
			
			 if(currentNode.left!=null) {
				 stack.push(currentNode.left); 
			 }
		 }
	 }
	 public void itreativeInOrderTraversal() {
		 if(root==null) {
			 System.out.println("no data or node");
			 return;
		 }
		 Stack<TreeNode> stack = new Stack<TreeNode>();
		 TreeNode temp=root;
		 while(!stack.isEmpty() || temp!=null) {
			 if(temp!=null) {
				 stack.push(temp);
				 temp=temp.left;
			 }else {
				 temp=stack.pop();
				 System.out.print(temp.data +" ");
				 temp=temp.right;
			 }
		 }
	 }
	 
	 public TreeNode deleteNode1(int key) {
		return deleteNode(root, key);
	 }
	 
	 public TreeNode deleteNode(TreeNode root,int key) {
		 if(root==null) {
			 return root;
		 }
		 if(key<root.data) {
			 root.left=deleteNode(root.left, key);
		 }else if(key>root.data) {
			 root.right=deleteNode(root.right, key);
		 }else {
			 if(root.left==null) {
				 return root.right;
			 }else if(root.right==null) {
				 return root.left;
			 }
			 root.data= findMin(root.right);
			 root.right=deleteNode(root.right, root.data);
		 }
		 return root;
	 }
	 
	 private int findMin(TreeNode root) {
		 while(root.left!=null) {
			 root=root.left;
		 }
		 return root.data;
	 }
	 
	 public int floor(int key) {
		 int ans=Integer.MAX_VALUE;
		 if(root==null) {
			 return ans;
		 }
		 while(root!=null) {
			 if(root.data==key) {
				 return root.data;
			 }
			 if(root.data>key) {
				 root=root.left;
			 }else {
				 ans=root.data;
				 root=root.right;
			 }
		 }
		return ans;
	 }
	 
	 public int ceil(int key) {
		 int ans=Integer.MAX_VALUE;
		 if(root==null) {
			 return ans;
		 }
		 while(root!=null) {
			 if(root.data==key) {
				 return root.data;
			 }
			 if(root.data>key) {
				 ans=root.data;
				 root=root.left;
			 }else {
				 root=root.right;
			 }
		 }
		return ans;
	 }
	 
	 public int twoSum(int target) {
		 if(root==null) {
			 return 0;
		 }
		 HashSet<Integer> set = new HashSet<Integer>();
		 boolean ans=twoSumUtil(root, target, set);
		 
		 return ans?1:0;
	 }
	 
	 public boolean twoSumUtil(TreeNode node,int sum,Set<Integer> set) {
		 if(node==null) {
			 return false;
		 }
		 if(twoSumUtil(node.left, sum, set)==true) {
			 return true;
		 }
		 if(set.contains(sum-node.data)) {
			 return true;
		 }
		 set.add(node.data);
		 
		 return twoSumUtil(node.right, sum, set);
	 }
	 
	 public ArrayList<Integer> verticalOrder(){
		 Queue<QueueObj> queue=new ArrayDeque<QueueObj>();
		 HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		 queue.add(new QueueObj(root, 0));
		 while(!queue.isEmpty()) {
			 QueueObj current = queue.poll();
			 if(map.containsKey(current.hd)) {
				 map.get(current.hd).add(current.node.data);
			 }else {
				 ArrayList<Integer> temp = new ArrayList<Integer>();
				 temp.add(current.node.data);
				 map.put(current.hd, temp);
			 }
			 if(current.node.left!=null) {
				 queue.add(new QueueObj(current.node.left,current.hd-1));
			 }
			 
			 if(current.node.right!=null) {
				 queue.add(new QueueObj(current.node.right,current.hd+1));
			 }
			 
		 }
		 ArrayList<Integer> ans = new ArrayList<Integer>();
		 for(Map.Entry<Integer, ArrayList<Integer>> entry: map.entrySet()) {
			 ans.addAll(entry.getValue());
		 }
		 return ans;
	 }

	public static void main(String[] args) {
		BinarySearchTreeImpl binaryTreeImpl = new BinarySearchTreeImpl();
		binaryTreeImpl.insert(2);
		binaryTreeImpl.insert(1);
		binaryTreeImpl.insert(7);
		binaryTreeImpl.insert(3);
		binaryTreeImpl.insert(10);
		binaryTreeImpl.insert(20);
		//System.out.println(binaryTreeImpl.twoSum(5)); 
		System.out.println(binaryTreeImpl.verticalOrder());
		
		
		//binaryTreeImpl.inOrder();
		//System.out.println();
		//binaryTreeImpl.deleteNode1(1);
		//System.out.println();
		//binaryTreeImpl.inOrder();
		//binaryTreeImpl.itreativeInOrderTraversal();
		//System.out.println( binaryTreeImpl.size());
		//System.out.println( binaryTreeImpl.height());
	//	System.out.println( binaryTreeImpl.findMin());
	//	System.out.println( binaryTreeImpl.findMax());
	//	System.out.println( binaryTreeImpl.countLeaves());
		//System.out.println(binaryTreeImpl.isValidBST());
		//System.out.println();
		//binaryTreeImpl.insertIterative1(26);
		//binaryTreeImpl.insertIterative1(78);
		//binaryTreeImpl.levelOrder();
		//System.out.println(binaryTreeImpl.search(3).data);
	}

}
