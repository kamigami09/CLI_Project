package com.cliproject.car;


import java.util.ArrayList;

public interface CarDAO {
    ArrayList<Car> getCars();
    void saveCar(Car car);
}
