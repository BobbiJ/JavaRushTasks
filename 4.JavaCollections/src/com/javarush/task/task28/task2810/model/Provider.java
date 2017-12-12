package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.List;



/**
 Created by Maxim Taxants 12.12.2017
 */
public class Provider {
   private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Provider(Strategy strategy) {

        this.strategy = strategy;
    }

    public List<Vacancy> getJavaVacancies(String searchString){

        return strategy.getVacancies(searchString);
    }
}
