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

    public Client[] getClients(){
        Client[] clients = (dataChoice == 1) ? clientFileDataAccessService.getClients()
                : (dataChoice == 2) ? clientArrayDataAccessService.getClients()
                : null;
        return clients;
    }
    public void registerNewClient(Client client){
        clientArrayDataAccessService.saveClient(client);
        clientFileDataAccessService.saveClient(client);
    }
    public void showClients() {
        Client[] clientsData = getClients();

        if (clientsData.length == 0) {
            System.out.println("Empty");
        }

        for (Client client : clientsData) {
            if (client != null) {
                System.out.println(client);
            }
            else {
                return;
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
