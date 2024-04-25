package com.cliproject.user;

import java.util.UUID;

public class Client {
    private String id;
    private String name;
    private String address;
    private String email;

    public Client() {
        id = generateId();
    }

    public Client(String name, String address, String email) {
        id = generateId();
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String generateId(){
        String uniqueId = UUID.randomUUID().toString();
        return uniqueId;
    }
}
