package com.prasanna.practice;

public class atoi {
	/*
	 * Implementation atoi function.
	 * ascii to integer -popular C function.
	 * 
	 */
    public int myAtoi(String str) {
        
        int length = str.length();
        if(length == 0) return 0;
        
        char[] chars = str.toCharArray();
        
        
        //iternate till first non-space character
        int index = 0;
        while(index < length && chars[index] == ' ') index++;
        if(index >= length) return 0;
        int signMultiplier = 1;
        if(chars[index] == '+' || chars[index] == '-'){
            signMultiplier = chars[index] == '+' ? 1 : -1;
            index++;
        }
        int returnValue = 0;
        
        //check if digits
        while(index < length && chars[index] > 47 && chars[index] < 58){
            int currValue = Character.getNumericValue(chars[index]);
            if(signMultiplier == 1 && ((returnValue > Integer.MAX_VALUE/10) || (returnValue == Integer.MAX_VALUE/10 && currValue > 7))) return Integer.MAX_VALUE;
            if(signMultiplier == -1 && ((returnValue*-1 < Integer.MIN_VALUE/10) || (returnValue*-1 == Integer.MIN_VALUE/10 && currValue*signMultiplier < -8))) return Integer.MIN_VALUE;
                returnValue = returnValue * 10 + currValue;
                index++;
        }
        returnValue *= signMultiplier;
        return returnValue;
    }
}