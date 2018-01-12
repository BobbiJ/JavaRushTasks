package com.javarush.task.task23.task2305;

import java.util.Collections;

/*
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution solution1 = new Solution();
        InnerClass innerClass1 = solution1.new InnerClass();
        InnerClass innerClass2 = solution1.new InnerClass();
        solution1.innerClasses[0] = innerClass1;
        solution1.innerClasses[1] = innerClass2;
        Solution solution12 = new Solution();
        InnerClass innerClass12 = solution12.new InnerClass();
        InnerClass innerClass22 = solution12.new InnerClass();
        solution12.innerClasses[0] = innerClass1;
        solution12.innerClasses[1] = innerClass2;
        Solution [] solutions = {solution1,solution12};
        return solutions;
    }

    public static void main(String[] args) {

    }
}
