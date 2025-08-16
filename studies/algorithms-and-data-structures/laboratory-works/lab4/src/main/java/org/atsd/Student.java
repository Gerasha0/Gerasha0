package org.atsd;

/**
 * Клас, що представляє студента
 */
public class Student {
    private String surname; // Прізвище
    private String name; // Ім'я
    private double averageGrade; // Середній бал
    private String gender; // Стать

    /**
     * Конструктор студента
     */
    public Student(String surname, String name, double averageGrade, String gender) {
        this.surname = surname;
        this.name = name;
        this.averageGrade = averageGrade;
        this.gender = gender;
    }

    // Геттери
    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public String getGender() {
        return gender;
    }

    // Сеттери
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("%.2f - %-15s %-12s %-6s",
            averageGrade, surname, name, gender);
    }

    /**
     * Форматований рядок для заголовка таблиці
     */
    public static String getTableHeader() {
        return String.format("%-6s   %-15s %-12s %-6s",
            "Бал", "Прізвище", "Ім'я", "Стать");
    }

    /**
     * Роздільник для таблиці
     */
    public static String getTableSeparator() {
        return "─".repeat(45);
    }

    /**
     * Порівняння студентів за середнім балом
     */
    public int compareByGrade(Student other) {
        return Double.compare(this.averageGrade, other.averageGrade);
    }
}
