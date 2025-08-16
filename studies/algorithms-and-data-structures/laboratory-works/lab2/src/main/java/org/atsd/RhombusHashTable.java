package org.atsd;

import java.util.ArrayList;
import java.util.List;

/**
 * Хеш-таблиця з відкритою адресацією для елементів типу Rhombus
 * Використовує метод ділення для хешування за ключем (периметр)
 * Розв'язання колізій - подвійне хешування
 */
public class RhombusHashTable {
    private Rhombus[] table;
    private boolean[] deleted; // Масив для позначення видалених елементів
    private int size;
    private int capacity;

    public RhombusHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new Rhombus[capacity];
        this.deleted = new boolean[capacity];
        this.size = 0;
    }

    /**
     * Основна хеш-функція (метод ділення)
     */
    private int hash1(double key) {
        // Перетворюємо double в int для хешування
        int intKey = (int) (key * 1000); // Множимо на 1000 для врахування десятк��вих знаків
        return Math.abs(intKey) % capacity;
    }

    /**
     * Друга хеш-функція для подвійного хешування
     */
    private int hash2(double key) {
        int intKey = (int) (key * 1000);
        // Використовуємо просте число, менше розміру таблиці
        int prime = capacity > 7 ? 7 : capacity - 1;
        if (prime < 1) prime = 1;
        return prime - (Math.abs(intKey) % prime);
    }

    /**
     * Вставка елемента в хеш-таблицю
     * @param rhombus елемент для вставки
     * @return true якщо вставка успішна, false якщо не вдалося вставити
     */
    public boolean insert(Rhombus rhombus) {
        if (size >= capacity) {
            return false; // Таблиця заповнена
        }

        double key = rhombus.getPerimeter();
        int index = hash1(key);
        int step = hash2(key);
        int attempts = 0;

        while (attempts < capacity) {
            if (table[index] == null || deleted[index]) {
                table[index] = rhombus;
                deleted[index] = false;
                size++;
                return true;
            }

            // Двойное хеширование
            index = (index + step) % capacity;
            attempts++;
        }

        return false; // Не вдалося знайти вільне місце
    }

    /**
     * Вставка елемента без колізій (для першого рівня завдання)
     */
    public boolean insertWithoutCollisions(Rhombus rhombus) {
        double key = rhombus.getPerimeter();
        int index = hash1(key);

        if (table[index] == null || deleted[index]) {
            table[index] = rhombus;
            deleted[index] = false;
            size++;
            return true;
        }

        return false; // Позиція зайнята - колізія
    }

    /**
     * Пошук елемента за ключем
     */
    public Rhombus search(double key) {
        int index = hash1(key);
        int step = hash2(key);
        int attempts = 0;

        while (attempts < capacity && table[index] != null) {
            if (!deleted[index] && Math.abs(table[index].getPerimeter() - key) < 1e-6) {
                return table[index];
            }
            index = (index + step) % capacity;
            attempts++;
        }

        return null; // Не знайдено
    }

    /**
     * Видалення елементів за критерієм (площа більша за задану)
     */
    public int removeByAreaCriterion(double maxArea) {
        int removedCount = 0;

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && !deleted[i] && table[i].getArea() > maxArea) {
                deleted[i] = true;
                size--;
                removedCount++;
            }
        }

        return removedCount;
    }

    /**
     * Отримання всіх елементів таблиці
     */
    public List<Rhombus> getAllElements() {
        List<Rhombus> elements = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && !deleted[i]) {
                elements.add(table[i]);
            }
        }
        return elements;
    }

    /**
     * Виведення вмісту хеш-таблиці
     */
    public void display() {
        System.out.println("\\n=== Вміст хеш-таблиці ===");
        System.out.printf("%-8s %-15s %-10s %s%n", "Позиція", "Ключ(периметр)", "Статус", "Елемент");
        System.out.println("─".repeat(80));

        for (int i = 0; i < capacity; i++) {
            if (table[i] == null) {
                System.out.printf("%-8d %-15s %-10s %s%n", i, "-", "пусто", "немає елемента");
            } else if (deleted[i]) {
                System.out.printf("%-8d %-15.2f %-10s %s%n", i, table[i].getPerimeter(), "видалено", "видалений елемент");
            } else {
                System.out.printf("%-8d %-15.2f %-10s %s%n", i, table[i].getPerimeter(), "зайнято", table[i]);
            }
        }

        System.out.printf("\\nВсього елементів: %d з %d\\n", size, capacity);
    }

    /**
     * Перевірка на порожність
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Отримання поточного розміру
     */
    public int getSize() {
        return size;
    }

    /**
     * Отримання ємності
     */
    public int getCapacity() {
        return capacity;
    }
}
