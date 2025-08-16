package business.models;

import java.io.Serializable;

public class Vacancy implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String title; // Назва посади
    private String company; // Назва компанії
    private double salary; // Зарплата
    private String description; // Опис вакансії

    // Конструктор
    public Vacancy(int id, String title, String company, double salary, String description) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.salary = salary;
        this.description = description;
    }

    // Геттери та сеттери
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Перевизначення методу toString для зручного виведення
    @Override
    public String toString() {
        return "Vacancy{" +
                "ID=" + id +
                ", Title='" + title + '\'' +
                ", Company='" + company + '\'' +
                ", Salary=" + salary +
                ", Description='" + description + '\'' +
                '}';
    }
}
