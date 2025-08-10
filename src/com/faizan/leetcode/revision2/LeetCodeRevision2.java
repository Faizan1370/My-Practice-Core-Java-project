package com.faizan.leetcode.revision2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.OptionalInt;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCodeRevision2 {

	public static boolean directCapital(String s) {
		if (allLower(s) || allUpper(s) || firstCap(s)) {
			return true;
		}
		return false;
	}

	public static boolean allLower(String s) {

		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLowerCase(s.charAt(i))) {
				count++;
			}
		}
		return s.length() == count;
	}

	public static boolean allUpper(String s) {

		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isUpperCase(s.charAt(i))) {
				count++;
			}
		}
		return s.length() == count;
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
			builder.append(reverse(word) + " ");
		}
		return builder.toString().trim();
	}

	public static String reverse(String word) {
		String result = "";
		for (int i = word.length() - 1; i >= 0; i--) {
			result += word.charAt(i);
		}
		return result;
	}

	public static int searchElelement(int[] nums, int element) {
		int left = 0, right = nums.length - 1, mid = 0;

		for (int i = 0; i < nums.length; i++) {
			mid = left + (right - left) / 2;
			if (nums[mid] == element) {
				return nums[mid];
			} else if (nums[mid] < element) {
				left = mid + 1;
			} else if (nums[mid] > element) {
				right = mid - 1;
			}
		}
		return -1;
	}

	public static int jwelsStones(String jwels, String stones) {
		HashSet<Character> set = new HashSet<Character>();
		int count = 0;
		for (int i = 0; i < jwels.length(); i++) {
			set.add(jwels.charAt(i));
		}
		for (int i = 0; i < stones.length(); i++) {
			if (set.contains(stones.charAt(i))) {
				count++;
			}
		}
		return count;
	}

	public static int[][] transposeMatrix(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] result = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result[j][i] = matrix[i][j];
			}
		}
		return result;
	}

	public static boolean validMaountainArray(int[] nums) {
		if (nums.length < 3) {
			return false;
		}
		int i = 0, j = nums.length - 1;
		while (i < nums.length && nums[i] < nums[i + 1]) {
			i++;
		}
		while (j >= 0 && nums[j] < nums[j - 1]) {
			j--;
		}
		if (i == j) {
			return true;
		}
		return false;
	}

	public static int[] sqaureArray(int[] array) {
		int start = 0;
		int end = array.length - 1;
		int[] ans = new int[array.length];

		for (int i = array.length - 1; i >= 0; i--) {
			int elemernt1 = array[start] * array[start];
			int elemernt2 = array[end] * array[end];
			if (elemernt1 > elemernt2) {
				ans[i] = elemernt1;
				start++;
			} else {
				ans[i] = elemernt2;
				end--;
			}
		}
		return ans;
	}

	public static int smashStone(int[] stones) {
		PriorityQueue<Integer> quueu = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
		for (int stone : stones) {
			quueu.add(stone);
		}
		while (quueu.size() > 1) {
			int first = quueu.poll();
			int second = quueu.poll();
			if (first > second) {
				quueu.add(first - second);
			}
		}
		return quueu.peek();
	}

	public static String removeAllAdjacent(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			if (stack.isEmpty()) {
				stack.add(s.charAt(i));
			} else if (stack.peek() == s.charAt(i)) {
				stack.pop();
			} else {
				stack.add(s.charAt(i));
			}
		}
		StringBuilder builder = new StringBuilder();
		for (char c : stack) {
			builder.append(c);
		}
		return builder.toString();
	}

	public static int balaceStringCount(String s) {
		int l_count = 0;
		int r_count = 0;
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'L') {
				l_count++;
			} else {
				r_count++;
			}
			if (l_count == r_count) {
				count++;
			}
		}
		return count;
	}

	public static int balaceSubArrayCount(int[] nums) {
		int zeroCout = 0;
		int oneCount = 0;
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				zeroCout++;
			} else {
				oneCount++;
			}
			if (zeroCout == oneCount) {
				count++;
			}
		}
		return count;
	}

	public static int evenDiigtsCount(int[] nums) {
		int count = 0;
		for (int num : nums) {
			if (digitCount(num) % 2 == 0) {
				count++;
			}
		}
		return count;
	}

	public static int digitCount(int num) {
		int count = 0;
		while (num > 0) {
			num = num / 10;
			count++;
		}
		return count;
	}

	public static boolean[] kidsWithCandies(int[] candies, int extraCandy) {
		int max = Integer.MIN_VALUE;
		boolean[] ans = new boolean[candies.length];
		for (int can : candies) {
			if (can > max) {
				max = can;
			}
		}
		for (int i = 0; i < candies.length; i++) {
			if ((candies[i] + extraCandy) >= max) {
				ans[i] = true;
			} else {
				ans[i] = false;
			}
		}
		return ans;
	}

	public static int maximumProduct(int[] nums) {
		int largest = 0, secondLargest = 0;
		for (int num : nums) {
			if (largest < num) {
				secondLargest = largest;
				largest = num;
			} else if (secondLargest < num) {
				secondLargest = num;
			}
		}

		return ((largest - 1) * (secondLargest - 1));
	}

	public static int[] runningSum(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum = sum + nums[i];
			nums[i] = sum;
		}
		return nums;
	}

	public static double avergaeSalary(int[] salary) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int sum = 0;

		for (int i = 0; i < salary.length; i++) {
			if (min > salary[i]) {
				min = salary[i];
			}
			if (max < salary[i]) {
				max = salary[i];
			}
			sum += salary[i];

		}
		double avg = (sum - min - max) / (salary.length - 2);
		return avg;

	}

	public static String reformatDate(String date) {
		String[] words = date.split(" ");
		String day = words[0];
		String month = words[1];
		String year = words[2];
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Jan", "01");
		map.put("Feb", "02");
		map.put("March", "03");
		map.put("April", "04");
		map.put("May", "05");
		map.put("June", "06");
		String formatDay = "";
		for (int i = 0; i < day.length(); i++) {
			if (Character.isDigit(day.charAt(i))) {
				formatDay += day.charAt(i);
			}
		}
		if (formatDay.length() == 1) {
			formatDay = "0" + formatDay;
		}
		StringBuilder builder = new StringBuilder();
		builder.append(year).append("-").append(map.get(month)).append("-").append(formatDay);
		return builder.toString();

	}

	public static String shufflngString(String s, int[] array) {
		if (s.length() != array.length) {
			return "";
		}
		HashMap<Integer, Character> map = new HashMap<Integer, Character>();
		for (int i = 0; i < array.length; i++) {
			map.put(array[i], s.charAt(i));
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			builder.append(map.get(i));
		}

		return builder.toString();
	}

	public static boolean consecutiveOdd(int[] nums) {
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] % 2 != 0 && nums[i - 1] % 2 != 0) {
				return true;
			}
		}
		return false;
	}

	public static double meanArray(int[] array) {
		Arrays.sort(array);
		int limit = (array.length * 5) / 100;
		int sum = 0;
		for (int i = limit; i < array.length - limit; i++) {
			sum += array[i];
		}

		return sum / (array.length - (2 * limit));
	}
	
	public static int[] sortArrayFreq(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0;i<nums.length;i++) {
			if(map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i])+1);
			}else {
				map.put(nums[i], 1);
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int num:nums) {
			list.add(num);
		}
		
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				int freq1=map.get(o1);
				int freq2=map.get(o2);
				if(freq1!=freq2) {
					return freq1-freq2;
				}else {
					return o2-o1;
				}
			}
		});
		
		int[] result = new int[nums.length];
		for(int i=0;i<nums.length;i++) {
			result[i]=list.get(i);
		}
		
		return result;
	}
	
	public static String goalParser(String s) {
		int i=0;
		StringBuilder builder = new StringBuilder();
		while(i<s.length()) {
			if(s.charAt(i)=='G') {
			  builder.append("G");
			  i++;
			}else if(s.charAt(i)=='(' && s.charAt(i+1)==')') {
				builder.append("o");
				i=i+2;
			}else {
				builder.append("al");
				i=i+4;
			}
		}
		return builder.toString();
	}
	
	public static int sumOfUnique(int[] nums) { // not proer
		HashSet<Integer> set = new HashSet<Integer>();
		for(int num:nums) {
			if(set.contains(num)) {
				set.remove(num);
			}else {
				set.add(num);
			}
		}
		Integer reduce = set.stream().reduce(0,(a,b)->a+b);
		return reduce;
	}
	
	public static int sumOfDistinct(int[] nums) {
	     Integer reduce = Arrays.stream(nums).mapToObj(num->(Integer)num)
	     .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
	     .entrySet()
	     .stream()
	     .filter(entry->entry.getValue()==1L)
	     .map(entry->entry.getKey())
	     .reduce(0,(a,b)->a+b);
	     return reduce;
	}
	
	public static String mergeSting(String word1,String word2) {
		if(word1.length()>=word2.length()) {
			return merger(word1, word2);
		}else {
			return merger(word2, word1);
		}
	}
	
	private static String merger(String word1,String word2) {
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<word2.length();i++) {
			builder.append(word1.charAt(i)).append(word2.charAt(i));	
		}
	 return	builder.append(word1.substring(word2.length())).toString();
	}
	
	public static int secondLargestDigit(String s) {
		int largest=0,secondLargest=0;
		for(int i=0;i<s.length();i++) {
			if(Character.isDigit(s.charAt(i))) {
				if(Character.getNumericValue(s.charAt(i))>largest) {
					secondLargest=largest;
					largest=Character.getNumericValue(s.charAt(i));
				}else if(Character.getNumericValue(s.charAt(i))>secondLargest) {
					secondLargest=Character.getNumericValue(s.charAt(i));
				}
			}
		}
		return secondLargest;
	}
	
	public static String sentece(String s,int k) {
		String[] words = s.split(" ");
		StringBuilder builder = new StringBuilder();
		for(int i=1;i<=k;i++) {
			if(i!=k) {
				builder.append(words[i-1]).append(" ");
			}else {
				builder.append(words[i-1]);
			}
		}
		
		return builder.toString();
		
	}
	
	public static int productSign(int[] arr) {
		int count=0;
		for(int num:arr) {
			if(num<0) {
				count++;
			}
		}
		if(count%2!=0) {
			return -1;
		}else {
			return 1;
		}
	}
	
	public static boolean sentencePanagram(String s) {
		HashSet<Character> set = new HashSet<Character>();
		for(int i=0;i<s.length();i++) {
			set.add(s.charAt(i));
		}
		if(set.size()==26) {
			return true;
		}
		return false;
	}
	public static String sortingSenetence(String s) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		String[] words = s.split(" ");
		for(String word:words) {
			map.put(Character.getNumericValue(word.charAt(word.length()-1)), word.substring(0,word.length()-1));
		}
		StringBuilder builder = new StringBuilder();
		for(int i=1;i<=words.length;i++) {
			builder.append(map.get(i)).append(" ");
		}
		return builder.toString().trim();
	}
	
	public static int[] concatArray(int[] nums) {
		int[] result= new int[2*nums.length];
		
		for(int i=0;i<nums.length;i++) {
			result[i]=nums[i];
			result[i+nums.length]=nums[i];
		}
		return result;
	}
	
	public static boolean checkSameOccurecne(String s) {
		Set<Long> set = s.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet()
		.stream()
		.map(entry->entry.getValue())
		.collect(Collectors.toSet());
		
		return set.size()==1;
		
	}

	public static void main(String[] args) {
		String s="aabba";
		System.out.println(checkSameOccurecne(s));
	   
	}
}
