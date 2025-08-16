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
    
    /**
     * Зберегти страву
     */
    Dish save(Dish dish);
    
    /**
     * Знайти страву за ID
     */
    Optional<Dish> findById(Long id);
    
    /**
     * Знайти всі страви
     */
    List<Dish> findAll();
    
    /**
     * Знайти страви за типом
     */
    List<Dish> findByDishType(DishType dishType);
    
    /**
     * Знайти доступні страви
     */
    List<Dish> findByAvailableTrue();
    
    /**
     * Знайти страви за назвою (часткове співпадіння)
     */
    List<Dish> findByNameContainingIgnoreCase(String name);
    
    /**
     * Знайти страви в ціновому діапазоні
     */
    List<Dish> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    /**
     * Видалити страву за ID
     */
    void deleteById(Long id);
    
    /**
     * Перевірити, чи існує страва з таким ID
     */
    boolean existsById(Long id);
}
