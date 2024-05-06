package com.cliproject.client;


import java.util.Scanner;

public class ClientService {

    private final ClientArrayDataAccessService clientArrayDataAccessService;
    private final ClientFileDataAccessService clientFileDataAccessService;
    private int dataChoice;

    public ClientService(ClientArrayDataAccessService clientArrayDataAccessService,
                         ClientFileDataAccessService clientFileDataAccessService,
                         int dataChoice) {
        this.clientArrayDataAccessService = clientArrayDataAccessService;
        this.clientFileDataAccessService = clientFileDataAccessService;
        this.dataChoice = dataChoice;
    }

    public void registerNewClient(Client client){
        clientArrayDataAccessService.saveClient(client);
        clientFileDataAccessService.saveClient(client);
    }

    public Client[] getClients(){
        Client[] clients = (dataChoice == 1) ? clientFileDataAccessService.getClients()
                : (dataChoice == 2) ? clientArrayDataAccessService.getClients()
                : null;
        return clients;
    }

    public void showClients() {
        Client[] clientsData = getClients();
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
