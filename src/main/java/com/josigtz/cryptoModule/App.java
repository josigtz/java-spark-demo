package com.josigtz.cryptoModule;

import com.josigtz.cryptoModule.config.Configuration;
import com.josigtz.cryptoModule.controller.FielController;
import com.josigtz.cryptoModule.controller.HelloController;
import com.josigtz.cryptoModule.repository.FielRepositoryHibernate;
import com.josigtz.cryptoModule.service.FielService;

public class App {

  public static void main(String[] args) {

    // 1. Initialize Configuration
    // This sets up the port and our GSON JSON converter
    Configuration config = new Configuration();
    config.setup();

    // 2. Initialize Services
    // Services hold your "business logic"
    FielService fielService = new FielService(new FielRepositoryHibernate());

    // 3. Initialize Controllers
    // Controllers handle web requests and use services to get work done
    new HelloController();
    new FielController(config.getGson());
  }

}
