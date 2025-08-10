package com.faizan.array.prac;import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Exer7 {
	
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
				integers.poll();
			}
		}
		System.out.println(integers.peek());
		
	}
	public static void kthMinValue() {
		int[] array = {1,6,4,9,3};
		int k=2;
		PriorityQueue<Integer> integers = new PriorityQueue(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}
		});
		for(int i=0;i<array.length;i++) {
			integers.offer(array[i]);
			if(integers.size()>k) {
				integers.poll();
			}
		}
		System.out.println(integers.peek());
	}
	
	public static void twoSum() {
		int[] array = {3,5,6,2,9,1,10};
		int target=8;
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		
		for(int i=0;i<array.length;i++) {
			int compliment = target-array[i];
			if(hashMap.containsKey(array[i])) {
				System.out.println(hashMap.get(array[i]) +" "+i);
			}else {
				hashMap.put(compliment, i);
			}
		}
		
	}
	
	public static void insertValue() {
		int[] array = {5,4,8,1,4,9};
		int pos=3;
		int value=10;
		for(int i=array.length-1;i>pos-1;i--) {
			array[i]=array[i-1];
		}
		array[pos-1]=value;
		System.out.println(Arrays.toString(array));
		
	}
	public static void deleteValue() {
		int[] array = {5,7,2,8,9};
		int delete =5;
		
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
			int midIndex=i;
			for(int j=i+1;j<array.length;j++) {
				if(array[j]<array[midIndex]) {
					midIndex=j;
				}
				int temp=array[i];
				array[i]=array[midIndex];
				array[midIndex]=temp;
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
		int num=121;
		String str = num+"";
		int left=0;
		int right=str.length()-1;
		boolean isPllin=false;
		while(left<right) {
			if(str.charAt(left) !=str.charAt(right)) {
				isPllin=false;
				break;
			}else {
				isPllin=true;
			}
			left++;
			right--;
		}
		if(isPllin) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}
	}
	
	public static void checkPalindrome1() {
		int num=125;
	    int temp=num;
	    int sum=0;
		while(num>0) {
			int r =num%10;
			sum =sum*10+r;
			num=num/10;
		}
		if(temp==sum) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}
	}
	
	public static void amonstrong() {
		int num=156;
	    int temp=num;
	    int sum=0;
		while(num>0) {
			int r =num%10;
			sum =sum+r*r*r;
			num=num/10;
		}
		if(temp==sum) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}
	}
	
	public static void removeDuplicate() {
		int[] array= {4,6,1,6,2,4,9,4};
		int[] result= new int[array.length];
		int k=0;
		for(int i=0;i<array.length;i++) {
			boolean isDuplicate=false;
			for(int j=0;j<k;j++) {
				if(array[i]==array[j]) {
					isDuplicate=true;
					break;
				}
				
			}
			if(!isDuplicate) {
				result[k]=array[i];
				k++;
			}
		}
		System.out.println(Arrays.toString(result));
	}
	
	public static void removeDup() {
		int[] array= {4,6,1,6,2,4,9,4};
		int[] result= new int[array.length];
		boolean[] seen=new boolean[128];
		int k=0;
		for(int i=0;i<array.length;i++) {
			if(!seen[array[i]]) {
				result[k]=array[i];
				seen[array[i]]=true;
				k++;
			}
		}
		System.out.println(Arrays.toString(result));
	}
	
	public static void removeDup1() {
		int[] array= {4,6,1,6,2,4,9,4};
		int[] result= new int[array.length];
		int[] seen=new int[256];
		int k=0;
		for(int i=0;i<array.length;i++) {
			if(seen[array[i]]==0) {
				result[k]=array[i];
				seen[array[i]]=1;
				k++;
			}
		}
		System.out.println(Arrays.toString(result));
	}
	
	public static void selectionSort1() {
		int[] array= {4,2,65,6,3};
		
		for(int i=0;i<array.length;i++) {
			int midIndex=i;
			for(int j=i+1;j<array.length;j++) {
				if(array[j]<array[midIndex]) {
					midIndex=j;
				}
			
			}
			int temp=array[i];
			array[i]=array[midIndex];
			array[midIndex]=temp;
		}
		System.out.println(Arrays.toString(array));
	}
	
	public static void insertionSort1() {
		int[] array= {4,6,1,54,8,9};
		
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
	
	public static void rotatebyK() {
		int[] array= {4,6,1,54,8,9};
		int k=2;
		 int n=array.length;
		 if(array==null || k>=n || k<=0 || n==0) {
			 System.out.println("invlaid input");
		 }
		 k=k%n;
		 reverser(array,0,n-1);
		 reverser(array,0,k-1);
		 reverser(array,k,n-1);
		 System.out.println(Arrays.toString(array));
		 
	}
	
	private static void reverser(int[] array,int start,int end) {
		while(start<end) {
			int temp=array[start];
			array[start]=array[end];
			array[end]=temp;
			start++;
			end--;
		}
	}
	 public static void findMasSumSubArray1() {
		 int[] array= {3,-2,-3,4,7};
		 int currentMax=0;
		 int maxSum=0;
		 for(int i=0;i<array.length;i++) {
			 currentMax=Math.max(array[i], currentMax+array[i]);
			 maxSum=Math.max(maxSum, currentMax);
		 }
		 System.out.println(maxSum);
	 }
	 public static void indices() {
		 int[] array= {3,2,5,4,7};
		 int target=7;
		 HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		 for(int i=0;i<array.length;i++) {
			 int compliment=target-array[i];
			 if(map.containsKey(compliment)) {
				 System.out.println(map.get(compliment)+ " "+i);
			 }else {
				 map.put(array[i], i);
			 }
		 }
		
	 }
	 public static void kthLargestElement() {
		 int[] array= {3,2,5,4,7};
		 int k=2;
		 PriorityQueue<Integer> integers = new PriorityQueue<Integer>();
		 for(int i=0;i<array.length;i++) {
			 integers.add(array[i]);
			 if(integers.size()>k) {
				 integers.poll();
			 }
		 }
		 System.out.println(integers.peek());
	 }
	public static void main(String[] args) {
		kthLargestElement();
	}

}
