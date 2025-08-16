package com.example2.dal.repositories.interfaces;

import com.example2.dal.entities.Dish;
import com.example2.dal.entities.DishType;
import java.time.LocalDate;
import java.util.List;

public interface IDishRepository extends IGenericRepository<Dish, Long> {
    List<Dish> findByDishType(DishType dishType);
    List<Dish> findByAvailableDate(LocalDate date);
    List<Dish> findByDishTypeAndAvailableDate(DishType dishType, LocalDate date);
    List<Dish> findAvailableDishes();
    List<Dish> findByNameContainingIgnoreCase(String name);
    List<Dish> findByPriceBetween(Double minPrice, Double maxPrice);
}
