package com.example2.bll.dto;

import com.example2.dal.entities.DishType;
import java.time.LocalDate;

public class DishDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private DishType dishType;
    private LocalDate availableDate;
    private Boolean isAvailable;
    
    // Constructors
    public DishDto() {}
    
    public DishDto(String name, String description, Double price, DishType dishType, LocalDate availableDate) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.dishType = dishType;
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
    
    public DishType getDishType() { return dishType; }
    public void setDishType(DishType dishType) { this.dishType = dishType; }
    
    public LocalDate getAvailableDate() { return availableDate; }
    public void setAvailableDate(LocalDate availableDate) { this.availableDate = availableDate; }
    
    public Boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(Boolean isAvailable) { this.isAvailable = isAvailable; }
    
    @Override
    public String toString() {
        return String.format("%s - %s (%.2f грн) [%s]", 
            name, description, price, dishType.getDisplayName());
    }
}
