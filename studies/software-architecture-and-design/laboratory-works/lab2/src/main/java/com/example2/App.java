package com.example2;

import com.example2.ui.console.ConsoleApplication;
import com.example2.dal.config.DatabaseConfig;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Food Delivery System Main Application
 * Multi-tier architecture with DAL, BLL, and UI layers
 */
public class App {
    private static final String UTF8_ENCODING = "UTF-8";
    private static final Logger logger = Logger.getLogger(App.class.getName());
    
    public static void main(String[] args) {
        // Set encoding properties for proper Ukrainian text display
        System.setProperty("file.encoding", UTF8_ENCODING);
        System.setProperty("console.encoding", UTF8_ENCODING);
        System.setProperty("sun.jnu.encoding", UTF8_ENCODING);
        System.setProperty("user.language", "ua");
        System.setProperty("user.country", "UA");
        
        // Disable excessive Hibernate logging
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        Logger.getLogger("org.hibernate.SQL").setLevel(Level.OFF);
        Logger.getLogger("org.hibernate.type").setLevel(Level.OFF);
        Logger.getLogger("org.hibernate.engine").setLevel(Level.OFF);
        Logger.getLogger("org.hibernate.jpa").setLevel(Level.OFF);
        Logger.getLogger("org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl").setLevel(Level.OFF);
        Logger.getLogger("").setLevel(Level.WARNING);  // Root logger
        
        try {
            // Initialize console application
            ConsoleApplication consoleApp = new ConsoleApplication();
            consoleApp.run();
        } catch (Exception e) {
            logger.severe("Помилка запуску застосування: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close database connections
            DatabaseConfig.closeEntityManagerFactory();
        }
    }
}
