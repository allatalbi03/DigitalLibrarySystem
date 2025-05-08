package com.eloueduniversity.library;

import com.eloueduniversity.library.core.Library;
import com.eloueduniversity.library.utils.InputValidator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("نظام إدارة المكتبة الرقمية - جامعة الوادي");
        
        while (true) {
            System.out.println("\n1. إضافة كتاب");
            System.out.println("2. إضافة مستعير");
            System.out.println("3. إعارة كتاب");
            System.out.println("4. استرجاع كتاب");
            System.out.println("5. عرض الكتب المتاحة");
            System.out.println("6. عرض الكتب المعارة");
            System.out.println("7. الخروج");
            System.out.print("اختر الخيار: ");
            
            int choice = InputValidator.getInt(scanner, 1, 7);
            
            switch (choice) {
                case 1 -> library.addBook(scanner);
                case 2 -> library.addBorrower(scanner);
                case 3 -> library.borrowBook(scanner);
                case 4 -> library.returnBook(scanner);
                case 5 -> library.displayAvailableBooks();
                case 6 -> library.displayBorrowedBooks();
                case 7 -> {
                    System.out.println("شكراً لاستخدام النظام. إلى اللقاء!");
                    System.exit(0);
                }
            }
        }
    }
}