package com.example1.state;

import com.example1.model.GrantApplication;

/**
 * Стан "Відкладено"
 */
public class DeferredState implements GrantApplicationState {
    
    @Override
    public void submitForReview(GrantApplication application) {
        System.out.println("Відкладена заявка " + application.getId() + " повернута на розгляд");
        application.setState(new UnderReviewState());
    }
    
    @Override
    public void approve(GrantApplication application) {
        System.out.println("Відкладена заявка " + application.getId() + " схвалена");
        application.setState(new ApprovedState());
    }
    
    @Override
    public void reject(GrantApplication application) {
        System.out.println("Відкладена заявка " + application.getId() + " відхилена. Причина: " + 
                          (application.getRejectionReason() != null ? application.getRejectionReason() : "Не вказана"));
        application.setState(new RejectedState());
    }
    
    @Override
    public void defer(GrantApplication application) {
        throw new IllegalStateException("Заявка вже відкладена.");
    }
    
    @Override
    public void cancel(GrantApplication application) {
        System.out.println("Відкладена заявка " + application.getId() + " скасована заявником");
        application.setState(new CancelledState());
    }
    
    @Override
    public void resubmit(GrantApplication application) {
        System.out.println("Відкладена заявка " + application.getId() + " подана на повторний розгляд");
        application.setState(new UnderReviewState());
    }
    
    @Override
    public String getStateName() {
        return "DEFERRED";
    }
    
    @Override
    public String getDescription() {
        return "Відкладено";
    }
}
