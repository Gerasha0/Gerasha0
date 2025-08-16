package com.example1.strategy;

import com.example1.model.GrantApplication;

/**
 * Інтерфейс стратегії для оцінки заявок на грант
 */
public interface EvaluationStrategy {
    double evaluateApplication(GrantApplication application);
    String getEvaluationCriteria();
}
