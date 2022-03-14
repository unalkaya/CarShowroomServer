package com.company.CarShowroom;

import java.util.Scanner;

public class Util {

    public static void print(String message){
        System.out.println(message);
    }

    public static void print(Vehicle vehicle){
        System.out.println(vehicle.toString());
    }

    public static int getInput(String message){
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        return sc.nextInt();
    }

    public static String getInputasString(String message){
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        return sc.nextLine();
    }

    public static double getInputasDouble(String message){
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        return sc.nextDouble();
    }


}
