package ru.gb.lesson3.hw;

import java.io.Serializable;

public class Car implements Serializable {
    private String model;
    private int gear;

    public Car(String model, int gear) {
        this.model = model;
        this.gear = gear;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", gear=" + gear +
                '}';
    }
}
