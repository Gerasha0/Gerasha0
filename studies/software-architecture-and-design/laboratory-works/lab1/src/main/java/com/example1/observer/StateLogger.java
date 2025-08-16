package com.example1.observer;

import com.example1.model.GrantApplication;
import com.example1.state.GrantApplicationState;

/**
 * Конкретний спостерігач для логування змін станів
 */
public class StateLogger implements StateObserver {
    
    @Override
    public void onStateChanged(GrantApplication application, GrantApplicationState oldState, GrantApplicationState newState) {
        System.out.printf("[LOG] Заявка %s: %s -> %s (Заявник: %s, Проект: '%s')%n",
                application.getId(),
                oldState != null ? oldState.getDescription() : "початковий стан",
                newState.getDescription(),
                application.getApplicantName(),
                application.getProjectTitle());
    }
}
