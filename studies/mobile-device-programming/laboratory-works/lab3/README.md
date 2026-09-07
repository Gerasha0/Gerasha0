# 🔬 Lab 3: UI Layouts & Interactive Overlays

## 📌 Overview
An interactive Android application demonstrating layout behavior differences between `FrameLayout` (overlapping stack) and `LinearLayout` (sequential orientation).

## 🎯 Features

- **FrameLayout Overlay Mode**: Displays stacked Card views overlapping each other inside a `FrameLayout`.
- **LinearLayout Sequential Mode**: Renders UI components linearly in vertical sequence.
- **Mode Toggle Button**: Dynamic UI visibility switching with `Toast` notifications indicating current layout mode.
- **Custom Card Styling**: Uses custom background drawables (`bg_card.xml`, `img.xml`) and Material Card design.

## 🛠️ Project Structure
```
lab3/
├── app/
│   └── src/main/
│       ├── java/com/example/lab3/
│       │   └── MainActivity.kt        # Mode switching logic
│       └── res/
│           ├── drawable/              # bg_card.xml & img.xml
│           └── layout/
│               └── activity_main.xml  # Combined FrameLayout & LinearLayout layout
└── build.gradle.kts
```

## 🚀 How to Run
Open `lab3` in Android Studio and run on an emulator/device (`./gradlew assembleDebug`).
