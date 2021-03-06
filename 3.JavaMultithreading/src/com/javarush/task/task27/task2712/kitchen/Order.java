package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    @Override
    public String toString() {
        return dishes.isEmpty() ? "" : "Your order: " + dishes + " of " + tablet;
    }

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }
    public int getTotalCookingTime(){
        int totalCookTime = 0;
        for (Dish dish : dishes){
            totalCookTime +=dish.getDuration();
        }
        return totalCookTime;
    }

    public Tablet getTablet() {
        return tablet;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public boolean isEmpty(){
        return dishes.isEmpty();
    }
    protected void initDishes() throws IOException {

        this.dishes = ConsoleHelper.getAllDishesForOrder();
        ConsoleHelper.writeMessage(this.toString());
    }
}
