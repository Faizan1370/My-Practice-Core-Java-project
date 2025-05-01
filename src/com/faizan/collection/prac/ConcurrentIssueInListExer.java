package com.faizan.collection.prac;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentIssueInListExer {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
    	//CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        list.add("ahdf");
        list.add("gfhfh");

        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    list.add("ghdf" + i);
                    try {
                        Thread.sleep(10); // Slow it down for better chance of collision
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    Iterator<String> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        System.out.println(iterator.next());
                        Thread.sleep(5); // Give time for t1 to add
                    }
                } catch (Exception e) {
                    System.out.println("Exception in t2: " + e);
                }
            }
        };

        t1.start();
        t2.start();
    }
}
