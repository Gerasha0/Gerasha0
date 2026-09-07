# 🔬 Lab 1: Android Resources, Graphic Shapes & Multilingual Localization

## 📌 Overview
This laboratory work focuses on managing Android application resources, creating custom vector and XML graphic shapes, and implementing dynamic multilingual localization (Ukrainian, German, English).

## 🎯 Features & Tasks

1. **Task 1: Custom Styled Text & Resources**
   - Displays student surname styled with custom background colors, borders, and typography using Jetpack Compose and resource definitions (`colors.xml`, `styles.xml`).

2. **Task 2: Custom XML Graphic Shapes**
   - Renders a custom XML graphic shape (`custom_shape.xml`) using `AndroidView` interoperability in Jetpack Compose.

3. **Task 3: Multilingual Localization & Flag/Emblem Display**
   - Supports three locales: **Ukrainian (`uk`)**, **German (`de`)**, and **English (`en-GB`)**.
   - Dynamically displays corresponding national flags and coats of arms loaded from locale-specific drawable qualifiers (`drawable-uk/`, `drawable-de/`, `drawable-en-rGB/`).
   - Includes interactive locale switcher chips updating app language via `AppCompatDelegate.setApplicationLocales()`.

## 🛠️ Project Structure
```
lab1/
├── app/
│   └── src/main/
│       ├── java/com/example/lab1resources/
│       │   ├── MainActivity.kt        # Jetpack Compose UI & language switcher logic
│       │   └── ui/theme/              # Compose color, type, and theme definitions
│       └── res/
│           ├── drawable/              # Default images and custom_shape.xml
│           ├── drawable-uk/           # Ukrainian flag & emblem
│           ├── drawable-de/           # German flag & emblem
│           ├── drawable-en-rGB/       # UK flag & emblem
│           ├── values/                # Default strings, styles, colors
│           ├── values-uk/             # Ukrainian strings
│           ├── values-de/             # German strings
│           └── values-en-rGB/         # English strings
└── build.gradle.kts
```

## 🚀 How to Run
1. Open the `lab1` folder in Android Studio.
2. Sync Gradle files.
3. Run on an emulator or physical device (`./gradlew assembleDebug`).
