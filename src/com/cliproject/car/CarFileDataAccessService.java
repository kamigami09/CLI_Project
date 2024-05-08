package com.cliproject.car;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CarFileDataAccessService implements CarDAO {

    private static ArrayList<Car> cars = new ArrayList<>();
    private static File csvFile = new File("src/com/cliproject/car/Cars.csv");
    private static PrintWriter writer;

    static {
        try {
            writer = new PrintWriter(new FileOutputStream(csvFile));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveCar(Car car) {
        writer.println(car.getRegis() + "," + car.getCompany() + "," + car.getModel() + "," + car.getColor());
        writer.flush();
    }

    @Override
    public ArrayList<Car> getCars() {
        try {
            Scanner scanner = new Scanner(csvFile);
            scanner.useDelimiter("[,\n]");

            while (scanner.hasNext()){
                String regis = scanner.next();
                String company = scanner.next();
                String model = scanner.next();
                String color = scanner.next();
                Car car = new Car(regis, company, model, color);
                cars.add(car);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return cars;
    }
}