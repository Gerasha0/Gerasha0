package com.example3.pl.dto;

import com.example3.domain.models.DishType;
import java.math.BigDecimal;

/**
 * DTO для страви на рівні представлення
 */
public class DishDto {
    
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private DishType dishType;
    private boolean available;
    
    // Конструктори
    public DishDto() {}
    
    public DishDto(Long id, String name, String description, BigDecimal price, 
                   DishType dishType, boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.dishType = dishType;
        this.available = available;
    }
    
    // Getters та Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public DishType getDishType() {
        return dishType;
    }
    
    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
