package com.cliproject.car;

import java.util.Objects;

public class Car {
    private String regis;
    private String company;
    private String model;
    private boolean isBooked;
    private String color;

    public Car(String regis, String company, String model, String color) {
        this.model = model;
        this.color = color;
        this.company = company;
        this.regis = regis;
        isBooked = false;
    }

    @Override
    public String toString() {
        return "Car{" +
                "regis='" + regis + '\'' +
                ", company='" + company + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return isBooked == car.isBooked && Objects.equals(regis, car.regis) && Objects.equals(company, car.company) && Objects.equals(model, car.model) && Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regis, company, model, isBooked, color);
    }

    public String getRegis() {
        return regis;
    }

    public void setRegis(String regis) {
        this.regis = regis;
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
