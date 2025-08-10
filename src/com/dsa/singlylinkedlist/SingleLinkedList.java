package com.dsa.singlylinkedlist;

import java.util.ArrayList;
import java.util.Currency;

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
	public static void main(String[] args) {
		SingleLinkedList linkedList = new SingleLinkedList();
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(4);
		linkedList.add(6);
		linkedList.add(7);
		System.out.println(linkedList.middleElement());
		
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
