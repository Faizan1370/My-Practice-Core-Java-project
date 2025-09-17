package com.faizan.leetcode.revision3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeetCodeRevision1 {

	public static boolean isPlaindrome(int num) {
		int sum = 0;
		int temp = num;
		while (num > 0) {
			int r = num % 10;
			sum = sum * 10 + r;
			num = num / 10;
		}
		System.out.println(sum);

		return sum == temp;
	}

	public static boolean isPlaindrome1(int num) {
		String str = num + "";
		int start = 0;
		int end = str.length() - 1;
		while (start < end) {
			if (str.charAt(start) != str.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}

	public static int romanToInt(String str) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);

		int result = 0;
		for (int i = 1; i < str.length(); i++) {
			if (map.get(str.charAt(i)) > map.get(str.charAt(i - 1))) {
				result -= map.get(str.charAt(i - 1));
			} else {
				result += map.get(str.charAt(i - 1));
			}
		}
		result += map.get(str.charAt(str.length() - 1));
		return result;
	}

	public static int romanToInt1(String str) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);

		int result = 0;
		int i, j;
		for (i = 0, j = 1; j < str.length(); i++, j++) {
			if (map.get(str.charAt(i)) >= map.get(str.charAt(j))) {
				result += map.get(str.charAt(i));
			} else {
				result -= map.get(str.charAt(i));
			}
		}

		result += map.get(str.charAt(i));
		return result;
	}

	public static boolean isValidParat(String str) {
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
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
		int start = 0, end = nums.length - 1, mid = 0;

		for (int i = 0; i < nums.length; i++) {
			mid = start + (end - start) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (target > nums[mid]) {
				start = start + 1;
			} else {
				end = end - 1;
			}
		}
		return start;
	}

	public static int[] mergerSortedArray(int[] nums, int[] nums2, int m, int n) {
		int p1 = m - 1;
		int p2 = n - 1;
		int p3 = nums.length - 1;
		while (p3 > 0) {
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

	public static int buySell(int nums[]) {
		int min = Integer.MAX_VALUE;
		int currentMax = 0, max = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < min) {
				min = nums[i];
			}
			currentMax = nums[i] - min;
			if (currentMax > max) {
				max = currentMax;
			}
		}
		return max;
	}

	public static boolean validPalidrmic(String s) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i)) || Character.isLetter(s.charAt(i))) {
				builder.append(s.charAt(i));
			}
		}
		return checkPlalin(builder.toString());
	}

	public static boolean checkPlalin(String s) {
		int start = 0;
		int end = s.length() - 1;
		while (start < end) {
			if (s.charAt(end) != s.charAt(start)) {
				return false;
			}
			start++;
			end--;
		}
		return true;

	}

	public static int singleNum(int[] nums) {
		int result = 0;
		for (int num : nums) {
			result ^= num;
		}
		return result;
	}

	public static int majorityElement(int[] nums) {
		int n = nums.length;
		Integer integer = Arrays.stream(nums).mapToObj(num -> (Integer) num)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(entry -> entry.getValue() > n / 2).map(entry -> entry.getKey()).findFirst().get();
		return integer;
	}

	public static boolean containDuplicate(int[] nums) {
		HashSet<Integer> hashSet = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (hashSet.contains(nums[i])) {
				return true;
			} else {
				hashSet.add(nums[i]);
			}
		}
		return false;
	}

	public static boolean containsDuplicate2(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {
			if (!map.containsKey(nums[i])) {
				map.put(nums[i], i);
			} else {
				int diff = Math.abs(map.get(nums[i]) - i);
				if (diff <= k) {
					return true;
				} else {
					map.put(nums[i], i);
				}
			}
		}
		return false;

	}

	public static boolean validAnagram(String str, String str1) {
		char[] charArray = str.toCharArray();
		char[] charArray2 = str1.toCharArray();
		if (charArray.length != charArray2.length) {
			return false;
		}
		Arrays.sort(charArray);
		Arrays.sort(charArray2);
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] != charArray2[i]) {
				return false;
			}
		}
		return true;
	}

	public static int[] rearangePosNeg(int[] array) {
		int j = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 0) {
				if (i != j) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
				j++;
			}
		}
		return array;
	}

	public static int[] countingBits(int n) {
		int[] ans = new int[n + 1];
		ans[0] = 0;
		for (int i = 1; i <= n; i++) {
			String binaryString = Integer.toBinaryString(i);
			ans[i] = count1s(binaryString);
		}
		return ans;
	}

	public static int count1s(String binaryStr) {
		int count = 0;
		for (int i = 0; i < binaryStr.length(); i++) {
			if (binaryStr.charAt(i) == '1') {
				count++;
			}
		}
		return count;
	}

	public static String reverseString(String s) {
		String result = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			result += s.charAt(i);
		}
		return result;
	}

	public static boolean ransomeNode(String ransomeNote, String magzine) {
		Map<Character, Long> collect = magzine.chars().mapToObj(c -> (char) c)
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

	public static char findDif(String s, String t) {
		int t_sum = 0;
		int s_sum = 0;

		for (int i = 0; i < t.length(); i++) {
			t_sum += t.charAt(i);
		}
		for (int i = 0; i < s.length(); i++) {
			s_sum += s.charAt(i);
		}
		return (char) (t_sum - s_sum);
	}

	// we can also solve using map with count freqncy
	public static char findDifByList(String s, String t) {
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

	public static int consecutiveOnce(int[] nums) {
		int currentMax = 0, max = 0;
		for (int num : nums) {
			if (num == 1) {
				currentMax = currentMax + 1;
				if (currentMax > max) {
					max = currentMax;
				}
			} else {
				currentMax = 0;
			}
		}
		return max;
	}

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
		return count == s.length();
	}

	public static boolean allUpper(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isUpperCase(s.charAt(i))) {
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
		StringBuilder builder = new StringBuilder();
		String[] words = s.split(" ");
		for (String word : words) {
			builder.append(reverse(word)).append(" ");
		}
		return builder.toString().trim();
	}

	public static String reverse(String s) {
		String result = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			result += s.charAt(i);
		}
		return result;

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

	public static boolean mountainArray(int[] array) {
		if (array.length <= 3) {
			return false;
		}
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
		int left = 0, right = nums.length - 1;
		int[] ans = new int[nums.length];
		for (int i = nums.length - 1; i >= 0; i--) {
			int element1 = nums[left] * nums[left];
			int element2 = nums[right] * nums[right];
			if (element1 > element2) {
				ans[i] = element1;
				left++;
			} else {
				ans[i] = element2;
				right--;
			}
		}
		return ans;
	}

	public static int lastStoneWieght(int[] stones) {
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

	public static String removeAdjacent(String s) {
		Stack<Character> characters = new Stack<Character>();

		for (int i = 0; i < s.length(); i++) {
			if (characters.isEmpty()) {
				characters.push(s.charAt(i));
			} else {
				if (characters.peek() == s.charAt(i)) {
					characters.pop();
				} else {
					characters.push(s.charAt(i));
				}
			}

		}
		StringBuilder builder = new StringBuilder();
		if (!characters.isEmpty()) {
			for (char c : characters) {
				builder.append(c);
			}
		}
		return builder.toString();
	}

	public static int balancedString(String str) {
		int l_count = 0;
		int r_count = 0;
		int result = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'L') {
				l_count++;
			}
			if (str.charAt(i) == 'R') {
				r_count++;
			}
			if (l_count == r_count) {
				result++;
			}
		}
		return result;
	}

	public static int evenDigits(int[] nums) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (countDigits(nums[i]) % 2 == 0) {
				count++;
			}
		}
		return count;
	}

	public static int countDigits(int num) {
		int count = 0;
		while (num > 0) {
			num = num / 10;
			count++;
		}
		return count;
	}

	public static boolean[] kidsCandies(int[] candies, int extraCandie) {
		int max = Integer.MIN_VALUE;
		boolean[] result = new boolean[candies.length];
		for (int candie : candies) {
			if (candie > max) {
				max = candie;
			}
		}
		for (int i = 0; i < candies.length; i++) {
			if ((candies[i] + extraCandie) > max) {
				result[i] = true;
			}
		}
		return result;
	}

	public static int maxProduct(int[] nums) {
		int max = Integer.MIN_VALUE;
		int secondMax = 0;
		for (int num : nums) {
			if (num > max) {
				secondMax = max;
				max = num;
			} else if (num > secondMax) {
				secondMax = num;
			}
		}
		return (max - 1) * (secondMax - 1);
	}

	public static int[] runningSum(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			nums[i] = sum;
		}
		return nums;
	}

	public static double avgSal(int[] salaries) {
		int max = Arrays.stream(salaries).max().getAsInt();
		int min = Arrays.stream(salaries).min().getAsInt();
		double avg = 0.0;
		int sum = 0;
		for (int num : salaries) {
			sum += num;
		}
		avg = (sum - min - max) / (salaries.length - 2);
		return avg;

	}

	public static String reformatDate(String date) {
		String[] dates = date.split(" ");
		String year = dates[dates.length - 1];
		String day = "";
		String month = dates[1];
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Jan", "01");
		map.put("Feb", "02");
		for (int i = 0; i < dates[0].length(); i++) {
			if (Character.isDigit(dates[0].charAt(i))) {
				day += dates[0].charAt(i);
			}
		}
		StringBuilder builder = new StringBuilder();
		if (day.length() == 1) {
			day = "0" + day;
		}
		builder.append(year).append("-").append(map.get(month)).append("-").append(day);
		return builder.toString();
	}

	public static String shuflingString(String s, int[] indices) {
		HashMap<Integer, Character> map = new HashMap<Integer, Character>();

		for (int i = 0; i < s.length(); i++) {
			map.put(indices[i], s.charAt(i));
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			builder.append(map.get(i));
		}

		return builder.toString();
	}

	public static String shufflingString1(String s, int[] nums) {
		char[] result = new char[s.length()];

		for (int i = 0; i < nums.length; i++) {
			result[nums[i]] = s.charAt(i);
		}

		return new String(result);
	}

	public static boolean threeConsecutiveOdd(int[] arr) {
		int count = 0;
		for (int num : arr) {
			if (num % 2 != 0) {
				count++;
				if (count == 3) {
					return true;
				}
			} else {
				count = 0;
			}
		}
		return false;
	}

	public static double meanArray(int[] arr) {
		Arrays.sort(arr);
		int limit = (arr.length * 5) / 100;
		int sum = 0;
		for (int i = limit; i < arr.length - limit; i++) {
			sum += arr[i];
		}

		return sum / (arr.length - (2 * limit));

	}

	public static int[] sortArrayFrq(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i]) + 1);
			} else {
				map.put(nums[i], 1);
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int num : nums) {
			list.add(num);
		}
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (map.get(o1) != map.get(o2)) {
					return map.get(o1) - map.get(o2);
				} else {
					return o2 - o1;
				}
			}
		});
		int[] result = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			result[i] = list.get(i);
		}

		return result;
	}

	public static String goalParser(String str) {
		StringBuilder builder = new StringBuilder();
		int i = 0;
		while (i < str.length()) {
			if (str.charAt(i) == 'G') {
				builder.append("G");
				i = i + 1;
			} else if (str.charAt(i) == '(' && str.charAt(i + 1) == ')') {
				builder.append("o");
				i = i + 2;
			} else {
				builder.append("al");
				i = i + 4;
			}
		}
		return builder.toString();
	}

	public static int sumOfDistinct(int[] nums) {
		Integer reduce = Arrays.stream(nums).mapToObj(num -> (Integer) num)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(entry -> entry.getValue() == 1L).map(entry -> entry.getKey()).reduce(0, (a, b) -> a + b);
		return reduce;
	}

	public static String mergeStringAlter(String word1, String word2) {

		String larggerString = "";
		StringBuilder builder = new StringBuilder();
		int min = Math.min(word1.length(), word2.length());
		if (word1.length() >= word2.length()) {
			larggerString = word1;
		} else {
			larggerString = word2;
		}
		for (int i = 0; i < min; i++) {
			builder.append(word1.charAt(i)).append(word2.charAt(i));
		}
		builder.append(larggerString.substring(min));
		return builder.toString();

	}

	public static String mergerALterNative(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
		int min = Math.min(len1, len2);
		String longerString = "";
		if (min == len1) {
			longerString = word2;
		}
		if (min == len2) {
			longerString = word1;
		}
		char[] charArray = word1.toCharArray();
		char[] charArray2 = word2.toCharArray();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < min; i++) {
			builder.append(charArray[i]);
			builder.append(charArray2[i]);
		}
		builder.append(longerString.substring(min));
		return builder.toString();
	}

	public static int seocndLargestDigit(String str) {
		int larget = 0;
		int seondLargest = 0;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				if (larget < Character.getNumericValue(str.charAt(i))) {
					seondLargest = larget;
					larget = Character.getNumericValue(str.charAt(i));
				}
			}
		}
		return seondLargest;
	}
	
	public static String sentenceList(String s,int k) {
		String[] words = s.split(" ");
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<k;i++) {
			builder.append(words[i]).append(" ");
		}
		return builder.toString().trim();
	}
	
	public static int signOfProductt(int[] nums) {
		int countNeg=0;
		for(int num:nums) {
			if(num<0) {
				countNeg++;
			}
		}
		if(countNeg%2==0) {
			return 1;
		}else {
			return -1;
		}
	}
	
	public static boolean checkPana(String str) {
		HashSet<Character> set = new HashSet<Character>();
		
		for(int i=0;i<str.length();i++) {
			set.add(str.charAt(i));
		}
		
		return set.size()==26;
	}
	
	public static String sortingSentence(String s) {
		String[] words = s.split(" ");
		HashMap<Integer,String> hashMap = new HashMap<Integer, String>();
		for(String word:words) {
			hashMap.put(Character.getNumericValue(word.charAt(word.length()-1)), word.substring(0,word.length()-1));
		}
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<words.length;i++) {
			builder.append(hashMap.get(i+1)).append(" ");
		}
		
		return builder.toString().trim();
	}
	
	public static int[] arrayConcat(int[] array) {
		int[] result= new int[2*array.length];
		for(int i=0;i<array.length;i++) {
			result[i]=array[i];
			result[array.length+i]=array[i];
		}
		return result;
	}
	
	public static boolean checkSameOccurence(String s) {
		 Set<Long> collect = s.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet()
		.stream()
		.map(entry->entry.getValue()).collect(Collectors.toSet());
		 
		 return collect.size()==1;
		
	}
	
	public static String kthDisticnt(String[] arr,int k) {
		List<String> collect = Arrays.stream(arr).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()))
		.entrySet()
		.stream()
		.filter(entry->entry.getValue()==1l)
		.map(entry->entry.getKey())
		.collect(Collectors.toList());
		if(collect.size()<k) {
			return "";
		}
		return collect.get(k-1);
	}
	
	public static int smallestIndex(int[] nums) {
		int index=-1;
		for(int i=0;i<nums.length;i++) {
			if(i%10==nums[i]) {
				index=i;
				break;
			}
		}
		return index;
	}
	
	public static boolean checkStringEquilant(String word1,String word2) {
		int[] freq1=new int[26];
		int[] freq2=new int[26];
		for(int i=0;i<word1.length();i++) {
			freq1[word1.charAt(i)-'a']++;
		}
		for(int i=0;i<word2.length();i++) {
			freq2[word2.charAt(i)-'a']++;
		}
		int count=0;
		for(int i=0;i<26;i++) {
			if(Math.abs(freq1[i]-freq2[i])>3) {
				count++;
				break;
			}
			
		}
		if(count==1) {
			return false;
		}
		return true;
		
	}
	public static int countWords(String[] word1,String[] word2) {
		Map<String, Long> map = Arrays.stream(word1).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		
		for(String word:word2) {
			if(map.containsKey(word)) {
				if(map.get(word)<=1) {
				map.put(word, map.get(word)-1);
				}
			}
		}
		int count=0;
		for(String word:word2) {
			if(map.containsKey(word) && map.get(word)==0) {
				count++;
			}
		}
		return count;
	}
	
	public static String firstPalindromic(String[] words) {
		
		for(String word:words) {
			if(palindromic(word)) {
				return word;
			}
		}
		return "";
	}
	
	private static boolean palindromic(String word) {
		int start=0,end=0;
		while(start<end) {
			if(word.charAt(start)!=word.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}

	public static void main(String[] args) {
		String[] word1= {"abc","cas","","ada","as"};
		System.out.println(firstPalindromic(word1));
		
	}

}
