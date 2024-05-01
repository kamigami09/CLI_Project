package com.cliproject.car;

import java.util.Objects;

public class Car {
    private String company;
    private String model;
    private boolean isBooked;
    private String color;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(company, car.company) && Objects.equals(model, car.model) && Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, model, isBooked, color);
    }

    public Car(String company,
               String model,
               String color) {
        this.company = company;
        this.model = model;
        this.color = color;
        isBooked=false;
    }

    @Override
    public String toString() {
        return "Car{" +
                "company='" + company + '\'' +
                ", model='" + model + '\'' +
                ", Color='" + color + '\'' +
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
        return color;
    }
}
