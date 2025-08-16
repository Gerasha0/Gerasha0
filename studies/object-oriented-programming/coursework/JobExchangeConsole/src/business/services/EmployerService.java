package business.services;

import business.models.Employer;
import business.utils.FileManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployerService {
    private List<Employer> employers; // Список замовників
    private static final String FILE_NAME = "data/employers.txt";

    // Автоматичне генерування ID
    private int generateId() {
        return employers.isEmpty() ? 1 : employers.get(employers.size() - 1).getId() + 1;
    }

    // Конструктор
    public EmployerService() {
        this.employers = loadEmployers(); // Завантаження замовників з файлу
    }

    // Збереження замовників у файл
    public void saveEmployers() {
        FileManager.saveToFile(FILE_NAME, employers);
    }

    // Завантаження замовників з файлу
    private List<Employer> loadEmployers() {
        List<Employer> loadedEmployers = FileManager.loadFromFile(FILE_NAME);
        return loadedEmployers != null ? loadedEmployers : new ArrayList<>();
    }
    // Додавання замовника
    public void addEmployer(String companyName, String contactPerson, String phoneNumber, String email, String address) {
        Employer employer = new Employer(generateId(), companyName, contactPerson, phoneNumber, email, address);
        employers.add(employer);
        saveEmployers();
        System.out.println("Employer added: " + employer);
    }

    // Видалення замовника за ID
    public void removeEmployer(int id) {
        if (employers.removeIf(employer -> employer.getId() == id)) {
            saveEmployers();
            System.out.println("Employer with ID " + id + " removed.");
        } else {
            System.out.println("Employer with ID " + id + " not found.");
        }
    }

    // Оновлення даних замовника
    public void updateEmployer(int id, Employer updatedEmployer) {
        for (int i = 0; i < employers.size(); i++) {
            if (employers.get(i).getId() == id) {
                employers.set(i, updatedEmployer);
                saveEmployers();
                System.out.println("Employer with ID " + id + " updated.");
                return;
            }
        }
        System.out.println("Employer with ID " + id + " not found.");
    }

    // Отримання замовника за ID
    public Employer getEmployerById(int id) {
        for (Employer employer : employers) {
            if (employer.getId() == id) {
                return employer;
            }
        }
        System.out.println("Employer with ID " + id + " not found.");
        return null;
    }

    public Employer getEmployerByCompanyName(String companyName) {
        return employers.stream()
                .filter(employer -> employer.getCompanyName().equalsIgnoreCase(companyName))
                .findFirst()
                .orElse(null);
    }

    // Отримання всіх замовників
    public List<Employer> getAllEmployers() {
        return new ArrayList<>(employers);
    }

    // Сортування замовників за назвою компанії
    public List<Employer> getSortedEmployersByCompanyName() {
        return employers.stream()
                .sorted(Comparator.comparing(Employer::getCompanyName))
                .toList();
    }

    // Сортування замовників за контактною особою
    public List<Employer> getSortedEmployersByContactPerson() {
        return employers.stream()
                .sorted(Comparator.comparing(Employer::getContactPerson))
                .toList();
    }
}
