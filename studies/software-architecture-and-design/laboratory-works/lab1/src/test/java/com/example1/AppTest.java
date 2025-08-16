package com.example1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.example1.model.GrantApplication;
import com.example1.model.GrantType;
import com.example1.service.GrantApplicationService;
import com.example1.strategy.AmountBasedEvaluation;

/**
 * Тести для системи управління заявками на гранти
 */
public class AppTest {
    
    private GrantApplicationService grantService;
    
    @Before
    public void setUp() {
        grantService = new GrantApplicationService();
    }
    
    @Test
    public void testApplicationCreation() {
        GrantApplication app = grantService.createApplication(
            "Тест Тестенко", 
            "Тестове дослідження", 
            50000, 
            GrantType.RESEARCH
        );
        
        assertNotNull("Заявка має бути створена", app);
        assertEquals("Тест Тестенко", app.getApplicantName());
        assertEquals("Тестове дослідження", app.getProjectTitle());
        assertEquals(50000.0, app.getRequestedAmount(), 0.01);
        assertEquals(GrantType.RESEARCH, app.getGrantType());
        assertEquals("CREATED", app.getCurrentStateName());
    }
    
    @Test
    public void testStateTransitions() {
        GrantApplication app = grantService.createApplication(
            "Іван Іванов", 
            "Наукове дослідження", 
            75000, 
            GrantType.RESEARCH
        );
        
        // Перевірка початкового стану
        assertEquals("CREATED", app.getCurrentStateName());
        
        // Подання на розгляд
        grantService.submitForReview(app.getId());
        assertEquals("UNDER_REVIEW", app.getCurrentStateName());
        
        // Схвалення
        grantService.approveApplication(app.getId());
        assertEquals("APPROVED", app.getCurrentStateName());
    }
    
    @Test
    public void testInvalidStateTransition() {
        GrantApplication app = grantService.createApplication(
            "Петро Петров", 
            "Освітній курс навчання програмування", 
            25000, 
            GrantType.EDUCATIONAL
        );
        
        // Спроба схвалити заявку без подання на розгляд
        try {
            grantService.approveApplication(app.getId());
            fail("Має бути викинуто IllegalStateException");
        } catch (IllegalStateException e) {
            // Очікувана поведінка
            assertTrue(e.getMessage().contains("Неможливо схвалити заявку у стані 'Створено'"));
        }
    }
    
    @Test
    public void testApplicationEvaluation() {
        GrantApplication app = grantService.createApplication(
            "Марія Маріївна", 
            "Культурний мистецький проект", 
            15000, 
            GrantType.CULTURAL
        );
        
        grantService.setEvaluationStrategy(new AmountBasedEvaluation());
        double score = grantService.evaluateApplication(app.getId());
        
        assertTrue("Оцінка має бути позитивною", score > 0);
        assertTrue("Оцінка має бути не більше 10", score <= 10);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidApplicationData() {
        // Спроба створити заявку з некоректними даними
        grantService.createApplication("", "Короткий", -1000, GrantType.RESEARCH);
    }
    
    @Test
    public void testRejectionAndResubmission() {
        GrantApplication app = grantService.createApplication(
            "Олена Оленівна", 
            "Соціальний проект допомоги", 
            40000, 
            GrantType.SOCIAL
        );
        
        // Подаємо на розгляд та відхиляємо
        grantService.submitForReview(app.getId());
        grantService.rejectApplication(app.getId(), "Потребує доопрацювання");
        assertEquals("REJECTED", app.getCurrentStateName());
        assertEquals("Потребує доопрацювання", app.getRejectionReason());
        
        // Повторне подання
        grantService.resubmitApplication(app.getId());
        assertEquals("UNDER_REVIEW", app.getCurrentStateName());
    }
}
