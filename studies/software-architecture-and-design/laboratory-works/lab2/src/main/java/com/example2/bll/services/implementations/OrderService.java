package com.example2.bll.services.implementations;

import com.example2.bll.dto.OrderDto;
import com.example2.bll.dto.OrderItemDto;
import com.example2.bll.mappers.OrderMapper;
import com.example2.bll.services.interfaces.IOrderService;
import com.example2.bll.exceptions.EntityNotFoundException;
import com.example2.bll.exceptions.InvalidOperationException;
import com.example2.dal.entities.Order;
import com.example2.dal.entities.OrderItem;
import com.example2.dal.entities.OrderStatus;
import com.example2.dal.entities.Dish;
import com.example2.dal.entities.ComplexLunch;
import com.example2.dal.uow.IUnitOfWork;
import com.example2.dal.config.DatabaseConfig;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class OrderService implements IOrderService {
    
    @Override
    public OrderDto createOrder(String customerName, String customerAddress, String customerPhone) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            unitOfWork.beginTransaction();
            
            Order order = new Order(customerName, customerAddress, customerPhone);
            Order savedOrder = unitOfWork.getOrderRepository().save(order);
            
            unitOfWork.commit();
            return OrderMapper.toDto(savedOrder);
        }
    }
    
    @Override
    public OrderDto createComplexLunchOrder(String customerName, String customerAddress, String customerPhone, Long complexLunchId) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            unitOfWork.beginTransaction();
            
            Optional<ComplexLunch> complexLunchOpt = unitOfWork.getComplexLunchRepository().findById(complexLunchId);
            if (!complexLunchOpt.isPresent()) {
                throw new EntityNotFoundException("Complex lunch", complexLunchId);
            }
            
            ComplexLunch complexLunch = complexLunchOpt.get();
            Order order = new Order(customerName, customerAddress, customerPhone);
            order.setIsComplexLunch(true);
            order.setTotalPrice(complexLunch.getPrice());
            
            // Add all dishes from complex lunch to order
            for (Dish dish : complexLunch.getDishes()) {
                OrderItem orderItem = new OrderItem(order, dish, 1);
                order.addOrderItem(orderItem);
            }
            
            Order savedOrder = unitOfWork.getOrderRepository().save(order);
            
            unitOfWork.commit();
            return OrderMapper.toDto(savedOrder);
        }
    }
    
    @Override
    public Optional<OrderDto> getOrderById(Long id) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            Optional<Order> order = unitOfWork.getOrderRepository().findById(id);
            return order.map(OrderMapper::toDto);
        }
    }
    
    @Override
    public List<OrderDto> getAllOrders() {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            List<Order> orders = unitOfWork.getOrderRepository().findAll();
            return OrderMapper.toDtoList(orders);
        }
    }
    
    @Override
    public List<OrderDto> getOrdersByStatus(OrderStatus status) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            List<Order> orders = unitOfWork.getOrderRepository().findByStatus(status);
            return OrderMapper.toDtoList(orders);
        }
    }
    
    @Override
    public List<OrderDto> getOrdersByCustomer(String customerName) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            List<Order> orders = unitOfWork.getOrderRepository().findByCustomerName(customerName);
            return OrderMapper.toDtoList(orders);
        }
    }
    
    @Override
    public List<OrderDto> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            List<Order> orders = unitOfWork.getOrderRepository().findByOrderDateBetween(startDate, endDate);
            return OrderMapper.toDtoList(orders);
        }
    }
    
    @Override
    public OrderDto addDishToOrder(Long orderId, Long dishId, Integer quantity) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            unitOfWork.beginTransaction();
            
            Optional<Order> orderOpt = unitOfWork.getOrderRepository().findById(orderId);
            Optional<Dish> dishOpt = unitOfWork.getDishRepository().findById(dishId);
            
            if (!orderOpt.isPresent()) {
                throw new EntityNotFoundException("Order", orderId);
            }
            if (!dishOpt.isPresent()) {
                throw new EntityNotFoundException("Dish", dishId);
            }
            
            Order order = orderOpt.get();
            Dish dish = dishOpt.get();
            
            if (order.getStatus() != OrderStatus.PENDING) {
                throw new InvalidOperationException("Cannot modify order that is not in PENDING status");
            }
            
            OrderItem orderItem = new OrderItem(order, dish, quantity);
            order.addOrderItem(orderItem);
            
            // Recalculate total price
            double totalPrice = order.getOrderItems().stream()
                    .mapToDouble(OrderItem::getPrice)
                    .sum();
            order.setTotalPrice(totalPrice);
            
            Order savedOrder = unitOfWork.getOrderRepository().save(order);
            
            unitOfWork.commit();
            return OrderMapper.toDto(savedOrder);
        }
    }
    
    @Override
    public OrderDto removeDishFromOrder(Long orderId, Long orderItemId) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            unitOfWork.beginTransaction();
            
            Optional<Order> orderOpt = unitOfWork.getOrderRepository().findById(orderId);
            if (!orderOpt.isPresent()) {
                throw new EntityNotFoundException("Order", orderId);
            }
            
            Order order = orderOpt.get();
            if (order.getStatus() != OrderStatus.PENDING) {
                throw new InvalidOperationException("Cannot modify order that is not in PENDING status");
            }
            
            order.getOrderItems().removeIf(item -> item.getId().equals(orderItemId));
            
            // Recalculate total price
            double totalPrice = order.getOrderItems().stream()
                    .mapToDouble(OrderItem::getPrice)
                    .sum();
            order.setTotalPrice(totalPrice);
            
            Order savedOrder = unitOfWork.getOrderRepository().save(order);
            
            unitOfWork.commit();
            return OrderMapper.toDto(savedOrder);
        }
    }
    
    @Override
    public OrderDto updateOrderStatus(Long orderId, OrderStatus status) {
        try (IUnitOfWork unitOfWork = new com.example2.dal.uow.UnitOfWork(DatabaseConfig.getEntityManagerFactory())) {
            unitOfWork.beginTransaction();
            
            Optional<Order> orderOpt = unitOfWork.getOrderRepository().findById(orderId);
            if (!orderOpt.isPresent()) {
                throw new EntityNotFoundException("Order", orderId);
            }
            
            Order order = orderOpt.get();
            order.setStatus(status);
            
            Order savedOrder = unitOfWork.getOrderRepository().save(order);
            
            unitOfWork.commit();
            return OrderMapper.toDto(savedOrder);
        }
    }
    
    @Override
    public Double calculateOrderTotal(OrderDto order) {
        if (order == null || order.getOrderItems() == null) {
            return 0.0;
        }
        return order.getOrderItems().stream()
                .filter(item -> item != null && item.getPrice() != null)
                .mapToDouble(OrderItemDto::getPrice)
                .sum();
    }
    
    @Override
    public OrderDto confirmOrder(Long orderId) {
        return updateOrderStatus(orderId, OrderStatus.CONFIRMED);
    }
    
    @Override
    public void cancelOrder(Long orderId) {
        updateOrderStatus(orderId, OrderStatus.CANCELLED);
    }
}
