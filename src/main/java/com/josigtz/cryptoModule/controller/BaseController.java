package com.josigtz.cryptoModule.controller;

/**
 * A simple interface for all controllers to implement.
 * This ensures they all have an initialization method.
 */
public interface BaseController {
    /**
     * Registers the routes for this controller.
     */
    void registerRoutes();
}
