package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hippodrome {
    public List<Horse> getHorses() {
        return horses;
    }
    private List<Horse> horses;
    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }
    static Hippodrome game;
    public static void main(String[] args) {
        game = new Hippodrome(Arrays.asList(new Horse ("Elena", 3,0),
                new Horse ("Hurricane", 3,0),
                new Horse("Lightning", 3,0)));
        game.run();
        game.printWinner();
    }

    public void run (){
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void move () {
        for (Horse horse : horses){
            horse.move();
        }
    }
    public void print (){
        for (Horse horse : horses){
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
    public Horse getWinner (){
        double maxDistance = 0;
        Horse winner = null;
        for (Horse horse : horses){
            if (horse.getDistance() > maxDistance) {
                maxDistance = horse.getDistance();
                winner = horse;
            }
        }


        return winner;
    }
    public void printWinner (){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
