package com.example3.domain.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Доменна модель для меню на конкретний день
 */
public class DailyMenu {
    
    private Long id;
    private LocalDate date;
    private List<Dish> dishes;
    private boolean active;
    
    // Конструктори
    public DailyMenu() {
        this.dishes = new ArrayList<>();
        this.active = true;
    }
    
    public DailyMenu(LocalDate date) {
        this();
        this.date = date;
    }
    
    // Методи для роботи зі стравами
    public void addDish(Dish dish) {
        if (dish != null && !dishes.contains(dish)) {
            dishes.add(dish);
        }
    }
    
    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }
    
    public List<Dish> getDishesByType(DishType dishType) {
        return dishes.stream()
                .filter(dish -> dish.getDishType() == dishType)
                .toList();
    }
    
    // Getters та Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public List<Dish> getDishes() {
        return new ArrayList<>(dishes);
    }
    
    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes != null ? new ArrayList<>(dishes) : new ArrayList<>();
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    @Override
    public String toString() {
        return "DailyMenu{" +
                "id=" + id +
                ", date=" + date +
                ", dishesCount=" + dishes.size() +
                ", active=" + active +
                '}';
    }
}
