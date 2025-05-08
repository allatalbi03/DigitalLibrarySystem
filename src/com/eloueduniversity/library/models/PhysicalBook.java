package com.eloueduniversity.library.models;


import com.eloueduniversity.library.enums.BookType;

public class PhysicalBook extends Book {
    private int pages;
    private String location;

    public PhysicalBook(String id, String title, String author, String isbn, 
                      int pages, String location) {
        super(id, title, author, isbn, BookType.PHYSICAL);
        this.pages = pages;
        this.location = location;
    }

    // Getters and additional methods
    public int getPages() { return pages; }
    public String getLocation() { return location; }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | Pages: %d | Location: %s", 
                pages, location);
    }
}