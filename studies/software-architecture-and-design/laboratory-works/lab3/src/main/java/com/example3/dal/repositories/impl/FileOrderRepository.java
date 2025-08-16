package com.example3.dal.repositories.impl;

import com.example3.dal.repositories.OrderRepository;
import com.example3.domain.models.Order;
import com.example3.domain.models.OrderStatus;
import com.example3.domain.models.OrderType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Файлова реалізація репозиторію замовлень
 * Зберігає дані в JSON файлі orders.txt
 */
@Component
public class FileOrderRepository implements OrderRepository {
    
    private static final String DATA_FILE = "orders.txt";
    private final ObjectMapper objectMapper;
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    public FileOrderRepository() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        initializeData();
    }
    
    private void initializeData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            // Створюємо порожній файл при першому запуску
            saveToFile(new ArrayList<>());
        } else {
            // Встановлюємо правильний лічильник ID
            List<Order> orders = loadFromFile();
            long maxId = orders.stream()
                .mapToLong(Order::getId)
                .max()
                .orElse(0);
            idGenerator.set(maxId + 1);
        }
    }
    
    private List<Order> loadFromFile() {
        try {
            File file = new File(DATA_FILE);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<Order>>() {});
        } catch (IOException e) {
            System.err.println("Помилка читання файлу замовлень: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    private void saveToFile(List<Order> orders) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(DATA_FILE), orders);
        } catch (IOException e) {
            System.err.println("Помилка запису файлу замовлень: " + e.getMessage());
        }
    }
    
    @Override
    public List<Order> findAll() {
        return loadFromFile();
    }
    
    @Override
    public Optional<Order> findById(Long id) {
        return loadFromFile().stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }
    
    @Override
    public Order save(Order order) {
        List<Order> orders = loadFromFile();
        
        if (order.getId() == null) {
            // Нове замовлення
            order.setId(idGenerator.getAndIncrement());
            orders.add(order);
        } else {
            // Оновлення існуючого замовлення
            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getId().equals(order.getId())) {
                    orders.set(i, order);
                    break;
                }
            }
        }
        
        saveToFile(orders);
        return order;
    }
    
    @Override
    public void deleteById(Long id) {
        List<Order> orders = loadFromFile();
        orders.removeIf(order -> order.getId().equals(id));
        saveToFile(orders);
    }
    
    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return loadFromFile().stream()
                .filter(order -> order.getStatus() == status)
                .toList();
    }
    
    @Override
    public List<Order> findByOrderType(OrderType orderType) {
        return loadFromFile().stream()
                .filter(order -> order.getOrderType() == orderType)
                .toList();
    }
    
    @Override
    public List<Order> findByCustomerPhone(String customerPhone) {
        return loadFromFile().stream()
                .filter(order -> order.getCustomerPhone().equals(customerPhone))
                .toList();
    }
    
    @Override
    public List<Order> findByOrderDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return loadFromFile().stream()
                .filter(order -> order.getOrderDateTime().isAfter(startDate) && 
                               order.getOrderDateTime().isBefore(endDate))
                .toList();
    }
    
    @Override
    public boolean existsById(Long id) {
        return loadFromFile().stream()
                .anyMatch(order -> order.getId().equals(id));
    }
}
