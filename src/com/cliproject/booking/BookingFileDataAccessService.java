package com.cliproject.booking;

import com.cliproject.car.Car;
import com.cliproject.client.Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingFileDataAccessService implements BookingDAO {

    private static ArrayList<Booking> bookings = new ArrayList<>();

    private static File csvFile = new File("src/com/cliproject/booking/Bookings.csv");
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
    public void saveBooking(Booking booking) {
        writer.println(booking.getCar().getRegis() + "," + booking.getCar().getCompany() + "," + booking.getCar().getModel() + "," + booking.getCar().getColor() + "," + booking.getClient().getId() + "," + booking.getClient().getName() + "," + booking.getClient().getAddress() + "," + booking.getClient().getEmail() + "," + booking.getStartDate() + "," + booking.getEndDate());
        writer.flush();
    }

    @Override
    public ArrayList<Booking> getBookings() {
        try {
            Scanner scanner = new Scanner(csvFile);
            scanner.useDelimiter("[,\n]");

            while (scanner.hasNext()){

                //Reading the data from the file
                String regis = scanner.next();
                String company = scanner.next();
                String model = scanner.next();
                String color = scanner.next();
                String id = scanner.next();
                String name = scanner.next();
                String address = scanner.next();
                String email = scanner.next();
                String startDate = scanner.next();
                String endDate = scanner.next();


                Car car = new Car(regis, company, model, color);
                Client client = new Client(id, name, address, email);
                Booking booking = new Booking(car, client, startDate, endDate);
                bookings.add(booking);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return bookings;
    }
}
