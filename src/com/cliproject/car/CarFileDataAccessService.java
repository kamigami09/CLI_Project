package com.cliproject.car;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class CarFileDataAccessService implements CarDAO {

    private static Car[] Cars;
    private static int nextAvailableSpot=0;
    static {
        Cars = new Car[CAPACITY];
    }
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
    public void saveCar(Car Car) {
        writer.println(Car.getCompany() + "," + Car.getModel() + "," + Car.getColor());
        writer.flush();
    }

    @Override
    public Car[] getCars() {
        try {
            Scanner scanner = new Scanner(csvFile);
            while (scanner.hasNext()){
                String input = scanner.nextLine();
                String[] data = input.split(",");
                String company = data[0];
                String model = data[1];
                String color = data[2];
                Car Car = new Car(company, model, color);
                Cars[nextAvailableSpot++] = Car;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return Cars;
    }
}
