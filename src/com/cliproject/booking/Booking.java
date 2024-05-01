package com.cliproject.booking;

import com.cliproject.car.Car;
import com.cliproject.client.Client;

public class Booking {
    private Car car;
    private Client client;
    private String startDate;
    private String endDate;

    public Booking(Car car, Client client, String startDate, String endDate) {
        this.car = car;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "car=" + car +
                ", client=" + client +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
