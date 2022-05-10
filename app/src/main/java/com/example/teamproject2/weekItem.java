package com.example.teamproject2;

public class weekItem {
    String schedule1;
    String schedule2;
    String schedule3;
    int day;



    weekItem(String schedule1, String schedule2, String schedule3, int day) {

        this.schedule1 = schedule1;
        this.schedule2 = schedule2;
        this.schedule3 = schedule3;
        this.day = day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}

