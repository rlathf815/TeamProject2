package com.example.teamproject2;

public class item {
    String day;
    String schedule1;
    String schedule2;
    int month;
    int year;


    item(String day, String schedule1, String schedule2, int month, int year) {
        this.day = day;
        this.schedule1 = schedule1;
        this.schedule2 = schedule2;
        this.month = month;
        this.year = year;
    }

    public void setDay(String day) {
        this.day = day;
    }
}

