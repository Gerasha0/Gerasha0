package org.atsd;

import java.util.Random;

//Клас, що представляє ромб
public class Rhombus {
    private double x1, y1; // Перша вершина
    private double x2, y2; // Друга
    private double x3, y3; // ...
    private double x4, y4; // ...

    //Конструктор із заданими координатами вершин
    public Rhombus(double x1, double y1, double x2, double y2,
                   double x3, double y3, double x4, double y4) {
        this.x1 = x1; this.y1 = y1;
        this.x2 = x2; this.y2 = y2;
        this.x3 = x3; this.y3 = y3;
        this.x4 = x4; this.y4 = y4;
    }

    // Конструктор, що створює випадковий ромб
    public static Rhombus createRandomRhombus() {
        Random random = new Random();

        // Генеруємо центр ромба
        double centerX = random.nextDouble() * 20 - 10; // від -10 до 10
        double centerY = random.nextDouble() * 20 - 10;

        // Генеруємо напівдіагоналі
        double halfDiag1 = random.nextDouble() * 5 + 1; // від 1 до 6
        double halfDiag2 = random.nextDouble() * 5 + 1;

        // Обчислюємо координати вершин ромба з діагоналями, паралельними осям
        double x1 = centerX + halfDiag1;
        double y1 = centerY;
        double x2 = centerX;
        double y2 = centerY + halfDiag2;
        double x3 = centerX - halfDiag1;
        double y3 = centerY;
        double x4 = centerX;
        double y4 = centerY - halfDiag2;

        return new Rhombus(x1, y1, x2, y2, x3, y3, x4, y4);
    }

    // Обчислює довжину сторони між двома точками
    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    // Обчислює периметр ромба
    public double getPerimeter() {
        double side = distance(x1, y1, x2, y2);
        return 4 * side; // У ромба всі сторони рівні
    }

    // Обчислює площу ромба через координати вершин
    public double getArea() {
        // Використовуємо формулу площі через координати
        double area = Math.abs((x1 * (y2 - y4) + x2 * (y3 - y1) + x3 * (y4 - y2) + x4 * (y1 - y3))) / 2.0;
        return area;
    }

    // Перевіряє, чи є фігура дійсно ромбом
    public boolean isValidRhombus() {
        double side1 = distance(x1, y1, x2, y2);
        double side2 = distance(x2, y2, x3, y3);
        double side3 = distance(x3, y3, x4, y4);
        double side4 = distance(x4, y4, x1, y1);

        double epsilon = 1e-6;
        return Math.abs(side1 - side2) < epsilon &&
               Math.abs(side2 - side3) < epsilon &&
               Math.abs(side3 - side4) < epsilon;
    }

    @Override
    public String toString() {
        return String.format("Ромб[вершини: (%.2f,%.2f), (%.2f,%.2f), (%.2f,%.2f), (%.2f,%.2f), площа=%.2f, периметр=%.2f]",
                x1, y1, x2, y2, x3, y3, x4, y4, getArea(), getPerimeter());
    }

    // Геттери для координат
    public double getX1() { return x1; }
    public double getY1() { return y1; }
    public double getX2() { return x2; }
    public double getY2() { return y2; }
    public double getX3() { return x3; }
    public double getY3() { return y3; }
    public double getX4() { return x4; }
    public double getY4() { return y4; }
}