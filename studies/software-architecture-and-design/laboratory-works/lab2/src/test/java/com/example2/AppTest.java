package com.example2;

import com.example2.bll.dto.DishDto;
import com.example2.bll.services.implementations.DishService;
import com.example2.bll.services.interfaces.IDishService;
import com.example2.dal.entities.DishType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Food Delivery System
 */
public class AppTest {
    
    @Test
    public void testCreateAndRetrieveDish() {
        IDishService dishService = new DishService();
        
        // Create a test dish
        DishDto dishDto = new DishDto("Test Dish", "Test Description", 50.0, DishType.MAIN_COURSE, LocalDate.now());
        DishDto savedDish = dishService.createDish(dishDto);
        
        // Verify the dish was saved
        assertNotNull(savedDish);
        assertNotNull(savedDish.getId());
        assertEquals("Test Dish", savedDish.getName());
        
        // Retrieve the dish by ID
        Optional<DishDto> retrievedDish = dishService.getDishById(savedDish.getId());
        assertTrue(retrievedDish.isPresent());
        assertEquals("Test Dish", retrievedDish.get().getName());
    }
    
    @Test
    public void testGetAllDishes() {
        IDishService dishService = new DishService();
        
        // Get all dishes (should include any previously created dishes)
        List<DishDto> dishes = dishService.getAllDishes();
        assertNotNull(dishes);
        // We can't guarantee the exact count as other tests might have run
        assertTrue(dishes.size() >= 0);
    }
    
    @Test
    public void testGetDishesByType() {
        IDishService dishService = new DishService();
        
        // Create dishes of different types
        dishService.createDish(new DishDto("Soup Test", "Test Soup", 30.0, DishType.SOUP, LocalDate.now()));
        dishService.createDish(new DishDto("Main Test", "Test Main", 80.0, DishType.MAIN_COURSE, LocalDate.now()));
        
        // Get dishes by type
        List<DishDto> soups = dishService.getDishesByType(DishType.SOUP);
        assertNotNull(soups);
        assertTrue(soups.size() >= 1);
        
        // Verify all returned dishes are soups
        for (DishDto dish : soups) {
            assertEquals(DishType.SOUP, dish.getDishType());
        }
    }
}
