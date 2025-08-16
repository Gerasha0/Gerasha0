package com.example2.dal.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private Double price;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DishType dishType;
    
    @Column(name = "available_date")
    private LocalDate availableDate;
    
    @Column(name = "is_available")
    private Boolean isAvailable = true;
    
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();
    
    // Constructors
    public Dish() {}
    
    public Dish(String name, String description, Double price, DishType dishType, LocalDate availableDate) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Dish name cannot be null or empty");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Dish description cannot be null or empty");
        }
        if (price == null || price <= 0) {
            throw new IllegalArgumentException("Dish price must be greater than 0");
        }
        if (dishType == null) {
            throw new IllegalArgumentException("Dish type cannot be null");
        }
        
        this.name = name;
        this.description = description;
        this.price = price;
        this.dishType = dishType;
        this.availableDate = availableDate;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    public DishType getDishType() { return dishType; }
    public void setDishType(DishType dishType) { this.dishType = dishType; }
    
    public LocalDate getAvailableDate() { return availableDate; }
    public void setAvailableDate(LocalDate availableDate) { this.availableDate = availableDate; }
    
    public Boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(Boolean isAvailable) { this.isAvailable = isAvailable; }
    
    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
}
