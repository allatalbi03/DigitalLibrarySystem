package com.eloueduniversity.library.utils;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputValidator {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static int getInt(Scanner scanner, int min, int max) {
        while (true) {
            try {
                System.out.print("> ");
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.printf("الرجاء إدخال رقم بين %d و %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("إدخال غير صالح. الرجاء إدخال رقم صحيح: ");
            }
        }
    }

    public static double getDouble(Scanner scanner, double min, double max) {
        while (true) {
            try {
                System.out.print("> ");
                double input = Double.parseDouble(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.printf("الرجاء إدخال رقم بين %.2f و %.2f: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("إدخال غير صالح. الرجاء إدخال رقم: ");
            }
        }
    }

    public static String getNonEmptyString(Scanner scanner, String fieldName) {
        while (true) {
            System.out.print(fieldName + ": ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("هذا الحقل مطلوب!");
        }
    }

    public static LocalDate getDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " (dd/MM/yyyy): ");
            String dateStr = scanner.nextLine();
            try {
                return LocalDate.parse(dateStr, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("صيغة التاريخ غير صحيحة. استخدم dd/MM/yyyy");
            }
        }
    }

    public static boolean getYesNo(Scanner scanner, String question) {
        while (true) {
            System.out.print(question + " (نعم/لا): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("نعم") || input.equals("n") || input.equals("y")) {
                return true;
            } else if (input.equals("لا") || input.equals("no") || input.equals("n")) {
                return false;
            }
            System.out.println("الرجاء إدخال 'نعم' أو 'لا'");
        }
    }
}