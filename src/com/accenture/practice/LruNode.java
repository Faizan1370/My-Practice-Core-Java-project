package com.accenture.practice;

public class LruNode {
	int key;
	int value;
	LruNode next;
	LruNode previous;
	
	public LruNode(int key,int value) {
		this.key=key;
		this.value=value;
		this.next=null;
		this.previous=null;
	}

}
