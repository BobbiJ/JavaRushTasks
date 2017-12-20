package com.javarush.task.task34.task3410.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    public LevelLoader(Path levels) {
    }

    public GameObjects getLevel(int level){
        Set<Wall> walls = new HashSet<>();
        walls.add(new Wall(10,30));
        walls.add(new Wall(20,30));
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(40,40));
        Set <Home> homes = new HashSet<>();
        homes.add(new Home(30,10));
        GameObjects gobjs = new GameObjects(walls,boxes,homes,new Player(10,10));

        return gobjs;
    }
}
