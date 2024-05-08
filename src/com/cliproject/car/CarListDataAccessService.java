package com.cliproject.car;


import java.util.ArrayList;

public class CarListDataAccessService implements CarDAO {
    private static ArrayList<Car> cars = new ArrayList<>();

    @Override
    public void saveCar(Car car){
        cars.add(car);
    }
    @Override
    public ArrayList<Car> getCars(){
        return cars;
    }
}
