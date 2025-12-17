package com.accenture.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class InterviewProblems {
	
	public static List<List<Integer>> threeSum(int[] nums, int target){
	    List<List<Integer>> result = new ArrayList<>();
	    Arrays.sort(nums);

	    for(int i = 0; i < nums.length - 2; i++){
	        if(i > 0 && nums[i] == nums[i-1]) continue; // skip duplicates

	        int left = i + 1;
	        int right = nums.length - 1;

	        while(left < right){
	            int sum = nums[i] + nums[left] + nums[right];
	            if(sum == target){
	                result.add(Arrays.asList(nums[i], nums[left], nums[right]));
	                left++;
	                right--;

	                // skip duplicates
	                while(left < right && nums[left] == nums[left-1]) left++;
	                while(left < right && nums[right] == nums[right+1]) right--;
	            } else if(sum < target){
	                left++;
	            } else {
	                right--;
	            }
	        }
	    }

	    return result;
	}

	public static List<List<Integer>> tripleSum(int[] nums, int target) {

	    List<List<Integer>> result = new ArrayList<>();

	    // Step 1: Store value + index
	    int n = nums.length;
	    int[][] arr = new int[n][2];

	    for (int i = 0; i < n; i++) {
	        arr[i][0] = nums[i];   // value
	        arr[i][1] = i;         // original index
	    }

	    // Step 2: Sort by value
	    Arrays.sort(arr, (a, b) -> a[0] - b[0]);

	    // Step 3: Two pointers for triplets
	    for (int i = 0; i < n - 2; i++) {

	        int left = i + 1;
	        int right = n - 1;

	        while (left < right) {
	            int sum = arr[i][0] + arr[left][0] + arr[right][0];

	            if (sum == target) {
	                // Store original indexes only
	                result.add(Arrays.asList(arr[i][1], arr[left][1], arr[right][1]));
	                left++;
	                right--;
	            } else if (sum < target) {
	                left++;
	            } else {
	                right--;
	            }
	        }
	    }

	    return result;
	}
	
	public static int findPeekElement(int[] nums) {
		for(int i=1;i<nums.length-1;i++) {
			if(nums[i]>nums[i-1] && nums[i]>nums[i+1]) {
				return i;
			}
		}
		if(nums.length==1 || nums[0]>nums[1]) {
			return 0;
		}
		if (nums[nums.length - 1] > nums[nums.length - 2]) {
			return nums.length - 1;
		}
		return -1;
	}
	public static int findPeakElement1(int[] nums) {
		int start = 0, end = nums.length - 1;


	   while(start<end) {
		   int mid = start+(end-start)/2;
		   
		   if(nums[mid]<nums[mid+1]) {
			   start=mid+1;
		   }else {
			   end=mid;
		   }
	   }
	    return start;
	}
	public static List<Integer> topKFrequent(int[] nums, int k) { // not correcty return freq

	    // Step 1: build frequency map
	    Map<Integer, Integer> map = new HashMap<>();
	    for (int n : nums) {
	        map.put(n, map.getOrDefault(n, 0) + 1);
	    }
	    PriorityQueue<Integer> queuue = new PriorityQueue<Integer>((a,b)->(b-a));
	    ArrayList<Integer> list = new ArrayList<Integer>(map.values());
	    for(int num:list) {
	    	queuue.add(num);
	    	if(queuue.size()>k) {
	    		queuue.poll();
	    	}
	    }
	    list.clear();
	    while(!queuue.isEmpty()) {
	    	list.add(queuue.poll());
	    }
	    return list;
	    
	}
	
	public static List<Integer> topKFrequent1(int[] nums, int k) { // correct return value not freq

	    // Step 1: Frequency map
	    Map<Integer, Integer> map = new HashMap<>();
	    for (int num : nums) {
	        map.put(num, map.getOrDefault(num, 0) + 1);
	    }

	    // Step 2: PriorityQueue of entries (min-heap based on frequency)
	    PriorityQueue<Map.Entry<Integer, Integer>> queue =
	            new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

	    // Step 3: Put entries list inside PQ with size limit k
	    ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

	    for (Map.Entry<Integer, Integer> entry : list) {
	        queue.add(entry);

	        if (queue.size() > k) {
	            queue.poll(); // remove smallest frequency
	        }
	    }

	    // Step 4: Extract elements (not frequencies)
	    List<Integer> result = new ArrayList<>();
	    while (!queue.isEmpty()) {
	        result.add(queue.poll().getKey());
	    }

	    return result;
	}
	public static int maxSubarray(int[] nums, int k) {
		 int maxSum = Integer.MIN_VALUE;
		for(int i=0;i<=nums.length-k;i++) {
			
			int currentSum=0;
			for(int j=i;j<k+i;j++) {
				currentSum += nums[j];
			}
			maxSum =Math.max(maxSum, currentSum);
		}
		return maxSum;
	}
	
	public static int maxSubarraySlidingWindow(int[] nums, int k) {
	    int windowSum = 0;

	    // first window
	    for (int i = 0; i < k; i++) {
	        windowSum += nums[i];
	    }

	    int maxSum = windowSum;

	    // slide window
	    for (int i = k; i < nums.length; i++) {
	        windowSum += nums[i] - nums[i - k];
	        maxSum = Math.max(maxSum, windowSum);
	    }

	    return maxSum;
	}




	
	public static void main(String[] args) {
		int[] nums= {1, 4, 2, 10, 23, 3, 1, 0, 20};
		int k=4;
		System.out.println(maxSubarraySlidingWindow(nums, k));
	}

}
