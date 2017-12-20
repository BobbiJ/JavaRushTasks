package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GRAY);
        graphics.drawLine(getX() - Model.FIELD_CELL_SIZE / 2,getY() + Model.FIELD_CELL_SIZE / 2,getX() + Model.FIELD_CELL_SIZE / 2,getY() + Model.FIELD_CELL_SIZE / 2 );
        graphics.drawLine(getX() + Model.FIELD_CELL_SIZE / 2,getY() + Model.FIELD_CELL_SIZE / 2,getX() + Model.FIELD_CELL_SIZE / 2,getY() - Model.FIELD_CELL_SIZE / 2 );
        graphics.drawLine(getX() + Model.FIELD_CELL_SIZE / 2,getY() - Model.FIELD_CELL_SIZE / 2,getX() -Model.FIELD_CELL_SIZE / 2,getY() - Model.FIELD_CELL_SIZE / 2 );
        graphics.drawLine(getX() - Model.FIELD_CELL_SIZE / 2,getY() - Model.FIELD_CELL_SIZE / 2,getX() - Model.FIELD_CELL_SIZE / 2,getY() + Model.FIELD_CELL_SIZE / 2 );
        graphics.drawLine(getX() - Model.FIELD_CELL_SIZE / 2,getY() - Model.FIELD_CELL_SIZE / 2,getX() + Model.FIELD_CELL_SIZE / 2,getY() + Model.FIELD_CELL_SIZE / 2 );
        graphics.fillRect(getX(),getY(),getWidth(),getHeight());
    }
}
