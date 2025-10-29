package com.example1;

import com.example1.model.GrantApplication;
import com.example1.model.GrantType;
import com.example1.service.GrantApplicationService;
import com.example1.strategy.AmountBasedEvaluation;
import com.example1.strategy.GrantTypeBasedEvaluation;

import java.util.Scanner;

/**
 * Демонстрація системи управління заявками на гранти
 * 
 * Реалізовані шаблони проектування:
 * 1. State - для управління станами заявок
 * 2. Observer - для сповіщення про зміни станів
 * 3. Factory Method - для створення різних типів грантів 
 * 4. Strategy - для різних алгоритмів оцінки заявок
 */
public class App {
    
    private static final GrantApplicationService grantService = new GrantApplicationService();
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== СИСТЕМА УПРАВЛІННЯ ЗАЯВКАМИ НА ГРАНТИ ===");
        System.out.println("Реалізовані шаблони: State, Observer, Factory Method, Strategy\n");
        
        // Демонстрація роботи системи з заздалегідь підготовленими даними
        demonstrateWithPreparedData();
        
        // Інтерактивний режим
        interactiveMode();
        
        scanner.close();
    }
    
    private static void demonstrateWithPreparedData() {
        System.out.println("=== ДЕМОНСТРАЦІЯ З ПІДГОТОВЛЕНИМИ ДАНИМИ ===\n");
        
        try {
            // Створення різних типів заявок (Factory Method pattern)
            GrantApplication app1 = grantService.createApplication(
                "Іван Петренко", 
                "Дослідження штучного інтелекту", 
                75000, 
                GrantType.RESEARCH
            );
            
            GrantApplication app2 = grantService.createApplication(
                "Марія Коваленко", 
                "Онлайн курс з програмування", 
                30000, 
                GrantType.EDUCATIONAL
            );
            
            GrantApplication app3 = grantService.createApplication(
                "Олександр Шевченко", 
                "Інноваційний стартап у сфері ІТ", 
                150000, 
                GrantType.STARTUP
            );
            
            System.out.println("\n--- Оцінка заявок (Strategy pattern) ---");
            grantService.evaluateApplication(app1.getId());
            grantService.evaluateApplication(app2.getId());
            grantService.evaluateApplication(app3.getId());
            
            System.out.println("\n--- Зміна стратегії оцінки ---");
            grantService.setEvaluationStrategy(new AmountBasedEvaluation());
            grantService.evaluateApplication(app1.getId());
            
            System.out.println("\n--- Управління станами (State pattern) ---");
            
            // Переведення заявок через різні стани
            grantService.submitForReview(app1.getId());
            grantService.submitForReview(app2.getId());
            grantService.submitForReview(app3.getId());
            
            // Схвалення однієї заявки
            grantService.approveApplication(app1.getId());
            
            // Відхилення іншої
            grantService.rejectApplication(app2.getId(), "Недостатнє обґрунтування проекту");
            
            // Відкладення третьої
            grantService.deferApplication(app3.getId());
            
            // Повторне подання відхиленої заявки
            grantService.resubmitApplication(app2.getId());
            grantService.approveApplication(app2.getId());
            
            // Скасування відкладеної заявки
            grantService.cancelApplication(app3.getId());
            
            grantService.printApplicationsSummary();
            
        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }
    
    private static void interactiveMode() {
        System.out.println("=== ІНТЕРАКТИВНИЙ РЕЖИМ ===");
        
        while (true) {
            printMenu();
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                switch (choice) {
                    case 1:
                        createNewApplication();
                        break;
                    case 2:
                        listApplications();
                        break;
                    case 3:
                        manageApplicationState();
                        break;
                    case 4:
                        evaluateApplicationInteractive();
                        break;
                    case 5:
                        showSummary();
                        break;
                    case 6:
                        showValidationRequirements();
                        break;
                    case 0:
                        System.out.println("До побачення!");
                        return;
                    default:
                        System.out.println("Невiрний вибiр. Спробуйте ще раз.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Будь ласка, введiть числове значення.");
            } catch (Exception e) {
                System.err.println("Помилка: " + e.getMessage());
            }
            
            System.out.println("\nНатиснiть Enter для продовження...");
            scanner.nextLine();
        }
    }
    
    private static void printMenu() {
        System.out.println("\n+============================================================+");
        System.out.println("|                        ГОЛОВНЕ МЕНЮ                       |");
        System.out.println("+============================================================+");
        System.out.println("| 1. [+] Створити нову заявку                               |");
        System.out.println("| 2. [*] Переглянути всi заявки                             |");
        System.out.println("| 3. [~] Управлiння станом заявки                           |");
        System.out.println("| 4. [%] Оцiнити заявку                                     |");
        System.out.println("| 5. [=] Показати пiдсумок                                  |");
        System.out.println("| 6. [?] Вимоги до заявок                                   |");
        System.out.println("| 0. [X] Вихiд                                              |");
        System.out.println("+============================================================+");
        System.out.print("Ваш вибiр: ");
    }
    
    private static void createNewApplication() {
        while (true) {
            System.out.println("\n--- Створення нової заявки ---");
            System.out.println("0. Повернутися до головного меню");
            System.out.println("1. Продовжити створення заявки");
            System.out.print("Ваш вибiр: ");
            
            String choice = scanner.nextLine().trim();
            
            if ("0".equals(choice)) {
                return; // Повертаємося до головного меню
            } else if ("1".equals(choice)) {
                break; // Продовжуємо створення заявки
            } else {
                System.out.println("Невiрний вибiр. Спробуйте ще раз.");
                continue;
            }
        }
        
        System.out.print("Iм'я заявника (або 0 для повернення): ");
        String applicantName = scanner.nextLine().trim();
        if ("0".equals(applicantName)) return;
        
        System.out.print("Назва проекту (або 0 для повернення): ");
        String projectTitle = scanner.nextLine().trim();
        if ("0".equals(projectTitle)) return;
        
        System.out.print("Сума гранту (або 0 для повернення): ");
        String amountStr = scanner.nextLine().trim();
        if ("0".equals(amountStr)) return;
        
        double amount;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            System.out.println("Невірний формат суми!");
            return;
        }
        
        System.out.println("\nТипи грантiв:");
        GrantType[] types = GrantType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.printf("%d. %s%n", i + 1, types[i].getDescription());
        }
        System.out.println("0. Повернутися до головного меню");
        
        System.out.print("Оберiть тип гранту (0-" + types.length + "): ");
        String typeChoice = scanner.nextLine().trim();
        
        if ("0".equals(typeChoice)) return;
        
        int typeIndex;
        try {
            typeIndex = Integer.parseInt(typeChoice) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Невірний формат числа!");
            return;
        }
        
        if (typeIndex >= 0 && typeIndex < types.length) {
            try {
                GrantApplication app = grantService.createApplication(applicantName, projectTitle, amount, types[typeIndex]);
                System.out.println("+ Заявку створено успiшно! ID: " + app.getId());
            } catch (Exception e) {
                System.out.println("- Помилка створення заявки: " + e.getMessage());
            }
        } else {
            System.out.println("Невiрний вибiр типу гранту!");
        }
    }
    
    private static void listApplications() {
        System.out.println("\n--- Список всiх заявок ---");
        var applications = grantService.getAllApplications();
        
        if (applications.isEmpty()) {
            System.out.println("Заявок немає.");
        } else {
            System.out.println("Знайдено заявок: " + applications.size());
            System.out.println("---------------------------------------------------------------");
            for (GrantApplication app : applications) {
                System.out.printf("ID: %-10s | %-20s | %-30s | %8.2f грн | %s%n",
                    app.getId(),
                    app.getApplicantName(),
                    app.getProjectTitle().length() > 30 ? 
                        app.getProjectTitle().substring(0, 27) + "..." : 
                        app.getProjectTitle(),
                    app.getRequestedAmount(),
                    app.getCurrentStateName()
                );
            }
            System.out.println("---------------------------------------------------------------");
        }
        
        System.out.println("\nНатиснiть Enter для повернення до головного меню...");
        scanner.nextLine();
    }
    
    private static void manageApplicationState() {
        while (true) {
            System.out.println("\n--- Управлiння станом заявки ---");
            System.out.println("0. Повернутися до головного меню");
            System.out.print("Введiть ID заявки (або 0 для повернення): ");
            String id = scanner.nextLine().trim();
            
            if ("0".equals(id)) return;
            
            GrantApplication app = grantService.getApplication(id);
            if (app == null) {
                System.out.println("- Заявку не знайдено. Спробуйте ще раз.");
                continue;
            }
            
            System.out.printf("%n[*] Заявка: %s%n", app.toString());
            System.out.printf("[i] Поточний стан: %s%n", app.getCurrentStateName());
            
            while (true) {
                System.out.println("\nДоступні дії:");
                System.out.println("1. Подати на розгляд");
                System.out.println("2. Схвалити");
                System.out.println("3. Відхилити");
                System.out.println("4. Відкласти");
                System.out.println("5. Скасувати");
                System.out.println("6. Повторно подати");
                System.out.println("7. Вибрати іншу заявку");
                System.out.println("0. Повернутися до головного меню");
                
                System.out.print("Оберіть дію: ");
                String actionStr = scanner.nextLine().trim();
                
                if ("0".equals(actionStr)) return;
                if ("7".equals(actionStr)) break; // Перейти до вибору іншої заявки
                
                try {
                    int action = Integer.parseInt(actionStr);
                    handleStateAction(id, action);
                    break; // Після успішної дії переходимо до вибору нової заявки
                } catch (NumberFormatException e) {
                    System.out.println("Будь ласка, введіть числове значення.");
                } catch (IllegalStateException e) {
                    System.out.println("✗ " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("✗ Помилка: " + e.getMessage());
                }
            }
        }
    }
    
    private static void handleStateAction(String id, int action) {
        switch (action) {
            case 1:
                grantService.submitForReview(id);
                System.out.println("+ Заявку подано на розгляд");
                break;
            case 2:
                grantService.approveApplication(id);
                System.out.println("+ Заявку схвалено");
                break;
            case 3:
                System.out.print("Введiть причину вiдхилення: ");
                String reason = scanner.nextLine().trim();
                grantService.rejectApplication(id, reason);
                System.out.println("+ Заявку вiдхилено");
                break;
            case 4:
                grantService.deferApplication(id);
                System.out.println("+ Заявку вiдкладено");
                break;
            case 5:
                grantService.cancelApplication(id);
                System.out.println("+ Заявку скасовано");
                break;
            case 6:
                grantService.resubmitApplication(id);
                System.out.println("+ Заявку подано повторно");
                break;
            default:
                System.out.println("Невiрний вибiр дii.");
        }
    }
    
    private static void evaluateApplicationInteractive() {
        while (true) {
            System.out.println("\n--- Оцiнка заявки ---");
            System.out.println("0. Повернутися до головного меню");
            System.out.print("Введiть ID заявки (або 0 для повернення): ");
            String id = scanner.nextLine().trim();
            
            if ("0".equals(id)) return;
            
            GrantApplication app = grantService.getApplication(id);
            if (app == null) {
                System.out.println("- Заявку не знайдено. Спробуйте ще раз.");
                continue;
            }
            
            System.out.printf("%n[*] Заявка: %s%n", app.toString());
            
            while (true) {
                System.out.println("\nСтратегii оцiнки:");
                System.out.println("1. За сумою");
                System.out.println("2. За типом гранту");
                System.out.println("3. Комплексна оцiнка");
                System.out.println("4. Вибрати iншу заявку");
                System.out.println("0. Повернутися до головного меню");
                
                System.out.print("Оберiть стратегiю: ");
                String strategyStr = scanner.nextLine().trim();
                
                if ("0".equals(strategyStr)) return;
                if ("4".equals(strategyStr)) break;
                
                try {
                    int strategy = Integer.parseInt(strategyStr);
                    
                    switch (strategy) {
                        case 1:
                            grantService.setEvaluationStrategy(new AmountBasedEvaluation());
                            break;
                        case 2:
                            grantService.setEvaluationStrategy(new GrantTypeBasedEvaluation());
                            break;
                        case 3:
                            // Комплексна стратегія вже встановлена за замовчуванням
                            break;
                        default:
                            System.out.println("Невiрна стратегiя.");
                            continue;
                    }
                    
                    System.out.println("\n[%] Результат оцiнки:");
                    grantService.evaluateApplication(id);
                    System.out.println("\nНатиснiть Enter для продовження...");
                    scanner.nextLine();
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Будь ласка, введiть числове значення.");
                } catch (Exception e) {
                    System.out.println("- Помилка: " + e.getMessage());
                }
            }
        }
    }
    
    private static void showSummary() {
        System.out.println("\n[=] Показ пiдсумку системи");
        grantService.printApplicationsSummary();
        System.out.println("\nНатиснiть Enter для повернення до головного меню...");
        scanner.nextLine();
    }
    
    private static void showValidationRequirements() {
        grantService.printAllValidationRequirements();
    }
}
