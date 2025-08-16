package org.atsd;

/**
 * Клас, що представляє студента
 */
public class Student {
    private String surname; // Прізвище
    private int studentId; // Студентський квиток (ключ для дерева)
    private double averageGrade; // Середній бал
    private boolean participatesInConferences; // Участь у конференціях

    /**
     * Конструктор студента
     */
    public Student(String surname, int studentId, double averageGrade, boolean participatesInConferences) {
        this.surname = surname;
        this.studentId = studentId;
        this.averageGrade = averageGrade;
        this.participatesInConferences = participatesInConferences;
    }

    /**
     * Перевіряє, чи є студент відмінником (середній бал >= 4.5)
     */
    public boolean isExcellentStudent() {
        return averageGrade >= 4.5;
    }

    /**
     * Перевіряє критерій пошуку: відмінник + участь у конференціях
     */
    public boolean matchesSearchCriteria() {
        return isExcellentStudent() && participatesInConferences;
    }

    // Геттери
    public String getSurname() {
        return surname;
    }

    public int getStudentId() {
        return studentId;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public boolean participatesInConferences() {
        return participatesInConferences;
    }

    // Сеттери
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void setParticipatesInConferences(boolean participatesInConferences) {
        this.participatesInConferences = participatesInConferences;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-8d %-6.2f %-5s",
            surname,
            studentId,
            averageGrade,
            participatesInConferences ? "Так" : "Ні");
    }

    /**
     * Форматований рядок для заголовка таблиці
     */
    public static String getTableHeader() {
        return String.format("%-15s %-8s %-6s %-5s",
            "Прізвище", "Квиток", "Бал", "Конф.");
    }

    /**
     * Роздільник для таблиці
     */
    public static String getTableSeparator() {
        return "─".repeat(40);
    }
}
