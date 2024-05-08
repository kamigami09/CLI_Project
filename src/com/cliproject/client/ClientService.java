package com.cliproject.client;


import java.util.ArrayList;
import java.util.Scanner;

public class ClientService {

    private final ClientLisDataAccessService clientLisDataAccessService;
    private final ClientFileDataAccessService clientFileDataAccessService;
    private int dataChoice;

    public ClientService(ClientLisDataAccessService clientLisDataAccessService,
                         ClientFileDataAccessService clientFileDataAccessService,
                         int dataChoice) {
        this.clientLisDataAccessService = clientLisDataAccessService;
        this.clientFileDataAccessService = clientFileDataAccessService;
        this.dataChoice = dataChoice;
    }

    public void registerNewClient(Client client){
        clientLisDataAccessService.saveClient(client);
        clientFileDataAccessService.saveClient(client);
    }

    public ArrayList<Client> getClients(){
        ArrayList<Client> clients = (dataChoice == 1) ? clientFileDataAccessService.getClients()
                : (dataChoice == 2) ? clientLisDataAccessService.getClients()
                : null;
        return clients;
    }

    public void showClients() {
        ArrayList<Client> clientsData = getClients();
        boolean clientExists = false;


        for (Client client : clientsData) {
            if (client != null) {
                clientExists = true;
            }
        }
        if (!clientExists) {
            System.out.println("No client found");
        }
        else {
            System.out.println("Available Clients: ");
            for (Client client : clientsData) {
                if (client != null) {
                    System.out.println(client);
                }
            }
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
        registerNewClient(client);
    }

}
