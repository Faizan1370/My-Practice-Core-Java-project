package com.accenture.practice;

import java.util.HashMap;
import java.util.Map;

public class LruImplementation {
	private int capacity;
	private LruNode head;
	private Map<Integer, LruNode> cacheMap;
	private LruNode tail;

	LruImplementation(int capacity) {
		this.capacity = capacity;
		this.cacheMap = new HashMap<>();
		this.head = new LruNode(-1, -1);
		this.tail = new LruNode(-1, -1);
		this.head.next = this.tail;
		this.tail.previous = this.head;
	}

	public int get(int key) {
		if (!cacheMap.containsKey(key)) {
			return -1;
		}
		LruNode node = cacheMap.get(key);
		remove(node);
		add(node);
		return node.value;
	}
	public void put(int key, int value) {
		if(cacheMap.containsKey(key)) {
			LruNode oldNode = cacheMap.get(key);
			remove(oldNode);
		}
		LruNode node = new LruNode(key, value);
		cacheMap.put(key, node);
		add(node);
		if(cacheMap.size()>capacity) {
			LruNode nodeToDelete = tail.previous;
			remove(nodeToDelete);
			cacheMap.remove(nodeToDelete.key);
		}
	}

	private void add(LruNode node) {
		LruNode nextNode = head.next;
		head.next = node;
		node.previous = head;
		node.next = nextNode;
		nextNode.previous = node;

	}

	private void remove(LruNode node) {
		LruNode preNode = node.previous;
		LruNode nextNode = node.next;
		preNode.next = nextNode;
		nextNode.previous = preNode;

	}
	public static void main(String[] args) {
		LruImplementation cache = new LruImplementation(2);

	        cache.put(1, 1);
	        cache.put(2, 2);
	        System.out.println(cache.get(1));
	        cache.put(3, 3);
	        System.out.println(cache.get(2));
	        cache.put(4, 4);
	        System.out.println(cache.get(1));
	        System.out.println(cache.get(3));
	        System.out.println(cache.get(4));
	}
}
