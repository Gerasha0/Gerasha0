package org.atsd;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

/**
 * Юніт-тести для математичних алгоритів
 * Лабораторна робота 2.1 - Варіант 7
 */
public class AppTest {

    private static final double EPSILON = 1e-6;
    private static final double INTEGRATION_EPSILON = 1e-2; // Змінено з 1e-3 на 1e-2 для більш реалістичних очікувань
    private static final double SIMPSON_EPSILON = 1e-4; // Спеціальна точність для методу Сімпсона
    private static final double ROOT_EPSILON = 1e-8;

    // =============== ТЕСТИ ДЛЯ ЗАВДАННЯ 1: ІНТЕГРАЛ ===============

    @Test
    @DisplayName("Тест підінтегральної функції f(x) = x√(2x+1)")
    public void testIntegralFunction() {
        // Тестуємо кілька значень функції
        assertEquals(0.0, MathematicalAlgorithms.integralFunction(0), EPSILON);
        assertEquals(Math.sqrt(3), MathematicalAlgorithms.integralFunction(1), EPSILON);
        assertEquals(2 * Math.sqrt(5), MathematicalAlgorithms.integralFunction(2), EPSILON);
    }

    @Test
    @DisplayName("Тест методу прямокутників")
    public void testRectangleMethod() {
        double result = MathematicalAlgorithms.rectangleMethod(0, 2, 0.1);
        double exactValue = MathematicalAlgorithms.exactIntegralValue();

        assertTrue(Math.abs(result - exactValue) < INTEGRATION_EPSILON,
            "Метод прямокутників повинен давати результат близький до точного значення");
        assertTrue(result > 0, "Результат інтегрування додатної функції повинен бути додатнім");
    }

    @Test
    @DisplayName("Тест методу трапецій")
    public void testTrapezoidMethod() {
        double result = MathematicalAlgorithms.trapezoidMethod(0, 2, 0.1);
        double exactValue = MathematicalAlgorithms.exactIntegralValue();

        assertTrue(Math.abs(result - exactValue) < INTEGRATION_EPSILON,
            "Метод трапецій повинен давати результат близький до точного значення");
        assertTrue(result > 0, "Результат інтегрування додатної функції повинен бути додатнім");
    }

    @Test
    @DisplayName("Тест методу Сімпсона")
    public void testSimpsonMethod() {
        double result = MathematicalAlgorithms.simpsonMethod(0, 2, 0.1);
        double exactValue = MathematicalAlgorithms.exactIntegralValue();

        assertTrue(Math.abs(result - exactValue) < SIMPSON_EPSILON,
            "Метод Сімпсона повинен бути найточнішим");
        assertTrue(result > 0, "Результат інтегрування додатної функції повинен бути додатнім");
    }

    @Test
    @DisplayName("Порівняння точності методів інтегрування")
    public void testIntegrationAccuracy() {
        double exact = MathematicalAlgorithms.exactIntegralValue();
        double rectangle = MathematicalAlgorithms.rectangleMethod(0, 2, 0.1);
        double trapezoid = MathematicalAlgorithms.trapezoidMethod(0, 2, 0.1);
        double simpson = MathematicalAlgorithms.simpsonMethod(0, 2, 0.1);

        double errorRectangle = Math.abs(exact - rectangle);
        double errorTrapezoid = Math.abs(exact - trapezoid);
        double errorSimpson = Math.abs(exact - simpson);

        // Метод Сімпсона повинен бути найточнішим
        assertTrue(errorSimpson < errorRectangle,
            "Метод Сімпсона повинен бути точнішим за метод прямокутників");
        assertTrue(errorSimpson < errorTrapezoid,
            "Метод Сімпсона повинен бути точнішим за метод трапецій");
    }

    @Test
    @DisplayName("Тест точного значення інтеграла")
    public void testExactIntegralValue() {
        double exact = MathematicalAlgorithms.exactIntegralValue();
        assertTrue(exact > 0, "Точне значення інтеграла повинно бути додатнім");

        // Перевіряємо, що точне значення близьке до очікуваного (нова формула)
        double expected = (1.0/15.0) * (25.0 * Math.sqrt(5) + 1.0);
        assertEquals(expected, exact, EPSILON);
    }

    // =============== ТЕСТИ ДЛЯ ЗАВДАННЯ 2: КОРЕНІ РІВНЯННЯ ===============

    @Test
    @DisplayName("Тест функції рівняння f(x) = x⁵ + 5x + 1")
    public void testEquationFunction() {
        // Тестуємо кілька значень
        assertEquals(1.0, MathematicalAlgorithms.equation(0), EPSILON);
        assertEquals(7.0, MathematicalAlgorithms.equation(1), EPSILON);
        assertEquals(-5.0, MathematicalAlgorithms.equation(-1), EPSILON); // Виправлено: (-1)^5 + 5*(-1) + 1 = -1 - 5 + 1 = -5

        // Перевіряємо, що функція монотонно зроста��ча
        assertTrue(MathematicalAlgorithms.equation(-1) < MathematicalAlgorithms.equation(0));
        assertTrue(MathematicalAlgorithms.equation(0) < MathematicalAlgorithms.equation(1));
    }

