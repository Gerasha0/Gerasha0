# 🔬 Lab 2: Scientific Calculator & Expression Evaluator

## 📌 Overview
An Android Scientific Calculator application featuring expression parsing, error handling, soft keyboard prevention, and unit tests for the calculation engine.

## 🎯 Features

- **Mathematical Operations**: Addition (`+`), Subtraction (`−`), Multiplication (`×`), Division (`÷`), Power (`^`), Modulo (`%`).
- **Smart Parentheses**: Automatic opening/closing parenthesis selection based on cursor context.
- **Custom Expression Engine**: `ExpressionEvaluator.kt` parses mathematical string expressions safely into formatted results.
- **Input Controls**: Digit input, decimal point, backspace (⌫), clear (C), and plus/minus sign toggle.
- **Unit Testing**: Included `CalculatorEngineTest.kt` verifying arithmetic precedence and edge cases.

## 🛠️ Project Structure
```
lab2/
├── app/
│   └── src/
│       ├── main/
│       │   ├── java/com/example/lab2/
│       │   │   ├── MainActivity.kt          # UI controller & button listeners
│       │   │   └── ExpressionEvaluator.kt   # Mathematical evaluation logic
│       │   └── res/layout/
│       │       └── activity_main.xml        # Calculator Grid Layout UI
│       └── test/java/com/example/lab2/
│           └── CalculatorEngineTest.kt      # Unit tests for calculation logic
└── build.gradle.kts
```

## 🚀 How to Run
```bash
# Run unit tests
./gradlew test

# Build debug package
./gradlew assembleDebug
```
