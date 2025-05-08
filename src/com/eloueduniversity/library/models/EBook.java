package com.eloueduniversity.library.models;
import com.eloueduniversity.library.enums.BookType;

public class EBook extends Book {
    private String fileFormat;
    private double fileSize; // in MB

    public EBook(String id, String title, String author, String isbn, 
                String fileFormat, double fileSize) {
        super(id, title, author, isbn, BookType.EBOOK);
        this.fileFormat = fileFormat;
        this.fileSize = fileSize;
    }

    // Getters and additional methods
    public String getFileFormat() { return fileFormat; }
    public double getFileSize() { return fileSize; }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | Format: %s | Size: %.2fMB", 
                fileFormat, fileSize);
    }
}