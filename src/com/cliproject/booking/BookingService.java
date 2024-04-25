package com.cliproject.booking;

import com.cliproject.car.Car;
import com.cliproject.car.CarService;
import com.cliproject.user.Client;
import com.cliproject.user.ClientService;

import java.util.Arrays;
import java.util.Scanner;

public class BookingService {
    BookingDAO bookingDAO = new BookingDAO();
    public void registerNewBooking(Booking booking){
        bookingDAO.saveBooking(booking);
        booking.getCar().setBooked(true);
    }

    public void showBookings(){
        System.out.println("\nBooked Cars: ");
        for (Booking x : bookingDAO.selectAllBookings())
            if (x != null){
            System.out.println("-- " + x.getCar().getCompany() + " " + x.getCar().getModel() + " (" + x.getCar().getColor() + ") " + x.getClient().getName() + " " + x.getClient().getEmail() + " " + x.getClient().getAddress() + " " + x.getStartDate() + " " + x.getEndDate());
        }
    }

    public void bookACar(Scanner scanner, CarService carService, ClientService clientService, BookingService bookingService){
        System.out.println("\nWhat is the model of the car you want to rent: ");
        String model = scanner.nextLine();

        Car[] availableCars = carService.getAvailableCarsByModel(model);

        if (availableCars.length == 0) {
            System.out.println("No available cars found with the specified model.");
            return;
        }
        for (int i = 0; i < availableCars.length; i++)
        {
            System.out.println((i + 1) + "." + availableCars[i].getCompany() + " " + availableCars[i].getModel() + " " + availableCars[i].getColor());
        }

        System.out.println("\nEnter the number of the corresponding car you want to book");
        int carChoice = scanner.nextInt();
        scanner.nextLine();

        Car selectedCar = availableCars[carChoice - 1];

        clientService.showClients();
        System.out.println("\nSelect a client ID in these client: ");
        String clientIdChoice = scanner.nextLine();
        Client [] availableClients = clientService.getClients();
        for (Client availableClient : availableClients) {
            if (clientIdChoice.equals(availableClient.getId())){
                System.out.print("Enter the start date of the booking: ");
                String startDate = scanner.nextLine();
                System.out.print("Enter the end date of the booking: ");
                String endDate = scanner.nextLine();
                Booking booking = new Booking(selectedCar, availableClient, startDate, endDate);
                bookingService.registerNewBooking(booking);
                System.out.println("Booking successful!");
            }
        }

    }
}
