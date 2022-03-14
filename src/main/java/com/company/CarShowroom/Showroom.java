package com.company.CarShowroom;

import java.util.ArrayList;

public class Showroom {
    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    private ArrayList<Vehicle> vehicleList;

    public Showroom() {
        this.vehicleList = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle){
        vehicleList.add(vehicle);
    }

    public void updateVehicle(Vehicle vehicle){
        int index = 0;
        for (Vehicle v: vehicleList) {
            if (v.getId() == vehicle.getId()){
                vehicleList.set(index,vehicle);
                break;
            }
            index++;
        }
    }

    public void deleteVehicle(Vehicle vehicle){
        for (Vehicle v: vehicleList) {
            if (v.getId() == vehicle.getId()){
                vehicleList.remove(vehicle);
                break;
            }
        }
    }

    public Vehicle getVehicle(int id){
        for (Vehicle v: vehicleList) {
            if (v.getId() == id){
                return v;
            }
        }
        return null;
    }

    public void displayShowroom(){
        for (Vehicle v : vehicleList) {
            Util.print(v.toString());
        }
    }

}
