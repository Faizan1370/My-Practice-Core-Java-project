package com.faizan.array.prac;

import java.util.Arrays;

public class SortingArrayAlgo {
	static int RUN = 32;
	
	public static void bubbleSort() {
		int[] array= {5,4,7,8,2,34};
		 
		for(int i=0;i<array.length-1;i++) {
			for(int j=i+1;j<array.length;j++) {
				if(array[i]>array[j]) {
					int temp=array[i];
					array[i]=array[j];
					array[j]=temp;
					//System.out.println(Arrays.toString(array));
				}
				
			}
		}
		System.out.println(Arrays.toString(array));
	}
	
	public static void selectionSort() {
		int[] array= {5,4,7,8,2,34};
		
		for(int i=0;i<array.length-1;i++) {
			int minIndex=i;
			for(int j=i+1;j<array.length;j++) {
				if(array[j]<array[minIndex]) {
					minIndex=j;
				}
			}
			int temp=array[i];
			array[i]=array[minIndex];
			array[minIndex] =temp;
		}
		System.out.println(Arrays.toString(array));
	}
	
	public static void insertionSort() {
		int[] array= {5,4,7,8,2,34};
		
		for(int i=1;i<array.length;i++) {
			int key =array[i];
			int j=i-1;
			while(j>=0 && array[j]>key) {
				array[j+1]=array[j];
				j--;
				System.out.println("***"+j);
			}
			array[j+1] =key;
			System.out.println(j+1);
		}
		System.out.println(Arrays.toString(array));
	}
	
	public static void mergerSort(int[] arr,int left,int right) {
		if(left>=right) {
			return;
		}
		int mid=left+(right-left)/2;
		mergerSort(arr, left, mid);
		mergerSort(arr, mid+1, right);
		merge(arr, left, mid, right);
		
	}
	
	private static void merge(int[] arr,int left,int mid,int right) {
		int[] temp=new int[right-left+1];
		
		int i=left; // strt for left
		int j=mid+1; // start for right
		int k=0; // Pointer for temp array
		
		 while(i<=mid && j<=right) {
			 if(arr[i]<=arr[j]) {
				 temp[k]=arr[i];
				 k++;
				 i++;
			 }else {
				 temp[k]=arr[j];
				 k++;
				 j++;
			 }
		 }
		 while(i<=mid) {
			 temp[k++]=arr[i++];
		 }
		 while(j<=right) {
			 temp[k++]=arr[j++];
		 }
		 
		 System.arraycopy(temp, 0, arr, left, temp.length);

	}
	
	 // Reusing your insertion sort on a segment
    public static void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= left && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    // Your merge sort logic
    public static void mergerSort1(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergerSort(arr, left, mid);
        mergerSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    // Your merge function (fixed 'i' start from 'left')
    private static void merge1(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        System.arraycopy(temp, 0, arr, left, temp.length);
    }
	 public static void timSort(int[] arr) {
		int n = arr.length;
		
		for(int i=0;i<n;i+=RUN) {
			insertionSort(arr, i, Math.min((i + RUN - 1), (n - 1)));
		}
		 // Merge sorted runs using merge sort
		for(int size=RUN;size<n;size=2*size) {
			for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));

                if (mid < right) {
                    merge1(arr, left, mid, right);
                }
            }
		}
	 }
	public static void quickSort(int[] arr ,int low ,int high) {
		if(low<high) {
			
			int pivot = partiion(arr, low, high);
			quickSort(arr, low, pivot-1);
			quickSort(arr, pivot+1, high);
			
		}
	}
	private static int partiion(int[] arr,int low,int high) {
		int pivot=arr[high];
		int i=low-1;
		 for(int j=low;j<high;j++) {
			 if(arr[j]<pivot) {
				 i++;
				 int temp=arr[i];
				 arr[i]=arr[j];
				 arr[j]=temp;
			 } 
			 }
		 int temp=arr[i+1];
		 arr[i+1]=arr[high];
		 arr[high]=temp;
		 
		 return i+1;
		 
			 
		
	}
  public static void main(String[] args) {
	  int[] arr = {6, 3, 8, 5, 2, 7, 4, 1};
      //quickSort(arr, 0, arr.length - 1);
	  //timSort(arr);
      //System.out.println("Sorted array using Quick Sort:");
      //System.out.println(Arrays.toString(arr));
	  insertionSort();
}
}
