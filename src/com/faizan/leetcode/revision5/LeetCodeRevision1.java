package com.faizan.leetcode.revision5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCodeRevision1 {

	public static boolean palinDrom(int n) {
		if (n < 0) {
			return false;
		}
		int temp = n;
		int sum = 0;
		while (n > 0) {
			int r = n % 10;
			sum = sum * 10 + r;
			n = n / 10;
		}
		if (temp == sum) {
			return true;
		} else {
			return false;
		}
	}

	public static int romanToInteger(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 100);
		int sum = 0;
		int i, j;
		for (i = 0, j = 1; j < s.length(); i++, j++) {
			if (map.get(s.charAt(i)) > map.get(s.charAt(j))) {
				sum += map.get(s.charAt(i));
			} else {
				sum -= map.get(s.charAt(i));
			}
		}
		sum += map.get(s.charAt(i));
		return sum;

	}

	public static int romanToInt(String s) {
		Map<Character, Integer> map = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);

		int sum = 0;

		for (int i = 0; i < s.length(); i++) {
			int value = map.get(s.charAt(i));

			// If next value is larger, subtract current
			if (i + 1 < s.length() && value < map.get(s.charAt(i + 1))) {
				sum -= value;
			} else {
				sum += value;
			}
		}
		return sum;
	}

	public static boolean validPara(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '(' || ch == '{' || ch == '[') {
				stack.push(ch);
			} else {
				if (stack.isEmpty())
					return false;
				char top = stack.pop();
				if (ch == ')' && top != '(')
					return false;
				if (ch == '}' && top != '{')
					return false;
				if (ch == ']' && top != '[')
					return false;
			}
		}
		return stack.isEmpty();
	}

	public static int[] bitCount(int n) {
		int[] result = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			int bitCount = Integer.bitCount(i);
			result[i] = bitCount;
		}
		return result;
	}

	public static int[] mergeSortedArray(int[] nums, int[] nums2, int m, int n) {
		int p1 = m - 1;
		int p2 = n - 1;
		int p3 = nums.length - 1;

		while (p3 >= 0) {
			int element1 = (p1 >= 0) ? nums[p1] : Integer.MIN_VALUE;
			int element2 = (p2 >= 0) ? nums2[p2] : Integer.MIN_VALUE;
			if (element1 > element2) {
				nums[p3] = element1;
				p3--;
				p1--;
			} else {
				nums[p3] = element2;
				p3--;
				p2--;
			}
		}
		return nums;
	}

	public static int buySell(int[] prices) {
		int min = Integer.MAX_VALUE;
		int maxProfit = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < min) {
				min = prices[i];
			}
			int currentProfit = prices[i] - min;
			maxProfit = Math.max(maxProfit, currentProfit);
		}
		return maxProfit;
	}

	public static int singleNumber(int[] nums) {
		int result = 0;
		for (int num : nums) {
			result ^= num;
		}
		return result;
	}

	public static boolean duplicate2(int[] nums, int k) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = 1; j < nums.length; j++) {
				if (nums[i] == nums[j] && Math.abs(i - j) <= k) {
					return true;
				}
			}
		}
		return false;

	}

	public static String[] reverseString(String[] s) {
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

	public static boolean ranSomeNote(String ransomeNote, String magzize) {
		Map<Character, Long> collect = magzize.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		for (int i = 0; i < ransomeNote.length(); i++) {
			if (collect.containsKey(ransomeNote.charAt(i)) && collect.get(ransomeNote.charAt(i)) >= 1) {
				collect.put(ransomeNote.charAt(i), collect.get(ransomeNote.charAt(i)) - 1);
			} else {
				return false;
			}
		}
		return true;
	}

	public static char findDii(String s, String t) {
		int freq1 = 0, freq2 = 0;
		for (int i = 0; i < s.length(); i++) {
			freq1 += s.charAt(i);
		}
		for (int i = 0; i < t.length(); i++) {
			freq2 += t.charAt(i);
		}
		return (char) (freq2 - freq1);
	}

	public static char findDiff(String s, String t) {
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

	public static int jwelsStone(String jwels, String stone) {
		HashSet<Character> set = new HashSet<Character>();
		for (int i = 0; i < jwels.length(); i++) {
			set.add(jwels.charAt(i));
		}
		int count = 0;
		for (int i = 0; i < stone.length(); i++) {
			if (set.contains(stone.charAt(i))) {
				count++;
			}
		}
		return count;
	}

	public static int[] squareSotedArr(int[] nums) {
		int start = 0, end = nums.length - 1;
		int j = nums.length - 1;
		int[] result = new int[nums.length];
		while (start < end) {
			int element1 = nums[start] * nums[start];
			int elelment2 = nums[end] * nums[end];
			if (element1 > elelment2) {
				result[j] = element1;
				start++;
			} else {
				result[j] = elelment2;
				end--;
			}
			j--;
		}
		return result;
	}

	public static int lastStoneWeight(int[] stone) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>((a, b) -> b - a);
		for (int st : stone) {
			queue.add(st);
		}
		while (queue.size() > 1) {
			int first = queue.poll();
			int second = queue.poll();
			if (first > second) {
				queue.add(first - second);
			}
		}
		return queue.peek();
	}

	public static String removeAdjacent(String s) {
		Stack<Character> stack = new Stack<Character>();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (stack.isEmpty()) {
				stack.push(ch);
			} else if (stack.peek() == ch) {
				stack.pop();
			} else {
				stack.push(ch);
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
		int count = 0;
		int lCount = 0;
		int rCount = 0;
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

	public static int evenDigits(int[] nums) {
		int count = 0;
		for (int num : nums) {
			if (countDigit(num) % 2 == 0) {
				count++;
			}
		}
		return count;
	}

	private static int countDigit(int num) {
		int ctr = 0;
		while (num > 0) {
			ctr++;
			num = num / 10;
		}
		return ctr;
	}

	public static boolean[] kidsCandies(int[] candies, int extraCan) {
		int max = Integer.MIN_VALUE;
		for (int can : candies) {
			if (can > max) {
				max = can;
			}
		}
		boolean[] ans = new boolean[candies.length];
		for (int i = 0; i < candies.length; i++) {
			if (candies[i] + extraCan >= max) {
				ans[i] = true;
			}
		}
		return ans;
	}

	public static int maxProduce(int[] nums) {
		Arrays.sort(nums);
		return (nums[nums.length - 1] - 1) * (nums[nums.length - 2] - 1);
	}

	public static int[] runningSum(int[] nums) {
		int[] ans = new int[nums.length];
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			ans[i] = sum;
		}
		return ans;
	}

	public static double avgSum(int[] salary) {
		Arrays.sort(salary);
		int sum = 0;
		for (int sal : salary) {
			sum += sal;
		}
		sum = sum - (salary[0] + salary[salary.length - 1]);
		return sum / (salary.length - 2);
	}

	public static String reformatDate(String date) {
		String[] dates = date.split(" ");
		String year = dates[dates.length - 1];
		String month = dates[1];
		String day = dates[0];
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("Oct", 10);
		StringBuilder builder = new StringBuilder();
		String finalDay = "";
		for (int i = 0; i < day.length(); i++) {
			if (Character.isDigit(day.charAt(i))) {
				finalDay += day.charAt(i);
			}
		}
		if (finalDay.length() == 1) {
			finalDay = "0" + finalDay;
		}
		builder.append(finalDay).append(map.get(month)).append(year);
		return builder.toString();

	}

	public static String shufflingString(String s, int[] indices) {
		if (s.length() != indices.length) {
			return "";
		}
		char[] chaArr = new char[indices.length];
		for (int i = 0; i < s.length(); i++) {
			chaArr[indices[i]] = s.charAt(i);
		}
		return new String(chaArr);
	}

	public static boolean consecutiveOdd(int[] nums) {
		int count = 0;
		for (int num : nums) {
			if (num % 2 != 0) {
				count++;
			} else {
				count = 0;
			}
			if (count == 3) {
				return true;
			}
		}
		return false;
	}

	public static double meanOfArray(int[] arr) {
		int limit = (arr.length * 5) / 100;
		Arrays.sort(arr);
		int sum = 0;
		for (int i = limit; i < arr.length - limit; i++) {
			sum += arr[i];
		}
		return sum / (arr.length - (2 * limit));
	}

	public static ArrayList<Integer> sortArrayFreq(int[] nums) {
		Map<Integer, Long> map = Arrays.stream(nums).boxed()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int num : nums) {
			list.add(num);
		}
		Collections.sort(list, new Comparator<Integer>() {

			public int compare(Integer o1, Integer o2) {
				if (map.get(o1) != map.get(o2)) {
					return (int) (map.get(o1) - map.get(o2));
				} else {
					return o2 - o1;
				}

			}
		});
		return list;
	}

	public static String goalParser(String s) {
		int i = 0;
		StringBuilder builder = new StringBuilder();
		while (i < s.length()) {
			if (s.charAt(i) == '(' && s.charAt(i + 1) == ')') {
				builder.append("o");
				i = i + 2;
			} else if (s.charAt(i) == 'G') {
				builder.append("G");
				i = i + 1;
			} else {
				builder.append("al");
				i = i + 4;
			}
		}
		return builder.toString();
	}

	public static String stringAlternative(String word1, String word2) {
		StringBuilder builder = new StringBuilder();
		int len1 = word1.length();
		int len2 = word2.length();
		int min = Math.min(len1, len2);
		String longestString = "";
		if (len1 == min) {
			longestString = word2;
		}
		if (len2 == min) {
			longestString = word1;
		}
		for (int i = 0; i < min; i++) {
			builder.append(word1.charAt(i));
			builder.append(word2.charAt(i));
		}
		builder.append(longestString.substring(min));
		return builder.toString();

	}

	public static int secondLarget(String s) {
		int largest = Integer.MIN_VALUE, secondLargest = Integer.MIN_VALUE;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (Character.isDigit(ch)) {
				if (Character.getNumericValue(ch) > largest) {
					largest = Character.getNumericValue(ch);
				} else if (Character.getNumericValue(ch) > secondLargest) {
					secondLargest = Character.getNumericValue(ch);
				}
			}
		}
		return secondLargest;
	}

	public static String listWords(String s, int k) {
		String[] words = s.split(" ");
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < k; i++) {
			builder.append(words[i]).append(" ");
		}
		return builder.toString();
	}

	public static int signOfProduct(int[] nums) {
		int count = 0;
		for (int num : nums) {
			if (num < 0) {
				count++;
			}
		}
		if (count % 2 == 0) {
			return 1;
		} else {
			return -1;
		}
	}
	public static boolean checkPanagram(String s) {
		HashSet<Character> set = new HashSet<Character>();
		s=s.toLowerCase();
		for(int i=0;i<s.length();i++) {
			if(Character.isLetter(s.charAt(i))) {
				set.add(s.charAt(i));	
			}
		}
		System.out.println(set.size());
		return set.size()==26;
	}
	
	public static String sortSentence(String s) {
		String[] words = s.split(" ");
		String[] result = new String[words.length];
		
		for(int i=0;i<words.length;i++) {
			int pos= Character.getNumericValue(words[i].charAt(words[i].length()-1))-1;
			result[pos]=words[i].substring(0,words[i].length()-1);
		}
		return String.join(" ", result);
	}
	
	public static boolean equalFreq(String s) {
		Set<Long> collect = s.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet()
		.stream()
		.map(entry->entry.getValue())
		.collect(Collectors.toSet());
		
		return collect.size()==1;
	}

	public static void main(String[] args) {
		String s="aaabbb";
		System.out.println(equalFreq(s));
	}

}
