package com.dsa.singlylinkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.dsa.tree.TreeNode;






public class SingleLinkedList {
	private ListNode head;
	private int length;
	
	public void add(int data) {
		ListNode newNoode = new ListNode(data);
		newNoode.next=head;
		head=newNoode;
	}
	
	public void print() {
		if(head ==null) {
			return;
		}
		ListNode current =head;
		//System.out.println(cuListNode.data + " "+cuListNode.next.data);
		while(current !=null) {
			System.out.print(current.data +" ");
			length++;
			current =current.next;
		}
	}
	public void length() {
		System.out.println(length);
	}
	
	public void insertFirtPosition(int data) {
		ListNode newNoode = new ListNode(data);
		newNoode.next=head;
		head=newNoode;
	}
	
	public void insertAtLastNode(int data) {
		ListNode newNode= new ListNode(data);
		if(head==null) {
			head=newNode;
		}
		ListNode current = head;
		while(current.next !=null) {
			current=current.next;
		}
		current.next=newNode;
	}
	
	public void removeFirst() {
		if(head==null || head.next==null) {
			head=null;
		}
		ListNode current=head;
		head=current.next;
		current.next=null;
	}
	
	public void removeLast() {
		if(head==null || head.next==null) {
			head=null;
		}
		ListNode current =head;
		while(current.next.next!=null) {
			current= current.next;
		}
		current.next=null;
	}
	
	public void reverseList() {
		if(head ==null || head.next==null) {
			return;
		}
		ListNode current=head;
		ListNode Previous=null;
		ListNode nextNode;
		while(current!=null) {
			nextNode= current.next;
			current.next=Previous;
			Previous=current;
			current=nextNode;
		}
		   head = Previous;
	}
	
	public void insertNodeGivenPosition(int value ,int position) {
		ListNode newNode= new ListNode(value);
		if(position==1) {
			head.next=newNode;
			head = newNode;
			return;
		}else {
			int count=1;
			ListNode previous=head;
			while(count<position-1) {
				previous=previous.next;
				count++;
			}
			ListNode current=previous.next;
			previous.next=newNode;
			newNode.next=current;
		}
	}
	public void deleteNodeGivenPosition(int position) {
		 if (head == null) {
		        throw new IllegalStateException("List is empty");
		    }
		if(position==1) {
			head=head.next;
			return;
		}
		int count=1;
		ListNode previous=head;
		while(count<position-1) {
			previous=previous.next;
			count++;
		}
		ListNode current=previous.next;
		previous.next=current.next;
		
		//previous.next=previous.next.next; //or tis way
	}
	public boolean searchElementByValue(int searchkey) {
		ListNode current=head;
		while(current!=null) {
			if(current.data==searchkey) {
				return true;
			}
			current =current.next;
		}
		return false;
	}
	public ListNode findMiddleNode() {
		if(head ==null) {
			return null;
		}
		ListNode fastPtr=head;
		ListNode slowPrt=head;
		while(fastPtr!=null && fastPtr.next!=null){
		     slowPrt=slowPrt.next;
		     fastPtr=fastPtr.next.next;
		}
		return slowPrt;
	}
	public void removeDuplicateElementInSortedList() {
		if(head==null) {
			return;
		}
		ListNode current=head;
		
		while(current!=null && current.next!=null) {
			if(current.data==current.next.data) {
				current.next=current.next.next;
			}else {
				current=current.next;
			}
		}
	}
	 public void addNewNodeSortedList(int data) {
		 ListNode newNode = new ListNode(data);
		 if(head==null || head.data>=data) {
			 newNode.next=head;
			 head=newNode;
		 }else {
			 ListNode current= head;
			 while(current.next!=null && current.next.data<newNode.data) {
				 current =current.next;
			 }
			 newNode.next=current.next;
			 current.next=newNode;
		 }
	 }
	 public void deleteNodeByValue(int value) {
		 if(head==null) {
			 return;
		 }
		 if(head.data==value) {
			 head=head.next;
			 return;
		 }
		 ListNode current = head;
		 while(current.next!=null && current.next.data!=value) {
			 current=current.next;
		 }
		 current.next= current.next.next;
	 }
	 public boolean containsLoopInLinkedList() {
		
		 ListNode slowPtr=head;
		 ListNode fastPtr=head;
		 while(fastPtr !=null && fastPtr.next!=null) {
			 slowPtr=slowPtr.next;
			 fastPtr=fastPtr.next.next;
			 if(fastPtr==slowPtr) {
				 return true;
			 }
		 }
		return false;
	 }
	 public ListNode findStartingLoopNode() {
		 ListNode fastPtr=head;
		 ListNode slowPtr=head;
		 while(fastPtr !=null && fastPtr.next!=null) {
			 slowPtr=slowPtr.next;
			 fastPtr=fastPtr.next.next;
			 if(fastPtr==slowPtr) {
				 getStartingNode(slowPtr);
			 }
		 }
		return null;
		 
	 }
	 
