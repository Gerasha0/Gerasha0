package com.example2.dal.entities;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;
    
    @Column(nullable = false)
    private Integer quantity;
    
    @Column(nullable = false)
    private Double price;
    
    // Constructors
    public OrderItem() {}
    
    public OrderItem(Order order, Dish dish, Integer quantity) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (dish == null) {
            throw new IllegalArgumentException("Dish cannot be null");
        }
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        if (dish.getPrice() == null) {
            throw new IllegalArgumentException("Dish price cannot be null");
        }
        
        this.order = order;
        this.dish = dish;
        this.quantity = quantity;
        this.price = dish.getPrice() * quantity;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
    
    public Dish getDish() { return dish; }
    public void setDish(Dish dish) { this.dish = dish; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
