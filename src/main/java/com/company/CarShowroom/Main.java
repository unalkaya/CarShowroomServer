package com.company.CarShowroom;

import com.google.gson.*;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main extends AbstractHandler {

    public static final int SC_SUCCESS = 0;
    public static final int SC_NOT_FOUND = 1;
    public static final int SC_INVALID_REQUEST = 2;
    public static final int SC_INVALID_PARAMETER = 3;

    private static Showroom showroom;
    private static Integer NEW_CAR_INDEX = 0;

    public static void main(String[] args) {

        Server server = new Server(8001);
        server.setHandler(new Main());
        try {
            showroom = new Showroom();
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(String s, Request req, HttpServletRequest servReq, HttpServletResponse res) throws IOException, ServletException {
        String result;
        switch (req.getMethod()) {
            case "GET":
                result = handleGet(req, res);
                break;
            case "POST":
                result = handlePost(req, res);
                break;
            case "PUT":
                result = handlePut(req, res);
                break;
            case "DELETE":
                result = handleDelete(req, res);
                break;
            default:
                result = "Operation not supported.";
        }
        res.setContentType("application/json;charset=utf-8");
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().println(result);
        req.setHandled(true);
    }

    private String handleDelete(Request req, HttpServletResponse res) {
        String idAsString = req.getParameter("id");
        int vehicleId;
        try {
            vehicleId = Integer.parseInt(idAsString);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return generateResponse(SC_INVALID_PARAMETER);
        }
        Vehicle vehicle = null;
        for (Vehicle v : showroom.getVehicleList()) {
            if (vehicleId == v.getId()) {
                vehicle = v;
            }
        }
        if (vehicle != null) {
            showroom.deleteVehicle(vehicle);
            return "0";
        }
        return generateResponse(SC_SUCCESS);
    }

    private String handlePut(Request req, HttpServletResponse res) {
        String body;
        String vName;
        String vModel;
        String vPowerOfEngine;
        Double vPrice;
        Integer vId;


        try {
            body = req.getReader().lines().collect(Collectors.joining());
            JsonElement json = JsonParser.parseString(body);
            vName = extractAsString(json, "name");
            vModel = extractAsString(json, "model");
            vPowerOfEngine = extractAsString(json, "powerOfEngine");
            vPrice = extractAsDouble(json, "price");
            vId = extractAsInteger(json, "id");
            if (vId == null) {
                throw new NullPointerException("Vehicle id is null");
            }
        } catch (IOException | JsonSyntaxException | NullPointerException e) {
            System.out.println(e.getMessage());
            return generateResponse(SC_INVALID_PARAMETER);
        }

        for (Vehicle v : showroom.getVehicleList()) {
            if (vId == v.getId()) {
                if (v.getType() == Vehicle.VehicleType.CAR) {
                    ((Car) v).update(vName, vModel, vPowerOfEngine, vPrice);
                    return generateResponse(SC_SUCCESS);
                } else if (v.getType() == Vehicle.VehicleType.MOTORBIKE) {
                    ((MotorBike) v).update(vName, vModel, vPowerOfEngine, vPrice);
                    return generateResponse(SC_SUCCESS);
                }
            }
        }
        return generateResponse(SC_INVALID_REQUEST);
    }

    private String handlePost(Request req, HttpServletResponse res) {
        String body;
        String type;
        try {
            body = req.getReader().lines().collect(Collectors.joining());
            JsonElement json = JsonParser.parseString(body);
            type = json.getAsJsonObject().get("type").getAsString();
        } catch (IOException | JsonSyntaxException e) {
            System.out.println(e.getMessage());
            return generateResponse(SC_INVALID_PARAMETER);
        }
        Gson gson = new Gson();
        if (type.equals("CAR")) {
            Car car = gson.fromJson(body, Car.class);
            car.setId(++NEW_CAR_INDEX);
            showroom.addVehicle(car);
            return generateResponse(SC_SUCCESS);
        } else if (type.equals("MOTORBIKE")) {
            MotorBike motorBike = gson.fromJson(body, MotorBike.class);
            motorBike.setId(++NEW_CAR_INDEX);
            showroom.addVehicle(motorBike);
            return generateResponse(SC_SUCCESS);
        } else {
            return generateResponse(SC_INVALID_REQUEST);
        }
    }

    private String handleGet(Request req, HttpServletResponse res) {
        String idAsString = req.getParameter("id");
        int vehicleId;
        try {
            vehicleId = Integer.parseInt(idAsString);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return generateResponse(SC_INVALID_PARAMETER);
        }
        for (Vehicle vehicle : showroom.getVehicleList()) {
            if (vehicleId == vehicle.getId()) {
                Gson gson = new Gson();
                return gson.toJson(vehicle);
            }
        }
        return generateResponse(SC_NOT_FOUND);
    }

    private String generateResponse(int statusCode) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map<String, Integer> scMap = new HashMap<>();

        scMap.put("statusCode", statusCode);

        return gson.toJson(scMap);
    }
    private String extractAsString(JsonElement json, String variable) {
        JsonElement element = json.getAsJsonObject().get(variable);

        return element == null ? null : element.getAsString();
    }

    private Double extractAsDouble(JsonElement json, String variable) {
        JsonElement element = json.getAsJsonObject().get(variable);

        return element == null ? null : element.getAsDouble();
    }

    private Integer extractAsInteger(JsonElement json, String variable) {
        JsonElement element = json.getAsJsonObject().get(variable);

        return element == null ? null : element.getAsInt();
    }

}
