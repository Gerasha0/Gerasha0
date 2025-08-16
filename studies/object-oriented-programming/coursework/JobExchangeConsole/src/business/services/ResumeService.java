package business.services;

import business.models.Resume;
import business.utils.FileManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResumeService {
    private List<Resume> resumes; // Список резюме
    private static final String FILE_NAME = "data/resumes.txt";

    // втоматичне генерування ID
    private int generateId() {
        return resumes.isEmpty() ? 1 : resumes.get(resumes.size() - 1).getId() + 1;
    }

    // Конструктор
    public ResumeService() {
        this.resumes = loadResumes(); // Завантаження резюме з файлу
    }

    // Збереження резюме у файл
    public void saveResumes() {
        FileManager.saveToFile(FILE_NAME, resumes);
    }

    // Завантаження резюме з файлу
    private List<Resume> loadResumes() {
        List<Resume> loadedResumes = FileManager.loadFromFile(FILE_NAME);
        return loadedResumes != null ? loadedResumes : new ArrayList<>();
    }

    // Додавання резюме
    public void addResume(String fullName, String desiredPosition, double expectedSalary, String skills, String experience) {
        Resume resume = new Resume(generateId(), fullName, desiredPosition, expectedSalary, skills, experience);
        resumes.add(resume);
        saveResumes();
        System.out.println("Resume added: " + resume);
    }

    // Видалення резюме за ID
    public void removeResume(int id) {
        if (resumes.removeIf(resume -> resume.getId() == id)) {
            saveResumes();
            System.out.println("Resume with ID " + id + " removed.");
        } else {
            System.out.println("Resume with ID " + id + " not found.");
        }
    }

    // Оновлення даних резюме
    public void updateResume(int id, Resume updatedResume) {
        for (int i = 0; i < resumes.size(); i++) {
            if (resumes.get(i).getId() == id) {
                resumes.set(i, updatedResume);
                saveResumes();
                System.out.println("Resume with ID " + id + " updated.");
                return;
            }
        }
        System.out.println("Resume with ID " + id + " not found.");
    }

    // Отримання резюме за ID
    public Resume getResumeById(int id) {
        for (Resume resume : resumes) {
            if (resume.getId() == id) {
                return resume;
            }
        }
        System.out.println("Resume with ID " + id + " not found.");
        return null;
    }

    // Отримання всіх резюме
    public List<Resume> getAllResumes() {
        return new ArrayList<>(resumes);
    }

    // 
    public Resume getResumeByFullName(String fullName) {
        return resumes.stream()
                .filter(resume -> resume.getFullName().equalsIgnoreCase(fullName))
                .findFirst()
                .orElse(null);
    }
    
    // Сортування резюме за ім'ям кандидата
    public List<Resume> getSortedResumesByName() {
        return resumes.stream()
                .sorted(Comparator.comparing(Resume::getFullName))
                .toList();
    }

    // Сортування резюме за очікуваною зарплатою
    public List<Resume> getSortedResumesBySalary() {
        return resumes.stream()
                .sorted(Comparator.comparingDouble(Resume::getExpectedSalary))
                .toList();
    }
}
