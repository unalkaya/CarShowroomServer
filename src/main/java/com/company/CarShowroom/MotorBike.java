package com.company.CarShowroom;

public class MotorBike extends Vehicle {
    public MotorBike(String name, String model, String powerOfEngine, double price) {
        super(name, model, powerOfEngine, price,VehicleType.MOTORBIKE);
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
        sb.append("Motorbike").append("\t");
        sb.append(super.toString());
        return sb.toString();
    }
}
