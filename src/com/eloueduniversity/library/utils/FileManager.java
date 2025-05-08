package com.eloueduniversity.library.utils;

import com.eloueduniversity.library.core.Library;
import com.eloueduniversity.library.models.*;

import java.io.*;
import java.util.Map;

public class FileManager {
    private static final String BOOKS_FILE = "data/books.dat";
    private static final String BORROWERS_FILE = "data/borrowers.dat";
    private static final String RECORDS_FILE = "data/records.dat";

    public static void saveLibraryData(Library library) {
        try {
            createDataDirectory();
            
            // حفظ الكتب
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKS_FILE))) {
                oos.writeObject(library.getBooks());
            }
            
            // حفظ المستعيرين
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BORROWERS_FILE))) {
                oos.writeObject(library.getBorrowers());
            }
            
            // حفظ سجلات الإعارة
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RECORDS_FILE))) {
                oos.writeObject(library.getBorrowingRecords());
            }
            
        } catch (IOException e) {
            System.err.println("حدث خطأ أثناء حفظ البيانات: " + e.getMessage());
        }
    }

    public static void loadLibraryData(Library library) {
        try {
            // تحميل الكتب
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOKS_FILE))) {
                Map<String, Book> books = (Map<String, Book>) ois.readObject();
                library.setBooks(books);
            }
            
            // تحميل المستعيرين
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BORROWERS_FILE))) {
                Map<String, Borrower> borrowers = (Map<String, Borrower>) ois.readObject();
                library.setBorrowers(borrowers);
            }
            
            // تحميل سجلات الإعارة
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RECORDS_FILE))) {
                Map<String, BorrowingRecord> records = (Map<String, BorrowingRecord>) ois.readObject();
                library.setBorrowingRecords(records);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("لم يتم العثور على ملفات البيانات. سيتم البدء بقاعدة بيانات فارغة.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("حدث خطأ أثناء تحميل البيانات: " + e.getMessage());
        }
    }

    private static void createDataDirectory() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
    }

    public static void exportToTextFile(Library library, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("===== تقرير المكتبة =====");
            writer.println("\nالكتب:");
            library.getBooks().values().forEach(writer::println);
            
            writer.println("\nالمستعيرون:");
            library.getBorrowers().values().forEach(writer::println);
            
            writer.println("\nسجلات الإعارة:");
            library.getBorrowingRecords().values().forEach(writer::println);
            
            writer.println("\n===== نهاية التقرير =====");
            System.out.println("تم تصدير البيانات إلى ملف: " + filename);
        } catch (IOException e) {
            System.err.println("حدث خطأ أثناء تصدير البيانات: " + e.getMessage());
        }
    }
}