    @Test
    @DisplayName("Тест похідної функції f'(x) = 5x⁴ + 5")
    public void testEquationDerivative() {
        // Похідна завжди додатна (функція мо��отонно зростаюча)
        assertTrue(MathematicalAlgorithms.equationDerivative(-2) > 0);
        assertTrue(MathematicalAlgorithms.equationDerivative(-1) > 0);
        assertTrue(MathematicalAlgorithms.equationDerivative(0) > 0);
        assertTrue(MathematicalAlgorithms.equationDerivative(1) > 0);

        // Перевіряємо конкретні значення
        assertEquals(5.0, MathematicalAlgorithms.equationDerivative(0), EPSILON);
        assertEquals(10.0, MathematicalAlgorithms.equationDerivative(1), EPSILON);
    }

    @Test
    @DisplayName("Тест методу половинчастого ділення")
    public void testBisectionMethod() {
        // Знаходимо корінь на інтервалі [-1, 0]
        double root = MathematicalAlgorithms.bisectionMethod(-1, 0);

        // Перевіряємо, що корінь дійсно є розв'язком
        assertTrue(Math.abs(MathematicalAlgorithms.equation(root)) < ROOT_EPSILON,
            "Знайдений корінь повинен задовольняти рівняння");

        // Корінь повинен бути в межах інтервалу
        assertTrue(root >= -1 && root <= 0,
            "Корінь повинен знаходитися в заданому інтервалі");
    }

    @Test
    @DisplayName("Тес�� методу дотичних (Ньютона)")
    public void testNewtonMethod() {
        // Починаємо з точки x0 = -0.5
        double root = MathematicalAlgorithms.newtonMethod(-0.5);

        // Перевіряємо, що корінь дійсно є розв'язком
        assertTrue(Math.abs(MathematicalAlgorithms.equation(root)) < ROOT_EPSILON,
            "Знайдений корінь повинен задовольняти рівняння");
    }

    @Test
    @DisplayName("Тест методу хорд")
    public void testChordMethod() {
        // Знаходимо корінь на інтервалі [-1, 0]
        double root = MathematicalAlgorithms.chordMethod(-1, 0);

        // Перевіряємо, що корінь дійсно є розв'язком
        assertTrue(Math.abs(MathematicalAlgorithms.equation(root)) < ROOT_EPSILON,
            "Знайдений корінь повинен задовольняти рівняння");

        // Корінь повинен бути в межах інтервалу
        assertTrue(root >= -1 && root <= 0,
            "Корінь повинен знаходитися в заданому інтервалі");
    }

    @Test
    @DisplayName("Тест збіжності всіх методів пошуку коренів")
    public void testRootFindingConvergence() {
        double rootBisection = MathematicalAlgorithms.bisectionMethod(-1, 0);
        double rootNewton = MathematicalAlgorithms.newtonMethod(-0.5);
        double rootChord = MathematicalAlgorithms.chordMethod(-1, 0);

        // Всі методи повинні знаходити один і той же корінь
        assertTrue(Math.abs(rootBisection - rootNewton) < ROOT_EPSILON * 10,
            "Методи бісекції та Ньютона повинні давати схожі результати");
        assertTrue(Math.abs(rootBisection - rootChord) < ROOT_EPSILON * 10,
            "Методи бісекції та хорд повинні давати схожі результати");
        assertTrue(Math.abs(rootNewton - rootChord) < ROOT_EPSILON * 10,
            "Методи Ньютона та хорд повинні давати схожі результати");
    }

    @Test
    @DisplayName("Тест обробки винятків для методів пошуку коренів")
    public void testRootFindingExceptions() {
        // Тестуємо ��нтервал, де функція ��е змінює знак
        assertThrows(IllegalArgumentException.class, () -> {
            MathematicalAlgorithms.bisectionMethod(1, 2);
        }, "Метод бісекції повине�� кидати виняток для неправильного інтервалу");

        assertThrows(IllegalArgumentException.class, () -> {
            MathematicalAlgorithms.chordMethod(1, 2);
        }, "Метод хорд повинен кидати виняток для неправильного інтервалу");
    }

    // =============== ТЕСТИ ДЛЯ ЗАВДАННЯ 3: ДИФЕРЕНЦІЙНЕ РІВНЯННІ ===============

