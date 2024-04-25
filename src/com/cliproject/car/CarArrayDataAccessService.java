package com.cliproject.car;


import java.util.Arrays;

public class CarArrayDataAccessService implements CarDAO {
    private static Car[] Cars;
    private static int nextAvailableSpot=0;
    static {
        Cars= new Car[CAPACITY];
    }

    @Override
    public void saveCar(Car car){
        if(nextAvailableSpot + 1 >= CAPACITY){
            //grow db
        }
        Cars[nextAvailableSpot++]=car;
    }
    @Override
    public Car[] getCars(){
        return Cars;
    }
}
