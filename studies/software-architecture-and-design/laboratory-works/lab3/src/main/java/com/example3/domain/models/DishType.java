package com.example3.domain.models;

/**
 * Тип страви
 */
public enum DishType {
    FIRST_COURSE("Перша страва"),
    SECOND_COURSE("Друга страва"),
    DESSERT("Десерт"),
    DRINK("Напій"),
    SALAD("Салат"),
    APPETIZER("Закуска");
    
    private final String displayName;
    
    DishType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
