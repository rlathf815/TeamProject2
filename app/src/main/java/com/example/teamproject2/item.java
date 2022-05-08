package com.example.teamproject2;

public class item {
    String day;
    String schedule1;
    String schedule2;
    String schedule3;
    int month;



    item(String day, String schedule1, String schedule2, String schedule3, int month) {
        this.day = day;
        this.schedule1 = schedule1;
        this.schedule2 = schedule2;
        this.schedule3 = schedule3;
        this.month = month;
    }

    public void setDay(String day) {
        this.day = day;
    }
}

