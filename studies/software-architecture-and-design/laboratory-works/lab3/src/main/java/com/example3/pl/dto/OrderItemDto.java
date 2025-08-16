package com.example3.pl.dto;

import java.math.BigDecimal;

/**
 * DTO для позиції замовлення на рівні представлення
 */
public class OrderItemDto {
    
    private Long id;
    private DishDto dish;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
    
    // Конструктори
    public OrderItemDto() {
        // Порожній конструктор для серіалізації
    }
    
    public OrderItemDto(DishDto dish, int quantity, BigDecimal unitPrice) {
        this.dish = dish;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
    
    // Getters та Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public DishDto getDish() {
        return dish;
    }
    
    public void setDish(DishDto dish) {
        this.dish = dish;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        recalculateSubtotal();
    }
    
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        recalculateSubtotal();
    }
    
    public BigDecimal getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    
    private void recalculateSubtotal() {
        if (unitPrice != null && quantity > 0) {
            this.subtotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }
}
