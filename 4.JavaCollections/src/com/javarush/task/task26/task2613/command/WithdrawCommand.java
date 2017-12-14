package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Map;

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = null;
        currencyCode = ConsoleHelper.askCurrencyCode();

        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        while (true) {
            try {
                ConsoleHelper.writeMessage("Entry amount");
                int withdrowAmount = Integer.parseInt(ConsoleHelper.readString());
                if (currencyManipulator.isAmountAvailable(withdrowAmount)){
                       currencyManipulator.withdrawAmount(withdrowAmount);
                        return;
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        }

    }

