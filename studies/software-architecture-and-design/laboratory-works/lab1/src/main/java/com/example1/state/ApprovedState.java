package com.example1.state;

import com.example1.model.GrantApplication;

/**
 * Стан "Схвалено"
 */
public class ApprovedState implements GrantApplicationState {
    
    @Override
    public void submitForReview(GrantApplication application) {
        throw new IllegalStateException("Схвалена заявка не може бути подана на повторний розгляд.");
    }
    
    @Override
    public void approve(GrantApplication application) {
        throw new IllegalStateException("Заявка вже схвалена.");
    }
    
    @Override
    public void reject(GrantApplication application) {
        throw new IllegalStateException("Неможливо відхилити схвалену заявку.");
    }
    
    @Override
    public void defer(GrantApplication application) {
        throw new IllegalStateException("Неможливо відкласти схвалену заявку.");
    }
    
    @Override
    public void cancel(GrantApplication application) {
        System.out.println("Схвалена заявка " + application.getId() + " скасована");
        application.setState(new CancelledState());
    }
    
    @Override
    public void resubmit(GrantApplication application) {
        throw new IllegalStateException("Схвалена заявка не потребує повторного подання.");
    }
    
    @Override
    public String getStateName() {
        return "APPROVED";
    }
    
    @Override
    public String getDescription() {
        return "Схвалено";
    }
}
