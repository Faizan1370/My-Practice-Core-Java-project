package com.dsa.stack.revision;

public class StackImpleRev {
	StackNodeRev head;

	public void push(int data) {
		StackNodeRev newNode = new StackNodeRev(data);
		if (head == null) {
			head = newNode;
			return;
		}
		newNode.next = head;
		head = newNode;
	}

	public void print() {
		if (head == null) {
			return;
		}
		StackNodeRev current = head;
		while (current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}
	}

	public int size() {
		if (head == null) {
			return 0;
		}
		StackNodeRev current = head;
		int count = 0;
		while (current != null) {
			current = current.next;
			count++;
		}
		return count;
	}
	public boolean isEmpty() {
	    return head == null;
	}


	public void pop() {
		if (head == null) {
			return;
		}
		head = head.next;
	}

	public int peek() {
		if (head == null) {
			return -1;
		}

		return head.data;
	}

	public static void main(String[] args) {
		StackImpleRev impleRev = new StackImpleRev();
		impleRev.push(8);
		impleRev.push(7);
		impleRev.push(3);
		impleRev.push(4);
		impleRev.push(9);
		impleRev.push(10);
		impleRev.print();
		System.out.println(impleRev.peek());
		impleRev.pop();
		impleRev.print();
		System.out.println(impleRev.peek());
		System.out.println(impleRev.isEmpty());
	}

}
