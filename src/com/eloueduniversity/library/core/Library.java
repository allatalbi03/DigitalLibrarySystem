package com.eloueduniversity.library.core;
import com.eloueduniversity.library.enums.BookStatus;
import com.eloueduniversity.library.models.*;
import com.eloueduniversity.library.utils.InputValidator;
import java.util.*;

public class Library implements LibraryInterface {
    private Map<String, Book> books = new HashMap<>();
    private Map<String, Borrower> borrowers = new HashMap<>();
    private Map<String, BorrowingRecord> records = new HashMap<>();

    @Override
    public void addBook(Scanner scanner) {
        System.out.println("\nإضافة كتاب جديد:");
        System.out.print("نوع الكتاب (1-ورقي، 2-إلكتروني): ");
        int typeChoice = InputValidator.getInt(scanner, 1, 2);
        
        String id = "BOOK-" + System.currentTimeMillis();
        System.out.print("العنوان: ");
        String title = scanner.nextLine();
        System.out.print("المؤلف: ");
        String author = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        
        Book book;
        if (typeChoice == 1) {
            System.out.print("عدد الصفحات: ");
            int pages = InputValidator.getInt(scanner, 1, 5000);
            System.out.print("الموقع: ");
            String location = scanner.nextLine();
            book = new PhysicalBook(id, title, author, isbn, pages, location);
        } else {
            System.out.print("صيغة الملف: ");
            String fileFormat = scanner.nextLine();
            System.out.print("حجم الملف (MB): ");
            double fileSize = InputValidator.getDouble(scanner, 0.1, 1000);
            book = new EBook(id, title, author, isbn, fileFormat, fileSize);
        }
        
        books.put(id, book);
        System.out.println("تمت إضافة الكتاب بنجاح!");
    }

    @Override
    public void addBorrower(Scanner scanner) {
        System.out.println("\nإضافة مستعير جديد:");
        String id = "BORR-" + System.currentTimeMillis();
        System.out.print("الاسم: ");
        String name = scanner.nextLine();
        System.out.print("الرقم الجامعي: ");
        String uniId = scanner.nextLine();
        
        borrowers.put(id, new Borrower(id, name, uniId));
        System.out.println("تمت إضافة المستعير بنجاح!");
    }

    @Override
    public void borrowBook(Scanner scanner) {
        System.out.println("\nإعارة كتاب:");
        System.out.print("رقم الكتاب: ");
        String bookId = scanner.nextLine();
        System.out.print("رقم المستعير: ");
        String borrowerId = scanner.nextLine();
        
        if (books.containsKey(bookId) && borrowers.containsKey(borrowerId)) {
            String recordId = "REC-" + System.currentTimeMillis();
            BorrowingRecord record = new BorrowingRecord(recordId, bookId, borrowerId);
            records.put(recordId, record);
            books.get(bookId).setStatus(BookStatus.BORROWED);
            System.out.println("تمت إعارة الكتاب بنجاح!");
        } else {
            System.out.println("الكتاب أو المستعير غير موجود!");
        }
    }

    @Override
    public void returnBook(Scanner scanner) {
        System.out.println("\nاسترجاع كتاب:");
        System.out.print("رقم الكتاب: ");
        String bookId = scanner.nextLine();
        
        if (books.containsKey(bookId)) {
            books.get(bookId).setStatus(BookStatus.AVAILABLE);
            System.out.println("تم استرجاع الكتاب بنجاح");
        } else {
            System.out.println("الكتاب غير موجود!");
        }
    }

    @Override
    public void displayAvailableBooks() {
        System.out.println("\nالكتب المتاحة:");
        books.values().stream()
            .filter(b -> b.getStatus() == BookStatus.AVAILABLE)
            .forEach(System.out::println);
    }

    @Override
    public void displayBorrowedBooks() {
        System.out.println("\nالكتب المعارة:");
        books.values().stream()
            .filter(b -> b.getStatus() == BookStatus.BORROWED)
            .forEach(System.out::println);
    }

    @Override
    public void saveData() {
        // سيتم تطبيقها لاحقاً
    }

    @Override
    public void loadData() {
        // سيتم تطبيقها لاحقاً
    }
}