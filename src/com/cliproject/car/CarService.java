package com.cliproject.car;


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

    public void registerNewCar(Car car) {
        carFileDataAccessService.saveCar(car);
        carArrayDataAccessService.saveCar(car);
    }


    public Car[] getCars(){
        Car[] cars = (dataChoice == 1) ? carFileDataAccessService.getCars()
                : (dataChoice == 2) ? carArrayDataAccessService.getCars()
                : null;
        return cars;
    }

    public void showAllAvailableCars() {
        Car[] carsData = getCars();


        for (int i = 0; i < carsData.length; i++) {
            if (carsData[i] != null && !carsData[i].isBooked()) {
                System.out.println((i + 1) +
                        "-- " +
                        carsData[i].getCompany() +
                        " " +
                        carsData[i].getModel() +
                        " (" +
                        carsData[i].getColor() +
                        ")");
            }
        }
    }

}
