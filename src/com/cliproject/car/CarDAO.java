package com.cliproject.car;



public interface CarDAO {
    int CAPACITY = 10;
    Car[] getCars();
    void saveCar(Car car);
}
