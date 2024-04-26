package com.cliproject.user;

import com.cliproject.car.Car;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ClientService {

    private final ClientArrayDataAccessService clientArrayDataAccessService;
    private final ClientFileDataAccessService clientFileDataAccessService;

    public ClientService(ClientArrayDataAccessService clientArrayDataAccessService, ClientFileDataAccessService clientFileDataAccessService) {
        this.clientArrayDataAccessService = clientArrayDataAccessService;
        this.clientFileDataAccessService = clientFileDataAccessService;
    }

    public Client[] getClientsFromArray(){
        return clientArrayDataAccessService.getClients();
    }
    public Client[] getClientsFromFile(){
        return clientFileDataAccessService.getClients();
    }
    public void registerNewClientToArray(Client client){
        clientArrayDataAccessService.saveClient(client);
    }
    public void registerNewClientToFile(Client client){
        clientFileDataAccessService.saveClient(client);
    }

    public void showClients() {
        for (Client x : getClientsFromFile()) {

                System.out.println("-- " + x.getId() + " " + x.getName() + " " + x.getAddress() + " " + x.getEmail());

        }
    }
    public void addClientByScan(Scanner scanner) {
        System.out.println("Enter client Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter client Address: ");
        String address = scanner.nextLine();
        System.out.println("Enter client Email: ");
        String email = scanner.nextLine();
        Client client = new Client(name,address,email);
        registerNewClientToArray(client);
        registerNewClientToFile(client);
    }

}
