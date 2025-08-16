package com.example1.factory;

import com.example1.model.GrantApplication;
import com.example1.model.GrantType;

/**
 * Конкретна фабрика для культурних грантів
 */
public class CulturalGrantFactory extends GrantApplicationFactory {
    
    @Override
    public GrantApplication createGrantApplication(String id, String applicantName, 
                                                 String projectTitle, double requestedAmount) {
        return new GrantApplication(id, applicantName, projectTitle, requestedAmount, GrantType.CULTURAL);
    }
    
    @Override
    protected GrantType getFactoryGrantType() {
        return GrantType.CULTURAL;
    }
    
    @Override
    protected double getMaxAmount() {
        return 40000.0; // Максимальна сума для культурного гранту
    }
    
    @Override
    protected boolean performSpecificValidation(String applicantName, String projectTitle, double requestedAmount) {
        // Специфічна валідація для культурних грантів
        return projectTitle.toLowerCase().contains("культур") || 
               projectTitle.toLowerCase().contains("мистецтво") ||
               projectTitle.toLowerCase().contains("театр") ||
               projectTitle.toLowerCase().contains("музика") ||
               projectTitle.toLowerCase().contains("проект");
    }
}
