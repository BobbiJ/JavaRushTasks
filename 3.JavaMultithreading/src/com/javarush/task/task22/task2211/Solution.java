package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
      FileInputStream fileInputStream = new FileInputStream(args[0]);
      FileOutputStream fileOutputStream = new FileOutputStream(args[1]);

      byte [] buffer = new byte[fileInputStream.available()];
      fileInputStream.read(buffer);
      String s1 = new String (buffer,"Windows-1251");
      fileOutputStream.write(s1.getBytes("UTF-8"));




      fileInputStream.close();
      fileOutputStream.close();
    }
}
