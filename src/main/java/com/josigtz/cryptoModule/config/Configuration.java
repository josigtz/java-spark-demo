package com.josigtz.cryptoModule.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static spark.Spark.*;

public class Configuration {

    private final int port = 8080;

    // A single, reusable GSON instance for the whole app
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Sets up the application's configuration (e.g., port).
     */
    public void setup() {
        port(port);
    }

    public int getPort() {
        return port;
    }

    public Gson getGson() {
        return gson;
    }
}