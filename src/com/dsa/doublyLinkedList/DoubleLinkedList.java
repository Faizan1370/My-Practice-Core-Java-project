package com.dsa.doublyLinkedList;


public class DoubleLinkedList {
	
	private ListNode head;
	
	public void addElementAtStart(int data) {
		ListNode newNode= new ListNode(data);
		newNode.next=head;
		newNode.previous=null;
		if(head !=null) {
			head.previous=newNode;
		}
		head=newNode;
	}
	
	public void print() {
		if(head==null) {
			return;
		}
		ListNode current=head;
		while(current !=null) {
			System.out.print(current.data +" ");
			current =current.next;
					
		}
	}
	public void insertNodeLast(int data) {
		ListNode newNode= new ListNode(data);
		ListNode current=head;
		//newNode.next=null;
		if(head==null) {
			//newNode.previous=null;
			head=newNode;
			return;
		}
		while(current.next!=null) {
			current=current.next;
		}
		newNode.previous=current;
		current.next=newNode;
	}
	public int length() {
		int count=0;
		ListNode current=head;
		while(current !=null) {
			count++;
			current=current.next;
			
		}
		return count;
	}
	public void deleteFirstNode() {
		if(head==null || head.next==null) {
			head=null;
			return;
		}else {
			ListNode current=head;
			head=head.next;
			current.next=null;
			head.previous=null;
		}
	}
	public void deleteLastNode() {
		if(head == null || head.next==null) {
			head=null;
		}else {
			ListNode current=head;
			
			while(current.next !=null) {
				current =current.next;
			}
			ListNode temp=current.previous;
			temp.next=null;
			current.previous=null;
			
		}
	}
	
	public void insertAtPosition(int position,int data) {
		ListNode newNode= new ListNode(data);
		if(head==null) {
			return;
		}
		if(head==null ||position==1) {
			newNode.next=head;
			newNode.previous=null;
			head=newNode;
		}else {
			int count=0;
			ListNode current=head;
			while(count<position-1) {
				current=current.next;
				count++;
			}
			ListNode prve=current.previous;
			prve.next=newNode;
			newNode.previous=prve;
			newNode.next=current;
			current.previous=newNode;
			
					
			
		}
	}
	public void deleteNodePosition(int position) {
		if(head==null) {
			return;
		}
		if(position==1) {
			ListNode current=head;
			head=head.next;
			current.next=null;
			head.previous=null;
		}else {
			int count=0;
			ListNode current=head;
			while(count<position-1) {
				current = current.next;
				count++;
			}
			ListNode prev=current.previous;
			prev.next=current.next;
			current.next.previous=prev;
			current.next=null;
			current.previous=null;
		}
	}
	
	public void deleteByValue(int value) {
		if(head==null) {
			return;
			
		}
		ListNode current=head;
		if(current.data==value) {
			head=current.next;
			current.next=null;
			head.previous=null;
		}
		while(current !=null && current.data !=value) {
			current=current.next;
		}
		ListNode prev=current.previous;
		prev.next=current.next;
		current.next.previous=prev;
		current.next=null;
		current.previous=null;
	}
	public void findNodePositionByValue(int data) {
		if(head==null) {
			return;
		}
		int count=1;
		ListNode current=head;
		while(current !=null && current.data !=data) {
			current =current.next;
			count++;
		}
		if(current !=null && current.data==data) {
			System.out.println(count);
		}
	}
	
	public static void main(String[] args) {
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
		doubleLinkedList.addElementAtStart(2);
		doubleLinkedList.addElementAtStart(6);
		doubleLinkedList.addElementAtStart(5);
		doubleLinkedList.addElementAtStart(3);
		doubleLinkedList.insertNodeLast(8);
		doubleLinkedList.print();
		System.out.println();
		doubleLinkedList.findNodePositionByValue(8);
		
		//doubleLinkedList.deleteByValue(6);
		//doubleLinkedList.insertAtPosition(3,15);
		//doubleLinkedList.deleteLastNode();
		//doubleLinkedList.deleteFirstNode();
		//doubleLinkedList.print();
		
	}

}
