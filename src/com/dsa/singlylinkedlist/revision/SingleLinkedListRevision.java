package com.dsa.singlylinkedlist.revision;

import com.dsa.singlylinkedlist.ListNode;

public class SingleLinkedListRevision {
	
	LisNodeRe head;
	
	public void insertNodeAtStart(int data) {
		LisNodeRe newNode = new LisNodeRe(data);
		if(head==null) {
			head=newNode;
			return;
		}else {
			newNode.next=head;
			head=newNode;
		}
	}
	
	public void insertNodeAtEnd(int data) {
		LisNodeRe newNode = new LisNodeRe(data);
		if(head==null) {
			head=newNode;
			return;
		}else {
			LisNodeRe current = head;
			while(current.next!=null) {
				current=current.next;
			}
			current.next=newNode;
		}
	}
	
	public void print() {
		if(head==null) {
			return;
		}
		LisNodeRe current =head;
		while(current!=null) {
			System.out.print(current.data +" ");
			current=current.next;
		}
	}
	public int length() {
		int length=0;
		LisNodeRe current =head;
		while(current!=null) {
			current=current.next;
			length++;
		}
		return length;
	}
	public void removeFirst() {
		if(head==null) {
			return;
		}
		head=head.next;
	}
	public void removeLast() {
		if(head==null) {
			return;
		}
		if (head.next == null) {  // only one node
	        head = null;
	        return;
	    }
		LisNodeRe current = head;
		LisNodeRe pre=null;
		while(current.next!=null) {
			pre =current;
			current=current.next;
		}
		pre.next=null;
	}
	public void reverseList() {
		if(head==null) {
			return;
		}
		if(head.next==null) {
			return;
		}
		LisNodeRe nxt;
		LisNodeRe pre=null;
		LisNodeRe current = head;
		while(current!=null) {
			nxt=current.next;
			current.next=pre;
			pre=current;
			current=nxt;
		}
		head=pre;
	}
	public void insertNodeGivenPosition(int value ,int position) {
		LisNodeRe newNode = new LisNodeRe(value);
		if(position<1 || position>length()+1) {
			return;
		}
		if(position==1) {
			head.next=newNode;
			head = newNode;
			return;
		}else {
			int count=0;
			LisNodeRe pre=head;
			while(count<position-1) {
				pre = pre.next;
				count++;
			}
			LisNodeRe current = pre.next;
			pre.next=newNode;
			newNode.next=current;
		}
	}
	public void deleteNodeGivenPosition(int position) {
		if(position==1) {
			head=head.next;
			return;
		}else {
			int count=0;
			LisNodeRe current=head;
			LisNodeRe pre = null;
			while(count<position-1) {
				pre=current;
				current=current.next;
				count++;
			}
			pre.next=current.next;
		}
	}
	
	public void deleteNodeGivenPosition1(int position) {
	    // Case 1: Empty list
	    if (head == null) {
	        System.out.println("List is empty");
	        return;
	    }

	    // Case 2: Delete head node
	    if (position == 1) {
	        head = head.next;
	        return;
	    }

	    LisNodeRe current = head;
	    LisNodeRe pre = null;
	    int count = 1;

	    // Traverse to the node before the one we want to delete
	    while (current != null && count < position) {
	        pre = current;
	        current = current.next;
	        count++;
	    }

	    // Case 3: Invalid position (position > list length)
	    if (current == null) {
	        System.out.println("Invalid position");
	        return;
	    }

	    // Case 4: Normal delete
	    pre.next = current.next;
	}
	public boolean searchElementByValue(int searchkey) {
		if(head==null) {
			return false;
		}
		LisNodeRe current = head;
		while(current !=null) {
			if(current.data==searchkey) {
				return true;
			}
			current=current.next;
		}
		return false;
	}

	
	public LisNodeRe findMiddleNode() {
		if(head==null) {
			throw new RuntimeException("list is empty");
		}
		LisNodeRe slowPtr= head;
		LisNodeRe fastPtr=head;
		while(fastPtr!=null && fastPtr.next!=null) {
			slowPtr=slowPtr.next;
			fastPtr=fastPtr.next.next;
		}
		return slowPtr;
	}
	public static void main(String[] args) {
		SingleLinkedListRevision linkedListRevision = new SingleLinkedListRevision();
		linkedListRevision.insertNodeAtStart(5);
		linkedListRevision.insertNodeAtStart(9);
		linkedListRevision.insertNodeAtStart(7);
		linkedListRevision.insertNodeAtStart(10);
		linkedListRevision.insertNodeAtEnd(17);
		linkedListRevision.print();
	 System.out.println(linkedListRevision.findMiddleNode().data);	
	}
}
