package com.cliproject.client;



public interface ClientDAO {

    int CAPACITY = 10;
    Client[] getClients();
    void saveClient(Client client);
}
