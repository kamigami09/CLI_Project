package com.cliproject.client;


import java.util.ArrayList;

public class ClientLisDataAccessService implements ClientDAO {
    private static ArrayList<Client> clients = new ArrayList<>();

    @Override
    public void saveClient(Client client){
        clients.add(client);
    }
    @Override
    public ArrayList<Client> getClients(){
        return clients;
    }
}
