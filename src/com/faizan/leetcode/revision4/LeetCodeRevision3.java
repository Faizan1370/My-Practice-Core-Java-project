package com.faizan.leetcode.revision4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeetCodeRevision3 {

	public static int findChampion(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;
		for (int i = 0; i < row; i++) {
			int count = 0;
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == 1) {
					count++;
				}
			}
			if (count == grid.length - 1) {
				return i;
			}
		}
		return -1;
	}

	public static boolean stringEquivalent(String[] word1, String[] word2) {
		StringBuilder builder = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		for (String str : word1) {
			builder.append(str);
		}
		for (String str : word2) {
			builder2.append(str);
		}
		return builder.toString().equals(builder2.toString());
	}

	public static int elementAppear25Per(int[] arr) {
		int limit = arr.length / 4;
		for (int i = 0; i < arr.length - limit; i++) {
			if (arr[i] == arr[i + limit]) {
				return arr[i];
			}
		}
		return -1;
	}

	public static int[] findIntersectionValues(int[] nums1, int[] nums2) {
		int[] count1 = new int[101];
		int[] count2 = new int[101];
		for (int i = 0; i < nums1.length; i++) {
			count1[nums1[i]]++;
		}
		for (int i = 0; i < nums2.length; i++) {
			count2[nums2[i]]++;
		}
		int result = 0, result2 = 0;
		for (int i = 1; i <= 100; i++) {
			if (count2[i] >= 1) {
				result += count1[i];
			}
			if (count1[i] >= 1) {
				result2 += count2[i];
			}
		}
		int[] res = new int[2];
		res[0] = result;
		res[1] = result2;

		return res;
	}

	public static int numSpecial(int[][] mat) {
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
		if (s.length() > t.length()) {
			return false;
		}
		HashSet<Character> set = new HashSet<Character>();
		for (int i = 0; i < s.length(); i++) {
			set.add(s.charAt(i));
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < t.length(); i++) {
			if (set.contains(t.charAt(i))) {
				builder.append(t.charAt(i));
			}
		}
		return s.equals(builder.toString());
	}

	public static boolean isSequence1(String s, String t) {
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

	public static boolean isSubsequence2(String s, String t) {
		int i = 0, j = 0;
		while (i < s.length() && j < t.length()) {
			if (s.charAt(i) == t.charAt(j)) {
				i++;
			}
			j++;
		}
		return i == s.length();
	}

	public static boolean plantFlower(int[] flower, int k) {
		int count = 0;
		for (int i = 0; i < flower.length; i++) {
			if (flower[i] == 0) {
				if ((i == 0 || flower[i - 1] == 0) && (i == flower.length - 1 || flower[i + 1] == 0)) {
					count++;
				}
				if (count == k) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean plantFlower1(int[] flower, int k) {
		int count = 0;
		for (int i = 0; i < flower.length; i++) {
			if (flower[i] == 0) {
				if ((i == 0 || flower[i - 1] == 0) && (i == flower.length - 1 || flower[i + 1] == 0)) {
					flower[i] = 1; // 🌱 mark as planted
					count++;
					if (count >= k) {
						return true;
					}
				}
			}
		}
		return count >= k;
	}

	public static int removeElement(int[] nums, int val) {
		int i = 0;
		int len = nums.length;
		while (i < len) {
			if (nums[i] == val) {
				nums[i] = nums[len - 1];
				len--;
			} else {
				i++;
			}

		}
		return len;
	}

	public static int removeElement2(int[] nums, int val) {
		int k = 0; // position for next valid element
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != val) { // only keep elements not equal to val
				nums[k] = nums[i]; // overwrite in place
				k++; // move pointer forward
			}
		}
		return k; // new length (number of valid elements)
	}

	public static String grestestCDiv(String str1, String str2) {
		String gcdStr = str1.substring(0, gcd(str1.length(), str2.length()));
		return gcdStr;
	}

	private static int gcd(int length, int length2) {
		if (length2 == 0) {
			return length;
		}
		return gcd(length2, length % length2);
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

	public static int pivotIndex1(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		int leftSum = 0;
		for (int i = 0; i < nums.length; i++) {
			if (leftSum == sum - leftSum - nums[i]) {
				return i;
			}
			leftSum += nums[i];
		}
		return -1;

	}

	public static int pivotIndex(int[] nums) {
		int start = 0, end = nums.length - 1;
		int leftSum = 0, rightSum = 0;

		while (start <= end) {
			if (leftSum == rightSum && start == end) {
				return start; // pivot found
			}

			if (leftSum <= rightSum) {
				leftSum += nums[start];
				start++;
			} else {
				rightSum += nums[end];
				end--;
			}
		}
		return -1; // no pivot
	}

	public static boolean uniueOccurence(int[] arr) {
		Map<Integer, Long> map = Arrays.stream(arr).boxed()
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
			char temp = words[start];
			words[start] = words[end];
			words[end] = temp;
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

	public static int varibaleValue(String[] operations) {
		int value = 0;
		for (String word : operations) {
			if (word.equals("++X") || word.equals("X++")) {
				value++;
			}
			if (word.equals("--X") || word.equals("X--")) {
				value--;
			}
		}
		return value;
	}

	public static List<Integer> containingChar(String[] words, char ch) {
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

	public static int[] findMissingRepeating(int[][] grid) {
		int n = grid.length;
		int a = 0, b = 0;
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
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

	public static int maxWordsSentence(String[] sentences) {
		int count = 0;
		for (String str : sentences) {
			String[] words = str.split(" ");
			count = Math.max(count, words.length);
		}
		return count;
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

	public static String removeTrailingZero1(String s) {
		int start = 0, end = s.length() - 1;
		while (start < end) {
			if (s.charAt(end) == '0') {
				end--;
			} else {
				break;
			}
		}
		return s.substring(0, end + 1);
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

	public static int buyChoclates(int[] price, int money) {
		int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
		for (int i = 0; i < price.length; i++) {
			if (price[i] < min) {
				secondMin = min;
				min = price[i];
			} else if (price[i] < secondMin) {
				secondMin = price[i];
			}
		}

		// If only one chocolate exists, we can't buy two
		if (secondMin == Integer.MAX_VALUE) {
			return money;
		}

		int total = min + secondMin;
		return (total > money) ? money : money - total;
	}

	public static int maxScore(String s) {
		int maxScore = 0;
		for (int i = 0; i < s.length() - 1; i++) {
			System.out.println(s.substring(0, i + 1));
			System.out.println(s.substring(i + 1, s.length() - 1));
			int currentCount = zeroCountLeft(s.substring(0, i + 1)) + oneCountRight(s.substring(i + 1, s.length()));
			maxScore = Math.max(maxScore, currentCount);
		}
		return maxScore;
	}

	public static int zeroCountLeft(String s) {
		int countZero = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0') {
				countZero++;
			}
		}
		return countZero;
	}

	public static int oneCountRight(String s) {
		int countOne = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				countOne++;
			}
		}
		return countOne;
	}

	public static double maxSubarray(int[] nums, int k) {
		int maxSum = 0;
		for (int i = 0; i < nums.length - k; i++) {
			int currentSum = 0;
			for (int j = i; j < k + i; j++) {
				currentSum += nums[j];
			}
			System.out.println(maxSum + " " + currentSum);
			maxSum = Math.max(maxSum, currentSum);
		}
		double res = maxSum / (double) k;
		return res;
	}

	public static boolean pathCross1(String path) {
		HashSet<String> set = new HashSet<String>();
		int x = 0, y = 0;
		set.add(x + "-" + y);
		for (int i = 0; i < path.length(); i++) {
			if (path.charAt(i) == 'N') {
				y += 1;
			} else if (path.charAt(i) == 'S') {
				y -= 1;
			} else if (path.charAt(i) == 'E') {
				x += 1;
			} else if (path.charAt(i) == 'W') {
				x -= 1;
			}
			if (set.contains(x + "-" + y)) {
				return true;
			}
			set.add(x + "-" + y);
		}
		return false;
	}

	public static int minOp(String s) {
		int startWith1 = 0;// 1010
		int startWith0 = 0;// 0101

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

	public static boolean isMonolatic(int[] nums) {
		boolean isInc = true;
		boolean isDec = true;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] > nums[i]) {
				isInc = false;
			} else if (nums[i - 1] < nums[i]) {
				isDec = false;
			}
		}
		if (isDec || isInc) {
			return true;
		}
		return isDec;
	}

	public static int[] minNumberGame(int[] nums) {
		int[] ans = new int[nums.length];
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for (int num : nums) {
			queue.add(num);
		}
		int i = 0;
		while (!queue.isEmpty()) {
			int first = queue.poll();
			if (!queue.isEmpty()) {
				int second = queue.poll();
				ans[i++] = second;
				ans[i++] = first;
			} else {
				ans[i++] = first;
			}

		}
		return ans;
	}

	public static int incRemovableSubArray(int[] nums) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i; j < nums.length; j++) {
				if (isIncresingArray(nums, i, j)) {
					count++;
				}
			}
		}
		return count;
	}

	private static boolean isIncresingArray(int[] nums, int start, int end) {
		int prev = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i <= end && i >= start) {
				continue;
			}
			if (nums[i] <= prev) {
				return false;
			}
			prev=nums[i];
		}
		return true;
	}
	public static List<String> stringMatching(String[] words) {
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<words.length;i++) {
			for(int j=0;j<words.length;j++) {
				String word1=words[i];
				String word2=words[j];
				if(word1.length()>=word2.length()) {
					continue;
				}
				if(isSubstring(word1,word2)) {
					list.add(word1);
				}
			}
		}
		return list;
	}

	private static boolean isSubstring(String word1, String word2) {
		if(word2.contains(word1)) {
			return true;
		}
		return false;
	}
	
	public static boolean makeEqual(String[] words) {
		int[] freq = new int[26];
		if(words.length==1) {
			return true;
		}
		for(String word:words) {
			for(int i=0;i<word.length();i++) {
				freq[word.charAt(i)-'a']++;
			}
		}
		for(int i:freq) {
			if(i%words.length!=0) {
				return false;
			}
		}
		return true;
	}
	
	public static int longestSubstring(String s) {
		int max=0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i=0;i<s.length();i++) {
			if(map.containsKey(s.charAt(i))) {
				max=Math.max(max,i-map.get(s.charAt(i))-1);
			}
				map.put(s.charAt(i), i);
			
			
		}
		return max;
	}
	
	public static boolean hasTrailingZero(int[] nums) {
		int count=0;
		for(int num:nums) {
			if((num & 1)==0) {
				count++;
				if(count>=2) {
					return true;
				}
			}
		}
		return false;
	}
	 public static int findContentChildren(int[] g,int[] s) {
		 Arrays.sort(g);
		 Arrays.sort(s);
		 int cockie=0;
		 int children=0;
		 while(cockie<s.length && children<g.length){
			 if(g[children]<=s[cockie]) {
				 children++;
			 }
			 cockie++;
		 }
		return children;
	 }

	 
	 public static boolean stringAlike(String s) {
		 int n =s.length();
		 if(countVowel(s.substring(0,n/2))==countVowel(s.substring(n/2, n))) {
			 return true;
		 }
		 return false;
	 }
	 public static int countVowel(String s) {
		 String vowelString="AEIOUaeiou";
		 int count=0;
		 for(int i=0;i<s.length();i++) {
			 if(vowelString.indexOf(s.charAt(i))!=-1) {
				 count++;
			 }
		 }
		return count;
	 }
	 
	 public static int climbingStairs(int n) {
		 int ans=0;
		 int a=0;
		 int b=1;
		 for(int i=1;i<=n;i++) {
			 ans=a+b;
			 a=b;
			 b=ans;
		 }
		return ans;
	 }
	 public static int missingInteger(int[] nums) {
		 HashSet<Integer> set = new HashSet<Integer>();
		 int maxSum=nums[0],sum=nums[0];
		 for(int num:nums) {
			 set.add(num);
		 }
		 for(int i=1;i<nums.length;i++) {
			 if(nums[i]-nums[i-1]==1) {
				 sum +=nums[i];
			 }else {
				 sum=0;
			 }
			 maxSum = Math.max(sum, maxSum);
		 }
		 if(maxSum==nums[0]) {
			 while(set.contains(maxSum)) {
				 maxSum++;
			 }
			 return maxSum;
		 }
		 while(set.contains(maxSum)) {
			 maxSum++;
		 }
		 return maxSum;
	 }
	 public static int areaOfMaxDiagonal(int[][] dimensions) {
		 int maxArea=0;
		double maxDiagonal=0;
		 for(int[] dimension:dimensions) {
			 int l=dimension[0];
			 int w=dimension[1];
			 int area=l*w;
			 double diagonal=Math.sqrt(l*l+w*w);
			 if(diagonal>maxDiagonal) {
				 maxDiagonal=diagonal;
				 maxArea=area;
			 }else if(diagonal==maxDiagonal) {
				 maxArea=Math.max(area, maxArea);
			 }
		 }
		return maxArea;
	 }
	 public static int countMaxFreq(int[] nums) {
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
	 public  static int minCost(int[] nums) {
		 if (nums.length < 3) {
		        return Arrays.stream(nums).sum(); // or define your behavior
		    }

		 int first=nums[0];
		 int firstMin=Integer.MAX_VALUE;
		 int secondMin=Integer.MAX_VALUE;
		 for(int i=1;i<nums.length;i++) {
			 if(firstMin>nums[i]) {
				secondMin=firstMin;
				firstMin=nums[i];
			 }else if(secondMin>nums[i]) {
				 secondMin=nums[i];
			 }
		 }
		 return first+firstMin+secondMin;
	 }
	 public static int[] setMismatch(int[] nums) {
		 int[] ans= new int[2];
		 HashSet<Integer> set = new HashSet<Integer>();
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
	 public static int changingKey(String s) {
		 int count=0;
		 s=s.toLowerCase();
		 for(int i=1;i<s.length();i++) {
			 if(s.charAt(i) != s.charAt(i-1)) {
				 count++;
			 }
		 }
		return count;
	 }
	 
	 public static int antBoundry(int[] nums) {
		 int sum=0;
		 int count=0;
		 for(int i=0;i<nums.length;i++) {
			 sum +=nums[i];
			 if(sum==0) {
				 count++;
			 }
		 }
		return count;
	 }
	 public static boolean isPowerOfTwo(int n) {
		 if(n<=0) {
			 return false;
		 }
		 int bitC=Integer.bitCount(8);
		 if(bitC==1) {
			 return true;
		 }
		return false;
	 }
	 
	 public static int missingNumber(int[] nums) {
		 int n=nums.length;
		 int sum=n*(n+1)/2;
		 for(int num:nums) {
			 sum -=num;
		 }
		return sum;
		 }
	 
	
	public static void main(String[] args) {
		int[] nums= {3,0,1};
		System.out.println(missingNumber(nums));
	}
}
