package business.models;

import java.io.Serializable;

public class Unemployed implements Serializable {
    private static final long serialVersionUID = 3L;
    
    private int id;
    private String fullName; // ПІБ безробітного
    private int age; // Вік
    private String education; // Освіта
    private String profession; // Спеціальність
    private String contactInfo; // Контактна інформація

    // Конструктор
    public Unemployed(int id, String fullName, int age, String education, String profession, String contactInfo) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.education = education;
        this.profession = profession;
        this.contactInfo = contactInfo;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // Перевизначення методу toString для зручного виведення
    @Override
    public String toString() {
        return "Unemployed{" +
                "ID=" + id +
                ", FullName='" + fullName + '\'' +
                ", Age=" + age +
                ", Education='" + education + '\'' +
                ", Profession='" + profession + '\'' +
                ", Contact Info='" + contactInfo + '\'' +
                '}';
    }
}
