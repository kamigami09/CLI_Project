package com.cliproject.booking;

import com.cliproject.car.Car;
import com.cliproject.user.Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class BookingFileDataAccessService implements BookingDAO {

    private static Booking[] Bookings;
    private static int nextAvailableSpot=0;
    static {
        Bookings = new Booking[CAPACITY];
    }
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
        writer.println(booking.getCar().getCompany() + "," + booking.getCar().getModel() + "," + booking.getCar().getColor() + "," + booking.getClient().getId() + "," + booking.getClient().getName() + "," + booking.getClient().getAddress() + "," + booking.getClient().getEmail() + "," + booking.getStartDate() + "," + booking.getEndDate());
        writer.flush();
    }

    @Override
    public Booking[] getBookings() {
        try {
            Scanner scanner = new Scanner(csvFile);
            while (scanner.hasNext()){
                String input = scanner.nextLine();
                String[] data = input.split(",");
                String company = data[0];
                String model = data[1];
                String color = data[2];
                String id = data[3];
                String name = data[4];
                String address = data[5];
                String email = data[6];
                Car car = new Car(company, model, color);
                Client client = new Client(id, name, address, email);
                String startDate = data[7];
                String endDate = data[8];
                Booking Booking = new Booking(car, client, startDate, endDate);
                Bookings[nextAvailableSpot++] = Booking;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return Bookings;
    }
}
