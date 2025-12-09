package com.dsa.doublyLinkedList.revision;

import com.dsa.doublyLinkedList.ListNode;

public class DoublyLinkedListRev {
	ListNodeRev head;
	ListNodeRev tail;
	
	public void insertAtLast(int data) {
		ListNodeRev newNode= new ListNodeRev(data);
		if(head==null) {
			head=newNode;
			tail=newNode;
			return;
		}else {
			ListNodeRev current =head;
			while(current.next!=null) {
				current=current.next;
			}
			current.next=newNode;
			tail=newNode;
			newNode.previous=current;
		}
	}
	public void insertAtLast1(int data) {
        ListNodeRev newNode = new ListNodeRev(data);
        if (head == null) {
            head = newNode;
            tail = newNode; // first node is both head and tail
            return;
        }
        tail.next = newNode;
        newNode.previous = tail;
        tail = newNode; // update tail
    }
	public void insertAtStart(int data) {
		ListNodeRev newNode= new ListNodeRev(data);
		if(head==null) {
			head=newNode;
			tail=newNode;
			return;
		}else {
			newNode.next=head;
			head.previous=newNode;
			head=newNode;
		}
	}
	public void print() {
		if(head==null) {
			return;
		}
		ListNodeRev current =head;
		while(current!=null) {
			System.out.print(current.data +" ");
			current =current.next;
		}
	}
	public void printReverse() {
		if(head==null) {
			return;
		}
		ListNodeRev current =tail;
		while(current!=null) {
			System.out.print(current.data +" ");
			current =current.previous;
		}
	}
	public int length() {
		int count=0;
		ListNodeRev current=head;
		while(current !=null) {
			count++;
			current=current.next;
			
		}
		return count;
	}
	public void deleteFirstNode() {
		if(head==null) {
			return;
		}
		if(head.next==null) {
			head=null;
			return;
		}
		head=head.next;
		head.previous=null;
	}
	public void deleteLastNode() {
		if(head==null) {
			return;
		}
		if(head==tail) {
			head=null;
			tail=null;
			return;
		}
		tail=tail.previous;
		tail.next=null;
	}
	public void deleteLastNodeWithouttail() {
		if(head==null) {
			return;
		}
		if(head.next==null) {
			head=null;
			//tail = null;
			return;
		}
		ListNodeRev current=head;
		while(current.next!=null) {
			current=current.next;
		}
		current=current.previous;
		current.next=null;
		//tail=current;
	}
	
	public void insertAtGivenPosition(int data,int position) {
		ListNodeRev newNode= new ListNodeRev(data);
		if(position==1) {
			newNode.next=head;
			if(head!=null) {
				head.previous=newNode;
			}
			head=newNode;
			return;
		}
		ListNodeRev current = head;
		int count=1;
		 while (count < position - 1 && current.next != null) {
			current= current.next;
			count++;
		}
		if(current.next==null) {
			current.next=newNode;
			newNode.previous=current;
			tail=newNode;
			return;
		 }
		ListNodeRev nxt=current.next;
		newNode.previous=current;
		current.next=newNode;
		nxt.previous=newNode;
		newNode.next=nxt;
	}
	public void deleteAtPosition(int position) {
		if (head == null) return;

	    // Case 1: Delete the first node
	    if (position == 1) {
	        if (head.next == null) {  // only one node
	            head = null;
	            tail = null;
	        } else {
	            head = head.next;
	            head.previous = null;
	        }
	        return;
	    }
		ListNodeRev current=head;
		int count=1;
		while(count<position-1 && current.next!=null) {
			current=current.next;
			count++;
		}
		 // Case 2: Position is invalid (greater than length)
	    if (current.next == null) {
	        return;
	    }
		 // Case 3: Deleting the last node
	    if (current.next.next == null) {
	        current.next = null;
	        tail = current;   // ✅ update tail
	        return;
	    }
		ListNodeRev nxt = current.next.next;
		current.next=nxt;
		nxt.previous=current;
		
	}
	
	public void deleteByValue(int key) {
		if(head==null) {
			return;
		}
		 // Case 1: delete head node
	    if (head.data == key) {
	        ListNodeRev temp = head;
	        head = head.next;
	        if (head != null) {
	            head.previous = null;
	        } else {
	            tail = null; // list became empty
	        }
	        temp.next = null; // clear for GC
	        return;
	    }

	    // Case 2: delete tail node
	    if (tail != null && tail.data == key) {
	        ListNodeRev temp = tail;
	        tail = tail.previous;
	        if (tail != null) {
	            tail.next = null;
	        } else {
	            head = null; // list became empty
	        }
	        temp.previous = null;
	        return;
	    }
		ListNodeRev current =head;
		while(current !=null && current.data !=key) {
			current=current.next;
		}
		if(current==null) {
			return;
		}
		ListNodeRev pre=current.previous;
		pre.next=current.next;
		current.next.previous=pre;
		current=null;
	}
	public int findNodePositionByValue(int data) {
		if(head==null) {
			return 0;
		}
		if(head.data==data) {
			return 1;
		}
		ListNodeRev current = head;
		int count=1;
		while(current!=null && current.data !=data) {
			current=current.next;
			count++;
		}
		if(current !=null && current.data==data) {
			return count;
		}
		return -1;
	}
	public int findNodePositionByValue1(int data) {
	    ListNodeRev current = head;
	    int position = 1;
	    while (current != null) {
	        if (current.data == data) {
	            return position;
	        }
	        current = current.next;
	        position++;
	    }
	    return -1; // not found
	}

	public static void main(String[] args) {
		DoublyLinkedListRev doublyLinkedListRev = new DoublyLinkedListRev();
		doublyLinkedListRev.insertAtLast(8);
		doublyLinkedListRev.insertAtLast(9);
		doublyLinkedListRev.insertAtLast(2);
		doublyLinkedListRev.insertAtLast(5);
		doublyLinkedListRev.insertAtLast(19);
		doublyLinkedListRev.insertAtLast(11);
		doublyLinkedListRev.insertAtStart(55);
		doublyLinkedListRev.print();
		System.out.println();
		System.out.println(doublyLinkedListRev.findNodePositionByValue(11));
		
	}

}
