package learn;


import java.sql.SQLOutput;

public class MathOperations {
    public static void main(String[] args) {

        System.out.println("применить несколько арифметических операций ( + , -, * , /) над двумя примитивами типа int");
        arifmeticOperations(4, 3, 0);
        arifmeticOperations(3, 4, 1);
        arifmeticOperations(6, 2, 2);
        arifmeticOperations(9, 3, 3);

        System.out.println("применить несколько арифметических операций над int и double в одном выражении");
        arifmeticOperations(9, 3.0);

        System.out.println("Применить несколько логических операций ( < , >, >=, <= )");
        logicalOperations(-275);
        logicalOperations(275);
        logicalOperations(5);
        logicalOperations(-25);

        System.out.println("Прочитать про диапазоны типов данных для вещественных / чисел с плавающей точкой (какие максимальные и минимальные значения есть, как их получить) и переполнение");
        MaxMinInf();

        System.out.println("Получить переполнение при арифметической операции");
        MaxMin();

    }

    public static void arifmeticOperations(int a, int b, int c) {

        if (c == 0) {
            System.out.println(a + " + " + b + " = " + (a + b));
        }
        if (c == 1) {
            System.out.println(a + " - " + b + " = " + (a - b));
        }
        if (c == 2) {
            System.out.println(a + " * " + b + " = " + (a * b));
        }
        if (c == 3) {
            System.out.println(a + " / " + b + " = " + (a / b));
        }
    }

    public static void arifmeticOperations(int a, double b) {

        System.out.println(a + " + " + b + " = " + (a + b));
        System.out.println(a + " - " + b + " = " + (a - b));
        System.out.println(a + " * " + b + " = " + (a * b));
        System.out.println(a + " / " + b + " = " + (a / b));
    }


    public static void logicalOperations(int a)
    {

        if (a > 0 && a < 100) {
            System.out.println("Температура выше нуля");
        }
        else if (a < 0 && a > -273) {
            System.out.println("Температура ниже нуля");
        }
        else if (a >= 100) {
            System.out.println("Температура выше температуры кипения воды");
        }
        else if (a <= -273) {
            System.out.println("Такая температура невозможна, вернитесь в свою вселенную");
        }
    }



    public static void MaxMin() {
        int maxNumber = Integer.MAX_VALUE;
        int minNumber = Integer.MIN_VALUE;

        System.out.println("Int MaxValue:" + maxNumber);
        System.out.println("Int MinValue:" + minNumber);
        System.out.println("-Overload: " + (minNumber - 2));
        System.out.println("Overload: " + (maxNumber + 2));
    }

    public static void MaxMinInf() {
        float infFloat = Float.MAX_VALUE * 2;
        double infDouble = Double.MAX_VALUE * 2;

        System.out.println("float: 32 бита "+ Float.MIN_VALUE+ " -- "+Float.MAX_VALUE);
        System.out.println("double: 64 бита "+ Double.MIN_VALUE+ " -- "+Double.MAX_VALUE);
        System.out.println("Переполнение Float: " + infFloat);
        System.out.println("Переполнение Double: " + infDouble);
        System.out.println("Переполнение -Float: " + infFloat * -1);
        System.out.println("Переполнение -Double: " + infDouble * -1);

    }
}



