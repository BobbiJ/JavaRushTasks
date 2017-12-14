package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;

public class LoginCommand implements Command{

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {
        String enteredNameCard = "";
        String enteredPinCard;
        while (true){
            try {
                System.out.println(res.getString("before"));
                ConsoleHelper.writeMessage(res.getString("specify.data"));
                enteredNameCard = ConsoleHelper.readString();
                ConsoleHelper.writeMessage("Enter pinCard");
                enteredPinCard = ConsoleHelper.readString();
                if (validCreditCards.getString(enteredNameCard).equals(enteredPinCard) && validCreditCards.containsKey(enteredNameCard)){
                    System.out.printf(res.getString("success.format"), enteredNameCard);
                    return;
                }
            } catch (Exception e) {
                System.out.printf(res.getString("not.verified.format"), enteredNameCard);
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }
        }
    }
}
