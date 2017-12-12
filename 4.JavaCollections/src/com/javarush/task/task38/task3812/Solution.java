package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)){
            PrepareMyTest mTest = (PrepareMyTest)c.getAnnotation(PrepareMyTest.class);
            for (String s1:mTest.fullyQualifiedNames()){
                System.out.println(s1);
            }
            return true;
        }


        return false;
    }

    public static boolean printValues(Class c) {

        if (c.isAnnotationPresent(PrepareMyTest.class)){
            PrepareMyTest mTest = (PrepareMyTest)c.getAnnotation(PrepareMyTest.class);
            for (Class s1:mTest.value()){
                System.out.println(s1.getSimpleName());
            }
            return true;
        }


        return false;
    }
}
