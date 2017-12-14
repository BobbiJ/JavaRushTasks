package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "info_en");

    @Override
    public void execute() {
        Collection<CurrencyManipulator> map = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        boolean mny = false;
        System.out.println(res.getString("before"));
            for (CurrencyManipulator currencyManipulator: map){
                if (currencyManipulator.hasMoney()) {
                    System.out.println(currencyManipulator.getCurrencyCode().toUpperCase() + " - " + currencyManipulator.getTotalAmount());
                    mny = true;
                }
            }
        if (!mny)
        ConsoleHelper.writeMessage(res.getString("no.money"));
    }
}
