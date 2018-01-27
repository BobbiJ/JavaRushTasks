package com.javarush.task.task27.task2712;


import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();



    public static void main(String[] args) throws InterruptedException {

        Cook cook1 = new Cook("Amigo");
        cook1.setQueue(orderQueue);
        Cook cook2 = new Cook("Bingo");
        cook2.setQueue(orderQueue);

        List tablets = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(orderQueue);
            tablets.add(tablet);

//            tablet.addObserver(orderManager);
//            tablet.addObserver(orderManager);
        }
        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);
        Thread thread1 = new Thread(cook1);
        thread1.start();
        Thread thread2 = new Thread(cook2);
        thread2.start();


        /*
        tablet.addObserver(cook);
        cook.addObserver(new Waiter());
        Order order = tablet.createOrder();
        */
        Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        //thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            return;
        }
        thread.interrupt();
        DirectorTablet director = new DirectorTablet();
        director.printAdvertisementProfit();
        director.printCookWorkloading();
        director.printActiveVideoSet();
        director.printArchivedVideoSet();


    }




}
