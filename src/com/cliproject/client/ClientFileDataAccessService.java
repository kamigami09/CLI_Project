package com.cliproject.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ClientFileDataAccessService implements ClientDAO {

    private static Client[] clients;
    static {
        clients = new Client[CAPACITY];
    }
    private static File csvFile = new File("src/com/cliproject/client/Clients.csv");
    private static PrintWriter writer;

    static {
        try {
            writer = new PrintWriter(new FileOutputStream(csvFile));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveClient(Client client) {
        writer.println(client.getId() + "," + client.getName() + "," + client.getAddress() + "," + client.getEmail());
        writer.flush();
    }

    @Override
    public Client[] getClients() {
        int index = 0;
        try {
            Scanner scanner = new Scanner(csvFile);
            while (scanner.hasNext()){
                String input = scanner.nextLine();
                String[] data = input.split(",");
                String id = data[0];
                String name = data[1];
                String address = data[2];
                String email = data[3];
                Client client = new Client(id, name, address, email);
                clients[index++] = client;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return clients;
    }
}
