package com.cliproject.car;


public class CarDAO {
    private static Car[] cars;
    private static int nextAvailableSpot=0;
    private static final int Capacity=5;

    static {
        cars = new Car[Capacity];
    }

    public void saveCar(Car car){
        if (nextAvailableSpot + 1 >= Capacity){
        //grow db
        }
        cars[nextAvailableSpot++]=car;
    }

    public Car[] selectAllCars(){
        return cars;
    }
}
