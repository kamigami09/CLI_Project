package com.cliproject.user;



public interface ClientDAO {
    Client[] selectAll();
    void saveClient(Client client);
}
