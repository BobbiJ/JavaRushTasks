package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        boolean isNumber = false;
        for (int i = 2; i < 37; i++) {
            try {
                new BigInteger(args[0],i);
                System.out.println(i);
                isNumber = true;
                break;
            } catch (Exception e){
            }
        }
        if (!isNumber) System.out.println("incorrect");
    }
}