package business.services;

import business.models.Vacancy;
import business.utils.FileManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VacancyService {
    private List<Vacancy> vacancies; // Список вакансій
    private static final String FILE_NAME = "data/vacancies.txt";

    // Автоматичне генерування ID
    private int generateId() {
        return vacancies.isEmpty() ? 1 : vacancies.get(vacancies.size() - 1).getId() + 1;
    }
    
    // Конструктор
    public VacancyService() {
        this.vacancies = loadVacancies(); // Завантаження вакансій з файлу
    }

    // Збереження вакансій у файл
    public void saveVacancies() {
        FileManager.saveToFile(FILE_NAME, vacancies);
    }

    // Завантаження вакансій з файлу
    private List<Vacancy> loadVacancies() {
        List<Vacancy> loadedVacancies = FileManager.loadFromFile(FILE_NAME);
        return loadedVacancies != null ? loadedVacancies : new ArrayList<>();
    }

    // Додавання вакансій
    public void addVacancy(String title, String company, double salary, String description) {
        Vacancy vacancy = new Vacancy(generateId(), title, company, salary, description);
        vacancies.add(vacancy);
        saveVacancies();
        System.out.println("Vacancy added: " + vacancy);
    }
    
    // Видалення вакансії за ID
    public void removeVacancy(int id) {
        if (vacancies.removeIf(vacancy -> vacancy.getId() == id)) {
            saveVacancies();
            System.out.println("Vacancy with ID " + id + " removed.");
        } else {
            System.out.println("Vacancy with ID " + id + " not found.");
        }
    }
         

    // Оновлення даних вакансії
    public void updateVacancy(int id, Vacancy updatedVacancy) {
        for (int i = 0; i < vacancies.size(); i++) {
            if (vacancies.get(i).getId() == id) {
                vacancies.set(i, updatedVacancy);
                saveVacancies();
                System.out.println("Vacancy with ID " + id + " updated.");
                return;
            }
        }
        System.out.println("Vacancy with ID " + id + " not found.");
    }

    public Vacancy getVacancyByTitle(String title) {
        return vacancies.stream()
                .filter(vacancy -> vacancy.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    // Отримання вакансії за ID
    public Vacancy getVacancyById(int id) {
        for (Vacancy vacancy : vacancies) {
            if (vacancy.getId() == id) {
                return vacancy;
            }
        }
        System.out.println("Vacancy with ID " + id + " not found.");
        return null;
    }

    // Отримання всіх вакансій
    public List<Vacancy> getAllVacancies() {
        return new ArrayList<>(vacancies);
    }

    // Сортування вакансій за назвою
    public List<Vacancy> getSortedVacanciesByTitle() {
        return vacancies.stream()
                .sorted(Comparator.comparing(Vacancy::getTitle))
                .toList();
    }

    // Сортування вакансій за зарплатою 
    public List<Vacancy> getSortedVacanciesBySalary() {
        return vacancies.stream()
                .sorted(Comparator.comparingDouble(Vacancy::getSalary))
                .toList();
    }

    // Пошук вакансій за ключовим словом
    public List<Vacancy> searchVacanciesByKeyword(String keyword) {
        return vacancies.stream()
            .filter(vacancy -> vacancy.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    vacancy.getDescription().toLowerCase().contains(keyword.toLowerCase()))
            .toList();
    }

}
