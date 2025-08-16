package com.example3.bll.services;

import com.example3.domain.models.Dish;
import com.example3.domain.models.DishType;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Інтерфейс сервісу для роботи зі стравами
 */
public interface DishService {
    
    /**
     * Створити нову страву
     */
    Dish createDish(Dish dish);
    
    /**
     * Оновити страву
     */
    Dish updateDish(Long id, Dish dish);
    
    /**
     * Знайти страву за ID
     */
    Optional<Dish> getDishById(Long id);
    
    /**
     * Отримати всі страви
     */
    List<Dish> getAllDishes();
    
    /**
     * Отримати доступні страви
     */
    List<Dish> getAvailableDishes();
    
    /**
     * Знайти страви за типом
     */
    List<Dish> getDishesByType(DishType dishType);
    
    /**
     * Пошук страв за назвою
     */
    List<Dish> searchDishesByName(String name);
    
    /**
     * Знайти страви в ціновому діапазоні
     */
    List<Dish> getDishesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    
    /**
     * Змінити доступність страви
     */
    void toggleDishAvailability(Long id);
    
    /**
     * Видалити страву
     */
    void deleteDish(Long id);
    
    /**
     * Перевірити, чи існує страва
     */
    boolean dishExists(Long id);
}
