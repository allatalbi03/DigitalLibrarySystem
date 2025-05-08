package com.eloueduniversity.library.core;

import java.util.Scanner;

public interface LibraryInterface {
    // إدارة الكتب
    void addBook(Scanner scanner);
    void searchBook(Scanner scanner);
    
    // إدارة المستعيرين
    void addBorrower(Scanner scanner);
    void searchBorrower(Scanner scanner);
    
    // عمليات الإعارة
    void borrowBook(Scanner scanner);
    void returnBook(Scanner scanner);
    
    // العروض
    void displayAvailableBooks();
    void displayBorrowedBooks();
    
    // إدارة البيانات
    void saveData();
    void loadData();
}