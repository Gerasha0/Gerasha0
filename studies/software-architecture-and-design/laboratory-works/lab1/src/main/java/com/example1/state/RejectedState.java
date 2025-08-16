package com.example1.state;

import com.example1.model.GrantApplication;

/**
 * Стан "Відхилено"
 */
public class RejectedState implements GrantApplicationState {
    
    @Override
    public void submitForReview(GrantApplication application) {
        throw new IllegalStateException("Відхилена заявка не може бути подана на розгляд. Використайте resubmit().");
    }
    
    @Override
    public void approve(GrantApplication application) {
        throw new IllegalStateException("Неможливо схвалити відхилену заявку.");
    }
    
    @Override
    public void reject(GrantApplication application) {
        throw new IllegalStateException("Заявка вже відхилена.");
    }
    
    @Override
    public void defer(GrantApplication application) {
        throw new IllegalStateException("Неможливо відкласти відхилену заявку.");
    }
    
    @Override
    public void cancel(GrantApplication application) {
        System.out.println("Відхилена заявка " + application.getId() + " скасована заявником");
        application.setState(new CancelledState());
    }
    
    @Override
    public void resubmit(GrantApplication application) {
        System.out.println("Заявка " + application.getId() + " подана на повторний розгляд");
        application.setState(new UnderReviewState());
    }
    
    @Override
    public String getStateName() {
        return "REJECTED";
    }
    
    @Override
    public String getDescription() {
        return "Відхилено";
    }
}
