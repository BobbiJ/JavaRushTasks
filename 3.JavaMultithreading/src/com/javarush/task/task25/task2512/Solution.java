package com.javarush.task.task25.task2512;

import java.util.Stack;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        Stack<Throwable> exceprionsStack = new Stack<>();
        while (e != null){
        exceprionsStack.push(e);
        e = e.getCause();
        }

        while (!exceprionsStack.empty()){
            Throwable except = exceprionsStack.pop();
            System.out.println(except.getClass().getName() + ": " + except.getMessage());
        }


    }

    public static void main(String[] args) {
        try {
            throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));

        } catch (Exception e) {
            Solution solution = new Solution();
            solution.uncaughtException(Thread.currentThread(), e);
        }
    }
}
