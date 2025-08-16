package com.example1.state;

import com.example1.model.GrantApplication;

/**
 * Стан "На розгляді"
 */
public class UnderReviewState implements GrantApplicationState {
    
    @Override
    public void submitForReview(GrantApplication application) {
        throw new IllegalStateException("Заявка вже знаходиться на розгляді.");
    }
    
    @Override
    public void approve(GrantApplication application) {
        System.out.println("Заявка " + application.getId() + " схвалена");
        application.setState(new ApprovedState());
    }
    
    @Override
    public void reject(GrantApplication application) {
        System.out.println("Заявка " + application.getId() + " відхилена. Причина: " + 
                          (application.getRejectionReason() != null ? application.getRejectionReason() : "Не вказана"));
        application.setState(new RejectedState());
    }
    
    @Override
    public void defer(GrantApplication application) {
        System.out.println("Заявка " + application.getId() + " відкладена для додаткового розгляду");
        application.setState(new DeferredState());
    }
    
    @Override
    public void cancel(GrantApplication application) {
        System.out.println("Заявка " + application.getId() + " скасована заявником під час розгляду");
        application.setState(new CancelledState());
    }
    
    @Override
    public void resubmit(GrantApplication application) {
        throw new IllegalStateException("Неможливо перевести заявку на повторний розгляд - вона вже розглядається.");
    }
    
    @Override
    public String getStateName() {
        return "UNDER_REVIEW";
    }
    
    @Override
    public String getDescription() {
        return "На розгляді";
    }
}
