package com.example2.dal.entities;

public enum DishType {
    APPETIZER("Закуска"),
    SOUP("Суп"),
    MAIN_COURSE("Основне блюдо"),
    DESSERT("Десерт"),
    BEVERAGE("Напiй"),
    SALAD("Салат");
    
    private final String displayName;
    
    DishType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
