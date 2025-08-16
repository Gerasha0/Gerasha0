package com.example3.dal.repositories;

import com.example3.domain.models.Order;
import com.example3.domain.models.OrderStatus;
import com.example3.domain.models.OrderType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Інтерфейс репозиторію для роботи із замовленнями
 */
public interface OrderRepository {
    
    /**
     * Зберегти замовлення
     */
    Order save(Order order);
    
    /**
     * Знайти замовлення за ID
     */
    Optional<Order> findById(Long id);
    
    /**
     * Знайти всі замовлення
     */
    List<Order> findAll();
    
    /**
     * Знайти замовлення за статусом
     */
    List<Order> findByStatus(OrderStatus status);
    
    /**
     * Знайти замовлення за типом
     */
    List<Order> findByOrderType(OrderType orderType);
    
    /**
     * Знайти замовлення клієнта за телефоном
     */
    List<Order> findByCustomerPhone(String customerPhone);
    
    /**
     * Знайти замовлення за періодом
     */
    List<Order> findByOrderDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Видалити замовлення за ID
     */
    void deleteById(Long id);
    
    /**
     * Перевірити, чи існує замовлення з таким ID
     */
    boolean existsById(Long id);
}
