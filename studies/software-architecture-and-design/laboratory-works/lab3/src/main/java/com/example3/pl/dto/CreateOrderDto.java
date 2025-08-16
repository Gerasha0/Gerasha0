package com.example3.pl.dto;

import com.example3.domain.models.OrderType;

/**
 * DTO для створення нового замовлення
 */
public class CreateOrderDto {
    
    private String customerName;
    private String customerPhone;
    private String deliveryAddress;
    private OrderType orderType;
    
    // Конструктори
    public CreateOrderDto() {
        // Порожній конструктор для десеріалізації
    }
    
    public CreateOrderDto(String customerName, String customerPhone, 
                         String deliveryAddress, OrderType orderType) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.deliveryAddress = deliveryAddress;
        this.orderType = orderType;
    }
    
    // Getters та Setters
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
    
    public OrderType getOrderType() {
        return orderType;
    }
    
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
}
