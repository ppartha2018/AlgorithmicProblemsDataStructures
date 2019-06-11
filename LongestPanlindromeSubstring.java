package com.prasanna.practice;


/*
 * 
 * Longest palindrome substring , brute force and dynamic programming approach.
 */
public class LongestPanlindromeSubstring {
	
	//Brute Force
	public String longestPalindromicSubstring(String s) {
		
		int maxLength = 0;
		String maxStr = null;
		//Generate all possible substring, check and store longest
		for(int i=0; i < s.length(); i++) {
			for(int j=i; j < s.length(); j++) {
				String subString = s.substring(i,j);
				if(checkPalindrome(subString)) {
					if(subString.length() > maxLength) {
						maxStr = subString;
						maxLength = subString.length();
					}
				}
			}
		}
		
		return maxStr;
	}
	
	//Dynamic Programming
	public int lPDynamicProgramming(String s) {
		int length = s.length();
		int[][] results = new int[length][length];
		int maxLength = 0;
		
		for(int i = 0; i < length; i++) {
			for(int j=i; j < s.length(); j++) {
				if(results[i][j] == 0) {
					String subString = s.substring(i,j);
					if(checkPalindrome(subString)) {
						results[i][j] = subString.length();
						maxLength = (subString.length() > maxLength) ? subString.length():maxLength;
					}
				}else {
					
				}
			}
		}
		
		return maxLength;
	}
	
	
	 public boolean checkPalindrome(String s){
		System.out.println("String to check for palindrome: "+ s);
		if(s.isEmpty()) {
			return false;
		}
	 	char[] temp = s.toCharArray();
        int mid = temp.length / 2;
        boolean result = true;
        for(int i=0, j=temp.length-1; i<=mid; i++, j--){
            if(temp[i] != temp[j]){
                result = false;
                break;
            }
        }
        return result;
	}
	
	public static void main(String[] args) {
		String s = "prasanna";
		System.out.println(s.substring(0,s.length()));
		
		LongestPanlindromeSubstring lc = new LongestPanlindromeSubstring();
		System.out.println(lc.longestPalindromicSubstring("cbbaabbd"));
		System.out.println(lc.lPDynamicProgramming("cbbaabbd"));
	}

}
