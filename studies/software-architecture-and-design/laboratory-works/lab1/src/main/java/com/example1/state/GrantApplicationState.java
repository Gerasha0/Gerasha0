package com.example1.state;

import com.example1.model.GrantApplication;

/**
 * Інтерфейс стану для шаблону State
 */
public interface GrantApplicationState {
    void submitForReview(GrantApplication application);
    void approve(GrantApplication application);
    void reject(GrantApplication application);
    void defer(GrantApplication application);
    void cancel(GrantApplication application);
    void resubmit(GrantApplication application);
    String getStateName();
    String getDescription();
}
