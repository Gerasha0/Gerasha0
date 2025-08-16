package com.example3.bll.services.impl;

import com.example3.bll.services.DishService;
import com.example3.bll.services.OrderService;
import com.example3.dal.repositories.OrderRepository;
import com.example3.domain.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Імплементація сервісу для роботи із замовленнями
 */
@Service
public class OrderServiceImpl implements OrderService {
    
    private final OrderRepository orderRepository;
    private final DishService dishService;
    
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, DishService dishService) {
        this.orderRepository = orderRepository;
        this.dishService = dishService;
    }
    
    @Override
    public Order createOrder(Order order) {
        validateOrder(order);
        order.setOrderDateTime(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.recalculateTotal();
        return orderRepository.save(order);
    }
    
    @Override
    public Order addDishToOrder(Long orderId, Long dishId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Кількість повинна бути позитивною");
        }
        
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            throw new IllegalArgumentException("Замовлення з ID " + orderId + " не знайдено");
        }
        
        Optional<Dish> dishOpt = dishService.getDishById(dishId);
        if (dishOpt.isEmpty()) {
            throw new IllegalArgumentException("Страва з ID " + dishId + " не знайдена");
        }
        
        Dish dish = dishOpt.get();
        if (!dish.isAvailable()) {
            throw new IllegalArgumentException("Страва '" + dish.getName() + "' недоступна");
        }
        
        Order order = orderOpt.get();
        order.addOrderItem(dish, quantity);
        return orderRepository.save(order);
    }
    
    @Override
    public Order removeDishFromOrder(Long orderId, Long dishId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            throw new IllegalArgumentException("Замовлення з ID " + orderId + " не знайдено");
        }
        
        Optional<Dish> dishOpt = dishService.getDishById(dishId);
        if (dishOpt.isEmpty()) {
            throw new IllegalArgumentException("Страва з ID " + dishId + " не знайдена");
        }
        
        Order order = orderOpt.get();
        order.removeOrderItem(dishOpt.get());
        return orderRepository.save(order);
    }
    
    @Override
    public Order updateOrderStatus(Long id, OrderStatus status) {
        Optional<Order> orderOpt = orderRepository.findById(id);
        if (orderOpt.isEmpty()) {
            throw new IllegalArgumentException("Замовлення з ID " + id + " не знайдено");
        }
        
        Order order = orderOpt.get();
        order.setStatus(status);
        return orderRepository.save(order);
    }
    
    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
    
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }
    
    @Override
    public List<Order> getOrdersByCustomerPhone(String customerPhone) {
        if (customerPhone == null || customerPhone.trim().isEmpty()) {
            throw new IllegalArgumentException("Телефон клієнта не може бути порожнім");
        }
        return orderRepository.findByCustomerPhone(customerPhone.trim());
    }
    
    @Override
    public List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Дати не можуть бути null");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Початкова дата не може бути пізніше кінцевої");
        }
        return orderRepository.findByOrderDateTimeBetween(startDate, endDate);
    }
    
    @Override
    public Order createComplexLunch(String customerName, String customerPhone, String deliveryAddress,
                                  Long firstCourseId, Long secondCourseId, Long saladId, Long drinkId) {
        
        Order order = new Order(customerName, customerPhone, deliveryAddress, OrderType.COMPLEX_LUNCH);
        order = orderRepository.save(order);
        
        // Додаємо страви до комплексного обіду
        if (firstCourseId != null) {
            addDishToOrder(order.getId(), firstCourseId, 1);
        }
        if (secondCourseId != null) {
            addDishToOrder(order.getId(), secondCourseId, 1);
        }
        if (saladId != null) {
            addDishToOrder(order.getId(), saladId, 1);
        }
        if (drinkId != null) {
            addDishToOrder(order.getId(), drinkId, 1);
        }
        
        return orderRepository.findById(order.getId()).orElse(order);
    }
    
    @Override
    public Order confirmOrder(Long orderId) {
        return updateOrderStatus(orderId, OrderStatus.CONFIRMED);
    }
    
    @Override
    public Order cancelOrder(Long orderId) {
        return updateOrderStatus(orderId, OrderStatus.CANCELLED);
    }
    
    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Замовлення з ID " + id + " не знайдено");
        }
        orderRepository.deleteById(id);
    }
    
    /**
     * Валідація замовлення
     */
    private void validateOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Замовлення не може бути null");
        }
        if (order.getCustomerName() == null || order.getCustomerName().trim().isEmpty()) {
            throw new IllegalArgumentException("Ім'я клієнта не може бути порожнім");
        }
        if (order.getCustomerPhone() == null || order.getCustomerPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("Телефон клієнта не може бути порожнім");
        }
        if (order.getDeliveryAddress() == null || order.getDeliveryAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Адреса доставки не може бути порожньою");
        }
    }
}
