package org.atsd;

import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * Лабораторна робота 2.1 - Дослідження математичних алгоритмів
 * Варіант 7
 *
 * Завдання:
 * 1. Обчислення інтеграла ∫₀² x√(2x+1) dx методами трапецій, прямокутників та Сімпсона
 * 2. Знаходження коренів рівняння x⁵ + 5x + 1 = 0 методами половинчастого ділення, дотичних та хорд
 * 3. Розв'язання диференційного рівняння dy/dx = e^(-x) - 2y методом Ейлера
 */
public class MathematicalAlgorithms {

    private static final DecimalFormat DF = new DecimalFormat("#.##########");
    private static final double EPSILON = 1e-10;
    private static final int MAX_ITERATIONS = 1000;

    // =============== ЗАВДАННЯ 1: ОБЧИСЛЕННЯ ІНТЕГРАЛА ===============

    /**
     * Підінтегральна функція: f(x) = x * √(2x + 1)
     */
    public static double integralFunction(double x) {
        return x * Math.sqrt(2 * x + 1);
    }

    /**
     * Метод прямокутників (середніх ординат)
     * ∫ₐᵇ f(x)dx ≈ h * Σf(xᵢ + h/2), де h = (b-a)/n
     */
    public static double rectangleMethod(double a, double b, double h) {
        if (Math.abs(b - a) < EPSILON) {
            return 0.0; // Інтегрування на нульовому інтервалі
        }

        int n = (int) Math.round((b - a) / h);
        if (n <= 0) n = 1; // Мінімум один інтервал
        h = (b - a) / n; // Коригуємо крок для точного покриття інтервалу

        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            double x = a + i * h + h / 2.0; // Середина відрізка
            sum += integralFunction(x);
        }
        return h * sum;
    }

    /**
     * Метод трапецій
     * ∫ₐᵇ f(x)dx ≈ h/2 * [f(a) + 2*Σf(xᵢ) + f(b)]
     */
    public static double trapezoidMethod(double a, double b, double h) {
        if (Math.abs(b - a) < EPSILON) {
            return 0.0; // Інтегрування на нульовому інтервалі
        }

        int n = (int) Math.round((b - a) / h);
        if (n <= 0) n = 1; // Мінімум один інтервал
        h = (b - a) / n;

        double sum = integralFunction(a) + integralFunction(b);
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            sum += 2 * integralFunction(x);
        }
        return h * sum / 2.0;
    }

    /**
     * Метод Сімпсона (парабол)
     * ∫ₐᵇ f(x)dx ≈ h/3 * [f(a) + 4*Σf(x₂ᵢ₋₁) + 2*Σf(x₂ᵢ) + f(b)]
     */
    public static double simpsonMethod(double a, double b, double h) {
        if (Math.abs(b - a) < EPSILON) {
            return 0.0; // Інтегрування на нульовому інтервалі
        }

        int n = (int) Math.round((b - a) / h);
        if (n <= 0) n = 2; // Мінімум два інтервали для Сімпсона
        if (n % 2 == 1) n++; // Для методу Сімпсона потрібна парна кількість інтервалів
        h = (b - a) / n;

        double sum = integralFunction(a) + integralFunction(b);

        // Додаємо непарні точки з коефіцієнтом 4
        for (int i = 1; i < n; i += 2) {
            double x = a + i * h;
            sum += 4 * integralFunction(x);
        }

        // Додаємо парні точки з коефіцієнтом 2
        for (int i = 2; i < n; i += 2) {
            double x = a + i * h;
            sum += 2 * integralFunction(x);
        }

        return h * sum / 3.0;
    }

    // =============== ЗАВДАННЯ 2: КОРЕНІ РІВНЯННЯ ===============

    /**
     * Функція y(x) = x⁵ + 5x + 1
     */
    public static double equation(double x) {
        return Math.pow(x, 5) + 5 * x + 1;
    }

    /**
     * Похідна функції y'(x) = 5x⁴ + 5
     */
    public static double equationDerivative(double x) {
        return 5 * Math.pow(x, 4) + 5;
    }

    /**
     * Метод половинчастого ділення (бісекції)
     */
    public static double bisectionMethod(double a, double b) {
        if (equation(a) * equation(b) > 0) {
            throw new IllegalArgumentException("Функція не змінює знак на заданому інтервалі");
        }

        double c = 0;
        int iterations = 0;

        System.out.println("=== Метод половинчастого ділення ===");
        System.out.println("Ітерація\ta\t\tb\t\tc\t\tf(c)");

        while (Math.abs(b - a) > EPSILON && iterations < MAX_ITERATIONS) {
            c = (a + b) / 2.0;
            double fc = equation(c);

            System.out.printf("%d\t\t%.6f\t%.6f\t%.6f\t%.6f%n",
                iterations + 1, a, b, c, fc);

            if (Math.abs(fc) < EPSILON) {
                break;
            }

            if (equation(a) * fc < 0) {
                b = c;
            } else {
                a = c;
            }
            iterations++;
        }

        System.out.printf("Знайдено корінь: %.10f за %d ітерацій%n%n", c, iterations);
        return c;
    }

    /**
     * Метод дотичних (Ньютона)
     */
    public static double newtonMethod(double x0) {
        double x = x0;
        int iterations = 0;

        System.out.println("=== Метод дотичних (Ньютона) ===");
        System.out.println("Ітерація\tx\t\tf(x)\t\tf'(x)");

        while (iterations < MAX_ITERATIONS) {
            double fx = equation(x);
            double fpx = equationDerivative(x);

            System.out.printf("%d\t\t%.6f\t%.6f\t%.6f%n",
                iterations + 1, x, fx, fpx);

            if (Math.abs(fx) < EPSILON) {
                break;
            }

            if (Math.abs(fpx) < EPSILON) {
                throw new IllegalArgumentException("Похідна близька до нуля, метод не збігається");
            }

            double xNew = x - fx / fpx;

            if (Math.abs(xNew - x) < EPSILON) {
                x = xNew;
                break;
            }

            x = xNew;
            iterations++;
        }

        System.out.printf("Знайдено корінь: %.10f за %d ітерацій%n%n", x, iterations);
        return x;
    }

    /**
     * Метод хорд (секущих)
     */
    public static double chordMethod(double a, double b) {
        if (equation(a) * equation(b) > 0) {
            throw new IllegalArgumentException("Функція не змінює знак на заданому інтервалі");
        }

        double x = 0;
        int iterations = 0;

        System.out.println("=== Метод хорд ===");
        System.out.println("Ітерація\ta\t\tb\t\tx\t\tf(x)");

        while (iterations < MAX_ITERATIONS) {
            double fa = equation(a);
            double fb = equation(b);

            x = a - fa * (b - a) / (fb - fa);
            double fx = equation(x);

            System.out.printf("%d\t\t%.6f\t%.6f\t%.6f\t%.6f%n",
                iterations + 1, a, b, x, fx);

            if (Math.abs(fx) < EPSILON) {
                break;
            }

            if (fa * fx < 0) {
                b = x;
            } else {
                a = x;
            }

            if (Math.abs(b - a) < EPSILON) {
                break;
            }

            iterations++;
        }

        System.out.printf("Знайдено корінь: %.10f за %d ітерацій%n%n", x, iterations);
        return x;
    }

    // =============== ЗАВДАННЯ 3: ДИФЕРЕНЦІЙНЕ РІВНЯННЯ ===============

    /**
     * Права частина диференційного рівняння: dy/dx = e^(-x) - 2y
     */
    public static double differentialFunction(double x, double y) {
        return Math.exp(-x) - 2 * y;
    }

    /**
     * Метод Ейлера для розв'язання диференційного рівняння
     * y_{n+1} = y_n + h * f(x_n, y_n)
     */
    public static void eulerMethod(double x0, double y0, double xEnd, double h) {
        System.out.println("=== Розв'язання диференційного рівняння методом Ейлера ===");
        System.out.println("Диференційне рівняння: dy/dx = e^(-x) - 2y");
        System.out.printf("Початкові умови: x₀ = %.2f, y₀ = %.2f%n", x0, y0);
        System.out.printf("Крок інтегрування: h = %.4f%n%n", h);

        System.out.println("n\tx\t\ty\t\tdy/dx\t\ty_{n+1}");
        System.out.println("─".repeat(60));

        double x = x0;
        double y = y0;
        int n = 0;

        while (x <= xEnd) {
            double dydx = differentialFunction(x, y);
            double yNext = y + h * dydx;

            System.out.printf("%d\t%.4f\t\t%.8f\t%.8f\t%.8f%n",
                n, x, y, dydx, yNext);

            x += h;
            y = yNext;
            n++;

            if (x > xEnd) break;
        }

        System.out.println("─".repeat(60));
        System.out.printf("Кінцевий результат: y(%.2f) = %.8f%n%n", xEnd, y);
    }

    // =============== АНАЛІТИЧНЕ РОЗВ'ЯЗАННЯ ДЛЯ ПЕРЕВІРКИ ===============

    /**
     * Точне значення інтеграла ∫₀² x√(2x+1) dx для перевірки
     * Використовуючи інтегрування частинами та заміну u = 2x + 1
     * Правильний результат: (4/15)(5√5 - 1) ≈ 3.7928
     */
    public static double exactIntegralValue() {
        // Після інтегрування частинами: ∫x√(2x+1)dx = (1/15)(2x+1)^(3/2)(3x-1)
        // На межах [0,2]: [(1/15)(5^1.5)(6-1)] - [(1/15)(1^1.5)(0-1)]
        // = (1/15)[5*√5*5 - (-1)] = (1/15)(25√5 + 1)
        return (1.0/15.0) * (25.0 * Math.sqrt(5) + 1.0);
    }

    /**
     * Перевірка точності методів інтегрування
     */
    public static void compareIntegrationMethods(double a, double b, double h) {
        double exact = exactIntegralValue();
        double rectangle = rectangleMethod(a, b, h);
        double trapezoid = trapezoidMethod(a, b, h);
        double simpson = simpsonMethod(a, b, h);

        System.out.println("=== Порівняння методів інтегрування ===");
        System.out.printf("Точне значення:\t\t%.10f%n", exact);
        System.out.printf("Метод прямокутників:\t%.10f\t(похибка: %.2e)%n",
            rectangle, Math.abs(exact - rectangle));
        System.out.printf("Метод трапецій:\t\t%.10f\t(похибка: %.2e)%n",
            trapezoid, Math.abs(exact - trapezoid));
        System.out.printf("Метод Сімпсона:\t\t%.10f\t(похибка: %.2e)%n%n",
            simpson, Math.abs(exact - simpson));
    }
}
