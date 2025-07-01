package com.faizan.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeetCode5 {
	
	
	public static String interpret(String command) {
		StringBuilder builder = new StringBuilder();
		int i=0;
		while(i<command.length()) {
			char ch=command.charAt(i);
			if(ch=='G') {
				builder.append("G");
				i++;
			}else if(ch=='(' && command.charAt(i+1)==')') {
				builder.append("o");
				i=i+2;
			}else {
				builder.append("al");
				i=i+4;
			}
		}
		return builder.toString();
	}
	
	public static int sumUniqueElement(int[] nums) {
		 Integer reduce = Arrays.stream(nums).mapToObj(num->(Integer)num)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet()
		.stream()
		.filter(entry->entry.getValue()==1L)
		.map(entry->entry.getKey())
		.reduce(0,(a,b)->a+b);
		return reduce;
	}
	
	
	public static String mergerALterNative(String word1,String word2) {
		int len1=word1.length();
		int len2=word2.length();
		int min=Math.min(len1, len2);
		String longerString="";
		if(min==len1) {
			longerString=word2;
		}
		if(min==len2) {
			longerString=word1;
		}
		char[] charArray = word1.toCharArray();
		char[] charArray2 = word2.toCharArray();
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<min;i++) {
			builder.append(charArray[i]);
			builder.append(charArray2[i]);
		}
		builder.append(longerString.substring(min));
		return builder.toString();
	}
	
	public static int secondHieghest(String s) {
		Character character = s.chars().mapToObj(c->(char)c)
		.distinct().filter(c->Character.isDigit(c))
		.sorted(Comparator.reverseOrder())
		.skip(1)
		.findFirst()
		.get();
		return Character.getNumericValue(character);
	}
	public static String truncateString(String s,int k) {
		if(s.length()<k) {
			return "";
		}
		String[] words =s.split(" ");
		if(words.length<k) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		int index =0;
		while(index<k) {
			builder.append(words[index]).append(" ");
			index++;
		}
		
		return builder.toString().trim();
		
		
	}
	
	public static int arraySign(int[] nums) {
		int negCount=0;
		for(int i=0;i<nums.length;i++) {
			if(nums[i]==0) {
				return 0;
			}
			if (nums[i]<0){
				negCount++;
			}
				
			}
		 if(negCount % 2==0) {
			 return 1;
		 }
		 return -1;
		}
	
	public static boolean checkIfPangram(String sentence) {
		HashSet<Character> set=new HashSet<Character>();
		for(int i=0;i<sentence.length();i++) {
			set.add(sentence.charAt(i));
		}
		if(set.size()==26) {
			return true;
		}
		return false;
	}
	public static String sortString(String sentence) {
		String[] words = sentence.split(" ");
	    Map<Integer,String> map = new HashMap<Integer, String>();
	     for(int i=0;i<words.length;i++) {
	    	 int len =words[i].length();
	    	 int num=Integer.parseInt(String.valueOf(words[i].charAt(len-1)));
	    	 map.put(num, words[i].substring(0, len-1));
	     }
	     
	     String[] result = new String[words.length];
	     for(Entry<Integer,String> entry:map.entrySet()) {
	    	 int index=entry.getKey();
	    	 result[index-1] =entry.getValue();
	    	 
	     }
	     StringBuilder builder = new StringBuilder();
	     for(int i=0;i<result.length;i++) {
	    	 builder.append(result[i]);
	    	 if(i!=result.length-1) {
	    		 builder.append(" ");
	    	 }
	     }
		return builder.toString();
	}
	
	public static int[] getConcatenation(int[] nums) {
		int[] ans= new int[2*nums.length];
		
		for(int i=0;i<nums.length;i++) {
			ans[i]=nums[i];
			ans[i + nums.length] = nums[i];  // second half
			
		}
		return ans;
	}
	
	public static boolean areOccurenceEqual(String s) {
		Map<Character, Long> collect = s.chars().mapToObj(c->(char)c)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		
		Set<Long> set = new HashSet<Long>(collect.values());
		if(set.size()==1) {
			return true;
		}
		return false;
		
	}
	
	public static String kthDistictString(String[] arr,int k) {
	  Map<String,Integer> map = new LinkedHashMap<String, Integer>();
	  for(int i=0;i<arr.length;i++) {
		  if(map.containsKey(arr[i])) {
			  map.put(arr[i], map.get(arr[i])+1);
		  }else {
			  map.put(arr[i], 1);
		  }
	  }
	  List<String> list = new ArrayList<String>();
	  for(Map.Entry<String, Integer> entry:map.entrySet()) {
		  if(entry.getValue()==1) {
			  list.add(entry.getKey());  
		  }
		  
	  }
	  if(list.size()<k) {
		  return "";
	  }
	  String result = list.get(k-1);
	  return result;
	}
	public static int smallestEqual(int[] nums) {
		int index=-1;
		for(int i=0;i<nums.length;i++) {
			if(i%10 == nums[i]) {
				index=i;
				break;
			}
		}
		return index;
	}
	
	public static void main(String[] args) {
		int[] nums= {4,3,2,1};
		System.out.println(smallestEqual(nums));
		//String[] arr= {"d","b","c","b","c","a"};
		//int k=2;
		//System.out.println(kthDistictString(arr,k));
		//String s="aabba";
		//System.out.println(areOccurenceEqual(s));
		//int[] nums= {1,2,1};
		//System.out.println(Arrays.toString(getConcatenation(nums)));
		//String sentence ="is2 sentence4 This1 a3";
		//System.out.println(sortString(sentence));
		//String sentence="thequickbrownfoxjumpsoverthelazydog";
		//System.out.println(checkIfPangram(sentence));
		//int[] nums= {-1,-2,-3,-4,3,2,1};
		//System.out.println(arraySign(nums));
		//String s="Hello how are you dear";
		//int k =4;
		//System.out.println(truncateString(s, k));
		//String s="abdgh11667744jgg";
		//System.out.println(secondHieghest(s));
		//String word1 ="abc";
		//String word2 ="pqrfaiz";
		//System.out.println(mergerALterNative(word1, word2));
		//int[] nums= {1,2,3,2};
		//System.out.println(sumUniqueElement(nums));
		//String command ="G()(al)";
		//System.out.println(interpret(command));
	}

}
