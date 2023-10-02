package Kata;


import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите выражение: ");
            String expression = scanner.nextLine();
            String[] parts = expression.split(" ");

            if (parts.length != 3) {
                throw new Exception("Строка не является математической операцией!");
            }

            String a = parts[0];
            String operator = parts[1];
            String b = parts[2];

            if (isRoman(a) && isRoman(b)) {
                int result = calculateRoman(toArabic(a), operator, toArabic(b));
                System.out.println("Результат: " + toRoman(result));
            } else if (isArabic(a) && isArabic(b)) {
                int result = calculateArabic(Integer.parseInt(a), operator, Integer.parseInt(b));
                System.out.println("Результат: " + result);
            } else {
                throw new Exception("Ошибка! Используются разные системы счисления!");
            }
        } catch (Exception e) {
            System.out.println("Ошибка! " + e.getMessage());
        }
    }

    public static boolean isRoman(String number) {
        return number.matches("[IVX]+");
    }

    public static boolean isArabic(String number) {
        return number.matches("[0-9]+");
    }

    public static int toArabic(String romanNumber) {
        int result = 0;
        int prevValue = 0;

        for (int i = romanNumber.length() - 1; i >= 0; i--) {
            char symbol = romanNumber.charAt(i);
            int value = getRomanValue(symbol);

            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
                prevValue = value;
            }
        }

        return result;
    }

    public static int getRomanValue(char symbol) {
        switch (symbol) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            default:
                return 0;
        }
    }

    public static String toRoman(int number) {
        StringBuilder romanNumber = new StringBuilder();

        String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arabicValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        for (int i = 0; i < arabicValues.length; i++) {
            while (number >= arabicValues[i]) {
                romanNumber.append(romanSymbols[i]);
                number -= arabicValues[i];
            }
        }

        return romanNumber.toString();
    }

    public static int calculateRoman(int a, String operator, int b) throws Exception {
        int result;

        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                if (result < 0) {
                    throw new Exception("Ошибка! В римской системе нет отрицательных чисел!");
                }
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new Exception("Неподдерживаемая операция!");
        }

        return result;
    }

    public static int calculateArabic(int a, String operator, int b) throws Exception {
        int result;

        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new Exception("Неподдерживаемая операция!");
        }

        return result;
    }
}
