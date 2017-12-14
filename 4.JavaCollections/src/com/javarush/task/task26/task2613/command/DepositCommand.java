package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;


/**
 * Created by Maxim Taxants 14/12/2017
 */

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = null;

        try {

            currencyCode = ConsoleHelper.askCurrencyCode();
            System.out.println(res.getString("before"));
            CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

            String[] s = ConsoleHelper.getValidTwoDigits(currencyCode);

            int nominal = Integer.parseInt(s[0]);

            int total = Integer.parseInt(s[1]);
            System.out.println(String.format(res.getString("success.format"),nominal*total,currencyCode.toUpperCase()));
            currencyManipulator.addAmount(nominal, total);

        } catch (NumberFormatException e) {

            ConsoleHelper.writeMessage(res.getString("invalid.data"));

        }
    }
}
