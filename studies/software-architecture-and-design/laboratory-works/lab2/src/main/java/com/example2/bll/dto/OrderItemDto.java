package com.example2.bll.dto;

public class OrderItemDto {
    private Long id;
    private DishDto dish;
    private Integer quantity;
    private Double price;
    
    // Constructors
    public OrderItemDto() {}
    
    public OrderItemDto(DishDto dish, Integer quantity) {
        this.dish = dish;
        this.quantity = quantity;
        this.price = dish.getPrice() * quantity;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public DishDto getDish() { return dish; }
    public void setDish(DishDto dish) { this.dish = dish; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    @Override
    public String toString() {
        return String.format("%s x%d = %.2f грн", dish.getName(), quantity, price);
    }
}
