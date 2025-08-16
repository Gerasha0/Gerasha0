package com.example1.observer;

import com.example1.model.GrantApplication;
import com.example1.state.GrantApplicationState;

/**
 * Інтерфейс спостерігача для Observer pattern
 */
public interface StateObserver {
    void onStateChanged(GrantApplication application, GrantApplicationState oldState, GrantApplicationState newState);
}
