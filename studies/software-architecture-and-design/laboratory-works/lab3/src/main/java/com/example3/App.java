package com.example3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        
        System.out.println("=".repeat(60));
        System.out.println("  Запустилось и слава Богу хДД");
        System.out.println("=".repeat(60));
        System.out.println("  Web Interface: http://localhost:8080/");
        System.out.println("  WebAPI: http://localhost:8080/api");
        System.out.println("=".repeat(60));
    }
}
