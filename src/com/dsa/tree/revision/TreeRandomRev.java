package com.dsa.tree.revision;

import java.util.ArrayList;

import com.dsa.singlylinkedlist.revision.LisNodeRe;

public class TreeRandomRev {
	
	public static int findRangeSum(TreeNodeRev node, int low, int high) {
		int[] sum= new int[1];
		rangeSumUtil(node,low,high,sum);
		return sum[0];
	}

	private static void rangeSumUtil(TreeNodeRev node, int low, int high, int[] sum) {
		if(node==null) {
			return;
		}
		if(node.data>=low && node.data<=high) {
			sum[0] +=node.data;
		}
		rangeSumUtil(node.left, low, high, sum);
		rangeSumUtil(node.right, low, high, sum);
		
	}
	public LisNodeRe mergerTwoSortedList(LisNodeRe list1,LisNodeRe list2) {
		LisNodeRe dummy = new LisNodeRe(-1);
		LisNodeRe current=dummy;
		if (list1 == null) return list2;
	    if (list2 == null) return list1;
	    while(list1!=null && list2 !=null) {
			if(list1.data>=list2.data) {
				current.next=list2;
				list2 = list2.next;
				
			}else {
				current.next=list1;
				list1 = list1.next;
			}
			current=current.next;
		}
		if(list1!=null) {
			current.next=list1;
		}else {
			current.next=list2;
		}
		
		return dummy.next;
	}
	public static boolean leafSimilar(TreeNodeRev node1,TreeNodeRev node2) {
		ArrayList<Integer> list1= new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		collectLeaves(node1,list1);
		collectLeaves(node2,list2);
		return list1.equals(list2);
	}
	private static void collectLeaves(TreeNodeRev node, ArrayList<Integer> list) {
		if(node==null) {
			return;
		}
		if(isLeaf(node)) {
			list.add(node.data);
			return;
		}
		collectLeaves(node.left, list);
		collectLeaves(node.right, list);
		
	}

	private static boolean isLeaf(TreeNodeRev node) {
		return node.left==null && node.right==null;
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
