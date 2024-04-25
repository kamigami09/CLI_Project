package com.cliproject.user;



public interface ClientDAO {

    int CAPACITY = 100;
    Client[] getClients();
    void saveClient(Client client);
}
