package com.faizan.array.prac;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Ex4 {
	
	public static void removeDuplicate() {
		int[] array = {4,6,2,8,90,78,4,2};
		
		int[] resultArray =new int[array.length];
		int k=0;
		
		for(int i =0;i<array.length;i++) {
			boolean isDuplicate=false;
			for(int j=0;j<k;j++) {
				if(array[i]==resultArray[j]) {
					isDuplicate=true;
					break;
				}
			}
			if(!isDuplicate) {
				resultArray[k]=array[i];
				k++;
			}
		}
		System.out.println(Arrays.toString(resultArray));
	}
	
	public static void removeDuplicate1() {
		int[] array = {4,6,2,8,90,78,4,2};
		int[] resultArray = new int[array.length];
		boolean[] seen = new boolean[127];
		int j=0;
		for(int i=0;i<array.length;i++) {
			if(!seen[array[i]]) {
				resultArray[j]=array[i];
				seen[array[i]] =true;
				j++;
				
			}
			
		}
		System.out.println(Arrays.toString(resultArray));
	}
	
	
	public static void removeDuplicate2() {
		int[] array = {4,6,2,8,90,78,4,2};
		int[] resultArray = new int[array.length];
		int[] seen= new int[256];
		int j=0;
		
		for(int i=0;i<array.length;i++) {
			if(seen[array[i]]==0) {
				resultArray[j]=array[i];
				seen[array[i]]=1;
				j++;
			}
		}
		System.out.println(Arrays.toString(resultArray));
		
	}
	
	 public static void rotateArrayByK(int[] array, int k) {
	        int n = array.length;
	        if (array == null || n == 0 || k <= 0 || k >= n) {
	            System.out.println("Invalid input.");
	            return;
	        }

	        // Normalize k in case it's > n
	        k = k % n;

	        // Step 1: Reverse whole array
	        reverse(array, 0, n - 1);
	        // Step 2: Reverse first k elements
	        reverse(array, 0, k - 1);
	        // Step 3: Reverse remaining n-k elements
	        reverse(array, k, n - 1);
	        System.out.println(Arrays.toString(array));
	    }

	    private static void reverse(int[] array, int start, int end) {
	        while (start < end) {
	            int temp = array[start];
	            array[start++] = array[end];
	            array[end--] = temp;
	        }
	    }
	    public static void findKthLargest() {
	    	int[] array= {3,5,7,9,2};
	    	int k=2;
	    	PriorityQueue<Integer> integers = new PriorityQueue<Integer>();
	    	for(int i =0;i<array.length;i++) {
	    		integers.offer(array[i]);
	    		if(integers.size()>k) {
	    			integers.poll();
	    		}
	    	}
	    	System.out.println(integers.peek());
	    } 
	    public static void findKthLargest1() {
	    	int[] array= {3,5,7,9,2};
	    	int k=2;
	    	 Integer integer = Arrays.stream(array)
	    	 .mapToObj(num->(Integer)num)
	    	 .sorted(Comparator.comparingInt(num->((Integer) num).intValue()).reversed())
	    	 .skip(k-1)
	    	 .findFirst()
	    	 .get();
	    	 System.out.println(integer);
	    }
	    
	    public static void indices() {
	    	int target=7;
	    	int[] array = {1,6,4,3,8,9,2};
	    	Map<Integer,Integer> map = new HashMap<Integer, Integer>();
	    	
	    	for(int i=0;i<array.length;i++) {
	    		int component =target-array[i];
	    		if(map.containsKey(array[i])) {
	    			System.out.println(map.get(array[i]) + " "+i);
	    		}else {
	    			map.put(component, i);
	    		}
	    	}
	    }
	    
	    public static void largestSubArraySum() {
	    	int[] array = {-3,5,7,3,9,-4,12,7};
	    	 int currentMax = array[0];
	    	    int sumMax = array[0];
	    	for(int i=0;i<array.length;i++) {
	    		currentMax=Math.max(array[i], array[i]+currentMax);
	    		sumMax=Math.max(sumMax, currentMax);
	    	}
	    	System.out.println(sumMax);
	    }
	    
	    public static void largestSubArraySum1() {
	        int[] array = {-3, -5, -7, -3, -9, -4, -12, -7};
	        int currentMax = Integer.MIN_VALUE;
	        int sumMax = Integer.MIN_VALUE;

	        for (int i = 0; i < array.length; i++) {
	            if (currentMax < 0) {
	                currentMax = array[i];
	            } else {
	                currentMax += array[i];
	            }
	            sumMax = Math.max(sumMax, currentMax);
	        }

	        System.out.println(sumMax);
	    }
	    public static void checkPrime() {
	    	int num=43;
	    	boolean isPrime=false;
	    	 if (num == 0 || num == 1) {
	 	        System.out.println("Not a valid value");
	 	        return;
	 	    }

	 	    if (num == 2) {
	 	        System.out.println("Yes, prime");
	 	        return;
	 	    }
	    	for(int i=2;i<num/2;i++) {
	    		if(num%i==0) {
	    			isPrime=false;
	    			break;
	    		}
	    	}
	    	if(isPrime) {
	    		System.out.println("prime number");
	    	}else {
	    		System.out.println("Not prime");
	    	}
	    }
	    
	    public static void selectionSort() {
	    	int[] array = {3,6,1,9,4,2};
	    	
	    	for(int i = 0;i<array.length-1;i++) {
	    		int minIndex=i;
	    		for(int j=i+1;j<array.length;j++) {
	    			if(array[j]<array[minIndex]) {
	    				minIndex=j;
	    			}
	    		}
	    		int temp=array[i];
	    		array[i]=array[minIndex];
	    		array[minIndex]=temp;
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
	    
	    public static void deleteValue() {
	    	int[] array= {5,6,2,8,4,9};
	    	int delete=2;
	    	
	    	for(int i=0;i<array.length;i++) {
	    		if(delete==array[i]) {
	    			for(int j=i;j<array.length-1;j++) {
	    				array[j]=array[j+1];
	    			}
	    			break;
	    		}
	    	}
	    	
	    	System.out.println(Arrays.toString(array));
	    }
	    
	    public static void insertValue() {
	    	int[] array= {5,6,2,8,4,9};
	    	int value=7;
	    	int position=3;
	    	
	    	for(int i=array.length-1;i>position-1;i--) {
	    		array[i]=array[i-1];
	    	}
	    	
	    	//System.out.println(Arrays.toString(array));
	    	
	    	array[position-1]=value;
	    	
	    	System.out.println(Arrays.toString(array));
	    	
	    }
	    
	    public static void removeDuplicates() {
	    	int[] array = {4,6,3,3,2,9,4};
	    	int[] resultArray=new int[array.length];
	    	boolean[] seen= new boolean[127];
	    	int j=0;
	    	for(int i=0;i<array.length;i++) {
	    		if(!seen[array[i]]) {
	    			resultArray[j]=array[i];
	    			seen[array[i]]=true;
	    			j++;
	    		}
	    	}
	    	System.out.println(Arrays.toString(resultArray));
	    }
	    
	    public static void removeDuplicates1() {
	    	int[] array = {4,6,3,3,2,9,4};
	    	int[] resultArray=new int[array.length];
	    	int[] seen= new int[256];
	    	int j=0;
	    	for(int i=0;i<array.length;i++) {
	    		if(seen[array[i]]==0) {
	    			resultArray[j]=array[i];
	    			seen[array[i]]=1;
	    			j++;
	    		}
	    	}
	    	System.out.println(Arrays.toString(resultArray));
	    }
	    
	    public static void removeDup() {
	    	int[] array = {4,6,2,7,4,9,1,9,5,4,7};
	    	int[] resultArray =new int[array.length];
	    	
	    	int k=0;
	    	for(int i=0;i<array.length;i++) {
	    		boolean isDuplicate=false;
	    		
	    		for(int j=0;j<k;j++) {
	    			if(array[i]==resultArray[j]) {
	    				isDuplicate=true;
	    				break;
	    			}
	    		}
	    		if(!isDuplicate) {
	    			resultArray[k]=array[i];
	    			k++;
	    		}
	    	}
	    	System.out.println(Arrays.toString(resultArray));
	    }
	    
	    public static void findPalin() {
	    	int num=123;
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
	    
	    public static void findAmonstrong() {
	    	int num=153;
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
	    
	    public static void fibo() {
	    	int a=0,b=1;
	    	for(int i=0;i<=5;i++) {
	    		System.out.print(a +" ");
	    		int c=a+b;
	    		a=b;
	    		b=c;
	    		
	    	}
	    }

	public static void main(String[] args) {
		fibo();
		//findAmonstrong();
		//findPalin();
		//removeDup();
		//removeDuplicates1();
		//removeDuplicate2();
		//int[] array ={4,6,2,8,90,78};
		//rotateArrayByK(array, 3);
		//findKthLargest1();
		//indices();
		//largestSubArraySum1();
		//checkPrime();
		//selectionSort();
		//insertionSort();
		//deleteValue();
		//insertValue();
		
	}

}
