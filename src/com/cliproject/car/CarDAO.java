package com.cliproject.car;



public interface CarDAO {
    int CAPACITY = 100;
    Car[] getCars();
    void saveCar(Car car);
}
