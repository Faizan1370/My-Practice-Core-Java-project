package com.dsa.queue;

public class QueueImpl {
	private QueueNode head;
	private QueueNode tail;
	
	/*
	 * public void add(int data) { QueueNode newNode= new QueueNode(data);
	 * if(head==null) { head=tail=newNode; return; } QueueNode current = head;
	 * while(current.next!=null) { current =current.next; } current.next=newNode;
	 * newNode.next=null; }
	 */
	public void add(int data) { //or offer
		QueueNode newNode= new QueueNode(data);
		if(head==null) {
			head=tail=newNode;
			return;
		}
		tail.next=newNode;
		tail=newNode;
	}
	
	public void display() {
		if(head==null) {
			return;
		}
		QueueNode current=head;
		while(current!=null) {
			System.out.print(current.data +" ");
			current =current.next;
		}
	}
	
	public void remove() { // or poll
		if(head==null) {
			return;
		}
		QueueNode current=head;
		head=head.next;
		current.next=null;
		if (head == null) {
			tail = null; // Queue is now empty
		}
	}
	
	public void peek() {
		if (head == null) {
			System.out.println("Queue is empty.");
		} else {
			System.out.println("Peek: " + head.data);
		}
	}
	
	public int size() {
	    int count = 0;
	    QueueNode current = head;
	    while (current != null) {
	        count++;
	        current = current.next;
	    }
	    return count;
	}

	
	public static void main(String[] args) {
		QueueImpl impl = new QueueImpl();
		impl.add(2);
		impl.add(4);
		impl.add(1);
		impl.display();
		System.out.println();
		impl.peek();
		impl.remove();
		impl.peek();
	}

}
