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
    
    Dish createDish(Dish dish);
    
    Dish updateDish(Long id, Dish dish);
    
    Optional<Dish> getDishById(Long id);
    
    List<Dish> getAllDishes();
    
    List<Dish> getAvailableDishes();
    
    List<Dish> getDishesByType(DishType dishType);
    
    List<Dish> searchDishesByName(String name);
    
    List<Dish> getDishesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    
    void toggleDishAvailability(Long id);
    
    void deleteDish(Long id);
    
    boolean dishExists(Long id);
}
