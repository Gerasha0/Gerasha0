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

    Order save(Order order);

    Optional<Order> findById(Long id);

    List<Order> findAll();

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByOrderType(OrderType orderType);

    List<Order> findByCustomerPhone(String customerPhone);

    List<Order> findByOrderDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    void deleteById(Long id);

    boolean existsById(Long id);
}
