package com.cliproject.user;


import java.util.Arrays;

public class ClientArrayDataAccessService implements ClientDAO {
    private static Client[] clients;
    private static int nextAvailableSpot=0;
    private static final int Capacity=5;
    static {
        clients= new Client[Capacity];
    }
    @Override
    public void saveClient(Client client){
        if(nextAvailableSpot + 1 >= Capacity){
            //grow db
        }
        clients[nextAvailableSpot++]=client;
    }
    @Override
    public Client[] selectAll(){
        return Arrays.copyOf(clients, nextAvailableSpot);
    }
}
