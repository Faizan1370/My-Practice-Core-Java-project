package com.dsa.queue;

import java.util.Stack;

public class QueueUsingStack {
	Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    
    public void enqueue(int data) {
    	stack1.push(data);
    }
    
    public int deqeue() {
    	if(isEmpty()) {
    		return -1;
    	}
    	if(stack2.isEmpty()) {
    		while(!stack1.isEmpty()) {
    			stack2.push(stack1.pop());
    		}
    	}
    	return stack2.pop();
    }
    
    public int peek() {
    	if(isEmpty()) {
    		return -1;
    	}
    	if(stack2.isEmpty()) {
    		while(!stack1.isEmpty()) {
    			stack2.push(stack1.pop());
    		}
    	}
    	return stack2.peek();
    }
    // Check if queue is empty
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

}
