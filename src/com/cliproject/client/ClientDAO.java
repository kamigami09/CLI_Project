package com.cliproject.client;


import java.util.ArrayList;

public interface ClientDAO {

    ArrayList<Client> getClients();

    void saveClient(Client client);

}
