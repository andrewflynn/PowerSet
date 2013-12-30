package com.andrewandwhitney.puzzles;

import java.util.Arrays;

/**
 * Finds the complete reverse of a String as well as the reverse of a String,
 * keeping words intact.
 */
public class StringReverse {
    private static final char SPACE = ' ';
    
    private static final String NULL = null;
    private static final String EMPTY = "";
    private static final String CHAR = "a";
    private static final String EVEN_WORD = "hi";
    private static final String ODD_WORD = "hello";
    private static final String PHRASE = "hello, world!";
    private static final String SENTENCE = "Why, hello there world. How are you?";
    
    public static void main(String[] args) {
        for (String str : Arrays.asList(NULL, EMPTY, CHAR, EVEN_WORD, ODD_WORD, PHRASE, SENTENCE)) {
            System.out.println(str + " -> " + reverse(str));
            System.out.println(str + " -> " + reverseWords(str));
        }
    }
    
    private static String reverseWords(String in) {
        if (in == null || in.isEmpty()) {
            return in;
        }
        char[] reverseChars = reverseChars(in.toCharArray());
        StringBuilder sb = new StringBuilder();

        int spaceIndex = 0;
        for (int i = 0; i < reverseChars.length; i++) {
            if (reverseChars[i] != SPACE) {
                continue;
            }
            sb.append(reverseChars(Arrays.copyOfRange(reverseChars, spaceIndex, i)));
            sb.append(SPACE);
            spaceIndex = i;
        }
        sb.append(reverseChars(Arrays.copyOfRange(reverseChars, spaceIndex, reverseChars.length)));
        
        return sb.toString();
    }
    
    private static String reverse(String in) {
        if (in == null || in.isEmpty()) {
            return in;
        }
        
        return new String(reverseChars(in.toCharArray()));
    }
    
    private static char[] reverseChars(char[] chars) {
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            char switchChar = chars[i];
            chars[i] = chars[j];
            chars[j] = switchChar;
            i++;
            j--;
        }
        
        return chars;
    }
}
