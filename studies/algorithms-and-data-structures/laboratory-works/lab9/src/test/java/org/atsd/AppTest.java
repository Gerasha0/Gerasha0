package org.atsd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Тести для класу App - комбінаторні алгоритми
 */
public class AppTest {

    @Test
    public void testCalculateArrangements() {
        // Тест розміщень без повторень A(n,k) = n!/(n-k)!

        // A(11,4) для спортивного гуртка
        assertEquals(7920, App.calculateArrangements(11, 4));

        // A(5,3) = 5*4*3 = 60
        assertEquals(60, App.calculateArrangements(5, 3));

        // A(4,4) = 4! = 24
        assertEquals(24, App.calculateArrangements(4, 4));

        // A(3,1) = 3
        assertEquals(3, App.calculateArrangements(3, 1));

        // A(n,0) = 1 для будь-якого n
        assertEquals(1, App.calculateArrangements(5, 0));

        // A(k,n) = 0 якщо k > n
        assertEquals(0, App.calculateArrangements(3, 5));
    }

    @Test
    public void testCalculateArrangementsWithRepetition() {
        // Тест розміщень з повтореннями A'(n,k) = n^k

        // A'(2,8) для двійкової системи з 8 біт
        assertEquals(256, App.calculateArrangementsWithRepetition(2, 8));

        // A'(3,2) = 3^2 = 9
        assertEquals(9, App.calculateArrangementsWithRepetition(3, 2));

        // A'(2,3) = 2^3 = 8
        assertEquals(8, App.calculateArrangementsWithRepetition(2, 3));

        // A'(n,1) = n для будь-якого n
        assertEquals(5, App.calculateArrangementsWithRepetition(5, 1));

        // A'(n,0) = 1 для будь-якого n
        assertEquals(1, App.calculateArrangementsWithRepetition(5, 0));
    }

    @Test
    public void testFactorial() {
        // Тест функції факторіала
        assertEquals(1, App.factorial(0));
        assertEquals(1, App.factorial(1));
        assertEquals(2, App.factorial(2));
        assertEquals(6, App.factorial(3));
        assertEquals(24, App.factorial(4));
        assertEquals(120, App.factorial(5));
    }

    @Test
    public void testSpecificTaskValues() {
        // Тест для конкретних значень із завдання

        // Завдання 1: 11 учасників, команда з 4 спортсменів
        // A(11,4) = 11*10*9*8 = 7920
        long result1 = App.calculateArrangements(11, 4);
        assertEquals(7920, result1);
        System.out.println("Завдання 1 - A(11,4) = " + result1);

        // Завдання 2: двійкова система (2 цифри), 8 біт (один байт)
        // A'(2,8) = 2^8 = 256
        long result2 = App.calculateArrangementsWithRepetition(2, 8);
        assertEquals(256, result2);
        System.out.println("Завдання 2 - A'(2,8) = " + result2);
    }
}
