package com.example2.bll.services.interfaces;

import com.example2.bll.dto.DishDto;
import com.example2.dal.entities.DishType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IDishService {
    DishDto createDish(DishDto dishDto);
    Optional<DishDto> getDishById(Long id);
    List<DishDto> getAllDishes();
    List<DishDto> getDishesByType(DishType dishType);
    List<DishDto> getDishesByDate(LocalDate date);
    List<DishDto> getDishesByTypeAndDate(DishType dishType, LocalDate date);
    List<DishDto> getAvailableDishes();
    List<DishDto> searchDishesByName(String name);
    List<DishDto> getDishesByPriceRange(Double minPrice, Double maxPrice);
    DishDto updateDish(DishDto dishDto);
    void deleteDish(Long id);
    void setDishAvailability(Long id, Boolean isAvailable);
}