	 private ListNode getStartingNode(ListNode slowPtr) {
		 ListNode temp=head;
		 while(temp!=slowPtr) {
			 temp=temp.next;
			 slowPtr=slowPtr.next;
		 }
		return temp;
		 
	 }
	 
	 public void removeLoopLinkedList() {
		  ListNode fastPtr = head;
		  ListNode slowPtr=head;
		  
		  while(fastPtr!=null && fastPtr.next!=null) {
			  fastPtr = fastPtr.next.next;
			  slowPtr=slowPtr.next;
			  if(fastPtr == slowPtr) {
				  removeLoop(slowPtr);
			  }
		  }
		  
	  }
	 private void removeLoop(ListNode slowPtr) {
		 ListNode temp=head;
		 while(temp.next!=slowPtr.next) {
			 temp=temp.next;
			 slowPtr=slowPtr.next;
		 }
		 slowPtr.next=null;
	 }
	 
	 public void sortLinkedList() {
		 if(head==null || head.next==null) {
			 return;
		 }
		 ListNode current=head;
		 ListNode next=null;
		 int temp=0;
		 while(current!=null) {
			 next = current.next;
			 while(next!=null) {
				 if(current.data>next.data) {
					 temp=current.data;
					 current.data=next.data;
					 next.data=temp;
				 }
				 next=next.next;
			 }
			 current=current.next;
		 }
	 }
	 
	 public void removeDuplicate() {
		 if(head==null) {
			 return;
		 }
		 ListNode current=head;
		 
		 while(current !=null && current.next!=null) {
			 if(current.data ==current.next.data) {
				 current.next=current.next.next;
			 }else {
				 current=current.next;
			 }
		 }
	 }
	 
	 public  boolean palindromicList() {
		 if(head==null) {
			 return false;
		 }
		 ArrayList<Integer> list = new ArrayList<Integer>();
		 ListNode current= head;
		 while(current !=null) {
			 list.add(current.data);
			 current =current.next;
		 }
		 int start=0;
		 int end= list.size()-1;
		 while(start<end) {
			 if(list.get(start) != list.get(end)) {
				 return false;
			 }
			 start++;
			 end--;
		 }
		return true;
	 }
	 
	 public int middleElement() {
		 if(head==null) {
			 return 0;
		 }
		 ListNode slowPtr=head;
		 ListNode fastPtr=head;
		 while(fastPtr!=null && fastPtr.next!=null) {
			 slowPtr=slowPtr.next;
			 fastPtr=fastPtr.next.next;
		 }
		return slowPtr.data;
	 }
	 
	 public void removeDup() {
		 if(head==null) {
			 return;
		 }
		 ListNode current = head;
		 while(current !=null && current.next!=null) {
			 if(current.data==current.next.data) {
				 current.next=current.next.next;
			 }else {
				 current=current.next;
			 }
		 }
	 }
	 
	 public boolean linedListCycle() {
		 if(head==null) {
			 return false;
		 }
		 ListNode fast= head;
		 ListNode slow=head;
		 while(fast!=null && fast.next!=null) {
			 slow=slow.next;
			 fast=fast.next.next;
		 }
		 return slow==fast;
	 }
	 
	 public  void reverse() {
		 if(head==null) {
			 return;
		 }
		 ListNode currentt= head;
		 ListNode nextNode;
		 ListNode prev=null;
		 while(currentt!=null) {
			 nextNode = currentt.next;
			 currentt.next=prev;
			 prev=currentt;
			 currentt=nextNode;
			 
		 }
		 head=prev;
	 }
	 
