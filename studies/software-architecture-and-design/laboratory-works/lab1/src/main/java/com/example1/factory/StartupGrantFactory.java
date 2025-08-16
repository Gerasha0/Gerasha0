package com.example1.factory;

import com.example1.model.GrantApplication;
import com.example1.model.GrantType;

/**
 * Конкретна фабрика для стартап грантів
 */
public class StartupGrantFactory extends GrantApplicationFactory {
    
    @Override
    public GrantApplication createGrantApplication(String id, String applicantName, 
                                                 String projectTitle, double requestedAmount) {
        return new GrantApplication(id, applicantName, projectTitle, requestedAmount, GrantType.STARTUP);
    }
    
    @Override
    protected GrantType getFactoryGrantType() {
        return GrantType.STARTUP;
    }
    
    @Override
    protected double getMaxAmount() {
        return 200000.0; // Максимальна сума для стартап гранту
    }
    
    @Override
    protected boolean performSpecificValidation(String applicantName, String projectTitle, double requestedAmount) {
        // Специфічна валідація для стартап грантів
        return projectTitle.toLowerCase().contains("стартап") || 
               projectTitle.toLowerCase().contains("бізнес") ||
               projectTitle.toLowerCase().contains("підприємництво") ||
               projectTitle.toLowerCase().contains("інновація");
    }
}
