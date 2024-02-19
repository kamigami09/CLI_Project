package com.cliproject.car;

import java.util.Arrays;

public class CarService {
    CarDAO carDAO = new CarDAO();

    public void registerNewCar(Car car) {
        carDAO.saveCar(car);
    }

    public Car[] getCars() {
        return carDAO.selectAllCars();
    }

    public void showAllCars() {
        System.out.println(Arrays.toString(getCars()));
    }

    public void showAllBookedCars() {
        for (Car x : getCars()) {
            if (x.isBooked() == true) System.out.println(x.getCompany() + x.getModel() + x.getColor());
        }
    }

    public void showAllAvailableCars() {
        for (Car x : getCars()) {
            if (x.isBooked() == false) System.out.println("--" + x.getCompany() + x.getModel() + x.getColor());
        }
    }

    public void bookACar(Car car){
        car.setBooked(true);
    }
}
