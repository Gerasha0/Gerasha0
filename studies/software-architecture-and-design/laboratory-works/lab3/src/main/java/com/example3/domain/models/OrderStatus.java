package com.example3.domain.models;

/**
 * Статус замовлення
 */
public enum OrderStatus {
    PENDING("Очікує підтвердження"),
    CONFIRMED("Підтверджено"),
    PREPARING("Готується"),
    READY("Готово"),
    DELIVERING("Доставляється"),
    DELIVERED("Доставлено"),
    CANCELLED("Скасовано");
    
    private final String displayName;
    
    OrderStatus(String displayName) {
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
