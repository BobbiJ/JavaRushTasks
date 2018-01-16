package com.javarush.task.task29.task2909.car;

import java.util.Date;

public abstract class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    protected Car(int type, int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
        this.type = type;
    }
    public double getWinterConsumption(int length){
        return length * winterFuelConsumption + winterWarmingUp;
    }
    public double getSummerConsumption(int length){
        return length * summerFuelConsumption;
    }



    public static Car create (int type, int numberOfPassengers){
        switch (type){
            case 0 : return new Truck(numberOfPassengers);
            case 1 : return new Sedan(numberOfPassengers);
            case 2 : return new Cabriolet(numberOfPassengers);
        }
        return null;
    }
    public boolean isSummer(Date date , Date summerStart, Date summerEnd){
        return date.getTime() <= summerEnd.getTime() && date.getTime() >= summerStart.getTime();


    }
    public void fill(double numberOfLiters) throws Exception {
        if (numberOfLiters < 0) throw new Exception();
        fuel += numberOfLiters;

    }

    private boolean canPassengersBeTransferred(){
        return(isDriverAvailable() & fuel > 0);
    }


    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        double consumption;
        if (!isSummer(date,SummerStart,SummerEnd)) {
            consumption = getWinterConsumption(length);
        } else {
            consumption = getSummerConsumption(length);
        }
        return consumption;
    }

    public int getNumberOfPassengersCanBeTransferred() {
        if (canPassengersBeTransferred()) return numberOfPassengers;
        else return 0;

    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        fastenDriverBelt();
        if (numberOfPassengers > 0) {
            fastenPassengersBelts();
        }
    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }

    public abstract int getMaxSpeed();
//        if (type == TRUCK)
//            return Truck.MAX_TRUCK_SPEED;
//        if (type == SEDAN)
//            return Sedan.MAX_SEDAN_SPEED;
//        return Cabriolet.MAX_CABRIOLET_SPEED;

}