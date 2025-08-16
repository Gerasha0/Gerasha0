package com.example3.dal.repositories.impl;

import com.example3.dal.repositories.DishRepository;
import com.example3.domain.models.Dish;
import com.example3.domain.models.DishType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Файлова реалізація репозиторію страв
 * Зберігає дані в JSON файлі dishes.txt
 */
@Component
public class FileDishRepository implements DishRepository {
    
    private static final String DATA_FILE = "dishes.txt";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    public FileDishRepository() {
        initializeData();
    }
    
    private void initializeData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            // Створюємо тестові дані при першому запуску
            List<Dish> initialDishes = createInitialDishes();
            saveToFile(initialDishes);
        } else {
            // Встановлюємо правильний лічильник ID
            List<Dish> dishes = loadFromFile();
            long maxId = dishes.stream()
                .mapToLong(Dish::getId)
                .max()
                .orElse(0);
            idGenerator.set(maxId + 1);
        }
    }
    
    private List<Dish> createInitialDishes() {
        List<Dish> dishes = new ArrayList<>();
        
        // Перші страви
        dishes.add(createDish(1L, "Борщ українский", "Традиційний український борщ з м'ясом", 
                new BigDecimal("85.00"), DishType.FIRST_COURSE));
        dishes.add(createDish(2L, "Солянка м'ясна", "Густий суп з ковбасою, оливками та лимоном", 
                new BigDecimal("95.00"), DishType.FIRST_COURSE));
        dishes.add(createDish(3L, "Куряча лапша", "Домашня лапша на курячому бульйоні", 
                new BigDecimal("75.00"), DishType.FIRST_COURSE));
        
        // Другі страви
        dishes.add(createDish(4L, "Котлета по-київськи", "Куряча котлета з вершковим маслом", 
                new BigDecimal("140.00"), DishType.SECOND_COURSE));
        dishes.add(createDish(5L, "Біфштекс з яйцем", "Соковитий біфштекс з смаженим яйцем", 
                new BigDecimal("180.00"), DishType.SECOND_COURSE));
        dishes.add(createDish(6L, "Риба запечена", "Запечена риба з овочами та зеленню", 
                new BigDecimal("160.00"), DishType.SECOND_COURSE));
        
        // Салати
        dishes.add(createDish(7L, "Олів'є", "Класичний салат з картоплею та ковбасою", 
                new BigDecimal("65.00"), DishType.SALAD));
        dishes.add(createDish(8L, "Грецький салат", "Свіжі овочі з сиром фета", 
                new BigDecimal("80.00"), DishType.SALAD));
        
        // Десерти
        dishes.add(createDish(9L, "Тірамісу", "Італійський десерт з маскарпоне", 
                new BigDecimal("70.00"), DishType.DESSERT));
        dishes.add(createDish(10L, "Чізкейк", "Нью-йоркський чізкейк з ягодами", 
                new BigDecimal("65.00"), DishType.DESSERT));
        
        // Напої
        dishes.add(createDish(11L, "Компот з сухофруктів", "Домашній компот", 
                new BigDecimal("25.00"), DishType.DRINK));
        dishes.add(createDish(12L, "Свіжовичавлений сік", "Апельсиновий сік", 
                new BigDecimal("35.00"), DishType.DRINK));
        
        idGenerator.set(13);
        return dishes;
    }
    
    private Dish createDish(Long id, String name, String description, BigDecimal price, DishType dishType) {
        Dish dish = new Dish(name, description, price, dishType);
        dish.setId(id);
        return dish;
    }
    
    private List<Dish> loadFromFile() {
        try {
            File file = new File(DATA_FILE);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<Dish>>() {});
        } catch (IOException e) {
            System.err.println("Помилка читання файлу: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    private void saveToFile(List<Dish> dishes) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(DATA_FILE), dishes);
        } catch (IOException e) {
            System.err.println("Помилка запису файлу: " + e.getMessage());
        }
    }
    
    @Override
    public List<Dish> findAll() {
        return loadFromFile();
    }
    
    @Override
    public Optional<Dish> findById(Long id) {
        return loadFromFile().stream()
                .filter(dish -> dish.getId().equals(id))
                .findFirst();
    }
    
    @Override
    public Dish save(Dish dish) {
        List<Dish> dishes = loadFromFile();
        
        if (dish.getId() == null) {
            // Новая страва
            dish.setId(idGenerator.getAndIncrement());
            dishes.add(dish);
        } else {
            // Оновлення існуючої страви
            for (int i = 0; i < dishes.size(); i++) {
                if (dishes.get(i).getId().equals(dish.getId())) {
                    dishes.set(i, dish);
                    break;
                }
            }
        }
        
        saveToFile(dishes);
        return dish;
    }
    
    @Override
    public void deleteById(Long id) {
        List<Dish> dishes = loadFromFile();
        dishes.removeIf(dish -> dish.getId().equals(id));
        saveToFile(dishes);
    }
    
    @Override
    public List<Dish> findByDishType(DishType dishType) {
        return loadFromFile().stream()
                .filter(dish -> dish.getDishType() == dishType)
                .toList();
    }
    
    @Override
    public List<Dish> findByAvailableTrue() {
        return loadFromFile().stream()
                .filter(Dish::isAvailable)
                .toList();
    }
    
    @Override
    public List<Dish> findByNameContainingIgnoreCase(String name) {
        return loadFromFile().stream()
                .filter(dish -> dish.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
    
    @Override
    public List<Dish> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return loadFromFile().stream()
                .filter(dish -> dish.getPrice().compareTo(minPrice) >= 0 && 
                               dish.getPrice().compareTo(maxPrice) <= 0)
                .toList();
    }
    
    @Override
    public boolean existsById(Long id) {
        return loadFromFile().stream()
                .anyMatch(dish -> dish.getId().equals(id));
    }
}
