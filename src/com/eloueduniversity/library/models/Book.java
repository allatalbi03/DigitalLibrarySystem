package com.eloueduniversity.library.models;
import com.eloueduniversity.library.enums.BookStatus;
import com.eloueduniversity.library.enums.BookType;

public abstract class Book {
    protected String id;
    protected String title;
    protected String author;
    protected String isbn;
    protected BookStatus status;
    protected BookType type;

    public Book(String id, String title, String author, String isbn, BookType type) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.type = type;
        this.status = BookStatus.AVAILABLE;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public BookStatus getStatus() { return status; }
    public BookType getType() { return type; }
    
    public void setStatus(BookStatus status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("%s - %s (%s) | %s | %s", 
                id, title, author, type, status);
    }
}
