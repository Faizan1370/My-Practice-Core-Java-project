package com.dsa.tree3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

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
	
	public ArrayList<Integer> topView(){
		if(root==null) {
			return null;
		}
		Map<Integer,Integer> map = new TreeMap<Integer, Integer>();
		Queue<HdNPair> queue = new LinkedList<HdNPair>();
		queue.add(new HdNPair(0, root));
		
		while(!queue.isEmpty()) {
			HdNPair pair = queue.poll();
			int hd=pair.hd;
			TreeNod current=pair.node;
			if(!map.containsKey(hd)) {
				map.put(hd, current.data);
			}
			if(current.left!=null) {
				queue.add(new HdNPair(hd-1, current.left));
			}
			if(current.right!=null) {
				queue.add(new HdNPair(hd+1, current.right));
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(Map.Entry<Integer, Integer> entry:map.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}
	TreeNod head=null;
	TreeNod prev;
	public void convertDll() {
		convertDll(root);
		printDll();
	}
	public void convertDll(TreeNod node) {
		if(node==null) {
			return;
		}
		convertDll(node.left);
		if(prev==null) {
			head=node;
		}else {
			node.left=prev;
			prev.right=node;
		}
		prev=node;
		convertDll(node.right);
	}
	public void printDll() {
		TreeNod current=head;
		while(current!=null) {
			System.out.print(current.data +" ");
			current=current.right;
		}
	}
	
	int diameter=0;
	public int diameter() {
		diameterUtil(root);
		return diameter;
	}
	
	private int diameterUtil(TreeNod node) {
		if(node==null) {
			return 0;
		}
		int lh=diameterUtil(node.left);
		int rh=diameterUtil(node.right);
		diameter = Math.max(diameter, 1+lh+rh);
		return 1+Math.max(lh, rh);
	}
	
	public int findLCA(int n1,int n2) {
		return lca(root, n1, n2).data;
	}
	
	public TreeNod lca(TreeNod node,int n1,int n2) {
		if(node==null) {
			return null;
		}
		if(node.data==n1 || node.data==n2) {
			return node;
		}
		TreeNod left=lca(node.left, n1, n2);
		TreeNod right=lca(node.right, n1, n2);
		if(left==null) {
			return right;
		}
		if(right==null) {
			return left;
		}
		return node;
	}
	
	public int burnTime(int target) {
		return minTime(root, target);
	}
	
	public int minTime(TreeNod node,int target) {
		DepthD depth= new DepthD(-1);
		return burn(node,target,depth);
	}

	

    int dep=0;
	private int burn(TreeNod node, int target, DepthD depth) {
		if(node==null) {
			return 0;
		}
		if(node.data==target) {
			depth.depth=0;
			return 1;
		}
		DepthD ld = new DepthD(-1);
		DepthD rd = new DepthD(-1);
		int lh = burn(node.left, target, ld);
		int rh = burn(node.right, target, rd);
		if(ld.depth!=-1) {
			dep = Math.max(dep, 1+rh+ld.depth);
			depth.depth=ld.depth+1;
		}else {
			dep = Math.max(dep, 1+lh+rd.depth);
			depth.depth=rd.depth+1;
		}

		return 1+Math.max(lh, rh);
	}

	public static void main(String[] args) {
		BinaryTreeImpl binaryTreeImpl = new BinaryTreeImpl();
		binaryTreeImpl.insert(50);
		binaryTreeImpl.insert(40);
		binaryTreeImpl.insert(60);
		binaryTreeImpl.insert(30);
		binaryTreeImpl.insert(45);
		binaryTreeImpl.insert(70);
		
		System.out.println(binaryTreeImpl.topView());
		System.out.println(binaryTreeImpl.diameter());
		System.out.println(binaryTreeImpl.findLCA(30, 70));
		System.out.println(binaryTreeImpl.burnTime(70));
		
	}

}
