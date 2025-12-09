package com.dsa.queue.revision;

import com.dsa.queue.QueueNode;

public class QueueImplRevision {
	QueueNodeRev head;
	QueueNodeRev tail;

	public void offer(int data) {
		QueueNodeRev newNode = new QueueNodeRev(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
			return;
		}
		QueueNodeRev current = head;
		while (current.next != null) {
			current = current.next;
		}
		current.next = newNode;
		tail = newNode;
	}

	public void insertNode1(int data) {
		QueueNodeRev newNode = new QueueNodeRev(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
	}

	public void remove() {
		if (head == null) {
			return;
		}
		if (head.next == null) {
			head = null;
			return;
		}
		// Case 1: Only one node
		if (head == tail) {
			head = tail = null; // both become null
			return;
		}
		head = head.next;
	}

	public void print() {
		if (head == null) {
			return;
		}
		QueueNodeRev current = head;
		while (current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}

	}

	public int size() {
		if (head == null) {
			return 0;
		}
		QueueNodeRev current = head;
		int count = 0;
		while (current != null) {
			current = current.next;
			count++;
		}
		return count;
	}
	public int peek() {
		if(head==null) {
			return -1;
		}
		
		return head.data;
	}

	public static void main(String[] args) {
		QueueImplRevision implRevision = new QueueImplRevision();
		implRevision.insertNode1(4);
		implRevision.insertNode1(6);
		implRevision.insertNode1(8);
		implRevision.insertNode1(12);
		implRevision.insertNode1(11);
		implRevision.print();
		System.out.println(implRevision.size());
		implRevision.remove();
		implRevision.print();
		System.out.println(implRevision.size());
		System.out.println(implRevision.peek());

	}

}
