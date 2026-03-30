package com.dsa.pract;

import com.dsa.doublyLinkedList.revision.ListNodeRev;

public class Pract1 {
	DLNode head;
	DLNode tail;
	int size;

	public void addFirst(int data) {
		DLNode newNode = new DLNode(data);
		if (tail == null) {
			head = tail = newNode;
		} else {
			newNode.next = head;
			head.previous = newNode;
			head = newNode;
		}
		size++;
	}

	public void insertLast(int data) {
		DLNode newNode = new DLNode(data);
		if (head == null) {
			head = tail = newNode;
		} else {
			newNode.previous = tail;
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}

	public void deleteFirst() {
		if (head == null) {
			return;
		}
		if (head == tail) { // only one node
			head = tail = null;
		} else {
			head = head.next;
			head.previous = null;
		}
		size--;

	}

	public void deleteLast() {
		if (head == null) {
			return;
		}
		if (head == tail) {
			head = tail = null;
		} else {
			tail = tail.previous;
			tail.next = null;
		}

		size--;
	}

	public void insertAtGivenPosition(int data, int position) {
		DLNode newNode = new DLNode(data);
		if (head == null) {
			head = tail = newNode;
			size++;
			return;
		}
		if (position == 1) {
			newNode.next = head;
			head.previous = newNode;
			head = newNode;
			size++;
			return;
		}
		int count = 1;
		DLNode current = head;
		while (count < position - 1) {
			current = current.next;
			count++;
		}
		if (current.next == null) {
			current.next = newNode;
			newNode.previous = current;
			tail = newNode;
			size++;
			return;
		}
		DLNode nxt = current.next;
		current.next = newNode;
		newNode.previous = current;
		newNode.next = nxt;
		nxt.previous = newNode;

		size++;
	}

	public void deleteAtPosition(int position) {
		if (head == null) {
			return;
		}
		if (position == 1) {
			if (head.next == null) { // only one node
				head = null;
				tail = null;
			} else {
				head = head.next;
				head.previous = null;
			}
			size--;
			return;
		}
		int count=1;
		DLNode current=head;
		while(current !=null && count <position-1) {
			current =current.next;
			count++;
		}
		if(current==null) {
			return;
		}
		if(current.next==null) {
			return;
		}
		if(current.next.next==null) {
			current.next=null;
			tail=current;
			size--;
			return;
		}
		DLNode nxt = current.next.next;
		current.next=nxt;
		nxt.previous=current;
		size--;
		
	}
	public void deleteByValue(int key) {
		if(head==null) {
			return;
		}
		if(head.data ==key) {
			DLNode temp=head;
			head=head.next;
			if(head !=null) {
				head.previous=null;
			}else {
				tail=null;
			}
			temp.next=null;
			size--;
			return;
		}
		
		if(tail != null && tail.data ==key) {
			DLNode temp=tail;
			tail=tail.previous;
			if(tail !=null) {
				tail.next=null;
			}else {
				head=null;
			}
			temp.previous=null;
			size--;
			return;
		}
		DLNode current =head;
		while(current !=null && current.data !=key) {
			current=current.next;
		}
		if(current==null) {
			return;
		}
		  // Case 2: delete tail
	    if (current == tail) {
	        tail = tail.previous;
	        tail.next = null;
	        size--;
	        return;
	    }
		DLNode prev=current.previous;
		prev.next=current.next;
		current.next.previous=prev;
		current=null; //no need just optional
		size--;
	}
	public int findNodePositionByValue(int data) {
		int postion=1;
		DLNode current =head;
		while(current !=null) {
			if(current.data==data) {
				return postion;
			}
			current=current.next;
			postion++;
		}
		return -1;
	}

}
