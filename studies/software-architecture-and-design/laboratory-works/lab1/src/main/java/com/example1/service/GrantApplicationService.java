package com.example1.service;

import com.example1.factory.*;
import com.example1.model.GrantApplication;
import com.example1.model.GrantType;
import com.example1.observer.ApplicantNotifier;
import com.example1.observer.StateLogger;
import com.example1.strategy.EvaluationStrategy;
import com.example1.strategy.CompositeEvaluation;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Сервіс для управління заявками на гранти
 */
public class GrantApplicationService {
    
    private final Map<String, GrantApplication> applications = new HashMap<>();
    private final Map<GrantType, GrantApplicationFactory> factories = new HashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);
    private EvaluationStrategy evaluationStrategy = new CompositeEvaluation();
    
    // Observer instances
    private final StateLogger stateLogger = new StateLogger();
    private final ApplicantNotifier applicantNotifier = new ApplicantNotifier();
    
    public GrantApplicationService() {
        initializeFactories();
    }
    
    private void initializeFactories() {
        factories.put(GrantType.RESEARCH, new ResearchGrantFactory());
        factories.put(GrantType.EDUCATIONAL, new EducationalGrantFactory());
        factories.put(GrantType.STARTUP, new StartupGrantFactory());
        factories.put(GrantType.SOCIAL, new SocialGrantFactory());
        factories.put(GrantType.CULTURAL, new CulturalGrantFactory());
    }
    
    public GrantApplication createApplication(String applicantName, String projectTitle, 
                                            double requestedAmount, GrantType grantType) {
        
        GrantApplicationFactory factory = factories.get(grantType);
        if (factory == null) {
            throw new IllegalArgumentException("Непідтримуваний тип гранту: " + grantType);
        }
        
        if (!factory.validateApplication(applicantName, projectTitle, requestedAmount)) {
            String errorMessage = "Заявка не пройшла валідацію!\n\n" + factory.getValidationRequirements();
            throw new IllegalArgumentException(errorMessage);
        }
        
        String id = "GRANT-" + String.format("%03d", idCounter.getAndIncrement());
        GrantApplication application = factory.createGrantApplication(id, applicantName, projectTitle, requestedAmount);
        
        // Додаємо спостерігачів
        application.addObserver(stateLogger);
        application.addObserver(applicantNotifier);
        
        applications.put(id, application);
        
        System.out.printf("Створено нову заявку: %s%n", application);
        return application;
    }
    
    public GrantApplication getApplication(String id) {
        return applications.get(id);
    }
    
    public List<GrantApplication> getAllApplications() {
        return new ArrayList<>(applications.values());
    }
    
    public List<GrantApplication> getApplicationsByState(String stateName) {
        return applications.values().stream()
                .filter(app -> app.getCurrentStateName().equals(stateName))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
    
    public double evaluateApplication(String applicationId) {
        GrantApplication application = applications.get(applicationId);
        if (application == null) {
            throw new IllegalArgumentException("Заявка з ID " + applicationId + " не знайдена");
        }
        
        double score = evaluationStrategy.evaluateApplication(application);
        System.out.printf("Оцінка заявки %s: %.2f/10%n", applicationId, score);
        System.out.printf("Критерії оцінки: %s%n", evaluationStrategy.getEvaluationCriteria());
        
        return score;
    }
    
    public void setEvaluationStrategy(EvaluationStrategy strategy) {
        this.evaluationStrategy = strategy;
    }
    
    // Методи для роботи з станами
    public void submitForReview(String applicationId) {
        GrantApplication app = getApplication(applicationId);
        if (app != null) {
            app.submitForReview();
        }
    }
    
    public void approveApplication(String applicationId) {
        GrantApplication app = getApplication(applicationId);
        if (app != null) {
            app.approve();
        }
    }
    
    public void rejectApplication(String applicationId, String reason) {
        GrantApplication app = getApplication(applicationId);
        if (app != null) {
            app.reject(reason);
        }
    }
    
    public void deferApplication(String applicationId) {
        GrantApplication app = getApplication(applicationId);
        if (app != null) {
            app.defer();
        }
    }
    
    public void cancelApplication(String applicationId) {
        GrantApplication app = getApplication(applicationId);
        if (app != null) {
            app.cancel();
        }
    }
    
    public void resubmitApplication(String applicationId) {
        GrantApplication app = getApplication(applicationId);
        if (app != null) {
            app.resubmit();
        }
    }
    
    public void printApplicationsSummary() {
        System.out.println("\n=== ЗАГАЛЬНИЙ СТАН ЗАЯВОК ===");
        Map<String, Long> stateCount = new HashMap<>();
        
        for (GrantApplication app : applications.values()) {
            String state = app.getCurrentStateName();
            stateCount.put(state, stateCount.getOrDefault(state, 0L) + 1);
        }
        
        stateCount.forEach((state, count) -> 
            System.out.printf("%s: %d заявок%n", state, count));
        System.out.println("===========================================\n");
    }
    
    /**
     * Показує вимоги валідації для конкретного типу гранту
     */
    public String getValidationRequirements(GrantType grantType) {
        GrantApplicationFactory factory = factories.get(grantType);
        if (factory == null) {
            return "Невідомий тип гранту";
        }
        return factory.getValidationRequirements();
    }
    
    /**
     * Показує вимоги валідації для всіх типів грантів
     */
    public void printAllValidationRequirements() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║           ВИМОГИ ДО ЗАЯВОК НА РІЗНІ ТИПИ ГРАНТІВ                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");
        
        for (GrantType type : GrantType.values()) {
            System.out.println(getValidationRequirements(type));
            System.out.println();
        }
        
        System.out.println("Загальні вимоги для всіх типів:");
        System.out.println("  • Ім'я заявника: мінімум 2 символи");
        System.out.println("  • Назва проекту: мінімум 5 символів");
        System.out.println("  • Сума має бути більше 0 і не перевищувати максимум для типу\n");
    }
}

