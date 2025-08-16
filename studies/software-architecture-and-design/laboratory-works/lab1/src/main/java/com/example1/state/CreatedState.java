package com.example1.state;

import com.example1.model.GrantApplication;

/**
 * Стан "Створено" - початковий стан заявки
 */
public class CreatedState implements GrantApplicationState {
    
    @Override
    public void submitForReview(GrantApplication application) {
        System.out.println("Заявка " + application.getId() + " подана на розгляд");
        application.setState(new UnderReviewState());
    }
    
    @Override
    public void approve(GrantApplication application) {
        throw new IllegalStateException("Неможливо схвалити заявку у стані 'Створено'. Спочатку подайте на розгляд.");
    }
    
    @Override
    public void reject(GrantApplication application) {
        throw new IllegalStateException("Неможливо відхилити заявку у стані 'Створено'. Спочатку подайте на розгляд.");
    }
    
    @Override
    public void defer(GrantApplication application) {
        throw new IllegalStateException("Неможливо відкласти заявку у стані 'Створено'. Спочатку подайте на розгляд.");
    }
    
    @Override
    public void cancel(GrantApplication application) {
        System.out.println("Заявка " + application.getId() + " скасована заявником");
        application.setState(new CancelledState());
    }
    
    @Override
    public void resubmit(GrantApplication application) {
        throw new IllegalStateException("Неможливо перевести заявку у стані 'Створено' на повторний розгляд.");
    }
    
    @Override
    public String getStateName() {
        return "CREATED";
    }
    
    @Override
    public String getDescription() {
        return "Створено";
    }
}
