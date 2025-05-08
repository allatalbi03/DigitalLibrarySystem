package com.eloueduniversity.library.models;

import java.util.ArrayList;
import java.util.List;

public class Borrower {
    private String id;
    private String name;
    private String universityId;
    private List<String> borrowedBooks;

    public Borrower(String id, String name, String universityId) {
        this.id = id;
        this.name = name;
        this.universityId = universityId;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getUniversityId() { return universityId; }
    public List<String> getBorrowedBooks() { return borrowedBooks; }
    
    public void borrowBook(String bookId) {
        borrowedBooks.add(bookId);
    }
    
    public boolean returnBook(String bookId) {
        return borrowedBooks.remove(bookId);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s) | Books borrowed: %d", 
                id, name, universityId, borrowedBooks.size());
    }
}