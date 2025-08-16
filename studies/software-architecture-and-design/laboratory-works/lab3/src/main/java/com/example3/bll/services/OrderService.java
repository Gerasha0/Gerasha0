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
    
    /**
     * Створити нове замовлення
     */
    Order createOrder(Order order);
    
    /**
     * Додати страву до замовлення
     */
    Order addDishToOrder(Long orderId, Long dishId, int quantity);
    
    /**
     * Видалити страву з замовлення
     */
    Order removeDishFromOrder(Long orderId, Long dishId);
    
    /**
     * Оновити статус замовлення
     */
    Order updateOrderStatus(Long id, OrderStatus status);
    
    /**
     * Знайти замовлення за ID
     */
    Optional<Order> getOrderById(Long id);
    
    /**
     * Отримати всі замовлення
     */
    List<Order> getAllOrders();
    
    /**
     * Знайти замовлення за статусом
     */
    List<Order> getOrdersByStatus(OrderStatus status);
    
    /**
     * Знайти замовлення клієнта
     */
    List<Order> getOrdersByCustomerPhone(String customerPhone);
    
    /**
     * Знайти замовлення за періодом
     */
    List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Створити комплексний обід
     */
    Order createComplexLunch(String customerName, String customerPhone, String deliveryAddress,
                           Long firstCourseId, Long secondCourseId, Long saladId, Long drinkId);
    
    /**
     * Підтвердити замовлення
     */
    Order confirmOrder(Long orderId);
    
    /**
     * Скасувати замовлення
     */
    Order cancelOrder(Long orderId);
    
    /**
     * Видалити замовлення
     */
    void deleteOrder(Long id);
}
