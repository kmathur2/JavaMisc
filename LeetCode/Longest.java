/**
 * Created by kartik on 1/3/17.
 */
/*
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

import java.util.*;
public class Longest {

    public static void main(String args[]){
        int a = lengthOfLongestSubstring("asadafgtryyu");
        System.out.println(a);
    }

    private static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        String lastLongest="";
        String longest="";
        for (char c : chars){
            String cs = Character.toString(c);
            if(longest.indexOf(cs) != -1){
                if(longest.length() >= lastLongest.length()) {
                    lastLongest = longest;
                }
                longest=longest + cs;
                longest=longest.substring(longest.indexOf(cs)+1);
            }else{
                longest=longest + cs;
            }
        }
        if(lastLongest.length() > longest.length())
            longest=lastLongest;
        System.out.println(longest);
        return longest.length();
    }
}

