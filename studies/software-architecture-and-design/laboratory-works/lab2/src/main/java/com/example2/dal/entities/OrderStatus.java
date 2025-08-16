package com.example2.dal.entities;

public enum OrderStatus {
    PENDING("В очiкуваннi"),
    CONFIRMED("Пiдтверджено"),
    PREPARING("Готуeться"),
    READY("Готово"),
    DELIVERED("Доставлено"),
    CANCELLED("Скасовано");
    
    private final String displayName;
    
    OrderStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
