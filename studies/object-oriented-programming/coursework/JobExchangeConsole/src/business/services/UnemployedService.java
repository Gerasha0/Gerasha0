package business.services;

import business.models.Unemployed;
import business.utils.FileManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UnemployedService {
    private List<Unemployed> unemployedList; // Список безробітних
    private static final String FILE_NAME = "data/unemployed.txt";

    // Автоматичне генерування ID
    private int generateId() {
        return unemployedList.isEmpty() ? 1 : unemployedList.get(unemployedList.size() - 1).getId() + 1;
    }

    // Конструктор
    public UnemployedService() {
        this.unemployedList = loadUnemployed(); // Завантаження безробітних з файлу
    }

    // Збереження безробітних у файл
    public void saveUnemployed() {
        FileManager.saveToFile(FILE_NAME, unemployedList);
    }

    // Завантаження безробітних з файлу
    private List<Unemployed> loadUnemployed() {
        List<Unemployed> loadedUnemployed = FileManager.loadFromFile(FILE_NAME);
        return loadedUnemployed != null ? loadedUnemployed : new ArrayList<>();
    }

    // Додавання безробітного
    public void addUnemployed(String fullName, int age, String education, String profession, String contactInfo) {
        Unemployed unemployed = new Unemployed(generateId(), fullName, age, education, profession, contactInfo);
        unemployedList.add(unemployed);
        saveUnemployed();
        System.out.println("Unemployed person added: " + unemployed);
    }

    // Видалення безробітного за ID
    public void removeUnemployed(int id) {
        if (unemployedList.removeIf(unemployed -> unemployed.getId() == id)) {
            saveUnemployed();
            System.out.println("Unemployed person with ID " + id + " removed.");
        } else {
            System.out.println("Unemployed person with ID " + id + " not found.");
        }
    }

    // Оновлення даних безробітного
    public void updateUnemployed(int id, Unemployed updatedUnemployed) {
        for (int i = 0; i < unemployedList.size(); i++) {
            if (unemployedList.get(i).getId() == id) {
                unemployedList.set(i, updatedUnemployed);
                saveUnemployed();
                System.out.println("Unemployed person with ID " + id + " updated.");
                return;
            }
        }
        System.out.println("Unemployed person with ID " + id + " not found.");
    }

    // Отримання безробітного за ID
    public Unemployed getUnemployedById(int id) {
        for (Unemployed unemployed : unemployedList) {
            if (unemployed.getId() == id) {
                return unemployed;
            }
        }
        System.out.println("Unemployed person with ID " + id + " not found.");
        return null;
    }

    public Unemployed getUnemployedByFullName(String fullName) {
        return unemployedList.stream()
                .filter(unemployed -> unemployed.getFullName().equalsIgnoreCase(fullName))
                .findFirst()
                .orElse(null);
    }    

    // Отримання всіх безробітних
    public List<Unemployed> getAllUnemployed() {
        return new ArrayList<>(unemployedList);
    }

   // Сортування безробітних за ім'ям
    public List<Unemployed> getSortedUnemployedByName() {
        return unemployedList.stream()
            .sorted(Comparator.comparing(Unemployed::getFullName))
            .toList();
    }
    
    // Сортування безробітних за освітою
    public List<Unemployed> getSortedUnemployedByEducation() {
        return unemployedList.stream()
            .sorted(Comparator.comparing(Unemployed::getEducation))
            .toList();
    }

    // Пошук безробітних за ключовим словом
    public List<Unemployed> searchUnemployedByKeyword(String keyword) {
        return unemployedList.stream()
            .filter(unemployed -> unemployed.getFullName().toLowerCase().contains(keyword.toLowerCase()) ||
                    unemployed.getProfession().toLowerCase().contains(keyword.toLowerCase()))
            .toList();
}
}
