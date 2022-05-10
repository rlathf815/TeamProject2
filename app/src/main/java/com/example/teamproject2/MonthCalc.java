package com.example.teamproject2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MonthCalc {

    public int[] calcCal()
    {
        int[] yearMonth = new int[3];//[0]=year, [1]=month, [2]=day
        long now = System.currentTimeMillis();
        Date date = new Date(now);

        SimpleDateFormat curYear = new SimpleDateFormat("yyyy", Locale.getDefault());
        SimpleDateFormat curMonth = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat curDay = new SimpleDateFormat("dd", Locale.getDefault());
        int year = Integer.parseInt(curYear.format(now));
        int month = Integer.parseInt(curMonth.format(now));
        int day = Integer.parseInt(curDay.format(now));
        yearMonth[0]= year;
        yearMonth[1]= month;
        yearMonth[2]= day;
        return yearMonth;
    }
    public int[] calcInfo(int[] yearMonth) {

        int[] info = new int[2]; //info[0]=blank, info[1]=max days
        Calendar cal = Calendar.getInstance();
        cal.set(yearMonth[0], yearMonth[1] - 1, 1);

        int blank = cal.get(Calendar.DAY_OF_WEEK);
        int maxDays =cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        info[0] = blank;
        info[1] = maxDays;
        return info;
    }
    public int calcWeekDay(int[] yearMonth)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(yearMonth[0], yearMonth[1], yearMonth[2]);
        int blank = cal.get(Calendar.DAY_OF_WEEK);
        return blank;
    }

    MonthCalc(){}
}
