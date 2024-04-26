package com.cliproject.car;


public class CarService {
    private final CarArrayDataAccessService carArrayDataAccessService;
    private final CarFileDataAccessService carFileDataAccessService;

    public CarService(CarArrayDataAccessService carArrayDataAccessService, CarFileDataAccessService carFileDataAccessService) {
        this.carArrayDataAccessService = carArrayDataAccessService;
        this.carFileDataAccessService = carFileDataAccessService;
    }

    public void registerNewCarInFile(Car car) {
        carFileDataAccessService.saveCar(car);
    }

    public Car[] getCarsFromFile() {
        return carFileDataAccessService.getCars();
    }
    public void registerNewCarInArray(Car car) {
        carArrayDataAccessService.saveCar(car);
    }

    public Car[] getCarsFromArray() {
        return carArrayDataAccessService.getCars();
    }

    public void showAllAvailableCars() {
        System.out.println("\nAvailable Cars:");
        for (Car x : getCarsFromFile()) {
            if (!x.isBooked()) {
                System.out.println("-- " + x.getCompany() + " " + x.getModel() + " (" + x.getColor() + ")");
            }
        }
    }

    public Car[] getAvailableCarsByModel(String model) {
        int count = 0;
        Car[] allCars = getCarsFromFile();
        for (Car car : allCars) {
            if (car != null && car.getModel().equalsIgnoreCase(model) && !car.isBooked()) {
                count++;
            }
        }

        Car[] availableCars = new Car[count];
        int availableCarsSpot = 0;
        for (Car car : allCars) {
            if (car != null && car.getModel().equalsIgnoreCase(model) && !car.isBooked()) {
                availableCars[availableCarsSpot] = car;
                availableCarsSpot++;
            }
        }
        return availableCars;
    }

}
