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

    public Client[] getClientsFromArray(){
        return clientArrayDataAccessService.getClients();
    }
    public Client[] getClientsFromFile(){
        return clientFileDataAccessService.getClients();
    }
    public void registerNewClient(Client client){
        clientArrayDataAccessService.saveClient(client);
        clientFileDataAccessService.saveClient(client);
    }
    public void showClients() {
        Client[] clientsData;

        clientsData = (dataChoice == 1) ? getClientsFromFile()
                : (dataChoice == 2) ? getClientsFromArray()
                : null;

        if(clientsData == null) {
            System.out.println("Invalid choice.");
            return;
        }

        for (Client client : clientsData) {
            if (client != null) {
                System.out.println("-- " + client.getId() + " " + client.getName() + " " + client.getAddress() + " " + client.getEmail());
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
