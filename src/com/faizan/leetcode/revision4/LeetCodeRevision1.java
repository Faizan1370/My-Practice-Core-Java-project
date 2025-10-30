package com.faizan.leetcode.revision4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCodeRevision1 {

	public static boolean checkPalindrome(int num) {
		int temp = num;
		int sum = 0;
		while (num > 0) {
			int r = num % 10;
			sum = sum * 10 + r;
			num = num / 10;
		}
		if (sum == temp) {
			return true;
		} else {
			return false;
		}
	}

	public static int romanToInt(String romanString) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('M', 1000);

		int result = 0;
		char[] chars = romanString.toCharArray();
		int i, j;
		for (i = 0, j = 1; j < chars.length; i++, j++) {
			if (map.get(chars[i]) >= map.get(chars[j])) {
				result += map.get(chars[i]);
			} else {
				result -= map.get(chars[i]);
			}
		}
		result += map.get(chars[i]);
		return result;
	}

	public static int romanToInt1(String romanString) {
		Map<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);

		int result = 0;

		for (int i = 1; i < romanString.length(); i++) {
			int curr = map.get(romanString.charAt(i));
			int prev = map.get(romanString.charAt(i - 1));

			if (prev < curr) {
				result -= prev; // subtract previous if smaller
			} else {
				result += prev; // otherwise, add previous
			}
		}

		// finally, add the last character value
		result += map.get(romanString.charAt(romanString.length() - 1));
		return result;
	}

	public static boolean validParanthesis(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '(' || ch == '{' || ch == '[') {
				stack.push(ch);
			} else if (!stack.isEmpty() && ch == ')' && stack.peek() == '(') {
				stack.pop();
			} else if (!stack.isEmpty() && ch == '}' && stack.peek() == '{') {
				stack.pop();
			} else if (!stack.isEmpty() && ch == ']' && stack.peek() == '[') {
				stack.pop();
			}
		}
		return stack.isEmpty();
	}

	public static int insertPosition(int[] nums, int target) {
		int start = 0, end = nums.length - 1;
		int mid = 0;
		while (start <= end) {
			mid = start + (end - start) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] > target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return start;

	}

	public static int[] mergeSortedArray(int[] nums1, int m, int n, int[] nums2) {
		int p1 = m - 1;
		int p2 = n - 1;
		int p3 = nums1.length - 1;
		while (p3 > 0) {
			int element1 = (p1 >= 0) ? nums1[p1] : Integer.MIN_VALUE;
			int element2 = (p2 >= 0) ? nums2[p2] : Integer.MIN_VALUE;
			if (element1 > element2) {
				nums1[p3] = element1;
				p3--;
				p1--;
			} else {
				nums1[p3] = element2;
				p3--;
				p2--;
			}
		}
		return nums1;
	}

	public static int bestTimeBuySel(int[] prices) {
		int min = Integer.MAX_VALUE;
		int maxProfit = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < min) {
				min = prices[i];
			}
			int currentProfit = prices[i] - min;
			if (currentProfit > maxProfit) {
				maxProfit = currentProfit;
			}
		}
		return maxProfit;
	}

	public static boolean validPalendrome(String s) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i))) {
				builder.append(s.charAt(i));
			}
		}
		String neStr = builder.toString();
		neStr = neStr.toLowerCase();
		int start = 0, end = neStr.length() - 1;
		while (start < end) {
			if (neStr.charAt(start) != neStr.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}

	public static int singleNumber(int[] nums) {
		int result = 0;
		for (int num : nums) {
			result ^= num;
		}
		return result;
	}

	public static int majorityElement(int[] nums) {
		int n = nums.length;
		return Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream().filter(entry -> entry.getValue() > n / 2).map(entry -> entry.getKey()).findFirst()
				.get();
	}

	public static boolean containsDuplicate(int[] nums, int k) {

		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] == nums[j] && Math.abs(i - j) <= k) {
					return true;
				}
			}

		}
		return false;
	}

	public static boolean containsDuplicate1(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				if (Math.abs(i - map.get(nums[i])) <= k) {
					return true;
				}
			} else {
				map.put(nums[i], i);
			}
		}
		return false;
	}

	public static int[] countingBits(int n) {
		int[] result = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			String binaryString = Integer.toBinaryString(i);
			result[i] = countOnces(binaryString);
		}
		return result;
	}

	private static int countOnces(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				count++;
			}
		}
		return count;
	}

	public static String[] reverse(String[] s) {
		int start = 0, end = s.length - 1;
		while (start < end) {
			String temp = s[start];
			s[start] = s[end];
			s[end] = temp;
			start++;
			end--;
		}
		return s;
	}

	public static boolean ransomeNode(String ransomeNote, String magzine) {
		Map<Character, Long> map = magzine.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		for (int i = 0; i < ransomeNote.length(); i++) {
			if (map.containsKey(ransomeNote.charAt(i)) && map.get(ransomeNote.charAt(i)) > 0) {
				map.put(ransomeNote.charAt(i), map.get(ransomeNote.charAt(i)) - 1);
			} else {
				return false;
			}
		}
		return true;
	}

	public static char diffrence(String s, String t) {
		int freq1 = 0, freq2 = 0;
		for (int i = 0; i < s.length(); i++) {
			freq1 += s.charAt(i);
		}

		for (int i = 0; i < t.length(); i++) {
			freq2 += t.charAt(i);
		}
		return (char) (freq2 - freq1);
	}

	public static char diffrence1(String s, String t) {
		ArrayList<Character> list = new ArrayList<Character>();
		for (int i = 0; i < s.length(); i++) {
			list.add(s.charAt(i));
		}

		for (int i = 0; i < t.length(); i++) {
			if (!list.remove((Character) t.charAt(i))) {
				return (Character) t.charAt(i);
			}
		}
		throw new IllegalArgumentException("No extra character found or invalid input.");
	}

	public static int maxConsecutiveOnes(int[] array) {
		int cutrrentMax = 0, max = 0;
		for (int num : array) {
			if (num == 1) {
				cutrrentMax++;
			} else {
				cutrrentMax = 0;
			}
			max = Math.max(cutrrentMax, max);
		}
		return max;
	}

	public static boolean directCapital(String s) {
		return allCap(s) || allLower(s) || firstCap(s);
	}

	public static boolean allCap(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isUpperCase(s.charAt(i))) {
				count++;
			}
		}
		return count == s.length();
	}

	public static boolean allLower(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLowerCase(s.charAt(i))) {
				count++;
			}
		}
		return count == s.length();
	}

	public static boolean firstCap(String s) {
		if (Character.isUpperCase(s.charAt(0)) && allLower(s.substring(1))) {
			return true;
		}
		return false;
	}

	public static String reverseWord(String s) {
		String[] words = s.split(" ");
		StringBuilder builder = new StringBuilder();
		for (String word : words) {
			builder.append(reverse(word)).append(" ");
		}
		return builder.toString().trim();
	}

	private static String reverse(String word) {
		StringBuilder builder = new StringBuilder();
		for (int i = word.length() - 1; i >= 0; i--) {
			builder.append(word.charAt(i));
		}
		return builder.toString();
	}

	public static boolean binarySeacrh(int[] nums, int target) {
		int start = 0, end = nums.length - 1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (nums[mid] == target) {
				return true;
			} else if (nums[mid] > target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return false;
	}

	public static int jwelsStones(String jwels, String stones) {
		HashSet<Character> set = new HashSet<Character>();
		for (int i = 0; i < jwels.length(); i++) {
			set.add(jwels.charAt(i));
		}
		int count = 0;
		for (int i = 0; i < stones.length(); i++) {
			if (set.contains(stones.charAt(i))) {
				count++;
			}
		}
		return count;
	}

	public static boolean validMountainArray(int[] array) {
		int i = 0, j = array.length - 1;

		while (i < array.length && array[i] < array[i + 1]) {
			i++;
		}
		while (j >= 0 && array[j] < array[j - 1]) {
			j--;
		}

		return i == j;
	}

	public static int[] squareArray(int[] nums) {
		int start = 0, end = nums.length - 1;
		int[] result = new int[nums.length];
		int j = nums.length - 1;
		while (start <= end) {
			int element1 = nums[start] * nums[start];
			int element2 = nums[end] * nums[end];
			if (element2 > element1) {
				result[j] = element2;
				end--;
			} else {
				result[j] = element1;
				start++;
			}
			j--;
		}
		return result;
	}

	public static int[] squareArray1(int[] nums) {
		int left = 0, right = nums.length - 1;
		int[] result = new int[nums.length];
		for (int i = nums.length - 1; i >= 0; i--) {
			int element1 = nums[left] * nums[left];
			int element2 = nums[right] * nums[right];
			if (element1 > element2) {
				result[i] = element1;
				left++;
			} else {
				result[i] = element2;
				right--;
			}
		}
		return result;
	}

	public static int lastStoneWeight(int[] stones) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
		for (int i = 0; i < stones.length; i++) {
			queue.add(stones[i]);
		}
		while (queue.size() > 1) {
			int first = queue.poll();
			int seond = queue.poll();
			if (first > seond) {
				queue.add(first - seond);
			}
		}
		return queue.peek();
	}

	public static String removeAllAdjacentDup(String s) {
		Stack<Character> stack = new Stack<Character>();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (stack.isEmpty()) {
				stack.push(s.charAt(i));
			} else if (stack.peek() == s.charAt(i)) {
				stack.pop();
			} else {
				stack.push(s.charAt(i));
			}
		}
		if (!stack.isEmpty()) {
			for (char c : stack) {
				builder.append(c);
			}
		}
		return builder.toString();
	}

	public static int splitString(String s) {
		int lCount = 0, rCount = 0;
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'L') {
				lCount++;
			}
			if (s.charAt(i) == 'R') {
				rCount++;
			}
			if (lCount == rCount) {
				count++;
			}
		}
		return count;
	}

	public static int countEvenDigistCount(int[] nums) {
		int count = 0;
		for (int num : nums) {
			if (countDigits(num) % 2 == 0) {
				count++;
			}
		}
		return count;
	}

	private static int countDigits(int num) {
		int count = 0;
		while (num > 0) {
			count++;
			num = num / 10;

		}
		return count;
	}
	
	public static boolean[] kidsWithCandies(int[] candies,int extraCandie) {
		int max=Integer.MIN_VALUE;
		for(int can:candies) {
			if(max<can) {
				max=can;
			}
		}
		boolean[] result= new boolean[candies.length];
		for(int i=0;i<candies.length;i++) {
			if(candies[i]+extraCandie>=max) {
				result[i]=true;
			}else {
				result[i]=false;
			}
		}
		return result;
	}
	
	public static int maxProduct(int[] nums) {
		Arrays.sort(nums);
		return (nums[nums.length-1]-1)*(nums[nums.length-2]-1);
	}
	
	public static int[] runnigSum(int[] nums) {
		int sum=0;
		int[] result=new int[nums.length];
		for(int i=0;i<nums.length;i++) {
			result[i]=sum+nums[i];
			sum +=nums[i];
		}
		return result;
	}
	public  static double avgSalary(int[] salaries) {
		int max = Arrays.stream(salaries).max().getAsInt();
		int min = Arrays.stream(salaries).min().getAsInt();
		int sum=0;
		for(int sal:salaries) {
			sum +=sal;
		}
		double avg=(sum-max-min)/(salaries.length-2);
		
		return avg;
	}
	
	public static String reformatDate(String date) {
		String[] dates = date.split(" ");
		String day=dates[0];
		String month=dates[1];
		String year=dates[2];
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("OCT", 10);
		StringBuilder builder = new StringBuilder();
		String finalDay="";
		for(int i=0;i<day.length();i++) {
			if(Character.isDigit(day.charAt(i))) {
				finalDay +=day.charAt(i);
			}
		}
		builder.append(year).append("-").append(map.get(month)).append("-").append(finalDay);
		return builder.toString();
		
	}
	
	public static String shufflingString(String s,int[] indices) {
		if(s.length()!=indices.length) {
			return "";
		}
		char[] charr= new char[indices.length];
		for(int i=0;i<s.length();i++) {
			charr[indices[i]]=s.charAt(i);
		}
		return new String(charr);
	}
	
	public static boolean threeConseCutiveOdd(int[] arr) {
		int count=0;
		for(int num:arr) {
			if((num &1)!=0) {
				count++;
			}else {
				count=0;
			}
			if(count==3) {
				return true;
			}
		}
		return false;
	}
	
	public static double meanOfArray(int[] arr) {
		int limit=(arr.length*5)/100;
		Arrays.sort(arr);
		int sum=0;
		for(int i=limit;i<arr.length-limit;i++) {
			sum +=arr[i];
		}
		return sum/(arr.length- (2*limit));
	}
	public static ArrayList<Integer> sortArray(int[] arr) {
		Map<Integer, Long> map = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int num:arr) {
			list.add(num);
		}
		Collections.sort(list,new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(map.get(o1) !=map.get(o2)) {
					return (int) (map.get(o1) - map.get(o2));
				}else {
					return o2-o1;
				}
			}
		});
		return list;
	}
	
	public static String goalParser(String s) {
		int i=0;
		StringBuilder builder = new StringBuilder();
		while(i<s.length()) {
			if(s.charAt(i)=='(' && s.charAt(i+1)==')') {
				builder.append("o");
				i=i+2;
			}else if(s.charAt(i)=='G') {
				builder.append("G");
				i=i+1;
			}else {
				builder.append("al");
				i=i+4;
			}
		}
		return builder.toString();
	}
	
	public static int sumOfUnique(int[] nums) {
		 int reduce = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
				.entrySet().stream().filter(entry->entry.getValue()==1L).map(entry->entry.getKey()).reduce(0,(a,b)->a+b);
		return reduce;
	}
	
	public static String stringAlternative(String word1,String word2) {
		StringBuilder builder = new StringBuilder();
		int len1=word1.length();
		int len2=word2.length();
		int min = Math.min(len1, len2);
		String longestString="";
		if(min==len1) {
			longestString=word2;
		}
		if(min==len2) {
			longestString=word1;
		}
		for(int i=0;i<min;i++) {
			builder.append(word1.charAt(i));
			builder.append(word2.charAt(i));
		}
		builder.append(longestString.substring(min));
		return builder.toString();
	}
	
	public static int secondLargestDigit(String s) {
		int largest=0,seocndLargest=0;
		for(int i=0;i<s.length();i++) {
			if(Character.isDigit(s.charAt(i))) {
				if(Character.getNumericValue(s.charAt(i))>largest) {
					seocndLargest=largest;
					largest=Character.getNumericValue(s.charAt(i));
				}else if(Character.getNumericValue(s.charAt(i))>seocndLargest) {
					seocndLargest=Character.getNumericValue(s.charAt(i));
				}
			}
		}
		return seocndLargest;
	}
	public static String truncateString(String s,int k) {
		String[] words = s.split(" ");
		if(words.length<k) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<k;i++) {
			builder.append(words[i]).append(" ");
		}
		return builder.toString();
	}
	
	public static int signOfProduct(int[] nums) {
		int count=0;
		for(int num:nums) {
			if(num<0) {
				count++;
			}
		}
		if((count & 1)==0) {
			return 1;
		}else {
			return -1;
		}
	}
	
	public static boolean checkPanagram(String s) {
		HashSet<Character> set = new HashSet<Character>();
		for(int i=0;i<s.length();i++) {
			if(Character.isLetter(s.charAt(i))) {
				set.add(s.charAt(i));	
			}
			
		}
		return set.size()==26;
	}
	public static String sortSentence(String s) {
		String[] words = s.split(" ");
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		StringBuilder builder = new StringBuilder();
		for(String word:words) {
			map.put(Character.getNumericValue(word.charAt(word.length()-1)), word.substring(0, word.length()-1));
		}
		for(int i=1;i<=words.length;i++) {
			builder.append(map.get(i)).append(" ");
		}
		return builder.toString().trim();
	}
	public static String sortSentence1(String s) {
		String[] words = s.split("\\\\s+");
		String[] result= new String[words.length];
		for(String word:words) {
			int pos= Character.getNumericValue(word.charAt(word.length()-1))-1;
			result[pos]=word.substring(0,word.length()-1);
		}
		return String.join(" ", result);
	}
	
	public static int[] arrayConcat(int[] nums) {
		int[] ans= new int[2*nums.length];
		for(int i=0;i<nums.length;i++) {
			ans[i]=nums[i];
			ans[nums.length+i]=nums[i];
		}
		return ans;
	}
	
	public static boolean checkCharSameOccurence(String s) {
		Set<Long> set = s.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet().stream()
		.map(entry->entry.getValue()).collect(Collectors.toSet());
		
		return set.size()==1;
	}
	
	
	public static String kthDistinctStr(String[] arr, int k) {
	    // Handle null or empty array
	    if (arr == null || arr.length == 0) {
	        return null;
	    }

	    // Handle invalid k
	    if (k <= 0) {
	        return null;
	    }

	    // Compute kth distinct string safely
	    Optional<String> result = Arrays.stream(arr)
	        .filter(Objects::nonNull) // handle null elements inside array
	        .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
	        .entrySet()
	        .stream()
	        .filter(entry -> entry.getValue() == 1L)
	        .map(Map.Entry::getKey)
	        .skip(k - 1)
	        .findFirst();

	    return result.orElse(null); // return null if not found
	}


	public static void main(String[] args) {
		String[] arr = {"b","c","c","d","d","a"};
		System.out.println(kthDistinctStr(arr, 2));
	}

}
