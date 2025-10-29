package com.example1.factory;

import com.example1.model.GrantApplication;
import com.example1.model.GrantType;

/**
 * Конкретна фабрика для освітніх грантів
 */
public class EducationalGrantFactory extends GrantApplicationFactory {
    
    @Override
    public GrantApplication createGrantApplication(String id, String applicantName, 
                                                 String projectTitle, double requestedAmount) {
        return new GrantApplication(id, applicantName, projectTitle, requestedAmount, GrantType.EDUCATIONAL);
    }
    
    @Override
    protected GrantType getFactoryGrantType() {
        return GrantType.EDUCATIONAL;
    }
    
    @Override
    protected double getMaxAmount() {
        return 50000.0; // Максимальна сума для освітнього гранту
    }
    
    @Override
    protected boolean performSpecificValidation(String applicantName, String projectTitle, double requestedAmount) {
        // Специфічна валідація для освітніх грантів
        return projectTitle.toLowerCase().contains("освіта") || 
               projectTitle.toLowerCase().contains("навчання") ||
               projectTitle.toLowerCase().contains("курс") ||
               projectTitle.toLowerCase().contains("школа");
    }
    
    @Override
    public String getValidationRequirements() {
        return "Освітній грант:\n" +
               "  • Максимальна сума: 50,000 грн\n" +
               "  • Назва проекту має містити: 'освіта', 'навчання', 'курс' або 'школа'\n" +
               "  • Мінімальна довжина назви: 5 символів\n" +
               "  • Мінімальна довжина імені заявника: 2 символи";
    }
}
