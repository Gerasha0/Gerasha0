package com.example3.pl.controllers;

import com.example3.bll.services.DishService;
import com.example3.domain.models.Dish;
import com.example3.domain.models.DishType;
import com.example3.pl.dto.DishDto;
import com.example3.pl.mappers.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * WebAPI контролер для роботи зі стравами
 */
@RestController
@RequestMapping("/api/dishes")
@CrossOrigin(origins = "*") // Для тестування з фронтенду
public class DishController {
    
    private final DishService dishService;
    private final DtoMapper dtoMapper;
    
    @Autowired
    public DishController(DishService dishService, DtoMapper dtoMapper) {
        this.dishService = dishService;
        this.dtoMapper = dtoMapper;
    }
    
    /**
     * GET /api/dishes - Отримати всі страви
     */
    @GetMapping
    public ResponseEntity<List<DishDto>> getAllDishes() {
        try {
            List<Dish> dishes = dishService.getAllDishes();
            List<DishDto> dishDtos = dtoMapper.toDishDtoList(dishes);
            return ResponseEntity.ok(dishDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * GET /api/dishes/{id} - Отримати страву за ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<DishDto> getDishById(@PathVariable Long id) {
        try {
            Optional<Dish> dishOpt = dishService.getDishById(id);
            if (dishOpt.isPresent()) {
                DishDto dishDto = dtoMapper.toDto(dishOpt.get());
                return ResponseEntity.ok(dishDto);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * GET /api/dishes/available - Отримати доступні страви
     */
    @GetMapping("/available")
    public ResponseEntity<List<DishDto>> getAvailableDishes() {
        try {
            List<Dish> dishes = dishService.getAvailableDishes();
            List<DishDto> dishDtos = dtoMapper.toDishDtoList(dishes);
            return ResponseEntity.ok(dishDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * GET /api/dishes/by-type/{dishType} - Отримати страви за типом
     */
    @GetMapping("/by-type/{dishType}")
    public ResponseEntity<List<DishDto>> getDishesByType(@PathVariable DishType dishType) {
        try {
            List<Dish> dishes = dishService.getDishesByType(dishType);
            List<DishDto> dishDtos = dtoMapper.toDishDtoList(dishes);
            return ResponseEntity.ok(dishDtos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * GET /api/dishes/search?name={name} - Пошук страв за назвою
     */
    @GetMapping("/search")
    public ResponseEntity<List<DishDto>> searchDishesByName(@RequestParam String name) {
        try {
            List<Dish> dishes = dishService.searchDishesByName(name);
            List<DishDto> dishDtos = dtoMapper.toDishDtoList(dishes);
            return ResponseEntity.ok(dishDtos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * GET /api/dishes/price-range?minPrice={minPrice}&maxPrice={maxPrice} - Страви в ціновому діапазоні
     */
    @GetMapping("/price-range")
    public ResponseEntity<List<DishDto>> getDishesByPriceRange(
            @RequestParam BigDecimal minPrice, 
            @RequestParam BigDecimal maxPrice) {
        try {
            List<Dish> dishes = dishService.getDishesByPriceRange(minPrice, maxPrice);
            List<DishDto> dishDtos = dtoMapper.toDishDtoList(dishes);
            return ResponseEntity.ok(dishDtos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * POST /api/dishes - Створити нову страву
     */
    @PostMapping
    public ResponseEntity<DishDto> createDish(@RequestBody DishDto dishDto) {
        try {
            Dish dish = dtoMapper.toDomain(dishDto);
            Dish createdDish = dishService.createDish(dish);
            DishDto resultDto = dtoMapper.toDto(createdDish);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * PUT /api/dishes/{id} - Оновити страву
     */
    @PutMapping("/{id}")
    public ResponseEntity<DishDto> updateDish(@PathVariable Long id, @RequestBody DishDto dishDto) {
        try {
            Dish dish = dtoMapper.toDomain(dishDto);
            Dish updatedDish = dishService.updateDish(id, dish);
            DishDto resultDto = dtoMapper.toDto(updatedDish);
            return ResponseEntity.ok(resultDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * PUT /api/dishes/{id}/toggle-availability - Змінити доступність страви
     */
    @PutMapping("/{id}/toggle-availability")
    public ResponseEntity<Void> toggleDishAvailability(@PathVariable Long id) {
        try {
            dishService.toggleDishAvailability(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * DELETE /api/dishes/{id} - Видалити страву
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        try {
            dishService.deleteDish(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
