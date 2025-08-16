package business.utils;

import java.util.Scanner;

public class ValidationUtil {
    private static final Scanner scanner = new Scanner(System.in);

    // Метод для вводу цілих чисел з валідацією
    public static int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    // Метод для вводу чисел з плаваючою точкою з валідацією
    public static double getDoubleInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    // Метод для вводу рядків з перевіркою на порожність
    public static String getStringInput(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    // Метод для закриття Scanner при завершенні програми
    public static void closeScanner() {
        scanner.close();
    }
}
