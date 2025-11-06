package com.josigtz.cryptoModule.controller;

import static spark.Spark.*;

/**
 * Handles basic "hello" routes.
 */
public class HelloController implements BaseController {

    public HelloController() {
        registerRoutes();
    }

    @Override
    public void registerRoutes() {

        get("/", (request, response) -> {
            response.type("text/html");
            return "<h1>Hello, Structured App!</h1>" +
                    "<p>Try: <a href='/hello/Sparky'>/hello/Sparky</a></p>" +
                    "<p>Try: <a href='/fiel/'>/fiel/</a></p>";
        });

        get("/hello/:name", (request, response) -> {
            String name = request.params(":name");
            response.type("text/plain");
            return "Hello, " + name + "!";
        });
    }
}