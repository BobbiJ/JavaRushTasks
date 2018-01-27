package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private String name;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                startCookingOrder(queue.take());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private LinkedBlockingQueue<Order> queue;
    private boolean busy;

    public boolean isBusy() {
        return busy;
    }

    @Override
    public String toString() {
        return name;
    }

    public Cook(String name) {

        this.name = name;
    }

//    @Override
//    public void update(Observable o, Object arg) {
//        ConsoleHelper.writeMessage( "Start cooking - " + arg + ", cooking time " + ((Order)arg).getTotalCookingTime() + "min");
//        setChanged();
//        notifyObservers(arg);
//        StatisticManager.getInstance().register(new CookedOrderEventDataRow(((Order) arg).getTablet().toString(),name,((Order)arg).getTotalCookingTime(),((Order)arg).getDishes()));
//    }
    public void startCookingOrder(Order order){
        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order.toString());
        try {
            Thread.sleep(10 * order.getTotalCookingTime());
        } catch (InterruptedException e) {
        }

        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(),
                this.toString(), order.getTotalCookingTime() * 60, order.getDishes()));
        setChanged();
        notifyObservers(order);
        busy = false;
    }


    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }
}
