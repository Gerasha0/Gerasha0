package com.example2.bll.services.interfaces;

import com.example2.bll.dto.OrderDto;
import com.example2.bll.dto.OrderItemDto;
import com.example2.dal.entities.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IOrderService {
    OrderDto createOrder(String customerName, String customerAddress, String customerPhone);
    OrderDto createComplexLunchOrder(String customerName, String customerAddress, String customerPhone, Long complexLunchId);
    Optional<OrderDto> getOrderById(Long id);
    List<OrderDto> getAllOrders();
    List<OrderDto> getOrdersByStatus(OrderStatus status);
    List<OrderDto> getOrdersByCustomer(String customerName);
    List<OrderDto> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    OrderDto addDishToOrder(Long orderId, Long dishId, Integer quantity);
    OrderDto removeDishFromOrder(Long orderId, Long orderItemId);
    OrderDto updateOrderStatus(Long orderId, OrderStatus status);
    Double calculateOrderTotal(OrderDto order);
    OrderDto confirmOrder(Long orderId);
    void cancelOrder(Long orderId);
}
