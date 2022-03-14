package com.company.CarShowroom;

public abstract class Vehicle {
    enum VehicleType{
        CAR,
        MOTORBIKE
    }
    private VehicleType type;
    private String name;
    private String model;
    private String powerOfEngine;
    private double price;
    private int id;

    public Vehicle(String name, String model, String powerOfEngine, double price, VehicleType type) {
        this.type = type;
        this.name = name;
        this.model = model;
        this.powerOfEngine = powerOfEngine;
        this.price = price;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getId()).append("\t");
        sb.append(getName()).append("\t");
        sb.append(getModel()).append("\t");
        sb.append(getPowerOfEngine()).append("\t");
        sb.append(getPrice());
        return sb.toString();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPowerOfEngine() {
        return powerOfEngine;
    }

    public void setPowerOfEngine(String powerOfEngine) {
        this.powerOfEngine = powerOfEngine;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public VehicleType getType() {
        return type;
    }
}
