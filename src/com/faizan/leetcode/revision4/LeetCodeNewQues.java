package com.faizan.leetcode.revision4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCodeNewQues {
	public static String maxOddBinaryNumber(String s) {
		int oneCount = 0;
		int zeroCount = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				oneCount++;
			} else {
				zeroCount++;
			}
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < oneCount - 1; i++) {
			builder.append("1");
		}
		for (int i = 0; i < zeroCount; i++) {
			builder.append("0");
		}
		builder.append("1");
		return builder.toString();
	}

	public static int[] intersection(int[] nums1, int[] nums2) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int num : nums1) {
			map.put(num, 1);

		}
		for (int num : nums2) {
			if (map.containsKey(num) && map.get(num) == 1) {
				list.add(num);
				map.put(num, 0);
			}
		}
		int[] res = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			res[i] = list.get(i);
		}
		return res;
	}

	public static int[] intersection1(int[] nums1, int[] nums2) {
		HashSet<Integer> set = new HashSet<>();
		HashSet<Integer> resSet = new HashSet<>();

		for (int num : nums1)
			set.add(num);

		for (int num : nums2) {
			if (set.contains(num)) {
				resSet.add(num);
			}
		}

		int[] res = new int[resSet.size()];
		int i = 0;

		for (int num : resSet) {
			res[i++] = num;
		}

		return res;
	}

	public static int minNumberOfBoxes(int[] apple, int[] boxes) {
		int sum = 0;
		for (int ap : apple) {
			sum += ap;
		}

		Arrays.sort(boxes); // ascending

		int count = 0;

		for (int i = boxes.length - 1; i >= 0; i--) {
			sum -= boxes[i];
			count++;

			if (sum <= 0) {
				break;
			}
		}

		return count;
	}

	public static int pivotInteger(int n) {
		int left = 1, right = n;
		int leftSum = 1, rightSum = n;

		while (left < right) {
			if (leftSum < rightSum) {
				left++;
				leftSum += left;
			} else {
				right--;
				rightSum += right;
			}

			if (leftSum == rightSum) {
				return left; // pivot
			}
		}

		return -1;
	}

	public static int pivotInteger1(int n) {

		int totalSum = n * (n + 1) / 2;

		int leftSum = 0;

		for (int i = 1; i <= n; i++) {

			leftSum += i;

			int rightSum = totalSum - leftSum + i;

			if (leftSum == rightSum) {
				return i;
			}
		}

		return -1;
	}

	public static int sumOfEncryptedInt(int[] nums) {
		int totalSum = 0;

		for (int num : nums) {
			int temp = num;
			int maxDigit = 0;
			int digits = 0;

			while (temp > 0) {
				int rem = temp % 10;
				maxDigit = Math.max(maxDigit, rem);
				digits++;
				temp /= 10;
			}
			int once = 0;
			for (int i = 0; i < digits; i++) {
				once = once * 10 + 1; // 111...
			}
			totalSum += maxDigit * once;
		}
		return totalSum;
	}

	public static int sumOfEncryptedInt1(int[] nums) {

		int sum = 0;

		for (int num : nums) {

			int temp = num;

			int maxDigit = 0;

			int digits = 0;

			while (temp > 0) {

				maxDigit = Math.max(maxDigit, temp % 10);

				temp /= 10;

				digits++;
			}

			int encrypted = 0;

			for (int i = 0; i < digits; i++) {
				encrypted = encrypted * 10 + maxDigit;
			}

			sum += encrypted;
		}

		return sum;
	}

	public static boolean stringExistence(String s) {
		StringBuilder builder = new StringBuilder(s);
		String revString = builder.reverse().toString();
		ArrayList<String> revList = new ArrayList<String>();
		for (int i = 0; i <= revString.length() - 2; i++) {
			String revStr = revString.substring(i, i + 2);
			revList.add(revStr);
		}

		for (int i = 0; i <= s.length() - 2; i++) {
			String subStr = s.substring(i, i + 2);
			if (revList.contains(subStr)) {
				return true;
			}
		}
		return false;
	}

	public int maximumLengthSubstring(String s) {
		int[] freq = new int[26];
		int left = 0;
		int maxLen = 0;

		for (int right = 0; right < s.length(); right++) {
			char ch = s.charAt(right);
			freq[ch - 'a']++;

			// shrink window if any char occurs more than 2 times
			while (freq[ch - 'a'] > 2) {
				freq[s.charAt(left) - 'a']--;
				left++;
			}

			maxLen = Math.max(maxLen, right - left + 1);
		}

		return maxLen;
	}

	public static boolean isIsomorphic(String s, String t) {
		return checkIso(s).equals(checkIso(t));
	}

	private static String checkIso(String s) {
		StringBuilder builder = new StringBuilder();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (!map.containsKey(ch)) {
				map.put(ch, i);
			}
			builder.append(map.get(ch)).append("-");
		}
		return builder.toString();
	}

	public static boolean isIsomorphicOptimized(String s, String t) {
		if (s.length() != t.length())
			return false;

		HashMap<Character, Character> mapST = new HashMap<>();
		HashMap<Character, Character> mapTS = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {
			char c1 = s.charAt(i);
			char c2 = t.charAt(i);

			if (mapST.containsKey(c1) && mapST.get(c1) != c2)
				return false;
			if (mapTS.containsKey(c2) && mapTS.get(c2) != c1)
				return false;

			mapST.put(c1, c2);
			mapTS.put(c2, c1);
		}

		return true;
	}

	public static int maxDepth(String s) {
		int max = 0, openBrackets = 0;

		for (char ch : s.toCharArray()) {
			if (ch == '(') {
				openBrackets++;
				max = Math.max(max, openBrackets); // update here
			} else if (ch == ')') {
				openBrackets--;
			}
		}
		return max;
	}

	public static String makeStringGreat(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			// if (!stack.isEmpty() && Math.abs(stack.peek() - s.charAt(i)) == 32) { //
			// Character.toLowerCase(a) ==
			// Character.toLowerCase(b)
			if (Character.toLowerCase(stack.peek()) == Character.toLowerCase(s.charAt(i))
					&& stack.peek() != s.charAt(i)) {
				stack.pop();
			} else {
				stack.push(s.charAt(i));
			}
		}
		StringBuilder builder = new StringBuilder();
		while (!stack.isEmpty()) {
			builder.append(stack.pop());
		}

		return builder.reverse().toString();
	}

	public static int timeRequiredToBuy(int[] tickets, int k) {
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < tickets.length; i++) {
			queue.add(i);
		}
		int seconds = 0;
		while (!queue.isEmpty()) {
			seconds++;
			int person = queue.poll();
			if (tickets[person] >= 1) {
				tickets[person]--;
			}
			if (person == k && tickets[person] == 0) {
				break;
			}
			if (person != k && tickets[person] == 0) {
				continue;
			}
			queue.add(person);
		}
		return seconds;
	}

	public static int timeRequiredToBuyOp(int[] tickets, int k) {
		int time = 0;

		for (int i = 0; i < tickets.length; i++) {
			if (i <= k) {
				time += Math.min(tickets[i], tickets[k]);
			} else {
				time += Math.min(tickets[i], tickets[k] - 1);
			}
		}

		return time;
	}

	public static boolean canAliceWin(int[] nums) {
		int singleDigitSum = 0, doubelDigitSum = 0;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < 10) {
				singleDigitSum += nums[i];
			} else {
				doubelDigitSum += nums[i];
			}
		}
		return singleDigitSum != doubelDigitSum;
	}

	public static int countSenior(String[] details) {
		int count = 0;
		for (int i = 0; i < details.length; i++) {
			int age = Integer.parseInt(details[i].substring(11, 13));
			if (age > 60) {
				count++;
			}
		}
		return count;
	}

	public static boolean canBeEqual(int[] taget, int[] arr) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < taget.length; i++) {
			map.put(taget[i], map.getOrDefault(taget[i], 0) + 1);
		}
		for (int i = 0; i < arr.length; i++) {
			if (!map.containsKey(arr[i])) {
				return false;
			}
			map.put(arr[i], map.get(arr[i]) - 1);
			if (map.get(arr[i]) == 0) {
				map.remove(arr[i]);
			}
		}
		return map.size() == 0;
	}

	public static int winnerPlayerCount(int n, int[][] pick) {
		Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
		for (int[] p : pick) {
			int player = p[0];
			int color = p[1];
			if (!map.containsKey(player)) {
				map.put(player, new HashMap<>());
			}
			Map<Integer, Integer> playerColors = map.get(player);
			playerColors.put(color, playerColors.getOrDefault(color, 0) + 1);
		}
		int winners = 0;
		for (int i = 0; i < n; i++) {
			boolean won = false;
			Map<Integer, Integer> playerColors = map.getOrDefault(i, new HashMap<>());
			for (int count : playerColors.values()) {
				if (count > i) {
					won = true;
					break;
				}
			}
			if (won) {
				winners++;
			}

		}
		return winners;
	}

	public static int findPositionOfSanke(int n, List<String> commands) {
		int pos = 0;
		for (String command : commands) {
			if (command.equals("RIGHT")) {
				pos += 1;
			}
			if (command.equals("LEFT")) {
				pos -= 1;
			}

			if (command.equals("UP")) {
				pos -= n;
			}
			if (command.equals("DOWN")) {
				pos += n;
			}
		}
		return pos;
	}

	public static int getLucky(String s, int k) {
		String numericString = "";
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			numericString += Integer.toString((ch - 'a' + 1));
		}
		while (k-- > 0) {
			int sum = 0;
			for (char digit : numericString.toCharArray()) {
				sum += digit - '0';
			}
			numericString = Integer.toString(sum);
		}
		return Integer.parseInt(numericString);
	}

	public static int getLuckyOpt(String s, int k) {
		int sum = 0;

		// Step 1: convert and directly sum digits
		for (char ch : s.toCharArray()) {
			int val = ch - 'a' + 1;

			while (val > 0) {
				sum += val % 10;
				val /= 10;
			}
		}

		// Step 2: repeat k-1 times
		while (k-- > 1) {
			int temp = 0;

			while (sum > 0) {
				temp += sum % 10;
				sum /= 10;
			}

			sum = temp;
		}

		return sum;
	}

	public static int countConsistentString(String allowed, String[] words) {
		HashSet<Character> set = new HashSet<Character>();
		for (char ch : allowed.toCharArray()) {
			set.add(ch);
		}
		int count = 0;
		for (String word : words) {
			boolean isConsistent = true;
			for (int i = 0; i < word.length(); i++) {
				if (!set.contains(word.charAt(i))) {
					isConsistent = false;
					break;
				}
			}
			if (isConsistent) {
				count++;
			}
		}
		return count;
	}

	public static String[] uncommonFromSentences(String s1, String s2) {
		String s = s1 + " " + s2;
		String[] words = s.split(" ");
		Map<String, Long> map = Arrays.stream(words)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		int index = 0;
		ArrayList<String> list = new ArrayList<String>();
		while (index < words.length) {
			if (map.get(words[index]) == 1L) {
				list.add(words[index]);
			}
			index++;
		}
		String[] result = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}

	public static int[] getSneakyNumbers(int[] nums) {
		int[] sneakyNumber = new int[2];
		int[] count = new int[nums.length];
		int index = 0;
		for (int i = 0; i < nums.length; i++) {
			count[nums[i]]++;
			if (count[nums[i]] == 2) {
				sneakyNumber[index] = nums[i];
				index++;
			}
			if (index == 2) {
				break;
			}
		}
		return sneakyNumber;
	}

	public static int[] getSneakyNumbersOpt(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		int[] result = new int[2];
		int index = 0;

		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);

			if (map.get(num) == 2) {
				result[index++] = num;
			}

			if (index == 2)
				break;
		}

		return result;
	}

	public static int maxConsecutiveOnnes(int[] nums) {
		int maxOnes = 0, one = 0;
		for (int num : nums) {
			if (num == 1) {
				one++;
				maxOnes = Math.max(maxOnes, one);
			} else {
				one = 0;
			}

		}
		return maxOnes;
	}

	public static ArrayList<Integer> stableMountain(int[] heights, int thresold) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < heights.length; i++) {
			if (heights[i - 1] > thresold) {
				list.add(heights[i]);
			}
		}
		return list;
	}

	public static boolean checkIfExist(int[] arr) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++) {
			int doub = 2 * arr[i];
			int half = arr[i] / 2;
			if (map.containsKey(doub) || (arr[i] % 2 == 0) && map.containsKey(half)) {
				return true;
			}
		}
		return false;
	}

	public static int prefixWordExist(String sentence, String searchWord) {
		int count = 0;
		String[] words = sentence.split(" ");
		for (String word : words) {
			if (word.startsWith(searchWord)) {
				count++;
			}
		}
		return count;

	}

	public static int countPartitionEvenSum(int[] nums) {
		int count = 0;
		int totalSum = 0;
		for (int num : nums) {
			totalSum += num;
		}
		int leftSum = 0;
		for (int i = 0; i < nums.length - 1; i++) {

			leftSum += nums[i];
			if ((leftSum - (totalSum - leftSum)) % 2 == 0) {
				count++;
			}
		}
		return count;
	}

	public static int countPartitionsOpti(int[] nums) {
		int totalSum = 0;

		for (int num : nums) {
			totalSum += num;
		}

		if (totalSum % 2 != 0) {
			return 0;
		}

		return nums.length - 1; // all splits valid
	}

	public static boolean isArraySpecial(int[] nums) {
		if (nums.length == 1) {
			return true;
		}
		for (int i = 0; i < nums.length - 1; i++) {
			if ((nums[i] % 2) == (nums[i + 1] % 2)) {
				return false;
			}
		}
		return true;
	}

	public static boolean check(int[] nums) {
		int len = nums.length;
		int deviations = 0;
		for (int i = 0; i < len; i++) {
			if (i < len - 1 && nums[i] > nums[i + 1]) {
				deviations++;
			} else if (i == len - 1 && nums[len - 1] > nums[0]) {
				deviations++;
			}
		}
		return (deviations > 1) ? false : true;
	}

	public static int longestMonotonicSubarray(int[] nums) {
		int increasing = 1, decreasing = 1;
		int longest = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] < nums[i]) {
				increasing++;
				decreasing = 1;
			} else if (nums[i - 1] > nums[i]) {
				decreasing++;
				increasing = 1;
			} else {
				increasing = 1;
				decreasing = 1;
			}
			longest = Math.max(longest, Math.max(increasing, decreasing));
		}
		return longest;

	}

	public static int maxAscendingSubarray(int[] nums) {
		int sum = nums[0];
		int maxSum = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > nums[i - 1]) {
				sum += nums[i];
			} else {
				sum = nums[i];
			}
			maxSum = Math.max(maxSum, sum);
		}
		return maxSum;
	}

	public static String cleraDigits(String s) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);

			if (Character.isDigit(ch)) {
				if (builder.length() > 0) {
					builder.deleteCharAt(builder.length() - 1);
				}
			} else {
				builder.append(ch);
			}
		}
		return builder.toString();
	}

	public static int sumOfGoodNumbers(int[] nums, int k) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			boolean isGood = true;
			if (i - k >= 0 && nums[i] <= nums[i - k]) {
				isGood = false;
			}
			if (i + k < nums.length && nums[i] <= nums[i + k]) {
				isGood = false;
			}
			if (isGood) {
				sum += nums[i];
			}
		}
		return sum;
	}

	public static int[] applyOperations(int[] nums) {
		int[] res = new int[nums.length];
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == nums[i + 1]) {
				nums[i] = nums[i] * 2;
				nums[i + 1] = 0;
			}
		}
		int idx = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				res[idx++] = nums[i];
			}
		}
		return res;
	}

	public static int[] transformArray(int[] nums) {
		int evenCount = 0;
		for (int num : nums) {
			if (num % 2 == 0) {
				evenCount++;
			}
		}
		int[] res = new int[nums.length];
		int idx = nums.length - 1;
		while (evenCount != 0) {
			res[idx] = 1;
			evenCount--;
			idx--;
		}
		return res;

	}

	public static int[][] mergeArray(int[][] nums1, int[][] nums2) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int[] num : nums1) {
			map.put(num[0], num[1]);
		}
		for (int[] num : nums2) {
			map.put(num[0], map.getOrDefault(num[0], 0) + num[1]);
		}
		int[][] res = new int[map.size()][2];
		int i = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			res[i][0] = entry.getKey();
			res[i][1] = entry.getValue();
			i++;
		}
		return res;
	}

	public static int minimumRecolors(String blocks, int k) {
		int left = 0;
		int whiteCount = 0, numRecolors = blocks.length() + 1;
		for (int right = 0; right < blocks.length(); right++) {
			if (blocks.charAt(right) == 'W') {
				whiteCount++;
			}
			if (right - left + 1 == k) {
				numRecolors = Math.min(numRecolors, whiteCount);
				if (blocks.charAt(left) == 'W') {
					whiteCount--;
				}
				left++;
			}
		}
		return numRecolors;
	}

	public static void main(String[] args) {
		String blocks = "WBBWWBBWBW";
		System.out.println(minimumRecolors(blocks, 7));

	}

}
