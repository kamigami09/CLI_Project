package com.cliproject;


import com.cliproject.car.Car;
import com.cliproject.car.CarService;
import com.cliproject.user.Client;
import com.cliproject.user.ClientService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//Make Scanner
        Scanner scanner = new Scanner(System.in);
        int ui = 0;
//Call CarService
        CarService carService = new CarService();
//Register new cars
        Car car1=new Car("Toyota","Camry","blue");
        Car car2=new Car("Toyota","supra","white");
        Car car3=new Car("Audi","Rs6","black");
        Car car4=new Car("Honda","Civic","Silver");
        Car car5=new Car ("BMW","3 Series","Black");
        carService.registerNewCar(car1);
        carService.registerNewCar(car2);
        carService.registerNewCar(car3);
        carService.registerNewCar(car4);
        carService.registerNewCar(car5);
//Call clientService
        ClientService clientService = new ClientService();
//Register new clients
        Client client1=new Client("yassine","kenitra cite chaabi","yassinebouhouch76@gmail.com");
        Client client2=new Client("anas","kenitra cite chaabi and sale","anason@gmail.com");
        Client client3=new Client("ayoub","casablanca lwalfa","ayoubbakhii@gmail.com");
        clientService.registerNewClient(client1);
        clientService.registerNewClient(client2);
        clientService.registerNewClient(client3);
        clientService.writeClientsToFile("src/com/cliproject/user/Clients.txt");
//UI
        while (true) {
            System.out.println("1/ View all users");
            System.out.println("2/ View all available cars");
            System.out.println("3/ View all bookings");
            System.out.println("4/ Join us as a client");
            System.out.println("5/ Book a car");
            System.out.println("6/ View all cars");
            System.out.println("7/ Exit");
            ui = scanner.nextInt();
            //Show Clients
            if (ui == 1) {
                clientService.showClients();
            }
            //Show cars
            if (ui == 2) {
                carService.showAllAvailableCars();
            }
            //Show booked cars
            if (ui == 3) {
                carService.showAllBookedCars();
            }
            //Scan a client
            if (ui == 4) {
                clientService.addClientByScan(scanner);
            }
            if (ui == 5){
                Scanner scanner = new Scanner(System.in);
                carService.showAllAvailableCars();
                carService.bookACar(car);
            }
            if (ui == 6) {

            }
            if (ui == 7) break;
        }

    }

}