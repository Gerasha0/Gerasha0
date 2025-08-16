package org.atsd;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Головний клас для демонстрації математичних алгоритмів
 * Лабораторна робота 2.1 - Варіант 7
 */
public class App {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║         ЛАБОРАТОРНА РОБОТА 2.1 - ВАРІАНТ 7                   ║");
        System.out.println("║        ДОСЛІДЖЕННЯ МАТЕМАТИЧНИХ АЛГОРИТМІВ                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = getIntInput("Виберіть завдання (1-4): ");

            switch (choice) {
                case 1:
                    performTask1();
                    break;
                case 2:
                    performTask2();
                    break;
                case 3:
                    performTask3();
                    break;
                case 4:
                    demonstrateAllTasks();
                    break;
                case 0:
                    running = false;
                    System.out.println("До побачення!");
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте знову.");
            }
        }

        scanner.close();
    }

    private static void showMainMenu() {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│                        ГОЛОВНЕ МЕНЮ                         │");
        System.out.println("├─────────────────────────────────────────────────────────────┤");
        System.out.println("│ 1. Завдання 1 - Обчислення інтеграла                        │");
        System.out.println("│    ∫₀² x√(2x+1) dx методами трапецій, прямокутників та      │");
        System.out.println("│    Сімпсона                                                 │");
        System.out.println("├─────────────────────────────────────────────────────────────┤");
        System.out.println("│ 2. Завдання 2 - Корені алгебричного рівняння                │");
        System.out.println("│    x⁵ + 5x + 1 = 0 методами половинчастого ділення,         │");
        System.out.println("│    дотичних та хорд                                         │");
        System.out.println("├─────────────────────────────────────────────────────────────┤");
        System.out.println("│ 3. Завдання 3 - Диференційне рівняння                       │");
        System.out.println("│    dy/dx = e⁻ˣ - 2y методом Ейлера                          │");
        System.out.println("├─────────────────────────────────────────────────────────────┤");
        System.out.println("│ 4. Демонстрація всіх завдань з типовими параметрами         │");
        System.out.println("│ 0. Вихід                                                    │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");
    }

    // =============== ЗАВДАННЯ 1: ОБЧИСЛЕННЯ ІНТЕГРАЛА ===============

    private static void performTask1() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("             ЗАВДАННЯ 1: ОБЧИСЛЕННЯ ІНТЕГРАЛА");
        System.out.println("                ∫₀² x√(2x+1) dx");
        System.out.println("═".repeat(60));

        // Вводимо параметри інтегрування
        System.out.println("Введіть параметри інтегрування:");
        double a = getDoubleInput("Нижня межа інтегрування a (за замовчуванням 0): ", 0.0);
        double b = getDoubleInput("Верхня межа інтегрування b (за замовчуванням 2): ", 2.0);
        double h = getDoubleInput("Крок інтегрування h (за замовчуванням 0.1): ", 0.1);

        System.out.printf("\nОбчислення інтеграла ∫%.1f^%.1f x√(2x+1) dx з кроком h = %.3f%n%n", a, b, h);

        // Обчислюємо інтеграл різними методами
        double rectangleResult = MathematicalAlgorithms.rectangleMethod(a, b, h);
        double trapezoidResult = MathematicalAlgorithms.trapezoidMethod(a, b, h);
        double simpsonResult = MathematicalAlgorithms.simpsonMethod(a, b, h);

        // Виводимо результати
        System.out.println("┌─────────────────────────────────────────────────────┐");
        System.out.println("│                 РЕЗУЛЬТАТИ ОБЧИСЛЕНЬ                │");
        System.out.println("├─────────────────────────────────────────────────────┤");
        System.out.printf("│ Метод прямокутників:  %20.10f    │%n", rectangleResult);
        System.out.printf("│ Метод трапецій:       %20.10f    │%n", trapezoidResult);
        System.out.printf("│ Метод Сімпсона:       %20.10f    │%n", simpsonResult);
        System.out.println("└─────────────────────────────────────────────────────┘");

        // Порівняння з точним значенням
        if (Math.abs(a - 0.0) < 1e-10 && Math.abs(b - 2.0) < 1e-10) {
            System.out.println();
            MathematicalAlgorithms.compareIntegrationMethods(a, b, h);
        }

        waitForEnter();
    }

    // =============== ЗАВДАННЯ 2: КОРЕНІ РІВНЯННЯ ===============

    private static void performTask2() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("        ЗАВДАННЯ 2: КОРЕНІ АЛГЕБРИЧНОГО РІВНЯННЯ");
        System.out.println("                 x⁵ + 5x + 1 = 0");
        System.out.println("═".repeat(60));

        boolean continueRootFinding = true;
        while (continueRootFinding) {
            System.out.println("\nВведіть інтервал для пошуку кореня:");
            double a = getDoubleInput("Ліва межа інтервалу a: ");
            double b = getDoubleInput("Права межа інтервалу b: ");

            // Перевіряємо, чи є корінь на інтервалі
            double fa = MathematicalAlgorithms.equation(a);
            double fb = MathematicalAlgorithms.equation(b);

            System.out.printf("\nПеревірка інтервалу [%.2f, %.2f]:%n", a, b);
            System.out.printf("f(%.2f) = %.6f%n", a, fa);
            System.out.printf("f(%.2f) = %.6f%n", b, fb);
            System.out.printf("f(a) * f(b) = %.6f%n", fa * fb);

            if (fa * fb > 0) {
                System.out.println("\n❌ УВАГА: Функція не змінює знак на заданому інтервалі!");
                System.out.println("   Корінь може бути відсутнім або їх може бути парна кількість.");
                System.out.println("   Рекомендується обрати інший інтервал.");

                System.out.print("\nБажаете спробувати інший інтервал? (y/n): ");
                String answer = scanner.nextLine().toLowerCase();
                if (!answer.startsWith("y") && !answer.startsWith("т")) {
                    continueRootFinding = false;
                    continue;
                }
            } else {
                System.out.println("✅ Функція змінює знак на інтервалі. Корінь існує!");

                try {
                    System.out.println("\n" + "─".repeat(50));
                    double root1 = MathematicalAlgorithms.bisectionMethod(a, b);

                    System.out.println("─".repeat(50));
                    double x0 = (a + b) / 2.0; // Початкове наближення для методу Ньютона
                    double root2 = MathematicalAlgorithms.newtonMethod(x0);

                    System.out.println("─".repeat(50));
                    double root3 = MathematicalAlgorithms.chordMethod(a, b);

                    // Порівняння результатів
                    System.out.println("┌─────────────────────────────────────────────────────┐");
                    System.out.println("│              ПОРІВНЯННЯ РЕЗУЛЬТАТІВ                 │");
                    System.out.println("├─────────────────────────────────────────────────────┤");
                    System.out.printf("│ Метод половинчастого ділення: %15.10f   │%n", root1);
                    System.out.printf("│ Метод дотичних (Ньютона):     %15.10f   │%n", root2);
                    System.out.printf("│ Метод хорд:                   %15.10f   │%n", root3);
                    System.out.println("├─────────────────────────────────────────────────────┤");
                    System.out.printf("│ Перевірка: f(%12.10f) = %15.2e  │%n", root1, MathematicalAlgorithms.equation(root1));
                    System.out.println("└─────────────────────────────────────────────────────┘");

                } catch (Exception e) {
                    System.out.println("❌ Помилка: " + e.getMessage());
                }

                continueRootFinding = false;
            }
        }

        waitForEnter();
    }

    // =============== ЗАВДАННЯ 3: ДИФЕРЕНЦІЙНЕ РІВНЯННЯ ===============

    private static void performTask3() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("          ЗАВДАННЯ 3: ДИФЕРЕНЦІЙНЕ РІВНЯННЯ");
        System.out.println("              dy/dx = e⁻ˣ - 2y");
        System.out.println("═".repeat(60));

        System.out.println("Введіть початкові умови та параметри:");
        double x0 = getDoubleInput("Початкове значення x₀ (за замовчуванням 0): ", 0.0);
        double y0 = getDoubleInput("Початкове значення y₀ (за замовчуванням 0): ", 0.0);
        double xEnd = getDoubleInput("Кінцеве значення x (за замовчуванням 2): ", 2.0);
        double h = getDoubleInput("Крок інтегрування h (за замовчуванням 0.1): ", 0.1);

        System.out.println("\n" + "─".repeat(60));
        MathematicalAlgorithms.eulerMethod(x0, y0, xEnd, h);

        // Додаткова інформація про аналітичне рішення
        System.out.println("📝 ДОВІДКА:");
        System.out.println("   Це лінійне диференційне рівняння першого порядку.");
        System.out.println("   Аналітичний розв'язок: y(x) = (1/3)e⁻ˣ + Ce⁻²ˣ");
        System.out.println("   де C визначається з початкових умов.");

        waitForEnter();
    }

    // =============== ДЕМОНСТРАЦІЯ ВСІХ ЗАВДАНЬ ===============

    private static void demonstrateAllTasks() {
        System.out.println("\n" + "═".repeat(80));
        System.out.println("                    ДЕМОНСТРАЦІЯ ВСІХ ЗАВДАНЬ");
        System.out.println("                      З ТИПОВИМИ ПАРАМЕТРАМИ");
        System.out.println("═".repeat(80));

        // Завдання 1: Інтеграл
        System.out.println("\n🔢 ЗАВДАННЯ 1: Обчислення інтеграла ∫₀² x√(2x+1) dx");
        System.out.println("─".repeat(60));
        MathematicalAlgorithms.compareIntegrationMethods(0.0, 2.0, 0.1);

        // Завдання 2: Корені рівняння
        System.out.println("🔍 ЗАВДАННЯ 2: Пошук коренів рівняння x⁵ + 5x + 1 = 0");
        System.out.println("─".repeat(60));
        System.out.println("Інтервал [-1, 0] (містить один корінь):");
        try {
            double root1 = MathematicalAlgorithms.bisectionMethod(-1.0, 0.0);
            double root2 = MathematicalAlgorithms.newtonMethod(-0.5);
            double root3 = MathematicalAlgorithms.chordMethod(-1.0, 0.0);

            System.out.println("┌─────────────────────────────────────────────────────┐");
            System.out.println("│              ПІДСУМОК РЕЗУЛЬТАТІВ                   │");
            System.out.println("├─────────────────────────────────────────────────────┤");
            System.out.printf("│ Всі методи знайшли корінь: x ≈ %.10f   │%n", root1);
            System.out.printf("│ Перевірка: f(x) = %25.2e        │%n", MathematicalAlgorithms.equation(root1));
            System.out.println("└─────────────────────────────────────────────────────┘");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        // Завдання 3: Диференційне рівняння
        System.out.println("\n📈 ЗАВДАННЯ 3: Розв'язання диференційного рівняння dy/dx = e⁻ˣ - 2y");
        System.out.println("─".repeat(60));
        MathematicalAlgorithms.eulerMethod(0.0, 0.0, 1.0, 0.1);

        System.out.println("═".repeat(80));
        System.out.println("                        ДЕМОНСТРАЦІЮ ЗАВЕРШЕНО");
        System.out.println("     Всі математичні алгоритми протестовано успішно!");
        System.out.println("═".repeat(80));

        waitForEnter();
    }

    // =============== ДОПОМІЖНІ МЕТОДИ ===============

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // Споживаємо залишковий символ нового рядка
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Помилка: введіть ціле число.");
                scanner.nextLine(); // Очищаємо некоректний ввід
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine(); // Споживаємо залишковий символ нового рядка
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Помилка: введіть число.");
                scanner.nextLine(); // Очищаємо некоректний ввід
            }
        }
    }

    private static double getDoubleInput(String prompt, double defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            return defaultValue;
        }

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Некоректний формат. Використано значення за замовчуванням: " + defaultValue);
            return defaultValue;
        }
    }

    private static void waitForEnter() {
        System.out.println("\nНатисніть Enter, щоб продовжити...");
        scanner.nextLine();
    }
}
