package com.faizan.java8Prac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prac6 {
	
	public static void evenNumber() {
		List<Integer> list = Arrays.asList(5,7,2,8,9,10);
		list.stream().filter(num->num%2==0).forEach(num->System.out.println(num));
	}
	
	public static void main(String[] args) {
		evenNumber();
	}

}
