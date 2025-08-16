# 💼 Job Exchange Console

<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white"/>
</div>

## 📖 Overview

Advanced command-line application implementing core business logic for job market management. This project demonstrates object-oriented programming principles, database integration, and secure authentication systems.

## 🎯 Purpose

**Academic Project**: Coursework for Object-Oriented Programming course  
**Focus**: Demonstrating OOP concepts, data structures, and console application development

## ✨ Key Features

- 🔐 **Secure Authentication System** - User login and registration
- 📊 **Efficient Data Processing** - Optimized algorithms for job matching
- 🗄️ **Robust Database Integration** - MySQL database connectivity
- 👥 **User Management** - Separate interfaces for employers and job seekers
- 📋 **Job Listing Management** - Create, update, and delete job postings
- 🔍 **Advanced Search** - Filter jobs by category, location, and requirements
- 📈 **Performance Optimization** - Efficient data handling and caching

## 🛠️ Technologies Used

- **Language**: Java 11+
- **Build Tool**: Maven
- **Database**: MySQL
- **Testing**: JUnit 5
- **IDE**: VS Code with Java Extension Pack

## 📁 Project Structure

```
src/
├── main/java/
│   ├── models/          # Data models (User, Job, Company)
│   ├── controllers/     # Business logic controllers
│   ├── database/        # Database connection and operations
│   ├── services/        # Service layer for business operations
│   └── utils/          # Utility classes and helpers
├── test/java/          # Unit tests
└── resources/          # Configuration files
```

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- MySQL 8.0+

### Installation

1. **Clone the repository**
   ```bash
   git clone [repository-url]
   cd JobExchangeConsole
   ```

2. **Set up MySQL database**
   ```sql
   CREATE DATABASE job_exchange;
   ```

3. **Configure database connection**
   - Update `src/main/resources/database.properties`
   - Set your MySQL credentials

4. **Build the project**
   ```bash
   mvn clean compile
   ```

5. **Run the application**
   ```bash
   mvn exec:java -Dexec.mainClass="Main"
   ```

## 🎮 Usage

### Main Menu Options
1. **Register** - Create new user account
2. **Login** - Access existing account
3. **Browse Jobs** - View available positions
4. **Search Jobs** - Filter by criteria
5. **Post Job** (Employers only)
6. **Manage Applications** - Track job applications

### Sample Workflow
```bash
# Start application
java -cp target/classes Main

# Register as job seeker
> Select option: 1 (Register)
> Enter details...

# Login and browse jobs
> Select option: 2 (Login)
> Browse available positions
```

## 🧪 Testing

Run unit tests:
```bash
mvn test
```

Run specific test class:
```bash
mvn test -Dtest=UserServiceTest
```

## 📊 Features Showcase

### Database Schema
- **Users**: Authentication and profile management
- **Jobs**: Job postings with detailed requirements
- **Applications**: Track job application status
- **Companies**: Employer information and verification

### Security Features
- Password hashing with salt
- Session management
- Input validation and sanitization
- SQL injection prevention

## 🎓 Learning Outcomes

This project demonstrates:
- **OOP Principles**: Encapsulation, Inheritance, Polymorphism
- **Design Patterns**: MVC, Singleton, Factory
- **Database Design**: Normalized schema, CRUD operations
- **Testing**: Unit testing with JUnit
- **Error Handling**: Exception management and logging

## 🤝 Contributing

This is an academic project. For suggestions or improvements:
1. Fork the repository
2. Create a feature branch
3. Submit a pull request

## 📄 License

This project is part of university coursework and is for educational purposes.

## 👨‍💻 Author

**Gerasha0** - Object-Oriented Programming Coursework  
📧 [mensotor@pm.me](mailto:mensotor@pm.me)

---

<div align="center">
  <i>⭐ If this project helped you learn OOP concepts, please star it!</i>
</div>
