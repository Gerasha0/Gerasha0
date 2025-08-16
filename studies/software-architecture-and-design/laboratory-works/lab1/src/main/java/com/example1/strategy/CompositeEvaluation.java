package com.example1.strategy;

import com.example1.model.GrantApplication;

/**
 * Комплексна стратегія оцінки
 */
public class CompositeEvaluation implements EvaluationStrategy {
    
    private final EvaluationStrategy amountStrategy = new AmountBasedEvaluation();
    private final EvaluationStrategy typeStrategy = new GrantTypeBasedEvaluation();
    
    @Override
    public double evaluateApplication(GrantApplication application) {
        double amountScore = amountStrategy.evaluateApplication(application);
        double typeScore = typeStrategy.evaluateApplication(application);
        
        // Комбінована оцінка з вагами
        return (amountScore * 0.4) + (typeScore * 0.6);
    }
    
    @Override
    public String getEvaluationCriteria() {
        return "Комплексна оцінка: 40% - сума, 60% - тип гранту";
    }
}
