package com.cliproject.car;



public class CarArrayDataAccessService implements CarDAO {
    private static Car[] Cars;
    private static int nextAvailableSpot=0;
    static {
        Cars= new Car[CAPACITY];
    }

    @Override
    public void saveCar(Car car){
        if(nextAvailableSpot + 1 >= CAPACITY){
            throw new ArrayIndexOutOfBoundsException("Cannot add more cars as the database is full");
            // GROW DB
        }
        Cars[nextAvailableSpot++]=car;
    }
    @Override
    public Car[] getCars(){
        return Cars;
    }
}
