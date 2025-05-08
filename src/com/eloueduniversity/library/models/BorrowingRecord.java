package com.eloueduniversity.library.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowingRecord {
    private String id;
    private String bookId;
    private String borrowerId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean returned;
    private double fineAmount;

    // ثوابت النظام
    private static final int MAX_BORROW_DAYS = 14;
    private static final double DAILY_FINE_RATE = 5.0;

    public BorrowingRecord(String id, String bookId, String borrowerId) {
        this.id = id;
        this.bookId = bookId;
        this.borrowerId = borrowerId;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(MAX_BORROW_DAYS);
        this.returned = false;
        this.fineAmount = 0.0;
    }

    // Getters
    public String getId() { return id; }
    public String getBookId() { return bookId; }
    public String getBorrowerId() { return borrowerId; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public boolean isReturned() { return returned; }
    public double getFineAmount() { return fineAmount; }

    /**
     * إرجاع الكتاب وحساب الغرامة إن وجدت
     */
    public void returnBook() {
        this.returnDate = LocalDate.now();
        this.returned = true;
        calculateFine();
    }

    /**
     * حساب الغرامة في حالة التأخير
     */
    private void calculateFine() {
        if (returnDate.isAfter(dueDate)) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
            this.fineAmount = daysLate * DAILY_FINE_RATE;
        }
    }

    /**
     * تمديد مدة الإعارة
     * @param extraDays عدد الأيام الإضافية
     * @return true إذا تم التمديد بنجاح
     */
    public boolean extendBorrowing(int extraDays) {
        if (!returned && extraDays > 0) {
            this.dueDate = dueDate.plusDays(extraDays);
            return true;
        }
        return false;
    }

    /**
     * التحقق من تأخر الإرجاع
     */
    public boolean isOverdue() {
        return !returned && LocalDate.now().isAfter(dueDate);
    }

    /**
     * عدد أيام التأخير
     */
    public long getDaysOverdue() {
        if (isOverdue()) {
            return ChronoUnit.DAYS.between(dueDate, LocalDate.now());
        }
        return 0;
    }

    @Override
    public String toString() {
        String status = returned ? 
            String.format("تم الإرجاع في %s (غرامة: %.2f دينار)", returnDate, fineAmount) :
            String.format("مستحق في %s%s", dueDate, isOverdue() ? " (متأخر!)" : "");
        
        return String.format(
            "إعارة [%s]\n- الكتاب: %s\n- المستعير: %s\n- تاريخ الإعارة: %s\n- الحالة: %s",
            id, bookId, borrowerId, borrowDate, status
        );
    }

    // Builder Pattern لإنشاء السجلات
    public static class Builder {
        private String id;
        private String bookId;
        private String borrowerId;

        public Builder(String id) {
            this.id = id;
        }

        public Builder withBook(String bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder withBorrower(String borrowerId) {
            this.borrowerId = borrowerId;
            return this;
        }

        public BorrowingRecord build() {
            return new BorrowingRecord(id, bookId, borrowerId);
        }
    }
}