package com.example1.observer;

import com.example1.model.GrantApplication;
import com.example1.state.GrantApplicationState;

/**
 * Спостерігач для сповіщення заявників про зміни статусу
 */
public class ApplicantNotifier implements StateObserver {
    
    @Override
    public void onStateChanged(GrantApplication application, GrantApplicationState oldState, GrantApplicationState newState) {
        String message = generateNotificationMessage(application, newState);
        sendNotification(application.getApplicantName(), message);
    }
    
    private String generateNotificationMessage(GrantApplication application, GrantApplicationState newState) {
        switch (newState.getStateName()) {
            case "UNDER_REVIEW":
                return "Ваша заявка на грант подана на розгляд і буде оброблена найближчим часом.";
            case "APPROVED":
                return "Вітаємо! Ваша заявка на грант схвалена. Очікуйте подальших інструкцій.";
            case "REJECTED":
                return "На жаль, ваша заявка на грант відхилена. Причина: " + 
                       (application.getRejectionReason() != null ? application.getRejectionReason() : "Не вказана");
            case "DEFERRED":
                return "Ваша заявка на грант відкладена для додаткового розгляду.";
            case "CANCELLED":
                return "Ваша заявка на грант скасована.";
            default:
                return "Статус вашої заявки змінився на: " + newState.getDescription();
        }
    }
    
    private void sendNotification(String applicantName, String message) {
        System.out.printf("[NOTIFICATION] Для %s: %s%n", applicantName, message);
    }
}
