package com.example1.model;

import com.example1.state.GrantApplicationState;
import com.example1.state.CreatedState;
import com.example1.observer.StateObserver;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 * Контекст для шаблону State - заявка на грант
 */
public class GrantApplication {
    private String id;
    private String applicantName;
    private String projectTitle;
    private double requestedAmount;
    private GrantType grantType;
    private GrantApplicationState state;
    private List<StateObserver> observers;
    private LocalDateTime createdAt;
    private String rejectionReason;
    private String approvalComments;

    public GrantApplication(String id, String applicantName, String projectTitle, 
                           double requestedAmount, GrantType grantType) {
        this.id = id;
        this.applicantName = applicantName;
        this.projectTitle = projectTitle;
        this.requestedAmount = requestedAmount;
        this.grantType = grantType;
        this.observers = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.state = new CreatedState();
    }

    // Методи для зміни станів (делеговані до поточного стану)
    public void submitForReview() {
        state.submitForReview(this);
    }

    public void approve() {
        state.approve(this);
    }

    public void reject(String reason) {
        this.rejectionReason = reason;
        state.reject(this);
    }

    public void defer() {
        state.defer(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    public void resubmit() {
        state.resubmit(this);
    }

    // Зміна стану з сповіщенням спостерігачів
    public void setState(GrantApplicationState newState) {
        GrantApplicationState oldState = this.state;
        this.state = newState;
        notifyObservers(oldState, newState);
    }

    // Observer pattern methods
    public void addObserver(StateObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(StateObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(GrantApplicationState oldState, GrantApplicationState newState) {
        for (StateObserver observer : observers) {
            observer.onStateChanged(this, oldState, newState);
        }
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getApplicantName() { return applicantName; }
    public String getProjectTitle() { return projectTitle; }
    public double getRequestedAmount() { return requestedAmount; }
    public GrantType getGrantType() { return grantType; }
    public GrantApplicationState getState() { return state; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getRejectionReason() { return rejectionReason; }
    public String getApprovalComments() { return approvalComments; }
    
    public void setApprovalComments(String approvalComments) {
        this.approvalComments = approvalComments;
    }

    public String getCurrentStateName() {
        return state.getStateName();
    }

    @Override
    public String toString() {
        return String.format("GrantApplication[id=%s, applicant=%s, project=%s, amount=%.2f, type=%s, state=%s]",
                id, applicantName, projectTitle, requestedAmount, grantType, getCurrentStateName());
    }
}
