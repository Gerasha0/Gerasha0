package com.mrpz;

/**
 * Hello world!
 *
 */
import java.util.Scanner;


class Subscriber {
    private String surname;
    private String address;
    private int localCallMinutes;
    private int internationalCallMinutes;

    public Subscriber(String surname, String address, int localCallMinutes, int internationalCallMinutes) {
        this.surname = surname;
        this.address = address;
        this.localCallMinutes = localCallMinutes;
        this.internationalCallMinutes = internationalCallMinutes;
    }

    public String getSurname() { return surname; }
    public String getAddress() { return address; }
    public int getLocalCallMinutes() { return localCallMinutes; }
    public int getInternationalCallMinutes() { return internationalCallMinutes; }

    @Override
    public String toString() {
        return String.format("%-15s %-20s %-10d %-10d", surname, address, localCallMinutes, internationalCallMinutes);
    }
}

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 5;
        Subscriber[] subscribers = new Subscriber[n];
        System.out.println("Введіть дані для " + n + " абонентів:");
        for (int i = 0; i < n; i++) {
            System.out.println("Абонент #" + (i+1));
            String surname = inputString(scanner, "Прізвище: ");
            String address = inputString(scanner, "Адреса: ");
            int local = inputInt(scanner, "Час внутрішніх переговорів (хв): ", 0, 10000);
            int intl = inputInt(scanner, "Час міжнародних переговорів (хв): ", 0, 10000);
            subscribers[i] = new Subscriber(surname, address, local, intl);
        }
        System.out.println();
        System.out.println("Введені дані:");
        printTable(subscribers);

        // Пошук 1
        int minLocal = inputInt(scanner, "\nВведіть мінімальний час внутрішніх переговорів для пошуку: ", 0, 10000);
        System.out.println();
        System.out.println("Абоненти, у яких час внутрішніх переговорів перевищує " + minLocal + ":");
        Subscriber[] localFiltered = filterByLocalCall(subscribers, minLocal);
        if (localFiltered.length == 0) {
            System.out.println("Немає абонентів за цим критерієм.");
        } else {
            printTable(localFiltered);
        }

        // Пошук 2
        System.out.println();
        System.out.println("Абоненти, які користувалися міжнародним зв’язком:");
        Subscriber[] intlFiltered = filterByInternationalCall(subscribers);
        if (intlFiltered.length == 0) {
            System.out.println("Немає абонентів за цим критерієм.");
        } else {
            printTable(intlFiltered);
        }
    }

    private static String inputString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Помилка: рядок не може бути порожнім. Спробуйте ще раз.");
        }
    }

    private static int inputInt(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value < min || value > max) {
                    System.out.println("Помилка: значення має бути в діапазоні " + min + "-" + max + ". Спробуйте ще раз.");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть ціле число. Спробуйте ще раз.");
            }
        }
    }

    private static void printTable(Subscriber[] arr) {
        System.out.printf("%-15s %-20s %-10s %-10s%n", "Прізвище", "Адреса", "Внутр.хв", "Міжн.хв");
        System.out.println("---------------------------------------------------------------");
        for (Subscriber s : arr) {
            System.out.println(s);
        }
    }

    private static Subscriber[] filterByLocalCall(Subscriber[] arr, int minLocal) {
        return java.util.Arrays.stream(arr)
            .filter(s -> s.getLocalCallMinutes() > minLocal)
            .toArray(Subscriber[]::new);
    }

    private static Subscriber[] filterByInternationalCall(Subscriber[] arr) {
        return java.util.Arrays.stream(arr)
            .filter(s -> s.getInternationalCallMinutes() > 0)
            .toArray(Subscriber[]::new);
    }
}
