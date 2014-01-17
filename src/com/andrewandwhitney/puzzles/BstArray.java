package com.andrewandwhitney.puzzles;

import java.util.Arrays;
import java.util.LinkedList;

public class BstArray {
    public static final int[] EMPTY = new int[] { };
    public static final int[] L1 = new int[] { 1 };
    public static final int[] L2 = new int[] { 1, 2 };
    public static final int[] L3 = new int[] { 1, 2, 3 };
    public static final int[] L4 = new int[] { 1, 2, 3, 5 };
    public static final int[] L5 = new int[] { 1, 2, 3, 5, 8 };
    public static final int[] L6 = new int[] { 1, 2, 3, 5, 8, 13 };
    public static final int[] L7 = new int[] { 1, 2, 3, 5, 8, 13, 21 };
    public static final int[] L8 = new int[] { 1, 2, 3, 5, 8, 13, 21, 35 };
    public static final int[] L9 = new int[] { 1, 2, 3, 5, 8, 13, 21, 35, 56 };
    public static final int[] L10 = new int[] { 1, 2, 3, 5, 8, 13, 21, 35, 56, 91 };
    
    public static void main(String[] args) {
        for (int[] list : Arrays.asList(EMPTY, L1, L2, L3, L4, L5, L6, L7, L8, L9, L10)) {
            System.out.println(Arrays.toString(list) + " => " + Arrays.toString(bstListFromList(list)));
        }
    }
    
    private static int[] bstListFromList(int[] orig) {
        int[] bst = new int[nextHighestPowerOfTwo(orig.length + 1) - 1];
        
        if (orig.length == 0) {
            return bst;
        }
        
        LinkedList<int[]> queue = new LinkedList<int[]>();
        queue.push(orig);
        
        int counter = 0;
        while (!queue.isEmpty()) {
            int[] list = queue.pop();
            int len = list.length;
            
            if (len == 1) {
                bst[counter] = list[0];
            } else if (len == 2) {
                bst[counter] = list[1];
                queue.add(getSubArray(list, 0, 1));
                queue.add(new int[] { 0 });
            } else if (len == 3) {
                bst[counter] = list[1];
                queue.add(getSubArray(list, 0, 1));
                queue.add(getSubArray(list, 2, 1));
            } else {
                int divide = len / 2;
                bst[counter] = list[divide];
                queue.add(getSubArray(list, 0, divide));
                queue.add(getSubArray(list, divide + 1, len - (divide + 1)));
            }
            counter++;
        }
        
        return bst;
    }
    
    private static int nextHighestPowerOfTwo(int n) {
        n--;
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        n++;
        
        return n;
    }
    
    private static int[] getSubArray(int[] orig, int origStart, int length) {
        int[] list = new int[length];
        System.arraycopy(orig, origStart, list, 0, length);
        return list;
    }
}
