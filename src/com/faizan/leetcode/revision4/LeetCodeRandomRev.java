package com.faizan.leetcode.revision4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCodeRandomRev {

	public static ArrayList<ArrayList<Integer>> arrayDiff(int[] nums1, int[] nums2) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		list.add(checkDiff(nums1, nums2));
		list.add(checkDiff(nums2, nums1));
		return list;

	}

	private static ArrayList<Integer> checkDiff(int[] nums1, int[] nums2) {
		HashSet<Integer> set = new HashSet<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int num : nums2) {
			set.add(num);
		}
		for (int num : nums1) {
			if (!set.contains(num)) {
				list.add(num);
			}
		}
		return list;
	}

	public static int ballons(String text) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('b', 0);
		map.put('a', 0);
		map.put('l', 0);
		map.put('o', 0);
		map.put('n', 0);
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) + 1);
			} else {
				map.put(ch, map.get(ch));
			}
		}
		int min = map.get('b');
		min = Math.min(min, map.get('a'));
		min = Math.min(min, map.get('a'));
		min = Math.min(min, map.get('l') / 2);
		min = Math.min(min, map.get('o') / 2);
		min = Math.min(min, map.get('n'));

		return min;

	}

	public static int findChamp(int[][] teams) {
		int count = 0;
		int r = teams.length;
		int c = teams[0].length;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (teams[i][j] == 1) {
					count++;
				}
				if (count == r - 1) {
					return i;
				}
			}
		}
		return -1;
	}

	public static boolean checkStringEqual(String[] word1, String[] word2) {
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

	public static int elementAppear25Per(int[] arr) {
		int limit = arr.length / 4;
		for (int i = limit; i < arr.length - limit; i++) {
			if (arr[i] == arr[i + 1]) {
				return arr[i];
			}
		}
		return -1;
	}

	public static int specailPos(int[][] mat) {
		int row = mat.length;
		int col = mat[0].length;
		int count = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (mat[i][j] == 0) {
					continue;
				}
				boolean flag = false;
				for (int r = 0; r < row; r++) {
					if (r != i && mat[r][j] == 1) {
						flag = true;
						break;
					}
				}
				for (int c = 0; c < col; c++) {
					if (c != j && mat[i][c] == 1) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					count++;
				}
			}
		}
		return count;
	}

	public static boolean isSequence(String s, String t) {
		int index = 0;

		for (int i = 0; i < t.length(); i++) {
			if (s.charAt(index) == t.charAt(i)) {
				index++;
			}
			if (s.length() == index) {
				return true;
			}
		}
		return false;
	}

	public static boolean isSequnece1(String s, String t) {
		int i = 0, j = 0;

		while (i < t.length() && j < t.length()) {
			if (s.charAt(i) == t.charAt(j)) {
				i++;
			}
			j++;
		}
		return i == s.length();
	}

	public static boolean canPlantFlower(int[] flower, int n) {
		int count = 0;
		for (int i = 0; i < flower.length; i++) {
			if (flower[i] == 0) {
				if ((i == 0 || flower[i - 1] == 0) && (i == flower.length - 1 || flower[i + 1] == 0)) {
					System.out.println(count);
					if (count == n) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static int removeElement(int[] nums, int k) {
		int len = nums.length;
		int i = 0;
		while (i < len) {
			if (nums[i] == k) {
				nums[i] = nums[len - 1];
				len--;
			} else {
				i++;
			}
		}
		return len;

	}

	public static int removeElement1(int[] nums, int k) {
		int index = 0;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != k) {
				nums[index] = nums[i]; // move non-k elements forward
				index++;
			}
		}
		return index;
	}

	public static String gcd(String str1, String str2) {
		// Check if valid gcd string exists
		if (!(str1 + str2).equals(str2 + str1)) {
			return "";
		}

		return str1.substring(0, gcdUtl(str1.length(), str2.length()));
	}

	private static int gcdUtl(int length, int length2) {
		if (length2 == 0) {
			return length;
		}

		return gcdUtl(length2, length % length2);

	}

	public static String destCity(String[][] paths) {
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
		int leftSum = 0, sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
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

	public static boolean uniueOccurence(int[] arr) {
		Map<Integer, Long> map = Arrays.stream(arr).boxed()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		return map.size() == new HashSet<>(map.values()).size();
	}

	public static String reverseVowelsString(String s) {
		char[] words = s.toCharArray();
		int start = 0, end = words.length - 1;
		String vowels = "AEIOUaeiuo";
		while (start < end) {
			while (start < end && vowels.indexOf(words[start]) == -1) {
				start++;
			}
			while (start < end && vowels.indexOf(words[end]) == -1) {
				end--;
			}
			char temp = words[start];
			words[start] = words[end];
			words[end] = temp;
			start++;
			end--;
		}

		return new String(words);
	}

	public static ArrayList<Integer> findWords(String[] words, char ch) {
		ArrayList<Integer> list = new ArrayList<Integer>();
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

	public static int[] findMissingRepeated(int[][] grid) {
		int n = grid.length;
		int a = 0, b = 0;
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (set.contains(grid[i][j])) {
					a = grid[i][j];
				}
				set.add(grid[i][j]);
			}
		}
		for (int i = 1; i <= n * n; i++) {
			if (!set.contains(i)) {
				b = i;
				break;
			}
		}
		return new int[] { a, b };
	}

	public static int maxNumberWords(String[] sentence) {
		int max = 0;
		for (String word : sentence) {
			String[] wo = word.split(" ");
			max = Math.max(max, wo.length);
		}
		return max;
	}

	public static String removeTrailingZero(String num) {
		int count = 0;
		for (int i = num.length() - 1; i >= 0; i--) {
			if (num.charAt(i) == '0') {
				count++;
			} else {
				break;
			}
		}
		return num.substring(0, num.length() - count);
	}

	public static int percentageLetter(String s, char ch) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ch) {
				count++;
			}
		}
		return (count * 100) / s.length();
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

	public static int buyChoclates(int[] prices, int money) {
		Arrays.sort(prices);
		return money - (prices[0] + prices[1]);
	}

	public static int maxScore(String s) {
		int oneCount = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				oneCount++;
			}
		}
		int zeroCount = 0, maxScore = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				oneCount--;
			} else {
				zeroCount++;
			}
			maxScore = Math.max(maxScore, oneCount + zeroCount);
		}
		return maxScore;

	}

	public static double maxSubarray(int[] nums, int k) {
		int maxSum = 0;
		for (int i = 0; i < nums.length - k; i++) {
			int currentSum = 0;
			for (int j = i; j < k + i; j++) {
				currentSum += nums[j];
			}
			maxSum = Math.max(maxSum, currentSum);
		}
		double res = maxSum / (double) k;
		return res;
	}

	public static boolean pathCross(String path) {
		HashSet<String> set = new HashSet<String>();
		int x = 0, y = 0;
		set.add(x + "-" + y);

		for (int i = 0; i < path.length(); i++) {
			char ch = path.charAt(i);
			if (ch == 'N') {
				y++;
			} else if (ch == 'S') {
				y--;
			} else if (ch == 'E') {
				x++;
			} else if (ch == 'W') {
				x--;
			}
			if (set.contains(x + "-" + y)) {
				return true;
			}
			set.add(x + "-" + y);
		}
		return false;
	}

	public static int minChanges(String s) {
		int startWith1 = 0;// 101
		int startWith0 = 0;// 010
		for (int i = 0; i < s.length(); i++) {
			if (i % 2 == 0) { // even index → expected = '1'
				if (s.charAt(i) == '0') {
					startWith1++; // mismatch
				}
			} else { // odd index → expected = '0'
				if (s.charAt(i) == '1') {
					startWith1++; // mismatch
				}
			}

		}
		return Math.min(startWith1, startWith0);
	}

	public static int minChanges1(String s) {
		int startWith1 = 0; // Pattern: 101010...
		int startWith0 = 0; // Pattern: 010101...

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			// Pattern: 010101...
			if (i % 2 == 0) { // even index → expected '0'
				if (c == '1')
					startWith0++; // mismatch
			} else { // odd index → expected '1'
				if (c == '0')
					startWith0++; // mismatch
			}

			// Pattern: 101010...
			if (i % 2 == 0) { // even index → expected '1'
				if (c == '0')
					startWith1++; // mismatch
			} else { // odd index → expected '0'
				if (c == '1')
					startWith1++; // mismatch
			}
		}

		return Math.min(startWith0, startWith1);
	}

	public static boolean isMonolethic(int[] nums) {
		boolean isDec = true;
		boolean isInc = true;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] < nums[i]) {
				isDec = false;
			} else if (nums[i - 1] > nums[i]) {
				isInc = false;
			}
			if (isDec || isInc) {
				return true;
			}
		}
		return isDec;
	}

	public static int[] minNumberGmae(int[] nums) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		int[] res = new int[nums.length];
		for (int num : nums) {
			queue.add(num);
		}
		int j = 0;
		while (!queue.isEmpty()) {
			int alice = queue.poll();
			res[j++] = alice;
			if (!queue.isEmpty()) {
				int bob = queue.poll();
				res[j++] = bob;
			}
		}
		return res;
	}

	public static int incRemovableSubArray(int[] nums) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i; j < nums.length; j++) {
				if (isIncreasingArray(nums, i, j)) {
					count++;
				}
			}
		}
		return count;
	}

	private static boolean isIncreasingArray(int[] nums, int start, int end) {
		int prev = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			if (i <= end && i >= start) {
				continue;
			}
			if (nums[i] <= prev) {
				return false;
			}
		}
		return true;
	}

	public static List<String> stringMatching(String[] words) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words.length; j++) {
				String word1 = words[i];
				String word2 = words[j];
				if (word1.length() >= word2.length()) {
					continue;
				}
				if (isSubstring(word1, word2)) {
					list.add(words[i]);
				}
			}
		}
		return list;
	}

	private static boolean isSubstring(String word1, String word2) {
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
			if (i % words.length != 0) {
				return false;
			}
		}
		return true;

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

	public static boolean checkTrailingZero(int[] nums) {
		int count = 0;
		for (int num : nums) {
			if ((num & 1) == 0) {
				count++;
			}
			if (count >= 2) {
				return true;
			}
		}
		return false;
	}

	public static int assignCokie(int[] g, int[] s) {
		Arrays.sort(g);
		Arrays.sort(s);
		int children = 0;
		int cockie = 0;
		while (cockie < s.length && children < g.length) {
			if (g[children] <= s[cockie]) {
				children++;
			}
			cockie++;

		}
		return children;
	}

	public static boolean stringAlike(String s) {
		int n = s.length();
		int firstHalf = countVowles(s.substring(0, n / 2));
		int secondHalf = countVowles(s.substring(n / 2, n));
		return firstHalf == secondHalf;
	}

	private static int countVowles(String str) {
		String vowles = "AEIOUaeiou";
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (vowles.indexOf(str.charAt(i)) != -1) {
				count++;
			}
		}

		return count;
	}

	public static int climbingStairs(int n) {
		int a = 0, b = 1;
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			ans = a + b;
			a = b;
			b = ans;
		}
		return ans;
	}

	public static int smallestMissingNumber(int[] nums) {
		HashSet<Integer> set = new HashSet<Integer>();
		for (int num : nums) {
			set.add(num);
		}
		int maxSum = nums[0], sum = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] - nums[i - 1] == 1) {
				sum += nums[i];
			} else {
				sum = 0;
			}
			maxSum = Math.max(maxSum, sum);
		}
		while (set.contains(maxSum)) {
			maxSum++;
		}
		return maxSum;
	}
	
	public static int maxArea(int[][] dimensions) {
		int maArea=0;
		double maxDiagonal=0;
		for(int[] dimension:dimensions) {
			int l=dimension[0];
			int w=dimension[1];
			double diagonal=Math.sqrt(l*l +w*w);
			int area=l*w;
			if(diagonal>maxDiagonal) {
				diagonal=maxDiagonal;
				maArea=area;
				
			}else if(diagonal==maxDiagonal) {
				maArea=Math.max(area, maArea);
			}
		}
		return maArea;
	}
	
	public static int maxElements(int[] nums) {
		int count=0;
		Map<Integer, Long> map = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		int max = map.entrySet().stream().map(entry->entry.getValue()).mapToInt(num->num.intValue()).max().getAsInt();
		for(int num:nums) {
			if(map.get(num).intValue()==max) {
				count++;
			}
		}
		return count;
	}
	public static int minCost(int[] nums) {
		if(nums.length<3) {
			return Arrays.stream(nums).sum();
		}
		int first=nums[0];
		int firstMin=Integer.MAX_VALUE;
		int secondMin=Integer.MAX_VALUE;
		 for(int i=1;i<nums.length;i++) {
			if(firstMin>nums[i]) {
				firstMin=nums[i];
			}else if(secondMin>nums[i]) {
				secondMin=nums[i];
			}
		}
		return first+firstMin+secondMin;
	}
	 public static int[] setMismatch(int[] nums) {
		 HashSet<Integer> set = new HashSet<Integer>();
		 int[] ans= new int[2];
		 for(int num:nums) {
			 if(set.contains(num)) {
				 ans[0]=num;
			 }else {
				 set.add(num);
			 }
		 }
		 for(int i=1;i<=nums.length;i++) {
			 if(!set.contains(i)) {
				 ans[1]=i;
				 break;
			 }
		 }
		return ans;
	 }
	 
	 public static int numberOfChangingKey(String s) {
		 s=s.toLowerCase();
		 int count=0;
		 for(int i=1;i<s.length();i++) {
			 if(s.charAt(i)!= s.charAt(i-1)) {
				 count++;
			 }
		 }
		return count;
	 }
	 
	 public static int antBoundry(int[] nums) {
		 int count=0;
		 int sum=0;
		 for(int num:nums) {
			 sum +=num;
			 if(sum==0) {
				 count++;
			 }
		 }
		return count;
	 }
	 public static boolean isPowerOfTwo(int n) {
		 if(n<0) {
			 return false;
		 }
		 int bitCount = Integer.bitCount(n);
		 if(bitCount==1) {
			 return true;
		 }
			 
		return false;
	 }
	 public static int missingNumber(int[] nums) {
		 int n=nums.length;
		 int sum =n*(n+1)/2;
		 for(int i=0;i<nums.length;i++) {
			 sum -=nums[i];
		 }
		return sum;
	 }

	public static void main(String[] args) {
	   int[] nums= {3,0,1};
	   System.out.println(missingNumber(nums));
	}

}
