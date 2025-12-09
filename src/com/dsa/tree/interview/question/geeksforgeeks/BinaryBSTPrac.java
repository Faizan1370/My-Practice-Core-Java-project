package com.dsa.tree.interview.question.geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

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
			return 0;
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
		if (root == null) {
			return;

		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNod current = queue.poll();
			System.out.print(current.data + " ");
			if (current.left != null) {
				queue.add(current.left);
			}
			if (current.right != null) {
				queue.add(current.right);
			}
		}
	}
	 public List<List<Integer>> levelOrder(TreeNod root) {
	        List<List<Integer>> result = new ArrayList<>();
	        dfs(root, 0, result);
	        return result;
	    }

	    private void dfs(TreeNod node, int level, List<List<Integer>> result) {
	        if (node == null) return;

	        // If this is the first time we reach this level, add a new list
	        if (result.size() == level) {
	            result.add(new ArrayList<>());
	        }

	        // Add current node value to its level list
	        result.get(level).add(node.data);

	        // Recurse left and right with next level
	        dfs(node.left, level + 1, result);
	        dfs(node.right, level + 1, result);
	    }

	public boolean isMirror(TreeNod leftSub, TreeNod rightSub) {
		if (leftSub == null && rightSub == null) {
			return true;
		}
		if (leftSub == null || rightSub == null || leftSub.data != rightSub.data) {
			return false;
		}

		return isMirror(leftSub.left, rightSub.right) && isMirror(leftSub.right, rightSub.left);
	}

	public boolean isSymtric() {
		if (root == null) {
			return true;
		}
		return isMirror(root.left, root.right);
	}

	public boolean isSymtricItrative() {
		if (root == null) {
			return true;
		}
		Stack<TreeNod> s1 = new Stack<TreeNod>();
		Stack<TreeNod> s2 = new Stack<TreeNod>();

		s1.push(root.left);
		s2.push(root.right);

		while (!s1.isEmpty() && !s2.isEmpty()) {
			TreeNod node1 = s1.pop();
			TreeNod node2 = s2.pop();

			if (node1 == null && node2 == null) {
				continue;
			}
			if (node1 == null || node2 == null || node1.data != node2.data) {
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
		if (root == null) {
			return true;
		}

		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root.left);
		queue.add(root.right);

		while (!queue.isEmpty()) {
			TreeNod node1 = queue.poll();
			TreeNod node2 = queue.poll();

			if (node1 == null && node2 == null) {
				continue;
			}
			if (node1 == null || node2 == null || node1.data != node2.data) {
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
		if (node == null) {
			return true;
		}
		int lh = heightUtil(node.left);
		int rh = heightUtil(node.right);

		if (Math.abs(lh - rh) > 1) {
			return false;
		}

		return isBalanced(node.left) && isBalanced(node.right);

	}

	public boolean isBalanceRec() {
		return isBalancedRec(root) > 0;
	}

	public int isBalancedRec(TreeNod node) {
		if (node == null) {
			return 0;
		}
		int lh = isBalancedRec(node.left);
		int rh = isBalancedRec(node.right);
		if (lh == -1 || rh == -1 || Math.abs(lh - rh) > 1) {
			return -1;
		}

		return 1 + Math.max(lh, rh);

	}

	public int childrenSum(TreeNod node) {
		if (node == null) {
			return 1;
		}
		int sum = 0;
		if (node.left != null) {
			sum += node.left.data;
		}
		if (node.right != null) {
			sum += node.right.data;
		}

		return ((node.data == sum) && (childrenSum(node.left) == 1) && (childrenSum(node.right) == 1)) ? 1 : 0;
	}

	public int childrenSumItrative() {
		if (root == null) {
			return 1;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int sum = 0;
			TreeNod current = queue.poll();

			if (current.left == null || current.right == null) {
				continue;
			}
			if (current.left != null) {
				sum += current.left.data;
			}
			if (current.right != null) {
				sum += current.right.data;
			}
			if (sum != current.data) {
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

	public void sortedArrtoBst(int[] arr) {
		root = sortedArryBST(arr, 0, arr.length - 1);
	}

	public TreeNod sortedArryBST(int[] arr, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = start + (end - start) / 2;
		TreeNod root = new TreeNod(arr[mid]);

		root.left = sortedArryBST(arr, start, mid - 1);

		root.right = sortedArryBST(arr, mid + 1, end);
		return root;
	}

	public TreeNod sortedArrayBSTItrative(int[] arr) {
		if (arr.length == 0) {
			return null;
		}
		int start = 0, end = arr.length - 1;
		int mid = start + (end - start) / 2;
		root = new TreeNod(arr[mid]);
		Queue<Data> queue = new LinkedList<Data>();
		queue.add(new Data(root, start, end));

		while (!queue.isEmpty()) {
			Data pair = queue.poll();
			TreeNod current = pair.node;
			int s = pair.start;
			int e = pair.end;
			int index = s + (e - s) / 2;
			if (s < index) {
				mid = s + (index - 1 - s) / 2;
				current.left = new TreeNod(arr[mid]);
				queue.add(new Data(current.left, s, index - 1));
			}
			if (e > index) {
				mid = index + 1 + (e - index - 1) / 2;
				current.right = new TreeNod(arr[mid]);
				queue.add(new Data(current.right, index + 1, e));
			}
		}
		return root;
	}

	public List<Integer> distK(int target, int k) {
		Map<TreeNod, TreeNod> parent_track = new HashMap<TreeNod, TreeNod>();
		markParents(root, parent_track);
		HashSet<TreeNod> visited = new HashSet<TreeNod>();
		Queue<TreeNod> queue = new LinkedList<TreeNod>();

        TreeNod targetNode = findNode(root, target);
        if (targetNode == null) return Collections.emptyList();
		queue.offer(targetNode);
		visited.add(targetNode);
		int dist = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			if (dist == k) {
				break;
			}
			dist++;
			for (int i = 0; i < size; i++) {
				TreeNod current = queue.poll();
				for (TreeNod next : Arrays.asList(current.left, current.right, parent_track.get(current))) {
					if (next != null && !visited.contains(next)) {
						visited.add(next);
						queue.add(next);
					}
				}
			}

		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			list.add(queue.poll().data);
		}

		return list;

	}
	

    // --- Step 2: Find reference of target node ---
    private TreeNod findNode(TreeNod node, int target) {
        if (node == null) return null;
        if (node.data == target) return node;
        TreeNod left = findNode(node.left, target);
        if (left != null) return left;
        return findNode(node.right, target);
    }

	private void markParents(TreeNod node, Map<TreeNod, TreeNod> parent_track) {
		if(node==null) {
			return;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.offer(node);
		while (!queue.isEmpty()) {
			TreeNod current = queue.poll();
			if (current.left != null) {
				parent_track.put(current.left, current);
				queue.offer(current.left);
			}
			if (current.right != null) {
				parent_track.put(current.right, current);
				queue.offer(current.right);
			}
		}
	}
	
	public TreeNod findSucc(int target) {
		if(root==null) {
			return null;
		}
		if(root.data == target && root.right!=null) {
			return leftMost(root);
		}
		TreeNod succ=null;
		TreeNod curr= root;
		while(curr!=null) {
			
			if(curr.data>target) {
				succ=curr;
				curr =curr.left;
			}else if(curr.data<=target) {
				curr =curr.right;
			}
		}
		return succ;
	}

	private TreeNod leftMost(TreeNod node) {
		TreeNod current =node;
		while(current.left!=null) {
			current =current.left;
		}
		return current;
	}
	
	public int kthLarget(int k) {
		if(root==null) {
			return -1;
		}
		TreeNod current = root;
		int count=0;
		
		while(current!=null) {
			
			if(current.right==null) {
				count++;
				if(count==k) {
					return current.data;
				}
				current=current.left;
			}else {
				TreeNod pred=current.right;
				while(pred.left !=null && pred !=current) {
					pred =pred.left;
				}
				if(pred.left==null) {
					pred.left=current;
					current =current.right;
				}else {
					count++;
					pred.left=null;
					if(count==k) {
						return current.data;
					}
					current = current.left;
				}
			}
		}
		return -1;
	}
	 public int kthLargest1(TreeNod root, int k) {
	        Stack<TreeNod> stack = new Stack<>();
	        TreeNod curr = root;

	        while (curr != null || !stack.isEmpty()) {
	            // Go to rightmost node first
	            while (curr != null) {
	                stack.push(curr);
	                curr = curr.right;
	            }

	            // Visit node
	            curr = stack.pop();
	            k--;
	            if (k == 0) return curr.data;

	            // Move to left subtree
	            curr = curr.left;
	        }

	        return -1; // should never happen
	    }
	 
	 public int kthSmallest(TreeNod root, int k) {
	        if(root==null){
	            return -1;
	        }
	        int[] count=new int[1];
	         int[] result = new int[1];
	        dfsSmallest(root,k,count,result);
	        return result[0];
	    }

	    public void dfsSmallest(TreeNod node,int k, int[] count,int[] result){
	        if(node==null){
	            return;
	        }
	        dfsSmallest(node.left,k,count,result);
	        count[0]++;
	        if(count[0]==k){
	            result[0]=node.data;
	            return;
	        }
	         dfsSmallest(node.right,k,count,result);
	    }
	
	public TreeNod balanceBST() {
		ArrayList<TreeNod> nodes = new ArrayList<TreeNod>();
		storeNode(root,nodes);
		
		return buildBst(nodes,0,nodes.size()-1);
	}

	private TreeNod buildBst(ArrayList<TreeNod> nodes, int start, int end) {
		if(start>end) {
			return null;
		}
		int mid = start+(end-start)/2;
		TreeNod root = nodes.get(mid);
		
		root.left= buildBst(nodes, start, mid-1);
		root.right = buildBst(nodes, mid+1,end);
		return root;
	}

	private void storeNode(TreeNod node, ArrayList<TreeNod> nodes) {
		if(node ==null) {
			return;
		}
		storeNode(node.left, nodes);
		nodes.add(node);
		storeNode(node.right, nodes);
	}
	int diameter=0;
	public int findDiameter() {
		diameter(root);
		return diameter;
	}
	public int diameter(TreeNod node) {
		if(node==null) {
			return 0;
		}
		int lh=diameter(node.left);
		int rh = diameter(node.right);
		diameter =Math.max(diameter, 1+lh+rh);
		
		return 1+Math.max(lh, rh);
	}
	
	public int rangeSum(int low,int heigh,TreeNod node) {
		if(node==null) {
			return 0;
		}
		if(node.data<low) {
			return rangeSum(low, heigh, node.right);
		}else  if(node.data>heigh){
			return rangeSum(low, heigh, node.left);
		}
		return node.data+rangeSum(low, heigh, node.left) +rangeSum(low, heigh, node.right);
	}
	int sum=0;
	
	public int findRangeSum(int low,int high) {
		rangeSum1(low, high, root);
		return sum;
	}
	public void rangeSum1(int low,int heigh,TreeNod node) {
		if(node== null) {
			return;
		}
		if(node.data >=low && node.data <=heigh) {
			sum +=node.data;
		}
		if(node.data>low) {
			rangeSum1(low, heigh, node.left);
		}
		if(node.data<heigh) {
			rangeSum1(low, heigh, node.right);
		}
	}
	
	public int rangeSumItra(int low,int high) {
		if(root==null) {
			return 0;
		}
		int sum=0;
		Stack<TreeNod> stack = new Stack<TreeNod>();
		stack.push(root);
		
		while(!stack.isEmpty()) {
			TreeNod curent = stack.pop();
			 if (curent == null) continue;  // ✅ important check
			if(curent.data >=low && curent.data <=high) {
				sum +=curent.data;
			}
			if(curent.data<high) {
				stack.push(curent.right);
			}

			if(curent.data>low) {
				stack.push(curent.left);
			}
		}
		return sum;
	}
	public boolean isSubtree(TreeNod root1,TreeNod root2) {
		if(root1==null) {
			return false;
		}
		if(root2==null) {
			return false;
		}
		if(areIdentical(root1,root2)) {
			return true;
		}
		return (isSubtree(root1.left, root2) || isSubtree(root.right, root2));
	}

	private boolean areIdentical(TreeNod root1, TreeNod root2) {
		if(root1==null && root2==null) {
			return true;
		}
		if(root1==null || root2==null) {
			return false;
		}
		return (root1.data == root2.data && areIdentical(root1.left, root2.left) && areIdentical(root1.right, root2.right));
	}
	
	private boolean areIdenticalItrative(TreeNod root1, TreeNod root2) {
		if(root1 ==null && root2==null) {
			return true;
		}
		if(root1 ==null || root2==null) {
			return false;
		}
		Queue<TreeNod> queue1= new LinkedList<TreeNod>();
		Queue<TreeNod> queue2= new LinkedList<TreeNod>();
		queue1.add(root1);
		queue2.add(root2);
		
		while(!queue1.isEmpty() && !queue2.isEmpty()) {
			TreeNod current1 = queue1.poll();
			TreeNod current2 = queue2.poll();
			if(current1 ==null && current2==null) {
				continue;
			}
			if(current1 ==null || current2==null) {
				return false;
			}
			 if (current1.data != current2.data) return false;
				queue1.add(current1.left);
				queue2.add(current2.left);
				queue1.add(current1.right);
				queue2.add(current2.right);
		}
		return queue1.isEmpty() && queue2.isEmpty();
	}
	
	public boolean checkBST() {
		return isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}

	private boolean isBST(TreeNod node, int minValue, int maxValue) {
		if(node ==null) {
			return true;
		}
		if(node.data<minValue || node.data>maxValue) {
			return false;
		}
		//return (isBST(node.left, minValue, node.data) && isBST(node.right, node.data, maxValue));
		return isBST(node.left, minValue, node.data - 1) && // avoid duplicate
				isBST(node.right, node.data + 1, maxValue);
	}
	TreeNod prev=null;
	public boolean isBSTInorder(TreeNod node) {
		if(node==null) {
			return true;
		}
		if(!isBSTInorder(node.left)) {
			return false;
		}
		if(prev!=null && node.data <=prev.data) {
			return false;
		}
		prev =node;
		
		return isBSTInorder(node.right);
	}
	
	public boolean leafSimilar(TreeNod root1,TreeNod root2) {
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		dfsSimilar(root1,list1);
		dfsSimilar(root2,list2);
		
		return list1.equals(list2);
	}

	private void dfsSimilar(TreeNod node, ArrayList<Integer> list) {
		if(node==null) {
			return;
		}
		if(node.left==null && node.right==null) {
			list.add(node.data);
		}
		dfsSimilar(node.left, list);
		dfsSimilar(node.right, list);
	}
	
	int count=0;
	
	public int findSingleValued() {
		singleValues(root);
		return count;
	}
	public boolean singleValues(TreeNod node) {
		if(node==null) {
			return true;
		}
		boolean left = singleValues(node.left);
		boolean right =singleValues(node.right);
		
		if(left==false || right ==false) {
			return false;
		}
		if(node.left!=null && node.data !=node.left.data) {
			return false;
		}
		if(node.right !=null && node.data != node.right.data) {
			return false;
		}
		count++;
		return true;
	}
	
	public int numOfTrees(int n) {
		int c=binomialCoeff(2*n,n);
		return c/(n+1);
	}

	private int binomialCoeff(int n, int k) {
		int res=1;
		if(k>n-k) {
			k=n-k;
		}
		for(int i=0;i<k;++i) {
			res *=(n-i);
			res /=(i+1);
		}
		return res;
	}
	
	public ArrayList<Integer> zigZagTraversal(TreeNod node){
		ArrayList<Integer> list = new ArrayList<Integer>();
		int height = heightUtil(node);
		boolean leftToRight=true;
		
		for(int i=1;i<=height;i++) {
			if(leftToRight) {
				leftToRight(node,list,i);
			}else {
				rightToLeft(node,list,i);
			}
			leftToRight =!leftToRight;
		}
		return list;
	}
	private void leftToRight(TreeNod node, ArrayList<Integer> list, int level) {
		if(node==null) {
			return;
		}
		if(level==1) {
			list.add(node.data);
			return;
		}
		leftToRight(node.left, list, level-1);
		leftToRight(node.right, list, level-1);
	}


	private void rightToLeft(TreeNod node, ArrayList<Integer> list, int level) {
		if(node==null) {
			return;
		}
		if(level==1) {
			list.add(node.data);
			return;
		}
		rightToLeft(node.right, list, level-1);
		rightToLeft(node.left, list, level-1);
		
		
	}
	
	public ArrayList<Integer> zigzagItrative(TreeNod node){
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(node==null) {
			return result;
		}
		Queue<TreeNod> queue = new LinkedList<TreeNod>();
		queue.add(node);
		boolean leftToRight=true;
		
		while(!queue.isEmpty()) {
			int size=queue.size();
			LinkedList<Integer> level = new LinkedList<Integer>();
			for(int i=0;i<size;i++) {
				TreeNod current = queue.poll();
				if(leftToRight) {
					level.addLast(current.data);
				}else {
					level.addFirst(current.data);
				}
				if(current.left!=null) {
					queue.add(current.left);
				}
				if(current.right!=null) {
					queue.add(current.right);
				}
			}
			result.addAll(level);
			leftToRight =!leftToRight;
		}
		return result;
	}
	
	public void topView(TreeNod node) {
		if(node==null) {
			return;
		}
		Queue<NodeHd> queue = new LinkedList<NodeHd>();
		Map<Integer,Integer> map = new TreeMap<Integer, Integer>();
		queue.add(new NodeHd(0, node));
		
		while(!queue.isEmpty()) {
			NodeHd pair = queue.poll();
			int hd=pair.hd;
			TreeNod current=pair.node;
			if(!map.containsKey(hd)) {
				map.put(hd, current.data);
			}
			if(current.left!=null) {
				queue.add(new NodeHd(hd-1, current.left));
			}
			if(current.right!=null) {
				queue.add(new NodeHd(hd+1, current.right));
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>(map.values());
		System.out.println(list);
	}
	
	public void verticalTraversal(TreeNod node) {
		if(node==null) {
			return;
		}
		Queue<NodeHd> queue = new LinkedList<NodeHd>();
		Map<Integer,ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		queue.add(new NodeHd(0, node));
		
		while(!queue.isEmpty()) {
			NodeHd pair = queue.poll();
			int hd=pair.hd;
			TreeNod current=pair.node;
			map.computeIfAbsent(hd, k -> new ArrayList<>()).add(current.data);
			if(current.left!=null) {
				queue.add(new NodeHd(hd-1, current.left));
			}
			if(current.right!=null) {
				queue.add(new NodeHd(hd+1, current.right));
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(ArrayList<Integer> data :map.values()) {
			list.addAll(data);
		}
		System.out.println(list);
	}
	
	public boolean isLeaf(TreeNod node) {
		return node.left==null && node.right==null;
	}
	
	public void collectLeft(TreeNod node,ArrayList<Integer> res) {
		if(node ==null || isLeaf(node)) {
			return;
		}
		res.add(node.data);
		if(node.left!=null) {
			collectLeft(node.left, res);
		}else if(node.right!=null) {
			collectLeft(node.right, res);
		}
	}
	public void collectLeaves(TreeNod node, ArrayList<Integer> res) {
		if(node==null) {
			return;
		}
		if(isLeaf(node)) {
			res.add(node.data);
		}
		collectLeaves(node.left, res);
		collectLeaves(node.right, res);
	}
	
	public void collectRight(TreeNod node ,ArrayList<Integer> res) {
		if(node==null || isLeaf(node)) {
			return;
		}
		
		if(node.right!=null) {
			collectRight(node.right, res);
		}else if(node.left !=null) {
			collectRight(node.left, res);
		}
		res.add(node.data);
		
	}
	 public ArrayList<Integer> boundaryTraversal(TreeNod node) {
		 ArrayList<Integer> res = new ArrayList<>();

	        if (node == null)
	            return res;
	        if(!isLeaf(node)) {
	        	res.add(node.data);
	        }
	        collectLeft(node.left, res);
	        
	        collectLeaves(node, res);
	        
	        collectRight(node.right, res);
	        
	        return res;
	 }
	 
	 public void boundaryTraversalIterative1(TreeNod node) {
		    ArrayList<Integer> res = new ArrayList<>();
		    if (node == null) return;

		    // 1️⃣ Root
		    if (!isLeaf(node)) res.add(node.data);

		    // 2️⃣ Left boundary (top-down, skip leaves)
		    TreeNod curr = node.left;
		    while (curr != null) {
		        if (!isLeaf(curr)) res.add(curr.data);
		        if (curr.left != null) curr = curr.left;
		        else curr = curr.right;
		    }

		    addLeavesIterative(node, res);

		    // 4️⃣ Right boundary (bottom-up, skip leaves)
		    Stack<TreeNod> stack = new Stack<>();
		    curr = node.right;
		    while (curr != null) {
		        if (!isLeaf(curr)) stack.push(curr);
		        if (curr.right != null) curr = curr.right;
		        else curr = curr.left;
		    }
		    while (!stack.isEmpty()) res.add(stack.pop().data);

		    System.out.println(res);
		}
	 
	// Iterative DFS to collect leaves (left → right)
	 private void addLeavesIterative(TreeNod root, ArrayList<Integer> res) {
	     if (root == null) return;
	     Stack<TreeNod> stack = new Stack<>();
	     stack.push(root);

	     while (!stack.isEmpty()) {
	         TreeNod node = stack.pop();
	         if (isLeaf(node)) {
	             res.add(node.data);
	         }
	         // Push right first so left is processed first
	         if (node.right != null) stack.push(node.right);
	         if (node.left != null) stack.push(node.left);
	     }
	 }



	 
	 
	 public void boundryTraversalItrative(TreeNod node) {
		 ArrayList<Integer> res = new ArrayList<>();
		 if(node==null) {
			 return;
		 }
		 if(!isLeaf(node)) {
			 res.add(node.data);
		 }
		 TreeNod current = node.left;
		 while(current!=null) {
			 if(!isLeaf(current)) {
				 res.add(current.data);
			 }
			 if(current.left!=null) {
				 current=current.left;
			 }else {
				 current = current.right;
			 }
		 }
		 Queue<TreeNod> queue = new LinkedList<TreeNod>();
		 queue.add(node);
		 while(!queue.isEmpty()) {
			 TreeNod curr = queue.poll();
			 if(isLeaf(curr)) {
				 res.add(curr.data);
			 }
			 if(curr.left!=null) {
				 queue.add(curr.left);
			 }
			 
			 if(curr.right!=null) {
				 queue.add(curr.right);
			 }
		 }
		 
		 Stack<TreeNod> stack = new Stack<TreeNod>();
		  TreeNod curr1=node.right;
		  while(curr1!=null) {
			  if(!isLeaf(curr1)) {
				  stack.push(curr1);
			  }
			  if(curr1.right!=null) {
				  curr1 =curr1.right;
			  }else {
				  curr1 =curr1.left;
			  }
		  }
		  while(!stack.isEmpty()) {
			  res.add(stack.pop().data);
		  }
		  System.out.println(res);
	 }
	  int preIndex = 0;
	 public TreeNod buildTree(int[] inOrder,int[] preOrder) {
		 Map<Integer,Integer> inOrderIndex= new HashMap<Integer, Integer>();
		 for(int i=0;i<inOrder.length;i++) {
			 inOrderIndex.put(inOrder[i],i);
		 }
		 preIndex=0;
		root= buildTreeUtil(preOrder,inOrderIndex,0,inOrder.length-1);
		 return root;
	 }

	
	private TreeNod buildTreeUtil(int[] preOrder, Map<Integer, Integer> inOrderIndex, int start, int end) {
		if(start>end) {
			return null;
		}
		int rootVal=preOrder[preIndex++];
		TreeNod rootNode=new TreeNod(rootVal);
		int inOrderPosition=inOrderIndex.get(rootVal);
		rootNode.left = buildTreeUtil(preOrder, inOrderIndex, start,inOrderPosition-1);
		rootNode.right = buildTreeUtil(preOrder, inOrderIndex, inOrderPosition+1, end);
		return rootNode;
	}
	
	public TreeNod construct(List<Integer> pre) {
		
		for(int i=0;i<pre.size();i++) {
			 root=insertBST(root,pre.get(i));
		}
		return root;
	}

	private TreeNod insertBST(TreeNod node, int i) {
		if(node==null) {
			return new TreeNod(i);
		}
		if(node.data>i) {
			node.left=insertBST(node.left, i);
		}else if(node.data<i) {
			node.right=insertBST(node.right, i);
		}
		return node;
	}
	int index=0;
	public TreeNod constructBST(List<Integer> pre) {
		index=0;
		root= builBSTT(pre,Integer.MIN_VALUE,Integer.MAX_VALUE);
		return root;
	}
	private TreeNod builBSTT(List<Integer> pre, int minValue, int maxValue) {
		if(index>=pre.size()) {
			return null;
		}
		int val = pre.get(index);
		if(val<minValue || val>maxValue) {
			return null;
		}
		TreeNod node = new TreeNod(val);
		index++;
		node.left = builBSTT(pre, minValue, val);
		node.right = builBSTT(pre, val, maxValue);

		return node;
	}
	
	public TreeNod LCA(TreeNod node,int n1,int n2) {
		if(node==null) {
			return null;
		}
		if(node.data == n1 || node.data==n2) {
			return node;
		}
		TreeNod left = LCA(node.left, n1, n2);
		TreeNod right= LCA(node.right,n1,n2);
		if(left==null) {
			return right;
		}
		if(right==null) {
			return left;
		}
		
		return node;
	}
	
	public int findLevel(TreeNod node,int k,int level) {
		if(node==null) {
			return -1;
		}
		if(node.data==k) {
			return level;
		}
		int left =findLevel(node.left, k, level+1);
		 if (left == -1) {
	            return findLevel(node.right, k, level + 1);
	        }
		 
		 return left;

	}
	
	public int findMinDistance(TreeNod node,int a,int b) {
		TreeNod lcaNode = LCA(node, a, b);
		int d1 = findLevel(lcaNode, a, 0);
		int d2 = findLevel(lcaNode, b, 0);
		
		return d1+d2;
	}

	public void inOrd() {
		inOrder(root);
	}
	
	public void inOrder(TreeNod node) {
		if(node==null) {
			return;
		}
		inOrder(node.left);
		System.out.print(node.data +" ");
		inOrder(node.right);
	}
   int maxSum=Integer.MIN_VALUE;
   public int maxPathSum(TreeNod node) {
	  maxSum = Integer.MIN_VALUE;  // reset for each call
	  maxSum(node, 0);
	  return maxSum;
   }
	public void maxSum(TreeNod node,int currSum) {
		if(node==null) {
			return;
		}
		currSum +=node.data;
		if(node.left ==null && node.right==null) {
			maxSum= Math.max(currSum, maxSum);
		}
		maxSum(node.left,currSum);
		maxSum(node.right,currSum);
	}
	
	public void findMaxSum(TreeNod node, int currSum, int[] mxSum) {
        if (node == null)
            return;

        // Add the current node's data to the path sum
        currSum += node.data;

        // Check if this node is a leaf node
        if (node.left == null && node.right == null) {

            // Update the maximum sum if a higher sum is found
            if (currSum > mxSum[0]) {
                mxSum[0] = currSum;
            }
        }

        // Recursively check for the maximum sum 
      	// in the left and right subtrees
        findMaxSum(node.left, currSum, mxSum);
        findMaxSum(node.right, currSum, mxSum);
    }

    // Function to return the maximum sum path from root to leaf
    public int maxPathSum1(TreeNod node) {

        // Empty tree has sum 0
        if (node == null)
            return 0;

        // Initialize max sum as the smallest possible integer
        int[] mxSum = { Integer.MIN_VALUE };

        // Find the target leaf and maximum sum
        findMaxSum(node, 0, mxSum);

        // Return the maximum sum found
        return mxSum[0];
    }
    
    public int oddEvenLevelSumDiff(TreeNod node) {
    	int[] oddSum= {0};
    	int[] evenSum= {0};
    	oddEvenSum(node, 0, oddSum, evenSum);
    	return oddSum[0]-evenSum[0];
    }
    
    public void oddEvenSum(TreeNod node,int level,int[] oddSum,int[] evenSum) {
    	if(node==null) {
    		return;
    	}
    	if(level %2!=0) {
    		oddSum[0] +=node.data;
    	}else {
    		evenSum[0] +=node.data;
    	}
    	oddEvenSum(node.left, level+1, oddSum, evenSum);
    	oddEvenSum(node.right, level+1, oddSum, evenSum);
    	
    }
    
    
    public int oddEvenLevelSumDiff1(TreeNod node) {
        return oddEvenSum(node, 0);
    }

    private int oddEvenSum(TreeNod node, int level) {
        if(node == null) return 0;

        int sum = (level % 2 != 0 ? node.data : -node.data);
        sum += oddEvenSum(node.left, level + 1);
        sum += oddEvenSum(node.right, level + 1);

        return sum;
    }

    
    
    public int oddEvenLevelDiff(TreeNod node) {
    	if(node==null) {
    		return 0;
    	}
    	Queue<TreeNod> queue = new LinkedList<TreeNod>();
    	queue.add(node);
    	int level=0;
    	int evenSum=0,oddSum=0;
    	while(!queue.isEmpty()) {
    		int size=queue.size();
    		
    		for(int i=0;i<size;i++) {
    			TreeNod current = queue.poll();
    			if(level %2!=0) {
    				oddSum +=current.data;
    			}else {
    				evenSum +=current.data;
    			}
    			if(current.left!=null) {
    				queue.add(current.left);
    			}
    			if(current.right!=null) {
    				queue.add(current.right);
    			}
    		}
    		level++;
    	}
    	return oddSum-evenSum;
    }

	
	public static void main(String[] args) {
		/*
		 * BinaryBSTPrac binaryTreeImpl = new BinaryBSTPrac(); List<Integer> pre =
		 * Arrays.asList(10, 5, 1, 7, 40, 50); binaryTreeImpl.constructBST(pre);
		 * binaryTreeImpl.inOrd();
		 */
		
		
		
		  BinaryBSTPrac binaryTreeImpl = new BinaryBSTPrac(); TreeNod root = new
		  TreeNod(20); root.left = new TreeNod(8); root.right = new TreeNod(22);
		  root.right.right = new TreeNod(11); root.left.left = new TreeNod(4);
		  root.left.right = new TreeNod(12); root.left.right.left = new TreeNod(10);
		  root.left.right.right = new TreeNod(14);
		  System.out.println(binaryTreeImpl.oddEvenLevelSumDiff1(root));
		//  binaryTreeImpl.boundaryTraversalIterative1(root);
		 
		/*
		 * ArrayList<Integer> res = binaryTreeImpl.zigzagItrative(root); for (int v :
		 * res) { System.out.print(v + " "); }
		 */
        
    }

		/*
		 * BinaryBSTPrac binaryTreeImpl = new BinaryBSTPrac();
		 * binaryTreeImpl.insert(50); binaryTreeImpl.insert(40);
		 * binaryTreeImpl.insert(60); binaryTreeImpl.insert(30);
		 * binaryTreeImpl.insert(45); binaryTreeImpl.insert(70);
		 * binaryTreeImpl.levelOrder(); System.out.println();
		 * binaryTreeImpl.mirrorItratve(); System.out.println();
		 * binaryTreeImpl.levelOrder();
		 */

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
