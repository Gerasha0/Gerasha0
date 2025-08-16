package com.example3.domain.models;

/**
 * Тип замовлення
 */
public enum OrderType {
    COMPLEX_LUNCH("Комплексний обід"),
    CUSTOM("Індивідуальний набір страв"),
    DELIVERY("Доставка"),
    TAKEAWAY("Самовинос");
    
    private final String displayName;
    
    OrderType(String displayName) {
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
