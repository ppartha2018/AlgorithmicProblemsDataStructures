package com.prasanna.practice;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/*
 * Check for balanced brackets from equation of multiple types of nested brackets.
 * Stack application.
 */
public class UnbalancedBrackets{

    // Complete the isBalanced function below.
    static String isBalanced(String s) {
        int length = s.length();
        if(length == 0)
            return "YES";
        if(length % 2 > 0)
            return "NO";
        Stack<Character> openBrackets = new Stack<Character>();
        int i = 0;
        while(i < length){
        	 if(s.charAt(i) == '(')
                 openBrackets.push(')'); 
             if(s.charAt(i) == '{')
                 openBrackets.push('}');
             if(s.charAt(i) == '[')
                 openBrackets.push(']');
            else{
                //stack underflow case - more close than open, cleary "NO"
                if(openBrackets.size() == 0)
                    return "NO";
                if(openBrackets.pop() != s.charAt(i))
                    return "NO";
            }
            i++;
        }
        //may have excess openBrackets
        return openBrackets.isEmpty() ? "YES" : "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
       System.out.println(UnbalancedBrackets.isBalanced("[()]"));
    }
}
