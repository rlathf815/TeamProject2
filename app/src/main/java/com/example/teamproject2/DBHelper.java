package com.example.teamproject2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {
    final static String TAG="SQLiteDB";

    public DBHelper(Context context) {
        super(context, ScheduleContract.DB_NAME, null, ScheduleContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,getClass().getName()+".onCreate()");
        db.execSQL(ScheduleContract.Schedules.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i(TAG,getClass().getName() +".onUpgrade()");
        db.execSQL(ScheduleContract.Schedules.DELETE_TABLE);
        onCreate(db);
    }
    public void insertSchBySQL(String date, String title, String start, String fin, String loc, String memo) {
        try {
            String sql = String.format (
                    "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) VALUES (NULL, '%s', '%s', '%s', '%s', '%s', '%s')",
                    ScheduleContract.Schedules.TABLE_NAME,
                    ScheduleContract.Schedules._ID,
                    ScheduleContract.Schedules.KEY_DATE,
                    ScheduleContract.Schedules.KEY_TITLE,
                    ScheduleContract.Schedules.KEY_START,
                    ScheduleContract.Schedules.KEY_FIN,
                    ScheduleContract.Schedules.KEY_LOC,
                    ScheduleContract.Schedules.KEY_MEMO,
                    date, title, start, fin, loc, memo);

            getWritableDatabase().execSQL(sql);
            System.out.println("-------------------------------------------------------------------writed-----------------------");


        } catch (SQLException e) {
            Log.e(TAG,"Error in inserting recodes");
        }
    }
    public void deleteSchBySQL(String _id) {
        try {
            String sql = String.format (
                    "DELETE FROM %s WHERE %s = %s",
                    ScheduleContract.Schedules.TABLE_NAME,
                    ScheduleContract.Schedules._ID,
                    _id);
            getWritableDatabase().execSQL(sql);

        } catch (SQLException e) {
            Log.e(TAG,"Error in deleting recodes");
        }
    }

    public void updateSchBySQL(String _id, String date, String title, String start, String fin, String loc, String memo) {
        try {
            String sql = String.format (
                    "UPDATE  %s SET %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s' WHERE %s = %s",
                    ScheduleContract.Schedules.TABLE_NAME,
                    ScheduleContract.Schedules.KEY_DATE, date,
                    ScheduleContract.Schedules.KEY_TITLE, title,
                    ScheduleContract.Schedules.KEY_START, start,
                    ScheduleContract.Schedules.KEY_FIN, fin,
                    ScheduleContract.Schedules.KEY_LOC, loc,
                    ScheduleContract.Schedules.KEY_MEMO, memo,
                    ScheduleContract.Schedules._ID, _id );
            getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG,"Error in updating recodes");
        }
    }
    public Cursor getAllSchBySQL() {
        String sql = "Select * FROM " + ScheduleContract.Schedules.TABLE_NAME;
        return getReadableDatabase().rawQuery(sql,null);
    }
    public long insertSchByMethod(String date, String title, String start, String fin, String loc, String memo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ScheduleContract.Schedules.KEY_DATE, date);
        values.put(ScheduleContract.Schedules.KEY_TITLE, title);
        values.put(ScheduleContract.Schedules.KEY_START, start);
        values.put(ScheduleContract.Schedules.KEY_FIN, fin);
        values.put(ScheduleContract.Schedules.KEY_LOC, loc);
        values.put(ScheduleContract.Schedules.KEY_MEMO, memo);

        return db.insert(ScheduleContract.Schedules.TABLE_NAME,null,values);
    }

    public Cursor getAllSchByMethod() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(ScheduleContract.Schedules.TABLE_NAME,null,null,null,null,null,null);
    }

    public long deleteSchByMethod(String _id) {
        SQLiteDatabase db = getWritableDatabase();

        String whereClause = ScheduleContract.Schedules._ID +" = ?";
        String[] whereArgs ={_id};
        return db.delete(ScheduleContract.Schedules.TABLE_NAME, whereClause, whereArgs);
    }

    public long updateSchByMethod(String _id, String date, String title, String start ,String fin, String loc, String memo) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ScheduleContract.Schedules.KEY_DATE, date);
        values.put(ScheduleContract.Schedules.KEY_TITLE, title);
        values.put(ScheduleContract.Schedules.KEY_START, start);
        values.put(ScheduleContract.Schedules.KEY_FIN, fin);
        values.put(ScheduleContract.Schedules.KEY_LOC, loc);
        values.put(ScheduleContract.Schedules.KEY_MEMO, memo);

        String whereClause = ScheduleContract.Schedules._ID +" = ?";
        String[] whereArgs ={_id};

        return db.update(ScheduleContract.Schedules.TABLE_NAME, values, whereClause, whereArgs);
    }

}


