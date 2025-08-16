package com.example1.strategy;

import com.example1.model.GrantApplication;

/**
 * Стратегія оцінки за сумою запитуваного гранту
 */
public class AmountBasedEvaluation implements EvaluationStrategy {
    
    @Override
    public double evaluateApplication(GrantApplication application) {
        double amount = application.getRequestedAmount();
        
        // Чим менша сума, тим вищий бал (більше шансів на схвалення)
        if (amount <= 10000) {
            return 9.0;
        } else if (amount <= 25000) {
            return 7.5;
        } else if (amount <= 50000) {
            return 6.0;
        } else if (amount <= 100000) {
            return 4.5;
        } else {
            return 3.0;
        }
    }
    
    @Override
    public String getEvaluationCriteria() {
        return "Оцінка на основі запитуваної суми: менша сума = вищий бал";
    }
}
