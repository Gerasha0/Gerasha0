package com.example1.state;

import com.example1.model.GrantApplication;

/**
 * Стан "Скасовано"
 */
public class CancelledState implements GrantApplicationState {
    
    @Override
    public void submitForReview(GrantApplication application) {
        throw new IllegalStateException("Скасована заявка не може бути подана на розгляд.");
    }
    
    @Override
    public void approve(GrantApplication application) {
        throw new IllegalStateException("Неможливо схвалити скасовану заявку.");
    }
    
    @Override
    public void reject(GrantApplication application) {
        throw new IllegalStateException("Неможливо відхилити скасовану заявку.");
    }
    
    @Override
    public void defer(GrantApplication application) {
        throw new IllegalStateException("Неможливо відкласти скасовану заявку.");
    }
    
    @Override
    public void cancel(GrantApplication application) {
        throw new IllegalStateException("Заявка вже скасована.");
    }
    
    @Override
    public void resubmit(GrantApplication application) {
        throw new IllegalStateException("Скасована заявка не може бути подана повторно.");
    }
    
    @Override
    public String getStateName() {
        return "CANCELLED";
    }
    
    @Override
    public String getDescription() {
        return "Скасовано";
    }
}
