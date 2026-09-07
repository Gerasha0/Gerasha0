# 🔬 Lab 4: Advanced Android Layouts & ConstraintLayout Dynamic Views

## 📌 Overview
Comprehensive Android application exploring advanced layout mechanisms, dynamic view manipulation, ConstraintLayout chains, circular positioning, and programmatic `ConstraintSet` animations.

## 🎯 Tasks & Features

1. **Tab 1: RelativeLayout Basics**
   - Implements dual button alignment using relative positioning rules (`layout_toLeftOf`, `layout_toRightOf`).

2. **Tab 2: Dynamic View Migration**
   - Demonstrates programmatic view re-parenting and rule updates at runtime, moving a `MaterialCardView` between top and bottom anchors using `RelativeLayout.LayoutParams`.

3. **Tab 3: ConstraintLayout Basics**
   - Recreates responsive side-by-side positioning using `ConstraintLayout` constraints.

4. **Tab 4: Advanced ConstraintLayout Techniques**
   - **Horizontal Chain Styles**: Toggles between `CHAIN_SPREAD`, `CHAIN_SPREAD_INSIDE`, and `CHAIN_PACKED` using `ConstraintSet`.
   - **Group Visibility**: Toggles visibility of multiple views simultaneously using `androidx.constraintlayout.widget.Group`.
   - **Circular Positioning**: Rotates a view along an orbital path using `layout_constraintCircleAngle`.
   - **ConstraintSet Animations**: Smooth keyframe transitions between constraint states using `TransitionManager.beginDelayedTransition()`.

## 🛠️ Project Structure
```
lab4/
├── app/
│   └── src/main/
│       ├── java/com/example/lab4/
│       │   └── MainActivity.kt                # Tab controller & dynamic layout logic
│       └── res/layout/
│           ├── activity_main.xml              # TabLayout & Content Container
│           ├── layout_task1_relative.xml      # Task 1 RelativeLayout
│           ├── layout_task2_dynamic.xml       # Task 2 Dynamic Container
│           ├── layout_task3_constraint.xml    # Task 3 ConstraintLayout
│           └── layout_task4_advanced.xml      # Task 4 Chains, Group, Orbit & Anim
└── build.gradle.kts
```

## 🚀 How to Run
Open `lab4` in Android Studio and run (`./gradlew assembleDebug`). Switch between tabs using the top TabLayout.
