package com.example1.strategy;

import com.example1.model.GrantApplication;

/**
 * Стратегія оцінки за типом гранту
 */
public class GrantTypeBasedEvaluation implements EvaluationStrategy {
    
    @Override
    public double evaluateApplication(GrantApplication application) {
        switch (application.getGrantType()) {
            case RESEARCH:
                return 8.5; // Високий пріоритет для наукових досліджень
            case EDUCATIONAL:
                return 8.0; // Високий пріоритет для освіти
            case SOCIAL:
                return 7.5; // Середній пріоритет для соціальних проектів
            case CULTURAL:
                return 7.0; // Середній пріоритет для культурних проектів
            case STARTUP:
                return 6.5; // Нижчий пріоритет для стартапів (більш ризиковані)
            default:
                return 5.0;
        }
    }
    
    @Override
    public String getEvaluationCriteria() {
        return "Оцінка на основі типу гранту: наука та освіта мають вищий пріоритет";
    }
}
