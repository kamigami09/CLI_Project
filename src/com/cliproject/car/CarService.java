package com.cliproject.car;


public class CarService {
    CarDAO carDAO = new CarDAO();

    public void registerNewCar(Car car) {
        carDAO.saveCar(car);
    }

    public Car[] getCars() {
        return carDAO.selectAllCars();
    }

    public void showAllAvailableCars() {
        System.out.println("\nAvailable Cars:");
        for (Car x : getCars()) {
            if (!x.isBooked()) {
                System.out.println("-- " + x.getCompany() + " " + x.getModel() + " (" + x.getColor() + ")");
            }
        }
    }

    public Car[] getAvailableCarsByModel(String model) {
        int count = 0;
        for (Car car : getCars()) {
            if (car != null && car.getModel().equalsIgnoreCase(model) && !car.isBooked()) {
                count++;
            }
        }

        Car[] availableCars = new Car[count];
        int availableCarsSpot = 0;
        for (Car car : getCars()) {
            if (car != null && car.getModel().equalsIgnoreCase(model) && !car.isBooked()) {
                availableCars[availableCarsSpot] = car;
                availableCarsSpot++;
            }
        }
        return availableCars;
    }

}
