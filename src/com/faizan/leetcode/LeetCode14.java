package com.faizan.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetCode14 {
	public String convert(String s, int numRows) {
        if(numRows==1 || s.length()<=numRows){
            return s;
        }
        List<StringBuilder> rows =new ArrayList<>();
        for(int i=0;i<numRows;i++){
            rows.add(new StringBuilder());
        }
        int row =0;
        boolean goDown=true;
      for(char c: s.toCharArray()){
           rows.get(row).append(c);
           if(row== numRows-1){
            goDown=false;
           }else if(row==0){
            goDown=true;
           }
           row +=goDown?1:-1;
      }
      StringBuilder result = new StringBuilder();
       for (StringBuilder sb : rows) {
            result.append(sb);
        }
        return result.toString();
    }
	
	
	public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        if(s.length()==1){
            return false;
        }
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch=='{' || ch=='[' || ch=='('){
                stack.push(ch);
            }else{
                 if (stack.isEmpty()) return false;

                char top = stack.pop();

                if (ch == ')' && top != '(') return false;
                if (ch == '}' && top != '{') return false;
                if (ch == ']' && top != '[') return false;
            }
        }
        return stack.isEmpty();
    }

}
