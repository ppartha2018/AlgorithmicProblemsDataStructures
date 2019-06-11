package com.prasanna.practice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PermutationofString {
	
	/*
	 * Cracking the coding interview - Problem 8.7 and 8.8
	 * 
	 * Solved with recursive formulation, bottom to top, n! time.
	 * Eg: if we calculate all the permutations of abc then permutations of abcd are inserting d all possible positions
	 * of all permutations of abc.
	 */
	
	static List<String> generatePermutations(String s, int n, int index){
		List<String> permutations = new ArrayList<String>();
		if(n == 1) {
			permutations.add(String.valueOf(s.charAt(index)));
		}
		if(n > 1) {
			//System.out.println("n="+n+" index="+index);
			List<String> prevPermutations = generatePermutations(s.substring(0, n-1), n-1, index-1);
			for(String prev: prevPermutations) {
				char[] curr = new char[n];
				int pos = 0;
				//System.out.println(prev.length());
				while(pos < n) {
					for(int i=0; i < pos; i++)
						curr[i] = prev.charAt(i);
					curr[pos] = s.charAt(index);
					
					for(int i=pos+1; i < n; i++)
						curr[i] = prev.charAt(i-1);
					permutations.add(new String(curr));
					pos++;
				}
			}
			
		}
		return permutations;
	}
	
	public static void printPermutations(List<String> perms) {
		Iterator<String> itr = perms.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		System.out.println("size: "+perms.size());
	}
	
	public static void main(String[] args) {
		//call with string, size, lastIndex
		printPermutations(PermutationofString.generatePermutations("abcd", 4, 3));
	}
	

}
