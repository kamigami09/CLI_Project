package com.cliproject.booking;

import com.cliproject.car.Car;
import com.cliproject.car.CarService;
import com.cliproject.client.Client;
import com.cliproject.client.ClientService;

import java.util.Scanner;

public class BookingService {

    private final BookingArrayDataAccessService bookingArrayDataAccessService;
    private final BookingFileDataAccessService bookingFileDataAccessService;
    private final int dataChoice;

    public BookingService(BookingArrayDataAccessService bookingArrayDataAccessService,
                          BookingFileDataAccessService bookingFileDataAccessService,
                          int dataChoice) {
        this.bookingArrayDataAccessService = bookingArrayDataAccessService;
        this.bookingFileDataAccessService = bookingFileDataAccessService;
        this.dataChoice = dataChoice;
    }

    public void registerNewBooking(Booking booking){
        bookingArrayDataAccessService.saveBooking(booking);
        bookingFileDataAccessService.saveBooking(booking);
    }

    public Booking[] getBookings(){
        Booking[] bookings = (dataChoice == 1) ? bookingFileDataAccessService.getBookings()
                : (dataChoice == 2) ? bookingArrayDataAccessService.getBookings()
                : null;
        return bookings;
    }

    public void showBookings(){
        Booking[] bookings = getBookings();

        if (bookings.length == 0) {
            System.out.println("Empty");
        }


        System.out.println("\nBooked Cars: ");
        for (Booking x : bookings)
            if (x != null){
            System.out.println("-- "
                    + x.getCar().getCompany()
                    + " "
                    + x.getCar().getModel()
                    + " ("
                    + x.getCar().getColor()
                    + ") "
                    + x.getClient().getName()
                    + " "
                    + x.getClient().getEmail()
                    + " "
                    + x.getClient().getAddress()
                    + " "
                    + x.getStartDate()
                    + " "
                    + x.getEndDate());
        }
    }

    public void bookACar(Scanner scanner,
                         CarService carService,
                         ClientService clientService,
                         BookingService bookingService){

        boolean foundClient = false;
        Car[] availableCars = carService.getCars();
        Client[] availableClients = clientService.getClients();


        if(availableCars == null || availableClients == null) {
            System.out.println("Invalid choice.");
            return;
        }

        carService.showAllAvailableCars();

        System.out.println("\nEnter the number of the corresponding car you want to book");
        int carChoice = scanner.nextInt();
        scanner.nextLine();

        Car selectedCar = availableCars[carChoice - 1];

        clientService.showClients();
        System.out.println("\nSelect a client ID in these client: ");
        String clientIdChoice = scanner.nextLine();

        for (Client availableClient : availableClients) {
            if (clientIdChoice.equals(availableClient.getId())) {
                System.out.print("Enter the start date of the booking: ");
                String startDate = scanner.nextLine();
                System.out.print("Enter the end date of the booking: ");
                String endDate = scanner.nextLine();
                Booking booking = new Booking(
                        selectedCar,
                        availableClient,
                        startDate,
                        endDate);
                bookingService.registerNewBooking(booking);
                System.out.println("Booking successful!");
                selectedCar.setBooked(true);
                foundClient = true;
                break;
            }
        }
        if (!foundClient) {
            System.out.println("Invalid client ID. Please enter a valid ID.");
        }

    }
}
