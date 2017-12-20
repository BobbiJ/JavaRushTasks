package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {
    @Override
    public void draw(Graphics graphics) {

        graphics.setColor(Color.GREEN);
        graphics.drawOval(getX(),getY(),getWidth(),getHeight());
        graphics.fillOval(getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public void move(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }

    public Player(int x, int y) {
        super(x, y);
    }
}
