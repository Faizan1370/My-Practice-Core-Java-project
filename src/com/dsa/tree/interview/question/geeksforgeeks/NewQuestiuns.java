package com.dsa.tree.interview.question.geeksforgeeks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.dsa.pract.TNode;

public class NewQuestiuns {
	
	public ArrayList<Integer> mergeTwoBstSort(TreeNod root1, TreeNod root2) {

	    ArrayList<Integer> list = new ArrayList<>();

	    inorder(root1, list);
	    inorder(root2, list);

	    Collections.sort(list);

	    return list;
	}


	public ArrayList<Integer> mergeTwoBst(TreeNod root1, TreeNod root2) {

		ArrayList<Integer> list1 = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>();

		inorder(root1, list1);
		inorder(root2, list2);

		return mergeList(list1, list2);
	}

	private ArrayList<Integer> mergeList(ArrayList<Integer> list1, ArrayList<Integer> list2) {

		ArrayList<Integer> res = new ArrayList<>();

		int i = 0, j = 0;

		while (i < list1.size() && j < list2.size()) {

			if (list1.get(i) <= list2.get(j)) {
				res.add(list1.get(i++));
			} else {
				res.add(list2.get(j++));
			}
		}

		while (i < list1.size()) {
			res.add(list1.get(i++));
		}

		while (j < list2.size()) {
			res.add(list2.get(j++));
		}

		return res;
	}

	private void inorder(TreeNod root, ArrayList<Integer> list) {

		if (root == null) {
			return;
		}

		inorder(root.left, list);
		list.add(root.data);
		inorder(root.right, list);
	}

	public ArrayList<Integer> mergeTwoBst2(TreeNod root1, TreeNod root2) {

		ArrayList<Integer> res = new ArrayList<>();

		Stack<TreeNod> s1 = new Stack<>();
		Stack<TreeNod> s2 = new Stack<>();

		while (root1 != null || root2 != null || !s1.isEmpty() || !s2.isEmpty()) {

			while (root1 != null) {
				s1.push(root1);
				root1 = root1.left;
			}

			while (root2 != null) {
				s2.push(root2);
				root2 = root2.left;
			}

			if (s2.isEmpty() || (!s1.isEmpty() && s1.peek().data <= s2.peek().data)) {

				root1 = s1.pop();
				res.add(root1.data);
				root1 = root1.right;

			} else {

				root2 = s2.pop();
				res.add(root2.data);
				root2 = root2.right;
			}
		}

		return res;
	}
	public static int burnTreeBfs(TNode root,int target) {
		if(root==null) {
			return 0;
		}
		HashMap<TNode, TNode> parentMap = new HashMap<TNode, TNode>();
		TNode targetNode =buildParentMapBFS(root,target,parentMap);
		if(targetNode==null) {
			return 0;
		}
		HashSet<TNode> visited = new HashSet<TNode>();
		int burnTime=0;
		Queue<TNode> q = new LinkedList<TNode>();
		q.add(targetNode);
		visited.add(targetNode);
		
		while(!q.isEmpty()) {
			int size = q.size();
			boolean burnFlag=false;
			
			for(int i=0;i<size;i++) {
				TNode current = q.poll();
				if(current.left !=null && !visited.contains(current.left)) {
					if(current.left !=null && !visited.contains(current.left)) {
						visited.add(current.left);
						q.add(current.left);
						burnFlag=true;
					}
					if(current.right !=null && !visited.contains(current.right)) {
						visited.add(current.right);
						q.add(current.right);
						burnFlag=true;
					}
					TNode parentNode=parentMap.get(current);
					if(parentNode !=null && !visited.contains(parentNode)) {
						visited.add(parentNode);
						q.add(parentNode);
						burnFlag=true;
					}
				}
			}
			if(burnFlag) {
				burnTime++;
			}
		}
		return burnTime;
		
	}

	private static TNode buildParentMapBFS(TNode root, int target, HashMap<TNode, TNode> parentMap) {
		 Queue<TNode> queue = new LinkedList<TNode>();
		 queue.add(root);
		 TNode targetNode=null;
		 parentMap.put(root, null);
		 
		 while(!queue.isEmpty()) {
			 TNode current = queue.poll();
			 if(current.data==target) {
				 targetNode=current;
			 }
			 if(current.left !=null) {
				 parentMap.put(current.left, current);
				 queue.add(current.left);
			 }
			 if(current.right !=null) {
				 parentMap.put(current.right, current);
				 queue.add(current.right);
			 }
			 
		 }
		return targetNode;
	}
}
