package com.example3.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Доменна модель для замовлення
 */
public class Order {
    
    private Long id;
    private String customerName;
    private String customerPhone;
    private String deliveryAddress;
    private LocalDateTime orderDateTime;
    private OrderStatus status;
    private List<OrderItem> orderItems;
    private BigDecimal totalAmount;
    private OrderType orderType;
    
    // Конструктори
    public Order() {
        this.orderItems = new ArrayList<>();
        this.orderDateTime = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
        this.totalAmount = BigDecimal.ZERO;
    }
    
    public Order(String customerName, String customerPhone, String deliveryAddress, OrderType orderType) {
        this();
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.deliveryAddress = deliveryAddress;
        this.orderType = orderType;
    }
    
    // Методи для роботи з позиціями замовлення
    public void addOrderItem(Dish dish, int quantity) {
        OrderItem existingItem = findOrderItem(dish);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            orderItems.add(new OrderItem(dish, quantity));
        }
        recalculateTotal();
    }
    
    public void removeOrderItem(Dish dish) {
        orderItems.removeIf(item -> item.getDish().getId().equals(dish.getId()));
        recalculateTotal();
    }
    
    private OrderItem findOrderItem(Dish dish) {
        return orderItems.stream()
                .filter(item -> item.getDish().getId().equals(dish.getId()))
                .findFirst()
                .orElse(null);
    }
    
    public void recalculateTotal() {
        this.totalAmount = orderItems.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    // Getters та Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getCustomerPhone() {
        return customerPhone;
    }
    
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    
    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }
    
    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }
    
    public OrderStatus getStatus() {
        return status;
    }
    
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    
    public List<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems);
    }
    
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems != null ? new ArrayList<>(orderItems) : new ArrayList<>();
        recalculateTotal();
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public OrderType getOrderType() {
        return orderType;
    }
    
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", orderDateTime=" + orderDateTime +
                ", status=" + status +
                ", totalAmount=" + totalAmount +
                ", orderType=" + orderType +
                '}';
    }
}
