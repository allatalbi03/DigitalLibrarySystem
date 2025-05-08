package com.eloueduniversity.library.enums;

public enum BookType {
    PHYSICAL("ورقي"),
    EBOOK("إلكتروني");

    private final String arabicName;

    BookType(String arabicName) {
        this.arabicName = arabicName;
    }

    public String getArabicName() {
        return arabicName;
    }

    @Override
    public String toString() {
        return arabicName;
    }
}
