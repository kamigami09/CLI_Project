package com.cliproject.user;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ClientService {

    private ClientArrayDataAccessService clientArrayDataAccessService = new ClientArrayDataAccessService();

    public Client[] getClients(){
        return clientArrayDataAccessService.selectAll();
    }
    public void registerNewClient(Client client){
        clientArrayDataAccessService.saveClient(client);
    }
    public void writeClientsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Client[] clients = clientArrayDataAccessService.selectAll();
            for (Client client : clients) {
                writer.write(client.toString()); // Assuming you have a proper toString() method in your Client class
                writer.newLine(); // Add a newline between clients
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showClients(){
        System.out.println(Arrays.toString(getClients()));
    }
    public void addClientByScan(Scanner scanner) {
        System.out.println("Enter client information (name, address, email).");
        String in = scanner.nextLine();
        String input = scanner.nextLine();
        String[] clientInfo=input.split(",");
        String name = clientInfo[0].trim();
        String address = clientInfo[1].trim();
        String email = clientInfo[2].trim();
        Client client = new Client(name,address,email);
        registerNewClient(client);
        writeClientsToFile("src/com/cliproject/user/Clients.txt");
    }

}
