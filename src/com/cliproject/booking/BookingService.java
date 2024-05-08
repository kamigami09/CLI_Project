package com.cliproject.booking;

import com.cliproject.car.Car;
import com.cliproject.car.CarService;
import com.cliproject.client.Client;
import com.cliproject.client.ClientService;

import java.util.ArrayList;
import java.util.Scanner;

public class BookingService {

    private final BookingListDataAccessService bookingListDataAccessService;
    private final BookingFileDataAccessService bookingFileDataAccessService;
    private final int dataChoice;

    public BookingService(BookingListDataAccessService bookingListDataAccessService,
                          BookingFileDataAccessService bookingFileDataAccessService,
                          int dataChoice) {
        this.bookingListDataAccessService = bookingListDataAccessService;
        this.bookingFileDataAccessService = bookingFileDataAccessService;
        this.dataChoice = dataChoice;
    }

    public void registerNewBooking(Booking booking) {
        bookingListDataAccessService.saveBooking(booking);
        bookingFileDataAccessService.saveBooking(booking);
    }

    public ArrayList<Booking> getBookings() {
        ArrayList<Booking> bookings = (dataChoice == 1) ? bookingFileDataAccessService.getBookings()
                : (dataChoice == 2) ? bookingListDataAccessService.getBookings()
                : null;
        return bookings;
    }

    public void showBookings() {
        ArrayList<Booking> bookings = getBookings();
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
        ArrayList<Car> allCars = carService.getCars();
        ArrayList<Booking> bookings = getBookings();

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
        ArrayList<Car> availableCars = carService.getCars();
        ArrayList<Client> availableClients = clientService.getClients();


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
