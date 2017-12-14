package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;


/**
 * Created by Maxim Taxants 14/12/2017
 */

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "withdraw_en");
    public void execute() throws InterruptOperationException {
        System.out.println(res.getString("before"));
        String currencyCode = null;
        currencyCode = ConsoleHelper.askCurrencyCode();

        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        while (true) {
            try {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                int withdrowAmount = Integer.parseInt(ConsoleHelper.readString());
                if (currencyManipulator.isAmountAvailable(withdrowAmount)){
                       currencyManipulator.withdrawAmount(withdrowAmount);
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"),withdrowAmount,currencyCode));
                        return;
                    }
            } catch (Exception e) {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
               continue;
            }
        }
        }

    }

