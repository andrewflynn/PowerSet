package com.andrewandwhitney.puzzles;

import java.util.LinkedList;
import java.util.Random;

public class Queues {
    private static final Random RAND = new Random();
    private static final int PUSH_MAX_SLEEP_MILLIS = 5000;
    private static final int POP_MAX_SLEEP_MILLIS = 2000;
    private static final int PUSH_RUN_COUNT = 5;
    private static final int POP_RUN_COUNT = 5;
    
    private static final MyBlockingQueue<Integer> q = new MyBlockingQueue<Integer>();

    private static final Runnable SLEEP_AND_PUSH_RUNNABLE = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < PUSH_RUN_COUNT; i++) {
                sleepRandom(PUSH_MAX_SLEEP_MILLIS);
                q.push(RAND.nextInt());
            }
        }
    };
    private static final Runnable SLEEP_AND_POP_RUNNABLE = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < POP_RUN_COUNT; i++) {
                sleepRandom(POP_MAX_SLEEP_MILLIS);
                q.pop();
            }
        }
    };
    
    public static void main(String[] args) {
        new Thread(SLEEP_AND_PUSH_RUNNABLE).start();
        new Thread(SLEEP_AND_POP_RUNNABLE).start();
    }
    
    private static void sleepRandom(int maxSleep) {
        try {
            Thread.sleep(RAND.nextInt(maxSleep));
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted while sleeping");
        }
    }
    
    private static interface MyQueue<T> {
        void push(T t); // Never blocks
        T pop(); // Blocks
    }
    
    private static class MyBlockingQueue<T> implements MyQueue<T> {
        private LinkedList<T> list = new LinkedList<T>();
        
        @Override
        public synchronized void push(T t) {
            System.out.println(System.currentTimeMillis() + ": push()");
            list.add(t);
            notifyAll();
            System.out.println(System.currentTimeMillis() + ": " + this);
        }
        
        @Override
        public synchronized T pop() {
            System.out.println(System.currentTimeMillis() + ":           pop()");
            while (list.isEmpty()) {
                try {
                    System.out.println(System.currentTimeMillis() + ":             waiting");
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted, continuing");
                }
            }
            T t = list.pop();
            System.out.println(System.currentTimeMillis() + ":           " + this);
            return t;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("BlockingQueue [ ");
            if (list.size() > 0) {
                for (int i = 0; i < list.size() - 1; i++) {
                    sb.append(list.get(i) + ", ");
                }
                sb.append(list.get(list.size() - 1));
            }
            sb.append(" ]");
            return sb.toString();
        }
    }
}
