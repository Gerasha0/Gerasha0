package com.example3.pl.dto;

/**
 * DTO для створення комплексного обіду
 */
public class ComplexLunchDto {
    
    private String customerName;
    private String customerPhone;
    private String deliveryAddress;
    private Long firstCourseId;
    private Long secondCourseId;
    private Long saladId;
    private Long drinkId;
    
    // Конструктори
    public ComplexLunchDto() {
    }
    
    // Getters та Setters
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getCustomerPhone() {
        return customerPhone;
    }
    
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    
    public Long getFirstCourseId() {
        return firstCourseId;
    }
    
    public void setFirstCourseId(Long firstCourseId) {
        this.firstCourseId = firstCourseId;
    }
    
    public Long getSecondCourseId() {
        return secondCourseId;
    }
    
    public void setSecondCourseId(Long secondCourseId) {
        this.secondCourseId = secondCourseId;
    }
    
    public Long getSaladId() {
        return saladId;
    }
    
    public void setSaladId(Long saladId) {
        this.saladId = saladId;
    }
    
    public Long getDrinkId() {
        return drinkId;
    }
    
    public void setDrinkId(Long drinkId) {
        this.drinkId = drinkId;
    }
}
