package com.cliproject;

import com.cliproject.booking.BookingArrayDataAccessService;
import com.cliproject.booking.BookingFileDataAccessService;
import com.cliproject.booking.BookingService;
import com.cliproject.car.Car;
import com.cliproject.car.CarArrayDataAccessService;
import com.cliproject.car.CarFileDataAccessService;
import com.cliproject.car.CarService;
import com.cliproject.client.Client;
import com.cliproject.client.ClientArrayDataAccessService;
import com.cliproject.client.ClientFileDataAccessService;
import com.cliproject.client.ClientService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dataChoice;
        int choice;


        // Data Choice
        System.out.println("Please select the data source:\n1. File\n2. Array\nEnter your choice: ");
        dataChoice = scanner.nextInt();
        scanner.nextLine();


        //Dependencies:
        CarFileDataAccessService carFileDataAccessService = new CarFileDataAccessService();
        CarArrayDataAccessService carArrayDataAccessService = new CarArrayDataAccessService();
        ClientArrayDataAccessService clientArrayDataAccessService = new ClientArrayDataAccessService();
        ClientFileDataAccessService clientFileDataAccessService = new ClientFileDataAccessService();
        BookingArrayDataAccessService bookingArrayDataAccessService = new BookingArrayDataAccessService();
        BookingFileDataAccessService bookingFileDataAccessService = new BookingFileDataAccessService();


        //Injecting the dependencies:
        CarService carService = new CarService(
                carArrayDataAccessService,
                carFileDataAccessService,
                dataChoice);
        ClientService clientService = new ClientService(
                clientArrayDataAccessService,
                clientFileDataAccessService,
                dataChoice);
        BookingService bookingService = new BookingService(
                bookingArrayDataAccessService,
                bookingFileDataAccessService,
                dataChoice);


        // Register some sample cars and clients
        registerSampleData(carService, clientService);

        while (true) {
            System.out.println("\nWelcome to Car Rental System\n");
            System.out.println("1. View all clients");
            System.out.println("2. View all available cars");
            System.out.println("3. View all booked cars");
            System.out.println("4. Register as a new client");
            System.out.println("5. Book a car");
            System.out.println("6. Exit");
            System.out.print("\nEnter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    clientService.showClients();
                    break;
                case 2:
                    carService.showAllAvailableCars();
                    break;
                case 3:
                    bookingService.showBookings();
                    break;
                case 4:
                    clientService.addClientByScan(scanner);
                    break;
                case 5:
                    bookingService.bookACar(scanner, carService, clientService, bookingService);
                    break;
                case 6:
                    System.out.println("\nThank you for using the Car Rental System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("\nInvalid choice. Please select a valid option.");
            }
        }
    }

    // Helper method to register sample data
    private static void registerSampleData(CarService carService, ClientService clientService) {
        // Register sample cars
        carService.registerNewCar(new Car("Toyota", "Camry", "Blue"));
        carService.registerNewCar(new Car("Toyota", "Supra", "White"));
        carService.registerNewCar(new Car("Audi", "Rs6", "Black"));
        carService.registerNewCar(new Car("Honda", "Civic", "Silver"));
        carService.registerNewCar(new Car("BMW", "3 Series", "Black"));

        // Register sample clients
        clientService.registerNewClient(new Client("Yassine", "Kenitra Cite Chaabi", "yassinebouhouch76@gmail.com"));
        clientService.registerNewClient(new Client("Anas", "Kenitra Cite Chaabi and Sale", "anason@gmail.com"));
        clientService.registerNewClient(new Client("Ayoub", "Casablanca Lwalfa", "ayoubbakhii@gmail.com"));
    }
}
