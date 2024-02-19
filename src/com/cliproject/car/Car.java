package com.cliproject.car;

public class Car {
    private String company;
    private String model;
    private boolean isBooked;
    private String Color;


    public Car(String company,
               String model,
               String color) {
        this.company = company;
        this.model = model;
        Color = color;
        isBooked=false;
    }

    @Override
    public String toString() {
        return "Car{" +
                "company='" + company + '\'' +
                ", model='" + model + '\'' +
                ", Color='" + Color + '\'' +
                '}';
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public String getCompany() {
        return company;
    }

    public String getModel() {
        return model;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public String getColor() {
        return Color;
    }
}
