package org.atsd;

/**
 * Допоміжний клас для роботи з системами числення
 */
public class NumberConverter {

    /**
     * Конвертує десяткове число у вісімкову систему числення
     */
    public static String toOctal(int decimal) {
        if (decimal == 0) {
            return "0";
        }

        StringBuilder octal = new StringBuilder();
        while (decimal > 0) {
            octal.insert(0, decimal % 8);
            decimal /= 8;
        }

        return octal.toString();
    }

    /**
     * Перевіряє, чи є рядок коректним вісімковим числом
     */
    public static boolean isValidOctal(String octal) {
        if (octal == null || octal.isEmpty()) {
            return false;
        }

        for (char c : octal.toCharArray()) {
            if (c < '0' || c > '7') {
                return false;
            }
        }

        return true;
    }
}
