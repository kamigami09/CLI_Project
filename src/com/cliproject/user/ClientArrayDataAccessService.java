package com.cliproject.user;


import java.util.Arrays;

public class ClientArrayDataAccessService implements ClientDAO {
    private static Client[] clients;
    private static int nextAvailableSpot=0;
    static {
        clients= new Client[CAPACITY];
    }

    @Override
    public void saveClient(Client client){
        if(nextAvailableSpot + 1 >= CAPACITY){
            //grow db
        }
        clients[nextAvailableSpot++]=client;
    }
    @Override
    public Client[] getClients(){
        return clients;
    }
}