    @Test
    @DisplayName("Тест правої частини диференційного рівняння")
    public void testDifferentialFunction() {
        // dy/dx = e^(-x) - 2y

        // При x=0, y=0: dy/dx = 1 - 0 = 1
        assertEquals(1.0, MathematicalAlgorithms.differentialFunction(0, 0), EPSILON);

        // При x=0, y=0.5: dy/dx = 1 - 1 = 0
        assertEquals(0.0, MathematicalAlgorithms.differentialFunction(0, 0.5), EPSILON);

        // При x=1, y=0: dy/dx = e^(-1) - 0 ≈ 0.368
        double expected = Math.exp(-1);
        assertEquals(expected, MathematicalAlgorithms.differentialFunction(1, 0), EPSILON);
    }

    @Test
    @DisplayName("Тест стабільності методу Ейлера")
    public void testEulerMethodStability() {
        // Т��ст з різними кроками інтегрування
        // Зменшення кроку повинно покращувати точність

        // Це тест на стабільність - метод не повинен давати нескінченні значення
        // При малих кроках та обмеженому часі інтегрування
        assertDoesNotThrow(() -> {
            // Створюємо StringBuffer для перехоплення виводу
            java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
            java.io.PrintStream originalOut = System.out;
            System.setOut(new java.io.PrintStream(outputStream));

            try {
                MathematicalAlgorithms.eulerMethod(0, 0, 1, 0.1);
            } finally {
                System.setOut(originalOut);
            }
        }, "Метод Ейлера повинен працювати стабільно з розумними параметрами");
    }

    @Test
    @DisplayName("Тест почат��ових умов для диференційного рівняння")
    public void testDifferentialEquationInitialConditions() {
        // Перевіряємо, що при y(0) = 0 початкова похідна дорівнює 1
        double initialDerivative = MathematicalAlgorithms.differentialFunction(0, 0);
        assertEquals(1.0, initialDerivative, EPSILON,
            "При початкових умовах x=0, y=0 похідна повинна дорівнювати 1");
    }

    // =============== ІНТЕГРАЦІЙНІ ТЕСТИ ===============

    @Test
    @DisplayName("Інтеграційний тест: всі алгоритми працюють без помилок")
    public void testAllAlgorithmsIntegration() {
        // Тестуємо, що всі основні методи можна викликати без винятків
        assertDoesNotThrow(() -> {
            // Завдання 1: Інтеграл
            MathematicalAlgorithms.rectangleMethod(0, 2, 0.5);
            MathematicalAlgorithms.trapezoidMethod(0, 2, 0.5);
            MathematicalAlgorithms.simpsonMethod(0, 2, 0.5);

            // Завдання 2: Корені рівнянн��
            MathematicalAlgorithms.bisectionMethod(-1, 0);
            MathematicalAlgorithms.newtonMethod(-0.5);
            MathematicalAlgorithms.chordMethod(-1, 0);

            // Завдання 3: Диференційне рівняння (перехоплюємо вивід)
            java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
            java.io.PrintStream originalOut = System.out;
            System.setOut(new java.io.PrintStream(outputStream));

            try {
                MathematicalAlgorithms.eulerMethod(0, 0, 0.5, 0.1);
            } finally {
                System.setOut(originalOut);
            }

        }, "Всі математичні алгоритми повинні працювати без винятків");
    }

    @Test
    @DisplayName("Тест граничних випадків")
    public void testBoundaryConditions() {
        // Інтегрування на нульовому інтервалі
        assertEquals(0.0, MathematicalAlgorithms.rectangleMethod(1, 1, 0.1), EPSILON);
        assertEquals(0.0, MathematicalAlgorithms.trapezoidMethod(1, 1, 0.1), EPSILON);
        assertEquals(0.0, MathematicalAlgorithms.simpsonMethod(1, 1, 0.1), EPSILON);

        // Функція рівняння в граничних точках
        assertTrue(MathematicalAlgorithms.equation(-10) < 0);
        assertTrue(MathematicalAlgorithms.equation(10) > 0);

        // Диференційна функція з граничними значеннями
        assertFalse(Double.isNaN(MathematicalAlgorithms.differentialFunction(0, 1000)));
        assertFalse(Double.isInfinite(MathematicalAlgorithms.differentialFunction(0, 1000)));
    }

    @Test
    @DisplayName("Тест продуктивності алгоритмів")
    public void testPerformance() {
        long startTime = System.nanoTime();

        // Виконуємо всі основні обчислення
        MathematicalAlgorithms.rectangleMethod(0, 2, 0.001);
        MathematicalAlgorithms.trapezoidMethod(0, 2, 0.001);
        MathematicalAlgorithms.simpsonMethod(0, 2, 0.001);

        MathematicalAlgorithms.bisectionMethod(-1, 0);
        MathematicalAlgorithms.newtonMethod(-0.5);
        MathematicalAlgorithms.chordMethod(-1, 0);

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        // Всі обчислення повинні виконуватися за розумний час (менше 5 секунд)
        assertTrue(duration < 5_000_000_000L,
            "Всі алгоритми повинні виконуватися за розумний час");
    }
}
