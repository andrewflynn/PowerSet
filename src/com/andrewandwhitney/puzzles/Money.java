package com.andrewandwhitney.puzzles;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Given a target amount of money and a set of denominations with which to use,
 * finds the minimum number of units in order to achieve this target number.
 */
public class Money {
    private static final int[] NULL = null;
    private static final int[] EMPTY = new int[0];
    private static final int[] ONE = new int[] { 1 };
    private static final int[] ONE_TWO = new int[] { 1, 2 };
    private static final int[] US_CHANGE = new int[] { 1, 5, 10, 25 };
    private static final int[] US_FOOTBALL = new int[] { 2, 3, 6, 7, 8 };
    
    private static final int[] TARGETS = new int[] { 0, 1, 2, 5, 8, 10, 49, 50, 67 };
    
    private static Map<int[], int[]> sCache = new HashMap<int[], int[]>();
    
    public static void main(String[] args) {
        for (int[] denominations : Arrays.asList(NULL, EMPTY, ONE, ONE_TWO, US_CHANGE, US_FOOTBALL)) {
            for (int target : TARGETS) {
                System.out.println("Normal: (" + target + ", " + Arrays.toString(denominations) + ") "
                        + minimumCount(target, denominations));
                System.out.println("Cache : (" + target + ", " + Arrays.toString(denominations) + ") "
                        + minimumCountUsingCache(target, denominations));
            }
        }
    }
    
    private static int minimumCountUsingCache(int target, int[] denominations) {
        if (denominations == null || denominations.length == 0) {
            return -1;
        }
        
        int start = 0;
        int[] counts = new int[target + 1];
        
        if (sCache.containsKey(denominations)) {
            int[] cacheHit = sCache.get(denominations);
            if (target < cacheHit.length) {
                return cacheHit[target];
            } else {
                start = cacheHit.length;
                System.arraycopy(cacheHit, 0, counts, 0, cacheHit.length);
            }
        }
        
        for (int i = start; i <= target; i++) {
            for (int denomination : denominations) {
                int curr = i - denomination;
                if (curr < 0) {
                    continue;
                } else if (curr == 0) {
                    counts[i] = 1;
                } else {
                    int currCounts = counts[curr];
                    if (counts[i] == 0 || currCounts + 1 < counts[i]) {
                        counts[i] = currCounts + 1;
                    }
                }
            }
        }
        
        sCache.put(denominations, counts);
        return counts[target];
    }
    
    private static int minimumCount(int target, int[] denominations) {
        if (denominations == null || denominations.length == 0) {
            return -1;
        }
        
        int start = 0;
        int[] counts = new int[target + 1];
        
        for (int i = start; i <= target; i++) {
            for (int denomination : denominations) {
                int curr = i - denomination;
                if (curr < 0) {
                    continue;
                } else if (curr == 0) {
                    counts[i] = 1;
                } else {
                    int currCounts = counts[curr];
                    if (counts[i] == 0 || currCounts + 1 < counts[i]) {
                        counts[i] = currCounts + 1;
                    }
                }
            }
        }
        
        return counts[target];
    }
}
