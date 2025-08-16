package com.example3.bll.services.impl;

import com.example3.bll.services.DishService;
import com.example3.dal.repositories.OrderRepository;
import com.example3.domain.models.*;
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
 * Тести для OrderServiceImpl
 */
@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    
    @Mock
    private OrderRepository orderRepository;
    
    @Mock
    private DishService dishService;
    
    @InjectMocks
    private OrderServiceImpl orderService;
    
    private Order testOrder;
    private Dish testDish;
    
    @BeforeEach
    void setUp() {
        testDish = new Dish("Борщ", "Український борщ", 
                new BigDecimal("85.00"), DishType.FIRST_COURSE);
        testDish.setId(1L);
        
        testOrder = new Order("Іван Петров", "+380501234567", "вул. Хрещатик 1", OrderType.CUSTOM);
        testOrder.setId(1L);
        testOrder.addOrderItem(testDish, 2);
    }
    
    @Test
    void testCreateOrder() {
        // Arrange
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);
        
        // Act
        Order result = orderService.createOrder(testOrder);
        
        // Assert
        assertNotNull(result);
        assertEquals(testOrder.getCustomerName(), result.getCustomerName());
        assertEquals(OrderStatus.PENDING, result.getStatus());
        verify(orderRepository, times(1)).save(testOrder);
    }
    
    @Test
    void testCreateOrder_WithNullOrder_ShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.createOrder(null);
        });
    }
    
    @Test
    void testGetOrderById() {
        // Arrange
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        
        // Act
        Optional<Order> result = orderService.getOrderById(1L);
        
        // Assert
        assertTrue(result.isPresent());
        assertEquals(testOrder.getCustomerName(), result.get().getCustomerName());
        verify(orderRepository, times(1)).findById(1L);
    }
    
    @Test
    void testGetAllOrders() {
        // Arrange
        List<Order> expectedOrders = Arrays.asList(testOrder);
        when(orderRepository.findAll()).thenReturn(expectedOrders);
        
        // Act
        List<Order> actualOrders = orderService.getAllOrders();
        
        // Assert
        assertEquals(expectedOrders.size(), actualOrders.size());
        assertEquals(expectedOrders.get(0).getCustomerName(), actualOrders.get(0).getCustomerName());
        verify(orderRepository, times(1)).findAll();
    }
    
    @Test
    void testConfirmOrder() {
        // Arrange
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);
        
        // Act
        Order result = orderService.confirmOrder(1L);
        
        // Assert
        assertNotNull(result);
        assertEquals(OrderStatus.CONFIRMED, result.getStatus());
        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(testOrder);
    }
    
    @Test
    void testConfirmOrder_WithNonExistentOrder_ShouldThrowException() {
        // Arrange
        when(orderRepository.findById(999L)).thenReturn(Optional.empty());
        
        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            orderService.confirmOrder(999L);
        });
        
        verify(orderRepository, times(1)).findById(999L);
        verify(orderRepository, never()).save(any(Order.class));
    }
    
    @Test
    void testCalculateOrderTotal() {
        // Arrange
        Order order = new Order("Тест", "+380501234567", "Адреса", OrderType.CUSTOM);
        order.addOrderItem(testDish, 2);
        
        Dish secondDish = new Dish("Салат", "Салат з овочів", 
                new BigDecimal("45.00"), DishType.APPETIZER);
        order.addOrderItem(secondDish, 1);
        
        // Act
        BigDecimal total = order.getTotalAmount();
        
        // Assert
        assertEquals(new BigDecimal("215.00"), total); // 85*2 + 45*1 = 215
    }
}
