package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class DepositCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = null;

        try {

            currencyCode = ConsoleHelper.askCurrencyCode();

            CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

            String[] s = ConsoleHelper.getValidTwoDigits(currencyCode);

            int nominal = Integer.parseInt(s[0]);

            int total = Integer.parseInt(s[1]);

            currencyManipulator.addAmount(nominal, total);

        } catch (NumberFormatException e) {

            ConsoleHelper.writeMessage("NumberFormatException");

        }
    }
}
