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

}
