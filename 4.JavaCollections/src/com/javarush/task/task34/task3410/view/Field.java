package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.GameObject;
import com.javarush.task.task34.task3410.model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class Field extends JPanel{
    private View view;

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    private EventListener eventListener;
    public Field (View view){
        this.view = view;
    }


   public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,500,500);
        GameObjects gameObjects = view.getGameObjects();
        Set<GameObject> gameObjectSet =gameObjects.getAll();
        for (GameObject gameObject:gameObjectSet){
            gameObject.draw(g);
        }
   }
}
