package com.example2.bll.dto;

import com.example2.dal.entities.OrderStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private Long id;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Double totalPrice;
    private Boolean isComplexLunch;
    private List<OrderItemDto> orderItems;
    
    // Constructors
    public OrderDto() {
        this.orderItems = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
    }
    
    public OrderDto(String customerName, String customerAddress, String customerPhone) {
        this();
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    
    public String getCustomerAddress() { return customerAddress; }
    public void setCustomerAddress(String customerAddress) { this.customerAddress = customerAddress; }
    
    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }
    
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    
    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
    
    public Boolean getIsComplexLunch() { return isComplexLunch; }
    public void setIsComplexLunch(Boolean isComplexLunch) { this.isComplexLunch = isComplexLunch; }
    
    public List<OrderItemDto> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItemDto> orderItems) { this.orderItems = orderItems; }
    
    public void addOrderItem(OrderItemDto orderItem) {
        this.orderItems.add(orderItem);
    }
}
