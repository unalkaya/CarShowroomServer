package com.company.CarShowroom;


public class Car extends Vehicle {
    public Car(String name, String model, String powerOfEngine, double price) {
        super(name, model, powerOfEngine, price,VehicleType.CAR);


    }

    public void update(String name, String model, String powerOfEngine, Double price){
        if(name != null){
            setName(name);
        }
        if(model != null){
            setModel(model);
        }
        if (powerOfEngine != null){
            setPowerOfEngine(powerOfEngine);
        }
        if (price != null){
            setPrice(price);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Car      ").append("\t");
        sb.append(super.toString());
        return sb.toString();
    }
}
