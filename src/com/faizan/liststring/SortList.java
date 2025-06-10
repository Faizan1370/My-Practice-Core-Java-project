package com.faizan.liststring;

import java.util.Comparator;
import java.util.List;

public class SortList {
	
	public static void sortList() {
		
		List<String> list = List.of("Ankita","Faizan","rahul");
		
		list.stream().sorted(Comparator.comparing(str->str.length()))
		.forEach(str->System.out.println(str));
		
	}
	
	public static void main(String[] args) {
		sortList();
	}

}
