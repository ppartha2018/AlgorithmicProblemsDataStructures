package com.prasanna.practice;

import java.util.HashMap;

public class StringPermutation {
	
	/*
	 * Check if one string is a permution of other
	 */
	public static boolean checkStrings(String s1, String s2) {
		
		HashMap<Character, Integer> chars = new HashMap<Character, Integer>();
		//tochar array and lower case, depending on condition
		if(s1.length() != s2.length()) {
			return false;
		}
		for(int i=0; i < s1.length(); i++) {
			int value = (chars.get(s1.charAt(i)) !=null) ? (chars.get(s1.charAt(i))+1) : 1;
			chars.put(s1.charAt(i), value);
		}
		
		for(int i=0; i < s2.length(); i++) {
			
			if(chars.get(s2.charAt(i)) == null) {
				return false;
			} else {
				int value = chars.get(s2.charAt(i)) - 1;
				if(value == 0)
					chars.remove(s2.charAt(i));
				else
					chars.put(s2.charAt(i), value);
			}
		}
		
		if(chars.size() > 0) return false;
		
		return true;
	}
	
	public static void main(String[] args) {
		String s1 = "a";
		String s2 = "a";
		
		System.out.println(StringPermutation.checkStrings(s1, s2));
		
	}

}
