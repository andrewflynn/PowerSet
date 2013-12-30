package com.andrewandwhitney.puzzles;

/**
 * Prints out Fibonacci numbers using recursion (with and without cache)
 * and an iterative approach (with and without storing all found answers).
 */
public class Fibonacci {
    private static int[] sCache;
    public static void main(String[] args) {
        // Recursive
        sCache = new int[42];
        for (int i = -2; i < 42; i++) {
            System.out.println(i + " -> " + recursiveFibonacci(i) + ", " + recursiveWithCacheFibonacci(i)
                    + ", " + iterativeFibonacci(i) + ", " + iterativeWithSpaceSavingsFibonacci(i));
        }
    }
    
    private static int iterativeWithSpaceSavingsFibonacci(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0 || n == 1) {
            return 1;
        }
        
        int prev1 = 1;
        int prev2 = 1;
        int answer = prev1 + prev2;
        for (int i = 2; i < n + 1; i++) {
            answer = prev1 + prev2;
            prev1 = prev2;
            prev2 = answer;
        }
        return answer;
    }
    
    private static int iterativeFibonacci(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0 || n == 1) {
            return 1;
        }
        
        int[] answers = new int[n + 1];
        answers[0] = 1;
        answers[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            answers[i] = answers[i - 2] + answers[i - 1];
        }
        return answers[n];
    }
    
    private static int recursiveWithCacheFibonacci(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0 || n == 1) {
            return 1;
        }
        
        if (sCache[n] == 0) {
            sCache[n] = recursiveWithCacheFibonacci(n - 1) + recursiveWithCacheFibonacci(n - 2);
        }
        return sCache[n];
    }
    
    private static int recursiveFibonacci(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0 || n == 1) {
            return 1;
        } else {
            return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
        }
    }
}
