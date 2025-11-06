package com.josigtz.cryptoModule.controller;

import com.google.gson.Gson;
import com.josigtz.cryptoModule.model.command.FielCommand;
import com.josigtz.cryptoModule.model.dto.FielDto;
import com.josigtz.cryptoModule.repository.FielRepositoryHibernate;
import com.josigtz.cryptoModule.service.FielService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class FielController implements BaseController{
    private final FielService fielService = new FielService(new FielRepositoryHibernate());
    private final Gson gson;

    public FielController(Gson gson) {
        this.gson = gson;
        registerRoutes();
    }

    @Override
    public void registerRoutes() {

        // Define a "route group" for all routes starting with /fiel
        path("/fiel", () -> {

            get("/:serie", (req, res) -> {
                res.type("application/json");

                try {
                    String serie = req.params(":serie");
                    FielDto fiel = fielService.findBySerie(serie);

                    if (fiel != null) {
                        // Use GSON to automatically convert the User object to a JSON string
                        return gson.toJson(fiel);
                    } else {
                        res.status(404); // Not Found
                        Map<String, String> errorMap = new HashMap<>();
                        errorMap.put("error", "User not found");
                        return gson.toJson(errorMap);
                    }
                } catch (NumberFormatException e) {
                    res.status(400); // Bad Request
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put("error", "Invalid user ID format");
                    return gson.toJson(errorMap);
                }
            });

            get("/", (req, res) -> {
                res.type("application/json");

                List<FielDto> fielDtos = fielService.findAll();

                if (fielDtos != null) {
                    // Use GSON to automatically convert the User object to a JSON string
                    return gson.toJson(fielDtos);
                } else {
                    res.status(404); // Not Found
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put("error", "Fiel not found");
                    return errorMap;
                }
            });

            post("/", (req, res) -> {
                res.type("application/json");

                // 1. Get the raw body as a String
                String bodyString = req.body();

                // 2. Deserialize the JSON string into your object
                try {
                    FielCommand fiel = gson.fromJson(bodyString, FielCommand.class);

                    // 3. Now you have a real Java object
                    System.out.println("Received: " + fiel.getSerie());
                    ;

                    // Set response type and return a success message
                    res.type("application/json");
                    return "{\"status\": \"success\", \"message\": \"Fiel " + fielService.insert(fiel)+ " created\"}";

                } catch (Exception e) {
                    // Handle bad JSON or other errors
                    res.status(400); // Bad Request
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put("error", e.getMessage());
                    return gson.toJson(errorMap);
                }
            });

            delete("/:serie", (req, res) -> {
                res.type("application/json");

                try {
                    String serie = req.params(":serie");
                    fielService.delete(serie);
                    return "{\"status\": \"success\", \"message\": \"Fiel " + serie+ " deleted\"}";
                } catch (NumberFormatException e) {
                    res.status(400); // Bad Request
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put("error", "Invalid serie");
                    return gson.toJson(errorMap);
                }
            });

        });
    }
}
