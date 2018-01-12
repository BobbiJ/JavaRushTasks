package com.javarush.task.task22.task2208;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {

    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry <String, String> pair : params.entrySet()){
            if (pair.getKey()!= null && pair.getValue() != null){
                stringBuilder.append(pair.getKey()).append(" = '").append(pair.getValue()).append("' and ");
            }

        }
        if (stringBuilder.length() > 5)
          stringBuilder.delete(stringBuilder.length() - 5, stringBuilder.length());

        return stringBuilder.toString();
    }
}
