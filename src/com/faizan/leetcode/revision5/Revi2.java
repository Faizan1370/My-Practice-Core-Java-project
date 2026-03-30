package com.faizan.leetcode.revision5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.dsa.singlylinkedlist.ListNode;
import com.dsa.tree.interview.question.geeksforgeeks.TreeNod;

public class Revi2 {

	public static int accountBalnce(int purchaseAmount) {
		int balalnceAmount = 0;
		int r = purchaseAmount % 10;
		if (r >= 5) {
			balalnceAmount = 10 - r;
			purchaseAmount += balalnceAmount;
		} else {
			purchaseAmount -= r;
		}

		return 100 - purchaseAmount;

	}

	public static int maxPairSum(int[] nums) {
		int maxSum = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (maxDigit(nums[i]) == maxDigit(nums[j])) {
					maxSum = Math.max(maxSum, nums[i] + nums[j]);
				}
			}

		}
		return maxSum;
	}

	private static int maxDigit(int num) {
		int maxDigit = 0;
		while (num > 0) {
			int r = num % 10;
			maxDigit = Math.max(maxDigit, r);
			num = num / 10;
		}
		return maxDigit;
	}
	

	public static boolean acronym(String[] words, String s) {
		StringBuilder builder = new StringBuilder();
		for (String word : words) {
			builder.append(word.charAt(0));
		}
		return builder.toString().equals(s);
	}

	public static int minOperation(int[] nums, int k) {
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 1; i <= k; i++) {
			set.add(i);
		}
		int count = 0;
		for (int num : nums) {
			if (set.contains(num)) {
				set.remove(num);
			}
			if (set.isEmpty()) {
				return count;
			}
			count++;

		}
		return count;
	}

	public static int sumDiff(int n, int m) {
		int sum1 = 0, sum2 = 0;
		for (int num = 1; num <= n; num++) {
			if (num % m == 0) {
				sum2 += num;
			} else {
				sum1 += num;
			}
		}
		return sum1 - sum2;
	}

	public static int highestAltitude(int[] gain) {
		int currentgain = 0, maxGain = 0;
		for (int ga : gain) {
			currentgain = currentgain + ga;
			maxGain = Math.max(currentgain, maxGain);
		}
		return maxGain;
	}

	public static int findChapion(int[][] teams) {
		int count = 0;
		for (int i = 0; i < teams.length; i++) {
			for (int j = 0; j < teams[0].length; j++) {
				if (teams[i][j] == 1) {
					count++;
				}
				if (count == teams.length - 1) {
					return i;
				}
			}
		}
		return 0;
	}

	public static boolean StringEqui(String[] word1, String[] word2) {
		StringBuilder builder = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		for (String word : word1) {
			builder.append(word);
		}
		for (String word : word2) {
			builder2.append(word);
		}
		return builder.toString().equals(builder2.toString());
	}

	public static int elementAppear(int[] nums) {
		int limit = nums.length / 4;
		for (int i = limit; i < nums.length - limit; i++) {
			if (nums[i] == nums[i + 1]) {
				return nums[i];
			}
		}
		return -1;
	}
	public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {

	    Set<Integer> set1 = new HashSet<>();
	    Set<Integer> set2 = new HashSet<>();

	    for (int n : nums1) set1.add(n);
	    for (int n : nums2) set2.add(n);

	    Set<Integer> diff1 = new HashSet<>(set1);
	    Set<Integer> diff2 = new HashSet<>(set2);

	    diff1.removeAll(set2);
	    diff2.removeAll(set1);

	    return Arrays.asList(new ArrayList<>(diff1), new ArrayList<>(diff2));
	}

	public static int[] findIntersectionValues(int[] nums1, int[] nums2) {
		Set<Integer> set1 = new HashSet<>();
		Set<Integer> set2 = new HashSet<>();

		for (int n : nums1)
			set1.add(n);
		for (int n : nums2)
			set2.add(n);

		int count1 = 0, count2 = 0;

		for (int n : nums1) {
			if (set2.contains(n))
				count1++;
		}

		for (int n : nums2) {
			if (set1.contains(n))
				count2++;
		}

		return new int[] { count1, count2 };
	}

	public static int specialPosition(int[][] mat) {
		int row = mat.length;
		int col = mat[0].length;
		int count = 0;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (mat[i][j] == 0) {
					continue;
				}
				boolean isflag = false;
				for (int r = 0; r < row; r++) {
					if (r != i && mat[r][j] == 1) {
						isflag = true;
						break;
					}
				}
				for (int c = 0; c < row; c++) {
					if (c != j && mat[i][c] == 1) {
						isflag = true;
						break;
					}
				}
				if (!isflag) {
					count++;
				}
			}
		}
		return count;
	}

	public static boolean isSequence(String s, String t) {
		StringBuilder builder = new StringBuilder();
		HashSet<Character> set = new HashSet<Character>();
		for (Character ch : s.toCharArray()) {
			set.add(ch);
		}
		for (int i = 0; i < t.length(); i++) {
			if (set.contains(t.charAt(i))) {
				builder.append(t.charAt(i));
			}
		}
		return s.equals(builder.toString());
	}

	public static boolean placeFlower(int[] flower, int n) {
		int count = 0;
		for (int i = 0; i < flower.length; i++) {
			if (flower[i] == 0) {
				while ((i == 0 || flower[i - 1] == 0) && (i == flower.length - 1 || flower[i + 1] == 0)) {
					flower[i] = 1;
					count++;
					if (count == n) {
						return true;
					}
				}

			}
		}
		return false;
	}

	public static boolean canPlaceFlower1(int[] flowerBer, int n) {
		int count = 0;
		for (int i = 1; i < flowerBer.length - 1; i++) {
			if (flowerBer[i] == 0 && flowerBer[i - 1] == 0 && flowerBer[i + 1] == 0) {
				flowerBer[i] = 1;
				count++;
				if (count == n) {
					return true;
				}
			}
		}
		return false;
	}

	public static int removeElement(int[] nums, int val) {
		int start = 0, end = nums.length - 1;
		while (start < end) {
			if (nums[start] == val) {
				nums[start] = nums[end];
				end--;
			} else {
				start++;
			}
		}

		System.out.println(Arrays.toString(Arrays.copyOf(nums, end + 1)));
		return end;
	}

	public static String findGCD(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();
		String result = str1.substring(0, gcd(len1, len2));
		return result;
	}

	private static int gcd(int len1, int len2) {
		if (len2 == 0) {
			return len1;
		}
		return gcd(len2, len1 % len2);
	}

	public static String destinationCity(String[][] paths) {
		HashSet<String> set = new HashSet<String>();
		for (int i = 0; i < paths.length; i++) {
			set.add(paths[i][0]);
		}
		for (int i = 0; i < paths.length; i++) {
			if (!set.contains(paths[i][1])) {
				return paths[i][1];
			}
		}
		return "";

	}

	public static int pivotIndex(int[] nums) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		int leftSum = 0;
		for (int i = 0; i < nums.length; i++) {
			int rightSum = sum - leftSum - nums[i];
			if (leftSum == rightSum) {
				return i;
			}
			leftSum += nums[i];
		}
		return -1;
	}

	public static int pivotIndex1(int[] nums) {
		int start = 0, end = nums.length - 1;
		int leftSum = 0, rightSum = 0;
		while (start <= end) {
			if (leftSum == rightSum && start == end) {
				return start;
			}
			if (leftSum <= rightSum) {
				leftSum += nums[start];
				start++;
			} else {
				rightSum += nums[end];
				end--;
			}
		}
		return -1;
	}

	public static boolean uniqueOccur(int[] nums) {
		Map<Integer, Long> map = Arrays.stream(nums).boxed()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		return map.size() == new HashSet<>(map.values()).size();
	}

	public static String reverseVowelsString(String s) {
		char[] words = s.toCharArray();
		String vowels = "AEIOUaeiuo";
		int start = 0, end = words.length - 1;

		while (start < end) {
			while (start < end && vowels.indexOf(words[start]) == -1) {
				start++;
			}
			while (start < end && vowels.indexOf(words[end]) == -1) {
				end--;
			}
			int temp = words[start];
			words[start] = words[end];
			words[end] = (char) temp;
			start++;
			end--;
		}
		return new String(words);
	}

	public static String reformatStringIp(String ip) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < ip.length(); i++) {
			if (ip.charAt(i) == '.') {
				builder.append("[.]");
			} else {
				builder.append(ip.charAt(i));
			}
		}

		return builder.toString();

	}

	public static int findVal(String[] ops) {
		int val = 0;
		for (String op : ops) {
			if (op == "++X" || op == "X++") {
				val++;
			}
			if (op == "--X" || op == "X--") {
				val--;
			}
		}
		return val;
	}

	public static List<Integer> containingChar(String[] words, char ch) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < words.length; i++) {
			if (words[i].indexOf(ch) != -1) {
				list.add(i);
			}
		}
		return list;
	}

	public static int maxProductDiff(int[] nums) {
		Arrays.sort(nums);

		return (nums[nums.length - 1] * nums[nums.length - 2]) - (nums[0] * nums[1]);
	}

	public static int[] dupMissingNum(int[][] grid) {
		int n = grid.length * 2;
		int sum = n * (n + 1) / 2;
		int[] arr = new int[2];
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (set.contains(grid[i][j])) {
					arr[0] = grid[i][j];
				} else {
					set.add(grid[i][j]);
					sum -= grid[i][j];
				}
			}
		}
		arr[1] = sum;

		return arr;

	}

	public static int maxSentence(String[] sentences) {
		int max = Integer.MIN_VALUE;
		for (String sen : sentences) {
			String[] words = sen.split(" ");
			max = Math.max(max, words.length);
		}
		return max;
	}

	public static String removeTrailingZero(String s) {
		int count = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == '0') {
				count++;
			} else {
				break;
			}
		}
		return s.substring(0, s.length() - count);
	}

	public static int percenageOfLetter(String s, char letter) {
		int n = s.length();
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == letter) {
				count++;
			}
		}
		return (count * 100) / n;
	}

	public static int countWordsPrefix(String[] words, String prefix) {
		int count = 0;
		for (String word : words) {
			if (word.startsWith(prefix)) {
				count++;
			}
		}
		return count;
	}

	public static int buyChoclates(int[] price, int money) {
		Arrays.sort(price);
		int remain = money - (price[0] + price[1]);
		if (remain >= 0) {
			return remain;
		} else {
			return -1;
		}
	}

	public static int maxScore(String s) {
		int max = 0;
		for (int i = 0; i < s.length() - 1; i++) {
			max = Math.max(max, (countZero(s.substring(0, i + 1)) + countOne(s.substring(i + 1))));
		}
		return max;
	}

	private static int countOne(String substring) {
		int count = 0;
		for (int i = 0; i < substring.length(); i++) {
			if (substring.charAt(i) == '1') {
				count++;
			}
		}
		return count;

	}

	private static int countZero(String substring) {
		int count = 0;
		for (int i = 0; i < substring.length(); i++) {
			if (substring.charAt(i) == '0') {
				count++;
			}
		}
		return count;

	}

	public static int maxScoreAfterSplit(String s) {
		int totalOnce = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				totalOnce++;
			}
		}
		int leftZero = 0, rightOnces = totalOnce;
		int maxScore = 0;
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) == '0') {
				leftZero++;
			} else {
				rightOnces--;
			}
			maxScore = Math.max(maxScore, leftZero + rightOnces);
		}
		return maxScore;

	}

	public static double maxAvgSubarray(int[] nums, int k) {
		int maxSum = 0;
		for (int i = 0; i <= nums.length - k; i++) {
			int sum = 0;
			for (int j = i; j < k + i; j++) {
				sum += nums[j];
			}
			maxSum = Math.max(maxSum, sum);
		}
		return (double) maxSum / k;
	}

	public static boolean crossPath(String path) {
		HashSet<String> set = new HashSet<String>();
		int x = 0, y = 0;
		set.add(x + " " + y);
		for (int i = 0; i < path.length(); i++) {
			if (path.charAt(i) == 'N') {
				x++;
			}
			if (path.charAt(i) == 'E') {
				y++;
			}
			if (path.charAt(i) == 'S') {
				x--;
			}
			if (path.charAt(i) == 'W') {
				y--;
			}
			if (set.contains(x + " " + y)) {
				return true;
			}
			set.add(x + " " + y);
		}
		return false;
	}

	public static int minOprationInAltenate(String s) {
		int startWith1 = 0; // 010
		int startWith0 = 0;// 101

		for (int i = 0; i < s.length(); i++) {
			if (i % 2 == 0) {
				if (s.charAt(i) == '0') {
					startWith1++;
				} else {
					startWith0++;
				}
			} else {
				if (s.charAt(i) == '1') {
					startWith1++;
				} else {
					startWith0++;
				}
			}
		}

		return Math.min(startWith1, startWith0);

	}

	public static boolean monolitic(int[] nums) {
		boolean isIncreasing = false, isDecreasing = false;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] < nums[i]) {
				isDecreasing = true;
			} else if (nums[i - 1] > nums[i]) {
				isDecreasing = true;
			}
		}
		if (isIncreasing || isDecreasing) {
			return true;

		}
		return isDecreasing;
	}

	public static int[] minNumberGame(int[] nums) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for (int num : nums) {
			queue.add(num);
		}
		int[] ans = new int[nums.length];
		int idx = 0;
		while (!queue.isEmpty()) {
			int alice = queue.poll();
			int bob = 0;
			if (!queue.isEmpty()) {
				bob = queue.poll();
			}
			if (bob != 0) {
				ans[idx++] = bob;
			}
			ans[idx++] = alice;
		}
		return ans;
	}

	public static int incRemovableSubArray(int[] nums) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i; j < nums.length; j++) {
				if (isIncreasingSubArray(nums, i, j)) {
					count++;
				}
			}
		}
		return count;
	}

	private static boolean isIncreasingSubArray(int[] nums, int start, int end) {
		int prev = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i <= end && i >= start) {
				continue;
			}
			if (nums[i] <= prev) {
				return false;
			}
			prev = nums[i];
		}
		return true;
	}

	public static ArrayList<String> stringMataching(String[] words) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words.length; j++) {
				if (words[i].length() >= words[j].length()) {
					continue;
				}
				if (isSub(words[i], words[j])) {
					list.add(words[i]);
				}
			}
		}
		return list;
	}

	private static boolean isSub(String word1, String word2) {
		if (word2.contains(word1)) {
			return true;
		}
		return false;
	}

	public static boolean makeEqual(String[] words) {
		int[] freq = new int[26];
		if (words.length == 1) {
			return true;
		}
		for (String word : words) {
			for (int i = 0; i < word.length(); i++) {
				freq[word.charAt(i) - 'a']++;
			}

		}
		for (int i : freq) {
			if (i % (words.length) != 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean makeEqual1(String[] words) {
		StringBuilder builder = new StringBuilder();
		for (String word : words) {
			builder.append(word);
		}
		Set<Long> collect = builder.toString().chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.map(entry -> entry.getValue()).collect(Collectors.toSet());
		return collect.size() == 1;
	}

	public static int longestSubstring(String s) {
		int max = 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (map.containsKey(ch)) {
				max = Math.max(max, i - map.get(ch) - 1);
			}
			map.put(ch, i);
		}
		return max;
	}

	public static int checkBitWise(int[] nums) {
		int count = 0;
		for (int num : nums) {
			if ((num & 1) == 0) {
				count++;
			}
		}
		return count;
	}

	public static int findContentChildren(int[] g, int[] s) {
		int cockie = 0, children = 0;
		Arrays.sort(g);
		Arrays.sort(s);
		while (cockie < s.length && children < g.length) {
			if (g[children] <= cockie) {
				children++;
			}
			cockie++;
		}
		return children;
	}

	public static int findContentChildren1(int[] g, int[] s) {
		int children = 0;
		for (int i = 0; i < g.length; i++) {
			if (g[i] <= s[children]) {
				children++;
			}
		}
		return children;
	}

	public static boolean stringAlkie(String s) {
		int n = s.length();
		if (countVowel(s.substring(0, n / 2)) == countVowel(s.substring(n / 2, n))) {
			return true;
		}
		return false;
	}

	private static int countVowel(String substring) {
		int count = 0;
		String vowelString = "AEIOUaeiou";
		for (int i = 0; i < substring.length(); i++) {
			if (vowelString.indexOf(substring.charAt(i)) != -1) {
				count++;
			}
		}
		return count;
	}

	public static int climingStairs(int n) {
		int a = 0, b = 1, c = 0;
		for (int i = 1; i <= n; i++) {
			c = a + b;
			a = b;
			b = c;
		}
		return c;
	}

	public static int missingInteger(int[] nums) {
		int maxSum = 0, sum = 0;
		HashSet<Integer> set = new HashSet<Integer>();
		for (int num : nums) {
			set.add(num);
		}
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] - nums[i - 1] == 1) {
				sum += nums[i];
			} else {
				sum = 0;
			}
			maxSum = Math.max(maxSum, sum);
		}
		/*
		 * if(maxSum==nums[0]) { while(set.contains(maxSum)) { maxSum++; } }
		 */
		while (set.contains(maxSum)) {
			maxSum++;
		}
		return maxSum;

	}

	public static int areaOfMaxDiagonal(int[][] dimensions) {
		int maxArea = 0;
		double maxDiagonal = 0;
		for (int[] dimension : dimensions) {
			int l = dimension[0];
			int w = dimension[1];
			int area = l * w;
			double digonal = Math.sqrt(l * l + w * w);
			if (digonal > maxDiagonal) {
				maxDiagonal = digonal;
				maxArea = area;
			} else if (maxDiagonal == digonal) {
				maxArea = Math.max(area, maxArea);
			}
		}
		return maxArea;
	}

	public static int countMaxFreq(int[] nums) {
		int count = 0;
		Map<Integer, Long> map = Arrays.stream(nums).boxed()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		int max = map.entrySet().stream().map(entry -> entry.getValue()).mapToInt(num -> num.intValue()).max()
				.getAsInt();

		for (int num : nums) {
			if (map.get(num).intValue() == max) {
				count++;
			}
		}
		return count;
	}

	public static int minCost(int[] nums) {
		if (nums.length < 3) {
			return Arrays.stream(nums).sum();
		}
		int first = nums[0];
		int firstMin = Integer.MAX_VALUE;
		int secondMin = Integer.MAX_VALUE;
		for (int i = 1; i < nums.length; i++) {
			if (firstMin > nums[i]) {
				secondMin = firstMin;
				firstMin = nums[i];
			} else if (secondMin > nums[i]) {
				secondMin = nums[i];
			}
		}
		return first + firstMin + secondMin;
	}

	public static int[] setMismatch(int[] nums) {
		HashSet<Integer> set = new HashSet<Integer>();
		int[] res = new int[2];
		for (int num : nums) {
			if (set.contains(num)) {
				res[0] = num;
			} else {
				set.add(num);
			}
		}
		for (int i = 1; i <= nums.length; i++) {
			if (!set.contains(i)) {
				res[1] = i;
				break;
			}
		}
		return res;
	}

	public static int numOfChangingKey(String s) {
		s = s.toLowerCase();
		int count = 0;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i - 1) != s.charAt(i)) {
				count++;
			}
		}
		return count;
	}

	public static int antBoundry(int[] nums) {
		int sum = 0;
		int count = 0;
		for (int num : nums) {
			sum += num;
			if (sum == 0) {
				count++;
			}
		}
		return count;
	}

	public static boolean isPowerOfTwo(int n) {
		if (n <= 0) {
			return false;
		}
		int btc = Integer.bitCount(n);
		if (btc == 1) {
			return true;
		}
		return false;
	}
	
	public static int missingNum(int[] nums) {
		int n=nums.length;
		int sum=n*(n+1)/2;
		for(int num:nums) {
			sum -=num;
		}
		return sum;
	}
	static Stack<Integer> stack= new Stack<Integer>();
	static Stack<Integer> stack2= new Stack<Integer>();
	public static void enqueue(int x) {
		while(!stack.isEmpty()) {
			stack2.push(stack.pop());
		}
		stack.push(x);
		while(!stack2.isEmpty()) {
			stack.push(stack2.pop());
		}
	}
	public int dqueue() {
		return stack.pop();
	}
	public int peek() {
		return stack.peek();
	}
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummy= new ListNode(-1);
		ListNode current = dummy;
		int carry =0;
		int sum=0;
		while(l1 !=null || l2 !=null || carry !=0) {
			sum =carry;
			if(l1!=null) {
				sum +=l1.data;
				l1= l1.next;
			}
			if(l2!=null) {
				sum +=l2.data;
				l2= l2.next;
			}
			carry =sum/10;
			current.next = new ListNode(sum %10);
			current =current.next;
		}
		return dummy.next;
		
	}
	public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
	  ListNode dummy = new ListNode(-1);
	  ListNode current =dummy;
	  int sum=0,carry=0;
	  while(l1!=null || l2 !=null || carry!=0 ) {
		  sum=carry;
		  if(l1!=null) {
			  sum +=l1.data;
			  l1=l1.next;
		  }
		  if(l2!=null) {
			  sum +=l2.data;
			  l2=l2.next;
		  }
		  carry =sum/10;
		  current.next = new ListNode(sum %10);
		  current =current.next;
	  }
	  return dummy.next;
	}
	

	public static void main(String[] args) {
		int[] nums= {3,0,1};
		System.out.println(missingNum(nums));
	}
}
