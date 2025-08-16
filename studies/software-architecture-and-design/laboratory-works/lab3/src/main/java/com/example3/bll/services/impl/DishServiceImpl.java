package com.example3.bll.services.impl;

import com.example3.bll.services.DishService;
import com.example3.dal.repositories.DishRepository;
import com.example3.domain.models.Dish;
import com.example3.domain.models.DishType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Імплементація сервісу для роботи зі стравами
 */
@Service
public class DishServiceImpl implements DishService {
    
    private final DishRepository dishRepository;
    
    @Autowired
    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }
    
    @Override
    public Dish createDish(Dish dish) {
        validateDish(dish);
        return dishRepository.save(dish);
    }
    
    @Override
    public Dish updateDish(Long id, Dish dish) {
        if (!dishRepository.existsById(id)) {
            throw new IllegalArgumentException("Страва з ID " + id + " не знайдена");
        }
        validateDish(dish);
        dish.setId(id);
        return dishRepository.save(dish);
    }
    
    @Override
    public Optional<Dish> getDishById(Long id) {
        return dishRepository.findById(id);
    }
    
    @Override
    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }
    
    @Override
    public List<Dish> getAvailableDishes() {
        return dishRepository.findByAvailableTrue();
    }
    
    @Override
    public List<Dish> getDishesByType(DishType dishType) {
        if (dishType == null) {
            throw new IllegalArgumentException("Тип страви не може бути null");
        }
        return dishRepository.findByDishType(dishType);
    }
    
    @Override
    public List<Dish> searchDishesByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва для пошуку не може бути порожньою");
        }
        return dishRepository.findByNameContainingIgnoreCase(name.trim());
    }
    
    @Override
    public List<Dish> getDishesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice == null || maxPrice == null) {
            throw new IllegalArgumentException("Межі ціни не можуть бути null");
        }
        if (minPrice.compareTo(maxPrice) > 0) {
            throw new IllegalArgumentException("Мінімальна ціна не може бути більшою за максимальну");
        }
        return dishRepository.findByPriceBetween(minPrice, maxPrice);
    }
    
    @Override
    public void toggleDishAvailability(Long id) {
        Optional<Dish> dishOpt = dishRepository.findById(id);
        if (dishOpt.isEmpty()) {
            throw new IllegalArgumentException("Страва з ID " + id + " не знайдена");
        }
        
        Dish dish = dishOpt.get();
        dish.setAvailable(!dish.isAvailable());
        dishRepository.save(dish);
    }
    
    @Override
    public void deleteDish(Long id) {
        if (!dishRepository.existsById(id)) {
            throw new IllegalArgumentException("Страва з ID " + id + " не знайдена");
        }
        dishRepository.deleteById(id);
    }
    
    @Override
    public boolean dishExists(Long id) {
        return dishRepository.existsById(id);
    }
    
    /**
     * Валідація страви
     */
    private void validateDish(Dish dish) {
        if (dish == null) {
            throw new IllegalArgumentException("Страва не може бути null");
        }
        if (dish.getName() == null || dish.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Назва страви не може бути порожньою");
        }
        if (dish.getPrice() == null || dish.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Ціна страви повинна бути позитивною");
        }
        if (dish.getDishType() == null) {
            throw new IllegalArgumentException("Тип страви не може бути null");
        }
    }
}
