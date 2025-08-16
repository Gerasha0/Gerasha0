package com.example1.factory;

import com.example1.model.GrantApplication;
import com.example1.model.GrantType;

/**
 * Конкретна фабрика для соціальних грантів
 */
public class SocialGrantFactory extends GrantApplicationFactory {
    
    @Override
    public GrantApplication createGrantApplication(String id, String applicantName, 
                                                 String projectTitle, double requestedAmount) {
        return new GrantApplication(id, applicantName, projectTitle, requestedAmount, GrantType.SOCIAL);
    }
    
    @Override
    protected GrantType getFactoryGrantType() {
        return GrantType.SOCIAL;
    }
    
    @Override
    protected double getMaxAmount() {
        return 75000.0; // Максимальна сума для соціального гранту
    }
    
    @Override
    protected boolean performSpecificValidation(String applicantName, String projectTitle, double requestedAmount) {
        // Специфічна валідація для соціальних грантів
        return projectTitle.toLowerCase().contains("соціальний") || 
               projectTitle.toLowerCase().contains("благодійність") ||
               projectTitle.toLowerCase().contains("допомога") ||
               projectTitle.toLowerCase().contains("спільнота");
    }
}
