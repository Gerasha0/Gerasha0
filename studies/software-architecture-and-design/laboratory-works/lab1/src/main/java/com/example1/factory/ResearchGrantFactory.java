package com.example1.factory;

import com.example1.model.GrantApplication;
import com.example1.model.GrantType;

/**
 * Конкретна фабрика для наукових грантів
 */
public class ResearchGrantFactory extends GrantApplicationFactory {
    
    @Override
    public GrantApplication createGrantApplication(String id, String applicantName, 
                                                 String projectTitle, double requestedAmount) {
        return new GrantApplication(id, applicantName, projectTitle, requestedAmount, GrantType.RESEARCH);
    }
    
    @Override
    protected GrantType getFactoryGrantType() {
        return GrantType.RESEARCH;
    }
    
    @Override
    protected double getMaxAmount() {
        return 100000.0; // Максимальна сума для наукового гранту
    }
    
    @Override
    protected boolean performSpecificValidation(String applicantName, String projectTitle, double requestedAmount) {
        // Специфічна валідація для наукових грантів
        return projectTitle.toLowerCase().contains("дослідження") || 
               projectTitle.toLowerCase().contains("наука") ||
               projectTitle.toLowerCase().contains("експеримент");
    }
}
