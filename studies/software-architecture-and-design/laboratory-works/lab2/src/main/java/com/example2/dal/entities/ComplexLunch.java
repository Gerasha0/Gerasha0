package com.example2.dal.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "complex_lunches")
public class ComplexLunch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private Double price;
    
    @Column(name = "available_date")
    private LocalDate availableDate;
    
    @Column(name = "is_available")
    private Boolean isAvailable = true;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "complex_lunch_dishes",
        joinColumns = @JoinColumn(name = "complex_lunch_id"),
        inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> dishes = new ArrayList<>();
    
    // Constructors
    public ComplexLunch() {}
    
    public ComplexLunch(String name, String description, Double price, LocalDate availableDate) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Complex lunch name cannot be null or empty");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Complex lunch description cannot be null or empty");
        }
        if (price == null || price <= 0) {
            throw new IllegalArgumentException("Complex lunch price must be greater than 0");
        }
        
        this.name = name;
        this.description = description;
        this.price = price;
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
    
    public LocalDate getAvailableDate() { return availableDate; }
    public void setAvailableDate(LocalDate availableDate) { this.availableDate = availableDate; }
    
    public Boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(Boolean isAvailable) { this.isAvailable = isAvailable; }
    
    public List<Dish> getDishes() { return dishes; }
    public void setDishes(List<Dish> dishes) { this.dishes = dishes; }
    
    public void addDish(Dish dish) {
        if (dish == null) {
            throw new IllegalArgumentException("Dish cannot be null");
        }
        if (this.dishes == null) {
            this.dishes = new ArrayList<>();
        }
        if (!this.dishes.contains(dish)) {
            this.dishes.add(dish);
        }
    }
}
