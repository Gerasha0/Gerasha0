package com.example3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Головний клас застосування для системи доставки їжі
 * 
 * Багаторівнева архітектура:
 * - Presentation Layer (PL): WebAPI контролери
 * - Business Logic Layer (BLL): сервіси
 * - Data Access Layer (DAL): репозиторії
 * - Domain Models: доменні моделі
 */
@SpringBootApplication
public class App {
    
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        
        System.out.println("=".repeat(60));
        System.out.println("  SISTEMA DOSTAVKI YZHI ZAPUSHCHENA!");
        System.out.println("=".repeat(60));
        System.out.println("  Web Interface: http://localhost:8080/");
        System.out.println("  WebAPI dostupniy za adresoyu: http://localhost:8080/api");
        System.out.println("  Dokumentatsiya API:");
        System.out.println("    Stravi: http://localhost:8080/api/dishes");
        System.out.println("    Zamovlennya: http://localhost:8080/api/orders");
        System.out.println("=".repeat(60));
        System.out.println("  Vikoristovuyte web interface dlya testuvannya!");
        System.out.println("=".repeat(60));
    }
}
