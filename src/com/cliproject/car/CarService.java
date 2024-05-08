package com.cliproject.car;


import java.util.ArrayList;

public class CarService {
    private final CarListDataAccessService carListDataAccessService;
    private final CarFileDataAccessService carFileDataAccessService;
    private int dataChoice;

    public CarService(CarListDataAccessService carListDataAccessService,
                      CarFileDataAccessService carFileDataAccessService,
                      int dataChoice) {
        this.carListDataAccessService = carListDataAccessService;
        this.carFileDataAccessService = carFileDataAccessService;
        this.dataChoice = dataChoice;
    }

    public void registerNewCar(Car car) {
        carFileDataAccessService.saveCar(car);
        carListDataAccessService.saveCar(car);
    }


    public ArrayList<Car> getCars(){
        ArrayList<Car> cars = (dataChoice == 1) ? carFileDataAccessService.getCars()
                : (dataChoice == 2) ? carListDataAccessService.getCars()
                : null;
        return cars;
    }

}
