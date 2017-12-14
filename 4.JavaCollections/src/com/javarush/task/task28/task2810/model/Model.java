package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.view.View;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Model {
    View view;
    Provider [] providers;

    public Model(View view, Provider ... providers) {
        this.view = view;
        this.providers = providers;
        if (providers == null || view == null || providers.length == 0) throw new IllegalArgumentException();
    }


    public void selectCity (String city){
        List<Vacancy> allVacancys = new ArrayList<>();

        try {
            for (Provider provider : providers) {
                allVacancys.addAll(provider.getJavaVacancies(city));
            }
        }catch (NullPointerException e){}

        view.update(allVacancys);
    }
    }

