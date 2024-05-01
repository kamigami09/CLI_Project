package com.cliproject.car;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class CarFileDataAccessService implements CarDAO {

    private static Car[] Cars;

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
    public void saveCar(Car car) {
        writer.println(car.getCompany() + "," + car.getModel() + "," + car.getColor() + "," + car.isBooked());
        writer.flush();
    }

    @Override
    public Car[] getCars() {
        int index = 0;
        try {
            Scanner scanner = new Scanner(csvFile);
            while (scanner.hasNext()){
                String input = scanner.nextLine();
                String[] data = input.split(",");
                String company = data[0];
                String model = data[1];
                String color = data[2];
                boolean isBooked = Boolean.parseBoolean(data[3]);
                Car car = new Car(company, model, color);
                car.setBooked(isBooked);
                Cars[index++] = car;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return Cars;
    }
}
