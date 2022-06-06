package com.example.teamproject2;

import android.provider.BaseColumns;

public class ScheduleContract {
    public static final String DB_NAME="schedule.db";
    public static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private ScheduleContract() {}

    public static class Schedules implements BaseColumns {
        public static final String TABLE_NAME = "Schedules";
        public static final String KEY_DATE = "date";
        public static final String KEY_TITLE = "Title";
        public static final String KEY_START = "Start_time";
        public static final String KEY_FIN = "Finish_time";
        public static final String KEY_LOC = "Location";
        public static final String KEY_MEMO = "Memo";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY" + COMMA_SEP
                + KEY_DATE + TEXT_TYPE + COMMA_SEP
                + KEY_TITLE + TEXT_TYPE + COMMA_SEP
                + KEY_START + TEXT_TYPE + COMMA_SEP
                + KEY_FIN + TEXT_TYPE + COMMA_SEP
                + KEY_LOC + TEXT_TYPE + COMMA_SEP
                + KEY_MEMO + TEXT_TYPE + ")";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}
