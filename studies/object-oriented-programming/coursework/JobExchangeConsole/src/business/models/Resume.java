package business.models;

import java.io.Serializable;

public class Resume implements Serializable {
    private static final long serialVersionUID = 2L;
    
    private int id;
    private String fullName; // ПІБ кандидата
    private String desiredPosition; // Бажана посада
    private double expectedSalary; // Очікувана зарплата
    private String skills; // Навички кандидата
    private String experience; // Досвід роботи

    // Конструктор
    public Resume(int id, String fullName, String desiredPosition, double expectedSalary, String skills, String experience) {
        this.id = id;
        this.fullName = fullName;
        this.desiredPosition = desiredPosition;
        this.expectedSalary = expectedSalary;
        this.skills = skills;
        this.experience = experience;
    }

    // Геттери та сеттери
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDesiredPosition() {
        return desiredPosition;
    }

    public void setDesiredPosition(String desiredPosition) {
        this.desiredPosition = desiredPosition;
    }

    public double getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(double expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    // Перевизначення методу toString для зручного виведення
    @Override
    public String toString() {
        return "Resume{" +
                "ID=" + id +
                ", FullName='" + fullName + '\'' +
                ", Desired Position='" + desiredPosition + '\'' +
                ", Expected Salary=" + expectedSalary +
                ", Skills='" + skills + '\'' +
                ", Experience='" + experience + '\'' +
                '}';
    }
}
