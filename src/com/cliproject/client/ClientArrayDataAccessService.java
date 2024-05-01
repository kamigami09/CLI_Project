package com.cliproject.client;


public class ClientArrayDataAccessService implements ClientDAO {
    private static Client[] clients;
    private static int nextAvailableSpot=0;
    static {
        clients= new Client[CAPACITY];
    }

    @Override
    public void saveClient(Client client){
        if(nextAvailableSpot + 1 >= CAPACITY){
            throw new ArrayIndexOutOfBoundsException("Cannot add more clients as the database is full");
            // GROW DB
        }
        clients[nextAvailableSpot++]=client;
    }
    @Override
    public Client[] getClients(){
        return clients;
    }
}
