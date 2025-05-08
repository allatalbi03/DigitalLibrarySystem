package com.eloueduniversity.library.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DateUtils {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("صيغة التاريخ غير صحيحة. استخدم dd/MM/yyyy");
        }
    }

    public static String formatDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    public static LocalDate getFutureDate(Scanner scanner) {
        while (true) {
            System.out.print("أدخل التاريخ (dd/MM/yyyy): ");
            String dateStr = scanner.nextLine();
            try {
                LocalDate date = parseDate(dateStr);
                if (date.isAfter(LocalDate.now())) {
                    return date;
                }
                System.out.println("يجب أن يكون التاريخ في المستقبل!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static LocalDate getPastDate(Scanner scanner) {
        while (true) {
            System.out.print("أدخل التاريخ (dd/MM/yyyy): ");
            String dateStr = scanner.nextLine();
            try {
                LocalDate date = parseDate(dateStr);
                if (date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now())) {
                    return date;
                }
                System.out.println("يجب أن يكون التاريخ في الماضي أو اليوم!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}