package com.cliproject.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientFileDataAccessService implements ClientDAO {

    private static ArrayList<Client> clients = new ArrayList<>();
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
    public ArrayList<Client> getClients() {
        try {
            Scanner scanner = new Scanner(csvFile);
            scanner.useDelimiter("[,\n]");
            while (scanner.hasNext()){
                String id = scanner.next();
                String name = scanner.next();
                String address = scanner.next();
                String email = scanner.next();
                Client client = new Client(id, name, address, email);
                clients.add(client);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return clients;
    }
}
