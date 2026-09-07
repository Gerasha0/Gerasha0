# 📱 Mobile Device Programming

This repository contains laboratory works for the **Mobile Device Programming** (Програмування мобільних пристроїв) course, focusing on modern Android application development using Kotlin, Jetpack Compose, Material Design 3, and traditional XML Views.

<div align="center">
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white"/>
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"/>
  <img src="https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white"/>
  <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"/>
  <img src="https://img.shields.io/badge/Material%203-757575?style=for-the-badge&logo=materialdesign&logoColor=white"/>
</div>

## 📚 Course Overview

The course covers key fundamentals of Android software engineering:
- Android application architecture and lifecycle management
- Resource management, custom graphic shapes, and multilingual localization
- UI development using both Jetpack Compose and XML Views (with Interoperability)
- Complex mathematical expression parsing and unit testing
- Advanced layout management: `RelativeLayout`, `LinearLayout`, `FrameLayout`, and `ConstraintLayout`
- Dynamic layout manipulation, `ConstraintSet` keyframe animations, chains, and group visibility

## 🔬 Laboratory Works

| Lab | Topic | Description | Technologies |
|-----|-------|-------------|--------------|
| [lab1](laboratory-works/lab1) | Android Resources & Localization | Resource management (`strings`, `colors`, `styles`), custom XML graphic shapes, and multilingual localization (UK, DE, EN) with flags & coats of arms. | Kotlin, Jetpack Compose, Material 3, Android Resources |
| [lab2](laboratory-works/lab2) | Scientific Calculator Application | Mathematical expression evaluator with support for arithmetic, exponentiation, modulo, parentheses, input history, error handling, and unit tests. | Kotlin, View System, Expression Engine, JUnit |
| [lab3](laboratory-works/lab3) | UI Layouts & Interactive Overlays | Comparative view demonstration toggling between `FrameLayout` (overlay mode) and `LinearLayout` (sequential mode) with styled card UI elements. | Kotlin, FrameLayout, LinearLayout, Material Cards |
| [lab4](laboratory-works/lab4) | Advanced ConstraintLayout & Dynamic Views | Multi-tab lab showcasing `RelativeLayout` rule positioning, runtime view migration, `ConstraintLayout` chains, group toggling, circular positioning, and `ConstraintSet` animations. | Kotlin, ConstraintLayout, ConstraintSet, RelativeLayout, TabLayout |

## 🛠️ Technologies Used

- **Language**: Kotlin
- **UI Frameworks**: Jetpack Compose, Android XML Views (ConstraintLayout, RelativeLayout, FrameLayout, LinearLayout), Material Design 3
- **Build System**: Gradle (Kotlin DSL `build.gradle.kts`) with Version Catalogs (`libs.versions.toml`)
- **Testing**: JUnit 4 (Unit testing for calculation logic)
- **IDE**: Android Studio / IntelliJ IDEA

## 🚀 Getting Started

### Prerequisites
- JDK 17 or higher
- Android Studio Ladybug (or newer) / Android SDK (API 34+)

### Building and Running
Each laboratory work is an independent Android Studio project:

```bash
# Navigate to desired lab folder
cd laboratory-works/lab1

# Build debug APK
./gradlew assembleDebug

# Run unit tests (e.g. for Lab 2)
cd ../lab2
./gradlew test
```

## 📈 Learning Outcomes

Upon completion of these laboratory works, students will be able to:
- Structure native Android applications using modern Kotlin practices
- Implement localized multi-language user interfaces with dynamic locale changing
- Build clean, responsive UI layouts with both Jetpack Compose and ConstraintLayout
- Implement business logic decoupled from UI components and verify it with Unit tests
- Manipulate UI hierarchy dynamically at runtime using programmatically modified layout parameters and `ConstraintSet`

---

*Part of the academic portfolio at [gerasha0](https://github.com/gerasha0)*
