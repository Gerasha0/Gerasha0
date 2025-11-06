package com.example3.bll.services;

import com.example3.domain.models.Order;
import com.example3.domain.models.OrderStatus;
import com.example3.domain.models.OrderType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Інтерфейс сервісу для роботи із замовленнями
 */
public interface OrderService {
    
    Order createOrder(Order order);
    
    Order addDishToOrder(Long orderId, Long dishId, int quantity);
    
    Order removeDishFromOrder(Long orderId, Long dishId);
    
    Order updateOrderStatus(Long id, OrderStatus status);
    
    Optional<Order> getOrderById(Long id);
    
    List<Order> getAllOrders();
    
    List<Order> getOrdersByStatus(OrderStatus status);
    
    List<Order> getOrdersByCustomerPhone(String customerPhone);
    
    List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    
    Order createComplexLunch(String customerName, String customerPhone, String deliveryAddress,
                           Long firstCourseId, Long secondCourseId, Long saladId, Long drinkId);
    
    Order confirmOrder(Long orderId);
    
    Order cancelOrder(Long orderId);

    void deleteOrder(Long id);
}