	 public boolean palin() {
		 if(head==null) {
			 return false;
		 }
		 ArrayList<Integer> list = new ArrayList<Integer>();
		 ListNode current = head;
		 while(current!=null) {
			 list.add(current.data);
			 current=current.next;
		 }
		 for(int i=0;i<list.size();i++) {
			 if(list.get(i)!=list.get(list.size()-(i+1))) {
				 return false;
			 }
		 }
			/*
			 * int start=0, end=list.size()-1; while(start<end) {
			 * if(list.get(start)!=list.get(end)) { return false; } start++; end--; }
			 */
		 return true;
	 }
	 
	 public ListNode mergeTwoSortedList(ListNode list1,ListNode list2) {
		 ListNode dummy = new ListNode(-1);
		 ListNode current=dummy;
		 while(list1!=null && list2!=null) {
			 if(list1.data<=list2.data) {
				 current.next=list1;
				 list1=list1.next;
			 }else {
				 current.next=list2;
				 list2=list2.next;
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
	 
	 public void removeDup1() {
		 ListNode current=head;
		 while(current!=null && current.next!=null) {
			 if(current.data == current.next.data) {
				 current.next=current.next.next;
			 }else {
				 current =current.next;
			 }
		 }
	 }
	 
	 
	 public boolean checkCycle() {
		 if(head==null) {
			 return false;
		 }
		 ListNode slowPtr=head;
		 ListNode fastPointer=head;
		 while(fastPointer !=null && fastPointer.next!=null) {
			 slowPtr=slowPtr.next;
			 fastPointer=fastPointer.next.next;
			 if(slowPtr==fastPointer) {
				 return true;
			 }
		 }
		return false;
	 }
	 
	 public void reverse1() {
		 if(head==null) {
			 return;
		 }
		 ListNode current =head;
		 ListNode next;
		 ListNode prev=null;
		 while(current!=null) {
			 next=current.next;
			 current.next=prev;
			 prev=current;
			 current=next;
		 }
		 head=prev;
	 }
	 
	 
	 
	 public boolean checkPalin() {
		 if(head==null) {
			 return true;
		 }
		 ArrayList<Integer> list = new ArrayList<Integer>();
		 ListNode current=head;
		 while(current!=null) {
			 list.add(current.data);
			 current =current.next;
		 }
		 int start=0,end=list.size()-1;
		 while(start<end) {
			 if(list.get(start)!=list.get(end)) {
				 return false;
			 }
			 start++;
			 end--;
		 }
		return true;
	 }
	 
	 public int middleElement2() {
		if(head==null) {
			return 0;
		}
		ListNode slowP=head;
		ListNode fastP=head;
		while(fastP !=null && fastP.next !=null) {
			slowP=slowP.next;
			fastP=fastP.next.next;
		}
		return slowP.data;
	 }
	 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        ListNode dummy = new ListNode(-1);
	        ListNode current=dummy;
	        int carry =0;
	        while(l1 !=null || l2 !=null || carry!=0){
	            int sum=carry;
	            if(l1!=null){
	                sum +=l1.data;
	                l1=l1.next;
	            }
	             if(l2!=null){
	                sum +=l2.data;
	                l2=l2.next;
	            }
	            carry = sum/10;
	            current.next= new ListNode(sum%10);
	            current=current.next;
	        }
	        return dummy.next;
	    }
	
	public static void main(String[] args) {
		SingleLinkedList linkedList = new SingleLinkedList();
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(1);
		linkedList.add(1);
		linkedList.print();
		linkedList.removeDup1();
		System.out.println();
		linkedList.print();
		
		//linkedList.insertAtLastNode(5);
		//linkedList.insertNodeGivenPosition(50, 2);
		//linkedList.deleteNodeGivenPosition(2);
		//System.out.println(linkedList.searchElementByValue(8));
		//linkedList.removeLast();
		//linkedList.print();
		//linkedList.reverseList();
		//System.out.println(linkedList.findMiddleNode().data);
		//linkedList.removeDuplicateElementInSortedList();
		//linkedList.addNewNodeSortedList(2);
		//linkedList.deleteNodeByValue(7);
		//linkedList.sortLinkedList();
		//linkedList.print();
		//System.out.println(linkedList.palindromicList());
		
		//linkedList.length();
	}

}
