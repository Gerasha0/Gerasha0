package com.example3.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;

/**
 * Позиція замовлення
 */
public class OrderItem {
    
    private Long id;
    private Dish dish;
    private int quantity;
    private BigDecimal unitPrice;
    
    // Конструктори
    public OrderItem() {}
    
    public OrderItem(Dish dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
        this.unitPrice = dish.getPrice();
    }
    
    // Розрахунок загальної вартості позиції
    @JsonIgnore
    public BigDecimal getSubtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
    
    // Getters та Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Dish getDish() {
        return dish;
    }
    
    public void setDish(Dish dish) {
        this.dish = dish;
        if (dish != null) {
            this.unitPrice = dish.getPrice();
        }
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", dish=" + dish +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", subtotal=" + getSubtotal() +
                '}';
    }
}
