package business.models;

import java.io.Serializable;

public class Employer implements Serializable {
    private static final long serialVersionUID = 4L;
    
    private int id;
    private String companyName; // Назва компанії
    private String contactPerson; // Контактна особа
    private String phoneNumber; // Номер телефону
    private String email; // Email компанії
    private String address; // Адреса компанії

    // Конструктор
    public Employer(int id, String companyName, String contactPerson, String phoneNumber, String email, String address) {
        this.id = id;
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    // Геттери та сеттери
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Перевизначення методу toString для зручного виведення
    @Override
    public String toString() {
        return "Employer{" +
                "ID=" + id +
                ", CompanyName='" + companyName + '\'' +
                ", ContactPerson='" + contactPerson + '\'' +
                ", PhoneNumber='" + phoneNumber + '\'' +
                ", Email='" + email + '\'' +
                ", Address='" + address + '\'' +
                '}';
    }
}
