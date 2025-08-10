package com.dsa.stack;

public class StackImpl {
	
	private StackNode head;
	
	
	public void push(int data) {
		StackNode newNode = new StackNode(data);
		newNode.next=head;
		head=newNode;
	}
	public void pop() {
		if(head==null) {
			return;
		}
		StackNode current=head;
		head=current.next;
		current.next=null;
	}
	
	public void display() {
		if(head==null) {
			return;
		}else {
			StackNode current=head;
			while(current !=null) {
				System.out.print(current.data +" ");
				current =current.next;
			}
		}
	}
	
	public void size() {
		if(head==null) {
			System.out.println("0");
		}
		StackNode current=head;
		int count=0;
		while(current!=null) {
			current =current.next;
			count++;
		}
		System.out.println(count);
	}
	
	public void peek() {
		if(head==null) {
			return;
		}
		System.out.println(head.data);
	}
	
	public static void main(String[] args) {
		StackImpl impl = new StackImpl();
		impl.push(1);
		impl.push(4);
		impl.push(8);
		impl.push(2);
		impl.display();
		System.out.println();
		impl.size();
		impl.peek();
		impl.pop();
		impl.display();
		System.out.println();
		impl.size();
		impl.peek();
	}

}
