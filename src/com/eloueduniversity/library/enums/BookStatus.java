package com.eloueduniversity.library.enums;

public enum BookStatus {
    AVAILABLE("متاح"),
    BORROWED("معار"),
    RESERVED("محجوز");

    private final String arabicName;

    BookStatus(String arabicName) {
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