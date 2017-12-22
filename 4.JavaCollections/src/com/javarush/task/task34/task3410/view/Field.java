package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObject;
import com.javarush.task.task34.task3410.model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

public class Field extends JPanel{
    private View view;

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    private EventListener eventListener;
    public Field (View view){
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

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



   public class KeyHandler extends KeyAdapter{

       public void keyPressed(KeyEvent e) {
           switch (e.getKeyCode()) {
               case (KeyEvent.VK_LEFT):
                   eventListener.move(Direction.LEFT);
                   break;
               case (KeyEvent.VK_RIGHT):
                   eventListener.move(Direction.RIGHT);
                   break;
               case (KeyEvent.VK_UP):
                   eventListener.move(Direction.UP);
                   break;
               case (KeyEvent.VK_DOWN):
                   eventListener.move(Direction.DOWN);
                   break;
               case (KeyEvent.VK_R):
                   eventListener.restart();
                   break;
           }

       }

   }

}