package com.faizan.array.prac;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;



public class Ex7 {
	
	public static void reArrangePosNeg() {
		int[] array= {-2,5,7,-4,10};
		int j=0;
		for(int i=0;i<array.length;i++) {
			if(array[i]<0) {
				if(i!=j) {
					int temp=array[i];
					array[i]=array[j];
					array[j]=temp;
				}
				j++;
			}
		}
		System.out.println(Arrays.toString(array));
		
	}
	public static void kthLargestValue() {
		int[] array = {1,6,4,9,3};
		int k=2;
		PriorityQueue<Integer> integers = new PriorityQueue<Integer>();
		
		for(int i=0;i<array.length;i++) {
			integers.offer(array[i]);
			if(integers.size()>k) {
				integers.remove();
			}
		}
		System.out.println(integers.peek());
		
		
	}
	public static void kthLargestValue1() {
		int[] array = {1,6,4,9,3};
		int k=2;
		
		Integer integer = Arrays.stream(array).mapToObj(val->(Integer)val)
		.sorted(Comparator.reverseOrder()).skip(k-1).findFirst().get();
		
		System.out.println(integer);
		
		
	}
	
	public static void kthMinValue() {
		int[] array = {1,6,4,9,3};
		int k=2;
		PriorityQueue<Integer> integers = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}
		});
		for(int i=0;i<array.length;i++) {
			integers.offer(array[i]);
			if(integers.size()>k) {
				integers.remove();
			}
		}
		System.out.println(integers.peek());
	}
	
	public static void kthMinValue1() {
		int[] array = {1,6,4,9,3};
		int k=2;
		
		Integer integer = Arrays.stream(array).mapToObj(val->(Integer)val)
		.sorted().skip(k-1).findFirst().get();
		
		System.out.println(integer);
		
		
	}
	
	public static int[] twoSum() {
		int[] array = {3,5,6,2,9,1,10};
		int target=8;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0;i<array.length;i++) {
			int compliment = target-array[i];
			if(map.containsKey(compliment)) {
				return new int[] {map.get(compliment),i};
			}else {
				map.put(array[i], i);
			}
			
		}
		return new int[0];
	}
	
	public static void insertValue() {
		int[] array = {5,4,8,1,4,9};
		int pos=3;
		int val=10;
		
		for(int i=array.length-1;i>pos-1;i--) {
			array[i]=array[i-1];
		}
		array[pos-1]=val;
		System.out.println(Arrays.toString(array));
		
	}
	
	public static void deleteValue() {
		int[] array = {5,4,8,1,4,9};
		int delete=4;
		for(int i=0;i<array.length;i++) {
			if(array[i]==delete) {
				for(int j=i;j<array.length-1;j++) {
					array[j]=array[j+1];	
				}
				break;
				
			}
		}
		System.out.println(Arrays.toString(array));
		
	}
	
	public static void selectionSort() {
		int[] array = {3,5,7,2,9,10};
		
		for(int i=0;i<array.length;i++) {
			int minIndex=i;
			for(int j=i+1;j<array.length;j++) {
				if(array[j]<array[minIndex]) {
					minIndex=j;
				}
				int temp=array[i];
				array[i]=array[minIndex];
				array[minIndex]=temp;
			}
		}
		System.out.println(Arrays.toString(array));
		
	}
	public static void insertionSort() {
		int[] array = {3,5,7,2,9,10};
		
		for(int i=1;i<array.length;i++) {
			int key=array[i];
			int j=i-1;
			while(j>=0 && array[j]>key) {
				array[j+1]=array[j];
				j--;
			}
			array[j+1]=key;
		}
		System.out.println(Arrays.toString(array));
		
	}
	public static void checkPalindrome() {
		int num=127;
		String str=num+"";
		int start=0;
		int end=str.length()-1;
		boolean isPali=true;
		while(start<end) {
			if(str.charAt(start)!=str.charAt(end)) {
				isPali =false;
				break;
			}
			start++;
			end--;
		}
		if(isPali) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}
		
	
	}
	public static void checkPalindrome1() {
		int num=121;
		int temp=num;
		int sum=0;
		
		while(num>0) {
			int r=num%10;
			sum =sum*10+r;
			num=num/10;
		}
		if(temp==sum) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}
		
	}
	
	public static void checkAmongst() {
		int num=156;
		int temp=num;
		int sum=0;
		
		while(num>0) {
			int r=num%10;
			sum =sum+r*r*r;
			num=num/10;
		}
		if(temp==sum) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}
		
	}
	
	public static void main(String[] args) {
		checkAmongst();
		//System.out.println(Arrays.toString(twoSum()));
	}

}
