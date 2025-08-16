package ui;

import business.models.*;
import business.services.*;
import business.utils.ValidationUtil;

import java.util.List;

public class Main {
    private static final VacancyService vacancyService = new VacancyService();
    private static final ResumeService resumeService = new ResumeService();
    private static final UnemployedService unemployedService = new UnemployedService();
    private static final EmployerService employerService = new EmployerService();
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Job Exchange System ===");
            System.out.println("1. Manage Vacancies");
            System.out.println("2. Manage Resumes");
            System.out.println("3. Manage Unemployed");
            System.out.println("4. Manage Employers");
            System.out.println("5. Exit");
            int choice = ValidationUtil.getIntInput("Select an option: ");

            switch (choice) {
                case 1 -> manageVacancies();
                case 2 -> manageResumes();
                case 3 -> manageUnemployed();
                case 4 -> manageEmployers();
                case 5 -> exitSystem();
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Меню управління вакансіями
    private static void manageVacancies() {
        while (true) {
            System.out.println("\n=== Manage Vacancies ===");
            System.out.println("1. Add Vacancy");
            System.out.println("2. View All Vacancies");
            System.out.println("3. Update Vacancy");
            System.out.println("4. Delete Vacancy");
            System.out.println("5. Search Vacancies");
            System.out.println("6. Back");
            int choice = ValidationUtil.getIntInput("Select an option: ");

            switch (choice) {
                case 1 -> {
                    String title = ValidationUtil.getStringInput("Enter Title: ");
                    String company = ValidationUtil.getStringInput("Enter Company: ");
                    double salary = ValidationUtil.getDoubleInput("Enter Salary: ");
                    String description = ValidationUtil.getStringInput("Enter Description: ");
    
                    vacancyService.addVacancy(title, company, salary, description);
                }
                case 2 -> {
                    System.out.println("\n=== View Vacancies ===");
                    System.out.println("1. View All Vacancies (Unsorted)");
                    System.out.println("2. View Vacancies Sorted by Title");
                    System.out.println("3. View Vacancies Sorted by Salary");
                    int sortChoice = ValidationUtil.getIntInput("Select an option: ");
                
                    List<Vacancy> vacancies;
                    switch (sortChoice) {
                        case 1 -> vacancies = vacancyService.getAllVacancies();
                        case 2 -> vacancies = vacancyService.getSortedVacanciesByTitle();
                        case 3 -> vacancies = vacancyService.getSortedVacanciesBySalary();
                        default -> {
                            System.out.println("Invalid option. Returning to previous menu.");
                            return;
                        }
                    }
                
                    if (vacancies.isEmpty()) {
                        System.out.println("No vacancies available.");
                    } else {
                        System.out.println("\n=== Vacancies ===");
                        vacancies.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    int id = ValidationUtil.getIntInput("Enter Vacancy ID to update: ");
                    String title = ValidationUtil.getStringInput("Enter New Title: ");
                    String company = ValidationUtil.getStringInput("Enter New Company: ");
                    double salary = ValidationUtil.getDoubleInput("Enter New Salary: ");
                    String description = ValidationUtil.getStringInput("Enter New Description: ");
    
                    Vacancy updatedVacancy = new Vacancy(id, title, company, salary, description);
                    vacancyService.updateVacancy(id, updatedVacancy);
                }
                case 4 -> {
                    int id = ValidationUtil.getIntInput("Enter Vacancy ID to delete: ");
                    vacancyService.removeVacancy(id);
                }
                case 5 -> {
                    System.out.println("\n=== Search Vacancy ===");
                    System.out.println("1. Search by Title");
                    System.out.println("2. Search by Keyword (Title or Description)");
                    int searchChoice = ValidationUtil.getIntInput("Select an option: ");
                
                    switch (searchChoice) {
                        case 1 -> {
                            String title = ValidationUtil.getStringInput("Enter Vacancy Title to view: ");
                            Vacancy vacancy = vacancyService.getVacancyByTitle(title);
                            if (vacancy != null) {
                                System.out.println("\n=== Vacancy Details ===");
                                System.out.println(vacancy);
                            } else {
                                System.out.println("Vacancy with Title '" + title + "' not found.");
                            }
                        }
                        case 2 -> {
                            String keyword = ValidationUtil.getStringInput("Enter keyword to search vacancies: ");
                            List<Vacancy> foundVacancies = vacancyService.searchVacanciesByKeyword(keyword);
                            if (foundVacancies.isEmpty()) {
                                System.out.println("No vacancies found with the given keyword.");
                            } else {
                                System.out.println("\n=== Search Results ===");
                                foundVacancies.forEach(System.out::println);
                            }
                        }
                        default -> System.out.println("Invalid option. Returning to previous menu.");
                    }
                }
                case 6 -> {
                    return; // Повернення до головного меню
                }
                default -> System.out.println("Invalid option. Please try again.");
                }
        }
    }

    // Меню управління резюме
    private static void manageResumes() {
        while (true) {
            System.out.println("\n=== Manage Resumes ===");
            System.out.println("1. Add Resume");
            System.out.println("2. View All Resumes");
            System.out.println("3. Update Resume");
            System.out.println("4. Delete Resume");
            System.out.println("5. Search Resumes");
            System.out.println("6. Back");   
            int choice = ValidationUtil.getIntInput("Select an option: ");
    
            switch (choice) {
                case 1 -> {
                    String fullName = ValidationUtil.getStringInput("Enter Full name: ");
                    String position = ValidationUtil.getStringInput("Enter Desired Position: ");
                    double salary = ValidationUtil.getDoubleInput("Enter Excepted Salary: ");
                    String skills = ValidationUtil.getStringInput("Enter Skills: ");
                    String experience = ValidationUtil.getStringInput("Enter Experience: ");
    
                    resumeService.addResume(fullName, position, salary, skills, experience);
                }
                case 2 -> {
                    System.out.println("\n=== View Resumes ===");
                    System.out.println("1. View All Resumes (Unsorted)");
                    System.out.println("2. View Resumes Sorted by Name");
                    System.out.println("3. View Resumes Sorted by Expected Salary");
                    int sortChoice = ValidationUtil.getIntInput("Select an option: ");
                
                    List<Resume> resumes;
                    switch (sortChoice) {
                        case 1 -> resumes = resumeService.getAllResumes();
                        case 2 -> resumes = resumeService.getSortedResumesByName();
                        case 3 -> resumes = resumeService.getSortedResumesBySalary();
                        default -> {
                            System.out.println("Invalid option. Returning to previous menu.");
                            return;
                        }
                    }
                
                    if (resumes.isEmpty()) {
                        System.out.println("No resumes available.");
                    } else {
                        System.out.println("\n=== Resumes ===");
                        resumes.forEach(System.out::println);
                    }
                }                
                case 3 -> {
                    int id = ValidationUtil.getIntInput("Enter Resume ID to update: ");
                    String fullName = ValidationUtil.getStringInput("Enter New Full name: ");
                    String position = ValidationUtil.getStringInput("Enter New Desired Position: ");
                    double salary = ValidationUtil.getDoubleInput("Enter New Excepted Salary: ");
                    String skills = ValidationUtil.getStringInput("Enter New Skills: ");
                    String experience = ValidationUtil.getStringInput("Enter New Experience: ");
    
                    Resume updatedResume = new Resume(id, fullName, position, salary, skills, experience);
                    resumeService.updateResume(id, updatedResume);
                }
                case 4 -> {
                    int id = ValidationUtil.getIntInput("Enter Resume ID to delete: ");
                    resumeService.removeResume(id);
                }
                case 5 -> {
                    String fullName = ValidationUtil.getStringInput("Enter Full Name to view Resume: ");
                    Resume resume = resumeService.getResumeByFullName(fullName);
                    if (resume != null) {
                        System.out.println("\n=== Resume Details ===");
                        System.out.println(resume);
                    } else {
                        System.out.println("Resume with Full Name '" + fullName + "' not found.");
                    }
                }
                case 6 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
    
        // Меню управління безробітними
        private static void manageUnemployed() {
            while (true) {
                System.out.println("\n=== Manage Unemployed ===");
                System.out.println("1. Add Unemployed");
                System.out.println("2. View All Unemployed");
                System.out.println("3. Update Unemployed");
                System.out.println("4. Delete Unemployed");
                System.out.println("5. Search Unemployed");
                System.out.println("6. Back");
                System.out.print("Select an option: ");
                int choice = ValidationUtil.getIntInput("Select an option: ");
    
                switch (choice) {
                    case 1 -> {
                        String fullName = ValidationUtil.getStringInput("Enter Full Name: ");
                        int age = ValidationUtil.getIntInput("Enter Age: ");
                        String education = ValidationUtil.getStringInput("Enter Education: ");
                        String profession = ValidationUtil.getStringInput("Enter Profession: ");
                        String contact = ValidationUtil.getStringInput("Enter Contact Info: ");
        
                        unemployedService.addUnemployed(fullName, age, education, profession, contact);
                    }
                    case 2 -> {
                        System.out.println("\n=== View Unemployed ===");
                        System.out.println("1. View All Unemployed (Unsorted)");
                        System.out.println("2. View Unemployed Sorted by Name");
                        System.out.println("3. View Unemployed Sorted by Education");
                        int sortChoice = ValidationUtil.getIntInput("Select an option: ");
                    
                        List<Unemployed> unemployedList;
                        switch (sortChoice) {
                            case 1 -> unemployedList = unemployedService.getAllUnemployed();
                            case 2 -> unemployedList = unemployedService.getSortedUnemployedByName();
                            case 3 -> unemployedList = unemployedService.getSortedUnemployedByEducation();
                            default -> {
                                System.out.println("Invalid option. Returning to previous menu.");
                                return;
                            }
                        }
                    
                        if (unemployedList.isEmpty()) {
                            System.out.println("No unemployed persons available.");
                        } else {
                            System.out.println("\n=== Unemployed ===");
                            unemployedList.forEach(System.out::println);
                        }
                    }
                    case 3 -> {
                        int id = ValidationUtil.getIntInput("Enter Unemployed ID to update: ");
                        String fullName = ValidationUtil.getStringInput("Enter New Full Name: ");
                        int age = ValidationUtil.getIntInput("Enter New Age: ");
                        String education = ValidationUtil.getStringInput("Enter New Education: ");
                        String profession = ValidationUtil.getStringInput("Enter New Profession: ");
                        String contact = ValidationUtil.getStringInput("Enter New Contact Info: ");
        
                        Unemployed updatedUnemployed = new Unemployed(id, fullName, age, education, profession, contact);
                        unemployedService.updateUnemployed(id, updatedUnemployed);
                    }
                    case 4 -> {
                        int id = ValidationUtil.getIntInput("Enter Unemployed ID to delete: ");
                        unemployedService.removeUnemployed(id);
                    }
                    case 5 -> {
                        System.out.println("\n=== Search Unemployed ===");
                        System.out.println("1. Search by Full Name");
                        System.out.println("2. Search by Keyword (Full Name or Profession)");
                        int searchChoice = ValidationUtil.getIntInput("Select an option: ");
                    
                        switch (searchChoice) {
                            case 1 -> {
                                String fullName = ValidationUtil.getStringInput("Enter Full Name to view Unemployed Person: ");
                                Unemployed unemployed = unemployedService.getUnemployedByFullName(fullName);
                                if (unemployed != null) {
                                    System.out.println("\n=== Unemployed Details ===");
                                    System.out.println(unemployed);
                                } else {
                                    System.out.println("Unemployed person with Full Name '" + fullName + "' not found.");
                                }
                            }
                            case 2 -> {
                                String keyword = ValidationUtil.getStringInput("Enter keyword to search unemployed: ");
                                List<Unemployed> foundUnemployed = unemployedService.searchUnemployedByKeyword(keyword);
                                if (foundUnemployed.isEmpty()) {
                                    System.out.println("No unemployed persons found with the given keyword.");
                                } else {
                                    System.out.println("\n=== Search Results ===");
                                    foundUnemployed.forEach(System.out::println);
                                }
                            }
                            default -> System.out.println("Invalid option. Returning to previous menu.");
                        }
                    }
                    case 6 -> {
                        return;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            }
        }
    
        // Меню управління замовниками
        private static void manageEmployers() {
            while (true) {
                System.out.println("\n=== Manage Employers ===");
                System.out.println("1. Add Employer");
                System.out.println("2. View All Employers");
                System.out.println("3. Update Employer");
                System.out.println("4. Delete Employer");
                System.out.println("5. Search Employers");
                System.out.println("6. Back");
                int choice = ValidationUtil.getIntInput("Select an option: ");
    
                switch (choice) {
                    case 1 -> {
                        String companyName = ValidationUtil.getStringInput("Enter Company Name: ");
                        String contactPerson = ValidationUtil.getStringInput("Enter Contact Person: ");
                        String phoneNumber = ValidationUtil.getStringInput("Enter Phone Number: ");
                        String email = ValidationUtil.getStringInput("Enter Email: ");
                        String address = ValidationUtil.getStringInput("Enter Address: ");
        
                        employerService.addEmployer(companyName, contactPerson, phoneNumber, email, address);
                    }
                    case 2 -> {
                        System.out.println("\n=== View Employers ===");
                        System.out.println("1. View All Employers (Unsorted)");
                        System.out.println("2. View Employers Sorted by Company Name");
                        System.out.println("3. View Employers Sorted by Contact Person");
                        int sortChoice = ValidationUtil.getIntInput("Select an option: ");
                    
                        List<Employer> employers;
                        switch (sortChoice) {
                            case 1 -> employers = employerService.getAllEmployers();
                            case 2 -> employers = employerService.getSortedEmployersByCompanyName();
                            case 3 -> employers = employerService.getSortedEmployersByContactPerson();
                            default -> {
                                System.out.println("Invalid option. Returning to previous menu.");
                                return;
                            }
                        }
                    
                        if (employers.isEmpty()) {
                            System.out.println("No employers available.");
                        } else {
                            System.out.println("\n=== Employers ===");
                            employers.forEach(System.out::println);
                        }
                    }
                    
                    case 3 -> {
                        int id = ValidationUtil.getIntInput("Enter Employer ID to update: ");
                        String companyName = ValidationUtil.getStringInput("Enter New Company Name: ");
                        String contactPerson = ValidationUtil.getStringInput("Enter New Contact Person: ");
                        String phoneNumber = ValidationUtil.getStringInput("Enter New Phone Number: ");
                        String email = ValidationUtil.getStringInput("Enter New Email: ");
                        String address = ValidationUtil.getStringInput("Enter New Address: ");
        
                        Employer updatedEmployer = new Employer(id, companyName, contactPerson, phoneNumber, email, address);
                        employerService.updateEmployer(id, updatedEmployer);
                    }
                    case 4 -> {
                        int id = ValidationUtil.getIntInput("Enter Employer ID to delete: ");
                        employerService.removeEmployer(id);
                    }
                    case 5 -> {
                        String companyName = ValidationUtil.getStringInput("Enter Company Name to view Employer: ");
                        Employer employer = employerService.getEmployerByCompanyName(companyName);
                        if (employer != null) {
                            System.out.println("\n=== Employer Details ===");
                            System.out.println(employer);
                        } else {
                            System.out.println("Employer with Company Name '" + companyName + "' not found.");
                        }
                    }
                    case 6 -> {
                        return;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            }
        }
    
    

    
    private static void exitSystem() {
        System.out.println("Exiting the system...");
        ValidationUtil.closeScanner();
        System.exit(0);
    }
}
