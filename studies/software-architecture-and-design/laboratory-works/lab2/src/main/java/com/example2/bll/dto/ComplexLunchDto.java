package com.example2.bll.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ComplexLunchDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private LocalDate availableDate;
    private Boolean isAvailable;
    private List<DishDto> dishes;
    
    // Constructors
    public ComplexLunchDto() {
        this.dishes = new ArrayList<>();
    }
    
    public ComplexLunchDto(String name, String description, Double price, LocalDate availableDate) {
        this();
        this.name = name;
        this.description = description;
        this.price = price;
        this.availableDate = availableDate;
        this.isAvailable = true;
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
    
    public List<DishDto> getDishes() { return dishes; }
    public void setDishes(List<DishDto> dishes) { this.dishes = dishes; }
    
    public void addDish(DishDto dish) {
        this.dishes.add(dish);
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s (%.2f грн)", name, description, price);
    }
}
