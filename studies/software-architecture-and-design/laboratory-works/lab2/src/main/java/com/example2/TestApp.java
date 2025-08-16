package com.example2;

import com.example2.bll.dto.DishDto;
import com.example2.bll.dto.OrderDto;
import com.example2.bll.services.implementations.DishService;
import com.example2.bll.services.implementations.OrderService;
import com.example2.bll.services.interfaces.IDishService;
import com.example2.bll.services.interfaces.IOrderService;
import com.example2.dal.config.DatabaseConfig;
import com.example2.dal.entities.DishType;

import java.time.LocalDate;

/**
 * Test class to validate application functionality
 */
public class TestApp {
    public static void main(String[] args) {
        System.out.println("=== ТЕСТУВАННЯ СИСТЕМИ ДОСТАВКИ СТРАВ ===");
        
        try {
            // Initialize services
            IDishService dishService = new DishService();
            IOrderService orderService = new OrderService();
            
            // Test 1: Create dishes
            System.out.println("\n1. Тестування створення страв:");
            DishDto dish1 = dishService.createDish(new DishDto("Борщ", "Червоний борщ", 50.0, DishType.SOUP, LocalDate.now()));
            DishDto dish2 = dishService.createDish(new DishDto("Котлета", "М'ясна котлета", 80.0, DishType.MAIN_COURSE, LocalDate.now()));
            System.out.println("✓ Створено страву: " + dish1.getName() + " (ID: " + dish1.getId() + ")");
            System.out.println("✓ Створено страву: " + dish2.getName() + " (ID: " + dish2.getId() + ")");
            
            // Test 2: Get all dishes
            System.out.println("\n2. Тестування отримання всiх страв:");
            var allDishes = dishService.getAllDishes();
            System.out.println("✓ Знайдено " + allDishes.size() + " страв");
            
            // Test 3: Create order
            System.out.println("\n3. Тестування створення замовлення:");
            OrderDto order = orderService.createOrder("iван Петров", "вул. Хрещатик 1", "+380501234567");
            System.out.println("✓ Створено замовлення ID: " + order.getId() + " для " + order.getCustomerName());
            
            // Test 4: Add dish to order
            System.out.println("\n4. Тестування додавання страви до замовлення:");
            OrderDto updatedOrder = orderService.addDishToOrder(order.getId(), dish1.getId(), 2);
            System.out.println("✓ Додано страву до замовлення. Загальна цiна: " + updatedOrder.getTotalPrice());
            
            // Test 5: Search dishes by type
            System.out.println("\n5. Тестування пошуку страв за типом:");
            var soups = dishService.getDishesByType(DishType.SOUP);
            System.out.println("✓ Знайдено " + soups.size() + " супiв");
            
            System.out.println("\n=== ВСi ТЕСТИ ПРОЙШЛИ УСПiШНО! ===");
            
        } catch (Exception e) {
            System.err.println("❌ Помилка пiд час тестування: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConfig.closeEntityManagerFactory();
        }
    }
}
