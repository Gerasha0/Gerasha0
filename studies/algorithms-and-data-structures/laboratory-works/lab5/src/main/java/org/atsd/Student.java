package org.atsd;

/**
 * Клас, що представляє студента
 */
public class Student {
    private String surname; // Прізвище
    private String name; // Ім'я
    private String group; // Група
    private String gender; // Стать
    private double averageGrade; // Середній бал

    /**
     * Конструктор студента
     */
    public Student(String surname, String name, String group, String gender, double averageGrade) {
        this.surname = surname;
        this.name = name;
        this.group = group;
        this.gender = gender;
        this.averageGrade = averageGrade;
    }

    /**
     * Перевіряє, чи є студент студенткою з балом вище 4.5
     */
    public boolean isExcellentFemaleStudent() {
        return "Ж".equals(gender) && averageGrade > 4.5;
    }

    // Геттери
    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public String getGender() {
        return gender;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    // Сеттери
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    /**
     * Порівняння за номером групи для сортування
     */
    public int compareByGroup(Student other) {
        return this.group.compareTo(other.group);
    }

    /**
     * Порівняння за середнім балом для BST
     */
    public int compareByGrade(Student other) {
        return Double.compare(this.averageGrade, other.averageGrade);
    }

    @Override
    public String toString() {
        return String.format("%-15s %-12s %-8s %-6s %.2f",
            surname, name, group, gender, averageGrade);
    }

    /**
     * Форматований рядок для заголовка таблиці
     */
    public static String getTableHeader() {
        return String.format("%-15s %-12s %-8s %-6s %s",
            "Прізвище", "Ім'я", "Група", "Стать", "Середній бал");
    }

    /**
     * Роздільник для таблиці
     */
    public static String getTableSeparator() {
        return "─".repeat(60);
    }
}
