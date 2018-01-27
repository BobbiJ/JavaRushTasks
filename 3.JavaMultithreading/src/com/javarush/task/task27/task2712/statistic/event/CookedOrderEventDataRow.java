package com.javarush.task.task27.task2712.statistic.event;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.util.Date;
import java.util.List;

public class CookedOrderEventDataRow implements EventDataRow {

    private String tabletName;
    private String cookName;
    private int cookingTimeSeconds;
    private Date currentDate;

    public CookedOrderEventDataRow(String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishs) {
        this.tabletName = tabletName;
        this.cookName = cookName;
        this.cookingTimeSeconds = cookingTimeSeconds;
        this.cookingDishs = cookingDishs;
        currentDate = new Date();
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    public String getCookName() {
        return cookName;
    }

    @Override
    public int getTime() {
        return cookingTimeSeconds;
    }

    private List<Dish> cookingDishs;

    @Override
    public EventType getType() {
        return EventType.COOKED_ORDER;
    }
}
