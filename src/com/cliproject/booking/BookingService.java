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

    public void registerNewBooking(Booking booking) {
        bookingArrayDataAccessService.saveBooking(booking);
        bookingFileDataAccessService.saveBooking(booking);
    }

    public Booking[] getBookings() {
        Booking[] bookings = (dataChoice == 1) ? bookingFileDataAccessService.getBookings()
                : (dataChoice == 2) ? bookingArrayDataAccessService.getBookings()
                : null;
        return bookings;
    }

    public void showBookings() {
        Booking[] bookings = getBookings();
        boolean bookingExists = false;

        for (Booking x : bookings){
            if (x != null) {
                bookingExists = true;
            }
        }
        if (!bookingExists) {
            System.out.println("No booking found");
        }
        else{
            System.out.println("Bookings: ");
            for (Booking x : bookings){
                if(x != null){
                    System.out.println(x);
                }
            }
        }
    }

    public void showAvailableCars(CarService carService) {
        Car[] allCars = carService.getCars();
        Booking[] bookings = getBookings();

        boolean availableCarsExist = false;
        for (Car car : allCars) {
            boolean isBooked = false;
            for (Booking booking : bookings) {
                if (booking != null && booking.getCar().equals(car)) {
                    isBooked = true;
                    break;
                }
            }
            if (!isBooked && car != null) {
                availableCarsExist = true;
            }
        }
        if (!availableCarsExist) {
            System.out.println("No cars are currently available.");
        }
        else {
            System.out.println("Available cars: ");
            for (Car car : allCars) {
                boolean isBooked = false;
                for (Booking booking : bookings) {
                    if (booking != null && booking.getCar().equals(car)) {
                        isBooked = true;
                    }
                }
                if (!isBooked && car != null) {
                    System.out.println(car);
                }
            }
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

        showAvailableCars(carService);

        System.out.println("\nEnter the registration number of the car you want to book");
        String carRegis = scanner.nextLine();

        Car selectedCar = null;
        for (Car car : availableCars) {
            if (car != null && car.getRegis().equals(carRegis)) {
                selectedCar = car;
                break;
            }
        }

        if (selectedCar == null) {
            System.out.println("Car with registration number " + carRegis + " not found.");
            return;
        }

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
                foundClient = true;
                break;
            }
        }
        if (!foundClient) {
            System.out.println("Invalid client ID. Please enter a valid ID.");
        }

    }
}
