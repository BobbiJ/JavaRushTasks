package com.javarush.task.task40.task4012;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/* 
Полезные методы DateTime API
*/

public class Solution {
    public static void main(String[] args) {

    }

    public static boolean isLeap(LocalDate date) {
        return date.isLeapYear();

    }

    public static boolean isBefore(LocalDateTime dateTime) {
        LocalDateTime ldt = LocalDateTime.now();
        return dateTime.isBefore(ldt);

    }

    public static LocalTime addTime(LocalTime time, int n, ChronoUnit chronoUnit) {
        return time.plus(n, chronoUnit);



    }

    public static Period getPeriodBetween(LocalDate firstDate, LocalDate secondDate) {

        if (firstDate.compareTo(secondDate) > 0){
            return Period.between(secondDate, firstDate);
        } else return Period.between(firstDate,secondDate);

    }
}
