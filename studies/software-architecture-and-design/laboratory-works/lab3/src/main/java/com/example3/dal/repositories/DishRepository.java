package com.example3.dal.repositories;

import com.example3.domain.models.Dish;
import com.example3.domain.models.DishType;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Інтерфейс репозиторію для роботи зі стравами
 */
public interface DishRepository {
    
    Dish save(Dish dish);
    
    Optional<Dish> findById(Long id);
    
    List<Dish> findAll();
    
    List<Dish> findByDishType(DishType dishType);
    
    List<Dish> findByAvailableTrue();
    
    List<Dish> findByNameContainingIgnoreCase(String name);
    
    List<Dish> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    void deleteById(Long id);
    
    boolean existsById(Long id);
}
