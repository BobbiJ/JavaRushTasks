package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.ArrayList;
import java.util.Collection;

class InfoCommand implements Command {
    @Override
    public void execute() {
        Collection<CurrencyManipulator> map = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        boolean mny = false;

            for (CurrencyManipulator currencyManipulator: map){
                if (currencyManipulator.hasMoney()) {
                    System.out.println(currencyManipulator.getCurrencyCode().toUpperCase() + " - " + currencyManipulator.getTotalAmount());
                    mny = true;
                }
            }
        if (!mny)
        ConsoleHelper.writeMessage("No money available.");
    }
}
