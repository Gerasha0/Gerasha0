package com.example1.factory;

import com.example1.model.GrantApplication;
import com.example1.model.GrantType;

/**
 * Абстрактна фабрика для шаблону Factory Method
 */
public abstract class GrantApplicationFactory {
    
    public abstract GrantApplication createGrantApplication(String id, String applicantName, 
                                                           String projectTitle, double requestedAmount);
    
    public GrantType getGrantType() {
        return getFactoryGrantType();
    }
    
    protected abstract GrantType getFactoryGrantType();
    
    // Template Method для валідації заявки
    public final boolean validateApplication(String applicantName, String projectTitle, double requestedAmount) {
        return validateApplicantName(applicantName) && 
               validateProjectTitle(projectTitle) && 
               validateAmount(requestedAmount) &&
               performSpecificValidation(applicantName, projectTitle, requestedAmount);
    }
    
    private boolean validateApplicantName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() >= 2;
    }
    
    private boolean validateProjectTitle(String title) {
        return title != null && !title.trim().isEmpty() && title.length() >= 5;
    }
    
    private boolean validateAmount(double amount) {
        return amount > 0 && amount <= getMaxAmount();
    }
    
    protected abstract double getMaxAmount();
    protected abstract boolean performSpecificValidation(String applicantName, String projectTitle, double requestedAmount);
    
    /**
     * Повертає опис вимог для даного типу гранту
     */
    public abstract String getValidationRequirements();
}
