package com.mlog.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

import com.mlog.logger.service.LogService;

@SpringBootApplication
public class MlogApplication implements CommandLineRunner {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${log.port:8080}") // Default to 8181 if not specified
    private int logPort;

    @Autowired
    private LogService logService;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MlogApplication.class);
        
        // Custom listener to set the server port before the application context is created
        app.addListeners((ApplicationListener<ApplicationEnvironmentPreparedEvent>) event -> {
            System.setProperty("server.port", String.valueOf(event.getEnvironment().getProperty("log.port", Integer.class, 8181)));
        });
        app.run(args);
    }

    @Override
    public void run(String... args) {
        logService.info("Initialization", "Application '" + applicationName + "' has started successfully on port " + logPort + ".");
        logService.error("Database Error", "Failed to connect to the database.");
        logService.debug("Debug Info", "Debugging application startup.");
        System.out.println("Application started on port: " + logPort);
    }
}
