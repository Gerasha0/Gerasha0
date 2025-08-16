package com.example3.bll.services.impl;

import com.example3.dal.repositories.DishRepository;
import com.example3.domain.models.Dish;
import com.example3.domain.models.DishType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Тести для DishServiceImpl
 */
@ExtendWith(MockitoExtension.class)
public class DishServiceImplTest {
    
    @Mock
    private DishRepository dishRepository;
    
    @InjectMocks
    private DishServiceImpl dishService;
    
    private Dish testDish;
    
    @BeforeEach
    public void setUp() {
        testDish = new Dish("Борщ", "Український борщ", 
                new BigDecimal("85.00"), DishType.FIRST_COURSE);
        testDish.setId(1L);
    }
    
    @Test
    public void testGetAllDishes() {
        // Arrange
        List<Dish> expectedDishes = Arrays.asList(testDish);
        when(dishRepository.findAll()).thenReturn(expectedDishes);
        
        // Act
        List<Dish> actualDishes = dishService.getAllDishes();
        
        // Assert
        assertEquals(expectedDishes.size(), actualDishes.size());
        assertEquals(expectedDishes.get(0).getName(), actualDishes.get(0).getName());
        verify(dishRepository, times(1)).findAll();
    }
    
    @Test
    public void testGetDishById() {
        // Arrange
        when(dishRepository.findById(1L)).thenReturn(Optional.of(testDish));
        
        // Act
        Optional<Dish> result = dishService.getDishById(1L);
        
        // Assert
        assertTrue(result.isPresent());
        assertEquals(testDish.getName(), result.get().getName());
        verify(dishRepository, times(1)).findById(1L);
    }
    
    @Test
    public void testCreateDish() {
        // Arrange
        when(dishRepository.save(any(Dish.class))).thenReturn(testDish);
        
        // Act
        Dish result = dishService.createDish(testDish);
        
        // Assert
        assertNotNull(result);
        assertEquals(testDish.getName(), result.getName());
        verify(dishRepository, times(1)).save(testDish);
    }
    
    @Test
    public void testCreateDish_WithNullDish_ShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            dishService.createDish(null);
        });
    }
    
    @Test
    public void testCreateDish_WithEmptyName_ShouldThrowException() {
        // Arrange
        testDish.setName("");
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            dishService.createDish(testDish);
        });
    }
    
    @Test
    public void testGetDishesByType() {
        // Arrange
        List<Dish> expectedDishes = Arrays.asList(testDish);
        when(dishRepository.findByDishType(DishType.FIRST_COURSE)).thenReturn(expectedDishes);
        
        // Act
        List<Dish> result = dishService.getDishesByType(DishType.FIRST_COURSE);
        
        // Assert
        assertEquals(expectedDishes.size(), result.size());
        assertEquals(DishType.FIRST_COURSE, result.get(0).getDishType());
        verify(dishRepository, times(1)).findByDishType(DishType.FIRST_COURSE);
    }
    
    @Test
    public void testToggleDishAvailability() {
        // Arrange
        testDish.setAvailable(true);
        when(dishRepository.findById(1L)).thenReturn(Optional.of(testDish));
        when(dishRepository.save(any(Dish.class))).thenReturn(testDish);
        
        // Act
        dishService.toggleDishAvailability(1L);
        
        // Assert
        assertFalse(testDish.isAvailable());
        verify(dishRepository, times(1)).findById(1L);
        verify(dishRepository, times(1)).save(testDish);
    }
}
