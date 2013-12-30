package com.andrewandwhitney.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Finds the power set (http://en.wikipedia.org/wiki/Power_set) of a given list of numbers
 * using bitwise-arithmetic, an iterative approach, and recursion.
 */
public class PowerSet {
    private static final List<String> ZERO = Arrays.asList();
    private static final List<String> ONE = Arrays.asList("A");
    private static final List<String> TWO = Arrays.asList("A", "B");
    private static final List<String> THREE = Arrays.asList("A", "B", "C");
    private static final List<String> SIX = Arrays.asList("A", "B", "C", "D", "E", "F");
    
    public static void main(String[] args) {
        for (List<String> list : Arrays.asList(ZERO, ONE, TWO, THREE, SIX)) {
            // Recursive
            List<List<String>> recursive = recursivePowerSet(list);
            System.out.println(String.format("Recursive (%1$d -> %2$d): %3$s", list.size(), recursive.size(), recursive));
            
            // Iterative
            List<List<String>> iterative = iterativePowerSet(list);
            System.out.println(String.format("Iterative (%1$d -> %2$d): %3$s", list.size(), iterative.size(), iterative));
            
            // Bitwise
            List<List<String>> bitwise = bitwisePowerSet(list);
            System.out.println(String.format("Bitwsie (%1$d -> %2$d): %3$s", list.size(), bitwise.size(), bitwise));
        }
    }
    
    private static <T> List<List<T>> bitwisePowerSet(List<T> list) {
        int size = list.size();
        int powSize = (int) Math.pow(2.0d, size);
        List<List<T>> superset = new ArrayList<List<T>>(powSize);
        
        for (int i = 0; i < powSize; i++) {
            List<T> currList = new ArrayList<T>();
            for (int j = 0; j < size; j++) {
                int bitPresent = (i >> j) & 1;
                if (bitPresent == 1) {
                    currList.add(list.get(j));
                }
            }
            superset.add(currList);
        }
        
        return superset;
    }
    
    private static <T> List<List<T>> iterativePowerSet(List<T> list) {
        List<List<T>> superset = new ArrayList<List<T>>((int) Math.pow(2.0d, list.size()));
        superset.add(new ArrayList<T>(0));
        
        for (T elem : list) {
            List<List<T>> newSet = new ArrayList<List<T>>();
            for (List<T> subList : superset) {
                newSet.add(subList);
                List<T> newSubList = new ArrayList<T>(subList.size() + 1);
                newSubList.add(elem);
                newSubList.addAll(subList);
                newSet.add(newSubList);
            }
            
            superset = newSet;
        }
        
        return superset;
    }
    
    private static <T> List<List<T>> recursivePowerSet(List<T> list) {
        if (list.size() <= 0) {
            List<List<T>> powerSet = new ArrayList<List<T>>(0);
            powerSet.add(new ArrayList<T>(0));
            return powerSet;
        } else if (list.size() == 1) {
            List<List<T>> powerSet = new ArrayList<List<T>>(2);
            powerSet.add(new ArrayList<T>(0));
            powerSet.add(list);
            return powerSet;
        } else {
            T first = list.get(0);
            List<List<T>> powerSet = recursivePowerSet(list.subList(1, list.size()));
            List<List<T>> newPowerSet = new ArrayList<List<T>>();
            for (List<T> subPowerSet : powerSet) {
                newPowerSet.add(subPowerSet);
                List<T> newSubPowerSet = new ArrayList<T>(subPowerSet);
                newSubPowerSet.add(first);
                newPowerSet.add(newSubPowerSet);
            }
            return newPowerSet;
        }
    }
}
