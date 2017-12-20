package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {


        graphics.setColor(Color.RED);
        graphics.drawLine(getX() - Model.FIELD_CELL_SIZE / 2,getY() + Model.FIELD_CELL_SIZE / 2,getX() + Model.FIELD_CELL_SIZE / 2,getY() + Model.FIELD_CELL_SIZE / 2 );
        graphics.drawLine(getX() + Model.FIELD_CELL_SIZE / 2,getY() + Model.FIELD_CELL_SIZE / 2,getX() + Model.FIELD_CELL_SIZE / 2,getY() - Model.FIELD_CELL_SIZE / 2 );
        graphics.drawLine(getX() + Model.FIELD_CELL_SIZE / 2,getY() - Model.FIELD_CELL_SIZE / 2,getX() -Model.FIELD_CELL_SIZE / 2,getY() - Model.FIELD_CELL_SIZE / 2 );
        graphics.drawLine(getX() - Model.FIELD_CELL_SIZE / 2,getY() - Model.FIELD_CELL_SIZE / 2,getX() - Model.FIELD_CELL_SIZE / 2,getY() + Model.FIELD_CELL_SIZE / 2 );
        graphics.drawLine(getX() - Model.FIELD_CELL_SIZE / 2,getY() - Model.FIELD_CELL_SIZE / 2,getX() + Model.FIELD_CELL_SIZE / 2,getY() + Model.FIELD_CELL_SIZE / 2 );
    }

    @Override
    public void move(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }
}
