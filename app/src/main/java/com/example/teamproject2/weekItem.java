package com.example.teamproject2;

public class weekItem {
    String schedule;
    int year, month;
    String day;

    weekItem(String schedule1, int year, int month, String day) {

        this.schedule = schedule1;

        this.year = year;
        this.month = month;

        this.day = day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}

