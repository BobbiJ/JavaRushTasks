package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;



/**
 * Created by Maxim Taxants 14/12/2017
 */

public class ConsoleHelper {

    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName()+".resources.common_en");
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    public static void  writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException{
        String input = "";

        try {
            input = bis.readLine();

            if (input.equalsIgnoreCase("exit")) {
                throw new InterruptOperationException();
            }
        } catch (IOException e) {}

        return input;

    }


    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        while (true) {
            String s1 = null;
                s1 = readString();
            if (s1.length()==3)return s1.toUpperCase();
            else System.out.println("Try again pls");
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(res.getString("choose.denomination.and.count.format"));
        String[] input;
        while (true) {
            input = readString().split(" ");
            int nominal = 0;
            int total = 0;
            try {
                nominal = Integer.parseInt(input[0]);
                total = Integer.parseInt(input[1]);

            } catch (Exception e) {
                writeMessage("Error, Repeat again:");
                continue;

            }
            if (nominal <= 0 || total <= 0) {
                writeMessage("Error, Repeat again (nominal | count <=0:");
                continue;
            }
            break;

        }

        return input;

    }
    public static Operation askOperation() throws InterruptOperationException {
        writeMessage("Enter pretarion number " + "\n" + "1 - INFO" + "\n" + "2 - DEPOSIT" + "\n" + "3 - WITHDRAW" + "\n" + "4 - EXIT");

        while (true)
            try {
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
            } catch (Exception e){
                throw new InterruptOperationException();
            }
    }

    public static void printExitMessage (){
        ConsoleHelper.writeMessage("Bye");
    }

}
