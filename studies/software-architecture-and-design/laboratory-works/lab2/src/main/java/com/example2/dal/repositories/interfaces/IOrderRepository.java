package com.example2.dal.repositories.interfaces;

import com.example2.dal.entities.Order;
import com.example2.dal.entities.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;

public interface IOrderRepository extends IGenericRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByCustomerName(String customerName);
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Order> findByIsComplexLunch(Boolean isComplexLunch);
}
