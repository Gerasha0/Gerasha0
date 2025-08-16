package org.atsd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Юніт-тести для додатку.
 */
public class AppTest {

    @Test
    public void testRhombusCreation() {
        Rhombus rhombus = Rhombus.createRandomRhombus();
        assertNotNull(rhombus);
        assertTrue(rhombus.isValidRhombus());
        assertTrue(rhombus.getArea() > 0);
        assertTrue(rhombus.getPerimeter() > 0);
    }

    @Test
    public void testRhombusCalculations() {
        // Створюємо ромб з відомими координатами
        Rhombus rhombus = new Rhombus(2, 0, 0, 1, -2, 0, 0, -1);

        assertTrue(rhombus.isValidRhombus());

        double expectedPerimeter = 4 * Math.sqrt(5); // 4 сторони по sqrt(5)
        assertEquals(expectedPerimeter, rhombus.getPerimeter(), 1e-6);

        double expectedArea = 4.0; // Площа цього ромба
        assertEquals(expectedArea, rhombus.getArea(), 1e-6);
    }

    @Test
    public void testHashTableInsertWithoutCollisions() {
        RhombusHashTable table = new RhombusHashTable(10);

        // Створюємо ромби з різними периметрами
        Rhombus rhombus1 = new Rhombus(1, 0, 0, 1, -1, 0, 0, -1);
        Rhombus rhombus2 = new Rhombus(2, 0, 0, 2, -2, 0, 0, -2);

        assertTrue(table.insertWithoutCollisions(rhombus1));
        assertTrue(table.insertWithoutCollisions(rhombus2));
        assertEquals(2, table.getSize());
    }

    @Test
    public void testHashTableInsertWithCollisions() {
        RhombusHashTable table = new RhombusHashTable(5);

        // Заповнюємо таблицю
        for (int i = 0; i < 4; i++) {
            Rhombus rhombus = Rhombus.createRandomRhombus();
            assertTrue(table.insert(rhombus));
        }

        assertEquals(4, table.getSize());
    }

    @Test
    public void testHashTableSearch() {
        RhombusHashTable table = new RhombusHashTable(10);
        Rhombus rhombus = new Rhombus(1, 0, 0, 1, -1, 0, 0, -1);

        assertTrue(table.insert(rhombus));

        Rhombus found = table.search(rhombus.getPerimeter());
        assertNotNull(found);
        assertEquals(rhombus.getPerimeter(), found.getPerimeter(), 1e-6);
    }

    @Test
    public void testHashTableRemoveByArea() {
        RhombusHashTable table = new RhombusHashTable(10);

        // Додаємо кілька ромбів
        Rhombus smallRhombus = new Rhombus(1, 0, 0, 1, -1, 0, 0, -1); // площа = 2
        Rhombus largeRhombus = new Rhombus(3, 0, 0, 3, -3, 0, 0, -3); // площа = 18

        table.insert(smallRhombus);
        table.insert(largeRhombus);

        assertEquals(2, table.getSize());

        // Видаляємо елементи з площею більше 10
        int removedCount = table.removeByAreaCriterion(10.0);
        assertEquals(1, removedCount);
        assertEquals(1, table.getSize());
    }

    @Test
    public void testEmptyHashTable() {
        RhombusHashTable table = new RhombusHashTable(5);

        assertTrue(table.isEmpty());
        assertEquals(0, table.getSize());
        assertEquals(5, table.getCapacity());

        assertNull(table.search(10.0));
        assertEquals(0, table.removeByAreaCriterion(100.0));
    }
}
