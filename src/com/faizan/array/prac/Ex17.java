package com.faizan.array.prac;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Ex17 {
	public static int[] rotateByK(int[] nums, int k) {
		int n = nums.length;
		if (n == 0 || n <= k || k <= 0) {
			System.out.println("invalid input");
		}
		k = k % n;
		reverse(nums, 0, n - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, n - 1);
		return nums;

	}

	private static void reverse(int[] nums, int i, int j) {
		while (i < j) {
			nums[i] = nums[i] ^ nums[j];
			nums[j] = nums[i] ^ nums[j];
			nums[i] = nums[i] ^ nums[j];
			i++;
			j--;
		}
	}

	public static void findMaxSumSubArray() {
		int[] array = { 3, -2, -3, 4, 7 };
		int currentMax = 0, max = 0;
		for (int i = 0; i < array.length; i++) {
			currentMax = currentMax + array[i];
			if (currentMax > max) {
				max = currentMax;
			}
			if (currentMax < 0) {
				currentMax = 0;
			}
		}
		System.out.println(max);

	}

	public static void findMaxSumSubArray1() {
		int[] array = { 3, -2, -3, 4, 7 };
		int currentMax = 0, max = 0;
		for (int i = 0; i < array.length; i++) {
			currentMax = Math.max(array[i], currentMax + array[i]);
			max = Math.max(max, currentMax);
		}
		System.out.println(max);

	}

	public static int[] indices(int[] array, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < array.length; i++) {
			int compliment = target - array[i];
			if (map.containsKey(compliment)) {
				return new int[] { map.get(compliment), i };
			} else {
				map.put(array[i], i);
			}
		}
		return new int[] { -1, -1 };

	}

	public static int[] twoSumSortedArray(int[] nums, int target) {
		int start = 0, end = nums.length - 1;
		while (start < end) {
			if (nums[start] + nums[end] == target) {
				return new int[] { start, end };
			} else if ((nums[start] + nums[end]) > target) {
				end--;

			} else {
				start++;
			}
		}
		return new int[] { -1, -1 };
	}

	public static int kthLargest(int[] nums, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for (int i = 0; i < nums.length; i++) {
			queue.add(nums[i]);
			if (queue.size() > k) {
				queue.poll();
			}
		}
		return queue.peek();
	}

	public static int kthSmallest(int[] nums, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
		for (int i = 0; i < nums.length; i++) {
			queue.add(nums[i]);
			if (queue.size() > k) {
				queue.poll();
			}
		}
		return queue.peek();
	}

	public static void removeDeuplicate() {
		int[] array = { 7, 8, 3, 7, 4 };
		int[] result = new int[array.length];
		int k = 0;
		for (int i = 0; i < array.length; i++) {
			boolean isDuplicate = false;
			for (int j = 0; j < k; j++) {
				if (array[i] == result[j]) {
					isDuplicate = true;
				}
			}
			if (!isDuplicate) {
				result[k] = array[i];
				k++;
			}
		}
		System.out.println(Arrays.toString(result));

	}

	public static void removeDeuplicate1() {
		int[] array = { 7, 8, 3, 7, 4 };
		int[] result = new int[array.length];
		int k = 0;
		boolean[] seen = new boolean[256];
		for (int i = 0; i < array.length; i++) {
			if (!seen[array[i]]) {
				result[k] = array[i];
				seen[array[i]] = true;
				k++;
			}
		}
		System.out.println(Arrays.toString(result));
	}

	public static void removeDeuplicate2() {
		int[] array = { 7, 8, 3, 7, 4 };
		int[] result = new int[array.length];
		int k = 0;
		int[] seen = new int[256];
		for (int i = 0; i < array.length; i++) {
			if (seen[array[i]] == 0) {
				result[k] = array[i];
				seen[array[i]] = 1;
				k++;
			}
		}
		System.out.println(Arrays.toString(result));
	}

	public static void insertElement() {
		int[] array = { 3, 5, 7, 2, 9 };
		int element = 10;
		int position = 3;

		for (int i = array.length - 1; i > position - 1; i--) {
			array[i] = array[i - 1];
		}
		array[position - 1] = element;
		System.out.println(Arrays.toString(array));

	}

	public static void deleteElement() {
		int[] array = { 3, 5, 7, 2, 9 };
		int delete = 2;

		for (int i = 0; i < array.length; i++) {
			if (array[i] == delete) {
				for (int j = i; j < array.length - 1; j++) {
					array[j] = array[j + 1];
				}
				break;
			}
		}
		System.out.println(Arrays.toString(array));

	}

	public static void movePosNeg() {
		int[] array = { -3, 5, -7, 2, 9 };
		int j = 0;
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] < 0) {
				if (i != j) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
				j++;
			}
		}
		System.out.println(Arrays.toString(array));

	}

	public static void movePosNeg1() {
		int[] array = { -3, 5, -7, 2, 9 };
		int j = array.length - 1;
		for (int i = array.length - 1; i >= 0; i--) {
			if (array[i] < 0) {
				if (i != j) {
					array[i] = array[i] ^ array[j];
					array[j] = array[i] ^ array[j];
					array[i] = array[i] ^ array[j];
				}
				j--;
			}
		}
		System.out.println(Arrays.toString(array));

	}

	public static void selectionSort() {
		int[] array = { 10, 5, 7, 2, 9 };

		for (int i = 0; i < array.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			array[i] = array[i] ^ array[minIndex];
			array[minIndex] = array[i] ^ array[minIndex];
			array[i] = array[i] ^ array[minIndex];
		}
		System.out.println(Arrays.toString(array));
	}

	public static void insertionSort() {
		int[] array = { 3, 5, 7, 2, 9 };
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

	public static void main(String[] args) {
		insertionSort();
	}

}
