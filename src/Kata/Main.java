package Kata;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите выражение: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));

    }

    public static String calc(String input) {
        try {

            String[] parts = input.split(" ");

            if (parts.length != 3) {
                throw new Exception("Строка не является математической операцией!");
            }

            String firstNumb = parts[0];
            String operator = parts[1];
            String secondNumb = parts[2];

            if (isRoman(firstNumb) && isRoman(secondNumb)) {
                return "Результат: " + toRoman(calculateRoman(toArabic(firstNumb), operator, toArabic(secondNumb)));
            } else if (isArabic(firstNumb) && isArabic(secondNumb)) {
                int firstNumbArabic = Integer.parseInt(firstNumb);
                int secondNumbArabic = Integer.parseInt(secondNumb);
                if ((firstNumbArabic < 1 || firstNumbArabic > 10) || (secondNumbArabic < 1 || secondNumbArabic > 10)) {
                    throw new RuntimeException("Введенные числа либо больше 10 либо меньше 1");
                }
                return "Результат: " + calculateArabic(Integer.parseInt(firstNumb), operator, Integer.parseInt(secondNumb));

            } else {
                throw new Exception("Ошибка! Используются разные системы счисления!");
            }
        } catch (Exception e) {
            System.out.println("Ошибка! " + e.getMessage());
        }
        return "";
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
