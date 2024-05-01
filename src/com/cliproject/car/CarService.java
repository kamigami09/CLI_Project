package com.cliproject.car;


import java.util.Scanner;

public class CarService {
    private final CarArrayDataAccessService carArrayDataAccessService;
    private final CarFileDataAccessService carFileDataAccessService;
    private int dataChoice;

    public CarService(CarArrayDataAccessService carArrayDataAccessService,
                      CarFileDataAccessService carFileDataAccessService,
                      int dataChoice) {
        this.carArrayDataAccessService = carArrayDataAccessService;
        this.carFileDataAccessService = carFileDataAccessService;
        this.dataChoice = dataChoice;
    }

    public Car[] getCarsFromFile() {
        return carFileDataAccessService.getCars();
    }
    public void registerNewCar(Car car) {
        carFileDataAccessService.saveCar(car);
        carArrayDataAccessService.saveCar(car);
    }

    public Car[] getCarsFromArray() {
        return carArrayDataAccessService.getCars();
    }

    public void showAllAvailableCars() {
        Car[] carsData;

        carsData = (dataChoice == 1) ? getCarsFromFile()
                : (dataChoice == 2) ? getCarsFromArray()
                : null;

        if(carsData == null) {
            System.out.println("Invalid choice.");
            return;
        }

        for (int i = 0; i < carsData.length; i++) {
            if (carsData[i] != null && !carsData[i].isBooked()) {
                System.out.println((i + 1) + "-- " + carsData[i].getCompany() + " " + carsData[i].getModel() + " (" + carsData[i].getColor() + ")");
            }
        }
    }

}
